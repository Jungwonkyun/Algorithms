package algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class PinBall {
	static int N;
	static int[][]map;
	static String[] in;
	static int startX, startY;
	static int[] dx = {0,0,1,-1};
	static int[] dy = {-1,1,0,0};
	static HashMap<Integer,Integer>block1;
	static HashMap<Integer,Integer>block2;
	static HashMap<Integer,Integer>block3;
	static HashMap<Integer,Integer>block4;
	static HashMap<Integer,Integer>block5;
	static int Result;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine().trim());
		
		makeBlockMap();
		
		for(int t = 1; t < TC+1; t++) {
			N = Integer.parseInt(br.readLine().trim());
			map = new int[N][N];
			Result = Integer.MIN_VALUE;
			for(int i = 0; i< N; i++) {
				in = br.readLine().trim().split(" ");
				for(int j = 0; j < N; j++) {
					int next = Integer.parseInt(in[j]);
					map[i][j] = next;			
				}
			}
			
			for(int i = 0; i< N; i++) {
				for(int j = 0; j < N; j++) {
					if(map[i][j]==0) {
						//동서남북 방향 모두로 돌려본다 
						Result = Math.max(Result,GameStart(i,j,0));
						Result = Math.max(Result,GameStart(i,j,1));
						Result = Math.max(Result,GameStart(i,j,2));
						Result = Math.max(Result,GameStart(i,j,3));
					}
				}
			}
			
			System.out.println("#"+t+" "+Result);
		}
	}
	
	public static int GameStart(int x, int y, int d) {

		int score = 0;
		int nx = x;
		int ny = y;
		int dir = d;
		
		while(true) {
			nx = nx+dx[dir];
			ny = ny+dy[dir];
			//벽에 부딪혔을 때 
			if(nx<0||nx>=N||ny<0||ny>=N) {		
				//방향 바꿔주기 
				if(dir == 0)dir = 1;
				else if(dir == 1)dir = 0;
				else if(dir == 2)dir = 3;
				else dir = 2;
				
				score++;
				continue;
			}
			
			//블랙홀을 만나거나 출발지로 돌아오면 리턴 
			else if(map[nx][ny] == -1||(nx==x && ny==y)) {
				return score;
			}
			
			//빈칸이 아니고 블록에 부딪혔을 때
			else if(map[nx][ny] != 0 && map[nx][ny]<=5) {
//				System.out.println("Bumped into Block");
				int BlockNum = map[nx][ny];
				
				//방향 바꿔주기 
				if(BlockNum == 1) {
					dir = block1.get(dir);
				}else if(BlockNum == 2) {
					dir = block2.get(dir);
				}else if(BlockNum == 3) {
					dir = block3.get(dir);
				}else if(BlockNum == 4) {
					dir = block4.get(dir);
				}else {
					dir = block5.get(dir);
				}	
				score++;
				continue;
			}
			
			//웜홀을 만나면 
			else if(map[nx][ny] != 0 && map[nx][ny]>5){
				int BlockNum = map[nx][ny];
				search:
				for(int i = 0; i < N; i++) {
					for(int j = 0; j < N; j++) {
						if(map[i][j] == BlockNum && (i!=nx || j!=ny)) {
							nx = i;
							ny = j;
							break search;
						}
					}
				}
			}
		}
	}
	
	public static void makeBlockMap() {
		
		block1 = new HashMap<>();
		block2 = new HashMap<>();
		block3 = new HashMap<>();
		block4 = new HashMap<>();
		block5 = new HashMap<>();
		
		block1.put(2, 1);
		block1.put(0, 3);
		block1.put(1, 0);
		block1.put(3, 2);
		
		block2.put(0, 2);
		block2.put(3, 1);
		block2.put(1, 0);
		block2.put(2, 3);
		
		block3.put(1, 2);
		block3.put(3, 0);
		block3.put(0, 1);
		block3.put(2, 3);
		
		block4.put(2, 0);
		block4.put(1, 3);
		block4.put(0, 1);
		block4.put(3, 2);
		
		block5.put(0, 1);
		block5.put(1, 0);
		block5.put(2, 3);
		block5.put(3, 2);
		
	}
	

}
