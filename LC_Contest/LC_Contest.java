import java.util.*;

public class LC_Contest {

    // Restore Finishing Order {E} LC:Contest(456)
    public static int[] recoverOrder(int[] order, int[] friends) {
        int n = order.length;

        Set<Integer> st = new HashSet<>();

        for(int f : friends){
            st.add(f);
        }

        int ind = 0;
        int ans[] = new int[friends.length];
        for(int i = 0 ; i < n; i++){
            if(st.contains(order[i])){
                ans[ind] = order[i];
                ind++;
            }
        }

        return ans;
    }
    
    // Balanced K-Factor Decomposition {M} LC:Contest(456)
    public static int[] minDifference(int n, int k) {

        List<Integer> bestRes = new ArrayList<>();
        findFactors(n , k ,  new ArrayList<Integer>() , bestRes);

        int[] res = new int[bestRes.size()];
        for(int i = 0 ; i < bestRes.size() ; i++){
            res[i] = bestRes.get(i);
        }

        return res;
    }

    private static void findFactors(int n , int k , List<Integer> ds , List<Integer> bestRes){
        if(k == 0){
            if(n == 1){
                int currDiff = Collections.max(ds) - Collections.min(ds);
                if(bestRes.isEmpty() || currDiff < (Collections.max(bestRes) - Collections.min(bestRes)) ){
                    bestRes.clear();
                    bestRes.addAll(ds);
                }
            }
            return;
        }

        // Find all possible divisors of k
        for(int f = 1 ; f <= n ; f++){
            if(n % f == 0){
                ds.add(f);
                findFactors(n / f , k - 1 , ds , bestRes);
                ds.remove(ds.size() - 1);
            }
        }
    }


    // 

    public static void main(String[] args) {

        // Restore Finishing Order
        int[] order = {5, 3, 1, 4, 2};
        int[] friends = {3, 2, 5};
        int[] restoredOrder = recoverOrder(order, friends);
        System.out.println("Restored Order: " + Arrays.toString(restoredOrder));

        // Balanced K-Factor Decomposition 
        int n = 100;
        int k = 2;
        int[] result = minDifference(n, k);
        System.out.println("Result: " + Arrays.toString(result));



    }

}
