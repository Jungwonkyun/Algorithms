package algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class LongestBitonicSeries {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int [] series = new int[N];
		int [] result = new int[N];
		int [][]DP = new int[2][N];
		Arrays.fill(DP[0],1);
		Arrays.fill(DP[1],1);
		String [] in = br.readLine().split(" ");
		
		for(int i = 0; i < N; i++) {
			series[i] = Integer.parseInt(in[i]);
		}
		
		for(int i = 1; i < N; i++) {
			for(int j = 0; j<i; j++) {
				if(series[j]<series[i]&&DP[0][j]>=DP[0][i]) {
					DP[0][i] = DP[0][j]+1;
				}
			}
		}
		
		for(int i = N-2; i >= 0; i--) {
			for(int j = N-1; i<j; j--) {
				if(series[j]<series[i]&&DP[1][j]>=DP[1][i]) {
					DP[1][i] = DP[1][j]+1;
				}
			}
		}
		
		for(int i = 0; i < N; i++) {
			result[i] = DP[0][i]+DP[1][i]-1;
			
		}
		
		int rs = 1;
		for(int i = 0; i < N; i++) {
			rs = Math.max(rs,result[i]);
		}
		
		System.out.println(rs);
	}

}
