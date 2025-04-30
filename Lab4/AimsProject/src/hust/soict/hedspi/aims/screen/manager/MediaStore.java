package hust.soict.hedspi.aims.screen.manager;

import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Playable;
import java.awt.*;
import javax.swing.*;
public class MediaStore extends JPanel {
    private Media media;

    public MediaStore(Media media) {
        this.media = media;
        // Sắp xếp các thành phần con theo chiều dọc
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Hiển thị tiêu đề Media
        JLabel title = new JLabel(media.getTitle());
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 15));
        title.setAlignmentX(CENTER_ALIGNMENT); // Căn giữa theo chiều ngang

        // Hiển thị giá Media
        JLabel cost = new JLabel("" + media.getCost() + " $");
        cost.setAlignmentX(CENTER_ALIGNMENT); // Căn giữa theo chiều ngang

        // Panel chứa nút Play (nếu có)
        JPanel container = new JPanel();
        container.setLayout(new FlowLayout(FlowLayout.CENTER)); // Căn giữa nút

        // Kiểm tra nếu Media có thể chơi được (implement Playable)
        if (media instanceof Playable) {
            JButton playButton = new JButton("Play");
            playButton.addActionListener(e -> {
                ((Playable) media).play(); // chỉ thực hiện hành động
                JOptionPane.showMessageDialog(this, "Playing " + media.getTitle(), "Playing Media", JOptionPane.INFORMATION_MESSAGE);
            });
            container.add(playButton);
        }

        // Thêm các thành phần vào MediaStore panel
        this.add(Box.createVerticalGlue()); // Keo dãn dọc ở trên
        this.add(title);
        this.add(cost);
        this.add(container); // Thêm panel chứa nút (hoặc panel rỗng)
        this.add(Box.createVerticalGlue()); // Keo dãn dọc ở dưới

        // Thêm đường viền
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }
}