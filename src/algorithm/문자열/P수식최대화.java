package algorithm.문자열;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class P수식최대화 {
    public long solution(String expression) {
        List<String> tokens = new ArrayList<>();

        // 정규표현식 패턴
        String regex = "(\\d+|[+\\-*])";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(expression);

        while (matcher.find()){
            tokens.add(matcher.group());
        }

        for(String token : tokens){
            System.out.println(token);
        }


    }


}
