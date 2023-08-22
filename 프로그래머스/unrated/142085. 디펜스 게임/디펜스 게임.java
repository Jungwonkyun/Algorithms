import java.util.*; 

class Solution {
    public int solution(int n, int k, int[] enemy) {
        int answer = 0;
        
        PriorityQueue<Integer>pq = new PriorityQueue<>(Collections.reverseOrder());
        
        if(enemy.length <= k)return enemy.length;
        
        int sum = 0; 
        
        for(int i = 0; i < enemy.length; i++){
            
            int now = enemy[i];
            
            pq.add(now);
            sum+=now;
            
            //System.out.println("sum: "+sum+" n: "+n+" now: "+now+" k: "+k);
            
            //라운드를 막을 수 없는데 무적권도 없는 경우
            if(sum > n && k == 0)return answer;
            
            //무적권이 남아있고 현재 라운드를 못 막는 경우
            else if(sum > n && k != 0){
    
                //pq에서 최댓값에 무적권 써버리고
                int max = pq.poll();
                //총합에서 뺴준다
                sum -= max;
                //무적권 하나 빼주기
                k--;
            }
            
            answer++;
        }
        
        
        return answer;
    }
}