package zaliczenie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class GUI extends JFrame {
    JLabel label;
    JPanel panel;
    
    class Logowanie implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            String login = JOptionPane.showInputDialog("Podaj login");
            String haslo = JOptionPane.showInputDialog("Podaj haslo");

            DBConnection db = DBConnection.getInstance();
            Map<String, ? super Object> data = db.getDataUser(login, haslo);
            if(data != null) {
                String typ_konta = (String)data.get("typ_konta");

                Builder b = null;
                if(typ_konta.equals("szef")) {
                    b = new BuilderSzef(data);
                }
                else if(typ_konta.equals("pracownik")) {
                    b = new BuilderPracownik(data);
                }
                Director d = new Director(b);
                User user = d.buildObjectUser();
                new AfterLoginGUI(user);
            }
            else {
                JOptionPane.showMessageDialog(panel, "Błędny login, hasło lub brak połączenia z bazą danych!");
            }      
        }  
    }
    
    GUI() {
        super("Company Control Panel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new JPanel();
        panel.setLayout(new GridLayout(2,0));
        
        JButton logowanie = new JButton("Zaloguj się");
        logowanie.addActionListener(new Logowanie());
        
        label = new JLabel("Witaj w Panelu Zarządzania Firmą!", SwingConstants.CENTER);
        panel.add(label);
        panel.add(logowanie);
        panel.setBorder(BorderFactory.createEmptyBorder(50,30,50,30));
        
        add(panel);

        setSize(450,250);
        setVisible(true);
    }
}
