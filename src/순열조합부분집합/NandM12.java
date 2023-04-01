package 순열조합부분집합;

import java.util.Arrays;
import java.util.Scanner;

public class NandM12 {
	
	static int N;
	static int M;
	static boolean visited[];
	static int[]num;
	static int[] arr;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		num = new int[N+1];
		
		for(int i = 1; i < N+1; i++) {
			num[i] = sc.nextInt();
		}
		
		Arrays.sort(num);
		
		visited = new boolean[N+1];
		arr = new int[M];
		
		permutation(1,0);
		System.out.println(sb);
	}
	
	public static void permutation(int idx, int depth) {
		
		if(depth == M) {
			for(int val:arr) {
				sb.append(val+" ");
			}sb.append("\n");
			return;
		}
		
		int before = 0;
		for(int i = idx; i <= N; i++) {
			if(before != num[i]) {	
				arr[depth] = num[i];
				before = num[i];
				permutation(i,depth+1);

			}
			
		}
	}

}
