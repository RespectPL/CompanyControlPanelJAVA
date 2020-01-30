package zaliczenie;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class DetermineMeetingGUI extends JFrame {
    JTextField data = new JTextField(10);
    JTextField godzina = new JTextField(5);
    JTextField tytul = new JTextField(100);
    JTextArea opis = new JTextArea(10,10);
    JLabel info = new JLabel();
    
    class AddMeeting implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                String date = data.getText();
                String time = godzina.getText();
                String title = tytul.getText();
                String description = opis.getText();
                DBConnection db = DBConnection.getInstance();
                boolean check = db.addZebranie(title, description, date, time);
                if(check) {
                    info.setText("Pomyślnie ustalono zebranie firmowe!");
                }
                else {
                    info.setText("Błąd! Sprawdź dane lub połączenie z bazą!");
                }
            }
            catch(Exception e) {
                System.out.println("Nie ustalono zebrania firmowego! Błędne dane!");
                info.setText("Błąd! Sprawdź dane lub połączenie z bazą!");
            }
        }
    }
    
    DetermineMeetingGUI(User user) {
        super("Company Control Panel (" + user.username + ") - Ustal zebranie");
        JPanel panel = new JPanel(new GridLayout(0,2));
        JLabel ldata = new JLabel("Data");
        JLabel lgodzina = new JLabel("Godzina");
        JLabel ltytul = new JLabel("Tytuł");
        JLabel lopis = new JLabel("Opis");
        JButton update = new JButton("Ustal zebranie");
        update.addActionListener(new AddMeeting());
        opis.setLineWrap(true);
        
        panel.add(ldata);
        panel.add(data);
        panel.add(lgodzina);
        panel.add(godzina);
        panel.add(ltytul);
        panel.add(tytul);
        panel.add(lopis);
        panel.add(opis);
        panel.add(update);
        panel.add(info);
        panel.setBorder(BorderFactory.createEmptyBorder(50,30,50,30));
        add(panel);
        setSize(650,350);
        setVisible(true);
    }
}
