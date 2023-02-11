package algorithms;

import java.util.*;
import java.io.*;

public class StartLink2 {
    static int N;
    static boolean[] visited;
    static int[][] team;
    static int difference = 10000000;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        visited = new boolean[N];
        team = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) {
                team[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        for(int i = 1; i<=N; i++) {
        	compute(0,0,i);
        }
      
        System.out.println(difference);

    }

    public static void compute(int idx,int depth,int end) {

        if (depth == end) {
            check_ability();
            return;
        }

        visited[depth] = false;
        compute(idx + 1, depth + 1,end);
        visited[depth] = true;
        compute(idx + 1, depth + 1,end);        
       
     }
  

    public static void check_ability() {
        int ability1 = 0;
        int ability2 = 0;

        for(int i = 0; i < N-1;i++){
            for(int j = i+1; j <N; j++){
                if(visited[i]==true && visited[j]==true){
                    ability1 += (team[i][j] + team[j][i]);
                }
                else if(visited[i]==false && visited[j]==false){
                    ability2 += (team[i][j] + team[j][i]);
                }
            }
        }

        difference = Math.min(Math.abs(ability1-ability2),difference);

        if (difference == 0) {
            System.out.println(difference);
            System.exit(0);
        }
    }
}
