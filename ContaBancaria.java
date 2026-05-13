public class ContaBancaria {
    // Atributos encapsulados (private)
    private String titular;
    private double saldo;

    // Construtor
    public ContaBancaria(String titular, double saldoInicial) {
        this.titular = titular;
        this.saldo = saldoInicial;
    }

    // Getter para saldo (consulta)
    public double getSaldo() {
        return saldo;
    }

    // Método para depositar (com validação)
    public void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
            System.out.println("Depósito de R$ " + valor + " realizado.");
        } else {
            System.out.println("Valor inválido para depósito.");
        }
    }

    // Método para sacar (com validação)
    public void sacar(double valor) {
        if (valor > 0 && valor <= saldo) {
            saldo -= valor;
            System.out.println("Saque de R$ " + valor + " realizado.");
        } else {
            System.out.println("Saldo insuficiente ou valor inválido.");
        }
    }

    // Método main para testar
    public static void main(String[] args) {
        ContaBancaria conta = new ContaBancaria("João", 1000.0);
        
        System.out.println("Titular: " + conta.titular);
        System.out.println("Saldo inicial: R$ " + conta.getSaldo());
        
        conta.depositar(500.0);
        System.out.println("Saldo atual: R$ " + conta.getSaldo());
        
        conta.sacar(200.0);
        System.out.println("Saldo final: R$ " + conta.getSaldo());
        
        conta.sacar(2000.0); // tentativa de saque maior que saldo
    }
}