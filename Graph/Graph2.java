import java.util.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;




public class Graph2 {

    // Dijkstra's Algo
    /*
     * not suitable for Graph with -ve Weights
     * Dijkstra's algorithm finds the shortest path from a source node to all other nodes
     * in a weighted graph with non-negative edge weights.
     * Key Points:
     * - Time Complexity: O(E log V) using a priority queue (min-heap).
     * - Does not work correctly with negative edge weights.
     * - Can be used for both directed and undirected graphs.
     * - Returns the shortest distance from the source to every other node.
     */
    
    public static int[] dijkstra(int V, int[][] edges, int src) {
        // TC : O(E log N)
        ArrayList<ArrayList<int[]>> adjLs = new ArrayList<>();
        
        for(int i = 0 ; i < V ; i++){
            adjLs.add(new ArrayList<int[]>());
        }
        
        for(int edge[] : edges){
            int u = edge[0];
            int v = edge[1];
            int wt = edge[2];
            
            adjLs.get(u).add(new int[]{v , wt});
            adjLs.get(v).add(new int[]{u , wt});
        }
       
       PriorityQueue<int[]> pq = new PriorityQueue<>((x,y) -> (x[1] - y[1]));
       
       int dist[] = new int[V];
       
       Arrays.fill(dist , (int) 1e9);
       
       dist[src] = 0;
       
       pq.add(new int[]{src , 0});
       
        while(!pq.isEmpty()){
           int curr[] = pq.poll();
           
           int node = curr[0];
           int prevDis = curr[1];
           
           for(int it[] : adjLs.get(node)){
               int adjN = it[0];
               int eWt  = it[1];
               
               if(prevDis + eWt < dist[adjN]){
                   dist[adjN] = prevDis + eWt;
                   pq.add(new int[]{adjN , dist[adjN]});
               }
           }
        }
        
        
        return dist;
    }

    static class PairT implements Comparable<PairT> {
        int node, dist;
        PairT(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }
        public int compareTo(PairT o) {
            if(this.dist == o.dist) return this.node - o.node;
            return this.dist - o.dist;
        }
        // Needed for TreeSet remove to work
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof PairT)) return false;
            PairT p = (PairT) o;
            return node == p.node && dist == p.dist;
        }
        @Override
        public int hashCode() {
            return node * 31 + dist;
        }
    }

    public static  int[] dijkstra_ST(int V, int[][] edges, int src) {
        ArrayList<ArrayList<int[]>> adjLs = new ArrayList<>();
        for (int i = 0; i < V; i++) adjLs.add(new ArrayList<>());
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], wt = edge[2];
            adjLs.get(u).add(new int[]{v, wt});
            adjLs.get(v).add(new int[]{u, wt});
        }

        TreeSet<PairT> st = new TreeSet<>();
        int[] dist = new int[V];
        Arrays.fill(dist, (int) 1e9);
        dist[src] = 0;
        st.add(new PairT(src, 0));

        while (!st.isEmpty()) {
            PairT curr = st.pollFirst();
            int node = curr.node;
            int prevDis = curr.dist;

            for (int[] it : adjLs.get(node)) {
                int adjN = it[0], eWt = it[1];
                if (prevDis + eWt < dist[adjN]) {
                    if (dist[adjN] != 1e9) {
                        st.remove(new PairT(adjN, dist[adjN]));
                    }
                    dist[adjN] = prevDis + eWt;
                    st.add(new PairT(adjN, dist[adjN]));
                }
            }
        }

        return dist;
    }

    // Print Shortest Path from 1 to n
    public static  List<Integer> shortestPath1ToN(int n, int m, int edges[][]) {
        
        ArrayList<ArrayList<int[]>> adjLs = new ArrayList<>();
        
        for(int i = 0 ; i <= n ; i++){
            adjLs.add(new ArrayList<int[]>());
        }
        
        for(int [] edge : edges){
            int u = edge[0];
            int v = edge[1];
            int wt = edge[2];
            
            adjLs.get(u).add(new int[]{v , wt});
            adjLs.get(v).add(new int[]{u , wt});
        }
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((x,y) -> (x[1] - y[1]));
        
        int dist[] = new int[n+1];
    
        int parent[] = new int[n+1];
        
        for(int i = 1 ; i <= n ; i++){
            dist[i] = (int) 1e9;
            parent[i] = i;
        }
        
        dist[1] = 0;
        
        pq.add(new int[]{1 , 0});
        
        while(!pq.isEmpty()){
            int curr[] = pq.poll();
            
            int node = curr[0];
            int prevDis = curr[1];
            
            for(int it[] : adjLs.get(node)){
                int adjN = it[0];
                int eWt = it[1];
                
                if(prevDis + eWt < dist[adjN]){
                    dist[adjN] = prevDis + eWt;
                    pq.add(new int[]{adjN , dist[adjN]});
                    parent[adjN] = node; 
                }
            }
        }
        
        List<Integer> path = new ArrayList<>();
        
        if(dist[n] == 1e9){
            path.add(-1);
            return path;
        }
        
        int node = n;
        
        while(parent[node] != node){
            path.add(node);
            node = parent[node];
        }
        
        path.add(1);
        
        Collections.reverse(path);
        
        int totWt = 0;
        
        for(int i = 1 ; i < path.size() ; i++){
            int u = path.get(i-1);
            int v = path.get(i);
            
            for(int it[] : adjLs.get(u)){
                if(it[0] == v){
                    totWt += it[1];
                    break;
                }
            }
        }
        
        path.add(0 , totWt);
        
        return path;
        
    }

    // Shortest path in a Binary Maze
    public static  int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        int vis [][] = new int[n][m];

        int[][] directions = {{-1,-1} , {-1,0} , {-1,1} , {0,1} , {1,1} , {1,0} , {1,-1} , {0 , -1}};

        if(grid[0][0] == 1 || grid[n-1][m-1] == 1) return -1;

        Queue<int[]> q = new LinkedList<>();

        q.add(new int[]{0 , 0 , 1});

        vis[0][0] = 1;

        while(!q.isEmpty()){
            int curr[] = q.poll();
            int row = curr[0];
            int col = curr[1];
            int d = curr[2];

            if (row == n - 1 && col == m - 1){
                return d;
            }

            for(int dir[] : directions){
                int nRow = row + dir[0];
                int nCol = col + dir[1];

                if(nRow < n && nCol < m && nRow >= 0 && nCol >= 0 && vis[nRow][nCol] == 0 && grid[nRow][nCol] == 0){
                    vis[nRow][nCol] = 1;
                    q.add(new int[]{nRow , nCol , d+1});
                }
            }
        }

        return -1;
    }

    // Shortest Distance in a Binary Maze
    public static int shortestDistBinary(int[][] grid, int[] src, int[] dst) {
        
        int n = grid.length;
        int m = grid[0].length;
        
        if(src[0] == dst[0] && src[1] == dst[1]) return 0;
        
        Queue<int[]> q = new LinkedList<>();
        
        int dist[][] = new int[n][m];
        
        for(int []row : dist){
            Arrays.fill(row , (int)(1e9));
        }
        
        dist[src[0]][src[1]] = 0;
        
        q.add(new int[]{src[0] , src[1] , 0} ); // row , col , distance
        
        int[][] directions = {{-1,0}, {0,-1}, {0,1}, {1,0}};
        
        while(!q.isEmpty()){
            int curr[] = q.poll();
            
            int row = curr[0];
            int col = curr[1];
            int d = curr[2];
            
            for(int dir[] : directions){
                int nRow = dir[0] + row;
                int nCol = dir[1] + col;
                
                if(nRow < n && nCol < m && nRow >= 0 && nCol >= 0 && grid[nRow][nCol] == 1 && d + 1 < dist[nRow][nCol] ){
                    dist[nRow][nCol] = 1 + d;
                    if(nRow == dst[0] && nCol == dst[1]) return d + 1;
                    
                    q.add(new int[]{nRow , nCol , d + 1});
                    
                }
            }
        }
        
        return -1;
        
    }

    // Path with minimum effort 
    public static int minimumEffortPath(int[][] heights) {
        int n = heights.length;
        int m = heights[0].length;

        int dist[][] = new int[n][m];

        for(int row[] : dist){
            Arrays.fill(row , (int)(1e9));
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((x,y) -> (x[2] - y[2]));

        pq.add(new int[]{0 , 0 , 0});
        dist[0][0] = 0;

        int[][] directions = {{-1,0}, {0,-1}, {0,1}, {1,0}};
        
        int diff = 0;

        while(!pq.isEmpty()){
            int curr[] = pq.poll();

            int row = curr[0];
            int col = curr[1];
            int eff = curr[2];

            for(int dir[] : directions){
                int nRow = row + dir[0];
                int nCol = col + dir[1];

                if (row == n - 1 && col == m - 1) {
                    return eff;
                }

                if(nRow < n && nCol < m && nRow >= 0 && nCol >= 0){
                    int nEff = Math.max( eff , Math.abs(heights[row][col] - heights[nRow][nCol]));

                    if(nEff < dist[nRow][nCol]){
                        dist[nRow][nCol] = nEff;
                        pq.add(new int[]{nRow , nCol , nEff});
                    }

                }
            }
        }

        return 0;
    }

    // Cheapest Flights Within K Stops
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        ArrayList<ArrayList<int[]>> adjLs = new ArrayList<>();

        for(int i = 0 ; i < n ; i++){
            adjLs.add(new ArrayList<int[]>());
        }
        
        for(int f[] : flights){
            int u = f[0];
            int v = f[1];
            int cst = f[2];

            adjLs.get(u).add(new int[]{v , cst});
        }

        Queue<int[]> q = new LinkedList<>();

        int dist[] = new int[n];
        Arrays.fill(dist , (int)(1e9));
        dist[src] = 0;

        q.add(new int[] {src , 0 , 0}); // {node , distance , stops}

        while(!q.isEmpty()){
            int curr[] = q.poll();

            int node = curr[0];
            int d = curr[1];
            int stops = curr[2];

            if(stops > k) continue;

            for(int it[] : adjLs.get(node)){
                int adjN = it[0];
                int eDiff = it[1];

                if(d + eDiff < dist[adjN] && stops <= k){
                    dist[adjN] = d + eDiff;
                    q.add(new int[]{adjN , dist[adjN] , stops+1});
                }
            }
        }

        if(dist[dst] == 1e9) return -1;
        else return dist[dst];
    } 

    // Network Delay time
    public static  int networkDelayTime(int[][] times, int n, int k) {

        ArrayList<ArrayList<int[]>> adjLs = new ArrayList<>();

        for(int i = 0 ; i <= n ; i++){
            adjLs.add(new ArrayList<int[]>());
        }

        for(int t[] : times){
            int u = t[0];
            int v = t[1];
            int wt = t[2];

            adjLs.get(u).add(new int[]{v , wt});
        }

        int dist[] = new int[n+1];
        Arrays.fill(dist , (int)(1e9));
        dist[k] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((x,y) -> (x[1] - y[1]));

        pq.add(new int[]{k , 0});

        while(!pq.isEmpty()){

            int curr[] = pq.poll();

            int node = curr[0];
            int d = curr[1];

            for(int[] it : adjLs.get(node)){
                int adjN = it[0];
                int eWt = it[1];

                if(d + eWt < dist[adjN]){
                    dist[adjN] = d + eWt;
                    pq.offer(new int[]{adjN , dist[adjN]});
                }
            }
        }

        int minDelay = -1;
        for(int i = 1 ; i <= n ; i++){
            if(dist[i] == 1e9) return -1;
            minDelay = Math.max(dist[i] , minDelay);
        }
        
        return minDelay;
    }

    // Number of Ways to arrive at a Destination
    public static  int countPaths(int n, int[][] roads) {
        ArrayList<ArrayList<long[]>> adjLs = new ArrayList<>();

        for(int i = 0 ; i < n ; i++){
            adjLs.add(new ArrayList<long[]>());
        }

        for(int r[] : roads){
            int u = r[0];
            int v = r[1];
            int wt = r[2];

            adjLs.get(u).add(new long[]{v , wt});
            adjLs.get(v).add(new long[]{u , wt});
        }

        long dist[] = new long [n];
        int ways[] = new int [n];

        Arrays.fill(dist , Long.MAX_VALUE );
        dist[0] = 0;
        ways[0] = 1;

        PriorityQueue<long[]> pq = new PriorityQueue<>((x,y) -> Long.compare(x[1], y[1]));

        pq.add(new long[]{0 , 0});

        int mod = (int) 1e9 + 7;

        while(!pq.isEmpty()){
            long curr[] = pq.poll();

            int node = (int) curr[0];
            long d = curr[1];

            if (d > dist[node]) continue; 

            for(long it[] : adjLs.get(node)){
                int adjN = (int) it[0]; 
                long eWt = it[1];

                if(eWt + d < dist[adjN]){
                    dist[adjN] = eWt + d;
                    pq.add(new long[]{adjN , dist[adjN]});
                    ways[adjN] = ways[node];
                }

                else if(eWt + d == dist[adjN]){
                    ways[adjN] = (ways[adjN] + ways[node]) % mod;
                }
            }
        }

        return ways[n-1] % mod;
    }

    // Bellman Ford algorithm
    public static  int[] bellmanFord(int V, int[][] edges, int src) {
       int dist[] = new int[V]; 
       Arrays.fill(dist , (int)(1e8));
       dist[src] = 0;
        
        // N-1 iterations
        for(int i = 0  ; i < V-1 ; i++){
            for(int[] e : edges){
                int u = e[0];
                int v = e[1];
                int wt = e[2];
                
                
                if(dist[u] != 1e8 && dist[u] + wt < dist[v]){
                    dist[v] = dist[u] + wt;
                }
                
            }
        }
        
        
        // Nth iteration
        for(int[] e : edges){
            int u = e[0];
            int v = e[1];
            int wt = e[2];
                
                
            if(dist[v] != 1e8 && dist[u] + wt < dist[v]){
                return new int[]{-1};
            }
                
        }
        
        return dist;
    }

    // Floyd Warshall algorithm
    public static  void floydWarshall(int[][] dist) {
        int n = dist.length;
        
        for(int k = 0 ; k < n ; k++){
            for(int i = 0 ; i < n ; i++){
                for(int j = 0 ; j < n ; j++){
                    if( dist[i][k] != 1e8 && dist[k][j] != 1e8){
                        dist[i][j] = Math.min(dist[i][j] , (dist[i][k] + dist[k][j]));
                    }
                    
                }
            }
        }
        
    }

    // Find the City With the Smallest Number of Neighbors at a Threshold Distance
    public static  int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int adjM[][] = new int[n][n];

        for(int row[] : adjM){
            Arrays.fill(row , Integer.MAX_VALUE);
        }

        for(int arr[] : edges){
            int i = arr[0];
            int j = arr[1];
            int d = arr[2];

            adjM[i][j] = d;
            adjM[j][i] = d;
        }

        for(int i = 0 ; i < n ; i++){
            adjM[i][i] = 0;
        }

        for(int k = 0 ; k < n ; k++){
            for(int i = 0 ; i < n; i++){
                for(int j = 0 ; j < n ; j++){
                    if(adjM[i][k] != Integer.MAX_VALUE && adjM[k][j] != Integer.MAX_VALUE){
                        adjM[i][j] = Math.min(adjM[i][j] , (adjM[i][k] + adjM[k][j]));
                    }
                }
            }
        }

        // Find city

        int cntCity = n;
        int cityNo = -1;

        for(int city = 0 ; city < n ; city++){
            int cnt = 0;
            for(int adjCity = 0 ; adjCity < n ; adjCity++){
                if(adjM[city][adjCity] <= distanceThreshold){
                    cnt++;
                }
            }

            if(cnt <= cntCity){
                cntCity = cnt;
                cityNo = city;
            }
        }

        return cityNo;

    }

    // MST - Minimum Spanning Tree
    public static int Prims_MST(int V, int E, List<List<int[]>> adj) {
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((x , y) -> (x[1] - y[1]));
        
        int vis[] = new int[V];
        
        pq.add(new int[]{0 , 0});
        
        int sum = 0;
        
        while(!pq.isEmpty()){
            
            int curr[] = pq.poll();
            int node = curr[0];
            int d = curr[1];
            
            if(vis[node] == 1) continue;
            
            vis[node] = 1;
            sum += d;
            
            for(int it[] : adj.get(node)){
                int adjN = it[0];
                int wt = it[1];
                
                if(vis[adjN] == 0){
                    pq.add(new int[]{adjN , wt});
                }
            }
        }
        
        return sum;
    }

    // Disjoint Set Union - (DS)
    public static class DisjointSet{
       int [] parent;
       int [] rank;
       int [] size;

        public DisjointSet(int n){
            parent = new int[n + 1];
            rank = new int[n + 1];
            size = new int[n + 1];

            for(int i = 0 ; i <= n ; i++){
                parent[i] = i;
                rank[i] = 0;
                size[i] = 1;
            }
        }

        public int findUPar(int node){
            if(node == parent[node]){
                return node;
            }

            // Path Compression
            return parent[node] = findUPar(parent[node]);
        }

        public void unionByRank(int u , int v){

            int ulp_u = findUPar(u);
            int ulp_v = findUPar(v);

            if (ulp_u == ulp_v) return; // already in the same set

            if(rank[ulp_u] < rank[ulp_v]){
                parent[ulp_u] = ulp_v;
            }
            else if(rank[ulp_v] < rank[ulp_u]){
                parent[ulp_v] = ulp_u;
            }
            else{
                parent[ulp_v] = ulp_u;
                rank[ulp_u]++;
            }
        }

        public void unionBySize(int u , int v){

            int ulp_u = findUPar(u);
            int ulp_v = findUPar(v);

            if (ulp_u == ulp_v) return; // already in the same set

            if(size[ulp_u] < size[ulp_v]){
                parent[ulp_u] = ulp_v;
                size[ulp_v] += size[ulp_u];
            }
            else{
                parent[ulp_v] = ulp_u;
                size[ulp_u] += size[ulp_v];
            }
        }
    }
    
    // Redundant Connection
    public static int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;

        DisjointSet ds = new DisjointSet(n);

        for(int arr[] : edges){
            int u = arr[0];
            int v = arr[1];

            if(ds.findUPar(u) == ds.findUPar(v)) return arr;

            ds.unionBySize(u , v);
        }

        return new int[0];
    }

    // Kruskal's algo
    public static int kruskalsMST(int V, int[][] edges) {
        
        Arrays.sort(edges , (x , y) -> (x[2] - y[2]));
        
        DisjointSet ds = new DisjointSet(V);
        
        int mstWeight = 0;
        
        for(int it[] : edges){
            int u = it[0];
            int v = it[1];
            int wt = it[2];
        
            if(ds.findUPar(u) != ds.findUPar(v)){
                mstWeight += wt;
                ds.unionBySize(u , v);
            }
            
        }
        
        return mstWeight;
        
    }

    // Number of Provinces using Union find
    public static int noOfProvinces(int[][] isConnected) {
        int n = isConnected.length;

        DisjointSet ds = new DisjointSet(n);

        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < n ; j++){
                if(isConnected[i][j] == 1){
                    ds.unionBySize(i , j);
                }
            }
        }

        int provinces = 0;
        for(int i = 0 ; i < n ; i++){
            if(ds.findUPar(i) == i){
                provinces++;
            }
        }

        return provinces;
    }

    // Number of Operations to Make Network Connected
    public static  int makeConnected(int n, int[][] connections) {
        DisjointSet ds = new DisjointSet(n);

        int cntExtras = 0;

        for(int e[] : connections){
            int u = e[0];
            int v = e[1];

            if(ds.findUPar(u) == ds.findUPar(v)){
                cntExtras++;
            }
            else{
                ds.unionBySize(u , v);
            }
        }

        int cntC = 0;

        for(int i= 0 ; i < n ; i++){
            if(ds.findUPar(i) == i) cntC++;
        }

        int ans = cntC - 1;

        if (cntExtras >= ans) return ans;

        return -1;

    }

    // Accounts Merge
    public static  List<List<String>> accountsMerge(List<List<String>> accounts) {
        int n = accounts.size();

        DisjointSet ds = new DisjointSet(n);
        Map<String , Integer> mpp = new HashMap<>();


        // Step 1: Union accounts by shared emails
        for(int i = 0 ; i < n ; i++){
            for(int j = 1 ; j < accounts.get(i).size() ; j++){
                String mail = accounts.get(i).get(j);
                if(!mpp.containsKey(mail)){
                    mpp.put(mail , i);
                }
                else{
                    ds.unionBySize(i , mpp.get(mail));
                }
            }
        }

        ArrayList<String>[] mergeMail = new ArrayList[n];

        for(int i = 0 ; i < n ; i++){
            mergeMail[i] = new ArrayList<String>();
        }

        // Step 2: Group emails by their ultimate parent account
        for(Map.Entry<String , Integer> it : mpp.entrySet()){
            String mail = it.getKey();
            int node = ds.findUPar(it.getValue());
            mergeMail[node].add(mail);
        }

        // Step 3: Build final merged account list
        List<List<String>> ans = new ArrayList<>();

        for(int i = 0 ; i < n ; i++){
            if(mergeMail[i].size() == 0) continue;
            Collections.sort(mergeMail[i]);
            List<String> temp = new ArrayList<>();
            temp.add(accounts.get(i).get(0));

            for(String it : mergeMail[i]){
                temp.add(it);
            }
            ans.add(temp);
        }

        return ans;
    }

    // Number of Island - II
    public List<Integer> numOfIslandsII(int rows, int cols, int[][] operators) {
        DisjointSet ds = new DisjointSet(rows * cols);
        
        int vis[][] = new int[rows][cols];
        
        int cnt = 0;
        
        List<Integer> ans = new ArrayList<Integer>();
        
        for(int it[] : operators){
            int row = it[0];
            int col = it[1];
            
            if(vis[row][col] == 1){
                ans.add(cnt);
                continue;
            }
            
            vis[row][col] = 1;
            cnt++;
            
            int[][] directions = {{-1,0}, {0,-1}, {0,1}, {1,0}};
            
            for(int dir[] : directions){
                int adjR = row + dir[0];
                int adjC = col + dir[1];
                
                if(adjR < rows && adjC < cols && adjR >= 0 && adjC >= 0 && vis[adjR][adjC] == 1 ){
                    int nodeNum = (row * cols) + col;
                    int adjNum = (adjR * cols) + adjC;
                    
                    if(ds.findUPar(nodeNum) != ds.findUPar(adjNum)){
                        cnt--;
                        ds.unionBySize(nodeNum , adjNum);
                    }
                }
                
            }
            
            ans.add(cnt);
            
        }
        
        return ans;
    }

    // Making a Larger Island
    public static  int largestIsland(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        DisjointSet ds = new DisjointSet(n * m);

        int[][] directions = {{-1,0}, {0,-1}, {0,1}, {1,0}};

        // unite the nodes with 1's
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < m ; j++){
                if(grid[i][j] == 0) continue;

                for(int dir[] : directions){
                    int nRow = i + dir[0];
                    int nCol = j + dir[1];

                    if(nRow < n && nCol < m && nRow >= 0 && nCol >= 0 && grid[nRow][nCol] == 1){
                        int nodeNum = (i * m) + j;
                        int adjNum = (nRow * m) + nCol;

                        ds.unionBySize(nodeNum , adjNum);
                    }
                }
            }
        }

        // select the 0's to maximize Island
        int maxSize = 0;
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < m ; j++){
                if(grid[i][j] == 1) continue;

                Set<Integer> components = new HashSet<>();
                for(int dir[] : directions){
                    int nRow = i + dir[0];
                    int nCol = j + dir[1];

                    if(nRow >= 0 && nRow < n && nCol >= 0 && nCol < m && grid[nRow][nCol] == 1){
                        components.add(ds.findUPar((nRow * m) + nCol));
                    }
                }

                int sizeTot = 0;
                for(Integer parents : components){
                    sizeTot += ds.size[parents];
                }

                maxSize = Math.max( sizeTot+1 , maxSize);

            }
        }

        //  What if the the grid contains all ones ? then step never works
        for(int cellNo = 0 ; cellNo < n * m ; cellNo++){
            maxSize = Math.max(maxSize , ds.size[ds.findUPar(cellNo)]);
        }

        return maxSize;

    }

    // Most Stones Removed with Same Row or Column
    public static  int removeStones(int[][] stones) {
        int n = stones.length;

        int maxRow = 0;
        int maxCol = 0;

        for(int it[] : stones){
            maxRow = Math.max(maxRow , it[0]);
            maxCol = Math.max(maxCol , it[1]);
        }

        DisjointSet ds = new DisjointSet(maxRow + maxCol + 2);

        Map<Integer, Integer> mpp = new HashMap<>();

        for(int it[] : stones){
            int nodeR = it[0];
            int nodeC = it[1] + maxRow + 1;

            ds.unionBySize(nodeR , nodeC);
            mpp.put(nodeR , 1);
            mpp.put(nodeC , 1);
        }

        int cnt = 0;
        for(Map.Entry<Integer , Integer> it : mpp.entrySet()){
            if(ds.findUPar(it.getKey()) == it.getKey()){
                cnt++;
            }
        }

        return n - cnt;
    }
    
    // Bridges in a Graph
    public static  List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        ArrayList<ArrayList<Integer>> adjLs = new ArrayList<>();

        for(int i = 0 ; i < n ; i++){
            adjLs.add(new ArrayList<Integer>());
        }

        for(List<Integer> it : connections){
            int u = it.get(0);
            int v = it.get(1);

            adjLs.get(u).add(v);
            adjLs.get(v).add(u);
        }

        int vis[] = new int[n];
        int tin[] = new int[n];
        int low[] = new int[n];

        List<List<Integer>> bridges = new ArrayList<>();
        dfs_bridge(0 , -1 ,adjLs, vis , tin , low , bridges);

        return bridges;
    }
    private static int timer = 1;
    public static void dfs_bridge(int node , int parent , ArrayList<ArrayList<Integer>> adjLs, int vis[] , int tin[] , int low[] , List<List<Integer>> bridges){
        vis[node] = 1;
        tin[node] = low[node] = timer;
        timer++;

        for(Integer it : adjLs.get(node)){
            if(it == parent) continue;

            if(vis[it] == 0){
                dfs_bridge(it , node , adjLs , vis , tin , low , bridges);
                low[node] = Math.min(low[node] , low[it]);

                // node |--| it
                if(low[it] > tin[node]){
                    bridges.add(Arrays.asList(it , node));
                }
            }
            else{
                low[node] = Math.min(low[node] , low[it]);
            }
        }
    }

    // Strongly Connected Components - SCC -- Kosaraju's Algorithm
    public static int kosaraju(ArrayList<ArrayList<Integer>> adj) {
        
        int n = adj.size();
        
        int vis[] = new int[n];
        Stack<Integer> st = new Stack<>();
        
        for(int i = 0 ; i < n ; i++){
            if(vis[i] == 0){
                dfs_1st(i , vis , adj , st);
            }
        }
        
        ArrayList<ArrayList<Integer>> adjT = new ArrayList<>();
        
        for(int i = 0 ; i < n ; i++){
            adjT.add(new ArrayList<Integer>());
        }
        
        for(int i = 0 ; i < n ; i++){
            vis[i] = 0;
            
            for(int it : adj.get(i)){
                adjT.get(it).add(i);
            }
            
        }
        
        int scc = 0;
        
        while(!st.isEmpty()){
            int node = st.pop();
            if(vis[node] == 0){
                scc++;
                dfs_2nd(node , vis , adjT);
            }
        }
        
        return scc;
        
    }
    
    public static void dfs_1st(int node , int[] vis , ArrayList<ArrayList<Integer>> adjLs , Stack<Integer> st){
        vis[node] = 1;
        
        for(int it : adjLs.get(node)){
            if(vis[it] == 0){
                dfs_1st(it , vis , adjLs , st);
            }
        }
        
        st.add(node);
    }
    
    public static void dfs_2nd(int node , int vis[] , ArrayList<ArrayList<Integer>> adjT){
        vis[node] = 1;
        
        for(int it : adjT.get(node)){
            if(vis[it] == 0){
                dfs_2nd(it , vis , adjT);
            }
        }
    }
    
    // Articulation point
    public static ArrayList<Integer> articulationPoints(int V, int[][] edges) {
        
       ArrayList<ArrayList<Integer>> adjLs = new ArrayList<>();
       
       for(int i = 0 ; i < V ; i++){
           adjLs.add(new ArrayList<>());
       }
       
       for(int e[] : edges){
           int u = e[0];
           int v = e[1];
           
           adjLs.get(u).add(v);
           adjLs.get(v).add(u);
       }
       
       int vis[] = new int[V];
       int tin[] = new int[V];
       int low[] = new int[V];
       int mark[] = new int[V];
       
       for(int i = 0 ; i < V ; i++){
           if(vis[i] == 0){
               dfs_arti(i , -1 , vis , tin , low , mark , adjLs);
           }
        }
        
        ArrayList<Integer> ans = new ArrayList<>();
        
        for(int i = 0 ; i < V ; i++){
            if(mark[i] == 1){
                ans.add(i);
            }
        }
        
        if(ans.size() == 0){
            ans.add(-1);
        }
        
        return ans;
    }
    private static int arT = 1;
    public static void dfs_arti(int node ,int parent , int [] vis , int[] tin , int low[] , int mark[] , ArrayList<ArrayList<Integer>> adjLs){
        vis[node] = 1;
        tin[node] = low[node] = arT;
        arT++;
        
        int child = 0;
        for(int it : adjLs.get(node)){
            if(it == parent) continue;
            if(vis[it] == 0){
                dfs_arti(it , node , vis , tin , low , mark , adjLs);
                low[node] = Math.min(low[node] , low[it]);
                
                // node |--| it
                if(low[it] >= tin[node] && parent != -1){
                    mark[node] = 1;
                }
                child++;
            }
            else {
                low[node] = Math.min(low[node] , tin[it]);
            }
            
        }
        
        if(child > 1 && parent == -1){
            mark[node] = 1;
        }
        
    }





    public static void main(String[] args) {
        int V = 5;
        int[][] edges = {
            {0, 1, 2},
            {0, 2, 4},
            {1, 2, 1},
            {1, 3, 7},
            {2, 4, 3},
            {3, 4, 1}
        };

        // Test dijkstra (PriorityQueue version)
        int[] dist = dijkstra(V, edges, 0);
        System.out.println("Dijkstra (PQ) distances from node 0:");
        System.out.println(Arrays.toString(dist));

        // Test shortestPath1ToN
        int n = 4, m = 4;
        int[][] edges2 = {
            {1, 2, 2},
            {2, 4, 1},
            {1, 3, 1},
            {3, 4, 3}
        };
        List<Integer> path = shortestPath1ToN(n, m, edges2);
        System.out.println("Shortest path from 1 to " + n + " (total weight at index 0): " + path);
       


        int[][] binaryMaze = {
            {0, 1, 0, 0, 0},
            {0, 1, 0, 1, 0},
            {0, 0, 0, 1, 0},
            {1, 1, 0, 0, 0},
            {0, 0, 0, 1, 0}
        };
        int shortest = shortestPathBinaryMatrix(binaryMaze);
        System.out.println("Shortest path length in binary maze: " + shortest);
        


        // Test Cheapest Flights Within K Stops
        Graph2 g = new Graph2();
        int nFlights = 4;
        int[][] flights = {
            {0, 1, 100},
            {1, 2, 100},
            {0, 2, 500},
            {2, 3, 100}
        };
        int src = 0, dst = 3, k = 2;
        int cheapest = g.findCheapestPrice(nFlights, flights, src, dst, k);
        System.out.println("Cheapest price from " + src + " to " + dst + " within " + k + " stops: " + cheapest);


        int[][] times = {
            {2, 1, 1},
            {2, 3, 1},
            {3, 4, 1}
        };
        int nodes = 4;
        int start = 2;
        int delay = networkDelayTime(times, nodes, start);
        System.out.println("Network delay time from node " + start + ": " + delay);

        // Test Number of Ways to arrive at a Destination
        int nWays = 7;
        int[][] roads = {
            {0, 6, 7},
            {0, 1, 2},
            {1, 2, 3},
            {1, 3, 3},
            {6, 3, 3},
            {3, 5, 1},
            {6, 5, 1},
            {2, 5, 1},
            {0, 4, 5},
            {4, 6, 2}
        };
        int ways = countPaths(nWays, roads);
        System.out.println("Number of ways to arrive at destination " + (nWays-1) + ": " + ways);

        // Test Bellman-Ford
        int[][] bellmanEdges = {
            {0, 1, -1},
            {0, 2, 4},
            {1, 2, 3},
            {1, 3, 2},
            {1, 4, 2},
            {3, 2, 5},
            {3, 1, 1},
            {4, 3, -3}
        };
        int V_bellman = 5;
        int[] bellmanDist = bellmanFord(V_bellman, bellmanEdges, 0);
        System.out.println("Bellman-Ford distances from node 0:");
        System.out.println(Arrays.toString(bellmanDist));

        // Test Floyd-Warshall
        int INF = (int) 1e8;
        int[][] fwDist = {
            {0, 3, INF, 5},
            {2, 0, INF, 4},
            {INF, 1, 0, INF},
            {INF, INF, 2, 0}
        };
        floydWarshall(fwDist);
        System.out.println("Floyd-Warshall all-pairs shortest distances:");
        for (int[] row : fwDist) {
            System.out.println(Arrays.toString(row));
        }

        // Find the City With the Smallest Number of Neighbors at a Threshold Distance
        int nCity = 4;
        int[][] nDEdges = {{0, 1, 3}, {1, 2, 1}, {1, 3, 4}, {2, 3, 1}};
        int smallestCity = findTheCity(nCity, nDEdges , 4);
        System.out.println("City number with the smallest number of neighbors at threshold distance: " + smallestCity);

        // Test Prims_MST
        int V_mst = 5;
        int E_mst = 6;
        List<List<int[]>> adjMST = new ArrayList<>();
        for (int i = 0; i < V_mst; i++) adjMST.add(new ArrayList<>());
        int[][] mstEdges = {
            {0, 1, 2},
            {0, 3, 6},
            {1, 2, 3},
            {1, 3, 8},
            {1, 4, 5},
            {2, 4, 7}
        };
        for (int[] edge : mstEdges) {
            adjMST.get(edge[0]).add(new int[]{edge[1], edge[2]});
            adjMST.get(edge[1]).add(new int[]{edge[0], edge[2]});
        }
        int mstWeight = Prims_MST(V_mst, E_mst, adjMST);
        System.out.println("Minimum Spanning Tree total weight: " + mstWeight);

        // Disjoint Set Union - by rank
        DisjointSet ds = new DisjointSet(7);
        ds.unionByRank(1,2);
        ds.unionByRank(2,3);
        ds.unionByRank(4,5);
        ds.unionByRank(6,7);
        ds.unionByRank(5,6);

        // [1 - 2 - 3]     [4 - 5 - 6 - 7]

        if(ds.findUPar(3) == ds.findUPar(7)){
            System.out.println("Same");
        }
        else{
            System.out.println("Not Same");
        }

        ds.unionByRank(3, 7);

        // [1 - 2 - 3 - 7 - 6 - 5 - 4]

        if(ds.findUPar(3) == ds.findUPar(7)){
            System.out.println("Same");
        }
        else{
            System.out.println("Not Same");
        }

        // Test findRedundantConnection
        int[][] edgesRed = {{1, 2}, {1, 3}, {2, 3}};
        int[] redundant = findRedundantConnection(edgesRed);
        System.out.println("Redundant connection: " + Arrays.toString(redundant));



        // Test Kruskal's MST
        int V_kruskal = 5;
        int[][] kruskalEdges = {
            {0, 1, 2},
            {0, 2, 4},
            {1, 2, 1},
            {1, 3, 7},
            {2, 4, 3},
            {3, 4, 1}
        };
        int kruskalMSTWeight = kruskalsMST(V_kruskal, kruskalEdges);
        System.out.println("Kruskal's MST total weight: " + kruskalMSTWeight);

        // Test noOfProvinces
        int[][] isConnected = {
            {1, 1, 0, 0},
            {1, 1, 0, 0},
            {0, 0, 1, 1},
            {0, 0, 1, 1}
        };
        int provinces = noOfProvinces(isConnected);
        System.out.println("Number of provinces: " + provinces);
        
        // Test makeConnected
        int nConn = 6;
        int[][] connections = {
            {0, 1},
            {0, 2},
            {0, 3},
            {1, 2},
            {1, 3}
        };
        int minOps = makeConnected(nConn, connections);
        System.out.println("Minimum operations to connect all computers: " + minOps);


        // Accounts Merge
        List<List<String>> accounts = new ArrayList<>();
        accounts.add(Arrays.asList("Sham", "shamprakash@mail.com", "sham00@mail.com"));
        accounts.add(Arrays.asList("Danny", "dannybravo@mail.com"));
        accounts.add(Arrays.asList("Sham", "shamprakash@mail.com", "sham_newyork@mail.com"));
        accounts.add(Arrays.asList("Praveen", "praveen@mail.com"));
        accounts.add(Arrays.asList("Sharon", "sharon@mail.com", "sharon123@mail.com"));

        List<List<String>> merged = accountsMerge(accounts);
        System.out.println("Merged accounts:");
        for (List<String> acc : merged) {
            System.out.println(acc);
        }

        // Test numOfIslandsII
        int rows = 4, cols = 5;
        // int[][] operators = {{0,0},{0,0},{1,1},{1,0},{0,1},{0,3},{1,3},{0,4}, {3,2},{2,2},{1,2}, {0,2}};
        int[][] operators = {
            {1, 1},
            {0, 1},
            {3, 3},
            {3, 4},
            {1, 2},
            {0, 4}
        };
        Graph2 graph = new Graph2();
        List<Integer> islands = graph.numOfIslandsII(rows, cols, operators);
        System.out.println("Number of islands after each operator: " + islands);

        // Test largestIsland
        int[][] grid = {
            {1, 0, 1, 1},
            {1, 1, 0, 1},
            {0, 1, 0, 0},
            {1, 0, 1, 1}
        };
        int maxIsland = largestIsland(grid);
        System.out.println("Largest possible island size after flipping one 0: " + maxIsland);

        // Test Most Stones Removed with Same Row or Column
        int[][] stones = {
            {0, 0},
            {0, 1},
            {1, 0},
            {1, 2},
            {2, 1},
            {2, 2}
        };
        int removed = removeStones(stones);
        System.out.println("Most stones removed with same row or column: " + removed);


        // Test Bridges in a Graph (Critical Connections)
        int nBridges = 5;
        List<List<Integer>> bridgeEdges = new ArrayList<>();
        bridgeEdges.add(Arrays.asList(1, 0));
        bridgeEdges.add(Arrays.asList(0, 2));
        bridgeEdges.add(Arrays.asList(2, 1));
        bridgeEdges.add(Arrays.asList(0, 3));
        bridgeEdges.add(Arrays.asList(3, 4));
        List<List<Integer>> bridges = criticalConnections(nBridges, bridgeEdges);
        System.out.println("Bridges (critical connections) in the graph:");
        for (List<Integer> bridge : bridges) {
            System.out.println(bridge);
        }


        // Test Articulation Points
        int V_ap = 5;
        int[][] apEdges = {
            {0, 1},
            {1, 2},
            {2, 0},
            {1, 3},
            {3, 4}
        };
        ArrayList<Integer> articulation = articulationPoints(V_ap, apEdges);
        System.out.println("Articulation points in the graph: " + articulation);

        
    }

}
