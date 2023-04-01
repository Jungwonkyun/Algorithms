package SamsungSwTest;

import java.io.*;

public class ControlLadder {
	
	static int[][] Ladder;
	static int[][] CopyLadder;
	static int N,M,H;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String in [] = br.readLine().split(" ");
		N = Integer.parseInt(in[0]);
		M = Integer.parseInt(in[1]);
		H = Integer.parseInt(in[2]);
		
		Ladder = new int[H][N];
		
		for(int i = 0; i < M; i++) {
			String input [] = br.readLine().split(" ");
			int a = Integer.parseInt(input[0])-1;
			int b = Integer.parseInt(input[1])-1;
			
			Ladder[a][b] = 1;
		}
			
		for(int i = 0; i <= 3; i++) {
			makeLadder(0,0,i);
		}
		
		System.out.println(-1);

	}
	
	public static void makeLadder(int Xidx,int depth, int NumOfLadder) {
		
		if(depth == NumOfLadder) {
			if(FallDown() == true) {
				System.out.println(depth);
				System.exit(0);

			}
			
			return;
			
		}
		
		
		for(int i = Xidx; i < H; i++) {
			for(int j = 0; j < N-1; j++) {
				//지금 사다리가 놓여있지 않고 옆에 사다리가 없는 경우에만 사다리를 놓는다 
				if(Ladder[i][j]!=1 && Ladder[i][j+1] == 0) {
					if(j>=1 && Ladder[i][j-1]==1)continue;
					//사다리를 놓고 
					Ladder[i][j] = 1;
					makeLadder(i,depth+1,NumOfLadder);
					//다시 돌려놓기 
					Ladder[i][j] = 0;
				}
			}
		}
		
	}
	
	public static boolean FallDown() {
		
		//맨 처음 자기 사다리의 첫번째 인덱스 부터 시작 start = Ladder[0][0] Ladder[0][2].... 			
			for(int i = 0; i < N; i++) {
				int X = 0;
				int Y = i;
			  	boolean [][] visited = new boolean [H][N+1];
				
				while(X<H) {
					if(Ladder[X][Y]==1 && visited[X][Y+1]!=true) {
						visited[X][Y] = true;
						Y++;
					}
					
					//왼쪽 탐색 if 사다리가 있으면 
					else if(Y>=1 &&Ladder[X][Y-1]==1 && visited[X][Y-1]!=true) {
						visited[X][Y] = true;
						Y--;
						
					}
					else {
						X++;
					}
				}

				if(Y != i)return false;
			}
		return true;
	}
	
}
