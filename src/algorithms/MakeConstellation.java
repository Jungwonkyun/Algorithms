package algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class MakeConstellation {

    public static int[] parent;
    public static double[][]Graph;
    public static double[][]Star;
    public static String[] in;
    
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	in = br.readLine().split(" ");
    	int V = Integer.parseInt(in[0]);
    	
    	Star = new double[V][2];
    	Graph = new double[V*(V-1)/2][3];
        parent = new int[V];
        
        //초기화
        for (int i = 0; i < V; i++) {
            parent[i] = i;
        }
        
        //별자리 좌표 초기화 
        for(int i = 0; i < V; i++) {
        	in = br.readLine().split(" ");
        	Star[i][0] = Double.parseDouble(in[0]);
        	Star[i][1] = Double.parseDouble(in[1]);
        }
        
        int cnt = 0;
        //그래프 정보 초기화
        for(int i = 0; i < V-1; i++) {
        	for(int j = i; j < V; j++) {
        		//거리 계산
        		double length = Math.sqrt(Math.pow(Graph[i][0] - Graph[j][0],2)+(Math.pow(Graph[i][1] - Graph[j][1],2)));
        		Graph[cnt][0] = i;
        		Graph[cnt][1] = j;
        		Graph[cnt][2] = length;
        	}
        }
        	
        
        //사용자 지정 정렬 꼭 기억하기 
        Arrays.sort(Graph, new Comparator<double[]>() {
        	@Override
			public int compare(double[] o1, double[] o2) {
				//가중치 기준 오름차순 정렬 
				return (int) (o1[2] - o2[2]);
			}
        });
        
        int totalWeight = 0;
        for(int i = 0; i < Graph.length; i++) {
        	if(find((int)Graph[i][0]) != find((int)Graph[i][1])) {
        		totalWeight += Graph[i][2];
        		union((int)Graph[i][0],(int)Graph[i][1]);
        	}	
        }
        
        System.out.println(totalWeight);
        
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
