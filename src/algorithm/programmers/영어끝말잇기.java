package algorithm.programmers;

import java.util.*;

class 영어끝말잇기 {
    public int[] solution(int n, String[] words) {

        int[] answer = new int[2];
        int personIdx = 2;
        for(int i = 1; i < words.length; i++){
            if(words[i - 1].charAt(words[i - 1].length() - 1) != words[i].charAt(0)){
                answer[0] = personIdx;
                answer[1] = i / n + 1;
                return answer;
            }
            for(int j = 0; j < i; j++){
                if(words[j].equals(words[i])){
                    answer[0] = personIdx;
                    answer[1] = i / n + 1;
                    return answer;
                }
            }
            personIdx++;
            if(personIdx > n) personIdx -= n;
        }

        answer[0] = 0;
        answer[1] = 0;
        return answer;
    }
}
