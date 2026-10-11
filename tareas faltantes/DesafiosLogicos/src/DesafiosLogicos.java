import java.util.Scanner;

public class DesafiosLogicos {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;

        // ===== MENÚ PRINCIPAL (do while + switch) =====
        do {
            System.out.println("\n========= DESAFÍOS LÓGICOS =========");
            System.out.println("1. Generar una secuencia");
            System.out.println("2. Realizar un conteo regresivo");
            System.out.println("3. Analizar números");
            System.out.println("4. Dibujar una pirámide");
            System.out.println("5. Validar palabra secreta");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // Limpiar el buffer después de nextInt()

            switch (opcion) {

                // ===== OPCIÓN 1: GENERAR UNA SECUENCIA =====
                case 1: {
                    System.out.print("Número inicial: ");
                    int inicio = sc.nextInt();
                    System.out.print("Número final: ");
                    int fin = sc.nextInt();
                    System.out.print("Incremento: ");
                    int inc = sc.nextInt();

                    // Validaciones
                    if (inc <= 0) {
                        System.out.println("Error: el incremento debe ser mayor que cero.");
                    } else if (fin <= inicio) {
                        System.out.println("Error: el número final debe ser mayor que el inicial.");
                    } else {
                        System.out.print("Resultado: ");
                        for (int i = inicio; i <= fin; i += inc) {
                            System.out.print(i + " ");
                        }
                        System.out.println();
                    }
                    break;
                }

                // ===== OPCIÓN 2: CONTEO REGRESIVO =====
                case 2: {
                    int num;
                    System.out.print("Ingrese el número inicial (10-50): ");
                    num = sc.nextInt();

                    // Validación con while: repetir hasta que esté en rango
                    while (num < 10 || num > 50) {
                        System.out.println("Error: el número debe estar entre 10 y 50.");
                        System.out.print("Ingrese el número inicial (10-50): ");
                        num = sc.nextInt();
                    }

                    System.out.println("Resultado:");
                    while (num >= 0) {
                        System.out.print(num + " ");
                        num--;
                    }
                    System.out.println("\n¡Despegue!");
                    break;
                }

                // ===== OPCIÓN 3: ANALIZAR NÚMEROS =====
                case 3: {
                    int positivos = 0, negativos = 0, ignorados = 0, suma = 0;
                    int numero;

                    while (true) {
                        System.out.print("Ingrese un número (0 para terminar): ");
                        numero = sc.nextInt();

                        if (numero == 0) {
                            break; // Finaliza el ingreso, el 0 no se contabiliza
                        }

                        // Ignorar múltiplos de 5
                        if (numero % 5 == 0) {
                            System.out.println("El número " + numero + " fue ignorado.");
                            ignorados++;
                            continue;
                        }

                        // Contadores de positivos y negativos
                        if (numero > 0) {
                            positivos++;
                        } else if (numero < 0) {
                            negativos++;
                        }

                        suma += numero; // Acumulador
                    }

                    System.out.println("Resultado:");
                    System.out.println("Positivos: " + positivos);
                    System.out.println("Negativos: " + negativos);
                    System.out.println("Suma válida: " + suma);
                    System.out.println("Números ignorados: " + ignorados);
                    break;
                }

                // ===== OPCIÓN 4: DIBUJAR UNA PIRÁMIDE =====
                case 4: {
                    System.out.print("Ingrese la altura (3-10): ");
                    int altura = sc.nextInt();

                    if (altura < 3 || altura > 10) {
                        System.out.println("Error: la altura debe estar entre 3 y 10.");
                    } else {
                        System.out.println("Resultado:");
                        // Ciclos for anidados
                        for (int i = 1; i <= altura; i++) {
                            // Imprimir espacios
                            for (int j = 1; j <= altura - i; j++) {
                                System.out.print(" ");
                            }
                            // Imprimir asteriscos
                            for (int k = 1; k <= (2 * i - 1); k++) {
                                System.out.print("*");
                            }
                            System.out.println();
                        }
                    }
                    break;
                }

                // ===== OPCIÓN 5: VALIDAR PALABRA SECRETA =====
                case 5: {
                    String palabra;
                    do {
                        System.out.print("Ingrese la palabra secreta: ");
                        palabra = sc.nextLine().trim(); // trim elimina espacios inicio/fin

                        if (!palabra.equalsIgnoreCase("Guatemala")) {
                            System.out.println("Palabra incorrecta. Intente nuevamente.\n");
                        }
                    } while (!palabra.equalsIgnoreCase("Guatemala"));

                    System.out.println("Palabra correcta.");
                    break;
                }

                // ===== OPCIÓN 6: SALIR =====
                case 6: {
                    System.out.println("Programa finalizado correctamente.");
                    break;
                }

                // ===== OPCIÓN INVÁLIDA =====
                default: {
                    System.out.println("Opción inválida. Intente nuevamente.");
                    break;
                }
            }

        } while (opcion != 6); // Repetir menú hasta seleccionar Salir

        sc.close();
    }
}