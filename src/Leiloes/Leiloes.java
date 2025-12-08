package Leiloes;

public class Leiloes {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new CadastroView().setVisible(true);
        });
    }
}