package algorithm.입출력과사칙연산;

import java.util.Scanner;

public class BOJ1000 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in); //BufferedReader 사용하여 다시 해보자
        int a = in.nextInt();
        int b = in.nextInt();

        System.out.println(a+b);
        in.close();
    }
}
