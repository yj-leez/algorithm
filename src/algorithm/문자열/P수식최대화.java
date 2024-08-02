package algorithm.문자열;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class P수식최대화 {

    static long solution(String expression) {
        List<String> tokens = new ArrayList<>();

        // 정규표현식 패턴
        String regex = "(\\d+|[+\\-*])";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(expression);

        while (matcher.find()){
            tokens.add(matcher.group());
        }

        long ans = 0L;
        ans = Math.max(ans, calculate(new ArrayList<>(tokens), new String[]{"+", "-", "*"}));
        ans = Math.max(ans, calculate(new ArrayList<>(tokens), new String[]{"+", "*", "-"}));
        ans = Math.max(ans, calculate(new ArrayList<>(tokens), new String[]{"-", "+", "*"}));
        ans = Math.max(ans, calculate(new ArrayList<>(tokens), new String[]{"-", "*", "+"}));
        ans = Math.max(ans, calculate(new ArrayList<>(tokens), new String[]{"*", "+", "-"}));
        ans = Math.max(ans, calculate(new ArrayList<>(tokens), new String[]{"*", "-", "+"}));

        return ans;

    }

    static long calculate(List<String> tokens, String[] signs) {

        for(String sign : signs){
            int idx = 0;
            while(idx < tokens.size()){
                if(tokens.get(idx).equals(sign)){
                    long a = Long.parseLong(tokens.get(idx - 1));
                    long b = Long.parseLong(tokens.get(idx + 1));
                    long cal = 0L;
                    switch(sign){
                        case "+": cal = a + b; break;
                        case "-": cal = a - b; break;
                        case "*": cal = a * b; break;
                    }

                    tokens.set(idx - 1, String.valueOf(cal));
                    tokens.remove(idx);
                    tokens.remove(idx);
                } else {
                    idx++;
                }
            }
        }

        return Math.abs(Long.parseLong(tokens.get(0)));
    }


}
