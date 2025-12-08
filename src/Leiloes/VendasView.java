package Leiloes;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class VendasView extends JFrame {

    private JTable tabela;
    private DefaultTableModel model;
    private ItemDAO dao = new ItemDAO();

    public VendasView() {
        setTitle("Itens Vendidos");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        model = new DefaultTableModel(new Object[]{"ID", "Nome", "Descrição", "Preço"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabela = new JTable(model);
        JScrollPane sp = new JScrollPane(tabela);
        add(sp, BorderLayout.CENTER);

        carregarTabela();
    }

    private void carregarTabela() {
        List<Item> vendidos = dao.listarItensVendidos();

        model.setRowCount(0);
        for (Item it : vendidos) {
            model.addRow(new Object[]{
                    it.getId(),
                    it.getNome(),
                    it.getDescricao(),
                    it.getPreco()
            });
        }
    }
}