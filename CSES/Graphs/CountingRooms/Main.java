// package Graphs.CountingRooms;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[][] directions = {{-1,0}, {0,-1}, {0,1}, {1,0}};
    static int n , m;
    static char[][] grid;
    static int vis[][];

    private static void bfs(int r , int c){
        Queue<int[]> q = new LinkedList<>();

        q.offer(new int[]{r , c});

        while(!q.isEmpty()){
            int curr[] = q.poll();

            int row = curr[0];
            int col = curr[1];

            for(int dir[] : directions){
                int nRow = row + dir[0];
                int nCol = col + dir[1];

                if(nRow >= 0 && nCol >= 0 && nRow < n && nCol < m && grid[nRow][nCol] == '.' && vis[nRow][nCol] == 0){
                    vis[nRow][nCol] = 1;
                    q.offer(new int[]{nRow ,nCol});
                }
            }
        }
    }
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        grid = new char[n][m];

        for(int i = 0 ; i < n ; i++){
            String row = br.readLine();

            for(int j = 0 ; j < m ; j++){
                grid[i][j] = row.charAt(j);
            }
        }


        vis = new int[n][m];

        int rooms = 0;

        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < m ; j++){
                if(grid[i][j] == '.' && vis[i][j] == 0){
                    bfs(i , j);
                    rooms++;
                }
            }
        }

        System.out.println(rooms);

    }
}
