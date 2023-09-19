import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Cctv {
	int type;
	int x;
	int y;
	
	public Cctv(int type, int x, int y) {
		this.type = type;
		this.x = x;
		this.y = y;
	}
	
}

public class Main {
	static int n,m;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int result = Integer.MAX_VALUE;
	//각각의 cctv idx마다 가능한 방향 조합을 미리 구해놓는다. => switch-case하기 싫어서
	static int[][][]dir = {{},{{0},{1},{2},{3}},{{0,1},{2,3}},{{0,2},{0,3},{1,2},{1,3}},
						{{0,1,2},{1,2,3},{2,3,0},{3,0,1}},{{0,1,2,3}}};
	
	static List<Cctv> cctvList;  //빈 영역
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		m =  Integer.parseInt(st.nextToken());
		n =  Integer.parseInt(st.nextToken());
		int[][] map = new int[m][n];
		cctvList = new ArrayList<Cctv>();
		
		for (int i = 0; i < m; i++) {
			String input[] = br.readLine().split(" ");
			for (int j = 0; j < input.length; j++) {
				int now = Integer.parseInt(input[j]);
				map[i][j] = now;
				//1~5일때 cctv이다
				if (now >= 1 && now <=5)
					cctvList.add(new Cctv(now,i, j));
			}
		}
		
		startWatch(0,map);
		System.out.println(result);
	}
	
	private static void startWatch(int depth, int[][] mapInfo) {
	
		//모든 cctv에 대해서 감시영역을 다 체크하고나면
		if(depth == cctvList.size()) {
			checkSqare(mapInfo);
			return;
		}
		
		//cctv정보 가져오기 
		int type = cctvList.get(depth).type;
		int x = cctvList.get(depth).x;
		int y = cctvList.get(depth).y;
		
		//해당 번호의 cctv가 가질 수 있는 모든 방향 체크 
		for(int[] di : dir[type]) {
			//배열 깊은 복사
			int[][]copyMap = new int[m][n];
			for(int i = 0; i < m; i++) {
				copyMap[i] = mapInfo[i].clone();
			}
			for(int d : di) {
				int nx = x;
				int ny = y;
				while(true) {
					nx = nx+dx[d];
					ny = ny+dy[d];
					//범위를 벗어나거나 벽을 만나면 break한다
					if(nx<0||nx>=m||ny<0||ny>=n||copyMap[nx][ny]==6)break;
					//cctv나 다른 감시카메라가 이미 감시하고 있는 영역이면 뛰어넘는다
					if(copyMap[nx][ny]!=0)continue;
					//감시가 가능한 영역이니까 체크 
					copyMap[nx][ny] = -1;
				}
			}
			//방향 별 체크를 마치고 나면 재귀돌리기
			startWatch(depth+1, copyMap);
		}		
	}

	private static void checkSqare(int[][] mapInfo) {
		int cnt = 0;
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				if(mapInfo[i][j] == 0)cnt++;
			}
		}
		result = Math.min(result, cnt);
	}

}