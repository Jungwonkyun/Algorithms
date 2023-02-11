package algorithms;

import java.util.*;
import java.io.*;

public class TSP2 {
	
	static int N;
	static int[][] map;
	static boolean[] visited; 
	static int minimum = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new boolean[N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}		
		
		for(int i = 0; i < N; i++) {
			visited[i] = true;
			DFS(0,0,0,i);
			
		}
		System.out.println(minimum);
	
	}
	
	
	public static void DFS(int sum,int node, int depth, int start) {
		
		if(depth == N-1) {
			if(map[node][start]!=0) {
				minimum = Math.min(minimum,sum+=map[node][start]);
			}
			return; 
		}
		
		
		for(int i = 0; i < N; i++) {
			if(!visited[i]&&map[node][i]!=0) {
				visited[i] = true;
				DFS(sum+map[node][i],i,depth+1,start);
				visited[i] = false;
			}
		}
		
	}

}
