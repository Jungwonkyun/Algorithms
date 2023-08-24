import java.util.*;

class Solution {
    public int solution(int[] cards) {
        int answer = 0;
        boolean[] visited = new boolean[cards.length]; 
        List<Integer>ary = new ArrayList<>();
        
        for(int card : cards){
            int count  = 0;
            int cardIdx = card -1;
            System.out.println("새로운 그룹 시작");
            
            while(!visited[cardIdx]){
                visited[cardIdx] = true;
                count++;
                System.out.println("index: "+cardIdx+" value: "+(cards[cardIdx]-1));
                cardIdx = cards[cardIdx]-1;
            }
            //System.out.println(count);
            ary.add(count);
        }        
        
        Collections.sort(ary,Collections.reverseOrder());
        
        if(ary.size()==1)return 0;
        
        return ary.get(0) * ary.get(1);
    }
    
}