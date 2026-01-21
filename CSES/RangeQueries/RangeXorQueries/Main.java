
import java.io.*;
import java.util.StringTokenizer;



public class Main {
    static class SegmentTree{
        
        static class Node{
            long data;
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

        // build
        public SegmentTree(long arr[]) {
            this.root = build(arr , 0 , arr.length - 1);
        }

        private Node build(long arr[] , int start , int end){
            if(start == end){
                Node leaf = new Node(start , end);
                leaf.data = arr[start];
                return leaf;
            }

            Node node = new Node(start , end);

            int mid = start + (end - start) / 2;

            node.left = build(arr , start , mid);
            node.right = build(arr , mid + 1 , end);

            node.data = merge(node.left.data , node.right.data);

            return node;
        }

        // query
        public long query(int qsi , int qei){
            return query(this.root , qsi , qei);
        }
        private long query(Node node ,int qsi , int qei){
            if(node.startInterval >= qsi && node.endInterval <= qei){
                return node.data;
            }

            if(qei < node.startInterval || qsi > node.endInterval){
                return neutralValue();
            }

            long leftAns = query(node.left , qsi , qei);
            long rightAns = query(node.right , qsi , qei);

            return merge(leftAns , rightAns);
        }

        // update
        public void update(int index , long value){
            update(this.root , index , value);
        }

        private void update(Node node , int index , long value){
            if(node.startInterval == node.endInterval){
                node.data = value;
                return;
            }

            int mid = node.startInterval + (node.endInterval - node.startInterval) / 2;

            if(index <= mid){
                update(node.left , index , value);
            }
            else{
                update(node.right , index , value);
            }

            node.data = merge(node.left.data, node.right.data);
        }

        private long merge(long a , long b){
            return a ^ b;
        }

        private long neutralValue(){
            return 0;
        } 
    }
    
    public static void main(String[] args) throws  Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        long arr[] = new long[n];

        st = new StringTokenizer(br.readLine());

        for(int i = 0 ; i < n ; i++){
            arr[i] = Long.parseLong(st.nextToken());
        }

        SegmentTree segT = new SegmentTree(arr);

        StringBuilder sb = new StringBuilder();

        for(int i = 0 ; i < q ; i++){
            st = new StringTokenizer(br.readLine());
            int qsi = Integer.parseInt(st.nextToken()) - 1;
            int qei = Integer.parseInt(st.nextToken()) - 1;

            long ans = segT.query(qsi, qei);

            sb.append(ans).append('\n');
        }

        System.out.println(sb.toString());
    }
}
