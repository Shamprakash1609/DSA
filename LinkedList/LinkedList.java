import java.util.*;

class Node{
    int data;
    Node next;

    Node(int data1 , Node next1){
        this.data = data1;
        this.next = next1;
    }

    Node(int data1){
        this.data = data1;
        this.next = null;
    }
};

public class LinkedList {

    public static Node convertArrtoLL(int[] arr){
        // TC : O(N)
        Node head = new Node(arr[0]);
        Node mover = head;

        for(int i = 1 ; i<arr.length ; i++){
            Node temp = new Node(arr[i]);
            mover.next = temp;
            mover = temp;
        }

        return head;
    }

    public static int lengthOfLL(Node Head){
        int cnt = 0;

        Node temp = Head;

        while(temp != null){
            cnt++;
            temp = temp.next;
        }

        return cnt;
    }

    public static boolean  checkIfPresent(Node Head , int val){

        Node temp = Head;

        while (temp != null) {
            if(temp.data == val) return true;
            temp = temp.next;
        }

        return false;
    }
    
    public static void printLL(Node head){
        System.out.print("The Linked list is : ");

        while(head != null){
            System.out.print(head.data + " ");
            head = head.next;
        }

    }

    // Deletion
    public static Node removeHead(Node head){
        if(head == null) return head;
        head = head.next;
        return head;
    }

    public static Node removeTail(Node head){
        if(head == null || head.next == null) return null;
        Node temp = head;
        while(temp.next.next != null){
            temp = temp.next;
        }
        temp.next = null;

        return head;
    }

    public static Node deleteK(Node head , int k){
        if(head == null) return head;
        if(k == 1){
            head = head.next;
            return head;
        }

        int cnt = 0;
        Node temp = head;
        Node prev = null;

        while(temp != null){
            cnt++;
            if(cnt == k){
                prev.next = prev.next.next;
                break;
            }
            prev = temp;
            temp = temp.next;
        }

        return head;
    }
    
    public static Node deleteVal(Node head , int el){
        if(head == null) return head;
        if(head.data == el){
            head = head.next;
            return head;
        }

        Node temp = head;
        Node prev = null;

        while(temp != null){

            if(temp.data == el){
                prev.next = prev.next.next;
                break;
            }
            prev = temp;
            temp = temp.next;
        }

        return head;
    }
    
    // insertion
    public static Node insertHead(Node head , int val){
        Node temp = new Node(val , head);
        return temp;
    }

    public static Node insertTail(Node head , int val){
        if(head == null) return new Node(val);

        Node temp = head;

        while(temp.next != null){
            temp = temp.next;
        }

        Node newNode = new Node(val);
        temp.next = newNode;

        return head;
    }

    public static Node insertK(Node head , int val , int k){
        if(head == null){
            if(k == 1) return new Node(val);
            else return head;
        }

        if(k == 1){
            Node temp = new Node(val , head);
            return temp;
        }

        int cnt = 0;
        Node temp = head;

        while(temp != null){
            cnt++;
            if(cnt == k-1){
                Node newNode = new Node(val , temp.next);
                temp.next = newNode;
                break;
            }
            temp = temp.next;
        }

        return head;
    }

    // insert the data before the value specified
    public static Node insertBeforeVal(Node head , int val , int before){
        if(head == null){
            return null;
        }

        if(head.data == before){
            Node temp = new Node(val , head);
            return temp;
        }


        Node temp = head;

        while(temp != null){

            if(temp.next.data == before){
                Node newNode = new Node(val , temp.next);
                temp.next = newNode;
                break;
            }
            temp = temp.next;
        }

        return head;
    }


    public static void main(String[] args) {
        int arr[] = {1,2,3,4,5};

        Node y = new Node(arr[0] , null);

        System.out.println(y.data);

        Node head = convertArrtoLL(new int[]{7,3,6,9});
        System.out.println(head.data);

        // Traversal
        Node temp = head;
        while(temp != null){
            System.out.print(temp.data + " ");
            temp = temp.next;
        }

        System.out.println("\nThe length of the linked list is : "+lengthOfLL(head));
        System.out.println("is the value present in the linked list : " + checkIfPresent(head, 6));


        // Deletion
        // head = removeHead(head);
        // head = removeTail(head);
        // head = deleteK(head, 2);
        // head = deleteVal(head, 6);

        // Insertion
        // head = insertHead(head, 8);
        // head = insertTail(head , 10);
        // head = insertK(head, 100, 2);
        head = insertBeforeVal(head, 500, 6);
        printLL(head);   

    }
}
