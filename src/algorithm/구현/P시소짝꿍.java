package algorithm.구현;

import java.io.*;
import java.util.*;

public class P시소짝꿍 {
    public long solution(int[] weights) {
        long answer = 0;
        int[] nor = new int[1001];
        int[] mul = new int[4001];

        for(int i = 0; i < weights.length; i++){
            int duplicated = nor[weights[i]];
            if(duplicated > 0){
                answer += duplicated;

                answer += (mul[weights[i] * 2] - duplicated);
                answer += (mul[weights[i] * 3] - duplicated);
                answer += (mul[weights[i] * 4] - duplicated);
            } else {
                answer += mul[weights[i] * 2];
                answer += mul[weights[i] * 3];
                answer += mul[weights[i] * 4];
            }
            nor[weights[i]]++;
            mul[weights[i] * 2]++;
            mul[weights[i] * 3]++;
            mul[weights[i] * 4]++;
        }


        return answer;
    }
}
