package 순열조합부분집합;


//depth 별로 딱히 할 일없으면 매개변수 하나로도 가능 
public class PowerSet {

	static int[] Original = { 100, 200, 300, 400, 500, 600 };
	static int[] result;
	static boolean[] visited;
	static int N = Original.length;
	static int cnt = 0;

	// 6개 원소를 가지는 집합의 부분집합 2^6 = 128
	public static void main(String[] args) {
		visited = new boolean[N];
		powerSet(0);
		System.out.println(cnt); //공집합 제외 127나오면 정상 

	}

	public static void powerSet(int depth) {
		cnt++;
		
		if(depth == N) {
			for(int i = 0; i < N; i++) {
				if(visited[i]==true) {
					System.out.print(Original[i]+" ");
				}
			}
			System.out.println();
			return;
		}
		
		//부분집합은 for문 쓰지 않는다 쓰면 중복해서 나옴 
		//지금 원소를 포함할 때 
		visited[depth] = true;
		
		//중복 안 되게 idx 늘려준다
		powerSet(depth+1);

		//지금 원소를 포함 안 할 때 
		visited[depth] = false;
		//똑같이 중복 안 되게 idx는 늘려준다 
		powerSet(depth+1);

	}

}
