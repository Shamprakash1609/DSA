package Strings;

import java.util.*;

public class Strings {

    //Function to arrange all letters of a string in lexicographical 
    //order using Counting Sort.
    public static String countSort(String arr)
    {
        // code here
        
        int cnt[] = new int[26];
        
        char[] chArr = arr.toCharArray();
        
        for(int i = 0 ; i< chArr.length ; i++){
            cnt[chArr[i] - 'a']++;
        }
        
        StringBuilder ans = new StringBuilder();
        
        for(int i = 0 ; i< 26 ; i++){
            while(cnt[i] > 0){
                ans.append((char)( i + 'a'));
                cnt[i]--;
            }
        }
        
        return ans.toString();
    }

    public boolean checkValidParanthesis(String s) {
        int n = s.length();
        int min = 0;
        int max = 0;
        for(int i = 0 ; i<n ; i++){
            char ch = s.charAt(i);

            if(ch == '('){
                min += 1;
                max += 1;
            }
            else if(ch == ')'){
                min -= 1;
                max -= 1;
            }
            else{
                min -= 1;
                max += 1;
            }

            if(min < 0) min = 0;
            if(max < 0 ) return false;
        }

        return min==0;
    }

    public String removeOuterParentheses_B(String s) {
        int n = s.length();

        Stack<Character> st = new Stack<>();
        StringBuilder ans = new StringBuilder();

        int start = 0;

        for(int i = 0 ; i<n ; i++){
            char ch = s.charAt(i);

            if(ch == '(') st.push(ch);
            else st.pop();

            if(st.isEmpty()){
                ans.append(s.substring(start + 1 , i));
                start = i + 1;
            }
        }

        return ans.toString();
    }

    public String removeOuterParentheses_O(String s) {
        StringBuilder ans = new StringBuilder();
        int count = 0;
    
        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                if (count > 0) ans.append(ch);
                count++;
            } else {
                count--;
                if (count > 0) ans.append(ch);
            }
        }
    
        return ans.toString();
    }

    private int[] findLPS(String patt){
        int n = patt.length();
        int[] lps = new int[n];
        int j = 0;
        int i = 1;

        while(i < n){
            if(patt.charAt(i) == patt.charAt(j)){
                lps[i] = j + 1;
                i++;
                j++;
            }
            else if(j > 0){
                j = lps[j - 1];
            } else { 
                lps[i] = 0;
                i++;
            }
        }

        return lps;
    }

    private boolean KMP(String text , String patt, int[]lps ){
        int n = text.length();
        int m = patt.length();

        int i = 0 , j = 0;

        while( i < n){
            if(text.charAt(i) == patt.charAt(j)){
                i++;
                j++;
            }
            if (j == m) return true;
            if(i < n && text.charAt(i) != patt.charAt(j)){
                if( j > 0){
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }

        return false;
    }

    public boolean rotateString_B(String s, String goal) {
        int n = s.length();
        int m = goal.length();

        if(n != m) return false;

        String text = s + s;
        int[] lps = findLPS(goal);

        return KMP(text, goal, lps);
    }

    public boolean rotateString(String s, String goal) {
        int n = s.length();
        int m = goal.length();

        if(n != m) return false;

        String text = s + s;
    
        return text.contains(goal);

    }

    public boolean isIsomorphic(String s, String t) {

        int n = s.length();
        int m = t.length();

        if(n != m) return false;

        Map<Character,Character> mpp = new HashMap<>();

        for(int i = 0 ; i<n ; i++){
            char og = s.charAt(i);
            char rp = t.charAt(i);

            if(!mpp.containsKey(og)){
                if(!mpp.containsValue(rp)){
                    mpp.put(og , rp);
                }
                else return false;
            }
            else{
                char mappedCh = mpp.get(og);
                if(mappedCh != rp) return false;
            }
        }

        return true;

    }

    public String longestCommonPrefix(String[] strs){
        
        StringBuilder res = new StringBuilder();

        Arrays.sort(strs);

        char[] first = strs[0].toCharArray();
        char[] last = strs[strs.length - 1].toCharArray();

        for(int i = 0 ; i < first.length ; i++){
            if(first[i] != last[i]) break;
            res.append(first[i]);
        }

        return res.toString();

    }

    public String largestOddNumber(String num) {
        int n = num.length();

        for(int i = n-1 ; i >= 0 ; i--){
            int digit = num.charAt(i) - '0';

            if(digit % 2 == 1) return num.substring(0 , i+1);
        }
        return "";
    }

    public String reverseWords(String s) {

        String[] arr = s.trim().split("\\s+");

        StringBuilder res = new StringBuilder();

        for(int i = arr.length-1 ; i>=0 ; i--){
            res.append(arr[i]);
            if(i != 0) res.append(" ");
        }

        return res.toString();
    }

    public int romanToInt_B(String s) {
        int n = s.length();

        Map<Character , Integer > roman = new HashMap<>();

        roman.put('I' , 1);
        roman.put('V' , 5);
        roman.put('X' , 10);
        roman.put('L' , 50);
        roman.put('C' , 100);
        roman.put('D' , 500);
        roman.put('M' , 1000);

        char[] arr = s.toCharArray();

        int sum = 0;

        for(int i = 0 ; i < n-1 ; i++){
            int curr = roman.get(s.charAt(i));
            int next = roman.get(s.charAt(i + 1));

            if(curr < next){
                sum -= curr;
            }else{
                sum += curr;
            }
        }

        sum += roman.get(s.charAt(n-1));

        return sum;

    }

    public int myAtoi(String s) {
        if(s==null) return 0;

        s=s.trim();

        if(s.length()==0) return 0;

        int sign = 1;
        long ans = 0;

        if(s.charAt(0) == '-') sign = -1;

        int max = Integer.MAX_VALUE ,min = Integer.MIN_VALUE;

        int i = (s.charAt(0) == '+' || s.charAt(0) == '-' ) ? 1 : 0;

        while(i < s.length()){
            if(s.charAt(i) == ' ' || !Character.isDigit(s.charAt(i))) break;

            char ch = s.charAt(i);

            ans = ans*10 +  (ch - '0');

            if(sign == -1 && -1*ans < min) return min;
            if(sign == 1 && ans > max) return max;

            i++;
        }

        return (int)(sign*ans);

    }

    public String longestPalindrome(String s) {
        
        if(s.length() <= 1) return s;

        String LPS = "";

        // Odd length
        for(int i = 1 ; i< s.length() ; i++){
            // Odd length
            int low = i;
            int high = i;

            while(s.charAt(low) == s.charAt(high)){
                low--;
                high++;

                if(low == -1 || high == s.length()){
                    break;
                }
            }

            String palindrome = s.substring(low+1 , high);
            if(palindrome.length() > LPS.length()) LPS = palindrome;

            // Even length
            low = i - 1;
            high = i;
            while(s.charAt(low) == s.charAt(high)){
                low--;
                high++;

                if(low == -1 || high == s.length()){
                    break;
                }
            }

            palindrome = s.substring(low+1 , high);
            if(palindrome.length() > LPS.length()) LPS = palindrome;

        }

        return LPS;
    }

    public String frequencySort_str(String s) {
        int n = s.length();

        Map<Character , Integer> fmpp = new HashMap<>();

        for(char ch : s.toCharArray()){
            fmpp.put(ch , fmpp.getOrDefault(ch , 0) + 1);
        }

        List<Character> bucket[] = new List[n+1];

        for(char key : fmpp.keySet()){
            int freq = fmpp.get(key);

            if(bucket[freq] == null){
                bucket[freq] = new ArrayList<>();
            }

            bucket[freq].add(key);
        }

        StringBuilder res = new StringBuilder();

        for(int i = bucket.length-1 ; i >= 0 ; i--){
            if(bucket[i] != null){
                for(Character ch : bucket[i]){
                    for(int j = 0 ; j < i ; j++){
                        res.append(ch);
                    }
                }
            }
        }

        return res.toString();

    }

    public int[] frequencySort_arr(int[] nums) {
        int n = nums.length;

        Map<Integer,Integer> mpp = new HashMap<>();

        for(int num : nums){
            mpp.put(num ,mpp.getOrDefault(num , 0) + 1);
        }

        
       List<Integer> bucket[] = new List[n + 1];

       for(int key : mpp.keySet()){
            int freq = mpp.get(key);

            if(bucket[freq] == null){
                bucket[freq] = new ArrayList<>();
            }

            bucket[freq].add(key);
       }

       for (List<Integer> bu : bucket) {
            if (bu != null) {
                bu.sort((a, b) -> b - a); // descending order
            }
        }

       ArrayList<Integer> ans = new ArrayList<>();

       for(int i = 0 ; i< bucket.length ; i++){
            if(bucket[i] != null){
                for(Integer num : bucket[i]){
                    for(int j = 0 ; j<i ; j++){
                        ans.add(num);
                    }
                }
            }
        }

        int res[] = new int[n];

        for(int k = 0 ; k< ans.size() ; k++){
            res[k] = ans.get(k);
        }


        return res;

    }


    public static void main(String[] args) {

        // Lexicogrphical sortingm
        String input = "programming";
        System.out.println("Original String: " + input);
        String sortedString = countSort(input);
        System.out.println("Sorted String: " + sortedString);

        // Check valid parentheses
        String parenthesesInput = "(*))";
        System.out.println("Input: " + parenthesesInput);
        Strings obj = new Strings();
        boolean isValid = obj.checkValidParanthesis(parenthesesInput);
        System.out.println("Is valid parentheses: " + isValid);

        // Remove outermost parentheses
        String parenthesesToRemove = "(()())(())";
        String result_O = obj.removeOuterParentheses_O(parenthesesToRemove);
        System.out.println("Afer removing outermost parantheses " + result_O);

        // Rotate string
        String s = "abcde";
        String goal = "cdeab";
        boolean canRotate = obj.rotateString_B(s, goal);
        System.out.println("Can \"" + s + "\" be rotated to \"" + goal + "\": " + canRotate);

        // Check if two strings are isomorphic
        String str1 = "egg";
        String str2 = "add";
        boolean isIsomorphic = obj.isIsomorphic(str1, str2);
        System.out.println("Are \"" + str1 + "\" and \"" + str2 + "\" isomorphic: " + isIsomorphic);

        // Find longest common prefix
        String[] strs = {"flower", "flow", "flight"};
        String lcp = obj.longestCommonPrefix(strs);
        System.out.println("Longest Common Prefix: " + lcp);

        // Find largest odd number
        String num = "573462";
        String largestOdd = obj.largestOddNumber(num);
        System.out.println("Largest odd number in \"" + num + "\": " + largestOdd);

        // Convert Roman numeral to integer
        String roman = "MCMXCIV";
        // String roman = "IM";

        int integerValue = obj.romanToInt_B(roman);
        System.out.println("Roman numeral \"" + roman + "\" as integer: " + integerValue);

        // Convert string to integer
        String atoiInput = "   -42";
        int atoiResult = obj.myAtoi(atoiInput);
        System.out.println("String \"" + atoiInput + "\" converted to integer: " + atoiResult);

        // Find longest palindromic substring
        String palindromeInput = "ABRBADAADAB";
        String longestPalindrome = obj.longestPalindrome(palindromeInput);
        System.out.println("Longest Palindromic Substring in \"" + palindromeInput + "\": " + longestPalindrome);

    


    }
    
}
































































































































/* import java.util.LinkedHashSet;
import java.util.Set;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        String str = "hello";
        char[] chArr = str.toCharArray();
        for (int i = chArr.length-1 ; i >= 0 ; i--){
            System.out.print(chArr[i]);
        }
        System.out.println(" ");
        // approach 2
        for(int i = str.length()-1 ; i >= 0 ; i--){
            System.out.print(str.charAt(i));
        }
        System.out.println(" ");
        // approach 3
        StringBuffer sb = new StringBuffer(str);
        System.out.print(sb.reverse());

        System.out.println(" ");

        // approach 4
        StringBuilder sbuilder = new StringBuilder(str);
        System.out.print(sbuilder.reverse());

        String strSp = "Sham$tark17a@Kingp!n";

        System.out.println(" ");

        String plainStr = strSp.replaceAll("[^a-zA-z0-9]" , "");
        System.out.println(plainStr);

        String strDup = "programming";

        // Approach 1
        StringBuilder sb1 = new StringBuilder();
        strDup.chars().distinct().forEach(c -> sb1.append((char)c));
        System.out.println("sb1: "+sb1);

        // Approach 2
        StringBuilder sb2 = new StringBuilder();
        for(int i=0  ; i < strDup.length() ; i++){
            char ch = strDup.charAt(i);
            int idx = strDup.indexOf(ch , i+1);
            if( idx == -1){
                sb2.append(ch);
            }

        }
        System.out.println("sb2 "+ sb2);

        // approach 3
        char[] arr =  strDup.toCharArray();
        StringBuilder sb3 = new StringBuilder();
        for(int i=0 ; i < arr.length ; i++){
            boolean flag = false;
            for (int j= i+1 ; j < arr.length ; j++){
                if( arr[i] == arr[j]){
                    flag = true;
                    break;
                }
            }

            if(!flag){
                sb3.append(arr[i]);
            }
        }
        System.out.println("sb3: "+ sb3);

        // approach 4
        StringBuilder sb4 = new StringBuilder();
        Set<Character> set = new LinkedHashSet();
        for(int i=0 ; i < strDup.length() ; i++){
            set.add(strDup.charAt(i));
        }
        for(Character c : set){
            sb4.append(c);
        }
        System.out.println("sb4: "+ sb4);


        ///////////////////////////////////////SORTING ALPHABETICAL ORDER \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

    }
} */


