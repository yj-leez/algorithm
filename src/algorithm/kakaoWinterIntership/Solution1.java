package algorithm.kakaoWinterIntership;

import java.util.HashMap;
import java.util.StringTokenizer;

public class Solution1 {
    public static void main(String[] args) {
        String[] friends = {"muzi", "ryan", "frodo", "neo"};
        String[] gifts = {"muzi frodo", "muzi frodo", "ryan muzi", "ryan muzi", "ryan muzi", "frodo muzi", "frodo ryan", "neo muzi"};

        System.out.println(solution(friends, gifts));
    }
    public static int solution(String[] friends, String[] gifts) {

        HashMap<String, Integer> friendIdx = new HashMap<>(friends.length); // 친구 인덱스
        for(int i = 0; i < friends.length; i++){
            friendIdx.put(friends[i], i);
        }

        int[][] history = new int[friends.length][friends.length]; // 주고 받은 기록
        int[] giftScore = new int[friends.length]; // 선물 지수
        int[] willBeReceived = new int[friends.length]; // 다음 달에 받을 선물 개수

        StringTokenizer st;
        for(int i = 0; i < gifts.length; i++){
            st = new StringTokenizer(gifts[i]);
            int giving = friendIdx.get(st.nextToken());
            int given = friendIdx.get(st.nextToken());

            history[giving][given]++;
            giftScore[giving]++;
            giftScore[given]--;
        }

        for(int i = 0; i < friends.length; i++){
            for (int j = 0; j < friends.length; j++) {
                if(history[i][j] > history[j][i]){
                    willBeReceived[i]++;
                } else if (history[i][j] == history[j][i] && giftScore[i] > giftScore[j]) {
                    willBeReceived[i]++;
                }
            }
        }

        int answer = willBeReceived[0];
        for (int i = 1; i < willBeReceived.length; i++) {
            if(willBeReceived[i] > answer) answer = willBeReceived[i];
        }

        return answer;
    }
}
