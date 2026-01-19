import java.util.*;
                    
public class SlidingWindow {

    /*
     * Sliding Window Templates (using while loops)
     * 
     * 1) Fixed-Size (Constant Window) Template
     *    - Use when you need to process all subarrays/substrings of a fixed size k.
     *    - Example: Find the maximum sum of any subarray of size k.
     *    - Steps:
     *      1. Compute the sum of the first window of size k (from index 0 to k-1).
     *      2. Slide the window by one element at a time using while loop:
     *         - Subtract the element going out (arr[l]).
     *         - Increment l and r.
     *         - Add the new element coming in (arr[r]).
     *         - Update the result (e.g., maxSum).
     *      Pseudocode:
     *          int sum = sum of arr[0..k-1];
     *          int maxSum = sum;
     *          int l = 0, r = k;
     *          while (r < n) {
     *              sum = sum - arr[l] + arr[r];
     *              maxSum = Math.max(maxSum, sum);
     *              l++;
     *              r++;
     *          }
     * 
     * 2) Variable-Size (Longest Subarray/Substring) Template
     *    - Use when you need the longest window that satisfies a condition (e.g., sum <= k, at most K distinct chars).
     *    - Steps:
     *      1. Initialize pointers l = 0, r = 0, and any needed state (e.g., sum = 0).
     *      2. Expand the window by moving r and updating state.
     *      3. While the window does not satisfy the condition, shrink from the left (move l).
     *      4. After adjusting, update the result if the window is valid.
     *      Pseudocode:
     *          int l = 0, r = 0, sum = 0, maxLen = 0;
     *          while (r < n) {
     *              sum += arr[r];
     *              while (sum > k) {
     *                  sum -= arr[l++];
     *              }
     *              maxLen = Math.max(maxLen, r - l + 1);
     *              r++;
     *          }
     * 
     * 3) Counting Subarrays with Exact Condition (Constant Condition)
     *    - Use when you need the number of subarrays/substrings with an exact property (e.g., sum == k).
     *    - Approach: Use the difference between two sliding window counts.
     *      - f1 = number of subarrays where sum <= k
     *      - f2 = number of subarrays where sum <= k-1
     *      - Result = f1 - f2 (gives number of subarrays with sum == k)
     * 
     * 4) Minimum Window Substring/Array
     *    - Use when you need the smallest window that satisfies a condition (e.g., contains all characters of t).
     *    - Steps:
     *      1. Expand the window by moving r and updating state.
     *      2. When the window satisfies the condition, try to shrink it from the left (move l) to find the minimum.
     *      3. Update the result whenever a valid window is found.
     *      Pseudocode (while loop version):
     *          int l = 0, r = 0, minLen = Integer.MAX_VALUE;
     *          while (r < n) {
     *              // update state with arr[r]
     *              while (window is valid) {
     *                  minLen = Math.min(minLen, r - l + 1);
     *                  // update state to remove arr[l]
     *                  l++;
     *              }
     *              r++;
     *          }
     *    - Pseudocode (for loop version):
     *          int l = 0, minLen = Integer.MAX_VALUE;
     *          for (int r = 0; r < n; r++) {
     *              // update state with arr[r]
     *              while (window is valid) {
     *                  minLen = Math.min(minLen, r - l + 1);
     *                  // update state to remove arr[l]
     *                  l++;
     *              }
     *          }
     * 
     * Edge Cases (especially with Map/Set):
     *    - Always check for empty input (n == 0), or k == 0, or k > n.
     *    - When using Map/Set, ensure to remove keys with zero count to avoid memory leaks and incorrect window size.
     *    - For character problems, be careful with character encoding (e.g., 'A' vs 'a').
     *    - When shrinking the window, update Map/Set counts before removing keys.
     *    - For problems with "at most k" or "exactly k" distinct elements, handle k == 0 and k > total unique elements.
     *    - For substring problems, always check if the window is valid before updating the result.
     */


    // Maximum Points You can obtain from cards
    public static int maxScore(int[] cardPoints, int k) {
        // TC: O(2K)
        int n = cardPoints.length;

        int lSum = 0 , rSum = 0 , maxSum = 0;

        for(int i = 0 ; i < k ; i++){
            lSum += cardPoints[i];
            maxSum = Math.max(maxSum , lSum);
        }

        int rInd = n-1;

        for(int i = k-1 ; i >= 0 ; i--){
            lSum -= cardPoints[i];
            rSum += cardPoints[rInd];

            rInd--;

            maxSum = Math.max(maxSum , (lSum + rSum));
        }

        return maxSum;
    }

    // Longest Substring Without Repeating Characters
    public static int lengthOfLongestSubstring_Br(String s) {
        // TC: O(N^2)
        int n = s.length();

        int len = 0 , maxLen = 0;
        for(int i = 0 ; i<n ; i++){
            int hash[] = new int[256];

            for(int j = i ; j<n ; j++){
                if(hash[s.charAt(j)] == 1) break;
                len = j - i +1;
                maxLen = Math.max(len , maxLen);
                hash[s.charAt(j)] = 1;
            }
        }

        return maxLen;

    }

    public static int lengthOfLongestSubstring_O(String s) {
        // TC: O(N) SC: O(1) since the hash array is of fixed size 256
        int n = s.length();

        int len = 0 , maxLen = 0;

        int hash[] = new int[256]; // to store index
        Arrays.fill(hash , -1);

        int l = 0 , r = 0;

        while(r < n){
            if(hash[s.charAt(r)] != -1){
                if(hash[s.charAt(r)] >= l){
                    l = hash[s.charAt(r)] + 1;
                }
            }

            len = r - l + 1;
            maxLen = Math.max(maxLen , len);

            hash[s.charAt(r)] = r;
            r++;
        }

        return maxLen;

    }

    // Max Consecutive Ones -III
    public static int longestOnes_Br(int[] nums, int k){
        int n = nums.length;
        int len = 0;
        int maxLen = 0;

        for(int i = 0 ; i<n ; i++){
            int zeros = 0;
            for(int j = i ; j<n ; j++){
                if(nums[j] == 0){
                    zeros++;
                }

                if(zeros <= k){
                    len = j-i+1;
                    maxLen = Math.max(maxLen , len);
                }else{
                    break;
                }
            }
        }

        return maxLen;
    }

    public static int longestOnes_O(int[] nums, int k) {
        // TC: O(N) SC: O(1)
        int n = nums.length;
        
        int maxLen = 0;

        int zeros = 0;

        int l=0,r=0;

        while(r < n){
          if(nums[r] == 0){
            zeros++;
          }

          if(zeros > k){
            if(nums[l] == 0){
                zeros--;
            }

            l++;
          }

          if(zeros <= k){
            maxLen = Math.max(maxLen , r-l+1);
          }

          r++;
        }

        return maxLen;
    }

    // Fruit Into Baskets
    public static int totalFruit_Br(int[] fruits) {
        int n = fruits.length;

        int maxLen = 0;

        for(int i = 0 ; i<n ; i++){
            Set<Integer> st = new HashSet<>();

            for(int j = i ; j < n ; j++){
                st.add(fruits[j]);

                if(st.size() <= 2){
                    maxLen = Math.max(maxLen , j-i+1);
                }else{
                    break;
                }
            }
        }

        return maxLen;
    }

    public static int totalFruit_O(int[] fruits) {
        // TC: O(N) SC: O(1) since the number of types of fruits is limited
        int n = fruits.length;

        int maxLen = 0;

        Map<Integer, Integer> mpp = new HashMap<>();

        int l = 0 , r = 0;
        
        while(r < n){

            mpp.put(fruits[r] , mpp.getOrDefault(fruits[r] , 0) + 1);

            if(mpp.size() > 2){
                if(mpp.containsKey(fruits[l])){
                    
                    int cnt = mpp.get(fruits[l]) - 1;

                    if(cnt == 0){
                        mpp.remove(fruits[l]);
                    }else{
                        mpp.put(fruits[l] , cnt);
                    }
                }

                l++;
            }

            if(mpp.size() <= 2){
                maxLen = Math.max(maxLen , r-l+1);
            }

            r++;

        }

        return maxLen;
    }

    // Longest Substring with K Distinct Characters
    public static int longestkSubstr_Br(String s, int k) {
        // TC: O(N^2) SC: O(1) since the hash array is of fixed size 256
        int n = s.length();
        
        int maxLen = -1; 
        
        Map<Character , Integer> mpp = new HashMap<>();
        
        for(int i = 0 ; i<n ; i++){
            mpp.clear();
            for(int j = i ; j<n ; j++){
                
                mpp.put(s.charAt(j) , mpp.getOrDefault(s.charAt(j) , 0) + 1 );
                
                if(mpp.size() == k){
                    maxLen = Math.max(maxLen , j-i+1);
                }else if (mpp.size() > k) {
                    break;
                }
            }
        }
        
        return maxLen;
    }

    public static int longestkSubstr_O(String s, int k) {
        // TC: O(N) SC: O(1) since the number of distinct characters is limited
        int n = s.length();
        
        int maxLen = -1; 
        
        Map<Character , Integer> mpp = new HashMap<>();
        
        int l = 0 , r = 0;
        
        while(r < n){
          
            mpp.put(s.charAt(r) , mpp.getOrDefault(s.charAt(r) , 0) + 1 );
            
            if(mpp.size() > k){
                if(mpp.containsKey(s.charAt(l))){
                    
                    int cnt = mpp.get(s.charAt(l)) - 1;
                    if(cnt == 0){
                        mpp.remove(s.charAt(l));
                    }else{
                        mpp.put(s.charAt(l) , cnt);
                    }
                    
                    l++;
                    
                }
            }
            
            if(mpp.size() == k){
                maxLen = Math.max(maxLen , r-l+1);
            }
            
            r++;
        }
        
        return maxLen;
    }

    // Number of Substrings Containing All Three Characters
    public static  int numberOfSubstrings_BrI(String s) {
        int n = s.length();

        Set<Character> st = new HashSet<>();

        int cnt = 0;

        for(int i = 0 ; i<n ; i++){
            st.clear();
            for(int j = i ; j < n ; j++){
                st.add(s.charAt(j));

                if(st.size() == 3){
                    cnt++;
                }
            }
        }

        return cnt;
    }

    public static  int numberOfSubstrings_BrII(String s) {
        int n = s.length();

        int cnt = 0;

        for(int i = 0 ; i<n ; i++){
            int hash[] = new int[3];
            for(int j = i ; j < n ; j++){

                hash[s.charAt(j) - 'a'] = 1;

                if((hash[0] + hash[1] + hash[2] ) == 3){
                    // cnt++;
                    cnt = cnt + (n-j);
                    break;
                }
            }
        }

        return cnt;
    }

    public static  int numberOfSubstrings(String s) {
        int n = s.length();

        int cnt = 0;

        int lastSeen[] = new int[3];
        Arrays.fill(lastSeen , -1);

        for(int i = 0 ; i<n ; i++){
            lastSeen[s.charAt(i) - 'a'] = i;

            if(lastSeen[0] != -1 && lastSeen[1] != -1 && lastSeen[2] != -1 ){
                cnt += Math.min(Math.min(lastSeen[0] , lastSeen[1]) , Math.min(lastSeen[1] , lastSeen[2])) + 1;
            }
        }

        return cnt;
    }

    public static  int numberOfSubstrings_O(String s) {
        // TC: O(N) SC: O(1) 
        int n = s.length();

        int cnt = 0;

        int a = -1 , b = -1 , c = -1;


        for(int i = 0 ; i<n ; i++){
            
            char ch = s.charAt(i);

            if(ch == 'a') a = i;
            else if(ch == 'b') b = i;
            else c = i;

            if(a != -1 && b != -1 && c != -1 ){
                cnt += Math.min(a , Math.min(b , c)) + 1;
            }
        }

        return cnt;
    }

    // Longest Reapeating Character Replacement
    public static  int characterReplacement_Br(String s, int k) {
        
        int n = s.length();

        int maxLen = 0;

        for(int i = 0 ; i<n ; i++){
            int hash[] = new int[26];
            int maxF = 0;
            for(int j = i ; j < n ; j++){

                hash[s.charAt(j) - 'A']++;

                maxF = Math.max(maxF , hash[s.charAt(j) - 'A']);

                int changes = (j-i+1) - maxF;

                if(changes <= k){
                    maxLen = Math.max(maxLen , (j-i+1));
                }else{
                    break;
                }
            }
        }

        return maxLen;
    }

    public static  int characterReplacement(String s, int k) {
        
        int n = s.length();

        int maxF = 0;

        int maxLen = 0;

        Map<Character , Integer> mpp = new HashMap<>();

        int l = 0 , r = 0;

        while(r < n){
            mpp.put(s.charAt(r) , mpp.getOrDefault(s.charAt(r) , 0) + 1);

            maxF = Math.max(maxF , mpp.get(s.charAt(r)));
            int changes = (r-l+1) - maxF;

            if(changes > k){
                if(mpp.containsKey(s.charAt(l))){
                    int cnt = mpp.get(s.charAt(l)) - 1;

                    if(cnt == 0) mpp.remove(s.charAt(l));
                    else mpp.put(s.charAt(l) , cnt);

                    l++;
                }
            }

            if(changes <= k){
                maxLen = Math.max(maxLen , (r-l+1));
            }
            
            r++;
        }

        return maxLen;
    }

    public static  int characterReplacement_O(String s, int k) {
        
        int n = s.length();

        int maxF = 0;

        int maxLen = 0;

        int hash[] = new int[26];

        int l = 0 , r = 0;

        while(r < n){
            hash[s.charAt(r) - 'A']++;

            maxF = Math.max(maxF , hash[s.charAt(r) - 'A']);
            int changes = (r-l+1) - maxF;

            if(changes > k){
                if(hash[s.charAt(l) - 'A'] != 0){
                    hash[s.charAt(l) - 'A']--;
                }

                l++;
            }

            if(changes <= k){
                maxLen = Math.max(maxLen , (r-l+1));
            }
            
            r++;
        }

        return maxLen;
    }

    // Binary Subarrays With Sum - type 3
    public static  int numSubarraysWithSum(int[] nums, int goal) {
        // TC: O(2 x 2N) SC: O(1)
        int f1 = func(nums , goal);
        int f2 = func(nums , goal-1);

        return f1 - f2;
    }

    private static int func(int nums[] , int goal){
        int n = nums.length;

        if(goal < 0) return 0;

        int cnt = 0;

        int l = 0 , r = 0;

        int sum = 0;

        while(r < n){
            sum += nums[r];

            while(sum > goal){
                sum -= nums[l];
                l++;
            }

            cnt += (r - l + 1);
            r++;
            
        }

        return cnt;
    }

    // Count number of nice subarrays
     public static int numberOfSubarrays(int[] nums, int k) {
        
        int f1 = func(nums , k);
        int f2 = func(nums , k-1);

        return f1 - f2;
    }

    // Subarray with K distinct Integers
    public static int subarraysWithKDistinct_Br(int[] nums, int k) {
        int n = nums.length;

        Map<Integer , Integer> mpp = new HashMap<>();

        int cnt = 0;

        for(int i = 0 ; i<n ; i++){
            mpp.clear();
            for(int j = i ; j<n ; j++){
                mpp.put(nums[j] , mpp.getOrDefault(nums[j] , 0) + 1);

                if(mpp.size() == k){
                    cnt++;
                }else if(mpp.size() > k) break;
            }
        }

        return cnt;
    }

    public static int subarraysWithKDistinct_O(int[] nums, int k) {
        int f1 = func_dist(nums , k);
        int f2 = func_dist(nums , k-1);

        return f1 - f2;
    }

    private static int func_dist(int nums[] , int k){
        if(k < 0) return 0;
        int n = nums.length;

        Map<Integer , Integer> mpp = new HashMap<>();

        int cnt = 0;

        int l = 0 , r = 0;

        while(r<n){
            mpp.put(nums[r] , mpp.getOrDefault(nums[r] , 0) + 1);

            while(mpp.size() > k){
                int val = mpp.get(nums[l]) - 1;

                if(val == 0){
                    mpp.remove(nums[l]);
                }else{
                    mpp.put(nums[l] , val);
                }

                l++;
            }

            if(mpp.size() <= k){
                cnt = cnt + (r - l + 1);
            }
            
            r++;
        }

        return cnt;
    }

    // Minimum Window Substring
    public static String minWindow_Br(String s, String t) {
        // TC: O(N^2) SC: O(1) since the hash array is of fixed size 256
        int n = s.length();
        int m = t.length();

        int minLen = Integer.MAX_VALUE;

        int startInd = -1;

        for(int i = 0 ; i<n ; i++){
            int hash[] = new int[256];
            int cnt = 0;

            for(int j = 0 ; j<m ; j++){
                hash[t.charAt(j)]++;
            }

            for(int k = i ; k<n ; k++){
                if(hash[s.charAt(k)] > 0) cnt++;

                hash[s.charAt(k)]--;

                if(cnt == m){
                    if( (k-i+1) < minLen){
                        minLen = (k-i+1);
                        startInd = i;
                    }

                    break;
                }
            }

        }

        return (startInd == -1) ? "" : s.substring(startInd, startInd + minLen);
    }

    public static String minWindow_O(String s, String t) {
        int n = s.length();
        int m = t.length();

        if (m > n) return "";

        int minLen = Integer.MAX_VALUE;

        int startInd = -1;


        int hash[] = new int[256];

        for(int i = 0 ; i<m ; i++){
            hash[t.charAt(i)]++;
        }

        int cnt = 0;

        int l = 0 , r = 0;

        while(r < n){

            if(hash[s.charAt(r)] > 0){
                cnt++;  
            }
            hash[s.charAt(r)]--;

    
            while(cnt == m){
                if(r-l+1 < minLen){
                   minLen = (r-l+1);
                   startInd = l;
                }

                hash[s.charAt(l)]++;
                if( hash[s.charAt(l)] > 0){
                    cnt--;
                }

                l++;
            }

            r++;

        }

        return (startInd == -1) ? "" : s.substring(startInd, startInd + minLen);
    }

    // Find all anagrams in a String
    public static  List<Integer> findAnagrams(String s, String p) {
        int sCnt[] = new int[26];
        int pCnt[] = new int[26];

        for(char ch : p.toCharArray()){
            pCnt[ch - 'a']++;
        }

        List<Integer> res = new ArrayList<>();

        int l = 0 , r = 0;

        int n = s.length();

        while(r < n){

            sCnt[s.charAt(r) - 'a']++;

            if(r - l + 1 == p.length()){
               if (Arrays.equals(sCnt, pCnt)) {
                    res.add(l);
                }

                sCnt[s.charAt(l) - 'a']--;
                l++;
            }

            r++;
        }

        return res;
    }

    // Permutation in String
    public static boolean checkInclusion(String s1, String s2) {
        int n = s1.length() , m = s2.length();

        if(m < n) return false;

        int hash[] = new int[26];

        for(int i = 0 ; i < n; i++){
            hash[s1.charAt(i) - 'a']++;
        }

        int charCnt = n;

        int l = 0 , r = 0;

        while(r < m){
            
            // Expand
            if (hash[s2.charAt(r) - 'a'] > 0) {
                charCnt--;
            }
            hash[s2.charAt(r) - 'a']--;
            r++;

            // Check
            if (charCnt == 0) return true;

            // Shrink 
            if (r - l == n) {
                if (hash[s2.charAt(l) - 'a'] >= 0) {
                    charCnt++;
                }
                hash[s2.charAt(l) - 'a']++;
                l++;
            }

            
        }

        return false;
    }
    
    // Frequency of the Most Frequent Element
    public static  int maxFrequency_Br(int[] nums, int k) {
        int n = nums.length;

        Arrays.sort(nums);

        int maxFreq = 1;

        for(int i = 0 ; i < n ; i++){
            int x = nums[i];

            int cost = 0;
            int cnt = 0;

            for(int j = 0 ; j <= i ; j++){
                cost += x - nums[j];
                if(cost <= k){
                    cnt++;
                }
                else break;
            }

            maxFreq = Math.max(cnt , maxFreq);
        }

        return maxFreq;
    }

    public static int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);

        int n = nums.length;

        long prefix[] = new long[n + 1];

        for(int i = 0 ; i < n ;i++){
            prefix[i + 1] = prefix[i] + nums[i]; 
        }

        int l = 0 , r = 0;
        int maxLen = 1;

        while(r < n){
            long windowSum = prefix[r + 1] - prefix[l];
            int len = r - l + 1;
            
            // long cost = (long) nums[r] * len - windowSum;

            // if(cost <= k){
            if((long) nums[r] * len <= windowSum + k){
                maxLen = Math.max(maxLen , len);
                r++;
            }else{
                l++;
            }
        }

        return maxLen;
    }

    public static int maxFrequency_O(int[] nums, int k) {
        Arrays.sort(nums);

        int n = nums.length;

        int l = 0 , r = 0;
        int maxLen = 1;

        long total = 0;

        while(r < n){
            total += nums[r];
            int len = r - l + 1;

            while ((long) nums[r] * len > total + k){
                total -= nums[l];
                l++;
                len = r - l + 1;
            }

            maxLen = Math.max(maxLen , len);
            r++;
        }

        return maxLen;
    }



    public static void main(String[] args) {

        // Maximum Points You can obtain from cards
        int[] cardPoints = {1,2,3,4,5,6,1};
        int k = 3;
        System.out.println( "Maximum Points You can obtain from cards : " + maxScore(cardPoints, k)); 

        // Longest Substring Without Repeating Characters
        String s = "abcabcbb";
        System.out.println("Length of Longest Substring Without Repeating Characters: " + lengthOfLongestSubstring_O(s));

        // Max Consecutive Ones -III
        int[] nums = {1,1,1,0,0,0,1,1,1,1,0};
        int kOnes = 2;
        System.out.println("Longest Consecutive Ones III : " + longestOnes_O(nums, kOnes));

        // Fruit Into Baskets
        int[] fruits = {3,3,3,1,2,1,1,2,3,3,4};
        System.out.println("Total Fruit Into Baskets: " + totalFruit_O(fruits));

        // Longest Substring with K Distinct Characters
        String s2 = "aabacbebebe";
        int kDistinct = 3;
        System.out.println("Longest Substring with " + kDistinct + " Distinct Characters: " + longestkSubstr_O(s2, kDistinct));

        // Number of Substrings Containing All Three Characters
        String s3 = "abcabc";
        System.out.println("Number of Substrings Containing All Three Characters : " + numberOfSubstrings_O(s3));

        // Longest Reapeating Character Replacement
        String s4 = "AABABBA";
        int kReplace = 1;
        System.out.println("Longest Repeating Character Replacement: " + characterReplacement_O(s4, kReplace));

        // Binary Subarrays With Sum
        int[] nums2 = {1,0,1,0,1};
        int goal = 2;
        System.out.println("Number of Subarrays With Sum " + goal + ": " + numSubarraysWithSum(nums2, goal));

        // Count number of nice subarrays
        int[] nums3 = {1,1,2,1,1};
        int kNice = 3;
        System.out.println("Number of Nice Subarrays with " + kNice + " odd numbers: " + numberOfSubarrays(nums3, kNice));

        // Subarray with K distinct Integers
        int[] nums4 = {1,2,1,2,3};
        int kDistinctInt = 2;
        System.out.println("Number of Subarrays with " + kDistinctInt + " distinct integers: " + subarraysWithKDistinct_O(nums4, kDistinctInt));


        // Minimum Window Substring
        String s5 = "ADOBECODEBANC";
        String t = "ABC";
        System.out.println("Minimum Window Substring: " + minWindow_O(s5, t));

        // Find all anagrams in a String
        String strA = "cbaebabacd";
        String p = "abc";

        List<Integer> indAnagram = findAnagrams(strA, p);
        System.out.println("The substring with start index which is an anagram are : " + indAnagram);
    }
}
