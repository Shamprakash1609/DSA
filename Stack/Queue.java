class Queue{

    private class QueueNode{
        int val;
        QueueNode next;
    
        QueueNode(int data) {
            val = data;
            next = null;
        }
    }

    QueueNode Front = null , Rear = null;
    int size = 0;

    boolean Empty(){
        return Front == null;
    }

    int Peek(){
        if(Empty()){
            System.out.println("Queue is empty");
            return -1;
        }
        else return Front.val;

    }

    void Enqueue(int value){
        QueueNode temp = new QueueNode(value);

        if(Empty()){
            Front = Rear = temp;
        }
        else{
            Rear.next = temp;
            Rear = temp;
        }

        System.out.println(value + " inserted into Queue");
        size++;
    }

    void Dequeue(){
        if(Front == null)
        {
            System.out.println("Queue is empty");
        }
        else{
            System.out.println(Front.val+" Removed from Queue");
            Front = Front.next;
            size--;
        }
    }

    public static void main(String[] args) {
        Queue queue = new Queue();

        // Enqueue elements
        queue.Enqueue(10);
        queue.Enqueue(20);
        queue.Enqueue(30);

        // Peek the front element
        System.out.println("Front element is: " + queue.Peek());

        // Dequeue elements
        queue.Dequeue();
        queue.Dequeue();

        // Check if the queue is empty
        System.out.println("Is queue empty? " + queue.Empty());

        // Dequeue the last element
        queue.Dequeue();

        // Try to peek or dequeue from an empty queue
        System.out.println("Front element is: " + queue.Peek());
        queue.Dequeue();
    }
    
}