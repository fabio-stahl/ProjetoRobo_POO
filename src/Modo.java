import java.util.List;
import java.util.Random;
public abstract class Modo {
    Tabuleiro tabuleiro = new Tabuleiro();
    protected static final long DELAY_MS = 1000;
    public final java.util.Scanner T = new java.util.Scanner(System.in);
    
    
    
    public abstract void jogar() throws MovimentoInvalidoException;


    protected void delay() {
        try {
            Thread.sleep(DELAY_MS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    protected boolean turno(Robo robo1, Robo robo2, Comida comida, List<Obstaculo> obstaculos, Random rand) throws MovimentoInvalidoException {
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

            System.out.print(robo1.getCor() + "Robô " + Main.RESET + "se move para: ");
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
                    if(o instanceof Rocha && robo1 instanceof RoboInteligente){
                        RoboInteligente roboIntel = (RoboInteligente) robo1;
                        roboIntel.adicionarPosInvalida(new int[] {robo1.getPosicao()[0], robo1.getPosicao()[1]});
                    }

                    o.bater(robo1, obstaculos);

                    break;
                }
            }

            // Verifica comida
            if (robo1.isComida(comida)) {
                System.out.println(robo1.getCor() + "Robô" + Main.RESET + " encontrou a comida!");
                return false;
            }
            

        } catch (MovimentoInvalidoException e) {
            System.out.println("Movimento inválido para o " + robo1.getCor() + "robô" + Main.RESET + ": " + Main.ERRO_COR + e.getMessage() + Main.RESET);
        }

        return true;
    }

}
