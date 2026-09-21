import modelo.Automovil;
import modelo.Motocicleta;
import modelo.Vehiculo;
import servicio.Estacionamiento;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static final Scanner sc = new Scanner(System.in);
    private static final Estacionamiento est = new Estacionamiento();

    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opción: ");
            switch (opcion) {
                case 1 -> registrarVehiculo();
                case 2 -> est.mostrarTodos();
                case 3 -> buscarVehiculo();
                case 4 -> mostrarMayorCosto();
                case 5 -> System.out.printf("💰 Total general recaudado: Q%.2f%n",
                        est.totalGeneral());
                case 6 -> mostrarTotalesPorTipo();
                case 7 -> System.out.println("👋 Saliendo del sistema...");
                default -> System.out.println("⚠️ Opción inválida. Intente de nuevo.");
            }
        } while (opcion != 7);
    }

    private static void mostrarMenu() {
        System.out.println("\n========= 🅿️ ESTACIONAMIENTO =========");
        System.out.println("1. Registrar vehículo");
        System.out.println("2. Mostrar todos los vehículos");
        System.out.println("3. Buscar vehículo por placa");
        System.out.println("4. Vehículo con mayor costo");
        System.out.println("5. Total general recaudado");
        System.out.println("6. Total recaudado por tipo");
        System.out.println("7. Salir");
        System.out.println("=====================================");
    }

    private static void registrarVehiculo() {
        System.out.println("\nTipo: 1) Automóvil  2) Motocicleta");
        int tipo = leerEntero("Tipo: ");
        if (tipo != 1 && tipo != 2) {
            System.out.println("⚠️ Tipo de vehículo inválido.");
            return;
        }

        System.out.print("Placa: ");
        String placa = sc.nextLine().trim();
        System.out.print("Propietario: ");
        String propietario = sc.nextLine().trim();
        int horaIngreso = leerEntero("Hora de ingreso (0-23): ");

        // ⬇️ try / catch / finally FUNCIONAL ⬇️
        boolean exito = false;
        try {
            double horas = leerDouble("Horas utilizadas: ");

            Vehiculo v = (tipo == 1)
                    ? new Automovil(placa, propietario, horaIngreso, horas)
                    : new Motocicleta(placa, propietario, horaIngreso, horas);

            exito = est.registrar(v);
            if (exito) System.out.println("✅ Vehículo registrado correctamente.");

        } catch (IllegalArgumentException e) {
            System.out.println("❌ Error de validación: " + e.getMessage());
        } finally {
            // ⬇️ finally con función OBSERVABLE: deja constancia del intento ⬇️
            System.out.println("📝 Intento de registro finalizado. Resultado: "
                    + (exito ? "EXITOSO" : "NO REGISTRADO"));
        }
    }

    private static void buscarVehiculo() {
        System.out.print("Ingrese la placa a buscar: ");
        String placa = sc.nextLine().trim();
        Vehiculo v = est.buscarPorPlaca(placa);
        if (v == null) System.out.println("🔎 No se encontró ningún vehículo con esa placa.");
        else {
            System.out.println("🔎 Vehículo encontrado:");
            System.out.println(v.mostrarInformacion());
        }
    }

    private static void mostrarMayorCosto() {
        Vehiculo v = est.vehiculoMayorCosto();
        if (v == null) System.out.println("📭 No hay vehículos registrados.");
        else {
            System.out.println("🏆 Vehículo con mayor costo:");
            System.out.println(v.mostrarInformacion());
        }
    }

    private static void mostrarTotalesPorTipo() {
        Map<String, Double> totales = est.getTotalesPorTipo();
        if (totales.isEmpty()) { System.out.println("📭 Sin datos."); return; }
        System.out.println("\n--- Totales por tipo ---");
        totales.forEach((tipo, total) ->
                System.out.printf("%-12s : Q%.2f%n", tipo, total));
    }

    // ---------- Utilidades de entrada con manejo de excepciones ----------

    private static int leerEntero(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                int valor = sc.nextInt();
                sc.nextLine();
                return valor;
            } catch (InputMismatchException e) {
                System.out.println("⚠️ Debe ingresar un número entero.");
                sc.nextLine(); // limpiar buffer
            }
        }
    }

    private static double leerDouble(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                double valor = sc.nextDouble();
                sc.nextLine();
                return valor;
            } catch (InputMismatchException e) {
                System.out.println("⚠️ Debe ingresar un número válido.");
                sc.nextLine();
            }
        }
    }
}