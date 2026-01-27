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
            adjLs.add(new ArrayList<>());
        }

        for(int i = 0 ; i < m ; i++){
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            adjLs.get(u).add(v);
            adjLs.get(v).add(u);
        }

        int color[] = new int[n + 1];
        Arrays.fill(color , -1);

        for(int i = 1 ; i <= n ; i++){
            if(color[i] == -1){
                if(dfs(adjLs , color , 0 , i) == false){
                    System.out.println("IMPOSSIBLE");
                    return;
                }
            }
        }


        StringBuilder team = new StringBuilder();

        for(int i = 1 ;  i <= n ; i++){
            if(color[i] == 0){
                team.append('1').append(" ");
            }
            else{
                team.append('2').append(" ");
            }
        }

        System.out.println(team.toString());

    }

    private static boolean dfs(ArrayList<ArrayList<Integer>> adjLs , int[] color , int col , int node){
        color[node] = col;  

        for(int it : adjLs.get(node)){
            if(color[it] == -1){
                if(dfs(adjLs , color , 1 - col , it) == false) return false;
            }
            else if(color[it] == color[node]) return false;
        }

        return true;
    }
}
