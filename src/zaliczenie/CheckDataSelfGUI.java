package zaliczenie;

import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.*;

public class CheckDataSelfGUI extends JFrame {
    CheckDataSelfGUI(User user) {
        super("Company Control Panel (" + user.username + ") - Sprawdź swoje dane");
        JPanel panel = new JPanel(new GridLayout(0,2));
        String imie = user.getImie();
        String nazwisko = user.getNazwisko();
        int wiek = user.getWiek();
        double wynagrodzenie = user.getWynagrodzenie();
        String username = user.getUsername();
        String stanowisko = user.getStanowisko();
        
        Font font = new Font("Arial", Font.BOLD, 20);
        
        JLabel limie = new JLabel("Imie");
        limie.setFont(font);
        JLabel lnazwisko = new JLabel("Nazwisko");
        lnazwisko.setFont(font);
        JLabel lusername = new JLabel("Username");
        lusername.setFont(font);
        JLabel lwiek = new JLabel("Wiek");
        lwiek.setFont(font);
        JLabel lstanowisko = new JLabel("Stanowisko");
        lstanowisko.setFont(font);
        JLabel lwynagrodzenie = new JLabel("Wynagrodzenie");
        lwynagrodzenie.setFont(font);
        
        JLabel limie2 = new JLabel(imie);
        limie2.setFont(font);
        JLabel lnazwisko2 = new JLabel(nazwisko);
        lnazwisko2.setFont(font);
        JLabel lusername2 = new JLabel(username);
        lusername2.setFont(font);
        JLabel lwiek2 = new JLabel("" + wiek);
        lwiek2.setFont(font);
        JLabel lstanowisko2 = new JLabel(stanowisko);
        lstanowisko2.setFont(font);
        JLabel lwynagrodzenie2 = new JLabel("" + wynagrodzenie + " PLN");
        lwynagrodzenie2.setFont(font);
        
        panel.add(limie);
        panel.add(limie2);
        panel.add(lnazwisko);
        panel.add(lnazwisko2);
        panel.add(lusername);
        panel.add(lusername2);
        panel.add(lwiek);
        panel.add(lwiek2);
        panel.add(lstanowisko);
        panel.add(lstanowisko2);
        panel.add(lwynagrodzenie);
        panel.add(lwynagrodzenie2);
        
        panel.setBorder(BorderFactory.createEmptyBorder(50,30,50,30));
        add(panel);
        setSize(650,350);
        setVisible(true);
    }
}
