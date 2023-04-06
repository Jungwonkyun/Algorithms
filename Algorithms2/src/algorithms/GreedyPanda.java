package algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class GreedyPanda {
	static int N;
	static int[][] map;
	static int[][] dp;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int max = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		dp = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 2차원 배열 순회하면서 방문한 적이 없을 때만 dfs를 돌려준다
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				max = Math.max(max, dfs(i, j));
			}
		}

		System.out.println(max);

	}

	public static int dfs(int x, int y) {
		
		//memorization 이미 방문했으면 그때의 결과를 리턴해준다
		if (dp[x][y] != 0)
			return dp[x][y];

		// 맨 처음 1로 초기화
		dp[x][y] = 1;

		int nx;
		int ny;

		for (int i = 0; i < 4; i++) {
			nx = x + dx[i];
			ny = y + dy[i];

			// 만약 범위를 벗어나거나 이동할 대나무 양이 현재보다 작으면 continue
			if (nx < 0 || nx >= N || ny < 0 || ny >= N || map[x][y] >= map[nx][ny])
				continue;
			
			//현재 값과 미래 DFS를 돌린 값을 비교해서 더 큰 값으로 업데이트 
			dp[x][y] = Math.max(dp[x][y], dfs(nx, ny) + 1);
			
		}

		return dp[x][y];
	}

}
