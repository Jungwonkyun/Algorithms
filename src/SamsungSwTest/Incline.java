package SamsungSwTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Incline {
	static int N;
	static int L;
	static int [][] Road;
	static boolean [][] visited;
	static int result = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String in [] = br.readLine().split(" ");
		N = Integer.parseInt(in[0]);
		L = Integer.parseInt(in[1]);
		
		Road = new int [N][N];
		
		
		for(int i = 0; i < N; i++) {
			String input[] = br.readLine().split(" ");
			for(int j = 0; j < N; j++) {
				Road[i][j] = Integer.parseInt(input[j]);
			}
		}
		
		putRow();
		
		int [][] temp = new int [N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j ++) {
				temp[j][N-1-i] = Road[i][j]; 
			}
		}
	
		Road = temp;
		putRow();
		System.out.println(result);
		
	}
	
	public static void putRow() {
		visited = new boolean[N][N];
		
		for(int i = 0; i < N; i++) {
			int prev = Road[i][0];
			int seq = 0;
			boolean flag = true;
			for(int j = 0; j < N; j++) {
				
				//만약에 높이 차이가 1이상이면 
				if(Math.abs(Road[i][j]-prev)>1) {
					flag = false;
					break;
				}
				
				//갈려는 길이 더 높으면 현재까지 온 길이가 L보다 긴지 확인한다 
				else if((Road[i][j]-prev)==1) {
					//L보다 길이가 작거나 반대편에 경사로가 이미 놓여있으면  경사로를 놓을 수 없다 
					if(seq < L) {
						flag = false;
						break;
					}
					
					for(int k = 1; k <= L; k++) {
						if(visited[i][j-k] == true) {
							flag = false;
							break;
						}
						visited[i][j-k] = true;
					}
					
					//새로운 높이로 왔으니 연속된 숫자 초기화 
					seq = 1;
				}
				
				//갈려는 길이 더 낮으면 다음 이후 길이가 L보다 긴지, 높이가 같은지 확인해야한다 
				else if((Road[i][j]-prev)==-1) {
					int temp_prev = Road[i][j];
					for(int k = 0; k < L; k++) {
						if(j+k>=N ||temp_prev != Road[i][j+k]||visited[i][j+k] == true) {
							flag = false;
							break;
						}
						visited[i][j+k] = true;
					}
					seq = 1;
				}
				
				
				
				//연속된 같은 높이 길이면
				else if(Road[i][j] == prev) {
					seq++;
				}
				
				if(flag == false)break;
				prev = Road[i][j];
			}
			
			//모든 조건을 통과하면 지나갈 수 있는 길이 된다 
			if(flag == true) {
				result++;
			}
		}
		
	}
	

}
