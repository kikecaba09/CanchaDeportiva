package pe.edu.utp.Model;

public class Cancha {

    private int canchaId;
    private int nroCancha;
    private double precioDia;
    private double precioNoche;
    private String imagenCancha;
    private String horaAbierto;
    private String horaCerrado;

    public Cancha(int canchaId, int nroCancha, double precioDia, double precioNoche,
                  String imagenCancha, String horaAbierto, String horaCerrado) {
        this.canchaId = canchaId;
        this.nroCancha = nroCancha;
        this.precioDia = precioDia;
        this.precioNoche = precioNoche;
        this.imagenCancha = imagenCancha;
        this.horaAbierto = horaAbierto;
        this.horaCerrado = horaCerrado;
    }

    public Cancha() {
    }

    public int getCanchaId() {
        return canchaId;
    }

    public void setCanchaId(int canchaId) {
        this.canchaId = canchaId;
    }

    public int getNroCancha() {
        return nroCancha;
    }

    public void setNroCancha(int nroCancha) {
        this.nroCancha = nroCancha;
    }

    public double getPrecioDia() {
        return precioDia;
    }

    public void setPrecioDia(double precioDia) {
        this.precioDia = precioDia;
    }

    public double getPrecioNoche() {
        return precioNoche;
    }

    public void setPrecioNoche(double precioNoche) {
        this.precioNoche = precioNoche;
    }

    public String getImagenCancha() {
        return imagenCancha;
    }

    public void setImagenCancha(String imagenCancha) {
        this.imagenCancha = imagenCancha;
    }

    public String getHoraAbierto() {
        return horaAbierto;
    }

    public void setHoraAbierto(String horaAbierto) {
        this.horaAbierto = horaAbierto;
    }

    public String getHoraCerrado() {
        return horaCerrado;
    }

    public void setHoraCerrado(String horaCerrado) {
        this.horaCerrado = horaCerrado;
    }

    @Override
    public String toString() {
        return "Cancha{" +
                "canchaId=" + canchaId +
                ", nroCancha=" + nroCancha +
                ", precioDia=" + precioDia +
                ", precioNoche=" + precioNoche +
                ", imagenCancha='" + imagenCancha + '\'' +
                ", horaAbierto='" + horaAbierto + '\'' +
                ", horaCerrado='" + horaCerrado + '\'' +
                '}';
    }
}
