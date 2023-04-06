package algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class GlobalWarming {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in = br.readLine().split(" ");
		int R = Integer.parseInt(in[0]);
		int C = Integer.parseInt(in[1]);
		char[][] map = new char[R][C];
		char[][] copyMap = new char[R][C];
		int dx[] = {-1,1,0,0};
		int dy[] = {0,0,-1,1};
		
		
		for(int i = 0; i < R; i++) {
			String in2 = br.readLine();
			for(int j = 0; j < C; j++) {
				map[i][j] = in2.charAt(j);
			}
		}
		
		for(int i = 0; i < R; i++) {
			copyMap[i] = Arrays.copyOf(map[i], C);
		}
		
		
		for(int i = 0; i < R; i ++) {
			for(int j = 0; j < C; j++) {
				int cnt = 0;
				if(map[i][j] == 'X') {
					for(int k = 0; k < 4; k++) {
						int ni = i + dx[k];
						int nj = j + dy[k];
						//범위를 벗어나도 바다로 친다 
						if(ni<0||ni>=R||nj<0||nj>=C) {
							cnt++;
							continue;
						}
						
						if(map[ni][nj] == '.')cnt++;
					}
				}
				if(cnt>=3) {
					copyMap[i][j] = '.';
					
				}
			}
		}
		
		
		boolean flag = false;
		
		int maxX = -1;
		int minX = -1;
		int maxY = -1;
		int minY = -1;
		
		map = copyMap;
		for(int i = 0; i < R; i ++) {
			for(int j = 0; j < C; j++) {
				if(map[i][j] == 'X') {
					
					if(maxX == -1) {
						maxX = i;
						minX = i;
						maxY = j;
						minY = j;			
					}
					maxX = Math.max(maxX,i);
					minX = Math.min(minX,i);
					maxY = Math.max(maxY,j);
					minY = Math.min(minY,j);
				}
			}
		}
		
		for(int i = minX; i < maxX+1; i ++) {
			for(int j = minY; j < maxY+1; j++) {
					System.out.print(map[i][j]);
			}System.out.println();
		}
	}
		
		
}


