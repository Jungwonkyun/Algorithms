import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, M, H;
	static int[][] ladder;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		ladder = new int[H + 1][N + 1];

		// 이미 깔린 사다리 처리하기
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			ladder[x][y] = 1;
		}

		// 사다리 0, 1, 2, 3개를 추가하는 경우의 수를 다 확인.
		for (int i = 0; i <= 3; i++) {
			pickLadder(1, 0, i);
		}
		
		System.out.println(-1);
	}

	// 사다리 선택하는 조합 구하는 로직
	private static void pickLadder(int row, int cnt, int size) {
		
		if (cnt == size) {
			// 만약에 조건을 만족하면 얘가 최솟값이 되니까 출력하고 나가면 됨
			if (conditionCheck()) {
				System.out.println(size);
				System.exit(0);
			}
			return;
		}

		// 옆에 사다리가 없는 경우에 사다리 놓는 로직 실행
		// row부터 하는 이유 : 이번 놓는 사다리 행 이전에는 이미 확인해서 놓을 필요가 없음
		for (int i = row; i < H + 1; i++) {
			for (int j = 1; j < N; j++) {
				// 현재 위치에 사다리 o or 왼쪽, 오른쪽 옆에 사다리로 연결되어있으면 놓으면 안 된다
				if (ladder[i][j] == 1 || ladder[i][j] == 1 || ladder[i][j+1] == 1)
					continue;
				ladder[i][j] = 1;
				pickLadder(row, cnt + 1, size);
				ladder[i][j] = 0;
			}
		}
	}

	// 사다리 타고 내려가는 로직
	private static boolean conditionCheck() {
		
		// 모든 열에 대해서 실행
		for (int i = 1; i < N + 1; i++) {
			int row = 1;
			int col = i;
			while (row < H+1) {
				// 오른쪽에 사다리가 놓이거나
				if (ladder[row][col] == 1) {
					row += 1;
					col += 1;
				}
				// 왼쪽에 사다리가 놓여진 경우
				else if (ladder[row][col - 1] == 1) {
					row += 1;
					col -= 1;
				}
				// 어디에도 사다리가 안 놓여져있으면 그냥 밑으로 내려감
				else {
					row++;
				}
			}
			
			if(col != i)return false; 
		}

		return true;
	}

}