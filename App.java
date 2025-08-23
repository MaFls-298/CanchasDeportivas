import java.time.LocalTime;
import javax.swing.*;

public class App{
    public static void main(String[] args) {
        ReservaManager manager = new ReservaManager(LocalTime.of(5,0), LocalTime.of(20,0));

        
        manager.registrarCancha(new Cancha(1, "Futbol", 22, 199.99));
        manager.registrarCancha(new Cancha(1, "Futbol", 22, 249.99));
        manager.registrarCancha(new Cancha(2, "Tenis", 4, 59.99));
        manager.registrarCancha(new Cancha(3, "Basketball", 10, 149.99));


        SwingUtilities.invokeLater(() -> {
            MainReservas view = new MainReservas(manager);
            view.setVisible(true);
        });
    }
}