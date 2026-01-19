import java.util.*;
import java.util.Arrays;

public class Recursion {

    public static void print1toN(int i , int n){

        if(i > n){
            return;
        }
        System.out.println(i);
        print1toN(i+1, n);
    }

    public static void print1toNbT(int i , int n){

        if(i < 1){
            return;
        }
        print1toNbT(i-1, n);
        System.out.println(i);
    }

    public static void printNto1(int i , int n){
       if(i <1) return;

       System.out.println(i);
       printNto1(i-1, n);
    }

    public static void printNto1bT(int i , int n){
        
        if(i > n) return;

        printNto1bT(i+1, n);
        System.out.println(i);
    }

    // Reverse a Number
    static int revSum = 0;
    public static void reverseNum_B(int n){
        if(n == 0) return;

        int rem = n % 10;
        revSum = revSum * 10 + rem;
        reverseNum_B(n / 10);
    }

    public static int reverseNum_A(int n){
        int digits = (int)(Math.log10(n)) + 1;
        return helper(n, digits);
    }

    public static int helper(int n , int digits){
        if(n % 10 == n){
            return n;
        }

        int rem = n % 10;

        return rem * (int) Math.pow(10 , digits - 1) + helper(n / 10, digits - 1);
    }


    public static void reverseArray(int i , int arr[] , int n ){
        if(i >= n/2) return;
        int temp = arr[i];
        arr[i] = arr[n-i-1];
        arr[n-i-1] = temp;

        reverseArray(i+1, arr , n);
    }

    public static boolean  checkPalindrome(int i , String s , int n ){

        if(i >= n/2) return  true;
        if(s.charAt(i) != s.charAt(n-i-1)) return false;

        return checkPalindrome(i+1, s, n);
    }

    // Generate Paranthesis
    public static  List<String> generateParenthesis(int n){
        List<String> ans = new ArrayList<>();
        String temp = "";
        
        paranthesis(n , 0 , 0, temp , ans);
        return ans;
    }

    public static void paranthesis(int n , int open , int close ,String temp , List<String> ans ){
        
        if(temp.length() == n*2){
            ans.add(temp);
            return;
        }

        if(open < n)paranthesis(n , open +1 , close , temp + "(" ,  ans);
        if(close < open) paranthesis(n , open , close+1 , temp+")" , ans );

    }

    // Generate Binary Strings
    public static List<String> generateBinaryStrings(int n) {
        List<String> ans = new ArrayList<>();
        String temp = "";
        
        binary(n , temp , ans);
        return ans;
    }
    
    public static void binary(int n , String temp , List<String> ans){
        if(temp.length() == n){
            ans.add(temp);
            return;
        }
        
        binary(n , temp + "0" , ans);
        
       if(temp.isEmpty() || temp.charAt(temp.length() -1) != '1'){
            binary(n , temp + "1" , ans);
       }
        
    }


    // Subsequences
    public static void printSubSequences( int arr[] ,ArrayList<Integer> ds , int ind ){
        if(ind == arr.length){
            System.out.println(ds);
            return;
        }

        ds.add(arr[ind]);

        printSubSequences(arr , ds , ind+1);

        ds.remove(ds.size()-1);

        printSubSequences(arr , ds , ind+1);

    }

    public static void subsequencesSumk(int arr[] ,int s , int k , ArrayList<Integer> ds , int ind ){
        if(ind == arr.length){
            if(s == k){
                System.out.println(ds);
            }
            return;
        }

        ds.add(arr[ind]);
        s += arr[ind];

        subsequencesSumk(arr, s, k, ds, ind+1);

        ds.remove(ds.size() - 1);
        s -= arr[ind];

        subsequencesSumk(arr, s, k , ds, ind+1);
    }

    public static boolean oneSubsequencesSumk(int arr[] ,int s , int k , ArrayList<Integer> ds , int ind ){     
        if(ind == arr.length ){
            if(s == k){
                System.out.println(ds);
                return true;
            }
            return false;
        }

        ds.add(arr[ind]);
        s += arr[ind];

        if (oneSubsequencesSumk(arr, s, k, ds, ind+1) == true) return true;

        ds.remove(ds.size() - 1);
        s -= arr[ind];

        if(oneSubsequencesSumk(arr, s, k , ds, ind+1) == true) return true;

        return false;
    }

    public static int  cntSubsequencesSumk(int arr[] ,int s , int k , ArrayList<Integer> ds , int ind ){
        if(s > k) return 0; // if array contains +ves alone
        if(ind == arr.length ){
            if(s == k){
                // System.out.println(ds);
                return 1;
            }
            return 0;
        }

        // ds.add(arr[ind]);
        s += arr[ind];

        int l = cntSubsequencesSumk(arr, s, k, ds, ind+1);

        // ds.remove(ds.size() - 1);
        s -= arr[ind];

        int r = cntSubsequencesSumk(arr, s, k , ds, ind+1);

        return l + r;
    }

    // Combinations 
    private static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        combinations(n , k , ans , new ArrayList<Integer>() , 1);
        return ans;
    }

    private static void combinations(int n , int k , List<List<Integer>> ans , List<Integer> ds , int ind){
        if(ds.size() == k){
            ans.add(new ArrayList<>(ds));
            return;
        }

        for(int i = ind ; i <= n ; i++){
            ds.add(i);
            combinations(n , k , ans ,ds , i + 1);
            ds.remove(ds.size() - 1);
        }
    }

    // Combination Sum - I
    public static List<List<Integer>> combinationSumI(int[] candidates , int target){
        List<List<Integer>> ans = new ArrayList<>();
        findCombinationsI(candidates, target , ans , new ArrayList<>() , 0);
        return ans;
    }

    public static void findCombinationsI(int[] arr , int target , List<List<Integer>> ans , List<Integer> ds , int ind){
        if(ind == arr.length){
            if(target == 0){
                ans.add(new ArrayList<>(ds));
            }
            return;
        }

        if(arr[ind] <= target){
            ds.add(arr[ind]);
            findCombinationsI(arr, target-arr[ind], ans, ds, ind); // pick ie, same candidate again
            ds.remove(ds.size() - 1);
        }
        findCombinationsI(arr, target, ans, ds, ind+1); // not pick 
    }

    // Combination Sum - II
    public static List<List<Integer>> combinationSumII(int [] candidates , int target){
        List<List<Integer>> ans = new ArrayList<>();
        // Set<List<Integer>> ans = new HashSet<>();
        Arrays.sort(candidates);
        // findCombinationsII_B(candidates, target, ans, new ArrayList<>(),0);
        findCombinationsII_O(candidates , target , ans, new ArrayList<>() , 0);
        return new ArrayList<>(ans);
    }

    public static void findCombinationsII_B(int arr[] , int target , Set<List<Integer>> ans , List<Integer> ds , int ind){

        if(ind == arr.length){
            if(target == 0){
                ans.add(new ArrayList<>(ds));
            }
            return;
        }

        if(arr[ind] <= target){
            ds.add(arr[ind]);
            findCombinationsII_B(arr, target-arr[ind], ans , ds, ind+1);
            ds.remove(ds.size() - 1);
        }
        findCombinationsII_B(arr , target , ans , ds , ind + 1);
    }

    public static void findCombinationsII_O(int arr[] , int target , List<List<Integer>> ans , List<Integer> ds , int ind){
        if(target == 0){
            ans.add(new ArrayList<>(ds));
            return;
        }

        for(int i = ind ; i<arr.length ; i++){
            if(i > ind && arr[i] == arr[i - 1]) continue;
            if(arr[i] > target) break;

            ds.add(arr[i]);
            findCombinationsII_O( arr, target - arr[i], ans, ds, i+ 1);
            ds.remove(ds.size() - 1);
        }
    }
   
    // Combination Sum - III
    public static  List<List<Integer>> combinationSum3(int k, int n) {
        int arr[] = {1,2,3,4,5,6,7,8,9};
        List<List<Integer>> ans = new ArrayList<>();
        findCombinationsIII(arr , k , n , new ArrayList<Integer>(), ans , 0);
        return ans;
    }

    public static void findCombinationsIII(int[] arr , int k , int target , ArrayList<Integer> ds , List<List<Integer>> ans , int ind){
        
        if(target == 0 && ds.size() == k){
            ans.add(new ArrayList<>(ds));
            return;
        }

        if(target < 0 || ds.size() > k || ind == arr.length) return;

        if(arr[ind] <= target){
            ds.add(arr[ind]);
            findCombinationsIII(arr , k , target-arr[ind] , ds , ans , ind+1);
            ds.remove(ds.size() - 1);
        }

        findCombinationsIII(arr , k , target , ds , ans , ind+1);
        
    }

    // Combination Sum - IV
    public static  int combinationSum4(int[] nums, int target) {
        return findCombinationsIV(nums , target);
    }

    public static int findCombinationsIV(int arr[] , int k ){
        if(k < 0 ) return 0;
        if(k == 0) return 1;

        int total = 0;

        for(int i = 0 ; i < arr.length; i++){
            total += findCombinationsIV(arr , k - arr[i]);
        }

        return total;
    }

    // Subsets - II
    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        int n = nums.length;
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        findSubsets(nums , n , new ArrayList<Integer>() , ans , 0);
        return ans;
    }

    public static void findSubsets(int arr[] , int n , ArrayList<Integer> ds , List<List<Integer>> ans , int ind ){
        
        ans.add(new ArrayList<>(ds));

        for(int i = ind ; i<n ; i++){
            if(i>ind && arr[i-1] == arr[i]) continue;

            ds.add(arr[i]);
            findSubsets(arr , n , ds , ans , i+1);
            ds.remove(ds.size() - 1);
        }

    }

    // Letter Combinations of a Phone Number
    public static List<String> letterCombinations(String digits){

        Map<Character,String> mpp = new HashMap<>();
        mpp.put('2', "abc");
        mpp.put('3' , "def");
        mpp.put('4',"ghi" );
        mpp.put('5',"jkl");
        mpp.put('6' , "mno");
        mpp.put('7' , "pqrs");
        mpp.put('8' , "tuv");
        mpp.put('9' , "wxyz");

        List<String> ans = new ArrayList<>();

        if(digits.length() == 0) return ans;

        String temp = "";

        findLetterCombinations(digits , ans , temp , mpp , 0);

        return  ans;
    }

    public static void findLetterCombinations(String s, List<String> ans, String temp, Map<Character, String> mpp, int ind){

        if(ind == s.length()){
            ans.add(temp);
            return;
        }

        char chr = s.charAt(ind);
        String str = mpp.get(chr);

        for(int j = 0 ; j<str.length() ; j++){
            char c = str.charAt(j);

            findLetterCombinations(s, ans, temp + c, mpp, ind+1);
        }

    }

    // Number of Dice Rolls With Target Sum (Brute Force Recursion)
    public static final int MOD = (int)(1e9 + 7);
    public static int numRollsToTarget(int n, int k, int target) {
        return validRolls(n , k , target , 0);
    }
    private static int validRolls(int n , int k , int target , int sum){
        if(sum > target) return 0;
        if(n == 0){
            return (sum == target) ? 1 : 0;
        }

        long cnt = 0L;

        if(n != 0){
            for(int j = 1 ; j <= k ; j++){
                cnt += validRolls(n - 1, k , target , sum + j);
            }
        }

        return (int)(cnt % MOD);
    }

    // permutation of String Array
    public static List<List<Integer>> permuteI(int [] nums){
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> ds = new ArrayList<>();
        boolean freq [] = new boolean[nums.length];
        recurPermutateI(nums ,ans , ds , freq);
        return ans;
    }

    public static void recurPermutateI(int[] nums , List<List<Integer>> ans, List<Integer> ds , boolean[]freq){
        if(ds.size() == nums.length){
            ans.add(new ArrayList<>(ds));
        }
        for(int i = 0 ; i<nums.length ; i++){
            if(!freq[i]){
                freq[i] = true;
                ds.add(nums[i]);
                recurPermutateI(nums, ans, ds, freq);
                ds.remove(ds.size()-1);
                freq[i] = false;
            }
        }
    } 

    public static List<List<Integer>> permuteII(int [] nums ,int ind ){
        List<List<Integer>> ans = new ArrayList<>();
        recurPermutateII(nums, ans , ind);
        return ans;
    }

    public static void swap(int i , int j , int[] nums){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void recurPermutateII(int[] nums ,List<List<Integer>> ans, int ind){
        if(ind == nums.length){
            List<Integer> ds = new ArrayList<>();
            for(int i = 0 ; i<nums.length ; i++){
                ds.add(nums[i]);
            }
            ans.add(new ArrayList<>(ds));
            return;
        }
        
        for(int i = ind ; i<nums.length ; i++)
        {
            swap(i , ind , nums);
            recurPermutateII(nums, ans, ind+1);
            swap(i , ind , nums); // reswap - backtracking
        }
    }

    // Permutations - II
    // Using Visited Array
    public static List<List<Integer>> permuteUniqueV(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        boolean vis[] = new boolean[nums.length];
        uniquePerI(nums , ans , new ArrayList<Integer>() , vis);
        return ans;
    }

    private static void uniquePerI(int[] nums , List<List<Integer>> ans , List<Integer> ds , boolean[] vis){
        if(ds.size() == nums.length){
            ans.add(new ArrayList<>(ds));
        }

        for(int i = 0 ; i < nums.length ; i++){
            if(vis[i]) continue;

            if(i > 0 && nums[i] == nums[i-1] && !vis[i - 1])continue;
            /*
             If the current element is the same as the previous element,
             and the previous element has not yet been used in the current branch,
             then skip this one.
            */

            vis[i] = true;
            ds.add(nums[i]);
            uniquePerI(nums , ans , ds , vis);
            ds.remove(ds.size() - 1);
            vis[i] = false;
            
        }
    }

    // Using Set 
    public static List<List<Integer>> permuteUniqueS(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        uniquePerII(nums , ans , 0);
        return ans;
    }

    private static void uniquePerII(int[] nums , List<List<Integer>> ans , int ind){

        if(ind == nums.length){
            List<Integer> ds = new ArrayList<>();
            for(int n : nums){
                ds.add(n);
            }

            ans.add(new ArrayList<>(ds));
        }

        Set<Integer> used = new HashSet<>();

        for(int i = ind ; i < nums.length ; i++){
            if(used.contains(nums[i])) continue;
            used.add(nums[i]);

            swap(nums , ind , i);
            uniquePerII(nums , ans , ind + 1);
            swap(nums , ind , i);
        }

    }

    private static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    // Palindrome partitioning 
    public static List<List<String>> palindromicPartition(String s){
        List<List<String>> ans = new ArrayList<>();
        List<String> path = new ArrayList<>();
        findPartitions(s, path, ans, 0);
        return ans;
    }

    public static void findPartitions(String s ,List<String> path ,List<List<String>> ans ,int ind){
        if(ind == s.length()){
            ans.add(new ArrayList<>(path));
            return;
        }
        for(int i = ind ; i< s.length() ; i++){
            if(isValidPalindrome(s, ind, i)){
                path.add(s.substring(ind , i+1));
                findPartitions(s, path, ans, i + 1); // Remeber i is the last index of the current partition
                path.remove(path.size() - 1);
            }
        }
    }

    public static boolean isValidPalindrome(String s , int start , int end ){
        while(start <= end){
            if(s.charAt(start) != s.charAt(end)){
                return false;
            }
            start++;
            end--;
        }

        return true;
    }

    // Word Search in a 2D matrix
    public static  boolean exist(char[][] board, String word) {
       int n = board.length;
       int m = board[0].length;

        for(int i = 0 ; i < n; i++){
            for(int j = 0 ; j < m; j++){
                if(board[i][j] == word.charAt(0)){
                    if(search(board , word , i , j , 0)){
                        return true;
                    }
                }
            }
        } 

        return false;
    }

    private static boolean search(char [][] board , String word , int i , int j , int ind){
        if(ind == word.length()){
            return true;
        }

        if(i < 0 || j < 0 || i == board.length || j == board[0].length || board[i][j] != word.charAt(ind)){
            return false;
        }

        char ch = board[i][j];
        board[i][j] = '#';

        boolean op1 = search(board , word , i - 1 , j , ind + 1); // up
        boolean op2 = search(board , word , i , j + 1 , ind + 1); // right
        boolean op3 = search(board , word , i + 1 , j , ind + 1); // down
        boolean op4 = search(board , word , i , j - 1 , ind + 1); // left

        board[i][j] = ch;

        return op1 || op2 || op3 || op4;
    }

    // Word Break partition
    public static boolean wordBreak(String s , String[] dictionary){
        Set<String> wordSet = new HashSet<>(Arrays.asList(dictionary));
        return findWords(s , wordSet , 0);
    }

    public static boolean findWords(String s , Set<String> dict , int ind){
        if(ind == s.length()){
            return true;
        }

        for(int i = ind ; i<s.length() ; i++){
            String prefix = s.substring(ind , i+1);

            if(dict.contains(prefix)){
                if(findWords(s , dict , i+1)){
                    return true;
                }
            }
        }
        return false;
    }
    
    // Decode Ways
    public static int countWays(String digits) {
        return decodeRecursive(digits, 0);
    }

    public static int decodeRecursive(String digits , int index){
        if(index == digits.length()) return 1;

        if(digits.charAt(index) == '0') return 0;

        // Take 1 digit
        int ways = decodeRecursive(digits , index + 1);

        // Take 2 digits
        if(index + 1 < digits.length()){
            int num = Integer.parseInt(digits.substring(index , index+2));
            if(num >= 10 && num <= 26){
                ways += decodeRecursive(digits, index+2);
            }
        }

        return ways;
    }

    // Rat in a Maze
    public static ArrayList<String> ratInMaze(int[][] maze) {
        int n = maze.length;
        int m = maze[0].length;
        ArrayList<String> ans = new ArrayList<>();
        
        if(maze[n-1][m-1] == 0 || maze[0][0] == 0) return ans;
        
        int vis[][] = new int[n][m];
        String temp = "";
        allPaths(maze , ans , temp , vis ,n - 1 , m - 1);
        
        Collections.sort(ans);
        return ans;
    }
    
    private static void allPaths(int[][] maze , List<String> ans , String temp , int[][] vis ,int i , int j){
        
        if(i < 0 || j < 0 || i >= maze.length || j >= maze[0].length ) return;
        
        if(maze[i][j] == 0) return;
        if(vis[i][j] == 1) return;
        
        if(i == 0 && j == 0){
            String rev = new StringBuilder(temp).reverse().toString();
            ans.add(rev);
            return;
        }
        
        vis[i][j] = 1;
        
        allPaths(maze , ans , temp + 'D' , vis ,i - 1 , j); // Down
        allPaths(maze , ans , temp + 'U' , vis ,i + 1 , j); // Up
        allPaths(maze , ans , temp + 'L' , vis , i , j + 1); // Left
        allPaths(maze , ans , temp + 'R' , vis , i , j - 1); // Right
        
        vis[i][j] = 0;
    }

    // N Queens
    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> ans = new ArrayList<>();
        boolean[][] board = new boolean[n][n];
        queens(board , ans , 0);
        return ans;
    }

    private static void queens(boolean[][] board , List<List<String>> ans , int row){
        if(row == board.length){
            List<String> ds = display(board);
            ans.add(new ArrayList<>(ds));
        }

        for(int col = 0;  col < board.length ; col++){
            if(isSafe(board , row , col)){
                board[row][col] = true;
                queens(board , ans , row + 1);
                board[row][col] = false;
            }
        }
    }

    private static boolean isSafe(boolean[][] board , int row , int col){
        
        // Check Upright
        for(int i = 0 ; i < row ; i++){
            if(board[i][col]){
                return false;
            }
        }

        // Check Upleft Diagonal
        int maxLeft = Math.min(row , col);
        for(int i = 1 ; i <= maxLeft ; i++){
            if(board[row - i][col - i]){
                return false;
            }
        }

        // Check Upright Diagonal
        int maxRight = Math.min(row , board.length - col - 1);
        for(int i = 1 ; i <= maxRight ; i++){
            if(board[row - i][col + i]){
                return false;
            }
        }

        return true;

    }

    private static List<String> display(boolean board[][]){

        List<String> res = new ArrayList<>();

        for(boolean row[] : board){
            String temp = "";
            
            for(boolean ele : row){
                if(ele){
                    temp += 'Q';
                }else{
                    temp += '.';
                }
            }
            res.add(temp);
        }

        return res;
    }

    // Sudoku Solver
    public static void solveSudoku(char[][] board) {
        sudoku(board);
    }

    private static boolean sudoku(char[][] board){
        int n = board.length;
        int row = -1;
        int col = -1;

        boolean emptyLeft = true;

        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < n ; j++){
               if(board[i][j] == '.'){
                    row = i;
                    col = j;
                    emptyLeft = false;
                    break;
                }
            }
        }

        if(emptyLeft == true) return true; // Sudoku Solved

        // Backtrack

        for(int num = 1 ; num <= 9 ; num++){
            char ch = (char) ('0' + num);
            if(isSafe(board , row , col , ch)){
                board[row][col] = ch;
                if(sudoku(board)){
                    return true;
                }else{
                    board[row][col] = '.';
                }
            }
        }

        return false;
    }

    private static boolean isSafe(char[][] board , int row , int col , char num){
        // Check Row
        for(int c = 0 ; c < board.length ; c++){
            if(board[row][c] == num ) return false;
        }

        // Check Col
        for(char nums[] : board){
            if(nums[col] == num){
                return false;
            }
        }

        // Check Grid
        int sqrt = (int) Math.sqrt(board.length);
        int rowStart = row - (row % sqrt);
        int colStart = col - (col % sqrt);


        for(int r = rowStart ; r < rowStart + sqrt ; r++){
            for(int c = colStart ; c < colStart + sqrt ; c++){
                if(board[r][c] == num ){
                    return false;
                }
            }
        }

        return true;
    } 

    public static void main(String[] args) {
        // print1toN(1, 4);
        // System.out.println();
        // print1toNbT(4, 4);if
        // System.out.println();
        // printNto1(4, 4);
        // System.out.println();
        // printNto1bT(1, 4);


        // reverseNum_B(1234);
        int revN = reverseNum_A(1234);
        System.out.println("The Reversed Number is : " + revN);

        int arr[] = {1,2,3,4};
        reverseArray(0, arr, 4);

        System.out.println(Arrays.toString(arr));

        System.out.println("The given String is Palindrome : " + checkPalindrome(0, "MADAM", 5));

        // Generate Parenthesis
        int nParanthesis = 3;
        List<String> paranthesis = generateParenthesis(nParanthesis );
        System.out.println("\nThe paranthesis of " + nParanthesis  + " were : " + paranthesis+  "\n");

        // Generate Binary Strings
        int nBinary = 3;
        List<String> binaryStrings = generateBinaryStrings(nBinary);
        System.out.println("\nThe binary strings of length " + nBinary + " with no consecutive 1's are: " + binaryStrings + "\n");

        // Subsequences in the Array
        int[] subSeq = {3,2,1};
        ArrayList<Integer> dsSeq = new ArrayList<>();
        printSubSequences(subSeq, dsSeq, 0);

        // Subsequences in the Array sum up to give K 
        System.out.println("\nArray element sum up to give K");
        ArrayList<Integer> dsSeqk = new ArrayList<>();
        subsequencesSumk(arr, 0, 5, dsSeqk, 0);

        System.out.println("\nPrint only one subSequence sum to k\n");
        oneSubsequencesSumk(arr, 0, 5, dsSeqk, 0);

        
        ArrayList<Integer> cntSeq = new ArrayList<>();
        int count = cntSubsequencesSumk(subSeq,  0, 5, cntSeq , 0);
        System.out.println("\nCount of subsequences sum upto k : " + count );

        // Combinations
        int nCombine = 4;
        int kCombine = 2;
        List<List<Integer>> combs = combine(nCombine, kCombine);
        System.out.println("\nCombinations of " + nCombine + " choose " + kCombine + " : " + combs);


        // CombinationSum - 1
        int[] candidatesI = {2, 3, 6, 7 };
        int targetI = 7;
        List<List<Integer>> resCombI = combinationSumI(candidatesI, targetI);
        System.out.println("\n(Combination Sum - I) The candidates statisfies the Combination sum were : "+resCombI);

        // CombinationSum - 2
        int [] candiateII = {10,1,2,7,6,1,5};
        int targetII = 8;
        List<List<Integer>> resCombII = combinationSumII(candiateII, targetII);
        System.out.println("\n(Combination Sum - II) The candidates statisfies the Combination sum by picking only once : "+resCombII);

        // CombinationSum - 3
        int k = 3;
        int n = 7;
        List<List<Integer>> resCombIII = combinationSum3(k, n);
        System.out.println("\n(Combination Sum - III) The candidates statisfies the Combination sum with k elements and sum n were : "+resCombIII);

        // CombinationSum - 4
        int [] candiateIV = {1,2,3};
        int targetIV = 4;
        System.out.println("\n(Combination Sum - IV) The candidates statisfies the Combination sum were : "+combinationSum4(candiateIV , targetIV));


        // Subsets - II
        int[] nums = {1,2,2};
        List<List<Integer>> resSubsets = subsetsWithDup(nums);
        System.out.println("\nThe subsets of the array with duplicates were : " + resSubsets);
        
        // Letter Combination Mobile phone problem
        List<String> letter_combinations = letterCombinations("23");
        System.out.println("\nThe Phone number combinations were : "+letter_combinations);

        // permutations in a Array
        // Brute force
        List<List<Integer>> permutationsI = permuteI(new int[]{1,2,3});
        System.out.println("\nThe permutations of the array were : " + permutationsI);

        // Optimal
        List<List<Integer>> permutationsII = permuteII(new int[]{1,2,3} , 0);
        System.out.println("\nThe permutations of the array were_Optimal : " + permutationsII);


        // Palindromic partions of a String
        List<List<String>> partitions = palindromicPartition("aabb");
        System.out.println("\nThe palindromic partitions of the String were : " + partitions);

        // Word Break partition
        boolean isWordbreak = wordBreak("mypenmy" , new String[]{"my","pen"});
        System.out.println("\nIs the word break possible with the given dictionary : " + isWordbreak);

        // Decode Ways
        int ways = countWays("123");
        System.out.println("\nThe number of ways to decode the digits is: " + ways);

        // Number of Dice Rolls With Target Sum
        int nDice = 2, faces = 6, targetSum = 7;
        int numWays = numRollsToTarget(nDice, faces, targetSum);
        System.out.println("\nNumber of ways to roll " + nDice + " dice with " + faces + " faces to get sum " + targetSum + " is: " + numWays);

        // Permutations - II (Unique permutations)
        // int[] numsDup = {1, 1, 2};
        int[] numsDup = {1, 2, 3 ,4};
        // List<List<Integer>> uniquePermsV = permuteUniqueV(numsDup);
        // System.out.println("\nUnique permutations using visited array: " + uniquePermsV);
        List<List<Integer>> uniquePermsS = permuteUniqueS(numsDup);
        System.out.println("Unique permutations using set: " + uniquePermsS);

        // Rat in a Maze
        int[][] maze = {
            {1, 0, 0, 0},
            {1, 1, 0, 1},
            {0, 1, 0, 0},
            {1, 1, 1, 1}
        };
        ArrayList<String> mazePaths = ratInMaze(maze);
        System.out.println("\nAll possible paths for Rat in a Maze: " + mazePaths);

        // N Queens
        int nQueens = 4;
        List<List<String>> nQueensSolutions = solveNQueens(nQueens);
        System.out.println("\nAll possible solutions for " + nQueens + "-Queens problem:");
        for (List<String> sol : nQueensSolutions) {
            for (String row : sol) {
            System.out.println(row);
            }
            System.out.println();
        }

        // Word Search in a 2D matrix
        char[][] board = {
            {'A','B','C','E'},
            {'S','F','C','S'},
            {'A','D','E','E'}
        };
        String word = "ABCCED";
        boolean found = exist(board, word);
        System.out.println("\nIs the word '" + word + "' present in the board? " + found);

        // Sudoku Solver
        char[][] boardToSolve = {
            {'5','3','.','.','7','.','.','.','.'},
            {'6','.','.','1','9','5','.','.','.'},
            {'.','9','8','.','.','.','.','6','.'},
            {'8','.','.','.','6','.','.','.','3'},
            {'4','.','.','8','.','3','.','.','1'},
            {'7','.','.','.','2','.','.','.','6'},
            {'.','6','.','.','.','.','2','8','.'},
            {'.','.','.','4','1','9','.','.','5'},
            {'.','.','.','.','8','.','.','7','9'}
        };

        System.out.println("Sudoku puzzle:");
        for (char[] row : boardToSolve) {
            System.out.println(String.valueOf(row));
        }

        solveSudoku(boardToSolve);

        System.out.println("\nSolved Sudoku:");
        int n2 = boardToSolve.length;
        // Print top border
        System.out.println("+-------+-------+-------+");
        for (int r = 0; r < n2; r++) {
            for (int c = 0; c < n2; c++) {
            if (c % 3 == 0) System.out.print("| ");
            System.out.print(boardToSolve[r][c] + " ");
            }
            System.out.println("|");
            if ((r + 1) % 3 == 0) System.out.println("+-------+-------+-------+");
        }

    }

}
