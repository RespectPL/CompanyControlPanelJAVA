package zaliczenie;

import java.util.Map;

public class BuilderSzef implements Builder {
    private Map<String, ? super Object> data;
    
    public BuilderSzef(Map<String, ? super Object> data) {
        this.data = data;
    }
    
    public String buildType() {
        return "Szef";
    }

    public String buildUsername() {
        return (String)data.get("username");
    }

    public String buildImie() {
        return (String)data.get("imie");
    }

    public String buildNazwisko() {
        return (String)data.get("nazwisko");
    }

    public int buildWiek() {
        return (int)data.get("wiek");
    }

    public String buildStanowisko() {
        return (String)data.get("stanowisko");
    }
    
    public double buildWynagrodzenie() {
        return (double)data.get("wynagrodzenie");
    }
    
}
