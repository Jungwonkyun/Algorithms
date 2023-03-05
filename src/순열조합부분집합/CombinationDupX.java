package 순열조합부분집합;

public class CombinationDupX {


	static int[] Original = { 100, 200, 300, 400, 500, 600 };
	static int[] result;
	static int N = Original.length;
	static int cnt = 0;

	// 6개중에 3개를 뽑는 조합의 수 (중복x) 6C3 = 6x5x4/3x2x1 = 20
	public static void main(String[] args) {

		result = new int[3];
		combination(0, 0);
		System.out.println(cnt);

	}

	public static void combination(int idx, int depth) {

		if (depth == 3) {
			for (int num : result) {
				System.out.print(num + " ");
			}
			System.out.println();
			cnt++;
			return;
		}
		
		//중복순열은 i = 0 부터 탐색 중복조합은 idx만 매개변수로 넘겨준다 딱 그차이 
		for (int i = idx; i < N; i++) {
			result[depth] = Original[i];
			combination(i+1, depth + 1);

		}
	}

}
