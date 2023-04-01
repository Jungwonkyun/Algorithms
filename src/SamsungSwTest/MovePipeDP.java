package SamsungSwTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MovePipeDP {
	static int N;
	static int[][] Room;
	static int[][][] DP;
	static ArrayList<Integer>[] Mode;
	static int[] dx = { 0, 1, 1 };
	static int[] dy = { 1, 1, 0 };
	static int result = 0;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		Room = new int[N][N];
		DP = new int[N+1][N+1][3];

		for (int i = 0; i < N; i++) {
			String in[] = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				Room[i][j] = Integer.parseInt(in[j]);
			}
		}

		putPipe();
		
		for(int i = 0; i < 3; i++) {
			result += DP[N-1][N-1][i];
		}
		System.out.println(result);
	}

	public static void putPipe() {

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				
				//초깃값 세팅
				if(i == 0 && j == 0) {
					DP[0][1][0] = 1;
					DP[0][1][1] = 0;
					DP[0][1][2] = 0;
					continue;
				}
				
				if(Room[i][j] == 1) {
					DP[i][j][0] = 0;
					DP[i][j][1] = 0;
					DP[i][j][2] = 0;
				}
				
				// 현재칸으로 오는 mode들에 대해서 유효성 검사
				for (int k = 0; k < 3; k++) {
					int mode = k;
					// 왼쪽, 대각선,위쪽 검사하기
					int temp0 = 0; // 0번 mode dp
					int temp1 = 0; // 1번 mode dp
					int temp2 = 0; // 2번 mode dp

					if (mode == 0) {
						if((j+1>=N)||Room[i][j+1]==1)continue;			
						temp0 = DP[i][j][0];
						temp1 = DP[i][j][1];
						DP[i][j+1][0] += (temp0 + temp1);
					}

					// 대각선 검사
					if (mode == 1) {
						if((i+1>=N)||(j+1>=N) || Room[i][j+1]==1||Room[i+1][j]==1|| Room[i+1][j+1]==1)continue;
						
						temp0 = DP[i][j][0];
						temp1 = DP[i][j][1];
						temp2 = DP[i][j][2];
						
						// 1번 파이프를 타고왔으면 모두 가능하니까 다 더해준다
						DP[i+1][j+1][1] += (temp0 + temp1 + temp2);
					}

					if (mode == 2) {
						if((i+1>=N)||Room[i+1][j]==1)continue;
						// 위쪽 검사
						temp1 = DP[i][j][1];
						temp2 = DP[i][j][2];

						// 2번 파이프를 타고 왔으니 0번 파이프 빼고 더해준다
						DP[i+1][j][2] += (temp1 + temp2);
					}
				}
			}
		}

	}

}
