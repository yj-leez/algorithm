package algorithm.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B20006 {
    static class Room{
        private int level;
        private List<Player> players;

        public Room(int level){
            this.level = level;
            players = new ArrayList<>();
        }
    }
    static class Player implements Comparable<Player>{
        private int level;
        private String nickname;
        public Player(int level, String nickname){
            this.level = level;
            this.nickname = nickname;
        }


        @Override
        public int compareTo(Player other) {
            return this.nickname.compareTo(other.nickname);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int P = Integer.parseInt(st.nextToken()); // 플레이어 수
        int M = Integer.parseInt(st.nextToken()); // 방 정원
        List<Room> rooms = new ArrayList<>();

        for(int i = 0; i < P; i++){
            st = new StringTokenizer(br.readLine());
            Player p = new Player(Integer.parseInt(st.nextToken()), st.nextToken());
            boolean flag = false; // 적합한 방이 있는지 확인하는 플래그

            for(int j = 0; j < rooms.size(); j++){
                Room room = rooms.get(j);
                if (Math.abs(room.level - p.level) <= 10 && room.players.size() < M){
                    flag = true;
                    room.players.add(p);
                    break;
                }
            }

            // 만약 적합한 방이 없었다면 새로운 방을 만들어서 들어간다.
            if (!flag){
                Room room = new Room(p.level);
                room.players.add(p);
                rooms.add(room);
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < rooms.size(); i++){
            Room room = rooms.get(i);
            if(room.players.size() == M) sb.append("Started!\n");
            else sb.append("Waiting!\n");

            Collections.sort(room.players);
            for(int j = 0; j < room.players.size(); j++){
                sb.append(room.players.get(j).level + " " + room.players.get(j).nickname + "\n");
            }
        }
        System.out.println(sb);

    }
}
