package algorithms;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node3 implements Comparable<Node3> {
	int nodeNum;
	int weight;

	public Node3(int nodeNum, int weight) {
		this.nodeNum = nodeNum;
		this.weight = weight;
	}

	@Override
	public int compareTo(Node3 o) {
		return weight - o.weight;
	}
}

public class Party {
	static List<ArrayList<Node3>> list;
	static int[] dist;
	static int V;
	static int party;
	static int first;
	static int second;
	static Boolean[] visited;
	static final int INF = 200000000;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		V = Integer.parseInt(st.nextToken()); // Vertex
		int E = Integer.parseInt(st.nextToken()); // Edge
		party = Integer.parseInt(st.nextToken()); //party가 열리는 마을
		
		list = new ArrayList<>();
		visited = new Boolean[V+1];
		
		// 인접리스트 초기화
		for (int i = 0; i <= V; i++) {
			list.add(new ArrayList<>());
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());

			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			// 시작 정점리스트에 정보 추가
			list.get(s).add(new Node3(d, w));
		}
		
		
		int result = -1;
		
		for(int i = 1; i <= V; i++) {
			int temp = dijkstra(i, party)+dijkstra(party, i);
			result = Math.max(result, temp);
		}
		
		System.out.println(result);

	}

	public static int dijkstra(int start, int destination) {
		PriorityQueue<Node3> que = new PriorityQueue<Node3>();
		Arrays.fill(visited, false);
		
		dist = new int[V + 1];
		Arrays.fill(dist, INF);
		
		dist[start] = 0;
		que.offer(new Node3(start, 0));

		while (!que.isEmpty()) {
			Node3 queNode = que.poll();
			int start_nodeNum = queNode.nodeNum;
			
			if(visited[start_nodeNum]) continue; 
            visited[start_nodeNum] = true;
			
            for (Node3 node : list.get(start_nodeNum)) {
				// 방문하지 않았고 업데이트 할 간선 정보가 원래 간선정보보다 짧을 때 업데이트
				if (!visited[node.nodeNum] && dist[node.nodeNum] > (dist[start_nodeNum] + node.weight)) {
					dist[node.nodeNum] = dist[start_nodeNum] + node.weight;
					que.offer(new Node3(node.nodeNum, dist[node.nodeNum]));
				}
			}
		}
		return dist[destination];
	}
	
	

}
