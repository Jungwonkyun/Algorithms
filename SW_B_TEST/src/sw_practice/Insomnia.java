package sw_practice;

//SWEA 1288.새로운 불면증 치료법

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Insomnia {
	static boolean[] check = new boolean [10];
	static StringBuffer sb = new StringBuffer();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		
		for(int t = 1; t < TC+1; t++) {
			int N = Integer.parseInt(br.readLine()); 
			check = new boolean[10];
			
			int k = 1;
			while(true) {
				int kn = k*N;
				
				//kn이 10보다 클 때는 자릿수를 낮춰가면서 1의 자리수 찾기 
				while(kn>=10) {
					int r = kn%10;
					check[r] = true;
					kn = kn/10;
				}
				
				//kn이 10보다 작을 때
				check[kn] = true;
				
				if(name() == true) {
					sb.append("#"+t+" "+k*N).append("\n");
					break;
				}
				k++;
			}
		}
		
		System.out.println(sb.toString());
	}
	
	public static boolean name() {
		for(int i = 0; i < 10; i++) {
			if(check[i] == false)return false;
		}
		return true;
	}

}
