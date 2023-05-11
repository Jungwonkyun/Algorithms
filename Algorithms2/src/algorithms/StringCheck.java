package algorithms;

/*
 * softwarecontest
 * 9
 * so
 * ware
 * softftware
 * con
 * conte
 * test
 * t
 * s
 * 
 * queue에 넣어서 가능한 것들 판별 
 * 뒤에서 부터 판별 
 * 
 * depth 1:
 * test -> 가능 replace => "softwarecon" Q in
 * t -> 가능 replace => "softwarecontes" Q in
 * 
 * depth 2:
 * con -> 가능 replace => "software" Q in
 * s -> 가능 replace => "softwareconte" Q in
 * 
 * depth 3:
 * ware -> 가능 replace => "soft" Q in
 * ftware -> 가능 replace => "so" Q in
 * conte -> software set에 의해 걸러짐
 * 
 * 부분 문자열 == 목표 문자열 이면 만들 수 있다 
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class StringCheck {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String target = br.readLine();
		int N = Integer.parseInt(br.readLine());
		Set<String>wordSet = new HashSet<>();
		
		ArrayList<String> words = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			words.add(br.readLine());
		}

		Queue<String> wordQ = new LinkedList<>();
		wordQ.offer(target);
		
		//BFS로 접근
		while (!wordQ.isEmpty()) {
			int Qsize = wordQ.size();
			for (int q = 0; q < Qsize; q++) {
				String now = wordQ.poll();
				loop: for (int i = 0; i < N; i++) {
					int wordSize = words.get(i).length();
					if(wordSize>now.length())continue;
					
					// 끝 글자가 같을 때
					if (words.get(i).charAt(wordSize - 1) == now.charAt(now.length() - 1)) {
						//해당글자가 부분문자열이 되는지 확인 
						for (int j = 1; j <= wordSize; j++) {
							if (words.get(i).charAt(wordSize - j) != now.charAt(now.length() - j))
								continue loop;
						}
						String newWord = now.substring(0, now.length() - wordSize);
						
						//현재 문자열과 부분 문자열이 같다면 가능하다
						if (now.length() == wordSize ) {
							System.out.println(1);
							System.exit(0);
						}
						
						//set에 넣어가면서 중복되는 문자는 추가하지 않는다 
						if(wordSet.contains(newWord))continue;
						wordQ.add(newWord);
						wordSet.add(newWord);
					}
				}
			}
		}
  
		System.out.println(0);

	}

}