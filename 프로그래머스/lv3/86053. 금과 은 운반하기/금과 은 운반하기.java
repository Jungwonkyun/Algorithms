class Solution {
    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        long answer = -1;
        //시간 최솟값
        long left = 1;
        //시간 최댓값
        long right = (long)(10e9 * 2 * 10e5 * 2);
        int len = s.length;
        
        while(left <= right){
            //이분탐색 중간값 (시간)
            long mid = (left+right)/2;
            long gold = 0, silver = 0, total = 0;
            
            //모든 도시들에 대해서 도시 순회
            for(int i = 0; i < len; i++){
                int weight = w[i];
                int time = t[i];
                
                //주어진 시간에서 왕복했을 때 가능한 최대횟수 
                long cnt = mid/(time*2); 
                
                //편도로 가능한 경우 카운트를 하나 더 늘려준다
                if((mid % (time*2) >= time)){
                    cnt++;
                }
                
                //얘는 금을 모두 채워서 보낼경우
                gold += Math.min(g[i],cnt*weight);
                //얘는 은을 모두 채워서 보낼경우
                silver += Math.min(s[i],cnt*weight);
                //금과 은을 모두 합했을 때 
                total += Math.min(g[i]+s[i],cnt*weight);
            }
            
            //운반 가능하면 시간 줄이기
            if(gold>=a && silver>=b && total>=a+b){
                answer = mid;
                right = mid-1;
            }
            else{
                left = mid+1;
            }   
        }
        return answer;
    }
}