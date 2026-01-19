import java.util.*;

public class ArrangeTrack {

    static int N, M;
    static int sheetGridSize;
    static int minPath = Integer.MAX_VALUE;

    static class Sheet {
        char[][] grid;

        Sheet(char[][] grid) {
            this.grid = new char[M][M];
            for (int i = 0; i < M; i++)
                this.grid[i] = Arrays.copyOf(grid[i], M);
        }

        Sheet rotate() {
            char[][] newGrid = new char[M][M];
            for (int i = 0; i < M; i++)
                for (int j = 0; j < M; j++)
                    newGrid[i][j] = this.grid[M - 1 - j][i];
            return new Sheet(newGrid);
        }
    }


    static void backtrack(int r, int c, Sheet[][] arrangement, boolean[] used, List<Sheet> intermediates, Sheet dSheet) {
        if (c == sheetGridSize) { r++; c = 0; }
        if (r == sheetGridSize) {
            char[][] finalGrid = buildFinalGrid(arrangement);
            int[] start = findChar('S', finalGrid);
            int[] dest = findChar('D', finalGrid);
            if (start != null && dest != null) {
                int distance = bfs(start[0], start[1], dest[0], dest[1], finalGrid);
                if (distance != -1) minPath = Math.min(minPath, distance);
            }
            return;
        }

        // D sheet
        if (r == sheetGridSize - 1 && c == sheetGridSize - 1) {
            Sheet rotatedDSheet = dSheet;
            for (int i = 0; i < 4; i++) {
                if (isValidPlacement(rotatedDSheet, r, c, arrangement)) {
                    arrangement[r][c] = rotatedDSheet;
                    backtrack(r, c + 1, arrangement, used, intermediates, dSheet);
                }
                rotatedDSheet = rotatedDSheet.rotate();
            }
        } else { 
            for (int i = 0; i < intermediates.size(); i++) {
                if (!used[i]) {
                    used[i] = true;
                    Sheet original = intermediates.get(i);
                    Sheet current = original;
                    for (int j = 0; j < 4; j++) {
                        if (isValidPlacement(current, r, c, arrangement)) {
                            arrangement[r][c] = current;
                            backtrack(r, c + 1, arrangement, used, intermediates, dSheet);
                        }
                        current = current.rotate();
                    }
                    used[i] = false;
                }
            }
        }
    }

 
    static boolean isValidPlacement(Sheet sheet, int r, int c, Sheet[][] arrangement) {
        // Top 
        if (r > 0) {
            Sheet topSheet = arrangement[r - 1][c];
            for (int i = 0; i < M; i++) {
                char topChar = topSheet.grid[M - 1][i];
                char currChar = sheet.grid[0][i];
                boolean topIsTrack = (topChar == 'T' || topChar == 'S' || topChar == 'D');
                boolean currIsTrack = (currChar == 'T' || currChar == 'S' || currChar == 'D');
                if (topIsTrack != currIsTrack) return false;
            }
        }
        // Left 
        if (c > 0) {
            Sheet leftSheet = arrangement[r][c - 1];
            for (int i = 0; i < M; i++) {
                char leftChar = leftSheet.grid[i][M - 1];
                char currChar = sheet.grid[i][0];
                boolean leftIsTrack = (leftChar == 'T' || leftChar == 'S' || leftChar == 'D');
                boolean currIsTrack = (currChar == 'T' || currChar == 'S' || currChar == 'D');
                if (leftIsTrack != currIsTrack) return false;
            }
        }
        return true;
    }

    static int[] findChar(char target, char[][] grid) {
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                if (grid[i][j] == target) return new int[]{i, j};
        return null;
    }

    static int bfs(int sr, int sc, int dr, int dc, char[][] grid) {
        boolean[][] visited = new boolean[N][N];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{sr, sc, 1});
        visited[sr][sc] = true;

        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int r = curr[0], c = curr[1], dist = curr[2];
            if (r == dr && c == dc) return dist;
            for (int[] d : dirs) {
                int nr = r + d[0], nc = c + d[1];
                if (nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc] &&
                        (grid[nr][nc] == 'T' || grid[nr][nc] == 'D')) {
                    visited[nr][nc] = true;
                    q.add(new int[]{nr, nc, dist + 1});
                }
            }
        }
        return -1;
    }

    static char[][] buildFinalGrid(Sheet[][] arrangement) {
        char[][] finalGrid = new char[N][N];
        for (int i = 0; i < sheetGridSize; i++)
            for (int j = 0; j < sheetGridSize; j++)
                for (int r = 0; r < M; r++)
                    for (int c = 0; c < M; c++)
                        finalGrid[i * M + r][j * M + c] = arrangement[i][j].grid[r][c];
        return finalGrid;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        sc.nextLine();

        char[][] initialGrid = new char[N][N];
        for (int i = 0; i < N; i++) {
            String line = sc.nextLine().trim();
            for (int j = 0; j < N; j++)
                initialGrid[i][j] = line.charAt(j);
        }
        sc.close();

        sheetGridSize = N / M;
        List<Sheet> intermediateSheets = new ArrayList<>();
        Sheet sSheet = null, dSheet = null;

        // Extract M x M sheets
        for (int i = 0; i < sheetGridSize; i++) {
            for (int j = 0; j < sheetGridSize; j++) {
                char[][] sheetData = new char[M][M];
                boolean isS = false, isD = false;
                for (int r = 0; r < M; r++) {
                    for (int c = 0; c < M; c++) {
                        sheetData[r][c] = initialGrid[i * M + r][j * M + c];
                        if (sheetData[r][c] == 'S') isS = true;
                        if (sheetData[r][c] == 'D') isD = true;
                    }
                }
                Sheet currentSheet = new Sheet(sheetData);
                if (isS) sSheet = currentSheet;
                else if (isD) dSheet = currentSheet;
                else intermediateSheets.add(currentSheet);
            }
        }

        Sheet[][] arrangement = new Sheet[sheetGridSize][sheetGridSize];
        boolean[] used = new boolean[intermediateSheets.size()];

        // Try all 4 orientations for S sheet at (0,0)
        Sheet rotatedSSheet = sSheet;
        for (int i = 0; i < 4; i++) {
            arrangement[0][0] = rotatedSSheet;
            backtrack(0, 1, arrangement, used, intermediateSheets, dSheet);
            rotatedSSheet = rotatedSSheet.rotate();
        }

        System.out.println(minPath == Integer.MAX_VALUE ? -1 : minPath - 1);
    }
}
