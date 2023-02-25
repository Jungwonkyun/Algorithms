package SamsungSwTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class DeclineRoad {

	//M x N array
	static int M;
	static int N;
	static int [][] Route;
	static int [][] Income;
	static boolean [][] visited;
 	static int [][] DP;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String [] in = br.readLine().split(" ");
		M = Integer.parseInt(in[0]);
		N = Integer.parseInt(in[1]);
		
		Route = new int[M][N];
		DP = new int[M][N];
		visited = new boolean[M][N];
		
		for(int i = 0; i < M; i++) {
			Arrays.fill(DP[i], -1);
		}
		
		for(int i = 0; i < M; i++) {
			String[] input = br.readLine().split(" ");
			for(int j = 0; j < N; j++) {
				Route[i][j] = Integer.parseInt(input[j]);
			}
		}
		
		System.out.println(DFS(0,0));
	}
	
	public static int DFS(int x, int y) {
		
		//도착지에 도달했다면 경로 1개를 리턴 
		if(x==(M-1) && y==(N-1))return 1;
		
		//Memorization 
		if(DP[x][y] != -1) return DP[x][y];
		
		//visited 대신 쓴다
		DP[x][y] = 0;
		
		int pivot = Route[x][y];
		for(int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx<0||nx>=M||ny<0||ny>=N||pivot<=Route[nx][ny])continue;
			
			DP[x][y] += DFS(nx,ny);
			
		}
		
		return DP[x][y];
		
	}
	
}