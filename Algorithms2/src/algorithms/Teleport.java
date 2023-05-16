package algorithms;

/*
 * 일반적인 플로이드 워셜 문제: 모든 도시 사이의 최단경로를 구해야 하니까 
 * 단 텔레포트로 이동하는 경우를 생각해야 하는데 그냥 이동하는 것이 빠른지
 * 텔레포트로 이동하는 것이 더 빠른지를 구별해서 플로이드 워셜 돌려주면 된다
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


class City {
	int x;
	int y;
	int special;

	public City(int x, int y, int special) {
		super();
		this.x = x;
		this.y = y;
		this.special = special;
	}

}

public class Teleport {
	static int M, T;
	static boolean specialCity[];
	static int[][] dist;
	static City[] cityList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		// 텔레포트가 가능한 도시 리스트
		specialCity = new boolean[M];
		dist = new int[M][M];
		cityList = new City[M];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			if (s == 1)
				specialCity[i] = true;
			City city = new City(x, y, s);
			cityList[i] = city;
		}

		floydWarshall();

		int TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
			
		for (int t = 0; t < TC; t++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken())-1;
			int end = Integer.parseInt(st.nextToken())-1;
			sb.append(dist[start][end]).append('\n');
		}
		
		System.out.println(sb.toString());
	}

	private static void floydWarshall() {

		//인접 리스트 초기화 
		for (int i = 0; i < M ; i++) {
			City city1 = cityList[i];
			for (int j = i ; j < M; j++) {
				if(i==j) {
					dist[i][j] = 100000000;
					continue;
				}
				City city2 = cityList[j];
				int d = Math.abs(city1.x - city2.x) + Math.abs(city1.y - city2.y);
				if (specialCity[i] && specialCity[j]) {
					//순간 이동 or 일반 이동중 빠른 걸로 
					dist[i][j] = Math.min(T, d);
					dist[j][i] = Math.min(T, d);
				}else {
					dist[i][j] = d;
					dist[j][i] = d;
				}
			}
		}
		
		for(int k = 0; k < M; k++) {
			for(int i = 0; i < M; i++) {
				for(int j = 0; j < M; j++) {
					dist[i][j] = Math.min(dist[i][j],dist[i][k]+dist[k][j]);
				}
			}
		}

	}

}
