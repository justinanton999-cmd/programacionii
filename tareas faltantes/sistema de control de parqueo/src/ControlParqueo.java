import java.util.Scanner;

public class ControlParqueo {

    // Constantes para las tarifas
    private static final double TARIFA_MOTOCICLETA = 5.00;
    private static final double TARIFA_AUTOMOVIL = 8.00;
    private static final double TARIFA_PICKUP = 12.00;
    private static final double DESCUENTO_PORCENTAJE = 0.15;
    private static final double RECARGO_TICKET_PERDIDO = 50.00;
    private static final int HORAS_MIN_DESCUENTO = 8;

    // Variables globales para el resumen
    private static int contadorMotocicletas = 0;
    private static int contadorAutomoviles = 0;
    private static int contadorPickups = 0;
    private static int contadorTicketsPerdidos = 0;
    private static double totalRecaudado = 0.0;
    private static double pagoMasAlto = 0.0;
    private static String placaPagoMasAlto = "";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. Solicitar cantidad de vehículos
        int cantidadVehiculos = solicitarCantidadVehiculos(scanner);

        // 2. Procesar cada vehículo
        for (int i = 0; i < cantidadVehiculos; i++) {
            System.out.println("\n--- Vehículo #" + (i + 1) + " ---");
            procesarVehiculo(scanner);
        }

        // 5. Mostrar resumen de la jornada
        mostrarResumen();

        scanner.close();
    }

    /**
     * Solicita la cantidad de vehículos validando que sea mayor que cero
     */
    public static int solicitarCantidadVehiculos(Scanner scanner) {
        int cantidad;
        do {
            System.out.print("Ingrese la cantidad de vehículos a registrar: ");
            while (!scanner.hasNextInt()) {
                System.out.print("Error: Ingrese un número válido: ");
                scanner.next();
            }
            cantidad = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            if (cantidad <= 0) {
                System.out.println("Error: La cantidad debe ser mayor que cero.");
            }
        } while (cantidad <= 0);

        return cantidad;
    }

    /**
     * Procesa el registro y cálculo de un vehículo
     */
    public static void procesarVehiculo(Scanner scanner) {
        // Solicitar placa
        System.out.print("Número de placa: ");
        String placa = scanner.nextLine().toUpperCase();

        // Solicitar tipo de vehículo
        int tipoVehiculo = solicitarTipoVehiculo(scanner);

        // Solicitar horas estacionadas
        int horas = solicitarHoras(scanner);

        // Solicitar si perdió el ticket
        boolean ticketPerdido = solicitarTicketPerdido(scanner);

        // Realizar cálculos
        double tarifa = obtenerTarifa(tipoVehiculo);
        String nombreVehiculo = obtenerNombreVehiculo(tipoVehiculo);
        double subtotal = tarifa * horas;
        double descuento = calcularDescuento(subtotal, horas);
        double recargo = ticketPerdido ? RECARGO_TICKET_PERDIDO : 0.0;
        double total = calcularPago(horas, tarifa, recargo);

        // Si no hay recargo, usar el método sin recargo
        if (!ticketPerdido) {
            total = calcularPago(horas, tarifa);
        }

        // Actualizar contadores y acumuladores
        actualizarEstadisticas(tipoVehiculo, ticketPerdido, total, placa);

        // Mostrar comprobante
        mostrarComprobante(placa, nombreVehiculo, horas, tarifa, subtotal, descuento, recargo, total);
    }

    /**
     * Solicita y valida el tipo de vehículo
     */
    public static int solicitarTipoVehiculo(Scanner scanner) {
        int tipo;
        do {
            System.out.print("Tipo de vehículo (1: Motocicleta, 2: Automóvil, 3: Pickup/Camioneta): ");
            while (!scanner.hasNextInt()) {
                System.out.print("Error: Ingrese un número (1, 2 o 3): ");
                scanner.next();
            }
            tipo = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            if (tipo < 1 || tipo > 3) {
                System.out.println("Error: Tipo de vehículo inválido. Debe ser 1, 2 o 3.");
            }
        } while (tipo < 1 || tipo > 3);

        return tipo;
    }

    /**
     * Solicita y valida las horas estacionadas
     */
    public static int solicitarHoras(Scanner scanner) {
        int horas;
        do {
            System.out.print("Cantidad de horas estacionadas: ");
            while (!scanner.hasNextInt()) {
                System.out.print("Error: Ingrese un número válido: ");
                scanner.next();
            }
            horas = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            if (horas <= 0) {
                System.out.println("Error: Las horas deben ser mayores que cero.");
            }
        } while (horas <= 0);

        return horas;
    }

    /**
     * Solicita y valida si el ticket fue perdido
     */
    public static boolean solicitarTicketPerdido(Scanner scanner) {
        String respuesta;
        do {
            System.out.print("¿Perdió el ticket? (S/N): ");
            respuesta = scanner.nextLine().toUpperCase();

            if (!respuesta.equals("S") && !respuesta.equals("N")) {
                System.out.println("Error: Debe ingresar S o N.");
            }
        } while (!respuesta.equals("S") && !respuesta.equals("N"));

        return respuesta.equals("S");
    }

    /**
     * Obtiene la tarifa según el tipo de vehículo
     */
    public static double obtenerTarifa(int tipoVehiculo) {
        switch (tipoVehiculo) {
            case 1:
                return TARIFA_MOTOCICLETA;
            case 2:
                return TARIFA_AUTOMOVIL;
            case 3:
                return TARIFA_PICKUP;
            default:
                return 0.0;
        }
    }

    /**
     * Obtiene el nombre del tipo de vehículo
     */
    public static String obtenerNombreVehiculo(int tipoVehiculo) {
        switch (tipoVehiculo) {
            case 1:
                return "Motocicleta";
            case 2:
                return "Automóvil";
            case 3:
                return "Pickup o Camioneta";
            default:
                return "Desconocido";
        }
    }

    /**
     * Calcula el descuento si aplica
     */
    public static double calcularDescuento(double subtotal, int horas) {
        if (horas > HORAS_MIN_DESCUENTO) {
            return subtotal * DESCUENTO_PORCENTAJE;
        }
        return 0.0;
    }

    /**
     * Calcula el pago sin recargo por ticket perdido
     */
    public static double calcularPago(int horas, double tarifa) {
        double subtotal = tarifa * horas;
        double descuento = calcularDescuento(subtotal, horas);
        return subtotal - descuento;
    }

    /**
     * Calcula el pago con recargo por ticket perdido
     */
    public static double calcularPago(int horas, double tarifa, double recargo) {
        double subtotal = tarifa * horas;
        double descuento = calcularDescuento(subtotal, horas);
        return subtotal - descuento + recargo;
    }

    /**
     * Actualiza las estadísticas de la jornada
     */
    public static void actualizarEstadisticas(int tipoVehiculo, boolean ticketPerdido, double total, String placa) {
        // Contar por tipo
        switch (tipoVehiculo) {
            case 1:
                contadorMotocicletas++;
                break;
            case 2:
                contadorAutomoviles++;
                break;
            case 3:
                contadorPickups++;
                break;
        }

        // Contar tickets perdidos
        if (ticketPerdido) {
            contadorTicketsPerdidos++;
        }

        // Acumular total recaudado
        totalRecaudado += total;

        // Verificar pago más alto
        if (total > pagoMasAlto) {
            pagoMasAlto = total;
            placaPagoMasAlto = placa;
        }
    }

    /**
     * Muestra el comprobante del vehículo
     */
    public static void mostrarComprobante(String placa, String tipoVehiculo, int horas, double tarifa,
                                          double subtotal, double descuento, double recargo, double total) {
        System.out.println("\n========== COMPROBANTE ==========");
        System.out.println("Placa: " + placa);
        System.out.println("Tipo: " + tipoVehiculo);
        System.out.println("Horas estacionado: " + horas);
        System.out.printf("Tarifa por hora: Q%.2f%n", tarifa);
        System.out.printf("Subtotal: Q%.2f%n", subtotal);

        if (descuento > 0) {
            System.out.printf("Descuento: Q%.2f%n", descuento);
        } else {
            System.out.println("Descuento: Q0.00");
        }

        if (recargo > 0) {
            System.out.printf("Recargo por ticket perdido: Q%.2f%n", recargo);
        } else {
            System.out.println("Recargo por ticket perdido: Q0.00");
        }

        System.out.printf("TOTAL: Q%.2f%n", total);
        System.out.println("=================================");
    }

    /**
     * Muestra el resumen de la jornada
     */
    public static void mostrarResumen() {
        System.out.println("\n========== RESUMEN DE LA JORNADA ==========");
        System.out.println("Cantidad de motocicletas: " + contadorMotocicletas);
        System.out.println("Cantidad de automóviles: " + contadorAutomoviles);
        System.out.println("Cantidad de pickups o camionetas: " + contadorPickups);
        System.out.println("Cantidad de tickets perdidos: " + contadorTicketsPerdidos);
        System.out.printf("Total de dinero recaudado: Q%.2f%n", totalRecaudado);
        System.out.println("Vehículo con el pago más alto: " + placaPagoMasAlto +
                " (Q" + String.format("%.2f", pagoMasAlto) + ")");
        System.out.println("============================================");
    }
}
