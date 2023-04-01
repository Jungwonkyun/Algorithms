package swea_2063;
import java.util.Scanner;
import java.util.Arrays;

public class Swea_2063 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		int[] arr = new int[T];
		
		for(int i = 0; i < T; i++) {
			arr[i] = sc.nextInt();
		}
		
		Arrays.sort(arr);
		System.out.println(arr[arr.length/2]);
	
	}

}
