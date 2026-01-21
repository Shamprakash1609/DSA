import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{

    // ---------- Segment Tree ----------
    static class SegmentTree {

        static class Node {
            long data;              // must be long (sums go up to 2e14)
            int startInterval;
            int endInterval;
            Node left;
            Node right;

            Node(int startInterval, int endInterval) {
                this.startInterval = startInterval;
                this.endInterval = endInterval;
            }
        }

        Node root;

        // 1. Build Tree
        public SegmentTree(long[] arr) {
            this.root = build(arr, 0, arr.length - 1);
        }

        private Node build(long[] arr, int start, int end) {
            if (start == end) {
                Node leaf = new Node(start, end);
                leaf.data = arr[start];
                return leaf;
            }

            Node node = new Node(start, end);

            int mid = start + (end - start) / 2;

            node.left = build(arr, start, mid);
            node.right = build(arr, mid + 1, end);

            node.data = merge(node.left.data, node.right.data);

            return node;
        }

        // 2. Query range sum [qsi, qei]
        public long query(int qsi, int qei) {
            return query(this.root, qsi, qei);
        }

        private long query(Node node, int qsi, int qei) {
            // Total overlap
            if (node.startInterval >= qsi && node.endInterval <= qei) {
                return node.data;
            }

            // No overlap
            if (node.endInterval < qsi || node.startInterval > qei) {
                return neutralValue();
            }

            // Partial overlap
            long leftAns = query(node.left, qsi, qei);
            long rightAns = query(node.right, qsi, qei);

            return merge(leftAns, rightAns);
        }

        // 3. Update index to value
        public void update(int index, long value) {
            update(root, index, value);
        }

        private void update(Node node, int index, long value) {
            if (node.startInterval == node.endInterval) {
                node.data = value;
                return;
            }

            int mid = node.startInterval + (node.endInterval - node.startInterval) / 2;

            if (index <= mid) {
                update(node.left, index, value);
            } else {
                update(node.right, index, value);
            }

            // Recalculate current node after update
            node.data = merge(node.left.data, node.right.data);
        }

        private long merge(long left, long right) {
            return left + right;
        }

        private long neutralValue() {
            return 0L;
        }
    }

    // ---------- Main ----------
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        long[] arr = new long[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }



        SegmentTree segT = new SegmentTree(arr);

        StringBuilder sb = new StringBuilder();

        for(int i = 0 ; i < q ; i++){
            st = new StringTokenizer(br.readLine());

            int type = Integer.parseInt(st.nextToken());

            if(type == 1){
                int idx = Integer.parseInt(st.nextToken()) - 1;
                long val = Long.parseLong(st.nextToken());

                segT.update(idx, val);
            }
            else{

                int qsi = Integer.parseInt(st.nextToken()) - 1;
                int qei = Integer.parseInt(st.nextToken()) - 1;

                long ans = segT.query(qsi, qei);
                sb.append(ans).append('\n');

            }
        }

        System.out.println(sb.toString());

            
    }
}
