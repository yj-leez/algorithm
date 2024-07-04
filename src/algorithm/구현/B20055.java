package algorithm.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B20055 {
    /**
     * 1. 벨트랑 벨트 위에 있는 로봇 한 칸 회전한다.
     * 2. 가장 먼저 올라간 로봇부터 앞 벨트의 내구도가 1 이상이고 로봇이 없다면 이동한다.
     * ** 단, 내리는 위치(N - 1)에 로봇이 도달하면 로봇을 내린다.
     * => 즉, 이동할 로봇은 N - 2부터 0까지만 검사하면 된다.
     * 3. 올리는 위치(0)에 내구도가 1 이상이고 로봇이 없다면 로봇을 올린다.
     * 4. 내구도가 0인 칸의 개수가 K개 이상이면 종료한다.
     */
    static class Belt{
        private int durability;
        private boolean robot;
        public Belt(int durability){
            this.durability = durability;
            this.robot = false;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Belt[] belts = new Belt[2 * N]; // 행 2개, 열 N개
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2 * N; i++) {
            belts[i] = new Belt(Integer.parseInt(st.nextToken()));
        }

        int cnt = 0;
        int stage = 0;

        int firstRobotLocation = -1;
        int lastRobotIdx = -1;

        while(cnt < K){

            stage++;

            // 벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전
            Belt tmp = belts[belts.length - 1];
            for(int i = belts.length - 1; i > 0; i--){
                belts[i] = belts[i - 1];
            }
            belts[0] = tmp;

            // 내리는 위치(N-1)에서 로봇 내리기
            if (belts[N - 1].robot) {
                belts[N - 1].robot = false;
            }

            // 로봇들 한 칸 전진
            for (int i = N - 2; i >= 0 ; i--) {
                if (belts[i].robot && !belts[i + 1].robot && belts[i + 1].durability >= 1){
                    belts[i + 1].robot = true;
                    belts[i + 1].durability--;
                    belts[i].robot = false;

                    if (belts[i + 1].durability == 0) cnt++;
                }
            }

            if (belts[N - 1].robot) {
                belts[N - 1].robot = false;
            }


            // 올리는 위치에 있는 칸의 내구도가 0이 아니면 올리는 위치에 로봇을 올린다.
            if (belts[0].durability > 0 && !belts[0].robot){
                belts[0].durability--;
                belts[0].robot = true;
                if (belts[0].durability == 0) cnt++;
            }

        }
        System.out.println(stage);


    }
}
