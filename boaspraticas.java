public class boaspraticas {
    public static void main(String[] args) {
        final double TAXA = 0.15;
        int salarioBase = 2000;
        double salarioFinal = salarioBase + (salarioBase * TAXA);

        System.out.println("Salário Final: " + salarioFinal);
    }
}
