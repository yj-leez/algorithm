package algorithm.문자열;

import java.util.*;

public class P메뉴리뉴얼 {
    /**
     * 문자열 내 알파벳 순으로 정렬: str.toCharArray()로 char 배열로 변환 -> 정렬 -> String으로 변환
     * 문자열 내 부분집합 구하기: 재귀 활용
     */
    static Map<String, Integer> dic;
    static Map<Integer, Integer> maxCnt;

    public String[] solution(String[] orders, int[] course) {

        dic = new HashMap<>();
        maxCnt = new HashMap<>();
        for(String str : orders){
            char[] ch = str.toCharArray();
            Arrays.sort(ch);
            String tmp = new String(ch);
            for(int i = 0; i < course.length; i++){
                generateCombinations(tmp, course[i], 0, "");
            }
        }

        List<String> ans = new ArrayList<>();
        for(String key : dic.keySet()){
            if(dic.get(key) >= 2){
                if(dic.get(key) == maxCnt.get(key.length())){
                    ans.add(key);
                }
            }
        }
        String[] answer = ans.toArray(new String[ans.size()]);
        Arrays.sort(answer);

        return answer;
    }

    static void generateCombinations(String input, int size, int start, String current){
        if(current.length() == size){
            dic.put(current, dic.getOrDefault(current, 0) + 1);
            maxCnt.put(size, Math.max(maxCnt.getOrDefault(size, 0), dic.get(current)));
            return;
        }

        for(int i = start; i < input.length(); i++){
            generateCombinations(input, size, i + 1, current + input.charAt(i));
        }
    }


}