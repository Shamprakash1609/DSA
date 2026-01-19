import java.util.*;
import java.util.Stack;

public class STProblems{

    // Check for Balanced Paranthesis
    public static boolean isValid(String s) {
        int n = s.length();

        if(n % 2 == 1)return false; 

        Stack<Character> st = new Stack<>();

        for(int i = 0 ; i < n; i++){
            char ch = s.charAt(i);

            if( ch == '(' || ch == '[' || ch == '{' ){
                st.push(ch);
            }

            else{
                if(st.isEmpty()) return false;
                char stCh = st.pop();

                if((ch == ')' && stCh == '(') || (ch == ']' && stCh == '[') || (ch == '}' && stCh == '{')){
                    continue;
                }
                else return false;
            }
        }

        return st.isEmpty();
    }
    
    // Next Greater Element
    public static  int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;

        System.out.println("nums1: " + Arrays.toString(nums1));
        System.out.println("nums2: " + Arrays.toString(nums2));

        Stack<Integer> st = new Stack<>();

        Map<Integer , Integer> mpp = new HashMap<>();

        for(int i = m-1 ; i >= 0 ; i--){
            int ele = nums2[i];

            if(st.isEmpty()){
                mpp.put(ele , -1);
                st.push(ele);
                continue;
            }

            if(!st.isEmpty()){
                if(st.peek() > ele){
                    mpp.put(ele , st.peek());
                    st.push(ele);
                    continue;
                }
            }

            while(!st.isEmpty() && ele >= st.peek()){
                st.pop();
            }

            if(st.isEmpty()){
                mpp.put(ele , -1);
            }
            else{
                mpp.put(ele , st.peek());
            }

            st.push(ele);
            
        }

        int ans[] = new int[n];

        for(int i = 0; i < n; i++){
            ans[i] = mpp.getOrDefault(nums1[i], -1);
        }
        
        return ans;
    }

    public static ArrayList<Integer> nextLargestElementI_B(int[] arr){
        // TC : O(N^2) SC: O(N)
        int n = arr.length;

        ArrayList<Integer> ans = new ArrayList<>();

        for(int i = 0 ; i<n ; i++){
            boolean found = false;
            for(int j = i+1 ; j<n ; j++){
                if(arr[j] > arr[i]){
                    ans.add(arr[j]);
                    found = true;
                    break;
                }
            }

            if (!found) {
                ans.add(-1);  
            }
        }

        return ans;
    }

    public static ArrayList<Integer> nextLargestElementI_O(int[] arr){
        // TC:O(N)  SC:O(N)

        int n = arr.length;

        int[] ans = new int[n];
        Stack<Integer> st = new Stack<>();
        
        for(int i = n-1 ; i >= 0 ; i--){
            while(!st.isEmpty() && st.peek() <= arr[i]){
                st.pop();
            }
            if(st.isEmpty()){
                ans[i] = -1;
            }
            else{ // current st element is greater
                ans[i] = st.peek();
            }

            st.push(arr[i]);
        }

    
        ArrayList<Integer> res = new ArrayList<>();
        for (int num : ans) {
            res.add(num);
        }

        return res;
    }
    
    public static ArrayList<Integer> nextLargestElementII_B(int arr[]){
        int n = arr.length;

        int[] nge = new int[n];

        for(int i = 0 ; i < n ; i++){
            boolean found = false;
            for(int j = i+1 ; j < i + n ; j++){
                int ind = j % n;
                if(arr[ind] > arr[i]){
                    nge[i] = arr[ind];
                    found = true;
                    break;
                }

                if(!found) nge[i] = -1;
            }
        }

        ArrayList<Integer> ans = new ArrayList<>();

        for(int num : nge){
            ans.add(num);
        }

        return ans;
    }

    public static ArrayList<Integer> nextLargestElementII_O(int arr[]){
        // TC: O(4N)  SC: O(N)
        int n = arr.length;

        int nge[] = new int[n];

        Stack<Integer> st = new Stack<>();

        for(int i = 2*(n-1) ; i >= 0 ; i--){
            while(!st.isEmpty() && st.peek() <= arr[i % n]){
                st.pop();
            }
            if( i < n){
                if(st.isEmpty()) nge[i] = -1;
                else nge[i] = st.peek();
            }
            st.push(arr[i%n]);
        }

        ArrayList<Integer> ans = new ArrayList<>();

        for(int num : nge){
            ans.add(num);
        }

        return ans;
    }

    public static String removeKdigits(String num, int k) {
        int n = num.length();

        Stack <Character> st = new Stack<>();

        for(int i = 0 ; i < n ; i++){
            while(!st.isEmpty() && k > 0 && 
            (st.peek() - '0') > (num.charAt(i) - '0') ){
                st.pop();
                k--;
            }

            st.push(num.charAt(i));
        }

        while(k > 0){
            st.pop();
            k--;
        }

        if(st.isEmpty()) return "0";

        StringBuilder res = new StringBuilder();

        while(!st.isEmpty()){
            res.append(st.pop());
        }

        res.reverse();

        while(res.length() > 0 && res.charAt(0) == '0'){
            res.deleteCharAt(0);
        }

        if(res.length() == 0) return "0";

        return res.toString();
    }

    // Sliding Window maximum
    public static int[] maxSlidingWindow(int arr[], int k){
        int n = arr.length;

        int[] res = new int[n-k+1];

        int ri = 0;

        Deque<Integer> q = new ArrayDeque<>();

        for(int i = 0 ; i<n ; i++){
            // remove numbers out of range k
            if(!q.isEmpty() && q.peek() == i-k){
                q.poll();
            }

            // remove smaller numbers in k range as they are useless
            while(!q.isEmpty() && arr[q.peekLast()] < arr[i]){
                q.pollLast();
            }

            q.offer(i);
            if(i >= k - 1){
                res[ri++] = arr[q.peek()];
            }

        }

        return res;
    }

    public static ArrayList<Integer> previousSE(int arr[]){
        int n = arr.length;

        int pse[] = new int[n];

        Stack<Integer> st = new Stack<>();

        for(int i = 0 ; i<n ; i++){
            while(!st.isEmpty() && st.peek() >= arr[i]){
                st.pop();
            }
            pse[i] = st.isEmpty() ? -1 : st.peek();

            st.push(arr[i]);
        }

        ArrayList<Integer> res = new ArrayList<>();

        for(int it : pse){
            res.add(it);
        }

        return res;

    }

    public static ArrayList<Integer> nextSE(int arr[]){
        int n = arr.length;

        int nse[] = new int[n];

        Stack<Integer> st = new Stack<>();

        for(int i = n-1 ; i >= 0 ; i--){
            while(!st.isEmpty() && st.peek() >= arr[i]){
                st.pop();
            }
            nse[i] = st.isEmpty() ? -1 : st.peek();

            st.push(arr[i]);
        }

        ArrayList<Integer> res = new ArrayList<>();

        for(int it : nse){
            res.add(it);
        }

        return res;

    }

    //  Largest Rectangle in the Histogram
    public static int[] previousSmallerIndices(int[] heights) {
        int n = heights.length;
        int[] pse = new int[n];
        Stack<Integer> st = new Stack<>();
    
        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && heights[st.peek()] >= heights[i]) {
                st.pop();
            }
            pse[i] = st.isEmpty() ? -1 : st.peek();
            st.push(i);
        }
    
        return pse;
    }
    
    public static int[] nextSmallerIndices(int[] heights) {
        int n = heights.length;
        int[] nse = new int[n];
        Stack<Integer> st = new Stack<>();
    
        for (int i = n - 1; i >= 0; i--) {
            while (!st.isEmpty() && heights[st.peek()] >= heights[i]) {
                st.pop();
            }
            nse[i] = st.isEmpty() ? n : st.peek();
            st.push(i);
        }
    
        return nse;
    }

    public static int largestRectangleArea_B(int heights[]){
        int n = heights.length;

        int[] nse = nextSmallerIndices(heights);
        int[] pse = previousSmallerIndices(heights);

        int maxArea = 0;

        for(int i = 0 ; i < n ; i++){
            int width = nse[i] - pse[i] - 1;
            int height = heights[i];
            maxArea = Math.max(maxArea , height * width);

        }

        return maxArea;

    }

    public static int largestRectangleArea_O(int heights[]){
        int n = heights.length;

        Stack<Integer> st = new Stack<>();

        int maxArea = 0;

        for(int i = 0 ; i < n ; i++){
            while(!st.isEmpty() && heights[st.peek()] > heights[i]){
                int element = st.peek();
                st.pop();
                int nse = i;
                int pse = st.isEmpty() ? -1 : st.peek();

                maxArea = Math.max(maxArea , heights[element] * (nse - pse - 1));
            }

            st.push(i);
        }

        while(!st.isEmpty()){
            int nse = n;

            int element = st.peek();
            st.pop();

            int pse = st.isEmpty() ? -1 : st.peek();

            maxArea = Math.max(maxArea , heights[element] * (nse - pse - 1));

        }

        return maxArea;
    }

    // Maximal Rectangles
    public static int maximalRectangle(char[][] mat) {
        // TC : O(m * n ) + O(n * 2m) SC : O(n*m) + O(n)
        int n = mat.length;
        int m = mat[0].length;

        int newMat[][] = new int[n][m];

        int maxRect = 0;

        for(int j = 0 ; j < m ; j++){
            int sum = 0;
            for(int i = 0 ; i < n ; i++){
                sum += (mat[i][j] - '0');
                if(mat[i][j] == '0') sum = 0;
                newMat[i][j] = sum;
            }
        }

        for(int i = 0 ; i<n ; i++){
            maxRect = Math.max(maxRect , maxHist(newMat[i])); 
        }

        return maxRect;
    }

    public static  int maxHist(int[]  heights){
        int n = heights.length;

        int maxArea = 0;

        Stack<Integer> st = new Stack<>();

        for(int i = 0 ; i < n ; i++){
            while(!st.isEmpty() && heights[st.peek()] > heights[i]){
                int element = st.pop();
                int nse = i;
                int pse = st.isEmpty() ? -1 : st.peek();

                maxArea = Math.max(maxArea , heights[element] * (nse - pse - 1));
            }

            st.push(i);
        }

        while(!st.isEmpty()){
            int element = st.pop();
            int nse = n;
            int pse = st.isEmpty() ? -1 : st.peek();

            maxArea = Math.max(maxArea , heights[element] * (nse - pse - 1));
        }

        return maxArea;
    }

    // Trapping RainWater
    public static int trappingRainWater_B(int[] heights) {
        int n = heights.length;
        if (n == 0) return 0;
    
        // Only suffixMax array needed
        int[] rightMax = new int[n];
        rightMax[n - 1] = heights[n - 1];
    
        // Fill suffixMax array from right to left
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(heights[i], rightMax[i + 1]);
        }
    
        int totalRain = 0;
        int leftMax = heights[0];
    
        // Traverse from left to right, updating leftMax on-the-fly
        for (int i = 0; i < n; i++) {
            leftMax = Math.max(leftMax, heights[i]);
            int waterAtI = Math.min(leftMax, rightMax[i]) - heights[i];
            totalRain += waterAtI;
        }
    
        return totalRain;
    }

    public static int trappingRainWater_O(int[] arr){
        int n = arr.length;

        int lMax = 0, rMax = 0 , totalWater = 0 , l = 0 , r = n-1;

        while( l < r){
            if(arr[l] <= arr[r]){
                if(lMax > arr[l]){
                    totalWater += lMax - arr[l];
                }
                else{ // lMax < arr[l]
                    lMax = arr[l];
                }

                l++;
            }
            else{
                if(rMax > arr[r]){
                    totalWater += rMax - arr[r];
                }
                else{
                    rMax = arr[r];
                }

                r--;
            }
        }

        return totalWater;
    }

    static class LRUCache {

        class Node{
            Node prev , next;
            int key , val;
    
            Node(int _key , int _val){
                key = _key;
                val = _val;
            }
        }
    
    
        Node head = new Node(0 , 0) , tail = new Node(0,0);
        Map<Integer,Node> mpp = new HashMap<>();
        int capacity;
    
    
        public LRUCache(int _capacity) {
            capacity = _capacity;
            head.next = tail;
            tail.prev = head;
        }
        
        public int get(int key) {
            if(mpp.containsKey(key)){
                Node node = mpp.get(key);
                remove(node);
                insert(node);
                return node.val;
            }else return -1;
        }
        
        public void put(int key, int value) {
            if(mpp.containsKey(key)){
                remove(mpp.get(key));
            }
            if(mpp.size() == capacity){
                remove(tail.prev);
            }
            insert(new Node(key , value));
        }

        private void remove(Node node){
            mpp.remove(node.key);
            Node prevNode = node.prev;
            Node nextNode = node.next;
    
            prevNode.next = nextNode;
            nextNode.prev = prevNode;
        }
    
        private void insert(Node node){
            mpp.put(node.key , node);
            Node curHeadnext = head.next;
            head.next = node;
            node.next = curHeadnext;
            curHeadnext.prev = node;
            node.prev = head;
    
        }
    }

    // Celebrity Problem
    public static int celebrity_B(int mat[][]) {
        
        int n = mat.length;
        
        int knowMe[] = new int [n];
        int iKnow[] = new int [n];
        
        for(int i = 0 ; i < n; i++){
            for(int j = 0 ; j < n ; j++){
                if(i != j && mat[i][j] == 1){
                    knowMe[j]++;
                    iKnow[i]++;
                }
            }
        }
        
        for(int i = 0; i < n; i++){
            if(knowMe[i] == n-1 && iKnow[i] == 0){
                return i;
            }
        }
        
        return -1;
    }

    public static  int celebrity_O(int mat[][]) {
        
        int n = mat.length;
        
        int top = 0;
        int down = n-1;
        
        while(top < down){
            if(mat[top][down] == 1){
                top ++;
            }
            else if(mat[down][top] == 1){
                down--;
            }
            else{
                top++;
                down--;
            }
        }
        
        if(top > down) return -1;
        
        for(int i = 0 ; i < n ; i++){
            if(i != top){
                if(mat[top][i] == 0 && mat[i][top] == 1){
                    continue;
                }
                else{
                    return -1;
                }
            }
        }
        
        return top;
    }


    public static void main(String[] args) {
        int[] arrNgI = {1, 3, 2, 4}; 


        ArrayList<Integer> ngeI = nextLargestElementI_O(arrNgI);
        System.out.println("Next Greater Elements - I : " + ngeI);

        int[]  arrNgII = {1,2,1};
        ArrayList<Integer> ngeII = nextLargestElementII_O(arrNgII);
        System.out.println("Next Greater Elements - 2 : " + ngeII);

        String num = "1432219";
        // String num = "1321";
        int k = 3;
        String result = removeKdigits(num, k);
        System.out.println("Result after removing " + k + " digits: " + result);

        // Sliding window maximum
        // int[] arrSW = {1, 3 ,-1 , -3 , 5 , 3 , 7 , 1 , 6};
        int[] arrSW  = {1, 2, 3, 1, 4, 5, 2, 3, 6};
        int kSW = 3;
        int[] slidingWindowResult = maxSlidingWindow(arrSW, kSW);
        System.out.println("Sliding Window Maximum: " + Arrays.toString(slidingWindowResult));

        // PSE and NSE
        int[] arrPSE = {4, 5, 2, 10, 8};
        ArrayList<Integer> pse = previousSE(arrPSE);
        System.out.println("Previous Smaller Elements: " + pse);

        int[] arrNSE = {4, 5, 2, 10, 8};
        ArrayList<Integer> nse = nextSE(arrNSE);
        System.out.println("Next Smaller Elements: " + nse);


        // Largest Rectangle in Histogram
        int[] heights = {2,1,5,6,2,3};
        STProblems stProblems = new STProblems();
        int largestRectangleArea = largestRectangleArea_O(heights);
        System.out.println("Largest Rectangle Area in Histogram: " + largestRectangleArea);

        // Maximal Rectangle
        char[][] matrix = {
            {'1', '0', '1', '0', '0'},
            {'1', '0', '1', '1', '1'},
            {'1', '1', '1', '1', '1'},
            {'1', '0', '0', '1', '0'}
        };
        int maximalRectangleArea = maximalRectangle(matrix);
        System.out.println("Maximal Rectangle Area: " + maximalRectangleArea);


        // Trapping Rain Water
        int[] rainWaterHeights = {0,1,0,2,1,0,1,3,2,1,2,1};
        int trappedWater = trappingRainWater_O(rainWaterHeights);
        System.out.println("Trapped Rain Water: " + trappedWater);

        // LRU Cache
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        System.out.println("Get key 1: " + lruCache.get(1)); // returns 1
        lruCache.put(3, 3); // evicts key 2
        System.out.println("Get key 2: " + lruCache.get(2)); // returns -1 (not found)
        lruCache.put(4, 4); // evicts key 1
        System.out.println("Get key 1: " + lruCache.get(1)); // returns -1 (not found)
        System.out.println("Get key 3: " + lruCache.get(3)); // returns 3
        System.out.println("Get key 4: " + lruCache.get(4)); // returns 4
    }
}