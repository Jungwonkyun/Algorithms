package AppleTree;

import java.util.Scanner;

public class AppleTree {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		// n * n 크기 배열 선언 (땅)
		int[][] map = new int[n][n];
		
		// 땅 정보 입력 받기
		for ( int i = 0 ; i < n ; i++ ) {
			for (int j = 0 ; j < n ; j++ ) {
				map[i][j] = sc.nextInt();
			}
		}
		
		// 최대값 저장
		int maxValue = 0;
		
		int dx[] = {0,0,-1,1};
		int dy[] = {-1,1,0,0};
		
		for ( int i = 0 ; i < n ; i++ ) {
			for (int j = 0 ; j < n ; j++ ) {
				int temp = map[i][j];
				for (int k = 0; k<4; k++) {
					int ni = i+dx[k];
					int nj = j+dy[k];
					if ((0<=ni&&ni<n)&&(0<=nj&&nj<n)) {
						temp += map[ni][nj];
					}
				}
				maxValue = Math.max(maxValue,temp);
				
			}
		}
	
		System.out.println(maxValue); 
	}

}
