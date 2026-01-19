import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DP2 {

    // House Robber - I
    public static  int houseRobberI_M(int[] nums) {
        int n = nums.length;
        int dp[] = new int[n+1];
        Arrays.fill(dp , -1);
        return robberyI(nums ,dp ,n-1);
    }

    public static  int robberyI(int[] nums , int[] dp ,int ind){
        if(ind == 0 ) return nums[ind];
        if(ind < 0) return 0;

        if(dp[ind] != -1) return dp[ind];

        int l = nums[ind] + robberyI(nums , dp , ind - 2);
        int r = 0 + robberyI(nums , dp ,  ind - 1);

        dp[ind] = Math.max(l , r);
        return dp[ind];
    }

    public static  int houseRobberI_T(int[] nums) {
        int n = nums.length;
        int dp[] = new int[n];

        dp[0] = nums[0];

        for(int i = 1 ; i<n ; i++){
            int left = nums[i]; 
            if( i > 1){
                left += dp[i-2];
            }

            int right = 0 + dp[i-1];

            dp[i] = Math.max(left , right);
        }

        return dp[n-1];
    }

    public int houseRobberI_TS(int[] nums) {
        int n = nums.length;
        
        int prev1 = nums[0];
        int prev2 = 0;
        int curr = 0;

        for(int i = 1 ; i<n ; i++){
            int left = nums[i];
            if( i > 1){
                left += prev2;
            }

            int right = 0 + prev1;

            curr = Math.max(left , right);
            prev2 = prev1;
            prev1 = curr;

        }

        return prev1;
    }

    // House Robber - II
    public static int houseRobberII_TS(int[] nums) {
        int n = nums.length;
        if(n == 1) return nums[0];
        int temp1[] = new int[n];
        int temp2[] = new int[n];

        for(int i = 0 ; i<n ; i++){
            if(i != 0) temp1[i] = nums[i];
            if(i != n-1) temp2[i] = nums[i];
        }

        int maxRob = Math.max(robberyII(temp1) , robberyII(temp2));

        return maxRob;
    }

    public static int robberyII(int nums[]){
        int n = nums.length;

        int prev1 = nums[0];
        int prev2 = 0;
        int curr = 0;

        for(int i = 0 ; i<n ; i++){
            int left = nums[i];
            if(i > 1){
                left += prev2;
            }

            int right = 0 + prev1;

            curr = Math.max(left , right);
            prev2 = prev1;
            prev1 = curr;
        }

        return prev1;
    }

////////////////////////////////////////////////////////////////////////// DP ON GRIDS \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

    // Ninja Training
    public static  int maximumPoints(int arr[][]) {
        int n = arr.length;
        int last = 3;
        int dp[][] = new int[n][4];
        
        for(int[] row : dp){
            Arrays.fill(row , -1);
        }
        
        return getMerit(arr , dp ,last , n-1);
    }
    
    public static int getMerit(int[][] arr , int[][] dp , int last , int day){
        if(day == 0){
            int maxi = 0;
            for(int i = 0 ; i < 3 ; i++){
                if(i != last){
                    maxi = Math.max(maxi , arr[0][i]);
                }
            }
            
            return maxi;
        }
        
        if(dp[day][last] != -1) return dp[day][last];
        
        int maxi = 0;
        for(int i = 0 ; i<3 ; i++){
            if(i != last){
                int points = arr[day][i] + getMerit(arr , dp ,i , day-1);
                maxi = Math.max(maxi , points);
            }
        }
        
        return dp[day][last] = maxi;
    }
    
    public static int ninjaTraining(int n, int points[][]) {
        int dp[][] = new int[n][4];
        for(int[] row : dp){
            java.util.Arrays.fill(row , -1);
        }


        dp[0][0] = Math.max(points[0][1] , points[0][2]);
        dp[0][1] = Math.max(points[0][0] , points[0][2]);
        dp[0][2] = Math.max(points[0][0] , points[0][1]);
        dp[0][3] = Math.max(points[0][0],Math.max(points[0][1],points[0][2]));


        
        for(int day = 1 ; day < n ; day++){
            for(int last = 0 ; last < 4 ; last++){
                dp[day][last] = 0;
                int maxi = Integer.MIN_VALUE;
                for(int task = 0 ; task < 3 ; task++){ 
                    if(task != last){
                        int pts = points[day][task] + dp[day - 1][task];
                        maxi = Math.max(pts , maxi);
                    }
                }
                dp[day][last] = maxi;
            }

            
        }

        return dp[n-1][3];
    }
    

    // Unique paths
    public static  int uniquePaths_M(int m, int n) {
        int dp[][] = new int[m][n];
        for(int[] row : dp){
            Arrays.fill(row , -1);
        }
        return cntPathsI(dp ,m-1 , n-1);
    }

    public static  int cntPathsI(int dp[][] ,int i , int j){
        if(i == 0 && j == 0){
            return 1;
        }

        if(i < 0 || j < 0){
            return 0;
        }

        if(dp[i][j] != -1) return dp[i][j];

        int l = cntPathsI(dp , i-1 , j);
        int r = cntPathsI(dp , i, j-1);

        return dp[i][j] = l+r;
    }

    public static int uniquePaths_T(int m, int n) {
        int dp[][] = new int[m][n];

        for(int i = 0 ; i<m ; i++){
            for(int j = 0 ; j<n ; j++){
                if (i == 0 && j == 0) {
                    dp[i][j] = 1;
                    continue;
                }

                int left = 0;
                int up = 0;

                if(i > 0)up = dp[i-1][j];
                if(j > 0)left = dp[i][j-1];

                dp[i][j] = up + left;
            }

        }

        return dp[m-1][n-1];
    }

    public static int uniquePaths_TS(int m, int n){
        int prev[] = new int[n];

        for(int i = 0 ; i<m ; i++){
                int temp[] = new int[n];
            for(int j = 0 ; j<n ; j++){
                if (i == 0 && j == 0) {
                    temp[j] = 1;
                    continue;
                }

                int left = 0;
                int up = 0;

                if(i > 0)up = prev[j];
                if(j > 0)left = temp[j-1];

                temp[j] = left + up;

            }

            prev = temp;

        }

        return prev[n-1];
    }


    // Unique paths - II
    public static int uniquePathsWithObstacles_M(int[][] obstacleGrid) {
        int n = obstacleGrid.length;
        int m = obstacleGrid[0].length;

        int dp[][] = new int[n][m];
        for(int[] row : dp){
            Arrays.fill(row , -1);
        }

        return cntPathsII(obstacleGrid , dp , n-1 , m-1);
    }

    public static int cntPathsII(int arr[][] , int dp[][] ,int i , int j){
        if(i >= 0 && j >= 0 && arr[i][j] == 1) return 0;
        if(i== 0 && j==0) return 1;
        if(i<0 || j<0) return 0;

        if(dp[i][j] != -1) return dp[i][j];

        int up = cntPathsII(arr , dp ,i-1 ,j);
        int left = cntPathsII(arr , dp ,i , j-1);

        return dp[i][j] = up + left;
    }

    public static int uniquePathsWithObstacles_T(int[][] obstacleGrid) {
        int n = obstacleGrid.length;
        int m = obstacleGrid[0].length;

        int dp[][] = new int[n][m];
        
        for(int i = 0 ; i<n ; i++){
            for(int j = 0 ; j<m ; j++){

                if(obstacleGrid[i][j] == 1){
                    dp[i][j] = 0;
                    continue;
                }

                if(i == 0 && j == 0){
                    dp[i][j] = 1;
                    continue;
                }


                int left = 0;
                int up = 0;

                if(i > 0){
                    up = dp[i-1][j];
                }

                if(j > 0){
                    left = dp[i][j-1];
                }

                dp[i][j] = up + left;

            }

           
        }

        return dp[n-1][m-1];
    }

    public static int uniquePathsWithObstacles_TS(int[][] arr) {
        int n = arr.length;
        int m = arr[0].length;

        int[] prev = new int[m];

        for(int i = 0 ; i<n ; i++){
            int[] temp = new int[m];
            for(int j = 0 ; j<m ; j++){
                if(arr[i][j] == 1){
                    temp[j] = 0;
                    continue;
                }
                if(i == 0 && j == 0 ){
                    temp[j] = 1;
                    continue;
                }


                int left = 0;
                int up = 0;

                if(i > 0){
                    up = prev[j];
                }

                if(j > 0){
                    left = temp[j-1];
                }

                temp[j] = up + left;

            }

            prev = temp;

        }

        return prev[m-1];
    }

    // Minimum Path Sum
    public static  int minPathSum(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int dp[][] = new int[n][m];

        for(int[] row : dp){
            Arrays.fill(row , -1);
        }
        return minCost(grid , dp ,  n-1 , m-1);
    }

    public static int minCost(int grid[][] , int dp[][] , int i , int j){
        if(i == 0 && j == 0){
            return grid[0][0];
        }

        if(i < 0 || j < 0) return 1000000009;

        if(dp[i][j] != -1) return dp[i][j];

        int left = grid[i][j] + minCost(grid ,dp , i , j-1);
        int up = grid[i][j] + minCost(grid , dp , i-1 , j);

        return dp[i][j] = Math.min(left, up);
    }

    // Minimum Path sum Triangle
    public static  int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int m = triangle.get(n-1).size();
        int dp[][] = new int[n][m];
        for(int[] row : dp){
            Arrays.fill(row , -1);
        }
        return minLen(triangle , dp , 0 , 0);
    }

    public  static int minLen(List<List<Integer>> triangle ,int dp [][], int i , int j){
        if(i == triangle.size() - 1) return triangle.get(triangle.size()-1).get(j);

        if(dp[i][j] != -1) return dp[i][j];

        int down = triangle.get(i).get(j) + minLen(triangle , dp , i+1 , j);
        int diagonal = triangle.get(i).get(j) + minLen(triangle , dp , i+1 ,j+1);

        return dp[i][j] = Math.min(down , diagonal);
    }


    // Minimum/Maximum falling pathsum
    public static  int minFallingPathSum_M(int[][] matrix){
        int n = matrix.length;
        int m = matrix[0].length;

        int dp[][] = new int[n][m];
        for(int row[] : dp){
            Arrays.fill(row , -1);
        }


        int minPath = Integer.MAX_VALUE;
        for(int j = 0 ; j<m ; j++){
            minPath = Math.min(minPath , findMinPath(matrix , dp , n-1 , j));
        }

        
        return minPath;
    }

    public static  int findMinPath(int matrix[][] , int dp[][] , int i , int j){
        if( j < 0 || j >= matrix[0].length) return 1000000009;
        if(i == 0){
            return matrix[0][j];
        }

        if(dp[i][j] != -1) return dp[i][j];

        int up = matrix[i][j] + findMinPath(matrix , dp , i-1 , j);
        int upLeft = matrix[i][j] + findMinPath(matrix , dp ,  i-1 , j-1);
        int upRight = matrix[i][j] + findMinPath(matrix , dp , i-1 ,j+1);

        return dp[i][j] =  Math.min(up , Math.min(upLeft , upRight));
    }

    public int minFallingPathSum_T(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;

        int dp[][] = new int[n][m];
        for(int row[] : dp){
            Arrays.fill(row , -1);
        }

        for(int j = 0 ;  j<m ; j++){
            dp[0][j] = matrix[0][j];
        }

        for(int i = 1 ; i<n ; i++){
            for(int j = 0 ; j<m ; j++){

                int up = matrix[i][j] + dp[i-1][j];

                int upLeft = Integer.MAX_VALUE;
                int upRight = Integer.MAX_VALUE;

                if(j-1 >= 0) upLeft = matrix[i][j] +  dp[i-1][j-1];
                if(j+1 < m) upRight = matrix[i][j] + dp[i-1][j+1];

                dp[i][j] = Math.min(up , Math.min(upLeft , upRight));


            }
        }

        int ans = dp[n-1][0];
        for(int j = 0 ; j< m ; j++){
            ans = Math.min(ans,dp[n-1][j]);
        }
        
        return ans;
    }

    public int minFallingPathSum_TS(int[][] matrix){
        int n = matrix.length;
        int m = matrix[0].length;

        int prev[] = new int[m];
        Arrays.fill(prev , -1);

        for(int j = 0 ;  j<m ; j++){
            prev[j] = matrix[0][j];
        }

        for(int i = 1 ; i<n ; i++){
            int[] temp = new int[m];
            for(int j = 0 ; j<m ; j++){

                int up = matrix[i][j] + prev[j];

                int upLeft = Integer.MAX_VALUE;
                int upRight = Integer.MAX_VALUE;

                if(j-1 >= 0) upLeft = matrix[i][j] +  prev[j-1];
                if(j+1 < m) upRight = matrix[i][j] + prev[j+1];

                temp[j] = Math.min(up , Math.min(upLeft , upRight));

            }

            prev = temp;
        }

        int ans = prev[0];
        for(int j = 1 ; j< m ; j++){
            ans = Math.min(ans,prev[j]);
        }
        

        
        return ans;
    }

////////////////////////////////////////////////////////////////// DP on Subsequences / Subsets \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

    // Subset sum equals target
    public static Boolean isSubsetSum_M(int arr[], int sum) {
        int n = arr.length;
        // int s = 0;
        Boolean dp[][] = new Boolean[n+1][sum + 1];
        Boolean check = subsets(arr,sum , n-1 , dp );
        return check;
    }
    
    public static Boolean subsets(int arr[] , int sum , int ind , Boolean dp[][]){

        if (ind < 0) return false;
        if(sum == 0){
            return true;
        }
    
        if(ind == 0){
            if(arr[0] == sum) return true;
        }
        
        if(dp[ind][sum] != null) return dp[ind][sum];
        
        Boolean notTake = subsets(arr , sum , ind-1 ,dp);
        Boolean take = false;
        if(arr[ind] <= sum){
            take = subsets(arr , sum-arr[ind] , ind-1 , dp);
        }
        
        return dp[ind][sum] = take || notTake;
    }

    public static Boolean isSubsetSum_T(int arr[], int sum) {
        int n = arr.length;
        Boolean dp[][] = new Boolean[n+1][sum + 1];
        
        for(Boolean row[] : dp){
            Arrays.fill(row , false);
        }
        
        for(int i = 0 ; i < n ; i++){
            dp[i][0] = true;
        }
        
        if(arr[0] <= sum){
            dp[0][arr[0]] = true;
        }
        
        
        for(int ind = 1 ; ind <n ; ind ++){
            for(int t = 1 ; t <= sum ; t++){
                
                Boolean notTake = dp[ind - 1][t];
                Boolean take = false;
                if(arr[ind] <= t){
                    take = dp[ind-1][t - arr[ind]];
                }
                
                dp[ind][t] = take || notTake; 
            }
            
        }
        
        return dp[n-1][sum];
    }

    public static Boolean isSubsetSum_TS(int arr[], int sum) {
        int n = arr.length;
        int s = 0;
        Boolean prev[] = new Boolean[sum + 1];
        
        Arrays.fill(prev , false);
        
        prev[0]  = true;
        
        if(arr[0] <= sum){
            prev[arr[0]] = true;
        }
        
        
        for(int ind = 1 ; ind <n ; ind ++){
            Boolean temp[] = new Boolean[sum + 1];
            Arrays.fill(temp, false);
            temp[0] = true;
            for(int t = 1 ; t <= sum ; t++){
                
                Boolean notTake = prev[t];
                Boolean take = false;
                if(arr[ind] <= t){
                    take = prev[t - arr[ind]];
                }
                
                temp[t] = take || notTake; 
            }
            
            prev = temp;
            
        }
        
        return prev[sum];
    }

    // Partition Equal Subset Sum
    public static boolean canPartition(int[] nums) {
        int n = nums.length;

        int totSum = 0;
        for(int i = 0 ; i<n ; i++){
            totSum += nums[i];
        }

        if(totSum % 2 == 1) return false;
        
        return isSubsetSum_T(nums , totSum/2);
    }

    // Partition Array Into Two Arrays to Minimize Sum Difference
    public static int minDifference(int arr[]) {
        int n = arr.length;
        int totSum = 0;
        for(int i = 0 ; i<n ; i++){
            totSum += arr[i];
        }

        boolean dp[][] = new boolean[n][totSum + 1];

        for (int i = 0; i < n; i++) {
            dp[i][0] = true;
        }

        if(arr[0] >= 0 && arr[0] <= totSum){
            dp[0][arr[0]] = true;
        }

        for(int ind = 1 ; ind < n ; ind++){
            for(int t=1 ; t <= totSum ; t++){
                boolean notTake = dp[ind-1][t];
                boolean take = false;
                if(arr[ind] <= t )take = dp[ind-1][t-arr[ind]]; 
                
                dp[ind][t] = take || notTake;
            }
        }
    
        int minAbs = Integer.MAX_VALUE;

        for(int s1 = 0 ; s1 <= totSum ; s1++){
            if(dp[n - 1][s1] == true){
                 int s2 = totSum - s1;
                minAbs = Math.min(minAbs , Math.abs(s2- s1));
            }
        }

        return minAbs;
    }

    // Perfect Sum Problem
    public static int perfectSum_M(int[] nums, int target) {
        
        int n = nums.length;
        int[][] dp = new int[n+1][target+1];
        
        for(int row[] : dp){
            Arrays.fill(row , -1);
        }
        int cnt = cntSums(nums , n-1 , target , dp);
        
        return cnt;
    }
    
    public static int cntSums(int[] arr , int ind , int t , int dp[][]){
        if(ind == 0){
            if (t == 0 && arr[0] == 0) return 2; 
            if (t == 0 || arr[0] == t) return 1;
        }
        
        
        if(dp[ind][t] != -1) return dp[ind][t];
        
        int notPick = cntSums(arr , ind - 1 , t , dp);
        int pick = 0;
        if(arr[ind] <= t){
            pick = cntSums(arr , ind-1 , t-arr[ind] , dp);
        }
        
        return dp[ind][t] = (notPick + pick);
    }

    public static int perfectSum_T(int[] arr, int target) {
        
        int n = arr.length;
        int[][] dp = new int[n][target+1];
        
    
       if(arr[0] == 0){
           dp[0][0] = 2;
       }else{
           dp[0][0] = 1;
           if(arr[0] <= target){
               dp[0][arr[0]] = 1;
           }
           
       }
        
        for(int i = 1; i<n ; i++){
            for(int t = 0 ; t <= target ; t++){
                
                int notTake = dp[i-1][t];
                int take = 0;
                if(arr[i] <= t){
                    take = dp[i-1][t-arr[i]];
                }
                
                dp[i][t] = take + notTake;
            }
        }
        
        return dp[n-1][target];
    
    }

    // 0/1 KnapSack problem
    public static int knapsack01_M(int capacity , int val[], int wt[]) {
        int n = val.length;
        int dp[][] = new int[n][capacity+1];
        for(int row[] : dp){
            java.util.Arrays.fill(row , -1);
        }

        return knapSackMax(val , wt , dp ,capacity , n-1);
    }

    public static int knapSackMax(int[] val , int wt[] , int[][] dp ,int W , int ind){
        if(ind == 0){
            if(wt[ind] <= W){
                return val[ind];
            }
            else return 0;
        }
        
        if(dp[ind][W] != -1) return dp[ind][W];
        
        int notTake = 0 + knapSackMax(val , wt , dp ,W , ind-1);
        int take = 0;
        if(wt[ind] <= W){
            take = val[ind] + knapSackMax(val , wt , dp ,W-wt[ind] , ind-1);
        }
        
        return dp[ind][W] = Math.max(notTake , take);
    }

    public static int knapsack01_T(int capacity , int val[], int wt[]) {
        int n = val.length;
        int dp[][] = new int[n][capacity+1];

        
        for(int j = wt[0] ; j<= capacity ; j++){
            dp[0][j] = val[0];
        }
        
        for(int i = 1 ; i<n ; i++){
            for(int W = 0 ; W <= capacity ; W++){
                int notTake = 0 + dp[i-1][W];
                int take = 0;
                if(wt[i] <= W){
                    take = val[i] + dp[i-1][W - wt[i]];
                }
                
                dp[i][W] = Math.max(notTake , take); 
            }
        }
        
        return dp[n-1][capacity];
        
    }

    public static int knapsack_TS(int capacity , int val[], int wt[]) {
        int n = val.length;
        // int dp[][] = new int[n][capacity+1];
        int prev[] = new int[capacity+1];

        
        for(int j = wt[0] ; j<= capacity ; j++){
                prev[j] = val[0];
        }
        
        for(int i = 1 ; i<n ; i++){
            int temp[] = new int[capacity+1];
            for(int W = 0 ; W <= capacity ; W++){
                int notTake = 0 + prev[W];
                int take = 0;
                if(wt[i] <= W){
                    take = val[i] + prev[W - wt[i]];
                }
                
                temp[W] = Math.max(notTake , take); 
            }
            
            prev = temp;
        }
        
        return prev[capacity];
        
    }

    // Coin Change - I
    public static  int coinChangeI_M(int[] coins, int amount) {
        int n = coins.length;
        int dp[][] = new int[n][amount+1];
        for(int row[] : dp){
            Arrays.fill(row , -1);
        }
        int res = exchange(coins , amount , dp , n-1);
        return (res >= 1000000000) ? -1 : res;
    }

    public static int exchange(int coins[] , int t , int[][] dp ,int ind){

        if(ind == 0){
            if(t % coins[ind]  == 0){
                return t/coins[ind];
            }
            else return 1000000009;
        }

        if(dp[ind][t] != -1) return dp[ind][t];

        int notTake = 0 + exchange(coins , t , dp , ind-1);
        int take = Integer.MAX_VALUE;

        if(coins[ind] <= t){
            take = 1 + exchange(coins , t-coins[ind] , dp , ind);
        }

        return dp[ind][t] = Math.min(notTake , take);
    }

    public static int coinChangeI_T(int[] coins, int amount) {
        int n = coins.length;
        int dp[][] = new int[n][amount+1];
        // for(int row[] : dp){
        //     Arrays.fill(row , -1);
        // }

        for(int t = 0 ; t <= amount ; t++){
            if( t % coins[0] == 0){
                dp[0][t] = t / coins[0];
            }
            else{
                dp[0][t] = 1000000009;
            }
        }

        for(int i = 1 ; i<n ; i++){
            for(int t = 0 ; t <= amount ; t++){
                int notTake = 0 + dp[i-1][t];
                int take = Integer.MAX_VALUE;

                if(coins[i] <= t){
                    take = 1 + dp[i][t-coins[i]];
                }

                dp[i][t] = Math.min(notTake , take);
            }
        }

        int res = dp[n-1][amount];
        return (res >= 1000000000) ? -1 : res;
    }

    public static int coinChangeI_TS(int[] coins, int amount) {
        int n = coins.length;
        int prev[] = new int[amount+1];

        for(int t = 0 ; t <= amount ; t++){
            if( t % coins[0] == 0){
                prev[t] = t / coins[0];
            }
            else{
                prev[t] = 1000000009;
            }
        }

        for(int i = 1 ; i<n ; i++){
            int temp[] = new int[amount + 1];
            for(int t = 0 ; t <= amount ; t++){
                int notTake = 0 + prev[t];
                int take = Integer.MAX_VALUE;

                if(coins[i] <= t){
                    take = 1 + temp[t-coins[i]];
                }

                temp[t] = Math.min(notTake , take);
            }

            prev = temp;
        }

        int res = prev[amount];
        return (res >= 1000000000) ? -1 : res;
    }

    // Coin Change - II
    public static int coinChangeII_M(int amount, int[] coins) {
        int n = coins.length;
        int dp[][] = new int[n][amount+1];
        for(int[] row : dp){
            Arrays.fill(row , -1);
        }
        return cntExchanges(coins , amount , dp , n-1);
    }

    public static  int cntExchanges(int coins[] , int t , int[][] dp ,int ind){

        if(ind == 0){
            if(t % coins[ind] == 0){
                return 1;
            }
            else return 0;
        }

        if(dp[ind][t] != -1) return dp[ind][t];

        int notTake = cntExchanges(coins , t , dp , ind-1);
        int take = 0;
        if(coins[ind] <= t){
            take = cntExchanges(coins , t-coins[ind] , dp , ind);
        }

        return dp[ind][t] = notTake + take;
    }

    public static int coinChangeII_T(int amount, int[] coins) {
        int n = coins.length;
        int dp[][] = new int[n][amount+1];
    

        for(int t = 0 ; t <= amount ; t++){
            if(t % coins[0] == 0){
                dp[0][t] = 1;
            }else{
                dp[0][t] = 0;
            }
        }

        for(int i = 1 ; i<n ; i++){
            for(int t = 0 ; t <= amount ; t++){
                int notTake = dp[i-1][t];
                int take = 0;
                if(coins[i] <= t){
                    take = dp[i][t-coins[i]];
                }

                dp[i][t] = notTake + take;
            }
        }

        return dp[n-1][amount];

    }
 
    public static int coinChangeII_TS(int amount, int[] coins) {
        int n = coins.length;
        // int dp[][] = new int[n][amount+1];
        int prev[] = new int[amount+1];
    

        for(int t = 0 ; t <= amount ; t++){
            if(t % coins[0] == 0){
                prev[t] = 1;
            }else{
                prev[t] = 0;
            }
        }

        for(int i = 1 ; i<n ; i++){
            int temp[] = new int[amount+1];
            for(int t = 0 ; t <= amount ; t++){
                int notTake = prev[t];
                int take = 0;
                if(coins[i] <= t){
                    take = temp[t-coins[i]];
                }

                temp[t] = notTake + take;
            }

            prev = temp;
        }

        return prev[amount];

    }

    // Unbounded KnapSack
    public static int UnboundedKnapSack_M(int val[], int wt[], int capacity) {
        int n = wt.length;
        int dp[][] = new int[n][capacity+1];
        for(int[]row : dp){
            Arrays.fill(row , -1);
        }
        
        return knapSackFill(val , wt , dp , capacity , n-1);
    }
    
    public static int knapSackFill(int val[] , int wt[] ,int[][] dp,int W , int ind){
        
         if(ind == 0){
            if(wt[ind] <= W){
                return (W/wt[0]) * val[0];
            }
            else return 0;
        }
        
        if(dp[ind][W] != -1) return dp[ind][W];
        
        int notTake = 0 + knapSackFill(val , wt , dp ,W , ind-1);
        int take = 0;
        if(wt[ind] <= W){
            take = val[ind] + knapSackFill(val , wt , dp ,W-wt[ind] , ind);
        }
        
        return dp[ind][W] = Math.max(notTake , take);
        
    }
    
    public static int UnboundedKnapSack_T(int val[], int wt[], int capacity) {
        int n = wt.length;
        int dp[][] = new int[n][capacity+1];
        
        
        for(int t = 0 ; t <= capacity ; t++){
            dp[0][t] = (t/wt[0]) * val[0];
        }
        
        for(int i = 1 ; i<n ; i++){
            for(int t = 0 ; t <= capacity ; t++){
                int notTake = 0 + dp[i-1][t];
                int take = Integer.MIN_VALUE;
                if(wt[i] <= t){
                    take = val[i] + dp[i][t-wt[i]];
                }
                
                dp[i][t] = Math.max(notTake , take);
            }
        }
        
        return dp[n-1][capacity];
        
        
    }

    public static int UnboundedKnapSack_TS(int val[], int wt[], int capacity) {
        int n = wt.length;
        int prev[] = new int[capacity+1];
        
        
        for(int t = 0 ; t <= capacity ; t++){
            prev[t] = (t/wt[0]) * val[0];
        }
        
        for(int i = 1 ; i<n ; i++){
            int temp[] = new int[capacity + 1];
            
            for(int t = 0 ; t <= capacity ; t++){
                int notTake = 0 + prev[t];
                int take = Integer.MIN_VALUE;
                if(wt[i] <= t){
                    take = val[i] + temp[t-wt[i]];
                }
                
                temp[t] = Math.max(notTake , take);
            }
            
            prev = temp;
        }
        
        return prev[capacity];
        
    }

    // Rod Cutting
    public static int cutRod_M(int[] price) {
        int n = price.length;
        int[][] dp = new int[n][n+1];
        for(int row[] : dp){
            Arrays.fill(row , -1);
        }
        return maxSale(price ,dp, n ,n-1);
    }
    
    public static int maxSale(int[] price , int[][] dp,int N, int ind){
        
        if(ind == 0){
            return N/(ind+1) * price[0];
        }
        
        
        if(dp[ind][N] != -1) return dp[ind][N];
        
        int notTake = 0 + maxSale(price , dp ,N , ind-1);
        int take = 0 ;
        
        if((ind + 1) <= N){
            take = price[ind] +  maxSale(price ,dp, N- (ind + 1) , ind);
        }
        
        return dp[ind][N] = Math.max(notTake , take);
    }

    public static  int cutRod_T(int[] price) {
        int n = price.length;
        int[][] dp = new int[n][n+1];
        
        
        for(int j = 0 ; j <= n ; j++){
            dp[0][j] = (j / 1) * price[0];
        }
        
        for(int i = 1 ; i<n ; i++){
            for(int s = 0 ; s <= n ; s++){
                int notTake = dp[i-1][s];
                int take = 0;
                if((i+1) <= s){
                    take = price[i] + dp[i][s - (i+1)];
                }
                
                dp[i][s] = Math.max(take , notTake);
            }
        }
        
        return dp[n-1][n];
    }
    
    public static int cutRod_TS(int[] price) {
        
        int n = price.length;
        int [] prev = new int[n+1];
        
        
        for(int j = 0 ; j <= n ; j++){
            prev[j] = j * price[0];
        }
        
        for(int i = 1 ; i<n ; i++){
            int temp[] = new int[n+1];
            for(int s = 0 ; s <= n ; s++){
                int notTake = 0 + prev[s];
                int take = 0;
                if((i+1) <= s){
                    take = price[i] + temp[s - (i+1)];
                }
                
                temp[s] = Math.max(take , notTake);
            }
            
            prev = temp;
        }
        
        return prev[n];
    }

///////////////////////////////////////////////////////////////////////// DP on STRINGS \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    // Longest Common Subsequence
    public static int longestCommonSubsequence_M(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();

        int dp[][] = new int[n][m];

        for(int row[] : dp){
            Arrays.fill(row , -1);
        }

        return maxLenLCS(text1 , text2 , n-1 , m-1 , dp);
    }

    public static int maxLenLCS(String s1 , String s2 , int ind1 , int ind2 , int[][] dp){
        if(ind1 < 0 || ind2 < 0){
            return 0;
        }

        if(dp[ind1][ind2] != -1) return dp[ind1][ind2];

        if(s1.charAt(ind1) == s2.charAt(ind2)){
            return 1 + maxLenLCS(s1 , s2 , ind1-1 , ind2-1 , dp);
        }

        return dp[ind1][ind2] = Math.max(maxLenLCS(s1 , s2 , ind1-1 , ind2 , dp ) , maxLenLCS(s1 , s2 , ind1 , ind2-1 , dp));
    }

    // NOTE : INDEX SHIFTED to meet the BASE CASE of memoization
    public static int longestCommonSubsequence_T(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();

        int dp[][] = new int[n+1][m+1];

        for(int j = 0 ; j<=m ; j++){
            dp[0][j] = 0;
        }

        for(int i = 0 ; i<= n ; i++){
            dp[i][0] = 0;
        }

        for(int i = 1 ; i <= n ; i++){
            for(int j = 1 ; j<= m ; j++){
                if(text1.charAt(i-1) == text2.charAt(j-1)){
                    dp[i][j] = 1 + dp[i-1][j-1];
                }
                else{
                    dp[i][j] = Math.max(dp[i-1][j] , dp[i][j-1]);
                }
            }
        }

        for(int i = 0 ; i < dp.length ; i++){
            for(int j = 0 ; j < dp[0].length ; j++){
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }


        return dp[n][m];
    }

    public static  int longestCommonSubsequence_TS(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();

        // int dp[][] = new int[n+1][m+1];
        int prev[] = new int [m+1];

        for(int j = 0 ; j<=m ; j++){
            prev[j] = 0;
        }

        // for(int i = 0 ; i<= n ; i++){
        //     dp[i][0] = 0;
        // }

        for(int i = 1 ; i <= n ; i++){
            int temp[] = new int[m+1];
            for(int j = 1 ; j<= m ; j++){
                if(text1.charAt(i-1) == text2.charAt(j-1)){
                    temp[j] = 1 + prev[j-1];
                }
                else{
                    temp[j] = Math.max(prev[j] , temp[j-1]);
                }
            }

            prev = temp;
        }

        return prev[m];
    }

    // Print Longest Common Sequence
    public static void  printLCS_T(String text1, String text2){
        int n = text1.length();
        int m = text2.length();

        int dp[][] = new int[n+1][m+1];

        for(int j = 0 ; j<=m ; j++){
            dp[0][j] = 0;
        }

        for(int i = 0 ; i<= n ; i++){
            dp[i][0] = 0;
        }

        for(int i = 1 ; i <= n ; i++){
            for(int j = 1 ; j<= m ; j++){
                if(text1.charAt(i-1) == text2.charAt(j-1)){
                    dp[i][j] = 1 + dp[i-1][j-1];
                }
                else{
                    dp[i][j] = Math.max(dp[i-1][j] , dp[i][j-1]);
                }
            }
        }

        // Printing
        // int lenLCS = dp[n][m];

        // int i = n , j = m;

        // StringBuilder str = new StringBuilder();

        // for(int k = 0 ; k < lenLCS ; k++){
        //     str.append('$');
        // }


        // while( i > 0 && j > 0){
        //     if(text1.charAt(i-1) == text2.charAt(j-1)){
        //         str.setCharAt(lenLCS - 1, text1.charAt(i-1));
        //         lenLCS--;
        //         i--;
        //         j--;
        //     }
        //     // else if(text1.charAt(i-1) > text2.charAt(j-1)){
        //     else if(dp[i-1][j] > dp[i][j-1]){
        //         i--;
        //     }
        //     else{
        //         j--;
        //     }
        // }

        // System.out.println(str);

        int i = n, j = m;
        StringBuilder str = new StringBuilder();

        while (i > 0 && j > 0) {
            if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                str.append(text1.charAt(i - 1)); // build in reverse
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }

        System.out.println(str.reverse()); 

    }

    // Longest common Substring
    public static int longestCommonSubstr(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        
        
        int[][] dp = new int[n+1][m+1];
        
        for(int j = 0 ; j <= m ; j++){
            dp[0][j] = 0;
        }
        
        for(int i = 0 ; i <= n ; i++){
            dp[i][0] = 0;
        }
        
        int maxLen = 0;
        
        for(int i = 1 ; i <= n ; i++){
            for(int j = 1 ; j <= m ; j++){
                if(s1.charAt(i-1) == s2.charAt(j-1)){
                    dp[i][j] = 1 + dp[i-1][j-1];
                    maxLen = Math.max(maxLen , dp[i][j]);
                }
                else{
                    dp[i][j] = 0;
                }
            }
        }
        
        return maxLen;
        
    }

    // Longest Palindromic Subsequence
    public static int longestPalindromeSubseq(String s) {
        return longestCommonSubsequence_TS(s , new StringBuilder(s).reverse().toString());
    }

    // Minimum Insertion Steps to Make a String Palindrome
    public int minInsertions(String s) {
        int lps = longestCommonSubsequence_TS(s , new StringBuilder(s).reverse().toString());

        return s.length() - lps;
    }

    // Delete Operation for Two Strings (or) Minimum Insertion / Deletion to make one STR1 -> STR2;
    public static int minDistance(String word1, String word2) {
        int op = longestCommonSubsequence_TS(word1 , word2);

        int n = word1.length();
        int m = word2.length();

        return((n+m) - 2*op);
    }

    // Shortest Common Supersequence 
    public static String shortestCommonSupersequence(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();

        int dp[][] = new int[n+1][m+1];

        for(int j = 0 ; j<=m ; j++){
            dp[0][j] = 0;
        }

        for(int i = 0 ; i<= n ; i++){
            dp[i][0] = 0;
        }

        for(int i = 1 ; i <= n ; i++){
            for(int j = 1 ; j<= m ; j++){
                if(str1.charAt(i-1) == str2.charAt(j-1)){
                    dp[i][j] = 1 + dp[i-1][j-1];
                }
                else{
                    dp[i][j] = Math.max(dp[i-1][j] , dp[i][j-1]);
                }
            }
        }

        int lenLCS = dp[n][m];

        // int lenSuperSeq = (n+m) - lenLCS;

        StringBuilder ansStr = new StringBuilder();

        int i = n , j = m;

        while(i > 0  && j > 0){
            if(str1.charAt(i-1) == str2.charAt(j-1)){
                ansStr.append(str1.charAt(i-1));
                i--;
                j--;
            }

            else if(dp[i-1][j] < dp[i][j-1]){
                ansStr.append(str2.charAt(j-1));
                j--;
            }else{
                ansStr.append(str1.charAt(i-1));
                i--;
            }
        }

        while(i>0){
            ansStr.append(str1.charAt(i-1));
            i--;
        }

        while(j > 0){
            ansStr.append(str2.charAt(j-1));
            j--;
        }

        return ansStr.reverse().toString();
    }

    // Distinct Subsequences
    public static int numDistinct_M(String s, String t) {
        int n = s.length();
        int m = t.length();

        int dp[][] = new int[n][m];

        for(int row[] : dp){
            Arrays.fill(row , -1);
        }

        return distSeq(s,t,n-1,m-1 , dp);
    }

    public static int distSeq(String s1 , String s2 , int i , int j , int[][] dp){
        if(j < 0) return 1;
        if(i < 0) return 0;

        if(dp[i][j] != -1)return dp[i][j];

        if(s1.charAt(i) == s2.charAt(j)){
            return dp[i][j] = (distSeq(s1,s2,i-1,j-1 , dp) + distSeq(s1,s2,i-1,j , dp));
        }else{
            return dp[i][j] = distSeq(s1,s2,i-1,j ,dp);
        }
    }

    public static int numDistinct_T(String s, String t) {
        int n = s.length();
        int m = t.length();

        int dp[][] = new int[n+1][m+1];


        for(int i = 0 ; i <= n ; i++){
            dp[i][0] = 1; // When dp[i][j] if(j == 0) return 1; [index shift]
        }

        for(int j = 1 ; j <= m ; j++){ 
            dp[0][j] = 0; // When dp[i][j] if(i == 0) return 0; [index shift]
        }

        for(int i = 1 ; i <= n ; i++){
            for(int j = 1 ; j <= m ; j++){
                if(s.charAt(i-1) == t.charAt(j-1)){
                    dp[i][j] = (dp[i-1][j-1] + dp[i-1][j]);
                }else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        return dp[n][m];
    }

    public int numDistinct_TS(String s, String t) {
        int n = s.length();
        int m = t.length();

        int prev[] = new int[m+1];

        prev[0] = 1;
        for(int j = 1 ; j <= m ; j++){
            prev[j] = 0;
        }

        for(int i = 1 ; i <= n ; i++){
            int temp[] = new int[m+1];
            temp[0] = 1;
            for(int j = 1 ; j <= m ; j++){
                if(s.charAt(i-1) == t.charAt(j-1)){
                    temp[j] = (prev[j-1] + prev[j]);
                }else{
                    temp[j] = prev[j];
                }
            }

            prev = temp;
        }

        return prev[m];
    }

    // Edit Distance
    public static  int editMinDistance_M(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        int dp[][] = new int[n][m];

        for(int row[] : dp){
            Arrays.fill(row , -1);
        }

        return minEdit(word1 , word2 , n-1 ,m-1 , dp );
    }

    public static int minEdit(String s1 , String s2 , int i , int j , int[][] dp){

        if(i < 0) return j+1;
        if(j < 0) return i+1;

        if(dp[i][j] != -1) return dp[i][j];

        if(s1.charAt(i) == s2.charAt(j)){
            return dp[i][j] = minEdit(s1 , s2 , i-1 ,j-1 , dp);
        }else{
            return dp[i][j] = 1 + Math.min(
                minEdit(s1 , s2 , i , j-1 , dp ), // Insertion
                Math.min(
                    minEdit(s1 ,s2 ,i-1,j , dp), // Deletion
                    minEdit(s1 , s2 ,i-1 , j-1 , dp) // Replace
                )
            );
        }
    }

    public static  int editMinDistance_T(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        int dp[][] = new int[n+1][m+1];

        for(int i = 0 ; i <= n ; i++){
            dp[i][0] = i;
        }

        for(int j = 0 ; j <= m ; j++){
            dp[0][j] = j;
        }


        for(int i = 1 ; i <= n ; i++){
            for(int j = 1 ; j <= m ; j++){
                if(word1.charAt(i-1) == word2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }else{
                    dp[i][j] = 1 + Math.min(
                        dp[i][j-1], // Insertion
                        Math.min(
                            dp[i-1][j], // Deletion
                            dp[i-1][j-1]
                        )
                    );
                }
            }
        }

        return dp[n][m];
    }

    public static  int editMinDistance_TS(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        // int dp[][] = new int[n+1][m+1];
        int prev[] = new int[m+1];

        // for(int i = 0 ; i <= n ; i++){
        //     dp[i][0] = i;
        // }

        for(int j = 0 ; j <= m ; j++){
            prev[j] = j;
        }


        for(int i = 1 ; i <= n ; i++){
            int temp[] = new int[m+1];
            temp[0] = i;
            for(int j = 1 ; j <= m ; j++){
                if(word1.charAt(i-1) == word2.charAt(j-1)){
                    temp[j] = prev[j-1];
                }else{
                    temp[j] = 1 + Math.min(
                        temp[j-1], // Insertion
                        Math.min(
                            prev[j], // Deletion
                            prev[j-1]
                        )
                    );
                }
            }

            prev = temp;
        }

        return prev[m];
    }


    // WildCard Pattern Matching
    public static  boolean isWildCardMatch_M(String s, String p) {
        int n = s.length();
        int m = p.length();

        Boolean dp[][] = new Boolean[n][m];

        return isWildCard(s,p,n-1, m-1 , dp);
    }

    public static boolean isWildCard(String s1 , String s2 , int i , int j , Boolean dp[][]){

        if(i<0 && j<0) return true;
        if(j<0 && i >= 0) return false;
        if( i<0 && j >= 0){
            if(isAllStars(s2 , j) == true) return true;
            else return false;
        } 

        // if(i<0 || j<0) return false;

        if(dp[i][j] != null) return dp[i][j];


        if(s1.charAt(i) == s2.charAt(j) || s2.charAt(j) == '?'){
            return dp[i][j]  =  isWildCard(s1 ,s2 , i-1 ,j-1, dp);
        }
        else if(s2.charAt(j) == '*'){
            // '*' means nothing || '*' means one comparison
            return dp[i][j] = (isWildCard(s1,s2,i,j-1, dp) || isWildCard(s1,s2,i-1,j, dp));
        }

        else return  dp[i][j] = false;
    }

    public static boolean isAllStars(String s2 , int j){
        for(int k = 0 ; k <= j ; k++){
            if(s2.charAt(k) != '*') return false;
        }
        return true;
    }

    public static  boolean isWildCardMatch_T(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();

        boolean dp[][] = new boolean[n+1][m+1];

        for(boolean[] row : dp){
            Arrays.fill(row , false);
        }

        dp[0][0] = true;

        for(int i = 1 ; i <= n ; i++){
            dp[i][0] = false;
        }

        for(int j = 1 ; j <= m ; j++){
            dp[0][j] = isAllStars(s2, j - 1);
        }
        
        for(int i = 1 ; i <= n ; i++){
            for(int j = 1 ; j <= m ; j++){
                if(s1.charAt(i-1) == s2.charAt(j-1) || s2.charAt(j-1) == '?'){
                    dp[i][j] = dp[i-1][j-1];
                }
                else if(s2.charAt(j-1) == '*'){
                    dp[i][j] =  dp[i][j-1] ||  dp[i-1][j] ;
                }
                else dp[i][j] = false;
            }
        }

        return dp[n][m];
    }

    public static  boolean isWildCardMatch_TS(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();

        boolean prev[] = new boolean[m+1];

        // for(boolean[] row : dp){
        //     Arrays.fill(row , false);
        // }

        prev[0] = true;

        for(int j = 1 ; j <= m ; j++){
            prev[j] = isAllStars(s2, j - 1);
        }

        for(int i = 1 ; i <= n ; i++){
            boolean temp[] = new boolean[m+1];
            for(int j = 1 ; j <= m ; j++){
                if(s1.charAt(i-1) == s2.charAt(j-1) || s2.charAt(j-1) == '?'){
                    temp[j] = prev[j-1];
                }
                else if(s2.charAt(j-1) == '*'){
                    temp[j] =  temp[j-1] ||  prev[j] ;
                }
                else temp[j] = false;
            }

            prev = temp;
        }

        return prev[m];
    }


///////////////////////////////////////////////////////////////////////////////// DP on Stocks \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    // Buy and Sell Stocks - II
    public static int maxProfit_II_O(int[] prices) {
        int maxProfit=0;
        int minimum=prices[0];
        for(int i=1;i<prices.length;i++){
            if(minimum>prices[i]){
              minimum=prices[i];
            }else{
                maxProfit+=prices[i]-minimum;
                minimum=prices[i];
            }
        }return maxProfit;
        
    }

    public static int maxProfit_II_M(int[] prices) {
        int n = prices.length;
        int dp[][] = new int[n + 1][2]; 
        for(int row[] : dp){
            Arrays.fill(row , -1);
        }
        return trade_II(prices , 0 , 1 , dp);
    }

    private static int trade_II(int [] prices, int ind , int buy , int[][] dp){
        if(ind == prices.length){
            return 0;
        }

        if(dp[ind][buy] != -1) return dp[ind][buy];

        int profit = 0;

        if(buy == 1){
            profit =  Math.max(
                -prices[ind] + trade_II(prices , ind + 1, 0 , dp),
                trade_II(prices , ind + 1 , 1 , dp)
            );
        }
        else{
            profit = Math.max(
                prices[ind] + trade_II(prices , ind + 1 , 1 , dp),
                trade_II(prices , ind + 1 , 0 , dp)
            );
        }

        return dp[ind][buy] = profit;
    }

    public static int maxProfit_II_T(int[] prices) {
        int n = prices.length;
        int dp[][] = new int[n + 1][2]; 

        dp[n][0] = dp[n][1] = 0;

        for(int i = n - 1 ; i >= 0 ; i--){
            for(int buy = 0 ; buy <= 1 ; buy++){
                int profit = 0;

                if(buy == 1){
                    profit = Math.max(
                        - prices[i] + dp[i+1][0],
                        dp[i+1][1]
                    );
                }
                else{
                    profit = Math.max(
                        prices[i] + dp[i+1][1],
                        dp[i+1][0]
                    );
                }

                dp[i][buy] = profit;
            }
        }

        return dp[0][1];
    }

    public static int maxProfit_II_TS(int[] prices) {
        int n = prices.length;
        int ahead[] = new int[2];
        int curr[] = new int[2];

        ahead[0] = ahead[1] = 0;

        for(int i = n - 1 ; i >= 0 ; i--){
            for(int buy = 0 ; buy <= 1 ; buy++){
                int profit = 0;

                if(buy == 1){
                    profit = Math.max(
                        - prices[i] + ahead[0],
                        ahead[1]
                    );
                }
                else{
                    profit = Math.max(
                        prices[i] + ahead[1],
                        ahead[0]
                    );
                }

                curr[buy] = profit;
            }

            ahead = curr;
        }

        return curr[1];
    }

    // Buy and Sell Stocks - III
    public static int maxProfit_III_O(int[] prices) {
        int firstBuy=Integer.MIN_VALUE,firstSell=0,secondBuy=Integer.MIN_VALUE,secondSell=0;
        int len = prices.length;
        for(int i=0;i<len;i++){
            firstBuy = Math.max(firstBuy,-prices[i]);
            firstSell = Math.max(firstSell,firstBuy+prices[i]);
            secondBuy = Math.max(secondBuy,firstSell-prices[i]);
            secondSell = Math.max(secondSell,secondBuy+prices[i]);
        }
        return secondSell;
    }

    public static int maxProfit_III_M(int[] prices) {
       int n = prices.length;
       int dp[][][] = new int[n+1][2][3];
       for(int [][] arr : dp){
            for(int row[] : arr){
                Arrays.fill(row , -1);
            }
       }
       return tradeCap_M(prices , 0 , 1 , 2 , dp);
    }

    private static int tradeCap_M(int [] prices , int ind , int buy , int cap , int dp[][][]){
        if(ind == prices.length || cap == 0) return 0;

        if(dp[ind][buy][cap] != -1) return dp[ind][buy][cap];

        int profit = 0;

        if(buy == 1){
            profit = Math.max(
                -prices[ind] + tradeCap_M(prices , ind + 1 , 0 , cap , dp),
                tradeCap_M(prices , ind + 1 , 1 , cap , dp)
            );
        }else{
            profit = Math.max(
                prices[ind] + tradeCap_M(prices , ind + 1 , 1 , cap - 1 , dp),
                tradeCap_M(prices , ind + 1 , 0 , cap , dp)
            );
        }

        return dp[ind][buy][cap] = profit;
    }

    public static  int maxProfit_III_T(int[] prices) {
        int n = prices.length;
        int dp[][][] = new int[n+1][2][3];

        dp[n][0][0] = dp[n][1][0] = 0;

        for(int ind = n - 1 ; ind >= 0 ; ind-- ){
            for(int buy = 0 ; buy <= 1 ; buy++){
                for(int cap = 1 ; cap <= 2 ; cap++){
                    int profit = 0;

                    if(buy == 1){
                        profit = Math.max(
                            -prices[ind] + dp[ind + 1][0][cap],
                            dp[ind + 1][1][cap]
                        );
                    }else{
                        profit = Math.max(
                            prices[ind] + dp[ind + 1][1][cap - 1],
                            dp[ind + 1][0][cap]
                        );
                    }

                    dp[ind][buy][cap] = profit;
                }
            }
        }

        return dp[0][1][2];
    }

    public static int maxProfit_III_TS(int[] prices) {
        int n = prices.length;
        
        int ahead[][] = new int[2][3];
        int curr[][] = new int[2][3];

        ahead[0][0] = ahead[1][0] = 0;

        for(int ind = n - 1 ; ind >= 0 ; ind-- ){
            for(int buy = 0 ; buy <= 1 ; buy++){
                for(int cap = 1 ; cap <= 2 ; cap++){
                    int profit = 0;

                    if(buy == 1){
                        profit = Math.max(
                            -prices[ind] + ahead[0][cap],
                            ahead[1][cap]
                        );
                    }else{
                        profit = Math.max(
                            prices[ind] + ahead[1][cap - 1],
                            ahead[0][cap]
                        );
                    }

                    curr[buy][cap] = profit;
                }

                ahead = curr;
            }
        }

        return ahead[1][2];
    }

    // Using Transaction number
    public static int maxProfitTrans_M(int[] prices) {
        int n = prices.length;
        int dp[][] = new int[n+1][4];
        for(int row[] : dp){
            Arrays.fill(row , -1);
        }
        
        
        return tradeCapTr(prices , 0 , 0 , dp);
    }

    private static int tradeCapTr(int[] prices , int ind , int tn , int[][] dp){
        if(ind == prices.length || tn == 4) return 0;

        if(dp[ind][tn] != -1) return dp[ind][tn];

        int profit = 0;

        if(tn % 2 == 0){
            profit = Math.max(
                -prices[ind] + tradeCapTr(prices , ind + 1 , tn + 1 , dp),
                tradeCapTr(prices , ind + 1 , tn , dp)
            );
        }
        else{
            profit = Math.max(
                prices[ind] + tradeCapTr(prices ,ind + 1 , tn + 1 , dp),
                tradeCapTr(prices , ind + 1 , tn , dp)
            );
        }

        return dp[ind][tn] = profit; 
    }

    public static int maxProfitTrans_T(int[] prices) {
        int n = prices.length;
        int dp[][] = new int[n+1][5];

        dp[n][4] = 0;

        for(int ind = n-1 ; ind >= 0 ; ind--){
            for(int tr = 3 ; tr >= 0 ; tr--){
                int profit;

                if(tr % 2 == 0){
                    profit = Math.max(
                        -prices[ind] + dp[ind + 1][tr + 1],
                        dp[ind + 1][tr]
                    );
                }
                else{
                    profit = Math.max(
                        prices[ind] + dp[ind + 1][tr + 1],
                        dp[ind + 1][tr]
                    );
                }

                dp[ind][tr] = profit;
            }
        }

        return dp[0][0];
    }

    public static int maxProfitTrans_TS(int[] prices) {
        int n = prices.length;

        int ahead[] = new int[5];
        int curr[] = new int[5];

        ahead[4] = 0;

        for(int ind = n-1 ; ind >= 0 ; ind--){
            for(int tr = 3 ; tr >= 0 ; tr--){
                int profit;

                if(tr % 2 == 0){
                    profit = Math.max(
                        -prices[ind] + ahead[tr + 1],
                        ahead[tr]
                    );
                }
                else{
                    profit = Math.max(
                        prices[ind] + ahead[tr + 1],
                        ahead[tr]
                    );
                }

                curr[tr] = profit;
            }

            ahead = curr;
        }

        return ahead[0];
    }

    // Buy and Sell Stocks - IV
    public static int maxProfit_IV_O(int k, int[] prices) {
        int n = prices.length;
        int[] profits = new int[n];
        for (int i = 1; i <= k ; i++) {
            int max = Integer.MIN_VALUE;
            int profit = 0;
            for( int j = n-1; j >= 0; j--) {
                max = Math.max(max, profits[j]+prices[j]);
                profit = Math.max(profit, max-prices[j]);
                profits[j] = profit;
            }
        }
        return profits[0];
    }

    public static int maxProfit_IV_M(int k, int[] prices) {
        int n = prices.length;
        int dp[][][] = new int[n + 1][2][k+1];

        for(int [][] arr : dp){
            for(int row[] : arr){
                Arrays.fill(row , -1);
            }
        }

        return tradeKtr(prices , 0 , 1 , k , dp);
    }

    private static int tradeKtr(int prices[] , int ind , int buy , int cap , int dp[][][]){
        if(ind == prices.length || cap == 0) return 0;

        if(dp[ind][buy][cap] != -1) return dp[ind][buy][cap];

        int profit;

        if(buy == 1){
            profit = Math.max(
                -prices[ind] + tradeKtr(prices , ind + 1, 0 , cap , dp),
                tradeKtr(prices , ind + 1 , 1 , cap , dp)
            );
        }
        else{
            profit = Math.max(
                prices[ind] + tradeKtr(prices , ind + 1 , 1 , cap - 1 , dp),
                tradeKtr(prices , ind + 1 , 0 , cap , dp)
            );
        }

        return dp[ind][buy][cap] = profit;
    }

    public static int maxProfit_IV_T(int k, int[] prices) {
         int n = prices.length;

        int dp[][][] = new int[n + 1][2][k + 1];

        dp[n][0][k] = dp[n][1][k] = 0;

        for(int ind  = n - 1 ; ind >= 0 ; ind--){
            for(int  buy = 0 ; buy < 2 ; buy++){
                for(int cap = 1 ; cap <= k ; cap++){
                    int profit;
                    if(buy == 1){
                        profit = Math.max(
                            -prices[ind] + dp[ind + 1][0][cap],
                            dp[ind + 1][1][cap]
                        );
                    }
                    else{
                        profit = Math.max(
                            prices[ind] + dp[ind + 1][1][cap - 1],
                            dp[ind + 1][0][cap]
                        );
                    }

                    dp[ind][buy][cap] = profit;
                }
            }
        }

        return dp[0][1][k];
    }

    public static int maxProfit_IV_TS(int k, int[] prices) {
        int n = prices.length;


        int ahead[][] = new int[2][k + 1];
        int curr[][] = new int[2][k + 1];

        for(int ind  = n - 1 ; ind >= 0 ; ind--){
            for(int  buy = 0 ; buy < 2 ; buy++){
                for(int cap = 1 ; cap <= k ; cap++){
                    int profit;
                    if(buy == 1){
                        profit = Math.max(
                            -prices[ind] + ahead[0][cap],
                            ahead[1][cap]
                        );
                    }
                    else{
                        profit = Math.max(
                            prices[ind] + ahead[1][cap - 1],
                            ahead[0][cap]
                        );
                    }

                    curr[buy][cap] = profit;
                }

                ahead = curr;
            }
        }
        

        return ahead[1][k];

    }

    // Using transcation number
    public static int maxProfitTrans_IV_M(int k, int[] prices) {
        int n = prices.length;
        int dp[][] = new int[n + 1][2*k + 1];

        for(int row[] : dp){
            Arrays.fill(row , -1);
        }

        return tradeKbs(prices , k , 0 , 0, dp);
    }

    private static int tradeKbs(int prices[] , int k , int ind , int tr , int[][] dp){
        if(ind == prices.length || tr == 2*k) return 0;

        if(dp[ind][tr] != -1) return dp[ind][tr];
        
        int profit;

        if(tr % 2 == 0){
            profit = Math.max(
                -prices[ind] + tradeKbs(prices , k , ind + 1, tr + 1 , dp),
                tradeKbs(prices , k , ind + 1 , tr , dp)
            );
        }
        else{
            profit = Math.max(
                prices[ind] + tradeKbs(prices , k , ind + 1, tr + 1 ,dp),
                tradeKbs(prices , k , ind + 1 , tr , dp)
            );
        }

        return dp[ind][tr] = profit;
    }

    public static int maxProfitTrans_IV_T(int k, int[] prices) {
        int n = prices.length;
        int dp[][] = new int[n + 1][2*k + 1];
        
        dp[n][2*k] = 0;

        for(int ind = n-1 ; ind >= 0 ; ind--){
            for(int tr = 2 * k - 1 ; tr >= 0 ; tr-- ){
                int profit;
                if(tr % 2 == 0){
                    profit = Math.max(
                        -prices[ind] + dp[ind + 1][tr + 1],
                        dp[ind + 1][tr]
                    );
                }
                else{
                    profit = Math.max(
                        prices[ind] + dp[ind + 1][tr + 1],
                        dp[ind + 1][tr]
                    );
                }

                dp[ind][tr] = profit;
            }
        }

        return dp[0][0];
    }

    public static int maxProfitTrans_IV_TS(int k, int[] prices) {
        int n = prices.length;
        
        int ahead[] = new int[2*k + 1];
        int curr[] = new int[2*k + 1];

        ahead[2*k] = 0;

        for(int ind = n-1 ; ind >= 0 ; ind--){
            for(int tr = 2 * k - 1 ; tr >= 0 ; tr-- ){
                int profit;
                if(tr % 2 == 0){
                    profit = Math.max(
                        -prices[ind] + ahead[tr + 1],
                        ahead[tr]
                    );
                }
                else{
                    profit = Math.max(
                        prices[ind] + ahead[tr + 1],
                        ahead[tr]
                    );
                }

                curr[tr] = profit;
            }

            ahead = curr;
        }

        return ahead[0];
    }


    // Best Time to Buy and Sell Stock with Cooldown
    public static int maxProfitCD_M(int[] prices) {
        int n = prices.length;
        int dp[][] = new int[n + 1][2];
        for(int row[] : dp){
            Arrays.fill(row , -1);
        }
        return tradeCD(prices , 0 , 1 , dp);
    }

    private static int tradeCD(int prices[] ,int ind , int buy , int dp[][]){
        if(ind >= prices.length ) return 0;

        if(dp[ind][buy] != -1) return dp[ind][buy];

        int profit;

        if(buy == 1){
            profit = Math.max(
                -prices[ind] + tradeCD(prices , ind + 1 , 0 , dp),
                tradeCD(prices , ind + 1 , 1 , dp)
            );
        }
        else{
            profit = Math.max(
                prices[ind] + tradeCD(prices , ind + 2 , 1 , dp),
                tradeCD(prices , ind + 1 , 0 , dp)
            );
        }

        return dp[ind][buy] = profit;
    }

    public static int maxProfitCD_T(int[] prices) {
        int n = prices.length;
        int dp[][] = new int[n + 2][2];

        dp[n][0] = dp[n][1] = 0;

        for(int ind = n - 1 ; ind >= 0 ; ind--){
            for(int buy = 0 ; buy <= 1 ; buy++){
                int profit;

                if(buy == 1){
                    profit = Math.max(
                        -prices[ind] + dp[ind + 1][0],
                        dp[ind + 1][1]
                    );
                }
                else{
                    profit = Math.max(
                        prices[ind] + dp[ind + 2][1],
                        dp[ind + 1][0]
                    );
                }

                dp[ind][buy] = profit;
            }
        }

        return dp[0][1];
    }

    // Best Time to Buy and Sell Stock with Transaction Fee
    public static int maxProfitFee_M(int[] prices, int fee) {
        int n = prices.length;
        int dp[][] = new int[n + 1][2];
        for(int[] row : dp){
            Arrays.fill(row , -1);
        }
        return tradeFee(prices , fee , 0 , 1 , dp);
    }

    private static int tradeFee(int prices[] , int fee , int ind , int buy, int[][] dp){
        if(ind == prices.length) return 0;

        if(dp[ind][buy] != -1) return dp[ind][buy];

        int profit;

        if(buy == 1){
            profit = Math.max(
                -prices[ind] + tradeFee(prices , fee , ind + 1 , 0 , dp),
                tradeFee(prices , fee , ind + 1 , 1 , dp)
            );
        }
        else{
            profit = Math.max(
               (prices[ind] - fee) + tradeFee(prices , fee , ind + 1 , 1, dp),
               tradeFee(prices , fee ,ind + 1 , 0 , dp)
            );
        }

        return dp[ind][buy] = profit;
    }

    public static int maxProfitFee_T(int[] prices, int fee) {
        int n = prices.length;
        int dp[][] = new int[n + 1][2];

        dp[n][0] = dp[n][1] = 0;

        for(int ind = n - 1 ; ind >= 0 ; ind--){
            for(int buy = 0 ; buy <= 1 ; buy++){
                int profit;

                if(buy == 1){
                    profit = Math.max(
                        -prices[ind] + dp[ind + 1][0],
                        dp[ind + 1][1]
                    );
                }
                else{
                    profit = Math.max(
                    (prices[ind] - fee) + dp[ind + 1][1],
                    dp[ind + 1][0]
                    );
                }

                dp[ind][buy] = profit;
            }
        }

        return dp[0][1];
    }

    public static int maxProfitFee_TS(int[] prices, int fee) {
        int n = prices.length;

        int ahead[] = new int[2];
        int curr[] = new int[2];

        ahead[0] = ahead[1] = 0;

        for(int ind = n - 1 ; ind >= 0 ; ind--){
            for(int buy = 0 ; buy <= 1 ; buy++){
                int profit;

                if(buy == 1){
                    profit = Math.max(
                        -prices[ind] + ahead[0],
                        ahead[1]
                    );
                }
                else{
                    profit = Math.max(
                    (prices[ind] - fee) + ahead[1],
                    ahead[0]
                    );
                }

                curr[buy] = profit;
            }

            ahead = curr;
        }


        return ahead[1];
    }



/////////////////////////////////////////////////////////////// Longest Increasing Subsequence \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

    // LIS
    public static  int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int dp[][] = new int[n][n+1]; // to store -1 at 0
        for(int row[] : dp){
            Arrays.fill(row , -1);
        }
        return LIS(nums , 0 , -1, dp);
    }

    public static int LIS(int [] arr , int ind , int prev , int[][] dp){

        if(ind == arr.length){
            return 0;
        }

        if(dp[ind][prev + 1] != -1) return dp[ind][prev + 1];

        int notTake = 0 + LIS(arr , ind+1 , prev , dp);
        int take = 0;

        if(prev == -1 || arr[ind] > arr[prev] ){
            take = 1 + LIS(arr , ind+1 , ind , dp);
        }

        return dp[ind][prev + 1] = Math.max(notTake , take);
    }

    public static int lengthOfLIS_T(int[] nums) {
        int n = nums.length;
        int dp[][] = new int[n+1][n+1]; // dp[i][prev+1]
        
        for(int i = n-1 ; i >= 0 ; i--){
            for(int prev_I = i-1 ; prev_I  >= -1 ; prev_I--){
                int notTake = dp[i + 1][prev_I + 1];

                int take = 0;

                if(prev_I == -1 || nums[i] > nums[prev_I]){
                    take = 1 + dp[i+1][i+1]; // i+1 at prev_I to shift -1 to 0
                }

                dp[i][prev_I+1] = Math.max(notTake , take);
            }

        }

        return dp[0][0];
    }

    public static int lengthOfLIS_TS(int[] nums) {
        int n = nums.length;
        int ahead[] = new int[n + 1];  // dp[i][prev+1]

        for(int ind = n-1 ; ind >= 0 ; ind--){
            int temp[] = new int[n + 1];
            for(int prev = ind - 1 ; prev >= -1 ; prev--){
                int notTake = ahead[prev + 1];
                int take = 0;
                if(prev == -1 || nums[ind] > nums[prev]){
                    take = 1 + ahead[ind + 1]; // i+1 at prev to shift -1 to 0
                }

                temp[prev + 1] = Math.max(take , notTake);
            }

            ahead = temp;
        }

        return ahead[0];
    }

    public static int lengthOfLIS_O(int[] nums) {
        int n = nums.length;

        int dp[] = new int[n];
        Arrays.fill(dp , 1);

        int maxLen = 1;
        for(int i = 1 ; i<n ; i++){
            for(int j = 0 ; j < i; j++){
                if(nums[j] < nums[i]){
                    if(dp[j] +1 > dp[i]){
                        dp[i] = dp[j] + 1;
                    }
                }
            }

            maxLen = Math.max(dp[i] , maxLen);
        }

        return maxLen;
    }

    public static int lengthOfLIS_BS(int[] nums) {
        int n = nums.length;

        ArrayList<Integer> temp = new ArrayList<>();
        temp.add(nums[0]);

        int maxLen = 1;
        for(int i = 1 ; i < n; i++){
            if(nums[i] > temp.get(temp.size() - 1)){
                temp.add(nums[i]);
            }
            else{
                int pos = lowerBound(temp , nums[i]);
                temp.set(pos , nums[i]);
            }

            maxLen = Math.max(maxLen , temp.size());
        }

        return maxLen;
    }

    private static int lowerBound(ArrayList<Integer> arr , int x){

        int n = arr.size();
        int low = 0 , high = n - 1;

        int ans = n;

        while(low <= high){
            int mid = low + (high - low ) / 2;

            if(arr.get(mid) >= x){
                ans = mid;
                high = mid - 1;
            }
            else{
                low = mid + 1;
            }

        }

        return ans;
    }

    public static int[] printLIS(int[] nums) {
        int n = nums.length;

        int dp[] = new int[n];
        Arrays.fill(dp , 1);

        int indHash[] = new int[n];
        Arrays.fill(indHash , -1);

        int maxLen = 1;
        int lastInd = 0;
        for(int i = 0 ; i<n ; i++){
            for(int j = 0 ; j < i; j++){
                if(nums[j] < nums[i] && dp[j] +1 > dp[i]){    
                    dp[i] = dp[j] + 1;
                    indHash[i] = j;
                }
            }
            if(dp[i] > maxLen){
                maxLen = dp[i];
                lastInd = i; // which is the max index updated lastly
            }
        }

        int lis[] = new int[maxLen];

        int pos = maxLen - 1;
        int cur = lastInd;

        while(cur != -1){
            lis[pos] = nums[cur];
            pos--;
            cur = indHash[cur];
        }

        // System.out.println(Arrays.toString(indHash));
        // System.out.println(Arrays.toString(lis));

        return lis;
    }

    // Largest Divisible Subset
    public static List<Integer> largestDivisibleSubset(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);

        int dp[] = new int[n];
        int indHash[] = new int[n];

        Arrays.fill(dp , 1);
        Arrays.fill(indHash , -1);

        int maxDiv = 1;
        int lastInd = 0;
        for(int i = 1 ; i < n ; i++){
            for(int j = 0 ; j < i ; j++){
                if(nums[i] % nums[j] == 0 && dp[j] + 1 > dp[i]){
                    dp[i] = dp[j] + 1;
                    indHash[i] = j;
                } 
            }

            if(dp[i] > maxDiv){
                maxDiv = dp[i];
                lastInd = i;
            } 
        }

        int cur = lastInd;
        
        List<Integer> ans = new ArrayList<>();
        
        while(cur != -1){
            ans.add(nums[cur]);
            cur = indHash[cur];
        }

        Collections.reverse(ans);

        return ans;
    }

    // Longest String Chain
    public static int longestStrChain(String[] words) {
        int n = words.length;

        Arrays.sort(words , (a , b) -> a.length() - b.length());

        int dp[] = new int[n];

        Arrays.fill(dp , 1);

        int maxLen = 1;

        for(int i = 1 ; i < n ; i++){
            for(int j = 0 ; j < i ; j++){
                if((words[i].length() == words[j].length() + 1) && compare(words[i] , words[j])){
                    dp[i] = Math.max(dp[i] , dp[j] + 1);
                }
            }

            maxLen = Math.max(maxLen , dp[i]);
        }

        return maxLen;
    }

    private static boolean compare(String s1 , String s2){
        int i = 0 , j = 0;

        while(i < s1.length() && j < s2.length()){
            if(s1.charAt(i) == s2.charAt(j)){
                i++;
                j++;
            }
            else{
                i++;
            }
        }

        return (j == s2.length());
    }

    // Longest Bitonic subsequence
    public static int LongestBitonicSequence(int n, int[] nums) {
       
        int dp1[] = new int[n];
        int dp2[] = new int[n];
       
        Arrays.fill(dp1 , 1);
        Arrays.fill(dp2 , 1);
       
        // int maxLen = 1;
       
        for(int i = 1 ; i < n ; i++){
            for(int j = 0 ; j < i ; j++){
                if(nums[i] > nums[j] && dp1[j] + 1 > dp1[i]){
                    dp1[i] = dp1[j] + 1;
                }
            }
           
            // maxLen = Math.max(dp1[i] , maxLen);
        }
       
        for(int i = n - 2 ; i >= 0 ; i--){
            for(int j = n-1 ; j > i ; j--){
                if(nums[i] > nums[j] && dp2[j] + 1 > dp2[i]){
                    dp2[i] = dp2[j] + 1;
                }
            }
        }
       
       
        int ans = 0;
       
        for(int i = 0 ; i < n ; i++){
            if(dp1[i] > 1 && dp2[i] > 1){
                ans = Math.max(ans , (dp1[i] + dp2[i]) - 1);
            }
        }
       
        return ans;
    }

    // Number of Longest Increasing Subsequence
    public static int findNumberOfLIS(int[] nums) {
        int n = nums.length;

        int dp[] = new int[n];
        int cnt[] = new int[n]; // cnt[i] = number of distinct LIS of length dp[i] that end at i.

        Arrays.fill(dp , 1);
        Arrays.fill(cnt , 1);

        int maxLen = 1;
        for(int i = 1 ; i < n ; i++){
            for(int j = 0 ; j < i ; j++){
                if(nums[i] > nums[j]){
                    if(dp[j] + 1 > dp[i]){
                        dp[i] = dp[j] + 1;
                        cnt[i] = cnt[j];
                    }
                    else if(dp[j] + 1 == dp[i]){
                        cnt[i] += cnt[j];
                    }
                }
            }

            maxLen = Math.max(maxLen , dp[i]);
        }

        int cntOfLIS = 0;

        for(int i = 0 ; i < n ; i++){
            if(dp[i] == maxLen) cntOfLIS += cnt[i];
        }


        return cntOfLIS;

    }



//////////////////////////////////////////////////////////////// DP on Partition \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

    // Matrix Chain Multiplication
    public static int matrixMultiplication_M(int arr[]) {
        int n = arr.length;
        
        int dp[][] = new int[n][n];
        
        for(int[] row : dp){
            Arrays.fill(row , -1);
        }
        
        return mcm(arr , 1 , n-1 , dp);
    }
    
    private static int mcm(int arr[] , int i , int j , int dp[][]){
        if(i == j) return 0;
        
        if(dp[i][j] != -1)return dp[i][j];
        
        int minMcm = (int) 1e9;
        
        for(int k = i ; k <= j - 1 ; k++){
            int steps = (arr[i - 1] * arr[k] * arr[j]) + mcm(arr , i , k , dp) + mcm(arr , k+1 , j , dp);
            
            minMcm = Math.min(minMcm , steps);
        }
        
        return dp[i][j] = minMcm;
    }

    public static int matrixMultiplication_T(int arr[]) {
        int n = arr.length;
        
        if (n < 2) return 0;
        
        int dp[][] = new int[n][n];
        
        for(int i = 0 ; i < n ; i++){
            dp[i][i] = 0;
        }
        
        for(int i = n - 1 ; i >= 1 ; i++){
            for(int j = i+1 ; j <= n - 1 ; j++){ // not 0 since logically j should be after i (since i== j) handled
                int minMcm = (int) 1e9;
                for(int k = i ; k <= j - 1 ; k++){
                    int steps = (arr[i - 1] * arr[k] * arr[j]) + dp[i][k] + dp[k + 1][j];
                    minMcm = Math.min(minMcm , steps);
                }
                dp[i][j] = minMcm;
            }
        }
        
        return dp[1][n - 1];
    }

    public static int matrixMultiplication_TC(int arr[]) {
        int n = arr.length;
        
        // There must be at least 2 dimensions to have 1 matrix
        // (e.g., [10, 20] is one 10x20 matrix).
        // To multiply, we need at least 3 dimensions (e.g., [10, 20, 30]).
        if (n < 3) return 0;
        
        int dp[][] = new int[n][n];
        
        // We are using 1-based indexing for our matrices (A_1, A_2, ...).
        // The dp[i][j] table will store the cost of multiplying A_i through A_j.
        // The dimensions of matrix A_i are arr[i-1] x arr[i].
        
        // A single matrix (length 1) has 0 cost.
        // This is already set to 0 by default, but we can be explicit.
        // for(int i = 1; i < n; i++) {
        //     dp[i][i] = 0;
        // }
    
        // 'len' is the length of the matrix chain.
        // We start with length 2, e.g., (A_1 * A_2).
        // The number of matrices is n-1. So the max length is n-1.
        for (int len = 2; len < n; len++) { 
            // 'i' is the starting matrix.
            // The last possible chain of length 'len' starts at (n - len).
            for (int i = 1; i <= n - len; i++) {
                // 'j' is the ending matrix.
                int j = i + len - 1;
                
                dp[i][j] = Integer.MAX_VALUE;
                
                // 'k' is the split point. We split at k.
                // (A_i ... A_k) * (A_{k+1} ... A_j)
                for (int k = i; k < j; k++) {
                    
                    // Cost = (cost of first part) + (cost of second part) + (cost of multiplying the two results)
                    // Result 1 (A_i...A_k) has dimensions: arr[i-1] x arr[k]
                    // Result 2 (A_{k+1}...A_j) has dimensions: arr[k] x arr[j]
                    int cost = dp[i][k] + dp[k + 1][j] + arr[i - 1] * arr[k] * arr[j];
                    
                    dp[i][j] = Math.min(dp[i][j], cost);
                }
            }
        }
        
        // The final answer is the cost of multiplying the full chain,
        // from matrix A_1 to matrix A_{n-1}.
        return dp[1][n - 1];
    }
    
    // Minimum Cost to Cut a Stick
    public static int minCost_M(int n, int[] cuts) {
        List<Integer> cutsArr = new ArrayList<>();
        cutsArr.add(0);
        cutsArr.add(n);
        for(int num : cuts){ 
            cutsArr.add(num);
        }
        Collections.sort(cutsArr);

        int m = cutsArr.size();

        int dp[][] = new int[m][m];
        for(int row[] : dp){
            Arrays.fill(row , -1);
        }

        return minCuts(n , cutsArr , 1 , cutsArr.size() - 2 , dp);
    }

    private static int minCuts(int n , List<Integer> cuts , int i , int j , int[][] dp){
        if(i > j) return 0;

        if(dp[i][j] != -1) return dp[i][j];

        int minCst = (int) 1e9;

        for(int k = i ; k <= j ; k++){
            int cost = ( cuts.get(j + 1) - cuts.get(i - 1) ) + minCuts(n , cuts , i, k - 1 , dp) + minCuts(n, cuts , k + 1 , j , dp);
            minCst = Math.min(minCst , cost);
        }

        return dp[i][j] = minCst;
    }

    

//////////////////////////////////////////////////////////////// DP on Counting / Combinatorics \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    public static final int MOD = (int)(1e9 + 7);
    public int numRollsToTarget(int n, int k, int target) {
        int dp[][] = new int[n + 1][target + 1];
        for(int [] row : dp){
            Arrays.fill(row , -1);
        }
        return validRolls(n , k , target , 0 , dp);
    }

    private static int validRolls(int n , int k , int target , int sum , int[][] dp){
        if(sum > target) return 0;
        if(n == 0){
            return (sum == target) ? 1 : 0;
        }

        if(dp[n][sum] != -1) return dp[n][sum];

        long cnt = 0L;

        if(n != 0){
            for(int j = 1 ; j <= k ; j++){
                cnt += validRolls(n - 1, k , target , sum + j , dp);
            }
        }

        dp[n][sum] = (int)(cnt % MOD);
        return dp[n][sum];
    }    

 




    public static void main(String[] args) {
        
        // House Robber - I
        int[] nums = {2, 7, 9, 3, 1};
        int maxRobbed = houseRobberI_M(nums);
        System.out.println("Maximum amount robbed: " + maxRobbed);

        // House Robber - II
        int[] nums2 = {2, 3, 2};
        int maxRobbed2 = houseRobberII_TS(nums2);
        System.out.println("Maximum amount robbed in House Robber II: " + maxRobbed2);

        // Ninja training
        int[][] points = {
            {1, 2, 5},
            {3, 1, 1},
            {3, 3, 3}
        };
        int maxPoints = maximumPoints(points);
        System.out.println("Maximum points in Ninja Training: " + maxPoints);

        // Unique Paths
        int m = 3, n = 7;
        int totalPaths = uniquePaths_M(m, n);
        System.out.println("Total unique paths in a " + m + "x" + n + " grid: " + totalPaths);

        // Unique Paths - II
        int[][] obstacleGrid = {
            {0, 0, 0},
            {0, 1, 0},
            {0, 0, 0}
        };
        int uniquePathsWithObs = uniquePathsWithObstacles_M(obstacleGrid);
        System.out.println("Unique paths with obstacles: " + uniquePathsWithObs);

        // Minimum Path Sum
        int[][] grid = {
            {1, 3, 1},
            {1, 5, 1},
            {4, 2, 1}
        };
        int minSum = minPathSum(grid);
        System.out.println("Minimum path sum: " + minSum);

        // Minimum Path Sum Triangle
        List<List<Integer>> triangle = Arrays.asList(
            Arrays.asList(2),
            Arrays.asList(3, 4),
            Arrays.asList(6, 5, 7),
            Arrays.asList(4, 1, 8, 3)
        );
        
        int minTriangleSum = minimumTotal(triangle);
        System.out.println("Minimum path sum in triangle: " + minTriangleSum);

        // Minimum Falling Path Sum
        int[][] matrix = {
            {2, 1, 3},
            {6, 5, 4},
            {7, 8, 9}
        };
        int minFallingSum = minFallingPathSum_M(matrix);
        System.out.println("Minimum falling path sum: " + minFallingSum);

        // Subset Sum Equals Target
        int[] subsetArr = {3, 34, 4, 12, 5, 2};
        int targetSum = 9;
        Boolean isSubset = isSubsetSum_T(subsetArr, targetSum);
        System.out.println("Is there a subset with sum " + targetSum + "? " + isSubset);

        // Partition Equal Subset Sum
        int[] partitionArr = {1, 5, 11, 5};
        boolean canPart = canPartition(partitionArr);
        System.out.println("Can partition into equal subset sum? " + canPart);

        // Partition Array Into Two Arrays to Minimize Sum Difference
        int[] minDiffArr = {1, 6, 11, 5};
        int minDiff = minDifference(minDiffArr);
        System.out.println("Minimum difference between two subsets: " + minDiff);

        // Perfect Sum Problem
        int[] perfectSumArr = {2, 3, 5, 6, 8, 10};
        int perfectTarget = 10;
        int perfectSumWays = perfectSum_T(perfectSumArr, perfectTarget);
        System.out.println("Number of subsets with sum " + perfectTarget + ": " + perfectSumWays);

        // 0/1 Knapsack Problem
        int[] values = {60, 100, 120};
        int[] weights = {10, 20, 30};
        int capacity = 50;
        int maxKnapsack = knapsack01_T(capacity, values, weights);
        System.out.println("Maximum value in 0/1 Knapsack: " + maxKnapsack);

        // Coin Change - I
        int[] coins = {1, 2, 5};
        int amount = 11;
        int minCoins = coinChangeI_T(coins, amount);
        System.out.println("Minimum coins needed for amount " + amount + ": " + minCoins);

        // Coin Change - II
        int[] coins2 = {1, 2, 5};
        int amount2 = 5;
        int ways = coinChangeII_TS(amount2, coins2);
        System.out.println("Number of ways to make amount " + amount2 + " with coins: " + ways);

        // Unbounded Knapsack Problem
        int[] unboundedValues = {10, 40, 50, 70};
        int[] unboundedWeights = {1, 3, 4, 5};
        int unboundedCapacity = 8;
        int maxUnboundedKnapsack = UnboundedKnapSack_T(unboundedValues, unboundedWeights, unboundedCapacity);
        System.out.println("Maximum value in Unbounded Knapsack: " + maxUnboundedKnapsack);

        // Rod Cutting Problem
        int[] rodPrices = {2, 5, 7, 8};
        int maxRodCut = cutRod_T(rodPrices);
        System.out.println("Maximum obtainable value by cutting rod: " + maxRodCut);


        // Longest Common Subsequence
        String text1 = "abcde";
        String text2 = "ace";
        int lcsLength = longestCommonSubsequence_T(text1, text2);
        System.out.println("Length of Longest Common Subsequence: " + lcsLength);

        // Print Longest Common Subsequence
        String lcsStr1 = "abcde";
        String lcsStr2 = "ace";
        System.out.print("Longest Common Subsequence: ");
        printLCS_T(lcsStr1, lcsStr2);

        // Longest Common Substring
        String substr1 = "abcdxyz";
        String substr2 = "xyzabcd";
        int lcsStrLen = longestCommonSubstr(substr1, substr2);
        System.out.println("Length of Longest Common Substring: " + lcsStrLen);

        // Longest Palindromic Subsequence
        String palindromeStr = "bbbab";
        int lpsLength = longestPalindromeSubseq(palindromeStr);
        System.out.println("Length of Longest Palindromic Subsequence: " + lpsLength);

        // Minimum Insertion Steps to Make a String Palindrome
        String minInsertStr = "mbadm";
        int minInsertionsNeeded = new DP2().minInsertions(minInsertStr);
        System.out.println("Minimum insertions to make palindrome: " + minInsertionsNeeded);

        // Minimum Insertion/Deletion to make one string to another
        String word1 = "sea";
        String word2 = "eat";
        int minInsDel = minDistance(word1, word2);
        System.out.println("Minimum insertions/deletions to convert '" + word1 + "' to '" + word2 + "': " + minInsDel);

        // Shortest Common Supersequence
        String scsStr1 = "brute";
        String scsStr2 = "groot";
        String scs = shortestCommonSupersequence(scsStr1, scsStr2);
        System.out.println("Shortest Common Supersequence: " + scs);

        // Distinct Subsequences
        String s = "rabbbit";
        String t = "rabbit";
        int numDistinctWays = numDistinct_T(s, t);
        System.out.println("Number of distinct subsequences: " + numDistinctWays);

        // Edit Distance
        String editWord1 = "horse";
        String editWord2 = "ros";
        int editDist = editMinDistance_M(editWord1, editWord2);
        System.out.println("Edit distance between '" + editWord1 + "' and '" + editWord2 + "': " + editDist);

        // Wildcard Pattern Matching with Memoization
        String wildStr = "adceb";
        String pattern = "*a*b";
        boolean isMatch = isWildCardMatch_M(wildStr, pattern);
        System.out.println("Wildcard pattern match : " + isMatch);


        // DP on Stocks - example calls (one call per question type)
        int[] stockA = {7, 1, 5, 3, 6, 4};
        int[] stockK = {3, 3, 5, 0, 0, 3, 1, 4};
        int fee = 2;
        int k = 2;

        // Buy & Sell II
        System.out.println("maxProfit_II_O: " + maxProfit_II_O(stockA));

        // Buy & Sell III (at most two transactions)
        System.out.println("maxProfit_III_O: " + maxProfit_III_O(stockK));

        // Using transaction number (fixed 2 transactions implementation)
        System.out.println("maxProfitTrans_T: " + maxProfitTrans_T(stockK));

        // Buy & Sell IV (k transactions)
        System.out.println("maxProfit_IV_O (k=2): " + maxProfit_IV_O(k, stockK));

        // Using transaction-number variant for general k
        System.out.println("maxProfitTrans_IV_T (k=2): " + maxProfitTrans_IV_T(k, stockK));

        // Cooldown
        System.out.println("maxProfitCD_T: " + maxProfitCD_T(stockK));

        // Transaction fee
        System.out.println("maxProfitFee_T (fee=2): " + maxProfitFee_T(stockK, fee));

        // Longest Increasing Subsequence
        int[] lisArr = {5,4,11,1,16,18};
        int lisLength = lengthOfLIS_BS(lisArr);
        System.out.println("Length of Longest Increasing Subsequence: " + lisLength);

        // Printing Increasing Subsequence
        int[] lisOutput = printLIS(lisArr);
        System.out.println("LIS: " + Arrays.toString(lisOutput));

        // Matrix Chain Multiplication(MCM)
        int[] mcmArr = {2, 1, 3, 4};
        int mcmAns = matrixMultiplication_M(mcmArr);
        System.out.println("Minimum multiplications for matrix chain: " + mcmAns);

    }
}
