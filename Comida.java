public class Comida {
    private int linha;
    private int coluna;


    public Comida(int coluna, int linha) {
        this.coluna = coluna;
        this.linha = linha;
    }
    public int[] getPosicao() {
        return new int []{linha, coluna};
    }
}
