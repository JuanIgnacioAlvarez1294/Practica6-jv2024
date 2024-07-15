package controller;


import dao.ClienteDAO;
import dao.FacturaDAO;
import dao.ProductoDAO;
import model.Cliente;
import model.Factura;
import model.Producto;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class AppPrincipal {
    private static Scanner scanner = new Scanner(System.in);
    private static ProductoDAO productoDAO = new ProductoDAO();
    private static ClienteDAO clienteDAO = new ClienteDAO();
    private static FacturaDAO facturaDAO = new FacturaDAO();

    public static void main(String[] args) {
        while (true) {
            System.out.println("1. Agregar Producto");
            System.out.println("2. Listar Productos");
            System.out.println("3. Agregar Cliente");
            System.out.println("4. Listar Clientes");
            System.out.println("5. Agregar Factura");
            System.out.println("6. Listar Facturas");
            System.out.println("7. Salir");
            System.out.print("Elija una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    agregarProducto();
                    break;
                case 2:
                    listarProductos();
                    break;
                case 3:
                    agregarCliente();
                    break;
                case 4:
                    listarClientes();
                    break;
                case 5:
                    agregarFactura();
                    break;
                case 6:
                    listarFacturas();
                    break;
                case 7:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    private static void agregarProducto() {
        System.out.print("Nombre del Producto: ");
        String nombre = scanner.next();
        System.out.print("Stock: ");
        int stock = scanner.nextInt();
        System.out.print("Precio: ");
        double precio = scanner.nextDouble();
        Producto producto = new Producto();
        producto.setNombreProducto(nombre);
        producto.setStock(stock);
        producto.setPrecio(precio);
        productoDAO.crear(producto);
    }

    private static void listarProductos() {
        List<Producto> productos = productoDAO.listar();
        for (Producto producto : productos) {
            System.out.println(producto);
        }
    }

    private static void agregarCliente() {
        System.out.print("DNI: ");
        String dni = scanner.next();
        System.out.print("Nombre del Cliente: ");
        String nombre = scanner.next();
        System.out.print("Email: ");
        String email = scanner.next();
        System.out.print("Teléfono: ");
        String telefono = scanner.next();
        Cliente cliente = new Cliente();
        cliente.setDni(dni);
        cliente.setNombreCliente(nombre);
        cliente.setEmail(email);
        cliente.setTelefono(telefono);
        clienteDAO.crear(cliente);
    }

    private static void listarClientes() {
        List<Cliente> clientes = clienteDAO.listar();
        for (Cliente cliente : clientes) {
            System.out.println(cliente);
        }
    }

    private static void agregarFactura() {
        System.out.print("ID del Cliente: ");
        int idCliente = scanner.nextInt();
        System.out.print("ID del Producto: ");
        int idProducto = scanner.nextInt();
        System.out.print("Fecha (YYYY-MM-DD): ");
        String fecha = scanner.next();
        System.out.print("Cantidad: ");
        int cantidad = scanner.nextInt();
        System.out.print("Total: ");
        double total = scanner.nextDouble();
        Factura factura = new Factura();
        factura.setIdCliente(idCliente);
        factura.setIdProducto(idProducto);
        factura.setFecha(Date.valueOf(fecha));
        factura.setCantidad(cantidad);
        factura.setTotal(total);
        facturaDAO.crear(factura);
    }

    private static void listarFacturas() {
        List<Factura> facturas = facturaDAO.listar();
        for (Factura factura : facturas) {
            System.out.println(factura);
        }
    }
}
