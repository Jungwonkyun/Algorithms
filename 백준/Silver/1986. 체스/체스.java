import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int KnightMoveX[] = {1,2,2,1,-1,-2,-2,-1};
    static int KnightMoveY[] = {-2,-1,1,2,2,1,-1,-2};
    static int QueenMoveX[] = {0,1,1,1,0,-1,-1,-1};
    static int QueenMoveY[] = {-1,-1,0,1,1,1,0,-1};
    static int N,M;
    static int Queen,Knight,Pawn;
    static Point[] queen,knight,pawn;
    static boolean visit[][];
    static int arr[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        첫째 줄에는 체스 판의 크기 n과 m이 주어진다. (1<=n, m<=1000) 그리고 둘째 줄에는 Queen의 개수와 그 개수만큼의 Queen의 위치가 입력된다. 그리고 마찬가지로 셋째 줄에는
//        Knight의 개수와 위치, 넷째 줄에는 Pawn의 개수와 위치가 입력된다. (즉 둘째 줄~ 넷째 줄은  k,r1,c1,r2,c2,...,rk,ck 이 빈칸을 사이에 두고 주어진다는 것이다.
//        4 4
//        2 1 4 2 4
//        1 1 2
//        1 2 3
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visit = new boolean[N+1][M+1];
        arr = new int[N+1][M+1];
        st = new StringTokenizer(br.readLine());
        Queen = Integer.parseInt(st.nextToken());
        queen = new Point[Queen];
        for(int i=0; i<Queen; i++) {
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            queen[i] = new Point(x,y);
            visit[y][x] = true;
            arr[y][x] = 1;
        }
        st = new StringTokenizer(br.readLine());
        Knight = Integer.parseInt(st.nextToken());
        knight = new Point[Knight];
        for(int i=0; i<Knight; i++) {
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            knight[i] = new Point(x,y);
            visit[y][x] = true;
            arr[y][x] = 1;
        }
        st = new StringTokenizer(br.readLine());
        Pawn = Integer.parseInt(st.nextToken());
        pawn = new Point[Pawn];
        for(int i=0; i<Pawn; i++) {
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            pawn[i] = new Point(x,y);
            visit[y][x] = true;
            arr[y][x] = 1;
        }

        for(int i=0; i<Knight; i++) {
            for(int d=0; d<8; d++) {
                int newX = knight[i].x + KnightMoveX[d];
                int newY = knight[i].y + KnightMoveY[d];
                if(0<newX && newX<=M && 0<newY && newY<=N && !visit[newY][newX])
                    visit[newY][newX] = true;
            }
        }
        for(int i=0; i<Queen; i++) {
            for(int d=0; d<8; d++) {
                int newX = queen[i].x + QueenMoveX[d];
                int newY = queen[i].y + QueenMoveY[d];
                while(true) {
                    if(0<newX && newX<=M && 0<newY && newY<=N && arr[newY][newX] != 1) {
                        visit[newY][newX] = true;
                        newX += QueenMoveX[d];
                        newY += QueenMoveY[d];
                    }else
                        break;
                }
            }
        }
        int result = 0;
        for(int i=1; i<=N; i++)
            for(int j=1; j<=M; j++)
                if(!visit[i][j])
                    result++;

        System.out.println(result);
    }
}