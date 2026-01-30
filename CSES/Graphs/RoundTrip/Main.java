import java.io.*;
import java.util.*;


public class Main {

    static int cycleStart = -1, cycleEnd = -1;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<Integer>> adjLs = new ArrayList<>();

        for(int i = 0; i <= n ; i++){
            adjLs.add(new ArrayList<>());
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

        for(int i = 1 ; i <= n ; i++){
            if(vis[i]== 0){
                if(dfsCycle(adjLs , vis , parent , -1 , i) == true){
                    break;
                }
            }
        }

        if(cycleStart == -1){
            System.out.println("IMPOSSIBLE");
            return;
        }

        // System.out.println("Cycle Start : "+ cycleStart + " Cycle End : " + cycleEnd);

        ArrayList<Integer> path = new ArrayList<>();
        path.add(cycleStart);

        int idx = cycleEnd;

        while(idx != cycleStart){
            path.add(idx);
            idx = parent[idx];
        }

        path.add(cycleStart);

        System.out.println(path.size());
        
        Collections.reverse(path);

        StringBuilder sb = new StringBuilder();

        for(int city : path){
            sb.append(city).append(' ');
        }

        System.out.println(sb.toString());
    }

    private static boolean dfsCycle(ArrayList<ArrayList<Integer>> adjLs , int vis[] , int parent[] , int par , int node){
       vis[node] = 1;
       parent[node] = par;

        for(int it : adjLs.get(node)){
            if(vis[it] == 0){
                if(dfsCycle(adjLs , vis , parent, node , it) == true){
                    return true;
                }
            }
            else if(it != par){
                cycleStart = it;
                cycleEnd = node;
                return true;
            }
        }

        return false;
    }
}
 