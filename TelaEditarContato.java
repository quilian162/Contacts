import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaEditarContato extends JDialog {
private JPanel panelPrincipal;
private JTextField campoNome;
private JTextField campoTelefone;
private JButton botaoEditar;
private JButton botaoCancelar;
private TelaContatos telaContatos;
private Contato contato;

public TelaEditarContato(TelaContatos telaContatos, Contato contato) {
    super(telaContatos, "Editar Contato", true);
    setSize(300, 200);

    this.telaContatos = telaContatos;
    this.contato = contato;

    panelPrincipal = new JPanel(new GridLayout(3, 2));
    panelPrincipal.add(new JLabel("Nome:"));
    campoNome = new JTextField(contato.getNome());
    panelPrincipal.add(campoNome);
    panelPrincipal.add(new JLabel("Telefone:"));
    campoTelefone = new JTextField(contato.getTelefone());
    panelPrincipal.add(campoTelefone);

    botaoEditar = new JButton("Editar");
    botaoEditar.addActionListener(new ActionListener() {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            String nome = campoNome.getText().trim();
            String telefone = campo;
            Telefone.getText().trim();
    if (nome.isEmpty() || telefone.isEmpty()) {
        JOptionPane.showMessageDialog(TelaEditarContato.this, "Por favor, preencha todos os campos.");
    } else {
            contato.setNome(nome);
            contato.setTelefone(telefone);
            telaContatos.editarContato(contato);
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

    JPanel panelBotoes = new JPanel (new FlowLayout(FlowLayout.RIGHT));
    panelBotoes.add(botaoEditar);
    panelBotoes.add(botaoCancelar);

    add(panelPrincipal, BorderLayout.CENTER);
    add(panelBotoes, BorderLayout.SOUTH);
}
}