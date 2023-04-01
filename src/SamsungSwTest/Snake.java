package SamsungSwTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

class SnakeInfo {

	int x;
	int y;
	boolean head;

	public SnakeInfo(int x, int y, boolean head) {
		this.x = x;
		this.y = y;
		this.head = head;
	}
}


class Visit{
	
	int x;
	int y;
	
	public Visit(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Snake {

	static int N;
	static int ap;
	static int mv;
	static int SnakeSize = 1;
	static int[][] map;
	static String[] timeLine = new String[10001];
	static Deque<SnakeInfo> snakeQueue = new ArrayDeque<>();
	static Deque<Visit> visitQueue = new ArrayDeque<>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		ap = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		Arrays.fill(timeLine,"A");
		
		// 사과 좌표 정리
		for (int i = 0; i < ap; i++) {
			String[] in = br.readLine().split(" ");
			int x = Integer.parseInt(in[0]);
			int y = Integer.parseInt(in[1]);
			map[x - 1][y - 1] = 1;
		}

		mv = Integer.parseInt(br.readLine());

		// 뱀의 움직임 타임라인 정리
		for (int i = 0; i < mv; i++) {
			String[] in = br.readLine().split(" ");
			int time = Integer.parseInt(in[0]);
			String dir = in[1];
			timeLine[time] = dir;
		}

		SnakeInfo sk = new SnakeInfo(0, 0, true); // 무조건 얘가 머리가 된다
		snakeQueue.add(sk);
		gameStart();

	}

	public static void gameStart() {

		int cnt = 0;
		int dx[] = { 0, 1, 0, -1 };
		int dy[] = { 1, 0, -1, 0 };
		int dir = 0;
		int x = 0;
		int y = 0;
		int nx = 0;
		int ny = 0;

		boolean visited[][] = new boolean[N][N];
		visited[0][0] = true;
		
		//initial visit queue
		Visit v = new Visit(0,0);
		visitQueue.add(v);

		while (true) {
			
			if(timeLine[cnt].equals("D"))dir = (dir+1)%4;
			else if(timeLine[cnt].equals("L"))dir = (dir+3)%4;
			
			cnt++;
			
			SnakeInfo nowSnake = snakeQueue.removeFirst();

			// 움직이기 전 snake좌표들
			x = nowSnake.x;
			y = nowSnake.y;

			// 움직이고 나서 snake좌표들
			nx = x + dx[dir];
			ny = y + dy[dir];

			if(nx<0||nx>=N||ny<0||ny>=N||visited[nx][ny])break;  //종료조건
			
			SnakeInfo newSnake = new SnakeInfo(nx, ny, true);
			Visit vs = new Visit(nx,ny);
			
			// 머리를 이동 방향으로 한 칸 늘려주고 dq에 넣는다
			snakeQueue.addFirst(newSnake);
			
			//visite queue에 추가
			visitQueue.add(vs);
			visited[nx][ny] = true;
			
			
			// 만약 다음 칸에 사과가 있다면 사이즈 증가 visited는 그대로
			if (map[nx][ny] == 1) {
				//사과는 먹으면 사라진
				map[nx][ny] = 0;
				// 뱀 길이도 1추가
				SnakeSize++;
			}

			// 만약 다음 칸에 사과가 없다면 꼬리 부분 visited삭제 visited 배열에서도 삭제해준다 
			else {
				Visit deleteV = visitQueue.removeFirst();
				visited[deleteV.x][deleteV.y] = false;
			}
			
		}
		
		System.out.println(cnt);

	}

}
