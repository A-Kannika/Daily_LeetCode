package Medium;

public class p904_totalFruit {
    public static void main(String[] args) {
        System.out.println(totalFruit(new int[]{1, 2, 3, 2, 2})); // 4
        System.out.println(totalFruit(new int[]{1, 2, 1})); // 3
        System.out.println(totalFruit(new int[]{0,1,2,2})); // 3
        System.out.println(totalFruit(new int[]{1,1,1,1})); // 4
        System.out.println(totalFruit(new int[]{})); // 0
        System.out.println(totalFruit(new int[]{0})); // 1
        System.out.println(totalFruit(new int[]{1,1,2,1,1,3,3,1,1,4,4,4,4,4,4,1})); // 9
    }

    public static int totalFruit(int[] fruits) {
        if (fruits.length <= 1) {
            return fruits.length;
        }

        int total = 0;
        // size of our current valid window.
        int current = 0;

        // set to -1 before assign the second type
        // 0 <= fruits[i] < fruits.length
        // Keep track the last 2 types
        int lastFruit = -1;
        int secondLastFruit = -1;
        int lastFruitCount = 0;

        for (int i = 0; i < fruits.length; i++) {
            if (fruits[i] == lastFruit || fruits[i] == secondLastFruit) {
                current++;
            } else {
                current = lastFruitCount + 1;
            }

            if (fruits[i] == lastFruit) {
                // We are continuing a block of the same fruit.
                lastFruitCount++;
            } else {
                secondLastFruit = lastFruit;
                lastFruit = fruits[i];
                // We are starting a new block, so its count is 1.
                lastFruitCount = 1;
            }
            total = Math.max(total, current);
        }
        return total;
    }
}
