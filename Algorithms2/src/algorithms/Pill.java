package algorithms;

/*
 * 알약 0개 dp[0] = 1
 * 알약 1개 dp[1] = 1
 * 알약 2개 dp[2] = 2
 * 
 * 작은 알약은 경우의 수가 생기지 않음 
 * 큰거 2개 작은 거 1개가 남음 
 * 
 * 작은 알약 -> 큰 알약 -> 큰 알약 
 * 작은 알약을 먹고나면 나머지 큰알약들을 먹는 수와 동일 
 * dp[0]*dp[2] = 2
 * 
 * 큰 알약 먹고 나면 dp[1] 작은 알약 2개 큰 알약 1개 dp[1]가 남음 
 * 큰 알약 -> 작은 알약 -> 큰 알약 
 * dp[1]*dp[1] = 1
 * 
 * 앞에 큰 알약 2개 먹는 거는 dp[2]
 * 큰 알약 -> 큰 알약 -> 작은 알약
 * dp[2]*dp[0] = 2

 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Pill {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		long [] dp = new long [31];
		dp[0] = 1;
		dp[1] = 1;
		dp[2] = 2;
		
		for(int i = 3; i < 31; i++) {
			for(int j = 0; j < i; j++) {
				dp[i] += dp[j]*dp[i-j-1];
			}
		}
		
		while(true) {
			int N = Integer.parseInt(br.readLine());
			if(N==0) {
				bw.flush();
				bw.close();
				System.exit(0);
			}
			bw.write(dp[N]+"\n");
		}	
	}
}