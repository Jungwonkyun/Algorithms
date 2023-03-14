package pocketBallGame;

import java.net.*;
import java.io.*;

public class Sample_Code {

	// User and Game Server Information
	static final String NICKNAME = "구미5반_정원균_자바";
	static final String HOST = "127.0.0.1";
	static final int PORT = 1447; // Do not modify

	// predefined variables(Do not modify these values)
	static final int TABLE_WIDTH = 254;
	static final int TABLE_HEIGHT = 124;
	static final int NUMBER_OF_BALLS = 5;
	static final int[][] HOLES = { { 0, 0 }, { 0, 260 }, { 0, 130 }, { 130, 260 } };
	static double[][] balls = {{40,130}, { 30, 200 }, { 100, 200 }, { 100, 100 }, { 30, 100 } };

	public static void main(String[] args) {

		double angle = 0;
		double power = 100;


		System.out.println(angle);
		
		//hole에서 가장 가까운 값 찾기 
		int what = 0;
		double min = Double.MAX_VALUE;
		for(int i = 0; i < 4; i++) {
			double tempDist = Math.sqrt(Math.pow(balls[1][0]-HOLES[i][0],2) + Math.pow(balls[1][1]-HOLES[i][1],2));
			
			if(tempDist<min) {
				what = i;
				min = tempDist;
			}
		}
		
		double dx = balls[0][0] - HOLES[what][0];
		double dy = balls[0][1] - HOLES[what][1];
		
		double degree1 = Math.atan2(Math.abs(dy), Math.abs(dx));
		//degree1 = Math.toDegrees(degree1);
		
		//System.out.println(degree1);
		
		//칠 공에서 구멍까지의 거리
		double d1  = Math.sqrt(Math.pow(balls[0][0]-HOLES[what][0],2) + Math.pow(balls[0][1]-HOLES[what][1],2));
		//목적 공에서 구멍까지의 거리
		double d2  = Math.sqrt(Math.pow(balls[1][0]-HOLES[what][0],2) + Math.pow(balls[1][1]-HOLES[what][1],2));
		//칠 공에서 목적공 까지의 거리 
		double d3  = Math.sqrt(Math.pow(balls[0][0]-balls[1][0],2) + Math.pow(balls[0][1]-balls[1][1],2));
		
		System.out.println(d1+" "+d2+" "+d3);
		
		double degree2 = Math.acos((Math.pow(d2, 2)+Math.pow(d1, 2)-Math.pow(d3, 2))/(2*d2*d1));

//		degree2 = Math.toDegrees(degree2);
//		System.out.println(degree2);
		
		double d4 = Math.sqrt(Math.pow(d1, 2)+Math.pow((d2+5.74),2) - 2*d1*(d2+5.74)*Math.cos(degree2));
		
		System.out.println(Math.pow(d1, 2)+" "+Math.pow((d2+5.74),2)+" "+2*d1*(d2+5.74)*Math.cos(degree2));
		
		System.out.println(d4);
		
		double degree3 = Math.acos((Math.pow(d1, 2)+Math.pow(d4,2)-Math.pow((d2+5.74),2))/(2*d1*d4));
		
		System.out.println(Math.toDegrees(degree3));
		
		
		
		
		
		
		
}}
