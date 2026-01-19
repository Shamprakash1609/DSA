import java.util.*;

public class BinaryTree {

    public BinaryTree(){

    }
    
    private static class Node{
        int val;
        Node left;
        Node right;

        public Node(int value){
            this.val = value;
        }
    }

    private Node root;

    // Insert
    public void populate(Scanner sc){
        System.out.println("Enter the root node : ");
        int value = sc.nextInt();
        root = new Node(value);
        populate(sc, root);
    }

    private void populate(Scanner sc , Node node){
        System.out.println("Do you want to enter the left node ? ");
        boolean left = sc.nextBoolean();
        if(left){
            System.out.println("Enter the left node for " + node.val);
            int value = sc.nextInt();
            node.left = new Node(value);
            populate(sc , node.left);
        }

        System.out.println("Do you want to enter the right node ? ");
        boolean right = sc.nextBoolean();
        if(right){
            System.out.println("Enter the right node for " + node.val);
            int value = sc.nextInt();
            node.right = new Node(value);
            populate(sc , node.right);
        }
    }


    // Display
    public void display(){
        display(this.root , "");
    }

    private void display(Node node , String indent){
        if(node == null) return;
        System.out.println(indent + node.val);
        display(node.left  , indent + "\t");
        display(node.right , indent + "\t");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BinaryTree tree = new BinaryTree();
        tree.populate(sc);
        tree.display();
    }
}
