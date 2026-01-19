public class SegmentTree {
    
    private class Node{
        int data;
        int startInterval;
        int endInterval;
        Node left;
        Node right;

        public Node(int startInterval , int endInterval){
            this.startInterval = startInterval;
            this.endInterval = endInterval;
        }
    }

    Node root;
    public SegmentTree(int arr[]){
        this.root = constructTree(arr , 0 , arr.length -1);
    }

    private Node constructTree(int arr[] , int start , int end){
        // leaf node
        if(start == end){
            Node leaf = new Node(start , end);
            leaf.data = arr[start];
            return leaf;
        }

        // create new node with index you are at
        Node node = new Node(start , end);
        int mid = (start + end) / 2;
        node.left = constructTree(arr, start, mid); // Left Subtree
        node.right = constructTree(arr, mid + 1, end); // Right SubTree
        node.data = (node.left.data) + (node.right.data); // Currrent node Data
        return node;
    }

    // Displaying
    public void display(){
        display(this.root);
    }

    private void display(Node node){
        if (node == null) {
            return;
        }

        String str = "";

        // For left child
        if(node.left != null){
            str += "L: [" + node.left.startInterval + "-" + node.left.endInterval + "]: " + node.left.data + "\t";
        } else {
            str += "L: null\t";
        }

        // For current node
        str += "ROOT: [" + node.startInterval + "-" + node.endInterval + "]: " + node.data + "\t";

        // For right child
        if(node.right != null){
            str += "R: [" + node.right.startInterval + "-" + node.right.endInterval + "]: " + node.right.data;
        } else {
            str += "R: null";
        }

        System.out.println(str);

        // Recursive call
        display(node.left);
        display(node.right);
    }

    // Querying
    public int query(int qsi , int qei){
        return this.query(this.root , qsi, qei);
    }

    private int query(Node node , int qsi , int qei){
        // If node interval is entirely within query interval, return node's sum
        /*  case1 : Completely Inside
            qs ----------- qe
                    L ----- R

            -> L >= qs  AND  R <= qe
        */
        if(node.startInterval >= qsi && node.endInterval <= qei){ // Completely Inside 
            return node.data;
        }
        /*  case 2 : Completely Outside the interval
            qs ----- qe          (OR)          qs ----- qe
                       L ----- R      L ----- R
            
            -> R < qs  OR  L > qe
        */
        else if(node.startInterval >  qei || node.endInterval < qsi){ //  Completely Outside the interval
            
            return 0;
        }
        /* case 3 : Partial overlap
            qs ----------- qe
                    L ------------- R

                (OR)
            
                    qs ----------- qe
            L ------------- R

            NOT(total overlap) AND NOT(no overlap)
        */
        else{ // If node interval partially overlaps query interval, sum left and right children
            int leftSum = this.query(node.left, qsi, qei);
            int rightSum = this.query(node.right, qsi, qei);
            return leftSum + rightSum;
        }
    }

    // Updating
    public void update(int index , int value){
        this.root.data = update(this.root , index , value);
    }

    private int update(Node node , int index , int value){
        if(index >= node.startInterval && index <= node.endInterval){
            if(index == node.startInterval && index == node.endInterval){ // Leaf node (start == end), update the data directly
                node.data = value;
                return node.data;
            }
            else{ // Internal node: update children and recalculate its data as sum of children
                int leftAns = update(node.left , index , value);
                int rightAns = update(node.right , index , value);
                node.data = leftAns + rightAns;
                return node.data;
            }
        }

        // If the index is outside this node's range, no update needed here
        return node.data;
    }

    

    public static void main(String[] args) {
        int arr[] = {3,8,7,6,-2,-8,4,9};
        SegmentTree tree = new SegmentTree(arr);
        System.out.println("The sum between range [2,6] : " + tree.query(2, 6));
        System.out.println("\n\n\nBefore Updation");
        tree.display();

        System.out.println("\n\n\nAfter Updation");
        tree.update(3, 14);
        tree.display();

    }

}
