package algorithms;

/*
 * 전형적인 부분 집합 문제
 * 부분집합 구해서 조건만 따져주면 되는 문제이다
 * 주의할점 depth가 끝까지 왔을 때 한 번만 확인해줘야한다 
 * 아니면 중복이 생김 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PrepareCamp {
	static int N,L,R,X;
	static int[] prob;
	static int result;
	static boolean[] visited;
	static int cnt = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		prob = new int [N];
		visited = new boolean[N];
		
		String [] in = br.readLine().split(" ");
		for(int i = 0; i < N; i++) {
			prob[i] = Integer.parseInt(in[i]);
		}
		
		powerSet(0);
		
		System.out.println(result);
	}
	
	public static void powerSet(int depth) {
		
		//2문제 이상일 때
		if(depth==N) {
			cnt++;
			int min = Integer.MAX_VALUE;
			int max = Integer.MIN_VALUE;
			int sum = 0;
			for(int i = 0; i < N; i++) {
				if(!visited[i])continue;
				min = Math.min(min,prob[i]);
				max = Math.max(max,prob[i]);
				sum+=prob[i];
			}

			if(sum>=L&&sum<=R&&max-min>=X) {
				result++;
			}			
			if(sum > R || depth == N)return;
		}
		
		//이번 원소가 포함되는 경우
		visited[depth] = true;
		powerSet(depth+1);
		
		//이번 원소가 포함되지 않는 경우
		visited[depth] = false;
		powerSet(depth+1);
	}

}
