package algorithms;

import java.io.*;
import java.util.Stack;
public class RightBigNum {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		int N = Integer.parseInt(br.readLine()); 
		int [] Number = new int[N];
		int [] Result = new int[N];
		
		String[] input = br.readLine().split(" ");
		
		for(int i = 0; i < N; i++) {
			Number[i] = Integer.parseInt(input[i]);
		}
		
		Stack<Integer> st = new Stack<>(); 
		
		for (int i = 0; i<N; i++) {
		
			while(!st.empty()&&Number[st.peek()]<Number[i]) {
				//지금 들어갈 수가 오큰수일 때
				Number[st.pop()] = Number[i];
				
			}
			st.push(i);
		}
		
		while(!st.isEmpty()) {
			Number[st.pop()] = -1;
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < N; i++) {
			sb.append(Number[i]).append(" ");
		}
		
		System.out.println(sb);
		
	}

}
