package pe.edu.utp.Model;

import java.util.Date;

public class Pago {

    private int pagoId;
    private int reservaId;
    private String metodoPago;
    private double monto;
    private Date fechaPago;

    public Pago(int pagoId, int reservaId, String metodoPago, double monto, Date fechaPago) {
        this.pagoId = pagoId;
        this.reservaId = reservaId;
        this.metodoPago = metodoPago;
        this.monto = monto;
        this.fechaPago = fechaPago;
    }

    public Pago() {
    }

    public int getPagoId() {
        return pagoId;
    }

    public void setPagoId(int pagoId) {
        this.pagoId = pagoId;
    }

    public int getReservaId() {
        return reservaId;
    }

    public void setReservaId(int reservaId) {
        this.reservaId = reservaId;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    @Override
    public String toString() {
        return "Pago{" +
                "pagoId=" + pagoId +
                ", reservaId=" + reservaId +
                ", metodoPago='" + metodoPago + '\'' +
                ", monto=" + monto +
                ", fechaPago='" + fechaPago + '\'' +
                '}';
    }
}
