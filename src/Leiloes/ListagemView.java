package Leiloes;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ListagemView extends JFrame {

    private JTable tabela;
    private DefaultTableModel model;
    private JButton btnVender;
    private JButton btnAtualizar;
    private JButton btnConsultarVendas;
    private ItemDAO dao = new ItemDAO();

    public ListagemView() {
        initUI();
        carregarTabela();
    }

    private void initUI() {
        setTitle("Listagem de Itens - Casa de Leilões");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700, 420);
        setLocationRelativeTo(null);

        model = new DefaultTableModel(new Object[]{"ID", "Nome", "Descrição", "Preço", "Status"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabela = new JTable(model);
        JScrollPane sp = new JScrollPane(tabela);

        btnAtualizar = new JButton("Atualizar");
        btnVender = new JButton("Vender");
        btnConsultarVendas = new JButton("Consultar Vendas");

        btnAtualizar.addActionListener(e -> carregarTabela());

        btnVender.addActionListener(e -> {
            int linha = tabela.getSelectedRow();
            if (linha >= 0) {
                int id = (int) tabela.getValueAt(linha, 0);
                dao.venderProduto(id);
                carregarTabela();
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um item para vender");
            }
        });

        btnConsultarVendas.addActionListener(e -> new VendasView().setVisible(true));

        JPanel topo = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topo.add(btnAtualizar);
        topo.add(btnVender);
        topo.add(btnConsultarVendas);

        add(topo, BorderLayout.NORTH);
        add(sp, BorderLayout.CENTER);
    }

    private void carregarTabela() {
        List<Item> lista = dao.listarTodosItens();

        model.setRowCount(0);
        for (Item it : lista) {
            model.addRow(new Object[]{
                    it.getId(),
                    it.getNome(),
                    it.getDescricao(),
                    it.getPreco(),
                    it.getStatus()
            });
        }
    }
}