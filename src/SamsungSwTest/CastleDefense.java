package SamsungSwTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;


class Node3 {

	int x;
	int y;

	public Node3(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

class EnermyInfo implements Comparable<EnermyInfo> {

	int x;
	int y;

	public EnermyInfo(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int compareTo(EnermyInfo o) {
		return this.y - o.y;
	}

}

public class CastleDefense {

	static int N;
	static int M;
	static int D;
	static int[][] map;
	static int[] nCr;
	static int[][] copyMap;
	static int[] dx = { 0, -1, 0 };
	static int[] dy = { -1, 0, 1 };
	static int result = 0;
	static boolean[] visit;
	static StringBuilder sb = new StringBuilder();
	static ArrayList<EnermyInfo> kill;

	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in = br.readLine().split(" ");
		N = Integer.parseInt(in[0]);
		M = Integer.parseInt(in[1]);
		D = Integer.parseInt(in[2]);
		map = new int[N + 1][M];
		nCr = new int[M];
		visit = new boolean [M];
		for (int i = 0; i < N; i++) {
			String[] input = br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(input[j]);
			}
		}

		PickArcher(0, 0);
		System.out.println(result);
	}

	// 궁수 3명 위치 선택
	public static void PickArcher(int idx, int depth) {
		
		
		
		if (depth == 3) {
			copyMap = new int[N + 1][M];
			ArrayList<Integer> arcList = new ArrayList<>();

			for (int i = 0; i < N + 1; i++) {
				copyMap[i] = Arrays.copyOf(map[i], M);
			}

			for(int i = 0; i < M; i++) {
				if(visit[i]==true) {
					copyMap[N][i] = 2;
					arcList.add(i);
				}
			}
			
//			for (int i = 0; i < 3; i++) {
//				copyMap[N][nCr[i]] = 2;
//				arcList.add(nCr[i]);
//			}
			
			result = Math.max(result, gameStart(arcList));

		}

		for (int i = idx; i < M; i++) {
			visit[i] = true;
			PickArcher(i + 1, depth + 1);
			visit[i] = false;
		}

	}

	// 게임 시작
	public static int gameStart(ArrayList<Integer> ary) {

		// 궁수는 N+1 위치에 맨 처음 있다
		int rowInfo = N;
		int temp = 0;
		
//		 for(int q = 0; q < N+1; q++) {
//			 for(int w = 0; w < M; w++) { 
//				 System.out.print(copyMap[q][w]+ " "); 
//			}System.out.println();
//		 } 
		
		// 게임은 총 N초간 진행한다
		for (int i = 0; i < N; i++) {
			kill = new ArrayList<>();
			for (int j = 0; j < 3; j++) {
				EnermyInfo pickedEnermy = getEnermy(rowInfo, ary.get(j));
//				System.out.println("------------------------------");
				
				if (pickedEnermy != null)
					kill.add(pickedEnermy);
			}

			// 죽일 적이 있을 때
			if (kill.size() != 0) {
				for (int k = 0; k < kill.size(); k++) {
					// 적이 이미 공격당하지 않았다면
					//sb.append("죽일 후보 x: "+kill.get(k).x+" ").append("y: "+kill.get(k).y+" \n");
					if (copyMap[kill.get(k).x][kill.get(k).y] == 1) {
						temp++;
						 //System.out.println("실제로 죽인 애들 x: "+kill.get(k).x+" "+"y: "+kill.get(k).y);
						copyMap[kill.get(k).x][kill.get(k).y] = 0;
					}
				}
			}
			// 궁수를 한 칸 위로 올려준다
			copyMap[rowInfo - 1] = copyMap[rowInfo];
			
			 System.out.println("----------"); System.out.println("죽인적 수: "+temp+ "round: "+i); for(int q = 0; q < N+1; q++) {
			 for(int w = 0; w < M; w++) { System.out.print(copyMap[q][w]+ " "); }System.out.println();
			 } System.out.println("----------");
//			
//			 System.out.println(sb.toString());
			
			
			 if (rowInfo == 1) {
				return temp;
			}
			
			rowInfo--;
		}

		return 0;
	}

	public static EnermyInfo getEnermy(int x, int y) {

		boolean [][] visited = new boolean[N + 1][M];

		Deque<Node3> dq = new ArrayDeque<>();
		Node3 arch = new Node3(x, y);
		dq.add(arch);

		int nx;
		int ny;
		int distance = 1;

		while (!dq.isEmpty()||distance <= D) {
			int size = dq.size();
			for(int k = 0; k < size; k++) {
				Node3 nowNode = dq.poll();
				x = nowNode.x;
				y = nowNode.y;
				
				for (int i = 0; i < 3; i++) {
					nx = x + dx[i];
					ny = y + dy[i];
					if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny]) continue;
				
					// 공격할 수 있는 적이 있다면 리턴 
					if (copyMap[nx][ny] == 1) {
						//System.out.println("new Enermy Info: nx:" +nx+" ny:"+ny+" distance: "+distance);
						EnermyInfo enermy = new EnermyInfo(nx, ny);
						return enermy;
					}

					visited[nx][ny] = true;
					Node3 node = new Node3(nx, ny);
					dq.add(node);
				}
			}
			distance++;
		}
		
		return null;
	}

}