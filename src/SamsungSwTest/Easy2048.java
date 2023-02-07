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
		if(depth == 3) {
			
//			for(int i = 0; i < N; i++) {
//				for(int j = 0; j < N; j++) {
//					System.out.printf("%d ",play[i][j]);
//			}	
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
			System.out.println("Original");
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					System.out.printf("%d ",play[i][j]);
				}
				System.out.println();
			}	
			System.out.println("--------------------");
			
			
			switch(dir) {
				//east
				case 1:
					System.out.println("right");
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
						int idx = N-1;
						int temp = -1;
						while(!dq.isEmpty()) {
							//System.out.println(dq);
							int now = dq.removeFirst();
							//만약 합쳐질 수 있고 이전 값과 지금 값이 같을 경우 합쳐준다 
							if(temp == now) {
								//play[i][idx+1] *= 2;
								copy[i][idx+1] *= 2;
								temp = -1;
							}
							//합쳐질 수 없는 case (같거나 다르거나 상관 x )
							else {
								//play[i][idx] = now;
								copy[i][idx] = now;
								temp = now;
								idx--;
							}
						}
					}
					break;
				
				//west	
				case 2:
					System.out.println("left");
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
						int idx = 0;
						int temp = -1;
						while(!dq.isEmpty()) {
							//System.out.println(dq);
							int now = dq.removeFirst();
							//만약 합쳐질 수 있고 이전 값과 지금 값이 같을 경우 합쳐준다 
							if(temp == now) {
								//play[i][idx-1] *= 2; 
								copy[i][idx-1] *= 2; 
								temp = -1;
							}
							//합쳐질 수 없는 case (같거나 다르거나 상관 x )
							else {
								//play[i][idx] = now;
								copy[i][idx] = now;
								temp = now;
								idx++;
							}
						}
					}
					
					break;
				
				//south
				case 3:
					System.out.println("down");
					for(int i = 0; i < N; i++) {
						//일단 그 행에 대한 배열 초기화 -> 한 쪽으로 밀기 위해서 
						for(int j = N-1; j>=0 ; j--) {
							if(play[j][i]!=0) {
								//일단 queue에 넣고 
								dq.add(play[j][i]);
								//원래 있던 값은 초기화 
								//play[j][i] = 0;
								copy[j][i] = 0;
								System.out.println("j: "+j+" i: "+i+" "+copy[j][i]);
							}
						}
						//동쪽 끝에서부터 하나씩 추가 if 같은게 있다면 더해준다 & 한 번 합치고 나면 flag = false로 대입 
						int idx = N-1;
						int temp = -1;
						while(!dq.isEmpty()) {
							for(int a = 0; a < N; a++) {
								for(int b = 0; b < N; b++) {
									System.out.printf("%d ",copy[a][b]);
								}
								System.out.println();
							}
							System.out.println(dq);
							int now = dq.removeFirst();
							//만약 합쳐질 수 있고 이전 값과 지금 값이 같을 경우 합쳐준다 
							if(temp == now) {
								//play[idx+1][i] *= 2; 
								System.out.println("i: "+i+" idx: "+idx);
								copy[idx+1][i] *= 2; 
								System.out.println("change "+copy[idx+1][i]/2+" to "+copy[idx+1][i]);
								temp = -1;
							}
							//합쳐질 수 없는 case (같거나 다르거나 상관 x )
							else {
								//play[idx][i] = now;
								System.out.println("i: "+i+" idx: "+idx);
								
								copy[idx][i] = now;
								System.out.println("input: " + copy[idx][i]);
								temp = now;
								idx--;
							}
							for(int a = 0; a < N; a++) {
								for(int b = 0; b < N; b++) {
									System.out.printf("%d ",copy[a][b]);
								}
								System.out.println();
							}	
						}
					}
					
					break;
				
				//north
				case 4:
					System.out.println("up");
					for(int i = 0; i < N; i++) {
						//일단 그 행에 대한 배열 초기화 -> 한 쪽으로 밀기 위해서 
						for(int j = 0; j < N ; j++) {
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
							if(temp == now) {
								//play[idx-1][i] *= 2;
								copy[idx-1][i] *= 2; 
								temp = -1;
							}
							//합쳐질 수 없는 case (같거나 다르거나 상관 x )
							else {
								//play[idx][i] = now;
								copy[idx][i] = now;
								temp = now;
								idx++;
							}
					
						}
					}
					break;	
			}
			
			System.out.println("change~! depth: "+depth);
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					System.out.printf("%d ",copy[i][j]);
				}
				System.out.println();
			}	
			
			System.out.println("--------------------");
			System.out.println();
			
			
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
