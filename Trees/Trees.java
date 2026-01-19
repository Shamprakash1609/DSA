import java.util.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class TreeNode{
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

public class Trees {

    // BFS - Level Order traversal of tree
    public static  List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();

        List<List<Integer>> wrapList = new LinkedList<List<Integer>>();
        if(root == null)return wrapList;

        q.add(root);

        while(!q.isEmpty()){
            int levelNum = q.size();

            List<Integer> subList = new LinkedList<>();

            for(int i = 0 ; i < levelNum ; i++){
                if(q.peek().left != null){
                     q.offer(q.peek().left);
                }
                if(q.peek().right != null){
                    q.offer(q.peek().right);
                }

                subList.add(q.poll().val);
            }

            wrapList.add(subList);
        }

        return wrapList;
    }

    public static List<Double> averageOfLevels(TreeNode root) {
        List<Double> ans = new ArrayList<>();

        Queue<TreeNode> q = new LinkedList<>();

        if(root == null) return ans;
        q.offer(root);

        while(!q.isEmpty()){
            int levelNum = q.size();

            double avg = 0.0;

            for(int i = 0 ; i < levelNum ; i++){
                if(q.peek().left != null){
                    q.offer(q.peek().left);
                }

                if(q.peek().right != null){
                    q.offer(q.peek().right);
                }

                avg += q.peek().val;
                q.poll();
            }

            avg /= levelNum;
            ans.add(avg);

        }

        return ans;
    }


    // Preorder Traversal
    public static List<Integer> preorderTraversal_R(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        preOrder(root , ans);
        return ans;
    }

    public static void preOrder(TreeNode node , List<Integer> ans){
        if(node == null) return;   

        ans.add(node.val);
        preOrder(node.left , ans);
        preOrder(node.right , ans);
    }

    public static  List<Integer> preorderTraversal_I(TreeNode root) {
        List<Integer> ans = new ArrayList<>();

        Stack<TreeNode> st = new Stack<>();

        if(root == null ) return ans;
        st.push(root);

        while(!st.isEmpty()){
            root = st.pop();
            ans.add(root.val);

            if(root.right != null){
                st.push(root.right);
            }

            if(root.left != null){
                st.push(root.left);
            }
        }
        
        return ans;
    }


    // Inorder Traversal
    public static  List<Integer> inorderTraversal_R(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        inOrder(root , ans);
        return ans;
    }

    public static void inOrder(TreeNode node , List<Integer> ans){
        if(node == null) return;

        inOrder(node.left , ans);
        ans.add(node.val);
        inOrder(node.right , ans);
    }

    public List<Integer> inorderTraversal_I(TreeNode root) {
        List<Integer> ans = new ArrayList<>();

        Stack<TreeNode> st = new Stack<>();

        TreeNode node = root;

        while(true){
            if(node != null){
                st.push(node);
                node = node.left;
            }
            else{
                if(st.isEmpty()){
                    break;
                }

                node = st.pop();
                ans.add(node.val);
                node = node.right;
            }
        }
        
        return ans;
    }

    // Postorder Traversal
    public static  List<Integer> postorderTraversal_R(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        postOrder(root , ans);
        return ans;
    }

    public static void postOrder(TreeNode node ,  List<Integer> ans){
        if(node == null) return;

        postOrder(node.left , ans);
        postOrder(node.right , ans);
        ans.add(node.val);
    }

    public static  List<Integer> postorderTraversal_I_2S(TreeNode root) {
        List<Integer> ans = new ArrayList<>();

        Stack<TreeNode> st1 = new Stack<>();
        Stack<TreeNode> st2 = new Stack<>();

        if(root == null) return ans;
        st1.add(root);

        while(!st1.isEmpty()){
            root = st1.pop();
            st2.add(root);

            if(root.left != null) st1.push(root.left);
            if(root.right != null) st1.push(root.right);
        }

        while(!st2.isEmpty()){
            ans.add(st2.pop().val);
        }

        return ans;
    }

    public static  List<Integer> postorderTraversal_I_1S(TreeNode root) {
        List<Integer> ans = new ArrayList<>();

        Stack<TreeNode> st = new Stack<>();

        TreeNode node = root;

        while(node != null || !st.isEmpty()){
            if(node != null){
                st.push(node);
                node = node.left;
            }

            else{
                TreeNode temp = st.peek().right;

                if(temp == null){
                    temp = st.pop();
                    ans.add(temp.val);

                    while(!st.empty() && temp == st.peek().right){
                        temp = st.pop();
                        ans.add(temp.val);
                    }
                   
                }
                else{
                    node = temp;
                }

            }
        }

        return ans;
    }

    // All three Traversals in one function
    static class Pair{
        TreeNode node;
        int num;

        public Pair(TreeNode node , int num){
            this.node = node;
            this.num = num;
        }
    }
    public static void preInPost(TreeNode root){
        if(root == null) return;

        Stack<Pair> st = new Stack<>();
    
        List<Integer> Preorder = new ArrayList<>();
        List<Integer> Inorder = new ArrayList<>();
        List<Integer> Postorder = new ArrayList<>();

        st.push(new Pair(root, 1));

        while(!st.isEmpty()){

            Pair currN = st.pop();

            if(currN.num == 1){
                Preorder.add(currN.node.val);
                st.add(new Pair(currN.node , currN.num + 1));

                if(currN.node.left != null){
                    st.push(new Pair(currN.node.left , 1));
                }
            }

            else if(currN.num == 2){
                Inorder.add(currN.node.val);
                st.add(new Pair(currN.node , currN.num+1));

                if(currN.node.right != null){
                    st.push(new Pair(currN.node.right , 1));
                }
            }

            else{
                Postorder.add(currN.node.val);
            }

        }

        System.out.println("Preorder : " + Preorder);
        System.out.println("Inorder  : " + Inorder);
        System.out.println("Postorder: " + Postorder);
       
    }
    
    // Minimum Depth of Binary Tree
    public static int minDepth(TreeNode root) {
        if(root == null) return 0;

        int left = minDepth(root.left); 
        int right = minDepth(root.right);

        if(left == 0) return right + 1;
        if(right == 0) return left + 1;

        int depth = Math.min(left , right) + 1;

        return depth;
    } 

    // Maximum depth in a Binary Tree
    public static int maxDepth(TreeNode root) {
        if(root == null) return 0;

        int lh = maxDepth(root.left);
        int rh = maxDepth(root.right);
        
        return 1 + Math.max(lh , rh);
    }

    // Balanced Binary Trees
    public static  boolean isBalanced_B(TreeNode root) {
        if(root == null){
            return true;
        }

        int lD = maxDepth(root.left);
        int rD = maxDepth(root.right);

        if(Math.abs(lD - rD) > 1) return false;

        boolean left = isBalanced_B(root.left);
        boolean right = isBalanced_B(root.right);

        return  left && right;

    }

    public static  boolean isBalanced_O(TreeNode root) {
        return  findMaxDepth(root) != -1;

    }

    public static int findMaxDepth(TreeNode root){
        if(root == null){
            return 0;
        }

        int lh = findMaxDepth(root.left);
        int rh = findMaxDepth(root.right);

        if(lh == -1 || rh == -1) return -1;
        if(Math.abs(lh - rh) > 1) return -1;

        return 1 + Math.max(lh , rh);
    }

    // Diameter of a Binary Tree
    // Longest path b/w two were nodes path doesn't need to pass via root
    public static int diameterOfBinaryTree_B(TreeNode root) {
        if(root == null) return 0;

        int lh =  maxDepth(root.left);
        int rh =  maxDepth(root.right);

        int maxi = -1; 
        maxi= Math.max(maxi , lh + rh);

        diameterOfBinaryTree_B(root.left);
        diameterOfBinaryTree_B(root.right);

        return maxi;
    }

    public static  int diameterOfBinaryTree_O(TreeNode root) {
        int [] diameter = new int[1];
        maxDepth(root , diameter);
        return diameter[0];
    }

    public static int maxDepth(TreeNode root ,  int[] diameter) {
        if(root == null) return 0;

        int lh = maxDepth(root.left , diameter);
        int rh = maxDepth(root.right , diameter);

        diameter[0] = Math.max(diameter[0] , lh + rh);

        return 1 + Math.max(lh , rh);
    }

    // max Path sum
    public static int maxPathSum(TreeNode root) {
        int maxVal[] = new int[1];
        maxVal[0] = Integer.MIN_VALUE;
        maxPathDown(root , maxVal);
        return maxVal[0];
    }

    private static int maxPathDown(TreeNode node , int maxi[]){
        if(node == null){
            return 0;
        }

        int left = Math.max(0 , maxPathDown(node.left , maxi));
        int right = Math.max(0 , maxPathDown(node.right , maxi));
        /* Max taken left right to avoid negative sum */
        maxi[0] = Math.max(maxi[0] , left + right + node.val);               
        return node.val+ Math.max(left , right) ;
    }

    // Check for Identical trees
    public static  boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null || q == null){
            return(p == q);
        }
        return(p.val == q.val) && isSameTree(p.left , q.left) && isSameTree(p.right , q.right);
    }

    // Zig-Zag traversal
    public static  List<List<Integer>> zigzagLevelOrder(TreeNode root) {

        List<List<Integer>> warpList = new LinkedList<>();

        if(root == null) return warpList;

        Queue<TreeNode> q = new LinkedList<>();

        q.offer(root);
        boolean flag = true;

        while(!q.isEmpty()){
            int levelNum = q.size();

            List<Integer> subList = new LinkedList<>();
            for(int i = 0 ; i < levelNum ; i++){
                TreeNode node = q.poll();

                int index = flag ? i : levelNum - i - 1;

                if (flag) {
                    subList.addLast(node.val);  // left to right
                } else {
                    subList.addFirst(node.val); // right to left
                }

                if(node.left != null){
                    q.add(node.left);
                }
                if(node.right != null){
                    q.add(node.right);
                }
            }

            flag = !flag;
            warpList.add(subList);

        }

        return warpList;

    }

    // Populating Next Right Pointers in Each Node
    public class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}
        
        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    };
    
    public static Node connect(Node root) {
        if(root == null) return root;

        Queue<Node> q = new LinkedList<>();
        q.offer(root);

        while(!q.isEmpty()){
            int levelNum = q.size();

            Node prev = null;

            for(int i = 0 ; i < levelNum ; i++){
                Node node = q.poll();

                if(prev != null){
                    prev.next = node;
                }
                prev = node;


                if(node.left != null) q.add(node.left);
                if(node.right != null) q.add(node.right);
                
            }
            
            prev.next = null;
        }

        return root;
    }
    
    public static Node connect_B(Node root) {
        if(root == null) return root;
        Node leftMost = root;

        while(leftMost.left != null){
            Node current = leftMost;
            while(current != null){
                current.left.next = current.right;
                if(current.next != null){
                    current.right.next = current.next.left;
                }
                current = current.next;
            }
            leftMost = leftMost.left;
        }

        return root;
    }

    public static  Node connect_D(Node root) {
        if(root == null) return root;

        if(root.left != null){
            root.left.next = root.right;
        }

        if(root.right != null && root.next != null){
            root.right.next = root.next.left;
        }

        connect(root.left);
        connect(root.right);
        return root;
    }

    // Populating Next Right Pointers in Each Node II
    public static  Node connectII(Node root) {
        if(root == null) return null;
        Queue<Node> q = new LinkedList<>();
        q.offer(root);

        while(!q.isEmpty()){
            int size = q.size();

            while(size-- > 0){
                Node curr = q.poll();
                if(size != 0) curr.next = q.peek();
                if(curr.left != null) q.add(curr.left);
                if(curr.right != null) q.add(curr.right);
            }
        }

        return root;
    }

 
    // Binary Tree Right Side View
    public static  List<Integer> rightSideView(TreeNode root) {
        List<Integer> ans = new LinkedList<>();

        if(root == null) return ans;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while(!q.isEmpty()){
            int size = q.size();

            for(int i = 0 ; i < size ; i++){
                TreeNode node = q.poll();

                if(i == size - 1) ans.add(node.val);

                if(node.left != null) q.add(node.left);
                if(node.right != null) q.add(node.right);
            }
        }

        return ans;
    } 

    // Cousins in Binary Tree
    public static  boolean isCousins_B(TreeNode root, int x, int y) {
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int xPar = -1;
        int yPar = -1;
        while(!q.isEmpty()){
            int size = q.size();

            for(int i = 0 ; i < size ; i++){
                TreeNode node = q.poll();

                if(node.left != null){
                    if(node.left.val == x) xPar = node.val;
                    if(node.left.val == y) yPar = node.val;
                    q.add(node.left);
                } 
                if(node.right != null){
                     if(node.right.val == x) xPar = node.val;
                    if(node.right.val == y) yPar = node.val;
                    q.add(node.right);
                } 
            }
            
            boolean foundX = (xPar != -1);
            boolean foundY = (yPar != -1);

            if(foundX && foundY){
                return xPar != yPar;
            }

            if((foundX && !foundY) || (!foundX && foundY)){
                return false;
            }

        }

        return false;
    }

    public static  boolean isCousins_D(TreeNode root, int x, int y) {
        TreeNode xx = findNode(root , x);
        TreeNode yy = findNode(root , y);

        if(level(root , xx , 0) == level(root , yy ,0) && !isSiblings(root ,xx ,yy)) return true;
        
        return false;
    }

    private static TreeNode findNode(TreeNode node , int x){
        if(node == null) return null;

        if(node.val == x) return node;

        TreeNode n = findNode(node.left , x);
        if(n != null) return n;
        return findNode(node.right , x);
    }

    private static int level(TreeNode node , TreeNode x , int level){
        if(node == null) return 0;

        if(node == x) return level;

        int l = level(node.left , x , level + 1);

        if(l != 0) return l;

        return level(node.right , x , level + 1);
    }

    private static boolean isSiblings(TreeNode node , TreeNode x , TreeNode y){
        if(node == null) return false;

        return (
            (node.left == x && node.right == y) || 
            (node.left == y && node.right == x) || 
            isSiblings(node.left , x , y) || 
            isSiblings(node.right , x , y)
        );
    }

    // Invert Binary Tree
    public static TreeNode invertTree(TreeNode root) {
        if(root == null) return root;

        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        
        root.left = right;
        root.right = left;

        return root;
    }

    // Symmetric 
    public static  boolean isSymmetric(TreeNode root) {
        return root == null || isSymmetricHelp(root.left , root.right);
    }

    public static boolean isSymmetricHelp(TreeNode left , TreeNode right){
        if(left == null || right == null){
            return left == right;
        }

        if(left.val != right.val) return false;

        return isSymmetricHelp(left.left , right.right) && isSymmetricHelp(left.right , right.left); 

    }
    
    public static boolean isSymmetric_D(TreeNode root) {
        if(root == null) return true; 
        Queue<TreeNode> q = new LinkedList<>();

        q.add(root.left);
        q.add(root.right);

        while(!q.isEmpty()){
            TreeNode left = q.poll();
            TreeNode right = q.poll();

            if(left == null && right == null) continue;

            if(left == null || right == null){
                return false;
            }

            if(left.val != right.val) return false;

            q.offer(left.left);
            q.offer(right.right);

            q.offer(left.right);
            q.offer(right.left);

        }

        return true;
    }

    // Lowest Common Ancestor
    public static TreeNode lowestCommonAncestor(TreeNode root , TreeNode p , TreeNode q){
        
        if(root == null || p == root || q == root){
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if(left == null){
            return right;
        }
        else if(right == null){
            return left;
        }
        else{ // both left and right are not null we found the result
            return root;
        }
    }

    public static  TreeNode lowestCommonAncestorBST(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null ){
            return root;
        }

        int curr = root.val;
        if(curr < p.val && curr < q.val){
            return lowestCommonAncestorBST(root.right , p , q);
        }

        if(curr > p.val && curr > q.val){
            return lowestCommonAncestorBST(root.left , p , q);
        }

        return root;
    }


    // Convert Sorted Array to Binary Search Tree
    public static  TreeNode sortedArrayToBST(int[] nums) {
        int n = nums.length;
        return populate(nums , 0 , n - 1);
    }

    private static TreeNode populate(int nums[] , int start , int end){
        if(start > end) return null;

        int mid = start + (end - start) / 2;

        TreeNode node = new TreeNode(nums[mid]);

        node.left = populate(nums , start , mid - 1);
        node.right = populate(nums , mid + 1 , end);

        return node;
    }

    // Flatten Binary Tree to Linked List
    public static  void flatten(TreeNode root) {
        if(root == null) return;
        TreeNode current = root;

        while(current != null){
            if(current.left != null){
                TreeNode temp = current.left;
                while(temp.right != null){
                    temp = temp.right;
                }
                temp.right = current.right;
                current.right = current.left;
                current.left = null;
            }
            current = current.right;
        }
    }
     
    // Validate Binary Search Tree
    public static boolean isValidBST(TreeNode root) {
        if(root == null) return true;

        return isValidBST(root , null , null);
    }

    private static boolean isValidBST(TreeNode node , Integer low , Integer high){
        if(node == null) return true;

        if(low != null && node.val <= low) return false; // Apply on right
        if(high != null && node.val >= high) return false; // Apply on left 
        
        boolean left = true;
        boolean right = true;

        left = isValidBST(node.left , low , node.val); 
        right = isValidBST(node.right , node.val , high);

        return left && right;
    }

    // Kth Smallest Element in a BST
    private static int count = 0;
    public int kthSmallest(TreeNode root, int k) {
        count = 0;
        TreeNode ans = findKthSmallest(root , k);
        return ans.val;
    }

    private static TreeNode findKthSmallest(TreeNode node , int k){
        if(node == null){
            return null;
        }

        // Traverse left subtree
        TreeNode left = findKthSmallest(node.left , k);
        if(left != null){
            return left;
        }

        // Visit current node
        count++;
        if(count == k) return node;

        // Traverse right subtree
        return findKthSmallest(node.right , k);
    }

    //  Kth Largest Element in a BST
    private static int cnt;
    public static int kthLargest(Node root, int k) {
        cnt = 0;
        Node ans = findKthLargest(root , k );
        return (ans == null) ? -1 : ans.val ;
    }
    
    private static Node findKthLargest(Node node, int k){
        if(node == null) return null;
        
        Node right = findKthLargest(node.right , k);
        if(right != null) return right;
        
        cnt++;
        if(cnt == k) return node;
        
        return findKthLargest(node.left , k);
    }


    // Construct Binary Tree from Preorder and Inorder Traversal
    public static  TreeNode buildTreeBTPreIn(int[] preorder, int[] inorder) {
        if(preorder.length == 0) return null;

        int r = preorder[0];
        int ind = 0;

        for(int i = 0 ; i < inorder.length ; i++){
            if(inorder[i] == r){
                ind = i;
            }
        }

        TreeNode node = new TreeNode(r);
        
        node.left = buildTreeBTPreIn(Arrays.copyOfRange(preorder , 1 , ind + 1) , Arrays.copyOfRange(inorder , 0 , ind));
        node.right = buildTreeBTPreIn(Arrays.copyOfRange(preorder ,ind + 1 , preorder.length) , Arrays.copyOfRange(inorder , ind + 1 , inorder.length));

        return node;
    }

    // optimal 
    private static Map<Integer, Integer> mpp;
    private static int index;
    public TreeNode buildTreeBTPreIn_O(int[] preorder, int[] inorder) {
        int n = preorder.length;

        index = 0;
        mpp = new HashMap<>();

        for(int i = 0 ; i < n ; i++){
            mpp.put(inorder[i] , i);
        }

        return buildTreeBTPreIn_O(preorder , 0 , n - 1 );
    }

    private static TreeNode buildTreeBTPreIn_O(int[] preorder, int left , int right){
        if(left > right) return null;


        TreeNode node = new TreeNode(preorder[index]);
        
        int mid = mpp.get(preorder[index]);
        index++;

        node.left = buildTreeBTPreIn_O(preorder , left , mid - 1);
        node.right = buildTreeBTPreIn_O(preorder , mid + 1 , right);

        return node;
    }

    // Construct Binary Tree from Inorder and Postorder Traversal
    public TreeNode buildTreeInPost(int[] inorder, int[] postorder) {
        if(postorder.length == 0) return null;

        int r = postorder[postorder.length - 1];
        int ind = 0;

        for(int i = 0 ; i < inorder.length ; i++){
            if(inorder[i] == r) ind = i;
        }

        TreeNode root = new TreeNode(r);

        root.left = buildTreeInPost(Arrays.copyOfRange(inorder , 0 , ind) , Arrays.copyOfRange(postorder , 0 , ind ));
        root.right = buildTreeInPost(Arrays.copyOfRange(inorder , ind + 1 , inorder.length) , Arrays.copyOfRange(postorder , ind  , postorder.length - 1) ); 

        return root;
    }

    // optimal
    private static int idx;
    public TreeNode buildTreeInPost_O(int[] inorder, int[] postorder) {
        int n = postorder.length;

        idx = n - 1;
        Map<Integer , Integer> mpp = new HashMap<>();

        for(int i = 0; i < n ; i++){
            mpp.put(inorder[i] , i);
        }

        return buildTreeInPost_O(postorder , 0 ,  n - 1 , mpp);
    }

    private static TreeNode buildTreeInPost_O(int [] postorder , int left , int right , Map<Integer , Integer> mpp){
        if(left > right) return null;

        TreeNode root = new TreeNode(postorder[idx]);

        int mid = mpp.get(postorder[idx]);
        idx--;

        root.right = buildTreeInPost_O(postorder , mid + 1 , right , mpp); 
        root.left = buildTreeInPost_O(postorder , left , mid - 1, mpp);
        
        
        return root;
    }

    //  Serialize and Deserialize Binary Tree
    public class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            List<String> ds = new ArrayList<>();
            helperSer(root , ds);
            String ans = String.join("," , ds);
            return ans;
        }

        private static void helperSer(TreeNode node , List<String> ds){
            if(node == null){
                ds.add("null");
                return;
            }
            ds.add(String.valueOf(node.val));
            helperSer(node.left , ds);
            helperSer(node.right , ds);
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if(data == null || data.isEmpty()) return null;
            Queue<String> q = new LinkedList<>(Arrays.asList(data.split(",")));
            TreeNode root = helperDeSer(q);
            return root;
        }

        private static TreeNode helperDeSer(Queue<String> q){
            String val = q.poll();

            if(val.equals("null")) return null;
            
            TreeNode node = new TreeNode(Integer.parseInt(val));
            node.left = helperDeSer(q);
            node.right = helperDeSer(q);

            return node;
        }
    }

    // Path Sum
    public static boolean hasPathSum(TreeNode root, int targetSum) {
        if(root == null) return false;
        return hasPathSum(root , targetSum , 0);
    }

    private static  boolean hasPathSum(TreeNode node , int t , int sum){
        if(node == null){
            return false;
        }

        sum += node.val;
        if(node.left == null && node.right == null) return (sum == t);

        
        return hasPathSum(node.left , t , sum) ||  hasPathSum(node.right , t , sum);
    }

    // Path Sum - II
    public static  List<List<Integer>> pathSumII(TreeNode root, int targetSum) {
        List<List<Integer>> ans = new ArrayList<>();
        findPaths(root , targetSum , 0 , new ArrayList<Integer>() , ans);
        return ans;
    }

    private static void findPaths(TreeNode node , int t , int sum , ArrayList<Integer> path , List<List<Integer>> ans){
        if(node == null) return;
        
        sum += node.val;
        path.add(node.val);

        if(node.left == null && node.right == null && sum == t){
            ans.add(new ArrayList<>(path));
        }
        else{
            findPaths(node.left , t , sum , path , ans);
            findPaths(node.right , t , sum , path , ans);
        }

        path.remove(path.size() - 1);
    }
    
    // PathSum - III
    public static int pathSumIII(TreeNode root, int targetSum) {
        Map<Long , Integer> mpp = new HashMap<>();
        mpp.put(0L , 1);
        return cntPaths(root , mpp , targetSum , 0L);
    }

    private static int cntPaths(TreeNode node , Map<Long , Integer> mpp , int t ,  long sum){
        if(node == null) return 0;

        sum += node.val;
        
        int paths = mpp.getOrDefault(sum - t , 0);

        mpp.put(sum , mpp.getOrDefault(sum , 0) + 1);

        paths += cntPaths(node.left , mpp , t , sum);
        paths += cntPaths(node.right , mpp , t , sum);

        mpp.put(sum  , mpp.get(sum) - 1);

        return paths;
    }

    // Sum Root to Leaf Numbers
    public static  int sumNumbers(TreeNode root) {
        return sumNumbers(root , 0);
    }

    private static int sumNumbers(TreeNode node , int sum){
        if(node == null) return 0;

        sum = sum * 10 + node.val;

        if(node.left == null && node.right == null){
            return sum;
        }

        return sumNumbers(node.left , sum) + sumNumbers(node.right , sum);
    }

    // Vertical Order Traversal of a Binary Tree
    private static class PairT{
        TreeNode node;
        int row;
        int col;

        PairT(TreeNode node , int row , int col){
            this.node = node;
            this.row = row;
            this.col = col;
        }
    }

    public static List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();

        if(root == null) return ans;

        Map<Integer , List<int[]>> mpp = new HashMap<>();

        Queue<PairT> q = new LinkedList<>();
        q.add(new PairT(root , 0 , 0));

        int minC = 0 , maxC = 0;

        while(!q.isEmpty()){
            PairT p = q.poll();
            TreeNode node = p.node;
            int row = p.row;
            int col = p.col;

            if(!mpp.containsKey(col)){
                mpp.put(col , new ArrayList<int[]>());
            }

            mpp.get(col).add(new int[]{row , node.val});

            minC = Math.min(col , minC);
            maxC = Math.max(col , maxC);

            if(node.left != null){
                q.add(new PairT(node.left , row + 1 , col - 1));
            }

            if(node.right != null){
                q.add(new PairT(node.right , row + 1 , col + 1));
            }

        }

        for(int c = minC ; c <= maxC ; c++){
            List<int[]> temp = mpp.get(c);
            if(temp == null) continue;

            Collections.sort(temp , new Comparator<int[]>(){
                @Override
                public int compare(int a[] , int b[]){
                    if(a[0] != b[0]){ // compare by row
                        return Integer.compare(a[0] , b[0]);
                    }
                    else{ // same row -> compare by node value
                        return Integer.compare(a[1] , b[1]);
                    }
                }
            });


            List<Integer> colVals = new ArrayList<>();

            for(int[] arr : temp){
                colVals.add(arr[1]);
            }
            
            ans.add(colVals);
            
        }

        return ans;

    }

    // Recover Binary Search Tree - Correct Binary Tree That Has Two Nodes Swapped
    private static TreeNode first ;
    private static TreeNode second ;
    private static TreeNode prev;
    public static  void recoverTree(TreeNode root) {
        first = null;
        second = null;
        prev = null;
        iot(root);

        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }

    private static void iot(TreeNode node){
        if(node == null) return;
        iot(node.left);

        if(prev != null && prev.val > node.val){
            if(first == null){
                first = prev;
            }
            second = node;
        }

        prev = node;
        iot(node.right);
    }

    // Longest Univalue Path
    private static int ansUni;
    public static  int longestUnivaluePath(TreeNode root) { 
        ansUni = 0;
        findUniVal(root);
        return ansUni;
    }

    private static int findUniVal(TreeNode node){
        if(node == null) return 0;

        int leftLen = findUniVal(node.left);
        int rightLen = findUniVal(node.right);

        int leftPath = 0 , rightPath = 0;

        if(node.left != null && node.left.val == node.val){
            leftPath += leftLen + 1;
        }

        if(node.right != null && node.right.val == node.val){
            rightPath += rightLen + 1;
        }

        ansUni = Math.max(leftPath + rightPath , ansUni);

        return Math.max(leftPath , rightPath);
    }
    
    // Maximum Width of Binary Tree
    private static  class Tuple{
        TreeNode node;
        int num;

        Tuple(TreeNode node , int num){
            this.node = node;
            this.num = num;
        }
    }
    public static  int widthOfBinaryTree(TreeNode root) {
        if(root == null) return 0;

        Queue<Tuple> q = new LinkedList<>();
        q.add(new Tuple(root , 0));

        int ans = 0;

        while(!q.isEmpty()){
            int size = q.size();

            int first = 0 , last = 0;

            int min = q.peek().num;

            for(int i = 0; i < size ; i++){
                Tuple curr = q.poll();
                int num = curr.num - min;
                TreeNode node = curr.node;

                if(i == 0) first = num;
                if(i == size - 1) last = num;

                if(node.left != null){
                    q.offer(new Tuple(node.left , 2 * num + 1));
                }

                if(node.right != null){
                    q.offer(new Tuple(node.right , 2 * num + 2));
                }
            }

            ans = Math.max(last - first + 1 , ans);
        }

        return ans;
    }

    // Children Sum in a Binary Tree - Root Equals Sum of Children
    public boolean isSumProperty(Node root) {
        if(root == null) return true;
        
        if(root == null || root.left == null && root.right == null) return true;
        
        int left = 0 , right = 0;
        
        if(root.left != null){
            left = root.left.val;
        }
        
        if(root.right != null){
            right = root.right.val;
        }
        
        int sum = left + right;
        
        return (sum == root.val) && isSumProperty(root.left) && isSumProperty(root.right); 
    }

    // Convert an arbitrary Binary Tree to a tree that holds Children Sum Property
    public static void changeTree(TreeNode root){
        if(root == null) return ;

        int sumChild = 0;
        if(root.left != null) sumChild += root.left.val;
        if(root.right != null) sumChild += root.right.val;

        if(sumChild >= root.val){
            root.val = sumChild;
        }
        else{
            if(root.left != null) root.left.val = root.val;
            if(root.right != null) root.right.val = root.val;
        }

        changeTree(root.left);
        changeTree(root.right);

        int tot = 0;
        if(root.left != null) tot += root.left.val;
        if(root.right != null) tot += root.right.val;

        // Leaf node check
        if(root.left != null || root.right != null) root.val = tot;

    }

    //  Count Complete Tree Nodes
    public static int countNodes(TreeNode root) {
       if(root == null) return 0;

       int lh = getLeftH(root);
       int rh = getRightH(root);

       if(lh == rh) return (int) Math.pow(2 , lh) - 1;
       
       return 1 + countNodes(root.left) + countNodes(root.right);
    }

    private static  int getLeftH(TreeNode node){
        int h = 0;

        while(node != null){
            h++;
            node = node.left;
        }

        return h;
    }

    private static  int getRightH(TreeNode node){
        int h = 0;

        while(node != null){
            h++;
            node = node.right;
        }

        return h;
    }

    // All Nodes Distance K in Binary Tree
    public static List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        if(root == null) return null;

        Queue<TreeNode> q = new LinkedList<>();

        q.offer(root);

        Map<TreeNode, TreeNode> mpp = new HashMap<>();
    
        while(!q.isEmpty()){
            TreeNode curr = q.poll();

            if(curr.left != null){
                mpp.put(curr.left , curr);
                q.offer(curr.left);
            }

            if(curr.right != null){
                mpp.put(curr.right , curr);
                q.offer(curr.right);
            }

        }

        List<Integer> ans = distanceK(target , k , mpp);

        return ans;
    }

    private static List<Integer> distanceK(TreeNode target , int k , Map<TreeNode , TreeNode> mpp){
        
        List<Integer> res = new ArrayList<>();

        Map<TreeNode , Boolean> vis = new HashMap<>();
        vis.put(target , true);

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(target);

        int currLevel = 0;

        while(!q.isEmpty()){

            int size = q.size();

            if(currLevel == k) break;
            currLevel++;

            for(int i = 0 ; i < size ; i++){
                TreeNode node = q.poll();

                if(node.left != null && vis.get(node.left) == null){
                    q.offer(node.left);
                    vis.put(node.left , true);
                }

                if(node.right != null && vis.get(node.right) == null){
                    q.offer(node.right);
                    vis.put(node.right , true);
                }

                if(mpp.get(node) != null && vis.get(mpp.get(node)) == null){
                    q.offer(mpp.get(node));
                    vis.put(mpp.get(node) , true);
                }
            }        

        }

        while(!q.isEmpty()){
            res.add(q.poll().val);
        } 

        return res;
    }

    // Amount of Time for Binary Tree to Be Infected
    public static int amountOfTime(TreeNode root, int start) {
        if(root == null) return 0;

        Map<TreeNode , TreeNode> mpp = new HashMap<>();

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        TreeNode startNode = null;

        while(!q.isEmpty()){
            TreeNode node = q.poll();

            if(node.val == start){
                startNode = node;
            }

            if(node.left != null){
                mpp.put(node.left , node);
                q.offer(node.left);
            }

            if(node.right != null){
                mpp.put(node.right , node);
                q.offer(node.right);
            }
        }

        if(startNode != null){
            return amountOfTime(startNode , mpp);
        }
        else return 0;
    }

    private static int amountOfTime(TreeNode start , Map<TreeNode , TreeNode> mpp){

        Map<TreeNode , Boolean> vis = new HashMap<>();
        vis.put(start , true);

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(start);

        int timer = 0;

        while(!q.isEmpty()){
            int size = q.size();

            timer++;

            for(int i = 0 ; i < size ; i++){
                TreeNode node = q.poll();

                if(node.left != null && vis.get(node.left) == null){
                    q.offer(node.left);
                    vis.put(node.left , true);
                }

                if(node.right != null && vis.get(node.right) == null){
                    q.offer(node.right);
                    vis.put(node.right , true);
                }

                if(mpp.get(node) != null && vis.get(mpp.get(node)) == null){
                    q.offer(mpp.get(node));
                    vis.put(mpp.get(node) , true);
                }
            }
        }

        return timer - 1;
    }
    
    // Ceil in BST
    public static int findCeil(Node root, int x) {
        if(root == null) return -1;
        int ceil = -1;
        
        while(root != null){
            if(root.val == x) return root.val;
            else if(root.val > x){
                ceil = root.val;
                root = root.left;
            }
            else{
                root = root.right;
            }
        }
        
        return  ceil;
    }

    // Floor in BST
    public static int floor(Node root, int x) {
        
        if(root == null) return -1;
        
        int floor = -1;
        
        while(root != null){
            if(root.val == x) return root.val;
            else if(root.val < x){
                floor = root.val;
                root = root.right;
            }
            else root = root.left;
        }
        
        return floor;
        
    }

    // Insert into a Binary Search Tree
    public static  TreeNode insertIntoBST(TreeNode root, int val) {
        // Idea is to insert in a leaf node
        if(root == null ){
            return new TreeNode(val);
        }

        if(val < root.val){
            root.left = insertIntoBST(root.left , val);
        }
        else{
            root.right = insertIntoBST(root.right , val);
        }

        //  insert(root , val);

        return root;
    }

    private static void insert(TreeNode node , int val){
        if(val < node.val){
            if(node.left == null){
                node.left = new TreeNode(val);
                return;
            }
            else insert(node.left , val);
        }
        else{
            if(node.right == null){
                node.right = new TreeNode(val);
                return;
            }
            else insert(node.right , val);
        }
    }

    // Delete Node in a BST
    public static TreeNode deleteNode(TreeNode root, int key) {
        if(root == null) return null;

        if(root.val == key){
           return manageDel(root);
        }

        TreeNode dummy = root;

        while(root != null){
            if(root.val > key){
                if(root.left != null && root.left.val == key){
                    root.left = manageDel(root.left );
                    break;
                }
                else root = root.left;
            }
            else{
                if(root.right != null && root.right.val == key){
                    root.right = manageDel(root.right);
                    break;
                }
                else root = root.right;
            }
        }

        return dummy;
    }

    private static TreeNode manageDel(TreeNode node ){
        if(node.left == null) return node.right;
        else if(node.right == null) return node.left;

        TreeNode rightChildren = node.right;
        TreeNode lastRight = findLastRight(node.left);
        lastRight.right = rightChildren;

        return node.left;
    }

    private static TreeNode findLastRight(TreeNode node){
        if(node.right == null) return node;
        return findLastRight(node.right);
    }

    // Construct Binary Search Tree from Preorder Traversal
    private static int preIdx;
    public static TreeNode bstFromPreorder(int[] preorder) {
        preIdx = 0;
        return construct(preorder , Integer.MIN_VALUE , Integer.MAX_VALUE);
    }

    private static TreeNode construct(int preorder[] , int low , int high){
        if(preIdx == preorder.length) return null;

        int val = preorder[preIdx];

        if(val < low || val > high){
            return null;
        }

        TreeNode root = new TreeNode(val);
        preIdx++;

        root.left = construct(preorder , low , val);
        root.right = construct(preorder , val , high);

        return root;
    }

    // Predecessor and Successor
    public static ArrayList<Node> findPreSuc(Node root, int key) {
        Node successor = null;
        Node predecessor = null;
        Node curr = root;
        
        // Find the node with value == key, tracking potential pred/succ
        while(curr != null && curr.val != key){
            if(curr.val > key){
                successor = curr;
                curr = curr.left;
            }else{
                predecessor = curr;
                curr = curr.right;
            }
        }
        
        // If we found the node with data == key
        if(curr != null){
            if(curr.left != null){
                Node temp = curr.left;
                
                // Predecessor = max in left subtree
                while(temp.right != null){
                    temp = temp.right;
                }
                
                predecessor = temp;
            }
            
            if(curr.right != null){
                Node temp = curr.right;
                
                // Successor = min in right subtree
                while(temp.left != null){
                    temp = temp.left;
                }
                
                successor = temp;
            }
        }
        

        
        ArrayList<Node> ans = new ArrayList<>();
        ans.add(predecessor);
        ans.add(successor);
        
        
        return ans;
        
    }

    // Binary Search Tree Iterator
    class BSTIterator {
    private Stack<TreeNode> st;

    public BSTIterator(TreeNode root) {
        st = new Stack<>();
        pushLeft(root);
    }

    private void pushLeft(TreeNode node) {
        while (node != null) {
            st.push(node);
            node = node.left;
        }
    }
    
    public int next() {
        TreeNode nextNode = st.pop();
        int nextVal = nextNode.val;
        
        if(nextNode.right != null){
            pushLeft(nextNode.right);
        }
        
    
        return nextVal;
    }
    
    public boolean hasNext() {
        return (!st.isEmpty());
    }
}

    // Largest BST
    private static class NodeValue{
        public int maxNode , minNode , maxSize;
        
        public NodeValue(int maxSize , int minNode , int maxNode){
            this.maxSize = maxSize;
            this.minNode = minNode;
            this.maxNode = maxNode;
        }
    }
    
    private static NodeValue largestSub(Node root){
        if(root == null){
            return new NodeValue(0 , Integer.MAX_VALUE , Integer.MIN_VALUE);
        }
        
        NodeValue left = largestSub(root.left);
        NodeValue right = largestSub(root.right);
        
        if(root.val > left.maxNode && root.val < right.minNode){
            int size = left.maxSize + right.maxSize + 1;
            int minVal = Math.min(left.minNode , root.val);
            int maxVal = Math.max(right.maxNode , root.val);
            return new NodeValue(size , minVal , maxVal);
        }
        
        return new NodeValue(
            Math.max(left.maxSize , right.maxSize),
            Integer.MIN_VALUE,
            Integer.MAX_VALUE
        );
    }
   
    public static int largestBst(Node root) {
        NodeValue ans = largestSub(root);
        return ans.maxSize;
    }

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n3 = new TreeNode(3);
        TreeNode leftChild = new TreeNode(2, n1, n3);

        TreeNode n5 = new TreeNode(5);
        TreeNode n7 = new TreeNode(7);
        TreeNode rightChild = new TreeNode(6, n5, n7);

        TreeNode root = new TreeNode(4, leftChild, rightChild);

        System.out.println("Root value: " + root.val);
        System.out.println("Left child value: " + root.left.val);
        System.out.println("Right child value: " + root.right.val);

        // Level Order Traversal
        List<List<Integer>> levels = levelOrder(root);
        System.out.println("Level Order Traversal: " + levels);

        // Preorder
        List<Integer> preorder = preorderTraversal_R(root);
        System.out.println("Preorder Traversal: " + preorder);

        // Inorder
        List<Integer> inorder = inorderTraversal_R(root);
        System.out.println("Inorder Traversal: " + inorder);

        // Postorder
        List<Integer> postorder = postorderTraversal_R(root);
        System.out.println("Postorder Traversal: " + postorder);

        // All traversals in one Function
        preInPost(root);

        // Maximum Depth in the Binary Tree
        System.out.println("Max Depth : " + maxDepth(root));

        // Check for Balanced Binary Trees
        System.out.println(isBalanced_O(root));

        // Diameter of a binary tree
        System.out.println("Diameter of BT : " + diameterOfBinaryTree_O(root));

        // Max Path Sum
        System.out.println("Max Path Sum: " + maxPathSum(root));

        // Check for Identical Trees
        TreeNode identicalRoot = new TreeNode(2, new TreeNode(1), new TreeNode(3));
        System.out.println("Is Same Tree: " + isSameTree(root, identicalRoot));

        // Zig-Zag Traversal
        List<List<Integer>> zigzag = zigzagLevelOrder(root);
        System.out.println("Zig-Zag Traversal: " + zigzag);

        // Symmetric
        TreeNode symmetricRoot = new TreeNode(1, new TreeNode(2, null, new TreeNode(3)), new TreeNode(2, null, new TreeNode(3)));
        System.out.println("Is Symmetric: " + isSymmetric(symmetricRoot));

        // Lowest Common Ancestor
        TreeNode lca = lowestCommonAncestor(root, root.left, root.right);
        System.out.println("Lowest Common Ancestor of " + root.left.val + " and " + root.right.val + ": " + (lca != null ? lca.val : "null"));

        // Serialize and Deserialize Binary Tree
        Trees.Codec codec = new Trees().new Codec();
        String serialized = codec.serialize(root);
        System.out.println("Serialized tree: " + serialized);

        TreeNode deserializedRoot = codec.deserialize(serialized);
        System.out.println("Deserialized root value: " + deserializedRoot.val);
        System.out.println("Is same after deserialize: " + isSameTree(root, deserializedRoot));

        // Vertical Order Traversal (LeetCode 987)
        List<List<Integer>> vertical = verticalTraversal(root);
        System.out.println("Vertical Order Traversal: " + vertical);

        // Maximum Width of Binary Tree
        System.out.println("Maximum Width of Binary Tree: " + widthOfBinaryTree(root));

        
        // Convert an arbitrary Binary Tree to a tree that holds Children Sum Property
        changeTree(root);
        System.out.println("After changing tree to satisfy children sum property:");
        List<List<Integer>> changedTree = levelOrder(root);
        System.out.println("Level Order Traversal: " + changedTree);

    }

    
}
