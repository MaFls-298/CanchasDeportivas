//package reservaCanchas.model;

import  java.time.LocalDate;
import java.time.LocalTime;

public class SolicitudReserva{
    private String responsable;
    private String tipoCanchaSolicitada;
    private int noJugadores;
    private LocalDate fechaReserva;
    private LocalTime horaReservaEntrada;
    private LocalTime horaReservaSalida;
    private boolean approved; 

    public SolicitudReserva(String responsable, String tipoCanchaSolicitada, int noJugadores, LocalDate fechaReserva, LocalTime horaReservaEntrada, LocalTime horaReservaSalida){
        this.responsable = responsable;
        this.tipoCanchaSolicitada = tipoCanchaSolicitada;
        this.noJugadores = noJugadores;
        this.fechaReserva = fechaReserva;
        this.horaReservaEntrada = horaReservaEntrada;
        this.horaReservaSalida = horaReservaSalida;
        this.approved = false;
    }
    

    public String getResponsable(){
        return responsable;
    }

    public String getTipoCanchaSolicitada(){
        return tipoCanchaSolicitada;
    }

    public int getNoJugadores(){
        return noJugadores;
    }

    public LocalDate getFechaReserva(){
        return fechaReserva;
    }

    public LocalTime getHoraReservaEntrada(){
        return horaReservaEntrada;
    }

    public LocalTime getHoraReservaSalida(){
        return horaReservaSalida;
    }

    public boolean isApproved(){
        return approved;
    }

    public void setApproved(boolean approved){
        this.approved = approved;
    }

    public float getHorasUso() {
        return (float) java.time.Duration.between(horaReservaEntrada, horaReservaSalida).toHours();
    }

}