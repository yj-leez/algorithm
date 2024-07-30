package algorithm.문자열;

import java.util.*;

public class P압축 {

    static int[] solution(String msg) {

        // 사전 초기화
        Map<String, Integer> dic = new HashMap<>();
        for(int i = 0; i < 27; i++){
            dic.put(Character.toString('A' + i),i + 1);
        }

        List<Integer> ans = new ArrayList<>();
        int i = 0;
        while(i < msg.length()){
            int j = i + 1;
            String tmp = msg.substring(i, j);
            while(true){
                if(j > msg.length()) break;

                if(dic.containsKey(msg.substring(i, j))){
                    tmp = msg.substring(i, j);
                    j++;
                } else{
                    break;
                }
            }

            ans.add(dic.get(msg.substring(i, j - 1)));
            if(j - 1 != msg.length()) dic.put(msg.substring(i, j), dic.size());
            i = j - 1;

        }

        return ans.stream().mapToInt(Integer::intValue).toArray();
    }
}
