import java.util.Arrays;
import java.util.Scanner;

public class Bai6_5{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Getting array size from user
        System.out.print("Nhập số phần tử: ");
        int size = scanner.nextInt();
        
        int[] numbers = new int[size];
        
        System.out.println("Nhập các phần tử:");
        for (int i = 0; i < size; i++) {
            numbers[i] = scanner.nextInt();
        }
        
        Arrays.sort(numbers);
        System.out.println("Mảng đã được sắp xếp: " + Arrays.toString(numbers));
        
        int sum = 0;
        for (int num : numbers) {
            sum += num;
        }
        System.out.println("Tổng của mảng: " + sum);
        
        double average = (double) sum / numbers.length;
        System.out.println("Trung bình của mảng: " + average);
        
        scanner.close();
    }
}
