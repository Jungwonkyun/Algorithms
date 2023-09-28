import java.util.*;

class Solution {
    static boolean [] visited;
    static int[] nodeInfo;
    static int[][]edgeInfo;
    static int maxSheep = 0;

    public int solution(int[] info, int[][] edges) {
        int answer = 0;
        nodeInfo = info;
        edgeInfo = edges;
        visited = new boolean[info.length];

        dfs(0,0,0);
        return maxSheep;
    }
    
    private void dfs(int node, int sheepCnt, int wolfCnt ){ 
        //현재 노드 방문처리 
        visited[node] = true;
        
        //만약 이번 노드가 양이라면
        if(nodeInfo[node] == 0){
            //양 갯수 늘려주기
            sheepCnt++;
            //만약 최대 양 갯수를 넘는다면 최대 양 수 업데이트
            if(sheepCnt > maxSheep)maxSheep = sheepCnt;
        }else {
            //늑대라면 늑대수를 늘려준다
            wolfCnt++;
        }
        
        //만약 늑대 수가 양 수보다 많으면 종료 조건
        if (sheepCnt <= wolfCnt) {
            //현재 노드는 다음에 다시 방문할 여지가 있으므로 되돌려줌
            visited[node] = false;
            return;
        }
        
        for(int [] edge : edgeInfo){
            //만약 현재노드는 방문하고 자식노드가 방문가능하다면
            if (visited[edge[0]] && !visited[edge[1]]){
                dfs(edge[1],sheepCnt,wolfCnt);
            }
        }
        
        //다시 방문할 수도 있게 만들기 위해서 방문조건을 풀어준다.
        visited[node] = false;
        return;
    }
}