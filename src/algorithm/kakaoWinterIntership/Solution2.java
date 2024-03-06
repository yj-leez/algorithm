package algorithm.kakaoWinterIntership;

public class Solution2 {
    public static void main(String[] args) {
        int[][] edges = {{2, 3}, {4, 3}, {1, 1}, {2, 1}};
        System.out.println(solution(edges));
    }

    public static int[] solution(int[][] edges) {

        int nodeTotalIdx = 0;
        for (int i = 0; i < edges.length; i++) {
            if(edges[i][0] > nodeTotalIdx) nodeTotalIdx = edges[i][0];
            if (edges[i][1] > nodeTotalIdx) nodeTotalIdx = edges[i][1];
        }

        int[][] outputAndInput = new int[nodeTotalIdx + 1][2]; // 1 ~ 숫자가 가장 큰 노드
        for (int i = 0; i < edges.length; i++) {
            outputAndInput[edges[i][0]][0]++;
            outputAndInput[edges[i][1]][1]++;
        }

        int rootIdx = 0;
        int[] answer = new int[4];
        for (int i = 0; i < outputAndInput.length; i++) {
            if (outputAndInput[i][0] >= 2 && outputAndInput[i][1] == 0){
                answer[0] = i; // 새로 생성한 노드의 인덱스
                answer[2]--; // 새로 생성한 노드도 막대 모양 취급 될 수 있으므로 하나 차감
            }
            if(outputAndInput[i][0] == 0){
                answer[2]++; // 막대 모양
            }
            if (outputAndInput[i][0] >= 2 && outputAndInput[i][1] >= 2){
                answer[3]++; // 8자 모양
            }
        }

        answer[1] = outputAndInput[answer[0]][0] - (answer[2] + answer[3]);

        return answer;
    }

}
