package gumi_five;

import java.util.*;

public class Snail {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int A = sc.nextInt();
		int B = sc.nextInt();
		int V = sc.nextInt();
		int diff = A - B;
		V = V-A;
		if(V==0)System.out.println(1);
		else{
			int res = (int) Math.ceil((double)V/diff);
			System.out.println(res+1);
		}
	}

}
