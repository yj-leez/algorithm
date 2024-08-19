package algorithm.투포인터;

import java.util.Arrays;

public class P구명보트 {
    public int solution(int[] people, int limit) {
        Arrays.sort(people);
        int start = 0;
        int end = people.length - 1;

        int answer = 0;
        while(start <= end){
            if(people[start] + people[end] <= limit) start++;

            answer++;
            end--;
        }

        return answer;
    }
}
