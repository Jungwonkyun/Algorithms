package algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

//꼭짓점 클래스
class Point {

	int x;
	int y;

	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

}

//봉우리 클래스 
class Peak implements Comparable<Peak> {

	int start;
	boolean isStart;

	public Peak(int start, boolean isStart) {
		super();
		this.start = start;
		this.isStart = isStart;
	}

	@Override
	public int compareTo(Peak o) {

		// x값 기준으로 정렬하기
		return this.start - o.start;
	}

}

public class CutCurve {
	static String[] in;
	static int NoContain = 0;
	static int NoCover = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		ArrayList<Point> pointAry = new ArrayList<>();
		ArrayList<Peak> peakAry = new ArrayList<>();
		int StartX = Integer.MAX_VALUE;
		int StartY = Integer.MAX_VALUE;
		int x, y, index = 0;

		for (int i = 0; i < N; i++) {
			in = br.readLine().split(" ");
			x = Integer.parseInt(in[0]);
			y = Integer.parseInt(in[1]);

			// 가장 왼쪽 아래부터 시작한다
			if (x < StartX && y < 0) {
				StartX = x;
				StartY = y;
				index = i;
			}

			Point p = new Point(x, y);
			pointAry.add(p);
		}

		int prevX = StartX;
		int prevY = StartY;
		int len = pointAry.size();

		// 왼쪽 제일 아래부터 탐색한다. but 중간에 있을 수도 있으니까 모듈러 연산 이용
		for (int i = 0; i < len; i++) {
			Point nowPoint = pointAry.get((index + i) % len);

			// 이전 y가 음수 현재 y가 양수일 때 -> 시작하는 봉우리
			if (prevY < 0 && nowPoint.y > 0) {

				// 이전 관통 선분이 봉우리 끝이면 지금 관통 선분은 무조건 봉우리 시작부분이다
				prevX = nowPoint.x;
				prevY = nowPoint.y;
			}

			// 봉우리가 끝나는 지점일 때 업데이트 해준다
			else if (prevY > 0 && nowPoint.y < 0) {
				int minX = Math.min(prevX, nowPoint.x);
				int maxX = Math.max(prevX, nowPoint.x);

				prevX = nowPoint.x;
				prevY = nowPoint.y;

				Peak left = new Peak(minX, true);
				Peak right = new Peak(maxX, false);
				peakAry.add(left);
				peakAry.add(right);
			}
		}

		// 커지는 순서로 봉우리를 정렬한다
		Collections.sort(peakAry);

		// 봉우리를 담는 스택
		Stack<Integer> peakStack = new Stack<>();
		int lenAry = peakAry.size();
		int numPeak = 0;

		for (int i = 0; i < lenAry; i++) {
			boolean check = peakAry.get(i).isStart;

			// 만약 봉우리가 시작하는 변이라면 스택에 담는다
			if (check == true) {
				peakStack.add(numPeak);
			}

			// 만약 봉우리가 끝나는 변이라면
			else {
				// stack의 가장 윗부분을 빼주고
				int left = peakStack.pop();

				// 만약 스택이 비워져 있다면 얘는 noCover이다
				if (peakStack.isEmpty()) {
					NoCover++;
				}

				// 만약 스택이 차있고 왼쪽 괄호 바로 다음에 오른쪽 괄호가 오는 경우는 NoContain (( )경우
				// 포함은 되어있지만 포함하지는 않는다
				if (left == numPeak) {
					NoContain++;
				}

				// 아닌 경우엔 포함되어 있으면서 포함도 한다
				// 시작하는 봉우리를 만날 때 마다 봉우리 갯수를 늘려준다
				numPeak++;
			}

		}

		System.out.println(NoCover + " " + NoContain);
	}

}
