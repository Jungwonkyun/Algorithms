package algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LCS {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str1 = br.readLine();
		String str2 = br.readLine();
		int length1 = str1.length();
		int length2 = str2.length();
		
		int[][] DP = new int [length1+1][length2+1];
		
		for(int i = 1; i < length1+1; i++) {
			for(int j = 1; j < length2+1; j++) {
				//해당 알파벳이 LCS에 포함되면 
				if(str1.charAt(i-1)==str2.charAt(j-1)) {
					DP[i][j] = DP[i-1][j-1]+1;
				}else {
					DP[i][j] = Math.max(DP[i-1][j],DP[i][j-1]);	
				}
			}
		}
		
		System.out.println(DP[length1][length2]);
	}
}
