package algorithm.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj1476 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int d = 1; int e = 1; int f = 1; int year = 1;
        while(!(d==a) || !(e==b) || !(f==c)){
            if(d < 15) d++;
            else d = 1;

            if(e < 28) e++;
            else e = 1;

            if(f < 19) f++;
            else f = 1;

            year++;
        }

        System.out.println(year);

    }
}
