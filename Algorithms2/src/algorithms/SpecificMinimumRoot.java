package algorithms;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node2 implements Comparable<Node2> {
	int nodeNum;
	int weight;

	public Node2(int nodeNum, int weight) {
		this.nodeNum = nodeNum;
		this.weight = weight;
	}

	@Override
	public int compareTo(Node2 o) {
		return weight - o.weight;
	}
}

public class SpecificMinimumRoot {
	static List<ArrayList<Node2>> list;
	static int[] dist;
	static int V;
	static int first;
	static int second;
	static Boolean[] visited;
	static final int INF = 200000000;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		V = Integer.parseInt(st.nextToken()); // Vertex
		int E = Integer.parseInt(st.nextToken()); // Edge

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
			list.get(s).add(new Node2(d, w));
			// 끝 정점리스트에 정보 추가
			list.get(d).add(new Node2(s, w));
		}
		
		st = new StringTokenizer(br.readLine());
		first = Integer.parseInt(st.nextToken());
		second = Integer.parseInt(st.nextToken());
		
		int result;
		int route1 = dijkstra(1,first)+dijkstra(first,second)+dijkstra(second,V);
		int route2 = dijkstra(1,second)+dijkstra(second,first)+dijkstra(first,V);

		result = Math.min(route1, route2);
		if(result >= INF)result = -1;
		
		
		System.out.println(result);

	}

	public static int dijkstra(int start, int destination) {
		PriorityQueue<Node2> que = new PriorityQueue<Node2>();
		Arrays.fill(visited, false);
		
		dist = new int[V + 1];
		Arrays.fill(dist, INF);
		
		dist[start] = 0;
		que.offer(new Node2(start, 0));

		while (!que.isEmpty()) {
			Node2 queNode = que.poll();
			int start_nodeNum = queNode.nodeNum;
			
			if(visited[start_nodeNum]) continue; 
            visited[start_nodeNum] = true;
			
            for (Node2 node : list.get(start_nodeNum)) {
				// 방문하지 않았고 업데이트 할 간선 정보가 원래 간선정보보다 짧을 때 업데이트
				if (!visited[node.nodeNum] && dist[node.nodeNum] > (dist[start_nodeNum] + node.weight)) {
					dist[node.nodeNum] = dist[start_nodeNum] + node.weight;
					que.offer(new Node2(node.nodeNum, dist[node.nodeNum]));
				}
			}
		}
		return dist[destination];
	}
	
	

}
