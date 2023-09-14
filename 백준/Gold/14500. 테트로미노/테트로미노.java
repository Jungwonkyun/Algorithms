import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static int n,m;
	static int[][] map;
	static int max = -1;
	static int [] dx = {-1,1,0,0};
	static int [] dy = {0,0,-1,1};
	static boolean[][] visited;
	static int [][] shapeX = {{1,2,1},{0,-1,0},{0,-1,1},{0,1,0}};
	static int [][] shapeY = {{0,0,1},{1,1,2},{1,1,1},{1,1,2}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n =  Integer.parseInt(st.nextToken());
		m =  Integer.parseInt(st.nextToken());
		map = new int[n][m];
		visited = new boolean[n][m];
		
		for(int i = 0; i < n; i++) {
			String input [] = br.readLine().split(" ");
			for(int j = 0; j < input.length; j++) {
				map[i][j] = Integer.parseInt(input[j]);
			}
		}
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				visited[i][j] = true;
				solve(i,j,0,map[i][j]);
				solve2(i,j);
				visited[i][j] = false;
			}
		}
		
		System.out.println(max);
	}
	
	

	//dfs 돌면서 depth 4짜리의 모든 max값 뽑아서 최댓값 갱신
	private static void solve(int x, int y, int depth, int sum) {
		
		//depth가 4일 때 최댓값 갱신 + return
		if(depth == 3) {
			max = Math.max(max, sum);
			return;
		}
		
		//상하좌우에 대해서 탐색
		for(int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(nx<0||nx>=n||ny<0||ny>=m||visited[nx][ny])continue;
			visited[nx][ny] = true;
			solve(nx,ny,depth+1,sum+map[nx][ny]);
			visited[nx][ny] = false;
		}
		
	}
	
	//ㅗ,ㅏ,ㅓ,ㅜ 모양 합 처리
	private static void solve2(int x, int y) {
		
		//4개의 모양에 대해서
		for(int i = 0; i < 4; i++) {
			int sum = map[x][y];
			boolean update  = true;
			
			//현재 위치 기준 3개 블럭 붙여서 모양 만들기
			for(int j = 0; j < 3; j++) {
				int nx = x + shapeX[i][j];
				int ny = y + shapeY[i][j];
				
				//범위 벗어나면 이 위치에 이 모양을 놓을 수 없다
				if(nx<0||nx>=n||ny<0||ny>=m) {
					update = false;
					break;
				}
				
				sum += map[nx][ny];
			}
			
			//모양을 놓을 수 있는 경우에만 최댓값을 업데이트
			if(update)max = Math.max(max,sum);
		}
	}
}