package datos;

public class ArticulosData {

    private int idArt;
    private String nombreArt;
    private String descripcionArt;
    private Float precioArt;
    private int disponibilidadArt;

    public ArticulosData(int idArt) {
        this.idArt = idArt;
    }

    public ArticulosData(int idArt, String nombreArt, String descripcionArt, Float precioArt, int disponibilidadArt) {
        this.idArt = idArt;
        this.nombreArt = nombreArt;
        this.descripcionArt = descripcionArt;
        this.precioArt = precioArt;
        this.disponibilidadArt = disponibilidadArt;
    }

    public ArticulosData(String nombreArt, String descripcionArt, Float precioArt, int disponibilidadArt) {
        this.nombreArt = nombreArt;
        this.descripcionArt = descripcionArt;
        this.precioArt = precioArt;
        this.disponibilidadArt = disponibilidadArt;
    }

    public int getIdArt() {
        return idArt;
    }

    public void setIdArt(int idArt) {
        this.idArt = idArt;
    }

    public String getNombreArt() {
        return nombreArt;
    }

    public void setNombreArt(String nombreArt) {
        this.nombreArt = nombreArt;
    }

    public String getDescripcionArt() {
        return descripcionArt;
    }

    public void setDescripcionArt(String descripcionArt) {
        this.descripcionArt = descripcionArt;
    }

    public Float getPrecioArt() {
        return precioArt;
    }

    public void setPrecioArt(Float precioArt) {
        this.precioArt = precioArt;
    }

    public int getDisponibilidadArt() {
        return disponibilidadArt;
    }

    public void setDisponibilidadArt(int disponibilidadArt) {
        this.disponibilidadArt = disponibilidadArt;
    }

    @Override
    public String toString() {
        return "ArticulosData{" +
                "idArt=" + idArt +
                ", nombreArt='" + nombreArt + '\'' +
                ", descripcionArt='" + descripcionArt + '\'' +
                ", precioArt=" + precioArt +
                ", disponibilidadArt=" + disponibilidadArt +
                '}';
    }
}
