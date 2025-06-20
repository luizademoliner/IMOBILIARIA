package model;

public class Locacao {

    private int id_locacao;
    private Cliente cliente;
    private Imovel imovel;
    private String data_inicio;
    private String data_fim;

    public Locacao(int id_locacao, Cliente cliente, Imovel imovel, String data_inicio, String data_fim) {  // COM ID
        this.id_locacao = id_locacao; 
        this.cliente = cliente;
        this.imovel = imovel;
        this.data_inicio = data_inicio;
        this.data_fim = data_fim;
    }

    public Locacao(Cliente cliente, Imovel imovel, String data_inicio, String data_fim) {  // SEM ID
        this.cliente = cliente;
        this.imovel = imovel;
        this.data_inicio = data_inicio;
        this.data_fim = data_fim;
    }

    
    public int getIdLocacao() {
        return this.id_locacao;
    }
    public void setIdLocacao(int id_locacao) {
        this.id_locacao = id_locacao;
    }


    public Cliente getCliente() {
        return this.cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }


    public Imovel getImovel() {
        return this.imovel;
    }
    public void setImovel(Imovel imovel) {
        this.imovel = imovel;
    }


    public String getDataInicio() {
        return this.data_inicio;
    }
    public void setDataInicio(String data_inicio) {
        this.data_inicio = data_inicio;
    }


    public String getDataFim() {
        return this.data_fim;
    }
    public void setDataFim(String data_fim) {
        this.data_fim = data_fim;
    }


    @Override
    public String toString() {
        return "" + "id da locação: " + getIdLocacao() + ", Cliente: " + cliente.getIdCliente() + ", id do imóvel: "//
        + imovel.getIdImovel() + ", data de início da locação: " + getDataInicio() + ", data do fim da locação: "
        + getDataFim();
    }
    
}
