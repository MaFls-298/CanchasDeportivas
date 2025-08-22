
import java.time.LocalTime;
import java.util.*;

public class ReservaManager{

    private List<Cancha> canchasDisponibles;
    private List<SolicitudReserva> reservasConfirmadas;
    private Queue<SolicitudReserva> reservasWaitList;
    private LocalTime horaApertura;
    private LocalTime horaCierre;

    public ReservaManager(LocalTime horaApertura, LocalTime horaCierre){
        this.canchasDisponibles = new ArrayList<>();
        this.reservasConfirmadas = new ArrayList<>();
        this.reservasWaitList = new LinkedList<>();
        this.horaApertura = horaApertura;
        this.horaCierre = horaCierre;
    }

    public void registrarCancha(Cancha cancha) {
        canchasDisponibles.add(cancha);
    }

    public boolean verificarHorario(SolicitudReserva solicitud) {
        return !solicitud.getHoraReservaEntrada().isBefore(horaApertura) && !solicitud.getHoraReservaSalida().isAfter(horaCierre);
    }

    public boolean verificarCapacidad(Cancha cancha, SolicitudReserva solicitud) {
        return solicitud.getNoJugadores() <= cancha.getCapacidadMaxima();
    }

    public boolean verificarDisponibilidad(Cancha cancha, SolicitudReserva newSolicitud) {
        for (SolicitudReserva reserva : cancha.getReservasAsignadas()) {
            if (reserva.getFechaReserva().equals(newSolicitud.getFechaReserva())) {
                boolean unav = !(newSolicitud.getHoraReservaSalida().isBefore(reserva.getHoraReservaEntrada()) || newSolicitud.getHoraReservaEntrada().isAfter(reserva.getHoraReservaSalida()));
                if (unav) return false;
            }
        }
        return true;
    }

    public boolean aprobarReserva(SolicitudReserva solicitud) {
        for (Cancha cancha : canchasDisponibles) {
            if (cancha.getTipoCancha().equalsIgnoreCase(solicitud.getTipoCanchaSolicitada())) {
                if (verificarHorario(solicitud) && verificarCapacidad(cancha, solicitud) && verificarDisponibilidad(cancha, solicitud)) {
                    cancha.asignarReserva(solicitud);
                    solicitud.setApproved(true);
                    reservasConfirmadas.add(solicitud);
                    return true;
                }
            }
        }
        reservasWaitList.add(solicitud);
        return false;
    }

    public void cancelarReserva(SolicitudReserva reserva) {
        for (Cancha cancha : canchasDisponibles) {
            if (cancha.getReservasAsignadas().contains(reserva)) {
                cancha.cancelarReserva(reserva);
                reservasConfirmadas.remove(reserva);
                break;
            }
        }
        if (!reservasWaitList.isEmpty()) {
            SolicitudReserva inWaiting = reservasWaitList.poll();
            aprobarReserva(inWaiting);
        }
    }

    public List<Cancha> getCanchasDisponibles(){
        return canchasDisponibles;
    }

    public List<SolicitudReserva> getReservasConfirmadas(){
        return reservasConfirmadas; 
        }

    public Queue<SolicitudReserva> getReservasWaitList(){ 
        return reservasWaitList; 
    }

}