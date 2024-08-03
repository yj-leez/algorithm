package algorithm.문자열;

public class P문자열압축 {

    static int solution(String s) {
        int answer = s.length();
        // i: 문자열의 길이
        for(int size = 1; size <= s.length() / 2; size++){
            String[] str;
            if(s.length() % size == 0) str = new String[s.length() / size];
            else str = new String[s.length() / size + 1];

            int idx = -1; // idx: s 배열의 인덱스
            for(int i = 0; i < s.length(); i++){
                if(i % size == 0){
                    str[++idx] = "" + s.charAt(i);
                } else{
                    str[idx] += s.charAt(i);
                }
            }
            answer = Math.min(answer, cal(str));
        }

        return answer;
    }

    static int cal(String[] s){
        StringBuilder sb = new StringBuilder();
        String remembered = s[0];
        int cnt = 1;

        for(int i = 1; i < s.length; i++){
            if(s[i].equals(remembered)){
                cnt++;
            } else{
                // 전의 문자열과 다른 경우
                if(cnt == 1) sb.append(remembered);
                else sb.append(String.valueOf(cnt) + remembered);

                remembered = s[i];
                cnt = 1;
            }
        }

        if(cnt == 1) sb.append(remembered);
        else sb.append(String.valueOf(cnt) + remembered);

        return sb.toString().length();
    }
}
