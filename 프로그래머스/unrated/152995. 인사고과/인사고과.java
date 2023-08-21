import java.util.*;


class Node implements Comparable<Node>{
    
    int x;
    int y; 
    
    public Node(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    public int compareTo(Node o){
        if(this.x == o.x){
            return  this.y - o.y;
        }
        return o.x - this.x;
    }

}


class Solution {
    public int solution(int[][] scores) {
        int answer = 0;
        int base = 0;
        int baseX = 0;
        int baseY = 0;
        
        List<Node> scoreList = new ArrayList<>();
        
        if(scores.length == 1)return 1;
        
        base = scores[0][0] + scores[0][1]; 
        baseX = scores[0][0];
        baseY = scores[0][1];
        
        Node nd = new Node(baseX,baseY);
        
        scoreList.add(nd);
        
        for(int i = 1; i < scores.length; i++){
            //동석차이거나 완호보다 점수가 낮은 경우에 생략
            if((scores[i][0]+scores[i][1])<=base)continue;
            
            Node node = new Node(scores[i][0],scores[i][1]);
            scoreList.add(node);            
        }

        
        
        //내림차순 정렬(근태기준)
        Collections.sort(scoreList);
        
        int max = -1; 
        int rank = 1;
        
        for(Node node : scoreList){
            //얘는 근태도 낮고 동평도 낮은 경우
            if(node.y < max){
                if(node.x == baseX && node.y == baseY)return -1;
                continue;
            }
            rank++;
            max =  Math.max(max,node.y); 
        }
        
        return rank-1;
    }
} 