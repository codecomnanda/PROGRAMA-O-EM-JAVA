public class ExemploWhile {
    public static void main(String[] args) {
        // Uso do while: repetir enquanto a condição for verdadeira
        int numero = 1;
        System.out.println("Números pares até 10:");
        while (numero <= 10) {
            if (numero % 2 == 0) {
                System.out.println(numero);
            }
            numero++;
        }
    }
}