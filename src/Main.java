import java.util.*;

public class Main{
    public static Scanner T = new Scanner(System.in);
    static Jogo jogo = new Jogo();
    

    public static void main(String[] args) throws MovimentoInvalidoException {
        while (true) {
            System.out.println("\nEscolha o modo de jogo:");
            System.out.println("1 - Robô Manual");
            System.out.println("2 - Robôs Automáticos (Robô Normal vs Robô Normal)");
            System.out.println("3 - Robôs Automáticos (Robô Inteligente vs Robo Normal)");
            System.out.println("4 - Robôs Automáticos com obstáculos (Robo Normal vs Robo Inteligente)");
            System.out.println("0 - Sair");
            System.out.print("-> ");
            int opc = T.nextInt();

            switch (opc) {
                case 1 -> jogo.jogarRoboNormal();
                case 2 -> jogo.jogarRoboAutomatico();
                case 3 -> jogo.jogarRoboxRoboInteligente();
                case 4 -> jogo.jogarComObstaculos();
                case 0 -> {
                    System.out.println("Saindo do jogo.");
                    return;
                }
                default -> System.out.println("Opção inválida.");
            }
        }
    }
}