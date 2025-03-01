import java.util.Scanner;
public class Bai6_3{
    public static void main(String args[]) {
        Scanner input = new Scanner(System.in);

        System.out.print("Nhập n:");
        int n = input.nextInt();
        for(int i=0;i<n;i++)
        {
            for (int j=0;j<n-i-1;j++) {
            	System.out.print(" ");
            }
            for (int j=0;j<=i;j++) {
            	System.out.print("*");
            }
            for (int j=0;j<=i-1;j++) {
            	System.out.print("*");
            }
            System.out.print("\n");
        }
    }
}
