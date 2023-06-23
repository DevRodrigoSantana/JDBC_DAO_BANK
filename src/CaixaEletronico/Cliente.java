package CaixaEletronico;


public class Cliente {

    private int cpf;
    private String nome;
    private String senha;
    private Conta conta;

    public Cliente(int cpf,String nome,String senha) {
        this.cpf = cpf;
        this.nome = nome;
        this.senha = senha;

    }

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }


    public Conta getConta() {
        return conta;
    }


}



