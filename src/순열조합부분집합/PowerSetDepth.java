package 순열조합부분집합;

//만약 뎁스별로 뭔갈 하고 싶으면 기저조건 다음에 depth별로 하고 싶은 거 하면 됨 
public class PowerSetDepth {

	static int[] Original = { 100, 200, 300, 400, 500, 600 };
	static int[] result;
	static boolean[] visited;
	static int N = Original.length;
	static int cnt = 0;

	// 6개 원소를 가지는 집합의 부분집합 2^6 = 128
	public static void main(String[] args) {
		visited = new boolean[N];
		powerSet(0, 0);
		System.out.println(cnt); // 공집합 제외 127나오면 정상

	}

	public static void powerSet(int idx, int count) {
		cnt++;

		if (idx == N) {
			return;
		}

		if (count == 2) {
			for (int i = 0; i < N; i++) {
				if (visited[i] == true) {
					System.out.print(Original[i] + " ");
				}
			}
			System.out.println();
		}

		visited[idx] = true;
		powerSet(idx + 1, count + 1);

		visited[idx] = false;
		powerSet(idx + 1, count);

	}
}
