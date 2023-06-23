package CaixaEletronico;



public class Conta {
    private int numeroConta=1;
    private Cliente cliente;
    private double saldo;
    private String tipoConta;

    public Conta(int numeroConta,String tipoConta ,double saldo) {
        this.cliente = cliente;
        this.tipoConta = tipoConta;
        this.saldo = saldo;

    }


    public int getNumeroConta() {
        return numeroConta;
    }


    public void setNumeroConta(int numeroConta) {
        this.numeroConta = numeroConta;
    }


    public Cliente getCliente() {
        return cliente;
    }


    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }


    public double getSaldo() {
        return saldo;
    }


    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }


    public String getTipoConta() {
        return tipoConta;
    }


    public void setTipoConta(String tipoConta) {
        this.tipoConta = tipoConta;
    }





}

