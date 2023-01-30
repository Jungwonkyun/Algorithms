package gumi_five;

import java.util.ArrayList;
import java.util.Scanner;

public class Sugar {
	
	ArrayList<Integer> ary5 = new ArrayList<Integer>();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int sugar = sc.nextInt();
		
		int num = sugar/5; 
		int result = 0;
		boolean flag = false;
		
		for(int i = num; i >-1; i-- ) {
			
			int temp = sugar - i*5;
			if(temp%3==0) {
				result += i;
				result += temp/3;
				flag = true;
				break;
			}
		}
		
		if(flag == true)System.out.println(result);
		else System.out.println(-1);
		
	}

}