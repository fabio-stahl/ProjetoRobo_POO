import java.util.List;

public class Tabuleiro {


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

    public void mostrarTabuleiro(Robo robo1, Robo robo2, int[] posicaoComida, List<Obstaculo> obstaculos){
        System.out.println(robo1.getCor() + "linha: " + robo1.getPosicao()[0] + " | coluna: " + robo1.getPosicao()[1] + Main.RESET);
        System.out.println(robo2.getCor() + "linha: " + robo2.getPosicao()[0] + " | coluna: " + robo2.getPosicao()[1] + Main.RESET);
        System.out.println(",---------------,");
        for(int i = 0; i < 4; i++){
            System.out.print("|");
            for(int j = 0; j < 4; j++){
                if(procurarPosObstaculo(i, j, obstaculos)){
                }else if(robo1.getPosicao()[0] == i && robo1.getPosicao()[1] == j && !robo1.getExplodiu() && robo2.getPosicao()[0] == i && robo2.getPosicao()[1] == j && !robo2.getExplodiu()){
                    System.out.print(robo1.getCor() + "O " + Main.RESET + robo2.getCor() + "O" + Main.RESET);
                }else if(robo1.getPosicao()[0] == i && robo1.getPosicao()[1] == j && !robo1.getExplodiu()){
                    System.out.print(robo1.getCor() + " O " + Main.RESET);
                }else if(robo2.getPosicao()[0] == i && robo2.getPosicao()[1] == j && !robo2.getExplodiu()){
                    System.out.print(robo2.getCor() + " O " + Main.RESET);
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
    
    public void mostrarTabuleiro(Robo robo1, Robo robo2, int[] posicaoComida){
        System.out.println(robo1.getCor() + "linha: " + robo1.getPosicao()[0] + " | coluna: " + robo1.getPosicao()[1] + Main.RESET);
        System.out.println(robo2.getCor() + "linha: " + robo2.getPosicao()[0] + " | coluna: " + robo2.getPosicao()[1] + Main.RESET);
        System.out.println(",---------------,");
        for(int i = 0; i < 4; i++){
            System.out.print("|");
            for(int j = 0; j < 4; j++){
                if(robo1.getPosicao()[0] == i && robo1.getPosicao()[1] == j && robo2.getPosicao()[0] == i && robo2.getPosicao()[1] == j){
                    System.out.print(robo1.getCor() + "O " + Main.RESET + robo2.getCor() + "O" + Main.RESET);
                }else if(robo1.getPosicao()[0] == i && robo1.getPosicao()[1] == j){
                    System.out.print(robo1.getCor() + " O " + Main.RESET);
                }else if(robo2.getPosicao()[0] == i && robo2.getPosicao()[1] == j){
                    System.out.print(robo2.getCor() + " O " + Main.RESET);
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

    public void mostrarTabuleiro(Robo robo, int[] posicaoComida){
        System.out.println(robo.getCor() + "linha: " + robo.getPosicao()[0] + " | coluna: " + robo.getPosicao()[1] + Main.RESET);
        System.out.println(",---------------,");
        for(int i = 0; i < 4; i++){
            System.out.print("|");
            for(int j = 0; j < 4; j++){
                if(robo.getPosicao()[0] == i && robo.getPosicao()[1] == j){
                    System.out.print(robo.getCor() + " O " + Main.RESET);
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
}
