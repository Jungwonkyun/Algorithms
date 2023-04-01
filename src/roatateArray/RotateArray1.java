package roatateArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class RotateArray1 {
	static String[] in;
	static int[][] Array;
	static int[][] Copy;
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		in = br.readLine().split(" ");
		int N = Integer.parseInt(in[0]);
		int M = Integer.parseInt(in[1]);
		int R = Integer.parseInt(in[2]);

		Array = new int[N][M];
		Copy = new int[N][M];

		for (int i = 0; i < N; i++) {
			in = br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				Array[i][j] = Integer.parseInt(in[j]);
			}
		}

		int group = Math.min(M, N) / 2;
		for (int r = 0; r < R; r++) {
			for (int g = 1; g < group + 1; g++) {

				int nx = g - 1;
				int ny = g - 1;
				int dir = 0; // start with south

				while (true) {
					nx = nx + dx[dir];
					ny = ny + dy[dir];

					Copy[nx][ny] = Array[nx - dx[dir]][ny - dy[dir]];

					// 범위 벗어날 때 방향전환 해주기
					if ((nx == N - g && ny == g - 1) || (nx == N - g && ny == M - g) || (nx == g - 1 && ny == M - g))
						dir = (dir + 1) % 4;

					if (nx == g - 1 && ny == g - 1)
						break;
				}

			}

			for (int i = 0; i < N; i++) {
				Array[i] = Arrays.copyOf(Copy[i], M);
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sb.append(Copy[i][j] + " ");
			}
			sb.append('\n');
		}

		System.out.println(sb);

	}
}
