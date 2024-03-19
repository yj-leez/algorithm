package algorithm.programmers;

import java.util.*;

class 뉴스클러스트링 {
    public static void main(String[] args) {
        String str1 = "FRANCE";
        String str2 = "french";
        System.out.println(solution(str1, str2));
    }
    public static int solution(String str1, String str2) {
        Set<String> union = new HashSet<>();
        Map<String, Integer> cntOfStr1 = new HashMap<>();
        Map<String, Integer> cntOfStr2 = new HashMap<>();
        for(int i = 0; i < str1.length() - 1; i++){

            if((Character.isLetter(str1.charAt(i)) && Character.isLetter(str1.charAt(i+1)))){
                String tmp = (String.valueOf(str1.charAt(i)) + String.valueOf(str1.charAt(i+1))).toLowerCase();
                if(cntOfStr1.containsKey(tmp)){
                    cntOfStr1.put(tmp, cntOfStr1.get(tmp)+1);
                } else {
                    union.add(tmp);
                    cntOfStr1.put(tmp, 1);
                }
            }
        }

        for(int i = 0; i < str2.length() - 1; i++){

            if(Character.isLetter(str2.charAt(i)) && Character.isLetter(str2.charAt(i+1))){
                String tmp = (String.valueOf(str2.charAt(i)) + String.valueOf(str2.charAt(i+1))).toLowerCase();
                if(cntOfStr2.containsKey(tmp)){
                    cntOfStr2.put(tmp, cntOfStr2.get(tmp)+1);
                } else {
                    union.add(tmp);
                    cntOfStr2.put(tmp, 1);
                }
            }
        }

        int unionSize = 0;
        int intersectionSize = 0;
        for(String s : union){

            if(cntOfStr1.containsKey(s) && cntOfStr2.containsKey(s)){
                unionSize += Math.max(cntOfStr1.get(s), cntOfStr2.get(s));
                intersectionSize += Math.min(cntOfStr1.get(s), cntOfStr2.get(s));
            } else if(cntOfStr1.containsKey(s)){
                unionSize += cntOfStr1.get(s);
            } else {
                unionSize += cntOfStr2.get(s);
            }
        }

        float answer = (float)intersectionSize/unionSize;
        answer *= 65536;
        return (int)answer;
    }
}
