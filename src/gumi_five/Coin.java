package gumi_five;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Coin {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String []in = br.readLine().split(" ");
		int N = Integer.parseInt(in[0]);
		int K = Integer.parseInt(in[1]); 
		int result = 0;
		int []input_coin = new int[N];
		
		for(int i = N-1; i>-1; i--) {
			input_coin[i] = Integer.parseInt(br.readLine());
		}
		
		for(int i = 0; i<N; i++) {
			int now = input_coin[i];
			if(now<=K) {
				int temp = K/now;
				result += temp;
				K -= temp*now;
			}
			
			if(K==0)break;
		}
		System.out.println(result);

	}

}