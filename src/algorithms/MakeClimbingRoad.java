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
					//System.out.println(i+" "+j);
					if(road[i][j] == max) {
						newRoad = new int[N][N];
						for(int k = 0; k < N; k++) {
							newRoad[k] = Arrays.copyOf(road[k], N);
						}
						visited = new boolean[N][N];
						visited[i][j] = true;
						makeRoad(i,j,1,false);	
						visited[i][j] = false;
					}
				}
			}		
			System.out.println("#"+t+" "+(result));
				
		}
	}
	
	public static void makeRoad(int x, int y, int depth, boolean check) {

		result = Math.max(result,depth);
		
		if(!possibleRoad(x,y,check)) {
			return;
		}
		
		int prev = newRoad[x][y];
		
		for(int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			//범위 벗어나거나 이전 값보다 높이가 높은데 땅을 다 깎아도 못 지나가는 경우에 continue
			if(nx<0||nx>=N||ny<0||ny>=N||visited[nx][ny]||newRoad[nx][ny]-K>=prev)continue;
			
			//땅을 안 깎아도 갈 수 있는 경우 
			if(newRoad[nx][ny]<prev) {
				visited[nx][ny] = true;
				makeRoad(nx, ny, depth+1, check);
				visited[nx][ny] = false;
			}
			
			//땅을 깎아야만 지나갈 수 있는 경우 prev - 1까지만 깎자(일단 최소비용을 들여서 지나갈 수 있도록 한다 )
			else {
				if(prev>0 && check == false) {
					int temp = newRoad[nx][ny];
					newRoad[nx][ny] = prev-1;
					visited[nx][ny] = true;
					makeRoad(nx, ny, depth+1, true);
					visited[nx][ny] = false;
					newRoad[nx][ny] = temp;
				}else continue;
			}
		}
	}
	
	
	//사방탐색 해서 갈 수 있는 길이 있는지 확인 
	public static boolean possibleRoad(int x, int y, boolean check) {
		int cnt = 0;
		//4방향 다 확인했는데 어디로도 못 가는 경우에 
		for(int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if(nx<0||nx>=N||ny<0||ny>=N)continue;
			
			//다음 갈 길을 깎아야 하면 한 번도 깎은 적이 없고 가능한 모든 땅을 깎았을 때 통과 가능해야한다 
			if(newRoad[nx][ny] >= newRoad[x][y]) {
				if(newRoad[x][y]>0 && check == false && newRoad[nx][ny] - K < newRoad[x][y])cnt++;
			}
		
			else {
				cnt++;
			}
		}
		
		if(cnt == 0)return false;
		return true;
	}

}
