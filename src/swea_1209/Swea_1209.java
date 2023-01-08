package swea_1209;

import java.util.*;


public class Swea_1209 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for (int i = 0; i < 10; i++) {
			int a = sc.nextInt();
			int[][] input_array = new int[100][100];
			for (int j = 0; j < 100; j++) {
				for (int k = 0; k < 100; k++) {
					input_array[j][k] = sc.nextInt();
				}
			}
			
			int maximum = -10000;
			
			//가로 확인 
			for (int j = 0; j < 100; j++) {
				int temp = 0;
				for (int k = 0; k < 100; k++) {
					temp += input_array[j][k]; 
				}
				maximum = Math.max(maximum,temp);
			}
			
			//세로 확인 
			for (int j = 0; j < 100; j++) {
				int temp = 0;
				for (int k = 0; k < 100; k++) {
					temp += input_array[k][j]; 
				}
				maximum = Math.max(maximum,temp);
			}
			
			//대각선 확인
			int temp = 0;
			for (int j = 0; j < 100; j++) {
				temp += input_array[j][j]; 
			}
			maximum = Math.max(maximum,temp);
			
			int temp2 = 0;
			for (int j = 99; j > -1; j--) {
				temp2 += input_array[99-j][j]; 
			}
			maximum = Math.max(maximum,temp2);
			
			System.out.println(String.format("#%d %d",i+1,maximum));
			
		}
		
	}

}
