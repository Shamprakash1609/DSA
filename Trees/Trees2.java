import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;


public class Trees2 {

    // QuadTree
    public static class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;

        
        public Node() {
            this.val = false;
            this.isLeaf = false;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }
        
        public Node(boolean val, boolean isLeaf) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }
        
        public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = topLeft;
            this.topRight = topRight;
            this.bottomLeft = bottomLeft;
            this.bottomRight = bottomRight;
        }
    }

    // Linked List
    public  class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     }
    
    // Binary Tree
    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val){
            this.val = val;
        }

        TreeNode(int val , TreeNode left , TreeNode right){
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

//**************************************************************************PROMBLEMS***************************************************************************
    // Binary Tree Paths
    public static  List<String> binaryTreePaths(TreeNode root) {
        List<String> ans = new ArrayList<>();
        if(root == null) return ans;
        paths(root ,"" , ans);
        return ans;
    }

    private static void paths(TreeNode node , String path , List<String>ans){
        if(node == null) return ;
        if(path.length() == 0){
            path = Integer.toString(node.val);
        }
        else{
            path = path + "->"+ String.valueOf(node.val);
        }

        if(node.left == null && node.right == null){
            ans.add(path);
            return;
        }

        paths(node.left , path , ans);
        paths(node.right , path , ans);    
    }


    // Count Good Nodes in Binary Tree
    public static int goodNodes(TreeNode root) {
        if(root == null) return 0;
        return goodNodes(root , root.val );
    }

    private static int goodNodes(TreeNode node , int maxi){
       if(node == null) return 0;
       int cnt = (node.val >= maxi) ? 1 : 0;

       maxi = Math.max(node.val , maxi);

       cnt += goodNodes(node.left , maxi);
       cnt += goodNodes(node.right , maxi);

       return cnt;
    }

    // House Robber III
    public static int rob(TreeNode root) {
        if(root == null) return 0;
        int[] res = letsRob(root);
        return Math.max(res[0] , res[1]);
    }

    private static int[] letsRob(TreeNode node){
        if(node == null) return new int[]{0 , 0};

        int[] left = letsRob(node.left);
        int[] right = letsRob(node.right);

        int rob = node.val + left[1] + right[1];
        int notRob = Math.max(left[0] , left[1]) + Math.max(right[0] , right[1]);

        return new int[]{rob , notRob};
    }

    // Subtree of Another Tree
    public static  boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if(root == null && subRoot == null) return true;
        if((root == null && subRoot != null) || (root != null && subRoot == null)) return false;
        return isSameTree(root , subRoot) || isSubtree(root.left , subRoot) || isSubtree(root.right , subRoot);
    }

    private static boolean isSameTree(TreeNode p , TreeNode q){
        if(p == null &&  q == null) return true;
        if((p == null && q != null) || (p != null && q == null)) return false;

        return p.val == q.val && isSameTree(p.left , q.left) && isSameTree(p.right , q.right);
    }

    // Linked List in Binary Tree
    public static  boolean isSubPath(ListNode head, TreeNode root) {
        if(root == null) return false;

        if(pathExists(head , root)) return true;

        return isSubPath(head , root.left) || isSubPath(head , root.right);
    }

    private static boolean pathExists(ListNode head , TreeNode root){
        if(head == null) return true;
        if(root == null) return false;

        if(head.val != root.val) return false;

        return pathExists(head.next , root.left) || pathExists(head.next , root.right);
    }

    // Operations on Tree
    class LockingTree {

        private static int[] parent;
        private static int[] locked;
        ArrayList<Integer>[] children;

        public LockingTree(int[] parent) {
            this.parent = parent;
            int n = parent.length;
            locked = new int[n];

            Arrays.fill(locked , -1);

            children = new ArrayList[n];
            for(int i = 0; i < n; i++){
                children[i] = new ArrayList<>();
            }

            for(int i = 1 ; i < n ; i++){
                children[parent[i]].add(i);
            }
        }
        
        public boolean lock(int num, int user) {
            if(locked[num] != -1) return false;
            locked[num] = user;
            return true;
        }
        
        public boolean unlock(int num, int user) {
            if(locked[num] != user) return false;
            locked[num] = -1;
            return true;
        }
        
        public boolean upgrade(int num, int user) {
            // 1. Node must be unlocked
            if(locked[num] != -1) return false;
            
            // 2. No locked ancestors 
            int p = parent[num];
            while(p != -1){
                if(locked[p] != -1) return false;
                p = parent[p];
            }

            // 3. Must have at least one locked descendant
            Queue<Integer> q = new LinkedList<>();
            q.offer(num);
            int lockedCnt = 0;

            while(!q.isEmpty()){
                int node = q.poll();

                if(locked[node] != -1){ // not meant to check parent , rather children nodes
                    locked[node] = -1;
                    lockedCnt++;
                }

                for(int child : children[node]){
                    q.offer(child);
                }
            }

            if(lockedCnt >= 1){
                locked[num] = user;
            }

            return lockedCnt >= 1;
        }
    }

    // Kth Largest Sum in a Binary Tree
    public static long kthLargestLevelSum(TreeNode root, int k) {
        // PriorityQueue<Long> pq = new PriorityQueue<>((a , b) -> Long.compare(b , a));
        // PriorityQueue<Long> pq = new PriorityQueue<>((a , b) -> b.compareTo(a));
        // PriorityQueue<Long> pq = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Long> pq = new PriorityQueue<>();

        Queue<TreeNode> q = new LinkedList<>();

        q.offer(root);

        while(!q.isEmpty()){
            int size = q.size();

            long sum = 0L;
            for(int i = 0 ; i < size ; i++){
                TreeNode node = q.poll();
                
                sum += node.val;

                if(node.left != null) q.offer(node.left);
                if(node.right != null) q.offer(node.right);
            }

            pq.offer(sum);
            if(pq.size() > k) pq.poll();
        }


        return (pq.size() < k) ? -1 : pq.peek();

    }

    // Maximum Product of Splitted Binary Tree
    private static long totalSum;
    private static long maxProd;
    private static final long MOD = 1000000007;
    public static  int maxProduct(TreeNode root) {
        if(root == null) return 0;

        totalSum = 0;
        maxProd = 0;
        
        totalSum = pathSum(root);
        maxSplit(root);
        return (int) (maxProd % MOD);
    }

    private static long maxSplit(TreeNode node ){
        if(node == null) return 0;

        long left = maxSplit(node.left);
        long right = maxSplit(node.right);

        long subTree = node.val + left + right;

        long prod = subTree * (totalSum - subTree);
        maxProd = Math.max(prod , maxProd);

        return subTree;
    }

    private static long pathSum(TreeNode node){
        if(node == null) return 0;

        long left = pathSum(node.left);
        long right = pathSum(node.right);

        return node.val + left + right;
    }

    // Step-By-Step Directions From a Binary Tree Node to Another
    public static  String getDirections(TreeNode root, int startValue, int destValue) {
        TreeNode ancestor = LCA(root , startValue , destValue);

        StringBuilder pathS = new StringBuilder();
        StringBuilder pathE = new StringBuilder();

        findRoute(ancestor , startValue , pathS);
        findRoute(ancestor , destValue , pathE);

        String up = "U".repeat(pathS.length());

        return up + pathE.toString();
    }

    private static TreeNode LCA(TreeNode node , int p, int q){
        if(node == null || node.val == p || node.val == q) return node;

        TreeNode left = LCA(node.left , p , q);
        TreeNode right = LCA(node.right , p , q);

        if(left == null){
            return right;
        }
        else if(right == null){
            return left;
        }else{
            return node;
        }
    }

    private static boolean findRoute(TreeNode node , int target , StringBuilder path){
        if(node == null) return false;
        if(node.val == target) return true;

        // Try Left
        path.append("L");
        if(findRoute(node.left , target , path)){
            return true;
        }
        path.deleteCharAt(path.length() - 1);

        // Try Right
        path.append("R");
        if(findRoute(node.right , target , path)){
            return true;
        }
        path.deleteCharAt(path.length() - 1);

        return false;
    }

    // Most Profitable Path in a Tree
    public static int mostProfitablePath(int[][] edges, int bob, int[] amount) {
        int n = edges.length;
        ArrayList<ArrayList<Integer>> adjLs = new ArrayList<>();

        for(int i = 0 ; i <= n ; i++){
            adjLs.add(new ArrayList<Integer>());
        }

        for(int arr[] : edges){
            int u = arr[0];
            int v = arr[1];

            adjLs.get(u).add(v);
            adjLs.get(v).add(u);
        }

        // Find both path 
        ArrayList<Integer> currPath = new ArrayList<>();
        ArrayList<Integer> bobPath = new ArrayList<>();

        findBobPath(adjLs , bob , -1 , currPath , bobPath);

        int size = bobPath.size();
        
        // Update bob path
        int i;
        for(i = 0 ; i < size / 2 ; ++i){
            amount[bobPath.get(i)] = 0;
        }

        if(size % 2 == 1){
            amount[bobPath.get(i)] = 0;
        } else { // meeting at even path length expect destionation node (0) int the path
            amount[bobPath.get(i)] /= 2;
        }

        // Find Alice maximum income
        return findMaxIncome(adjLs , 0 , -1 , amount);

    }

    private static boolean findBobPath(ArrayList<ArrayList<Integer>> adjLs , int bob , int parent , ArrayList<Integer> currPath , ArrayList<Integer> bobPath){
        if(bob == 0){
            bobPath.addAll(currPath);
            return true;
        }

        currPath.add(bob);

        for(int it : adjLs.get(bob)){
            if(it != parent && findBobPath(adjLs , it , bob , currPath , bobPath)){
                return true;
            }
        }

        currPath.remove(currPath.size() - 1);
        return false;
    }

    private static int findMaxIncome(ArrayList<ArrayList<Integer>> adjLs , int alice, int parent, int[] amount){
        int maxIncome = Integer.MIN_VALUE;

        for(int it : adjLs.get(alice)){
            if(it != parent){
                int income = findMaxIncome(adjLs , it , alice , amount);
                if(income + amount[alice] > maxIncome){
                    maxIncome = income + amount[alice];
                }
            }
        }

        return (maxIncome == Integer.MIN_VALUE) ? amount[alice] : maxIncome;
    }

    // Flip Binary Tree To Match Preorder Traversal
    private static int ind;

    public static  List<Integer> flipMatchVoyage(TreeNode root, int[] voyage) {
        List<Integer> ans = new ArrayList<>();
        ind = 0;
        return flipTree(root , voyage , ans) ? ans : Arrays.asList(-1);
    }
    
    private static boolean flipTree(TreeNode node , int[] voyage , List<Integer> ans){
        if(node == null) return true;
        if(node.val != voyage[ind++]) return false;
        if(node.left != null && node.left.val != voyage[ind]){
            ans.add(node.val);
            return flipTree(node.right , voyage , ans) && flipTree(node.left , voyage , ans);
        }
        return flipTree(node.left , voyage , ans) && flipTree(node.right , voyage , ans);
    }

    // Validate Binary Tree Nodes
    public static  boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        /*
        Citeria for a Valid Binary Tree
        1. Exactly one root
        2. Every node except root must have exactly one parent
        3. No cycles
        4. Entire graph must be connected
        */

        int inDeg[] = new int[n];

        // Step 1: Build indegree array & detect multiple parents
        for(int i = 0 ; i < n ; i++){
            if(leftChild[i] != -1){
                inDeg[leftChild[i]]++;
                if(inDeg[leftChild[i]] > 1) return false;
            }

            if(rightChild[i] != -1){
                inDeg[rightChild[i]]++;
                if(inDeg[rightChild[i]] > 1) return false;
            }

        }

        // Step 2: Find the single root (indegree == 0)
        int root = -1;
        for(int i = 0 ; i < n ; i++){
            if(inDeg[i] == 0){
                if(root != -1) return false; // more than one root
                root = i;
            }
        }

        if(root == -1) return false; // no root → cycle exists

        // Step 3: BFS/DFS to ensure connectivity
        int vis[] = new int[n];
        Queue<Integer> q = new LinkedList<>();

        q.offer(root);
        vis[root] = 1;

        int visCnt = 0;

        while(!q.isEmpty()){
            int node = q.poll();
            visCnt++;

            int left = leftChild[node];
            int right = rightChild[node];

            if(left != -1){
                if(vis[left] == 1) return false;
                vis[left] = 1;
                q.offer(left);
            }

            if(right != -1){
                if(vis[right] == 1) return false;
                vis[right] = 1;
                q.offer(right);
            }
        }

        // Step 4: Must visit exactly n nodes
        return visCnt == n;
    }

    // Count Nodes With the Highest Score
    public static  int countHighestScoreNodes(int[] parents) {
        int n = parents.length; // total nodes

        // 1. Build binary tree from parents[]
        TreeNode[] nodes = new TreeNode[n];
        for(int i = 0 ; i < n ; i++){
            nodes[i] = new TreeNode(i);
        }


        TreeNode root = null;

        for(int i = 0 ; i < n ; i++){
            int p = parents[i];
            if(p == -1){
                root= nodes[i];
            }
            else{
                if(nodes[p].left == null)nodes[p].left = nodes[i];
                else nodes[p].right = nodes[i];
            }
        }

        // 2. Compute subtree sizes for each node
        int[] sub = new int[n];
        countNodes(root , sub);


        // 3. Compute Score for each node
        long maxScore = 0;
        int count = 0;

        for(int i= 0 ; i < n ; i++){
            long left = 0 , right = 0;

            TreeNode node = nodes[i];

            if(node.left != null) left = sub[node.left.val];
            if(node.right != null) right = sub[node.right.val];

            // totalCnt - left subtree - right subtree - current node 
            long remaining = n - left - right - 1; 
            if(remaining == 0) remaining = 1;

            long score = Math.max(1 , left) * Math.max(1 , right) * remaining;

            // track maximum score
            if(score > maxScore){
                maxScore = score;
                count = 1;
            }
            else if(score == maxScore){
                count++;
            }
        }

        return count;
    }

    private static int countNodes(TreeNode node , int sub[]){
        if(node == null) return 0;
        int left = countNodes(node.left , sub);
        int right = countNodes(node.right , sub);
        
        sub[node.val] = left + right + 1;

        return sub[node.val];
    }

    // Verify Preorder Serialization of a Binary Tree
    public static boolean isValidSerialization(String preorder) {
        String[] nodes = preorder.split(",");

        int vacancy = 1;
        for(String node : nodes){
            vacancy--;

            if(vacancy < 0) return false;
            if(!node.equals("#")){
                vacancy += 2;
            }
        }

        return (vacancy == 0);
    }

    // Construct Quad Tree
    public static Node construct(int[][] grid) {
        return solve(grid , 0 , 0 , grid.length);
    }

    private static boolean sameValue(int[][] grid , int x1 , int y1 , int len){
        int val = grid[x1][y1];
        for(int i = x1 ; i < x1 + len ; i++){
            for(int j = y1 ; j < y1 + len ; j++){
                if(grid[i][j] != val){
                    return false;
                }
            }
        }

        return true;
    }

    private static Node solve(int grid[][] , int x1 , int y1 , int len){
        if(sameValue(grid , x1 , y1 ,len)){
            return new Node(grid[x1][y1] == 1 , true);
        } 
        Node root = new Node(false , false);

        int half = len / 2;

        root.topLeft = solve(grid , x1 , y1 , half);
        root.topRight = solve(grid , x1 , y1 + half , half);
        root.bottomLeft = solve(grid , x1 + half , y1 , half);
        root.bottomRight = solve(grid , x1 + half , y1 + half, half);

        return root;
    }

    // Binary Search Tree to Greater Sum Tree
    private static int sumNode;
    public TreeNode bstToGst(TreeNode root) {
        if(root == null) return null;
        sumNode = 0;
        convertGst(root);
        return root;
    }

    private static void convertGst(TreeNode node){
        // Reverse Inorder
        if(node == null) return;
        convertGst(node.right);
        node.val += sumNode;
        sumNode = node.val;
        convertGst(node.left);
    }

    // Maximum Sum BST in Binary Tree
    public static class NodeValue{
        int minNode , maxNode , maxSum;

        public NodeValue(int minNode , int maxNode , int maxSum){
            this.minNode = minNode;
            this.maxNode = maxNode;
            this.maxSum = maxSum;
        }
    }

    private static int globalMax;

    public int maxSumBST(TreeNode root) {
        globalMax = 0;
        maxSum(root);
        return globalMax;
    }

    private static NodeValue maxSum(TreeNode root){
        if(root == null){
            return new NodeValue(Integer.MAX_VALUE, Integer.MIN_VALUE, 0);
        }

        NodeValue left = maxSum(root.left);
        NodeValue right = maxSum(root.right);

        if(left.maxNode < root.val && right.minNode > root.val){
            int minVal = Math.min(left.minNode ,root.val);
            int maxVal = Math.max(right.maxNode , root.val);
            int treeSum = root.val + left.maxSum + right.maxSum;

            globalMax = Math.max(treeSum , globalMax);

            return new NodeValue(minVal , maxVal , treeSum);
        }

        return new NodeValue(
            Integer.MIN_VALUE,
            Integer.MAX_VALUE,
            Math.max(left.maxSum, right.maxSum)
        );
    }

    //  Closest Nodes Queries in a Binary Search Tree
    public static  List<List<Integer>> closestNodes(TreeNode root, List<Integer> queries) {

        List<Integer> iot = new ArrayList<>();
        inorder(root, iot);

        List<List<Integer>> res = new ArrayList<>();

        for(int q : queries){
            int mini = floor(iot , q);
            int maxi = ceil(iot , q);

            res.add(Arrays.asList(mini , maxi));
        }

        return res;
    }

    private static  void inorder(TreeNode node, List<Integer> arr){
        if(node == null) return;
        inorder(node.left, arr);
        arr.add(node.val);
        inorder(node.right, arr);
    }

    private static  int floor(List<Integer> arr, int x){
        int ans = -1;
        int low = 0, high = arr.size() - 1;

        while(low <= high){
            int mid = (low + high) / 2;

            if(arr.get(mid) <= x){
                ans = arr.get(mid);
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }

    private static  int ceil(List<Integer> arr, int x){
        int ans = -1;
        int low = 0, high = arr.size() - 1;

        while(low <= high){
            int mid = (low + high) / 2;

            if(arr.get(mid) >= x){
                ans = arr.get(mid);
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }









    public static void main(String[] args) {
        Trees2 outer = new Trees2();

        // build example tree: [3,1,4,3,null,1,5]
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.left.left = new TreeNode(3);
        root.right.left = new TreeNode(1);
        root.right.right = new TreeNode(5);

        // Count Good Nodes in Binary Tree
        int result = goodNodes(root);
        System.out.println("Good nodes count: " + result);
        
        // House Robber III
        int robbed = rob(root);
        System.out.println("Max amount robbed: " + robbed);

        // Subtree of Another Tree
        TreeNode subRoot = new TreeNode(4);
        subRoot.left = new TreeNode(1);
        subRoot.right = new TreeNode(5);
        boolean isSub = isSubtree(root, subRoot);
        System.out.println("Is subtree: " + isSub);

        // Linked List in Binary Tree
        ListNode head = outer.new ListNode(3, outer.new ListNode(1, outer.new ListNode(3)));
        boolean hasPath = isSubPath(head, root);
        System.out.println("Is linked list a downward path in the tree: " + hasPath);

        // Operations on Tree
        // LeetCode-style sequence: ["LockingTree","lock","unlock","unlock","lock","upgrade","lock"]
        int[] parentArr = {-1, 0, 0, 1, 1, 2, 2};
        LockingTree tree = outer.new LockingTree(parentArr); // constructor (no return)

        System.out.println("lock(2,2): " + tree.lock(2, 2));     // true
        System.out.println("unlock(2,3): " + tree.unlock(2, 3)); // false (wrong user)
        System.out.println("unlock(2,2): " + tree.unlock(2, 2)); // true
        System.out.println("lock(4,5): " + tree.lock(4, 5));     // true
        System.out.println("upgrade(0,1): " + tree.upgrade(0, 1)); // true (locks 0, unlocks descendants)
        System.out.println("lock(0,1): " + tree.lock(0, 1));     // false (already locked by user 1)

        // Most Profitable Path in a Tree
        int[][] edges2 = {{0,1},{1,2},{1,3},{3,4}};
        int bobNode = 4;
        int[] amount2 = {0,3,2,-2,0};
        int profit = mostProfitablePath(edges2, bobNode, amount2);
        System.out.println("Most profitable path profit: " + profit);

        // Flip Binary Tree To Match Preorder Traversal
        TreeNode flipRoot = new TreeNode(1);
        flipRoot.left = new TreeNode(2);
        flipRoot.right = new TreeNode(3);
        int[] voyage = {1, 3, 2};
        List<Integer> flipped = flipMatchVoyage(flipRoot, voyage);
        System.out.println("Flip nodes: " + flipped);

        // Validate Binary Tree Nodes
        int[] leftChild = {1, -1, 3, -1};
        int[] rightChild = {2, -1, -1, -1};
        boolean isValid = validateBinaryTreeNodes(4, leftChild, rightChild);
        System.out.println("Is valid binary tree: " + isValid);

        // Count Nodes With the Highest Score
        int[] parents = {-1, 2, 0, 2, 0};
        int highestScoreCount = countHighestScoreNodes(parents);
        System.out.println("Count of nodes with highest score: " + highestScoreCount);

        // Verify Preorder Serialization of a Binary Tree
        String preorder = "9,3,4,#,#,1,#,#,2,#,6,#,#";
        boolean validSerialization = isValidSerialization(preorder);
        System.out.println("Is valid preorder serialization: " + validSerialization);

        // Construct Quad Tree
        int[][] grid = {
            {1,1,1,1,0,0,0,0},
            {1,1,1,1,0,0,0,0},
            {1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1},
            {1,1,1,1,0,0,0,0},
            {1,1,1,1,0,0,0,0},
            {1,1,1,1,0,0,0,0},
            {1,1,1,1,0,0,0,0}
        };

        Node quadTree = construct(grid);
        System.out.println("Quad tree constructed with root val: " + quadTree.val + ", isLeaf: " + quadTree.isLeaf);

        // Maximum Sum BST in Binary Tree
        int maxSumBSTResult = outer.maxSumBST(root);
        System.out.println("Maximum sum BST: " + maxSumBSTResult);

    }

}
