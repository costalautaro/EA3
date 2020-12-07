package Assembler;

import Tabla.Simbolo;
import Tercetos.Terceto;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;

public class GestorDeCodigoAssembler {

    private static final String ARCHIVO_ASSEMBLER = "Final.asm";
    private ConstructorDeAssembler constructorDeAssembler = new ConstructorDeAssembler();

    public void escribirAssemblerDeTercetos(List<Terceto> tercetos, List<Simbolo> listaDeSimbolos) {
        try (BufferedWriter br = new BufferedWriter(new FileWriter(ARCHIVO_ASSEMBLER))) {

            String instrucciones = constructorDeAssembler.generarInstrucciones(tercetos);
            String header = constructorDeAssembler.generarHeader(listaDeSimbolos);
            String codigo = constructorDeAssembler.generarCodigo(instrucciones);

            br.write(header);
            br.write(codigo);

            System.out.println("Codigo assembler guardado en >> " + ARCHIVO_ASSEMBLER);
        } catch (Exception e) {
            System.err.println("Ocurrio un error al guardar el archivo");
            e.printStackTrace();
        }
    }

}
