package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Cliente;
import model.Contrato;
import model.Corretor;
import model.Imovel;

public class ContratoDAO {
    private Connection bd;
    private ClienteDAO clienteDAO;  
    private CorretorDAO corretorDAO;
    private ImovelDAO imovelDAO;

    public ContratoDAO() {
        this.bd = BancoDeDados.getBd();
        this.clienteDAO = new ClienteDAO(); 
        this.corretorDAO = new CorretorDAO();
        this.imovelDAO = new ImovelDAO();
    }

    //CREATE
    public void create(Contrato contrato) throws SQLException {                                          
        String query = "INSERT INTO contrato (id_cliente, id_imovel, id_corretor, data_inicio, data_fim, comissao) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement st = this.bd.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            st.setInt(1, contrato.getCliente().getIdCliente());
            st.setInt(2, contrato.getImovel().getIdImovel());
            st.setInt(3, contrato.getCorretor().getIdCorretor());
            st.setString(4, contrato.getDataInicio());
            st.setString(5, contrato.getDataFim());
            st.setDouble(6, contrato.getComissao());
            st.executeUpdate(); 
            
            try (ResultSet generatedKeys = st.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int idGerado = generatedKeys.getInt(1); // Obtem o ID gerado
                    contrato.setIdContrato(idGerado); 
                } else {
                    throw new SQLException("Falha ao obter o ID gerado.");
                }
            }
        } 
    }

    //UPDATE
    public void update (Contrato contrato) throws SQLException {
        String query = """
        UPDATE contrato
        SET data_inicio = ?, data_fim = ?
        WHERE  id_imovel = ?
        """;
        try (PreparedStatement st = this.bd.prepareStatement(query)) {
            st.setString(1, contrato.getDataInicio());
            st.setString(2, contrato.getDataFim());
            st.setInt(3,contrato.getImovel().getIdImovel());
            st.executeUpdate();
        }
    }

    //CONSULTA
    public Contrato findById(int id_contrato) throws SQLException {
        String query = """
        SELECT * FROM contrato
        WHERE id_contrato = ?
        """;
        try (PreparedStatement st = this.bd.prepareStatement(query)) {
            st.setInt(1, id_contrato);
            try (ResultSet res = st.executeQuery()) {
                if (res.next()) {
                    Cliente cliente = new ClienteDAO().findById(res.getInt("id_cliente")); 
                    Imovel imovel = new ImovelDAO().findById(res.getInt("id_imovel"));
                    Corretor corretor = new CorretorDAO().findById(res.getInt("id_corretor"));
                    return new Contrato(
                    res.getInt("id_contrato"),
                    cliente,
                    imovel,
                    corretor,
                    res.getString("data_inicio"),
                    res.getString("data_fim"),
                    res.getDouble("comissao")
                    );
                } else {
                    throw new SQLException("Contrato com ID " + id_contrato + " não encontrado.");
                }
            }
        }
    }

    ///DELETE
    public void delete(Contrato contrato) throws SQLException {
        String query = """
        DELETE FROM contrato
        WHERE id_contrato = ?
        """;
        try (PreparedStatement st = this.bd.prepareStatement(query)) {
            st.setInt(1, contrato.getIdContrato());
            st.executeUpdate();
        }
    }


    public ArrayList<Contrato> getAll() throws SQLException {
        ArrayList<Contrato> listaContratos = new ArrayList<>();
        String query = "SELECT id_contrato, id_cliente, id_imovel, id_corretor, data_inicio, data_fim, comissao FROM contrato";
        try (PreparedStatement st = this.bd.prepareStatement(query)) {
            try (ResultSet res = st.executeQuery()) {
                while (res.next()) {
                    int id_contrato = res.getInt("id_contrato");
                    int id_cliente = res.getInt("id_cliente");
                    int id_imovel = res.getInt("id_imovel");
                    int id_corretor = res.getInt("id_corretor");
                    String data_inicio = res.getString("data_inicio");
                    String data_fim = res.getString("data_fim");
                    double comissao = res.getDouble("comissao");
                    
                    Cliente cliente = clienteDAO.findById(id_cliente);
                    Corretor corretor = corretorDAO.findById(id_corretor);
                    Imovel imovel = imovelDAO.findById(id_imovel);

                    Contrato contrato = new Contrato(id_contrato, cliente, imovel, corretor, data_inicio, data_fim, comissao); 
                    listaContratos.add(contrato);
                }
                return listaContratos;
            }
        }
    }

}