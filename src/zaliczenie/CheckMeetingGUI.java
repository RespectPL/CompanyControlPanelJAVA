package zaliczenie;

import java.awt.BorderLayout;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.*;

public class CheckMeetingGUI extends JFrame {
    CheckMeetingGUI(User user) {
        super("Company Control Panel (" + user.username + ") - Sprawdź ustalone zebrania");
        DBConnection db = DBConnection.getInstance();
        List<Map<String, String>> listMeetings = db.getAllMeetings();
        String[] colName = { "Data", "Godzina", "Temat", "Opis" };
        Object[][] data = new Object[listMeetings.size()][4];
        int i = 0;
        for(Map<String, String> dataMeeting : listMeetings) {
            Object[] d = new Object[4];
            Set<Map.Entry<String, String>> entrySet = dataMeeting.entrySet();
            for(Map.Entry<String, String> entry: entrySet) {
                if(entry.getKey().equals("data")) {
                    d[0] = entry.getValue();
                }
                else if(entry.getKey().equals("godzina")) {
                    d[1] = entry.getValue();
                }
                else if(entry.getKey().equals("temat")) {
                    d[2] = entry.getValue();
                }
                else if(entry.getKey().equals("opis")) {
                    d[3] = entry.getValue();
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
