import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Main {

    static int maximum = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer> numAry = new ArrayList<>();
        String[] input = br.readLine().split(" ");

        for(int i = 0; i < N; i++){
            int n = Integer.parseInt(input[i]);
            numAry.add(n);
        }

        dfs(0,0,numAry);

        System.out.println(maximum);
    }

    public static void dfs(int depth, int sum, ArrayList<Integer>list){

        if(list.size() <= 2){
            maximum = Math.max(maximum,sum);
            return;
        }

        for(int i = 1; i < list.size()-1; i++){
            int now = list.get(i);
            int num = (list.get(i-1) * list.get(i+1));
            list.remove(i);
            dfs(depth+1,sum+num,list);
            list.add(i,now);
        }

    }

}