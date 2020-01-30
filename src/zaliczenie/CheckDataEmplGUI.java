package zaliczenie;

import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.swing.*;

public class CheckDataEmplGUI extends JFrame {
    CheckDataEmplGUI(User user) {
        super("Company Control Panel (" + user.username + ") - Sprawdź dane pracowników");
        DBConnection db = DBConnection.getInstance();
        List<Map<String, ? super Object>> listUser = db.getDataAllUser();
        String[] colName = { "Imię", "Nazwisko", "Wiek", "Stanowisko" , "Wynagrodzenie" };
        Object[][] data = new Object[listUser.size()][5];
        int i = 0;
        for(Map<String, ? super Object> dataUser : listUser) {
            Object[] d = new Object[5];
            Set<Entry<String, ? super Object>> entrySet = dataUser.entrySet();
            for(Entry<String, ? super Object> entry: entrySet) {
                if(entry.getKey().equals("imie")) {
                    d[0] = entry.getValue();
                }
                else if(entry.getKey().equals("nazwisko")) {
                    d[1] = entry.getValue();
                }
                else if(entry.getKey().equals("wiek")) {
                    d[2] = entry.getValue();
                }
                else if(entry.getKey().equals("stanowisko")) {
                    d[3] = entry.getValue();
                }
                else if(entry.getKey().equals("wynagrodzenie")) {
                    d[4] = entry.getValue();
                }
            }
            data[i] = d;
            i++;
        }
        JTable table = new JTable(data, colName);

        add(table.getTableHeader(), BorderLayout.PAGE_START);
        add(table, BorderLayout.CENTER);
        
        setSize(1050,350);
        setVisible(true);
    }
}
