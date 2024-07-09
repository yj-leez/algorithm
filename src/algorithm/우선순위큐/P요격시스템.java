package algorithm.우선순위큐;

import java.util.*;

public class P요격시스템 {
    public int solution(int[][] targets) {
        Arrays.sort(targets, (a, b) -> Integer.compare(a[0], b[0]));
        PriorityQueue<int[]> pQ = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return Integer.compare(b[0], a[0]);
            }
        });

        for(int i = 0; i < targets.length; i++){
            if(pQ.isEmpty()){
                pQ.add(targets[i]);
            } else {
                int[] peek = pQ.peek();
                if(Math.min(peek[1],targets[i][1]) - Math.max(peek[0],targets[i][0]) > 0){
                    pQ.poll();
                    pQ.add(new int[]{Math.max(peek[0],targets[i][0]), Math.min(peek[1],targets[i][1])});
                } else {
                    pQ.add(targets[i]);
                }
            }
        }
        return pQ.size();
    }
}
