package zaliczenie;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import javax.swing.*;

public class AfterLoginGUI extends JFrame {
    JLabel info;
    JLabel label;
    JPanel panel;
    User user;
    JFileChooser fc = new JFileChooser();
    
    class AddEmpl implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            new AddEmpGUI(user);     
        }  
    }
    
    class CheckDataEmpl implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            new CheckDataEmplGUI(user);     
        }  
    }
    
    class ChangeSalaryEmpl implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            new ChangeSalaryEmplGUI(user);     
        }  
    }
    
    class ChangePositionEmpl implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            new ChangePositionEmplGUI(user);     
        }  
    }
    
    class DetermineMeeting implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            new DetermineMeetingGUI(user);     
        }  
    }
    
    class SaveDataEmplToFile implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
             fc.showSaveDialog(panel);
             File f = fc.getSelectedFile();
             try {
                 PrintWriter pw = new PrintWriter(f);
                 DBConnection db = DBConnection.getInstance();
                 List<Map<String, ? super Object>> listUser = db.getDataAllUser();
                 for(Map<String, ? super Object> dataUser : listUser) {
                    Set<Entry<String, ? super Object>> entrySet = dataUser.entrySet();
                    for(Entry<String, ? super Object> entry: entrySet) {
			pw.println(entry.getKey() + ": " + entry.getValue());
                    }
                    pw.println("--------------------------------------------------");
                 }
                 pw.close();
             }
             catch(IOException e) {
                 System.out.println("Error: " + e);
             }
        }  
    }
    
    class CheckSalary implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            new CheckSalaryGUI(user);     
        }  
    }
    
    class CheckPosition implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            new CheckPositionGUI(user);     
        }  
    }
    
    class CheckDataSelf implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            new CheckDataSelfGUI(user);     
        }  
    }
    
    class CheckMeeting implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            new CheckMeetingGUI(user);     
        }  
    }
    
    class SaveMeetingsToFile implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            fc.showSaveDialog(panel);
             File f = fc.getSelectedFile();
             try {
                 PrintWriter pw = new PrintWriter(f);
                 DBConnection db = DBConnection.getInstance();
                 List<Map<String, String>> listMeetings = db.getAllMeetings();
                 for(Map<String, String> dataMeeting : listMeetings) {
                    Set<Entry<String, String>> entrySet = dataMeeting.entrySet();
                    for(Entry<String, String> entry: entrySet) {
			pw.println(entry.getKey() + ": " + entry.getValue());
                    }
                    pw.println("--------------------------------------------------");
                 }
                 pw.close();
             }
             catch(IOException e) {
                 System.out.println("Error: " + e);
             }
        }  
    }
    
    AfterLoginGUI(User user) {
        super("Company Control Panel (" + user.username + ")");
        this.user = user;
        info = new JLabel("Jesteś zalogowany!", SwingConstants.CENTER);
        label = new JLabel(user.getImie() + " " + user.getNazwisko() + " (" + user.getUsername() + ")", SwingConstants.CENTER);
        panel = new JPanel(new GridLayout(0,2));
        panel.add(info);
        panel.add(label);
        JButton checkDataEmpl = new JButton("Sprawdź dane pracowników");
        checkDataEmpl.addActionListener(new CheckDataEmpl());
        JButton changeSalaryEmpl = new JButton("Zmień wynagrodzenie pracownika");
        changeSalaryEmpl.addActionListener(new ChangeSalaryEmpl());
        JButton changePositionEmpl = new JButton("Zmień stanowisko pracownika");
        changePositionEmpl.addActionListener(new ChangePositionEmpl());
        JButton addEmpl = new JButton("Dodaj pracownika");
        addEmpl.addActionListener(new AddEmpl());
        JButton determineMeeting = new JButton("Ustal zebranie");
        determineMeeting.addActionListener(new DetermineMeeting());
        JButton saveDataEmplToFile = new JButton("Zapisz dane pracowników do pliku");
        saveDataEmplToFile.addActionListener(new SaveDataEmplToFile());
        
        JButton checkSalary = new JButton("Sprawdź swoje wynagrodzenie");
        checkSalary.addActionListener(new CheckSalary());
        JButton checkPosition = new JButton("Sprawdź swoje stanowisko");
        checkPosition.addActionListener(new CheckPosition());
        
        JButton checkDataSelf = new JButton("Sprawdź swoje dane");
        checkDataSelf.addActionListener(new CheckDataSelf());
        JButton checkMeeting = new JButton("Sprawdź zaplanowane zebrania");
        checkMeeting.addActionListener(new CheckMeeting());
        JButton saveMeetingsToFile = new JButton("Zapisz dane o zebraniach do pliku");
        saveMeetingsToFile.addActionListener(new SaveMeetingsToFile());
        
        if(user.getType().equals("Szef")) {
            panel.add(addEmpl);
            panel.add(checkDataEmpl);
            panel.add(changeSalaryEmpl);
            panel.add(changePositionEmpl);
            panel.add(determineMeeting);
            panel.add(checkSalary);
            panel.add(checkPosition);
            panel.add(saveDataEmplToFile);
            panel.add(checkMeeting);
            panel.add(checkDataSelf);
        }
        else if(user.getType().equals("Pracownik")) {
            panel.add(checkDataSelf);
            panel.add(checkSalary);
            panel.add(checkPosition);
            panel.add(checkMeeting);
            panel.add(saveMeetingsToFile);
        }
        panel.setBorder(BorderFactory.createEmptyBorder(50,30,50,30));
        add(panel);
        setSize(650,350);
        setVisible(true);
    }
}
