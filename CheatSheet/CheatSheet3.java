import java.util.*;

public class CheatSheet3 {
    
    public static int maximum_Cuts(int n) {
        return(1 + n * (n+1)/2);
    }

    // Equilibrium point
    public static int findEquilibrium(int arr[]) {
        // code here
        int n = arr.length;
        
        int lSum = 0;
        int rSum = 0;
        
        for(int i = 0 ; i < n ; i++){
            rSum += arr[i];
        }
        
        for(int j = 0 ; j < n ; j++){
            rSum -= arr[j];
            
            if(lSum == rSum){
                return j;
            }
            
            lSum += arr[j];
        }
        
        return -1;
    }

    // Reverse K element in a queue
    public static Queue<Integer> reverseFirstK(Queue<Integer> q, int k) {
        
        int n = q.size();
        
        if(k > n || k == 0) return q;
        
        int arr[] = new int[k];
        
        for(int i = 0 ; i < k ; i++){
            arr[i] = q.poll();
        }
        
        for(int i = k-1 ; i >= 0 ; i--){
            q.offer(arr[i]);
        }
        
        for (int i = 0; i < n - k; i++) {
            q.offer(q.poll());
        }
        
        return q;
        
    }
    
    // Sum of upper and lower triangles
    public static ArrayList<Integer> sumTriangles(int matrix[][], int n) {
        
        int upperT = 0;
        int lowerT = 0;
        
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < n ; j++){
                if(i <= j) upperT += matrix[i][j];
                if(i >= j) lowerT += matrix[i][j];
            }
        }
        
        ArrayList<Integer> ans = new ArrayList<>();
        ans.add(upperT);
        ans.add(lowerT);
        
        return ans;
        
    }

    // String rotational check;
    public static boolean isRotated(String s1, String s2) {
    
        int n = s1.length();
        int m = s2.length();
        
        if(n != m) return false;
        if (n <= 2) return s1.equals(s2);
        
        char[] chArr = s1.toCharArray();
        
        // left rotated by two
        reverse_S(chArr , 0 , 2-1);
        reverse_S(chArr , 2 , n-1);
        reverse_S(chArr , 0 , n-1);
        
        String left = new String(chArr);
        
        // right rotate by two
        chArr = s1.toCharArray();
        reverse_S(chArr , 0 , n-1);
        reverse_S(chArr , 0 , 2-1);
        reverse_S(chArr , 2 , n-1);
        
        String right = new String(chArr);
        
        
        
        return s2.equals(left) || s2.equals(right);
        
    }

    static void reverse_S(char[] s1 , int start , int end){
        while(start < end){
            char temp = s1[start];
            s1[start] = s1[end];
            s1[end] = temp;
            
            start++;
            end--;
        }
    }

    // strstr
    public static int firstOccurence(String txt, String pat) {
        
        int n = txt.length();
        int m = pat.length();
        
        for(int i = 0 ; i < n ; i++){
            int idx = i;
            boolean flag = true;
            for(int j = 0 ; j < m ; j++){
                if (idx >= n || txt.charAt(idx) != pat.charAt(j)) { 
                    flag = false;
                    break;
                }
                idx++;
            }
            
            if(flag == true){
                return i;
                
            }
        }
        
        return -1;
        
    }

    // Front-Back Transformation - copy
    public static String convert(String s) { 
        // a - 97 - z - 122
        // A - 65 - Z - 90
        
        int n = s.length();
        char chArr[] = s.toCharArray();
        
        for(int i = 0 ; i < n ; i++){
            char ch = chArr[i];
            
            if(Character.isUpperCase(ch)){
                chArr[i] = (char)('Z' - (ch - 'A'));
                
            }else{
                chArr[i] = (char)('z' - (ch - 'a'));
            }
            
        }
        
        
        return new String(chArr);
        
    }

    // Remove Character
    public static String removeChars(String str1, String str2) {
        // code here
        int n = str1.length();
        int m = str2.length();
        
        StringBuilder sb = new StringBuilder();
        
        int hash[] = new int[26];
        
        for(int i = 0 ; i < m ; i++){
            hash[str2.charAt(i) - 'a']++;
        }
        
        for(int i = 0 ;  i < n ; i++){
            char ch = str1.charAt(i);
            
            if(hash[ch - 'a'] == 0){
                sb.append(ch);
            }
        }
        
        return sb.toString();
    }

    // Replace all 0's with 5's
    public static int convertfive(int num) {
        int copy = num;
        int factor = 1;
        while(num > 0){
            int digit = num % 10;
            if(digit == 0){
                copy += factor * 5;
            }
            
            factor *= 10;
            num = num / 10;
        }
        
        return copy;
    }

    // Multiply polynomials
    public static int[] multiply(int[] A, int[] B) {
        // code here
        // ArrayList<Integer> ans = new ArrayList<>();
        int n = A.length;
        int m = B.length;
        int ans[] = new int[n+m - 1];
        
        for(int i = 0; i < n ; i++){
            for(int j = 0 ; j < m ; j++){
                ans[i+j] += A[i] * B[j]; 
            }
        }
        
        return ans;
    }

    // Geekonacci number
    public static int nextNum(int a , int b , int c , int N){
        if(N == 1) return a;
        if(N == 2) return b;
        if(N == 3) return c;
        
        for(int i = 4 ; i <= N ; i++){
            int next = a + b + c;
            a = b;
            b = c;
            c = next;
        }
        
        return c;
    }
    
    // Super Ascii String
    public static boolean  superAscii(String s ){
        int n = s.length();

        int hash[] = new int[26 + 1];

        for(int i = 0 ; i < n; i++){
            char ch = s.charAt(i);

            hash[ch - 'a' + 1]++;
        }

        for(int i = 0 ; i < n; i++){
            char ch = s.charAt(i);
             int asciiVal = ch - 'a' + 1;
            if(hash[asciiVal] != asciiVal){
                return false;
            }
        }

        return true;
    }

    // 2nd Maximum String
    public static String secFrequent(String arr[], int N) {
        Map <String , Integer> mpp = new HashMap<>();
        
        for(String str : arr){
            mpp.put(str , mpp.getOrDefault(str , 0) + 1);
        }
        
        int first = 0, second = 0;
        String res = "";
        
        for(Map.Entry<String , Integer> entry : mpp.entrySet()){
            int freq = entry.getValue();
            if(freq > first){
                second = first;
                first = freq;
            }else if(freq > second && freq < first){
                second = freq;
            }
        }
        
        for (Map.Entry<String, Integer> entry : mpp.entrySet()) {
            if (entry.getValue() == second) {
                res = entry.getKey();
                break;
            }
        }
        
        return res;
    }

    // Remove vowels
    public static String removeVowels(String s) {
        Set<Character> vowels = new HashSet<>();
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');
    
        StringBuilder result = new StringBuilder();
    
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (!vowels.contains(ch)) {
                result.append(ch);
            }
        }
    
        return result.toString();
        
    }

    // Gap sum
    public static void gapSum(int arr[] , int gap){
        int n = arr.length;

        ArrayList<Integer> ans = new ArrayList<>();

        for(int i = 0 ; i < gap ; i++){
            int sum = 0;
            for(int j = i ; j < n ; j+= gap){
                sum += arr[j];
            }

            ans.add(sum);
        }

        System.out.println("The Gap sum obtained is : " + ans);
    }

    // miscellaneous
    public static int operations(String s){
        // A - and , B - or , C - xor
        int n = s.length();

        char[] arr = s.toCharArray();

        int ans = (int)(arr[0] - '0');

        for(int i = 1 ; i < n ; i+= 2){
            if(arr[i] == 'A'){
                ans = (int) ( ans & (arr[i+1] - '0'));
            }
            else if(arr[i] == 'B'){
                ans = (int) (ans | (arr[i+1] - '0'));
            }
            else{
                ans = (int) (ans ^ (arr[i+1] - '0'));
            }
        }

        return ans;

    }

    // Isomorphic strings
    public static  boolean areIsomorphic(String s1, String s2) {
        // code here
        int n = s1.length();
        int m = s2.length();
        
        if(n != m) return false;
       
        
        Map<Character , Character> mpp = new HashMap<>();
        
        for(int i = 0 ; i < n; i++){
           char og = s1.charAt(i);
           char rep = s2.charAt(i);
           
           if(!mpp.containsKey(og)){
               if(!mpp.containsValue(rep)){
                   mpp.put(og , rep);
               }
               else return false;
           }
           else{
               char mapped = mpp.get(og);
               if(mapped != rep){
                   return false;
               }
           }
        }
        
        return true;
        
        
    }

    // Unique numbers
    public static ArrayList<Integer> uniqueNumbers(int L, int R) {

        ArrayList<Integer> ans = new ArrayList<>();
        
        for(int i = L ; i <= R ; i++){
           String temp = Integer.toString(i);
            
            Map<Character , Integer> mpp = new HashMap<>();
            
            int isUnique = 1;
            for(int j = 0 ;  j < temp.length() ; j++){
                
                char ch = temp.charAt(j);
                
                if(mpp.containsKey(ch)){
                    isUnique = 0;
                    break;
                }
                else{
                    mpp.put(ch, 1);
                }
            }
            
            if(isUnique == 1){
                ans.add(i);
            }
            
        }
        
        return ans;
    }

    // Number of Trailing Zeroes
    public static int trailingZeroes(int n) {
        if(n < 5) return 0;

        int zeroes = 0;

        while(n >= 5){
            zeroes += n / 5;
            n = n/5;
        }

        return zeroes;
    }

    // Run Length encoding of the string
    public static String encode(String s) {
        int n = s.length();
        
        StringBuilder sb = new StringBuilder();
        
        int cnt = 1;
        
        for(int i = 1 ; i < n ; i++){
            if(s.charAt(i-1) != s.charAt(i)){
                sb.append(s.charAt(i-1));
                sb.append(cnt);
                cnt = 1;
            }
            else{
                cnt++;
            }
        }
        
        sb.append(s.charAt(n-1));
        sb.append(cnt);
        
        return sb.toString();
    }

    // Non Repeating Character
    public static char nonRepeatingChar(String s) {
        
        int n = s.length();
        
        Map<Character , Integer> mpp = new HashMap<>();
        
        for(int i = 0 ; i < n; i++){
            
            mpp.put(s.charAt(i) , mpp.getOrDefault(s.charAt(i) , 0) + 1);
        }
    
    
        
       for(int i = 0 ; i < n ; i++){
          if( mpp.get(s.charAt(i)) == 1){
              return s.charAt(i);
          }

       }
        
        return '$';
    }

    // Hamming Distance
    public static int hammingDistance(int x, int y) {
        int c = x ^ y;
        
        int cnt = 0;

        while(c > 0){
            if(c % 2 == 1) cnt++;
            c /= 2;
        }

        return cnt;

    }

    // setbits
    public static int bits(int num){
        int cnt = 0;

        while(num > 0){
            if(num % 2 == 1) cnt++;
            num /= 2;
        }

        return cnt;
    }

    // Rope Cutting problem
    public static  ArrayList<Integer> RopeCutting(int arr[]) {
        int n = arr.length;
        
        Arrays.sort(arr);
        
        int minCut = arr[0];
        
        ArrayList<Integer> ans = new ArrayList<>();
        
        for(int i = 1 ; i < n; i++){
            if(arr[i] - minCut > 0){
                minCut = arr[i];
                ans.add(n - i);
            }
        }
        
        return ans;
        
    }

    // Completing Tasks
    public static List<List<Integer>> findTasks(int[] arr, int k) {
        // code here
        int n = arr.length;
        
        List<List<Integer>> ans = new ArrayList<>();
        
        int tasks[] = new int[k];
         
        for(int i = 0 ; i < n; i++){
            tasks[arr[i] - 1] = 1;
        }
        
        List<Integer> tanya = new ArrayList<>();
        List<Integer> manya = new ArrayList<>();
        
        int turn = 0;
        
        for(int i = 0 ; i < tasks.length ; i++){
           if(tasks[i] == 0){
                if(turn % 2 == 0){
                   tanya.add(i+1);
               }else{
                   manya.add(i+1);
               }
               
               turn++;
           }
            
        }
        
        ans.add(tanya);
        ans.add(manya);
         
        return ans;
        
    }

    // Jumpy Balls
    public static long jumpyBall(long N) {
        // code here
        long dist = 2 * N;
        
        while(N > 0){
            dist += 2 * Math.floor(N/2);
            N = N / 2;
        }
        
        return dist;
    }

    // at least two greater
    public static long[] findElements(long arr[]) {
        
        int n = arr.length;
        if (n <= 2) return new long[] {};
        
        long first = Long.MIN_VALUE;
        long second = Long.MIN_VALUE;
        
        for(int i = 0 ; i < n ; i++){
            if(arr[i] > first){
                second = first;
                first = arr[i];
            }
            else if(arr[i] < first && arr[i] > second){
                second = arr[i];
            }
        }
        
         // Collect all except first and second max
        List<Long> temp = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (arr[i] != first && arr[i] != second) {
                temp.add(arr[i]);
            }
        }
    
        // Convert to array
        long[] res = new long[temp.size()];
        for (int i = 0; i < temp.size(); i++) {
            res[i] = temp.get(i);
        }
    
        Arrays.sort(res); 
        
        return res;

    }

    // Largest number with given sum
    public static String largestNumber(int n, int sum) {
        
       String ans = "";
       
       if(9 * n < sum) return "-1";
       
       while(sum > 0){
           if(sum >= 9){
               ans = ans + "9";
               sum -= 9;
           }
           else
           {
                ans = ans + sum;    
                sum = 0;
                break;
           }
       }
       
       if(ans.length() < n){
           for(int i = ans.length() ; i < n  ; i++){
               ans = ans + "0";
           }
       }
       
       return ans;
    }

    // Reciprocal of a Sring
    public static String reciprocalString(String S) {
        int n = S.length();
        
        char chArr[] = S.toCharArray();
        
        for(int i = 0 ; i < n ; i++){
            char ch = chArr[i];
            
            if(Character.isUpperCase(ch)){
                chArr[i] = (char)('Z' - (ch - 'A'));
                
            }else if(Character.isLowerCase(ch)){
                chArr[i] = (char)('z' - (ch - 'a'));
            }else{
                chArr[i] = ' ';
            }
            
        }
        
        return new String(chArr);
        
        
    }

    // String Reversal
    public static  String reverseString(String S) {
        int n = S.length();
        
        int hash[] = new int[26];
        
        StringBuilder sb = new StringBuilder();
        
        for(int i = n-1 ; i >= 0 ; i--){
            char ch = S.charAt(i);
            if (ch == ' ') continue;
            
            if(hash[ch - 'A'] == 0){
                sb.append(ch);
                hash[ch - 'A'] = 1;
            }
        }

        return sb.toString();
    }

    // Form a number divisible by 3 using array digits
    public static int isPossible(int N, int arr[]) {
        int sum = 0;
        
        for(int i = 0 ; i < N ; i++){
            sum += arr[i] % 3;
        }
        
        StringBuilder sb = new StringBuilder();
        
        if(sum % 3 == 0){
            return 1;
        }else return 0;
    
    }

    // Print Bracket Number
    public static ArrayList<Integer> bracketNumbers(String str) {
        
        int cnt = 1;
        
        Stack<Integer> st = new Stack<>();
        ArrayList<Integer> ans = new ArrayList<>();
        
        
        for(int i = 0 ; i < str.length() ; i++){
            if(str.charAt(i) == '('){
                st.push(cnt);
                ans.add(cnt);
                cnt++;
            }else if(str.charAt(i) == ')'){
                ans.add(st.pop());
            }
        }
        
        return ans;
        
    }

    // Top K frequent Element - using Heap
    public int[] topKFrequent(int[] nums, int k) {
        int n = nums.length;

        Map<Integer , Integer> mpp = new HashMap<>(); 

        for(int it : nums){
            mpp.put(it , mpp.getOrDefault(it , 0) + 1);
        }

        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>((a , b) -> a.getValue() - b.getValue());

        for(Map.Entry<Integer , Integer> entry : mpp.entrySet()){
            pq.add(entry);
            if(pq.size() > k) pq.poll();
        }

        int[] ans = new int[k];

        for(int i = 0; i < k ; i++){
            ans[i] = pq.poll().getKey();
        }

        return ans;
    }
    
    // Distinct Permutations of a String
    public static  ArrayList<String> findPermutation(String s) {
       Set<String> set = new HashSet<>();
        char chArr[] = s.toCharArray();
        permutation(chArr , set , 0);
        
        return new ArrayList<>(set);       
    }
    
    public static void permutation(char[] arr , Set<String> ans , int ind){
        
        if(ind == arr.length){
            ans.add(new String(arr));
            return;
        }
        
        for(int i = ind ; i < arr.length; i++){
            swap(arr , i , ind);
            permutation(arr , ans , ind+1);
            swap(arr , ind , i);
        }
        
    }
    
    public static void swap(char[] arr , int a , int b){
        char temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    // Find All Duplicates in an Array
    public List<Integer> findDuplicates(int[] nums) {
        int n = nums.length;

        List<Integer> ans = new ArrayList<>();

        for(int i  = 0; i < n ; i++){
            int idx = Math.abs(nums[i]) - 1;

            if(nums[idx] < 0){
                ans.add(idx+1);
            }

            nums[idx] = nums[idx] * -1;
        }

        return ans;
        
    }

    // Minimum Time Diffrence
    public static int findMinDifference(List<String> timePoints) {

        if(timePoints.size() > 24 * 60) return 0;

        int [] minutes = new int[timePoints.size()];

        for(int i = 0 ; i < timePoints.size(); i++){
            int hrs = Integer.parseInt(timePoints.get(i).split(":")[0]);
            int mins = Integer.parseInt(timePoints.get(i).split(":")[1]);
            minutes[i] = hrs * 60 + mins;
        }

        Arrays.sort(minutes);

        int ans = Integer.MAX_VALUE;

        for(int i = 1 ; i < minutes.length ; i++){
            ans = Math.min( ans , minutes[i] - minutes[i - 1]);
        }

        return Math.min(ans , minutes[0] + 1440 - minutes[minutes.length - 1]);
    }



    public static void main(String[] args) {
        // Super Ascii
        System.out.println(superAscii("bba") ? "Yes" : "No");    
        System.out.println(superAscii("ssba") ? "Yes" : "No"); 
        
        // Gap Sum
        gapSum(new int[]{1,2,3,4,5,6,7,8,9} , 3);

        // Operations
        System.out.println("The Resultant after (A -> &) ,( B -> |) , (C -> ^) is : "+operations("1A0B1"));

        // Maximum Cuts
        System.out.println("Maximum cuts for n=3: " + maximum_Cuts(3));

        // Equilibrium point
        System.out.println("Equilibrium index: " + findEquilibrium(new int[]{1, 3, 5, 2, 2}));

        // Reverse first K elements in a queue
        Queue<Integer> q = new LinkedList<>(Arrays.asList(1, 2, 3, 4, 5));
        System.out.println("Queue after reversing first 3: " + reverseFirstK(q, 3));

        // Sum of upper and lower triangles
        int[][] mat = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println("Sum of triangles: " + sumTriangles(mat, 3));

        // String rotational check
        System.out.println("Is Rotated: " + isRotated("amazon", "azonam"));

        // strstr
        System.out.println("First occurrence: " + firstOccurence("hello", "ll"));

        // Front-Back Transformation
        System.out.println("Front-Back: " + convert("Abc"));

        // Remove Character
        System.out.println("Remove chars: " + removeChars("computer", "cat"));

        // Replace all 0's with 5's
        System.out.println("Convert five: " + convertfive(1020));

        // Multiply polynomials
        System.out.println("Multiply polynomials: " + Arrays.toString(multiply(new int[]{1, 2}, new int[]{3, 4})));

        // Geekonacci number
        System.out.println("Geekonacci: " + nextNum(1, 3, 2, 5));

        // Super Ascii String (already tested above)

        // 2nd Maximum String
        System.out.println("Second frequent: " + secFrequent(new String[]{"aaa", "bbb", "ccc", "bbb"}, 4));

        // Remove vowels
        System.out.println("Remove vowels: " + removeVowels("beautiful"));

        // Isomorphic strings
        System.out.println("Are isomorphic: " + areIsomorphic("aab", "xxy"));

        // Unique numbers
        System.out.println("Unique numbers: " + uniqueNumbers(10, 15));

        // Number of Trailing Zeroes
        System.out.println("Trailing zeroes: " + trailingZeroes(100));

        // Run Length encoding
        System.out.println("Run Length Encoding: " + encode("aaabbc"));

        // Non Repeating Character
        System.out.println("Non-repeating char: " + nonRepeatingChar("geeksforgeeks"));

        // Hamming Distance
        System.out.println("Hamming distance: " + hammingDistance(4, 14));

        // Set bits
        System.out.println("Set bits: " + bits(15));

        // Rope Cutting problem
        System.out.println("Rope Cutting: " + RopeCutting(new int[]{5, 1, 1, 2, 3, 5}));

        // Completing Tasks
        System.out.println("Completing Tasks: " + findTasks(new int[]{2, 3}, 4));

        // Jumpy Balls
        System.out.println("Jumpy Ball: " + jumpyBall(5));

        // At least two greater
        System.out.println("At least two greater: " + Arrays.toString(findElements(new long[]{2, 8, 7, 1, 5})));

        // Largest number with given sum
        System.out.println("Largest number: " + largestNumber(3, 20));

        // Reciprocal of a String
        System.out.println("Reciprocal String: " + reciprocalString("AbC"));

        // String Reversal
        System.out.println("Reverse String: " + reverseString("ABAC"));

        // Form a number divisible by 3 using array digits
        System.out.println("Is possible divisible by 3: " + isPossible(3, new int[]{40, 50, 90}));

        // Print Bracket Number
        System.out.println("Bracket Numbers: " + bracketNumbers("(aa(bdc))p(dee)"));

        // Top K frequent Element
        System.out.println("Top K Frequent: " + Arrays.toString(new CheatSheet3().topKFrequent(new int[]{1,1,1,2,2,3}, 2)));

        // Distinct Permutations of a String
        System.out.println("Distinct Permutations: " + findPermutation("ABC"));

        // Find All Duplicates in an Array
        System.out.println("Find Duplicates: " + new CheatSheet3().findDuplicates(new int[]{4,3,2,7,8,2,3,1}));

        // Minimum Time Difference
        System.out.println("Min Time Difference: " + findMinDifference(Arrays.asList("00:00", "23:59", "12:30", "12:31", "23:58")));



    }

}
