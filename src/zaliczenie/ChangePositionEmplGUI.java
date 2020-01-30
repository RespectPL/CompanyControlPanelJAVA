package zaliczenie;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ChangePositionEmplGUI extends JFrame {
    JTextField imie = new JTextField(25);
    JTextField nazwisko = new JTextField(25);
    JTextField wynagrodzenie = new JTextField(10);
    JLabel info = new JLabel();
    
    class ChangePosition implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                String firstname = imie.getText();
                String lastname = nazwisko.getText();
                String position = wynagrodzenie.getText();
                DBConnection db = DBConnection.getInstance();
                boolean change = db.updateStanowisko(firstname, lastname, position);
                if(change) {
                    info.setText("Pomyślnie zaktualizowano stanowisko pracownika!");
                }
                else {
                    info.setText("Błąd! Sprawdź dane lub połączenie z bazą!");
                }
            }
            catch(Exception e) {
                System.out.println("Nie zaktualizowano stanowiska pracownika! Błędne dane!");
                info.setText("Błąd! Sprawdź dane lub połączenie z bazą!");
            }
        }
    }
    
    ChangePositionEmplGUI(User user) {
        super("Company Control Panel (" + user.username + ") - Zmień stanowisko pracownika");
        JPanel panel = new JPanel(new GridLayout(0,2));
        JLabel limie = new JLabel("Imie");
        JLabel lnazwisko = new JLabel("Nazwisko");
        JLabel lstanowisko = new JLabel("Stanowisko");
        JButton update = new JButton("Zaktualizuj");
        update.addActionListener(new ChangePosition());
        panel.add(limie);
        panel.add(imie);
        panel.add(lnazwisko);
        panel.add(nazwisko);
        panel.add(lstanowisko);
        panel.add(wynagrodzenie);
        panel.add(update);
        panel.add(info);
        panel.setBorder(BorderFactory.createEmptyBorder(50,30,50,30));
        add(panel);
        setSize(650,250);
        setVisible(true);
    }
}
