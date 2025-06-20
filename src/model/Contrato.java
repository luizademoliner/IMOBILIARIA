package model;

public class Contrato {

    private int id_contrato;
    private Imovel imovel;
    private Cliente cliente;
    private Corretor corretor;
    private String data_inicio, data_fim;
    private double comissao;

    public Contrato(int id_contrato, Cliente cliente, Imovel imovel, Corretor corretor, String data_inicio, String data_fim, double comissao) { // CONSTRUTOR COM ID
        this.id_contrato = id_contrato;
        this.imovel = imovel;
        this.cliente = cliente;
        this.corretor = corretor;
        this.data_inicio = data_inicio;
        this.data_fim = data_fim;
        this.comissao = comissao;
    }

    public Contrato( Cliente cliente, Imovel imovel, Corretor corretor, String data_inicio, String data_fim, double comissao) {  // CONSTRUTOR SEM ID
        this.imovel = imovel;
        this.cliente = cliente;
        this.corretor = corretor;
        this.data_inicio = data_inicio;
        this.data_fim = data_fim;
        this.comissao = comissao;
    }


    public int getIdContrato() {
        return this.id_contrato;
    }
    public void setIdContrato(int id_contrato) {
        this.id_contrato = id_contrato;
    }


    public Cliente getCliente() {
        return this.cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }


    public Corretor getCorretor() {
        return this.corretor;
    }
    public void setCorretor(Corretor corretor) {
        this.corretor = corretor;
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


    public double getComissao() {
        return this.comissao;
    }
    public void setComissao(double comissao) { 
        this.comissao = comissao;
    }


    public double taxarCompra() {
        return this.comissao*this.imovel.getValorVenda();
    }


    @Override
    public String toString() {
        return "ID do Contrato: " + getIdContrato() + "\n" + "ID do Cliente: " + getCliente().getIdCliente() + "\n" + "ID do Corretor: " + getCorretor().getIdCorretor() + "\n" 
        + "ID do Imóvel: " + getImovel().getIdImovel() + "\n" + "Data de Início do Contrato: " + getDataInicio() + "\n" + "Data de Fim do Contrato: " + getDataFim() + "\n" +
         "Porcentagem a Pagar: R$" + taxarCompra() + "\n----------------------------------------";
    }

}

