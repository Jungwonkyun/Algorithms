package swea_1936;
import java.util.Scanner;
import java.util.Arrays;

public class Swea_1936 {

	public static void main(String[] args) {
		//공백 기준으로 입력받아서 리스트로 저장하는 방법 
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		String[] sp_str = str.split(" ");
		Arrays.toString(sp_str);
		
		//possible combination: (1,2) (2,3),(1,3) 
		if(sp_str[0]=="1" && sp_str[0]== "3" || sp_str[0]=="3" && sp_str[0]== "1") {
			if (sp_str[0]=="1") {
				System.out.println("B");
			}else System.out.println("A");
			
		}
		
		else {
			int a = Integer.parseInt(sp_str[0]);
			int b = Integer.parseInt(sp_str[1]);
			
			if (a<b)System.out.println("B");
			else System.out.println("A");
			
		}
		
 	}

}
