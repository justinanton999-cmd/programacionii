package modelo;

public abstract class Vehiculo {
    // Atributos encapsulados
    private String placa;
    private String propietario;
    private int horaIngreso;        // 0-23
    private double horasUtilizadas;

    public Vehiculo(String placa, String propietario, int horaIngreso, double horasUtilizadas) {
        this.placa = placa.toUpperCase().trim();
        this.propietario = propietario.trim();
        this.horaIngreso = horaIngreso;
        this.horasUtilizadas = horasUtilizadas;
    }

    // Getters
    public String getPlaca()           { return placa; }
    public String getPropietario()     { return propietario; }
    public int getHoraIngreso()        { return horaIngreso; }
    public double getHorasUtilizadas() { return horasUtilizadas; }

    // Método abstracto → se sobrescribe en cada subclase (POLIMORFISMO)
    public abstract double calcularCosto();

    // Nombre del tipo (lo sobreescriben las subclases)
    public abstract String getTipo();

    // Aplica el descuento del 10% si supera 5 horas
    protected double aplicarDescuento(double costoBase) {
        if (horasUtilizadas > 5) {
            return costoBase * 0.90;
        }
        return costoBase;
    }

    // Mostrar información general
    public String mostrarInformacion() {
        return String.format(
                "%-10s | %-20s | %-12s | %5.2f h | Q%8.2f",
                placa, propietario, getTipo(), horasUtilizadas, calcularCosto()
        );
    }

    @Override
    public String toString() { return mostrarInformacion(); }
}
