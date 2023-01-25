package bruteforce;

import java.util.*;


public class NumberBaseball {

	static int[][] digit;
	static int [][] score;
	static int N;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		

		int[][] digit = new int[N][3];
		int[][] score = new int[N][2];

		for(int i = 0; i < N; i++) {
			
			int num = sc.nextInt();
			
			digit[i][0] = num/100;
			digit[i][1] = (num - digit[i][0]*100)/10;
			digit[i][2] = (num - digit[i][0]*100 - digit[i][1]*10);
			
			
			score[i][0] = sc.nextInt();  //strike
			score[i][1] = sc.nextInt();  //ball 
		}
		
		//이거 왜 static으로 못 알아 먹음? 
		System.out.println(compute_case(digit,score));
	
	}
	
	public static int compute_case(int[][]digit,int[][] score) {
		
		int result = 0;
		for(int i = 1; i <= 9; i++) {
			for(int j = 1; j <= 9; j++) {
				if(i != j) {
					for(int k = 1; k <= 9 ; k++) {
						//3자리 숫자가 모두 중복이 안 되는 경우만 생각 
						if(i!=k && j!=k) {
							boolean flag = true;
							for(int n = 0; n < N; n++) {
								int strike = 0;
								int ball = 0;

								//첫 번째 숫자 
								if(i == digit[n][0])strike++;
								else if(i == digit[n][1]||i == digit[n][2])ball++;
								
								//두 번째 숫자 
								if(j == digit[n][1])strike++;
								else if(j == digit[n][0]||j == digit[n][2])ball++;
								
								//세 번째 숫자 
								if(k == digit[n][2])strike++;
								else if(k == digit[n][0]||k == digit[n][1])ball++;
								
								
								//계산한 s,b 값과 실제 값이 다르면 break
								if(strike != score[n][0] || ball != score[n][1])	{
									flag = false;
									break;
								}

							}
							
							if (flag == true)result++;
						}
						
							
					}
				}
					
			}
		}
		
		return result;
		
	}
}
