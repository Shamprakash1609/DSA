import java.util.*;

public class DetectiveChu{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int R = 0 , C = 0;
        R = sc.nextInt();
        C = sc.nextInt();

        sc.nextLine();

        char[][] city = new char[R][C];
        for(int i = 0 ; i < R ; i++){
            String line = "";
            // read next non-empty line
            while (sc.hasNextLine()) {
                line = sc.nextLine().trim();
                if (!line.isEmpty()) break;
            }
            city[i] = line.replaceAll("\\s+" , "").toCharArray();
        }

        String pathSeq = "";
        while(sc.hasNextLine()){
            pathSeq = sc.nextLine().trim();
            if(!pathSeq.isEmpty()) break;
        }
        sc.close();

        int detectPikaPi = solveDetective(R , C , city , pathSeq);
        if(detectPikaPi == 0) System.out.println("Impossible");
        else System.out.println(detectPikaPi);
    }

    public static int solveDetective(int R , int C , char[][] city , String path){
        int len = path.length();

        int[][] directions = {{-1,0} , {0,-1} , {0,1} , {1,0}};

        int[] leftT = {1,3,0,2};
        int[] rightT = {2,0,3,1};

        // visited: r x c x dir(4) x step(len+1)
        boolean[][][][] vis = new boolean[R][C][4][len + 1];
        boolean[][] endP = new boolean[R][C];

        Queue<int[]> q = new LinkedList<>();

        for(int r = 0 ;  r < R ; r++){
            for(int c = 0 ;  c < C ; c++){
                if(city[r][c] == '#') continue;
                for (int d = 0; d < 4; d++) {
                    vis[r][c][d][0] = true;
                    q.add(new int[]{r, c, d, 0});
                }
            }
        }


        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];
            int dir = cur[2];
            int step = cur[3];

            if (step == len) {
                endP[r][c] = true;
                continue;
            }

            char cmd = path.charAt(step);

            if (cmd == 'L') {
                int nd = leftT[dir];
                int ns = step + 1;
                if (!vis[r][c][nd][ns]) {
                    vis[r][c][nd][ns] = true;
                    q.add(new int[]{r, c, nd, ns});
                }

            } else if (cmd == 'R') {
                int nd = rightT[dir];
                int ns = step + 1;
                if (!vis[r][c][nd][ns]) {
                    vis[r][c][nd][ns] = true;
                    q.add(new int[]{r, c, nd, ns});
                }

            } else if (cmd == 'S') {
                int nr = r + directions[dir][0];
                int nc = c + directions[dir][1];
                int ns = step + 1;
                if (nr >= 0 && nr < R && nc >= 0 && nc < C && city[nr][nc] == '.') {
                    if (!vis[nr][nc][dir][ns]) {
                        vis[nr][nc][dir][ns] = true;
                        q.add(new int[]{nr, nc, dir, ns});
                    }
                }
            }

        }

        int count = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (endP[i][j]) count++;
            }
        }

        return count;

    }

}

