package algorithm.자료구조;

import java.util.*;

public class P올바른괄호 {
    boolean solution(String s) {
        Queue<Integer> q = new ArrayDeque<>();

        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '('){
                q.add(1);
            } else if(s.charAt(i) == ')'){
                if(q.isEmpty()) return false;
                else q.poll();
            }
        }

        if(q.size() == 0) return true;
        else return false;
    }
}