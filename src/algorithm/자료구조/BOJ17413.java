package algorithm.자료구조;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ17413 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        boolean tag = false;

        String s = br.readLine();
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i)=='<'){
                while(!stack.empty()){
                    sb.append(stack.pop());
                }
                tag = true;
                sb.append(s.charAt(i));
            } else if (s.charAt(i)=='>'){
                tag = false;
                sb.append(s.charAt(i));
            } else if (tag) {
                sb.append(s.charAt(i));
            } else {
                if(s.charAt(i)==' '){
                    while(!stack.empty()){
                        sb.append(stack.pop());
                    }
                    sb.append(' ');
                } else stack.push(s.charAt(i));
            }
            // 계속 반복
        }

        while(!stack.empty()){
            sb.append(stack.pop());
        }

        System.out.println(sb);
        br.close();

    }
}
