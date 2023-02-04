package gumi_five;

import java.util.*;
import java.io.*; 

public class Virus {
		static int N;
		static boolean [] visited;
		static Deque<Integer> dq = new ArrayDeque<>();
		static ArrayList<Integer> result = new ArrayList<>();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		visited = new boolean[N+1]; 
		
		
		ArrayList<Integer> [] network = new ArrayList[N+1];
		for(int i = 0; i < N+1; i++) {
			network[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int fi = Integer.parseInt(st.nextToken());
			int se = Integer.parseInt(st.nextToken());
			network[fi].add(se);
			network[se].add(fi);
		}
	
		
		visited[1] = true;
		dq.add(1);
		while(!dq.isEmpty()) {
			
			int now = dq.removeFirst();
			if(now!=1)result.add(now);
			
			for(int i = 0; i < network[now].size();i++) {
				if(visited[network[now].get(i)] == false) {
					dq.add(network[now].get(i));
					visited[network[now].get(i)] = true;
				}
			}
			
		}
		
		System.out.println(result.size());
	}
	

}
