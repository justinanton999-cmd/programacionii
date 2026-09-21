package servicio;

import modelo.Vehiculo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Estacionamiento {

    // ArrayList: almacena todos los vehículos
    private final List<Vehiculo> vehiculos = new ArrayList<>();

    // HashSet: controla placas duplicadas
    private final Set<String> placasRegistradas = new HashSet<>();

    // HashMap: totales por tipo de vehículo
    private final Map<String, Double> totalesPorTipo = new HashMap<>();

    /** Registra un vehículo aplicando validaciones. Devuelve true si se agregó. */
    public boolean registrar(Vehiculo v) {
        if (v == null) throw new IllegalArgumentException("Vehículo nulo.");
        if (v.getPlaca().isEmpty())
            throw new IllegalArgumentException("La placa no puede estar vacía.");
        if (v.getPropietario().isEmpty())
            throw new IllegalArgumentException("El propietario no puede estar vacío.");
        if (v.getHorasUtilizadas() <= 0)
            throw new IllegalArgumentException("Las horas deben ser mayores que cero.");

        // Validación de duplicado con HashSet
        if (!placasRegistradas.add(v.getPlaca())) {
            System.out.println("⚠️ La placa " + v.getPlaca() + " ya está registrada. No se agregó.");
            return false;
        }

        vehiculos.add(v);

        // Acumulamos totales por tipo (HashMap)
        totalesPorTipo.merge(v.getTipo(), v.calcularCosto(), Double::sum);
        return true;
    }

    public void mostrarTodos() {
        if (vehiculos.isEmpty()) {
            System.out.println("📭 No hay vehículos registrados.");
            return;
        }
        System.out.println("\n--- Vehículos registrados ---");
        System.out.printf("%-10s | %-20s | %-12s | %-6s | %-9s%n",
                "Placa", "Propietario", "Tipo", "Horas", "Costo");
        System.out.println("-".repeat(70));
        for (Vehiculo v : vehiculos) {
            System.out.println(v.mostrarInformacion());
        }
    }

    public Vehiculo buscarPorPlaca(String placa) {
        for (Vehiculo v : vehiculos) {
            if (v.getPlaca().equalsIgnoreCase(placa.trim())) return v;
        }
        return null;
    }

    public Vehiculo vehiculoMayorCosto() {
        Vehiculo mayor = null;
        for (Vehiculo v : vehiculos) {
            if (mayor == null || v.calcularCosto() > mayor.calcularCosto()) {
                mayor = v;
            }
        }
        return mayor;
    }

    public double totalGeneral() {
        double total = 0;
        for (Vehiculo v : vehiculos) total += v.calcularCosto();
        return total;
    }

    public Map<String, Double> getTotalesPorTipo() {
        return new HashMap<>(totalesPorTipo);
    }
}