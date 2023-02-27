package algorithms;

import java.util.Arrays;
import java.util.Scanner;

public class MakeMaze {
	//South, West, North, East
	static int[] dx = {1,0,-1,0};
	static int[] dy = {0,-1,0,1};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int len = sc.nextInt();
		
		String move = sc.next(); 
		StringBuilder sb = new StringBuilder();
		String[][] result = new String[101][101];
		
		for(int i = 0; i < 101; i++) {
			Arrays.fill(result[i],"#");
		}
		
		int x = 50;
		int y = 50;
		
		int dir = 0;
		int maxX = 50;
		int minX = 50;
		int maxY = 50;
		int minY = 50;
		result[x][y] = ".";
		
		for(int i = 0; i < len; i++) {
			if(move.charAt(i) == 'R') {
				dir = (dir+1)%4;
			}else if (move.charAt(i) == 'L') {
				dir = (dir+3)%4;
			}else {
				x = x+dx[dir];
				y = y+dy[dir];
			}
			result[x][y] = ".";
			maxX = Math.max(maxX,x);
			minX = Math.min(minX,x);
			maxY = Math.max(maxY,y);
			minY = Math.min(minY,y);
			
		}

	
		for(int i = minX; i < maxX+1; i++) {
			for(int j = minY; j < maxY+1; j++) {
				sb.append(result[i][j]);
			}sb.append("\n");
		}
		
		System.out.println(sb);
	}

}
