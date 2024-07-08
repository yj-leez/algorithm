package algorithm.우선순위큐;

import java.util.*;

public class P호텔대실 {
    public int solution(String[][] book_time) {
        Arrays.sort(book_time, Comparator.comparing(a -> a[0]));


        int[][] time = new int[book_time.length][book_time[0].length];
        for(int i = 0; i < book_time.length; i++){
            String[] tmp = book_time[i][0].split(":");
            int hour = Integer.parseInt(tmp[0]);
            int minutes = Integer.parseInt(tmp[1]);
            time[i][0] = hour * 60 + minutes;

            tmp = book_time[i][1].split(":");
            hour = Integer.parseInt(tmp[0]);
            minutes = Integer.parseInt(tmp[1]);
            time[i][1] = hour * 60 + minutes + 10;
        }

        PriorityQueue<Integer> pQ = new PriorityQueue<>();

        for(int i = 0; i < time.length; i++){
            if(!pQ.isEmpty() && pQ.peek() <= time[i][0]){
                pQ.poll();
            }
            pQ.add(time[i][1]);
        }

        return pQ.size();
    }
}
