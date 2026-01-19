import java.util.*;
import java.util.Arrays;
import java.util.Stack;

public class CheatSheet4 {

    // Roman to Integer
    public static  int romanToInt(String s) {
        int n = s.length();

        Map<Character , Integer> mpp = new HashMap<>();
        mpp.put('I' , 1);
        mpp.put('V' , 5);
        mpp.put('X' , 10);
        mpp.put('L' , 50);
        mpp.put('C' , 100);
        mpp.put('D' , 500);
        mpp.put('M' , 1000);

        if(n == 1) return mpp.get(s.charAt(0));

        int ans = 0;

        for(int i = 0 ; i < n ;  i++){
            int cur = mpp.get(s.charAt(i));
            int nxt = ((i + 1) < n) ? mpp.get(s.charAt(i+1)) : 0;
 
            if(nxt > cur){
                ans += nxt - cur;
                i++;
            }else{
                ans += cur;
            }
        }

        return ans;
    }

    // Integer to Roman 
    public static String intToRoman(int num) {
        Map<Integer , String> mpp = new HashMap<>();

        mpp.put(1 , "I");
        mpp.put(4 , "IV");
        mpp.put(5 , "V");
        mpp.put(9 , "IX");
        mpp.put(10 , "X");
        mpp.put(40 , "XL");
        mpp.put(50 , "L");
        mpp.put(90 , "XC");
        mpp.put(100 , "C");
        mpp.put(400 , "CD");
        mpp.put(500 , "D");
        mpp.put(900 , "CM");
        mpp.put(1000 , "M");

        int val[] = {1000 , 900 , 500 , 400 , 100 , 90 , 50 , 40 , 10 , 9 , 5 , 4 , 1};


        String res = "";

        for(int i =0 ; i < val.length; i++){
            
            if(num >= val[i]){
                int q = num / val[i];
                num = num % val[i];

                for(int j = 0 ; j < q ; j++){
                    res += mpp.get(val[i]);
                }
            }
        }

        return res;
    }

    // Greedy Algo
    // Boats to Save people
    public static int numRescueBoats(int[] people, int limit) {
        int n = people.length;
        int boats = 0;
        Arrays.sort(people);

        // Greed criteria :- Pair up heaviest and Lightest people in the same boat
        int l = 0 , r = n - 1;
        while(l <= r){
            if(people[l] + people[r] <= limit){
                l++;
            }
            
            r--;
            boats++;
        }

        return boats;
    }

    // Bag of Tokens
    public static int bagOfTokensScore(int[] tokens, int power) {
        
        int n = tokens.length;

        Arrays.sort(tokens);

        int l = 0 , r = n-1;

        int score = 0 , maxScore = 0;

        while(l <= r){
            if(power >= tokens[l]){
                power -= tokens[l];
                l++;
                score++;
                maxScore = Math.max(maxScore , score);
            }
            else if(score >= 1){
                power += tokens[r];
                r--;
                score--;
            }
            else{
                break;
            }
        }


        return maxScore;
    }

    // Longest Palindrome by Concatenating Two Letter Words
    public static int longestPalindrome(String[] words) {
        int n = words.length;

        int hash[][] = new int[26][26];
        int palindrome_cnt = 0;
        int mid_cnt = 0;

        for (String word : words) {
            int a = word.charAt(0) - 'a';
            int b = word.charAt(1) - 'a';

            if (hash[b][a] > 0) {
                palindrome_cnt += 4;
                hash[b][a]--;
                if (a == b) {
                    mid_cnt--; // remove one unpaired middle when we find its match
                }
            } else {
                hash[a][b]++;
                if (a == b) {
                    mid_cnt++; // count only unpaired same-letter words
                }
            }
        }

        if (mid_cnt > 0) {
            palindrome_cnt += 2;
        }

        return palindrome_cnt;
    }

    // Broken Calculator
    public static int brokenCalc(int startValue, int target) {
        // TC : O(Log(Target))
        if(startValue >= target) return startValue - target;

        if(target % 2 == 0){
            return 1 + brokenCalc(startValue , target / 2);
        }

        return 1 + brokenCalc(startValue , target + 1);
    }

    // Minimum Time to Make Rope Colorful
    public static int minCost(String colors, int[] neededTime) {
        // TC : O(n) 
        int curr = 0;
        int res = 0;
        for(int i = 0 ; i < colors.length() ; i++){
            if(i > 0 && colors.charAt(i) != colors.charAt(i - 1)){
                curr = 0;
            }

            res += Math.min(curr , neededTime[i]);
            curr = Math.max(curr , neededTime[i]);
        }

        return res;
    }

    // Minimum Rounds to Complete All Tasks 
    public static  int minimumRounds(int[] tasks) {
        int n = tasks.length;

        Map<Integer, Integer> mpp = new HashMap<>();

        for(int t : tasks){
            mpp.put(t , mpp.getOrDefault(t , 0) + 1);
        }

        int rounds = 0;

        for(int key : mpp.keySet()){
            int cnt = mpp.get(key);

            if(cnt == 1) return -1;
            else if(cnt % 3 == 0){
                rounds += cnt / 3;
            }
            else{
                rounds += (cnt / 3) + 1;
            }
        } 

        return rounds;  
    }

    // Maximum Bags With Full Capacity of Rocks
    public static  int maximumBags_PQ(int[] capacity, int[] rocks, int additionalRocks) {
        int n = capacity.length;
        PriorityQueue<Integer> requiredRocks = new PriorityQueue<>();

        for(int i = 0 ; i < n; i++){
            requiredRocks.add(capacity[i] - rocks[i]);
        }

        int filledCnt = 0;
        while(!requiredRocks.isEmpty() && additionalRocks >= requiredRocks.peek()){
            additionalRocks -= requiredRocks.poll();
            filledCnt++;
        }  

        return filledCnt;
        
    }

    public static int maximumBags_Sr(int[] capacity, int[] rocks, int additionalRocks) {
        int n = capacity.length;

        int requiredRocks[] = new int[n];

        for(int i = 0 ; i < n; i++){
            requiredRocks[i] = capacity[i] - rocks[i];
        }

        Arrays.sort(requiredRocks);

        int filledCnt = 0;

        for(int i = 0 ;i < n; i++){
            if(additionalRocks >= requiredRocks[i]){
                additionalRocks -= requiredRocks[i];
                filledCnt++;
            }
        }


        return filledCnt;
    }

    // Earliest Possible Day of Full Bloom
    public static int earliestFullBloom(int[] plantTime, int[] growTime) {
        int n = growTime.length;
        List<int[]> pAndG = new ArrayList<>();

        for(int i = 0 ; i < n; i++){
            pAndG.add(new int[]{plantTime[i] , growTime[i]});
        }

        pAndG.sort((a,b) -> Integer.compare(b[1] , a[1]));

        int time = 0;
        int maxi = 0;

        for(int curr[] : pAndG){
            time += curr[0]; // Plant Time
            maxi = Math.max(maxi , time + curr[1]); // Grow Time 
        }

        return maxi;
    }

    // Optimal Partition of String
    public static int partitionString(String s) {
        // TC : O(N) SC : O(N)
        int n = s.length();

        Set<Character> st = new HashSet<>();

        int cnt = 0;

        for(char c : s.toCharArray()){
            if(st.contains(c)){
                // count partion
                cnt++;

                // reset
                st = new HashSet<>();
                st.add(c);
            }else{
                st.add(c);
            }
        }

        if(!st.isEmpty()) cnt++;

        return cnt;

    }

    // Dota2 Senate
    public static String predictPartyVictory(String senate) {
        int n = senate.length();

        Deque<Integer> dire = new ArrayDeque<>();
        Deque<Integer> radiant = new ArrayDeque<>();

        for(int i = 0 ; i < n; i++){
            if(senate.charAt(i) == 'R'){
                radiant.add(i);
            }else{
                dire.add(i);
            }
        }

        while(!dire.isEmpty() && !radiant.isEmpty()){
            int d = dire.removeFirst();
            int r = radiant.removeFirst();

            if(r < d){
                radiant.add(r+n);
            }
            else{
                dire.add(d + n);
            }
        }

        if(!radiant.isEmpty()){
            return "Radiant";
        }
        else{
            return "Dire";
        }
    }

    // Minimum Deletions to Make Character Frequencies Unique
    public static int minDeletions(String s) {
        int n = s.length();
        int hash[] = new int[26];

        for(int i = 0; i < n; i++){
            hash[s.charAt(i) - 'a']++;
        }

        Set<Integer> st = new HashSet<>();

        int deletions = 0;

        for(int i = 0 ; i < hash.length; i++){
            if(hash[i] != 0){
                int freq = hash[i];
               if(st.contains(freq)){
                    while(st.contains(freq)){
                        freq--;
                        deletions++;
                    }
                    if(freq > 0) st.add(freq);
               }

               else{
                st.add(freq);
               }
            }
        }

        return deletions;
    }

    // Remove Colored Pieces if Both Neighbors are the Same Color
    public static boolean winnerOfGame(String colors) {
      int n = colors.length();

      int aliceMoves = 0;
      int bobMoves = 0;

      for(int i = 1 ; i < n - 1 ; i++){
        if(colors.charAt(i-1) == colors.charAt(i) && colors.charAt(i) == colors.charAt(i+1)){
            if(colors.charAt(i) == 'A') aliceMoves++;
            else bobMoves++;
        }
      }

      return aliceMoves > bobMoves;

    }

    // Eliminate Maximum Number of Monsters
    public static int eliminateMaximum(int[] dist, int[] speed) {
        int n = dist.length;
        
        int time[] = new int[n];

        for(int i = 0 ; i< n; i++){
            time[i] = (int) (Math.ceil((double) dist[i] / (double) speed[i]));
        }

        Arrays.sort(time);

        int killCnt = 0;

        for(int i = 0 ; i < n ; i++){ // Simulating for Every Minute till n(max time)
            if(time[i] <= i){
                return killCnt;
            }
            killCnt++;
        }

        return killCnt;

    }

    // Happy Number
    public static boolean isHappy(int n) {
        Set<Integer> used = new HashSet<>();

        while(true){

            int sum = 0;
            while( n != 0){
                sum += Math.pow( n % 10 , 2.0);
                n /= 10;
            }

            if(sum == 1) return true;

            n = sum;

            if(used.contains(n)){
                return false;
            }
            
            used.add(n);
        }
    }

    // Plus One
    public static int[] plusOne(int[] digits) {
        
        int n = digits.length;

        for(int i = n - 1 ; i >= 0 ; i--){
            if(digits[i] < 9){
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }

        int newN[] = new int[n + 1];
        newN[0] = 1;
        for(int i = 1 ; i < newN.length ; i++){
            newN[i] = digits[i-1];
        }

        return newN;

    }

    // Pass The Pillow
    public static  int passThePillow_B(int n, int time) {
        int pass = 1;
        int dir = 1;
        for(int i = 0 ; i < time ; i++){
            if(pass == n){
                dir = -1;
            }else if (pass == 1) {
                dir = 1;    
            }

            pass += dir;
        }

        return pass;
    }

    public static int passThePillow_O(int n, int time) {
        int returnTime = 2*n - 2;
        int pass = time % returnTime;
        if(pass < n) return pass + 1;
        else return returnTime - pass + 1;
    }

    // Water Bottles
    public static int numWaterBottles(int numBottles, int numExchange) {
        int fullBottles = numBottles;
        int emptyBottles = 0;

        int ans = 0;

        while(fullBottles > 0){
            ans += fullBottles; // Drink
            emptyBottles += fullBottles; // Empty
            fullBottles = emptyBottles / numExchange; // Exchange
            emptyBottles = emptyBottles % numExchange; // LeftOvers
        } 

        return ans;  
    }

    // Valid Palindrome II
    public static boolean validPalindromeII(String s) {
        int i = 0;
        int j = s.length() - 1;

        while(i < j){
            if(s.charAt(i) == s.charAt(j)){
                i++;
                j--;
            }
            else{
                return isPalindrome(s , i+1 , j) || isPalindrome(s , i , j-1);
            }
        }

        return true;
    }

    private static boolean isPalindrome(String s , int i , int j){
        while(i < j){
            if(s.charAt(i) != s.charAt(j)){
                return false;
            }
            i++;
            j--;
        }

        return true;
    }

    // Simplify Path
    public static String simplifyPath(String path) {
        String dir[] = path.split("/");

        Stack<String> st = new Stack<>();

        for(String file : dir){
            if(file.equals("..")){
                if(!st.isEmpty()){
                    st.pop();
                }
            }
            else if(!file.isEmpty() && !file.equals(".") ){
                st.push(file);
            }
        }

        StringBuilder sb = new StringBuilder();

        for(String s : st){
            sb.append("/").append(s);
        }

        return sb.length() > 0 ? sb.toString() : "/";
    }

    // Minimum Moves to Equal Array Elements
    public static int minMoves(int[] nums) {
        int n = nums.length;

        int mini = Integer.MAX_VALUE;

        for(int i : nums){
            mini = Math.min(i , mini);
        }

        int moves = 0;

        for(int i = 0 ; i < n; i++){
            moves += (nums[i] - mini);
        }

        return moves;
    }

    // Minimum Moves to Equal Array Elements 2
    public static int minMoves2(int[] nums) {
        int n = nums.length;

        Arrays.sort(nums);

        int i = 0 , j = n - 1;
        int cnt = 0;

        while(i < j){
            cnt += Math.abs(nums[j] - nums[i]);

            i++;
            j--;
        }

        return cnt;
    }

    // Balanced K-Factor Decomposition LC:Contest(456)
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

    // Contiguous Array (PrefixSum) 
    public static int findMaxLength(int[] nums) {
        int n = nums.length;

        for(int i = 0 ; i < n ; i++){
            if(nums[i] == 0){
                nums[i] = -1;
            }
        }

        Map<Integer , Integer> mpp =new  HashMap<>(); //(sum , index)

        int maxLen = 0;
        
        int sum = 0;
        mpp.put(0 , -1);

        for(int i = 0 ; i < n ; i++){
            sum += nums[i];

            if(mpp.containsKey(sum)){
                int len = i - mpp.get(sum);
                maxLen = Math.max(len , maxLen);
            }else{
                mpp.put(sum , i);
            }
        }

        return maxLen;

    }

    // Maximum Number of Coins You Can Get
    public static int maxCoins(int[] piles) {
        int n = piles.length;

        quickSort(piles , 0 , n - 1);

        System.out.println(Arrays.toString(piles));

        int total = 0;

        int i = 0 , j = n-1;
        while(i < j){
            total += piles[i + 1];
            i += 2;
            j -= 1;
        }
        
        return total;
    }

    private static void quickSort(int arr[] , int lb , int ub){
        if(lb >= ub) return;

        int p = partition(arr , lb , ub);
        quickSort(arr , lb , p - 1);
        quickSort(arr , p+1 , ub);
    }

    private static int partition(int arr[] , int lb , int ub){
        int pivot = arr[lb];
        int low = lb;
        int high = ub;

        while(true){
            while(low <= high && arr[low] >= pivot){
                low++;
            }

            while(low <= high && arr[high] < pivot){
                high--;
            }

            if(low <= high){
                int temp = arr[low];
                arr[low] = arr[high];
                arr[high] = temp;
            }else{
                break;
            }
        }

        int temp = arr[lb];
        arr[lb] = arr[high];
        arr[high] = temp;

        return high;
    }

    // Subarray Product Less Than K
    public static int numSubarrayProductLessThanK(int[] nums, int k) {
        if(k <= 1) return 0;

        int n = nums.length;

        int prod = 1;

        int cnt = 0;

        int l = 0 , r = 0;

        while(r < n){
            prod *= nums[r];

            while(l <= r && prod >= k){
                prod /= nums[l];
                l++;
            }

            cnt += (r - l + 1);
            r++;
        }

        return cnt;
    }

    // Largest Sum of Averages
    public static double largestSumOfAverages(int[] nums, int k) {
        int n = nums.length;

        double[][] memo = new double[k + 1][n];

        double largestAvg = findPartitions(nums , memo , k ,0);

        return largestAvg;
    }

    private static double findPartitions(int nums[] , double[][] memo ,int k , int ind){
        int n = nums.length;

        if (ind >= n) {
            return 0;
        }

        if(k == 1){
            return memo[k][ind] = average(nums , ind , n - 1);
        }

        if(memo[k][ind] != 0) return memo[k][ind]; 

        double best = 0.0;

        for(int i = ind ; i <= n - k ; i++){

            double avg = average(nums, ind , i);

            best = Math.max(best, avg + findPartitions(nums, memo ,k - 1, i + 1));
        }

        return memo[k][ind] = best;
    }

    private static double average(int nums[] , int start , int end){

        double sum = 0.0;
        for(int i = start ; i <= end ; i++){
            sum += nums[i];
        }

        if (end < start) {
            return 0;
        }

        sum /= (end - start + 1);

        return sum;
    }

    // Shifting Letters
    public static String shiftingLetters(String s, int[] shifts) {
        int n = shifts.length;

        int shift = 0;

        char str[] = s.toCharArray();

        for(int i = n-1 ; i >= 0; i--){
            shift = (shift + shifts[i]) % 26;
            str[i]  = (char) ((str[i] - 'a' + shift) % 26 + 'a');
        }

        return new String(str);
    }
    
    // Minimum Number of Operations to Make Array Empty
    public static int minOperations(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<>();

        // Count frequencies
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        int cost = 0;

        for (int count : freq.values()) {
            if (count == 1) {
                return -1; // can't remove a single element
            }
            // ceil(count / 3.0) → using (count + 2) / 3 for integer math
            cost += (count + 2) / 3;
        }

        return cost;
    }

    // Harmonic Strings
    public static int haramonicStr(String S){
        int n = S.length();
        int maxLen = 0;
    

        for(int i = 0 ; i < n ; i++){
            int len = 0;
            int blockSize = 2;
            int j = i;

            while(true){
                int nextChar = 'a';
                StringBuilder block = new StringBuilder();

                for(int k = 0 ; k < blockSize ; k++){
                    block.append((char) (nextChar + k));
                }

                if(j + blockSize > n) break;

                if((S.substring(j , j + blockSize)).equals(block.toString())){
                    len += blockSize;
                    j += blockSize;
                    blockSize++;
                }else{
                    break;
                }

            }

            maxLen = Math.max(len , maxLen);
        }

        return maxLen;

    }


    public static void main(String[] args) {

        // Roman to Integer
        // String roman = "MCMXCIV"; 
        // String roman = "IX"; 
        String roman = "MMXLVIII"; 
        int result = romanToInt(roman);
        System.out.println("Integer value: " + result);
 
        // Integer to Roman
        // int roman_num = 9;
        int roman_num = 2048;
        String romanStr = intToRoman(roman_num);
        System.out.println("Roman value: " + romanStr);

        // Boats to Save people
        int people[] = {3,8,7,1,4};
        int limit = 9;
        System.out.println("The number of boats required to transport people is : " + numRescueBoats(people, limit));

        // Bag of Tokens 
        int[] tokens = {100, 200, 300, 400};
        int power = 200;
        int maxScore = bagOfTokensScore(tokens, power);
        System.out.println("Maximum score from bag of tokens: " + maxScore);

        // Longest Palindrome by Concatenating Two Letter Words
        String[] words = {"ab", "ty", "yt", "lc", "cl", "ab"};
        System.out.println("Longest palindrome length: " + longestPalindrome(words));

        // Broken Calculator
        int startValue = 2, target = 3;
        System.out.println("Broken calculator min operations: " + brokenCalc(startValue, target));

        // Minimum Time to Make Rope Colorful
        String colors = "abaac";
        int[] neededTime = {1,2,3,4,5};
        System.out.println("Minimum cost to make rope colorful: " + minCost(colors, neededTime));

        // Minimum Rounds to Complete All Tasks
        int[] tasks = {2,2,3,3,2,4,4,4,4,4};
        System.out.println("Minimum rounds to complete all tasks: " + minimumRounds(tasks));

        // Maximum Bags With Full Capacity of Rocks (PQ)
        int[] capacity = {2,3,4,5}, rocks = {1,2,4,4};
        int additionalRocks = 2;
        System.out.println("Maximum bags (PQ): " + maximumBags_PQ(capacity, rocks, additionalRocks));
        System.out.println("Maximum bags (Sort): " + maximumBags_Sr(capacity, rocks, additionalRocks));

        // Earliest Possible Day of Full Bloom
        int[] plantTime = {1,4,3}, growTime = {2,3,1};
        System.out.println("Earliest full bloom: " + earliestFullBloom(plantTime, growTime));

        // Optimal Partition of String
        String s = "abacaba";
        System.out.println("Optimal partition count: " + partitionString(s));

        // Dota2 Senate
        String senate = "RDD";
        System.out.println("Winning party: " + predictPartyVictory(senate));

        // Minimum Deletions to Make Character Frequencies Unique
        String s2 = "aaabbbcc";
        System.out.println("Minimum deletions for unique frequencies: " + minDeletions(s2));

        // Remove Colored Pieces if Both Neighbors are the Same Color
        String colors2 = "AAABABB";
        System.out.println("Winner of game: " + winnerOfGame(colors2));

        // Eliminate Maximum Number of Monsters
        int[] dist = {1,3,4}, speed = {1,1,1};
        System.out.println("Eliminate maximum monsters: " + eliminateMaximum(dist, speed));

        // Happy Number
        int happyNum = 19;
        System.out.println("Is happy number: " + isHappy(happyNum));

        // Plus One
        int[] digits = {9,9,9};
        System.out.println("Plus one: " + Arrays.toString(plusOne(digits)));

        // Pass The Pillow
        int n = 4, time = 5;
        System.out.println("Pass the pillow (Brute): " + passThePillow_B(n, time));
        System.out.println("Pass the pillow (Optimal): " + passThePillow_O(n, time));

        // Water Bottles
        int numBottles = 9, numExchange = 3;
        System.out.println("Total water bottles drunk: " + numWaterBottles(numBottles, numExchange));

        // Valid Palindrome II
        String palindromeTest = "abca";
        System.out.println("Valid palindrome II: " + validPalindromeII(palindromeTest));

        // Simplify Path
        String path = "/a/./b/../../c/";
        System.out.println("Simplified path: " + simplifyPath(path));

        // Minimum Moves to Equal Array Elements
        int[] nums = {1,2,3};
        System.out.println("Minimum moves to equal elements: " + minMoves(nums));

        // Minimum Moves to Equal Array Elements 2
        int[] nums2 = {1,10,2,9};
        System.out.println("Minimum moves to equal elements 2: " + minMoves2(nums2));

        // Balanced K-Factor Decomposition
        int nFactor = 100, kFactor = 2;
        int[] decomposition = minDifference(nFactor, kFactor);
        System.out.println("Balanced K-Factor Decomposition: " + Arrays.toString(decomposition));

        // Contiguous Array (PrefixSum)
        int[] arr = {0, 1, 0, 1, 1, 0, 0};
        System.out.println("Max length of contiguous array: " + findMaxLength(arr));

        // Maximum Number of Coins You Can Get
        int[] piles = {2,4,1,2,7,8};
        System.out.println("Maximum coins you can get: " + maxCoins(piles));

        // Subarray Product Less Than K
        int[] numsSub = {10, 5, 2, 6};
        int kSub = 100;
        System.out.println("Number of subarrays with product less than k: " + numSubarrayProductLessThanK(numsSub, kSub));

        // Largest Sum of Averages
        int[] numsAvg = {9,1,2,3,9};
        int kAvg = 3;
        System.out.println("Largest sum of averages: " + largestSumOfAverages(numsAvg, kAvg));

        // Shifting Letters
        String shiftStr = "abc";
        int[] shifts = {3,5,9};
        System.out.println("Shifting letters: " + shiftingLetters(shiftStr, shifts));

        // Minimum Number of Operations to Make Array Empty
        int[] numsOps = {2,3,3,2,2,4,4,4,4,4};
        System.out.println("Minimum operations to make array empty: " + minOperations(numsOps));

        // Harmonic Strings
        String harmonic = "ababcabc";
        System.out.println("Harmonic string max length: " + haramonicStr(harmonic));

        
    }
}
