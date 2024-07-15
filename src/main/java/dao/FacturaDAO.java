package dao;

import model.Factura;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FacturaDAO implements DAO<Factura> {

    @Override
    public void crear(Factura factura) {
        String sql = "INSERT INTO factura (idCliente, idProducto, fecha, cantidad, total) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, factura.getIdCliente());
            stmt.setInt(2, factura.getIdProducto());
            stmt.setDate(3, factura.getFecha());
            stmt.setInt(4, factura.getCantidad());
            stmt.setDouble(5, factura.getTotal());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Factura> listar() {
        List<Factura> facturas = new ArrayList<>();
        String sql = "SELECT * FROM factura";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Factura factura = new Factura();
                factura.setNumeroFactura(rs.getInt("numeroFactura"));
                factura.setIdCliente(rs.getInt("idCliente"));
                factura.setIdProducto(rs.getInt("idProducto"));
                factura.setFecha(rs.getDate("fecha"));
                factura.setCantidad(rs.getInt("cantidad"));
                factura.setTotal(rs.getDouble("total"));
                facturas.add(factura);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return facturas;
    }
}
