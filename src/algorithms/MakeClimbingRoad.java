package algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class MakeClimbingRoad {
	static String[] in;
	static int N;
	static int K;
	static int[][] road;
	static int[][] newRoad;
	static boolean[][] visited;
	static int [] dx = {0,0,1,-1};
	static int [] dy = {-1,1,0,0};
	static int result;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		
		for(int t = 1; t < TC+1; t++) {
			in = br.readLine().split(" ");
			N = Integer.parseInt(in[0]);
			K = Integer.parseInt(in[1]);
			result = -1;
			
			road = new int[N][N];
			int max = Integer.MIN_VALUE;
			
			for(int i = 0; i<N; i++) {
				in = br.readLine().split(" ");
				for(int j = 0; j < N; j++) {
					int num = Integer.parseInt(in[j]);
					road[i][j] = num;
					max = Math.max(max,num);
				}
			}
			
			//최댓값을 가진 봉우리에서 시작한다 
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(road[i][j] == max) {
						//시작부터 4개 방향에 대해서 등산로 체크 
						for(int d = 0; d < 4; d++) {
							int nx = i + dx[d];
							int ny = j + dy[d];
							
							//범위 벗어나면 x
							if(nx<0||nx>=N||ny<0||ny>=N) continue;
							
							newRoad = new int[N][N];
							
							for(int k = 0; i < N; i++) {
								newRoad[k] = Arrays.copyOf(road[k], N);
							}
						
							visited = new boolean[N][N];
							makeRoad(i,j,0,K);
							
						}
					}
				}
				
			}
			
			System.out.println(result);
				
		}
	}
	
	public static void makeRoad(int x, int y, int depth, int capa) {
		
		if(!possibleRoad(x,y,capa)) {
			result = Math.max(result,depth);
			return;
		}
		
		int prev = newRoad[x][y];
		
		for(int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			//범위 벗어나거나 이전 값보다 높이가 높은데 땅을 다 깎아도 못 지나가는 경우에 continue
			if(nx<0||nx>=N||ny<0||ny>=N||visited[nx][ny]||newRoad[nx][ny]-capa>=prev)continue;
			
			//땅을 안 깎아도 갈 수 있는 경우 
			if(newRoad[nx][ny]<prev) {
				makeRoad(nx, ny, depth+1, capa);
			}
			
			//땅을 깎아야만 지나갈 수 있는 경우 prev - 1까지만 깎자(일단 최소비용을 들여서 지나갈 수 있도록 한다 )
			else {
				newRoad[nx][ny] = prev-1;
				int C = newRoad[nx][ny] - (prev-1);
				makeRoad(nx, ny, depth+1, C);
			}
		
			
		}
	}
	
	
	//사방탐색 해서 갈 수 있는 길이 있는지 확인 
	public static boolean possibleRoad(int x, int y, int capa) {
		
		int cnt = 0;
		//4방향 다 확인했는데 어디로도 못 가는 경우에 
		for(int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			System.out.println("1: "+nx+ " " + ny);
			if(nx<0||nx>=N||ny<0||ny>=N)continue;
			System.out.println("2: "+nx+ " " + ny);
			System.out.println("2: "+x+ " " + y);
			if(newRoad[nx][ny] - capa < newRoad[x][y])cnt++;

		}
		
		if(cnt == 0)return false;
		return true;
	}

}
