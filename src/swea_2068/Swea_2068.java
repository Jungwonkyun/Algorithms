package swea_2068;
import java.util.Scanner;

public class Swea_2068 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int i = 1 ; i< T+1 ; i++) {
			int t = 10;
			int max = -1; 
			for (int j = 0; j < t; j++) {
				int temp = sc.nextInt();
				if (temp > max) {
					max = temp;
				}
			}
			System.out.println(String.format("#%d %d", i,max));
		}
	}

}
