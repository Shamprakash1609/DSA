import java.util.*;

public class Greedy {

    // Assign Cookies
    public static int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);

        int cnt = 0;

        int i = 0 , j = 0;
        
        while(i < g.length && j < s.length){
            if(g[i] <= s[j]){
                cnt++;
                i++;
                j++;
            }else{
                j++;
            }
            
        }

        return cnt;
    }

    // lemonade Change
    public boolean lemonadeChange(int[] bills) {
        int five = 0;
        int ten = 0;
  
        int i ;
  
        for(i = 0 ; i<bills.length ; i++){
          if(bills[i] == 5){
              five ++;
          }
          else if(bills[i] == 10){
              if(five >= 1){
                  ten++;
                  five--;
              }else return false;
  
          }else{
              if(five >= 1 && ten >= 1){
                  five--;
                  ten--;
              }else if(five >= 3){
                  five -= 3;
              }
              else return false;
          }
        }  
  
        return true;
      }

    // Job Sequencing Problem
    public static  ArrayList<Integer> jobSequencing(int[] deadline, int[] profit) {
        // TC : O(nlogn) SC : O(n)
        int n = profit.length;
        
        ArrayList<int[]> jobs = new ArrayList<>();
        
        for(int i = 0 ; i<n ; i++){
            jobs.add(new int[]{profit[i] , deadline[i]});
        }
        
        jobs.sort((a,b) -> Integer.compare(b[0] , a[0])); // sort profit in descending order
        
        int cnt = 0;
        int maxProfit = 0;
        
        int slot[] = new int[n];
        Arrays.fill(slot , -1);
        
        for(int i = 0 ; i<n ; i++){
            int[] job = jobs.get(i);
            
            for(int j = job[1] - 1 ; j >= 0 ; j--){
                if(slot[j] == -1){
                    slot[j] = 1;
                    cnt++;
                    maxProfit += job[0];
                    break;
                }
            }
            
        }
        
        ArrayList<Integer> ans = new ArrayList<>();
        
        ans.add(cnt);
        ans.add(maxProfit);
        
        return ans;
        
    }

    // Jump Game  - I
    public static boolean canJump(int[] nums) {
        int n = nums.length;

        int maxInd = 0;

        for(int i = 0 ; i<n ; i++){
            if(i > maxInd) return false; // in case Zero
            maxInd = Math.max(maxInd , i + nums[i]);
            if(maxInd >= n-1) return true;
        }

        return true;
    }

    // Jump Game  - II
    public static int jump(int[] nums) {
        int n = nums.length;

        int l = 0, r = 0;
        int jumps = 0;

        while(r < n-1){
            int far = 0;

            for(int i = l ; i <= r ; i++){
                far = Math.max(i+nums[i] , far);
            }
            l = r+1;
            r = far;
            jumps++;
        }

        return jumps;
    }

    // Jump Game VII
    public static boolean canReach_B(String s, int minJump, int maxJump) {
        int n = s.length();
        Boolean dp[] = new Boolean[n];

        return canReach(s , minJump , maxJump , dp , 0);
    }

    private static boolean canReach(String s , int minJump , int maxJump , Boolean dp[] , int ind){
        if(ind == s.length() - 1) return true;
        if(ind > s.length() - 1 || s.charAt(ind) == '1') return false;

        if(dp[ind] != null) return dp[ind];

        for(int i = ind + minJump ; i <= Math.min(ind + maxJump , s.length() - 1) ; i++){
            if(s.charAt(i) == '0'){
                if (canReach(s, minJump, maxJump, dp, i)) {
                    return dp[ind] = true;   // ✅ any one path works
                }
            }
        }

        return dp[ind] = false;  // ❌ all paths failed
    }
    
    public static boolean canReach(String s, int minJump, int maxJump) {
        int n = s.length();

        int farthest = 0;
        
        Queue<Integer> q = new LinkedList<>();
        q.offer(0);

        while(!q.isEmpty()){
            int idx = q.poll();

            int start = Math.max(idx + minJump , farthest + 1); // skip jumps less than farthest

            for(int j = start ; j <= Math.min( idx + maxJump , n - 1) ; j++){
                if(s.charAt(j) == '0'){
                    q.offer(j);

                    if(j == n - 1) return true;
                }
            }

            farthest = idx + maxJump;
        }

        return false;
        
    } 

    
    // Valid paranthesis 
    public static  boolean checkValidString(String s) {
        int n = s.length();

        int min = 0 , max = 0;

        for(int i = 0 ; i < n ; i++){
            char ch = s.charAt(i);

            if(ch == '('){
                min++;
                max++;
            }
            else if(ch == ')')
            {
                min--;
                max--;
            }
            else
            {
                max++;
                min--;
            }

            if(min < 0) min = 0;
            if(max < 0) return false;
        }

    
        return (min == 0);
    }

    // N meeting in one room
    public static  int maxMeetings(int start[], int end[]) {
        // add your code here
        int n = start.length;
        
        ArrayList<int[]> meetings = new ArrayList<>();
        
        for(int i = 0 ; i<n ; i++){
            meetings.add(new int[]{start[i] , end[i]});
        }
        
        meetings.sort((a,b) -> Integer.compare(a[1] , b[1]));
        

        int freeTime = meetings.get(0)[1];
        
        int maxMeets = 1;
        
        for(int i = 1 ; i<n ; i++){
            int[] current = meetings.get(i);
            if( current[0] > freeTime ){
                maxMeets++;
                freeTime = current[1];
            }
        }
        
        return maxMeets;
        
    }

    // Non-overlapping Intervals (similar to N meeting in one room)
    public static int eraseOverlapIntervals(int[][] arr) {
        int n = arr.length;

        Arrays.sort(arr , (a , b) -> Integer.compare(a[1] ,b[1]));

        int maxCnt = 1;
        int freeTime = arr[0][1];

        for(int i = 1 ; i<n ; i++){
            int curr[] = arr[i];
            if(curr[0] >= freeTime){
                maxCnt += 1;
                freeTime = curr[1];
            }
        }

        return n - maxCnt;
    }

    // Insert Interval
    public static  int[][] insert(int[][] arr, int[] newI) {
        
        int n = arr.length;
       
        List<int[]> res = new ArrayList<>();

        int i = 0 ;
        
        // left
        while(i < n && arr[i][1] < newI[0]){
            res.add(arr[i]);
            i++;
        }

        // overlapping
        while(i < n && arr[i][0] <= newI[1]){
            newI[0] = Math.min(newI[0] , arr[i][0]);
            newI[1] = Math.max(newI[1] , arr[i][1]);
            i++;
        }

        res.add(newI);

        // right
        while(i < n){
            res.add(arr[i]);
            i++;
        }

        return res.toArray(new int[res.size()][]);
    }

    // Merge Intervals
    public static int[][] merge(int[][] arr) {
        int n = arr.length;

        if(n <= 1) return arr;

        Arrays.sort(arr , (a , b) -> Integer.compare(a[0] , b[0]));

        List<int[]> ans = new ArrayList<>();

        int[] newI = arr[0];
        ans.add(newI);

        for(int[] intr : arr){
            if(intr[0] <= newI[1]){
                newI[1] = Math.max(intr[1] , newI[1]); 
                // Never forget changing value here changes value in the LIST as well
            }
            else{
                newI = intr;
                ans.add(newI);
            }
        }

        
        return ans.toArray(new int[ans.size()][]);
    }

    // Minimum Platforms
    public static int findPlatform(int arr[], int dep[]) {
        int n = arr.length;
        
        Arrays.sort(arr);
        Arrays.sort(dep);
        
        int i = 0 , j = 0 , cnt = 0;
        int maxCnt = 0;
        
        while( i < n){
            if(arr[i] <= dep[j]){
                cnt++;
                i++;
            }else{
                cnt--;
                j++;
            }
            
            maxCnt = Math.max(cnt , maxCnt);
        }
        
        return maxCnt;
    }

    // Fractional Knapsack
    public static double fractionalKnapsack(int[] values, int[] weights, int W) {
        // code here
        int n = values.length;
        List<int[]> valAndWts = new ArrayList<>();
        
        for(int i = 0 ; i<n ; i++){
            valAndWts.add(new int[]{values[i] , weights[i]});
        }
        
        valAndWts.sort((a,b) -> Double.compare((double)b[0]/b[1] , (double)a[0]/a[1]));
        
        double value = 0.0;
        
        for(int i = 0 ; i<n ;i++){
            int [] currI = valAndWts.get(i);
            
            if(W >= currI[1]){
                W -= currI[1];
                value += currI[0];
            } else{
                value += ((double) currI[0]/currI[1]) * W;
                break; // knapsack is Full
            }
        }
        
        return value;
    }

    // Candy Distribution
    public static int candy_b(int[] ratings) {
        int n = ratings.length;
        int []left = new int[n];
        int []right = new int[n];


        left[0] = 1;
        for(int i = 1 ; i< n; i++){
            if(ratings[i-1] < ratings[i]){
                left[i] = left[i-1] + 1;
            }else{
                left[i] = 1;
            }
        }

        right[n-1] = 1;
        for(int i = n-2 ; i >= 0 ; i--){
            if(ratings[i] > ratings[i+1]){
                right[i] = right[i+1] + 1;
            }else{
                right[i] = 1;
            }
        }

        int sum = 0;

        for(int i = 0 ; i<n ; i++){
            sum += Math.max(left[i] , right[i]);
        }

        return sum;
    }

    public static int candy_O(int[] ratings) {
        int n = ratings.length;
        
        int i = 1 ;
        int sum = 1;

        while(i < n){
            if(ratings[i] == ratings[i-1]){
                sum += 1;
                i++;
                continue;
            }

            int peak = 1;

            while(i<n && ratings[i] > ratings[i-1]){
                peak += 1;
                sum += peak;
                i++;
            }

            int slope = 1;
            while(i <n && ratings[i] < ratings[i-1]){
                sum += slope;
                slope += 1;
                i++;
            }

            if(slope > peak){
                sum = sum + (slope - peak);
            }
        }

        return sum;
    }

    // Gas Stations
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int totGas = 0 , totCst = 0;

        int n = gas.length;

        for(int i = 0 ; i < n ; i++){
            totGas += gas[i];
            totCst += cost[i];
        }

        if(totGas < totCst) return -1;

        int currentGas = 0 , startInd = 0;

        for(int i = 0 ; i < n; i++){
            currentGas += gas[i] - cost[i];

            if(currentGas < 0){
                startInd = i + 1;
                currentGas = 0;
            }
        }

        return startInd;
    }

    
    public static void main(String[] args) {

        // Assign Cookies
        int[] g = {10,9,8,7};
        int[] s = {5,6,7,8};

        int contentChildren = findContentChildren(g, s);
        System.out.println("Number of content children: " + contentChildren);

        
        // job sequnecing problem
        int[] deadline = {2, 1, 2, 1};
        int[] profit = {100, 19, 27, 25};

        ArrayList<Integer> result = jobSequencing(deadline, profit);
        System.out.println("Number of jobs done: " + result.get(0));
        System.out.println("Maximum profit: " + result.get(1));

        // Jump Game - I
        int[] nums = {3,2,1,0,4};
        boolean canReach = canJump(nums);
        System.out.println("Can jump to the last index: " + canReach);

        // Jump Game - II
        int[] nums2 = {2, 3, 1, 4, 1, 1, 1, 2};
        int minJumps = jump(nums2);
        System.out.println("Minimum number of jumps to reach the last index: " + minJumps);

        // Valid Parenthesis
        
        String s1 = "()*)*()";
        boolean isValid3 = checkValidString(s1);
        System.out.println("Is \"" + s1 + "\" a valid parenthesis string? " + isValid3);

        // N meetings in one room
        int[] start = {1, 3, 0, 5, 8, 5};
        int[] end = {2, 4, 6, 7, 9, 9};

        int maxMeetings = maxMeetings(start, end);
        System.out.println("Maximum number of meetings: " + maxMeetings);

        // Non-overlapping Intervals
        int[][] intervals = {{1, 2}, {2, 3}, {3, 4}, {1, 3}};
        int erasedIntervals = eraseOverlapIntervals(intervals);
        System.out.println("Number of intervals to erase: " + erasedIntervals);

        // Insert Interval
        int[][] intervals2 = {{1,2},{3,5},{6,7},{8,10},{12,16}};
        int[] newInterval = {4,8};
        int[][] resultIntervals = insert(intervals2, newInterval);

        System.out.println("Intervals after insertion:");
        for (int[] interval : resultIntervals) {
            System.out.println(Arrays.toString(interval));
        }


        // Merge Intervals
        int[][] intervals3 = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] mergedIntervals = merge(intervals3);

        System.out.println("Merged intervals:");
        for (int[] interval : mergedIntervals) {
            System.out.println(Arrays.toString(interval));
        }

        // Find Platform
        int[] arrival = {900, 940, 950, 1100, 1500, 1800};
        int[] departure = {910, 1200, 1120, 1130, 1900, 2000};

        int platformsNeeded = findPlatform(arrival, departure);
        System.out.println("Minimum number of platforms required: " + platformsNeeded);
        
        // Fractional Knapsack
        int[] values = {60, 100, 120};
        int[] weights = {10, 20, 30};
        int capacity = 50;

        double maxValue = fractionalKnapsack(values, weights, capacity);
        System.out.println("Maximum value in the knapsack: " + maxValue);

        // Candy Distribution
        int[] ratings = {1, 0, 2};
        int candies = candy_O(ratings);
        System.out.println("Minimum candies required: " + candies);



    }
}
