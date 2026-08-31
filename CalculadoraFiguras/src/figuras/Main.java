import figuras.Circulo;
import figuras.Figura;
import figuras.Rectangulo;
import figuras.Triangulo;

import java.text.DecimalFormat;

public class Main {
    public static void main(String[] args) {
        // Crear objetos de cada figura
        Circulo circulo = new Circulo("Círculo", 5.0);
        Rectangulo rectangulo = new Rectangulo("Rectángulo", 4.0, 6.0);
        Triangulo triangulo = new Triangulo("Triángulo", 3.0, 4.0);

        // Guardar en un arreglo de tipo Figura[]
        Figura[] figuras = {circulo, rectangulo, triangulo};

        // Formateador para dos decimales
        DecimalFormat df = new DecimalFormat("#.##");

        // Recorrer el arreglo con for-each
        System.out.println("=== CÁLCULO DE ÁREAS ===\n");
        for (Figura figura : figuras) {
            figura.mostrarInformacion();
            double area = figura.calcularArea();
            System.out.println("Área: " + df.format(area) + " unidades²");
            System.out.println("------------------------");
        }
    }
}