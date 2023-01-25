package swea_1961;
import java.util.*;

public class Swea_1961 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int test_case = sc.nextInt();
		for(int i=1;i<test_case+1;i++) {
			int N = sc.nextInt();
			String[][] input_array = new String[N][N];
			String[][] output_array = new String[N][3];
			for (int j=0;j<N;j++) {
				for (int k=0;k<N;k++) {
					input_array[j][k] = sc.next();
				}
			}
			
			//degree 90
			for(int j = 0 ;j < N;j++) {
				String temp = "";
				for(int k = N-1; k>=0; k--) {
					temp+=input_array[k][j];
				}
				output_array[j][0] = temp;
			}
			
			//degree 180
			for(int j = N-1 ;j >= 0;j--) {
				String temp = "";
				for(int k = N-1; k>=0; k--) {
					temp+=input_array[j][k];
				}
				output_array[N-1-j][1] = temp;
			}
			
			//degree 270
			for(int j = N-1 ;j >= 0;j--) {
				String temp = "";
				for(int k = 0; k < N; k++) {
					temp+=input_array[k][j];
				}
				output_array[N-1-j][2] = temp;
			}
			
			//System.out.println(Arrays.deepToString(output_array));
			System.out.println(String.format("#%d",i));
			
			for(int j = 0 ;j < N;j++) {
				for(int k = 0 ;k < 3; k++) {
					System.out.print(output_array[j][k]+" ");
				}
				System.out.println();
			}
		}
		
	}

}
