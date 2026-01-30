import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        char[][] grid = new char[n][m];
    
        int me[] = new int[2];
        

        // 1) Multi-source BFS for monsters
        Queue<int[]> q = new LinkedList<>();

        int monsterVis[][] = new int[n][m];

        int monsterTime[][] = new int[n][m];

        for(int row[] : monsterTime){
            Arrays.fill(row , (int)(1e9));
        }

        for(int i = 0 ; i < n ; i++){
            String row = br.readLine();
            for(int j = 0 ; j < m ; j++){
                char ch = row.charAt(j);
                grid[i][j] = ch;
                if(ch == 'A'){
                    me[0] = i;
                    me[1] = j;
                }
                if(ch == 'M'){
                    q.offer(new int[]{i , j , 0});
                    monsterVis[i][j] = 1;
                    monsterTime[i][j] = 0;
                }
            }
        }

        // Instant Escape
        if (me[0] == 0 || me[0] == n - 1 || me[1] == 0 || me[1] == m - 1) {
            System.out.println("YES");
            System.out.println(0);
            System.out.println();
            return;
        }


        int directions[][] = {
            {0 , -1 , 1}, // left
            {-1 , 0 , 2}, // up
            {0 , 1 , 3}, // right
            {1 , 0 , 4} // down
        };

        
        while(!q.isEmpty()){
            int curr[] = q.poll();

            int row = curr[0];
            int col = curr[1];
            int tm = curr[2];

            for(int dir[] : directions){
                int r = row + dir[0];
                int c = col + dir[1];

                if(r >= 0 && c >= 0 && r < n && c < m && monsterVis[r][c] == 0 && grid[r][c] != '#' ){
                    monsterVis[r][c] = 1;
                    monsterTime[r][c] = tm + 1;
                    q.offer(new int[]{r , c , tm + 1});
                }
            }
        }

        // BFS for My Escape
        q.clear();

        int vis[][] = new int[n][m];
        int path[][] = new int[n][m];

        q.offer(new int[]{me[0] , me[1] , 0});
        vis[me[0]][me[1]] = 1;


        int end[] = new int[2];
        boolean escaped = false;

        while(!q.isEmpty()){
            int curr[] = q.poll();

            int row = curr[0];
            int col = curr[1];
            int tm = curr[2];

            for(int dir[] : directions){
                int r = row + dir[0];
                int c = col + dir[1];
                int d = dir[2];

                if(r >= 0 && c >= 0 && r < n && c < m && vis[r][c] == 0 && grid[r][c] == '.' && ((tm + 1 ) < monsterTime[r][c])){
                    vis[r][c] = 1;
                    path[r][c] = d;

                    if (r == 0 || r == n-1 || c == 0 || c == m-1) {
                        end[0] = r;
                        end[1] = c;
                        escaped = true;
                        break;
                    }

                    q.offer(new int[]{r , c , tm + 1});
                }
            }
        }

        // Escape Check
        if(escaped == false){
            System.out.println("NO");
            return;
        }

        // Edge case Start == End
        if(end[0] == me[0] && end[1] == me[1]){
            System.out.println("YES");
            System.out.println(0);
            System.out.println();
            return;
        }
        
        
        // Path Reconstruction
        StringBuilder sb = new StringBuilder();
        int r = end[0], c = end[1];

        while (!(r == me[0] && c == me[1])) {
            int d = path[r][c];

            if (d == 1) {        // came from left → parent at (r, c+1)
                sb.append('L');
                c++;
            }
            else if (d == 2) {   // came from up → parent at (r+1, c)
                sb.append('U');
                r++;
            }
            else if (d == 3) {   // came from right → parent at (r, c-1)
                sb.append('R');
                c--;
            }
            else if (d == 4) {   // came from down → parent at (r-1, c)
                sb.append('D');
                r--;
            }
        }
        System.out.println("YES");
        System.out.println(sb.length());
        System.out.println(sb.reverse());
    }
}
