package bruteforce;

import java.util.*;
public class DfsBfs {
	
	static int N;
	static int M;
	static int V;
	static boolean [][] graph;
	static boolean [] visited;
	static ArrayList<Integer> DFS_result = new ArrayList<>();
	static ArrayList<Integer> BFS_result = new ArrayList<>();
	static Deque<Integer> dq = new ArrayDeque<>();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		V = sc.nextInt(); 
		graph = new boolean[N+1][N+1];
		visited = new boolean[N+1];
		
		for(int i = 0; i < M; i++) {
			int start = sc.nextInt();
			int end = sc.nextInt();
			graph[start][end] = true;
			graph[end][start] = true;
		}
		
		
		DFS(V);
		for(int i = 0; i < DFS_result.size(); i++) {
			System.out.printf("%d ",DFS_result.get(i));
		}
		
		System.out.println();
		
		//BFS용 초기화 
		visited = new boolean[N+1];
		BFS(V);
		for(int i = 0; i < BFS_result.size(); i++) {
			System.out.printf("%d ",BFS_result.get(i));
		}
		
	}
	
	public static void DFS(int node) {
		
		if(visited[node]==true) {
			return;
		}
		
		visited[node] = true;
		DFS_result.add(node);
		
		for(int i = 1; i < N+1; i++) {
			if(graph[node][i]==true) {
				DFS(i);
			}
		}
	}
	
	public static void BFS(int node) {
		
		dq.add(node);
		visited[node] = true;
		
		while(!dq.isEmpty()) {
			//System.out.println(dq);
			int now = dq.removeFirst();	
			
			BFS_result.add(now);
			
			for(int i = 1; i < N+1;i++) {
				if(graph[now][i]==true && visited[i] == false) {
					dq.addLast(i);
					visited[i] = true;
				}
			}

		}
	
	}

}
