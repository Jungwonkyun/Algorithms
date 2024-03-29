package algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class PartSum {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		long[] arr = new long[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<n; i++) {
			arr[i] = Long.parseLong(st.nextToken());
		}
		
		int left =0;
		int right = n-1;
		
		int lf =0 , rt = 0;
		long min = Long.MAX_VALUE;
		
		while(left<right) {
			long sum = arr[left]+arr[right];
			
			if(min > Math.abs(sum)) {
				min = Math.abs(sum);
				lf = left; rt = right;
			}
			if(sum>=0) {
				right--;	
			}else {
				left++;
			}
		}
		System.out.println(arr[lf] +" "+arr[rt]);
	}
}