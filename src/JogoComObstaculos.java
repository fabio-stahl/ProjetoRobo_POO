import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JogoComObstaculos extends Modo{


    public void jogar() throws MovimentoInvalidoException {
        System.out.println("\n>>> MODO: Robo Normal vs Robo Inteligente (com Obstáculos)");
        System.out.println("\nDigite a cor do robô normal:");
        System.out.println("1. " + Main.VERDE + "Verde"+ Main.RESET);
        System.out.println("2. " + Main.AMARELO + "Amarelo"+ Main.RESET);
        System.out.println("3. " + Main.VERMELHO + "Vermelho"+ Main.RESET);
        System.out.println("4. " + Main.ROSA + "Rosa"+ Main.RESET);
        System.out.println("5. " + Main.ROXO + "Roxo"+ Main.RESET);
        System.out.println("6. " + Main.AZUL + "Azul"+ Main.RESET);
        System.out.print("-> ");
        int esc1 = T.nextInt();
        String cor1;
        switch (esc1) {
            case 1:
                cor1 = Main.VERDE;
                break;
            case 2:
                cor1 = Main.AMARELO;
                break;
            case 3:
                cor1 = Main.VERMELHO;
                break;
            case 4:
                cor1 = Main.ROSA;
                break;
            case 5:
                cor1 = Main.ROXO;
                break;
            case 6:
                cor1 = Main.AZUL;
                break;
            default:
                System.out.println(Main.ERRO_COR + "Cor inválida. " + Main.RESET + "Tente novamente.");
                return;
        }
        Robo normal = new Robo(cor1);
        
        System.out.println("\nDigite a cor do robô inteligente:");
        System.out.println("1. " + Main.VERDE + "Verde"+ Main.RESET);
        System.out.println("2. " + Main.AMARELO + "Amarelo"+ Main.RESET);
        System.out.println("3. " + Main.VERMELHO + "Vermelho"+ Main.RESET);
        System.out.println("4. " + Main.ROSA + "Rosa"+ Main.RESET);
        System.out.println("5. " + Main.ROXO + "Roxo"+ Main.RESET);
        System.out.println("6. " + Main.AZUL + "Azul"+ Main.RESET);
        System.out.print("-> ");
        int esc2 = T.nextInt();
        String cor2;
        switch (esc2) {
            case 1:
                cor2 = Main.VERDE;
                break;
            case 2:
                cor2 = Main.AMARELO;
                break;
            case 3:
                cor2 = Main.VERMELHO;
                break;
            case 4:
                cor2 = Main.ROSA;
                break;
            case 5:
                cor2 = Main.ROXO;
                break;
            case 6:
                cor2 = Main.AZUL;
                break;
            default:
                System.out.println(Main.ERRO_COR + "Cor inválida. " + Main.RESET + "Tente novamente.");
                return;
        }
        Robo inteligente = new RoboInteligente(cor2);

        System.out.println("\nDigite a linha e a coluna da comida: ");
        System.out.print("-> linha: ");
        int l = T.nextInt();
        System.out.print("-> coluna: ");
        int c = T.nextInt();
        if(l < 0 || l > 3 || c < 0 || c > 3 || l == 0 && c == 0){
            System.out.println(Main.ERRO_COR + "Posição inválida da comida. " + Main.RESET + "Tente novamente.");
            return;
        }
        Comida comida = new Comida(l, c);

        List<Obstaculo> obstaculos = new ArrayList<>();
        System.out.println("\nQuantos obstáculos deseja inserir?");
        System.out.print("-> ");
        int n = T.nextInt();
        for (int i = 0; i < n; i++) {
            System.out.println("\n[1 : Bomba | 2 : Rocha]");
            System.out.print("-> Tipo do obstáculo " + (i + 1) + ": ");
            int tipo = T.nextInt();
            if(tipo < 1 || tipo > 2){
                System.out.println(Main.ERRO_COR + "Tipo inválido. " + Main.RESET + "Tente novamente.");
                return;
            }

            System.out.println("Linha e coluna do Obstáculo " + (i + 1) + ":");
            System.out.print("-> linha: ");
            int ol = T.nextInt();
            System.out.print("-> coluna: ");
            int oc = T.nextInt();

            if(ol < 0 || ol > 3 || oc < 0 || oc > 3 || ol == 0 && oc == 0){
                i--;
                continue;
            }

            String id = "O" + (i + 1);
            if (tipo == 1) obstaculos.add(new Bomba(id, ol, oc));
            else obstaculos.add(new Rocha(id, ol, oc));
        }

        Random rand = new Random();

        int rodada = 0;
        while (true) {
            System.out.println("\n***********");
            System.out.println("*Rodada " + rodada + "*");
            System.out.println("***********");
            rodada++;
            if(rodada == 1){
                System.out.println("Tabuleiro Inicial:");
                tabuleiro.mostrarTabuleiro(normal, inteligente, comida.getPosicao(), obstaculos);
                continue;
            }

            System.out.println(">> Turno do " + normal.getCor() + "robô normal" + Main.RESET + ":");
            if (!turno(normal, inteligente, comida, obstaculos, rand))
                break;

            tabuleiro.mostrarTabuleiro(normal, inteligente, comida.getPosicao(), obstaculos);
            delay();

            System.out.println(">> Turno do " + inteligente.getCor() + "robô inteligente" + Main.RESET + ":");
            if (!turno(inteligente, normal, comida, obstaculos, rand))
                break;

            tabuleiro.mostrarTabuleiro(normal, inteligente, comida.getPosicao(), obstaculos);
            delay();

        }

        System.out.println("\n--- FIM DE JOGO ---");
        System.out.println("Movimentos do " + normal.getCor() + "robô normal" + Main.RESET + ": " + normal.getMovimentos());
        System.out.println("Movimentos do " + inteligente.getCor() + "robô inteligente" + Main.RESET + ": " + inteligente.getMovimentos());

        if (normal.getExplodiu() && inteligente.getExplodiu())
            System.out.println("Robôs explodiram.");
        else if (!normal.getExplodiu() && !inteligente.getExplodiu()) {
            System.out.println("Nenhum robô explodiu.");
        }
    }
}
