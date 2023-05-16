package algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class virus implements Comparable<virus> {
	int x;
	int y;
	int num;

	public virus(int x, int y, int num) {
		super();
		this.x = x;
		this.y = y;
		this.num = num;
	}

	@Override
	public int compareTo(virus o) {
		return this.num - o.num;
	}

}

public class CompetitiveInfection {
	static int N, K, S, X, Y;
	static int[][] map;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static List<virus> VirusList;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		VirusList= new LinkedList<>();
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			String[] in = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {	
				int input = Integer.parseInt(in[j]);
				map[i][j] = input;
				if(input!= 0) {
					virus v = new virus(i, j, input);
					VirusList.add(v);
				}
			}
		}
		
		Collections.sort(VirusList);

		st = new StringTokenizer(br.readLine());

		S = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken())-1;
		Y = Integer.parseInt(st.nextToken())-1;
		
		if(map[X][Y]!=0) {
			System.out.println(map[X][Y]);
			System.exit(0);
		}
		
		if(S==0) {
			System.out.println(0);
			System.exit(0);
		}
		spread();
	}

	public static void spread() {
		int cnt = 0;
		Queue<virus>VirusQ = new LinkedList<>();
		
		for(int i = 0; i < VirusList.size(); i++) {
			VirusQ.offer(VirusList.get(i));
		}
		
		while (!VirusQ.isEmpty()) {
			int size = VirusQ.size();
			for (int n = 0; n < size; n++) {
				virus nowVirus = VirusQ.poll();
				int x = nowVirus.x;
				int y = nowVirus.y;
				int num = nowVirus.num;
				
				for (int i = 0; i < 4; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];

					if (nx < 0 || nx >= N || ny < 0 || ny >= N || map[nx][ny] != 0)
						continue;
					
					if(nx == X && ny == Y) {
						System.out.println(num);
						System.exit(0);
					}
					
					map[nx][ny] = num;
					virus nextVirus = new virus(nx, ny, num);
					VirusQ.add(nextVirus);
				}
			}
			
			cnt++;
			if(cnt==S) {
				System.out.println(0);
				System.exit(0);
			}
		}

	}

}
