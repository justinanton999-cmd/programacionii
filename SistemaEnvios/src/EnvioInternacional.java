public class EnvioInternacional extends Envio { // HERENCIA
    // ATRIBUTOS ADICIONALES
    private String pais;
    private static final double CARGO_FIJO = 75.0; // Constante

    // CONSTRUCTOR
    public EnvioInternacional(String codigo, String destinatario, double peso, String pais) {
        super(codigo, destinatario, peso); // Llama al constructor de Envio
        this.pais = pais;
    }

    // GETTERS Y SETTERS
    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    // SOBRESCRITURA del método calcularCostoFinal
    @Override
    public double calcularCostoFinal() {
        double costoBase = calcularCostoBase();
        double recargo = costoBase * 0.12; // 12%
        return costoBase + CARGO_FIJO + recargo;
    }

    // SOBRESCRITURA del método mostrarCargosAdicionales
    @Override
    protected void mostrarCargosAdicionales() {
        double costoBase = calcularCostoBase();
        double recargo = costoBase * 0.12;
        System.out.println("Cargo internacional: Q" + String.format("%.2f", CARGO_FIJO));
        System.out.println("Recargo del 12%: Q" + String.format("%.2f", recargo));
    }
}