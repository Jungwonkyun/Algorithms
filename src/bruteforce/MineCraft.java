package bruteforce;

import java.io.*;

public class MineCraft {
	
	static int[][]map;
	static int N;
	static int M;
	static int B;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String []input = br.readLine().split(" "); 

		int block_sum = 0; // 맵에 있는 모든 블록의 갯수 
		int min = Integer.MAX_VALUE; // 맵에 있는 블록의 가장 낮은 높이 
		int max = Integer.MIN_VALUE; // 맵에 있는 블록의 가장 높은 높이 
		int result_time = Integer.MAX_VALUE; //정답 시간 
		int result_ground = 0; //정답 땅 
		N = Integer.parseInt(input[0]); //row
		M = Integer.parseInt(input[1]); //col
		B = Integer.parseInt(input[2]); //block
		map = new int[N][M]; //맵 배열 초기화 
		
		
		//input process
		for(int i = 0; i < N; i++) {
			String []temp = br.readLine().split(" ");
			for(int j = 0; j < M; j++) {
				int now = Integer.parseInt(temp[j]);
				map[i][j] = now;
				min = Math.min(min,now);
				max = Math.max(max,now);
			}
		}
		
		// 최적값은 가장 작은 블록과 가장 많은 블록사이에서 나온다 
		for(int i = min; i <max+1; i++) {
			int tm = time_compute(i);
			if(tm <=result_time) {
				result_time = tm;
				result_ground = i;
				if(result_time == 0)break; 
			} 	
		}
		
		System.out.println(result_time+" "+result_ground);
			
	}
	
	public static int time_compute(int height) {
		
		int plus = 0; 
		int minus = 0; 
		int time = 0;
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				int diff = map[i][j] - height;
				if(diff < 0)plus+=(-diff); //지금 블록 높이가 예상 높이보다 낮을 경우 블록을 쌓아야한다 
				else if(diff > 0)minus+=diff; //지금 블록 높이가 예상 높이보다 높을 경우 블록을 제거해야한다 
			}
		}
		
		//쓸 수 있는 블록 갯수보다 더 많은 블록을 쌓을 수는 없기 때문에 break
		if(minus+B < plus)return Integer.MAX_VALUE;
		
		
		//시간 계산  
		time += minus*2; 
		time += plus;
		
		return time;
	}

}
