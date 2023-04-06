package algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;


class BC implements Comparable<BC>{
	int x;
	int y;
	int C;
	int P; 
	
	BC(int x,int y,int C, int P){
		this.x = x-1;
		this.y = y-1;
		this.C = C;
		this.P = P;
	}

	@Override
	public int compareTo(BC o) {
		return o.P - this.P;
	}	
}

class Node4{
	int x;
	int y;
	
	Node4(int x, int y){
		this.x = x;
		this.y = y;
	}
	
}

public class WirelessCharge {
	
	static int T;
	static int M;
	static int A;
	static int[] MoveA;
	static int[] MoveB;
	static String[] input;
	static BC[] BCInfo;
	static int [] dx = {0,-1,0,1,0};
	static int [] dy = {0,0,1,0,-1};
	static int [][][] map;
	static ArrayList<BC> Acnt = new ArrayList<>();
	static ArrayList<BC> Bcnt = new ArrayList<>();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine()); 
		
		for(int n = 0; n < T; n++) {
		
		int Total = 0;
		input = br.readLine().split(" "); 
		M = Integer.parseInt(input[0]);
		A = Integer.parseInt(input[1]);
		
		MoveA = new int[M+1];
		MoveB = new int[M+1];
		BCInfo = new BC[A];
		map = new int[10][10][A];
		
		
		input = br.readLine().split(" "); 
		for(int i = 0; i < M; i++) {
			MoveA[i+1] = Integer.parseInt(input[i]);
		}
		
		input = br.readLine().split(" "); 
		for(int i = 0; i < M; i++) {
			MoveB[i+1] = Integer.parseInt(input[i]);
		}
		
		MoveA[0] = 0;
		MoveB[0] = 0;
		
		for(int i = 0; i < A; i++) {
			input = br.readLine().split(" "); 
			int [] temp = new int[4];
			BC bc = null;
			for(int j = 0; j < 4; j++) {
				temp[j] = Integer.parseInt(input[j]);
			}
			bc = new BC(temp[1],temp[0],temp[2],temp[3]);
			BCInfo[i] = bc;
		}
		
		//BC마다 전파가 퍼지는 영역 표시 
		getBCArea();
		
		int Ax = 0;
		int Ay = 0;
		int Bx = 9;
		int By = 9;
		
		//move 1번마다 충전할 수 있는 경우를 트래킹 
		for(int i = 0; i < M+1; i++) {
			
			//다음 명령으로 이동하는 A와 B의 좌표 
			Ax = Ax + dx[MoveA[i]];
			Ay = Ay + dy[MoveA[i]];
			
			Bx = Bx + dx[MoveB[i]];
			By = By + dy[MoveB[i]];
			
			for(int j = 0; j < A; j++) {	
				//A가 있는 지점부터 접근할 수 있는 BC가 몇 개인가? 
				if(map[Ax][Ay][j] == 1)Acnt.add(BCInfo[j]);
				
				//B가 있는 지점부터 접근할 수 있는 BC가 몇 개인가? 
				if(map[Bx][By][j] == 1)Bcnt.add(BCInfo[j]);
			}
			
			Collections.sort(Acnt);
			Collections.sort(Bcnt);
			
			//A는 없고 B는 충전할 수 있을 때 
			if(Acnt.size()==0) {				
				if(Bcnt.size() != 0) {
					Total+=Bcnt.get(0).P;
				} 
			}
			
			//B는 없고 A는 충전할 수 있을 때 
			else if(Bcnt.size()==0) {				
				if(Acnt.size() != 0) {
					Total+=Acnt.get(0).P;
				} 
			}
			
			//둘 다 충전할 수 있을 때 
			else{
				int Asize = Acnt.size();
				int Bsize = Bcnt.size();
				
				//둘이 충전할 수 있는 최대의 충전소가 같을 때 
				if(Acnt.get(0) == Bcnt.get(0)) {
					//겹치는데 둘이 같은 충전소에서 해야한다면 반띵한다 
					if(Asize == 1 && Bsize == 1) {
						//System.out.println(1);
						Total += (Acnt.get(0).P);
		
					}
					
					//겹치는데 대안이 있다면 차선책을 택한다 
					else if(Asize == 1 && Bsize != 1) {
						Total += (Acnt.get(0).P+Bcnt.get(1).P);	
					}
					
					//겹치는데 대안이 있다면 차선책을 택한다 
					else if(Asize != 1 && Bsize == 1) {
						Total += (Acnt.get(1).P+Bcnt.get(0).P);	 
					}
					
					//겹치는데 대안이 있다면 차선책을 택하는데 2번째 값 중에 큰 값으로 
					else {
						int secondMax = Math.max(Acnt.get(1).P,Bcnt.get(1).P);
						Total += (Acnt.get(0).P + secondMax);
					}
				}
				
				//둘이 충전할 수 있는 최대 충전소가 다를 때 
				else {
					Total += (Acnt.get(0).P+Bcnt.get(0).P);		
				}				
			}
			
			Acnt.clear();
			Bcnt.clear();
		}
		
		System.out.println("#"+n+" "+Total);
		}
	}
	
	
	//각 BC마다 접속가능한 영역을 표시해준다 
	public static void getBCArea() {
		
		for(int i = 0; i < A; i++) {
			BC nowBC = BCInfo[i];
			bfs(nowBC.x,nowBC.y,i,nowBC.C);
			
		}
	}
	
	public static void bfs(int a, int b, int num, int capa) {
		Deque<Node4> dq = new ArrayDeque<>();
		boolean[][] visited = new boolean[10][10];
		visited[a][b] = true;
		map[a][b][num] = 1;
		
		Node4 node = new Node4(a,b);
		dq.add(node);
		int distance = 1;
		
		while(!dq.isEmpty() && distance <= capa) {
			int size = dq.size();
			for(int i = 0; i < size;i++) {		
				Node4 nowNode = dq.poll();
				int x = nowNode.x;
				int y = nowNode.y;
				
				for(int j = 1; j < 5; j++) {
					int nx = x+dx[j];
					int ny = y+dy[j];
					
					if(nx<0||nx>=10||ny<0||ny>=10||visited[nx][ny]==true)continue;
					
					Node4 nextNode = new Node4(nx,ny);
					dq.add(nextNode);
					visited[nx][ny] = true;
					
					
					map[nx][ny][num] = 1;
				}				
			}
			distance++;
		}
	}
	
}
