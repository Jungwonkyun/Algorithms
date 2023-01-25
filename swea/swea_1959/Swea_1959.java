package swea_1959;
import java.util.*;

public class Swea_1959 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		for(int i = 1; i < tc+1; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			List<Integer> arr1 = new ArrayList<Integer>(); 
			List<Integer> arr2 = new ArrayList<Integer>(); 
			
			for(int j = 0; j < a; j++) {
				arr1.add(sc.nextInt());
			}
			for(int j = 0; j < b; j++) {
				arr2.add(sc.nextInt());
			}
			
			int maximum = -10000000;
			boolean flag = true;
			//b가 더 길 거나 같을 때 
			if (a<=b) {
				while(arr1.size()<=arr2.size()) {
					int temp = 0;
					for(int j = 0; j<arr1.size(); j++) {
						temp += arr1.get(j)*arr2.get(j);
					}
					maximum = Math.max(maximum,temp);
					arr1.add(0,0);
				}
			}
			else {
				while(arr1.size()>=arr2.size()) {
					int temp = 0;
					for(int j = 0; j<arr2.size(); j++) {
						temp += arr1.get(j)*arr2.get(j);
					}
					maximum = Math.max(maximum,temp);
					arr2.add(0,0);
				}
				
			}
			System.out.println(String.format("#%d %d",i,maximum));	
		}
		
	}

}
