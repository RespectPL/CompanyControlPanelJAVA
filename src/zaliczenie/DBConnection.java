package zaliczenie;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBConnection {
    private Connection conn;
    private Statement stat;
    private ResultSet rs;
    public static DBConnection instance = null; 
    
    public DBConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company","root","");
            stat = conn.createStatement();
            System.out.println("Uzyskano połączenie z bazą danych!");
        } 
        catch(Exception e) {
            System.out.println("Error: "+e);
        }
    }
    
    public static DBConnection getInstance(){
        if(instance == null){
            instance = new DBConnection();
        }
        return instance;
    }
    
    public Map<String, ? super Object> getDataUser(String login, String haslo) {
        try {
            Map<String, ? super Object> daneUzytkownika = new HashMap<>();
            
            String query = "SELECT uzytkownicy.typ_konta, pracownicy.imie, pracownicy.nazwisko, pracownicy.wiek, pracownicy.stanowisko, "
                    + "pracownicy.wynagrodzenie FROM uzytkownicy LEFT JOIN pracownicy ON uzytkownicy.pracownik_id = pracownicy.pracownik_id "
                    + "WHERE uzytkownicy.login = '" + login + "' AND uzytkownicy.haslo = '" + haslo +"'";
            rs = stat.executeQuery(query);
            
            while(rs.next()){
                String imie  = rs.getString("pracownicy.imie");
                String nazwisko = rs.getString("pracownicy.nazwisko");
                String typ_konta = rs.getString("uzytkownicy.typ_konta");
                double wynagrodzenie = Double.parseDouble(rs.getString("pracownicy.wynagrodzenie"));
                int wiek = Integer.parseInt(rs.getString("pracownicy.wiek"));
                String stanowisko = rs.getString("pracownicy.stanowisko");
                
                daneUzytkownika.put("username", login);
                daneUzytkownika.put("imie", imie);
                daneUzytkownika.put("nazwisko", nazwisko);
                daneUzytkownika.put("typ_konta", typ_konta);
                daneUzytkownika.put("wynagrodzenie", wynagrodzenie);
                daneUzytkownika.put("wiek", wiek);
                daneUzytkownika.put("stanowisko", stanowisko);
                
                return daneUzytkownika;
            }
        }
        catch(Exception e) {
            System.out.println("Error: " + e);
        }
        return null;
    }
    
    public String getUserTypKonta(String login, String haslo) {
        try {
            String query = "SELECT typ_konta FROM uzytkownicy WHERE uzytkownicy.login = '" + login + "' AND uzytkownicy.haslo = '" + haslo +"'";
            rs = stat.executeQuery(query);
            
            while(rs.next()){
                String typ_konta = rs.getString("uzytkownicy.typ_konta");
                return typ_konta;
            }
        }
        catch(Exception e) {
            System.out.println("Error: " + e);
        }
        return null;
    }
    
    public int searchIdPracownikViaData(String imie, String nazwisko, String stanowisko, int wiek, double wynagrodzenie) {
        int id = -1;
        try {
            String query = "SELECT pracownik_id FROM pracownicy WHERE imie = '" + imie + "' AND nazwisko = '" + nazwisko + "'"
                    + " AND wiek = " + wiek + " AND wynagrodzenie = " + wynagrodzenie;
            rs = stat.executeQuery(query);
            while(rs.next()) {
                id = rs.getInt("pracownik_id");
            }  
        }
        catch(Exception e) {
            System.out.println("Error: " + e);
        }
        return id;
    }
    
    public void addPracownik(String login, String haslo, String imie, String nazwisko, String stanowisko, int wiek, double wynagrodzenie) {
        try {
            String query = "INSERT INTO pracownicy (imie, nazwisko, stanowisko, wiek, wynagrodzenie) VALUES "
                    + "('" + imie + "', '" + nazwisko + "', '" + stanowisko + "', " + wiek + ", " + wynagrodzenie + ")";
            stat.execute(query);
            System.out.println("Dodano pracownika");
            int pracownik_id = searchIdPracownikViaData(imie, nazwisko, stanowisko, wiek, wynagrodzenie);
            query = "INSERT INTO uzytkownicy (login, haslo, typ_konta, pracownik_id) VALUES "
                    + "('" + login + "', '" + haslo + "', 'pracownik', " + pracownik_id + ")";
            stat.execute(query);
            System.out.println("Dodano uzytkownika");
        }
        catch(Exception e) {
            System.out.println("Error: " + e);
        }
    }
    
    
    public List<Map<String, ? super Object>> getDataAllUser() {
        List<Map<String, ? super Object>> listUser = new ArrayList<>();
        try {
            String query = "SELECT uzytkownicy.login, uzytkownicy.typ_konta, pracownicy.imie, pracownicy.nazwisko, pracownicy.wiek, pracownicy.stanowisko, "
                    + "pracownicy.wynagrodzenie FROM uzytkownicy LEFT JOIN pracownicy ON uzytkownicy.pracownik_id = pracownicy.pracownik_id";
            rs = stat.executeQuery(query);
            
            while(rs.next()){
                Map<String, ? super Object> daneUzytkownika = new HashMap<>();
                String imie  = rs.getString("pracownicy.imie");
                String nazwisko = rs.getString("pracownicy.nazwisko");
                String typ_konta = rs.getString("uzytkownicy.typ_konta");
                String username = rs.getString("uzytkownicy.login");
                double wynagrodzenie = Double.parseDouble(rs.getString("pracownicy.wynagrodzenie"));
                int wiek = Integer.parseInt(rs.getString("pracownicy.wiek"));
                String stanowisko = rs.getString("pracownicy.stanowisko");
                
                daneUzytkownika.put("username", username);
                daneUzytkownika.put("imie", imie);
                daneUzytkownika.put("nazwisko", nazwisko);
                daneUzytkownika.put("typ_konta", typ_konta);
                daneUzytkownika.put("wynagrodzenie", wynagrodzenie);
                daneUzytkownika.put("wiek", wiek);
                daneUzytkownika.put("stanowisko", stanowisko);
                
                listUser.add(daneUzytkownika);
            }
        }
        catch(Exception e) {
            System.out.println("Error: " + e);
        }
        return listUser;
    }
    
    public double checkWynagrodzenie(String imie, String nazwisko) {
        double wynagrodzenie = 0.0;
        try {
            String query = "SELECT wynagrodzenie FROM pracownicy WHERE imie = '" + imie + "' AND nazwisko = '" + nazwisko + "'";
            rs = stat.executeQuery(query);
            
            while(rs.next()){
                wynagrodzenie = Double.parseDouble(rs.getString("wynagrodzenie"));
            }
        }
        catch(Exception e) {
            System.out.println("Error: " + e);
        }
        return wynagrodzenie;
    }
    
    public String checkStanowisko(String imie, String nazwisko) {
        String stanowisko = "";
        try {
            String query = "SELECT stanowisko FROM pracownicy WHERE imie = '" + imie + "' AND nazwisko = '" + nazwisko + "'";
            rs = stat.executeQuery(query);
            
            while(rs.next()){
                stanowisko = rs.getString("stanowisko");
            }
        }
        catch(Exception e) {
            System.out.println("Error: " + e);
        }
        return stanowisko;
    }
    
    public boolean updateWynagrodzenie(String imie, String nazwisko, double wynagrodzenie) {
        try {
            String query = "UPDATE pracownicy SET wynagrodzenie = " + wynagrodzenie + " WHERE imie = '" + imie + "' AND nazwisko = '" + nazwisko + "'";
            int a = stat.executeUpdate(query);
            if(a == 1) {
                System.out.println("Zaktualizowano wynagrodzenie pracownika " + imie + " " + nazwisko);
                return true;
            }
            else {
                System.out.println("Błąd");
                return false;
            }
        }
        catch(Exception e) {
            System.out.println("Error: " + e);
            return false;
        }
    }
    
    public boolean updateStanowisko(String imie, String nazwisko, String stanowisko) {
        try {
            String query = "UPDATE pracownicy SET stanowisko = '" + stanowisko + "' WHERE imie = '" + imie + "' AND nazwisko = '" + nazwisko + "'";
            int a = stat.executeUpdate(query);
            if(a == 1) {
                System.out.println("Zaktualizowano stanowisko pracownika " + imie + " " + nazwisko);
                return true;
            }
            else {
                System.out.println("Błąd");
                return false;
            }
        }
        catch(Exception e) {
            System.out.println("Error: " + e);
            return false;
        }
    }
    
    public boolean addZebranie(String temat, String opis, String data, String godzina) {
        try {
            String query = "INSERT INTO zebranie (data, godzina, temat, opis) VALUES "
                    + "('" + data + "', '" + godzina + "', '" + temat + "', '" + opis + "')";
            int check = stat.executeUpdate(query);
            if(check == 1) {
                System.out.println("Dodano zebranie");
                return true;
            }
            else {
                System.out.println("Błąd! Nie dodano zebrania!");
                return false;
            }
        }
        catch(Exception e) {
            System.out.println("Error: " + e);
            return false;
        }
    }
    
    public List<Map<String, String>> getAllMeetings() {
        List<Map<String, String>> listMeetings = new ArrayList<>();
        try {
            String query = "SELECT data, godzina, temat, opis FROM zebranie";
            rs = stat.executeQuery(query);
            
            while(rs.next()){
                Map<String, String> daneZebrania = new HashMap<>();
                String data  = rs.getString("data");
                String godzina = rs.getString("godzina");
                String temat = rs.getString("temat");
                String opis = rs.getString("opis");
                
                daneZebrania.put("data", data);
                daneZebrania.put("godzina", godzina);
                daneZebrania.put("temat", temat);
                daneZebrania.put("opis", opis);
                
                listMeetings.add(daneZebrania);
            }
        }
        catch(Exception e) {
            System.out.println("Error: " + e);
        }
        return listMeetings;
    }
}
