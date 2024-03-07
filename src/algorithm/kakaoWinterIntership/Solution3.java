package algorithm.kakaoWinterIntership;

public class Solution3 {

    int [][] choice;
    int idx = 0;


    public static void main(String[] args) {

    }


    public int[] solution(int[][] dice) {

        choice = new int [(dice.length - 1) * (dice.length - 2) / 2][dice.length / 2];

        int[] diceIdx = new int[dice.length];
        boolean[] visited = new boolean[dice.length];
        for (int i = 0; i < dice.length - 1; i++) {
            diceIdx[i] = i + 2; // 1 뛰어넘고 2부터 저장
        }




        int[] answer = {};
        return answer;
    }

    public void combination(int[] arr, boolean[] visited, int start, int n, int r) {
        if(r == 0) {
            choice[idx++] = arr;
            return;
        }

        for(int i = start; i < n; i++) {
            visited[i] = true;
            combination(arr, visited, i + 1, n, r - 1);
            visited[i] = false;
        }
    }
}
