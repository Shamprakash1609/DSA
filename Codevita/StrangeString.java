import java.util.*;

public class StrangeString {

    /**
     * Calculates the minimum cost assuming letters can be rearranged.
     * This is a shortest path problem on a state graph, solved with Dijkstra's algorithm.
     * A state represents the counts of each required character collected so far.
     */
    public static long calculateCost_Rearrange(String strangeString, int n, String[] pieces, int[] costs) {
        if (strangeString.isEmpty()) {
            return 0;
        }

        // 1. Determine character requirements
        Map<Character, Integer> targetFreq = new HashMap<>();
        for (char c : strangeString.toCharArray()) {
            targetFreq.put(c, targetFreq.getOrDefault(c, 0) + 1);
        }

        List<Character> charMap = new ArrayList<>(targetFreq.keySet());
        int uniqueChars = charMap.size();
        int[] bases = new int[uniqueChars];
        int targetState = 0;
        int stateSpaceSize = 1;

        for (int i = 0; i < uniqueChars; i++) {
            bases[i] = targetFreq.get(charMap.get(i)) + 1;
            targetState += (bases[i] - 1) * stateSpaceSize;
            stateSpaceSize *= bases[i];
        }
        
        // Pre-calculate character counts for each piece
        List<int[]> pieceFreqs = new ArrayList<>();
        for(String piece : pieces) {
            int[] freqs = new int[uniqueChars];
            for(char c : piece.toCharArray()) {
                int idx = charMap.indexOf(c);
                if(idx != -1) {
                    freqs[idx]++;
                }
            }
            pieceFreqs.add(freqs);
        }

        // 2. Dijkstra's Algorithm
        long[] dist = new long[stateSpaceSize];
        Arrays.fill(dist, Long.MAX_VALUE / 2);
        dist[0] = 0;

        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));
        pq.add(new long[]{0, 0}); // {cost, state}

        while (!pq.isEmpty()) {
            long[] current = pq.poll();
            long cost = current[0];
            int u_state = (int) current[1];

            if (cost > dist[u_state]) {
                continue;
            }
            if (u_state == targetState) {
                return cost;
            }

            // Decode current state
            int[] u_counts = new int[uniqueChars];
            int temp_u_state = u_state;
            for(int i = 0; i < uniqueChars; i++) {
                u_counts[i] = temp_u_state % bases[i];
                temp_u_state /= bases[i];
            }

            // 3. Try adding each piece
            for (int i = 0; i < n; i++) {
                int[] v_counts = Arrays.copyOf(u_counts, uniqueChars);
                boolean changed = false;
                for(int j=0; j<uniqueChars; j++) {
                    if(v_counts[j] < bases[j] - 1) { // If we still need this character
                        int newCount = Math.min(bases[j] - 1, v_counts[j] + pieceFreqs.get(i)[j]);
                        if(newCount > v_counts[j]) {
                            v_counts[j] = newCount;
                            changed = true;
                        }
                    }
                }

                if(!changed) continue;

                // Encode new state
                int v_state = 0;
                int power = 1;
                for(int j=0; j<uniqueChars; j++) {
                    v_state += v_counts[j] * power;
                    power *= bases[j];
                }

                if (dist[u_state] + costs[i] < dist[v_state]) {
                    dist[v_state] = dist[u_state] + costs[i];
                    pq.add(new long[]{dist[v_state], v_state});
                }
            }
        }

        return dist[targetState];
    }

    /**
     * Calculates the minimum cost where the order of letters in pieces is fixed.
     * DP state: dp[i] = min cost to build the suffix of the target string starting from index i.
     */
    public static long calculateCost_NoRearrange_T(String strangeString, int n, String[] pieces, int[] costs) {
        int sLen = strangeString.length();
        if (sLen == 0) {
            return 0;
        }

        long[] dp = new long[sLen + 1];
        Arrays.fill(dp, Long.MAX_VALUE / 2);
        dp[sLen] = 0; // Base case: cost to build an empty suffix is 0.

        for (int i = sLen - 1; i >= 0; i--) {
            // For each starting position i, try to cover it with each available piece.
            for (int k = 0; k < n; k++) {
                int targetIndex = i;
                int pieceIndex = 0;

                // Find how far this piece can extend the subsequence match.
                while (targetIndex < sLen && pieceIndex < pieces[k].length()) {
                    if (strangeString.charAt(targetIndex) == pieces[k].charAt(pieceIndex)) {
                        targetIndex++;
                    }
                    pieceIndex++;
                }

                // If the piece covered at least one character (i.e., targetIndex moved forward)
                if (targetIndex > i) {
                    // The cost from i is the cost of this piece + the pre-calculated cost for the rest.
                    dp[i] = Math.min(dp[i], (long)costs[k] + dp[targetIndex]);
                }
            }
        }

        return dp[0];
    }
    
    public static long solveStrangeString(String strangeString, int n, String[] pieces, int[] costs) {
        long costWithRearrangement = calculateCost_Rearrange(strangeString, n, pieces, costs);
        long costWithoutRearrangement = calculateCost_NoRearrange_T(strangeString, n, pieces, costs);
        
        if (costWithRearrangement >= Long.MAX_VALUE / 2 || costWithoutRearrangement >= Long.MAX_VALUE / 2) {
            return -1; // Indicates impossible
        }
        
        return costWithoutRearrangement - costWithRearrangement;
    }

    public static void main(String[] args) {
        // Hardcoded input from Example 1
        String strangeString = "gowiththeflow";
        int n = 8;
        String[] pieces = {"cat", "and", "goat", "wins", "the", "game", "of", "life"};
        int[] costs = {2, 5, 1, 4, 8, 3, 1, 11};

        // Solve the problem
        long costA = calculateCost_Rearrange(strangeString, n, pieces, costs);
        long costB = calculateCost_NoRearrange_T(strangeString, n, pieces, costs);
        long result = costB - costA;

        System.out.println("Cost with rearrangement (DP): " + costA);
        System.out.println("Cost without rearrangement (DP): " + costB);
        System.out.println("The additional cost is: " + result);
    }
}