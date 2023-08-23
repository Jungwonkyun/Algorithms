import java.util.*;

public class Solution {
    static String[] dirsStr = {"d" , "l", "r", "u"};
    static int[][] dirs = {{1, 0}, {0, -1}, {0, 1}, {-1, 0}};
    static StringBuilder answer;
    static String result;
    static int endRow, endCol, mapRow, mapCol;
    public static String solution(int n, int m, int x, int y, int r, int c, int k) {
        result = null;
        answer = new StringBuilder();
        mapRow = n;
        mapCol = m;
        endRow = r;
        endCol = c;
        // x, y : start
        // r, c : end

        // TC 31
        int distance = distance(x, y, endRow, endCol);
        if (distance > k || (k - distance) % 2 == 1) return "impossible";
        dfs(x, y, 0, k);

        return result != null ? result : "impossible";
    }

    static int distance(int x, int y, int r, int c) {
        return Math.abs(x - r) + Math.abs(y - c);
    }

    static void dfs(int row, int col, int depth, int limit) {
        if (result != null) return;
        if (depth + distance(row, col, endRow, endCol) > limit) return;
        if (limit == depth) {
            if (row == endRow && col == endCol) result = answer.toString();
            return;
        }
        for (int i = 0; i < dirs.length; i++) {
            int nRow = row + dirs[i][0];
            int nCol = col + dirs[i][1];
            if (nRow > 0 && nCol > 0 && nRow <= mapRow && nCol <= mapCol) {
                answer.append(dirsStr[i]);
                dfs(nRow, nCol, depth + 1, limit);
                answer.delete(depth, depth + 1);
            }
        }
    }
}
    




// class Node {
//     int x;
//     int y;
//     int cnt;
//     String str;

//     Node(int x, int y, int cnt, String str) {
//         this.x = x;
//         this.y = y;
//         this.cnt = cnt;
//         this.str = str;
//     }
// }

// class Solution {
//     static int[][] array;
//     static int[] dx = {-1, 1, 0, 0};
//     static int[] dy = {0, 0, -1, 1};
//     static int N;
//     static int M;
//     static ArrayList<String> strList;
//     static String[] directions = {"u", "d", "l", "r"};

//     public String solution(int n, int m, int x, int y, int r, int c, int k) {
//         N = n;
//         M = m;
//         array = new int[n][m];
//         strList = new ArrayList<>();

//         array[x - 1][y - 1] = 1;
//         array[r - 1][c - 1] = 2;

//         bfs(x - 1, y - 1, r - 1, c - 1, k);

//         Collections.sort(strList);

//         if (strList.isEmpty()) {
//             return "impossible";
//         }

//         return strList.get(0);
//     }

//     public void bfs(int a, int b, int r, int c, int k) {
//         Node start = new Node(a, b, 0, "");
//         Queue<Node> queue = new LinkedList<>();
//         queue.add(start);
        
//         // 3차원 visited 배열 생성
//         boolean[][][] visited = new boolean[N][M][k + 1];
//         visited[a][b][0] = true;

//         while (!queue.isEmpty()) {
//             Node now = queue.poll();
//             int x = now.x;
//             int y = now.y;
//             int cnt = now.cnt;

//             for (int i = 0; i < 4; i++) {
//                 int nx = x + dx[i];
//                 int ny = y + dy[i];

//                 if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny][cnt + 1]) {
//                     continue;
//                 }

//                 int newCnt = cnt + 1;

//                 if (nx == r && ny == c && newCnt == k) {
//                     strList.add(now.str + directions[i]);
//                 }

//                 if (newCnt > k) {
//                     continue; // 더 이상 탐색하지 않음
//                 }

//                 Node nxt = new Node(nx, ny, newCnt, now.str + directions[i]);
//                 queue.add(nxt);
//                 visited[nx][ny][newCnt] = true;
//             }
//         }
//     }
// }