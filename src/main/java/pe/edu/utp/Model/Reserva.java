package pe.edu.utp.Model;

import java.util.Date;

public class Reserva {

    private int reservaId;
    private int userId;
    private int canchaId;
    private double precioReserva;
    private Date fechaReserva;
    private String horaInicio;
    private String horaFin;
    private String estadoReserva;

    public Reserva(int reservaId, int userId, int canchaId, double precioReserva, Date fechaReserva,
                   String horaInicio, String horaFin, String estadoReserva) {
        this.reservaId = reservaId;
        this.userId = userId;
        this.canchaId = canchaId;
        this.precioReserva = precioReserva;
        this.fechaReserva = fechaReserva;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.estadoReserva = estadoReserva;
    }

    public Reserva() {
    }

    public int getReservaId() {
        return reservaId;
    }

    public void setReservaId(int reservaId) {
        this.reservaId = reservaId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCanchaId() {
        return canchaId;
    }

    public void setCanchaId(int canchaId) {
        this.canchaId = canchaId;
    }

    public double getPrecioReserva() {
        return precioReserva;
    }

    public void setPrecioReserva(double precioReserva) {
        this.precioReserva = precioReserva;
    }

    public Date getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(Date fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public String getEstadoReserva() {
        return estadoReserva;
    }

    public void setEstadoReserva(String estadoReserva) {
        this.estadoReserva = estadoReserva;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "reservaId=" + reservaId +
                ", userId=" + userId +
                ", canchaId=" + canchaId +
                ", precioReserva=" + precioReserva +
                ", fechaReserva=" + fechaReserva +
                ", horaInicio='" + horaInicio + '\'' +
                ", horaFin='" + horaFin + '\'' +
                ", estadoReserva='" + estadoReserva + '\'' +
                '}';
    }
}
