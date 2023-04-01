package algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Lie {
	static String[] in;
	static int N, M, total = 0;
	static boolean[] truePeople = new boolean[51];
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 사람 수
		M = Integer.parseInt(st.nextToken()); // 파티 수

		parent = new int[N + 1];

		for (int i = 1; i < N + 1; i++) {
			parent[i] = i;
		}

		// 2. 진실을 아는 사람 정보 받아오기 truePeople[진실을아는사람] == true
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		for (int i = 0; i < n; i++) {
			truePeople[Integer.parseInt(st.nextToken())] = true;
		}

		// 3. 파티 정보를 받아오면서 같은 파티에 있는 사람들 union
		ArrayList<Integer>[] peoples = new ArrayList[M];
		for (int i = 0; i < M; i++) {
			peoples[i] = new ArrayList<>();
		}
		
		int value, pre = 0;
		
		for (int i = 0; i < M; i++) {
			
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			
			if (n > 0) {
				pre = Integer.parseInt(st.nextToken());
				peoples[i].add(pre);
			}
			
			for (int j = 1; j < n; j++) {
				value = Integer.parseInt(st.nextToken());
				peoples[i].add(value);
				union(pre, value); // 두명씩 union하면 모두가 같은 parent를 갖게 됨.
				pre = value;
			}
		}

		// 4. 진실을 아는 사람들의 parent는 같이 파티를 참여 했으므로 진실을 아는 사람들
		int parent;
		for (int i = 1; i < truePeople.length; i++) {
			if (truePeople[i]) {
				truePeople[find(i)] = true;
			}
		}

		// 5. 진실을 아는 사람들과 파티를 같이 하지 않았으면 total++
		for (int i = 0; i < M; i++) {
			if (peoples[i].size() > 0) {
				parent = find(peoples[i].get(0));
				if (!truePeople[parent])
					total++;
			}
		}

		// 6. 거짓말 할 수 있는 파티 최대 수 출력
		System.out.println(total);
	}

	public static void union(int a, int b) {

		// 각각의 부모노들 찾는다
		int x = find(a);
		int y = find(b);

		// 더 작은 노드로 합쳐준다 (부모노드를 합쳐줘야함)
		if (x < y) {
			parent[x] = y;
		}

		else if (x > y) {
			parent[y] = x;
		}
	}

	// a와 b의 부모가 같은지 확인
	public static boolean equals(int a, int b) {
		int x = find(a);
		int y = find(b);
		return x == y;
	}

	// 자기자신이 부모노드이면 리턴 아니면 재귀로 부모노드 찾을 때까지 재귀로 찾는다
	public static int find(int a) {
		if (parent[a] == a)
			return a;

		return parent[a] = find(parent[a]);
	}
}
