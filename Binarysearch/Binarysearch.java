package Binarysearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Binarysearch {

    public static boolean binarySearch(int[] nums, int target) {
        int n = nums.length; 
        int low = 0, high = n - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (nums[mid] == target) return true;

            else if (target > nums[mid]) low = mid + 1;

            else high = mid - 1;

        }
        return false;
    }

    public static int binarySearchInd(int[] nums, int target) {
        int n = nums.length; 
        int low = 0, high = n - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (nums[mid] == target) return mid;

            else if (target > nums[mid]) low = mid + 1;

            else high = mid - 1;

        }
        return -1;
    }

    // lowerBound - smallest Index such that arr[i] >= x
    // Example: lowerBound([3, 5, 8, 9, 15, 19], 8) returns 2, since arr[2] = 8 is the first index where arr[i] >= 8.

    public static int lowerBound(int arr[] , int x){

        int n = arr.length;

        int low = 0  , high = n-1;

        int ans = n;

        while(low <= high){
            int mid = (high + low) / 2;

            if(arr[mid] >= x){

                ans = mid;

                high = mid - 1;

            }

            else{
                low = mid + 1;
            }
        }

        return ans;

    }

    // upperBound - smallest Index such that arr[i] > x
    // Example: upperBound([3, 5, 8, 9, 15, 19], 8) returns 3, since arr[3] = 9 is the first index where arr[i] > 8.
    public static int upperBound(int arr[] , int x){

        int n = arr.length;

        int low = 0 , high = n-1;
        int ans = n;

        while(low <= high){

            int mid = (low + high)/2;

            if(arr[mid] > x){

                ans = mid;

                high = mid - 1;
            }

            else{
            
                low = mid + 1;
            }
        }

        return ans;
    }

    // Floor and Ceil 
    // Floor of x → The largest element in the array <= X
    // Ceil of x → The smallest element in the array >= X

    public static int findFloor(int arr[] , int x){

        int n = arr.length;

        int low = 0 , high = n-1;
        int ans = -1;

        while(low <= high){

            int mid = (low + high)/2;

            if(arr[mid] <= x){
                ans = arr[mid];

                low = mid + 1;
            } 
            else{
                high = mid - 1;
            }

        }

        return ans;

    }

    public static int findCeil(int arr[] , int x){

        int n = arr.length;

        int low = 0 , high = n-1;
        int ans = -1;

        while(low <= high){

            int mid = (low + high)/2;

            if(arr[mid] >= x){
                ans = arr[mid];

                high = mid - 1;
            } 
            else{

                low = mid + 1;
            }

        }

        return ans;

    }

    public static int[] findFirstAndLastOccurancesN(int arr[] , int x){
        int n = arr.length;

        int lb = lowerBound(arr, x);

        if(lb == n || arr[lb] != x) return new int[]{-1, -1};

        return new int[] {lb , upperBound(arr, x) - 1};

    }

    public static int findFirstOccurance(int arr[] , int x){
        int n = arr.length;

        int low = 0 , high = n-1;
        int first = -1;

        // find first  

        while(low <= high){

            int mid = (low + high) / 2;

            if(arr[mid] == x){
                first = mid;

                high = mid -1;
            } 
            else if(arr[mid] < x){
                low = mid + 1;
            }
            else{
                high = mid - 1; 
            }

        }

        return first;

        
    }

    public static int findLastOccurance(int arr[] , int x){

        int n = arr.length;

        int low = 0 , high = n-1;

        int last = -1;

        // find last

        while(low <= high){
            int mid = (low + high)/2;

            if(arr[mid] == x){
                last = mid;
                low = mid + 1;
            }
            else if(arr[mid] < x){
                low = mid + 1;
            }
            else{
                high = mid - 1;
            }
        }

        return last;

    }

    public static int rtSortedSearchI(int arr[] , int x){

        int n = arr.length;

        int low = 0 , high = n-1;

        while(low <= high){

            int mid = (low + high)/2;

            if(arr[mid] == x){

                return mid;
            }

            if(arr[low] <= arr[mid]){

                if(arr[low] <= x && x <= arr[mid]){

                    high = mid - 1;
                }

                else{
                     low = mid + 1;
                }
            }

            else{
                if(arr[mid] <= x && x <= arr[high]){

                    low = mid + 1;
                    
                }

                else{

                    high = mid -1;
                }
            }
        }

        return -1;

    }

    public static boolean rtSortedSearchII(int arr[] ,int x){

        int n = arr.length;

        int low = 0 , high = n-1;

        while(low <= high){

            int mid = (low + high) / 2;

            if(arr[mid] == x) return true;

            if(arr[low] == arr[mid] && arr[mid] == arr[high]){
                low++;
                high--;
                continue;
            }

            if(arr[low] <= arr[mid]){

                if(arr[low] <= x && x <= arr[high]){

                    high = mid -1;
                }

                else{

                    low = mid +1 ;
                }
            }

                if(arr[mid] <= arr[high]){

                if(arr[mid] <= x && x <= arr[high]){

                    low = mid + 1;
                }
                else{

                    high = mid - 1;
                }
            }
        }

        return false;
    }

    public static int smallestInRotated(int arr[]){

        // TC: O(logN);

        int n = arr.length;

        int low = 0 , high = n-1;

        int ans = Integer.MAX_VALUE;

        while(low <= high){

            int mid = (low + high)/2;

            // Optimisation at any point the low cross the rotation pivot the array is completely sorted and hence arr[low] is smallest

            if(arr[low] <= arr[high]){
                ans = Math.min(ans , arr[low]);
                break;
            }

            if(arr[low] <= arr[mid]){
                
                ans = Math.min(ans , arr[low]);

                low = mid + 1;
            }
            else {

                ans = Math.min(ans , arr[mid]);

                high = mid - 1;

            }

        }

        return ans;
    }

    public static int findKRotation(List<Integer> arr) {
        int n = arr.size();
        
        int low = 0 , high = n-1;
        
        int rotated = 0;
        
        int ans = Integer.MAX_VALUE;
        
        while(low <= high){
            int mid = (low + high) / 2;
            
            if(arr.get(low) <= arr.get(high)){
                
                if(arr.get(low) < ans){
                   
                    rotated = low;
                    ans = arr.get(low);
                   
                }
                
                 break;
               
            }
            else if(arr.get(low) <= arr.get(mid)){
                
                if(arr.get(low) < ans){
                    
                    rotated = ans;
                    ans = arr.get(low);
                }
                
                low = mid + 1;
                
            }
            else {
                
                if(arr.get(mid) < ans){
                    
                    rotated = mid;
                    ans = arr.get(mid);
                }
                high = mid - 1;
            }
            
        }
        
        return rotated;
    }
    
    public static int findKRotation(int arr[]) {
        int n = arr.length;
        
        int low = 0 , high = n - 1;
        
        if(arr[low] <= arr[high]) return 0;
    
        
        while(low < high){
            int mid = low + (high - low) / 2;
            
            if(arr[mid] > arr[high]){
                low = mid + 1;
            }else{
                high = mid;
            }
        }
        
        return low;
        
        
    }

    public  static int singleNonDuplicate(int arr[]){

        int n = arr.length;

        int low = 1 , high = n - 2;

        if(n == 1) return arr[0];

        if(arr[0] != arr[1]) return arr[0];

        if(arr[n-1] != arr[n-2]) return arr[n-1];

        while(low <= high){

            int mid = (low + high)/2;

            if(arr[mid - 1] != arr[mid] && arr[mid] != arr[mid+1]){
                return arr[mid];
            }
            // left half
            else if(( mid % 2 == 1 && arr[mid] == arr[mid -1]) || ( mid % 2 == 0 && arr[mid] == arr[mid + 1] ) ){
                low = mid + 1;
            }

            // right half
            else
            {
                high = mid - 1;
            }
        }

        return -1;
        
    }

    public static int findPeakElementB(int[] arr) {
        int n = arr.length; 
    
        for (int i = 0; i < n; i++) {
            
            if ((i == 0 || arr[i - 1] < arr[i])
                    && (i == n - 1 || arr[i] > arr[i + 1])) {
                return i;
            }
        }
        return -1;
    }

    public static int findPeakElement(int[] arr){
        int n = arr.length;

        if(n == 1) return arr[0];
        if(arr[1] < arr[0]) return arr[0];
        if(arr[n-1] > arr[n-2]) return arr[n-1];

        int low = 1 , high = n-1;

        while(low <= high){
            int mid = (low + high)/2;

            if(arr[mid - 1] < arr[mid] && arr[mid] > arr[mid+1]){
                return mid;
            }
            else if(arr[mid] < arr[mid + 1]){
                low = mid + 1;
            }
            else{
                high = mid - 1;
            }
        }

        return -1;
    }

    public static int[] findRightInterval(int[][] intervals) {
        int n = intervals.length;

        int ans[] = new int[n];

        int starts[][] = new int[n][2];

        // (start , originalIdx)
        for(int i = 0 ; i < n ; i++){
            starts[i][0] = intervals[i][0];
            starts[i][1] = i;
        }
        
        Arrays.sort(starts , (a , b) -> Integer.compare(a[0] , b[0]));

        for(int i = 0 ; i < n ; i++){
            int end = intervals[i][1];

            int low = 0 , high = n - 1;

            int idx = -1;

            while(low <= high){
                int mid = low + (high - low) / 2;

                if(starts[mid][0] >= end){
                    idx = starts[mid][1];
                    high = mid - 1;   // try to find smaller valid start
                } else {
                    low = mid + 1;
                }

            }

            ans[i] = idx;
        }

        return ans;
    }

    // Reach a Number
    public static int reachNumber(int target) {
        // You first reach or exceed the target, then ensure the extra distance is even so it can be canceled by flipping directions.
        target = Math.abs(target); // Symmetry 

        long sum = 0;

        long numMoves = 0;

        while(sum < target){
            numMoves++;
            sum += numMoves;
        }

        while((sum - target) % 2 != 0){
            numMoves++;
            sum += numMoves;
        }

        return (int) numMoves;
    }

    // Minimum Absolute Sum Difference
    public static  int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
        int n = nums1.length;

        int MOD = 1_000_000_007;

        // 1️⃣ Compute initial sum
        long total = 0;

        for(int i = 0 ; i < n ; i++){
            total += Math.abs(nums1[i] - nums2[i]);
        }


        // 2️⃣ Sort nums1 for searching
        int[] sorted = nums1.clone();
        Arrays.sort(sorted);

        // 3️⃣ Find maximum gain
        /*
            Binary search in sorted nums1
            Find the closest value to nums2[i]
            Compute the best possible improvement

            current = |nums1[i] - nums2[i]|
            best = min(|candidate - nums2[i]|)
            gain = current - best
        */
        long maxGain = 0;
        for(int i = 0 ; i < n ; i++){
            int target = nums2[i];
            int curr = Math.abs(nums1[i] - target);
            

            int idx = lowerBound(sorted , target);

            if(idx < n){
                int best = Math.abs(sorted[idx] - target);
                maxGain = Math.max(maxGain, curr - best);
            }
            
            if(idx > 0){
                int best = Math.abs(sorted[idx - 1] - target);
                maxGain = Math.max(maxGain , curr - best);
            }
        }

        return (int) ((total - maxGain) % MOD);
    }


    // Maximum Value at a Given Index in a Bounded Array
    public static  int maxValue(int n, int index, int maxSum) {
        int low = 1 , high = maxSum;

        int ans = -1;

        while(low <= high){
            int mid = low + (high - low) / 2;

            if(pyramid(mid , n , index , maxSum)){
                ans = mid;
                low = mid + 1;
            }
            else{
                high = mid - 1;
            }
        }

        return ans;

    }

    private static boolean pyramidBr(int x, int n, int index, int maxSum) {
        long sum = 0;

        // Go left
        int val = x;
        for (int i = index; i >= 0; i--) {
            sum += val;
            if (val > 1) val--;
        }

        // Go right
        val = x - 1;
        for (int i = index + 1; i < n; i++) {
            sum += val;
            if (val > 1) val--;
        }

        return sum <= maxSum;
    }

    private static boolean pyramid(int x, int n, int index, int maxSum) {
        long sum = x;   // center value

        int left = index;
        int right = n - index - 1;

        // LEFT SIDE
        if (x - 1 >= left) {
            // Case 1: no 1s reached
            sum += (long)(x - 1 + x - left) * left / 2;
        } else {
            // Case 2: reach 1 early
            sum += (long)(x - 1 + 1) * (x - 1) / 2; // decreasing part
            sum += (left - (x - 1));              // fill rest with 1s
        }

        // RIGHT SIDE
        if (x - 1 >= right) {
            sum += (long)(x - 1 + x - right) * right / 2;
        } else {
            sum += (long)(x - 1 + 1) * (x - 1) / 2;
            sum += (right - (x - 1));
        }

        return sum <= maxSum;
    }

    // optimal
    private static  long calculateSum(long peak, long len){
        if(peak >= len){
            return (peak + (peak - len + 1)) * len / 2;
        }else{
            return (peak + 1) * peak / 2 + (len - peak);
        }
    }

    public static  int maxValue_O(int n, int index, int maxSum) {
        long start = 1;
        long end = maxSum;
        long ans = -1;

        while (start <= end) {

            long target = start + (end - start) / 2;

            long leftSum = calculateSum(target - 1, index);
            long rightSum = calculateSum(target - 1, n - index - 1);

            System.out.println("target-" + target + ", leftSum - " + leftSum + ", rightSum - " + rightSum);

            long totalSum = leftSum + rightSum + target;

            if (totalSum > maxSum) {
                end = target - 1;
            } else {
                ans = Math.max(ans, target);
                start = target + 1;
            }
        }

        return (int)ans;
    }



 ////////////////////////////////////////////////////// BINARY SEARCH ON ANSWERS \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    
    public static int floorSqrt(int n){
        int ans = 1;

        int low = 1 , high = n;

        while(low <= high){
            int mid = (low + high)/2;

            if(mid * mid <= n){
                ans = mid;
                low = mid + 1;
            }else{
                high = mid - 1;
            }
        }

        return ans;
    }

    public static int func(int mid ,int n, int m){
        long ans = 1;
        for(int i = 0 ; i<n ; i++){
            ans = ans * mid;
            if(ans > mid) return 2;
        }
        if(ans == mid) return 1;
        return 0;
    }

    public static int  NthRoot(int n , int m){
        
        int low = 1 , high = m;

        while(low <= high){
            int mid = (low + high)/2;

            int val = func(mid, n, m);

            if(val == 1){
                return mid;
            }
            else if(val == 0){
                low = mid + 1;
            }
            else{
                high = mid - 1;
            }
            
        }

        return -1;
    }

    // KOKO eating Bananas
    public static int calculateHours(int arr[] , int K){
        int n = arr.length;

        int totHours = 0 ;

        for(int i = 0 ; i<n ; i++){
            totHours += Math.ceil((double)arr[i] / K);
        }

        return totHours;
    }

    public static int KOKOEatingBananasB(int piles[] , int maxHours){
        int maxi = Integer.MIN_VALUE;

        for(int num : piles){
            maxi = Math.max(num, maxi);
        }

        for(int i = 1 ; i <= maxi ; i++){
            int hours = calculateHours(piles, i);
            if(hours <= maxHours) return i;
        }

        return -1;
    }

    public static int KOKOEatingBananasO(int piles[] , int maxHours){
        int ans = 1;

        int maxi = Integer.MIN_VALUE;

        for(int num : piles){
            maxi = Math.max(num, maxi);
        }

        int low = 1 ; int high = maxi;

        while(low <= high){
            int mid = (low + high)/2;

            int val = calculateHours(piles, mid);

            if(val <= maxHours){
                ans = mid;
                high = mid - 1;
            }else{
                low = mid + 1;
            }
        }

        return ans;
    }

    // Minimum days to make M bouquets
    // M -> no of bouquets to be made 
    // K -> no of adjacent bloomed flowers to be add in the boquet 
    public static boolean isPossible(int arr[] , int day , int M , int K ){

        int n = arr.length; 

        int cnt = 0;
        int nofB = 0;

        for(int i = 0 ; i< n ; i++){
            if(arr[i] <= day){
                cnt++;
            }
            else{
                nofB += (cnt / K);
                cnt = 0 ;
            }
        }
        nofB += (cnt/K);

        if(nofB >= M)return true;
        else return false;
    }
    
    public static int minDaysForMBouquetsB(int arr[] , int M , int K){
        int n = arr.length;

        if(n < (M * K)) return -1;

        int mini = Integer.MAX_VALUE;
        int maxi = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            mini = Math.min(mini, arr[i]);
            maxi = Math.max(maxi, arr[i]);
        }

        for(int i = mini ;i <= maxi ; i++){
            if(isPossible(arr, i, M, K)){
                return i;
            }
        }

        return -1;

    
    }

    public static int minDaysForMBouquetsO(int arr[] , int M , int K){
        int n = arr.length;

        int ans = -1;

        if(n < (long)(M * K)) return -1;

        int mini = Integer.MAX_VALUE;
        int maxi = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            mini = Math.min(mini, arr[i]);
            maxi = Math.max(maxi, arr[i]);
        }

        int low = mini , high = maxi;

        while(low <= high){

            int mid = (low + high)/2;

            if(isPossible(arr, mid , M , K)){
                ans = mid;

                high = mid - 1;

            }
            else{
                low = mid + 1;
            }
        }

        return ans;

    }

    // find the smallest divisors given a thershold
    public static int smallestDivisorB(int [] arr , int limit){

        int n = arr.length;

        int maxi = Integer.MIN_VALUE;

        for(int num : arr){
            maxi = Math.max(maxi , num);
        }

        for(int d = 1 ; d <= maxi ; d++){
            int divSum = 0;

            for(int j = 0 ; j< n ; j++){

                divSum += Math.ceil((double)arr[j] / d);

            }

            if(divSum <= limit) return d;

        }

        return -1;

    }

    public static int  divSum(int arr[] , int divisor ){
        int n = arr.length;

        int divTot = 0;

        for(int i = 0 ; i<n ; i++){
            
            divTot += Math.ceil((double) arr[i] / (double) divisor);
        }

       return divTot;

    }
    public static int smallestDivisorO(int[] arr , int limit){
        int n = arr.length;

        int maxi = Integer.MIN_VALUE;

        int ans = n;

        for(int num : arr){
            maxi = Math.max(num, maxi);
        }

        int low = 1 , high = maxi;

        while(low <= high){

            int mid = (low + high)/2;

            int val = divSum(arr, mid);

            if(val <= limit){
                ans = mid;
                high = mid -1;

            }else{
                low = mid + 1;
            }

           
        }

        return ans;
    }
    
    // Capacity to Ship Packages within D Days
    // need to find the minimum capacity to ship with D days.
    // w.k.t -> capcity can be maximum weight - to - sum all weights are eligible.
    public static int findDays(int weights[] , int cap){

        int n = weights.length;

        int days = 1;

        int load = 0;

        for(int i = 0 ; i<n ; i++){
            if((weights[i] + load ) > cap){
                days += 1;
                load = weights[i];
            }else{
                load += weights[i];
            }
        }

        return days;
    }

    public static int leastCapacityOfShipB(int weights[] , int days){
        
        int n = weights.length;

        int maxi = Integer.MIN_VALUE;
        int sumOfWts = 0 ;

        for(int wt : weights){
            maxi = Math.max(maxi, wt);
            sumOfWts += wt;
        }

        for(int i = maxi ; i<= sumOfWts ;i++){

           int daysNeeded = findDays(weights, i);

           if(daysNeeded <= days){
            return i;
           }
        }

        return -1; // dummy
    }

    public static int leastCapacityOfShipO(int weights[] ,int days){

        int n = weights.length;

        int maxi = Integer.MIN_VALUE;

        int sumOfWts = 0;

        int ans = sumOfWts;

        for(int i = 0 ; i<n ; i++){
            maxi = Math.max(maxi, weights[i]);
            sumOfWts += weights[i];
        }

        int low = maxi , high = sumOfWts;

        while(low <= high){

            int mid = (low + high)/2;
            int daysNeeded = findDays(weights, mid);

            if(daysNeeded <= days){
                ans = mid;
                high = mid -1;
            }else{
                low = mid + 1;
            }
        }

        return ans;
    }

    // Find K missing numbers - Find the 'kth' positive integer missing from 'array' starting form 1.
    public static int missingK_B(int arr[] , int k){

        int n = arr.length;

        for(int i = 0 ; i<n ; i++){
            if( arr[i] <= k) k++;
            else break;
        }
        return k;
    }

    public static int missingK_O(int arr[] , int k){
        int n = arr.length;

        int low = 0 , high = n-1;

        while (low <= high) {
            
            int mid = (low + high)/2;

            int missing = arr[mid] - (mid + 1);
            // mid + 1 -> is the element which is actually should be at that index

            if(missing < k) low = mid + 1; 
            else high = mid - 1;

        }


        // arr[high] + more - the missing element -> missing arr[high] - (high + 1) -> actual element to be in that position 
        //  arr[high] + k - missing 
        // hence 
        // arr[high] + k - (arr[high] - (high + 1));
        // arr[high] + k - (arr[high] - high - 1);
        // arr[high] + k - arr[high] + high + 1;
        // k + high + 1; (or) low + k;
        
        return k + high + 1;


    }
    
    // Divide Chocolates
    public static int getMaximumSweetness(ArrayList<Integer> arr, int k) {

        long sum = 0;
        long low = Long.MAX_VALUE;

        for(int num : arr){
            low = Math.min(low, num);   // minimum element
            sum += num;
        }

        // long low = 1;
        long high = sum;
        long ans = low;

        while(low <= high){
            long mid = low + (high - low) / 2;

            if(divideChunks(mid , arr , k + 1)){
                ans = mid;
                low = mid + 1;
            }
            else{
                high = mid - 1;
            }
        }

        return (int) ans;
    }

    private static boolean divideChunks(long minTaste , ArrayList<Integer> arr , int chunks){
        int cnt = 0;
        long sum = 0;

        for(int val : arr){
            sum += val;
            if(sum >= minTaste){
                cnt++;
                sum = 0;
            }
        }

        return cnt >= chunks;
    }

 
    // Aggressive Cows
    /*
        * No two cows share the same stall.
        * The minimum distance between any two cows is as large as possible.
        * You need to find the maximum possible minimum distance.
    */

    public static boolean canWePlaceCows(int stalls[] , int dist , int Cows){
        int n = stalls.length;

        int last = stalls[0];

        int cntCows = 1;

        for(int i = 1 ; i < n ; i++){

            if(stalls[i] - last >= dist){
                cntCows += 1;
                last = stalls[i];
            }

        }

        if(cntCows >= Cows) return true;
        else return false; 
    }

    public static int AggressiveCows_B(int stalls[], int Cows) {
        int n = stalls.length;
        Arrays.sort(stalls);
    
        int mini = stalls[0];
        int maxi = stalls[n - 1];

        int limit = maxi - mini;
    
        for (int i = 1; i <= limit ; i++) {
            if (canWePlaceCows(stalls, i, Cows) == false) {
                return (i - 1);
            } 
        }
    
        return limit;
    }

    public static int AggressiveCows_O(int stalls[] , int Cows){
        int n = stalls.length;

        Arrays.sort(stalls);

        int low = 1 , high = stalls[n-1] - stalls[0];

        int ans = -1;


        while(low <= high){
            int mid = (low + high)/2;

            boolean isPossible = canWePlaceCows(stalls, mid, Cows);

            if(isPossible){
                ans = mid;
                low = mid +1;
            }else{
                high = mid -1;
            }
        }

        return ans;
    }

    // Book Allocation
    /*
        * Each student gets at least one book.
        * Each book should be allocated to only one student.
        * Book allocation should be in a contiguous manner.
        * 
        * “My binary search failed because I checked for exactly k partitions instead of at most k. Feasibility checks must preserve monotonicity.”
    */

    public static int allocateBooks(int[] books , int pages){
        int n = books.length;

        int students = 1; 
        int pagesStudent = 0;

        for(int i = 0 ; i<n ; i++){
            if(pagesStudent + books[i] <= pages){
                pagesStudent += books[i];
            }
            else{
                students += 1;
                pagesStudent = books[i];
            }
        }

        return students;
    }

    public static int  BookAllocation_B(int books[] , int students){
        int n = books.length;

        if(n < students) return -1;

        int maxi = Integer.MIN_VALUE;
        int sumOfpgs = 0;

        for(int num : books){
            maxi = Math.max(num, maxi);
            sumOfpgs += num;
        }

        for(int pgs = maxi ; pgs <= sumOfpgs ; pgs++){

            int cntStudents = allocateBooks(books , pgs);

            if(cntStudents == students){
                return pgs;
            }
        }

        return -1;
    }

    public static int BookAllocation_O(int books[] , int students){

        int n = books.length;

        if(n < students) return -1;

        int ans = -1;

        int maxi = Integer.MIN_VALUE;
        int sumOfpgs = 0;

        for(int num : books){
            maxi = Math.max(maxi, num);
            sumOfpgs += num;
        }

        int low = maxi , high = sumOfpgs;

        while ( low <= high) {
            int mid = (low + high)/2;

            int allocatedStudents = allocateBooks(books, mid);

            if(allocatedStudents <= students){
                ans = mid;
                high = mid - 1;
            }else{
                low = mid + 1;
            }
        }

        return ans;

    }

    // Split Array Largest Sum / painters Partition
    public static int splitArray(int[] nums, int k) {
        int n = nums.length;

        if (n < k) return -1;

        int maxElement = Integer.MIN_VALUE;
        int totalSum = 0;

        for (int num : nums) {
            maxElement = Math.max(num, maxElement);
            totalSum += num;
        }

        int low = maxElement, high = totalSum;
        int result = -1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (canSplit(nums, k, mid)) {
                result = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return result;
    }

    public static boolean canSplit(int[] nums, int maxSubarrays, int maxAllowedSum) {
        int currentSum = 0;
        int requiredSubarrays = 1;

        for (int num : nums) {
            if (currentSum + num > maxAllowedSum) {
                requiredSubarrays++;
                currentSum = num;
            } else {
                currentSum += num;
            }
        }

        return requiredSubarrays <= maxSubarrays;
    }

    public static  int splitArrayDP(int[] nums, int k) {
        int n = nums.length;
        
        int prefix[] = new int[n + 1];

        for(int i = 0 ; i < n ; i++){
            prefix[i + 1] = prefix[i] + nums[i]; 
        }

        int dp[][] = new int[n + 1][k + 1];

        for(int[] row : dp){
            Arrays.fill(row , -1);
        }


        return partition(nums , prefix , k , 0, dp);
    }

    private static int partition(int[] nums , int[] prefix , int k , int ind, int[][] dp){
        int n = nums.length;
        if(k == 1){
            return prefix[n] - prefix[ind];
        }

        if(dp[ind][k] != -1) return dp[ind][k];

        int ans = Integer.MAX_VALUE;

        for(int i = ind ; i <= n - k ; i++){
            int first = prefix[i + 1] - prefix[ind];
            int rest = partition(nums , prefix , k - 1 , i + 1 , dp);
            int cost = Math.max(first , rest);

            ans = Math.min(ans , cost);
        }

        return dp[ind][k] = ans;
    }
   

    // Minimize Max Distance to Gas Station
    public static double minMaxDist(int[] stations, int K) {
        // code here
        int n = stations.length;
        
        double low = 0.0 ;
        double high = 0.0;
        
        
        for(int i = 1; i < n ; i++){
            high = Math.max(Math.abs(stations[i] - stations[i - 1]), high);
        }
        
        
        while(high - low > 1e-6){
            double mid = low + (high - low) / 2.0;
            
            if(placeStations(stations , K , mid)){
                high = mid;
            }
            else{
                low = mid;
            }
        }
        
        return high;
    }
    
    private static boolean placeStations(int arr[] , int k , double dist){
        int n  = arr.length;
        
        int cnt = 0;
        
        for(int i = 1 ; i < n ; i++){
            double gap = (double) Math.abs(arr[i] - arr[i - 1]);
            int segments = (int) Math.ceil(gap / dist);
            
            cnt += (segments - 1);
        }
        
        return cnt <= k;
    }
   
    // Median of two sorted array
    public static double medianArrB(int a[] , int b[]){

        int n1 = a.length;
        int n2 = b.length;

        int n = n1 + n2;

        int ind2 = n/2;
        int ind1 = ind2 - 1;

        int cnt = 0;

        int indEle1 = -1;
        int indEle2 = -1;

        int i=0 , j=0;

        while(i < n1 && j <n2 ){

            if(a[i] < b[j]){
                if(cnt == ind1) indEle1 = a[i];
                if(cnt == ind2) indEle2 = a[i];

                i++;
                cnt++;

            }else{
                if(cnt == ind1) indEle1 = b[j];
                if(cnt == ind2) indEle2 = b[j];

                j++;
                cnt++;
            }
        }

        while(i < n1){
            if(cnt == ind1) indEle1 = a[i];
            if(cnt == ind2) indEle2 = a[i];

                i++;
                cnt++;
        }

        while(j < n2){
            if(cnt == ind1) indEle1 = b[j];
            if(cnt == ind2) indEle2 = b[j];

            j++;
            cnt++;
        }



        if( n % 2 == 0) return (double)((indEle2 + indEle1) / 2.0);
        else return indEle2;
    }

    public static double medianArrO(int a[] , int b[]){

        int n1 = a.length , n2 = b.length;

        if(n1 > n2) return medianArrO(b, a);

        int low = 0 ; 
        int high = n1;

        int left = (n1 + n2 + 1)/2;

        int n = n1 + n2;

        while(low <= high){
            int mid1 = low + (high - low)/2;
            int mid2 = left - mid1;

            int l1 = Integer.MIN_VALUE , l2 = Integer.MIN_VALUE;
            int r1 = Integer.MAX_VALUE , r2 = Integer.MAX_VALUE;

            if(mid1 < n1) r1 = a[mid1];
            if(mid2 < n2) r2 = b[mid2];
            if(mid1 - 1 >= 0) l1 = a[mid1-1];
            if(mid2 - 1 >= 0) l2 = b[mid2-1];

            if(l1 <= r2 && l2 <= r1){
                if(n % 2 == 0) return (double)((Math.max(l1 , l2) + Math.min(r1 , r2)) / 2.0);
                else return Math.max(l1 , l2);
            }
            else if(l1 > r2){
                high = mid1 - 1;
            }
            else{
                low = mid1 + 1;
            }


        }

        return 0;


    }

    // K-th Element of two sorted arrays
    public static int kthElementofSortedB(int a[] , int b[] , int k){
        int n = a.length;
        int m = b.length;

        int ele = -1;
        int cnt = 0;

        int i = 0 , j= 0;

        while( i<n && j<m){
            if(a[i] < b[j]){
                if(cnt == (k-1)) ele = a[i];
                cnt++;
                i++;
            }else{

                if(cnt == (k-1))ele = b[j];
                cnt++;
                j++;
            }
        }

        while(i < n){
            if(cnt == (k-1)) ele = a[i];
            cnt++;
            i++;
        }
        while(j < m){
            if(cnt == (k-1)) ele = b[j];
            cnt++;
            j++;
        }

        return ele;
        
    }

    public static int  kthElementofSortedO(int a[] , int b[] ,int k){
        int n1 = a.length;
        int n2 = b.length;

        int left = k;

        int low = Math.max(0 , k - n1) , high = Math.min(k,n1);

        while(low <= high){

            int mid1 = (low + high)/2;
            int mid2 = left - mid1;

            int l1 = Integer.MIN_VALUE , l2 = Integer.MIN_VALUE;
            int r1 = Integer.MAX_VALUE , r2 = Integer.MAX_VALUE;

            if(mid1 < n1) r1 = a[mid1];
            if(mid2 < n2) r2 = b[mid2];
            if((mid1 - 1) >= 0) l1 = a[mid1 - 1];
            if((mid2 - 1) >= 0) l2 = b[mid2 - 1];

            if(l1 <= r2 && l2 <= r1) return Math.max(l1,l2);

            else if(l1 > r2) high = mid1 - 1;
            else low = mid1 + 1;
        }

        return -1;
    }

    // Find the row with maximum number of 1's
    public static int rowWithMax1sB(int mat[][]){
        int n = mat.length;
        int m = mat[0].length;

        int cntMax = 0;
        int index = 0;

        for(int i = 0 ; i< n ; i++){
            int cntOnes = 0;
            for(int j = 0 ; j<m ; j++){

                cntOnes += mat[i][j];

                if(cntOnes > cntMax){
                    index = i;
                    cntMax = cntOnes;
                }
            }
        }

        return index;
    }

    public static int rowWithMax1sO(int mat[][]){
        int n = mat.length;
        int m = mat[0].length;
        
        int cntMax = 0;
        int index = -1;

        for(int i = 0 ; i< n ; i++){
            int cntOnes = m -  lowerBound(mat[i], 1);
            if(cntOnes > cntMax){
                cntMax = cntOnes;
                index = i;
            }
        }

        return index;

    }

    // Search in 2D matrix I - optimal by flatting the matrix by 
    // index/m(col no) - gives row value 
    // index%m(col no) - gives col value

    public static boolean search2dI_B(int mat[][] , int x){
        // TC : O(n) + log₂M
        int n = mat.length;
        int m = mat[0].length;

        for(int i = 0 ; i<n ; i++){
            if(mat[i][0] <= x && x <= mat[i][m-1]){
                return binarySearch(mat[i], x);
            }
        }

        return false;

    }

    public static boolean search2dI_O(int mat[][] , int x){
        int n = mat.length;
        int m = mat[0].length;

        int low = 0 , high = (m * n -1);

        while(low <= high)
        {
            int mid = (low + high)/2;

            int row = mid / m ,  col = mid % m;

            if(mat[row][col] == x) return true;
            else if(mat[row][col] < x) low = mid + 1;
            else high = mid -1;
        }

        return false;
    }

    // Search in 2D matrix II
    public static int[] search2dII_B(int mat[][] , int x){
        int n = mat.length;
        int m = mat[0].length;

        for(int i = 0 ; i<n ; i++){
            int index =  binarySearchInd(mat[i] , x);

            if(index != -1) return new int[]{i , index};
        }

        return new int[] {-1,-1};
    }

    public static int[] search2dII_O(int mat[][] , int x){
        // TC: O(N + M)
        int n = mat.length;
        int m = mat[0].length;

        int row = 0 , col = m-1;

        while(row < n && col >= 0){

            if(mat[row][col] == x) return new int[]{row , col};
            else if(mat[row][col] < x) row++;
            else col--;
        }

        return new int[]{-1 , -1};
    }


    // Peak Element in a 2D matrix
    public static int maxElement(int mat[][] , int n , int m , int colNO){
    
        int maxVal = -1;
        int index = -1;

        for(int i = 0 ; i< n ; i++){
            if(mat[i][colNO] > maxVal){
                maxVal = mat[i][colNO];
                index = i;
            }
        }

        return index;

    }

    public static int[] findPeakElement2D(int mat[][]){
        // TC: O(log₂M * N)

        int n = mat.length;
        int m = mat[0].length;

        int low = 0 , high = m-1;

        while (low <= high) {
        
            int mid = (low + high)/2;

            int row = maxElement(mat , n , m , mid);

            int left = (mid - 1) >= 0 ? mat[row][mid - 1] : -1;
            int right = (mid + 1) < m ? mat[row][mid + 1] : -1;

            if(mat[row][mid] > left && mat[row][mid] > right){
                return new int[]{row , mid};
            }
            else if(mat[row][mid] < left) high = mid - 1;
            else low = mid + 1;
        }

        return new int[]{ -1, -1};

    }




    public static void main(String[] args) {

        int[] arrLU = {3, 5, 8, 9, 15, 19};

        int lb = lowerBound(arrLU , 3);

        int ub = upperBound(arrLU , 15);

        System.out.println("1. The Lower bound is : " + lb);
        System.out.println("2. The Upper bound is : " + ub);


        int[] arrFC = {10 , 20 , 30 , 40 , 50};

        int fl = findFloor(arrFC , 25);
        int cl = findCeil(arrFC , 25);

        System.out.println("3. The Floor value is : " + fl);
        System.out.println("4. The Ceil value is : " + cl);


        int[] arrFstLst = {3,4,13,13,13,20,40};

        int fst = findFirstOccurance(arrFstLst , 13);
        int lst = findLastOccurance(arrFstLst , 13);

        System.out.println("5. The first occurance of 13 is : " + fst);
        System.out.println("6. The last occurance of 13 is : " + lst);
        System.out.println("7. The count of occurances of 13 is (lst - fst) + 1 : " + ((lst - fst) + 1) );


        int ansFandL[] = findFirstAndLastOccurancesN(arrFstLst , 13);

        System.out.println("8. The first and last occurances of 13 are: " + java.util.Arrays.toString(ansFandL));

        int rtS1 = rtSortedSearchI(new int[]{7, 8, 9, 1, 2, 3, 4, 5, 6} , 1);

        System.out.println("9. The index of the element in the rotated sorted array[unique elements] : " + rtS1);


        boolean rtS2 = rtSortedSearchII(new int[]{7, 8, 1, 2, 3, 3, 3, 4, 5, 6} , 3);
        System.out.println("10. The element in the rotated sorted array[duplicate elements] : " + rtS2);

        int smInArr = smallestInRotated(new int[]{4, 5, 6, 7, 0, 1, 2, 3});

        System.out.println("11. The sortest element in the given array is : " + smInArr);


        int ansSingleNonDup = singleNonDuplicate(new int[]{1, 1, 2, 2, 3, 3, 4, 5, 5, 6, 6});

        System.out.println("12. The single element is: " + ansSingleNonDup);

        int peakEleIndex = findPeakElement(new int[]{1,2,3,4,5,6,7,8,5,1});

        System.out.println("13. The peak element in the array is : " + peakEleIndex);

        // Binary search on answers 
        System.out.println("14. The answer of floor sqrt on num is : " + floorSqrt(36));

        // M no of Boquets 
        int bloomDays[] = {7, 7, 7, 7, 13, 11, 12, 7};
        int K = 3;
        int M = 2;

        int ansNofBs = minDaysForMBouquetsO(bloomDays, M, K);
        if (ansNofBs == -1)
            System.out.println("15. We cannot make m bouquets.");
        else
            System.out.println("16. We can make given M bouquets on day :-  " + ansNofBs);

        // KOKO eating Bananas
        int[] piles = {7, 15, 6, 3};
        int H = 8;
        int minimumRateToEatBananas = KOKOEatingBananasO(piles , H);

        System.out.println("17. Koko should eat at least " + minimumRateToEatBananas + " bananas/hr.");

        // Smallest Divisor
        int ansMinDiv = smallestDivisorO(new int[]{1,2,5,9} , 7);

        System.out.println("18. The minimum divisor is: " + ansMinDiv);

        // Least capacity of the ship 
        int weights[] = {5,4,5,2,3,4,5,6};
        int days = 5;

        int shipCapcity = leastCapacityOfShipO(weights, days);
        System.out.println("19. The minimum capacity of the ship carring load should be : " + shipCapcity);

        // Kth Missing number
        int[] vec = {4, 7, 9, 10};
        System.out.println("20. The kth Missing number is : " + missingK_O(vec, 5));


        // Aggressive Cows
        int ansMax_minCows = AggressiveCows_O(new int[]{0,3,4,7,10,9} , 4);

        System.out.println("21. The maximum possible minimum distance is: " + ansMax_minCows);


        // Book Allocation
        int pagesPerStudents = BookAllocation_O(new int[] {25, 46, 28, 49, 24} , 4);

        System.out.println("22. The Maximum no of pages assigned to a student in minimum is : " + pagesPerStudents);

        int[] splitArrayInput = {7, 2, 5, 10, 8};
        int kSplit = 2;
        int splitArrayResult = splitArray(splitArrayInput, kSplit);
        System.out.println("23. The minimal largest sum after splitting the array into " + kSplit + " subarrays is: " + splitArrayResult);

        // Median of the two sorted array 

        double ansMedian = medianArrO(new int[]{1, 3 ,4, 7, 10, 12} , new int[]{2, 3, 6, 15});

        System.out.println("The median of two sorted arrays is " + ansMedian);

        // Kth element in the two sorted array 
        System.out.println("The k-th element of two sorted arrays is: " + kthElementofSortedB(new int[]{2,3,6,7,9}, new int[]{1,4,8,10}, 5));

        // The row with maximum number of 1's
        int matOnes[][] = {
            {0,0,0,0},
            {0,1,1,1},
            {0,0,1,1},
            {0,0,0,1},
        };

        rowWithMax1sB(matOnes);

        System.out.println("The row with the maximum number of 1's is: " +  rowWithMax1sO(matOnes));

        // Search in 2D Matrix - I 
        int search2dI[][] ={
            {1,2,3,4},
            {5,6,7,8},
            {9,10,11,12},
        }; 

        System.out.println("The number present in the 2d matrix ? " + search2dI_O(search2dI, 8)); 

        // Search in 2D Matrix - II

        int search2dII [][] = {
            {1, 4, 7, 11, 15},
            {2, 5, 8, 12, 19},
            {3, 6, 9, 16, 22},
            {10, 13, 14, 17, 24},
            {18, 21, 23, 26, 30},
        };

        System.out.println("The number present in the 2d matrix ? " +Arrays.toString( search2dII_O(search2dII, 15))); 

        // Find Peak element in a 2d matrix
        int [][] peak2D = {
            // {10,20,15},
            // {21,30,14},
            // {7,16,32},
            {1,4},
            {3,2}
        };
        System.out.println("The peak element in the 2D matrix : " + Arrays.toString(findPeakElement2D(peak2D)));



        // Reach a Number
        int reachNumberResult = reachNumber(7);
        System.out.println("24. The minimum number of moves to reach the target is: " + reachNumberResult);

        // Minimum Absolute Sum Difference
        int[] nums1 = {1, 7, 5};
        int[] nums2 = {2, 3, 5};
        int minAbsSumDiffResult = minAbsoluteSumDiff(nums1, nums2);
        System.out.println("25. The minimum absolute sum difference is: " + minAbsSumDiffResult);

        // Maximum Value at a Given Index in a Bounded Array
        int n = 4, index = 2, maxSum = 6;
        int maxValueResult = maxValue_O(n, index, maxSum);
        System.out.println("26. The maximum value at index " + index + " in a bounded array is: " + maxValueResult);

    
    }
    
}
