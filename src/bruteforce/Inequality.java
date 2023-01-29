package bruteforce;

import java.io.*;
import java.util.*;

public class Inequality {
	
	static ArrayList<String> result = new ArrayList<>();
	static int N;
	static boolean[] visited;
	static String [] ineq;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		ineq = br.readLine().split(" ");
		
		for(int i = 0; i<10; i++) {
			visited = new boolean[10];
			visited[i] = true;
			//String tmp = Integer.toString(i);
			DFS(i,0,Integer.toString(i));
			visited[i] = false;
		}
		
		System.out.println(Collections.max(result));
		System.out.println(Collections.min(result));
		
	}
	
	public static void DFS(int num, int depth, String word) {
		
		if (word.length() == N+1) {
			result.add(word);
			return;
		}
		
		for (int i = 0; i < 10; i++) {
//			System.out.println(depth+ " "+ word);
			if(visited[i]==false) {	
				//부등호 방향에 따라 Backtracking 
				if(ineq[depth].equals(">")) {
					if(num>i && !word.contains(Integer.toString(i))) {
						visited[i] = true;
						//System.out.println("> "+word+" "+num+" "+i+" "+depth);
						DFS(i,depth+1,word+i);
						visited[i] = false;
					}
					
				}
				else if(ineq[depth].equals("<")){
					if(num<i && !word.contains(Integer.toString(i))) {
						visited[i] = true;
						//System.out.println("< "+word+" "+num+" "+i+" "+depth);
						
						DFS(i,depth+1,word+i);
						visited[i] = false;
					}
				}
				
			}
		}
		
	}

}
