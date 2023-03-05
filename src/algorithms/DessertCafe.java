package algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class DessertCafe {
	
	static int N;
	static int[][] cafe; 
	static String[] in;
	static int d1;
	static int d2;
	static int result;
	
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
		
			loop2:
			for(int i = 0; i < N-2; i++) {
				for(int j = 1; j< N-1; j++) {
					for(int k = 1; k < N-1; k++) {
						for(int l = 1; l< N-1; l++) {
							d1 = k;
							d2 = l;
							
							int type = eatDessert(i,j);
							System.out.println("x: "+i +" y: "+j+" d1: "+d1+" d2: "+d2+" type: "+type);
							//if(i==0 && j == 4 && d1 == 2 && d2 ==6)System.out.println("----------------------------"+type);
							
							if(i==1)break loop2;
							
							//d1을 줄여야 하니까 더 이상 가능한 d1,d2조합이 나오지 않는다 
							//if(type==1)
							
							//d1을 늘렸을 때 가능한 조합이 나올 수 있는 경우 
							//else if(type>=2)break;
									
						}
					}	
				}
			}
			
			System.out.println("#"+t+" "+result);
		
		}

	}


	
	public static int eatDessert(int x, int y) {
		
		//if(!(x==0 && y == 4 && d1 == 2 &&d2 ==6))return 0;
		
		int totalCnt = 0;
		Set<Integer> dessertSet = new HashSet<>();
		//from north to west
		for(int i = 1; i < d1; i++) {
			System.out.println("north -> west "+(x+i)+" "+(y-i));
			
			//범위를 벗어나면 리턴 -> d1을 줄여야 한다 
			if(x+i>=N||y-i<0)return 1;
			int nowDessert = cafe[x+i][y-i];
				
			//만약 이미 포함한 디저트 가게라면 리턴 
			if(dessertSet.contains(nowDessert))return 1;
				
			//디저트 카페 리스트에 넣어주고 카운트에 더해주기 
			dessertSet.add(nowDessert);
			totalCnt++;
				
		}
		
		
		//from west to south
		for(int i = 1; i < d2; i++) {
			System.out.println("west -> south "+(x+d1+i)+" "+(y-d1+i)+" "+i);
			//범위를 벗어나면 리턴 -> d2을 줄여야 한다 
			if(x+d1+i>=N||x+d1+i<0||y-d1+i>=N||y-d1+i<0)return 2;
						
			int nowDessert = cafe[x+d1+i][y-d1+i];
			//만약 이미 포함한 디저트 가게라면 리턴 
			if(dessertSet.contains(nowDessert))return 2;
						
			//디저트 카페 리스트에 넣어주고 카운트에 더해주기 
			dessertSet.add(nowDessert);
			totalCnt++;

		}
		
		
		//from north to East
		for(int i = 1; i < d2; i++) {
			System.out.println("north -> east "+(x+i)+" "+(y+i)+" "+i);
			//범위를 벗어나면 리턴 -> d2을 줄여야 한다 
			if(x+i>=N||y+i>=N)return 3;
			int nowDessert = cafe[x+i][y+i];
			
			//만약 이미 포함한 디저트 가게라면 리턴 
			if(dessertSet.contains(nowDessert))return 3;
				
			//디저트 카페 리스트에 넣어주고 카운트에 더해주기 
			dessertSet.add(nowDessert);
			totalCnt++;
						
		}
		
		//from East to South
		for(int i = 1; i < d1; i++) {
			System.out.println("East -> south "+(x+d2+i)+" "+(y+d2-i));
			//범위를 벗어나면 리턴 -> d1을 줄여야 한다 
			if(x+i+d2>=N||y+d2-i<0||x+i+d2<0||y+d2-i>=N)return 4;
			int nowDessert = cafe[x+d2+i][y+d2-i];
								
			//만약 이미 포함한 디저트 가게라면 리턴 
			if(dessertSet.contains(nowDessert))return 4;
								
			//디저트 카페 리스트에 넣어주고 카운트에 더해주기 
			dessertSet.add(nowDessert);
			totalCnt++;
						
		}
		
		result = Math.max(totalCnt,result);
		
		return 0;
	}
	
}
