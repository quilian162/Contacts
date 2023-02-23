import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContatoDAO {
    private Connection conexao;

    public ContatoDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public void adicionar(Contato contato) throws SQLException {
        String sql = "INSERT INTO contatos (nome, telefone, email) VALUES (?, ?, ?)";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setString(1, contato.getNome());
        stmt.setString(2, contato.getTelefone());
        stmt.setString(3, contato.getEmail());
        stmt.executeUpdate();
        stmt.close();
    }

    public List<Contato> listar() throws SQLException {
        List<Contato> contatos = new ArrayList<>();
        String sql = "SELECT * FROM contatos";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String nome = rs.getString("nome");
            String telefone = rs.getString("telefone");
            String email = rs.getString("email");
            Contato contato = new Contato(id, nome, telefone, email);
            contatos.add(contato);
        }
        rs.close();
        stmt.close();
        return contatos;
    }

    public void atualizar(Contato contato) throws SQLException {
        String sql = "UPDATE contatos SET nome=?, telefone=?, email=? WHERE id=?";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setString(1, contato.getNome());
        stmt.setString(2, contato.getTelefone());
        stmt.setString(3, contato.getEmail());
        stmt.setInt(4, contato.getId());
        stmt.executeUpdate();
        stmt.close();
    }

    public void excluir (Contato contato) throws SQLException {
        String sql = "DELETE FROM contatos WHERE id=?";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setInt(1, contato.getId());
        stmt.executeUpdate();
        stmt.close();
        }
        }