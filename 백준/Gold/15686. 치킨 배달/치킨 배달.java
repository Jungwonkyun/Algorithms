import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class chicken {
	int x;
	int y;
	
	chicken(int x, int y){
		this.x = x;
		this.y = y;
	}
}

class house {
	int x;
	int y;
	
	house(int x, int y){
		this.x = x;
		this.y = y;
	}
}


public class Main {
	static int n,m;
	static int[][] map;
	static int min = Integer.MAX_VALUE;
	static int num; 
	static chicken [] chickenList;
	static boolean [] visited;
	static List<house>houseList;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n =  Integer.parseInt(st.nextToken());
		m =  Integer.parseInt(st.nextToken());
		map = new int[n][n];
		
		//모든 치킨집 정보 저장 -> 아직 몇개 들어올지 모르니까 ArrayList
		List<chicken> temp = new ArrayList<>();
		houseList = new ArrayList<>();
		
		for(int i = 0; i < n; i++) {
			String input [] = br.readLine().split(" ");
			for(int j = 0; j < input.length; j++) {
				int info = Integer.parseInt(input[j]);
				map[i][j] = info;
				if(info == 2)temp.add(new chicken(i, j));
				if(info == 1)houseList.add(new house(i, j));
			}
		}
		
		num = temp.size();
		chickenList = new chicken[num];
		visited = new boolean[num];
		
		//부분집합 쉽게 뽑기 위해서 Array로 수정
		for(int i = 0; i < num; i++) {
			chickenList[i] = temp.get(i);
		}
	
		//1.부분집합 이용해서 R개의 치킨집 중에서 M개를 선택한다
		pickChicken(0,0);
		System.out.println(min);
	}
	
	private static void pickChicken(int idx, int cnt) {
		
		//cnt가 끝까지 가거나, m개를 다 정한 경우에 리턴조건  
		if(cnt == m || idx == num){
			//끝까지 갔는데 m만큼 못 뽑았으면 로직 처리 안 함
			if(cnt!=m)return;
			chicken[] mChicken = new chicken[m];

			int c = 0;
			for(int i = 0; i < num; i++) {
				if(visited[i]) {
					mChicken[c] = chickenList[i];
					c++;
				}
			}
			
			
			//치킨 거리 계산
			calDistance(mChicken);
			return;
		}
		
		visited[idx] = true; // 선택	
		pickChicken(idx+1, cnt+1);
		
		visited[idx] = false; // 선택 해제
		pickChicken(idx+1,cnt);
		
	}
	
	//치킨 거리 계산하는 로직 
	private static void calDistance(chicken[] mChicken) {

		int distance = 0;
		for(int i = 0; i < houseList.size(); i++) {
			//특정 집에서 가장 짧은 치킨거리
			int temp = Integer.MAX_VALUE;
			//모든 치킨집 중에 젤 가까운애
			for(int j = 0; j < mChicken.length; j++) {
				temp = Math.min(temp, (Math.abs(houseList.get(i).x - mChicken[j].x ) + Math.abs(houseList.get(i).y - mChicken[j].y)));
			}
			distance += temp;
		}
		
		min = Math.min(min, distance);
		return;
	}

}