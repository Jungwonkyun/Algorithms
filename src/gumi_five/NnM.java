package gumi_five;

import java.util.Scanner;

public class NnM {
	
	static int N,M;
	static boolean [] visited;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		visited = new boolean[N];
		
		DFS(0,0);
	}
	
	
	public static void DFS(int num, int depth) {
		
		if(depth == M) {
			for(int i = 0; i< N; i++) {
				if(visited[i] == true) {
					System.out.printf("%d ",i+1);
				}
			}
			System.out.println();
			
		}
		
		for(int i = num; i < N; i++) {
			visited[i] = true; 
			DFS(i+1,depth+1);
			visited[i] = false;
		}
		
		
	}
	
}
