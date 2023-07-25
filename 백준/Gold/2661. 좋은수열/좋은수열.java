import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {
    static Set<String>numSet;
    static int N;
    static String[] num = {"1","2","3"};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        numSet = new HashSet<>();

        dfs(0,"");

    }

    public static void dfs(int depth, String str){
        if(depth == N){
            System.out.println(str);
            System.exit(0);
        }

        for(int i = 0; i < 3; i++){
            if(!check(str+num[i]))continue;
            dfs(depth+1,str+num[i]);
        }
    }

    public static boolean check(String str){

        for(int i = 1; i <= str.length() / 2; i++) {
            String front = str.substring(str.length() -i * 2, str.length() - i);
            String back = str.substring(str.length() - i, str.length());
            if(front.equals(back)) return false;
        }
        return true;
    }
}