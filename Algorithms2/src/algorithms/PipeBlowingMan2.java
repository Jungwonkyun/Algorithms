package algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class PipeBlowingMan2 {

	static int R;
	static int C;
	static String[][] map;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new String[R][C];
		parent = new int[R * C];

		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().split("");
		}

		// 부모노드 초기화 (2차원 배열을 1차원으로)
		for (int i = 0; i < R * C; i++) {
			parent[i] = i;
		}

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				int cur = i*C+j;
				int nxt = followDirection(i, j, map[i][j]);
				if(!equals(cur, nxt))union(cur, nxt);
			}
		}
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				int cur = i*C+j;
				int nxt = followDirection(i, j, map[i][j]);
				if(!equals(cur, nxt))union(cur, nxt);
			}
		}


		Set<Integer> s = new HashSet<>();
		for (int i = 0; i < R * C; i++) {
			s.add(parent[i]);
		}

		for (int i = 0; i < R * C; i++) {
			System.out.print(parent[i] + " ");
			if(i!=0 && i%(C-1)==0)System.out.println();
		}
		

	}

	public static int followDirection(int x, int y, String d) {

		int dir = -1;

		switch (d) {
		case "U": {
			dir = 0;
		}
			break;

		case "D": {
			dir = 1;
		}
			break;

		case "L": {
			dir = 2;
		}
			break;

		case "R": {
			dir = 3;
		}
			break;
		}

		int nx = x + dx[dir];
		int ny = y + dy[dir];

		return nx*C+ny;
	}

	// a,b의 대소관계에 따라서 부모노드를 바꿔준다
	public static void union(int a, int b) {
		// 최상위 부모 찾기
		int x = find(a);
		int y = find(b);

		if (x < y) {
			parent[y] = x;
		} else {
			parent[x] = y;
		}
	}

	// a와 b의 부모가 같은지 확인
	public static boolean equals(int a, int b) {
		int x = find(a);
		int y = find(b);
		return x == y;
	}

	// a의 부모노드를 찾는 연산
	public static int find(int a) {
		if (parent[a] == a)
			return a;
		return parent[a] = find(parent[a]);
	}

}