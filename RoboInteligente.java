public class RoboInteligente extends Robo{
    private int movimentoAnterior;

    public RoboInteligente(String cor) {
        super(cor);
        this.movimentoAnterior = 0;
    }
    public boolean mover(int direcao) throws MovimentoInvalidoException {
        if (direcao == movimentoAnterior) {
            throw new MovimentoInvalidoException("Movimento inválido: já se moveu nessa direção.");
        }
        movimentoAnterior = direcao;
        return super.mover(direcao);
    }
}
