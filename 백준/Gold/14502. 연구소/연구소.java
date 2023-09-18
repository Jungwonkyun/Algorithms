import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Node {
	int x;
	int y;

	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Node [x=");
		builder.append(x);
		builder.append(", y=");
		builder.append(y);
		builder.append("]");
		return builder.toString();
	}

}

public class Main {
	static int n, m;
	static int[][] map;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static List<Node> emptyList;
	static List<Node> virusList;
	static Node[] pickedWall = new Node[3];
	static int safteyArea = -1;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		emptyList = new ArrayList<>();
		virusList = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			String input[] = br.readLine().split(" ");
			for (int j = 0; j < input.length; j++) {
				int now = Integer.parseInt(input[j]);
				map[i][j] = now;
				if (now == 0)
					emptyList.add(new Node(i, j));
				else if (now == 2)
					virusList.add(new Node(i, j));
			}
		}

		pickWall(0, 0);
		System.out.println(safteyArea);
	}

	// 조합을 통해 세울 3개의 벽 좌표 고르기
	private static void pickWall(int idx, int depth) {

		// 3개의 벽 선택하고 확산함수로 넘기기
		if (depth == 3) {
			spread(pickedWall);
			return;
		}

		// 끝까지 탐색한 이후에 리턴
		if (idx == emptyList.size())
			return;
		
		pickedWall[depth] = emptyList.get(idx);

		// 재귀를 이용한 선택
		pickWall(idx + 1, depth + 1);
		pickWall(idx + 1, depth);

	}

	// 벽 세우고 BFS로 바이러스
	private static void spread(Node[] wall) {
		
		boolean[][] visited = new boolean[n][m];;
		
		//원본 손대면 안 되니까 배열 깊은 복사
		int copyMap[][] = new int[n][m];
        for (int i = 0; i < n; i++) {
            copyMap[i] = map[i].clone();
        }
		
		// 벽세우기
		for (Node node : wall) {
			copyMap[node.x][node.y] = 1;
		}
		
		//BFS queue에 넣고
		Queue<Node>q = new LinkedList<>();
		for(int i = 0; i < virusList.size(); i++) {
			q.offer(virusList.get(i));
			visited[virusList.get(i).x][virusList.get(i).y] = true;
		}
		
		while(!q.isEmpty()) {
			Node now = q.poll();
			int x = now.x;
			int y = now.y;
			for(int i = 0; i < 4; i++) {
				int nx = x+dx[i];
				int ny = y+dy[i];
				if(nx<0||nx>=n||ny<0||ny>=m||visited[nx][ny]||copyMap[nx][ny]!=0)continue;
				visited[nx][ny] = true;
				copyMap[nx][ny] = 2;
				q.offer(new Node(nx,ny));
			}
		}
		
		countArea(copyMap);
		
	}

	private static void countArea(int[][] copyMap) {
		int cnt = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(copyMap[i][j] == 0)cnt++;
			}
		}
		
		
		safteyArea = Math.max(safteyArea, cnt);
		
		
	}

}