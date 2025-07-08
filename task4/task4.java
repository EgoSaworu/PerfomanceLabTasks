import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class task4 {
    public static void main(String[] args)  {

        Scanner scanner = new Scanner(System.in);

        String filePath = scanner.nextLine();

        List<Integer> nums = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null){
                nums.add(Integer.parseInt(line.trim()));
            }
        } catch (IOException e){
            System.err.println("File reading error" + e.getMessage()+ '!');
        } catch (NumberFormatException e){
            System.err.println("The file contains non-numerical values!");
        }

        if (nums.isEmpty()) {
            System.err.println("The file is empty or contains only whitespaces!");
            return;
        }

        Collections.sort(nums);
         int median = 0;
         int n = nums.size();
         if (n%2 == 1){
             median = nums.get(n/2);
         } else {
             median = (nums.get(n / 2 - 1) + nums.get(n / 2)) / 2;
         }
        int finalMedian = median;
        int moves = 0;
        for (int num : nums) {
            moves += Math.abs(num - median);
        }

        System.out.println(moves);
    }
}