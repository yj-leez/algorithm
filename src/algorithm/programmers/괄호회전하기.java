package algorithm.programmers;

import java.util.*;

class 괄호회전하기 {
    public int solution(String s) {
        int answer = 0;

        Stack<Character> st = new Stack<>();
        boolean right = true;
        for(int i = 0; i < s.length(); i++){


            // 올바른 괄호 문자열인지 확인
            right = true;
            for(int j = 0; j < s.length(); j++){
                char ch = s.charAt(j);

                if(ch == '[' || ch == '(' || ch == '{'){
                    st.push(ch);
                } else if(!st.empty()){
                    if((ch == ']' && st.peek()=='[') || (ch == ')' && st.peek() == '(') || (ch == '}' && st.peek() == '{') ){
                        st.pop();
                    } else {
                        right = false;
                    }
                } else { right = false; }

                //
                if(!right) break;

            }
            if(right && st.empty()) answer++;

            //문자열 재배치
            String tmp = s.substring(1) + s.charAt(0);
            s = tmp;

        }



        return answer;
    }
}
