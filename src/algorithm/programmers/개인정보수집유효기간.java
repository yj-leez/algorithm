package algorithm.programmers;

import java.util.Arrays;
import java.util.HashMap;

public class 개인정보수집유효기간 {
    public static void main(String[] args) {
        String[] terms = {"Z 3", "D 5"};
        String[] privacies = {"2019.01.01 D", "2019.11.15 Z", "2019.08.02 D", "2019.07.01 D", "2018.12.28 Z"};
        System.out.println(solution("2020.01.01", terms, privacies).toString());
    }

    public static int[] solution(String today, String[] terms, String[] privacies) {

        /**
         * key는 비교값이므로 굳이 Character로 변환할 필요 없음
         */
        HashMap<Character, Integer> termsMap = new HashMap<>(terms.length);
        for (int i = 0; i < terms.length; i++) {
            String[] splitTerms = terms[i].split(" ");
            termsMap.put(splitTerms[0].charAt(0), Integer.parseInt(splitTerms[1]));
        }

        int[] todayDate = Arrays.stream(today.split("\\."))
                .mapToInt(Integer::parseInt)
                .toArray();
        int[] answer = new int[privacies.length];
        int idx = 0;
        for (int i = 0; i < privacies.length; i++) {
            String[] splitTerms = privacies[i].split(" ");
            int [] collectionDate = Arrays.stream(splitTerms[0].split("\\."))
                    .mapToInt(Integer::parseInt)
                    .toArray(); // 개인 정보 수집 일자를 년도, 월, 일로 나눠서 저장
            char termsType = splitTerms[1].charAt(0); // 계약 종류

            collectionDate[1] += termsMap.get(termsType); // 개인 정보 수집 일자 + 유효 기간

            while(collectionDate[1] > 12){
                collectionDate[0]++;
                collectionDate[1] -= 12;
            }


            if (todayDate[0] > collectionDate[0] || (todayDate[0] == collectionDate[0] && todayDate[1] > collectionDate[1])
            || (todayDate[0] == collectionDate[0] && todayDate[1] == collectionDate[1] && todayDate[2] >= collectionDate[2])){
                answer[idx++] = i + 1;
            }

        }

        int[] newAnswer = new int[idx];
        for (int i = 0; i < idx; i++) {
            newAnswer[i] = answer[i];
        }

        return newAnswer;
    }

}
