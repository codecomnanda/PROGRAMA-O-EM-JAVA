public class OperadoresLogicos {
    public static void main(String[] args) {
        boolean usuarioLogado = true;
        boolean temPermissao = false;

        if (usuarioLogado && temPermissao) {
            System.out.println("Acesso concedido!");
        
        } else {
            System.out.println("Acesso negado!");
        }
    }
    
}
