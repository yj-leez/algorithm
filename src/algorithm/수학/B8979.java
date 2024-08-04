package algorithm.수학;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B8979 {
    static class Country implements Comparable<Country>{
        private int num; // 나라 번호
        private int gold;
        private int silver;
        private int bronze;
        public Country(int num, int gold, int silver, int bronze){
            this.num = num;
            this.gold = gold;
            this.silver = silver;
            this.bronze = bronze;
        }

        @Override
        public int compareTo(Country o) {
            if(this.gold < o.gold) return 1;
            else if(this.gold > o.gold) return -1;
            else {
                if(this.silver < o.silver) return 1;
                else if(this.silver > o.silver) return -1;
                else {
                    if(this.bronze < o.bronze) return 1;
                    else if(this.bronze > o.bronze) return -1;
                }
            }
            return 0;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());


        Country[] countries = new Country[N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            countries[i] = new Country(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(countries);

        int rank = -1;
        for(int i = 0; i < N; i++){
            if(countries[i].num == K){
                rank = i + 1;
                for(int j = i - 1; j >= 0; j--){
                    if(countries[i].gold == countries[j].gold && countries[i].silver == countries[j].silver && countries[i].bronze == countries[j].bronze){
                        rank--;
                    } else {
                        break;
                    }
                }
            }
        }

        System.out.println(rank);
    }
}
