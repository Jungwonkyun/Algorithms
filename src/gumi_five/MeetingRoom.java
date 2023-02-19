package gumi_five;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Time implements Comparable<Time>{
	
	int start;
	int end;
	
	public Time(int s, int e) {
		start = s;
		end = e;

	}

	@Override
	public int compareTo(Time o) {
		
		if(this.end == o.end) {
			return this.start - o.start;
		}
		
		return this.end - o.end;
	}
}


public class MeetingRoom {
	
	static Time [] timeline;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int time = Integer.parseInt(br.readLine());
		timeline = new Time[time];
	
		for(int i = 0; i < time; i++) {
			
			String [] in = br.readLine().split(" ");
			int s = Integer.parseInt(in[0]);
			int e = Integer.parseInt(in[1]);
			
			Time t = new Time(s,e);
			timeline[i] = t;
		}
		
		Arrays.sort(timeline);
		
		int result = 0;
		int now_e = 0;
		for(int i = 0; i < time; i++) {
			if (now_e > timeline[i].start)continue;
			result++;
			now_e = timeline[i].end;
			
		}
		
		System.out.println(result);
	}

}
