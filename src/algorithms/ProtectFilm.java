//package algorithms;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.Arrays;
//
//public class ProtectFilm {
//	static String[] in;
//	static int D, W, K;
//	static int[][] film;
//	static int[][] copy;
//	static int[] num;
//	static int result;
//	static boolean[] visited;
//	static int[] cell = {0,1};
//	static int[] per;
//	static int t;
//
//	public static void main(String[] args) throws NumberFormatException, IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		int TC = Integer.parseInt(br.readLine().trim());
//
//		for (t = 1; t < TC + 1; t++) {
//			in = br.readLine().trim().split(" ");
//			D = Integer.parseInt(in[0]);
//			W = Integer.parseInt(in[1]);
//			K = Integer.parseInt(in[2]);
//			result = -1;
//
//			film = new int[D][W];
//			visited = new boolean[D];
//			for (int i = 0; i < D; i++) {
//				in = br.readLine().trim().split(" ");
//				for (int j = 0; j < W; j++) {
//					film[i][j] = Integer.parseInt(in[j]);
//				}
//			}
//
//			// 한번에 가능하면 출력 후 continue
//			if (isPossible(film)) {
//				System.out.println("#" + t + " " + 0);
//				continue;
//			}
//
//			for (int i = 2; i < K+1; i++) {
//				num = new int[i];
//				pickPowerSet(0, 0, i);
//				//System.out.println("===============");
//			}
//			
//			System.out.println("#" + t + " " + result);
//
//		}
//
//	}
//
//	public static boolean isPossible(int [][]check) {
//
//		for (int i = 0; i < W; i++) {
//			int cnt = 1;
//			int prev = -1;
//			boolean flag = false;
//			for (int j = 0; j < D; j++) {
//				if (check[j][i] == prev) {
//					cnt++;
//				} else {
//					prev = check[j][i];
//					cnt = 1;
//				}
//				if (cnt == K) {
//					flag = true;
//					break;
//				}
//			}
//
//			if (flag == false)
//				return false;
//		}
//
//		return true;
//	}
//
//	public static void pickPowerSet(int idx, int depth, int r) {
//
//		if (depth == r) {
//			copy = new int [D][W];
//			for(int i = 0; i < D; i++) {
//				copy[i] = Arrays.copyOf(film[i],W);
//			}
//			
//			for(int n: num) {
//				System.out.print(n+" ");
//			}System.out.println();
//			
//			inject(0);
//			return;
//		}
//
//		for (int i = idx; i < D; i++) {
//			if (visited[i] == false) {
//				visited[i] = true;
//				num[depth] = i;
//				pickPowerSet(i + 1, depth + 1, r);
//				visited[i] = false;
//			}
//		}
//		
//		return;
//
//	}
//
//	public static int inject(int depth) {
//		
//		if(depth == num.length) {
//			
//			for (int i = 0; i < D; i++) {
//				for (int j = 0; j < W; j++) {
//					System.out.print(copy[i][j]+" ");
//				}System.out.println();
//			}
//			
//			System.out.println();
//			System.out.println("----------------");
//			System.out.println();
//			
//			if(isPossible(copy)) {
//				result = num.length;
//				System.out.println("good");
//				return result;
//			}
//			return -1;
//		}
//		
////		int temp[] = new int[W];
////		temp = Arrays.copyOf(copy[num[depth]],W);
//		
//		//A inject
//		for(int j = 0; j < W; j++) {
//			copy[num[depth]][j] = 0;
//		}
//		
//		if(inject(depth+1)!=-1)return result;
//		
//		
//		
//		//B inject
//		for(int j = 0; j < W; j++) {
//			copy[num[depth]][j] = 1;
//		}
//		inject(depth+1);
//		if(inject(depth+1)!=-1)return result;
//
//	}
//
//	
//	
//}
