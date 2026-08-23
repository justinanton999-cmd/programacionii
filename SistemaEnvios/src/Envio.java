public class Envio {
    // ATRIBUTOS (privados para encapsulamiento)
    private String codigo;
    private String destinatario;
    private double peso;

    // CONSTRUCTOR
    public Envio(String codigo, String destinatario, double peso) {
        this.codigo = codigo;
        this.destinatario = destinatario;
        this.peso = peso;
    }

    // GETTERS Y SETTERS (Encapsulamiento)
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    // MÉTODO PARA CALCULAR COSTO BASE
    public double calcularCostoBase() {
        return peso * 10.0; // Q10.00 por kg
    }

    // MÉTODO PARA CALCULAR COSTO FINAL (será sobrescrito)
    public double calcularCostoFinal() {
        return calcularCostoBase();
    }

    // SOBRECARGA 1: Mostrar resumen sin desglose
    public void mostrarResumen() {
        System.out.println("\n=== RESUMEN DEL ENVÍO ===");
        System.out.println("Código: " + codigo);
        System.out.println("Destinatario: " + destinatario);
        System.out.println("Costo final: Q" + String.format("%.2f", calcularCostoFinal()));
    }

    // SOBRECARGA 2: Mostrar resumen con desglose completo
    public void mostrarResumen(boolean desgloseCompleto) {
        if (desgloseCompleto) {
            System.out.println("\n=== DESGLOSE COMPLETO DEL ENVÍO ===");
            System.out.println("Código: " + codigo);
            System.out.println("Destinatario: " + destinatario);
            System.out.println("Peso: " + peso + " kg");
            System.out.println("Costo base: Q" + String.format("%.2f", calcularCostoBase()));
            mostrarCargosAdicionales();
            System.out.println("Costo final: Q" + String.format("%.2f", calcularCostoFinal()));
        } else {
            mostrarResumen();
        }
    }

    // Método auxiliar (será sobrescrito por las subclases)
    protected void mostrarCargosAdicionales() {
        // No hay cargos adicionales en la clase base
    }
}