import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class stack{
    Queue<Integer> q = new LinkedList<>();

    void pushS(int x){
        q.add(x);
        for(int i = 1 ; i< q.size() ; i++){
            q.add(q.remove());
        }
    }
    int popS() {
        return q.remove();
    }
    int topS() {
        return q.peek();
    }
    int sizeS() {
        return q.size();
    } 
}

class myqueue{
    Stack <Integer> input = new Stack<>();
    Stack <Integer> output = new Stack<>();

    public void pushQ(int x){
        /*
         * s1 -> s2
         * s1 -> x
         * s2 -> s1
         */
        
        while(input.empty() == false){
            output.push(input.peek());
            input.pop();
        }

        System.out.println("The element pushed is " + x);
        input.push(x);

        while(output.empty() == false){
            input.push(output.peek());
            output.pop();
        }
    }

    public int popQ(){
        if(input.empty()){
            System.out.println("The Stack is empty");
        }
        int val = input.peek();
        input.pop();
        return val;
    }

    public int peekQ(){
        if(input.empty()){
            System.out.println("The Stack is empty");
        }

        return input.peek();
    }

    int sizeQ() {
        return input.size();
    }
}

public class STbyQ{
    public static void main(String[] args) {
        stack s = new stack();
        
        s.pushS(10);
        s.pushS(20);
        s.pushS(30);
        
        System.out.println("Top element: " + s.topS());
        System.out.println("Size of stack: " + s.sizeS());
        
        System.out.println("Popped element: " + s.popS());
        System.out.println("Top element after pop: " + s.topS());
        System.out.println("Size of stack after pop: " + s.sizeS());

        System.out.println("\nQueue using Stack");

        myqueue q = new myqueue();

        q.pushQ(100);
        q.pushQ(200);
        q.pushQ(300);

        System.out.println("Front element: " + q.peekQ());
        System.out.println("Size of queue: " + q.sizeQ());

        System.out.println("Dequeued element: " + q.popQ());
        System.out.println("Front element after dequeue: " + q.peekQ());
        System.out.println("Size of queue after dequeue: " + q.sizeQ());

        
    }
}

