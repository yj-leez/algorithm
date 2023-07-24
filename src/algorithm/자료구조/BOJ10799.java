package algorithm.자료구조;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ10799 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<Integer> stack = new Stack<>();
        String s = br.readLine();

        int total= 0;
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i)=='(') stack.push(i);
            else {
                int num = stack.pop();
                if(num == i-1) total += stack.size();
                else total += 1;
            }
        }
        System.out.println(total);

    }
}
