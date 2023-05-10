package algorithms;

/*
 * 1. 용액을 정렬 N이 최대 100000이므로 NlogN으로 가능 
 * 2. 양쪽 끝에서 시작 음수이면 앞에서 땡겨오기 양수이면 뒤에서 밀기 => 최솟값 갱신
 * 3. 0이되면 출력하고 끝내기
 * 4. left_idx == right_idx이면 종료
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class TwoSolution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int [] solution = new int[N];
		int sol1= 0;
		int sol2= 0;
		int diff = Integer.MAX_VALUE;
		
		String [] in = br.readLine().split(" ");
		
		for(int i = 0; i < N; i++) {
			solution[i] = Integer.parseInt(in[i]);
		}

		//배열정렬
		Arrays.sort(solution);
		
		int left = 0;
		int right = N-1;
		
		//범위를 넘어가지 않는 선에서 
		while(left < right) {
			int sum = solution[left]+solution[right]; 
			if(Math.abs(sum)<diff) {
				sol1 = solution[left];
				sol2 = solution[right];
				diff = Math.abs(sum);
			}
			
			if(sum==0) {
				System.out.println(sol1+" "+sol2);
				System.exit(0);
			}
			//음수이면 앞에서 밀어준다
			else if(sum<0) {
				left++;
			}
			//양수이면 뒤에서 민다
			else {
				right--;
			}
		}
		
		System.out.println(sol1+" "+sol2);
	}
}
