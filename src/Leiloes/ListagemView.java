package Leiloes;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ListagemView extends JFrame {

    private JTable tabela;
    private DefaultTableModel model;

    public ListagemView() {
        initUI();
        carregarTabela();
    }

    private void initUI() {
        setTitle("Listagem de Itens - Casa de Leilões");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700, 420);
        setLocationRelativeTo(null);

        model = new DefaultTableModel(new Object[]{"ID", "Nome", "Descrição", "Preço"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tabela = new JTable(model);
        JScrollPane sp = new JScrollPane(tabela);

        JButton btnAtualizar = new JButton("Atualizar");
        btnAtualizar.addActionListener(e -> carregarTabela());

        JPanel topo = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topo.add(btnAtualizar);

        add(topo, BorderLayout.NORTH);
        add(sp, BorderLayout.CENTER);
    }

    private void carregarTabela() {
        ItemDAO dao = new ItemDAO();
        List<Item> lista = dao.listar();

        model.setRowCount(0);
        for (Item it : lista) {
            model.addRow(new Object[]{
                    it.getId(),
                    it.getNome(),
                    it.getDescricao(),
                    it.getPreco()
            });
        }
    }
}