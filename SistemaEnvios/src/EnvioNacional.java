public class EnvioNacional extends Envio { // HERENCIA
    // ATRIBUTOS ADICIONALES
    private String departamento;
    private double distancia;

    // CONSTRUCTOR
    public EnvioNacional(String codigo, String destinatario, double peso,
                         String departamento, double distancia) {
        super(codigo, destinatario, peso); // Llama al constructor de Envio
        this.departamento = departamento;
        this.distancia = distancia;
    }

    // GETTERS Y SETTERS
    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    // SOBRESCRITURA del método calcularCostoFinal
    @Override
    public double calcularCostoFinal() {
        double costoBase = calcularCostoBase();
        double cargoDistancia = distancia * 0.50;
        return costoBase + cargoDistancia;
    }

    // SOBRESCRITURA del método mostrarCargosAdicionales
    @Override
    protected void mostrarCargosAdicionales() {
        double cargoDistancia = distancia * 0.50;
        System.out.println("Cargo por distancia: Q" + String.format("%.2f", cargoDistancia));
    }
}