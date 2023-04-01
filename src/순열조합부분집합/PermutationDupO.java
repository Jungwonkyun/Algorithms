package 순열조합부분집합;

public class PermutationDupO {
	static int [] Original = {100,200,300,400,500,600};
	static int[] result;
	static int N = Original.length;
	static int cnt = 0;
	//6개중에 3개를 뽑는 순열의 수 (중복 o) 6Pi3 = 6x6x6 = 216
	public static void main(String[] args) {
		
		result = new int[3];
		permutation(0);
		System.out.println(cnt);

	}
	
	public static void permutation(int depth) {
		
		if(depth == 3) {
			for(int num : result) {
				System.out.print(num+" ");
			}System.out.println();
			cnt++;
			return;
		}
		
		for(int i = 0; i < N; i++) {
			result[depth] = Original[i];
			permutation(depth+1);
		}
	}
}
