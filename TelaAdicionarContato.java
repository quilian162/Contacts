import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaAdicionarContato extends JDialog {
private JPanel panelPrincipal;
private JTextField campoNome;
private JTextField campoTelefone;
private JButton botaoAdicionar;
private JButton botaoCancelar;
private TelaContatos telaContatos;

public TelaAdicionarContato(TelaContatos telaContatos) {
    super(telaContatos, "Adicionar Contato", true);
    setSize(300, 200);

    this.telaContatos;

    panelPrincipal = new JPanel(new GridLayout(3, 2));
    panelPrincipal.add(new JLabel("Nome:"));
    campoNome = new JTextField();
    panelPrincipal.add(campoNome);
    panelPrincipal.add(new JLabel("Telefone:"));
    campoTelefone = new JTextField();
    panelPrincipal.add(campoTelefone);

    botaoAdicionar = new JButton("Adicionar");
    botaoAdicionar.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String nome = campoNome.getText().trim();
            String telefone = campoTelefone.getText().trim();
            if (nome.isEmpty() || telefone.isEmpty()) {
                JOptionPane.showMessageDialog(TelaAdicionarContato.this, "Por favor, preencha todos os campos.");
            } else {
                Contato contato = new Contato(nome, telefone);
                telaContatos.adicionarContato(contato);
                dispose();
            }
        }
    });

    botaoCancelar = new JButton("Cancelar");
    botaoCancelar.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
        }
    });

    JPanel panelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    panelBotoes.add(botaoAdicionar);
    panelBotoes.add(botaoCancelar);

    add(panelPrincipal, BorderLayout.CENTER);
    add(panelBotoes, BorderLayout.SOUTH);
}
}