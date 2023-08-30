
import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        int[] answer = new int[2];
        int len = Integer.MAX_VALUE, start = 0, idx = 0;
        
        
        Set<String>set = new HashSet<>();
        Map<String, Integer>map = new HashMap<>();
        Queue<String>queue = new LinkedList<>();
        
        for(String gem : gems){
            set.add(gem);
        }
        
        
        for (int i = 0; i < gems.length; i++) {
            map.put(gems[i], map.getOrDefault(gems[i], 0) + 1);
            queue.add(gems[i]);

            while (map.get(queue.peek()) > 1) {
                map.put(queue.peek(), map.get(queue.poll()) - 1);
                idx++;
            }

            if (map.size() == set.size() && len > (i - idx)) {
                len = i - idx;
                start = idx + 1;
            }
        }
    
        answer[0] = start;
        answer[1] = start+len;
        
        return answer;
    }
}