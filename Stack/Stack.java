
public class Stack {

    private class StackNode{
        int data;
        StackNode next;
    
        StackNode(int data){
            this.data = data;
            this.next = null;
        }
    }
    
    StackNode top;
    int size;
    Stack(){
        this.top = null;
        this.size = 0;
    }

    public void stackPush(int x){
        StackNode element = new StackNode(x);
        element.next = top;
        top = element;
        System.out.println("Element pushed");
        size++;
    }

    public int stackPop(){
        if(top == null) return -1;

        int topData = top.data;
        // StackNode temp = top;
        top = top.next;
        size--;
        return topData;
    }

    public int stackSize(){
        return size;
    }

    public Boolean stackIsEmpty(){
        return top == null;
    }

    public void printStack(){
        StackNode current = top;
        while(current != null){
            System.out.println(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }



    


    public static void main(String[] args) {
        Stack s = new Stack();
        s.stackPush(10);
        s.stackPush(20);
        s.stackPush(30);
        s.stackPush(40);
        s.stackPush(50);

        s.printStack();
        int poped = s.stackPop();
        int size = s.stackSize();
        System.out.println("Size of the Stack is : " + size);
        System.out.println("The popped element is : " + poped);
    }


}
