package algorithm.programmers;

import java.util.Arrays;

public class 광물캐기 {

    public static void main(String[] args) {
        int[] picks = {1, 3, 2};
        String[] minerals = {"diamond", "diamond", "diamond", "iron", "iron", "diamond", "iron", "stone"};
        solution(picks, minerals);
    }
    public static int solution(int[] picks, String[] minerals) {
        int totalPick = Math.min(picks[0] + picks[1] + picks[2], minerals.length / 5 + 1);
        int [][] mineralsCnt = new int[totalPick][3];
        int [][] fatigueSum =  new int[totalPick][3];

        for(int i = 0; i < minerals.length; i++){
            if(i / 5 == totalPick) {
                break; }
            if(minerals[i].equals("diamond")) mineralsCnt[i / 5][0]++;
            else if(minerals[i].equals("iron")) mineralsCnt[i / 5][1]++;
            else if(minerals[i].equals("stone")) mineralsCnt[i / 5][2]++;
        }

        for(int i = 0; i < mineralsCnt.length; i++){
            if(i / 5 == totalPick) {
                break; }
            fatigueSum[i][0] = mineralsCnt[i][0] + mineralsCnt[i][1] + mineralsCnt[i][2];
            fatigueSum[i][1] = mineralsCnt[i][0] * 5 + mineralsCnt[i][1] + mineralsCnt[i][2];
            fatigueSum[i][2] = mineralsCnt[i][0] * 25 + mineralsCnt[i][1] * 5 + mineralsCnt[i][2];
        }

        Arrays.sort(fatigueSum, (arr1, arr2) -> Integer.compare(arr2[2], arr1[2]));

        int answer = 0;
        for(int i = 0; i < totalPick; i++){
            if(i < picks[0]){
                answer += fatigueSum[i][0];
            } else if(i < picks[0] + picks[1]){
                answer += fatigueSum[i][1];
            } else {
                answer += fatigueSum[i][2];
            }
        }

        return answer;
    }

}
