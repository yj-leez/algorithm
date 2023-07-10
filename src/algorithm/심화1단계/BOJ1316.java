package algorithm.심화1단계;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1316 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        boolean[] arr = new boolean[26];

        boolean word = true;
        int wordTotal = 0;
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            word = true;
            for (int j = 0; j < arr.length; j++) {
                arr[j] = true;
            }
            for (int j = 0; j < s.length(); j++) {
                if(arr[s.charAt(j)-'a']== false &&(s.charAt(j-1)!=s.charAt(j))) word = false;
                else arr[s.charAt(j)-'a']=false;
            }
            if(word) wordTotal++;
        }
        System.out.println(wordTotal);
    }
}
