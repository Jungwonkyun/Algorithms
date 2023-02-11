package algorithms;

import java.io.*;
import java.util.*;

public class Alphabet {
	
	static String alpha[][];
	static int R;
	static int C;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static String result = "";
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R =  Integer.parseInt(st.nextToken());
		C =  Integer.parseInt(st.nextToken());
		alpha = new String[R][C];
		
		for(int i = 0; i < R; i++) {
			String input = br.readLine();
			for(int j = 0; j < C; j++) {
				alpha[i][j] = Character.toString(input.charAt(j)) ;
			}	
		}
		
		
		String str = ""+alpha[0][0]; 
		boolean visited[][] = new boolean[R][C];
		DFS(0,str,visited,0,0);	
		
		System.out.println(result.length());
	}
	
	public static void DFS(int depth, String str,boolean[][] visit, int i, int j) {
		
		int x = i;
		int y = j; 
		
		if(str.length()>result.length())result = str;
		
		for(int n = 0; n < 4; n++) {
			int nx = x+dx[n];
			int ny = y+dy[n];
			
			//범위 넘으면 continue
			if(nx>=R||nx<0||ny>=C||ny<0)continue;
			//이미 방문했거나 완성 문자에 포함되어있으면 continue
			if(visit[nx][ny] == true || str.contains(alpha[nx][ny]) == true)continue;
			
			//아니라면 
			visit[nx][ny] = true;
			DFS(depth+1,str+alpha[nx][ny],visit,nx,ny);
			visit[nx][ny] = false;
			
		}
		
	}

}
