//package reservaCanchas.model;

import java.util.ArrayList;
import java.util.List;


public class Cancha{
    private int idCancha;
    private String tipoCancha;
    private int capacidadMaxima;
    private Double costoXhora;
    private List<SolicitudReserva> reservasAsignadas;


    public Cancha(int idCancha, String tipoCancha, int capacidadMaxima, Double costoXhora){
        this.idCancha = idCancha;
        this.tipoCancha = tipoCancha;
        this.capacidadMaxima = capacidadMaxima;
        this.costoXhora = costoXhora;
        this.reservasAsignadas = new ArrayList<>();
    }

    
    public int getIdCancha(){
        return idCancha;
    }

    public String getTipoCancha(){
        return tipoCancha;
    }

    public int getCapacidadMaxima(){
        return capacidadMaxima;
    }

    public Double getCostoXhora(){
        return costoXhora;
    }

    public List<SolicitudReserva> getReservasAsignadas(){
        return reservasAsignadas;
    }

    public void asignarReserva(SolicitudReserva reserva) {
        reservasAsignadas.add(reserva);
    }

    public void cancelarReserva(SolicitudReserva reserva) {
        reservasAsignadas.remove(reserva);
    }

    public String mostrarInfoCancha() {
    return "Cancha #" + idCancha + 
            " • Tipo: " + tipoCancha + 
            " • Capacidad: " + capacidadMaxima + 
            " • Precio por hora: $" + costoXhora;
    }
    
}