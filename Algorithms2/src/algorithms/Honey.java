package algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Honey {
	
	static int N;
	static int M;
	static int C;
	static int[][] honey;
	static String[] in;
	static int[] basket;
	static boolean[] visited;
	static int[] subset;
	static int total;
	static int Maxtotal;
	static int result;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine().trim());
		
		for(int t = 1; t < TC+1; t++) {
			in = br.readLine().trim().split(" ");
			N = Integer.parseInt(in[0]);
			M = Integer.parseInt(in[1]);
			C = Integer.parseInt(in[2]);
			
			honey = new int [N][N];
			result = Integer.MIN_VALUE;
			for(int i = 0; i<N; i++) {
				in = br.readLine().trim().split(" ");
				for(int j = 0; j < N; j++) {
					honey[i][j] = Integer.parseInt(in[j]);
				}
			}
			
			pickHoney();
			System.out.println("#"+t+" "+result);
		}
		
	}
	
	public static void pickHoney() {
		
		//같은 행에 벌꿀 통 2개가 안 들어갈 때 
		for(int i = 0; i < N-1; i++) {
			for(int j = 0; j < N-M+1; j++) {
				for(int k = i+1; k < N; k++) {
					for(int l = 0; l < N-M+1; l++) {
						int x1 = i;
						int y1 = j;
						int x2 = k;
						int y2 = l;
							
						Maxtotal = 0;
						calPrice(x1,y1,x2,y2);
						result = Math.max(Maxtotal,result);
					}
				}
			}
		}
		
		//같은 행에 벌꿀 통 2개가 들어갈 수 있을 떄 
		if(N >= 2*M)
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N-2*M; j++) {
				for(int k = j+M; k<N-M; k++) {
					int x1 = i;
					int y1 = j;
					int x2 = i;
					int y2 = k;
					Maxtotal = 0;
					calPrice(x1,y1,x2,y2);
					result = Math.max(Maxtotal,result);
				}
			}
		}
		
		return;
		
		
	}
	
	public static void calPrice(int x1,int y1, int x2, int y2) {
		
		basket = new int[M];
		visited = new boolean[M];
		total = 0;
		
		for(int i = 0 ; i < M; i++) {
			basket[i] = honey[x1][y1+i];
		}
		
		powerSet(0, 0);
		basket = new int[M];
		Maxtotal+=total;
		total = 0;
		
		
		for(int i = 0 ; i < M; i++) {
			basket[i] = honey[x2][y2+i];
		}
		powerSet(0, 0);
		Maxtotal += total;
	}
	
	public static void powerSet(int idx, int sum) {
		if(sum>C)return;
		
		else {
			int temp = 0;
			for(int i = 0; i < M; i++) {
				if(visited[i]==true) {
					temp+=basket[i]*basket[i];
				}
			}
			total = Math.max(total,temp);
			if(idx == M)return;
		}
		
		visited[idx] = true;
		powerSet(idx+1, sum + basket[idx]);
		
		visited[idx] = false;
		powerSet(idx+1, sum);
		
	}
	

}
