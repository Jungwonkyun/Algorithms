import java.util.*;

class Dungeon {
    int capacity;   //던전에 들어갈 수 있는 최소 피로도
    int loss;       //던전에 들어가면 소모되는 피로도
    public Dungeon(int capacity, int loss){
        this.capacity = capacity;
        this.loss = loss;
    }
}

class Solution {
    static Dungeon[] DList;
    static int len; 
    static int result = 0; //탐험할 수 있는 던전 갯수
    static boolean[] visited;
    public int solution(int k, int[][] dungeons) {
        len = dungeons.length;
        DList = new Dungeon[len];
        visited = new boolean[len];
           
        //던전들 배열 초기화
        for(int i = 0; i < len; i++){
            DList[i] = new Dungeon(dungeons[i][0],dungeons[i][1]); 
        }
        
        pickOrder(0, k);
        
        return result;
    }
    
    protected void pickOrder(int depth, int tired){
    
        for(int i = 0; i < len; i++){
            Dungeon nowDungeon = DList[i];
            //이미 방문한 던전 제외
            if(visited[i])continue; 
            //던전 입장 최소 피로도보다 작으면 입장불가
            if(tired < nowDungeon.capacity)continue;
            //방문표시
            visited[i] = true; 
            //DFS 돌리기
            pickOrder(depth+1, tired-nowDungeon.loss);
            //다시 돌려주기
            visited[i] = false;
        }
        
        result = Math.max(result,depth);
        
    }
    
    
}