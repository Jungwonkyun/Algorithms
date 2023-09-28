import java.util.*;

class Solution {
    public int[] solution(int n, String[] words) {
        Set<String> wordSet = new HashSet<>();
        int[] answer = new int[2];
        char prev = words[0].charAt(words[0].length() - 1);
        wordSet.add(words[0]);
        int cnt = 1;

        for (int i = 1; i < words.length; i++) {
            cnt++;
            // 앞의 말끝을 잇지 않았을 경우 혹은 이미 나온 단어를 말한 경우
            if (prev != words[i].charAt(0) || wordSet.contains(words[i])) {
                answer[0] = cnt % n == 0 ? n : cnt % n;
                answer[1] = (cnt - 1) / n + 1;
                return answer;
            }
            
            // 게임이 계속 된다면 단어 사전에 추가하고 끝말을 등록
            wordSet.add(words[i]);
            prev = words[i].charAt(words[i].length() - 1);
        }

        // 모든 단어를 정상적으로 마친 경우
        answer[0] = 0;
        answer[1] = 0;
        return answer;
    }
}