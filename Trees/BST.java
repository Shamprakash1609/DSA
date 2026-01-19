public class BST {
    
    public class Node{
        private int val;
        private int height;
        private Node left;
        private Node right;

        public Node(int value){
            this.val = value;
            this.height = 0;
        }

        public int getValue(){
            return val;
        }
        
    }

    private Node root;

    public BST(){}

    public int height(Node node){
        if(node == null) return -1;
        return node.height;
    }

    public boolean isEmpty(){
        return root == null;
    }

    // Insert
    public void insert(int value){
        root = insert(value , root);
    }

    private Node insert(int value , Node node){
        if(node == null){
            node = new Node(value);
            return node;
        }
        if(value < node.val){
            node.left = insert(value , node.left);
        }

        if(value > node.val){
            node.right = insert(value , node.right);
        }
        node.height = Math.max(height(node.left) , height(node.right)) + 1;

        return node;
    }
    
    // Check for Balanced
    public boolean balanced(){
        return balanced(root);
    }

    private boolean balanced(Node node){
        if(node == null) return true;
        return Math.abs(height(node.left) - height(node.right)) <= 1 && balanced(node.left) && balanced(node.right);
    }

    // Populate with multiple items
    public void populate(int nums[]){
        for(int i = 0 ; i < nums.length ; i++){
            this.insert(nums[i]);
        }
    }

    // Populate sorted array
    public void populateSorted(int nums[]){
        populateSorted(nums , 0 , nums.length - 1);
    }

    private void populateSorted(int nums[] , int start , int end){
        if(start >= end) return;

        int mid = (start + end) / 2;
        this.insert(nums[mid]);
        populateSorted(nums , start , mid);
        populateSorted(nums , mid + 1, end);
    }

    // Display
    public void display(){
        display(root , "Root Node : ");
    }

    private void display(Node node , String details){
        if(node == null) return;
        System.out.println(details + node.getValue() + " (height=" + node.height + ")");
        display(node.left , "Left Node of " + node.getValue() + " : ");
        display(node.right ,"Right Node of " + node.getValue() + " : ");
    }

    //  Traversals
    public void inorder(Node node){
        if(node == null) return;
        inorder(node.left);
        System.out.print(node.getValue() + " ");
        inorder(node.right);
    }

    public void postorder(Node node){
        if(node == null) return;
        postorder(node.left);
        postorder(node.right);
        System.out.print(node.getValue() + " ");
    }

    public void preorder(Node node){
        if(node == null) return;
        System.out.print(node.getValue() + " ");
        preorder(node.left);
        preorder(node.right);
    }



    public static void main(String[] args) {
        BST tree = new BST();
        // int nums [] = {5 , 2 ,7 ,1 ,4 ,6 ,9 , 8 ,3 ,10};
        int nums [] = {4,2,7,1,3,6,9};
        // int nums [] = {1 , 2 ,3 ,4};
        tree.populate(nums);

        tree.display();

        System.out.println("Does the Tree is a Balanced Binary Search Tree ? " + tree.balanced());

        // tree.inorder(tree.root);
        // tree.postorder(tree.root);
        tree.preorder(tree.root);


    }

}
