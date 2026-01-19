import java.util.*;

public class DP {
    
    static long topDown(int n) {
        long dp[] = new long[n+1];
        Arrays.fill(dp,-1);
        return fibMemo(n, dp);
    }
    
    static long fibMemo(int n , long dp[]){
        int MOD = 1000000007;
        
        if(n == 0) return 0;
        if(n == 1) return 1;
        
        if(dp[n] != -1) return dp[n];
        
        dp[n] = (fibMemo(n-1 , dp) + fibMemo(n-2 , dp)) % MOD ;
        return dp[n];
    }

    static long bottomUp(int n) {
        int MOD = 1000000007;
        
        if(n == 0) return 0;
        if(n == 1) return 1;
       
        long prev2 = 0 , prev1 = 1 , curr = 0;

        for(int i = 2 ; i<= n ; i++){
            curr = (prev2 + prev1) % MOD;
            prev2 = prev1;
            prev1 = curr;
        }

        return curr;
    }


    // Climbing stairs
    public static int climbStairsM(int n) { // Memoization
        int dp[] = new int[n+1];
        Arrays.fill(dp,-1);
        dp[0] = 1;
        dp[1] = 1;

        for(int i=2; i<= n; i++){
            dp[i] = dp[i-1]+ dp[i-2];
        }

        return dp[n];
    }

    public static  int climbStairsT(int n) { // Tabulation
        int dp[] = new int[n+1];
        Arrays.fill(dp,-1);
        dp[0] = 1;
        dp[1] = 1;

        int prev2 = 1;
        int prev1 = 1;

        for(int i = 2 ; i<=n ; i++){
            int curr_i =  prev2+ prev1;
            prev2 = prev1;
            prev1 = curr_i;
        }

        return prev1;
    }

    // Frog Jump
    public static int minCost(int[] height) {
        int n = height.length;
        int dp[] = new int[n+1];
        java.util.Arrays.fill(dp, -1);
        
        return frogJump(n-1 , dp , height);
        
    }
    
    // memoization
    public static int frogJump(int ind , int[] dp , int[] arr){
        if(ind == 0) return 0;
        
        if(dp[ind] != -1) return dp[ind];
        
        int left = frogJump(ind - 1 , dp , arr ) + Math.abs(arr[ind] - arr[ind - 1]);
        int right = Integer.MAX_VALUE;
        if(ind > 1)
        {
         right = frogJump(ind - 2 , dp , arr) + Math.abs(arr[ind] - arr[ind - 2]);
        }
        
        dp[ind] = Math.min(left , right);
        return dp[ind];
    }

    public static int minCostTab(int[] height){
        int n = height.length;
        int prev1 = 0  , prev2 = 0;

        for(int i = 1 ; i< n ; i++){
            int right = Integer.MAX_VALUE;
            int left = prev1 + Math.abs(height[i-1] - height[i]);
            if(i > 1){
                right = prev2 + Math.abs(height[i-2] - height[i]);
            }

            int curr_i = Math.min(left , right);
            prev2 = prev1;
            prev1 = curr_i;
            
        }

        return prev1;
    }
   

    // Frog Jump with K distance
    public static int frogJumpK(int k , int[] height){
        int n = height.length;
        int[] dp = new int[n+1];
        Arrays.fill(dp , -1);
        return frogKTab(height , k , dp ,n-1);
        // return frogK(height , k , dp ,n-1);
    }

    public static int frogK(int[] arr , int k , int dp[] ,int ind){
        if(ind == 0) return 0;

        if(dp[ind] != -1) return dp[ind];
        int minSteps = Integer.MAX_VALUE;

        for(int i = 1 ; i<= k ; i++){
        
            if(ind - i >= 0){
                int jumps = frogK(arr , k , dp , ind - i) + Math.abs(arr[ind - i] - arr[ind]);
                minSteps = Math.min(minSteps , jumps);
            }
           
        }

        return dp[ind] =  minSteps;
    }
    
    public static int frogKTab(int[] arr , int k , int dp[] ,int ind){
        dp[0] = 0;

        for(int i = 1 ; i < arr.length ; i++){
            int mmSteps = Integer.MAX_VALUE;
            
            for(int j = 1 ; j <= k ; j++){
                if (i - j >= 0){
                    int jump = dp[i - j] + Math.abs(arr[i ] - arr[i - j]);
                    mmSteps = Math.min(jump, mmSteps);
                }
            }

            dp[i] = mmSteps;
        }

        return dp[arr.length - 1];
    }
    
    
    
    // Word Break
    public static  boolean wordBreak(String s, String[] dictionary) {
        // TC : O(N * M)  SC : O(N * M) + O(N)
       Set<String> wordSet = new HashSet<>(Arrays.asList(dictionary));
       Map<Integer , Boolean> memo = new HashMap<>();
       int maxLen = 0;
        for(String word : dictionary){
           maxLen = Math.max(maxLen , word.length());
       }
       return findWords(s , wordSet , memo , maxLen ,0);
    }
    
    public static boolean findWords(String s , Set<String> wordSet , Map<Integer , Boolean> memo ,int maxLen, int ind){
        if(ind == s.length()){
            return true;
        }
        
        if(memo.containsKey(ind)){
            return memo.get(ind);
        }
        
        for(int i = ind ; i< s.length()&& (i - ind+1) <= maxLen ; i++){
            String prefix = s.substring(ind , i+1);
            if(wordSet.contains(prefix)){
                if(findWords(s , wordSet , memo , maxLen , i + 1)){
                    memo.put(ind , true);
                    return true;
                }
            }
        }
        
        memo.put(ind , false);
        return false;
    }

    public static boolean wordBreak_U(String s, List<String> wordDict) {
        int n = s.length();
        Boolean dp[] = new Boolean[n + 1];
        Set<String> st = new HashSet<>(wordDict);

        return findWords_U(s , st , dp , 0);
    }

    private static boolean findWords_U(String s , Set<String> st , Boolean dp[] , int ind){
        if(ind == s.length()){
            return true;
        }

        if(dp[ind] != null) return dp[ind];

        for(int i = ind ; i < s.length() ; i++){
            String prefix = s.substring(ind , i+1);
            if(st.contains(prefix)){
                if(findWords_U(s , st , dp, i + 1)){
                    return dp[ind] = true;
                }
            }
        }

        return dp[ind] = false;
    }



    /*
     public static boolean wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        Boolean dp[] = new Boolean[s.length()]; 
        return findWords(s , dict , 0 , dp);
    }

    public static boolean findWords(String s , Set<String> dict  , int ind , Boolean[] dp){
        if(ind == s.length()){
            return true;
        }

        if(dp[ind] != null) return dp[ind];

        for(int i = ind ; i<s.length() ; i++){
            String prefix = s.substring(ind , i+1);

            if(dict.contains(prefix)){
                if(findWords(s , dict , i+1 , dp)){
                    return dp[ind] = true;
                }
            }
        }

        return dp[ind] = false;
    }
    */

    // Decode Ways
    /*
     * memo[i] = memo[i - 1](if single digit is valid) + memo[i - 2](if last 2 digits are valid)
    */
    public int countWaysM(String digits){
        int n = digits.length();
        int memo[] = new int[n+ 1];

        memo[0] = 1;
        memo[1] = (digits.charAt(0) == '0') ? 0 : 1;

        for(int i = 2 ; i <= n ; i++){
            int oneDigit = Integer.parseInt(digits.substring(i-1 , i));
            int twoDigit = Integer.parseInt(digits.substring(i-2 , i));

            if(oneDigit >= 1 && oneDigit <= 9){
                memo[i] += memo[i-1];
            }

            if(twoDigit >= 10 && twoDigit <= 26){
                memo[i] += memo[i - 2];
            }
        }

        return memo[n];
    }

    public int countWaysT(String s) {
    
        int n = s.length();
        if (n == 0) return 0;

        int prev1 = 1;
        int prev2 = (s.charAt(0) == '0') ? 0 : 1;


        for(int i = 2 ; i<=n ; i++){
            int ways = 0;

            int oneDigit = Integer.parseInt(s.substring(i-1 , i));
            int twoDigit = Integer.parseInt(s.substring(i-2 , i));

            if(oneDigit >= 1 && oneDigit <= 9){
                ways += prev2;
            }

            if(twoDigit >= 10 && twoDigit <= 26){
                ways += prev1;
            }

            prev1 = prev2;
            prev2 = ways;
        }

        return prev2;
    }

    // WildCard Pattern Matching
    public static int wildCard(String S1 , String S2){
        // TC : O(N * M)  SC : O(N * M) + O(N)
        int n = S1.length();
        int m = S2.length();

        int memo[][] =  new int[n][m];

        for(int i = 0 ; i<n ; i++){
            Arrays.fill(memo[i] , -1);
        }

        // for (int row[]: memo){
        //     Arrays.fill(row, -1);
        // }

        return isWildCardMatching(S1 , S2 , n-1 , m-1 , memo);
    }

    public static int isWildCardMatching(String S1 , String S2 , int i , int j , int[][] memo){
        if(i<0 && j<0) return 1;

        if(i<0 && j >= 0) return 0;

        if(j<0 && i>=0){
            return isAllStars(S1 , i) ? 1 : 0;
        }

        if(memo[i][j] != -1) return memo[i][j];

        if(S1.charAt(i) == S2.charAt(j) || S1.charAt(i) == '?'){
            return memo[i][j] = isWildCardMatching(S1, S2, i-1, j-1, memo);
        }else{
            if(S1.charAt(i) == '*'){
                // Two possibilities when encountering '*':
                // 1. '*' matches one or more characters in S2.
                // 2. '*' matches zero characters in S2.
                return memo[i][j] = (isWildCardMatching(S1, S2, i-1, j, memo) == 1 || isWildCardMatching(S1, S2, i, j-1, memo) == 1) ? 1:0;
            }else{
                return 0;
            }
        }

    }

    public static boolean isAllStars(String S1 , int i){
        for(int k =0 ; k <= i ; k++){
            if(S1.charAt(k) != '*') return false;
        }

        return true;
    }

    // KnapSack 0/1
    public static int knapsack(int W, int val[], int wt[]) {
        // code here
       int n = wt.length;
       
       int[][] memo = new int[n][W+1];
       
       for(int row[] : memo){
           java.util.Arrays.fill(row , -1);
       }
       
       return knapSackFillMax(wt , val , W, memo , n-1);
        
    }
    
    public static int knapSackFillMax(int [] wt , int val[] , int W , int[][]memo , int ind){
        
        if(ind == 0){
          if(wt[0] <= W) return val[0];
          else return 0;
        }
        
        if(memo[ind][W] != -1) return memo[ind][W];
        
        int notTaken = knapSackFillMax(wt , val , W , memo , ind-1);
        int taken = Integer.MIN_VALUE;
        
        if(wt[ind] <= W){
            taken = val[ind] + knapSackFillMax(wt , val , W-wt[ind] , memo ,ind-1);
        }
        
        return memo[ind][W] = Math.max(notTaken , taken);
    }

    // Subset Sum Problem - True or False
    public static Boolean isSubsetSum(int arr[], int sum) {
        int n = arr.length;
        int s = 0;
        Boolean dp[][] = new Boolean[n+1][sum + 1];
        Boolean check = subsets(arr , n ,s, sum , 0 , dp);
        return check;
    }
    
    public static Boolean subsets(int arr[] , int n , int s ,int sum , int ind , Boolean[][] dp){
        if (s > sum) return false;
        if(ind == n){
            if(s == sum){
                return true;
            }
            return false;
        }
        
        if (dp[ind][s] != null) {
        return dp[ind][s];
    }
        
        s += arr[ind];
        if(subsets(arr , n , s , sum , ind+1 , dp) == true){
            dp[ind][s - arr[ind]] = true;
            return true;
        }
        
        s -= arr[ind];
        if(subsets(arr , n , s , sum , ind+1 , dp) == true){
             dp[ind][s] = true;
             return true;
        }
        
        dp[ind][s] = false;
        return false;
    }

    //  Combination Sum - IV
    public int combinationSum4(int[] nums, int target) {
        int dp[] = new int[target + 1]; 
        Arrays.fill(dp , -1);
        return findComb(nums , target , dp );
    }

    private static int findComb(int arr[] , int k , int[]dp){
        if(k < 0 ) return 0;
        if(k == 0) return 1;

        if(dp[k] != -1) return dp[k];

        int total = 0;

        for(int i = 0 ; i < arr.length; i++){
            total += findComb(arr , k - arr[i] , dp);
        }

        dp[k] = total;
        return dp[k];
    }

    public static void main(String[] args) {
        int n = 697;
        System.out.println("Top Down Result: " + topDown(n));
        System.out.println("Bottom Up Result: " + bottomUp(n));

        // Frog jump k
        int[] heights = {10, 30, 40, 50, 20};
        int k = 3;
        int minCostK = frogJumpK(k, heights); // Expected Output : 30
        System.out.println("Minimum cost with K jumps: " + minCostK);

        // Word break
        boolean isWordbreak = wordBreak("mypenmy" , new String[]{"my","pen"});
        // boolean isWordbreak = wordBreak("catsandog" , new String[]{"cats", "dog", "sand", "and", "cat"});
        System.out.println("\nIs the word break possible with the given dictionary : " + isWordbreak);


        // WildCard Pattern Matching

        // String S1 = "ab*cd";
        // String S2 = "abdefcd";

        String S1 = "ab?d";
        String S2 = "abcd";

        if (wildCard(S1, S2) == 1) System.out.println("Wild Card pattern matching");
        else System.out.println("Wild Card pattern NOT MATCHING");

        // Knapsack Problem
        int W = 5;
        int[] val = {30, 40, 60};
        int[] wt = {3, 2, 4};

        int maxProfit = knapsack(W, val, wt);
        System.out.println("\nMaximum profit in Knapsack: " + maxProfit);

        // Subset Sum Problem
        int[] arr = {3, 34, 4, 12, 5, 2};
        int sum = 9;
        Boolean isSubset = isSubsetSum(arr, sum);
        if (isSubset) {
            System.out.println("Subset with the given sum exists.");
        } else {
            System.out.println("No subset with the given sum exists.");
        }
        

    }
}
