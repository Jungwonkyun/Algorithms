package algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ProtectFilm {
	static String[] in;
	static int D, W, K;
	static int[][] film;
	static int[][] copy;
	static int[] num;
	static int result;
	static boolean[] visited;
	static int min;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine().trim());

		for (int t = 1; t < TC + 1; t++) {
			in = br.readLine().trim().split(" ");
			D = Integer.parseInt(in[0]);
			W = Integer.parseInt(in[1]);
			K = Integer.parseInt(in[2]);
			result = Integer.MAX_VALUE;
			min = D;
			
			film = new int[D][W];
			visited = new boolean[D];
			for (int i = 0; i < D; i++) {
				in = br.readLine().trim().split(" ");
				for (int j = 0; j < W; j++) {
					film[i][j] = Integer.parseInt(in[j]);
				}
			}
			
			copy = new int [D][W];
			
			for(int i = 0; i < D; i++) {
				copy[i] = Arrays.copyOf(film[i], W);
			}
			
			inject(0,0);
			
			System.out.println("#" + t + " " + result);

		}

	}

	public static boolean isPossible() {

		for (int i = 0; i < W; i++) {
			int cnt = 1;
			int prev = -1;
			boolean flag = false;
			for (int j = 0; j < D; j++) {
				if (copy[j][i] == prev) {
					cnt++;
				} else {
					prev = copy[j][i];
					cnt = 1;
				}
				if (cnt == K) {
					flag = true;
					break;
				}
			}

			if (flag == false)
				return false;
		}
		
		return true;
	}

	
	public static void inject(int layer,int depth) {
		
		if(depth>K)return;
		
		//layer의 수만큼 돌리고 확인하기 
		if(layer == D) {
			if(isPossible()) {
				result = Math.min(result, depth);
			}
			return;
		}
		
		//첨가하지 않고 돌리기 
		inject(layer+1,depth);
			
		for(int i = 0; i < W; i++) {
			copy[layer][i] = 1;
		}
		
		inject(layer+1,depth+1);

		for(int i = 0; i < W; i++) {
			copy[layer][i] = 0;
		}
		
		inject(layer+1,depth+1);
		
		copy[layer] = Arrays.copyOf(film[layer], W);
	}
	
	
}

