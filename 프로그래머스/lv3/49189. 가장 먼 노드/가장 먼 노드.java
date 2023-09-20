import java.util.*;

class Solution {
    static List<ArrayList<Integer>>adjList;
    static boolean[] visited;
    static int size;
    public int solution(int n, int[][] edge) {
         //인접 리스트  
         adjList = new ArrayList<>();
         //방문 확인 배열
         visited = new boolean[n+1];

         for(int i = 0; i < n+1; i++){
            adjList.add(new ArrayList<Integer>());
         }
         
         //인접리스트 초기화
         for(int[] e : edge){
             adjList.get(e[0]).add(e[1]);
             adjList.get(e[1]).add(e[0]);
         }
         
        //bfs 돌리기
         bfs();
         return size;
     }
    
    private void bfs(){
        Queue<Integer>q = new LinkedList<>();    
        q.add(1);
        visited[1] = true;
        while(!q.isEmpty()){
            //미리 할당 안 해주면 계속 바뀜
            size = q.size();
            for(int i = 0; i < size; i++){
                int node = q.poll();
                //해당 노드랑 연결 되어있는 노드 중에서
                for(int num : adjList.get(node)){
                    if(visited[num])continue;
                    //방문 안 한 노드만 방문 처리하고 다시 queue에 넣어준다
                    visited[num] = true;
                    q.offer(num);
                } 
            }   
        }
    
    }
}