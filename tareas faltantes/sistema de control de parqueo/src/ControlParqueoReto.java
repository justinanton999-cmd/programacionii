import java.util.Scanner;
public class ControlParqueoReto {

    public static int solicitarHorasConMinutos(Scanner scanner) {
        int horaEntrada, minutoEntrada, horaSalida, minutoSalida;

        // Solicitar hora de entrada
        do {
            System.out.print("Hora de entrada (0-23): ");
            while (!scanner.hasNextInt()) {
                System.out.print("Error: Ingrese un número válido (0-23): ");
                scanner.next();
            }
            horaEntrada = scanner.nextInt();
            if (horaEntrada < 0 || horaEntrada > 23) {
                System.out.println("Error: La hora debe estar entre 0 y 23.");
            }
        } while (horaEntrada < 0 || horaEntrada > 23);

        // Solicitar minuto de entrada
        do {
            System.out.print("Minuto de entrada (0-59): ");
            while (!scanner.hasNextInt()) {
                System.out.print("Error: Ingrese un número válido (0-59): ");
                scanner.next();
            }
            minutoEntrada = scanner.nextInt();
            if (minutoEntrada < 0 || minutoEntrada > 59) {
                System.out.println("Error: El minuto debe estar entre 0 y 59.");
            }
        } while (minutoEntrada < 0 || minutoEntrada > 59);

        // Solicitar hora de salida
        do {
            System.out.print("Hora de salida (0-23): ");
            while (!scanner.hasNextInt()) {
                System.out.print("Error: Ingrese un número válido (0-23): ");
                scanner.next();
            }
            horaSalida = scanner.nextInt();
            if (horaSalida < 0 || horaSalida > 23) {
                System.out.println("Error: La hora debe estar entre 0 y 23.");
            }
        } while (horaSalida < 0 || horaSalida > 23);

        // Solicitar minuto de salida
        do {
            System.out.print("Minuto de salida (0-59): ");
            while (!scanner.hasNextInt()) {
                System.out.print("Error: Ingrese un número válido (0-59): ");
                scanner.next();
            }
            minutoSalida = scanner.nextInt();
            if (minutoSalida < 0 || minutoSalida > 59) {
                System.out.println("Error: El minuto debe estar entre 0 y 59.");
            }
        } while (minutoSalida < 0 || minutoSalida > 59);

        // Calcular tiempo en minutos
        int tiempoEntrada = horaEntrada * 60 + minutoEntrada;
        int tiempoSalida = horaSalida * 60 + minutoSalida;

        // Si el vehículo salió después de medianoche
        if (tiempoSalida <= tiempoEntrada) {
            tiempoSalida += 24 * 60; // Agregar un día
        }

        int minutosTotales = tiempoSalida - tiempoEntrada;
        int horasCobradas = (int) Math.ceil(minutosTotales / 60.0); // Redondear hacia arriba

        System.out.printf("Tiempo estacionado: %d horas y %d minutos%n",
                minutosTotales / 60, minutosTotales % 60);
        System.out.println("Horas cobradas: " + horasCobradas);

        return horasCobradas;
    }

    /**
     * Modifica procesarVehiculo para usar la nueva función
     */
    public static void procesarVehiculo(Scanner scanner) {
        // ... resto del código similar, pero usando solicitarHorasConMinutos
        int horas = solicitarHorasConMinutos(scanner);
        // ... resto del procesamiento
    }
}
