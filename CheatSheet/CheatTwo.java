import java.util.*;

public class CheatTwo {

    public static  String convertToBase7(int num) {

        StringBuilder sb = new StringBuilder();
        
        boolean isNeg = num < 0;
        num = Math.abs(num);

        if(num == 0) return "0";

        while(num != 0){
            int digit = num % 7;
            sb.append(digit);
            num = num/7;
        }

        if(isNeg) sb.append('-');

        sb.reverse();

        return sb.toString();
    }
    
    public static void WaveArray(int[] arr) {
        
        for(int i = 0 ; i< arr.length - 1 ; i+= 2){
            int temp = arr[i];
            arr[i] = arr[i+1];
            arr[i+1] = temp;
        }
    }

    public static int countFreq(int[] arr, int x) {
        // code here
        int n = arr.length;
        
        int low = 0, high = n-1;
        
        int first = -1 , last = -1;
        
        while(low <= high){
            int mid = (low + high)/2;
            
            if(arr[mid] == x){
                first = mid;
                
                high = mid - 1;
            }else if(arr[mid] > x){
                high = mid - 1;
            }else{
                low = mid + 1;
            }
        }
        
       low = 0;
       high = n-1;
        
        while(low <= high){
            int mid = (low + high)/2;
            
            if(arr[mid] == x){
                last = mid;
                
                low = mid + 1;
            }else if(arr[mid] < x){
                low = mid + 1;
            }else{
                high = mid - 1;
            }
        }
        
        if(first == -1) return 0;
        else return last - first + 1;
    }

    public static String remainingString(String s, char ch, int count) {
        
        int cnt = 0;
        String res = "";
        for(int i = 0 ; i < s.length() ; i++){
            if(s.charAt(i) == ch){
                cnt++;
                
                if(cnt == count){
                    res = s.substring(i+1 , s.length());
                }
                
            }
        }
        
        return res;
        
    }

    public static ArrayList<ArrayList<Integer>> uniqueRow(int a[][],int r, int c)
    {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        
        
        for(int i = 0 ; i< r ; i++){
            ArrayList<Integer> row = new ArrayList<>();
            for(int j = 0 ; j < c ; j++){
                row.add(a[i][j]);
            }
            
            if(!ans.contains(row)){
                ans.add(row);
            }
           
        }
        
        
        return ans;
    }

    public static int longestConsecutive(int[] arr) {
        
        int n = arr.length;
        
        if(n == 0) return 0;
        
        int longest = 1;
        
        Set<Integer> set  = new HashSet<>();

        for(int i = 0 ; i< n ; i++){
            set.add(arr[i]);
        }
        
        for(int it : arr){
            if(!set.contains(it - 1)){
                int cnt = 1;
                int x = it;
                
                while(set.contains(x+1)){
                    cnt++;
                    x++;
                }
                
                longest = Math.max(cnt , longest);
            }
        }
        
        return longest;
    }

    public static int[] greaterElement(int arr[], int n) {
        // Complete the function
        Map<Integer , Integer> mpp = new HashMap<>();
        
        int copy[] = new int[n];
        
        for(int i = 0 ; i<n ; i++){
            copy[i] = arr[i];
        }
        
        Arrays.sort(copy);
        
        int i = 0;
        for(;i< n-1 ; i++){
            mpp.put(copy[i] , copy[i+1]);
        }
        
        mpp.put(copy[i] , -10000000);
        
        for(i = 0 ; i<n ; i++){
            arr[i] = mpp.get(arr[i]);
        }
        
        return arr;
        
    }

    public static int[] greaterElement_O(int arr[], int n) {
        // Complete the function
        TreeSet<Integer> st = new TreeSet<>();
        
        for(int num : arr){
            st.add(num);
        }
        
        
        int[] res = new int[n];
        
        for(int i = 0 ;i<n ; i++){
            Integer ng = st.higher(arr[i]);
            if(ng == null){
                res[i] = -10000000;
            }else{
                res[i] = ng;
            }
        }
        
        return res;
    }

    public String Specialrev(String str) {
        // complete the function here
        int n = str.length();
        
        int i = 0 , j = n-1;
        
        char[] arr = str.toCharArray();
        
        while(i < j){
            if(!Character.isLetter(arr[i])){
                i++;
                continue;
            }
            
            if(!Character.isLetter(arr[j])){
                j--;
                continue;
            }
            
            else{
                 char temp = arr[i];
                 arr[i] = arr[j];
                 arr[j] = temp;
                 
                 i++;
                 j--;
            }
        }
        
        return new String(arr);
    }

    public String reverseWords(String s) {
        // Code here
        
        // Stack<String> st = new Stack<>();
        
        s = s.trim();
        
        String parts[] = s.split("\\s+");
        
        // for(int i = 0 ; i< parts.length ; i++){
        //     st.push(parts[i]);
        // }
        
        StringBuilder sb = new StringBuilder();
        
        // while(!st.isEmpty()){
        //     sb.append(st.pop());
        //     sb.append(" ");
        // }
        
        
        for(int i = parts.length-1;  i>= 1 ; i--){
            sb.append(parts[i]);
            sb.append(" ");
        }
        
        sb.append(parts[0]);
        
        return sb.toString();
    }

    public String reverseWordsIII(String s) {
        char[] arr = s.toCharArray();

        int left = 0 , right = 0;

        while(right < arr.length){
            if(arr[right] == ' '){
                reverseIII(arr , left , right-1);
                left = right + 1;
            }

            right++;
        }

        reverseIII(arr , left , right-1);

        return new String(arr);
    }

    private void reverseIII(char arr[] , int left , int right){
        while(left<right){
            char temp = arr[left];
            arr[left++] = arr[right];
            arr[right--] = temp;
        }
    }


    // Group Anagrams
    public static  List<List<String>> groupAnagrams(String[] strs) {
        // TC : O(N * K)
        if(strs == null || strs.length == 0){
            return new ArrayList<>();
        }

        Map< String , List<String>> freqStrMpp = new HashMap<>();
        for(String str : strs){
            String freqStr = getFreqStr(str);

            if(freqStrMpp.containsKey(freqStr)){
                freqStrMpp.get(freqStr).add(str);
            }else{
                List<String> strList = new ArrayList<>();
                strList.add(str);
                freqStrMpp.put(freqStr , strList);
            }
        }

        return new ArrayList<>(freqStrMpp.values()); 
    }

    public static String getFreqStr(String str){
        int freq[] = new int[26];

        for(char ch : str.toCharArray()){
            freq[ch - 'a']++;
        }

        StringBuilder fstr = new StringBuilder();

        char c = 'a';
        for(int i : freq){
            fstr.append(c);
            fstr.append(i);
            c++;
        }

        return fstr.toString();
    }

    // Find the String in the grid
    public static int[][] searchWord(char[][] grid, String word)
    {
        // Code here
        List<int[]> ans = new ArrayList<>();
        
        
        int x[] = {-1 , 0 , 1, 1, 1 , 0 , -1 , -1};
        int y[] = {1, 1 , 1 , 0 , -1 , -1 , -1 , 0};
        
        int n = grid.length;
        int m = grid[0].length;
        int s = word.length();
        
        for(int i = 0 ; i<n ; i++){
            for(int j = 0 ; j<m ; j++){
                if(exists(grid , word , i , j , n , m , s , x , y)){
                    ans.add(new int[]{i , j});
                }
            }
        }
        
        if (ans.size() == 0) {
            return new int[][]{{-1}};
        }
        
        int res[][] = new int[ans.size()][2];
        
        for(int i = 0 ; i<ans.size() ; i++){
            res[i] = ans.get(i);
        }
        
        return res;
    }
    
    public static boolean exists(char[][] grid , String word , 
    int i , int j ,int n , int m, int s , int[] x , int[] y){
        
        if (grid[i][j] != word.charAt(0)) return false;
        
        for(int dir = 0 ; dir < 8 ; dir++){
            
            int i1 = i , j1 = j , k = 0;
            
            int x1 = x[dir] , y1 = y[dir];
            
            while(i1 >= 0 && i1 < n && j1 >= 0 && j1 < m && k<s){
                if(word.charAt(k) == grid[i1][j1]){
                    i1 += x1;
                    j1 += y1;
                    k++;
                }else{
                    break;
                }
            }
            
            if(k == s){
                return true;
            }
            
        }
        
        return false;
    }

    // Reversing the Vowel
    public static String modify (String s)
    {
        // your code here
        int n = s.length();
        
        char[] arr = s.toCharArray();
        
        int i = 0 , j = n-1;
        
        while(i < j){
            while(i < j && !isVowel(arr[i])){
                i++;
            }
            
            while(i < j && !isVowel(arr[j])){
                j--;
            }
            
            char temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            
            i++;
            j--;
            
        }
        
        return new String(arr);
    }
    
    public static boolean isVowel(char ch){
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
    }


    // FindSum 
    public static String findSum(String s1, String s2) {
        // code here
        if (s1.length() < s2.length()) {
            String temp = s1;
            s1 = s2;
            s2 = temp;
        }
        
        StringBuilder result = new StringBuilder();
        int carry = 0;
        
        int n1 = s1.length(), n2 = s2.length();
        
        for(int i = 0 ; i<n1 ; i++){
            int dig1 = s1.charAt(n1 - i -1) - '0';
            int dig2 = (i < n2) ? s2.charAt(n2 - i - 1) - '0' : 0;
            
            int sum = dig1 + dig2 + carry;
            result.append(sum % 10);
            
            carry = sum / 10;
            
        }
        
        if(carry > 0){
            result.append(carry);
        }
        
        String finalRes = result.reverse().toString();
        
        int i = 0;
        while( i< finalRes.length() - 1 && finalRes.charAt(i) == '0'){
            i++;
        }
        
        return finalRes.substring(i);
    }

    // Indexes of Sum array sum
    public static ArrayList<Integer> indSubarraySum(int[] arr, int target) {
        
        int n = arr.length;
        
        int right = 0, left = 0;
        
        long sum = arr[0];
        
         ArrayList<Integer> ans = new ArrayList<>();

        
        while(right < n){
            
            while(left <= right && sum > target){
                sum -= arr[left];
                left++;
            }
            
            if(sum == target){
                ans.add(left + 1);
                ans.add(right + 1);
                return ans;
            }
            
            right++;
            if(right < n) sum += arr[right];
        }
        
        ans.add(-1);
        return ans;
       
    }

    public static void printPattern(String s) {
        // Your code here
        int n = s.length();
        
        int mid = n/2;
        
        StringBuilder result = new StringBuilder();
        StringBuilder temp = new StringBuilder();
        
        temp.append(s.charAt(mid));
        result.append(temp).append("$ ");
        
        int left = mid - 1;
        int right = mid + 1;
        
        while(left >= 0 || right < n){
            if(right < n){
                temp.append(s.charAt(right));
                result.append(temp).append("$ ");
                right++;
            }
            if(left >= 0){
                temp.append(s.charAt(left));
                result.append(temp).append("$ ");
                left--;
            }
            
        }
        
        // if (result.charAt(result.length() - 1) == '$') {
        //         result.deleteCharAt(result.length() - 1);
        // }
        
        if (result.length() >= 2) {
        result.delete(result.length() - 1, result.length());
        }
        
         System.out.println(result.toString());
    }

    // Validate Ip Address
    public static boolean isValidIp_RX(String s) {
        
        String numBlock = "([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])";
        
        String regex = "^(" + numBlock + "\\.){3}" + numBlock ;
        
        return s.matches(regex);

    }

    public boolean isValidIp_Br(String s) {
        // Write your code here
        String[] parts = s.split("\\.");
        
        if (parts.length != 4) return false;
        
      for(String part : parts){
           
          if (part.length() == 0 || part.length() > 3) return false;
           
            for(char c : part.toCharArray()){
                if(!Character.isDigit(c)) return false;
            }
            
            if (part.length() > 1 && part.charAt(0) == '0') return false;
            
            int num = Integer.parseInt(part);
            if (num < 0 || num > 255) return false;
            
      }
       
      return true;
    }

    public String validIPv6ORv4(String queryIP) {
        String Ipv4 = "([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])";
        String regexIpv4 = "^("+Ipv4+"\\.){3}"+Ipv4+"$";

        String Ipv6 = "([0-9a-fA-F]){1,4}";
        String regexIpv6 = "^("+Ipv6+"\\:){7}"+Ipv6+"$";

        if(queryIP.matches(regexIpv4)) return "IPv4";
        else if(queryIP.matches(regexIpv6))return "IPv6";
        else return "Neither";
    }


    public static  String validIPAddress_LC(String queryIP) {
        if(queryIP.chars().filter(ch -> ch == '.').count() == 3){
            return isValidIPv4(queryIP) ? "IPv4" : "Neither";
        }
        else if(queryIP.chars().filter(ch -> ch == ':').count() == 7){
            return isValidIPv6(queryIP) ? "IPv6" : "Neither";
        }
        else return "Neither";
    }

    private static boolean isValidIPv4(String queryIp){
        String[] parts = queryIp.split("\\." , -1);

        if (parts.length != 4) return false;

        for(String part : parts){
            if(part.length() == 0 || part.length() > 3) return false;
            if(part.length() > 1 && part.charAt(0) == '0') return false;

            for(char ch : part.toCharArray()){
                if(!Character.isDigit(ch)) return false;
            }

            int num = Integer.parseInt(part);
            if(num < 0 || num > 255) return false;
        }

        return true;
    }

    private static boolean isValidIPv6(String queryIP){
        if(queryIP.contains("::")) return false;

        String[] parts = queryIP.split("\\:" , -1);

        if(parts.length > 8) return false;

        String hexaDigits = "0123456789abcdefABCDEF";

        for(String part : parts){
            if(part.length() == 0 || part.length() > 4) return false;
            for(char ch : part.toCharArray()){
                if(hexaDigits.indexOf(ch) == -1) return false;
            }
        }

        return true;
    }
    

    // Word Search problem 
    public static boolean wordExist(char[][] board, String word) {
        int n = board.length;
        int m = board[0].length;

        for(int i = 0 ; i<n ; i++){
            for(int j = 0 ; j<m ; j++){
                if(board[i][j] == word.charAt(0)){
                    if(wordSearch(i , j , n , m , board , word , 0)) return true;
                }
            }
        }

        return false;
    }

    public static boolean wordSearch(int i , int j , int n , int m , char[][] board , String word , int k){
        if(k == word.length()) return true;
        if(i < 0 || j < 0 || i == n || j == m || board[i][j] != word.charAt(k)) return false;
        char ch = board[i][j];
        board[i][j] = '$';
        boolean op1 = wordSearch(i+1 , j , n , m , board , word , k+1);
        boolean op2 = wordSearch(i-1 , j , n , m , board , word , k+1);
        boolean op3 = wordSearch(i , j+1 , n , m , board , word , k+1);
        boolean op4 = wordSearch(i , j-1 , n , m , board , word , k+1);
        board[i][j] = ch;
        return op1 || op2 || op3 || op4;
    }


    // Shortest path in Binary Matrix
    public static  int shortestPathBinaryMatrix(int[][] grid) {
        /*
        Time Complexity (TC): O(n^2), where n is the number of rows/columns in the grid.
        - Each cell is visited at most once, and for each cell, we check up to 8 directions.
        - In the worst case, all n^2 cells are processed.

        Space Complexity (SC): O(n^2)
        - The queue can hold up to O(n^2) elements in the worst case (all cells are 0).
        - The grid is modified in-place, so no extra grid is used.
        */
        
        int n = grid.length;

        int[][] directions = {{-1,-1} , {-1,0} , {-1,1} , {0,1} , {1,1} , {1,0} , {1,-1} , {0 , -1}};

        if(grid[0][0] == 1 || grid[n-1][n-1] == 1) return -1;

        Queue<int[]> q = new LinkedList<>();

        q.offer(new int[]{0,0,1}); // row , col , steps
        grid[0][0] = 1;

        while(!q.isEmpty()){
            int curr[] = q.poll();
            int row = curr[0];
            int col = curr[1];
            int steps = curr[2];

            if(row == n-1 && col == n-1) return steps;

            for(int[] dir : directions){
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                if(newRow >= 0 && newRow < n && newCol >= 0 && newCol < n && grid[newRow][newCol] == 0){
                    grid[newRow][newCol] = 1;
                    q.offer(new int[]{newRow , newCol , steps+1});
                }
            }
        }

        return -1;

    }

    // Maximum number of characters between two same character in a String
    public int maxChars(String s) {
        // code here
        int n = s.length();
        
        int arr[] = new int[52];
        
        Arrays.fill(arr , -1);
        
        int maxCnt = -1;
        
        for(int i = 0 ;i<n ; i++){
            
            char ch = s.charAt(i);
            int index;
            
            if(Character.isLowerCase(ch)){
                index = ch - 'a';
            }else{
                index = ch - 'A' + 26;
            }
            
            if(arr[index] != -1){
                int cnt = i - arr[index] -1;
                maxCnt = Math.max(cnt , maxCnt);
            }else{
                 arr[index] = i;
            }
        }
        
        return maxCnt;
    }


    public ArrayList<Integer> Non_NegativeSubarray(int arr[]) {
        // code here
        int n = arr.length;
        
        long currSum = 0 ;
        int currStart = -1 , currEnd = -1;
        
        long sum = 0 ; 
        int start = -1 , end = -1;
        
        int i = 0;
        while(i < n){
            if(arr[i] >= 0){
                currStart = i;
                currSum = 0;
                while(i<n && arr[i] >= 0){
                    currSum += arr[i];
                    i++;
                }
                currEnd = i - 1;
                if(currSum > sum){
                    start = currStart;
                    end = currEnd;
                    sum = currSum;
                }
                
                else if(currSum == sum && (end - start) < (currEnd - currStart)){
                    start = currStart;
                    end = currEnd;
                }
            }
            
            else{
                i++;
            }
        }
        
        ArrayList<Integer> ans = new ArrayList<>();
    
       if (start == -1) {
            ans.add(-1);
            return ans;
        }
        
        for(int j = start ; j<= end ; j++){
            ans.add(arr[j]);
        }
        
        return ans;
    }


    // Most Common word 
    public String mostCommonWord(String paragraph, String[] banned) {
        HashSet<String> bW = new HashSet<>();
        HashMap<String , Integer> mpp = new HashMap<>();

        for(String ban : banned){
            bW.add(ban);
        }

        String[] words = paragraph.toLowerCase().split("\\W+");

        for(String word : words){
            if(!bW.contains(word)){
                mpp.put(word , mpp.getOrDefault(word , 0) + 1);

            }
        }

        int max = 0;
        String res = "";

        for(String word : mpp.keySet()){
            if(mpp.get(word) > max){
                max = mpp.get(word);
                res = word;
            }
        }

        return res;

    }


    // Longest Increasing subsequence
    public static int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int memo[] = new int[n];

        Arrays.fill(memo , 1);

        for(int i = 1 ; i<n ; i++){
            for(int j = 0 ; j<i ; j++){
                if(nums[j] < nums[i]){
                    if(memo[j] + 1 > memo[i]){
                        memo[i] = memo[j] + 1;
                    }
                }
            }
        }

        int maxLen = 0;

        for(int l : memo){
            maxLen = Math.max(l , maxLen);
        }

        return maxLen;

    }

    // Hamming Weight
    public static  int hammingWeight(int n) {
        int ones = 0;

        for(int i = 1 ; i<=32 ; i++){
            ones += n&1;
            n = n >> 1;
        }

        return ones;
    }


    public static void main(String[] args) {
    
        // Base 7 
        int num = -100;
        String base7 = convertToBase7(num);
        System.out.println("Base 7 representation of " + num + " is: " + base7);

        // Count Frequency
        int[] arr = {1, 2, 2, 2, 3, 4, 5};
        int x = 2;
        int frequency = countFreq(arr, x);
        System.out.println("Frequency of " + x + " in the array is: " + frequency);

        // Remaining String
        String s = "hello world";
        char ch = 'o';
        int count = 2;
        String remaining = remainingString(s, ch, count);
        System.out.println("Remaining string after " + count + " occurrences of '" + ch + "' is: " + remaining);

        // Longest Consecutive Sequence 
        int[] sequenceArr = {100, 4, 200, 1, 3, 2};
        int longestSeq = longestConsecutive(sequenceArr);
        System.out.println("Length of the longest consecutive sequence is: " + longestSeq);

        // Group Anagrams
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> groupedAnagrams = groupAnagrams(strs);
        System.out.println("Grouped Anagrams: " + groupedAnagrams);

        // Find the String in the grid
        char[][] grid = {
            {'a', 'b', 'a', 'b'},
            {'a', 'b', 'e', 'b'},
            {'e', 'b', 'e', 'b'}
        };
        String word = "abe";
        int[][] positions = searchWord(grid, word);

        System.out.println("Positions of the word '" + word + "' in the grid:");
        for (int[] pos : positions) {
            System.out.println("Row: " + pos[0] + ", Column: " + pos[1]);
        }

        // Reverse Vowel
        String input = "practice";
        String reversedVowelString = modify(input);
        System.out.println("String after reversing vowels: " + reversedVowelString);

        // Find Sum
        String s1 = "23";
        String s2 = "23";
        String sum = findSum(s1, s2);
        System.out.println("Sum of " + s1 + " and " + s2 + " is: " + sum);

        // Indexes of Subarray Sum
        int[] subarrayArr = {1, 2, 3, 7, 5};
        int targetSum = 12;
        ArrayList<Integer> subarrayIndexes = indSubarraySum(subarrayArr, targetSum);
        System.out.println("Indexes of subarray with sum " + targetSum + ": " + subarrayIndexes);

        // Validate IP Address
        String queryIP1 = "192.168.1.1";
        String queryIP2 = "2001:0db8:85a3:0000:0000:8a2e:0370:7334";
        String queryIP3 = "256.256.256.256";


        System.out.println("Validation result for IP '" + queryIP1 + "': " + validIPAddress_LC(queryIP1));
        System.out.println("Validation result for IP '" + queryIP2 + "': " + validIPAddress_LC(queryIP2));
        System.out.println("Validation result for IP '" + queryIP3 + "': " + validIPAddress_LC(queryIP3));


        // Word Search Problem
        char[][] board = {
            {'C', 'O', 'D', 'A'},
            {'P', 'R', 'E', 'G'},
            {'J', 'A', 'V', 'A'}
        };
        String searchWord = "CODE";
        boolean exists = wordExist(board, searchWord);
        System.out.println("Does the word '" + searchWord + "' exist in the board? " + exists);

        // Shortest Path in Binary Matrix
        int[][] binaryMatrix = {
            {0, 0, 0, 0},
            {0, 0, 1, 0},
            {0, 0, 1, 0},
            {0, 0, 0, 0}
        };
        int shortestPath = shortestPathBinaryMatrix(binaryMatrix);
        System.out.println("Shortest path in binary matrix: " + shortestPath);


        // Non-Negative Subarray
        int[] nonNegativeArr = {1, 2, -3, 4, 5, -6, 7, 8};
        CheatTwo obj = new CheatTwo();
        ArrayList<Integer> nonNegativeSubarray = obj.Non_NegativeSubarray(nonNegativeArr);
        System.out.println("Non-negative subarray with maximum sum: " + nonNegativeSubarray);

        // Maximum number of characters between two same characters in a String
        String testString = "abcdacfgabe";
        int maxCharacters = obj.maxChars(testString);
        System.out.println("Maximum number of characters between two same characters in the string '" + testString + "' is: " + maxCharacters);

        // Most Common Word
        String paragraph = "Bob hit a ball, the hit BALL flew far after it was hit.";
        String[] bannedWords = {"hit"};
        String mostCommon = obj.mostCommonWord(paragraph, bannedWords);
        System.out.println("Most common word: " + mostCommon);

        // Longest Increasing Subsequence
        int[] lisArr = {10, 9, 2, 5, 3, 7, 101, 18};
        int lisLength = lengthOfLIS(lisArr);
        System.out.println("Length of the longest increasing subsequence is: " + lisLength);
    }
}
