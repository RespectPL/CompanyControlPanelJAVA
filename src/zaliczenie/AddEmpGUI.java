package zaliczenie;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class AddEmpGUI extends JFrame {
    JTextField login = new JTextField(25);
    JTextField haslo = new JTextField(25);
    JTextField imie = new JTextField(25);
    JTextField nazwisko = new JTextField(25);
    JTextField wiek = new JTextField(3);
    JTextField stanowisko = new JTextField(25);
    JTextField wynagrodzenie = new JTextField(10);
    JLabel info = new JLabel();
    
    class AddEmp implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                String username = login.getText();
                String password = haslo.getText();
                String firstname = imie.getText();
                String lastname = nazwisko.getText();
                String position = stanowisko.getText();
                int age = Integer.parseInt(wiek.getText());
                double salary = Double.parseDouble(wynagrodzenie.getText());
                DBConnection db = DBConnection.getInstance();
                db.addPracownik(username, password, firstname, lastname, position, age, salary);
                info.setText("Pomyślnie dodano pracownika!");
            }
            catch(Exception e) {
                System.out.println("Nie dodano użytkownika! Błędne dane!");
                info.setText("Błąd! Sprawdź dane lub połączenie z bazą!");
            }
        }
    }
    
    AddEmpGUI(User user) {
        super("Company Control Panel (" + user.username + ") - Dodaj pracownika");
        JPanel panel = new JPanel(new GridLayout(0,2));
        JLabel limie = new JLabel("Imie");
        JLabel lnazwisko = new JLabel("Nazwisko");
        JLabel llogin = new JLabel("Login");
        JLabel lhaslo = new JLabel("Hasło");
        JLabel lwiek = new JLabel("Wiek");
        JLabel lstanowisko = new JLabel("Stanowisko");
        JLabel lwynagrodzenie = new JLabel("Wynagrodzenie");
        JButton dodajPracownika = new JButton("Dodaj pracownika");
        dodajPracownika.addActionListener(new AddEmp());
        
        panel.add(limie);
        panel.add(imie);
        panel.add(lnazwisko);
        panel.add(nazwisko);
        panel.add(llogin);
        panel.add(login);
        panel.add(lhaslo);
        panel.add(haslo);
        panel.add(lstanowisko);
        panel.add(stanowisko);
        panel.add(lwiek);
        panel.add(wiek);
        panel.add(lwynagrodzenie);
        panel.add(wynagrodzenie);
        panel.add(dodajPracownika);
        panel.add(info);
        panel.setBorder(BorderFactory.createEmptyBorder(50,30,50,30));
        add(panel);
        setSize(650,350);
        setVisible(true);
    }
}
