package algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Gerrymandering {
	static int N;
	static int[] cityInfo;
	static boolean [][] network;
	static String[] in;
	static boolean[] visited;
	static int result = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		in = br.readLine().split(" ");
		cityInfo = new int[N+1];
		network = new boolean[N+1][N+1];
		visited = new boolean[N+1];
		
		for(int i = 1; i< N+1; i++) {
			cityInfo[i] = Integer.parseInt(in[i-1]);
		}
		
		for(int start = 1; start< N+1; start++) {
			in = br.readLine().split(" ");
			for(int i = 1; i< Integer.parseInt(in[0])+1;i++) {
				int end = Integer.parseInt(in[i]); 
				network[start][end] = true;
			}
		}
		
		pickCity(1,0);
		if(result == Integer.MAX_VALUE)System.out.println(-1);
		else System.out.println(result);
	}
	
	public static void pickCity(int cityNum, int depth) {
		
		//서로 팀만 바뀐 경우는 고려할 필요 x 
		if(depth>N/2)return;
		
		//depth = N/2까지만 조합의 수를 뽑자 
		if(depth != 0) {
			//도시 1그룹 도시 2그룹 선정 
			int[] cityGroup1 = new int[depth];
			int[] cityGroup2 = new int[N-depth];
			int cnt1 = 0;
			int cnt2 = 0;
			
			for(int i = 1; i < N+1; i++) {
				if(visited[i] == true) {
					cityGroup1[cnt1] = i;
					cnt1++;
				}else {
					cityGroup2[cnt2] = i;
					cnt2++;
				}		
			}
			
			//둘 다 연결되어 있으면 인구수 계산 
			if(isConnected(cityGroup1) && isConnected(cityGroup2)) {
				int peopleCity1 = 0;
				int peopleCity2 = 0;
				
				for(int i = 0; i < depth; i++) {
					peopleCity1+=cityInfo[cityGroup1[i]];
				}
				
				for(int i = 0; i < N-depth; i++) {
					peopleCity2+=cityInfo[cityGroup2[i]];
				}
				
				result = Math.min(result, Math.abs(peopleCity1 - peopleCity2));
			}
			
		}
		
		//combination
		for(int i = cityNum; i < N+1; i++) {
			if(visited[i]==false) {
				visited[i] = true;
				pickCity(i+1, depth+1);
				visited[i] = false;
			}
		}
		
	}
	
	//도시가 연결되어있는지 확인 
	public static boolean isConnected(int []cityGroup) {
		Deque<Integer>dq = new ArrayDeque<>();
		boolean [] visit = new boolean[N+1];
		int size = cityGroup.length;
		dq.add(cityGroup[0]);
		visit[cityGroup[0]] = true;
		
		while(!dq.isEmpty()) {
			for(int i = 0; i < dq.size(); i++) {
				int start = dq.poll();
				for(int j = 0; j < size ; j++) {
					int now = cityGroup[j];
					//방문한 적 없고 네트워크가 연결되어 있으W면 
					if(visit[now]==false && network[start][now] == true) {
						dq.add(now);
						visit[now] = true;
					}
				}
			}
		}
		
		//그룹에 들어가있는데 방문이 안 되어있다면 unconnected이다
		for(int i = 0; i< size; i++) {
			if(visit[cityGroup[i]] == false)return false;
		}
		
		return true; 
	}
	
}
