package algorithm.문자열;

public class P괄호변환 {
    public String solution(String p) {
        String answer = cal(p);
        return answer;
    }

    static String cal(String w){
        // 1. 입력이 빈 문자열인 경우, 빈 문자열을 반환
        if(w.equals("")) return "";

        // 2. u, v로 분리
        int b = 0;
        int idx = 0;
        for(int i = 0; i < w.length(); i++){
            if(w.charAt(i) == '(') b++;
            else b--;

            if(b == 0) {
                idx = i;
                break;
            }
        }

        String u = w.substring(0, idx + 1);
        String v = w.substring(idx + 1);

        // 3. u가 "올바른 괄호 문자열"이라면
        String answer = "";
        if(isCorrect(u)){
            answer += u;
            answer += cal(v); // 3-1
        } else {
            // 4. u가 "올바른 괄호 문자열"이 아니라면
            answer += "("; // 4-1
            answer += cal(v); // 4-2
            answer += ")"; // 4-3
            for(int i = 1; i < u.length() - 1; i++){
                if(u.charAt(i) == '(') answer += ")";
                else answer += "(";
            }
        }
        return answer;
    }

    static boolean isCorrect(String s){
        // '('이라면 +1 한다.
        // ')'이라면 -1 한다. 만약 중간에 음수가 되면 false
        int sum = 0;
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '(') sum++;
            else sum--;

            if(sum < 0) return false;
        }

        return true;
    }
}
