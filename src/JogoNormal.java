public class JogoNormal extends Modo {

    
    public void jogar(){
        Robo robo;
        Comida comida;
        int movs = 0;

        System.out.println("\n>>> MODO: Robo Manual");
        System.out.println("Digite a cor do robo:");
        System.out.println("1. " + Main.VERDE + "Verde"+ Main.RESET);
        System.out.println("2. " + Main.AMARELO + "Amarelo"+ Main.RESET);
        System.out.println("3. " + Main.VERMELHO + "Vermelho"+ Main.RESET);
        System.out.println("4. " + Main.ROSA + "Rosa"+ Main.RESET);
        System.out.println("5. " + Main.ROXO + "Roxo"+ Main.RESET);
        System.out.println("6. " + Main.AZUL + "Azul"+ Main.RESET);
        System.out.print("-> ");
        int esc = T.nextInt();
        String cor;
        switch (esc) {
            case 1: //verde
                cor = Main.VERDE;
                break;
            case 2: //amarelo
                cor = Main.AMARELO;
                break;
            case 3: //vermelho
                cor = Main.VERMELHO;
                break;
            case 4: //rosa
                cor = Main.ROSA;
                break;
            case 5: //roxo
                cor = Main.ROXO;
                break;
            case 6: //azul
                cor = Main.AZUL;
                break;
            default:
                System.out.println(Main.ERRO_COR + "Cor inválida. " + Main.RESET + "Tente novamente.");
                return;
        }
        robo = new Robo(cor);

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
        String direcao;
        int rodada = 1;
        while (true) { 
            System.out.println("\n***********");
            System.out.println("*Rodada " + rodada + "*");
            System.out.println("***********");
            rodada++;

            tabuleiro.mostrarTabuleiro(robo, comida.getPosicao());
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
                        System.out.println("Movimentos do " + robo.getCor() + "robô manual" + Main.RESET + ": " + robo.getMovimentos());
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
}
