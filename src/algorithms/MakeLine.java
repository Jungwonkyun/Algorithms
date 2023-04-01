package algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class MakeLine {
	static int N;
	static int M;
	static List<ArrayList<Integer>>Height;
	static int[] inNode;
	static Queue<Integer>numQueue;
	static boolean[] visited;
	static ArrayList<Integer>result;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		Height = new ArrayList<>();
		result = new ArrayList<>();
		numQueue = new LinkedList<>();
		
		
		for(int i = 0; i < N+1; i++) {
			ArrayList<Integer> temp = new ArrayList<>();
			Height.add(temp);
		}
		
		inNode = new int[N+1];
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
		
			//제일 처음 시작하는 노드 잡아주고
			int prev = Integer.parseInt(st.nextToken());
			int next = Integer.parseInt(st.nextToken());
			
			Height.get(prev).add(next);
			inNode[next]++;

		}
		
		for(int i = 1; i < N+1; i++) {
			if(inNode[i] == 0)numQueue.add(i);
		}
		
		
		while(!numQueue.isEmpty()) {
			int nowNum = numQueue.poll();
			result.add(nowNum);
			for(int i = 0; i < Height.get(nowNum).size(); i++) {
				int nextNode = Height.get(nowNum).get(i);
				inNode[nextNode]--;
				if(inNode[nextNode]==0)numQueue.add(nextNode);
				
			}
		}
		
		for(int n : result) {
			System.out.print(n+" ");
		}
	}

}
