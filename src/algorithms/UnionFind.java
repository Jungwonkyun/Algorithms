package algorithms;

public class UnionFind {

    public static int[] parent;
   
    public static void main(String[] args) {
    	//문제 input 조건 따라서 N 적절히 설정
        parent = new int[10];
        
        //초기화
        for (int i = 0; i < 10; i++) {
            parent[i] = i;
        }
    }
    
    //a,b의 대소관계에 따라서 부모노드를 바꿔준다 
    public static void union(int a, int b) {
        // 최상위 부모 찾기
        int x = find(a);
        int y = find(b);

        if (x < y) {
            parent[y] = x;
        } else {
            parent[x] = y;
        }
    }
    
    //a와 b의 부모가 같은지 확인
    public static boolean equals(int a, int b) {
        int x = find(a);
        int y = find(b);
        return x == y;
    }
    
    //a의 부모노드를 찾는 연산 
    public static int find(int a) {
        if (parent[a] == a)
            return a;
        return parent[a] = find(parent[a]);
    }
}