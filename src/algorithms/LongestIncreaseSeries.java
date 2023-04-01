package algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class LongestIncreaseSeries {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int [] series = new int[N];
		int [] DP = new int[N+1];
		Arrays.fill(DP,1);
		
		String[] in= br.readLine().split(" ");
		
		for(int i = 0; i < N; i++) {
			series[i] = Integer.parseInt(in[i]);
		}
		
		for(int i = 1; i < N; i++) {
			for(int j = 0; j<i; j++) {
				if(series[j]<series[i]&&DP[j]>=DP[i]) {
					DP[i] = DP[j]+1;
				}
			}
		}
		
		int result = 1;
		
		for(int i = 0; i< N; i++) {
			result = Math.max(result,DP[i]);
		}
		
		System.out.println(result);
		
	}

}
