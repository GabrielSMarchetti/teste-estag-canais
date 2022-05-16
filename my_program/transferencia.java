import java.util.*;


class Transferencia {

    private float valor;
    private String tipo, id;

    Transferencia(String id, float valor, String tipo) {
        this.id = id;
        this.valor = valor;
        this.tipo = tipo;
    }

    //Verifica a validade da transferencia pelo tipo. Retorna false caso encontre algum erro.

    public Boolean transferenciaValidadorTipo() {

        if(this.tipo.equals("PIX") == false && this.tipo.equals("TED") == false && this.tipo.equals("DOC") == false){
            System.out.println("Erro. Preencha corretamente o tipo de transação");
            return false;
        }

        if (this.valor <= 0) {
            System.out.printf("Transferencia invalida : valor menor ou igual a R$ 0,00.\n");
            return false;
        }


        switch (this.tipo) {

            case "PIX":
                if (this.valor > 5000) {
                    System.out.printf("Transferencia invalida : PIX suporta apenas valores até R$ 5000,00.\n");
                    return false;
                }
                break;

            case "TED":
                if (this.valor < 5000 || this.valor > 10000) {
                    System.out.printf("Transferencia invalida : TED suporta apenas valores entre R$ 5000,00 e R$ 10000,00.\n");
                    return false;
                }
                break;

            case "DOC":
                if (this.valor < 10000) {
                    System.out.printf("Transferencia invalida : DOC suporta apenas valores maiores que R$ 10000,00.\n");
                    return false;
                }
                break;

        }

        return true;
    }

    //Verifica a validade da transferencia pela conta. Retorna false caso encontre algum erro

    public Boolean transferenciaValidadorConta(Emissor e, Receptor r) {
        if (e.getConta().equals(r.getConta()) == true) {
            System.out.printf("Transferencia invalida : nao sao permitidas transferencias entre as mesmas contas.\n");
            return false;
        }
        return true;
    }

    //Declaração da função main
  
        public static void main (String[] args){
            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();             //Pula as primeiras linhas que nao contem informacoes
            scanner.nextLine();

            String s = scanner.nextLine(); //Pegando a entrada
            String [] entrada = s.split("[|]+"); //Separando a entrada com o delimitador fornecido ( '|' )

            //Verificando se todos os campos foram preenchidos
          
            try {
                Transferencia transferencia = new Transferencia(entrada[0], Float.parseFloat(entrada[1]), entrada[2]);
                Emissor emissor = new Emissor(0.00f, entrada[3], entrada[4], entrada[5], entrada[6]);
                Receptor receptor = new Receptor(0.00f, entrada[7], entrada[8], entrada[9], entrada[10]);
            } catch(ArrayIndexOutOfBoundsException exc){
                System.out.println("Erro. Preencha todos os campos da transferência");
                System.exit(1);
            }

            Transferencia transferencia = new Transferencia(entrada[0], Float.parseFloat(entrada[1]), entrada[2]);
            Emissor emissor = new Emissor(0.00f, entrada[3], entrada[4], entrada[5], entrada[6]);
            Receptor receptor = new Receptor(0.00f, entrada[7], entrada[8], entrada[9], entrada[10]);

            if(transferencia.transferenciaValidadorConta(emissor, receptor) == false || transferencia.transferenciaValidadorTipo() == false) System.exit(1);

            emissor.setSaldo(emissor.getSaldo() - transferencia.valor);
            receptor.setSaldo(receptor.getSaldo() + transferencia.valor);

            System.out.println("Sua transferência foi realizada com sucesso!");
            System.out.printf("Saldo do emissor: R$ %.2f\n", emissor.getSaldo());
            System.out.printf("Saldo do receptor: R$%.2f", receptor.getSaldo());

            scanner.close();
        }
}
