package algorithm.투포인터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class B20922 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(stz.nextToken());
        int K = Integer.parseInt(stz.nextToken());

        int[] nums = new int[N];
        Map<Integer, Integer> cnts = new HashMap<>();
        stz = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(stz.nextToken());
        }

        int st = 0;
        int en = 1;
        cnts.put(nums[st], 1);

        int ans = 0;
        while(en < N){
            cnts.put(nums[en], cnts.getOrDefault(nums[en], 0) + 1);

            while(cnts.get(nums[en]) > K){

                cnts.put(nums[st], cnts.get(nums[st]) - 1);
                st++;
            }

            ans = en - st + 1 > ans? en - st + 1: ans;
            en++;
        }

        System.out.println(ans);
    }
}
