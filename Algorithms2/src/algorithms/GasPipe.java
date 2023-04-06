package algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GasPipe {
	
	static String[] input;
	static int R;
	static int C;
	static char[][] map;
	static int[] dx = {-1,1,0,0};  
	static int[] dy = {0,0,-1,1};
	static int bx = -1;
	static int by = -1;
	static boolean[][] visited;
	static boolean flag = true;
	static int dir1;   //1,2,3,4 상,하,좌,우 
	static int dir2;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		input = br.readLine().split(" ");
		R = Integer.parseInt(input[0]);
		C = Integer.parseInt(input[1]);
		map = new char[R][C];
		visited = new boolean [R][C];
		char result = '0';
		
		int Mx = 0;
		int My = 0;
		
	
		for(int i = 0; i < R; i++) {
			String in = br.readLine();
			for(int j = 0; j < C; j++) {
				map[i][j] = in.charAt(j); 
				if(in.charAt(j)=='M') {
					Mx = i;
					My = j;
				}
			}
		}

		int nMx = 0;
		int nMy = 0;
		
		for(int i = 0; i<4; i++) {
			nMx = Mx + dx[i];
			nMy = My + dy[i];
			if(nMx<0||nMx>=R||nMy<0||nMy>=C||map[nMx][nMy] == '.'||map[nMx][nMy] == 'M'||map[nMx][nMy] == 'Z')continue;
			visited[Mx][My] = true;
			findBreak(Mx,My,nMx,nMy);
			break;
		}
		
		
		int tx;
		int ty;
		int[] cnt = new int[4];
		int c = 0;
		
		for(int i = 0; i<4; i++) {
			tx = bx+dx[i];
			ty = by+dy[i];
			
			if(tx<0||tx>=R||ty<0||ty>=C||map[tx][ty]=='M'||map[tx][ty]=='Z'||map[tx][ty]=='.')continue;
			
			//안되는 케이스는 빼줘야한다 
			if(i==0) {
				if(map[tx][ty] == '2' ||map[tx][ty] == '3'||map[tx][ty] == '-')continue;
			}
			else if(i==1) {
				if(map[tx][ty] == '1' ||map[tx][ty] == '4'||map[tx][ty] == '-')continue;
			}
			else if(i==2) {
				if(map[tx][ty] == '3' ||map[tx][ty] == '4'||map[tx][ty] == '|')continue;
			}
			else if(i==3){
				if(map[tx][ty] == '1' ||map[tx][ty] == '2'||map[tx][ty] == '|')continue;
			}
			
			cnt[i]++;
			c++;
			
		}
		
		if(c == 4) {
			result = '+';
		}else {
			if(cnt[0] == 1 && cnt[1] == 1)result = '|';
			else if(cnt[0] == 1 && cnt[2] == 1)result = '3';
			else if(cnt[0] == 1 && cnt[3] == 1)result = '2';
			else if(cnt[1] == 1 && cnt[2] == 1)result = '4';
			else if(cnt[1] == 1 && cnt[3] == 1)result = '1';
			else if(cnt[2] == 1 && cnt[3] == 1)result = '-';
		}
		
		
		System.out.println((bx+1)+" "+(by+1)+" "+Character.toString(result));

	}

	public static boolean findBreak(int prevx, int prevy, int nowx, int nowy) {
		//다음 칸이 방문한적이 있다면 가스관이 연결 되었다는 의미 
		if(visited[nowx][nowy] == true)return true;
		
		visited[nowx][nowy]= true;
		
		//가스관을 타고 가다가 끊어진 칸을 만나면 브레이크포인트 
		if(map[nowx][nowy] == '.') {
			visited[nowx][nowy] = false;
			if(bx == -1 && by == -1) {
				bx = nowx;
				by = nowy;
				return false;
			}
		}
		
		int mode = 0;
		
		if(map[nowx][nowy]=='1')mode = 1;
		else if(map[nowx][nowy]=='2')mode = 2;
		else if(map[nowx][nowy]=='3')mode = 3;
		else if(map[nowx][nowy]=='4')mode = 4;
		else if(map[nowx][nowy]=='+')mode = 5;
		else if(map[nowx][nowy]=='-')mode = 6;
		else if(map[nowx][nowy]=='|')mode = 7;
		
		switch (mode) {
		case 1:
			//from right bottom
			if(nowy - prevy == -1) {
				flag = findBreak(nowx,nowy,nowx+1,nowy);
			}
				
			//from bottom to right
			else {
				flag = findBreak(nowx,nowy,nowx,nowy+1);
				
				
			}
			break;
			
		case 2:
			//from up right
			if(nowx - prevx == 1) {
				flag = findBreak(nowx,nowy,nowx,nowy+1);
			}
				
			//from right to up
			else {
				flag = findBreak(nowx,nowy,nowx-1,nowy);
			}
			break;
			
		case 3:
			//from up to left 
			if(nowx - prevx == 1) {
				flag = findBreak(nowx,nowy,nowx,nowy-1);
			}
				
			//from left to up
			else {
				flag = findBreak(nowx,nowy,nowx-1,nowy);
			}
			break;
			
		case 4:
			//from bottom to left
			if(nowx - prevx == -1) {
				flag = findBreak(nowx,nowy,nowx,nowy-1);
			}
			//from left to bottom
			else {
				flag = findBreak(nowx,nowy,nowx+1,nowy);
			}
			
			break;
		case 5:
			//from up
			if(nowx - prevx == 1) {
				//to left
				flag = findBreak(nowx,nowy,nowx,nowy-1);
				//to right
				flag = findBreak(nowx,nowy,nowx,nowy+1);
				//to bottom
				flag = findBreak(nowx,nowy,nowx+1,nowy);
			}
			//from bottom
			else if(nowx - prevx == -1){
				//to left
				flag = findBreak(nowx,nowy,nowx,nowy-1);
				//to right
				flag = findBreak(nowx,nowy,nowx,nowy+1);
				//to down
				flag = findBreak(nowx,nowy,nowx-1,nowy);		 
			}
			//from right
			else if(nowy - prevy == -1){
				//to left
				flag = findBreak(nowx,nowy,nowx,nowy-1);
				//to bottom
				flag = findBreak(nowx,nowy,nowx+1,nowy);
				//to up
				flag = findBreak(nowx,nowy,nowx-1,nowy);
			}
			//from left
			else {
				//to right
				flag = findBreak(nowx,nowy,nowx,nowy+1);
				//to up
				flag = findBreak(nowx,nowy,nowx-1,nowy);
				//to bottom
				flag = findBreak(nowx,nowy,nowx+1,nowy);
			}
			
			break;
		case 6:
			//from right
			if(nowy - prevy == -1) {
				//to left
				flag = findBreak(nowx,nowy,nowx,nowy-1);
			}
			else {
				//to left
				flag = findBreak(nowx,nowy,nowx,nowy+1);
			}
			break;
		
		case 7:
			//from up
			if(nowx-prevx==1) {
				//to bottom
				flag = findBreak(nowx,nowy,nowx+1,nowy);
			}
			//from bottom
			else {
				//to up
				flag = findBreak(nowx,nowy,nowx-1,nowy);
			}
			break;
		
			
		default:
			break;
		}
		
		if(flag == false)return false;
		
		return true;
		
	}

}
