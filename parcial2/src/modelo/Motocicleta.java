package modelo;

public class Motocicleta extends Vehiculo {
    private static final double TARIFA_HORA = 6.00;

    public Motocicleta(String placa, String propietario, int horaIngreso, double horas) {
        super(placa, propietario, horaIngreso, horas);
    }

    @Override
    public double calcularCosto() {
        double costo = getHorasUtilizadas() * TARIFA_HORA;
        return aplicarDescuento(costo);
    }

    @Override
    public String getTipo() { return "Motocicleta"; }
}