package algorithm.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj1149 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] price = new int[n][3];
        int[][] colorOfHouse = new int[n][3];
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                price[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 초기화
        colorOfHouse[0][0] = price[0][0];
        colorOfHouse[0][1] = price[0][1];
        colorOfHouse[0][2] = price[0][2];

        //dp
        for (int i = 1; i < n; i++) {
            colorOfHouse[i][0] = Math.min(colorOfHouse[i-1][1], colorOfHouse[i-1][2]) + price[i][0];
            colorOfHouse[i][1] = Math.min(colorOfHouse[i-1][0], colorOfHouse[i-1][2]) + price[i][1];
            colorOfHouse[i][2] = Math.min(colorOfHouse[i-1][0], colorOfHouse[i-1][1]) + price[i][2];
        }

        System.out.println(Math.min(Math.min(colorOfHouse[n-1][0], colorOfHouse[n-1][1]),colorOfHouse[n-1][2]));

    }
}
