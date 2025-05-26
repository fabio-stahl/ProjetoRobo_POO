import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class Jogo {
    private ArrayList<String> cores;
    private static final long DELAY_MS = 1000;
    public static final java.util.Scanner T = new java.util.Scanner(System.in);
    

    //cores
    public static final String RESET = "\u001B[0m";
    public static final String VERDE = "\u001B[32m";
    public static final String AMARELO = "\u001B[38;5;226m";
    public static final String AZUL = "\u001B[34m";
    public static final String ROXO = "\u001B[38;5;135m";
    public static final String VERMELHO = "\u001B[38;5;196m";
    public static final String ROSA = "\u001B[38;5;205m";

    public static final String ERRO_COR = "\u001B[38;5;202m";

    public Jogo() {
        this.cores = new ArrayList<>();
    }

    
    private boolean procurarPosObstaculo(int i, int j, List<Obstaculo> obstaculos){
        for(Obstaculo o : obstaculos){
            if(o instanceof Rocha && o.linha == i && o.coluna == j){
                System.out.print(" R ");
                return true;
            }else if(o instanceof Bomba && o.linha == i && o.coluna == j){
                Bomba bomba = (Bomba) o;
                if(bomba.getAtivada()){
                    System.out.print(" B ");
                    return true;
                }
            }
        }
        return false;
    }

    private void mostrarTabuleiro(Robo robo1, Robo robo2, int[] posicaoComida, List<Obstaculo> obstaculos){
        System.out.println(robo1.getCor() + "linha: " + robo1.getPosicao()[0] + " | coluna: " + robo1.getPosicao()[1] + RESET);
        System.out.println(robo2.getCor() + "linha: " + robo2.getPosicao()[0] + " | coluna: " + robo2.getPosicao()[1] + RESET);
        System.out.println(",---------------,");
        for(int i = 0; i < 4; i++){
            System.out.print("|");
            for(int j = 0; j < 4; j++){
                if(procurarPosObstaculo(i, j, obstaculos)){
                }else if(robo1.getPosicao()[0] == i && robo1.getPosicao()[1] == j && !robo1.getExplodiu() && robo2.getPosicao()[0] == i && robo2.getPosicao()[1] == j && !robo2.getExplodiu()){
                    System.out.print(robo1.getCor() + "O " + RESET + robo2.getCor() + "O" + RESET);
                }else if(robo1.getPosicao()[0] == i && robo1.getPosicao()[1] == j && !robo1.getExplodiu()){
                    System.out.print(robo1.getCor() + " O " + RESET);
                }else if(robo2.getPosicao()[0] == i && robo2.getPosicao()[1] == j && !robo2.getExplodiu()){
                    System.out.print(robo2.getCor() + " O " + RESET);
                }else if(posicaoComida[0] == i && posicaoComida[1] == j){
                    System.out.print(" C ");
                }else{
                    System.out.print("   ");
                }
                
                System.out.print("|");
            }
            System.out.println();
            if(i < 3){
                System.out.println("|---+---+---+---|");
            }
        }
        System.out.println("'---------------'");
    }
    
    private void mostrarTabuleiro(Robo robo1, Robo robo2, int[] posicaoComida){
        System.out.println(robo1.getCor() + "linha: " + robo1.getPosicao()[0] + " | coluna: " + robo1.getPosicao()[1] + RESET);
        System.out.println(robo2.getCor() + "linha: " + robo2.getPosicao()[0] + " | coluna: " + robo2.getPosicao()[1] + RESET);
        System.out.println(",---------------,");
        for(int i = 0; i < 4; i++){
            System.out.print("|");
            for(int j = 0; j < 4; j++){
                if(robo1.getPosicao()[0] == i && robo1.getPosicao()[1] == j && robo2.getPosicao()[0] == i && robo2.getPosicao()[1] == j){
                    System.out.print(robo1.getCor() + "O " + RESET + robo2.getCor() + "O" + RESET);
                }else if(robo1.getPosicao()[0] == i && robo1.getPosicao()[1] == j){
                    System.out.print(robo1.getCor() + " O " + RESET);
                }else if(robo2.getPosicao()[0] == i && robo2.getPosicao()[1] == j){
                    System.out.print(robo2.getCor() + " O " + RESET);
                }else if(posicaoComida[0] == i && posicaoComida[1] == j){
                    System.out.print(" C ");
                }else{
                    System.out.print("   ");
                }
                
                System.out.print("|");
            }
            System.out.println();
            if(i < 3){
                System.out.println("|---+---+---+---|");
            }
        }
        System.out.println("'---------------'");
    }

    private void mostrarTabuleiro(Robo robo, int[] posicaoComida){
        System.out.println(robo.getCor() + "linha: " + robo.getPosicao()[0] + " | coluna: " + robo.getPosicao()[1] + RESET);
        System.out.println(",---------------,");
        for(int i = 0; i < 4; i++){
            System.out.print("|");
            for(int j = 0; j < 4; j++){
                if(robo.getPosicao()[0] == i && robo.getPosicao()[1] == j){
                    System.out.print(robo.getCor() + " O " + RESET);
                }else if(posicaoComida[0] == i && posicaoComida[1] == j){
                    System.out.print(" C ");
                }else{
                    System.out.print("   ");
                }

                System.out.print("|");
            }
            System.out.println();
            if(i < 3){
                System.out.println("|---+---+---+---|");
            }
        }
        System.out.println("'---------------'");
    }

    public void jogarRoboNormal(){
        Robo robo;
        Comida comida;
        int movs = 0;

        System.out.println("\n>>> MODO: Robo Manual");
        System.out.println("Digite a cor do robo:");
        System.out.println("1. " + VERDE + "Verde"+ RESET);
        System.out.println("2. " + AMARELO + "Amarelo"+ RESET);
        System.out.println("3. " + VERMELHO + "Vermelho"+ RESET);
        System.out.println("4. " + ROSA + "Rosa"+ RESET);
        System.out.println("5. " + ROXO + "Roxo"+ RESET);
        System.out.println("6. " + AZUL + "Azul"+ RESET);
        System.out.print("-> ");
        int esc = T.nextInt();
        String cor;
        switch (esc) {
            case 1: //verde
                cor = VERDE;
                break;
            case 2: //amarelo
                cor = AMARELO;
                break;
            case 3: //vermelho
                cor = VERMELHO;
                break;
            case 4: //rosa
                cor = ROSA;
                break;
            case 5: //roxo
                cor = ROXO;
                break;
            case 6: //azul
                cor = AZUL;
                break;
            default:
                System.out.println(ERRO_COR + "Cor inválida. " + RESET + "Tente novamente.");
                return;
        }
        robo = new Robo(cor);

        System.out.println("Digite a linha e a coluna da comida:");
        System.out.print("-> linha: ");
        int linha = T.nextInt();
        System.out.print("-> coluna: ");
        int coluna = T.nextInt();

        if (linha < 0 || linha > 3 || coluna < 0 || coluna > 3 || linha == 0 && coluna == 0) {
            System.out.println(ERRO_COR + "Posição inválida da comida. " + RESET + "Tente novamente.");
            return;
        }

        comida = new Comida(linha, coluna);
        String direcao;
        int rodada = 1;
        while (true) { 
            System.out.println("\n***********");
            System.out.println("*Rodada " + rodada + "*");
            System.out.println("***********");
            rodada++;

            mostrarTabuleiro(robo, comida.getPosicao());
            System.out.println("Digite a direção (up, down, left, right) ou sair para sair)");
            System.out.print("-> ");
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
                        System.out.println("Movimentos do " + robo.getCor() + "robô manual" + RESET + ": " + robo.getMovimentos());
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

    private void delay() {
        try {
            Thread.sleep(DELAY_MS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void jogarRoboAutomatico() throws MovimentoInvalidoException {
        Robo robo1;
        Robo robo2;
        Comida comida;
        
        System.out.println("\n>>> MODO: Robô Automático (Robô Normal vs Robô Normal)");
        System.out.println("\nDigite a cor do robo 1:");
        System.out.println("1. " + VERDE + "Verde"+ RESET);
        System.out.println("2. " + AMARELO + "Amarelo"+ RESET);
        System.out.println("3. " + VERMELHO + "Vermelho"+ RESET);
        System.out.println("4. " + ROSA + "Rosa"+ RESET);
        System.out.println("5. " + ROXO + "Roxo"+ RESET);
        System.out.println("6. " + AZUL + "Azul"+ RESET);
        System.out.print("-> ");
        int esc1 = T.nextInt();
        String cor1;
        switch (esc1) {
            case 1:
                cor1 = VERDE;
                break;
            case 2:
                cor1 = AMARELO;
                break;
            case 3:
                cor1 = VERMELHO;
                break;
            case 4:
                cor1 = ROSA;
                break;
            case 5:
                cor1 = ROXO;
                break;
            case 6:
                cor1 = AZUL;
                break;
            default:
                System.out.println(ERRO_COR + "Cor inválida. " + RESET + "Tente novamente.");
                return;
        }
        robo1 = new Robo(cor1);

        System.out.println("\nDigite a cor do robo 2:");
        System.out.println("1. " + VERDE + "Verde"+ RESET);
        System.out.println("2. " + AMARELO + "Amarelo"+ RESET);
        System.out.println("3. " + VERMELHO + "Vermelho"+ RESET);
        System.out.println("4. " + ROSA + "Rosa"+ RESET);
        System.out.println("5. " + ROXO + "Roxo"+ RESET);
        System.out.println("6. " + AZUL + "Azul"+ RESET);
        System.out.print("-> ");
        int esc2 = T.nextInt();
        String cor2;
        switch (esc2) {
            case 1:
                cor2 = VERDE;
                break;
            case 2:
                cor2 = AMARELO;
                break;
            case 3:
                cor2 = VERMELHO;
                break;
            case 4:
                cor2 = ROSA;
                break;
            case 5:
                cor2 = ROXO;
                break;
            case 6:
                cor2 = AZUL;
                break;
            default:
                System.out.println(ERRO_COR + "Cor inválida. " + RESET + "Tente novamente.");
                return;
        }
        robo2 = new Robo(cor2);

        System.out.println("Digite a linha e a coluna da comida:");
        System.out.print("-> linha: ");
        int linha = T.nextInt();
        System.out.print("-> coluna: ");
        int coluna = T.nextInt();

        if (linha < 0 || linha > 3 || coluna < 0 || coluna > 3 || linha == 0 && coluna == 0) {
            System.out.println(ERRO_COR + "Posição inválida da comida. " + RESET + "Tente novamente.");
            return;
        }

        comida = new Comida(linha, coluna);
        Random rand = new Random();
        // Inicializa a posição dos robôs
        int rodada = 0;
        int movs1 = 0;
        int movs2 = 0;
        while (true) { 
            System.out.println("\n***********");
            System.out.println("*Rodada " + rodada + "*");
            System.out.println("***********");
            rodada++;
            if (rodada == 1) {
                System.out.println("Tabuleiro Inicial:");
                mostrarTabuleiro(robo1, robo2, comida.getPosicao());
                continue;
            }
            int direcao1 = rand.nextInt(4) + 1;
            int direcao2 = rand.nextInt(4) + 1;

            try {
                if (robo1.mover(direcao1)) {
                    movs1 += 1;
                    robo1.setMovimentos(movs1);
                    System.out.print(robo1.getCor() + "Robô normal 1 " + RESET + "se move para: ");
                    switch(direcao1){
                        case 1:
                            System.out.println("cima");
                            break;
                        case 2:
                            System.out.println("baixo");
                            break;
                        case 3:
                            System.out.println("direita");
                            break;
                        case 4:
                            System.out.println("esquerda");
                            break;
                        default:
                            break;
                    }
                }
            } catch (MovimentoInvalidoException e) {
                System.out.println("Erro no " + robo1.getCor() + "robô normal 1" + RESET + ": " + ERRO_COR + e.getMessage() + RESET);
            }
            mostrarTabuleiro(robo1, robo2, comida.getPosicao());
            delay();

            try {
                if (robo2.mover(direcao2)) {
                    movs2 += 1;
                    robo2.setMovimentos(movs2);
                    System.out.print(robo2.getCor() + "Robô normal 2 " + RESET + "se move para: ");
                    switch(direcao2){
                        case 1:
                            System.out.println("cima");
                            break;
                        case 2:
                            System.out.println("baixo");
                            break;
                        case 3:
                            System.out.println("direita");
                            break;
                        case 4:
                            System.out.println("esquerda");
                            break;
                        default:
                            break;
                    }
                }
            } catch (MovimentoInvalidoException e) {
                System.out.println("Erro no " + robo2.getCor() + "robô normal 2" + RESET + ": " + ERRO_COR + e.getMessage() + RESET);
            }
            mostrarTabuleiro(robo1, robo2, comida.getPosicao());
            delay();

            if (robo1.isComida(comida) || robo2.isComida(comida)) {
                if (robo1.isComida(comida)) {
                    System.out.println("Comida encontrada pelo " + robo1.getCor() + "robô normal 1!" + RESET);
                } else {
                    System.out.println("Comida encontrada pelo " + robo1.getCor() + "robô normal 2!" + RESET);
                }
                System.out.println("Movimentos do " + robo1.getCor() + "robô normal 1" + RESET + ": " + robo1.getMovimentos());
                System.out.println("Movimentos do " + robo2.getCor() + "robô normal 2" + RESET + ": " + robo2.getMovimentos());
                break;
            }
        }

    }

    public void jogarRoboxRoboInteligente() throws MovimentoInvalidoException{
        Robo robo1;
        Robo robo2;
        Comida comida;
        
        System.out.println("\n>>> MODO: Robô Automático (Robô Inteligente vs Robô Normal)");
        System.out.println("\nDigite a cor do robô inteligente:");
        System.out.println("1. " + VERDE + "Verde"+ RESET);
        System.out.println("2. " + AMARELO + "Amarelo"+ RESET);
        System.out.println("3. " + VERMELHO + "Vermelho"+ RESET);
        System.out.println("4. " + ROSA + "Rosa"+ RESET);
        System.out.println("5. " + ROXO + "Roxo"+ RESET);
        System.out.println("6. " + AZUL + "Azul"+ RESET);
        System.out.print("-> ");
        int esc1 = T.nextInt();
        String cor1;
        switch (esc1) {
            case 1:
                cor1 = VERDE;
                break;
            case 2:
                cor1 = AMARELO;
                break;
            case 3:
                cor1 = VERMELHO;
                break;
            case 4:
                cor1 = ROSA;
                break;
            case 5:
                cor1 = ROXO;
                break;
            case 6:
                cor1 = AZUL;
                break;
            default:
                System.out.println(ERRO_COR + "Cor inválida. " + RESET + "Tente novamente.");
                return;
        }
        robo1 = new RoboInteligente(cor1);

        System.out.println("\nDigite a cor do robô normal:");
        System.out.println("1. " + VERDE + "Verde"+ RESET);
        System.out.println("2. " + AMARELO + "Amarelo"+ RESET);
        System.out.println("3. " + VERMELHO + "Vermelho"+ RESET);
        System.out.println("4. " + ROSA + "Rosa"+ RESET);
        System.out.println("5. " + ROXO + "Roxo"+ RESET);
        System.out.println("6. " + AZUL + "Azul"+ RESET);
        System.out.print("-> ");
        int esc2 = T.nextInt();
        String cor2;
        switch (esc2) {
            case 1:
                cor2 = VERDE;
                break;
            case 2:
                cor2 = AMARELO;
                break;
            case 3:
                cor2 = VERMELHO;
                break;
            case 4:
                cor2 = ROSA;
                break;
            case 5:
                cor2 = ROXO;
                break;
            case 6:
                cor2 = AZUL;
                break;
            default:
                System.out.println(ERRO_COR + "Cor inválida. " + RESET + "Tente novamente.");
                return;
        }
        robo2 = new Robo(cor2);

        System.out.println("Digite a linha e a coluna da comida:");
        System.out.print("-> linha: ");
        int linha = T.nextInt();
        System.out.print("-> coluna: ");
        int coluna = T.nextInt();

        if (linha < 0 || linha > 3 || coluna < 0 || coluna > 3 || linha == 0 && coluna == 0) {
            System.out.println(ERRO_COR + "Posição inválida da comida. " + RESET + "Tente novamente.");
            return;
        }

        comida = new Comida(linha, coluna);
        Random rand = new Random();
        int rodada = 0;
        int movs1 = 0;
        int movs2 = 0;
        while (true) { 
            System.out.println("\n***********");
            System.out.println("*Rodada " + rodada + "*");
            System.out.println("***********");
            rodada++;
            if(rodada == 1){
                System.out.println("Tabuleiro Inicial:");
                mostrarTabuleiro(robo1, robo2, comida.getPosicao());
                continue;
            }
            int direcao1 = rand.nextInt(4) + 1;
            int direcao2 = rand.nextInt(4) + 1;

            try {
                if (robo1.mover(direcao1)) {
                    movs1 += 1;
                    robo1.setMovimentos(movs1);
                    
                    System.out.print(robo1.getCor() + "Robô inteligente " + RESET + "se move para: ");
                    switch(direcao1){
                        case 1:
                            System.out.println("cima");
                            break;
                        case 2:
                            System.out.println("baixo");
                            break;
                        case 3:
                            System.out.println("direita");
                            break;
                        case 4:
                            System.out.println("esquerda");
                            break;
                        default:
                            break;
                    }
                }
            } catch (MovimentoInvalidoException e) {
                System.out.println("Erro no " + robo1.getCor() + "robô inteligente" + RESET + ": " + ERRO_COR + e.getMessage() + RESET);
            }
            mostrarTabuleiro(robo1, robo2, comida.getPosicao());
            delay();

            try {
                if (robo2.mover(direcao2)) {
                    movs2 += 1;
                    robo2.setMovimentos(movs2);
                    
                    System.out.print(robo2.getCor() + "Robô normal " + RESET + "se move para: ");
                    switch(direcao2){
                        case 1:
                            System.out.println("cima");
                            break;
                        case 2:
                            System.out.println("baixo");
                            break;
                        case 3:
                            System.out.println("direita");
                            break;
                        case 4:
                            System.out.println("esquerda");
                            break;
                        default:
                            break;
                    }
                }
            } catch (MovimentoInvalidoException e) {
                System.out.println("Erro no " + robo2.getCor() + "robô normal" + RESET + ": " + ERRO_COR + e.getMessage() + RESET);
            }
            mostrarTabuleiro(robo1, robo2, comida.getPosicao());
            delay();

            if (robo1.isComida(comida) || robo2.isComida(comida)) {
                if (robo1.isComida(comida)) {
                    System.out.println("Comida encontrada pelo " + robo1.getCor() + "robô inteligente!" + RESET);
                } else {
                    System.out.println("Comida encontrada pelo " + robo2.getCor() + "robô normal!" + RESET);
                }
                System.out.println("Movimentos do " + robo1.getCor() + "robô inteligente" + RESET + ": " + robo1.getMovimentos());
                System.out.println("Movimentos do " + robo2.getCor() + "robô normal" + RESET + ": " + robo2.getMovimentos());
                break;
            }
        }

    }

    public void jogarComObstaculos() throws MovimentoInvalidoException {
        System.out.println("\n>>> MODO: Robo Normal vs Robo Inteligente (com Obstáculos)");
        System.out.println("\nDigite a cor do robô normal:");
        System.out.println("1. " + VERDE + "Verde"+ RESET);
        System.out.println("2. " + AMARELO + "Amarelo"+ RESET);
        System.out.println("3. " + VERMELHO + "Vermelho"+ RESET);
        System.out.println("4. " + ROSA + "Rosa"+ RESET);
        System.out.println("5. " + ROXO + "Roxo"+ RESET);
        System.out.println("6. " + AZUL + "Azul"+ RESET);
        System.out.print("-> ");
        int esc1 = T.nextInt();
        String cor1;
        switch (esc1) {
            case 1:
                cor1 = VERDE;
                break;
            case 2:
                cor1 = AMARELO;
                break;
            case 3:
                cor1 = VERMELHO;
                break;
            case 4:
                cor1 = ROSA;
                break;
            case 5:
                cor1 = ROXO;
                break;
            case 6:
                cor1 = AZUL;
                break;
            default:
                System.out.println(ERRO_COR + "Cor inválida. " + RESET + "Tente novamente.");
                return;
        }
        Robo normal = new Robo(cor1);
        
        System.out.println("\nDigite a cor do robô inteligente:");
        System.out.println("1. " + VERDE + "Verde"+ RESET);
        System.out.println("2. " + AMARELO + "Amarelo"+ RESET);
        System.out.println("3. " + VERMELHO + "Vermelho"+ RESET);
        System.out.println("4. " + ROSA + "Rosa"+ RESET);
        System.out.println("5. " + ROXO + "Roxo"+ RESET);
        System.out.println("6. " + AZUL + "Azul"+ RESET);
        System.out.print("-> ");
        int esc2 = T.nextInt();
        String cor2;
        switch (esc2) {
            case 1:
                cor2 = VERDE;
                break;
            case 2:
                cor2 = AMARELO;
                break;
            case 3:
                cor2 = VERMELHO;
                break;
            case 4:
                cor2 = ROSA;
                break;
            case 5:
                cor2 = ROXO;
                break;
            case 6:
                cor2 = AZUL;
                break;
            default:
                System.out.println(ERRO_COR + "Cor inválida. " + RESET + "Tente novamente.");
                return;
        }
        Robo inteligente = new RoboInteligente(cor2);

        System.out.println("\nDigite a linha e a coluna da comida: ");
        System.out.print("-> linha: ");
        int l = T.nextInt();
        System.out.print("-> coluna: ");
        int c = T.nextInt();
        if(l < 0 || l > 3 || c < 0 || c > 3 || l == 0 && c == 0){
            System.out.println(ERRO_COR + "Posição inválida da comida. " + RESET + "Tente novamente.");
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
                System.out.println(ERRO_COR + "Tipo inválido. " + RESET + "Tente novamente.");
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
                mostrarTabuleiro(normal, inteligente, comida.getPosicao(), obstaculos);
                continue;
            }

            System.out.println(">> Turno do " + normal.getCor() + "robô normal" + RESET + ":");
            if (!turno(normal, inteligente, comida, obstaculos, rand))
                break;

            mostrarTabuleiro(normal, inteligente, comida.getPosicao(), obstaculos);
            delay();

            System.out.println(">> Turno do " + inteligente.getCor() + "robô inteligente" + RESET + ":");
            if (!turno(inteligente, normal, comida, obstaculos, rand))
                break;

            mostrarTabuleiro(normal, inteligente, comida.getPosicao(), obstaculos);
            delay();

        }

        System.out.println("\n--- FIM DE JOGO ---");
        System.out.println("Movimentos do " + normal.getCor() + "robô normal" + RESET + ": " + normal.getMovimentos());
        System.out.println("Movimentos do " + inteligente.getCor() + "robô inteligente" + RESET + ": " + inteligente.getMovimentos());

        if (normal.getExplodiu() && inteligente.getExplodiu())
            System.out.println("Robôs explodiram.");
        else if (!normal.getExplodiu() && !inteligente.getExplodiu()) {
            System.out.println("Nenhum robô explodiu.");
        }
    }


    private boolean turno(Robo robo1, Robo robo2, Comida comida, List<Obstaculo> obstaculos, Random rand) throws MovimentoInvalidoException {
        if(robo1.getExplodiu() && robo2.getExplodiu()){
            return false;
        }else if(robo1.getExplodiu()){
            return true;
        }

        int dir = rand.nextInt(4) + 1;
        robo1.lastLinha = robo1.getPosicao()[0];
        robo1.lastColuna = robo1.getPosicao()[1];

        try {
            robo1.mover(dir);
            robo1.movimentos++;

            System.out.print(robo1.getCor() + "Robô " + RESET + "se move para: ");
            switch(dir){
                case 1:
                    System.out.println("cima");
                    break;
                case 2:
                    System.out.println("baixo");
                    break;
                case 3:
                    System.out.println("direita");
                    break;
                case 4:
                    System.out.println("esquerda");
                    break;
                default:
                    break;
            }

            // Verifica obstáculos
            for (Obstaculo o : obstaculos) {
                if (o.getLinha() == robo1.getPosicao()[0] && o.getColuna() == robo1.getPosicao()[1]) {
                    o.bater(robo1, obstaculos);
                    break;
                }
            }

            // Verifica comida
            if (robo1.isComida(comida)) {
                System.out.println(robo1.getCor() + "Robô" + RESET + " encontrou a comida!");
                return false;
            }
            

        } catch (MovimentoInvalidoException e) {
            System.out.println("Movimento inválido para o " + robo1.getCor() + "robô" + RESET + ": " + ERRO_COR + e.getMessage() + RESET);
        }

        return true;
    }

    public void setCores(String cor){
        cores.add(cor);
    }
}
