import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class task2 {
    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);

        System.out.println("Введите путь к файлу с параметрами окружности:");
        String circleFilePath = inputScanner.nextLine();

        System.out.println("Введите путь к файлу с координатами точек:");
        String pointsFilePath = inputScanner.nextLine();

        try {
            Scanner circleScanner = new Scanner(new File(circleFilePath));
            double centerX = circleScanner.nextDouble();
            double centerY = circleScanner.nextDouble();
            double radius = circleScanner.nextDouble();
            circleScanner.close();

            Scanner pointsScanner = new Scanner(new File(pointsFilePath));
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
            System.out.println("Ошибка: файл не найден");
        } finally {
            inputScanner.close();
        }
    }
}