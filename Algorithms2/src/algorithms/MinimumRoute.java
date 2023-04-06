package algorithms;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable<Node> {
	int nodeNum;
	int weight;

	public Node(int nodeNum, int weight) {
		this.nodeNum = nodeNum;
		this.weight = weight;
	}

	@Override
	public int compareTo(Node o) {
		return weight - o.weight;
	}
} 

public class MinimumRoute {
	static List<ArrayList<Node>> list;
	static int dist[];
	static int V;
	private static final int INF = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		V = Integer.parseInt(st.nextToken()); // Vertex
		int E = Integer.parseInt(st.nextToken()); // Edge
		
		int start = Integer.parseInt(br.readLine()); 

		list = new ArrayList<>();
		dist = new int[V + 1];
		Arrays.fill(dist, INF);
		
		//인접리스트 초기화 
		for(int i=0; i<=V; i++) {
			list.add(new ArrayList<>());
		}
		
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());

			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			//시작 정점리스트에 정보 추가
			list.get(s).add(new Node(d, w));
		}
		
		dijkstra(start);
			
		for(int i=1; i<=V; i++) {
			if(dist[i]!=Integer.MAX_VALUE)System.out.println(dist[i]);
			else System.out.println("INF");
		}
	} 

	static void dijkstra(int start) {
		PriorityQueue<Node> que = new PriorityQueue<Node>();
		boolean visit[] = new boolean[V + 1];

		dist[start] = 0;
		que.offer(new Node(start, 0));
		
		while( !que.isEmpty() ) {
			Node queNode = que.poll();
			int start_nodeNum = queNode.nodeNum;
			
			if( !visit[start_nodeNum] ) {
				visit[start_nodeNum] = true;
				
				for(Node node : list.get(start_nodeNum)) {
					//방문하지 않았고 업데이트 할 간선 정보가 원래 간선정보보다 짧을 때 업데이트
					if( !visit[node.nodeNum] && dist[node.nodeNum] > (dist[start_nodeNum] + node.weight)) {
						dist[node.nodeNum] = dist[start_nodeNum] + node.weight;
						que.offer(new Node(node.nodeNum, dist[node.nodeNum]));
					}
				}
			}
		}
	} 

}
