package algorithm.slidingWindow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B1522 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        char[] chars = new char[s.length()];
        int aCnt = 0;
        for (int i = 0; i < chars.length; i++) {
            chars[i] = s.charAt(i);
            if (chars[i] == 'a') aCnt++;
        }

        int bCnt = 0;
        for (int i = 0; i < aCnt; i++) {
            if (chars[i] == 'b') bCnt++;
        }

        int bCntMin = bCnt;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == 'b') bCnt--;
            int idx = i + aCnt >= chars.length ? i + aCnt - chars.length : i + aCnt;
            if (chars[idx] == 'b') bCnt++;

            if (bCnt < bCntMin) bCntMin = bCnt;
        }

        System.out.println(bCntMin);
    }
}
