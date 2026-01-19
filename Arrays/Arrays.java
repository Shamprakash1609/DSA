
import java.util.*;
 

public class Arrays {

    public static int secondLargestElement(int[] nums) {
        int n = nums.length;
        if(n < 2) return -1;
    
        int first = Integer.MIN_VALUE;
        int second = Integer.MIN_VALUE;
        
        for(int i = 0 ; i < nums.length ; i++){
            if(nums[i] > first) {
                second = first;
                first = nums[i];
            } else if(nums[i] < first && nums[i] > second){
                second = nums[i];
            }
        }
    
        return (second == Integer.MIN_VALUE) ? -1 : second;
    }

    static boolean isSorted(int[] arr){
        int  n = arr.length;
        for(int i = 1 ; i<n ; i++){
            if(arr[i] >= arr[i-1]){
                
            } else return false;
        }

        return true;
    }

    public static boolean isRotatedSorted(int[] nums) {
        int n = nums.length;
        if(n == 1) return true;

        int cnt = 0;

        for(int i = 0 ; i<n ; i++){
            // Circular check
            if(nums[i] > nums[(i+1) % n]) cnt++;
        }

        return cnt <= 1;
    }

    static int removeDuplicates(int arr[]){

        int i = 0;
        int n = arr.length;

        for(int j = 1 ; j<n ; j++){
            if(arr[j] != arr[i]){
                arr[i+1] = arr[j];
                i++;
            }
        }

        return i+1;
    }

    static void rotateBArr(int arr[] , int d){
        int n = arr.length;
        // int temp[] = new int[d];
        List<Integer> temp = new ArrayList<>();

        for(int i =0 ; i< d ; i++){
            temp.add(arr[i]);
            // temp[i] = arr[i];
        }

        for(int i = d ; i< n ; i++){
            arr[i-d] = arr[i];
        }

        for(int i = n-d ; i< n ; i++ ){
            arr[i] = temp.get(i - (n-d));
        }

        // for (int i = 0; i < d; i++) {
        //     arr[n - d + i] = temp[i];
        // }
    }

    static void reverse(int arr[] , int start , int end){
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;

            start++;
            end--;
        }
    }
    static void rotateOArr(int arr[] , int d){
        int n = arr.length;
        d = d%n;

        reverse(arr, 0, d-1);
        reverse(arr, d , n-1);
        reverse(arr , 0 , n-1);

    }

    static int [] moveZerostoEnd(int arr[]){
        int n = arr.length;
        int j = -1;

        for(int i = 0 ; i< n ; i++){
            if(arr[i] == 0){
                j = i;
                break;
            }
        }

        if (j == -1) return arr; // If no zero is found

        for(int i = j+1 ; i<n ; i++){
            if(arr[i] != 0){
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;

                j++;
            }
        }

        System.out.println();

        return arr;
        
    }

    static ArrayList<Integer> findUnion(int arr1[] , int arr2[] ){

        /* Make sure both arrays are sorted
        Compare elements at arr1[i] and arr2[j]:
        If arr1[i] is smaller, add it to Union and increment i.
        If arr2[j] is smaller, add it to Union and increment j.
        If both are equal, add one of them to Union and increment both pointers.
        Ensure no duplicates are added. */
        
        int n = arr1.length;
        int m = arr2.length;

        int i=0,j=0;
        ArrayList<Integer>Union = new ArrayList<>();

        while(i<n && j<m){
            if(arr1[i] <= arr2[j]){
                if(Union.size() == 0 || Union.get(Union.size() -1) != arr1[i]) {
                   Union.add(arr1[i]);
                } 
                i++;
            } else {
                if(Union.size() == 0 || Union.get(Union.size()-1) != arr2[j]){
                    Union.add(arr2[j]);
                }
                j++;
            }
        }

        while (i < n) {
            if(Union.get(Union.size() -1) != arr1[i] ) {
                Union.add(arr1[i]);
            } 

            i++;
        }

        while (j < m) {
            if(Union.get(Union.size() -1 ) != arr2[j]){
                Union.add(arr2[j]);
            }
            j++;
        }

        return Union;

    }

    static ArrayList<Integer> findIntersectionB(int arr1[] , int arr2[]){

        int n = arr1.length;
        int m = arr2.length;

        ArrayList<Integer> ans = new ArrayList<>();
        int visited[] = new int[arr2.length];

        for(int i = 0 ; i<n ; i++){
            for(int j = 0 ; j<m ; j++){
                if(arr1[i] == arr2[j] && visited[j] == 0){
                    ans.add(arr1[i]);
                    visited[j] = 1;
                    break;
                } 

                else if(arr2[j] > arr1[i]){
                    break;
                }
            }
        }

        return ans;

    }

    static ArrayList<Integer> findIntersectionO(int arr1[] , int arr2[]){
        int n = arr1.length;
        int m = arr2.length;

        ArrayList<Integer> ans = new ArrayList<>();

        int i=0 ,j=0;

        while (i<n && j<m) {
            if(arr1[i] < arr2[j]){
                i++;
            } 
            else if(arr2[j] < arr1[i]){
                j++;
            }
            else{ // arr[i] == arr[j]
                ans.add(arr1[i]);
                i++;
                j++;
            }
        }

        return ans;
    }

    //  need to pass one more parameter n to function properly
    static int findMissingXOR(int arr[] ){
        // int n = arr.length;

        // int xor1 = 0, xor2 = 0;

        // for(int i = 0 ; i<n-1 ; i++){
        //     xor2 = xor2 ^ arr[i];
        //     xor1 = xor1 ^ (i + 1);
        // }
        // xor1 = xor1 ^ n;

        // return (xor1 ^ xor2);

        int n = arr.length;

       int xor1 = 0 , xor2 = 0;

       for(int i = 0 ; i < n ; i++){
            xor2 = xor2 ^ arr[i];
            xor1 = xor1 ^ (i + 1);
        }

       return (xor1 ^ xor2);
    }

    static int findMissingS(int arr[]){
        int n = arr.length;

        int sum = (n * (n + 1) / 2);

        int s2 = 0;

        for(int i = 0 ; i<n-1 ; i++){
            s2 += arr[i];
        }

        int missingNum = sum - s2;
        return missingNum;

    }

    static int findMissingH(int arr[]){ 
        int n = arr.length;
        int hash[] = new int[n + 1];

        for(int i = 0 ; i< n-1 ; i++){
            hash[arr[i]]++;
        }

        for(int i = 1 ; i<= n ; i++){
            if (hash[i] == 0) {
                return i;
            }
        }

        return -1;
    }

    public static int findMaxConsecutiveOnesB(int arr[]){
        int n = arr.length;
        int maxi = 0;
        int cnt = 0;
        for(int i = 0 ; i<n ; i++){
            if(arr[i] == 1){
                cnt++;
                maxi = Math.max(maxi, cnt);
            } else {
                cnt = 0;
            }
        }

        return maxi;
    }

    public static int getSingleElementHash(int []arr) {
        
        int n = arr.length;
        
        HashMap<Integer, Integer> mpp = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int value = mpp.getOrDefault(arr[i], 0);
            mpp.put(arr[i], value + 1);
        }
        
        for (Map.Entry<Integer, Integer> it : mpp.entrySet()){
            if (it.getValue() == 1) {
                return it.getKey();
            }
        }
        return -1;
    }

    public static int getSingleElementXOR(int[] arr) {
        int xor = 0;
        for (int num : arr) {
            xor ^= num;
        }
        return xor;
    }

    public static int getLongestSubarrayB(int []arr, long k){

        int n = arr.length;

        int len = 0;

        for(int i = 0 ; i<n ; i++){
            long  sum = 0;
            for(int j=i ; j<n ; j++){

                sum += arr[j];

                if(sum == k){
                    len = Math.max(len , j-i+1);
                }

            }
        }

        System.out.println();

        return len;
    }

    public static int getLongestSubarrayH(int [] arr , long k){
        int n = arr.length;

        Map<Long,Integer> preSumMap = new HashMap<>();
        long sum = 0;
        int maxLen = 0;

        for(int i = 0 ; i<n ; i++){
            
            sum += arr[i];

            if(sum == k){
                maxLen = Math.max(maxLen, i+1);
            }

            long rem = sum - k;

            if(preSumMap.containsKey(rem)){
                int len = i-preSumMap.get(rem);
                maxLen = Math.max(maxLen , len);
            }

            if(!preSumMap.containsKey(sum)){
                preSumMap.put(sum , i);
            }

        }

        return maxLen;

    }

    public static int getLongestSubarrayO(int [] arr , long k){
        int n = arr.length;

        int right = 0 , left = 0;
        long sum = arr[0];
        int maxLen = 0;
        while (right < n) {

            while (left <= right && sum > k) {
                sum -= arr[left];
                left++;
            }

            if(sum == k){
                maxLen = Math.max(maxLen, right - left + 1);
            }

            right++;
            if(right < n) sum += arr[right];
        }

        return maxLen;
    }

    public int findZerosSubarray(int[] arr) {
        
        /*
        Input: arr[] = [0, 0, 5, 5, 0, 0]
        Output: 6
        Explanation: The 6 subarrays are [0], [0], [0], [0], [0,0], and [0,0].
        */
       
        int n = arr.length;
        
        Map<Long , Integer> mpp = new HashMap<>();
        
        long sum = 0;
        int cnt = 0;
        
        for(int i = 0 ; i<n ; i++){
            sum += arr[i];
            
            if(sum == 0){
                cnt++;
            }
            
            if(mpp.containsKey(sum)){
                cnt += mpp.get(sum);
            }
            
            mpp.put(sum , mpp.getOrDefault(sum , 0) + 1);
            
        }
        
        return cnt;
    }

    public static int[] twoSumB(int arr[] , int t){
        int n = arr.length;

        int ans[] = new int[2];
        ans[0] = ans[1] = -1;

        for(int i = 0 ; i<n ; i++){
            for(int j = i+1 ; j<n ; j++){
                if(arr[i] + arr[j] == t){
                    ans[0] = i;
                    ans[1] = j;
                    return ans;
                }
            }
        }

        return ans;
    }
    
    public static int[] twoSumH(int arr[] ,int t){
        int n = arr.length;

        int ans[] = new int[2];
        ans[0] = ans[1] = -1;

        HashMap<Integer, Integer> mpp = new HashMap<>();

        for(int i = 0 ; i<n ; i++){
           int num = arr[i];
           int moreNeeded = t - num;
           if(mpp.containsKey(moreNeeded)){
                ans[0] = mpp.get(moreNeeded);
                ans[1] = i;
                return ans;
           }

           mpp.put(arr[i],i);
        }

        return ans;
    }

    public static String twoSumO(int arr[] , int t){
        int n = arr.length;

        java.util.Arrays.sort(arr);
        
        int left = 0, right = n - 1;
        while (left < right) {
            int sum = arr[left] + arr[right];
            if (sum == t) {
                return "YES";
            } else if (sum < t) left++;
            else right--;
        }
        return "NO";
    }
    
    public static int[] sortDNF(int arr[]){
        /*
         [0 - low-1] should be with 0's
         [low - mid-1] should be 1's
         [mid - high] - needed to be sorted
         [high+1 - n-1] should be 2's
         */
        int n = arr.length;

        int low = 0 ;
        int mid = 0;
        int high = n-1;

        while(mid <= high){
            if(arr[mid] == 0){
                // swapping arr[low] and arr[mid]
                int temp = arr[low];
                arr[low] = arr[mid];
                arr[mid] = temp;

                low++;
                mid++;
            }
            else if(arr[mid] == 1){
                mid++;
            }
            else{ // arr[mid] == 2
                // swapping arr[mid] and arr[high]

                int temp = arr[mid];
                arr[mid] = arr[high];
                arr[high] = temp;

                high--;
            }
        }

        return arr;
    }

    public static int majorityElementH(int arr[]){
        int n = arr.length;

        HashMap <Integer,Integer> mpp = new HashMap<>();

        for(int i = 0 ; i<n ; i++){
            int val = mpp.getOrDefault(arr[i], 0);
            mpp.put(arr[i] , val + 1);
        }

        for(Map.Entry<Integer,Integer> it : mpp.entrySet()){
            if(it.getValue() > (n/2)){
                return it.getKey();
            }
        }

        return -1;
    }
    public static int majorityElementO(int arr[]){
        // Moore's voting algorithm.
        int n = arr.length;

        int cnt = 0;

        int ele = 0;

        for(int i = 0 ; i<n ; i++){
            if(cnt == 0){
                cnt = 1;
                ele = arr[i];
            }
            else if(ele == arr[i]) cnt++;
            else cnt--; // ele != arr[i]
        }

        int sum = 0;

        for(int i=0 ; i<n ;i++){
            if(arr[i] == ele){
                sum++;
            }
        }

        if(sum > (n/2)) return ele;

        return -1;
    }
      
    public static long sumOfSubarrayKadane(int arr[]){
        int n = arr.length;

        long maxi = Long.MIN_VALUE;
        long sum = 0;

        int start = 0;

        int ansStart = -1 , ansEnd = -1;

        for(int i = 0 ; i<n ; i++){
            if(sum == 0) start =i;

            sum += arr[i];

            if(sum > maxi){
                maxi = sum;

                ansStart = start;
                ansEnd = i;
            }

            if(sum < 0){
                sum = 0;
            }
        }

        System.out.print("The subarray is: [");
        for (int i = ansStart; i <= ansEnd; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.print("]n");

        return maxi;

    }

    public static int buyAndSellStocks(int prices[]){
        int n = prices.length;

        int mini = prices[0];

        int maxProfit = 0;

        for(int i = 0 ; i<n ;i++){
            int cost = prices[i] - mini;
            maxProfit = Math.max(maxProfit, cost);
            mini = Math.min(mini, prices[i]);
        }
        return maxProfit;
    }

    public static ArrayList<Integer> rearrangePosNeg(ArrayList<Integer> arr){
        int n = arr.size();

        ArrayList<Integer> ans = new ArrayList<>(Collections.nCopies(n, 0));

        int posIndex = 0, negIndex = 1;

        for(int i = 0 ; i< n ; i++){
            if(arr.get(i) > 0){
                ans.set(posIndex, arr.get(i));
                posIndex += 2;
            }
            else{
                ans.set(negIndex, arr.get(i));
                negIndex += 2;
            }
        }

        return ans ;

    }
   
    public static int[] rearrangeArray(int[] nums) {
        int n = nums.length;

        int ans[] = new int[n];

        int posInd = 0 , negInd = 1;

        for(int i = 0 ; i<n ; i++){
            if(nums[i] > 0){
                ans[posInd] = nums[i];
                posInd += 2;
            }else{
                ans[negInd] = nums[i];
                negInd += 2;
            }
        }

        return ans;
    }

    public static int longestConsecutiveSeqB(ArrayList<Integer> arr){
        int n = arr.size();

        int cnt = 0; 
        int lastSmaller = Integer.MIN_VALUE;
        int longSeq = 1;

        Collections.sort(arr); // not prefered 

        for(int i = 0 ; i<n ; i++){
            if(arr.get(i) - 1 == lastSmaller){
                cnt++;
                lastSmaller = arr.get(i);
            }

            else if(arr.get(i) != lastSmaller){
                cnt = 1;
                lastSmaller = arr.get(i);
            }

            longSeq = Math.max(longSeq , cnt);
        }

        return longSeq;

    }

    public static int longestConsecutiveSeqO(ArrayList<Integer> arr){
        int n = arr.size();

        if(n == 0) return 0;

        int longest = 1;

        Set<Integer> set  = new HashSet<>();

        for(int i = 0 ; i< n ; i++){
            set.add(arr.get(i));
        }

        for(int it : set){
            if(!set.contains(it - 1)){
                int cnt = 1;
                int x = it;
                while (set.contains(x+1)) {
                    x += 1;
                    cnt += 1;
                }

                longest = Math.max(longest , cnt);
            }
        }

        return longest;
    }
    
    public static ArrayList<Integer> leadersInArrayB(int arr[]){ 
        int n = arr.length;

        ArrayList<Integer> ans = new ArrayList<>();

        for(int i = 0 ; i<n ; i++){
            boolean isLeader = true;

            for(int j = i+1 ; j<n ; j++){
                if(arr[j] > arr[i]){
                    isLeader = false;
                    break;
                }
            }

            if(isLeader) ans.add(arr[i]);
           
        }

        return ans;
    }

    public static ArrayList<Integer> leadersInArrayO(int arr[]){
        int n = arr.length;

        int maxi = arr[n-1];

        ArrayList<Integer> ans = new ArrayList<>();

        ans.add(arr[n-1]);

        for(int i = n-2 ; i >= 0 ; i--){
            if(arr[i] > maxi){
                ans.add(arr[i]);
                maxi = arr[i];
            }
        }

        return ans;
    }
    
    public static int findAllSubarraysWithGivenSum(int arr[] , int k){
        
        int n = arr.length;

        int preSum = 0;

        int cnt = 0;

        HashMap<Integer,Integer> mpp = new HashMap<>();

        mpp.put(0,1);
        
        for(int i = 0 ; i<n ; i++){
           
            preSum += arr[i];

            int rem = preSum - k;

            cnt += mpp.getOrDefault(rem, 0);

            mpp.put(preSum , mpp.getOrDefault(preSum, 0) + 1);
        }

        return cnt;

    }

    public static int AfindAllSubarraysWithGivenSum(int arr[], int k) {
        int n = arr.length;
        int preSum = 0;
        int cnt = 0;
        // Sum , count
        HashMap<Integer, Integer> mpp = new HashMap<>();

        mpp.put(0, 1);

        for (int i = 0; i < n; i++) {
            preSum += arr[i];

            int rem = preSum - k;
            
            if (mpp.containsKey(rem)) {
                cnt += mpp.get(rem);
            }

            
            if (mpp.containsKey(preSum)) {
                mpp.put(preSum, mpp.get(preSum) + 1);
            } else {
                mpp.put(preSum, 1);
            }
        }

        return cnt;
    }

    // Continuous Subarray Sum
    public static boolean checkSubarraySum(int[] nums, int k) {
        int n = nums.length;
        
        // Remainder , Index
        Map<Integer,Integer> mpp = new HashMap<>();

        int preSum = 0;

        mpp.put(0 , -1);

        for(int i = 0 ; i < n ; i++){
            
            preSum += nums[i];

            int rem = preSum % k;

            if(!mpp.containsKey(rem)){
                mpp.put(rem, i);
                
            }
            else{
                if (i - mpp.get(rem) >= 2) {
                    return true;
                }
            }
        }

        return false;
    }

    // Subarray Sums Divisible by K
    public static  int subarraysDivByK(int[] nums, int k) {
        int n = nums.length;

        int preSum = 0;
        int cnt = 0;

        // Sum , Count
        Map<Integer ,Integer> mpp = new HashMap<>();

        mpp.put(0 , 1);

        for(int i = 0 ; i < n ; i++){
            preSum += nums[i];

            int rem = preSum % k;

            if (rem < 0) {
                rem += k;
            }

            if(mpp.containsKey(rem)){
                cnt += mpp.get(rem);
            }

            mpp.put(rem, mpp.getOrDefault(rem, 0) + 1);

        }

        return cnt;
    }
    
    // Product of Array Except Self
    public static int[] productExceptSelf_O(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];

        // Step 1: Build left product into ans[]
        ans[0] = 1;
        for (int i = 1; i < n; i++) {
            ans[i] = ans[i - 1] * nums[i - 1];
        }

        // Step 2: Multiply right product into ans[]
        int right = 1;
        for (int i = n - 1; i >= 0; i--) {
            ans[i] *= right;
            right *= nums[i];
        }

        return ans;
    }

    public static  int[] productExceptSelf_B(int[] nums) {
        int n = nums.length;

        int [] ans = new int[n];

        int left[] = new int[n];
        int right[] = new int[n];
        
        left[0] = 1;
        for(int i = 1 ; i< n; i++){
            left[i] = left[i-1] * nums[i-1];
        }

        right[n-1] = 1;
        for(int i = n-2 ; i >= 0 ; i--){
            right[i] = right[i+1] * nums[i+1];
        }

        for(int i = 0 ; i < n ; i++){
            ans[i] = left[i] * right[i];
        }

        return ans;
    }

    // Max Circular Subarray Sum
    public static  int maxCircularSum_B(int arr[]) {
        int n = arr.length;
       
        int maxSum = Integer.MIN_VALUE;
       
        for(int i = 0 ; i <n ; i++){
            int sum = 0;
            for(int j = i + 1 ; j < i + n ; j++ ){
                int ind = j % n;
                sum += arr[ind];
                maxSum = Math.max(sum , maxSum);
            }
        }
       
        return maxSum;
        
    }

    public static  int maxCircularSum_O(int arr[]) {
        int n = arr.length;
       
        int totSum = 0;
        
        int maxSum = Integer.MIN_VALUE;
        int minSum = Integer.MAX_VALUE;
        
        int curMax = 0;
        int curMin = 0;
    
       
        for(int i = 0 ; i <n ; i++){
            totSum += arr[i];
            
            curMax = Math.max(arr[i] , curMax + arr[i]);
            maxSum = Math.max(maxSum , curMax);
            
            curMin = Math.min(arr[i], curMin + arr[i]);
            minSum = Math.min(minSum , curMin);
        }
        
        if(maxSum < 0){
           return maxSum;
        }
       
        return Math.max(maxSum , totSum - minSum);
        
    }
    
    // Pascals Triangle
    public static  List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        for(int i = 1 ; i <= numRows ; i++){
            ans.add(pascals(i));
        }

        return ans;
    }

    public static List<Integer> pascals(int n){
        
        List<Integer> arr = new ArrayList<>();
        int ans = 1;
        arr.add(ans);
        for(int i = 1 ; i < n ; i++){
            ans = ans * (n - i);
            ans = ans / i;

            arr.add(ans);
        }

        return arr;
    }

    // merge sorted Array
    public static  void merge(int[] nums1, int m, int[] nums2, int n) {
        

        int i = m-1 , j = n-1;
        int k = m + n - 1;
        
        while(i >= 0 && j >= 0){
            if(nums1[i] > nums2[j]){
                nums1[k] = nums1[i];
                k--;
                i--;
            }
            else{
                nums1[k] = nums2[j];
                k--;
                j--;
            }
        }

        while (j >= 0) {
            nums1[k--] = nums2[j--];
        }
    
    }

    // Equivalent Dominoes Pairs
    public static  int numEquivDominoPairs(int[][] dominoes) {
        int n = dominoes.length;

        int primes[] = {2 , 3 , 5 , 7 , 11 , 13 , 17 , 23 , 29};

        Map<Integer , Integer> mpp = new HashMap<>();

        for(int i = 0 ; i < n; i++){    
            int num = primes[dominoes[i][0] - 1] * primes[dominoes[i][1] - 1];

            mpp.put(num , mpp.getOrDefault(num , 0) + 1);
        }


        int ans = 0;

        for(int p : mpp.keySet()){
            ans += (mpp.get(p) * (mpp.get(p) - 1)) / 2;
        }

        return ans;
    }

    public static void main(String[] args) {

        // int arr[] = {12,4,55,0,0,2,12,34,0};
        int arr[] = {1,2,3,4,5,6,7};

        int arrM[] = {1,2,4,5};


        int missing = findMissingXOR(arrM);

        System.out.println("\nThe missing value in the given array (arr) is : " + missing + "\n" );

        boolean sorted = isSorted(arr);

        if(sorted){
            System.out.println("The array is sorted\n");
        } else {
            System.out.println("The array is not sorted\n");
        }

       int res = secondLargestElement(arr);
       System.out.println("\nThe Second largest value in the array is : " + res);

       int d = 3;

       System.out.println("\nThe rotation of the arr for " + d + " rotations is");
       rotateOArr(arr, d);
       for(int nums : arr){
        System.out.print(nums + " ");
       }

       moveZerostoEnd(arr);
       System.out.println("\n\nmoved the zeros in the array");
       for(int nums : arr){
        System.out.print(nums + " ");
       }

       int arr1[] = {1, 2, 3, 4, 5, 6, 7, 8, 9,10}; 
       int arr2[] = {2, 3, 4, 4, 5, 11, 12}; 

       ArrayList<Integer> UnionArr = findUnion(arr1, arr2);

       ArrayList<Integer> IntersectionArr = findIntersectionO(arr1, arr2);

       System.out.println("\n\nThe union of the arrays arr1 & arr2 is : " + UnionArr);
       System.out.println("\nThe intersection of the arrays arr1 & arr2 is : " + IntersectionArr);


       int numOnlyonce = getSingleElementHash(new int[]{4, 1, 2, 1, 2});
    //    int numOnlyonce = getSingleElementXOR(new int[]{4, 1, 2, 1, 2});


       System.out.println("The number appears only once in this array { 4, 1, 2, 1, 2} is : " + numOnlyonce);



       int arrLSub[] = {1,2,3,1,1,1,1,3,3,3};

       long k = 6;

       int longestSubarrayLen = getLongestSubarrayO(arrLSub, k);

       System.out.println("The longest sub array length of the array is : " + longestSubarrayLen);

       int [] indices = twoSumH(arrLSub, (int) k);

       System.out.print("\nThe indices are that give the twoSum of target "+ k + " is indices : " );

       for(int nums : indices){
        System.out.print(nums + " ");
       }


       System.out.print("\n\nThe sorted array with DNF algorithm of arrays containing 0's , 1's and 2's were : ");

       

       int[] DNfarr = sortDNF(new int[]{2,2,1, 2, 1, 1, 1, 0, 1, 0, 0, 1}); 
       for(int nums : DNfarr){
        System.out.print(nums + " ");
       }

       int majority = majorityElementO(new int[]{2, 2, 1, 1, 1, 2, 2});

       System.out.println("\n\nThe element that appears greater than (n/2) is : " + majority + "\n");



       long maxSum = sumOfSubarrayKadane(new int[]{ -2, 1, -3, 4, -1, 2, 1, -5, 4});

       System.out.println("\nThe maximum sum calculated using Kadane's algorithm is : "+maxSum + "\n");

       int maxProfit = buyAndSellStocks(new int[] {7,1,5,3,6,4});

       System.out.println("The maximum profit from the prices is : "+ maxProfit + "\n");


    //    ArrayList<Integer> A = new ArrayList<>(java.util.Arrays.asList(1, 2, -4, -5));

       ArrayList<Integer> ans = rearrangePosNeg(new ArrayList<>(java.util.Arrays.asList(1, 2, -4, -5)));

       System.out.println("\nThe array after rearranging positive and negatives is : "+ ans + "\n");


       int maxSeq = longestConsecutiveSeqO(new ArrayList<>(java.util.Arrays.asList(1,101,103,102,105,4,2,6,2,1,1,1,3,4,4)));
       System.out.println("\nThe longest consecutive sequence of the array is : " + maxSeq + "\n" );

       ArrayList<Integer> leaders = leadersInArrayB(new int[]{4, 7, 1, 0});
       System.out.println("\nThe leaders in the array are: " + leaders);


       int subarrayCnt = findAllSubarraysWithGivenSum(new int[] {1,2,3,-3,1,1,1,4,2,-3} , 3);

       System.out.println("\n\nThe count of subarrays with given sum is: " + subarrayCnt);

    // Pascals Triangle
    int numRows = 5;
    List<List<Integer>> pascal = generate(numRows);
    System.out.println("\nPascal's Triangle with " + numRows + " rows:");
    for (List<Integer> row : pascal) {
        System.out.println(row);
    }

    // Max Circular Subarray Sum
    int[] circArr = {5, -2, 3, 4};
    int maxCircSumB = maxCircularSum_B(circArr);
    int maxCircSumO = maxCircularSum_O(circArr);
    System.out.println("\nMax Circular Subarray Sum (Brute): " + maxCircSumB);
    System.out.println("Max Circular Subarray Sum (Optimal): " + maxCircSumO);

    // Product of Array Except Self
    int[] prodArr = {1, 2, 3, 4};
    int[] prodExceptSelfO = productExceptSelf_O(prodArr);
    int[] prodExceptSelfB = productExceptSelf_B(prodArr);
    System.out.println("\nProduct of Array Except Self (Optimal): " + java.util.Arrays.toString(prodExceptSelfO));
    System.out.println("Product of Array Except Self (Brute): " + java.util.Arrays.toString(prodExceptSelfB));

    // Continuous Subarray Sum
    int[] subSumArr = {23, 2, 4, 6, 7};
    int kSub = 6;
    boolean hasSubSum = checkSubarraySum(subSumArr, kSub);
    System.out.println("\nContinuous Subarray Sum exists (sum divisible by " + kSub + "): " + hasSubSum);

    

    }
}
