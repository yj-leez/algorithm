package algorithm.입출력과사칙연산;

import java.util.Scanner;

public class BOJ10430 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int a = in.nextInt();
        int b = in.nextInt();
        int c = in.nextInt();

        System.out.println((a + b) % c);
        System.out.println(((a % c) + (b % c)) % c );
        System.out.println((a * b) % c);
        System.out.println(((a % c) * (b % c)) % c );
        in.close();
    }
}
