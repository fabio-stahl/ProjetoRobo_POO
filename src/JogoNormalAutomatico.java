import java.util.Random;

public class JogoNormalAutomatico extends Modo {
    public void jogar() throws MovimentoInvalidoException {
        Robo robo1;
        Robo robo2;
        Comida comida;
        
        System.out.println("\n>>> MODO: Robô Automático (Robô Normal vs Robô Normal)");
        System.out.println("\nDigite a cor do robo 1:");
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
        robo1 = new Robo(cor1);

        System.out.println("\nDigite a cor do robo 2:");
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
        robo2 = new Robo(cor2);

        System.out.println("Digite a linha e a coluna da comida:");
        System.out.print("-> linha: ");
        int linha = T.nextInt();
        System.out.print("-> coluna: ");
        int coluna = T.nextInt();

        if (linha < 0 || linha > 3 || coluna < 0 || coluna > 3 || linha == 0 && coluna == 0) {
            System.out.println(Main.ERRO_COR + "Posição inválida da comida. " + Main.RESET + "Tente novamente.");
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
                tabuleiro.mostrarTabuleiro(robo1, robo2, comida.getPosicao());
                continue;
            }
            int direcao1 = rand.nextInt(4) + 1;
            int direcao2 = rand.nextInt(4) + 1;

            try {
                if (robo1.mover(direcao1)) {
                    movs1 += 1;
                    robo1.setMovimentos(movs1);
                    System.out.print(robo1.getCor() + "Robô normal 1 " + Main.RESET + "se move para: ");
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
                System.out.println("Erro no " + robo1.getCor() + "robô normal 1" + Main.RESET + ": " + Main.ERRO_COR + e.getMessage() + Main.RESET);
            }
            tabuleiro.mostrarTabuleiro(robo1, robo2, comida.getPosicao());
            delay();

            try {
                if (robo2.mover(direcao2)) {
                    movs2 += 1;
                    robo2.setMovimentos(movs2);
                    System.out.print(robo2.getCor() + "Robô normal 2 " + Main.RESET + "se move para: ");
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
                System.out.println("Erro no " + robo2.getCor() + "robô normal 2" + Main.RESET + ": " + Main.ERRO_COR + e.getMessage() + Main.RESET);
            }
            tabuleiro.mostrarTabuleiro(robo1, robo2, comida.getPosicao());
            delay();

            if (robo1.isComida(comida) || robo2.isComida(comida)) {
                if (robo1.isComida(comida)) {
                    System.out.println("Comida encontrada pelo " + robo1.getCor() + "robô normal 1!" + Main.RESET);
                } else {
                    System.out.println("Comida encontrada pelo " + robo1.getCor() + "robô normal 2!" + Main.RESET);
                }
                System.out.println("Movimentos do " + robo1.getCor() + "robô normal 1" + Main.RESET + ": " + robo1.getMovimentos());
                System.out.println("Movimentos do " + robo2.getCor() + "robô normal 2" + Main.RESET + ": " + robo2.getMovimentos());
                break;
            }
        }

    }
}
