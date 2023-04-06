package algorithms;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class CityDivide {

    public static int[] parent;
    public static int[][]Graph;
    public static String[] in;
    
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	in = br.readLine().split(" ");
    	int V = Integer.parseInt(in[0]);
    	int E = Integer.parseInt(in[1]);
    	
    	Graph = new int[E][3];
        parent = new int[V+1];
        
        //초기화
        for (int i = 1; i < V+1; i++) {
            parent[i] = i;
        }
        
        //그래프 정보 초기화
        for(int i = 0; i < E; i++) {
        	in = br.readLine().split(" ");
        	int f = Integer.parseInt(in[0]);
        	int s = Integer.parseInt(in[1]);
        	int w = Integer.parseInt(in[2]);
        	
        	Graph[i][0] = f;
        	Graph[i][1] = s;
        	Graph[i][2] = w;
       
        }
        
        //사용자 지정 정렬 꼭 기억하기 
        Arrays.sort(Graph, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				//가중치 기준 오름차순 정렬 
				return o1[2] - o2[2];
			}
        });
        
        int totalWeight = 0;
        int maxWeight = 0;
        for(int i = 0; i < Graph.length; i++) {
        	if(find(Graph[i][0]) != find(Graph[i][1])) {
        		totalWeight += Graph[i][2];
        		union(Graph[i][0], Graph[i][1]);
        		maxWeight = Math.max(maxWeight, Graph[i][2]);
        	}	
        }
        
        System.out.println(totalWeight-maxWeight);
        
    }
    
    //a,b의 대소관계에 따라서 부모노드를 바꿔준다 
    public static void union(int a, int b) {
        // 최상위 부모 찾기
        int x = find(a);
        int y = find(b);

        if (x < y) {
            parent[y] = x;
        } else {
            parent[x] = y;
        }
    }
    
    //a와 b의 부모가 같은지 확인
    public static boolean equals(int a, int b) {
        int x = find(a);
        int y = find(b);
        return x == y;
    }
    
    //a의 부모노드를 찾는 연산 
    public static int find(int a) {
        if (parent[a] == a)
            return a;
        return parent[a] = find(parent[a]);
    }

}
