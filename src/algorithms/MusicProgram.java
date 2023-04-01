package algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class MusicProgram {
	static int N;
	static int M;
	static List<ArrayList<Integer>>Singer;
	static int[] inNode;
	static Queue<Integer>numQueue;
	static boolean[] visited;
	static ArrayList<Integer>result;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		Singer = new ArrayList<>();
		result = new ArrayList<>();
		numQueue = new LinkedList<>();
		
		
		for(int i = 0; i < N+1; i++) {
			ArrayList<Integer> temp = new ArrayList<>();
			Singer.add(temp);
		}
		
		inNode = new int[N+1];
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			
			if(num <= 1)continue;
			
			//제일 처음 시작하는 노드 잡아주고
			int prev = Integer.parseInt(st.nextToken());
			
			for(int j = 1; j < num; j++) {
				int next = Integer.parseInt(st.nextToken());
				Singer.get(prev).add(next);
				prev = next;
				inNode[next]++;
			}
		}
		
		for(int i = 1; i < N+1; i++) {
			if(inNode[i] == 0)numQueue.add(i);
		}
		
		
		while(!numQueue.isEmpty()) {
			int nowNum = numQueue.poll();
			result.add(nowNum);
			for(int i = 0; i < Singer.get(nowNum).size(); i++) {
				int nextNode = Singer.get(nowNum).get(i);
				inNode[nextNode]--;
				if(inNode[nextNode]==0)numQueue.add(nextNode);
				
			}
		}
		
		if(result.size() == N) {
			for(int n : result) {
				System.out.println(n);
			}
		}else {
			System.out.println(0);
		}
		
		
	}

}
