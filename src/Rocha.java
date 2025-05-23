import java.util.*;
class Rocha extends Obstaculo {
    public Rocha(String id, int linha, int coluna) {
        super(id, linha, coluna);
    }

    @Override
    public void bater(Robo robo, List<Obstaculo> lista) {
        System.out.println(robo.getCor() + "Robô " + RESET + "bateu na rocha " + id + " e voltou à posição anterior.");
        robo.reverter();
    }
}