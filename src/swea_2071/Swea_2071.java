package swea_2071;
import java.util.Scanner;
import java.util.Arrays; 



//2071. 평균값 구하기
public class Swea_2071 {
  public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      
      //next() 메서드는 공백 이전까지의 문자열을 입력받는 메서드
      System.out.println(sc.nextInt());      
      int T = sc.nextInt();    // 반복 횟수
      for(int i=0; i<T; i++) {
          float sum = 0;
          for(int j=0; j<10; j++) {    // 10개의 수 입력
              int num = sc.nextInt();
              sum += num;
          }
          System.out.println("#" + (i+1)+ " " + Math.round(sum/10));
      }
  }
}
