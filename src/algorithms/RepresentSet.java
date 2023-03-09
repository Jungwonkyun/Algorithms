package algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RepresentSet {

	static String[] in;
	static int[] parent;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		in = br.readLine().split(" ");
		int N = Integer.parseInt(in[0]);
		int M = Integer.parseInt(in[1]);
		StringBuilder sb = new StringBuilder();
		
		parent = new int[N + 1];

		for (int i = 0; i < N + 1; i++) {
			parent[i] = i;
		}

		for (int i = 0; i < M; i++) {
			in = br.readLine().split(" ");
			int check = Integer.parseInt(in[0]);
			int first = Integer.parseInt(in[1]);
			int second = Integer.parseInt(in[2]);
		
			// 얘는 union
			if (check == 0) {
				if(!equals(first,second)) {
					union(first,second);
				}
			}

			// 얘는 find
			else {
				if(equals(first,second)) {
					sb.append("YES").append('\n');
				}
				else sb.append("NO").append('\n');
			}
		}
		
		System.out.println(sb);
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
