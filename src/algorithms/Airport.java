package algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Airport {
	
	static int G;
	static int P;
	static int [] parent;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		int G = Integer.parseInt(br.readLine());
		int P = Integer.parseInt(br.readLine());
		
		parent = new int[G+1];
		
		for(int i = 1; i < G+1; i++) {
			parent[i] = i;
		}
		
		int cnt = 0;  
		
		for(int i = 0; i< P; i++) {
			int idx = Integer.parseInt(br.readLine());
			
			//현재 비행기가 도킹할 수 있는 게이트 찾기 
			int canDock = find(idx);
			
			//1번 게이트에 연결할 때 빼고 이전 게이트와 현재 게이트를 union 해준다
			if(find(canDock)!=0) {
				union(canDock,canDock-1);
				cnt++;
			}
			//만약 게이트 0을 가리키고 있다면 도킹할 수 없는 비행기
			else {
				break;
			}
		}
		System.out.println(cnt);
		
	}
	
	
	public static void union(int a, int b) {
		
		int x = parent[a];
		int y = parent[b];
		
		if(x<y) {
			parent[b] = x;
		}else{
			parent[a] = y;
		}
		
	}
	
	public static int find(int a) {
		
		if(a == parent[a])return a;
		return parent[a] = find(parent[a]);
		
	}
}