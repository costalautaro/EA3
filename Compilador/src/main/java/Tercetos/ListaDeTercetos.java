package Tercetos;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ListaDeTercetos {

    private static final String ARCHIVO_INTERMEDIA = "intermedia.txt";
    private List<Terceto> tercetos = new ArrayList<>();

    public Integer crearTerceto(String valor) {
        Terceto terceto = new Terceto(valor);
        tercetos.add(terceto);
        return terceto.getId();
    }

    public Integer crearTerceto(String valor, String izq) {
        Terceto terceto = new Terceto(valor, izq);
        tercetos.add(terceto);
        return terceto.getId();
    }

    public Integer crearTerceto(String valor, String izq, String der) {
        Terceto terceto = new Terceto(valor, izq, der);
        tercetos.add(terceto);
        return terceto.getId();
    }

    public Integer crearTerceto(String valor, Integer izq) {
        Terceto terceto = new Terceto(valor, String.format("[%02d]", izq));
        tercetos.add(terceto);
        return terceto.getId();
    }

    public Integer crearTerceto(String valor, String izq, Integer der) {
        Terceto terceto = new Terceto(valor, izq, String.format("[%02d]", der));
        tercetos.add(terceto);
        return terceto.getId();
    }

    public void guardarTercetos() {
        try (BufferedWriter br = new BufferedWriter(new FileWriter(ARCHIVO_INTERMEDIA))) {
            tercetos.forEach(terceto -> {
                try {
                    br.write(terceto.toString() + "\n");
                } catch (IOException e) {
                    System.err.println("Ocurrio un error al guardar los tercetos");
                    e.printStackTrace();
                }
            });
            System.out.println("Lista de tercetos guardada en >> " + ARCHIVO_INTERMEDIA);
        } catch (Exception e) {
            System.err.println("Ocurrio un error al guardar el archivo");
            e.printStackTrace();
        }
    }

    public List<Terceto> getTercetos() {
        return tercetos;
    }

    public Integer getCantCreados() {
        return Terceto.getCantCreados();
    }

    public Integer getUltimoCreado() {
        return Terceto.getUltimoCreado();
    }

    public void mostrarTercetos() {
        tercetos.forEach(System.out::println);
    }

}
