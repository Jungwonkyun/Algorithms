package SamsungSwTest;

import java.io.*;
import java.util.*;


public class BabyShark {
	
	static class fish{
		int i;
		int j;
		
		public fish(int i,int j) {
			this.i = i;
			this.j = j;
		}
	}
	
	static int [][] ocean;
	static int N;
	static int SharkSize = 2;
	static int time = 0;
	
	static ArrayList<fish>compute = new ArrayList<>();
	static int s_i,s_j;  //상어의 좌표 
	static int distance[][];
	static int di; 

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		ocean = new int[N][N];
		fish shark = null;
		
		for(int  i = 0; i < N; i++) {
			String [] input = br.readLine().split(" ");
			for(int  j = 0; j < N; j++) {
				fish f = new fish(i,j);
				int now_fish = Integer.parseInt(input[j]);
				ocean[i][j] = now_fish;
				

			    if (now_fish == 9) {
					s_i = i;
					s_j = j;
				}
			}
		}
		
		
		
		shark = new fish(s_i,s_j);
		int eat_count = 0;
		
		while(true) {
			
			compute(shark);
			if(check()==0)break;
			eatFish();
			
			
			if(SharkSize <= 7)eat_count++;
			time+=di;
			
			if(eat_count == SharkSize) {
				eat_count = 0;
				SharkSize++;
			}
			
			shark = new fish(s_i,s_j);
		}
		
		System.out.println(time);
		
	}
	
	public static void compute(fish shark) {
		
		int dx[] = {-1,1,0,0};
		int dy[] = {0,0,-1,1};
		
		Deque<fish>dq = new ArrayDeque<>();
		dq.add(shark);
		
		distance = new int [N][N];
		boolean visited[][] = new boolean[N][N];
		visited[s_i][s_j] = true;
		
		while(!dq.isEmpty()) {
			
			fish now = dq.removeFirst();
			int x = now.i;
			int y = now.j;
			
			for(int i = 0; i < 4; i++) {
				int nx = x+dx[i];
				int ny = y+dy[i];
				
				//범위를 벗어나면 넘어간다 
				if(nx >= N || nx < 0 || ny >= N|| ny < 0)continue;
				//if 방문한 곳이면 넘어간다 
				if(visited[nx][ny] == true)continue;
				//if 지나갈 수 없는 길이라면 넘어간다 
				if(ocean[nx][ny] > SharkSize)continue; 
				
				visited[nx][ny] = true;
				
				//다음에 지나갈 idx = 지금 idx + 1
				distance[nx][ny] = distance[x][y]+1;
				fish f = new fish(nx,ny);
				dq.add(f);
			}	
		}
		
	}
	
	//가장 가까운 거리에 있는 먹을 수 있는 물고기를 먹는다 
	static public void eatFish() {
		
		int eat_x = 0, eat_y = 0;
		di = Integer.MAX_VALUE; 
		
		//거리가 같으면 윗쪽에 있는 (i값이 작은) 같다면 왼쪽에 있는(j값이 작은) 물고기를 먹는다 
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				//distance =0 이거나 물고기가 없는 지역이거나 자기 크기보다 크면  continue
				if(distance[i][j]==0 || ocean[i][j]==0 ||ocean[i][j]>=SharkSize )continue;
				
				//이전에 측정된 최솟값 보다 작을 때 더 작은 거리에 있는 물고기를 먹는다 
				if(distance[i][j]<di) {
					eat_x = i;
					eat_y = j;
					di = distance[i][j];
				}
				
				//distance가 같을 떄는 조건을 만들 필요가 없다 왜냐면 for문 자체가 열을 늘리고 행을 늘리면서 보기 때문에 조건과 동일 
				
			}
		}		
		
		if(di == Integer.MIN_VALUE)return;
		
		
		ocean[s_i][s_j] = 0;
		ocean[eat_x][eat_y] = 9;
		s_i = eat_x;
		s_j = eat_y;
	}
	
	static public int check() {
		
		int cnt = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(ocean[i][j] < SharkSize && ocean[i][j] != 0 && distance[i][j]!=0) {
					cnt++;
				}
			}		
		}
		
		return cnt;
	}
	
}
