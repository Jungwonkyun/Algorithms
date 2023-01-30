package gumi_five;

import java.util.Scanner;

public class HoneyHouse {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(); 
		
		int start = 1;
		int cnt = 1;
		while(start<N) {
			start += 6*cnt;
			cnt++;
		}
		
		System.out.println(cnt);
		
	}

}
