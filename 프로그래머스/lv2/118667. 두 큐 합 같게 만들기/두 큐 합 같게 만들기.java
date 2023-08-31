import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;
        
        Queue<Integer>q1 = new LinkedList<>();
        Queue<Integer>q2 = new LinkedList<>();
        
        long sum1 = 0;
        long sum2 = 0;
        
        int size1 = 0;
        int size2 = 0;
        
        for(int i = 0 ; i < queue1.length; i++){
            int q1Num = queue1[i];
            int q2Num = queue2[i];
            
            q1.add(q1Num);
            q2.add(q2Num);
            
            sum1 += q1Num;
            sum2 += q2Num;
        }
        
        size1 = q1.size();
        size2 = q2.size();
        
        while(sum1 != sum2){
            if(answer > 2*(size1+size2)){
                return -1;
            }
            if(sum1 > sum2){
                int now = q1.poll();
                q2.offer(now);
                sum1 -= now;
                sum2 += now;
            }else if(sum1 < sum2){
                int now = q2.poll();
                q1.offer(now);
                sum1 += now;
                sum2 -= now;
            }else{
                return answer;
            }
               
            answer++;
        }
        
        
        return answer;
    }
}