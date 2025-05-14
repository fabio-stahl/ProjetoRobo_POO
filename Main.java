import java.util.Scanner;
public class Main {
    public static void main(String[] args){
        Scanner T = new Scanner(System.in);
        Robo robo;
        Comida comida;
        System.out.println("Digite a cor do robo:");
        String cor = T.next();
        robo = new Robo(cor);
        System.out.println("Digite a linha e a coluna da comida:");
        int linha = T.nextInt();
        int coluna = T.nextInt();
        comida = new Comida(linha, coluna);
        String direcao;
        while (true) { 
            System.out.println("Digite a direção (up, down, left, right) ou sair para sair)");
            direcao = T.next();
            if (direcao.equals("sair")) {
                break;
            }else{
                try {
                    robo.mover(direcao);
                    int[] posicao = robo.getPosicao();
                    System.out.println("Posição do robô: " + posicao[0] + ", " + posicao[1]);
                    if (robo.isComida(comida)) {
                        System.out.println("Comida encontrada!");
                        break;
                    }
                } catch (MovimentoInvalidoException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
