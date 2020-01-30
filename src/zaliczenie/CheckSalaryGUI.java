package zaliczenie;

import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.*;

public class CheckSalaryGUI extends JFrame {
    CheckSalaryGUI(User user) {
        super("Company Control Panel (" + user.username + ") - Sprawdź pensję");
        JPanel panel = new JPanel(new GridLayout(0,1));
        JLabel info = new JLabel("Obecna wysokość Twojej pensji: ", SwingConstants.CENTER);
        
        DBConnection db = DBConnection.getInstance();
        double wynagrodzenie = db.checkWynagrodzenie(user.getImie(), user.getNazwisko());
        
        JLabel pensja = new JLabel(wynagrodzenie + " PLN", SwingConstants.CENTER);
        int font_size = 36;
        String font_family = "Arial";
        int font_style = Font.BOLD;
        pensja.setFont(new Font(font_family, font_style, font_size));
        
        panel.add(info);
        panel.add(pensja);
        
        panel.setBorder(BorderFactory.createEmptyBorder(50,30,50,30));
        add(panel);
        setSize(450,250);
        setVisible(true);
    }
}
