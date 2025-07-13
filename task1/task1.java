import java.util.ArrayList;
import java.util.List;

public class task1 {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Необходимо указать два аргумента: n и m");
            return;
        }

        try {
            int n = Integer.parseInt(args[0]);
            int m = Integer.parseInt(args[1]);

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

        } catch (NumberFormatException e) {
            System.out.println("Аргументы должны быть целыми числами");
        }
    }
}