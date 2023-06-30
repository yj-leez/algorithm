package algorithm.반복문;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ25304 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int totalPrice = Integer.parseInt(br.readLine());
        int totalNum = Integer.parseInt(br.readLine());

        StringTokenizer st;
        int checkPrice = 0;
        for (int i =0; i<totalNum; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            checkPrice += a*b;
        }

        if(checkPrice == totalPrice) System.out.println("Yes");
        else System.out.println("No");

    }
}
