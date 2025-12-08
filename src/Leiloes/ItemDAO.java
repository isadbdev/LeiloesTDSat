package Leiloes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO {

    public boolean salvar(Item item) {
        String sql = "INSERT INTO itens (nome, preco) VALUES (?, ?)";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, item.getNome());
            stmt.setDouble(2, item.getPreco());
            stmt.executeUpdate();
            return true;

        } catch (Exception e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
            return false;
        }
    }

    public List<Item> listar() {
        List<Item> lista = new ArrayList<>();
        String sql = "SELECT * FROM itens";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Item i = new Item();
                i.setId(rs.getInt("id"));
                i.setNome(rs.getString("nome"));
                i.setPreco(rs.getDouble("preco"));
                lista.add(i);
            }

        } catch (Exception e) {
            System.out.println("Erro ao listar: " + e.getMessage());
        }
        return lista;
    }
}