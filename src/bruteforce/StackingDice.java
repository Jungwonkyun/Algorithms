package bruteforce;

import java.io.*;
import java.util.*;
import java.util.StringTokenizer;

// O(6*6*N) = O(N)으로 풀이가능 N > 36 이상일 때 효율
public class StackingDice {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		ArrayList<Integer>[] dice = new ArrayList[N];
		
		for(int i = 0; i < N; i++) {
			dice[i] = new ArrayList<Integer>();
		}
		
		int result = Integer.MIN_VALUE;
		HashMap<Integer,Integer> dice_map = new HashMap<>();
		
		
		//주사위 input 받기 
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j<6; j++) {
				dice[i].add(Integer.parseInt(st.nextToken()));
			}
		}
		
		//마주보는 인덱스끼리 맵에 저장 
		dice_map.put(0,5);
		dice_map.put(1,3);
		dice_map.put(2,4);
		dice_map.put(5,0);
		dice_map.put(3,1);
		dice_map.put(4,2);
		
		
		//1번째 주사위 경우의 수 
		for(int i = 0; i < 6; i++) {
			int temp = 0;
			
			//1번째 주사위의 제일위에 숫자 
			int pivot = dice[0].get(i);  //1 2 3 4 5 6 한 번씩은 나온다 
			int idx = 0;
			int op_idx = 0;

			for(int j = 0; j < N; j++) {
				int max = Integer.MIN_VALUE;
				
				idx = dice[j].indexOf(pivot); //아랫쪽 주사위와 접하고 있는 값의 index를 알아온다 
	
				op_idx = dice_map.get(idx); //반대쪽 눈을 가지고 있는 index 값을 map을 통해 받아온다 
				pivot = dice[j].get(op_idx); //윗쪽 주사위의 눈을 pivot으로 넘겨준다  
			
				for(int k = 0; k < 6; k++) {
					if(k!=idx && k!= op_idx) {
						max = Math.max(max,dice[j].get(k));
					}
				}
				temp+=max;	
			}
			result = Math.max(result,temp);
		}
		
		System.out.println(result);
		
	}

}
