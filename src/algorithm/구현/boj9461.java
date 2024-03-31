package algorithm.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj9461 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testcaseNum = Integer.parseInt(br.readLine());
        int [] testcases = new int[testcaseNum];

        int max = 0;
        for (int i = 0; i < testcaseNum; i++) {
            testcases[i] = Integer.parseInt(br.readLine());
            if (testcases[i] > max) max = testcases[i];
        }

        long [] edge = new long[max];
        edge[0] = 1; edge[1] = 1; edge[2] = 1; edge[3] = 2; edge[4] = 2;
        for (int i = 5; i < max; i++) {
            edge[i] = edge[i - 5] + edge[i - 1];
        }

        for (int i = 0; i < testcaseNum; i++) {
            System.out.println(edge[testcases[i] - 1]);
        }

    }
}
