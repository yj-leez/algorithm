package algorithm.입출력과사칙연산;

import java.util.Scanner;

public class BOJ11382 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        long a = in.nextLong();
        long b = in.nextLong();
        long c = in.nextLong();

        System.out.println(a + b + c);
    }
}
/**
 * int형의 범위는 -2,147,483,648 ~ 2,147,483,647이므로
 * 1 ≤ A, B, C ≤ 10^12라면 int형의 범위를 넘을 수 있어서
 * long형 사용해야함.
 */
