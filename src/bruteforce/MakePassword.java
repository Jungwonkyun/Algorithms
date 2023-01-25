package bruteforce;

import java.util.*;
import java.io.*;

public class MakePassword {
	static int L,C;
    static char[] alpha;
    static boolean[] visited;
    static ArrayList<String> result = new ArrayList<String>();

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        alpha = new char[C];
        st = new StringTokenizer(bf.readLine());

        for(int i = 0; i<C; i++) {
            alpha[i] = st.nextToken().charAt(0);
        }
        Arrays.sort(alpha);
        visited = new boolean[C];

        DFS(0,0);
    }

    public static void DFS(int idx, int depth){

        if (depth == L){
            String result = "";
            for (int i = 0; i<C; i++){
                if (visited[i]){
                    result+=alpha[i];
                }
            }
            if(validCheck(result)){
                System.out.println(result);
            }
        }

        for(int i = idx; i < C; i++){
            visited[i] = true;
            DFS(i+1,depth+1);
            visited[i] = false;
        }
    }
    public static boolean validCheck(String word){
        int vowel = 0;
        int cos = 0;
        for(int i = 0; i < word.length();i++){
            if (isVowel(word.charAt(i))){
                vowel++;
            }else cos++;
        }

        if (vowel>=1 && cos>=2)return true;
        return false;
    }

    public static boolean isVowel(char al){
        if (al=='a'||al=='e'||al=='i'||al=='o'||al=='u'){
            return true;
        }else return false;
    }
}
