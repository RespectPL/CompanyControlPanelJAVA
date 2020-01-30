package zaliczenie;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ChangeSalaryEmplGUI extends JFrame {
    JTextField imie = new JTextField(25);
    JTextField nazwisko = new JTextField(25);
    JTextField wynagrodzenie = new JTextField(10);
    JLabel info = new JLabel();
    
    class ChangeSalary implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                String firstname = imie.getText();
                String lastname = nazwisko.getText();
                double salary = Double.parseDouble(wynagrodzenie.getText());
                DBConnection db = DBConnection.getInstance();
                boolean change = db.updateWynagrodzenie(firstname, lastname, salary);
                if(change) {
                    info.setText("Pomyślnie zaktualizowano wynagrodzenie pracownika!");
                }
                else {
                    info.setText("Błąd! Sprawdź dane lub połączenie z bazą!");
                }
            }
            catch(Exception e) {
                System.out.println("Nie zaktualizowano wynagrodzenia pracownika! Błędne dane!");
                info.setText("Błąd! Sprawdź dane lub połączenie z bazą!");
            }
        }
    }
    
    ChangeSalaryEmplGUI(User user) {
        super("Company Control Panel (" + user.username + ") - Zmień pensję pracownika");
        JPanel panel = new JPanel(new GridLayout(0,2));
        JLabel limie = new JLabel("Imie");
        JLabel lnazwisko = new JLabel("Nazwisko");
        JLabel lwynagrodzenie = new JLabel("Wynagrodzenie");
        JButton update = new JButton("Zaktualizuj");
        update.addActionListener(new ChangeSalary());
        panel.add(limie);
        panel.add(imie);
        panel.add(lnazwisko);
        panel.add(nazwisko);
        panel.add(lwynagrodzenie);
        panel.add(wynagrodzenie);
        panel.add(update);
        panel.add(info);
        panel.setBorder(BorderFactory.createEmptyBorder(50,30,50,30));
        add(panel);
        setSize(650,250);
        setVisible(true);
    }
}
