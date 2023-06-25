package algorithm.조건문;

import java.util.Scanner;

public class BOJ1330 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int a = in.nextInt();
        int b = in.nextInt();
/**
        if(a > b) System.out.println(">");
        else if (a < b) System.out.println("<");
        else System.out.println("==");
**/
        String s = ((a > b)? ">" : ((a < b)? "<" : "=="));
        System.out.println(s);

        in.close();
    }
}
