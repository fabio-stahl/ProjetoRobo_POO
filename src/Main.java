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
            System.out.print("-> ");
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
                Bomba bomba = (Bomba) o;
                if(bomba.getAtivada()){
                    System.out.print("B ");
                    return true;
                }
            }
        }
        return false;
    }

    private static void mostrarTabuleiro(Robo robo1, Robo robo2, int[] posicaoComida, List<Obstaculo> obstaculos){
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if(procurarPosObstaculo(i, j, obstaculos)){
                    continue;
                }
                if(robo1.getPosicao()[0] == i && robo1.getPosicao()[1] == j && !robo1.getExplodiu()){
                    System.out.print(robo1.getCor() + "O " + "\u001B[0m");
                }else if(robo2.getPosicao()[0] == i && robo2.getPosicao()[1] == j && !robo2.getExplodiu()){
                    System.out.print(robo2.getCor() + "O " + "\u001B[0m");
                }else if(posicaoComida[0] == i && posicaoComida[1] == j){
                    System.out.print("C ");
                }else{
                    System.out.print("+ ");
                }
            }
            System.out.println();
        }
    }
    
    private static void mostrarTabuleiro(Robo robo1, Robo robo2, int[] posicaoComida){
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if(robo1.getPosicao()[0] == j && robo1.getPosicao()[1] == i){
                    System.out.print(robo1.getCor() + "O " + "\u001B[0m");
                }else if(robo2.getPosicao()[0] == j && robo2.getPosicao()[1] == i){
                    System.out.print(robo2.getCor() + "O " + "\u001B[0m");
                }else if(posicaoComida[0] == j && posicaoComida[1] == i){
                    System.out.print("C ");
                }else{
                    System.out.print("+ ");
                }
            }
            System.out.println();
        }
    }

    private static void mostrarTabuleiro(Robo robo, int[] posicaoComida){
        System.out.println("X: " + robo.getPosicao()[0] + " | Y: " + robo.getPosicao()[1]);
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if(robo.getPosicao()[0] == j && robo.getPosicao()[1] == i){
                    System.out.print(robo.getCor() + "O " + "\u001B[0m");
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
        int movs = 0;

        System.out.println("\n>>> MODO: Robo Manual");
        System.out.println("Digite a cor do robo:");
        System.out.println("1. " + "\u001B[31m" + "Vermelho"+ "\u001B[0m");
        System.out.println("2. " + "\u001B[32m" + "Verde"+ "\u001B[0m");
        System.out.println("3. " + "\u001B[33m" + "Amarelo"+ "\u001B[0m");
        System.out.println("4. " + "\u001B[34m" + "Azul"+ "\u001B[0m");
        System.out.println("5. " + "\u001B[35m" + "Roxo"+ "\u001B[0m");
        System.out.println("6. " + "\u001B[36m" + "Ciano"+ "\u001B[0m");
        System.out.print("-> ");
        int esc = T.nextInt();
        String cor;
        switch (esc) {
            case 1:
                cor = "\u001B[31m";
                break;
            case 2:
                cor = "\u001B[32m";
                break;
            case 3:
                cor = "\u001B[33m";
                break;
            case 4:
                cor = "\u001B[34m";
                break;
            case 5:
                cor = "\u001B[35m";
                break;
            case 6:
                cor = "\u001B[36m";
                break;
            default:
                System.out.println("Cor inválida. Tente novamente.");
                return;
        }
        robo = new Robo(cor);

        System.out.println("Digite a linha e a coluna da comida:");
        System.out.print("-> linha: ");
        int linha = T.nextInt();
        System.out.print("-> coluna: ");
        int coluna = T.nextInt();

        if (linha < 0 || linha > 4 || coluna < 0 || coluna > 4) {
            System.out.println("Posição inválida da comida. Tente novamente.");
            return;
        }

        comida = new Comida(linha, coluna);
        String direcao;
        while (true) { 
            mostrarTabuleiro(robo, comida.getPosicao());
            System.out.println("Digite a direção (up, down, left, right) ou sair para sair)");
            direcao = T.next();
            if (direcao.equals("sair")) {
                break;
            }else{
                try {
                    robo.mover(direcao);
                    movs += 1;
                    robo.setMovimentos(movs);
                    if (robo.isComida(comida)) {
                        System.out.println("Comida encontrada!");
                        System.out.println("Movimentos do robô " + robo.getCor() + "manual" + "\u001B[0m" + ": " + robo.getMovimentos());
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
        
        System.out.println("\n>>> MODO: Robô Automático (Robô Normal vs Robô Normal)");
        System.out.println("\nDigite a cor do robo 1:");
        System.out.println("1. " + "\u001B[31m" + "Vermelho"+ "\u001B[0m");
        System.out.println("2. " + "\u001B[32m" + "Verde"+ "\u001B[0m");
        System.out.println("3. " + "\u001B[33m" + "Amarelo"+ "\u001B[0m");
        System.out.println("4. " + "\u001B[34m" + "Azul"+ "\u001B[0m");
        System.out.println("5. " + "\u001B[35m" + "Roxo"+ "\u001B[0m");
        System.out.println("6. " + "\u001B[36m" + "Ciano"+ "\u001B[0m");
        System.out.print("-> ");
        int esc1 = T.nextInt();
        String cor1;
        switch (esc1) {
            case 1:
                cor1 = "\u001B[31m";
                break;
            case 2:
                cor1 = "\u001B[32m";
                break;
            case 3:
                cor1 = "\u001B[33m";
                break;
            case 4:
                cor1 = "\u001B[34m";
                break;
            case 5:
                cor1 = "\u001B[35m";
                break;
            case 6:
                cor1 = "\u001B[36m";
                break;
            default:
                System.out.println("Cor inválida. Tente novamente.");
                return;
        }
        robo1 = new Robo(cor1);

        System.out.println("\nDigite a cor do robo 2:");
        System.out.println("1. " + "\u001B[31m" + "Vermelho"+ "\u001B[0m");
        System.out.println("2. " + "\u001B[32m" + "Verde"+ "\u001B[0m");
        System.out.println("3. " + "\u001B[33m" + "Amarelo"+ "\u001B[0m");
        System.out.println("4. " + "\u001B[34m" + "Azul"+ "\u001B[0m");
        System.out.println("5. " + "\u001B[35m" + "Roxo"+ "\u001B[0m");
        System.out.println("6. " + "\u001B[36m" + "Ciano"+ "\u001B[0m");
        System.out.print("-> ");
        int esc2 = T.nextInt();
        String cor2;
        switch (esc2) {
            case 1:
                cor2 = "\u001B[31m";
                break;
            case 2:
                cor2 = "\u001B[32m";
                break;
            case 3:
                cor2 = "\u001B[33m";
                break;
            case 4:
                cor2 = "\u001B[34m";
                break;
            case 5:
                cor2 = "\u001B[35m";
                break;
            case 6:
                cor2 = "\u001B[36m";
                break;
            default:
                System.out.println("Cor inválida. Tente novamente.");
                return;
        }
        robo2 = new Robo(cor2);

        System.out.println("Digite a linha e a coluna da comida:");
        System.out.print("-> linha: ");
        int linha = T.nextInt();
        System.out.print("-> coluna: ");
        int coluna = T.nextInt();

        if (linha < 0 || linha > 3 || coluna < 0 || coluna > 3) {
            System.out.println("Posição inválida da comida. Tente novamente.");
            return;
        }

        comida = new Comida(linha, coluna);
        Random rand = new Random();
        // Inicializa a posição dos robôs
        int[] posicao;
        int rodada = 0;
        int movs1 = 0;
        int movs2 = 0;
        while (true) { 
            System.out.println("\n***********");
            System.out.println("*Rodada " + rodada + "*");
            System.out.println("***********");
            rodada++;
            int direcao1 = rand.nextInt(4) + 1;
            int direcao2 = rand.nextInt(4) + 1;

            try {
                if (robo1.mover(direcao1)) {
                    movs1 += 1;
                    robo1.setMovimentos(movs1);
                    posicao = robo1.getPosicao();
                    System.out.println("Posição do " + robo1.getCor() + "robô normal 1" + "\u001B[0m" + ": " + posicao[0] + ", " + posicao[1]);
                }
            } catch (MovimentoInvalidoException e) {
                System.out.println("Erro no " + robo1.getCor() + "robô normal 1" + "\u001B[0m" + ": " + e.getMessage());
            }
            mostrarTabuleiro(robo1, robo2, comida.getPosicao());
            delay();

            try {
                if (robo2.mover(direcao2)) {
                    movs2 += 1;
                    robo2.setMovimentos(movs2);
                    posicao = robo2.getPosicao();
                    System.out.println("Posição do " + robo2.getCor() + "robô normal 2" + "\u001B[0m" + ": " + posicao[0] + ", " + posicao[1]);
                }
            } catch (MovimentoInvalidoException e) {
                System.out.println("Erro no " + robo2.getCor() + "robô normal 2" + "\u001B[0m" + ": " + e.getMessage());
            }
            mostrarTabuleiro(robo1, robo2, comida.getPosicao());
            delay();

            if (robo1.isComida(comida) || robo2.isComida(comida)) {
                if (robo1.isComida(comida)) {
                    System.out.println("Comida encontrada pelo " + robo1.getCor() + "robô normal 1!" + "\u001B[0m");
                } else {
                    System.out.println("Comida encontrada pelo " + robo1.getCor() + "robô normal 2!" + "\u001B[0m");
                }
                System.out.println("Movimentos do " + robo1.getCor() + "robô normal 1" + "\u001B[0m" + ": " + robo1.getMovimentos());
                System.out.println("Movimentos do " + robo2.getCor() + "robô normal 2" + "\u001B[0m" + ": " + robo2.getMovimentos());
                break;
            }
        }

    }

    private static void jogarRoboxRoboInteligente() throws MovimentoInvalidoException{
        Robo robo1;
        Robo robo2;
        Comida comida;
        
        System.out.println("\n>>> MODO: Robô Automático (Robô Inteligente vs Robô Normal)");
        System.out.println("\nDigite a cor do robô inteligente:");
        System.out.println("1. " + "\u001B[31m" + "Vermelho"+ "\u001B[0m");
        System.out.println("2. " + "\u001B[32m" + "Verde"+ "\u001B[0m");
        System.out.println("3. " + "\u001B[33m" + "Amarelo"+ "\u001B[0m");
        System.out.println("4. " + "\u001B[34m" + "Azul"+ "\u001B[0m");
        System.out.println("5. " + "\u001B[35m" + "Roxo"+ "\u001B[0m");
        System.out.println("6. " + "\u001B[36m" + "Ciano"+ "\u001B[0m");
        System.out.print("-> ");
        int esc1 = T.nextInt();
        String cor1;
        switch (esc1) {
            case 1:
                cor1 = "\u001B[31m";
                break;
            case 2:
                cor1 = "\u001B[32m";
                break;
            case 3:
                cor1 = "\u001B[33m";
                break;
            case 4:
                cor1 = "\u001B[34m";
                break;
            case 5:
                cor1 = "\u001B[35m";
                break;
            case 6:
                cor1 = "\u001B[36m";
                break;
            default:
                System.out.println("Cor inválida. Tente novamente.");
                return;
        }
        robo1 = new RoboInteligente(cor1);

        System.out.println("\nDigite a cor do robô normal:");
        System.out.println("1. " + "\u001B[31m" + "Vermelho"+ "\u001B[0m");
        System.out.println("2. " + "\u001B[32m" + "Verde"+ "\u001B[0m");
        System.out.println("3. " + "\u001B[33m" + "Amarelo"+ "\u001B[0m");
        System.out.println("4. " + "\u001B[34m" + "Azul"+ "\u001B[0m");
        System.out.println("5. " + "\u001B[35m" + "Roxo"+ "\u001B[0m");
        System.out.println("6. " + "\u001B[36m" + "Ciano"+ "\u001B[0m");
        System.out.print("-> ");
        int esc2 = T.nextInt();
        String cor2;
        switch (esc2) {
            case 1:
                cor2 = "\u001B[31m";
                break;
            case 2:
                cor2 = "\u001B[32m";
                break;
            case 3:
                cor2 = "\u001B[33m";
                break;
            case 4:
                cor2 = "\u001B[34m";
                break;
            case 5:
                cor2 = "\u001B[35m";
                break;
            case 6:
                cor2 = "\u001B[36m";
                break;
            default:
                System.out.println("Cor inválida. Tente novamente.");
                return;
        }
        robo2 = new Robo(cor2);

        System.out.println("Digite a linha e a coluna da comida:");
        System.out.print("-> linha: ");
        int linha = T.nextInt();
        System.out.print("-> coluna: ");
        int coluna = T.nextInt();

        if (linha < 0 || linha > 3 || coluna < 0 || coluna > 3) {
            System.out.println("Posição inválida da comida. Tente novamente.");
            return;
        }

        comida = new Comida(linha, coluna);
        Random rand = new Random();
        int[] posicao;
        int rodada = 0;
        int movs1 = 0;
        int movs2 = 0;
        while (true) { 
            System.out.println("\n***********");
            System.out.println("*Rodada " + rodada + "*");
            System.out.println("***********");
            rodada++;
            int direcao1 = rand.nextInt(4) + 1;
            int direcao2 = rand.nextInt(4) + 1;

            try {
                if (robo1.mover(direcao1)) {
                    movs1 += 1;
                    robo1.setMovimentos(movs1);
                    posicao = robo1.getPosicao();
                    System.out.println("Posição do " + robo1.getCor() + "robô inteligente" + "\u001B[0m" + ": " + posicao[0] + ", " + posicao[1]);
                }
            } catch (MovimentoInvalidoException e) {
                System.out.println("Erro no " + robo1.getCor() + "robô inteligente" + "\u001B[0m" + ": " + e.getMessage());
            }
            mostrarTabuleiro(robo1, robo2, comida.getPosicao());
            delay();

            try {
                if (robo2.mover(direcao2)) {
                    movs2 += 1;
                    robo2.setMovimentos(movs2);
                    posicao = robo2.getPosicao();
                    System.out.println("Posição do " + robo2.getCor() + "robô normal" + "\u001B[0m" + ": " + posicao[0] + ", " + posicao[1]);
                }
            } catch (MovimentoInvalidoException e) {
                System.out.println("Erro no " + robo2.getCor() + "robô normal" + "\u001B[0m" + ": " + e.getMessage());
            }
            mostrarTabuleiro(robo1, robo2, comida.getPosicao());
            delay();

            if (robo1.isComida(comida) || robo2.isComida(comida)) {
                if (robo1.isComida(comida)) {
                    System.out.println("Comida encontrada pelo" + robo1.getCor() + "robô inteligente!" + "\u001B[0m");
                } else {
                    System.out.println("Comida encontrada pelo" + robo2.getCor() + "robô normal!" + "\u001B[0m");
                }
                System.out.println("Movimentos do " + robo1.getCor() + "robô inteligente" + "\u001B[0m" + ": " + robo1.getMovimentos());
                System.out.println("Movimentos do " + robo2.getCor() + "robô normal" + "\u001B[0m" + ": " + robo2.getMovimentos());
                break;
            }
        }

    }

    private static void jogarComObstaculos() throws MovimentoInvalidoException {
        System.out.println("\n>>> MODO: Robo Normal vs Robo Inteligente (com Obstáculos)");
        System.out.println("\nDigite a linha e a coluna da comida: ");
        System.out.print("-> linha: ");
        int l = T.nextInt();
        System.out.print("-> coluna: ");
        int c = T.nextInt();
        if(l < 0 || l > 3 || c < 0 || c > 3){
            System.out.println("Posição inválida da comida. Tente novamente.");
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
                System.out.println("Tipo inválido. Tente novamente.");
                return;
            }

            System.out.println("Linha e coluna do Obstáculo " + (i + 1) + ":");
            System.out.print("-> linha: ");
            int ol = T.nextInt();
            System.out.print("-> coluna: ");
            int oc = T.nextInt();

            if(ol < 0 || ol > 3 || oc < 0 || oc > 3){
                i--;
                continue;
            }

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
            if (!turno(normal, inteligente, comida, obstaculos, rand))
                break;

            int[] posNormal = normal.getPosicao();
            int[] posInt = inteligente.getPosicao();
            System.out.println("Posição atual Robo Normal: (" + posNormal[0] + "," + posNormal[1] + ")");
            mostrarTabuleiro(normal, inteligente, comida.getPosicao(), obstaculos);
            delay();

            System.out.println(">> Turno do Robo Inteligente:");
            if (!turno(inteligente, normal, comida, obstaculos, rand))
                break;

            System.out.println("Posição atual Robo Inteligente: (" + posInt[0] + "," + posInt[1] + ")");
            mostrarTabuleiro(normal, inteligente, comida.getPosicao(), obstaculos);
            delay();

            rodada++;
        }

        System.out.println("\n--- FIM DE JOGO ---");
        System.out.println("Movimentos Robo Normal: " + normal.getMovimentos());
        System.out.println("Movimentos Robo Inteligente: " + inteligente.getMovimentos());

        if (normal.getExplodiu() && inteligente.getExplodiu())
            System.out.println("Robo Normal explodiu.");
        else if (!normal.getExplodiu() && !inteligente.getExplodiu()) {
            System.out.println("Nenhum robô explodiu.");
        }
    }


    private static boolean turno(Robo robo1, Robo robo2, Comida comida, List<Obstaculo> obstaculos, Random rand) throws MovimentoInvalidoException {
        if(robo1.getExplodiu() && robo2.getExplodiu()){
            return false;
        }

        int dir = rand.nextInt(4) + 1;
        robo1.lastLinha = robo1.getPosicao()[0];
        robo1.lastColuna = robo1.getPosicao()[1];

        try {
            robo1.mover(dir);
            robo1.movimentos++;

            // Verifica obstáculos
            for (Obstaculo o : obstaculos) {
                if (o.getLinha() == robo1.getPosicao()[0] && o.getColuna() == robo1.getPosicao()[1]) {
                    o.bater(robo1, obstaculos);
                    break;
                }
            }

            // Verifica comida
            if (robo1.isComida(comida)) {
                System.out.println("Robo " + robo1.getCor() + " encontrou a comida!");
                return false;
            }

        } catch (MovimentoInvalidoException e) {
            System.out.println("Movimento inválido para o robô " + robo1.getCor() + ": " + e.getMessage());
        }

        return true;
    }
}