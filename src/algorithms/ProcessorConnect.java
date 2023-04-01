package algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

class processor {

	int x;
	int y;

	processor(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class ProcessorConnect {
	static int[][] chip;
	static int N;
	static ArrayList<processor> pList;
	static int max;
	static int result;
	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { -1, 1, 0, 0 };
	static int pcnt;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());

		for (int t = 1; t < TC + 1; t++) {
			N = Integer.parseInt(br.readLine());
			chip = new int[N][N];
			pList = new ArrayList<>();
			max = Integer.MIN_VALUE;
			result = Integer.MAX_VALUE;
			pcnt = 0;

			for (int i = 0; i < N; i++) {
				String[] in = br.readLine().split(" ");
				for (int j = 0; j < N; j++) {
					chip[i][j] = Integer.parseInt(in[j]);
					
					if(in[j].equals("1"))pcnt++;
					// 끝에 없는 프로세서들만 연결한다
					if (i != 0 && j != 0 && i != N - 1 && j != N - 1 && in[j].equals("1")) {
						processor ps = new processor(i, j);
						pList.add(ps);
					}
				}
			}


			boolean[][] visited = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (chip[i][j] == 1)
						visited[i][j] = true;
				}
			}

			dfs(0, 0, 0, visited);

			System.out.println("#"+t+" "+(result - pcnt));

		}

	}

	public static void dfs(int idx, int depth, int cnt, boolean[][] visited) {

		// 만약 지금 연결하는 프로세서랑 남은 프로세서를 다 연결해도 max를 못 넘거나, 마지막 프로세서까지 넣으면 리턴
		if (cnt + (pList.size() - depth) < max || depth == pList.size()) {
				int temp = 0;
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if (visited[i][j] == true)
							temp++;
					}
				}

				//연결된 프로세서가 이전 맥스값 보다 큰 경우만 업데이트 해준다 
				if (cnt >= max) {
					

					if(cnt > max) {
						result = temp;
					}else {
						result = Math.min(result, temp);
					}
					max = cnt;
					
				}
			return;
		}

		for (int i = idx; i < pList.size(); i++) {
			int x = pList.get(idx).x;
			int y = pList.get(idx).y;

			// 프로세서를 추가하는 경우
			for (int j = 0; j < 4; j++) {
				boolean flag = true;
				boolean[][] newVisit = new boolean[N][N];

				for (int k = 0; k < N; k++) {
					newVisit[k] = Arrays.copyOf(visited[k], N);
				}

				// 왼쪽 탐색
				if (j == 0) {
					for (int a = y - 1; a >= 0; a--) {
						newVisit[x][a] = true;
						if (chip[x][a] == 1 || visited[x][a] == true) {
							flag = false;
							break;
						}
					}
				}

				// 오른쪽 탐색
				else if (j == 1) {
					for (int a = y + 1; a < N; a++) {
						newVisit[x][a] = true;
						if (chip[x][a] == 1 || visited[x][a] == true) {
							flag = false;
							break;
						}
					}
				}

				// 아래쪽 탐색
				else if (j == 2) {
					for (int a = x + 1; a < N; a++) {
						newVisit[a][y] = true;
						if (chip[a][y] == 1 || visited[a][y] == true) {
							flag = false;
							break;
						}
					}
				}

				// 위쪽 탐색
				else if (j == 3) {
					for (int a = x - 1; a >= 0; a--) {
						newVisit[a][y] = true;
						if (chip[a][y] == 1 || visited[a][y] == true) {
							flag = false;
							break;
						}
					}
				}

				// 전선을 연결할 수 있는 경우에만 다음 dfs를 보내준다
				if (flag == true)
					dfs(i + 1, depth + 1, cnt + 1, newVisit);
			}

			// 프로세서를 추가하지 않는 경우
			dfs(i + 1, depth + 1, cnt, visited);

		}

	}

}
