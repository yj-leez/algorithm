package algorithm.수학;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B9655 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int i = Integer.parseInt(br.readLine());

        if(i % 2 == 1) System.out.println("SK");
        else System.out.println("CY");
    }
}
