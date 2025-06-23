package Daily;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class p3443_maxDistance {
    public static void main(String[] args) {
        System.out.println(maxDistance("NWSE", 1));
        System.out.println(maxDistance("NSWWEW", 3));
    }

    public static int maxDistance(String s, int k) {
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        char[] dirs = {'N', 'S', 'E', 'W'};

        int n = s.length();
        Queue<int[]> queue = new ArrayDeque<>();
        // state: [x, y, index, kUsed]
        queue.offer(new int[]{0, 0, 0, 0});

        HashSet<String> visited = new HashSet<>();
        visited.add("0,0,0,0");

        int maxDistance = 0;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0], y = cur[1], idx = cur[2], kUsed = cur[3];

            maxDistance = Math.max(maxDistance, Math.abs(x) + Math.abs(y));
            if (idx == n) continue;

            char move = s.charAt(idx);
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i], ny = y + dy[i];
                int nk = kUsed + (dirs[i] == move ? 0 : 1);

                if (nk > k) continue;

                String key = nx + "," + ny + "," + (idx + 1) + "," + nk;
                if (visited.add(key)) {
                    queue.offer(new int[]{nx, ny, idx + 1, nk});
                }
            }
        }

        return maxDistance;
    }

    public static int maxDistance2(String s, int k) {
        // Directions for 'N', 'S', 'E', 'W'
        // Use 2D-Array
        char[] dirChars = {'N', 'S', 'E', 'W'};
        int[][] dirs = {
                {0, 1},    // the coordinate of N from the origin
                {0, -1},   // the coordinate of S from the origin
                {1, 0},    // the coordinate of E from the origin
                {-1, 0}    // the coordinate of W from the origin
        };

        // start from origin
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0, 0, 0});
        HashSet<String> visited = new HashSet<>();
        visited.add("0,0,0,0");

        int maxDistance = 0;

        while (!queue.isEmpty()) {
            int[] state = queue.poll();
            int x = state[0];
            int y = state[1];
            int idx = state[2];
            int usedK = state[3];

            // Update max Manhattan distance
            maxDistance = Math.max(maxDistance, Math.abs(x) + Math.abs(y));

            if (idx == s.length()) {
                continue;
            }
            // Step 1: go to the original direction form String s
            char curChar = s.charAt(idx);
            int dx = 0, dy = 0;
            if (curChar == 'N') {
                dx = 0;
                dy = 1;
            } else if (curChar == 'S') {
                dx = 0;
                dy = -1;
            } else if (curChar == 'E') {
                dx = 1;
                dy = 0;
            } else if (curChar == 'W') {
                dx = -1;
                dy = 0;
            }

            int nx = x + dx;
            int ny = y + dy;
            String key = nx + "," + ny + "," + (idx + 1) + "," + usedK;
            if (!visited.contains(key)) {
                visited.add(key);
                queue.add(new int[]{nx, ny, idx + 1, usedK});
            }

            // try changing to other directions --> if allowed
            if (usedK < k) {
                for (int i = 0; i < 4; i++) {
                    if (dirChars[i] == curChar) {
                        continue;
                    }
                    int altDx = dirs[i][0];
                    int altDy = dirs[i][1];
                    int cx = x + altDx;
                    int cy = y + altDy;
                    String altKey = cx + "," + cy + "," + (idx + 1) + "," + (usedK + 1);
                    if (!visited.contains(altKey)) {
                        visited.add(altKey);
                        queue.add(new int[]{cx, cy, idx + 1, usedK + 1});
                    }
                }
            }
        }
        return maxDistance;
    }
}
