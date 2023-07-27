import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String [] input =  br.readLine().split(" ");
        String [] input2 = br.readLine().split(" ");

        int H = Integer.parseInt(input[0]);
        int W = Integer.parseInt(input[1]);

        if(W == 1){
            System.out.println(0);
            System.exit(0);
        }
        int [] block = new int [W];

        for(int i = 0; i < W; i++){
            block[i] = Integer.parseInt(input2[i]);
        }

        int sum = 0;

        for(int i = 1; i < W-1; i++){

            int l_idx = i;
            int r_idx = i;
            int left = 0;
            int right = 0;
            int now = block[i];

            //왼쪽 확인
            while(l_idx >= 0){
                if(now < block[l_idx] && left < block[l_idx]){
                    left = block[l_idx];
                }
                l_idx--;
            }

            //오른쪽 확인
            while(r_idx < W){
                if(now < block[r_idx]&& right < block[r_idx]){
                    right = block[r_idx];
                }
                r_idx++;
            }

            if(right == 0 || left == 0)continue;

            else sum+=(Math.min(left,right) - now);
        }

        System.out.println(sum);
    }
}