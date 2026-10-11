import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("======================================");
        System.out.println("Estudiante: Nombre completo");
        System.out.println("Carné: 0000-00-0000");
        System.out.println("======================================");

        int opcion;

        do {

            System.out.println("\n========== MENU PRINCIPAL ==========");
            System.out.println("1. Generar una secuencia");
            System.out.println("2. Realizar un conteo regresivo");
            System.out.println("3. Analizar numeros");
            System.out.println("4. Dibujar una piramide");
            System.out.println("5. Palabra secreta");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opcion: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {

                case 1:

                    System.out.println("\n--- Generar una secuencia ---");

                    System.out.print("Ingrese el numero inicial: ");
                    int inicial = scanner.nextInt();

                    System.out.print("Ingrese el numero final: ");
                    int numeroFinal = scanner.nextInt();

                    System.out.print("Ingrese el incremento: ");
                    int incremento = scanner.nextInt();

                    scanner.nextLine();

                    if (incremento <= 0) {

                        System.out.println(
                                "El incremento debe ser mayor que cero."
                        );

                    } else if (numeroFinal < inicial) {

                        System.out.println(
                                "El numero final debe ser mayor o igual al inicial."
                        );

                    } else {

                        System.out.println("Secuencia:");

                        for (int i = inicial; i <= numeroFinal; i += incremento) {
                            System.out.print(i + " ");
                        }

                        System.out.println();
                    }

                    break;


                case 2:

                    System.out.println("\n--- Conteo regresivo ---");

                    int numero;

                    while (true) {

                        System.out.print(
                                "Ingrese un numero entre 10 y 50: "
                        );

                        numero = scanner.nextInt();
                        scanner.nextLine();

                        if (numero >= 10 && numero <= 50) {
                            break;
                        }

                        System.out.println(
                                "Numero invalido. Intente nuevamente."
                        );
                    }

                    System.out.println("Conteo regresivo:");

                    for (int i = numero; i >= 1; i--) {
                        System.out.print(i + " ");
                    }

                    System.out.println();

                    break;

                case 3:

                    System.out.println("\n--- Analizar numeros ---");

                    int positivos = 0;
                    int negativos = 0;
                    int ignorados = 0;
                    int suma = 0;

                    while (true) {

                        System.out.print(
                                "Ingrese un numero (0 para finalizar): "
                        );

                        int valor = scanner.nextInt();
                        scanner.nextLine();

                        if (valor == 0) {
                            break;
                        }

                        // Ignorar multiplos de 5
                        if (valor % 5 == 0) {

                            ignorados++;

                            System.out.println(
                                    "El numero fue ignorado porque es multiplo de 5."
                            );

                            continue;
                        }

                        if (valor > 0) {

                            positivos++;

                        } else {

                            negativos++;
                        }

                        suma += valor;
                    }

                    System.out.println("\n--- Resultados ---");
                    System.out.println("Numeros positivos: " + positivos);
                    System.out.println("Numeros negativos: " + negativos);
                    System.out.println("Numeros ignorados: " + ignorados);
                    System.out.println("Suma: " + suma);

                    break;

                case 4:

                    System.out.println("\n--- Dibujar una piramide ---");

                    int altura;

                    while (true) {

                        System.out.print(
                                "Ingrese la altura entre 3 y 10: "
                        );

                        altura = scanner.nextInt();
                        scanner.nextLine();

                        if (altura >= 3 && altura <= 10) {
                            break;
                        }

                        System.out.println(
                                "Altura invalida. Intente nuevamente."
                        );
                    }

                    System.out.println("\nPiramide:");

                    // Ciclo externo
                    for (int fila = 1; fila <= altura; fila++) {

                        // Espacios
                        for (int espacio = 1;
                             espacio <= altura - fila;
                             espacio++) {

                            System.out.print(" ");
                        }

                        // Asteriscos
                        for (int asterisco = 1;
                             asterisco <= (2 * fila - 1);
                             asterisco++) {

                            System.out.print("*");
                        }

                        System.out.println();
                    }

                    break;

                case 5:

                    System.out.println("\n--- Palabra secreta ---");

                    String palabra;

                    do {

                        System.out.print(
                                "Ingrese la palabra secreta: "
                        );

                        palabra = scanner.nextLine();

                        // Elimina espacios al inicio y al final
                        palabra = palabra.trim();

                        if (palabra.equalsIgnoreCase("Guatemala")) {

                            System.out.println(
                                    "Palabra correcta."
                            );

                        } else {

                            System.out.println(
                                    "Palabra incorrecta. Intente nuevamente."
                            );
                        }

                    } while (
                            !palabra.equalsIgnoreCase("Guatemala")
                    );

                    break;

                case 6:

                    System.out.println(
                            "\nPrograma finalizado correctamente."
                    );

                    break;

                default:

                    System.out.println(
                            "\nOpcion invalida. Seleccione del 1 al 6."
                    );

                    break;
            }

        } while (opcion != 6);

        scanner.close();
    }
}