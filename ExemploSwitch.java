public class ExemploSwitch {
    public static void main(String[] args) {
        int diaSemana = 3; // Simula uma opção (1 a 7)

        // Estrutura switch: avalia a variável diaSemana
        switch (diaSemana) {
            case 1:
                System.out.println("Domingo");
                break; // Sai do switch
            case 2:
                System.out.println("Segunda-feira");
                break;
            case 3:
                System.out.println("Terça-feira");
                break;
            case 4:
                System.out.println("Quarta-feira");
                break;
            case 5:
                System.out.println("Quinta-feira");
                break;
            case 6:
                System.out.println("Sexta-feira");
                break;
            case 7:
                System.out.println("Sábado");
                break;
            default:
                System.out.println("Dia inválido!"); // Se não for 1-7
                break;
        }

        System.out.println("Fim do programa.");
    }
}
