package swea_1204;
import java.util.*;

public class Swea_1204 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		
		for(int i = 1; i < tc+1; i++) {
			int a = sc.nextInt();
			int maximum = 1;
			Map<Integer,Integer> score = new HashMap<Integer,Integer>();
			for (int j = 0; j < 1000; j++) {
				int temp = sc.nextInt();
				if(score.get(temp)!=null) {
					score.replace(temp,score.get(temp)+1);
				}else {
					score.put(temp,1);
				}
				
				maximum = Math.max(score.get(temp),maximum);
			}
			
			//Map 순회하는 방법 
			int result = -1;
			List<Integer> arr = new ArrayList<Integer>(); 
			for (Map.Entry<Integer, Integer> entry : score.entrySet()) {
				int now = entry.getKey();
				if (score.get(now)==maximum) {
					arr.add(now);
				}
			}
			
			result = Collections.max(arr);
			
			System.out.println(String.format("#%d %d",i,result));
			
		}

	}

}
