package algorithms;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class SpinArray {
	static class RCS {
		int r;
		int c;
		int s;

		public RCS(int r, int c, int s) {
			super();
			this.r = r;
			this.c = c;
			this.s = s;
		}
	}

	static int N, M, K, min;
	static RCS[] rcs;
	static int[][] A, temp;

	static int[][] dir4 = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		A = new int[N][M];
		temp = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int j = 0; j < M; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		rcs = new RCS[K];

		min = Integer.MAX_VALUE;
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine().trim());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			rcs[i] = new RCS(r, c, s);
		}
		nPr(0, 0, new int[K]);

		System.out.println(min);
	}

	public static void nPr(int cnt, int flag, int[] list) {
		if (cnt == K) {

			for (int i = 0; i < N; i++) {
				System.arraycopy(A[i], 0, temp[i], 0, A[i].length);
			}
	
			for (int i : list) {
				rotate(rcs[i].r, rcs[i].c, rcs[i].s);
			}

			for (int i = 0; i < N; i++) {
				int sum = 0;
				for (int j = 0; j < M; j++) {
					sum += temp[i][j];
				}
	
				min = Math.min(min, sum);
			}
			return;
		}
		for (int i = 0; i < K; i++) {
			if ((flag & 1 << i) != 0) {
				continue;
			}
			list[cnt] = i;
			nPr(cnt + 1, (flag | 1 << i), list);
		}

	}

	public static void rotate(int r, int c, int s) {
		int[][] temp2 = new int[N][M];

		for (int i = 0; i < N; i++) {
			System.arraycopy(temp[i], 0, temp2[i], 0, temp[i].length);
		}
		int size = 2 * s + 1;
		int x = r - s - 1;
		int y = c - s - 1;
		while (size > 1) {
			int nx = x;
			int ny = y;
			for (int[] d : dir4) {
				for (int i = 0; i < size - 1; i++) {
					nx += d[0];
					ny += d[1];
					temp[nx][ny] = temp2[nx - d[0]][ny - d[1]];
				}
			}
			size -= 2;
			x += 1;
			y += 1;
		}
	}
}