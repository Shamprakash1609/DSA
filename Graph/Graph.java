import java.util.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Graph {
    
    public static ArrayList<Integer> bfsOfGraph(int V , ArrayList<ArrayList<Integer>> adj){
        // TC: O(N + E), where N is the number of vertices and E is the number of edges
        // SC: O(N), for the visited array and queue

        ArrayList<Integer> bfs = new ArrayList<>();

        boolean visited[] = new boolean[V];

        Queue <Integer> q = new LinkedList <> ();

        q.add(0);
        visited[0] = true;

        while(!q.isEmpty()){
            Integer node = q.poll();
            bfs.add(node); 

            for(Integer it : adj.get(node)){
                if(visited[it] == false){
                    visited[it] = true;
                    q.add(it);
                }
            }
        }

        return bfs;
    }

    public static ArrayList<Integer> dfsOfGraph(int V , ArrayList<ArrayList<Integer>> adj){
        // TC : O(N) * (2 * E[edges]) SC : O(N) * 3
        boolean visited[] = new boolean[V + 1];

        visited[0] = true;
        ArrayList<Integer> ls = new ArrayList<>();
        dfs(0, visited, adj, ls); 
        return ls;
    }

    public static void dfs(int node , boolean vis[] ,ArrayList<ArrayList<Integer>> adj , ArrayList<Integer> ls){
        vis[node] = true;
        ls.add(node);

        for(Integer it : adj.get(node)){
            if(vis[it] == false){
                vis[it] = true;
                dfs(it, vis, adj, ls);
            }
        }
    }


///////////////////////////////////////////////////////// Problems on Graphs \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

    // Number of Complete components
    public static  int countCompleteComponents(int n, int[][] edges) {
        ArrayList<ArrayList<Integer>> adjLs = new ArrayList<>();

        for(int i = 0 ; i < n ; i++){
            adjLs.add(new ArrayList<Integer>());
        }

        for(int e[] : edges){
            int u = e[0];
            int v = e[1];

            adjLs.get(u).add(v);
            adjLs.get(v).add(u);
        }

        int vis[] = new int[n];

        int cnt = 0;

        for(int i = 0 ; i < n ; i++){
            if(vis[i] == 0){
                List<Integer> component = new ArrayList<>();
                dfsComp(i , vis , adjLs , component);


                int nodes = component.size();

                int edSum = 0;

                for(int node : component){
                    edSum += adjLs.get(node).size();
                }

                int edInComp = edSum / 2;

                if(edInComp == nodes * (nodes - 1) / 2) cnt++;
            }

        }

        return cnt;
        
    }

    public static void dfsComp(int node , int[] vis , ArrayList<ArrayList<Integer>> adjLs , List<Integer> component ){
        vis[node] = 1;
        component.add(node);

        for(Integer it : adjLs.get(node)){
            if(vis[it] == 0){
                dfsComp(it , vis , adjLs , component);
            }
        }
    }


    // Number of Provinces
    public static  int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        
        ArrayList<ArrayList<Integer>> adjLs = new ArrayList<>();

        for(int i = 0 ; i < n ; i++){
            adjLs.add(new ArrayList<Integer>());
        }

        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < n ; j++){
                if(isConnected[i][j] == 1 && i != j){
                    adjLs.get(i).add(j);
                    adjLs.get(j).add(i);
                }
            }
        }

        int vis[] = new int[n];
        int cnt = 0;

        for(int i = 0 ; i<n ; i++){
            if(vis[i] == 0){
                cnt++;
                dfsTraversal(i , vis , adjLs);
                // bfsTraversal(i , vis , adjLs);
            }
        }

        return cnt;
    }

    public static void dfsTraversal(int node , int vis[] , ArrayList<ArrayList<Integer>> adjLs){
        vis[node] = 1;

        for(Integer it : adjLs.get(node)){
            if(vis[it] == 0){
                vis[it] = 1;
                dfsTraversal(it , vis , adjLs);
            }
        }
    }

    public static void bfsTraversal(int node , int vis[] , ArrayList<ArrayList<Integer>> adjLs){

        Queue<Integer> q = new LinkedList<>();

        q.add(node);
        vis[node] = 1;

        while(!q.isEmpty()){
            Integer ver = q.poll();

            for(Integer it : adjLs.get(ver)){
                if(vis[it] == 0){
                    vis[it] = 1;
                    q.add(it);
                }
            }
        }
    }


    // Number of Island
    public static  int numIslands(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        int vis[][] = new int[n][m];

        int cnt = 0;

        for(int i = 0 ; i<n ; i++){
            for(int j = 0 ; j < m ; j++){
                if(vis[i][j] == 0 && grid[i][j] == '1'){
                    cnt++;
                    bfs4dChar (i , j , vis , grid);
                    // dfs4dChar (i , j , vis , grid);
                    // bfs8dChar (i , j , vis , grid);
                    // dfs8dChar (i , j , vis , grid);
                }
            }
        }

        return cnt;
    }

    private static void bfs4dChar(int row , int col , int[][] vis , char[][] grid){

        vis[row][col] = 1;

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{row , col});

        int n = grid.length , m = grid[0].length;

        int[][] directions = {{-1,0} , {0,-1} , {0,1} , {1,0}};

        while(!q.isEmpty()){
            int curr[] = q.poll();
            int ro = curr[0];
            int co = curr[1];

            for(int[] dir : directions){
                int nRow = ro + dir[0];
                int nCol = co + dir[1];

                if(nRow >= 0 && nRow < n && nCol >= 0 && nCol < m && grid[nRow][nCol] == '1' && vis[nRow][nCol] == 0){
                    q.add(new int[]{nRow , nCol});
                    vis[nRow][nCol] = 1;
                }
            }
            
        }


    }

    private static void dfs4dChar(int row, int col, int[][] vis, char[][] grid) {
        vis[row][col] = 1;

        int n = grid.length, m = grid[0].length;

        int[][] directions = {{-1,0}, {0,-1}, {0,1}, {1,0}};

        for (int[] dir : directions) {
            int nRow = row + dir[0];
            int nCol = col + dir[1];

            if (nRow >= 0 && nRow < n && nCol >= 0 && nCol < m &&
                grid[nRow][nCol] == '1' && vis[nRow][nCol] == 0) {
                dfs4dChar(nRow, nCol, vis, grid);
            }
        }
    }

    private static void bfs8dChar(int row , int col , int[][] vis , char[][] grid){

        vis[row][col] = 1;

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{row , col});

        int n = grid.length , m = grid[0].length;

        while(!q.isEmpty()){
            int curr[] = q.poll();
            int ro = curr[0];
            int co = curr[1];

            for(int delRow = -1 ; delRow <= 1 ; delRow++){
                for(int delCol = -1 ; delCol <= 1 ; delCol++){
                    int nRow = ro + delRow;
                    int nCol = co + delCol;

                    if(nRow >= 0 && nRow <n && nCol >= 0 && nCol < m && grid[nRow][nCol] == '1' && vis[nRow][nCol] == 0){
                        vis[nRow][nCol] = 1;
                        q.add(new int[]{nRow , nCol});
                    }
                }
            }
        }


    }

    private static void dfs8dChar(int row , int col , int[][] vis , char[][] grid){

        vis[row][col] = 1;

        int n = grid.length, m = grid[0].length;

        for(int delRow = -1 ; delRow <= 1 ; delRow++){
            for(int delCol = -1 ; delCol <= 1 ; delCol++){
                int nRow = row + delRow;
                int nCol = col + delCol;

                if(nRow >= 0 & nCol >= 0 && nRow <n && nCol < m && grid[nRow][nCol] == '1' && vis[nRow][nCol] == 0){
                    vis[nRow][nCol] = 1;
                    dfs8dChar(nRow , nCol , vis , grid);
                }
            }
        }
     }

    // Flood fill algo
    public static int[][] floodFill(int[][] image, int sr, int sc, int newC) {
        int n = image.length;
        int m = image[0].length;

        int ans[][] = image;

        // int vis[][] = new int[n][m];

        int intial = image[sr][sc];

        dfsFill(sr , sc , ans , image , intial , newC);
        // bfsFill(sr , sc , ans , image , intial , newC);

        return ans;

    }

    private static void dfsFill(int row , int col , int[][] ans , int [][] image , int intial , int newC){
        ans[row][col] = newC;

        int[][] directions = {{-1,0}, {0,-1}, {0,1}, {1,0}};

        int n = image.length , m = image[0].length;

        for(int [] dir : directions){
            int nRow = row + dir[0];
            int nCol = col + dir[1];

            if(nRow >= 0 && nCol >= 0 && nRow < n && nCol < m && image[nRow][nCol] == intial && ans[nRow][nCol] != newC){
                ans[row][col] = newC;
                dfsFill(nRow , nCol , ans , image , intial , newC);
            }
        }
    }
    
    private static void bfsFill(int row , int col , int[][] ans , int [][] image , int intial , int newC){

        Queue<int[]> q = new LinkedList<>();

        ans[row][col] = newC;

        q.add(new int[]{row , col});

        int n = image.length , m = image[0].length;

        int[][] directions = {{-1,0}, {0,-1}, {0,1}, {1,0}};

        while(!q.isEmpty()){
            int curr[] = q.poll();
            int ro = curr[0];
            int co = curr[1];

            for(int [] dir : directions){
                int nRow = ro + dir[0];
                int nCol = co + dir[1];

                if(nRow >= 0 && nCol >= 0 && nRow < n && nCol < m && image[nRow][nCol] == intial && ans[nRow][nCol] != newC){
                    ans[nRow][nCol] = newC;
                    q.add(new int[]{nRow , nCol});
                }
            }
        }
    }

    // Rotting oranges
    public static int orangesRotting(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        int vis[][] = new int[n][m];

        Queue<int[]> q = new LinkedList<>();

        int cntFresh = 0;

        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < m ; j++){
                if(grid[i][j] == 2){
                    q.add(new int[]{i , j , 0});
                    vis[i][j] = 2;
                }else{
                    vis[i][j] = 0;
                }
                
                if(grid[i][j] == 1) cntFresh++;
            }
        }

        int time = 0;
        int[][] directions = {{-1,0}, {0,-1}, {0,1}, {1,0}};
        int cnt = 0;

        while(!q.isEmpty()){
            int curr[] = q.poll();
            int ro = curr[0];
            int co = curr[1];
            int tm = curr[2];

            time = Math.max(time , tm);

            for(int dir[] : directions){
                int nRow = ro + dir[0];
                int nCol = co + dir[1];

                if(nRow < n && nCol < m && nRow >= 0 && nCol >= 0 && grid[nRow][nCol] == 1 && vis[nRow][nCol] == 0){
                    q.add(new int[]{nRow , nCol , tm + 1});
                    vis[nRow][nCol] = 2;
                    cnt++;
                }
            }
            
        }

        if(cnt != cntFresh) return -1;
        return time;
    } 

    // Check for Cycle in a Undirected graph
    public static boolean isCycle(int V, int[][] edges) {
       
       ArrayList<ArrayList<Integer>> adjLs = new ArrayList<>();
       
        for(int i = 0 ; i<V ; i++){
           adjLs.add(new ArrayList<Integer>());
        }
       
       for(int [] edge : edges){
           int u = edge[0];
           int v = edge[1];
           
           adjLs.get(u).add(v);
           adjLs.get(v).add(u);
       }
       
       
       int vis[] = new int[V];
       
        for(int i = 0 ; i < V ; i++){
           if(vis[i] == 0){
            //    if(checkForCycleB(i , V , adjLs , vis)) return true;
               if(checkForCycleD(i ,-1 ,V , adjLs , vis)) return true;
           }
        }
       
       return false;
        
    }
    
    // Using BFS
    public static boolean checkForCycleB(int src , int V , ArrayList<ArrayList<Integer>> adjLs , int[] vis){
        // TC : O(N + 2E) SC : O(N)
        vis[src] = 1;
        
        Queue<int[]> q = new LinkedList<>();
        
        q.add(new int[]{src , -1});
        
        while(!q.isEmpty()){
            int curr[] = q.poll();
            
            int node = curr[0];
            int parent = curr[1];
            
            for(Integer it : adjLs.get(node)){
                if(vis[it] == 0){
                    vis[it] = 1;
                    q.add(new int[]{it , node});
                }
                // If the node is visted and did not come from the parent of the current node 
                // if the parent is not in the adjLs then it is been visited by other node hence is has cycle
                else if(parent != it){ 
                    return true;
                }
            }
        }
        
        return false;
    }

    // Using DFS
    public static  boolean checkForCycleD(int node ,int parent , int V ,  ArrayList<ArrayList<Integer>> adjLs ,  int [] vis){
        // TC : O(N+ 2E)  SC: O(N)
        vis[node] = 1;
        
        for(Integer it : adjLs.get(node)){
            if(vis[it] == 0){
                vis[it] = 1;
                if(checkForCycleD(it , node , V , adjLs , vis) == true){
                    return true;
                }
            }
            
            else if(it != parent){
                return true;
            }
        }
        
        return false;
    }


    // 0/1 Matrix
    public static int[][] updateMatrix(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;

        int[][] vis = new int[n][m];
        int[][] ans = new int[n][m];

        Queue<int[]> q = new LinkedList<>();

        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < m ; j++){
                if(mat[i][j] == 0){
                    q.add(new int[]{i , j , 0});
                    vis[i][j] = 1;
                }
            }
        }

        int[][] directions = {{-1,0}, {0,-1}, {0,1}, {1,0}};

        while(!q.isEmpty()){
            int curr[] = q.poll();

            int row = curr[0];
            int col = curr[1];
            int steps = curr[2];

            ans[row][col] = steps;

            for(int dir[] : directions){
                int nRow = row + dir[0];
                int nCol = col + dir[1];

                if(nRow < n && nCol < m && nRow >= 0 && nCol >= 0 && vis[nRow][nCol] == 0){
                    vis[nRow][nCol] = 1;
                    q.add(new int[]{nRow , nCol , steps+1});
                }
            }
        }

        return ans;

    }


    // Surrounded Regions
    public static  void solve(char[][] board) {
        int n = board.length;
        int m = board[0].length;

        int vis[][] = new int[n][m];


       for(int j = 0 ; j < m ; j++){
            if(vis[0][j] == 0 && board[0][j] == 'O'){
                // fillOs_D(0 , j , vis , board);
                fillOs_B(0 , j , vis , board);
            }

            if(vis[n-1][j] == 0 && board[n-1][j] == 'O'){
                // fillOs_D(n-1 , j , vis , board);
                fillOs_B(n-1 , j , vis , board);
            }
       }

       for(int i = 0 ; i < n ; i++){
            if(vis[i][0] == 0 && board[i][0] == 'O'){
                // fillOs_D(i , 0 , vis , board);
                fillOs_B(i , 0 , vis , board);
            }

            if(vis[i][m-1] == 0 && board[i][m-1] == 'O'){
                // fillOs_D(i , m-1 , vis , board);
                fillOs_B(i , m-1 , vis , board);
            }
       }


       for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < m ; j++){
                if(vis[i][j] == 0 && board[i][j] == 'O'){
                    board[i][j] = 'X';
                }
            }
       }
    }

    public static void fillOs_D(int row , int col , int[][] vis,  char [][] board){
        vis[row][col] = 1;

        int n = board.length , m = board[0].length;

        int[][] directions = {{-1,0}, {0,-1}, {0,1}, {1,0}};

        for(int dir[] : directions){

            int nRow = row + dir[0];
            int nCol = col + dir[1];

            if(nRow < n && nCol < m && nRow >= 0 && nCol >= 0 
            && vis[nRow][nCol] == 0 && board[nRow][nCol] == 'O'){
                fillOs_D(nRow , nCol , vis , board);
            }
        }
    }

    public static void fillOs_B(int row , int col , int[][] vis,  char [][] board){

        Queue<int[]> q = new LinkedList<>();

        vis[row][col] = 1;
        q.add(new int[]{row , col});

        int[][] directions = {{-1,0}, {0,-1}, {0,1}, {1,0}};

        int n = board.length , m = board[0].length;

        while(!q.isEmpty()){

            int curr[] = q.poll();
            int ro = curr[0];
            int co = curr[1];

            for(int dir[] : directions){
                int nRow = ro + dir[0];
                int nCol = co + dir[1];

                if(nRow < n && nCol < m && nRow >= 0 && nCol >= 0 && vis[nRow][nCol] == 0 && board[nRow][nCol] == 'O'){
                    q.add(new int[]{nRow , nCol});
                    vis[nRow][nCol] = 1;
                }
            }
        }
    }


    // Number of Enclaves
    public static int numEnclaves(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        int vis[][] = new int[n][m];

        for(int j = 0 ; j < m ; j++){
            if(grid[0][j] == 1 && vis[0][j] == 0){
                enclave_B(0 , j , vis , grid);
            }

            if(grid[n-1][j] == 1 && vis[n-1][j] == 0){
                enclave_B(n-1 , j , vis , grid);
            }
        }

        for(int i = 0 ; i < n ; i++){
            if(grid[i][0] == 1 && vis[i][0] == 0){
                enclave_B(i , 0 , vis , grid);
            }

            if(grid[i][m-1] == 1 && vis[i][m-1] == 0){
                enclave_B(i , m-1 , vis , grid);
            }
        }

        int cnt = 0;

        for(int i = 0 ; i<n ; i++){
            for(int j = 0 ; j<m ; j++){
                if(vis[i][j] == 0 && grid[i][j] == 1){
                    cnt++;
                }
            }
        }

        return cnt;
    }

    public static void enclave_D(int row , int col , int[][] vis , int[][] grid){
        vis[row][col] = 1;

        int n = grid.length, m = grid[0].length;

        int [][] directions = {{-1,0} , {0,1} , {1,0} , {0 , -1}};

        for(int dir[] : directions){
            int nRow = row + dir[0];
            int nCol = col + dir[1];

            if(nRow >= 0 && nCol >= 0 && nRow < n && nCol < m && vis[nRow][nCol] == 0 && grid[nRow][nCol] == 1){
                enclave_D(nRow , nCol , vis , grid);
            }
        }
    }

    public static void enclave_B(int row , int col , int[][] vis , int[][] grid){

        Queue<int[]> q = new LinkedList<>();

        vis[row][col] = 1;
        q.add(new int[]{row , col});

        int n = grid.length, m = grid[0].length;
        
        int [][] directions = {{-1,0} , {0,1} , {1,0} , {0 , -1}};

        while(!q.isEmpty()){
            int curr[] = q.poll();

            int ro = curr[0];
            int co = curr[1];

            for(int dir[] : directions){
                int nRow = ro + dir[0];
                int nCol = co + dir[1];

                if(nRow >= 0 && nCol >= 0 && nRow < n && nCol < m && vis[nRow][nCol] == 0 && grid[nRow][nCol] == 1){
                    vis[nRow][nCol] = 1;
                    q.add(new int[]{nRow , nCol});
                }
            }
        }

    }

    // Number of Distinct Islands
    private static int countDistinctIslands(int[][] grid) {
        // Your Code here
        int n = grid.length;
        int m = grid[0].length;
        
        int vis[][] = new int[n][m];
        
        Set<ArrayList<String>> st = new HashSet<>();

        
        for(int i = 0 ; i < n; i++){
            for(int j = 0 ; j < m ; j++){
                if(vis[i][j] == 0 && grid[i][j] == 1){
                    
                    ArrayList<String> ds = new ArrayList<>();
                    
                    dfs_distIsland(i , j , grid , vis , new int[]{i , j} , ds);
                    
                    st.add(ds);
                }
            }
        }
        
        return st.size();
    }
    
    private static void dfs_distIsland(int i , int j , int[][] grid , int vis[][] , int base[] , ArrayList<String> ds){
        vis[i][j] = 1;
        
        ds.add(Arrays.toString(new int[] {i - base[0] , j - base[1]}));
        
        
        int[][] directions = {{0,1}, {1,0}, {-1,0}, {0,-1}};
        
        for(int dir[] : directions){
            int row = i + dir[0];
            int col = j + dir[1];
            
            if(row >= 0 && row < grid.length && col >= 0 && col < grid[0].length && vis[row][col] == 0 && grid[row][col] == 1){
               dfs_distIsland(row , col , grid ,  vis , base , ds); 
            }
        }
        
    }


    // Is a Bipartite Graph
    public static boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int m = graph[0].length;

        int color[] = new int[n];

        Arrays.fill(color, -1);

        for(int i = 0 ; i <n ; i++){
            if(color[i] == -1){
                // if(checkBi_B(i , n , graph , color) == false){
                if(checkBi_D(i , 0 , n , graph , color) == false){
                    return false;
                }
            }
        }

        return true;
    }

    public static boolean checkBi_B(int start , int n , int[][] graph , int[] color){

        Queue<Integer> q = new LinkedList<>();

        q.add(start);
        color[start] = 0;

        while(!q.isEmpty()){
            int node = q.poll();

            for(int it : graph[node]){

                if(color[it] == -1){
                    color[it] = 1 - color[node];
                    q.add(it);
                }
                else if(color[it] == color[node]){
                    return false;
                }
            }
        }

        return true;
    }

    public static boolean checkBi_D(int start , int col , int n , int [][] graph , int[] color){
        color[start] = col ;

        for(int it : graph[start]){
            if(color[it] == -1){
                if(checkBi_D(it , 1 - color[start] , n , graph , color) == false){
                    return false;
                }
            }
            else if(color[it] == color[start]){
                return false;
            }
        }

        return true;
    }

    // Possible Bipartition
    public static boolean possibleBipartition(int n, int[][] dislikes) {
        ArrayList<ArrayList<Integer>> adjLs = new ArrayList<>();

        for(int i = 0 ; i <= n ; i++){
            adjLs.add(new ArrayList<Integer>());
        }

        for(int pair[] : dislikes){
            int u = pair[0];
            int v = pair[1];

            adjLs.get(u).add(v);
            adjLs.get(v).add(u);
        }

        int[] color = new int[n + 1];

        Arrays.fill(color , -1);

        for( int i = 1 ; i <= n ; i++){
            if (color[i] == -1) {
                // if(isBipartite_B(i , color , adjLs) == false){
                if(isBipartite_D(i , 0 , color , adjLs) == false){
                    return false;
                }
            }
            
        }

        return true;
    }

    public static boolean isBipartite_B(int start , int[] color , ArrayList<ArrayList<Integer>> adjLs){
        
        Queue<Integer> q = new LinkedList<>();

        color[start] = 0;
        q.add(start);

        while(!q.isEmpty()){
            int node = q.poll();

            for(int it : adjLs.get(node)){
                if(color[it] == -1){
                    color[it] = 1 - color[node];
                    q.add(it);
                }
                else if(color[node] == color[it]){
                    return false;
                }
               
            }
        }

        return true;
    }

    public static boolean isBipartite_D(int start , int c ,int[] color , ArrayList<ArrayList<Integer>> adjLs){

        color[start] = c;
        
        for(int it : adjLs.get(start)){
            if(color[it] == -1){
                if(isBipartite_D(it , 1 - color[start] , color , adjLs) == false){
                    return false;
                }
            }
            

            else if(color[it] == color[start]){
                return false;
            }
        }

        return true;
    }

    // Detect Cycle in Directed Graph
    public static  boolean isCyclic(int V, int[][] edges) {
        ArrayList<ArrayList<Integer>> adjLs = new ArrayList<>();
        
        for(int i = 0 ; i < V ; i++){
            adjLs.add(new ArrayList<Integer>());
        }
        
        for(int edge[] : edges){
            int u = edge[0];
            int v = edge[1];
            
            adjLs.get(u).add(v);
            // adjLs.get(v).add(u); --- ! Not to done in a Directed Graph
        }
        
        int vis[] = new int [V];
        int pathVis[] = new int[V];
        
        for(int i = 0 ; i < V ; i++){
            if(vis[i] == 0){
                if(checkDirect_D(i , adjLs , vis , pathVis) == true){
                    return true;
                }
            }
        }
        
        return false;
    }
    
    public static boolean checkDirect_D(int node , ArrayList<ArrayList<Integer>> adjLs , int vis[] , int pathVis[]){
        vis[node] = 1;
        pathVis[node] = 1;
        
        for(int it : adjLs.get(node)){
            if(vis[it] == 0){
                if(checkDirect_D(it , adjLs , vis , pathVis) == true) return true;
            }
            
            else if(pathVis[it] == 1){
                return true;
            }
        }
        
        pathVis[node] = 0;
        return false;
    }

    // Find eventual Safe States 
    public static  List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        
        int vis[] = new int [n];
        int pathVis[] = new int [n];
        int safeNodes[] = new int[n];
        
        for(int i = 0 ; i < n ; i++){
            if(vis[i] == 0){
                checkSafe_D(i , graph , vis , pathVis , safeNodes);
            }
        }

        List<Integer> ans = new ArrayList<>();

        for(int i = 0 ; i < n ; i++){
            if(safeNodes[i] == 1){
                ans.add(i);
            }
        }

        return ans;

    }

    public static boolean checkSafe_D(int node , int [][] graph , int[] vis , int[] pathVis , int safeNodes[]){

        vis[node] = 1;
        pathVis[node] = 1;

        for(int it : graph[node]){
            if(vis[it] == 0){
                if(checkSafe_D(it , graph , vis , pathVis , safeNodes) == true){
                    return true;
                }
            }
            else if(pathVis[it] == 1){
                return true;
            }
        }

        safeNodes[node] = 1;
        pathVis[node] = 0;
        return false;
    }

    // TopoLogical Sort -> only valid in DAGs
    public static ArrayList<Integer> topoSort(int V, int[][] edges) {

        ArrayList<ArrayList<Integer>> adjLs = new ArrayList<>();
        
        for(int i = 0 ; i < V ; i++){
            adjLs.add(new ArrayList<Integer>());
        }
        
        for(int edge[] : edges){
            int u = edge[0];
            int v = edge[1];
            
            adjLs.get(u).add(v);
        }
        
        int vis[] = new int[V];
        
        Stack<Integer> st = new Stack<>();
        
        for(int i = 0 ; i < V ; i++){
            if(vis[i] == 0){
                sortopo_D(i , adjLs , st , vis);
            }
        }
        
        ArrayList<Integer> ans = new ArrayList<>();
        
        for(int i = 0 ; i < V ; i++){
            ans.add(st.pop());
        }
        
        return ans;
        
    }
    
    public static void sortopo_D(int node ,   ArrayList<ArrayList<Integer>> adjLs , Stack<Integer> st , int[] vis){
        
        vis[node] = 1;
        
        for(int it : adjLs.get(node)){
            
            if(vis[it] == 0){
                sortopo_D(it , adjLs , st , vis);
            }
        }
        
        st.push(node);
    }

    // Khan's algo TopoSort - BFS
    public static ArrayList<Integer> topoSort_Khan(int V, int[][] edges) {

        ArrayList<ArrayList<Integer>> adjLs = new ArrayList<>();
        
        for(int i = 0 ; i < V ; i++){
            adjLs.add(new ArrayList<Integer>());
        }
        
        for(int edge[] : edges){
            int u = edge[0];
            int v = edge[1];
            
            adjLs.get(u).add(v);
        }
        
        int inDeg[] = new int[V];
        
        for(int i = 0 ; i < V ; i++){
            for(int it : adjLs.get(i)){
                inDeg[it]++;
            }
        }
    
        Queue<Integer> q = new LinkedList<>();
        for(int i = 0 ; i < V ; i++){
            if(inDeg[i] == 0){
                q.add(i);
            }
        }
        
        ArrayList<Integer> ans = new ArrayList<>();
        
        while(!q.isEmpty()){
            int node = q.poll();
            ans.add(node);
            
            for(int it : adjLs.get(node)){
                inDeg[it]--;
                
                if(inDeg[it] == 0){
                    q.add(it);
                }
            }
        }
        
        return ans;
        
    }

    // Detect Cycle in Directed Graph Using BFS
    public static  boolean isCyclic_Khan_B(int V, int[][] edges) {
        ArrayList<ArrayList<Integer>> adjLs = new ArrayList<>();
        
        for(int i = 0 ; i < V ; i++){
            adjLs.add(new ArrayList<Integer>());
        }
        
        for(int edge[] : edges){
            int u = edge[0];
            int v = edge[1];
            
            adjLs.get(u).add(v);
            // adjLs.get(v).add(u); --- ! Not to done in a Directed Graph
        }
        
        int inDeg[] = new int[V];
        
        for(int i = 0 ; i < V ; i++){
            for(int it : adjLs.get(i)){
                inDeg[it]++;
            }
        }
    
        Queue<Integer> q = new LinkedList<>();
        for(int i = 0 ; i < V ; i++){
            if(inDeg[i] == 0){
                q.add(i);
            }
        }
        
        ArrayList<Integer> ans = new ArrayList<>();
        
        while(!q.isEmpty()){
            int node = q.poll();
            ans.add(node);
            
            for(int it : adjLs.get(node)){
                inDeg[it]--;
                
                if(inDeg[it] == 0){
                    q.add(it);
                }
            }
        }
        
        return (ans.size() != V); // If we cannot produce a toposort of size n the only problem is the graph got the cycle
    }

    // Course Schedule
    public static boolean courseSchedule_I(int numCourses, int[][] prerequisites) { 

        ArrayList<ArrayList<Integer>> adjLs = new ArrayList<>();

        for(int i = 0 ; i < numCourses ; i++){
            adjLs.add(new ArrayList<Integer>());
        }

        for(int[] edge : prerequisites){
            int u = edge[0];
            int v = edge[1];

            adjLs.get(v).add(u); // Order matters
        }

        int inDeg [] = new int[numCourses];

        for(int i = 0 ; i < numCourses ; i++){
            for(int it : adjLs.get(i)){
                inDeg[it]++;
            }
        }

        Queue<Integer> q = new LinkedList<>();

        for(int i = 0 ; i < numCourses ; i++){
            if(inDeg[i] == 0){
                q.add(i);
            }
        }

        List<Integer> topo = new ArrayList<>();

        while(!q.isEmpty()){
            int node = q.poll();

            topo.add(node);

            for(int it : adjLs.get(node)){
                inDeg[it]--;

                if(inDeg[it] == 0){
                    q.add(it);
                }
            }
        }

        return (topo.size() == numCourses);
    }

    public static int[] courseSchedule_II (int numCourses, int[][] prerequisites) {
        ArrayList<ArrayList<Integer>> adjLs = new ArrayList<>();

        for(int i = 0 ; i < numCourses ; i++){
            adjLs.add(new ArrayList<Integer>());
        }

        for(int[] edge : prerequisites){
            int u = edge[0];
            int v = edge[1];

            adjLs.get(v).add(u);
        }

        int inDeg [] = new int[numCourses];

        for(int i = 0 ; i < numCourses ; i++){
            for(int it : adjLs.get(i)){
                inDeg[it]++;
            }
        }

        Queue<Integer> q = new LinkedList<>();

        for(int i = 0 ; i < numCourses ; i++){
            if(inDeg[i] == 0){
                q.add(i);
            }
        }

        int topo[] = new int[numCourses];
        int ind = 0;

        while(!q.isEmpty()){
            int node = q.poll();

            topo[ind++] = node;

            for(int it : adjLs.get(node)){
                inDeg[it]--;

                if(inDeg[it] == 0){
                    q.add(it);
                }
            }
        }

       if(ind == numCourses) return topo;
       int arr[] = {};

       return arr;

    }

    // Eventual Safenodes Topo - BFS
    public static  List<Integer> eventualSafeNodes_B(int[][] graph) {
        int n = graph.length;
        
        ArrayList<ArrayList<Integer>> adjRev = new ArrayList<>();

        for(int i = 0 ; i < n; i ++){
            adjRev.add(new ArrayList<Integer>());
        }

        int[] inDeg = new int[n];

        for(int i = 0 ; i < n ; i++){
            for(int it : graph[i]){
                adjRev.get(it).add(i);
                inDeg[i]++;
            }
        }

        Queue<Integer> q = new LinkedList<>();

        for(int i = 0 ; i < n ; i++){
            if(inDeg[i] == 0){
                q.add(i);
            }
        }

        List<Integer> safeNodes = new ArrayList<>();

        while(!q.isEmpty()){
            int node = q.poll();
            safeNodes.add(node);

            for(int it : adjRev.get(node)){
                inDeg[it]--;
                if(inDeg[it] == 0){
                    q.add(it);
                }
            }
        }

        Collections.sort(safeNodes);

        return safeNodes;

    }

    // Alien Dictionary
    public static String alienDict(String[] words) {
    
        int n = words.length;
        
        int K = 26;
        
        ArrayList<ArrayList<Integer>> adjLs = new ArrayList<>();
        
        for(int i = 0 ; i < K ; i++){
            adjLs.add(new ArrayList<Integer>());
        }
        
        int [] present = new int[K];
        
        for(String word : words){
            for(char alpha : word.toCharArray()){
                present[alpha - 'a'] = 1;
            }
        }
        
        
        for(int i = 0 ; i < n-1 ; i++){
            String s1 = words[i];
            String s2 = words[i+1];
            
            int len = Math.min(s1.length() , s2.length());
            boolean found = false;
            
            for(int ptr = 0 ; ptr < len ; ptr++){
                if(s1.charAt(ptr) != s2.charAt(ptr)){
                    adjLs.get(s1.charAt(ptr) - 'a').add(s2.charAt(ptr) - 'a');
                    found = true;
                    break;
                }
            }
            
             if (!found && s1.length() > s2.length()) {
                return "";
            }
        }
        
        List<Integer> topo = topoSort_dict(adjLs , K , present);
        if (topo.size() == 0) return "";
        
        
        String ans = "";
        for(int it : topo){
            ans += (char) (it + (int)'a');
        }
        
        return ans;

    }
    
    public static List<Integer> topoSort_dict(ArrayList<ArrayList<Integer>> adjLs  , int K , int[] present){
        
        int inDeg[] = new int[K];
        
        for(int i = 0 ; i < K ; i++){
            for(int it : adjLs.get(i)){
                inDeg[it]++;
            }
        }
        
        Queue<Integer> q = new LinkedList<>();
        
        for(int i = 0 ; i < K ; i++){
            if(present[i] == 1 &&inDeg[i] == 0){
                q.add(i);
            }
        }
        
        List<Integer> topo = new ArrayList<>();
        
        while(!q.isEmpty()){
            int node = q.poll();
            topo.add(node);
            
            for(int it : adjLs.get(node)){
                inDeg[it]--;
                
                if(inDeg[it] == 0){
                    q.add(it);
                }
            }
        }
        
        int totPresent = 0;
        
        for(int p : present){
            if(p == 1) totPresent++;
        }
        
        if (topo.size() != totPresent) {
            return new ArrayList<>(); // cycle exists
        }
        
        return topo;
        
    }

    // Shortest Path in a DAG
    public static  int[] shortestPath_DAG(int V, int E, int[][] edges) {
        
        ArrayList<ArrayList<int[]>> adjLs = new ArrayList<>();
        
        for(int i = 0 ; i < V ; i++){
            adjLs.add(new ArrayList<int[]>());
        }
        
        
        for(int edge[] : edges){
            int u = edge[0];
            int v = edge[1];
            int wt = edge[2];
            
            adjLs.get(u).add(new int[]{v , wt});
        }
        
        int vis[] = new int[V];
        
        Stack<Integer> st = new Stack<>();
        
        for(int i = 0 ; i < V ; i++){
            if(vis[i] == 0){
                topo_D(i , adjLs , st , vis);
            }
        }
        
        // Calculate Shortest path
        
        int dist[] = new int[V];
        Arrays.fill(dist , (int) 1e9);
        
        dist[0] = 0;
        
        while(!st.isEmpty()){
            
            int node = st.pop();
            
            for(int it[] : adjLs.get(node)){
                int v = it[0];
                int wt = it[1];
                
                if(dist[node] + wt < dist[v]){
                    dist[v] = wt + dist[node];
                }
            }
        }
        
        for(int i = 0 ; i < V ; i++){
            if(dist[i] == (int) 1e9){
                dist[i] = -1;
            }
        }
        
        
        return dist;
        
        
        
    }
    
    public static void topo_D(int node , ArrayList<ArrayList<int[]>> adjLs, Stack<Integer> st , int[] vis){
        
        vis[node] = 1;
        
        for(int it[] : adjLs.get(node)){
            int v = it[0];
            if(vis[v] == 0){
               topo_D(v , adjLs , st , vis);
            }
        }
        
        st.add(node);
    }

    // Shortest Path in UG
    public static  int[] shortestPath_UG(ArrayList<ArrayList<Integer>> adj, int src) {
        
        int n = adj.size();
        
        Queue<Integer> q = new LinkedList<>();
        
        int []dist = new int[n];
        Arrays.fill(dist , (int)1e9);
        
        dist[src] = 0;
        
        q.add(src);
        
        while(!q.isEmpty()){
            int node = q.poll();
            
            for(int it : adj.get(node)){
                if(dist[node] + 1 < dist[it]){
                    dist[it] = dist[node] + 1;
                    q.add(it);
                }
            }
        }
        
        for(int i = 0 ; i < n ; i++){
            if(dist[i] == (int)1e9){
                dist[i] = -1;
            }
        }
        
        return dist;
        
    }

    // Word ladder - I
    static class PairWI{
        String first;
        int second;

       PairWI(String _first , int _second){
            this.first = _first;
            this.second = _second;
        }
    }

    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {

        Queue<PairWI> q = new LinkedList<>();

        q.add(new PairWI(beginWord , 1));

        Set<String> st = new HashSet<>();

        int n = wordList.size();

        for(int i = 0 ; i < n ; i++){
            st.add(wordList.get(i));
        }

        st.remove(beginWord);

        while(!q.isEmpty()){
            String word = q.peek().first;
            int steps = q.peek().second;
            q.remove();

            if(word.equals(endWord) == true) return steps;

            for(int i = 0 ; i < word.length() ; i++){
                for(char ch = 'a' ; ch <= 'z' ; ch++){
                    char replaceArr[] = word.toCharArray();
                    replaceArr[i] = ch;

                    String replaceWord = new String(replaceArr);

                    if(st.contains(replaceWord) == true){
                        st.remove(replaceWord);
                        q.add(new PairWI(replaceWord , steps+1));
                    }
                }
            }
        }

        return 0;
    }

    // Word Ladder - II
    public static  List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        
        Set<String> st = new HashSet<>();

        int n = wordList.size();

        for(int i = 0 ; i < n ; i++){
            st.add(wordList.get(i));
        }

        Queue<List<String>> q = new LinkedList<>();

        List<String> ls = new ArrayList<>();
        ls.add(beginWord);

        q.add(ls);

        int level = 0;
        List<String> usedOnLevel = new ArrayList<>();
        usedOnLevel.add(beginWord);

        
        List<List<String>> ans = new ArrayList<>();

        while(!q.isEmpty()){
            List<String> vec = q.poll();
            
            // Erase all words that has been used in previous level to transform
            if(vec.size() > level){
                level++;
                for(String it : usedOnLevel){
                    st.remove(it);
                }

                usedOnLevel.clear();
            }

            String word = vec.get(vec.size() - 1);

            if(word.equals(endWord)){
                if(ans.size() == 0) ans.add(vec); // For first sequence reached endWord
                else if(ans.get(0).size() == vec.size()) ans.add(vec); // add further sequence if it is of same length;
            }

            for(int i = 0 ; i < word.length() ; i++){
                for(char ch = 'a' ; ch <= 'z' ; ch++){
                    char replaceArr[] = word.toCharArray();
                    replaceArr[i] = ch;

                    String replacedWord = new String(replaceArr);
                    if(st.contains(replacedWord) == true){
                        vec.add(replacedWord);

                        List<String> temp = new ArrayList<>(vec);
                        q.add(temp);

                        usedOnLevel.add(replacedWord);
                        vec.remove(vec.size() - 1);
                    }
                }
            }
        }

        return ans;
    }

    
    
    public static void main(String[] args) {
        // Example graph with 5 vertices (0 to 4)
        int V = 5;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        // Initialize adjacency list
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        // Add edges to the graph (starting from node 1)
        adj.get(0).add(1);
        adj.get(1).add(0);
        adj.get(0).add(4);
        adj.get(4).add(0);
        adj.get(1).add(2);
        adj.get(2).add(1);
        adj.get(1).add(3);
        adj.get(3).add(1);
        adj.get(3).add(4);
        adj.get(4).add(3);


        // Perform BFS traversal
        ArrayList<Integer> bfsResult = bfsOfGraph(V, adj);

        // Print the BFS traversal result
        System.out.println("BFS Traversal of the graph: " + bfsResult);

        // Perform DFS traversal
        ArrayList<Integer> dfsResult = dfsOfGraph(V, adj);

        // Print the DFS traversal result
        System.out.println("DFS Traversal of the graph: " + dfsResult);

        // Number of Complete Components Example
        int nComplete = 6;
        int[][] completeEdges = {
            {0, 1},
            {0, 2},
            {1, 2},
            {3, 4},
            {3, 5}
        };
        int completeComponents = countCompleteComponents(nComplete, completeEdges);
        System.out.println("Number of Complete Components: " + completeComponents);

        // Number of Provinces 
        int[][] isConnected = {
            {1,0,0},
            {0,1,0},
            {0,0,1},
        };

        int provinces = findCircleNum(isConnected);
        System.out.println("Number of Provinces: " + provinces);

        // Number of Islands
        char[][] grid = {
            {'1','1','0','0','0'},
            {'1','1','0','0','0'},
            {'0','0','1','0','0'},
            {'0','0','0','1','1'}
        };

        int islands = numIslands(grid);
        System.out.println("Number of Islands: " + islands);

        // Flood Fill Algorithm Example
        int[][] image = {
            {1, 1, 1},
            {1, 1, 0},
            {1, 0, 1}
        };
        int sr = 1, sc = 1, newColor = 2;
        int[][] filledImage = floodFill(image, sr, sc, newColor);

        System.out.println("Flood Filled Image:");
        for (int[] row : filledImage) {
            System.out.println(Arrays.toString(row));
        }


        // Check for Cycle in an Undirected Graph
        int[][] undirectedEdges = {
            {0, 1},
            {1, 2},
            {2, 0}, // This edge creates a cycle
            {3, 4}
        };
        boolean hasCycle = isCycle(5, undirectedEdges);
        System.out.println("Does the undirected graph have a cycle? " + hasCycle);


        // 0/1 Matrix Example
        int[][] mat = {
            {0, 0, 0},
            {0, 1, 0},
            {1, 1, 1}
        };
        int[][] updatedMatrix = updateMatrix(mat);
        System.out.println("Updated 0/1 Matrix:");
        for (int[] row : updatedMatrix) {
            System.out.println(Arrays.toString(row));
        }

        // Surrounded Regions Example
        char[][] board = {
            {'X', 'X', 'X', 'X'},
            {'X', 'O', 'O', 'X'},
            {'X', 'X', 'O', 'X'},
            {'X', 'O', 'X', 'X'}
        };
        solve(board);
        System.out.println("Board after solving Surrounded Regions:");
        for (char[] row : board) {
            System.out.println(Arrays.toString(row));
        }

        // Number of Enclaves Example
        int[][] enclavesGrid = {
            {0, 0, 0, 0},
            {1, 0, 1, 0},
            {0, 1, 1, 0},
            {0, 0, 0, 0}
        };
        int enclaves = numEnclaves(enclavesGrid);
        System.out.println("Number of Enclaves: " + enclaves);

        // Number of Distinct Islands
        int[][] distinctGrid = {
            {1,1,0,0,1},
            {1,0,0,0,1},
            {0,0,0,1,0},
            {1,1,0,1,0}
        };
        int distinctCount = countDistinctIslands(distinctGrid);
        System.out.println("Number of Distinct Islands: " + distinctCount);

        // Is Bipartite Graph Example
        int[][] bipartiteGraph = {
            {1, 3},
            {0, 2},
            {1, 3},
            {0, 2}
        };
        boolean isBipartiteResult = isBipartite(bipartiteGraph);
        System.out.println("Is the graph bipartite? " + isBipartiteResult);

        // Possible Bipartition Example
        int n = 4;
        int[][] dislikes = {
            {1, 2},
            {1, 3},
            {2, 4}
        };
        boolean canBipartition = possibleBipartition(n, dislikes);
        System.out.println("Is possible bipartition? " + canBipartition);

        // Detect Cycle in Directed Graph Example
        int V_directed = 4;
        int[][] directedEdges = {
            {0, 1},
            {1, 2},
            {2, 3},
            {3, 1} // This edge creates a cycle
        };
        boolean hasDirectedCycle = isCyclic(V_directed, directedEdges);
        System.out.println("Does the directed graph have a cycle? " + hasDirectedCycle);

        // Eventual Safe States Example
        int[][] safeGraph = {
            {1, 2},
            {2, 3},
            {5},
            {0},
            {5},
            {},
            {}
        };
        List<Integer> safeStates = eventualSafeNodes(safeGraph);
        System.out.println("Eventual Safe States: " + safeStates);

        // Topological Sort Example
        int V_topo = 6;
        int[][] topoEdges = {
            {5, 2},
            {5, 0},
            {4, 0},
            {4, 1},
            {2, 3},
            {3, 1}
        };
        ArrayList<Integer> topoOrder = topoSort(V_topo, topoEdges);
        System.out.println("Topological Sort Order: " + topoOrder);

        // Course Schedule I Example
        int numCourses1 = 2;
        int[][] prerequisites1 = {
            {1, 0}
        };
        boolean canFinish = courseSchedule_I(numCourses1, prerequisites1);
        System.out.println("Can finish all courses (Course Schedule I)? " + canFinish);

        // Course Schedule II Example
        int numCourses2 = 4;
        int[][] prerequisites2 = {
            {1, 0},
            {2, 0},
            {3, 1},
            {3, 2}
        };
        int[] order = courseSchedule_II(numCourses2, prerequisites2);
        System.out.println("Course order (Course Schedule II): " + Arrays.toString(order));

        // Alien Dictionary Example
        String[] alienWords = {"wrt", "wrf", "er", "ett", "rftt"};
        String alienOrder = alienDict(alienWords);
        System.out.println("Alien Dictionary Order: " + alienOrder);

        // Shortest Path in DAG Example
        int V_dag = 6;
        int E_dag = 7;
        int[][] dagEdges = {
            {0, 1, 2},
            {0, 4, 1},
            {1, 2, 3},
            {4, 2, 2},
            {4, 5, 4},
            {2, 3, 6},
            {5, 3, 1}
        };
        int[] shortestPaths = shortestPath_DAG(V_dag, E_dag, dagEdges);
        System.out.println("Shortest paths from node 0 in DAG: " + Arrays.toString(shortestPaths));

        // Shortest Path in Undirected Graph Example
        // Define edges as an array of pairs
        int[][] ugEdges = {
            {0, 1},
            {0, 2},
            {1, 3},
            {2, 3},
            {3, 4},
            {4, 5}
        };
        int ugV = 6;
        ArrayList<ArrayList<Integer>> ugAdj = new ArrayList<>();
        for (int i = 0; i < ugV; i++) {
            ugAdj.add(new ArrayList<>());
        }
        // Build adjacency list from edge list
        for (int[] edge : ugEdges) {
            ugAdj.get(edge[0]).add(edge[1]);
            ugAdj.get(edge[1]).add(edge[0]);
        }
        int ugSrc = 0;
        int[] ugShortest = shortestPath_UG(ugAdj, ugSrc);
        System.out.println("Shortest path from node " + ugSrc + " in UG: " + Arrays.toString(ugShortest));


        // Word Ladder I 
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
        int ladderLen = ladderLength(beginWord, endWord, wordList);
        System.out.println("Word Ladder Length: " + ladderLen);

        // Word Ladder II
        String beginWord2 = "hit";
        String endWord2 = "cog";
        List<String> wordList2 = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
        List<List<String>> ladders = findLadders(beginWord2, endWord2, wordList2);
        System.out.println("All shortest transformation sequences (Word Ladder II):");
        for (List<String> ladder : ladders) {
            System.out.println(ladder);
        }


    }
}
