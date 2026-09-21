package modelo;

public class Automovil extends Vehiculo {
    private static final double TARIFA_HORA = 10.00;

    public Automovil(String placa, String propietario, int horaIngreso, double horas) {
        super(placa, propietario, horaIngreso, horas);
    }

    @Override
    public double calcularCosto() {
        double costo = getHorasUtilizadas() * TARIFA_HORA;
        return aplicarDescuento(costo);
    }

    @Override
    public String getTipo() { return "Automóvil"; }
}
