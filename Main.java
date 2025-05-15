import java.util.Random;
import java.util.Scanner;

public class Main{
    public static Scanner T = Entrada.getScanner();

    public static void main(String[] args){
        
        int tipoRobo = -1;
        while(tipoRobo != 0){
            menuInicial();
            tipoRobo = T.nextInt();

            switch (tipoRobo) {
                case 1:
                    modoManual();
                    break;
                case 2:
                    modoX1Normais();
                    break;
                case 3:
                    modoNormalVSInteligente();
                    break;
                case 4:
                    modoX1Inteligentes();
                    break;
                default:
                    break;
            }
        }
    }

    private static void menuInicial(){
        System.out.println("Digite o modo que deseja jogar:");
        System.out.println("1. Modo manual");
        System.out.println("2. Modo x1 de robôs normais");
        System.out.println("3. Modo robô normal VS robô inteligente");
        System.out.println("4. Modo x1 de robôs inteligentes");
        System.out.println("0. sair");
        System.out.print("-> ");
    }

    private static void modoManual(){
        Robo robo;
        Comida comida;

        System.out.print("Digite a cor do robo: ");
        String cor = T.next();
        robo = new Robo(cor);

        System.out.println("Digite a linha e a coluna da comida:");
        int linha = T.nextInt();
        int coluna = T.nextInt();

        if (linha < 0 || linha > 4 || coluna < 0 || coluna > 4) {
            System.out.println("Posição inválida da comida. Tente novamente.");
            return;
        }

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
                if(robo.isComida(comida)){
                    System.out.println("Comida encontrada!");
                    break;
                }
            }
        }
    }
    private static void modoX1Normais () {
        Robo robo1;
        Robo robo2;
        Comida comida;
        
        System.out.println("Digite a cor do robô 1:");
        String cor = T.next();
        robo1 = new Robo(cor);

        System.out.println("Digite a cor do robô 2:");
        cor = T.next();
        robo2 = new Robo(cor);

        System.out.println("Digite a linha e a coluna da comida:");
        int linha = T.nextInt();
        int coluna = T.nextInt();

        if (linha < 0 || linha > 4 || coluna < 0 || coluna > 4) {
            System.out.println("Posição inválida da comida. Tente novamente.");
            return;
        }

        comida = new Comida(linha, coluna);
        Random rand = new Random();
        int correto1 = 0;
        int correto2 = 0;
        int errado1 = 0;
        int errado2 = 0;
        // Inicializa a posição dos robôs
        int[] posicao;

        while (true) { 
            int direcao1 = rand.nextInt(4) + 1;
            int direcao2 = rand.nextInt(4) + 1;

            try {
                if (robo1.mover(direcao1)) {
                    correto1++;
                    posicao = robo1.getPosicao();
                    System.out.println("Posição do robô 1: " + posicao[0] + ", " + posicao[1]);
                }
            } catch (MovimentoInvalidoException e) {
                errado1++;
                System.out.println("Erro no robô 1: " + e.getMessage());
            }

            try {
                if (robo2.mover(direcao2)) {
                    correto2++;
                    posicao = robo2.getPosicao();
                    System.out.println("Posição do robô 2: " + posicao[0] + ", " + posicao[1]);
                }
            } catch (MovimentoInvalidoException e) {
                errado2++;
                System.out.println("Erro no robô 2: " + e.getMessage());
            }

            if (robo1.isComida(comida) || robo2.isComida(comida)) {
                if (robo1.isComida(comida)) {
                    System.out.println("Comida encontrada pelo robô 1!");
                } else {
                    System.out.println("Comida encontrada pelo robô 2!");
                }
                break;
            }
        }

    }

    private static void modoNormalVSInteligente () {
        Robo robo1;
        Robo robo2;
        Comida comida;
        
        System.out.println("Digite a cor do robô inteligente :");
        String cor = T.next();
        robo1 = new RoboInteligente(cor);

        System.out.println("Digite a cor do robô 2:");
        cor = T.next();
        robo2 = new Robo(cor);

        System.out.println("Digite a linha e a coluna da comida:");
        int linha = T.nextInt();
        int coluna = T.nextInt();

        if (linha < 0 || linha > 4 || coluna < 0 || coluna > 4) {
            System.out.println("Posição inválida da comida. Tente novamente.");
            return;
        }

        comida = new Comida(linha, coluna);
        Random rand = new Random();
        int correto1 = 0;
        int correto2 = 0;
        int errado1 = 0;
        int errado2 = 0;
        int[] posicao;

        while (true) { 
            int direcao1 = rand.nextInt(4) + 1;
            int direcao2 = rand.nextInt(4) + 1;

            try {
                if (robo1.mover(direcao1)) {
                    correto1++;
                    posicao = robo1.getPosicao();
                    System.out.println("Posição do robô 1: " + posicao[0] + ", " + posicao[1]);
                }
            } catch (MovimentoInvalidoException e) {
                errado1++;
                System.out.println("Erro no robô 1: " + e.getMessage());
            }

            try {
                if (robo2.mover(direcao2)) {
                    correto2++;
                    posicao = robo2.getPosicao();
                    System.out.println("Posição do robô 2: " + posicao[0] + ", " + posicao[1]);
                }
            } catch (MovimentoInvalidoException e) {
                errado2++;
                System.out.println("Erro no robô 2: " + e.getMessage());
            }

            if (robo1.isComida(comida) || robo2.isComida(comida)) {
                if (robo1.isComida(comida)) {
                    System.out.println("Comida encontrada pelo robô inteligente!");
                } else {
                    System.out.println("Comida encontrada pelo robô 2!");
                }
                break;
            }
        }

    }

    private static void modoX1Inteligentes(){
        
    }
}
