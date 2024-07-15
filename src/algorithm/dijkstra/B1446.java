package algorithm.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B1446 {
    static class ShortCut{
        private int st;
        private int en;
        private int distance;
        public ShortCut(int st, int en, int distance){
            this.st = st;
            this.en = en;
            this.distance = distance;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 지름길 개수
        int D = Integer.parseInt(st.nextToken()); // 고속도로 길이

        List<ShortCut> shortCuts = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            shortCuts.add(new ShortCut(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        int[] dijk = new int[D + 1];
        for (int i = 1; i <= D; i++) {
            dijk[i] = dijk[i - 1] + 1;
            for(ShortCut s : shortCuts){
                if (s.en == i && dijk[s.st] + s.distance < dijk[i]){
                    dijk[i] = dijk[s.st] + s.distance;
                }
            }
        }

        System.out.println(dijk[D]);
    }
}
