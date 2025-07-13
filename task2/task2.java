import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class task2 {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Необходимо указать два аргумента: путь к файлу окружности и путь к файлу точек");
            return;
        }

        try {
            Scanner circleScanner = new Scanner(new File(args[0]));
            double centerX = circleScanner.nextDouble();
            double centerY = circleScanner.nextDouble();
            double radius = circleScanner.nextDouble();
            circleScanner.close();

            Scanner pointsScanner = new Scanner(new File(args[1]));
            while (pointsScanner.hasNextDouble()) {
                double pointX = pointsScanner.nextDouble();
                double pointY = pointsScanner.nextDouble();

                double distanceSquared = Math.pow(pointX - centerX, 2) + Math.pow(pointY - centerY, 2);
                double radiusSquared = Math.pow(radius, 2);

                int position;
                if (Math.abs(distanceSquared - radiusSquared) < 1e-10) {
                    position = 0;
                } else if (distanceSquared < radiusSquared) {
                    position = 1;
                } else {
                    position = 2;
                }

                System.out.println(position);
            }
            pointsScanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("Ошибка: файл не найден - " + e.getMessage());
        }
    }
}
