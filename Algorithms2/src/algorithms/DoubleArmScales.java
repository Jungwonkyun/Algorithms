package algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class DoubleArmScales {

	static int N;
	static int M;
	static Set<Integer> chooSum;
	static int[] choo;
	static int[] marble;
	static boolean[] possible;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		choo = new int[N + 1];
		chooSum = new HashSet<>();

		int sum = 0;
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i < N + 1; i++) {
			int now = Integer.parseInt(st.nextToken());
			choo[i] = now;
			sum += now;

		}
	
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		marble = new int[M];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			marble[i] = Integer.parseInt(st.nextToken());
		}

	}
}