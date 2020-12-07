package Tabla;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class TablaDeSimbolos {

    private static final String ARCHIVO_TABLA_SIMBOLOS = "ts.txt";
    private List<Simbolo> listaDeSimbolos;
    private static Integer CANT_CTES_STRING_CREADAS = 0;

    public TablaDeSimbolos() {
        this.listaDeSimbolos = new LinkedList<>();
    }

    public void agregarEnTabla(String nombre, String tipo, String valor, Integer longitud) {
        if (tipo.equals("String") && !existeEnTablaString(valor)) {
            String nombreCteString = "_cte_s" + CANT_CTES_STRING_CREADAS++;
            listaDeSimbolos.add(new Simbolo(nombreCteString, tipo, valor, longitud));
        } else if (!existeEnTabla(nombre)) listaDeSimbolos.add(new Simbolo(nombre, tipo, valor, longitud));
    }

    public Boolean existeEnTabla(String nombre) {
        return listaDeSimbolos.stream().anyMatch(s -> nombre.equals(s.getNombre()));
    }

    public Boolean existeEnTablaString(String valor) {
        return listaDeSimbolos.stream().anyMatch(s -> valor.equals(s.getValor()));
    }

    public void recorrerTabla() {
        listaDeSimbolos.forEach(System.out::println);
    }

    public void guardarTabla() {
        try (BufferedWriter br = new BufferedWriter(new FileWriter(ARCHIVO_TABLA_SIMBOLOS))) {

            br.write(String.format("%-45s|%-45s|%-45s|%-45s\n", "NOMBRE", "TIPODATO", "VALOR", "LONGITUD"));
            listaDeSimbolos.forEach(simbolo -> {
                try {
                    br.write(simbolo.toString() + "\n");
                } catch (IOException e) {
                    System.err.println("Ocurrio un error al guardar los simbolos");
                    e.printStackTrace();
                }
            });
            System.out.println("Tabla de simbolos guardada en >> " + ARCHIVO_TABLA_SIMBOLOS);
        } catch (Exception e) {
            System.err.println("Ocurrio un error al guardar el archivo");
            e.printStackTrace();
        }
    }

    public Simbolo buscarSimboloPorValor(String valor){
        return listaDeSimbolos.stream().filter(s -> valor.equals(s.getValor())).findFirst().get();
    }

    public List<Simbolo> getListaDeSimbolos() {
        return listaDeSimbolos;
    }
}
