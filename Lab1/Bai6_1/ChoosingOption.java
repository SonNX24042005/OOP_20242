
import javax.swing.JOptionPane;

public class ChoosingOption {
    public static void main(String[] args) {
        Object[] options = { "Tôi đồng ý", "Tôi không đồng ý" };
        int choice = JOptionPane.showOptionDialog(null, 
                "Bạn có đồng ý không?", 
                "Choose option",
                JOptionPane.YES_NO_OPTION, 
                JOptionPane.QUESTION_MESSAGE, 
                null, options, options[0]); 

        JOptionPane.showMessageDialog(null, "Bạn đã chọn: " + options[choice]);
        System.exit(0);
    }
}
