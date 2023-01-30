package good;

import java.util.*; 
import java.io.*;

public class ATM {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String [] ary = br.readLine().split(" ");
		int [] input = new int[N];
		
		for(int i = 0; i<N; i++) {
			input[i] = Integer.parseInt(ary[i]);
		}
		
		Arrays.sort(input);
		
		int sum = 0;
		for(int i = 0; i<N; i++) {
			sum+=input[i]*(N-i);
		}
		
		System.out.println(sum);
	}

}
