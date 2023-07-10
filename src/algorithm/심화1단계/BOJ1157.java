package algorithm.심화1단계;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1157 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int[] arr = new int[26];


        for (int i = 0; i < s.length(); i++) {
            if('A' <= s.charAt(i) && s.charAt(i) <= 'Z' ) arr[s.charAt(i) - 65]++;
            else arr[s.charAt(i) - 97]++;
        }
        int max = 0; int maxIndex = -1;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] > max){
                max = arr[i];
                maxIndex = i;
            }
            else if(arr[i] == max) maxIndex = -2;
        }
        System.out.println((char)(maxIndex+65));
    }
}
