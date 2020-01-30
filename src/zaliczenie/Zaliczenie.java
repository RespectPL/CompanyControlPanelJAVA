package zaliczenie;

import java.awt.EventQueue;

public class Zaliczenie {    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUI();
            }
        });
    }
}
