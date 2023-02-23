import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TelaContatos extends JFrame {
private JPanel panelPrincipal;
private JTable tabelaContatos;
private JButton botaoAdicionar;
private JButton botaoEditar;
private JButton botaoExcluir;
private ContatoDAO contatoDAO;

public TelaContatos(Connection conexao) {
    super("Gerenciador de Contatos");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(600, 400);

    panelPrincipal = new JPanel();
    panelPrincipal.setLayout(new BorderLayout());

    tabelaContatos = new JTable();
    panelPrincipal.add(new JScrollPane(tabelaContatos), BorderLayout.CENTER);

    botaoAdicionar = new JButton("Adicionar");
    botaoEditar = new JButton("Editar");
    botaoExcluir = new JButton("Excluir");

    JPanel panelBotoes = new JPanel();
    panelBotoes.setLayout(new FlowLayout());
    panelBotoes.add(botaoAdicionar);
    panelBotoes.add(botaoEditar);
    panelBotoes.add(botaoExcluir);
    panelPrincipal.add(panelBotoes, BorderLayout.SOUTH);

    setContentPane(panelPrincipal);
    setLocationRelativeTo(null);

    contatoDAO = new ContatoDAO(conexao);

    botaoAdicionar.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            TelaAdicionarContato telaAdicionarContato = new TelaAdicionarContato(TelaContatos.this);
            telaAdicionarContato.setVisible(true);
        }
    });

    botaoEditar.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            int linhaSelecionada = tabelaContatos.getSelectedRow();
            if (linhaSelecionada != -1) {
                int id = (int) tabelaContatos.getValueAt(linhaSelecionada, 0);
                Contato contato = buscarContatoPorId(id);
                if (contato != null) {
                    TelaEditarContato telaEditarContato = new TelaEditarContato(TelaContatos.this, contato);
                    telaEditarContato.setVisible(true);
                }
            }
        }
    });

    botaoExcluir.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            int linhaSelecionada = tabelaContatos.getSelectedRow();
            if (linhaSelecionada != -1) {
                int id = (int) tabelaContatos.getValueAt(linhaSelecionada, 0);
                Contato contato = buscarContatoPorId(id);
                if (contato != null) {
                    try {
                        contatoDAO.excluir(contato);
                        atualizarTabelaContatos();
                        JOptionPane.showMessageDialog(TelaContatos.this, "Contato excluído com sucesso.");
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(TelaContatos.this, "Erro ao excluir contato: " + ex.getMessage());
                    }
                }
            }
        }
    });

    atualizarTabelaContatos();
}

private Contato buscarContatoPorId(int id) {
    List<Contato> contatos = contatoDAO.listar();
    for (Contato contato : contatos) {
        if (contato.getId() == id) {
            return contato;
        }
    }
    return null;
}

public void adicionarContato(Contato contato) {
    try {
        contatoDAO.adicionar(contato);
        atualizarTabelaContatos();
        JOptionPane.showMessageDialog(this, "Contato adicionado com sucesso.");
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Erro ao adicionar contato: " + ex.getMessage());
    }
}

public void editarContato(Contato contato) {
    try {
        contatoDAO.atualizar(contato);
        atualizarTabelaContatos();
        JOptionPane.showMessageDialog(this, "Contato atualizado com sucesso.");
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Erro ao atualizar contato: " + ex.getMessage());
    }
}

private void atualizarTabelaContatos() {
    try {
        List<Contato> contatos = contatoDAO.listar();
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Nome");
        modelo.addColumn("Telefone");
        for (Contato contato : contatos) {
            modelo.addRow(new Object[]{contato.getId(), contato.getNome(), contato.getTelefone()});
        }
        tabelaContatos.setModel(modelo);
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Erro ao listar contatos: " + ex.getMessage());
    }
}
}
