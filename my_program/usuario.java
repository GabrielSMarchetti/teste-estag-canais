class usuario{

    private float saldo;
    private String nome, agencia, conta, cpf;

    usuario(float saldo, String nome, String agencia, String conta, String cpf){
        this.saldo = 0.00f;
        this.nome = nome;
        this.agencia = agencia;
        this.conta = conta;
        this.cpf = cpf;
    }

    //Montando setters/getters

    public float getSaldo() {return saldo;}
    public String getNome() {return nome;}
    public String getAgencia() {return agencia;}
    public String getConta() {return conta;}
    public String getCpf() {return cpf;}

    public void setSaldo(float saldo) {this.saldo = saldo;}

}
