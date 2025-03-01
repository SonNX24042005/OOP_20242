import java.util.Scanner;
public class Bai2_2_6 {

    public static void linear_system(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập a1: ");
        double a1 = sc.nextDouble();
        System.out.print("Nhập b1: ");
        double b1 = sc.nextDouble();
        System.out.print("Nhập c1: ");
        double c1 = sc.nextDouble();
        System.out.print("Nhập a2: ");
        double a2 = sc.nextDouble();
        System.out.print("Nhập b2: ");
        double b2 = sc.nextDouble();
        System.out.print("Nhập c2: ");
        double c2 = sc.nextDouble();

        double det = a1 * b2 - a2 * b1;

        if (det == 0) {
            if (c1/c2 == a1/a2) {
                System.out.println("Hệ phương trình có vô số nghiệm.");
            } else {
                System.out.println("Hệ phương trình vô nghiệm.");
            }
        } else {
            double x = (c1 * b2 - c2 * b1) / det;
            double y = (a1 * c2 - a2 * c1) / det;

            System.out.println("Nghiệm của hệ phương trình là:");
            System.out.println("x = " + x);
            System.out.println("y = " + y);
        }
    }
    public static void linear_equation() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập a: ");
        double a = sc.nextDouble();
        System.out.print("Nhập b: ");
        double b = sc.nextDouble();

        if (a == 0) {
            if (b == 0) {
                System.out.println("Phương trình vô số nghiệm.");
            } 
            else {
                System.out.println("Phương trình vô nghiệm.");
            }
        } 
        else {
            double x = -b / a;
            System.out.println("Nghiệm của phương trình là: x = " + x);
        }
    }
    
    
    public static void second_degree_equation() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập a: ");
        double a = sc.nextDouble();
        System.out.print("Nhập b: ");
        double b = sc.nextDouble();
        System.out.print("Nhập c: ");
        double c = sc.nextDouble();
        double delta = b * b - 4 * a * c;

        if (delta > 0) {
            double x1 = (-b + Math.sqrt(delta)) / (2 * a);
            double x2 = (-b - Math.sqrt(delta)) / (2 * a);
            System.out.println("Nghiệm của phương trình là:");
            System.out.println("x1 = " + x1);
            System.out.println("x2 = " + x2);
        }
        else if (delta == 0) {
            double x = -b / (2 * a);
            System.out.println("Phương trình có nghiệm kép:");
            System.out.println("x = " + x);
        }
        else {
            double re = -b / (2 * a);
            double im = Math.sqrt(-delta) / (2 * a);
            System.out.println("Phương trình có nghiệm phức:");
            System.out.println("x1 = " + re + " + " + im + "i");
            System.out.println("x2 = " + re + " - " + im + "i");
        }
    }
    public static void choose_option() {
    	Scanner sc = new Scanner(System.in);
        System.out.print("Nhập loại phương trình cần giải:\n -Nhập 1: Giải phương trình tuyến tính một ẩn\n -Nhập 2: Giải hệ phương trình tuyến tính 2 ẩn\n -Nhập 3: Giải phương trình bậc 2\n");
        System.out.print("Nhập lựa chọn: ");
        double op = sc.nextDouble();
        if (op==1) {
        	linear_equation();
        }
        else if (op==2) {
        	linear_system();;
        }
        else if(op==3) {
        	second_degree_equation();
        }
        	
    }
    public static void main(String[]args) {
    	choose_option();
    }
}