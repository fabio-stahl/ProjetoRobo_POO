import java.util.*;

public class Main{
    public static Scanner T = new Scanner(System.in);
    public static void main(String[] args) throws MovimentoInvalidoException {
        while (true) {
            System.out.println("\nEscolha o modo de jogo:");
            System.out.println("1 - Robo Normal (manual)");
            System.out.println("2 - Robo Automático (aleatório x aleatório)");
            System.out.println("3 - Robo Inteligente vs Robo Normal (teste)");
            System.out.println("4 - Robo Normal vs Robo Inteligente com Obstáculos");
            System.out.println("5 - Sair");
            int opc = T.nextInt();

            switch (opc) {
                case 1 -> jogarRoboNormal();
                case 2 -> jogarRoboAutomatico();
                case 3 -> jogarRoboxRoboInteligente();
                case 4 -> jogarComObstaculos();
                case 5 -> {
                    System.out.println("Saindo do jogo.");
                    return;
                }
                default -> System.out.println("Opção inválida.");
            }
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
    private static void jogarRoboAutomatico () {
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
            System.out.println("\n***************");
            System.out.println("*Rodada " + rodada + "*");
            System.out.println("***************");
            rodada++;
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
                    System.out.println("Movimentos do robô " + robo1.getCor() + ": " + robo1.getMovimentos());
                } else {
                    System.out.println("Comida encontrada pelo robô 2!");
                    System.out.println("Movimentos do robô " + robo2.getCor() + ": " + robo2.getMovimentos());
                }
                break;
            }
        }

    }

    private static void jogarRoboxRoboInteligente() {
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
            System.out.println("\n***************");
            System.out.println("*Rodada " + rodada + "*");
            System.out.println("***************");
            rodada++;
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
                    System.out.println("Movimentos do robô " + robo1.getCor() + ": " + robo1.getMovimentos());
                } else {
                    System.out.println("Comida encontrada pelo robô 2!");
                    System.out.println("Movimentos do robô " + robo2.getCor() + ": " + robo2.getMovimentos());
                }
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
            System.out.print("Tipo (1=Bomba, 2=Rocha), linha e coluna: ");
            int tipo = T.nextInt(), ol = T.nextInt(), oc = T.nextInt();
            String id = "O" + (i+1);
            if (tipo == 1) obstaculos.add(new Bomba(id, ol, oc));
            else              obstaculos.add(new Rocha(id, ol, oc));
        }

        Robo normal = new Robo("Normal");
        Robo inteligente = new RoboInteligente("Inteligente");
        Random rand = new Random();

        while (true) {
            if (!turno(normal, comida, obstaculos, rand)){
                break;
            }
            if (!turno(inteligente, comida, obstaculos, rand)){
                break;
            }
        }

        System.out.println("\n--- FIM DE JOGO ---");
        System.out.println("Movimentos Robo Normal: " + normal.getMovimentos());
        System.out.println("Movimentos Robo Inteligente: " + inteligente.getMovimentos());
        if (normal.isExplodiu()) System.out.println("Robo Normal explodiu.");
        if (inteligente.isExplodiu()) System.out.println("Robo Inteligente explodiu.");
        if (!normal.isExplodiu() && !inteligente.isExplodiu()) {
            System.out.println("Nenhum robô explodiu.");
        }
    }

    private static boolean turno(Robo robo, Comida comida, List<Obstaculo> obstaculos, Random rand) throws MovimentoInvalidoException {
        if (robo.isExplodiu()) return false;
        int dir = rand.nextInt(4) + 1;
        try {
            robo.mover(dir);
            // checa obstáculos
            Iterator<Obstaculo> it = obstaculos.iterator();
            while (it.hasNext()) {
                Obstaculo o = it.next();
                if (o.getLinha() == robo.getPosicao()[0] && o.getColuna() == robo.getPosicao()[1]) {
                    o.bater(robo, obstaculos);
                    break;
                }
            }
            if (robo.isComida(comida)) {
                System.out.println("Robo " + robo.getCor() + " encontrou a comida!");
                return false;
            }
        } catch (MovimentoInvalidoException e) {
            //Só ignora e passa a vez (Fazer um tratamento depois se necessário)
        }
        return true;
    }
    
}
