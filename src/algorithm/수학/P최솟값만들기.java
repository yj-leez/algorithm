package algorithm.수학;

import java.util.*;

public class P최솟값만들기 {

    public int solution(int []A, int []B) {
        Arrays.sort(A);
        Arrays.sort(B);

        int answer = 0;
        for(int i = 0; i < A.length; i++){
            answer += (A[i] * B[B.length - 1 - i]);
        }



        return answer;
    }
}