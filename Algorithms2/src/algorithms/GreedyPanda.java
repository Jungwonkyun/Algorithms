package algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class GreedyPanda {
	static int N;
	static int[][] map;
	static int[][] dp;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		map = new int [N][N];
		dp = new int [N][N];
		
	
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//2차원 배열 순회하면서 방문한 적이 없을 때만 dfs를 돌려준다
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(dp[i][j] == 0)dfs(i,j,1);
			}
		}
		
		int max = 1;
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				max = Math.max(max, dp[i][j]);
			}
		}
		
		System.out.println(max);
		
	}
	
	public static void dfs(int x, int y, int depth) {
		
		dp[x][y] = depth;
		
		int nx;
		int ny;
		
		for(int i = 0; i < 4; i++) {
			nx = x + dx[i];
			ny = y + dy[i];
			
			//만약 범위를 벗어나거나 이동할 대나무 양이 현재보다 작으면 continue
			if(nx < 0|| nx >= N|| ny < 0 || ny >= N || map[x][y]>=map[nx][ny])continue;
			
			//다음 움직일 칸의 dp값이 더 크다면 탐색할 필요없음 
			if (depth<dp[nx][ny])continue;
			
			int prev = map[x][y];
			map[x][y] = 0;
			dfs(nx,ny,depth+1);
			map[x][y] = prev;
		}
		
		return;
	}
	
}
