package Tabla;

public class Simbolo {
    private String nombre;
    private String valor;
    private String tipo;
    private Integer longitud;

    public Simbolo(String nombre, String tipo, String valor, Integer longitud) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.valor = valor;
        this.longitud = longitud;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Integer getLongitud() {
        return longitud;
    }

    public void setLongitud(Integer longitud) {
        this.longitud = longitud;
    }

    private String oGuion(Object o) {
        return o != null ? "" + o : "-";
    }

    @Override
    public String toString() {
        return String.format(
                "%-45s|%-45s|%-45s|%-45s", oGuion(nombre), oGuion(tipo), oGuion(valor), oGuion(longitud)
        );
    }
}
