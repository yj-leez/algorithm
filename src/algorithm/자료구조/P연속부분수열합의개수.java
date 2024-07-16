package algorithm.자료구조;

import java.util.*;

class P연속부분수열합의개수 {
    public int solution(int[] elements) {

        Set<Integer> set = new HashSet<>();
        for(int l = 1; l <= elements.length; l++){
            for(int st = 0; st < elements.length; st++){
                int sum = 0;

                for(int i = 0; i < l; i++){
                    int idx = st + i >= elements.length? st + i - elements.length: st + i;
                    sum += elements[idx];
                }
                set.add(sum);
            }
        }


        return set.size();
    }
}
