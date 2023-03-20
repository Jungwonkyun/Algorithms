package algorithms;

import java.util.Scanner;

public class Make1_2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();

		int N = sc.nextInt();
		int[][] ary = new int[N + 1][2];
		
		ary[1][0] = 0;
		ary[1][1] = 1;
		
		for (int i = 2; i < Math.min(N + 1, 4); i++) {
			ary[i][0] = 1;
			ary[i][1] = i;
		}

		if (N == 1) {
			System.out.println(0);
			System.out.println(1);
		}

		else if (N < 4 && N != 1) {
			System.out.println(1);
			System.out.print(N + " " + 1);
		}

		else {
			for (int i = 4; i < N + 1; i++) {
				if (i % 3 == 0) {

					if (i % 2 == 0) {
						if (ary[i - 1][0] <= ary[i / 3][0] && ary[i - 1][0] <= ary[i / 2][0]) {
							ary[i][0] = ary[i - 1][0] + 1;
							ary[i][1] = i - 1;
						} else if (ary[i/2][0] <= ary[i / 3][0] && ary[i/2][0] <= ary[i -1][0]) {
							ary[i][0] = ary[i / 2][0] + 1;
							ary[i][1] = i / 2;
						} else if (ary[i/3][0] <= ary[i / 2][0] && ary[i/3][0] <= ary[i-1][0]){
							ary[i][0] = ary[i / 3][0] + 1;
							ary[i][1] = i / 3;
						}
						
					}

					else {
						if (ary[i - 1][0] < ary[i / 3][0]) {
							ary[i][0] = ary[i - 1][0] + 1;
							ary[i][1] = i - 1;
						} else {
							ary[i][0] = ary[i / 3][0] + 1;
							ary[i][1] = i / 3;
						}

					}

				} else if (i % 2 == 0) {
					if (ary[i - 1][0] < ary[i / 2][0]) {
						ary[i][0] = ary[i - 1][0] + 1;
						ary[i][1] = i - 1;
					} else {
						ary[i][0] = ary[i / 2][0] + 1;
						ary[i][1] = i / 2;
					}
				} else {
					ary[i][0] = ary[i - 1][0] + 1;
					ary[i][1] = i - 1;
				}

			}

			int cnt = 1;

			sb.append(N + " ");
			while (N > 3) {
				sb.append(ary[N][1] + " ");
				N = ary[N][1];
				cnt++;
			}
			sb.append(1);

			System.out.println(cnt);
			System.out.println(sb);
		}

	}

}
