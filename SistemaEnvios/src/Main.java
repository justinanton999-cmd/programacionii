import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;

        do {
            mostrarMenu();
            opcion = leerOpcionValida();

            switch (opcion) {
                case 1:
                    registrarEnvioNacional();
                    break;
                case 2:
                    registrarEnvioInternacional();
                    break;
                case 3:
                    System.out.println("¡Gracias por usar el Sistema de Envíos!");
                    break;
            }

            if (opcion != 3) {
                System.out.print("\n¿Desea registrar otro envío? (s/n): ");
                String continuar = scanner.next().toLowerCase();
                scanner.nextLine(); // Limpiar buffer
                if (!continuar.equals("s")) {
                    System.out.println("¡Gracias por usar el Sistema de Envíos!");
                    break;
                }
            }

        } while (opcion != 3);

        scanner.close();
    }

    // MÉTODO PARA MOSTRAR EL MENÚ
    private static void mostrarMenu() {
        System.out.println("\n=== SISTEMA DE ENVÍOS ===");
        System.out.println("1. Registrar envío nacional");
        System.out.println("2. Registrar envío internacional");
        System.out.println("3. Salir");
        System.out.print("Seleccione una opción: ");
    }

    // MÉTODO PARA LEER OPCIÓN VÁLIDA (Validación)
    private static int leerOpcionValida() {
        while (true) {
            try {
                int opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar buffer
                if (opcion >= 1 && opcion <= 3) {
                    return opcion;
                }
                System.out.print("Opción inválida. Ingrese 1, 2 o 3: ");
            } catch (Exception e) {
                System.out.print("Entrada inválida. Ingrese un número: ");
                scanner.nextLine(); // Limpiar buffer
            }
        }
    }

    // MÉTODO PARA LEER TEXTO NO VACÍO (Validación)
    private static String leerTextoNoVacio(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String texto = scanner.nextLine().trim();
            if (!texto.isEmpty()) {
                return texto;
            }
            System.out.println("Este campo no puede estar vacío. Intente nuevamente.");
        }
    }

    // MÉTODO PARA LEER NÚMERO POSITIVO (Validación)
    private static double leerDoublePositivo(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                double valor = scanner.nextDouble();
                scanner.nextLine(); // Limpiar buffer
                if (valor > 0) {
                    return valor;
                }
                System.out.println("El valor debe ser mayor que cero.");
            } catch (Exception e) {
                System.out.println("Entrada inválida. Ingrese un número.");
                scanner.nextLine(); // Limpiar buffer
            }
        }
    }

    // REGISTRAR ENVÍO NACIONAL
    private static void registrarEnvioNacional() {
        System.out.println("\n--- REGISTRO DE ENVÍO NACIONAL ---");

        String codigo = leerTextoNoVacio("Código del envío: ");
        String destinatario = leerTextoNoVacio("Nombre del destinatario: ");
        double peso = leerDoublePositivo("Peso del paquete (kg): ");
        String departamento = leerTextoNoVacio("Departamento de destino: ");
        double distancia = leerDoublePositivo("Distancia del envío (km): ");

        // POLIMORFISMO: variable de tipo Envio apunta a objeto EnvioNacional
        Envio envio = new EnvioNacional(codigo, destinatario, peso, departamento, distancia);

        // Mostrar resumen con desglose completo (SOBRECARGA)
        envio.mostrarResumen(true);
    }

    // REGISTRAR ENVÍO INTERNACIONAL
    private static void registrarEnvioInternacional() {
        System.out.println("\n--- REGISTRO DE ENVÍO INTERNACIONAL ---");

        String codigo = leerTextoNoVacio("Código del envío: ");
        String destinatario = leerTextoNoVacio("Nombre del destinatario: ");
        double peso = leerDoublePositivo("Peso del paquete (kg): ");
        String pais = leerTextoNoVacio("País de destino: ");

        // POLIMORFISMO: variable de tipo Envio apunta a objeto EnvioInternacional
        Envio envio = new EnvioInternacional(codigo, destinatario, peso, pais);

        // Mostrar resumen con desglose completo (SOBRECARGA)
        envio.mostrarResumen(true);
    }
}
