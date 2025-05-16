public class Comida {
    private int linha;
    private int coluna;


    public Comida(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
    }
    public int[] getPosicao() {
        return new int []{linha, coluna};
    }
}
