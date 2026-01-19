public class SegmentTree {

    private class Node {
        int data;                 // 🔁 CHANGE meaning if needed (sum / min / max / etc.)
        int startInterval;
        int endInterval;
        Node left;
        Node right;

        public Node(int startInterval, int endInterval) {
            this.startInterval = startInterval;
            this.endInterval = endInterval;
        }
    }

    private Node root;

    // 1) Build
    public SegmentTree(int[] arr) {
        this.root = build(arr, 0, arr.length - 1);
    }

    private Node build(int[] arr, int start, int end) {
        if (start == end) {
            Node leaf = new Node(start, end);
            leaf.data = arr[start];     // 🔁 BASE VALUE
            return leaf;
        }

        Node node = new Node(start, end);
        int mid = (start + end) / 2;

        node.left = build(arr, start, mid);
        node.right = build(arr, mid + 1, end);

        node.data = merge(node.left.data, node.right.data); // 🔁 MERGE LOGIC
        return node;
    }

    // 2) Query
    public int query(int qsi, int qei) {
        return query(root, qsi, qei);
    }

    private int query(Node node, int qsi, int qei) {
        // total overlap
        if (node.startInterval >= qsi && node.endInterval <= qei) {
            return node.data;
        }

        // no overlap
        if (node.endInterval < qsi || node.startInterval > qei) {
            return neutralValue(); // 🔁 NEUTRAL VALUE
        }

        // partial overlap
        int leftAns = query(node.left, qsi, qei);
        int rightAns = query(node.right, qsi, qei);

        return merge(leftAns, rightAns); // 🔁 MERGE LOGIC
    }

    // 3) Update (point update)
    public void update(int index, int value) {
        update(root, index, value);
    }

    private void update(Node node, int index, int value) {
        if (node.startInterval == node.endInterval) {
            node.data = value; // 🔁 BASE VALUE
            return;
        }

        int mid = (node.startInterval + node.endInterval) / 2;

        if (index <= mid) {
            update(node.left, index, value);
        } else {
            update(node.right, index, value);
        }

        node.data = merge(node.left.data, node.right.data); // 🔁 MERGE LOGIC
    }

    // ===== Customizable Operations =====

    private int merge(int left, int right) {
        return left + right;  // 🔁 CHANGE THIS
    }

    private int neutralValue() {
        return 0;             // 🔁 CHANGE THIS
    }
}

