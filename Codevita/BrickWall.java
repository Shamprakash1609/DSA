import java.util.*;

public class BrickWall {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = Integer.parseInt(sc.nextLine().trim()); // wall size

        char[][] wall = new char[N][N];
        for (int i = 0; i < N; i++) {
            String rowLine = sc.nextLine().replaceAll("\\s+", "");
            expandBrickRow(rowLine, wall[i], N);
        }

        int sr = -1, sc1 = -1, dr = -1, dc = -1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (wall[i][j] == 'S') { sr = i; sc1 = j; }
                if (wall[i][j] == 'D') { dr = i; dc = j; }
            }
        }

        if (sr == -1 || dr == -1) {
            System.out.println(-1); // no source or destination
            return;
        }

        // Optional debug:
        // System.out.println("Source: (" + sr + "," + sc1 + ") -> Destination: (" + dr + "," + dc + ")");
        int minBricks = minGreenBricksToBreak(wall, sr, sc1, dr, dc);
        System.out.println(minBricks);
    }

    // Parse RLE-style row into full wall row
    private static void expandBrickRow(String rleRow, char[] wallRow, int N) {
        int posInRLE = 0, posInRow = 0;

        while (posInRLE < rleRow.length()) {
            int repeat = 0;
            while (Character.isDigit(rleRow.charAt(posInRLE))) {
                repeat = repeat * 10 + (rleRow.charAt(posInRLE) - '0');
                posInRLE++;
            }
            char brickType = rleRow.charAt(posInRLE++);
            for (int k = 0; k < repeat; k++) {
                wallRow[posInRow++] = brickType;
            }
        }
    }

    // 0-1 BFS to find minimum number of green bricks to break
    private static int minGreenBricksToBreak(char[][] grid, int sr, int sc, int dr, int dc) {
        int N = grid.length;
        int[][] dist = new int[N][N];
        for (int[] row : dist) Arrays.fill(row, (int)1e9);

        Deque<int[]> dq = new ArrayDeque<>();
        dist[sr][sc] = 0;
        dq.addFirst(new int[]{sr, sc});

        int[][] directions = {{-1,0}, {0,-1}, {0,1}, {1,0}};

        while (!dq.isEmpty()) {
            int[] curr = dq.removeFirst();
            int r = curr[0], c = curr[1];

            if (r == dr && c == dc) return dist[r][c];

            for (int[] dir : directions) {
                int nr = r + dir[0], nc = c + dir[1];
                if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;

                char cell = grid[nr][nc];
                if (cell == 'R') continue;

                int newCost = dist[r][c] + (cell == 'G' ? 1 : 0);
                if (newCost < dist[nr][nc]) {
                    dist[nr][nc] = newCost;
                    if (cell == 'G') dq.addLast(new int[]{nr, nc});
                    else dq.addFirst(new int[]{nr, nc});
                }
            }
        }

        return -1;
    }
}
