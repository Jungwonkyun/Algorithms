import java.io.*;
import java.util.HashSet;

public class Main {
    static int [][] member;
    static int max = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int TC = Integer.parseInt(br.readLine());

        for(int i = 0; i < TC;  i++){
            member = new int [11][11];
            for(int j = 0; j < 11; j++){
                String [] input = br.readLine().split(" ");
                for(int k = 0; k < 11; k++){
                    member[j][k] = Integer.parseInt(input[k]);
                }
            }
            boolean[]visited = new boolean[11];
            dfs(0,0, visited);
            sb.append(max).append('\n');
            max = 0;
        }

        System.out.println(sb.toString());

    }

    public static void dfs(int idx, int sum, boolean [] visited){

        if(idx == 11){
            max = Math.max(max,sum);
            return;
        }

        for(int i = 0; i < 11; i++){
            if(member[idx][i] == 0||visited[i])continue;
            visited[i] = true;
            dfs(idx+1,sum+member[idx][i], visited);
            visited[i] = false;
        }

    }
}