// https://www.geeksforgeeks.org/explore?page=5&company=Zoho&sortBy=submissions

// https://www.naukri.com/code360/problem-lists/zoho-interview-questions


// https://www.naukri.com/code360/problems/sort-integers-by-factor-value_1281384 -- Sort Integers by Factor Value - DP need to solve
// https://leetcode.com/problems/word-break/description/ -- Word Break problem - DP 

//  https://leetcode.com/problems/single-number-ii/description/ -- Bitwise operator 


// https://www.geeksforgeeks.org/sort-elements-basis-number-factors/ -- Sort elements on the basis of number of factors

import java.util.*;
import java.util.Arrays;
import java.util.Stack;


public class CheatSheet {

    public static int[] distinctDigitsB(int[] nums)
    {
        int n = nums.length;
        
        Set<Integer> st = new HashSet<>();
        
        for(int i = 0 ; i<n ; i++){
            int temp = 0;
            temp = nums[i];
            while(temp != 0){
                int digit = temp % 10;
                st.add(digit);
                temp /= 10;
            }
        }
        
        List<Integer> arr = new ArrayList<>(st);
        
        Collections.sort(arr);
        
        int res[] = new int[arr.size()];
        
        for(int i = 0 ; i< arr.size() ;i++){
            res[i] = arr.get(i);
        }
        
        return res;
    }

    public static void  sortItB(Long arr[]) {
        
        long n = arr.length;
        
        List<Long> odd = new ArrayList<>();
        List<Long> even = new ArrayList<>();
        
        for(int i = 0 ;i<n ; i++){
            long num = arr[i];
            
            if(num % 2 == 0){
                even.add((long)arr[i]);
            }else{
                odd.add((long)arr[i]);
            }
        }
        
        Collections.sort(odd , Collections.reverseOrder());
        Collections.sort(even);
        
        
        int index = 0;
        
        for(Long num: odd){
            arr[index++] = num;
        }
        
        for(Long num : even){
            arr[index++] = num;
        }
    }

    public static void sortItO(int arr[]){
        int n = arr.length;

        for(int i = 0 ; i< n ; i++){
            if(arr[i] % 2 == 1){
                arr[i] = -arr[i];
            }
        }

        Arrays.sort(arr);

        for(int i = 0 ; i<n ; i++){
            if(arr[i] < 0){
                arr[i] = -arr[i];
            }
        }
    }

    public static void denominatorCurr(int amt){
        int[] currencyNotes = new int[] {2000,500,200,100,50,20,10,5,2,1};
        int[] numberOfNotes = new int[10];

        for(int i = 0 ; i<9 ; i++ ){
            if(amt >= currencyNotes[i]){
                numberOfNotes[i] = amt / currencyNotes[i];
                amt = amt % currencyNotes[i];
            }
        }

        System.out.println("3. Denomination - Currency Count ");
        for(int j = 0 ; j<9 ; j++){
            if(numberOfNotes[j] != 0){
                System.out.println(currencyNotes[j] + ":" + numberOfNotes[j]);
            }
        }
    }

    public static void deciToBinary(int n){
        int ans = 0;

        int factor = 1;

        while(n != 0){
            ans += (n%2)*factor;
            factor *= 10;
            n = n/2;
        }
        
        System.out.println("The binary value of the integer is : "+ ans);
    }

    public static void deciToHexadeci(int n) {
        if (n == 0) {
            System.out.println("The Hexadecimal value of the provided decimal value is : 0");
            return;
        }
    
        StringBuilder hexValue = new StringBuilder();
    
        int r;
        while (n != 0) {
            r = n % 16;
            if (r < 10) {
                hexValue.append((char) (r + '0')); // Convert to character '0'-'9'
            } else {
                hexValue.append((char) (r - 10 + 'A')); // Convert to 'A'-'F'
            }
            n = n / 16;
        }
    
        System.out.print("The Hexadecimal value of the provided decimal value is : ");
        System.out.println(hexValue.reverse().toString());
    }

    public static int hexToDecimal(String hex) {
        int decimalValue = 0;
        int base = 1; // 16^0 initially

        for (int i = hex.length() - 1; i >= 0; i--) {
            char ch = hex.charAt(i);
            int value;

            if (ch >= '0' && ch <= '9') {
                value = ch - '0';  // Convert '0'-'9' to 0-9
            } else if (ch >= 'A' && ch <= 'F') {
                value = ch - 'A' + 10; // Convert 'A'-'F' to 10-15
            } else if (ch >= 'a' && ch <= 'f') {
                value = ch - 'a' + 10; // Handle lowercase hex letters
            } else {
                throw new IllegalArgumentException("Invalid Hexadecimal Character: " + ch);
            }

            decimalValue += value * base;
            base *= 16; // Increase power of 16
        }

        return decimalValue;
    }
    
    public static void hexToBinary(String hex){
        StringBuilder binary = new StringBuilder();

        String[] hexToBinMap = {
            "0000" , "0001" , "0010" , "0011" , "0100" , "0101" , "0110" , "0111",
            "1000" , "1001" , "1010" , "1011" , "1100" , "1101" , "1110" , "1111"
        };

        for(char hexChar : hex.toUpperCase().toCharArray()){
            if(hexChar >= '0' && hexChar <= '9'){
                binary.append(hexToBinMap[hexChar - '0']);
            }
            else if(hexChar >= 'A' && hexChar <= 'F'){
                binary.append(hexToBinMap[hexChar - 'A' + 10]); 
                // eg:- F - ascii val = 70 - 'A'(65) = 5 => 5+10 = hexToBinMap[15] == "1111"
            }
            else{
                System.out.print("Invalid HexaDecimal Input");
                return;
            }
        }

        System.out.println("The Binary Equivalent is : " + binary.toString());
    }

    public static void binaryToHex(String bin){

        StringBuilder hexaDecimal = new StringBuilder();

        if(!bin.matches("[01]+")){
            System.out.println("Invalid Binary Input");
            return;
        }

        while(bin.length() % 4 != 0){
            bin = "0" + bin;
        }

        String[] binToHexMap = {
            "0" , "1" , "2" , "3" , "4" , "5" , "6" , "7" , 
            "8" , "9" , "A" , "B" , "C" , "D" , "E" , "F"
        };

        // Process 4 bits (nibbles) at a time
        for(int i = 0 ; i<bin.length() ; i += 4){
            String fourBits = bin.substring(i , i+4);
            int decimalValue = Integer.parseInt(fourBits , 2);
            hexaDecimal.append(binToHexMap[decimalValue]);
        }

        System.out.println("Hexadecimal equivalent: " + hexaDecimal.toString());
    }

    public static ArrayList<Integer> sortFreq(int arr[]){
	    int n = arr.length;
	    
	    HashMap<Integer,Integer>mpp = new HashMap<>();
	    ArrayList<Integer> l = new ArrayList<>();
	    
	    
	    
	    for(int i = 0 ; i<n ; i++){
	        mpp.put(arr[i] , mpp.getOrDefault(arr[i] , 0) + 1);
	        l.add(arr[i]);
	    }
	    
	    Collections.sort(l , new Comparator<Integer>(){
	        @Override
	        public int compare(Integer a , Integer b){
	            
	            int freqA = mpp.get(a);
	            int freqB = mpp.get(b);
	            
	            if(freqA != freqB){
	                return freqB - freqA; // Sort by frequency (descending)
	            }
	            
	            return a - b; // Sort by value (ascending)
	            
	        }
	    });
	    
	    return l;
	    
	    
	}

    public static boolean isSubsequence(String A , String B){

        int n = A.length();
        int m = B.length();

        int i = 0;

        int j = 0;

        while(i < n && j < m){
            if( A.charAt(i) == B.charAt(j)){
                i++;
            }
            j++;

        }

        if(i == n) return true;
        else return false;
        
    }

    public static int[] findLeastPrimes(int[] A, int[] B) {
        int n = A.length;
        int[] result = new int[n];

        for (int i = 0; i < n; i++) {
            int num = A[i];
            int divisor = B[i];
            
            // Start checking from the smallest prime number
            int P = 2;
            while (true) {
                if ((num + P) % divisor == 0) { // Check divisibility condition
                    result[i] = P;
                    break; // Stop checking once the smallest prime is found
                }
                P = nextPrime(P); // Get the next prime number
            }
        }
        return result;
    }

    public static int nextPrime(int num) {
        num++; 
        while (!isPrime(num)) {
            num++; 
        }
        return num;
    }
 
    public static boolean isPrime(int num) {
        if (num < 2) return false;
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    ArrayList<Integer> primeRange(int M, int N) {
        // code here
        ArrayList<Integer> ans = new ArrayList<>();
        for(int i = M ; i<= N ; i++){
            if(isPrime(i)){
                ans.add(i);
            }
        }
        
        return ans;
    }

    public static long primeProduct(int N) 
    { 
        long prod = 1;
        
        int sqrtN = (int) Math.sqrt(N);
        
        for(int i = 2 ; i<= sqrtN ; i++){
            if(N % i == 0){
                prod *= i;
                
                while(N % i == 0){
                    N = N/i;
                }
            }
        }
        
        if(N != 1) prod *= N;
        
        return prod;
    }
    
    public static int[] leastPrimeFactor(int n){

        int[] lpf = new int[n + 1];

        // 1 is its own least prime factor
        lpf[1] = 1;

        for(int i = 2 ; i <= n ; i++){
            if(lpf[i] == 0){

                lpf[i] = i;


                for(int j = i ; j<= n ; j += i){

                    if(lpf[j] == 0){
                        lpf[j] = i;
                    }
                }

            }
        }

        return lpf;


    }

    public int findExtra(int a[], int b[]) {
        // add code here.
        int n = a.length;
        
        
    //     int i= 0 , j = 0;
        
    //     while(i<n && j < m){
            
    //         if(a[i] == b[j]){
    //             j++;
    //             i++;
    //         }else return i;
            
    //     }
        
    //   return -1;
    
        int low = 0 , high = n-2;
        
        int ans = n-1;
        
        while(low <= high){
            int mid = low + (high - low)/2;
            
            if(a[mid] != b[mid]){
                ans = mid;
                high = mid -1;
            }else{
                low = mid + 1;
            }
        }
        
        return ans;
    
    }

    // transform string
    public static int minimumMoves(String s) {
        int moves = 0 ;
        int i = 0;

        while(i < s.length()){
            if(s.charAt(i) == 'X'){
                moves += 1;
                i += 3;
            }

            else{
                i += 1;
            }
        }

        return moves;
    }

    // Sort Matrix Diagonally
    public static  int[][] diagonalSort(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
    
        // Step 1: Sort diagonals starting from first row
        for (int col = 0; col < m; col++) {
            sort_O(mat, 0, col, n, m);
        }
    
        // Step 2: Sort diagonals starting from first column (excluding first row)
        for (int row = 1; row < n; row++) {
            sort_O(mat, row, 0, n, m);
        }
    
        return mat;
    }
    
    public static void sort_O(int[][] mat, int row, int col, int n, int m) {
        int[] values = new int[101];  // Counting sort array (assumption: values range from 0 to 100)
    
        // Step 1: Store frequency of diagonal elements
        int r = row, c = col;
        while (r < n && c < m) {
            values[mat[r][c]]++;
            r++;
            c++;
        }
    
        // Step 2: Refill diagonal using sorted values
        r = row;
        c = col;
        for (int i = 0; i < 101; i++) {  // Traverse sorted values
            while (values[i] > 0) {  // If value exists in diagonal
                mat[r][c] = i;  // Place sorted value
                r++;
                c++;
                values[i]--;  // Reduce count
            }
        }
    }

    public void sort_B(int[][] mat, int row, int col, int n, int m) {
        List<Integer> values = new ArrayList<>();
    
        // Step 1: Store diagonal elements in ArrayList
        int r = row, c = col;
        while (r < n && c < m) {
            values.add(mat[r][c]);
            r++;
            c++;
        }
    
        // Step 2: Sort the values
        Collections.sort(values);
    
        // Step 3: Place sorted values back in the matrix
        r = row;
        c = col;
        int index = 0;
        while (r < n && c < m) {
            mat[r][c] = values.get(index++);
            r++;
            c++;
        }
    }
    

    public static int countTriangles(int arr[]) {
        int n = arr.length;
        
        Arrays.sort(arr);
        
        int cnt = 0;
        
        for(int i = n-1 ; i >= 0 ; i--){
            int start = 0;
            int end = i-1;
            
            
            while(start < end){
                
                if(arr[start] + arr[end] > arr[i]){
                
                    cnt += (end - start);
                    end--;
                }
                else{
                    start++;
                }
            
            }
        }
        
        return cnt;
    }

    // return valid triplets
    public static List<List<Integer>> validTriangles(int arr[]) {
        int n = arr.length;
        Arrays.sort(arr); // Sorting the array

        List<List<Integer>> result = new ArrayList<>(); // To store valid triplets

        // Fix the largest side and find two smaller sides using two-pointer approach
        for (int i = n - 1; i >= 2; i--) {
            int start = 0;
            int end = i - 1;

            while (start < end) {
                if (arr[start] + arr[end] > arr[i]) {
                    // Store all valid triplets
                    for (int k = start; k < end; k++) {
                        result.add(Arrays.asList(arr[k], arr[end], arr[i]));
                    }
                    end--; // Move end pointer left
                } else {
                    start++; // Move start pointer right
                }
            }
        }

        return result;
    }


    // Sort elements on the basis of number of factors
    static class Pair{
        int num;
        int factorCount;

        Pair(int num , int factorCount){
            this.num = num;
            this.factorCount = factorCount;
        }
        
    }

    public static int countFactors(int N){
        int cnt = 0;

        for(int i = 1 ; i*i <= N ; i++){
            if(N % i == 0){
                cnt++;
                if(N/i != N){
                    
                        cnt++;
                }
            }
        }

        return cnt;
    }

    public static void sortByFactors(int[] arr){
        List<Pair> factorList = new ArrayList<>();

        for(int num : arr){
            factorList.add(new Pair(num , countFactors(num)));
        }

        factorList.sort((a,b) -> b.factorCount - a.factorCount); // sort in descending order;

        for (Pair p : factorList) {
            System.out.print(p.num + " ");
        }

    }

    // reverse except special characters
    public static  String reverseSpecialString(String str)
    {
        //complete the function here
        char[] charArray = str.toCharArray();

        int n = str.length();
        
        int i = 0 , j = n-1;
        
        while( i< j){
            
            if(!Character.isAlphabetic(charArray[i])){
                i++;
            }
            else if(!Character.isAlphabetic(charArray[j])){
                j--;
            }
            else{
                char temp = charArray[i];
                charArray[i] = charArray[j];
                charArray[j] = temp;
                
                i++;
                j--;
            }
        }
        
         
         
         return  new String(charArray);
    }

    // Decode String s = "3[b2[ca]]" -> "bcacabcacabcaca"
    static String decodeString(String s) {
        
        int n = s.length();
        
        Stack<Integer> numStack = new Stack<>();
        Stack<StringBuilder> strStack = new Stack<>();
        
        int num = 0;
        StringBuilder currStr = new StringBuilder();
        
        
        for(int i = 0 ; i<n ; i++){
            char ch = s.charAt(i);
            
            if(Character.isDigit(ch)){
                num = (num * 10) + (ch - '0');
            }
            else if(ch == '['){
                numStack.push(num);
                strStack.push(currStr);
                num = 0;
                currStr = new StringBuilder();
            }
            else if(ch == ']'){
                int repeatTimes = numStack.pop();
                StringBuilder temp = strStack.pop();
                for(int j = 0 ; j < repeatTimes ; j++){
                    temp.append(currStr);
                }
                currStr = temp;
            }
            else{
                currStr.append(ch);
            }
        }
        
        return currStr.toString();
    
    }

    // Modify the array 
    static ArrayList<Integer> modifyAndRearrangeArr(int arr[]) {
        int n = arr.length;
        
        ArrayList<Integer> ans = new ArrayList<>();
        
        for(int i = 0 ; i< n-1 ; i++){
            if(arr[i] != 0 && arr[i+1] != 0 && arr[i] == arr[i+1]){
                arr[i] = arr[i] * 2;
                arr[i+1] = 0;
            }
        }
        
        for(int num : arr){
            if(num > 0){
                ans.add(num);
            }
        }
        
        int j = ans.size();
        
        while(j < n){
            ans.add(0);
            j++;
        }
        
        return ans;
    }

    // Two sum check
    public static boolean twoSum(int arr[], int target) {
        int n = arr.length;
        
        Arrays.sort(arr);
        
        int i = 0 , j = n-1;
        
        while(i < j){
            int sum = arr[i] + arr[j];
            
            if(sum == target) return true;
            else if(sum < target) i++;
            else j--;
        }
        
        return false;
    }

    // Remove Character
    public static String removeChars(String str1, String str2) {
        // code here
        int n = str1.length();
        int m = str2.length();
        
        HashSet<Character> toBeRemoved = new HashSet<>();
        
        for(int i = 0 ; i<m ; i++){
            toBeRemoved.add(str2.charAt(i));
        }
        
        StringBuilder ans = new StringBuilder();
        
        for(int i = 0 ;i<n ; i++){
            if(toBeRemoved.contains(str1.charAt(i))){
                continue;
            }
            else{
                ans.append(str1.charAt(i));
            }
        }
        
        return ans.toString();
    }


   // Top k Frequent element 
   public static int[] topKFrequent(int [] arr , int k){
        int n = arr.length;

        List<Integer> bucket[] = new List[n + 1];

        Map<Integer,Integer> fmpp = new HashMap<>();

        for(int i : arr){
            fmpp.put(i , fmpp.getOrDefault(i, 0) + 1);
        }

        for(int key : fmpp.keySet()){
            int freq = fmpp.get(key);

            if(bucket[freq] == null){
                bucket[freq] = new ArrayList<>();
            }
            bucket[freq].add(key);
        }

        int res[] = new int[k];

        int cnt = 0;

        for(int i = bucket.length - 1 ; i >= 0 && cnt < k ; i--){
            if(bucket[i] != null){
                for(Integer it : bucket[i]){
                    res[cnt++] = it;
                }
            }
        }

        return res;
    }

   // Sort Characters By Frequency  -- eg :- Input: s = "tree"  Output: "eert"
   public static  String frequencySort(String s) {
    int n = s.length();

    Map<Character , Integer> fmpp = new HashMap<>();

    for(char ch : s.toCharArray()){
        fmpp.put(ch , fmpp.getOrDefault(ch , 0) + 1);
    }

    List<Character> bucket[] = new List[n + 1];

    for(char key : fmpp.keySet()){
        int freq = fmpp.get(key);
        
        if(bucket[freq] == null){
            bucket[freq] = new ArrayList<>();
        }

        bucket[freq].add(key);
    }

    StringBuilder res = new StringBuilder();

    for(int i = bucket.length - 1 ; i >= 0 ; i--){
        if(bucket[i] != null){
            for(Character c : bucket[i]){
                for(int j = 0 ; j < i ; j++){
                    res.append(c);
                }
            }
        }
    }

    return res.toString();

}
    
    // next_permutation : find next lexicographically greater permutation
    public static List< Integer > nextGreaterPermutation(List< Integer > A){
        int n = A.size();

        // 1. Find Breakpoint
        int ind = -1;
        for(int i = n - 2 ; i >= 0 ; i--){
            if(A.get(i) < A.get(i + 1)){
                ind = i;
                break;
            }
        }

        if(ind == -1){
            Collections.reverse(A);
            return A;
        }

        // 2. Find the next greater element
        for(int i = n-1 ; i > ind ; i--){
            if(A.get(i) > A.get(ind)){
                int temp = A.get(i);
                A.set(i , A.get(ind));
                A.set(ind , temp);
                break;
            }
        }

        // 3. Reverse the Right half
        List <Integer> subList = A.subList(ind+1, n);
        Collections.reverse(subList);

        return A;
    }

    public static long getNextEven(String x) {
        // Your code goes here
        int n = x.length();
        
        char[] nums = x.toCharArray();
        
        while(true){
            int idx = -1;
            
            for(int i = n - 2 ; i >= 0 ; i--){
                if(nums[i] < nums[i+1]){
                    idx = i;
                    break;
                }
            }
            
            if(idx == -1){
                return -1L;
            }
            
            int index = idx + 1;
            for(int i = index ; i < n ; i++){
                if(nums[i] > nums[idx]){
                    index = i;
                }
            }
            
            char temp = nums[idx];
            nums[idx] = nums[index];
            nums[index] = temp;
            
            Arrays.sort(nums , idx + 1 , n);
            
            if((nums[n-1] - '0') %2 == 0){
                String val = String.valueOf(nums);
                return Long.parseLong(val);
            }
        }
    }

    // Find number of days between two given dates
    public static int[] daysInMonth ={31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public static boolean isLeap(int year){
        return ((year % 4== 0 && year % 100 != 0) || (year % 400 == 0));
    }

    public static int daysFromStart(int day , int month , int year){

        int days = 0;

        // Add days for complete years
        for(int y = 0 ; y < year ; y++){
            days += isLeap(y) ? 366 : 365; 
        }

        // Add days for complete months
        for(int m = 0 ; m < month-1 ; m++){
            days += daysInMonth[m];
            if(m == 1 && isLeap(year)){ // Check for leap year in February
                days += 1;
            }
        }

        // Add remaining days
        days += day;

        return days;
    }

    public static int noOfDays(int d1 , int m1 , int y1 , int d2 , int m2 , int y2){
        int days1 = daysFromStart(d1 , m1 , y1);
        int days2 = daysFromStart(d2 , m2 , y2);
        
        return(Math.abs(days2 - days1));
    }

    // Numbers smaller than current number 
    public static int[] smallerNumbersThanCurrent(int[] nums) {
        int bucket[] = new int[102];

        // Get frequency
        for(int num : nums){
            bucket[num]++;
        }

        // Calculate prefix sum
        for(int i = 1 ; i< bucket.length ; i++){
            bucket[i] += bucket[i - 1];
        }

        // populate result
        int res[] = new int[nums.length];

        for(int i = 0 ; i< res.length ; i++){
            if(nums[i] == 0){
                res[i] = 0;
            }
            else{
                res[i] = bucket[nums[i] - 1];
            }
        }

        return res;


    }
    
    // Count of Smaller Numbers After Self
    public static  int lowerBound(List<Integer> list, int x){
        
        int low = 0 , high = list.size() - 1;

        int ans = list.size() ;

        while(low <= high){
            int mid = (low + high)/2;

            if(list.get(mid) >= x){
                ans = mid;
                high = mid - 1;
            }
            else{
                low = mid + 1;
            }
        }

        return ans;
    }
    public static  List<Integer> countSmaller(int[] nums) {
        List<Integer> res = new ArrayList<>();
        List<Integer> sortedList = new ArrayList<>();

        for(int i = nums.length - 1; i >= 0 ; i--){
            int index = lowerBound(sortedList , nums[i]);
            res.add(index);

            sortedList.add(index , nums[i]);
        }

        Collections.reverse(res);
        return res;
    }

    // Find the kth largest element
    public static int findKthLargest(int[] nums, int k) {
        // TC: O(N*logK) SC: O(K)
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for(int i = 0 ; i< nums.length ; i++){
            if(minHeap.size() < k || nums[i] > minHeap.peek()){
                minHeap.add(nums[i]);
            }

            if(minHeap.size() > k){
                minHeap.poll();
            }
        }

        return minHeap.poll();
    }

    // Equilibrium point
    public static int findEquilibrium(int arr[]) {
        // code here
        int totalSum = 0;
        for(int n : arr){
            totalSum += n;
        }
        
        int curSum = 0;
        
        for(int i = 0 ; i< arr.length ; i++){
            if(curSum == totalSum - curSum -arr[i]){
                return i;
            }
            
            curSum += arr[i];
        }
        
        return -1;
    }

    // Maximum product of three numbers 
    public static int maximumProduct(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);

        return Math.max((nums[n-1] * nums[n-2] * nums[n-3]) , (nums[0]*nums[1]*nums[n-1]));
    }

    // Largest number formed from an Array
    public static String findLargest(int[] arr) {
        String strNums[] = new String[arr.length];
        for(int i = 0 ; i< arr.length ; i++){
            strNums[i] = String.valueOf(arr[i]);
        }
        
        // Compare and sort
        Arrays.sort(strNums , (a,b) -> (b + a).compareTo(a + b));
        
        if (strNums[0].equals("0")) return "0";
        
        StringBuilder sb = new StringBuilder();
        
        for(String str : strNums){
            sb.append(str);
        }
        
        return sb.toString();
    }

    // Pair sum closest to Zero
    public static int closestToZero (int arr[], int n)
    {
        Arrays.sort(arr);
        if(n < 2) return 2*arr[0];
        
        else if(arr[0] > 0) return (arr[0] + arr[1]);
        else if(arr[n-1] < 0) return(arr[n-1] + arr[n-2]);
        
        int i = 0 , j = n-1;
        int minSum = Integer.MAX_VALUE;
        
        while(i < j){
            int sum = arr[i] + arr[j];
            
            if(Math.abs(sum) < Math.abs(minSum)){
                minSum = sum;
            }
            
            if(Math.abs(sum) == Math.abs(minSum)){
                minSum = Math.max(sum , minSum);
            }
            
            if(sum < 0) i++;
            else j--;
        }
        
        return minSum;
    }

    // Count palindrome words in a sentence
    public static boolean checkPalin(String word)
    {
        int n = word.length();
        word = word.toLowerCase();
        for (int i=0; i<n; i++,n--)
           if (word.charAt(i) != word.charAt(n - 1))
              return false;       
        return true;
    }
     
    // Function to count palindrome words
    public static int countPalin(String str)
    {        
        // to check last word for palindrome
        str = str + " ";
         
        // to store each word
        String word = "";
        int count = 0;
        for (int i = 0; i < str.length(); i++)
        {
            char ch = str.charAt(i);
             
            // extracting each word
            if (ch != ' ')
                word = word + ch;
            else {
                if (checkPalin(word))
                    count++;
                word = "";
            }
        }
         
        return count;
    }

    // Check Panagram
    public static boolean checkIfPangram(String sentence) {
        boolean seen[] = new boolean[26];

        for(char ch : sentence.toCharArray()){
            seen[ch - 'a'] = true;
        }

        for(boolean b : seen){
            if(!b) return false;
        }

        return true;
    }

    // Needle in Haystack
    public int strStr(String haystack, String needle) {
        // TC : O(M * N)
        for(int i = 0 ; i< haystack.length() ; i++){
            int j = 0;
            int k = i;

            while(j < needle.length() && k < haystack.length() && 
            needle.charAt(j) == haystack.charAt(k)){
                j++;
                k++;
                if(j == needle.length()){
                    return i;
                }
            }
        }

        return -1;
    }

    // Matrix Diagonal sum
    public static int diagonalSum(int[][] mat) {
        int n = mat.length;

        int sum = 0;

        for(int i = 0 ; i<n ; i++){
            sum += mat[i][i];
            sum += mat[i][n-i-1];
        }

        if(n % 2 == 1){
            sum -= mat[n/2][n/2];
        }

        return sum;
    }

    // Matrix sum
    public static int matrixSum(int[][] nums) {

        int n = nums.length;

        int m = nums[0].length;

        for(int i = 0 ; i<n ; i++){
            Arrays.sort(nums[i]);
        }

        int sum = 0;

        for(int j = 0 ; j< m ; j++){
            int maxCol = 0;

            for(int i = 0 ; i< n ; i++){
                maxCol = Math.max(nums[i][j] , maxCol);
            }

            sum += maxCol;
        }

        return sum;
        
    }

    // Calculate Cricket Scores
    public static int calculateScores(String[] scores){
        int playerOne = 0;
        int playerTwo = 0;
        int extras = 0;
        boolean isPlayerOneTurn = true;

        for(String val : scores){
            if(val.equals("N") || val.equals("W")){
                extras += 1;
            }else if(val.equals(".")){

            }else{
                int run = Integer.parseInt(val);
                if(isPlayerOneTurn){
                    playerOne += run;
                }else{
                    playerTwo += run;
                }

                isPlayerOneTurn = !isPlayerOneTurn;
            }
        }

        return (playerOne + playerTwo + extras);
    }

    // Insert 0 after Consecutive (K times) Of 1 is Found
    public static List<Integer> insertZeroAfterKOnes(int[] bits, int k){
        ArrayList<Integer> res = new ArrayList<>();

        int count = 0;

        for(int bit : bits){
            res.add(bit);

            if(bit == 1){
                count++;

                if(count == k){
                    res.add(0);
                    count = 0;
                }
            }else{
                count = 0;
            }
        }

        return res;
    }

    // Is A Square
    public static int squareDist(int x1 , int y1 , int x2 , int y2){
        return (x2 - x1) * (x2-x1) + (y2 - y1)*(y2 - y1);
    }

    public String isSquare(int x1, int y1, int x2, int y2, int x3, int y3, int x4,
    int y4) {
        // code here
        int [] d = new int[6];
        
        d[0] = squareDist(x1,y1,x2,y2);
        d[1] = squareDist(x2,y2,x3,y3);
        d[2] = squareDist(x3,y3,x4,y4);
        d[3] = squareDist(x4,y4,x1,y1);
        d[4] = squareDist(x1,y1,x3,y3);
        d[5] = squareDist(x2,y2,x4,y4);
        
        Arrays.sort(d);
        
        if (d[0] == 0) return "No";
        
        if(d[0] == d[1] && 
        d[0] == d[2]  && 
        d[0]  == d[3]  && 
       d[4]  == d[5]){
            return "Yes";
        }else return "No";

        
    }

    // Create Target Array in the Given Order
    public static int[] createTargetArray(int[] nums, int[] index) {
        int n = nums.length;

        int target[] = new int[n];
        int size = 0;
        for(int i = 0; i < n ; i++){
            // “Starting from the last valid element, keep copying each value one step to the right until I reach the insertion index.”
            for(int j = size ; j > index[i] ; j--){
                target[j] = target[j - 1];
            }

            target[index[i]] = nums[i];
            size++;
        }

        return target;
    }

    // Lucky Numbers in a Matrix
    public static List<Integer> luckyNumbers(int[][] matrix) {
        // Set Intersection works on brute force 
        // TC : O(M * N)
        int m = matrix.length;
        int n = matrix[0].length;

        int maxRowMins = Integer.MIN_VALUE;
        
        for(int row[] : matrix){
            int rowMins = Integer.MAX_VALUE;
            for(int j = 0 ; j < n ; j++){
                rowMins = Math.min(rowMins , row[j]);
            }

            maxRowMins = Math.max(rowMins , maxRowMins);
        }

        for(int j = 0 ; j < n ; j++){
            int maxCols = matrix[0][j];
            for(int i = 0 ; i < m ; i++){
                maxCols = Math.max(matrix[i][j] , maxCols);
            }

            if(maxCols == maxRowMins){
                List<Integer> ans = new ArrayList<>();
                ans.add(maxCols);
                return ans;
            }
            
        }

        return new ArrayList<>();
    }




    public static void main(String[] args) {
        
        int[] nums = {131, 11, 48};
        System.out.println("1. The distinct elements in the array elements are : " +Arrays.toString(distinctDigitsB(nums))); 


        int arrSRT[] = {1, 2, 3, 5, 4, 7, 10};
        sortItO(arrSRT);
        System.out.println("2. The Array sorted in a specific order is : "+Arrays.toString(arrSRT)); 

        denominatorCurr(2485);
        deciToBinary(7);
        deciToHexadeci(15);

        System.out.println("3. The Decimal value of the provided Hexadecimal value is: " + hexToDecimal("9F"));
        hexToBinary("2f"); // 152

        binaryToHex("00101111");

        ArrayList<Integer> ansFsorted = sortFreq(new int[]{7,7,5,5,6});

        System.out.println( "The frequency sorted array is : " + ansFsorted);

        String A = "gksrek", B = "geeksforgeeks";
        //  String A = "AXY", B = "YADXCP";

        System.out.println("is the String A is a Subsequence of String B : " + isSubsequence(A, B));

        // sort by factors 
        int factSort[] =  {5, 11, 10, 20, 9, 16, 23};
        sortByFactors(factSort);

        // reverse special char
        System.out.println("Reversed string: " + reverseSpecialString("AbC&dEf"));

        // String Decoding
        String encoded = "2[a3[c2[x]]y]";
        String decoded = decodeString(encoded);
        System.out.println("Decoded String: " + decoded);


        // Top K frequent elements
        int[] freqArr = {3, 4, 2, 3, 16, 3, 15, 16, 15, 15, 16, 2, 3};
        int k = 3;
        int[] topK = topKFrequent(freqArr, k);
        System.out.println("Top " + k + " frequent elements are: " + Arrays.toString(topK));

        // Frequent repeating Character sort
        String input = "tree";
        String sortedByFrequency = frequencySort(input);
        System.out.println("String sorted by frequency: " + sortedByFrequency);


        // Next Permutation 
        List<Integer> permutation = Arrays.asList(1, 2, 3);
        List<Integer> nextPermutation = nextGreaterPermutation(permutation);
        System.out.println("Next greater permutation: " + nextPermutation);

        // Get Next Even
        String number = "34722641";
        long nextEven = getNextEven(number); // Output: 34724126
        if (nextEven != -1) {
            System.out.println("Next even number with same digits: " + nextEven);
        } else {
            System.out.println("No valid next even number can be formed.");
        }

        // Number of days between two dates
        int d1 = 10, m1 = 2, y1 = 2014;
        int d2 = 10, m2 = 3, y2 = 2015;
        int daysBetween = noOfDays(d1, m1, y1, d2, m2, y2);
        System.out.println("Number of days between the two dates: " + daysBetween);

        // Numbers smaller than current number
        int[] smallerNums = {8, 1, 2, 2, 3};
        int[] smallerCounts = smallerNumbersThanCurrent(smallerNums);
        System.out.println("Numbers smaller than current number: " + Arrays.toString(smallerCounts));

        // Count of Smaller Numbers After Self
        int[] numsForCountSmaller = {5, 2, 6, 1};
        List<Integer> smallerCountsList = countSmaller(numsForCountSmaller);
        System.out.println("Count of smaller numbers after self: " + smallerCountsList);

        // Find the kth largest element
        int[] numsForKthLargest = {3, 2, 1, 5, 6, 4};
        int kForLargest = 3;
        int kthLargest = findKthLargest(numsForKthLargest, kForLargest);
        System.out.println("The " + kForLargest + "th largest element is: " + kthLargest);

        // Equilibrium Point
        int[] equilibriumArr = {1, 3, 5, 2, 2};
        int equilibriumIndex = findEquilibrium(equilibriumArr);
        if (equilibriumIndex != -1) {
            System.out.println("Equilibrium point is at index: " + equilibriumIndex);
        } else {
            System.out.println("No equilibrium point found.");
        }

        // Largest number formed from an Array
        int[] largestArr = {3, 30, 34, 5, 9}; // output : "9534330"
        String largestNumber = findLargest(largestArr);
        System.out.println("Largest number formed from the array: " + largestNumber);


        // Closest to Zero
        int[] closestArr = {-8 , -66 ,-60};
        int closestSum = closestToZero(closestArr, closestArr.length);
        System.out.println("The pair sum closest to zero is: " + closestSum);

        // Count palindromic sentence
        System.out.println("The palindromic word in the sentence are : "+ countPalin("Madam Arora teaches malayalam"));

        // Calculate Scores
        String[] timeline = {"1", "2", "W", "1", ".", "2", "N", "1", "4", "6"};
        int totalScores = calculateScores(timeline);
        System.out.println("The Total scores by the players were : " + totalScores);

        // Insert 0 after Consecutive (K times) Of 1 is Found
        int[] bits = {1, 1, 1, 0, 1, 1, 1, 1, 0, 1};
        int kOnes = 3;
        List<Integer> modifiedBits = insertZeroAfterKOnes(bits, kOnes);
        System.out.println("Modified bits array: " + modifiedBits);

        // Create Target Array in the Given Order
        int[] numsTarget = {0, 1, 2, 3, 4};
        int[] indexTarget = {0, 1, 2, 2, 1};
        int[] createdTarget = createTargetArray(numsTarget, indexTarget);
        System.out.println("Created target array: " + Arrays.toString(createdTarget));


    }

}
