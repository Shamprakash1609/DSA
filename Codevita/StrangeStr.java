import java.util.*;

public class StrangeStr {

    // rearranged
public static long minCostWithRearrangement(String target, int n, String[] pieces, int[] costs) {
    if (target.isEmpty()) return 0;

    Map<Character, Integer> targetFreq = new HashMap<>();
    for (char c : target.toCharArray()) targetFreq.put(c, targetFreq.getOrDefault(c, 0) + 1);

    List<Character> letters = new ArrayList<>(targetFreq.keySet());
    int unique = letters.size();

    int[] limits = new int[unique];
    int totalStates = 1;
    int goalState = 0;
    for (int i = 0; i < unique; i++) {
        limits[i] = targetFreq.get(letters.get(i)) + 1;
        goalState += (limits[i] - 1) * totalStates;
        totalStates *= limits[i];
    }

    List<int[]> pieceCounts = new ArrayList<>();
    for (String piece : pieces) {
        int[] counts = new int[unique];
        for (char c : piece.toCharArray()) {
            int idx = letters.indexOf(c);
            if (idx != -1) counts[idx]++;
        }
        pieceCounts.add(counts);
    }

    // Precompute decoding of all states into counts arrays, to speed up
    int[][] stateToCounts = new int[totalStates][unique];
    for (int s = 0; s < totalStates; s++) {
        int tmp = s;
        for (int i = 0; i < unique; i++) {
            stateToCounts[s][i] = tmp % limits[i];
            tmp /= limits[i];
        }
    }

    long[] dp = new long[totalStates];
    Arrays.fill(dp, Long.MAX_VALUE / 2);
    dp[0] = 0;

    PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));
    pq.add(new long[]{0, 0}); // {cost, state}

    while (!pq.isEmpty()) {
        long[] cur = pq.poll();
        long cost = cur[0];
        int state = (int) cur[1];
        if (dp[state] < cost) continue;
        if (state == goalState) return cost;

        int[] counts = stateToCounts[state];

        for (int i = 0; i < n; i++) {
            int[] nextCounts = new int[unique];
            boolean updated = false;

            for (int j = 0; j < unique; j++) {
                if (counts[j] < limits[j] - 1) {
                    int newCount = Math.min(limits[j] - 1, counts[j] + pieceCounts.get(i)[j]);
                    nextCounts[j] = newCount;
                    if (newCount > counts[j]) updated = true;
                } else {
                    nextCounts[j] = counts[j];
                }
            }

            if (!updated) continue;

            int nextState = 0, mult = 1;
            for (int j = 0; j < unique; j++) {
                nextState += nextCounts[j] * mult;
                mult *= limits[j];
            }

            if (dp[state] + costs[i] < dp[nextState]) {
                dp[nextState] = dp[state] + costs[i];
                pq.add(new long[]{dp[nextState], nextState});
            }
        }
    }
    return dp[goalState];
}


    // no rearrangement
    public static long minCostWithoutRearrangement(String target, int n, String[] pieces, int[] costs) {
        int len = target.length();
        long[] dp = new long[len + 1];
        Arrays.fill(dp, Long.MAX_VALUE / 2);
        dp[len] = 0; 

        for (int i = len - 1; i >= 0; i--) {
            for (int k = 0; k < n; k++) {
                int tIdx = i, pIdx = 0;
                while (tIdx < len && pIdx < pieces[k].length()) {
                    if (target.charAt(tIdx) == pieces[k].charAt(pIdx)) tIdx++;
                    pIdx++;
                }
                if (tIdx > i) {
                    dp[i] = Math.min(dp[i], costs[k] + dp[tIdx]);
                }
            }
        }

        return dp[0];
    }

    //compute the extra cost
    public static long extraCost(String target, int n, String[] pieces, int[] costs) {
        long costRearrange = minCostWithRearrangement(target, n, pieces, costs);
        long costOrdered = minCostWithoutRearrangement(target, n, pieces, costs);
        System.out.println("costRearrange: " + costRearrange);
        System.out.println("costOrdered: " + costOrdered);
        return costOrdered - costRearrange;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String target = sc.nextLine().trim();
        int n = Integer.parseInt(sc.nextLine().trim());

        String[] pieces = null;
        while (true) {
            String line = sc.nextLine().trim();
            pieces = line.split(" ");
            if (pieces.length == n) break;
            if (pieces.length > n)
                pieces = Arrays.copyOf(pieces, n);
            else
                continue; 
        }

        int[] costs = new int[n];
        int idx = 0;
        while (idx < n) {
            String line = sc.nextLine().trim();
            if (!line.isEmpty()) {
                String[] tokens = line.split(" ");
                for (String tok : tokens) {
                    if (idx < n) costs[idx++] = Integer.parseInt(tok);
                }
            }
        }

        System.out.println(extraCost(target, n, pieces, costs));
    }



}

