package 순열조합부분집합;

public class PermutationDupX {
	static int[] Original = { 100, 200, 300, 400, 500, 600 };
	static int[] result;
	static boolean[] visited;
	static int N = Original.length;
	static int cnt = 0;

	// 6개중에 3개를 뽑는 순열의 수 (중복 x) 6P3 = 6x5x4 = 120
	public static void main(String[] args) {

		visited = new boolean[N];
		result = new int[3];
		permutation(0);
		System.out.println(cnt);

	}

	public static void permutation(int depth) {

		if (depth == 3) {
			for (int num : result) {
				System.out.print(num + " ");
			}
			System.out.println();
			cnt++;
			return;
		}

		for (int i = 0; i < N; i++) {
			if (!visited[i]) {
				visited[i] = true;
				result[depth] = Original[i];
				permutation(depth + 1);
				visited[i] = false;
			}
		}

	}

}
