package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Corretor;

public class CorretorDAO{
    private Connection bd;

    public CorretorDAO() {
        this.bd = BancoDeDados.getBd();
    }

    //CREATE
    public void create (Corretor corretor) throws SQLException {
        String query = "INSERT INTO corretor (nome_corretor, email, tel, creci) VALUES(?, ?, ?, ?)";
        try (PreparedStatement st = this.bd.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            st.setString(1, corretor.getNome());
            st.setString(2, corretor.getEmail());
            st.setString(3, corretor.getTel());
            st.setString(4, corretor.getCreci());
            st.executeUpdate();
            
            try (ResultSet generatedKeys = st.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                int idGerado = generatedKeys.getInt(1); // Obtém o ID gerado
                corretor.setIdCorretor(idGerado); //
                } else {
                throw new SQLException("Falha ao obter o ID gerado.");
                }
            }
        }
    }

    //UPDATE
    public void update (Corretor corretor) throws SQLException {
        String query = """
        UPDATE corretor
        SET nome_corretor = ?, email = ?, tel = ?
        WHERE creci = ?
        """;

        try (PreparedStatement st = this.bd.prepareStatement(query)) {
            st.setString(1, corretor.getNome());
            st.setString(2, corretor.getEmail());
            st.setString(3, corretor.getTel());
            st.setString(4, corretor.getCreci());
            st.executeUpdate();
        }
    }

    
    //READ
    public Corretor findByCreci(String creci) throws SQLException {
        String query = """
        SELECT * FROM corretor
        WHERE creci LIKE ?
        """;
        try (PreparedStatement st = this.bd.prepareStatement(query)) {
            st.setString(1, creci);
            try (ResultSet res = st.executeQuery()) {
                if (res.next()) {
                    return new Corretor (
                    res.getInt("id_corretor"),
                    res.getString("nome_corretor"),
                    res.getString("email"),
                    res.getString("tel"),
                    res.getString("creci")
                   );
                } else {
                    throw new SQLException("Corretor com creci " + creci + " não encontrado.");
                }
            }
        }
    }
    
    public Corretor findById(int id_corretor) throws SQLException {
        String query = """
        SELECT * FROM corretor
        WHERE id_corretor = ?
        """;
        
        try (PreparedStatement st = this.bd.prepareStatement(query)) {
            st.setInt(1, id_corretor);
            try (ResultSet res = st.executeQuery()) {
                if (res.next()) {
                    return new Corretor(
                        res.getInt("id_corretor"),
                        res.getString("nome_corretor"),
                        res.getString("creci"),
                        res.getString("tel"),
                        res.getString("email")
                        );
                } else {
                    throw new SQLException("Corretor com ID " + id_corretor + " não encontrado.");
                }
            }
        }
    }


    //DELETE
    public void delete(Corretor corretor) throws SQLException {
        String query  = """
        DELETE FROM corretor
        WHERE  creci = ?
        """;
        try (PreparedStatement st = this.bd.prepareStatement(query)) {
            st.setString(1, corretor.getCreci());
            st.executeUpdate();
        }
    }
    

    public ArrayList<Corretor> getAll() throws SQLException {
        ArrayList<Corretor> lista_geral = new ArrayList<>();
        String query = "SELECT id_corretor, nome_corretor, email, tel, creci FROM corretor";
        try (PreparedStatement st = this.bd.prepareStatement(query)) {
            try (ResultSet res = st.executeQuery()) {
                while (res.next()) {
                    int id_corretor = res.getInt("id_corretor");
                    String nome_corretor = res.getString("nome_corretor");
                    String email_corretor = res.getString("email");
                    String tel_corretor = res.getString("tel");
                    String creci = res.getString("creci");
                    Corretor corretor = new Corretor(id_corretor, nome_corretor, email_corretor, tel_corretor, creci);
                    lista_geral.add(corretor);
                }
                return lista_geral;
            }
        }
    }

}


