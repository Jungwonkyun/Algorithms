package algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SNS {
	
	static int N; 
	static ArrayList<Integer>[]friends;
	static int [][] dp;
	static boolean[] visited;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		
		//dp[N][0]은 자기 자신이 얼리어답터가 아닐 때 
		//dp[N][1]은 자기 자신이 얼리 어답터 일 때
		dp = new int[N+1][2];
		visited = new boolean [N+1];
		friends = new ArrayList[N+1];
		
		for(int i = 0; i < N+1; i++) {
			friends[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int to = Integer.parseInt(st.nextToken());
			int from = Integer.parseInt(st.nextToken());
			
			//인접리스트에 추가한다 
			friends[to].add(from);
			friends[from].add(to);
		}
		
		dfs(1);
		
		System.out.println(Math.min(dp[1][0], dp[1][1]));
	}
	
	public static void dfs(int node) {
		visited[node] = true; 
		
		dp[node][0] = 0;   //지금 노드가 얼리어답터가 아닐 때
		dp[node][1] = 1;   //지금 노드가 얼리어답터 일 때
		
		ArrayList<Integer>ChildAry = friends[node];
		
		for(int child : ChildAry) {
			if(!visited[child]) {
				
				//자식 노드 얼리어답터 정보 업데이트  
				dfs(child);
				
				//현재 노드가 얼리어답터가 아니면 앞 뒤 노드가 모드 얼리어답터
				dp[node][0] += dp[child][1];  
				
				//현재 노드가 얼리어답터 이면 앞 뒤 노드는 얼리어답터 이어도 되고 아니어도 상관 없다
				dp[node][1] += Math.min(dp[child][0], dp[child][1]); 	
			}
		}
		
		return;
	}
		
}