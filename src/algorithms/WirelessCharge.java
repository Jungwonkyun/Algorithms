package algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


class BC{
	//몇 번 BC인지 체크
	int num;
	int x;
	int y;
	int C;
	int P; 
	
	BC(int num, int x,int y,int C, int P){
		this.x = x-1;
		this.y = y-1;
		this.C = C;
		this.P = P;
		this.num = num;
	}	
}

public class WirelessCharge {
	
	static int T;
	static int M;
	static int A;
	static int[] MoveA;
	static int[] MoveB;
	static String[] input;
	static BC[] BCInfo;
	static int [] dx = {0,-1,0,1,0};
	static int [] dy = {0,0,1,0,-1};
	static ArrayList<Integer>[] map;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine()); 
		
		input = br.readLine().split(" "); 
		M = Integer.parseInt(input[0]);
		A = Integer.parseInt(input[1]);
		
		MoveA = new int[M];
		MoveB = new int[M];
		BCInfo = new BC[A];
		
		input = br.readLine().split(" "); 
		for(int i = 0; i < M; i++) {
			MoveA[i] = Integer.parseInt(input[i]);
		}
		
		input = br.readLine().split(" "); 
		for(int i = 0; i < M; i++) {
			MoveB[i] = Integer.parseInt(input[i]);
		}
		
		for(int i = 0; i < A; i++) {
			input = br.readLine().split(" "); 
			int [] temp = new int[4];
			BC bc = null;
			for(int j = 0; j < 4; j++) {
				temp[j] = Integer.parseInt(input[j]);
			}
			bc = new BC(0,temp[0],temp[1],temp[2],temp[3]);
			BCInfo[i] = bc;
		}
	}
	
	
	
	
	
	
	

}
