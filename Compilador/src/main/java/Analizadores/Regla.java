package Analizadores;

public enum Regla {

    REGLA_0_S(0, "S → PROG"), REGLA_1_PROG(1, "PROG → SENT"), REGLA_2_PROG(2, "PROG → PROG SENT"),
    REGLA_3_SENT_R(3, "SENT → READ"), REGLA_3_SENT_W(3, "SENT → WRITE"), REGLA_3_SENT_A(3, "SENT → ASIG"),
    REGLA_4_READ(4, "READ → read id"), REGLA_5_ASIG(5, "ASIG → id asigna COLA"),
    REGLA_6_SI(6, "PROMR → promr para id pyc ca LISTA cc parc"), REGLA_7_SI(6, "PROMR → promr para id pyc ca cc parc"),
    REGLA_8_LISTA(7, "LISTA → cte"), REGLA_9_LISTA(8, "LISTA → LISTA coma cte"),
    REGLA_10_WRITE(9, "WRITE → write cte_s"), REGLA_11_WRITE(10, "WRITE → write id");

    private final Integer numero;
    private final String regla;

    Regla(Integer numero, String regla) {
        this.numero = numero;
        this.regla = regla;
    }

    @Override
    public String toString() {
        return "[STX] REGLA " + this.numero + ". " + this.regla;
    }
}
