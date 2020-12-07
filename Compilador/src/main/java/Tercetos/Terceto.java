package Tercetos;

public class Terceto {
    private static Integer CANT_CREADOS = 0;

    private final Integer id;
    private final String valor;

    private final String izq;
    private final String der;

    public Terceto(String valor, String izq, String der) {
        this.id = CANT_CREADOS++;
        this.valor = valor;
        this.izq = izq;
        this.der = der;
    }

    public Terceto(String valor, String izq) {
        this.id = CANT_CREADOS++;
        this.valor = valor;
        this.izq = izq;
        this.der = null;
    }

    public Terceto(String valor) {
        this.id = CANT_CREADOS++;
        this.valor = valor;
        this.izq = null;
        this.der = null;
    }

    @Override
    public String toString() {
        String izq = this.izq != null ? this.izq : "_";
        String der = this.der != null ? this.der : "_";
        return String.format("[%02d]\t( %s, %s, %s )", this.id, this.valor, izq, der);
    }

    public Integer getId() {
        return id;
    }

    public String getValor() {
        return valor;
    }

    public String getIzq() {
        return izq;
    }

    public String getDer() {
        return der;
    }

    public static Integer getCantCreados() {
        return CANT_CREADOS;
    }

    public static Integer getUltimoCreado() {
        return CANT_CREADOS - 1;
    }
}
