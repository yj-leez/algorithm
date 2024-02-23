package algorithm.자료구조;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class boj2504 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();

        Stack<Character> stack = new Stack<>();
        int tmp = 1;
        int answer = 0;

        for (int i = 0; i < line.length(); i++) {
            char ch = line.charAt(i);
            switch (ch){
                case '(':
                    stack.push(ch);
                    tmp *= 2;
                    break;
                case ')':
                    if(!stack.empty() && stack.peek() == '('){
                        if (line.charAt(i - 1) == '(') answer += tmp;
                        tmp /= 2;
                        stack.pop();
                        break;
                    } else {
                        System.out.println(0);
                        return;
                    }
                case '[':
                    stack.push(ch);
                    tmp *= 3;
                    break;
                case ']':
                    if (!stack.empty() && stack.peek() == '['){
                        if (line.charAt(i - 1) == '[') answer += tmp;
                        tmp /= 3;
                        stack.pop();
                        break;
                    } else {
                        System.out.println(0);
                        return;
                    }
            }
        }
        if (stack.empty()) System.out.println(answer);
        else System.out.println(0);
    }
}
