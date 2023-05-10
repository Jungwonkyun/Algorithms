package algorithms;

/*
 * BFS depth 별로 수행하는 기본문제 
 * 대신 조건에 따라서 depth를 비교해가며 업데이트 해줘야한다
 * 1개만 있을 때 조건 다르게 수행 
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Node {
	int x;
	int y;

	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class EscapeRoom {
	static int N, M;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int answer = 0;
	static int maxDepth = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		

		// input 받기
		for (int i = 0; i < N; i++) {
			String[] in = br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(in[j]);
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0)
					continue;
				bfs(i, j);
			}
		}
		
		System.out.println(answer);
	}

	public static void bfs(int a, int b) {
		
		visited = new boolean[N][M];
		
		int result = map[a][b];
		Deque<Node> q = new LinkedList<>();
		Node start = new Node(a, b);
		q.add(start);
		visited[a][b] = true;

		int depth = 0;
		int max = -1;
		
		//depth별 BFS
		while (!q.isEmpty()) {
			int size = q.size();
			max = -1;
			depth++;
			for (int n = 0; n < size; n++) {
				Node now = q.poll();
				int x = now.x;
				int y = now.y;
				max = Math.max(max,map[x][y]);
				for (int i = 0; i < 4; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];
					if (nx < 0 || nx >= N || ny < 0 || ny >= M || map[nx][ny] == 0 || visited[nx][ny])
						continue;
					Node newNode = new Node(nx, ny);
					visited[nx][ny] = true;
					q.offer(newNode);
				}
			}
		}
		
		//1칸짜리가 비밀번호가 될 수 있는 경우
		if(maxDepth<=1 && depth==1) {
			answer =  Math.max(answer,result);
			return;
		}
		
		//depth가 더 깊은 경우엔 무조건 업데이트 
		if(maxDepth<depth) { 
			maxDepth = depth;
			answer = result+max;
		}
		//depth가 같은 경우엔 최댓값을 업데이트
		else if(maxDepth==depth) {
			answer = Math.max(answer,result+max);
		}
		
	}
}