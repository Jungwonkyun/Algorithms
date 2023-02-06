package SamsungSwTest;

import java.io.*;
import java.util.*;
public class Easy2048 {
	
	static int N;
	static int max = Integer.MIN_VALUE;
	static int[][] game;
	static int[][] copy;
	static int[] mode = {1,2,3,4}; //east,west,south,north
	static Deque<Integer> dq = new ArrayDeque<>();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		game = new int[N][N];
		copy = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				game[i][j] = Integer.parseInt(st.nextToken());	
			}	
		}
		
//		for(int i = 0; i < N; i++) {
//			for(int j = 0; j < N; j++) {
//				System.out.printf("%d ",game[i][j]);
//			}	
//			System.out.println();
//		}
		
		
		start(0,game);
		System.out.println(max);
//		System.out.println();
//		System.out.println("-------------------");
//		System.out.println();
//		
//		for(int i = 0; i < N; i++) {
//			for(int j = 0; j < N; j++) {
//				System.out.printf("%d ",game[i][j]);
//			}	
//			System.out.println();
//		}
		
	}
	
	public static void start(int depth, int[][]play) {
		if(depth == 5) {
			
//			for(int i = 0; i < N; i++) {
//				for(int j = 0; j < N; j++) {
//					System.out.printf("%d ",play[i][j]);
//				}	
//			System.out.println();
//			}
//			System.out.println();
//			System.out.println();
			
			for(int i = 0; i< N; i++) {
				for(int j = 0; j < N; j++) {
					if(play[i][j]>max) {
						max = play[i][j];
						
					}
				}
			}	
			return;
		}
		
		for(int dir : mode) {
			switch(dir) {
				//east
				case 1:
					for(int i = 0; i < N; i++) {
						//일단 그 행에 대한 배열 초기화 -> 한 쪽으로 밀기 위해서 
						for(int j = N-1; j>=0 ; j--) {
							if(play[i][j]!=0) {
								//일단 queue에 넣고 
								dq.add(play[i][j]);
								//원래 있던 값은 초기화 
								//play[i][j] = 0;
								copy[i][j] = 0;
							}
						}
						//동쪽 끝에서부터 하나씩 추가 if 같은게 있다면 더해준다 & 한 번 합치고 나면 flag = false로 대입 
						boolean flag = true;
						int idx = N-1;
						int temp = -1;
						while(!dq.isEmpty()) {
							//System.out.println(dq);
							int now = dq.removeFirst();
							//만약 합쳐질 수 있고 이전 값과 지금 값이 같을 경우 합쳐준다 
							if(flag==true && temp == now) {
								//play[i][idx+1] *= 2;
								copy[i][idx+1] *= 2;
								temp = -1;
							}
							//합쳐질 수 없는 case (같거나 다르거나 상관 x )
							else {
								//play[i][idx] = now;
								copy[i][idx] = now;
								temp = now;
								flag = true;
								idx--;
							}
						}
					}
					break;
				
				//west	
				case 2:
					for(int i = 0; i < N; i++) {
						//일단 그 행에 대한 배열 초기화 -> 한 쪽으로 밀기 위해서 
						for(int j = 0; j< N ; j++) {
							if(play[i][j]!=0) {
								//일단 queue에 넣고 
								dq.add(play[i][j]);
								//원래 있던 값은 초기화 
								//play[i][j] = 0;
								copy[i][j] = 0;
							}
						}
						//동쪽 끝에서부터 하나씩 추가 if 같은게 있다면 더해준다 & 한 번 합치고 나면 flag = false로 대입 
						boolean flag = true;
						int idx = 0;
						int temp = -1;
						while(!dq.isEmpty()) {
							//System.out.println(dq);
							int now = dq.removeFirst();
							//만약 합쳐질 수 있고 이전 값과 지금 값이 같을 경우 합쳐준다 
							if(flag==true && temp == now) {
								//play[i][idx-1] *= 2; 
								copy[i][idx-1] *= 2; 
								flag = false;
							}
							//합쳐질 수 없는 case (같거나 다르거나 상관 x )
							else {
								//play[i][idx] = now;
								copy[i][idx] = now;
								temp = now;
								flag = true;
								idx++;
							}
						}
					}
					
					break;
				
				//south
				case 3:
					for(int i = 0; i < N; i++) {
						//일단 그 행에 대한 배열 초기화 -> 한 쪽으로 밀기 위해서 
						for(int j = N-1; j>=0 ; j--) {
							if(play[j][i]!=0) {
								//일단 queue에 넣고 
								dq.add(play[j][i]);
								//원래 있던 값은 초기화 
								//play[j][i] = 0;
								copy[j][i] = 0;
							}
						}
						//동쪽 끝에서부터 하나씩 추가 if 같은게 있다면 더해준다 & 한 번 합치고 나면 flag = false로 대입 
						boolean flag = true;
						int idx = N-1;
						int temp = -1;
						while(!dq.isEmpty()) {
							//System.out.println(dq);
							int now = dq.removeFirst();
							//만약 합쳐질 수 있고 이전 값과 지금 값이 같을 경우 합쳐준다 
							if(flag==true && temp == now) {
								//play[idx+1][i] *= 2; 
								copy[idx+1][i] *= 2; 
								flag = false;
							}
							//합쳐질 수 없는 case (같거나 다르거나 상관 x )
							else {
								//play[idx][i] = now;
								copy[idx][i] = now;
								temp = now;
								flag = true;
								idx--;
							}
						}
					}
					
					break;
				
				//north
				case 4:
					for(int i = 0; i < N; i++) {
						//일단 그 행에 대한 배열 초기화 -> 한 쪽으로 밀기 위해서 
						for(int j = 0; j< N ; j++) {
							if(play[j][i]!=0) {
								//일단 queue에 넣고 
								dq.add(play[j][i]);
								//원래 있던 값은 초기화 
								//play[j][i] = 0;
								copy[j][i] = 0;
							}
						}
						//동쪽 끝에서부터 하나씩 추가 if 같은게 있다면 더해준다 & 한 번 합치고 나면 flag = false로 대입 
						boolean flag = true;
						int idx = 0;
						int temp = -1;
						while(!dq.isEmpty()) {
							//System.out.println(dq);
							int now = dq.removeFirst();
							//만약 합쳐질 수 있고 이전 값과 지금 값이 같을 경우 합쳐준다 
							if(flag==true && temp == now) {
								//play[idx-1][i] *= 2;
								copy[idx-1][i] *= 2; 
								flag = false;
							}
							//합쳐질 수 없는 case (같거나 다르거나 상관 x )
							else {
								//play[idx][i] = now;
								copy[idx][i] = now;
								temp = now;
								flag = true;
								idx++;
							}
						}
					}
					break;	
			}
			
			
//			for(int n = 0; n < N; n++) {
//				for(int k = 0;  k< N; k++){
//					if(play[n][k] != copy[n][k]) {
//						start(depth+1,copy);
//					}
//					if(n==N-1 && k==N-1) {
//						start(5,copy);
//					}
//				}
//			}	
			start(depth+1,copy);
		}
		
	}

}
