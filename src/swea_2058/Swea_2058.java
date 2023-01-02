package swea_2058;
import java.util.Scanner;
import java.lang.Integer;

public class Swea_2058 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String now_string = sc.next();
		int sum = 0;
		for(int i = 0; i < now_string.length(); i++) {
			char temp = now_string.charAt(i);
			int myInt = Character.getNumericValue(temp);
			sum += myInt;
		}
		System.out.println(sum);
	}

}
