import java.util.Scanner;
public class Bai6_4{
	public static int getDaysInMonth(int year, int month) {
        int days = 0;
        if (month >= 1 && month <= 12) {
            switch (month) {
                case 4:
                case 6:
                case 9:
                case 11:
                    days = 30;
                    break;
                case 2:
                    if ((year % 4 == 0 && year % 100 != 0)) {
                        days = 29; 
                    } else {
                        days = 28; 
                    }
                    break;
                default:
                    days = 31;
                    break;
            }
        }
        return days;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhập năm : ");
        int year = sc.nextInt();

        System.out.print("Nhập tháng: ");
        int month = sc.nextInt();

        if (month < 1 || month > 12) {
            System.out.println("Tháng không hợp lệ.");
        } else {
            int daysInMonth = getDaysInMonth(year, month);
            System.out.println("Tháng " + month + " năm " + year + " có " + daysInMonth + " ngày.");
        }
    }
    
}