package sw_practice;

//10726. 이진수 표현 비트 연산자는 아주 유용한 무기, 생활화 하자

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Binary_present {
	static StringBuffer sb = new StringBuffer();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		
		for(int t = 1; t < TC+1; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			int binary = (int)(Math.pow(2, N))-1;
			int result = binary & M;

			
			if(result == binary)sb.append("#"+t+" ON\n");
			else sb.append("#"+t+" OFF\n");
		}
		System.out.println(sb);
	}
}



//public class Binary_present {
//	static StringBuffer sb = new StringBuffer();
//	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		int TC = Integer.parseInt(br.readLine());
//		
//		loop:
//		for(int t = 1; t < TC+1; t++) {
//			StringTokenizer st = new StringTokenizer(br.readLine());
//			int N = Integer.parseInt(st.nextToken());
//			int M = Integer.parseInt(st.nextToken());
//			
//			String binary = Integer.toBinaryString(M);
//			if(binary.length() > N)binary = binary.substring(binary.length()-N, binary.length());
//			else if(binary.length() < N) {
//				sb.append("#"+t+" OFF\n");
//				continue loop;
//			}
//			
//			for(int i = 0; i < binary.length(); i++) {
//				if(binary.charAt(i)=='0') {
//					sb.append("#"+t+" OFF\n");
//					continue loop;
//				}
//			}
//			sb.append("#"+t+" ON\n");
//		}
//		System.out.println(sb);
//	}
//}
