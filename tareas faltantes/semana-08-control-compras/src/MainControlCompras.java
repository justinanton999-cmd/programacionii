import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class MainControlCompras {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // ===== COLECCIONES =====
        ArrayList<Producto> productos = new ArrayList<>();       // Todos los productos
        HashSet<String> categorias = new HashSet<>();            // Categorías sin duplicados
        HashMap<String, Double> totalesPorCategoria = new HashMap<>(); // Total por categoría

        int cantidadProductos = 5; // Mínimo de productos

        // ===== REGISTRO DE PRODUCTOS =====
        for (int i = 1; i <= cantidadProductos; i++) {
            System.out.println("\n--- Producto " + i + " ---");
            System.out.print("Nombre del producto: ");
            String nombre = sc.nextLine().trim();

            System.out.print("Categoría: ");
            String categoria = sc.nextLine().trim();

            System.out.print("Precio unitario: ");
            double precio = sc.nextDouble();

            System.out.print("Cantidad: ");
            int cantidad = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            // ===== VALIDACIONES =====
            if (nombre.isEmpty()) {
                System.out.println("Producto no registrado: el nombre no puede estar vacío.");
                continue;
            }
            if (categoria.isEmpty()) {
                System.out.println("Producto no registrado: la categoría no puede estar vacía.");
                continue;
            }
            if (precio <= 0) {
                System.out.println("Producto no registrado: el precio debe ser mayor que cero.");
                continue;
            }
            if (cantidad <= 0) {
                System.out.println("Producto no registrado: la cantidad debe ser mayor que cero.");
                continue;
            }

            // ===== CREAR Y ALMACENAR EL PRODUCTO =====
            Producto p = new Producto(nombre, categoria, precio, cantidad);
            productos.add(p);
            categorias.add(categoria);

            // Acumular total por categoría en el HashMap
            double subtotal = p.calcularSubtotal();
            if (totalesPorCategoria.containsKey(categoria)) {
                totalesPorCategoria.put(categoria, totalesPorCategoria.get(categoria) + subtotal);
            } else {
                totalesPorCategoria.put(categoria, subtotal);
            }

            System.out.println("Producto registrado correctamente.");
        }

        // ===== RESUMEN DE COMPRAS =====
        System.out.println("\n===== RESUMEN DE COMPRAS =====\n");

        double totalGeneral = 0;
        Producto mayor = null;
        Producto menor = null;

        // Recorrer ArrayList
        for (Producto p : productos) {
            double subtotal = p.calcularSubtotal();
            System.out.printf("%s | %s | Q%.2f x %d | Subtotal: Q%.2f%n",
                    p.getNombre(), p.getCategoria(), p.getPrecioUnitario(),
                    p.getCantidad(), subtotal);

            totalGeneral += subtotal;

            if (mayor == null || subtotal > mayor.calcularSubtotal()) {
                mayor = p;
            }
            if (menor == null || subtotal < menor.calcularSubtotal()) {
                menor = p;
            }
        }

        // Categorías sin duplicados
        System.out.println("\nCategorías registradas:");
        System.out.println(categorias);

        // Total por categoría desde HashMap
        System.out.println("\nTotal por categoría:");
        for (String cat : totalesPorCategoria.keySet()) {
            System.out.printf("%s: Q%.2f%n", cat, totalesPorCategoria.get(cat));
        }

        // Cantidad y total general
        System.out.println("\nProductos registrados: " + productos.size());
        System.out.printf("%nTotal general: Q%.2f%n", totalGeneral);

        // Producto con mayor y menor gasto
        if (mayor != null) {
            System.out.printf("%nProducto con mayor gasto:%n%s - Q%.2f%n",
                    mayor.getNombre(), mayor.calcularSubtotal());
        }
        if (menor != null) {
            System.out.printf("%nProducto con menor gasto:%n%s - Q%.2f%n",
                    menor.getNombre(), menor.calcularSubtotal());
        }

        // Categoría con mayor gasto (recorriendo HashMap)
        String categoriaMayor = null;
        double valorMayor = 0;
        for (String cat : totalesPorCategoria.keySet()) {
            if (totalesPorCategoria.get(cat) > valorMayor) {
                valorMayor = totalesPorCategoria.get(cat);
                categoriaMayor = cat;
            }
        }
        if (categoriaMayor != null) {
            System.out.printf("%nCategoría con mayor gasto:%n%s - Q%.2f%n",
                    categoriaMayor, valorMayor);
        }

        // ===== CONSULTA DE CATEGORÍA =====
        System.out.print("\nIngrese una categoría para consultar: ");
        String consulta = sc.nextLine().trim();

        if (totalesPorCategoria.containsKey(consulta)) {
            System.out.printf("Total gastado en %s: Q%.2f%n",
                    consulta, totalesPorCategoria.get(consulta));
        } else {
            System.out.println("La categoría ingresada no se encuentra registrada.");
        }

        sc.close();
    }
}
