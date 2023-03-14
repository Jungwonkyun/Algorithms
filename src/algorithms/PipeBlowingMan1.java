package algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class PipeBlowingMan1 {

	static int R;
	static int C;
	static String[][] map;
	static boolean[][] visited;
	static int SafetyZone = 1;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new String[R][C];
		visited = new boolean[R][C];

		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().split("");
		}

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (!visited[i][j]) {
					followDirection(i, j, map[i][j]);

				}
			}
		}

		System.out.println(SafetyZone - 1);

	}

	public static void followDirection(int x, int y, String d) {

		int dir = -1;
		visited[x][y] = true;
		map[x][y] = "X"; 
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

		if(visited[nx][ny] == false) {
			followDirection(nx, ny, map[nx][ny]);
		}else {
			if(map[nx][ny] == "X")SafetyZone++;
		}

		map[x][y] = "O"; 
		
		return;
	}

}