package Assembler;

import Tabla.Simbolo;
import Tercetos.Terceto;

import java.util.List;

public class ConstructorDeAssembler {

    public String generarHeader(List<Simbolo> listaDeSimbolos) {
        String includes =
                "include number.asm\n" +
                        "include macros2.asm\n\n" +
                        ".MODEL LARGE ;Modelo de Memoria\n" +
                        ".386 ;Tipo de Procesador\n" +
                        ".STACK 200h ;Bytes en el Stack\n\n";
        String data =
                ".DATA\n" +
                        "%s\n";

        String dataGenerada = listaDeSimbolos.stream()
                .map(this::tsEnFormatoAssembler)
                .reduce("", (subtotal, linea) -> subtotal + linea) + "\n";

        return includes + String.format(data, dataGenerada);
    }

    public String generarCodigo(String codigoPrograma) {
        String codigo =
                ".CODE\n\n" +
                        "start:\n" +
                        "\tMOV EAX,@DATA\n" +
                        "\tMOV DS,EAX\n" +
                        "\tMOV ES,EAX\n\n" +
                        "%s\n" +
                        "ETIQfin: \n"     +
                        "\tMOV EAX, 4C00h\n" +
                        "\tINT 21h\n\n" +
                        "\tEND start";
        return String.format(codigo, codigoPrograma);
    }

    private String tsEnFormatoAssembler(Simbolo linea) {
        String valor;
        String nombre;
        switch (linea.getTipo()) {
            case "Integer":
                valor = linea.getValor() != null ? Double.valueOf(linea.getValor()).toString() : "?";
                nombre = linea.getNombre();
                return String.format("\t%s\tdd\t%s\n", nombre, valor);
            case "String":
                valor = linea.getValor();
                nombre = linea.getNombre();
                return String.format("\t%s\tdb\t%s,'$', %s dup (?)\n", nombre, valor, linea.getLongitud());
            default:
                throw new IllegalStateException("Tipo inesperado: " + linea.getTipo());
        }
    }

    public String generarInstrucciones(List<Terceto> tercetos) {
        StringBuilder assembler = new StringBuilder();
        tercetos.forEach(terceto -> {
            switch (terceto.getValor()) {
                case "=":
                    assembler.append(generarCodigoAsignacion(terceto));
                    break;
                case "+":
                    assembler.append(gestorSuma(terceto));
                    break;
                case "++":
                    assembler.append(generarCodigoIncremento(terceto));
                    break;
                case "READ":
                    assembler.append(generarCodigoRead(terceto));
                    break;
                case "WRITE":
                    assembler.append(generarCodigoWrite(terceto));
                    break;
                case "ETIQ":
                    assembler.append(generarCodigoEtiq(terceto));
                    break;
                case "BGE":
                    assembler.append(generarCodigoBGE(terceto));
                    break;
                case "CMP":
                    assembler.append(generarCodigoCMP(terceto));
                    break;
                case "BLE":
                    assembler.append(generarCodigoBLE(terceto));
                    break;
                case "BNE":
                    assembler.append(generarCodigoBNE(terceto));
                    break;
                case "BEQ":
                    assembler.append(generarCodigoBEQ(terceto));
                    break;
                case "BGT":
                    assembler.append(generarCodigoBGT(terceto));
                    break;
                case "JMP":
                    assembler.append(generarCodigoJMP(terceto));
                    break;   
                default:
                    throw new IllegalStateException("Valor inesperado en el terceto: " + terceto.getValor());
            }
        });

        return assembler.toString();
    }

    private String generarCodigoBGE(Terceto terceto) {
        String id = terceto.getIzq().replace("[", "").replace("]", "");
        return formatAssembler("JAE", String.format("ETIQ%s", id)) + "\n";
    }
    
    private String generarCodigoBGT(Terceto terceto) {
        String id = terceto.getIzq().replace("[", "").replace("]", "");
        return formatAssembler("JA", String.format("ETIQ%s", id)) + "\n";
    }
    
    private String generarCodigoBNE(Terceto terceto) {
        String id = terceto.getIzq().replace("[", "").replace("]", "");
        return formatAssembler("JNE", String.format("ETIQ%s", id)) + "\n";
    }
    
    private String generarCodigoBEQ(Terceto terceto) {
        String id = terceto.getIzq().replace("[", "").replace("]", "");
        return formatAssembler("JE", String.format("ETIQ%s", id)) + "\n";
    }

    private String generarCodigoJMP(Terceto terceto) {
        String id = terceto.getIzq().replace("[", "").replace("]", "");
        return formatAssembler("JMP", String.format("ETIQ%s", id)) + "\n";
    }
    
    private String generarCodigoBLE(Terceto terceto) {
        String id = terceto.getIzq().replace("[", "").replace("]", "");
        return formatAssembler("JNA", String.format("ETIQ%s", id)) + "\n";
    }

    private String generarCodigoCMP(Terceto terceto) {
        String izq = terceto.getIzq();
        String der = terceto.getDer();

        String asmFields = formatAssembler("FLD", izq) + formatAssembler("FLD", der);
        String asmComp = formatAssembler("FCOM") +
                formatAssembler("FSTSW AX") +
                formatAssembler("SAHF");

        return asmFields + asmComp + "\n";
    }

    private String generarCodigoEtiq(Terceto terceto) {
        return "ETIQ" + (terceto.getIzq()) + ":" + "\n";
    }

    private String generarCodigoWrite(Terceto terceto) {
        String izq = terceto.getIzq();
        String asmFields;
        if (izq.matches("_cte_s.*")) asmFields = formatAssembler("displayString", izq);
        else asmFields = formatAssembler("DisplayFloat", izq + ",1");

        String asmNewLine = "\tnewline 1\n";

        return asmFields + asmNewLine + "\n";
    }

    private String generarCodigoRead(Terceto terceto) {
        String izq = terceto.getIzq();

        String asmFields = formatAssembler("GetFloat", izq);

        return asmFields + "\n";
    }

    private String generarCodigoAsignacion(Terceto terceto) {
        String izq = terceto.getIzq();
        String der = terceto.getDer();

        String asmFields = formatAssembler("FLD", der);
        String asmStore = formatAssembler("FSTP", izq);

        return asmFields + asmStore + "\n";
    }

    private String gestorSuma(Terceto terceto){
    	String izq = terceto.getIzq();
    	String der = terceto.getDer();
        
      
        String asmFields = formatAssembler("FLD", izq)+formatAssembler("FLD", der);
        String asmCommands = formatAssembler("FADD");
        String asmStore = formatAssembler("FSTP", izq);
        String asmFree = formatAssembler("FFREE");

        return asmFields + asmCommands + asmStore + asmFree + "\n"; 
    }
    
    private String generarCodigoIncremento(Terceto terceto) {
    	String izq = terceto.getIzq();
    

        String asmFields = formatAssembler("FLD", izq) + formatAssembler("FLD", "1");
        String asmCommands = formatAssembler("FADD");
        String asmStore = formatAssembler("FSTP", izq);
        String asmFree = formatAssembler("FFREE");

        return asmFields + asmCommands + asmStore + asmFree + "\n";
    }

    private String formatAssembler(String command, String value) {
        String _value = (value.matches("[0-9]+") ? "_" : "") + value;
        return String.format("\t%s %s\n", command, _value);
    }

    private String formatAssembler(String command) {
        return String.format("\t%s\n", command);
    }
}
