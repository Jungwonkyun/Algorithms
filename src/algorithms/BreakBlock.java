package algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;

class Block {
	int x;
	int y;
	int value;

	public Block(int x, int y, int value) {
		this.x = x;
		this.y = y;
		this.value = value;
	}
}

public class BreakBlock {
	static String[] in;
	static StringBuilder sb = new StringBuilder();
	static int N, W, H;
	static int[][] map;
	static int[][] newMap;
	static int[] col;
	static boolean[][] visited;
	static int dx[] = { 0, 0, 1, -1 };
	static int dy[] = { -1, 1, 0, 0 };
	static Deque<Block> dq;
	static int result;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t < T + 1; t++) {
			in = br.readLine().split(" ");
			N = Integer.parseInt(in[0]);
			W = Integer.parseInt(in[1]);
			H = Integer.parseInt(in[2]);
			map = new int[H][W];
			dq = new ArrayDeque<>();
			result = Integer.MAX_VALUE;

			for (int i = 0; i < H; i++) {
				in = br.readLine().split(" ");
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(in[j]);
				}
			}

			col = new int[N];
			Arrays.fill(col, -1);
			pickCol(0);
			
			System.out.println("#"+t+" "+result);
		}
	}

	public static void pickCol(int depth) {
		if (depth == N) {
			startGame(map, col);
			return;
		}

		for (int i = 0; i < W; i++) {
			col[depth] = i;
			pickCol(depth + 1);
			col[depth] = -1;
		}

	}

	public static void startGame(int[][] blockMap, int[] col) {

		newMap = new int[H][W];
		for (int i = 0; i < H; i++) {
			newMap[i] = Arrays.copyOf(map[i], W);
		}

		for (int i = 0; i < N; i++) {

			// ����Ʈ�� ��
			int c = col[i];
			int startx = 0;
			int nextblock = -1;

			// ������ ����ų� ��ĭ�� �ƴ� ������ ���� ������ �μ� ������ ã�´�
			while (true) {
				if (newMap[startx][c] != 0) {
					nextblock = newMap[startx][c];
					break;
				}

				if (startx == H - 1) {
					break;
				}

				startx++;
			}

			// �μ� ������ ���� ��� ���� ���� ������ ����Ʈ����
			if (nextblock == -1)
				continue;
			// ���� 1�� �μ��� 1�� �����ش�
			else if (nextblock == 1)
				newMap[startx][c] = 0;
			// ���� 1�� �ƴ϶� �� ū ����� �����ؼ� �μ�����Ѵ�
			else {
				bfs(startx, c, nextblock);
			}

		}
		
		result = Math.min(countBlock(), result);

	}
	

	public static void bfs(int a, int b, int value) {
	
		Block bl = new Block(a, b, value);
		dq.add(bl);
		newMap[a][b] = 0;
		while (!dq.isEmpty()) {
			Block nowBlock = dq.poll();
			int x = nowBlock.x;
			int y = nowBlock.y;
			
			for (int d = 0; d < 4; d++) {
				for (int k = 0; k < nowBlock.value; k++) {
					int nx = x + k * dx[d];
					int ny = y + k * dy[d];

					if (nx < 0 || nx >= H || ny < 0 || ny >= W || newMap[nx][ny] == 0)
						continue;

					// ���������� �μ� ������ 1¥���� �¸� 0���� �ٲ�����
					if (newMap[nx][ny] == 1) {
						newMap[nx][ny] = 0;
					} else {
						Block newBlock = new Block(nx, ny, newMap[nx][ny]);
						newMap[nx][ny] = 0;
						dq.add(newBlock);
					}

				}
				
			}

		}
		
		downBlock();
	
	}
	
	
	public static void downBlock() {
		
		for(int w = 0; w < W; w++) {
			for(int i = H-1; i >= 0; i--) {
				int x = i;
				int y = w;
				//������ ������ �ƴ϶��
				if(newMap[x][y]!=0) {
					//������ ����� �ʰ� ���� �̵��� ����ĭ�� ������� �� ����
					while((x+1)<H && newMap[x+1][y]==0) {		
						int temp = newMap[x][y];
						newMap[x][y] = 0;
						newMap[x+1][y] = temp;
						x++;
					}
				}
			} 
		}
		
	}
	
	public static int countBlock() {
		
		int cnt = 0;
		for(int i = 0; i < H; i++) {
			for(int j = 0; j < W; j++) {
				if(newMap[i][j]!=0)cnt++;
			}
		}
		
		return cnt;
		
	}
	
}
