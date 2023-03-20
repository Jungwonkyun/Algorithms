package algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CardGame {
	static int N;
	static int M;
	static int K;
	static int[] baseCard;
	static boolean[] pickedCard;
	static int[] cardList;
	static int[] parent;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		//N개의 카드 중에서 어떤 M개의 카드가 뽑혔는지 확인하는 부울 배열
		pickedCard = new boolean[N+1];
		//뽑은 M개의 카드들이 저장되어있는 리스트
		cardList = new int[M];
		//철수가 뽑은 카드들
		baseCard = new int[K];
		//부모노드 초기화 
		parent = new int[M];
		
		for(int i = 0; i < M; i++) {
			parent[i] = i;
		}
		
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < M; i++) {
			int now = Integer.parseInt(st.nextToken());
			pickedCard[now] = true;
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < K; i++) {
			int now = Integer.parseInt(st.nextToken());
			baseCard[i] = now;
		}
		
		//O(M)을 이용한 counting sort
		int c = 0;
		for(int i = 0; i < N+1; i++) {
			if(pickedCard[i]==true) {
				cardList[c] = i;
				c++;
			}
		}
		
		for(int i = 0; i < K; i++) {
			int nowCard = baseCard[i];
			int idx = binarySearch(nowCard); //해당카드가 있는 index 찾기
			idx = find(idx); //현재 idx의 루트 노드 찾기
			
			sb.append(cardList[idx]);
			sb.append('\n');
			
			if(idx!=M-1)union(idx, idx+1);
		}

		System.out.println(sb);
	}
	
	//이분탐색으로 값 찾기 
	public static int binarySearch(int target) {
		
		int left = 0;
		int right = M - 1;
		int mid;
		
		while(left<right) {
			mid = (left+right)/2;
			if(cardList[mid]<target)left = mid+1;
			else if(cardList[mid]>target)right = mid;
			//값을 찾은 경우 그 인덱스를 넘겨준다 
			else return mid+1;
		}
		
		return left;
	}
	
    //a,b의 대소관계에 따라서 부모노드를 바꿔준다 
    public static void union(int a, int b) {
        // 최상위 부모 찾기
        int x = find(a);
        int y = find(b);
        
        //여기서 index가 큰 쪽으로 부모를 합쳐줘야 한다
        if (x > y) {
            parent[y] = x;
        } else {
            parent[x] = y;
        }
    }
    
    //a의 부모노드를 찾는 연산 
    public static int find(int a) {
        if (parent[a] == a)
            return a;
        return parent[a] = find(parent[a]);
    }

}
