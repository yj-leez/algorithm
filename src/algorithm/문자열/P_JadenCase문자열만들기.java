package algorithm.문자열;

public class P_JadenCase문자열만들기 {
    public String solution(String s) {
        String answer = "";
        for(int i = 0; i < s.length(); i++){
            // 단어의 첫 문자인 경우
            if(i == 0 || (i > 0 && s.charAt(i - 1) == ' ')){
                // 그 문자가 영어인 경우
                if('a' <= s.charAt(i) && s.charAt(i) <= 'z'){
                    answer += Character.toUpperCase(s.charAt(i));
                    continue;
                }
            } else {
                // 단어의 첫 문자가 아닌데 대문자인 경우
                if('A' <= s.charAt(i) && s.charAt(i) <= 'Z'){
                    answer += Character.toLowerCase(s.charAt(i));
                    continue;
                }
            }
            answer += s.charAt(i);
        }
        return answer;
    }
}
