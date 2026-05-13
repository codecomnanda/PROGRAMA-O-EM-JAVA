// Classe abstrata: define o "contrato" para qualquer tipo de pagamento
abstract class Pagamento {
    protected double valor;

    public Pagamento(double valor) {
        this.valor = valor;
    }

    // Método abstrato: cada subclasse é obrigada a implementar
    public abstract void processarPagamento();
}

// Implementação concreta para Cartão de Crédito
class CartaoCredito extends Pagamento {
    private String numeroCartao;

    public CartaoCredito(double valor, String numeroCartao) {
        super(valor);
        this.numeroCartao = numeroCartao;
    }

    @Override
    public void processarPagamento() {
        System.out.println("Pagamento de R$ " + valor + " processado via Cartão de Crédito (final: " + numeroCartao + ").");
    }
}

// Implementação concreta para Boleto
class Boleto extends Pagamento {
    private String codigoBarras;

    public Boleto(double valor, String codigoBarras) {
        super(valor);
        this.codigoBarras = codigoBarras;
    }

    @Override
    public void processarPagamento() {
        System.out.println("Pagamento de R$ " + valor + " processado via Boleto (código: " + codigoBarras + ").");
    }
}

// Classe principal para testar a abstração
public class TestePagamento {
    public static void main(String[] args) {
        // Polimorfismo: tratamos diferentes objetos pela interface comum Pagamento
        Pagamento p1 = new CartaoCredito(150.00, "1234-5678-9012-3456");
        Pagamento p2 = new Boleto(89.90, "34191.79001 01043.510047 91020.150008 7 00000026000");

        p1.processarPagamento(); // chama método do CartaoCredito
        p2.processarPagamento(); // chama método do Boleto
    }
}
