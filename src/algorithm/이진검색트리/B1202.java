package algorithm.이진검색트리;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class B1202 {
    /**
     * 1. 풀이) 무게가 가장 작은 가방부터 확인하며 담을 수 있는 보석을 lowerBound로 찾고 그 이하의 보석들 중 가장 가치가 높은 것을 선택한다.
     *
     * 보석의 무게, 가방의 제한 무게순으로 둘 다 정렬한 후
     * 가방이 큰 순서대로 담을 수 있는 제일 무거운 보석의 인덱스 아래로 탐색하면서
     * 제일 가치가 큰 보석을 선택한다.
     * (이분탐색(logN) + 해당 인덱스 이하 탐색(N)) * 가방 개수(K) = 최악의 경우  300,000 * 300,000 = 90,000,000,000
     * => 시간 제한으로 터짐
     */

    /**
     * 2. 풀이) 가장 가격이 높은 보석부터 확인하며 해당 보석을 담을 수 있는 가방 중 무게가 가장 작은 가방을 선택한다.
     *
     * 무게가 가장 작은 가방을 찾는 것을 이진검색트리를 이용하여 logN에 찾을 수 있다.
     */

        static class Jew implements Comparable<Jew>{
        int weight;
        int price;

        public Jew(int weight, int price){
            this.weight = weight;
            this.price = price;
        }


        @Override
        public int compareTo(Jew other) {
            // 내림차순으로 정렬
            return Integer.compare(other.price, this.price);
        }
    }

    static Jew[] jews;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 보석개수
        int k = Integer.parseInt(st.nextToken()); // 가방개수

        jews = new Jew[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            jews[i] = new Jew(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(jews);

        TreeMap<Integer, Integer> bag = new TreeMap<>();
        for (int i = 0; i < k; i++) {
            int weight = Integer.parseInt(br.readLine());
            bag.put(weight, bag.getOrDefault(weight, 0) + 1);
        }

        /**
         * ans는 최대 300,000 * 1,000,000으로 integer의 범위를 넘으므로 long으로 설정해야함
         */
        long ans = 0;
        int bagCount = 0;
        for (int i = 0; i < n; i++) {
            //가장 가격이 높은 보석부터 확인하며 해당 보석을 담을 수 있는 가방 중 무게가 가장 작은 가방을 선택

            int weight = jews[i].weight; // 현재 가장 가격이 높은 보석의 무게

            /**
             * map.higherKey(i)는 i보다 높은 키들 중 가장 작은 키를 리턴함. => 같은 값을 가지고 있는 키는 리턴하지 않음!
             * => 똑같은 값을 가지고 있는지 분기 처리 해야함
             */
            Integer bagKey = null;
            if (bag.containsKey(weight)){
                bagKey = weight;
            } else {
                bagKey = bag.higherKey(weight);
            }

            if (bagKey == null) continue; // 보석보다 무게가 더 큰 가방이 존재하지않는다면 다음 보석으로

            bag.put(bagKey,bag.get(bagKey) - 1);
            if (bag.get(bagKey) == 0){
                bag.remove(bagKey);
            }
            bagCount++;
            ans += jews[i].price;


            if (bagCount == k){
                // 가방 개수만큼 찼다면 break
                break;
            }

        }

        System.out.println(ans);
    }

//    static class Jew implements Comparable<Jew>{
//        int weight;
//        int price;
//
//        public Jew(int weight, int price){
//            this.weight = weight;
//            this.price = price;
//        }
//
//
//        @Override
//        public int compareTo(Jew other) {
//            return Integer.compare(this.weight, other.weight);
//        }
//    }
//
//    static Jew[] jews;
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//
//        int n = Integer.parseInt(st.nextToken()); // 보석개수
//        int k = Integer.parseInt(st.nextToken()); // 가방개수
//
//        jews = new Jew[n];
//        for (int i = 0; i < n; i++) {
//            st = new StringTokenizer(br.readLine());
//            jews[i] = new Jew(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
//        }
//        Arrays.sort(jews);
//
//        int[] bag = new int[k];
//        for (int i = 0; i < k; i++) {
//            bag[i] = Integer.parseInt(br.readLine());
//        }
//
//        int ans = 0;
//        for (int i = k - 1; i >= 0; i--) {
//            int idx = getLowerBound(bag[i]);
//
//            // 이 두 조건을 제거할 수 있도록 이분탐색법을 수정할 수 있지 않을까
//            if (idx == n) idx--;
//            if (jews[idx].weight > bag[i]){
//                idx--;
//            }
//
//            ans += findMostExpensive(idx);
//        }
//
//        System.out.println(ans);
//    }
//
//    static int findMostExpensive(int idx){
//        int max = 0;
//        int maxIdx = -1;
//
//        if (idx >= 0) {
//            for (int i = idx; i >= 0 ; i--) {
//                if (jews[i].price > max){
//                    max = jews[i].price;
//                    maxIdx = i;
//                }
//            }
//        }
//
//
//        if (maxIdx != -1){
//            jews[maxIdx].price = 0;
//        }
//
//        return max;
//    }
//
//    static int getLowerBound(int key){
//        int st = 0;
//        int en = jews.length;
//
//        while(st < en){
//            int mid = (st + en)/ 2;
//
//            if (jews[mid].weight >= key){
//                en = mid;
//            } else {
//                st = mid + 1;
//            }
//        }
//
//        return st;
//    }
}
