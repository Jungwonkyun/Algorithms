package algorithms;

import java.io.*;
import java.util.*;

class Node{
	
	int x;
	int y;
	int cnt;
	boolean wall;
	boolean isBreak;
	
	public Node(int x, int y, int cnt, boolean wall, boolean isBreak) {
		this.x = x;
		this.y = y;
		this.wall = wall;
		this.cnt = cnt;
		this.isBreak = isBreak;
	}
}

public class BreakingWall {
	
	static int N;
	static int M; 
	static Node [][] map;
	static boolean [][][] visited;
	static int result = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in = br.readLine().split(" ");
		N = Integer.parseInt(in[0]);
		M = Integer.parseInt(in[1]);
		map = new Node[N][M];
		visited = new boolean[N][M][2];
		Node nd;
		
		for(int i = 0; i < N; i++) {
			String [] input = br.readLine().split("");
			for(int j = 0; j < M; j++) {				
				if(input[j].equals("0")) {
					nd = new Node(i,j,1,false,false);
				}else {
					nd = new Node(i,j,1,true,false);
				}
				map[i][j] = nd;
			}
		}
		
		
		BFS();
		if(result != Integer.MAX_VALUE)System.out.println(result);
		else if(N==1 && M==1)System.out.println(1);
		else System.out.println(-1);
		
	}
	
	public static void BFS() {
		
		int dx[] = {-1,1,0,0};
		int dy[] = {0,0,1,-1};
		
		Deque<Node> dq = new ArrayDeque<>();
		
		//맨 앞에 0,0 넣고 시작 
		dq.add(map[0][0]); 
		visited[0][0][0] = true;
		
		while(!dq.isEmpty()) {
			Node now_node = dq.removeFirst();	
			int x = now_node.x;
			int y = now_node.y;
			
			for(int i = 0; i < 4; i++) {
				int nx = x+dx[i];
				int ny = y+dy[i];
				
				
				if(nx<0||nx>=N||ny<0||ny>=M)continue;
				//다음 지점이 벽이 아니고  
				if(!map[nx][ny].wall) {

					//이전에 벽을 부시지 않았다면 
					if(!now_node.isBreak&&!visited[nx][ny][0]) {
						visited[nx][ny][0] = true;
					}
					//이전에 벽을 부셨다면  
					else if(now_node.isBreak&&!visited[nx][ny][1])
					{
						visited[nx][ny][1] = true;
					}else continue;
					
					//벽을 부셨는지에 대한 여부는 전 노드를 따라감  -> 지금은 벽이 아니라서 갱신불가 
					Node nod = new Node(nx,ny,now_node.cnt+1,false,now_node.isBreak);
					dq.add(nod);
				}	
				
				//다음 지점이 벽일 때 
				else {
					//만약 벽을 뚫은 적이 있다면 또 뚫을 수 없다 
					if(now_node.isBreak&&!visited[nx][ny][1]) {
						continue;
					}
					
					//벽을 뚫은 적이 없다면 
					else if(!now_node.isBreak&&!visited[nx][ny][0])
					{
						Node nod = new Node(nx,ny,now_node.cnt+1,false,true);		
						visited[nx][ny][1] = true;
						dq.add(nod);
					}else continue;	
				}
				
				//도착한게 있다면 
				if(nx == N-1 && ny == M-1) {
					result = Math.min(result,now_node.cnt+1);
				}
			}
			
		}	
	}

}
