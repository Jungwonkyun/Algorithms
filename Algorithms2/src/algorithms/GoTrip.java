package algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GoTrip {
	static String[] in;
	static int[] parent;
	static int[] TripList;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());

		boolean flag = true;
		
		parent = new int[N + 1];
		TripList = new int[M];
		
		
		for (int i = 1; i < N + 1; i++) {
			parent[i] = i;
		}
		
		for (int i = 1; i < N+1; i++) {
			in = br.readLine().split(" ");
			for(int j = 0; j < N; j++) {
				int connected = Integer.parseInt(in[j]);
				
				//연결되어 있다면 union
				if(connected == 1) {
					union(i,j+1);
				}
				
			}
		}

		in = br.readLine().split(" ");
		for(int i = 0; i < M; i++) {
			TripList[i] = Integer.parseInt(in[i]);
		}
		
		int prevRoot = find(TripList[0]);
		
		for(int i = 1; i < M; i++) {
			if(prevRoot != find(TripList[i])) {
				flag = false;
				break;
			}
		}
		
		if(flag == true)System.out.println("YES");
		else System.out.println("NO");


	}

	public static void union(int a, int b) {
		
		//각각의 부모노들 찾는다
		int x = find(a);
		int y = find(b);
		
		//더 작은 노드로 합쳐준다 (부모노드를 합쳐줘야함)
		if(x<y) {
			parent[x] = y;
		}
		
		else if(x>y) {
			parent[y] = x;
		}
	}
	
	//a와 b의 부모가 같은지 확인 
	public static boolean equals(int a, int b) {
        int x = find(a);
        int y = find(b);
        return x == y;
    }

	//자기자신이 부모노드이면 리턴 아니면 재귀로 부모노드 찾을 때까지 재귀로 찾는다 
	public static int find(int a) {
		if (parent[a] == a)
			return a;

		return parent[a] = find(parent[a]);
	}
}