package bruteforce;

import java.util.*;

public class PrimePellindrom {

	static int N;
	static int[] prime = new int[1003002];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		initialize_prime();
		
		
		for(int i = N; i<1003002; i++) {
			//숫자가 홀수일 때만 재귀를 돌아준다 
			if(prime[i] != 0) {
				if( Pellindrome(Integer.toString(i))) {
					System.out.println(i);
					break;
				}
			}
		}	
		return; 
	}
	
	//에라토스테네스의 채 
	public static void initialize_prime() {
		for(int i = 2; i < 1003002; i++) {
			prime[i] = i;
		}
		
		for(int i = 2; i < 1003002; i++) {
			if(prime[i] == 0)continue;
			for(int j = 2*i; j < 1003002; j+=i) {
				prime[j] = 0;
			}
		}
	}
	
	public static boolean Pellindrome(String num) {
		int temp = num.length()/2;
		for(int i = 0; i<temp; i++) {
			if(num.charAt(i)!=num.charAt(num.length()-1-i))return false;
		}
		return true;
	}

}
