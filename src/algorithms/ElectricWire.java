package algorithms;

import java.util.Arrays;
import java.util.Scanner;

class point implements Comparable<point>{
	
	int a;
	int b;
	
	public point(int x, int y) {
		a = x;
		b = y;
	}

	@Override
	public int compareTo(point o) {
		
		if(o.a == this.a) {
			return this.b - o.b; 
		}
		
		return this.a - o.a;
	}	
	
}


public class ElectricWire {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int result = 1;
		point [] elecwire = new point[N];
		int [] DP = new int[N+1];
		Arrays.fill(DP,1);
		
		
		for(int i = 0; i < N; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			
			point p = new point(x,y);
			elecwire[i] = p;
		}
		
		Arrays.sort(elecwire);
		
		int [] Seq = new int[N]; 
		
		for(int i = 0; i <N; i++) {
			Seq[i] = elecwire[i].b;
		}
		
		for(int i = 1; i < N; i++) {
			for(int j = 0; j<i; j++) {
				if(Seq[j]<Seq[i]&&DP[j]>=DP[i]) {
					DP[i] = DP[j]+1;
				}
			}
		}
		
		for(int i = 0; i< N; i++) {
			result = Math.max(result,DP[i]);
		}
		
		System.out.println(N-result);
	}
}
