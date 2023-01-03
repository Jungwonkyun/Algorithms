package swea_1974;
import java.util.*;


public class Swea_1974 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int i = 1; i < T+1; i++) {
			int[][] array = new int[9][9];
			for(int j = 0; j<9; j++) {
				for(int k = 0; k<9;k++) {
					array[j][k] = sc.nextInt();
				}
			}
			//2차원 배열 출력은 이런식으로 한다. Arrays.toString로 하면 주솟값이 나옴 
			//System.out.println(Arrays.deepToString(array)); 
			
			int flag = 1;
			//가로 검사
			for(int j = 0; j < 9; j++) {
				List<Integer> check = new ArrayList<Integer>(); 
				for(int k = 0; k < 9; k++) {
					if (check.contains(array[j][k])) {
						flag = 0;
						break;
					}
					if (flag == 0)break;
					check.add(array[j][k]);					
				}
				if (flag == 0)break;
			}
			
			//System.out.println(flag);
			if (flag == 0) {
				System.out.println(String.format("#%d %d",i,flag));
				continue;
			}
			
			//세로 검사
			for(int j = 0; j < 9; j++) {
				List<Integer> check = new ArrayList<Integer>(); 
				for(int k = 0; k < 9; k++) {
					if (check.contains(array[k][j])) {
						flag = 0;
						break;
					}
					if (flag == 0)break;
					check.add(array[k][j]);					
				}
				if (flag == 0)break;
			}
			if (flag == 0) {
				System.out.println(String.format("#%d %d",i,flag));
				continue;
			}
			
			//System.out.println(flag);
			
			//사각형 검사 
			for(int j = 0; j < 3; j++) { 
				for(int k = 0; k < 3; k++) {
					List<Integer> check = new ArrayList<Integer>();
					for(int l = 0; l < 3; l++) {
						for(int m = 0; m < 3; m++) {
							if (check.contains(array[j*3+l][k*3+m])){
								flag = 0;
								break;
							}
							check.add(array[j*3+l][k*3+m]);	
						}
						
						if (flag == 0)break;
						
					}
					if (flag == 0)break;	
				} 
				if (flag == 0)break;
			}		
			System.out.println(String.format("#%d %d",i,flag));
		
		}
	}
}
