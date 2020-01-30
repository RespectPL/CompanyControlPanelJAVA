package zaliczenie;

public class User {
    protected String username;
    protected String type;
    protected String imie;
    protected String nazwisko;
    protected int wiek;
    protected String stanowisko;
    protected double wynagrodzenie;
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public void setImie(String imie) {
        this.imie = imie;
    }
    
    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }
    
    public void setWiek(int wiek) {
        this.wiek = wiek;
    }
    
    public void setStanowisko(String stanowisko) {
        this.stanowisko = stanowisko;
    }
    
    public void setWynagrodzenie(double wynagrodzenie) {
        this.wynagrodzenie = wynagrodzenie;
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getType() {
        return type;
    }
    
    public String getImie() {
        return imie;
    }
    
    public String getNazwisko() {
        return nazwisko;
    }
    
    public int getWiek() {
        return wiek;
    }
    
    public String getStanowisko() {
        return stanowisko;
    }
    
    public double getWynagrodzenie() {
        return wynagrodzenie;
    }
    
    @Override
    public String toString() {
        return imie + " " + nazwisko + " (" + username +"): " + stanowisko + ", " + wiek + ", " + wynagrodzenie; 
    }
}
