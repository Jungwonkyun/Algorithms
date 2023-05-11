package algorithms;

/*
 * 1. 양수 음수 나누기 
 * 2. 각 부호별로 절대값이 높은 N개씩 묶기 절대값이 큰 거리만 가면 된다
 * 3. 절대값이 제일 큰 1그룹은 제일 마지막에 추가 
 * 
 * 7 2
 * -39 -37 -29  -28  -6  2  11 
 * (-39,-37) (-29,-28) (11,2), -6
 * (11+29+6)*2 + 39
 * 46*2+39 = 131
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Library {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int sum = 0;
		
		String [] in = br.readLine().split(" ");
		int [] books = new int[N];
		
		//음수 양수를 기준으로 나누고 절대값이 높은 순부터 정렬
		PriorityQueue<Integer>minus = new PriorityQueue<>((o1,o2) -> (o2-o1));
		PriorityQueue<Integer>plus = new PriorityQueue<>((o1,o2) -> (o2-o1));
		
		
		for(int i = 0; i< N; i++) {
			int Now = Integer.parseInt(in[i]);
			books[i] = Now;
			if(Now<0)minus.offer(Math.abs(Now));
			else plus.offer(Now);
		}
		
		ArrayList<Integer>total = new ArrayList<>();
		
		int cnt = 0;
		
		//양수 음수 별로 거리가 먼 것들 부터 M개 만큼 묶어서 전체 리스트에 추가 
		//여기서 묶음별 가장 큰 값 만큼만 가면 된다 -> 가는 길에 놔두면 되니까
		while(!minus.isEmpty()) {
			int temp = minus.poll();
			if(cnt == 0)total.add(temp);
			cnt++;
			if(cnt == M)cnt = 0; 
		}
		
		cnt = 0;
		while(!plus.isEmpty()) {
			int temp = plus.poll();
			if(cnt == 0)total.add(temp);
			cnt++;
			if(cnt == M)cnt = 0; 
		}
		
		Collections.sort(total);
		
		//가장 먼 책을 제외하고는 책을 다시 가지러 와야함
		for(int i = 0; i < total.size()-1; i++) {
			sum += 2*total.get(i);
		}
		
		//가장 거리가 먼 책은 1번만 더하고
		sum += total.get(total.size()-1);
		
		System.out.println(sum);
	}

}
