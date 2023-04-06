package algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

class roadInfo {
	int x;
	int y;
	int value;

	roadInfo(int x, int y, int value) {
		this.x = x;
		this.y = y;
		this.value = value;
	}
}

public class BattleRoad {
	static String[] in;
	static int N;
	static int[][] Road;
	static int[][] shortest;
	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { -1, 1, 0, 0 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine().trim());

		for (int t = 1; t < TC + 1; t++) {
			N = Integer.parseInt(br.readLine().trim());
			Road = new int[N][N];
			shortest = new int[N][N];
			for (int i = 0; i < N; i++) {
				String[] in = br.readLine().trim().split("");
				for (int j = 0; j < N; j++) {
					Road[i][j] = Integer.parseInt(in[j]);
				}
			}

			// 제일 처음 경로는 Max 값으로 잡는다
			for (int i = 0; i < N; i++) {
				Arrays.fill(shortest[i], Integer.MAX_VALUE);
			}

			Dijkstra();
			System.out.println("#"+t+" "+shortest[N-1][N-1]);
		}
	}

	public static void Dijkstra() {

		Queue<roadInfo> pq = new PriorityQueue<>(Comparator.comparingInt(now -> now.value));
		roadInfo start = new roadInfo(0, 0, 0);
		pq.add(start);
		shortest[0][0] = 0;

		while (!pq.isEmpty()) {
			int size = pq.size();
			roadInfo nowRoad = pq.poll();
			int x = nowRoad.x;
			int y = nowRoad.y;
			int nowValue = nowRoad.value;
			
			if(shortest[x][y] < nowValue)continue;
			
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				// 경로 벗어나는 경우
				if (nx < 0 || nx >= N || ny < 0 || ny >= N)
					continue;
				// 업데이트 할 길의 가중치가 원래 가중치 보다 높을 경우 업데이트할 필요가 없음
				if (nowValue + Road[nx][ny] >= shortest[nx][ny])
					continue;

				int nextValue = nowValue + Road[nx][ny];

				// 최소 경로 업데이트
				shortest[nx][ny] = nextValue;
				roadInfo newRoad = new roadInfo(nx, ny, nextValue);
				pq.add(newRoad);

			}

		}

	}

}
