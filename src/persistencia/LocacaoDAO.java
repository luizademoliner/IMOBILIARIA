package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Cliente;
import model.Imovel;
import model.Locacao;

public class LocacaoDAO {
    private Connection bd;
    private ClienteDAO clienteDAO;  
    private ImovelDAO imovelDAO;

    public LocacaoDAO() {
        this.bd = BancoDeDados.getBd();
        this.clienteDAO = new ClienteDAO(); 
        this.imovelDAO = new ImovelDAO();            
    }

    //CREATE
    public void create(Locacao locacao) throws SQLException {
        String query = "INSERT INTO locacao (id_cliente, id_imovel, data_inicio, data_fim) VALUES (?, ?, ?, ?)";
        try (PreparedStatement st = this.bd.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            st.setInt(1, locacao.getCliente().getIdCliente());
            st.setInt(2, locacao.getImovel().getIdImovel());
            st.setString(3, locacao.getDataInicio());
            st.setString(4, locacao.getDataFim());
            st.executeUpdate();
            
            try (ResultSet generatedKeys = st.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int idGerado = generatedKeys.getInt(1); // Obtém o ID gerado
                    locacao.setIdLocacao(idGerado);
                } else {
                    throw new SQLException("Falha ao obter o ID gerado.");
                }
            }
        }
    }

    // DELETE
    public void delete(Locacao locacao) throws SQLException {
        String query  = """
        DELETE FROM locacao
        WHERE  id_locacao = ?
        """;
        try (PreparedStatement st = bd.prepareStatement(query)) {
            st.setInt(1, locacao.getIdLocacao());
            st.executeUpdate();
        }
    }

    // LISTA GERAL
    public ArrayList<Locacao> getAll() throws SQLException {
        ArrayList<Locacao> listaContratos = new ArrayList<>();
        String query = "SELECT id_cliente, id_imovel, data_inicio, data_fim, FROM locacao";
        try (PreparedStatement st = bd.prepareStatement(query)) {
            try (ResultSet res = st.executeQuery()) {
                while (res.next()) {
                    int id_locacao = res.getInt("id_locacao");
                    int id_cliente = res.getInt("id_cliente");
                    int id_imovel = res.getInt("id_imovel");
                    String data_inicio = res.getString("data_inicio");
                    String data_fim = res.getString("data_fim");

                    Cliente cliente = clienteDAO.findById(id_cliente);
                    Imovel imovel = imovelDAO.findById(id_imovel);

                    Locacao locacao = new Locacao(id_locacao, cliente, imovel, data_inicio, data_fim);
                    listaContratos.add(locacao);
                }
                return listaContratos;
            }
        }
    }

}

