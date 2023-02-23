package SamsungSwTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;


public class MovePipeDFS {
	static int N;
	static int[][] Room;
	static int[] dx = { 0, 1, 1 };
	static int[] dy = { 1, 1, 0 };
	static int result = 0;
	static ArrayList<Integer>Mode[]; 
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		Room = new int[N][N];


		for (int i = 0; i < N; i++) {
			String in[] = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				Room[i][j] = Integer.parseInt(in[j]);
			}
		}
		
		Mode = new ArrayList[3];
		
		for(int i = 0; i < 3; i++) {
			Mode[i] = new ArrayList<Integer>();
		}
		
		//초기화 
		Mode[0].add(0);
		Mode[0].add(1);
		
		Mode[1].add(0);
		Mode[1].add(1);
		Mode[1].add(2);
		
		Mode[2].add(1);
		Mode[2].add(2);
		
		DFS(0,1,0);
		DFS(0,1,1);
		
		System.out.println(result);
	}
	
	public static void DFS(int x, int y, int mode) {
	
		int nx = x + dx[mode];
		int ny = y + dy[mode];
		
		
		if(nx<0||nx>=N||ny<0||ny>=N||Room[nx][ny]==1)return;
		

		for(int i = 0; i < Mode[mode].size(); i++) {
			if(mode == 1) {
				if(Room[x+1][y] == 1 ||Room[x][y+1]==1)return;
			}
			
			if(nx == N-1 && ny == N-1) {
				result++;
				return;
			}
			
			DFS(nx,ny,Mode[mode].get(i));
		}
		
	}
	
}
