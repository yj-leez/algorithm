package algorithm.문자열;

import java.util.*;

public class P짝지어제거하기 {
    public int solution(String str) {
        String[] split = str.split("");
        Stack<String> stack = new Stack<>();

        for (String s : split) {
            if (!stack.isEmpty() && stack.peek().equals(s)) {
                stack.pop();
            } else {
                stack.push(s);
            }
        }

        return stack.isEmpty() ? 1 : 0;
    }
}