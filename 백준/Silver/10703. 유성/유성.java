import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int R, S;
    static char pic[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int min = Integer.MAX_VALUE;
        R = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        pic = new char[R][S];

        //입력 받는 칸
        for (int i = 0; i < R; i++) {
            String temp = br.readLine();
            for (int j = 0; j < S; j++) {
                pic[i][j] = temp.charAt(j);
            }
        }

        //운석과 땅사이에 존재하는 길이 구하기
        for (int j = 0; j < S; j++) {
            int cnt = 0;
            for (int i = 0; i < R; i++) {
                if (i + 1 < S && pic[i][j] == 'X' && pic[i + 1][j] == '.') {
                    for (int k = i + 1; k < R - 1; k++) {
                        if (pic[k][j] == '#')
                            break;
                        if (pic[k][j] == 'X') {
                            cnt = 0;
                            break;
                        }
                        if (pic[k][j] == '.')
                            cnt++;
                    }
                }
                if (cnt > 0 && pic[i][j] == '#') {
                    min = Math.min(cnt, min);
                    cnt = 0;
                }
            }
        }

        for (int j = 0; j < S; j++) {
            for (int i = R - 1; i >= 0; i--) {
                if (pic[i][j] == 'X' && pic[i + min][j] == '.') {
                    char temp = pic[i][j];
                    pic[i][j] = pic[i + min][j];
                    pic[i + min][j] = temp;
                }
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < S; j++) {
                sb.append(pic[i][j]);
            }
            sb.append('\n');
        }

        System.out.println(sb.toString());
    }
}