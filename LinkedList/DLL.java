

class Node{
    int data; 
    Node next; 
    Node back;

    Node(int data1 , Node next1 , Node back1){
        this.data = data1;
        this.next = next1;
        this.back = back1;
    }

    Node(int data1){
        this.data = data1;
        this.next = null;
        this.back = null;
    }
}


public class DLL {
    
    public static Node convertArr2Dll(int[] arr){
        Node head = new Node(arr[0]);
        Node prev = head;

        for(int i = 01 ; i< arr.length ; i++){
            Node temp = new Node(arr[i] , null , prev);
            prev.next = temp;
            prev = temp;
        }

        return head;
    }

    public static int lengthOfDll(Node head){
        Node temp = head;

        int cnt = 0;
        while(temp.next != null){
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

    public static void printDLL(Node head){
        System.out.print("The Linked list is : ");

        while(head != null){
            System.out.print(head.data + " ");
            head = head.next;
        }

    }


    // Deletion
    public static Node deleteHead(Node head){
        if(head == null || head.next == null){
            return null;
        }

        Node prev = head;
        head = head.next;
        head.back = null;
        prev.next = null;

        return head;
    }

    public static Node deleteTail(Node head){
        if(head == null || head.next == null){
            return null;
        }

        Node tail = head;

        while(tail.next != null){
            tail = tail.next;
        }

        Node newTail = tail.back;
        newTail.next = null;
        tail.back = null;

        return head;
    }

    public static Node deleteK(Node head , int k){
        if(head == null){
            return null;
        }

        if(k == 1){
            Node prev = head;
            head = head.next;
            head.back = null;
            prev.next = null;

            return head;
        }

        int cnt = 0;
        Node kNode = head;
        while(kNode != null){
            cnt++;
            if(cnt == k) break;

            kNode = kNode.next;
        }

        Node prev = kNode.back;
        Node front = kNode.next;
        
        if(prev == null && front == null){
            return null;
        }

        else if(prev == null){
            return deleteHead(head);
        }

        else if(front == null){
            return deleteTail(head);
        }

        prev.next = front;
        front.back = prev;

        return head;

    }

    public static void deleteNode(Node temp){
        Node prev = temp.back;
        Node front = temp.next;

        if(front == null){
            prev.next = null;
            temp.back = null;
            return;
        }

        prev.next = front;
        front.back = prev;

        temp.next = null;
        temp.back = null;

    }


    // Insertion
    public static Node insertBefHead(Node head , int val){
        Node newHead = new Node(val , head , null);
        head.back = newHead;

        return newHead;
    }

    public static Node insertBefTail(Node head , int val){
        Node tail = head;

        while(tail.next != null){
            tail = tail.next;
        }

        Node prev = tail.back;
        Node newNode = new Node(val , tail , prev);
        prev.next = newNode;
        tail.back = newNode;
        return head;
    }

    public static Node insertBefKthEle(Node head , int k , int val){
        if(k == 1){
            return insertBefHead(head, val);
        }
        Node temp = head;
        int cnt = 0;
        while(temp != null){
            cnt++;
            if(cnt == k) break;
            temp = temp.next;
        }

        Node prev = temp.back;
        Node newNode = new Node(val , temp , prev);
        prev.next = newNode;
        temp.back = newNode;
        return head;
    }

    public static void insertBefNode(Node node , int val){
        Node prev = node.back;
        Node newNode = new Node(val , node , prev);
        prev.next = node;
        node.back = newNode;

    }

    public static void main(String[] args) {
        int[] arr = {10, 20, 30, 40, 50 , 60 , 70 , 80};
        Node head = convertArr2Dll(arr);

        System.out.println("Original Doubly Linked List:");
        printDLL(head);
        System.out.println();

        System.out.println("Length of DLL: " + lengthOfDll(head));

        int searchValue = 30;
        System.out.println("Is " + searchValue + " present in DLL? " + checkIfPresent(head, searchValue));

        System.out.println("\nDeleting head...");
        head = deleteHead(head);
        printDLL(head);
        System.out.println();

        System.out.println("\nDeleting tail...");
        head = deleteTail(head);
        printDLL(head);
        System.out.println();

        int k = 2;
        System.out.println("\nDeleting " + k + "th node...");
        head = deleteK(head, k);
        printDLL(head);
        System.out.println();

        System.out.println("\nDeleting a specific node (e.g., node with value 20)...");
        Node temp = head;
        deleteNode(temp.next);
    
        printDLL(head);
        System.out.println();

        System.out.println("\nInserting 5 before head...");
        head = insertBefHead(head, 5);
        printDLL(head);
        System.out.println();

        System.out.println("\nInserting 75 before tail...");
        head = insertBefTail(head, 75);
        printDLL(head);
        System.out.println();

        int kInsert = 3;
        System.out.println("\nInserting 25 before " + kInsert + "rd node...");
        head = insertBefKthEle(head, kInsert, 25);
        printDLL(head);
        System.out.println();

        System.out.println("\nInserting 35 before a specific node (e.g., node with value 40)...");
        Node specificNode = head.next.next.next; // Assuming this is the node with value 40
        insertBefNode(specificNode, 35);
        printDLL(head);
        System.out.println();
    }

    
}
