import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CheatSheet5 {
    // Ugly number
    public static boolean isUgly(int n) {
        if(n <= 0) return false;
        int ugly[] = {2,3,5}; 

        for(int num : ugly){
            while(n % num == 0){
                n /= num;
            }
        }

        return(n == 1);
    }

    // Reshape the Matrix
    public static int[][] matrixReshape(int[][] mat, int r, int c) {
        int n = mat.length ;
        int m = mat[0].length;
        int [][] ans = new int[r][c];

        if(n * m != r * c) return mat;


        int nRow = 0 , nCol = 0;

        for(int i = 0 ; i < n; i++){
            for(int j = 0; j < m; j++){
                ans[nRow][nCol] = mat[i][j];
                nCol++;
                if(nCol == c){
                    nRow += 1;
                    nCol = 0;
                }
            }
        }

        /*
        for(int i = 0; i < n ;i++){
            for(int j = 0; j < m ; j++){
                int idx = i * m + j;
                ans[idx / c][idx % c] = mat[i][j];
            }
        }
        */

        return ans;
    }

    // Minimum Cost to Move Chips to The Same Position
    public static int minCostToMoveChips(int[] position) {
        int n = position.length;
        int oddCnt = 0;

        for(int i = 0 ; i < n ; i++){
            if(position[i] % 2 == 1) oddCnt++;
        }

        int evenCnt = n - oddCnt;
        return Math.min(oddCnt , evenCnt);        
    }

    // Sort Array By Parity
    public static int[] sortArrayByParity(int[] nums) {
        int idx = 0;

        for(int i = 0 ; i < nums.length ; i++){
            if(nums[i] % 2 == 0){
                int temp = nums[i];
                nums[i] = nums[idx];
                nums[idx] = temp;
                idx++;
            }
        }

        return nums;
    }
    
    // Maximum Population Year
    public int maximumPopulation(int[][] logs) {
        int year[] = new int[2051];

        for(int arr[] : logs){
            int birth = arr[0];
            int death = arr[1];

            year[birth]++;
            year[death]--;
        }
        int maxPopulation = year[1950] , maxYear = 1950;

        for(int i = 1951 ; i < 2051 ; i++){
            year[i] += year[i - 1];
            
            if(year[i] > maxPopulation){
                maxPopulation = year[i];
                maxYear = i;
            }
        }

        return maxYear;
    }

    // Add to Array-Form of Integer
    public static  List<Integer> addToArrayForm(int[] num, int k) {
        List<Integer> ans = new ArrayList<>();

        int carry = 0;
        int i = num.length - 1;

        while(i >= 0 || k > 0 || carry > 0){
            int n = k % 10;
            int digit = (i >= 0) ? num[i] : 0;
            int sum = n + digit + carry;
                
            ans.add(sum % 10);
            
            k /= 10;
            carry = sum / 10;
            i--;
        }

        Collections.reverse(ans);
        return ans;
    }

    // Fair Candy Swap
    public static  int[] fairCandySwap(int[] aliceSizes, int[] bobSizes) {
        // sumA - x + y = sumB - y + x
        // 2(x - y) = sumA - sumB
        // x - y = (sumA - sumB) / 2

        int sumA = 0 , sumB = 0;

        for(int x : aliceSizes) sumA += x;
        for(int y : bobSizes) sumB += y;

        int diff = (sumA - sumB) / 2;

        Set<Integer> bobSet = new HashSet<>();

        // y = x - diff

        for(int y : bobSizes){
            bobSet.add(y);
        }

        for(int x : aliceSizes){
            int y = x - diff;
            if(bobSet.contains(y)){
                return new int[]{x , y};
            }
        }

        return new int[0];
    }

    // Find the Duplicate Number
    public static int findDuplicate(int[] nums) {
        int slow = nums[0];
        int fast = nums[0];

        // Traversal - 1
        do{
            slow = nums[slow];
            fast = nums[nums[fast]];
        }while(slow != fast);

        // Traversal - 2
        fast = nums[0];
        while(slow != fast){
            slow = nums[slow];
            fast = nums[fast];
        }

        return slow;
    }

    // Squares of a Sorted Array
    public static int[] sortedSquares(int[] nums) {
        int n = nums.length;

        int l = 0 , r = n - 1;

        
        int idx = n - 1;

        int res[] = new int[n];

        while(l <= r){
            int left = nums[l] * nums[l];
            int right = nums[r] * nums[r];

            if(left > right){
                res[idx] = left;
                l++;
            }else{
                res[idx] = right;
                r--;
            }

            idx--;
        }

        return res;
    }

    // Minimum Absolute Difference
    public static  List<List<Integer>> minimumAbsDifference(int[] arr) {
        int n = arr.length;
        List<List<Integer>> ans = new ArrayList<>();

        Arrays.sort(arr);
        int minDiff = Integer.MAX_VALUE;

        for(int i = 0 ; i < n - 1 ; i++){
            minDiff = Math.min(minDiff , arr[i + 1] - arr[i]);
        }

        for(int i = 0 ; i < n - 1 ; i++){
            if(arr[i] + minDiff == arr[i + 1]){
                ans.add(Arrays.asList(arr[i] , arr[i + 1]));
            }
        }

        return ans;
    }

    // Sort Integers by The Number of 1 Bits
    public static int[] sortByBits(int[] arr) {
        int n = arr.length;
        Integer boxed[] = new Integer[n];

        for(int i = 0 ; i < n ;i++){
            boxed[i] = arr[i];
        }

        Arrays.sort(boxed , (a , b) -> {
            int countA = Integer.bitCount(a);
            int countB = Integer.bitCount(b);

            if(countA != countB){
                return countA - countB;
            }
            
            return a - b;
        });

        for(int i = 0 ; i < n ;i++){
            arr[i] = boxed[i];
        }

        return arr;
    }

    public static int[] sortByBits_Bucket(int[] nums) {
        int n = nums.length;

        List<List<Integer>> bucket = new ArrayList<>();

        // 2 ^ 13 = 8,192 and 2 ^ 14 = 16,384 - no of bits
        for(int i = 0 ; i <= 14 ; i++){
            bucket.add(new ArrayList<>());
        }

        for(int i = 0 ; i < n ;i++){
            int bits = countBits(nums[i]);
            bucket.get(bits).add(nums[i]);
        }

        int idx = 0;

        for(int i = 0 ; i <= 14 ; i++){
            Collections.sort(bucket.get(i));
            for(int num : bucket.get(i)){
                nums[idx] = num;
                idx++;
            }
        }

        return nums;
    }

    private static int countBits(int x) {
        int cnt = 0;
        while (x != 0) {
            cnt += (x & 1);
            x >>= 1;
        }
        return cnt;
    }

    // Find All Numbers Disappeared in an Array
    public static List<Integer> findDisappearedNumbers(int[] nums) {
        int n = nums.length;

        for(int i = 0 ; i < n ; i++){
            int idx = Math.abs(nums[i]) - 1;
            if(nums[idx] < 0) continue;
            
            nums[idx] *= -1;
        }

        List<Integer> ans = new ArrayList<>();

        for(int i = 0 ; i < n ; i++){
            if(nums[i] > 0) ans.add(i + 1);
        }

        return ans;
    }
    
    // Set Mismatch
    public static int[] findErrorNums(int[] nums) {
        int n = nums.length;

        int[] ans = new int[2];

        for(int i = 0; i < n ; i++){
            int idx = Math.abs(nums[i]) - 1;

            if(nums[idx] < 0){
                // already visited → duplicate found
                ans[0] = Math.abs(nums[i]);
            }
            else nums[idx] = -nums[idx];
        }

        // Find missing number
        for(int i = 0 ; i < n ; i++){
            if(nums[i] > 0) ans[1] = i + 1;
        }

        return ans;
    }

    // Find Missing and Repeated Values
    public static int[] findMissingAndRepeatedValues(int[][] grid) {
        int n = grid.length;

        int ans[] = new int[2];

        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < n ; j++){
                int idx = Math.abs(grid[i][j]) - 1;

                int row = idx / n;
                int col = idx % n;

                if(grid[row][col] < 0){
                    ans[0] = Math.abs(grid[i][j]);
                }
                else{
                    grid[row][col] *= -1;
                }
            }
        }

        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < n ; j++){
                if(grid[i][j] > 0){
                    int idx = i * n + j;
                    ans[1] = idx + 1;
                    break;
                }
            }
        }


        return ans;
    }

    // Can Make Arithmetic Progression From Sequence
    public static boolean canMakeArithmeticProgression(int[] arr) {
        int n = arr.length;
        int mini = Integer.MAX_VALUE;
        int maxi = Integer.MIN_VALUE;

        for(int num : arr){
            mini = Math.min(mini , num);
            maxi = Math.max(maxi , num);
        }

        /*
        The last term formula:
            last = a + (n - 1) * d
        
            first term = a -> mini
            last term = maxi

            maxi = mini + (n - 1) * d

            d = maxi - mini / (n - 1)

            check : (maxi - mini) % (n - 1) to ensure AP is possible with Integer
                arr = [1,2,4]
                min = 1, max = 4, n = 3
                d = (4 - 1) / (3 - 1) = 3/2 = 1.5 ❌

            every element must be exactly:
                min + i*d   for i = 0 to n-1

            Since If it is an arithmetic progression, then the values must be exactly:
                min , min + d , min + 2d , min + 3d ,... min + (n - 1)d;

        */

        if((maxi - mini) % (n - 1) != 0) return false;

        int diff = (maxi - mini) / (n - 1);

        Set<Integer> st = new HashSet<>();

        for(int num : arr){
            st.add(num);
        }


        for(int i = 0 ; i < n ; i++){
            int expected = mini + i * diff;

            if(!st.contains(expected)) return false;
        }

        return true;
    }

    // To Lower Case
    public static String toLowerCase(String str) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            if (ch >= 'A' && ch <= 'Z') {
                sb.append((char)(ch + 32));
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    // Decrypt String from Alphabet to Integer Mapping
    public static String freqAlphabets(String s) {
        int n = s.length();

        int i = n - 1;

        StringBuilder sb = new StringBuilder();

        while(i >= 0){
            if(s.charAt(i) == '#'){
                int num = (s.charAt(i - 2) - '0') * 10 + (s.charAt(i - 1) - '0');
                char ch = (char) ('a' + num - 1);

                sb.append(ch);

                i -= 3;
            }
            else{
                int num = (s.charAt(i) - '0');
                char ch = (char)('a' + num - 1);

                sb.append(ch);

                i--;
            }
        }

        return sb.reverse().toString();
    }

    // Number of Strings That Appear as Substrings in Word
    public static int numOfStrings(String[] patterns, String word) {
        int cnt = 0;
        for(String w : patterns){
            if(isSubString(word , w)) cnt++;
        }

        return cnt;
    }

    private static boolean isSubString(String word , String pat){
        int n = word.length();
        int m = pat.length();

        for(int i = 0 ; i <= n - m ; i++){
            int j = 0;

            while(j < m && word.charAt(i + j) == pat.charAt(j)){
                j++;
            }

            if(j == m) return true;
        }

        return false;
    }

    // Excel Sheet Column Title
    public static String convertToTitle(int columnNumber) {
        StringBuilder sb = new StringBuilder();

        while(columnNumber > 0){
            columnNumber--;

            int rem = columnNumber % 26;
            sb.append((char)('A' + rem));

            columnNumber /= 26;
        }

        return sb.reverse().toString();
    }
    
    // Long Pressed Name
    public static boolean isLongPressedName(String name, String typed) {
        int n = name.length();
        int m = typed.length();

        int i = 0 , j = 0;

        while(j < m){
            if(i < n && name.charAt(i) == typed.charAt(j)){
                i++;
                j++;
            }
            else if(j > 0 && typed.charAt(j - 1) == typed.charAt(j)){
                j++;
            }
            else return false;
        }

        return i == n;
    }

    // Check if Binary String Has at Most One Segment of Ones
    public static boolean checkOnesSegment(String s) {
        boolean seenZero = false;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == '0') {
                seenZero = true;
            } 
            else { // ch == '1'
                if (seenZero) {
                    return false;  // found '1' after a '0'
                }
            }
        }

        return true;
    }

    // Split Two Strings to Make Palindrome
    public static boolean checkPalindromeFormation(String a, String b) {
       return check(a , b) || check(b , a);
    }

    private static boolean check(String a , String b){
        int n = b.length();
        int i = 0 , j = n - 1;

        while(i < j && a.charAt(i) == b.charAt(j)){
            i++;
            j--;
        }

        // Ensure Crossed - means palindrome formed - else check empty string + palindrome of both
        return isPalindrome(a , i , j) || isPalindrome(b , i , j); 
    }

    private static boolean isPalindrome(String a , int i , int j){
        while(i < j){
            if(a.charAt(i) != a.charAt(j)) return false;
            i++;
            j--;
        }

        return true;
    }

    // Number of Ways to Split a String
    public static int numWaysBruteForce(String s) {
        int n = s.length();
        int ways = 0;

        // first cut at i
        for (int i = 1; i <= n - 2; i++) {

            // second cut at j
            for (int j = i + 1; j <= n - 1; j++) {

                int ones1 = 0, ones2 = 0, ones3 = 0;

                // s1 = [0 .. i-1]
                for (int k = 0; k < i; k++) {
                    if (s.charAt(k) == '1') ones1++;
                }

                // s2 = [i .. j-1]
                for (int k = i; k < j; k++) {
                    if (s.charAt(k) == '1') ones2++;
                }

                // s3 = [j .. n-1]
                for (int k = j; k < n; k++) {
                    if (s.charAt(k) == '1') ones3++;
                }

                if (ones1 == ones2 && ones2 == ones3) {
                    ways++;
                }
            }
        }

        return ways;
    }
    
    public static int numWays(String s) {
        int MOD = 1_000_000_007;
        int n = s.length();

        int ones = 0;

        for(int i = 0 ; i < n ; i++){
            if(s.charAt(i) == '1') ones++;
        }

        if(ones % 3 != 0) return 0;


        if(ones == 0){
            return (int)(((long)(n - 1) * (n - 2)) / 2 % MOD);
        }

        int onesPerPart = ones / 3;

        int cnt1 = 0 , cnt2 = 0;
        int curr = 0;

        for(int i = 0 ; i < n ; i++){
            if(s.charAt(i) == '1'){
                curr++;
            }

            if(curr == onesPerPart) cnt1++;
            else if(curr == 2 * onesPerPart) cnt2++;
        }

        return (int)((long) cnt1 * cnt2 % MOD);
        
    }
    
    // Sentence Similarity III
    public static boolean areSentencesSimilar_DQ(String sentence1, String sentence2) {
       ArrayDeque<String> dq1 = new ArrayDeque<>(Arrays.asList(sentence1.split(" ")));
       ArrayDeque<String> dq2 = new ArrayDeque<>(Arrays.asList(sentence2.split(" ")));


        while(!dq1.isEmpty() && !dq2.isEmpty()){
            if(dq1.peek().equals(dq2.peek())){
                dq1.poll();
                dq2.poll();
            }else{
                break;
            }
        }

        while(!dq1.isEmpty() && !dq2.isEmpty()){
            if(dq1.peekLast().equals(dq2.peekLast())){
                dq1.pollLast();
                dq2.pollLast();
            }else{
                break;
            }
        }

        return (dq1.isEmpty() || dq2.isEmpty());

    }

    public static boolean areSentencesSimilar_TP(String sentence1, String sentence2) {
        String[] s1 = sentence1.split(" ");
        String[] s2 = sentence2.split(" ");


        if(s2.length > s1.length){
            return areSentencesSimilar_TP(sentence2 , sentence1);
        }

        int l = 0;
        int r1 = s1.length - 1;
        int r2 = s2.length - 1;

        while(l < s2.length && s2[l].equals(s1[l])){
            l++;
        }
        

        while(r2 >= l && s1[r1].equals(s2[r2])){
            r1--;
            r2--;
        }

        return (r2 < l);
    }

    // Repeated String Match
    public static int repeatedStringMatch_Br(String a, String b) {
        String og = a;
        int cnt = 1;

        while(a.length() < b.length()){
            a += og;
            cnt++;
        }

        if(a.contains(b)) return cnt;

        a += og;
        if(a.contains(b)) return cnt + 1;

        a += og;
        if(a.contains(b)) return cnt + 2;

        return -1;
    } 
    
    // Repeated Substring Pattern
    public static boolean repeatedSubstringPattern_Br(String s) {
        /*
            Intuition:
            If a string is made by repeating a smaller substring, that substring
            must be a PREFIX of the string (repetition always starts at index 0).

            We only need to try prefix lengths up to n/2 because a substring longer
            than half the string cannot repeat more than once.

            For each valid prefix length (that divides total length),
            check block by block whether repeating the prefix recreates the string.

            If any prefix works, return true; otherwise false.
        */

        int n = s.length();

        for(int len = 1 ; len <= n / 2 ; len++){
            
            if(n % len != 0) continue;

            String prefix = s.substring(0 , len);
            boolean valid = true;

            for(int i = len ; i < n ; i += len){
                if(!s.substring(i , i + len).equals(prefix)){
                    valid = false;
                    break;
                }
            }

            if(valid) return true;
        }

        return false;
    }

    public static boolean repeatedSubstringPattern_O(String s) {
        /*
        Intuition (Doubling + Rotation Trick):

        If a string S is made by repeating a smaller substring, then S is periodic.
        That means rotating S by some non-zero amount (not 0 or full length)
        will produce the same string again.

        Repeated substring ⇔ some rotation (≠ 0, ≠ n) gives the same string.

        All cyclic rotations of S appear as substrings inside (S + S).
        However, S always appears trivially at the start and end of (S + S).

        To avoid these trivial matches, remove the first and last characters:
        (S + S).substring(1, 2*n - 1)

        After trimming, only shifted (non-trivial) rotations remain.
        If S appears here, a repeating pattern exists.

        Example:
        S = "abab"
        Rotations: "baba", "abab" → valid repeat
        (S+S) = "abababab" → trimmed = "bababa" contains "abab"
        */

        String strstr = s + s;

        return strstr.substring(1 , strstr.length() - 1).contains(s);
    }

    




    public static void main(String[] args) {
        // Ugly number
        System.out.println("Is number 6 is ugly ? " + isUgly(6));

        // Reshape the Matrix
        int[][] mat = {{1,2}, {3,4}};
        int[][] reshaped = matrixReshape(mat, 1, 4);
        System.out.println("Matrix reshaped: " + java.util.Arrays.deepToString(reshaped));

        // Minimum Cost to Move Chips
        int[] position = {1, 2, 3};
        System.out.println("Min cost to move chips: " + minCostToMoveChips(position));

        // Sort Array By Parity
        int[] nums = {3, 1, 2, 4};
        System.out.println("Sorted by parity: " + java.util.Arrays.toString(sortArrayByParity(nums)));

        // Maximum Population Year
        CheatSheet5 obj = new CheatSheet5();
        int[][] logs = {{1950, 1961}, {1960, 1971}, {1955, 1965}};
        System.out.println("Maximum population year: " + obj.maximumPopulation(logs));

        // Add to Array-Form of Integer
        int[] num = {2, 1, 5};
        System.out.println("Array form after adding: " + addToArrayForm(num, 806));

        // Fair Candy Swap
        int[] aliceSizes = {1, 1};
        int[] bobSizes = {2, 2};
        System.out.println("Fair candy swap: " + java.util.Arrays.toString(fairCandySwap(aliceSizes, bobSizes)));

        // Find the Duplicate Number
        int[] nums1 = {1, 3, 4, 2, 2};
        System.out.println("Duplicate number: " + findDuplicate(nums1));

        // Squares of a Sorted Array
        int[] sortedArr = {-4, -1, 0, 3, 10};
        System.out.println("Sorted squares: " + java.util.Arrays.toString(sortedSquares(sortedArr)));

        // Minimum Absolute Difference
        int[] arr1 = {4, 2, 1, 3};
        System.out.println("Minimum absolute difference: " + minimumAbsDifference(arr1));

        // Sort Integers by The Number of 1 Bits
        int[] arr2 = {0, 1, 2, 3, 4, 5, 6, 7, 8};
        System.out.println("Sorted by bits: " + java.util.Arrays.toString(sortByBits(arr2)));

        // Find All Numbers Disappeared in an Array
        int[] arr3 = {4, 3, 2, 7, 8, 2, 3, 1};
        System.out.println("Disappeared numbers: " + findDisappearedNumbers(arr3));

        // Set Mismatch
        int[] arr4 = {1, 2, 2, 4};
        System.out.println("Error nums: " + java.util.Arrays.toString(findErrorNums(arr4)));

        // Find Missing and Repeated Values
        int[][] grid = {{9, 1, 7}, {8, 9, 2}, {3, 4, 6}};
        System.out.println("Missing and repeated values: " + java.util.Arrays.toString(findMissingAndRepeatedValues(grid)));

        // Can Make Arithmetic Progression From Sequence
        int[] arr5 = {3, 5, 1};
        System.out.println("Can make arithmetic progression: " + canMakeArithmeticProgression(arr5));

        // To Lower Case
        System.out.println("To lower case: " + toLowerCase("Hello"));

        // Decrypt String from Alphabet to Integer Mapping
        System.out.println("Decrypted string: " + freqAlphabets("10#11#12"));

        // Number of Strings That Appear as Substrings in Word
        String[] patterns = {"ab", "ba", "aaab", "abab", "baa"};
        String word = "aaab";
        System.out.println("Number of strings as substrings: " + numOfStrings(patterns, word));

        // Excel Sheet Column Title
        System.out.println("Excel column title: " + convertToTitle(28));

        // Long Pressed Name
        System.out.println("Is long pressed name: " + isLongPressedName("alex", "aaleex"));

        // Check if Binary String Has at Most One Segment of Ones
        System.out.println("Check ones segment: " + checkOnesSegment("1110"));

        // Split Two Strings to Make Palindrome
        System.out.println("Can form palindrome: " + checkPalindromeFormation("abdef", "fecab"));

        // Number of Ways to Split a String
        System.out.println("Number of ways to split: " + numWays("0110"));

        // Sentence Similarity III - Deque approach
        System.out.println("Sentences similar (DQ): " + areSentencesSimilar_DQ("My name is Haley", "My name is Haley"));

        // Sentence Similarity III - Two Pointer approach
        System.out.println("Sentences similar (TP): " + areSentencesSimilar_TP("Eating is fun", "Eating is fun"));

    }
}
