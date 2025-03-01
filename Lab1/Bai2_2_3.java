import javax.swing.JOptionPane;
public class Bai2_2_3 {
	public static void main(String []args)
	{
		String result;
		result = JOptionPane.showInputDialog("PLS enter your name");
		JOptionPane.showMessageDialog(null,"Hi "+result+"!");
		System.exit(0);
	}

}