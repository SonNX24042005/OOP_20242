import javax.swing.JOptionPane;
public class Bai2_2_5 {
	public static void main(String []args)
	{
		String strNum1,strNum2;
		String strNotification;
		strNum1=JOptionPane.showInputDialog(null,
				"Please input the first number:","Input the first number",
				JOptionPane.INFORMATION_MESSAGE);
				
		strNum2=JOptionPane.showInputDialog(null,
				"Please input the second number:","Input the second number",
				JOptionPane.INFORMATION_MESSAGE);
		double num1=Double.parseDouble(strNum1);
		double num2=Double.parseDouble(strNum2);
		double sum=num1+num2;
		double difference=num1-num2;
		double product=num1*num2;
		double quotient=num1/num2;
		strNotification="First number = "+num1+"\nSecond number = "+num2+"\nSum = "+sum+"\nDifference = "+difference+"\nProduct = "+product+"\nQuotient = "+quotient;
		JOptionPane.showMessageDialog(null, strNotification,"Show answer",JOptionPane.INFORMATION_MESSAGE);
		System.exit(0);
	}

}