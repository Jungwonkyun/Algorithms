import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main {

    static int N;
    static int[] strength;
    static int[] weight;
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        strength = new int[N];
        weight = new int[N];


        for(int i = 0; i < N; i++){
            String [] input = br.readLine().split(" ");
            strength[i] = Integer.parseInt(input[0]);
            weight[i] = Integer.parseInt(input[1]);
        }

        dfs(0,0);
        System.out.println(result);

    }

    public static void dfs(int idx, int cnt) {

        if(idx == N){
            result = Math.max(result,cnt);
            return;
        }

        //만약에 든 계란이 깨져있다면 오른쪽 계란을 집는다
        if(strength[idx]<=0 || cnt == N-1){
            dfs(idx+1,cnt);
            return;
        }

        int tempCnt = cnt;
        for(int i = 0; i < N; i++){
            //현재 계란이나 이미 계란이 꺠져있으면 치지 않는다
            if(idx == i || strength[i]<=0)continue;
            //해당 계란이 깨지지 않았을 때 계란을 친다
            strength[idx] -= weight[i];
            strength[i] -= weight[idx];

            if(strength[idx] <= 0)cnt++;
            if(strength[i] <= 0)cnt++;

            //다음 계란을 쥔다
            dfs(idx+1,cnt);

            strength[idx] += weight[i];
            strength[i] += weight[idx];
            cnt = tempCnt;
        }

    }

}