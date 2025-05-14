public class Robo {
    private int linha;
    private int coluna;
    private String cor;
    private int maxLinhas = 5;
    private int maxColunas = 5;

    public Robo(String cor) {
        this.cor = cor;
        this.linha = 0;
        this.coluna = 0;
    }

    public void mover(String direcao) throws MovimentoInvalidoException {
        switch (direcao.toLowerCase()) {
            case "up":
                if (linha > 0) {
                    linha--;
                } else {
                    throw new MovimentoInvalidoException("Movimento inválido: já está no topo.");
                }
                break;
            case "down":
                if (linha < maxLinhas - 1) {
                    linha++;
                } else {
                    throw new MovimentoInvalidoException("Movimento inválido: já está embaixo.");
                }
                break;
            case "left":
                if (coluna > 0) {
                    coluna--;
                } else {
                    throw new MovimentoInvalidoException("Movimento inválido: já está à esquerda.");
                }
                break;
            case "right":
                if (coluna < maxColunas - 1) {
                    coluna++;
                } else {
                    throw new MovimentoInvalidoException("Movimento inválido: já está à direita.");
                }
                break;
            default:
                throw new MovimentoInvalidoException("Direção inválida: " + direcao);
        }
    }

    public boolean isComida (Comida comida) { 
        if (comida.getPosicao()[0] == linha && comida.getPosicao()[1] == coluna) {
            return true;
        }
        return false;
    }

    public int[] getPosicao() {
        return new int[]{linha, coluna};
    }

    public String getCor () {
        return cor;
    }

}
