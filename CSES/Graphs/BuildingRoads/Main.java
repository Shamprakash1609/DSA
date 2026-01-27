import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
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
        int cnt = 0;
        int prevCity = -1;

        ArrayList<int[]> roads = new ArrayList<>();

        for(int i = 1 ; i <= n ; i++){
            if(vis[i] == 0){
                if(prevCity != -1){
                    roads.add(new int[]{prevCity , i});
                }
                prevCity = i;
                cnt++;
                dfs(adjLs , vis , i);
            }
        }

        int roadsReq = cnt - 1;
        System.out.println(roadsReq);

        for(int[] rd : roads){
            System.out.println(rd[0] + " " + rd[1]);
        }
    }

    private static void dfs(ArrayList<ArrayList<Integer>> adjLs , int vis[] , int node){
        vis[node] = 1;

        for(int it : adjLs.get(node)){
            if(vis[it] == 0){
                dfs(adjLs , vis , it);
            }
        }
    }
}
