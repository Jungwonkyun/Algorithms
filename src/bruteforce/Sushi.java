package bruteforce;

import java.io.*;
import java.util.StringTokenizer;

//method 1: brute Force 접근    method 2: sliding Window 접근 
public class Sushi {
	
	static int [] sushi;
	static int N,d,k,c,maximum;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st =  new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());  
		d = Integer.parseInt(st.nextToken());  
		k = Integer.parseInt(st.nextToken());  
		c = Integer.parseInt(st.nextToken());  
		maximum = Integer.MIN_VALUE;
		
		
		sushi = new int[N];
		
		for(int i = 0; i < N; i++) {
			sushi[i] = Integer.parseInt(br.readLine());
		}
	
		
		//bruteForce();
		slidingWindow();
		System.out.println(maximum);
	}
	
	public static void bruteForce() {
		for(int i = 0; i < N; i++) {
			int cnt = 0; 
			boolean []visited = new boolean[d+1];
			
			for(int j = 0; j < k; j++) {
				if(!visited[sushi[(i+j)%N]]) {
					cnt++;
					visited[sushi[(i+j)%N]] = true;
				}
				
			}

			if (!visited[c])cnt++;
			maximum = Math.max(maximum,cnt);
		}
	}
	
	public static void slidingWindow() {
		
		int []visited = new int[d+1];
		
		//처음 initialize 하는 부분 
		int max, cnt = 0;
		for(int i = 0; i < k; i++) {
			if(visited[sushi[i]] == 0)cnt++; 
			visited[sushi[i]]++;
		}
		
		max = cnt;
		
		for(int i = 1; i < N; i++) {
			if(max <= cnt) {
				if(visited[c] == 0) {
					max = cnt+1;
				}else max = cnt;
			}
			//sliding window 맨 앞에 거 방문횟수 1개 빼주기 
			visited[sushi[i-1]]--;
			//만약에 0이라면 먹은 초밥 종류가 줄어듦 아니라면 그냥 2번 먹은 걸 1번으로 바뀌는 거라서 종류엔 변함 x
			if(visited[sushi[i-1]] == 0)cnt--;
			
			
			//만약에 얘가 처음방문이면 먹은 초밥 종류가 하나 늘어난 거기 때문에 카운트 해줘야
			if(visited[sushi[(i+k-1)%N]] == 0) cnt++;
			visited[sushi[(i+k-1)%N]]++;
		}
		
		maximum = max;
	}

}
