import java.util.*;

public class Main{
    public static Scanner T = new Scanner(System.in);
    static Modo modo;

    //cores
    public static final String RESET = "\u001B[0m";
    public static final String VERDE = "\u001B[32m";
    public static final String AMARELO = "\u001B[38;5;226m";
    public static final String AZUL = "\u001B[34m";
    public static final String ROXO = "\u001B[38;5;135m";
    public static final String VERMELHO = "\u001B[38;5;196m";
    public static final String ROSA = "\u001B[38;5;205m";

    public static final String ERRO_COR = "\u001B[38;5;202m";
    

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
                case 1:
                    modo = new JogoNormal();
                    modo.jogar();
                    break;
                case 2:
                    modo = new JogoNormalAutomatico();
                    modo.jogar();
                    break;
                case 3:
                    modo = new JogoInteligentexInteligente();
                    modo.jogar();
                    break;
                case 4:
                    modo = new JogoComObstaculos(); 
                    modo.jogar();
                    break;
                case 0:
                    System.out.println("Saindo do jogo.");
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}