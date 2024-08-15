package algorithm.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B9251 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str1 = br.readLine().split("");
        String[] str2 = br.readLine().split("");

        int[][] arr = new int[str1.length + 1][str2.length + 2];

        for (int i = 1; i <= str1.length; i++) {
            for (int j = 1; j <= str2.length; j++) {

                if (str1[i - 1].equals(str2[j - 1])){
                    arr[i][j] = arr[i - 1][j - 1] + 1;
                } else {
                    arr[i][j] = Math.max(arr[i - 1][j], arr[i][j - 1]);
                }
            }
        }

        System.out.println(arr[str1.length][str2.length]);

    }
}
