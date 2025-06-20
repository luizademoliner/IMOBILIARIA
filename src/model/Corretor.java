package model;

 public class Corretor extends Usuario{

    private int id_corretor;
    private String creci;


    public Corretor(int id_corretor, String nome_corretor, String email_corretor, String tel_corretor, String creci){ //CONTRUTOR COM ID
        super(nome_corretor,email_corretor, tel_corretor);
        this.creci = creci;
        this.id_corretor = id_corretor;  
    }
    
    public Corretor(String nome_corretor, String email_corretor, String tel_corretor, String creci){ //CONSTRUTOR SEM ID  (para novos registros)
        super(nome_corretor,email_corretor, tel_corretor);
        this.creci = creci;
    }


    public String getCreci() {////
        return this.creci;
    }
    public void setCreci(String creci) {
        this.creci = creci;
    }


    public int getIdCorretor() {
        return this.id_corretor;
    }
    public void setIdCorretor(int id_corretor){
        this.id_corretor = id_corretor;
    }


    @Override
    public String toString() {
        return "" + getIdCorretor() + ", " + getNome() + ", " + getCreci() + ", E-mail: " + getEmail() + ", Telefone: " + getTel();
    }

}

