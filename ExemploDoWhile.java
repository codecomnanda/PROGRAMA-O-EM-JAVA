public class ExemploDoWhile {
    public static void main(String[] args) {
        // Uso do do-while: o bloco executa pelo menos uma vez
        int tentativas = 0;
        int senhaCorreta = 1234;
        int senhaDigitada;
        java.util.Scanner sc = new java.util.Scanner(System.in);
        
        do {
            System.out.print("Digite a senha: ");
            senhaDigitada = sc.nextInt();
            tentativas++;
        } while (senhaDigitada != senhaCorreta && tentativas < 3);
        
        if (senhaDigitada == senhaCorreta) {
            System.out.println("Acesso permitido!");
        } else {
            System.out.println("Bloqueado!");
        }
        sc.close();
    }
}
