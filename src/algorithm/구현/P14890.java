package algorithm.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P14890 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;

        // 가로 행 중에 길을 만족하는 행이 있는지 확인
        ArrayList<int[]>[] rows = new ArrayList[N]; // 행
        for (int i = 0; i < N; i++) {
            rows[i] = new ArrayList();
            int past = map[i][0];
            int cnt = 1;
            for (int j = 1; j < N; j++) {
                if (past == map[i][j]) cnt++;
                else {
                    rows[i].add(new int[]{past, cnt});
                    past = map[i][j];
                    cnt = 1;
                }
            }
            if (cnt > 0) {
                rows[i].add(new int[]{past, cnt});
            }
        }


        for (ArrayList<int[]> row : rows){
            int pastHeight = row.get(0)[0];
            int pastCnt = row.get(0)[1];
            boolean makeRoad = true;
            int pastRoadIdx = -1;

            for (int i = 1; i < row.size(); i++) {
                if (pastHeight - 1 == row.get(i)[0] && row.get(i)[1] >= L){
                    // 앞 쪽의 높이가 1만큼 더 높고 뒤 쪽의 길이가 L 이상인 경우
                    pastHeight = row.get(i)[0];
                    pastCnt = row.get(i)[1];
                    pastRoadIdx = i;
                } else if (pastHeight == row.get(i)[0] - 1 && pastCnt >= L &&
                        (pastRoadIdx != i - 1 || pastCnt >= L * 2)){
                    // 뒤 쪽의 높이가 1만큼 더 높고 앞 쪽의 길이가 L 이상인 경우
                    pastHeight = row.get(i)[0];
                    pastCnt = row.get(i)[1];
                    pastRoadIdx = i - 1;
                } else {
                    makeRoad = false;
                    break;
                }
            }

            if (makeRoad) answer++;
        }


        // 세로 열 중에 길을 만족하는 열이 있는지 확인
        ArrayList[] cols = new ArrayList[N]; // 열
        for (int i = 0; i < N; i++) {
            cols[i] = new ArrayList();
            int past = map[0][i];
            int cnt = 1;
            for (int j = 1; j < N; j++) {
                if (past == map[j][i]) cnt++;
                else {
                    cols[i].add(new int[]{past, cnt});
                    past = map[j][i];
                    cnt = 1;
                }
            }
            if (cnt > 0) {
                cols[i].add(new int[]{past, cnt});
            }
        }


        for (ArrayList<int[]> col : cols){
            int pastHeight = col.get(0)[0];
            int pastCnt = col.get(0)[1];
            boolean makeRoad = true;
            int pastRoadIdx = -1;

            for (int i = 1; i < col.size(); i++) {
                if (pastHeight - 1 == col.get(i)[0] && col.get(i)[1] >= L){
                    // 앞 쪽의 높이가 1만큼 더 높고 뒤 쪽의 길이가 L 이상인 경우
                    pastHeight = col.get(i)[0];
                    pastCnt = col.get(i)[1];
                    pastRoadIdx = i;
                } else if (pastHeight == col.get(i)[0] - 1 && pastCnt >= L &&
                        (pastRoadIdx != i - 1 || pastCnt >= L * 2)){
                    // 뒤 쪽의 높이가 1만큼 더 높고 앞 쪽의 길이가 L 이상인 경우
                    pastHeight = col.get(i)[0];
                    pastCnt = col.get(i)[1];
                    pastRoadIdx = i - 1;
                } else {
                    makeRoad = false;
                    break;
                }
            }

            if (makeRoad) answer++;

        }

        System.out.println(answer);

    }
}
