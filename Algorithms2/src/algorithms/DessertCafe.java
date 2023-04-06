package algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class DessertCafe {
	static int N;
	static int[][] cafe; 
	static boolean [][]visited;
	static String[] in;
	static int startX;
	static int startY;
	static int result;
	static int[] dx = {1,1,-1,-1};   //방향: 우하 -> 좌하 -> 좌상 -> 우상
	static int[] dy = {1,-1,-1,1};

	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC =  Integer.parseInt(br.readLine().trim());
		
		for(int t = 1; t < TC+1; t++) {
			N =  Integer.parseInt(br.readLine().trim());
			result = -1;
			cafe = new int[N][N];
			
			for(int i = 0; i < N; i++) {
				in = br.readLine().trim().split(" ");
				for(int j = 0; j < N; j++) {
					cafe[i][j] = Integer.parseInt(in[j]);
				}
			}
		

			for(int i = 0; i < N-2; i++) {
				for(int j = 1; j< N-1; j++) {
					visited = new boolean[N][N];
					HashSet<Integer>cafeSet = new HashSet<>();
					visited[i][j] = true;
					cafeSet.add(cafe[i][j]);
					startX = i;
					startY = j;
					eatDessert(i, j, 1, 0,cafeSet);
				}
			}
			
			System.out.println("#"+t+" "+result);
		
		}
	}
	
	public static int eatDessert(int x, int y, int cnt, int prevD, HashSet<Integer> cafeSet ) {
		
		//동일한 루트인데 방향만 다른 경우는 같은 경우니까 방향성 순서를 지정해줘야 한다 
		for(int i = prevD; i < 4; i++) {
			int nx = x+dx[i];
			int ny = y+dy[i];
			
			//범위 벗어났을 경우
			if(nx<0||nx>=N||ny<0||ny>=N)continue;
			
			//시작지점으로 돌아왔을 때 최대값 구하기 최소 3번은 탐색하고 돌아왔을 때 
			if(nx == startX && ny == startY && cafeSet.size() >= 3) {
				result = Math.max(result, cnt);
			}
						
			//다음 루트가 방문한 적이 없고, 해당 카페 번호도 처음일 때만
			if(!visited[nx][ny] && !cafeSet.contains(cafe[nx][ny])) {
				cafeSet.add(cafe[nx][ny]);
				visited[nx][ny] = true;
				eatDessert(nx, ny, cnt+1, i, cafeSet);
				visited[nx][ny] = false;
				cafeSet.remove(cafe[nx][ny]);
			}
		}

		return 0;
	}
	
}
