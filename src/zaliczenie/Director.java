package zaliczenie;

public class Director {
    private Builder builder;
    
    public Director(Builder builder) {
        this.builder = builder;
    }
    
    public User buildObjectUser() {
        User u = new User();
        u.setType(builder.buildType());
        u.setImie(builder.buildImie());
        u.setNazwisko(builder.buildNazwisko());
        u.setWiek(builder.buildWiek());
        u.setUsername(builder.buildUsername());
        u.setStanowisko(builder.buildStanowisko());
        u.setWynagrodzenie(builder.buildWynagrodzenie());
        return u;
    }
}
