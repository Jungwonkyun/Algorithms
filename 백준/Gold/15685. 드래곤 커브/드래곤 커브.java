import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	static boolean[][] visited;
	static int[] dx = { 1, 0, -1, 0 }; // 방향 순서는 E -> N -> W -> S
	static int[] dy = { 0, -1, 0, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		visited = new boolean[101][101];
		for (int i = 0; i < n; i++) {
			String[] input = br.readLine().split(" ");
			int x = Integer.parseInt(input[0]);
			int y = Integer.parseInt(input[1]);
			int d = Integer.parseInt(input[2]);
			int g = Integer.parseInt(input[3]);
			drawDragonCurve(x, y, d, g);
		}
		int result = checkSquare();
		System.out.println(result);
	}

	//드래곤 커브 그리는 로직
	private static void drawDragonCurve(int x, int y, int d, int g) {

		List<Integer> dir = new ArrayList<Integer>();
		// 0세대 방향 주기
		dir.add(d);
		visited[x][y] = true;
		
		// 0세대이면 방문처리하고 리턴해준다
		if (g == 0) {
			visited[x][y] = true;
			visited[x + dx[d]][y + dy[d]] = true;
			return;
		}

		// 0세대는 이미 저장했으니까 1세대 부터
		for (int i = 1; i < g + 1; i++) {
			// 이전 세대 드래곤 커브를 뒤집어서 90씩 틀어줘야한다
			for (int j = dir.size() - 1; j >= 0; j--) {
				dir.add((dir.get(j) + 1) % 4);
			}
		}
		
		//드래곤 커브에 따라서 점찍기
		for(int num : dir) {
			x = x+dx[num];
			y = y+dy[num];
			if(x<0||x>100||y<0||y>100)continue;
			visited[x][y] = true;
		}	
	}
	
	//정사각형의 모든 꼭짓점이 방문처리 되어있으면 드래곤 커브로 둘러싸여 있는 거임
	private static int checkSquare() {
		int cnt = 0;
		for(int i = 0; i<100; i++) {
			for(int j = 0; j<100; j++) {
				if(visited[i][j]&&visited[i+1][j]&&visited[i][j+1]&visited[i+1][j+1])cnt++;
			}
		}
		return cnt;
	}
}