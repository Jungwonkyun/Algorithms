package SamsungSwTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

class Node{
	
	int x;
	int y;
	
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
}


public class MagicianSharkFirestorm {

	static int size;
	static int Q;
	static int[][] map;
	static int[] level;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int result = 0;
	static int ice_cnt = 1;
	static boolean[][] visited;
	static int max_mass = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in = br.readLine().split(" ");
		int N = Integer.parseInt(in[0]);
		Q = Integer.parseInt(in[1]);
		size = (int) Math.pow(2, N);

		map = new int[size][size];
		level = new int[Q];
		int result = 0; 
		
		for (int i = 0; i < size; i++) {
			String[] in2 = br.readLine().split(" ");
			for (int j = 0; j < size; j++) {
				map[i][j] = Integer.parseInt(in2[j]);
			}
		}

		String[] in2 = br.readLine().split(" ");
		for (int i = 0; i < Q; i++) {
			level[i] = Integer.parseInt(in2[i]);
		}

		for (int i = 0; i < Q; i++) {
			DivideAndRotate(level[i]);
			map = MeltIce();
			
		}
		
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				result += map[x][y];
			}
		}
		check();
		System.out.println(result);
		if(max_mass!=0)System.out.println(ice_cnt);
		else System.out.println(0);
	}

	public static void DivideAndRotate(int lv) {

		int frame = (int) Math.pow(2, lv); // 2

		// 한 변에 프레임 갯수
		int numFrame = (size / frame);

		for (int i = 0; i < size; i += frame) {
			for (int j = 0; j < size; j += frame) {

				int[][] temp = new int[frame][frame];
				for (int x = 0; x < frame; x++) {
					for (int y = 0; y < frame; y++) {
						temp[y][frame - x - 1] = map[i + x][j + y];
					}
				}

				for (int x = 0; x < frame; x++) {
					for (int y = 0; y < frame; y++) {
						map[i + x][j + y] = temp[x][y];
					}
				}

			}
		}
	}

	public static int[][] MeltIce() {

		int[][] temp = new int[size][size];
		
		for (int i = 0; i < size; i++) {
			temp[i] = Arrays.copyOf(map[i], size);
		}

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				int cnt = 0;
				int now_ice = temp[i][j];
				if (now_ice == 0)
					continue;

				for (int k = 0; k < 4; k++) {
					int nx = i + dx[k];
					int ny = j + dy[k];
					if (0 > nx || size <= nx || 0 > ny || size <= ny)continue;
					if (map[nx][ny] != 0)cnt++;
				}
				if (cnt < 3)
					temp[i][j]--;
			}
		}
		return temp;
	}

	public static void check() {
		Deque<Node> dq = new ArrayDeque<>();
		visited = new boolean[size][size];
		
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				if(map[i][j]!=0 && visited[i][j]==false) {
					visited[i][j] = true;
					Node nd = new Node(i,j);
					dq.add(nd);
					BFS(dq);
				}
			}
		}
	}
	
	public static void BFS(Deque<Node> dq) {
		
		int temp = 1;
		while(!dq.isEmpty()) {
			
			Node now_node = dq.removeFirst();
			int x = now_node.x;
			int y = now_node.y;
			
			for (int k = 0; k < 4; k++) {
				int nx = x + dx[k];
				int ny = y + dy[k];
				if (0 > nx || size <= nx || 0 > ny || size <= ny || visited[nx][ny] == true||map[nx][ny]==0)continue;
				Node new_node = new Node(nx,ny);
				dq.add(new_node);
				visited[nx][ny] = true;
				temp++;
			}
			
			max_mass = Math.max(temp, max_mass);
		}
		
		ice_cnt = Math.max(ice_cnt, temp);
	}

}
