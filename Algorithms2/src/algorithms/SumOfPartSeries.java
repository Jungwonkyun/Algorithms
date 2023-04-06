package algorithms;

import java.util.*;

public class SumOfPartSeries {
    static int N;
    static int S;
    static int[] ary;
    static int result = 0;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        S = sc.nextInt();
        ary = new int[N];
        for(int i = 0; i < N; i++){
            ary[i] = sc.nextInt();
        }
        compute(0,0);
        if (S==0)result--;
        System.out.println(result);
    }

    public static void compute(int depth, int sum){

        if(depth==N){
            if(sum==S)result++;
            return;
        }
        compute(depth+1,sum+ary[depth]);
        compute(depth+1,sum);
    }
}
