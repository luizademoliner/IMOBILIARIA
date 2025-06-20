package model;

public class Usuario {

    private String nome;
    private String email;
    private String tel;

    Usuario(String nome, String email, String tel){
        this.nome = nome;
        this.email = email;
        this.tel = tel;
    }


    public String getNome() {
        return this.nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }


    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) { 
        this.email = email;
    }


    public String getTel() {
        return this.tel;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }                                            
} 
