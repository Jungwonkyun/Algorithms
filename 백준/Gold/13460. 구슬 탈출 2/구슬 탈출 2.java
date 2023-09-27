import java.io.*;
import java.util.*;

class State {
    int rx, ry, bx, by, depth;
    
    public State(int rx, int ry, int bx, int by, int depth) {
        this.rx = rx;
        this.ry = ry;
        this.bx = bx;
        this.by = by;
        this.depth = depth;
    }
}

public class Main {
    static int N, M;
    static char[][] board;
    static int[] dx = {0, 0, 1, -1}; // 상하좌우
    static int[] dy = {1, -1, 0, 0};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        int rx = 0, ry = 0, bx = 0, by = 0;
        
        // 맵 정보 받아서 기록
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            board[i] = line.toCharArray();
            if (line.contains("R")) {
                rx = i;
                ry = line.indexOf("R");
            }
            if (line.contains("B")) {
                bx = i;
                by = line.indexOf("B");
            }
        }
        
        int answer = bfs(rx, ry, bx, by);
        System.out.println(answer);
        br.close();
    }
    
    public static int bfs(int rx, int ry, int bx, int by) {
        // BFS를 돌릴 큐
        Queue<State> queue = new LinkedList<>();
        // 빨간 구슬 & 파란 구슬의 위치 방문 여부 저장
        boolean[][][][] visited = new boolean[N][M][N][M];
        
        queue.offer(new State(rx, ry, bx, by, 0));
        visited[rx][ry][bx][by] = true;
        
        while (!queue.isEmpty()) {
            State current = queue.poll();
            int crx = current.rx, cry = current.ry, cbx = current.bx, cby = current.by, depth = current.depth;
            
            if (depth > 9) {
                break;
            }
            
            for (int i = 0; i < 4; i++) {
                int nrx = crx, nry = cry, nbx = cbx, nby = cby;
                
                // 빨간 구슬 이동
                while (board[nrx + dx[i]][nry + dy[i]] != '#' && board[nrx][nry] != 'O') {
                    nrx += dx[i];
                    nry += dy[i];
                }
                
                // 파란 구슬 이동
                while (board[nbx + dx[i]][nby + dy[i]] != '#' && board[nbx][nby] != 'O') {
                    nbx += dx[i];
                    nby += dy[i];
                }
                
                // 파란 구슬이 구멍에 빠지면 실패
                if (board[nbx][nby] == 'O') continue;
                
                // 빨간 구슬만 구멍에 빠지면 성공
                if (board[nrx][nry] == 'O') return depth + 1;
                
                // 빨간 구슬과 파란 구슬이 같은 칸에 위치하면 위치 조정
                if (nrx == nbx && nry == nby) {
                    int rDist = Math.abs(nrx - crx) + Math.abs(nry - cry);
                    int bDist = Math.abs(nbx - cbx) + Math.abs(nby - cby);
                    
                    if (rDist > bDist) {
                        nrx -= dx[i];
                        nry -= dy[i];
                    } else {
                        nbx -= dx[i];
                        nby -= dy[i];
                    }
                }
                
                // 방문한 적이 없다면 queue에 넣고 다시 돌리기
                if (!visited[nrx][nry][nbx][nby]) {
                    visited[nrx][nry][nbx][nby] = true;
                    queue.offer(new State(nrx, nry, nbx, nby, depth + 1));
                }
            }
        }
        return -1; // 10번 이상 시도해도 못 풀 경우
    }
}