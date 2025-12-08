package Leiloes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastroView extends JFrame {

    private JTextField txtNome;
    private JTextField txtPreco;
    private JTextArea txtDescricao;
    private JButton btnSalvar;
    private JButton btnAbrirListagem;

    public CadastroView() {
        initUI();
    }

    private void initUI() {
        setTitle("Cadastro de Itens - Casa de Leilões");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(520, 360);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(8,8,8,8);
        c.fill = GridBagConstraints.HORIZONTAL;
        
        c.gridx = 0; c.gridy = 0; c.weightx = 0;
        panel.add(new JLabel("Nome:"), c);
        txtNome = new JTextField();
        c.gridx = 1; c.gridy = 0; c.weightx = 1;
        panel.add(txtNome, c);

        c.gridx = 0; c.gridy = 1; c.weightx = 0; c.anchor = GridBagConstraints.NORTH;
        panel.add(new JLabel("Descrição:"), c);
        txtDescricao = new JTextArea(5, 30);
        JScrollPane sp = new JScrollPane(txtDescricao);
        c.gridx = 1; c.gridy = 1; c.weightx = 1; c.fill = GridBagConstraints.BOTH;
        panel.add(sp, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0; c.gridy = 2; c.weightx = 0;
        panel.add(new JLabel("Preço:"), c);
        txtPreco = new JTextField();
        c.gridx = 1; c.gridy = 2; c.weightx = 1;
        panel.add(txtPreco, c);

        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        btnSalvar = new JButton("Salvar");
        btnAbrirListagem = new JButton("Abrir Listagem");
        botoes.add(btnSalvar);
        botoes.add(btnAbrirListagem);

        c.gridx = 0; c.gridy = 3; c.gridwidth = 2; c.weightx = 1; c.fill = GridBagConstraints.NONE;
        panel.add(botoes, c);

        add(panel);

        btnSalvar.addActionListener((ActionEvent e) -> {
            salvarItem();
        });

        btnAbrirListagem.addActionListener((ActionEvent e) -> {
            abrirListagem();
        });
    }

    private void salvarItem() {
        String nome = txtNome.getText().trim();
        String descricao = txtDescricao.getText().trim();
        String precoText = txtPreco.getText().trim();

        if (nome.isEmpty() || precoText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha Nome e Preço.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        double preco;
        try {
            preco = Double.parseDouble(precoText.replace(",", "."));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Preço inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Item item = new Item(nome, descricao, preco);
        ItemDAO dao = new ItemDAO();
        boolean sucesso = dao.salvar(item);

        if (sucesso) {
            JOptionPane.showMessageDialog(this, "Cadastro realizado com sucesso!");
            txtNome.setText("");
            txtDescricao.setText("");
            txtPreco.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Falha ao cadastrar item.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void abrirListagem() {
        ListagemView lv = new ListagemView();
        lv.setVisible(true);
    }
}