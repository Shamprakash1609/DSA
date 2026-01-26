import java.io.*;
import java.util.*;

public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());


        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        char grid[][] = new char[n][m];
        int vis[][] = new int[n][m];
        
       

        int[] start = new int[2];
        int[] end = new int[2];

        for(int i = 0 ; i < n ; i++){
            String row = br.readLine();
            for(int j = 0 ; j < m ; j++){
                grid[i][j] = row.charAt(j);
                if(grid[i][j] == 'A'){
                    start[0] = i;
                    start[1] = j;
                }
                else if(grid[i][j] == 'B'){
                    end[0] = i;
                    end[1] = j;
                }
            }
        }

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{start[0], start[1]});
        vis[start[0]][start[1]] = 1;

        int directions[][] = {
            {1 , 0 , 1}, // Down
            {0 , 1 , 2}, // Right
            {-1 , 0 , 3}, // Up
            {0 , -1 , 4} // Left
        };

        int route[][] = new int[n][m];

        boolean found = false;

        while(!q.isEmpty()){
            int curr[] = q.poll();

            int row = curr[0];
            int col = curr[1];

            if (row == end[0] && col == end[1]) {
                found = true;
                break;
            }

            for(int dir[] : directions){
                int r = row + dir[0];
                int c = col + dir[1];
                int d = dir[2];

                if(r >= 0 && c >= 0 && r < n && c < m && (grid[r][c] == '.' || grid[r][c] == 'B') && vis[r][c] == 0){
                    vis[r][c] = 1;
                    route[r][c] = d;
                    q.offer(new int[]{r , c});
                }
            }
        }

        if (!found) {
            System.out.println("NO");
            return;
        }

        StringBuilder sb = new StringBuilder();

        int r = end[0] , c = end[1];

        while(!(r == start[0] && c == start[1])){
            if(route[r][c] == 1){
                sb.append('D');
                r--;
            }
            else if(route[r][c] == 2){
                sb.append('R');
                c--;
            }
            else if(route[r][c] == 3){
                sb.append('U');
                r++;
            }
            else{
                sb.append('L');
                c++;
            }
        }

        System.out.println("YES");
        System.out.println(sb.length());
        System.out.println(sb.reverse().toString());

        

    }
}