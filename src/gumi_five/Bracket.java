package gumi_five;

import java.util.*;
import java.io.*;

public class Bracket {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < N; i++) {
			String [] bracket = br.readLine().split("");
			boolean flag = true;
			Stack<String> st = new Stack<>(); 
			
			for(int j = 0; j < bracket.length; j++) {
				if(bracket[j].equals("(")) {
					st.add(bracket[j]);
				}
				else if(st.isEmpty()) {
					flag = false;
					break;
				}else {
					st.pop();
				}
			}
			
			if(st.empty()&&flag==true)System.out.println("YES");
			else System.out.println("NO");
			
		}
		
	}

}
