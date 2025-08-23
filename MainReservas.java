import java.time.LocalDate;
import java.time.LocalTime;
import javax.swing.*;

public class MainReservas extends JFrame {
    private ReservaManager manager;

    public MainReservas(ReservaManager manager){
        this.manager = manager;

        
        setTitle("RESERVA CANCHAS DEPORTIIVAS");
        setSize(900, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        JButton btnNewRes = new JButton("Nueva Reserva");
        JButton btnViewRes = new JButton("Ver Reservas");
        JButton btnSalir = new JButton("Salir");


        JPanel panel = new JPanel();
        panel.add(btnNewRes);
        panel.add(btnViewRes);
        panel.add(btnSalir);

        add(panel);

        btnNewRes.addActionListener(e -> crearNuevaReserva());
        btnViewRes.addActionListener(e -> mostrarReservas());
        btnSalir.addActionListener(e -> System.exit(0));
    }

    private void crearNuevaReserva(){
        String responsable = JOptionPane.showInputDialog("Nombre del responsable:");
        String tipo = JOptionPane.showInputDialog("Tiipo de cancha (Futbol/Tenis/Basketball):");
        int jugadores = Integer.parseInt(JOptionPane.showInputDialog("Numero de jugadores:"));


        LocalDate fecha = LocalDate.now();
        LocalTime inicio = LocalTime.of(5, 0);
        LocalTime fin = LocalTime.of(8, 0);

        SolicitudReserva solicitud = new SolicitudReserva(responsable, tipo, jugadores, fecha, inicio, fin);
        boolean aprobada = manager.aprobarReserva(solicitud);

        if (aprobada) {
            JOptionPane.showMessageDialog(this, "✅ Reserva confirmada");
        } else {
            JOptionPane.showMessageDialog(this, "⚠️ No disponible, anadida a la lista de espera");
        }
    }

    private void mostrarReservas(){
        if (manager.getReservasConfirmadas().isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay reservas confirmadas todavía.");
        } else {
            StringBuilder sb = new StringBuilder("Reservas confirmadas:\n");
            for (SolicitudReserva r : manager.getReservasConfirmadas()) {
                sb.append("- ").append(r.getResponsable()).append(" | ").append(r.getTipoCanchaSolicitada()).append(" | ").append(r.getFechaReserva()).append("\n");
            }
            JOptionPane.showMessageDialog(this, sb.toString());
        }
    }
}