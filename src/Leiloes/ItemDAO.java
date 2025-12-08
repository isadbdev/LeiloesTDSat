package Leiloes;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO {

    public boolean salvar(Item item) {
        String sql = "INSERT INTO itens (nome, descricao, preco, status) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, item.getNome());
            stmt.setString(2, item.getDescricao());
            stmt.setDouble(3, item.getPreco());
            stmt.setString(4, "Disponível");
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Item> listarTodosItens() {
        List<Item> lista = new ArrayList<>();
        String sql = "SELECT * FROM itens";
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Item item = new Item(rs.getString("nome"), rs.getString("descricao"), rs.getDouble("preco"));
                item.setId(rs.getInt("id"));
                item.setStatus(rs.getString("status"));
                lista.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<Item> listarItensVendidos() {
        List<Item> vendidos = new ArrayList<>();
        String sql = "SELECT * FROM itens WHERE status = 'Vendido'";
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Item item = new Item(rs.getString("nome"), rs.getString("descricao"), rs.getDouble("preco"));
                item.setId(rs.getInt("id"));
                item.setStatus(rs.getString("status"));
                vendidos.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vendidos;
    }

    public void venderProduto(int idItem) {
        String sql = "UPDATE itens SET status = 'Vendido' WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idItem);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}