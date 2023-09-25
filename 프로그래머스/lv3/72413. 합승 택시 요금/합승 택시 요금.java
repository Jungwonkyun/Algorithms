import java.util.*;

class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;
        
        //인접리스트 배열 초기화
        int [][] Graph = new int [n+1][n+1];
        
        //거리 무한대로 초기화 시키기
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                //자기 자신까지의 거리는 0
                if(i==j){
                    Graph[i][j] = 0;
                    continue;
                }
                Graph[i][j] =  10000000;
            }
        }
        
        //연결 노드 가중치 초기화 시키기
        for(int[] fare : fares){
            int st = fare[0];
            int e = fare[1];
            int w = fare[2];
            
            Graph[st][e] = w;
            Graph[e][st] = w;
        }
        
        //플로이드 워셜로 각 노드에서 다른 노드끼리의 최단거리 갱신 
        for(int k = 1; k < n+1; k++){
            for(int i = 1; i < n+1; i++){
                for(int j = 1; j < n+1; j++){
                    Graph[i][j] = Math.min(Graph[i][k]+Graph[k][j],Graph[i][j]);
                }
            }
        }
        
        for(int i = 1; i < n+1; i++){        
            //합승요금
            int carpool = Graph[s][i];
            //분기 지점부터 A집까지 요금
            int toA = Graph[i][a];
            //분기 지점부터 B집까지 요금
            int toB = Graph[i][b];
            //최종요금
            int total = carpool + toA + toB;
            
            answer = Math.min(total,answer);
        }
        
        return answer;
    }
}