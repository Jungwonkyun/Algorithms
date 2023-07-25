import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    static int maximum = -1;
    static boolean[] visited;
    static int N;
    static String A;
    static String B;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String [] input = br.readLine().split(" ");
        A = input[0];
        B = input[1];

        N = A.length();
        visited = new boolean[N];

        if(A.length() > B.length()){
            System.out.println(-1);
            System.exit(0);
        }

        dfs(0,"");
        System.out.println(maximum);
    }


    public static void dfs(int depth, String str){

        if(depth == N){
            int per = Integer.parseInt(str);
            if(per<Integer.parseInt(B))maximum = Math.max(maximum,per);
        }

        for(int i = 0; i < N; i++){
            if(str.length()==0 && A.charAt(i)=='0')continue;
            if(!visited[i]){
                visited[i] = true;
                dfs(depth+1, str + A.charAt(i));
                visited[i] = false;
            }
        }


    }

}