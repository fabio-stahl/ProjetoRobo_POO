import java.util.*;

public class Main{
    public static Scanner T = new Scanner(System.in);
    private static final long DELAY_MS = 1000;
    public static void main(String[] args) throws MovimentoInvalidoException {
        while (true) {
            System.out.println("\nEscolha o modo de jogo:");
            System.out.println("1 - Robô Manual");
            System.out.println("2 - Robôs Automáticos (Robô Normal vs Robô Normal)");
            System.out.println("3 - Robôs Automáticos (Robô Inteligente vs Robo Normal)");
            System.out.println("4 - Robôs Automáticos com obstáculos (Robo Normal vs Robo Inteligente)");
            System.out.println("0 - Sair");
            int opc = T.nextInt();

            switch (opc) {
                case 1 -> jogarRoboNormal();
                case 2 -> jogarRoboAutomatico();
                case 3 -> jogarRoboxRoboInteligente();
                case 4 -> jogarComObstaculos();
                case 0 -> {
                    System.out.println("Saindo do jogo.");
                    return;
                }
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    private static boolean procurarPosObstaculo(int i, int j, List<Obstaculo> obstaculos){
        for(Obstaculo o : obstaculos){
            if(o instanceof Rocha && o.linha == i && o.coluna == j){
                System.out.print("R ");
                return true;
            }else if(o instanceof Bomba && o.linha == i && o.coluna == j){
                System.out.print("B ");
                return true;
            }
        }
        return false;
    }

    private static void mostrarTabuleiro(int[] posicaoRobo1, int[] posicaoRobo2, int[] posicaoComida, List<Obstaculo> obstaculos){
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if(procurarPosObstaculo(i, j, obstaculos)){
                    continue;
                }
                if(posicaoRobo1[0] == i && posicaoRobo1[1] == j){
                    System.out.print("O ");
                }else if(posicaoRobo2[0] == i && posicaoRobo2[1] == j){
                    System.out.print("O ");
                }else if(posicaoComida[0] == i && posicaoComida[1] == j){
                    System.out.print("C ");
                }else{
                    System.out.print("+ ");
                }
            }
            System.out.println();
        }
    }
    
    private static void mostrarTabuleiro(int[] posicaoRobo1, int[] posicaoRobo2, int[] posicaoComida){
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if(posicaoRobo1[0] == j && posicaoRobo1[1] == i){
                    System.out.print("O ");
                }else if(posicaoRobo2[0] == j && posicaoRobo2[1] == i){
                    System.out.print("O ");
                }else if(posicaoComida[0] == j && posicaoComida[1] == i){
                    System.out.print("C ");
                }else{
                    System.out.print("+ ");
                }
            }
            System.out.println();
        }
    }

    private static void mostrarTabuleiro(int[] posicaoRobo, int[] posicaoComida){
        System.out.println("X: " + posicaoRobo[0] + " | Y: " + posicaoRobo[1]);
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if(posicaoRobo[0] == j && posicaoRobo[1] == i){
                    System.out.print("O ");
                }else if(posicaoComida[0] == j && posicaoComida[1] == i){
                    System.out.print("C ");
                }else{
                    System.out.print("+ ");
                }
            }
            System.out.println();
        }
    }

    private static void jogarRoboNormal(){
        Robo robo;
        Comida comida;

        System.out.println("Digite a cor do robo:");
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
            mostrarTabuleiro(robo.getPosicao(), comida.getPosicao());
            System.out.println("Digite a direção (up, down, left, right) ou sair para sair)");
            direcao = T.next();
            if (direcao.equals("sair")) {
                break;
            }else{
                try {
                    robo.mover(direcao);
                    if (robo.isComida(comida)) {
                        System.out.println("Comida encontrada!");
                        System.out.println("Movimentos do robô " + robo.getCor() + ": " + robo.getMovimentos());
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

    private static void delay() {
        try {
            Thread.sleep(DELAY_MS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private static void jogarRoboAutomatico() throws MovimentoInvalidoException {
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
        int rodada = 0;
        while (true) { 
            System.out.println("\n***********");
            System.out.println("*Rodada " + rodada + "*");
            System.out.println("***********");
            rodada++;
            int direcao1 = rand.nextInt(4) + 1;
            int direcao2 = rand.nextInt(4) + 1;

            try {
                if (robo1.mover(direcao1)) {
                    correto1++;
                    posicao = robo1.getPosicao();
                    System.out.println("Posição do robô " + robo1.getCor() + ": " + posicao[0] + ", " + posicao[1]);
                    mostrarTabuleiro(robo1.getPosicao(), robo2.getPosicao(), comida.getPosicao());
                }
            } catch (MovimentoInvalidoException e) {
                errado1++;
                System.out.println("Erro no robô " + robo1.getCor() + ": " + e.getMessage());
            }

            delay();

            try {
                if (robo2.mover(direcao2)) {
                    correto2++;
                    posicao = robo2.getPosicao();
                    System.out.println("Posição do robô " + robo2.getCor() + ": " + posicao[0] + ", " + posicao[1]);
                    mostrarTabuleiro(robo1.getPosicao(), robo2.getPosicao(), comida.getPosicao());
                }
            } catch (MovimentoInvalidoException e) {
                errado2++;
                System.out.println("Erro no robô " + robo2.getCor() + ": " + e.getMessage());
            }

            delay();

            if (robo1.isComida(comida) || robo2.isComida(comida)) {
                if (robo1.isComida(comida)) {
                    System.out.println("Comida encontrada pelo robô 1!");
                } else {
                    System.out.println("Comida encontrada pelo robô 2!");
                }
                System.out.println("Movimentos do robô " + robo1.getCor() + ": " + robo1.getMovimentos());
                System.out.println("Movimentos do robô " + robo2.getCor() + ": " + robo2.getMovimentos());
                break;
            }
        }

    }

    private static void jogarRoboxRoboInteligente() throws MovimentoInvalidoException{
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
        int rodada = 0;
        while (true) { 
            System.out.println("\n***********");
            System.out.println("*Rodada " + rodada + "*");
            System.out.println("***********");
            rodada++;
            int direcao1 = rand.nextInt(4) + 1;
            int direcao2 = rand.nextInt(4) + 1;

            try {
                if (robo1.mover(direcao1)) {
                    correto1++;
                    posicao = robo1.getPosicao();
                    System.out.println("Posição do robô " + robo1.getCor() + ": " + posicao[0] + ", " + posicao[1]);
                }
            } catch (MovimentoInvalidoException e) {
                errado1++;
                System.out.println("Erro no robô " + robo1.getCor() + ": " + e.getMessage());
            }
            mostrarTabuleiro(robo1.getPosicao(), robo2.getPosicao(), comida.getPosicao());
            delay();

            try {
                if (robo2.mover(direcao2)) {
                    correto2++;
                    posicao = robo2.getPosicao();
                    System.out.println("Posição do robô " + robo2.getCor() + ": " + posicao[0] + ", " + posicao[1]);
                }
            } catch (MovimentoInvalidoException e) {
                errado2++;
                System.out.println("Erro no robô " + robo2.getCor() + ": " + e.getMessage());
            }
            mostrarTabuleiro(robo1.getPosicao(), robo2.getPosicao(), comida.getPosicao());
            delay();

            if (robo1.isComida(comida) || robo2.isComida(comida)) {
                if (robo1.isComida(comida)) {
                    System.out.println("Comida encontrada pelo robô inteligente!");
                } else {
                    System.out.println("Comida encontrada pelo robô 2!");
                }
                System.out.println("Movimentos do robô " + robo1.getCor() + ": " + robo1.getMovimentos());
                System.out.println("Movimentos do robô " + robo2.getCor() + ": " + robo2.getMovimentos());
                break;
            }
        }

    }

    private static void jogarComObstaculos() throws MovimentoInvalidoException {
        System.out.println("\n>>> MODO: Robo Normal vs Robo Inteligente (com Obstáculos)");
        System.out.print("Digite a linha e a coluna da comida (0–4): ");
        int l = T.nextInt(), c = T.nextInt();
        Comida comida = new Comida(l, c);

        List<Obstaculo> obstaculos = new ArrayList<>();
        System.out.print("Quantos obstáculos deseja inserir? ");
        int n = T.nextInt();
        for (int i = 0; i < n; i++) {
            System.out.print("Tipo (1=Bomba, 2=Rocha): ");
            int tipo = T.nextInt();
            System.out.print("Linha e coluna do Obstáculo: ");
            int ol = T.nextInt(), oc = T.nextInt();
            String id = "O" + (i + 1);
            if (tipo == 1) obstaculos.add(new Bomba(id, ol, oc));
            else obstaculos.add(new Rocha(id, ol, oc));
        }

        Robo normal = new Robo("Normal");
        Robo inteligente = new RoboInteligente("Inteligente");
        Random rand = new Random();

        int rodada = 1;
        while (true) {
            System.out.println("\nRODADA " + rodada + " -------------------");

            System.out.println(">> Turno do Robo Normal:");
            if (!turno(normal, comida, obstaculos, rand))
                break;

            int[] posNormal = normal.getPosicao();
            int[] posInt = inteligente.getPosicao();
            System.out.println("Posição atual Robo Normal: (" + posNormal[0] + "," + posNormal[1] + ")");
            mostrarTabuleiro(posNormal, posInt, comida.getPosicao(), obstaculos);
            delay();

            System.out.println(">> Turno do Robo Inteligente:");
            if (!turno(inteligente, comida, obstaculos, rand))
                break;

            System.out.println("Posição atual Robo Inteligente: (" + posInt[0] + "," + posInt[1] + ")");
            mostrarTabuleiro(posNormal, posInt, comida.getPosicao(), obstaculos);
            delay();

            rodada++;
        }

        System.out.println("\n--- FIM DE JOGO ---");
        System.out.println("Movimentos Robo Normal: " + normal.getMovimentos());
        System.out.println("Movimentos Robo Inteligente: " + inteligente.getMovimentos());

        if (normal.isExplodiu())
            System.out.println("Robo Normal explodiu.");
        if (inteligente.isExplodiu())
            System.out.println("Robo Inteligente explodiu.");

        if (!normal.isExplodiu() && !inteligente.isExplodiu()) {
            System.out.println("Nenhum robô explodiu.");
        }
    }


    private static boolean turno(Robo robo, Comida comida, List<Obstaculo> obstaculos, Random rand) throws MovimentoInvalidoException {
        if (robo.isExplodiu()) return false;
        int dir = rand.nextInt(4) + 1;
        robo.lastLinha = robo.getPosicao()[0];
        robo.lastColuna = robo.getPosicao()[1];

        try {
            robo.mover(dir);
            robo.movimentos++;

            // Verifica obstáculos
            for (Obstaculo o : obstaculos) {
                if (o.getLinha() == robo.getPosicao()[0] && o.getColuna() == robo.getPosicao()[1]) {
                    o.bater(robo, obstaculos);
                    break;
                }
            }

            // Verifica comida
            if (robo.isComida(comida)) {
                System.out.println("Robo " + robo.getCor() + " encontrou a comida!");
                return false;
            }

        } catch (MovimentoInvalidoException e) {
            System.out.println("Movimento inválido para o robô " + robo.getCor() + ": " + e.getMessage());
        }

        return !robo.isExplodiu();
    }
}