package algorithm.문자열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ27866 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int index = Integer.parseInt(br.readLine());

        System.out.println(s.charAt(index-1));
        br.close();
    }
}
