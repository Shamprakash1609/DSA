import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) throws  Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<Integer>> adjLs = new ArrayList<>();

        for(int i = 0 ; i <= n ; i++){
            adjLs.add(new ArrayList<Integer>());
        }

        for(int i = 0 ; i < m ; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            adjLs.get(u).add(v);
            adjLs.get(v).add(u);
        }
        
        int vis[] = new int[n + 1];
        int parent[] = new int[n + 1];

        Queue<Integer> q = new LinkedList<>();

        vis[1] = 1;
        parent[1] = -1;

        q.offer(1);

        while(!q.isEmpty()){
            int node = q.poll();

            if(node == n) break;

            for(int it : adjLs.get(node)){
                if(vis[it] == 0){
                    vis[it] = 1;
                    parent[it] = node;
                    q.offer(it); 
                }
            }
        }

        if(vis[n] == 0){
            System.out.println("IMPOSSIBLE");
            return;
        }

        ArrayList<Integer> path = new ArrayList<>();

        int idx = n;

        while(idx != -1){
            path.add(idx);
            idx = parent[idx];
        }

        Collections.reverse(path);

        System.out.println(path.size()); // computers int route

        StringBuilder sb = new StringBuilder();

        for(int node : path){
           sb.append(node).append(" ");
        }

        System.out.println(sb.toString());
    }
}
