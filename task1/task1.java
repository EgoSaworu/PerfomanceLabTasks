import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class task1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        List<Integer> circularArray = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            circularArray.add(i);
        }

        List<Integer> path = new ArrayList<>();
        int index = 0;

        while (true) {
            path.add(circularArray.get(index));

            index = (index + m - 1) % n;

            if (index == 0) {
                break;
            }
        }

        for (int num : path) {
            System.out.print(num);
        }
    }
}