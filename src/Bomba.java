import java.util.*;
class Bomba extends Obstaculo {
    private boolean ativada = true;
    
    public Bomba(String id, int linha, int coluna) {
        super(id, linha, coluna);
    }

    @Override
    public void bater(Robo robo, List<Obstaculo> lista) {
        if (!ativada) return;
        
        System.out.println(robo.getCor() + "Robô" + "\u001B[0m" + " explodiu na bomba " + id + "!");
        robo.setExplodiu(true);
        lista.remove(this);

        ativada = false;
    }

    public boolean getAtivada(){
        return ativada;
    }
}
