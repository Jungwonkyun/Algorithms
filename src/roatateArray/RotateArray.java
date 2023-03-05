package roatateArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class RotateArray {
	static String[] in;
	static int[][] Array;
	static Queue<Integer> q1;
	static Queue<Integer> q2;
	static Queue<Integer> q3;
	static Queue<Integer> q4;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 0; t < TC; t++) {
			in = br.readLine().split(" ");
			int N = Integer.parseInt(in[0]);
			int d = Integer.parseInt(in[1]);

			Array = new int[N][N];
			q1 = new LinkedList<>();
			q2 = new LinkedList<>();
			q3 = new LinkedList<>();
			q4 = new LinkedList<>();

			for (int i = 0; i < N; i++) {
				in = br.readLine().split(" ");
				for (int j = 0; j < N; j++) {
					Array[i][j] = Integer.parseInt(in[j]);
				}
			}
			
			if(d>=360)d-=(360*(d/360));
			for (int k = 0; k < Math.abs(d) / 45; k++) {
				// 주 대각선 담기
				for (int i = 0; i < N; i++) {
					q1.add(Array[i][i]);
				}

				// 가운데 행 담기
				for (int i = 0; i < N; i++) {
					q2.add(Array[(N - 1) / 2][i]);
				}

				// 부 대각선 담기
				for (int i = 0; i < N; i++) {
					q3.add(Array[(N - 1) - i][i]);
				}

				// 가운데 열 담기
				for (int i = 0; i < N; i++) {
					q4.add(Array[(N - 1) - i][(N - 1) / 2]);
				}

				// clockwise Rotate
				if (d > 0) {
					for (int i = 0; i < N; i++) {
						Array[i][(N - 1) / 2] = q1.poll();
					}
					for (int i = 0; i < N; i++) {
						Array[i][i] = q2.poll();
					}
					for (int i = 0; i < N; i++) {
						Array[(N - 1) / 2][i] = q3.poll();
					}
					for (int i = 0; i < N; i++) {
						Array[(N - 1) - i][i] = q4.poll();
					}
				}

				// counter Clockwise Rotate
				else if (d < 0) {
					for (int i = 0; i < N; i++) {
						Array[(N - 1) / 2][i] = q1.poll();
					}
					for (int i = 0; i < N; i++) {
						Array[(N - 1) - i][i] = q2.poll();
					}
					for (int i = 0; i < N; i++) {
						Array[(N - 1) - i][(N - 1) / 2] = q3.poll();
					}
					for (int i = 0; i < N; i++) {
						Array[(N - 1) - i][(N - 1) - i] = q4.poll();
					}
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					sb.append(Array[i][j] + " ");
				}
				sb.append('\n');
			}
		}
		System.out.println(sb);

	}

}
