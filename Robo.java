public class Robo {
    protected int linha;
    protected int coluna;
    protected String cor;
    protected int maxLinhas = 5;
    protected int maxColunas = 5;

    public Robo(String cor) {
        this.cor = cor;
        this.linha = 0;
        this.coluna = 0;
    }

    public boolean mover(String direcao) throws MovimentoInvalidoException {
    switch (direcao.toLowerCase()) {
        case "up":
            if (linha > 0) {
                linha--;
                return true;
            } else {
                throw new MovimentoInvalidoException("Movimento inválido: já está no topo.");
            }

        case "down":
            if (linha < maxLinhas - 1) {
                linha++;
                return true;
            } else {
                throw new MovimentoInvalidoException("Movimento inválido: já está embaixo.");
            }

        case "left":
            if (coluna > 0) {
                coluna--;
                return true;
            } else {
                throw new MovimentoInvalidoException("Movimento inválido: já está à esquerda.");
            }

        case "right":
            if (coluna < maxColunas - 1) {
                coluna++;
                return true;
            } else {
                throw new MovimentoInvalidoException("Movimento inválido: já está à direita.");
            }

        default:
            throw new MovimentoInvalidoException("Direção inválida: " + direcao);
    }
}
    public boolean mover(int direcao) throws MovimentoInvalidoException {
    switch (direcao) {
        case 1: // Cima
            if (linha > 0) {
                linha--;
                return true;
            } else {
                throw new MovimentoInvalidoException("Movimento inválido: já está no topo.");
            }

        case 2: // Baixo
            if (linha < maxLinhas - 1) {
                linha++;
                return true;
            } else {
                throw new MovimentoInvalidoException("Movimento inválido: já está embaixo.");
            }

        case 4: // Esquerda
            if (coluna > 0) {
                coluna--;
                return true;
            } else {
                throw new MovimentoInvalidoException("Movimento inválido: já está à esquerda.");
            }

        case 3: // Direita
            if (coluna < maxColunas - 1) {
                coluna++;
                return true;
            } else {
                throw new MovimentoInvalidoException("Movimento inválido: já está à direita.");
            }

        default:
            throw new MovimentoInvalidoException("Direção inválida: " + direcao + ". Não é um número válido.");
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
