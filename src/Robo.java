public class Robo {
    protected int linha;
    protected int coluna;
    protected int lastLinha, lastColuna;
    protected String cor;
    protected int maxLinhas = 4;
    protected int maxColunas = 4;
    protected boolean explodiu;
    protected int movimentos;

    public Robo(String cor) {
        this.cor = cor;
        this.linha = 0;
        this.coluna = 0;
        this.lastColuna = 1;
        this.lastLinha = 1;
        this.explodiu = false;
        this.movimentos = 0;
    }

    public boolean mover(String direcao) throws MovimentoInvalidoException {
        lastLinha = linha;
        lastColuna = coluna;
        switch (direcao.toLowerCase()) {
            case "up" -> {
                if (coluna > 0) {
                    coluna--;
                    return true;
                } else {
                    throw new MovimentoInvalidoException("Movimento inválido: já está no topo.");
                }
            }

            case "down" -> {
                if (coluna < maxColunas - 1) {
                    coluna++;
                    return true;
                } else {
                    throw new MovimentoInvalidoException("Movimento inválido: já está embaixo.");
                }
            }

            case "left" -> {
                if (linha > 0) {
                    linha--;
                    return true;
                } else {
                    throw new MovimentoInvalidoException("Movimento inválido: já está à esquerda.");
                }
            }

            case "right" -> {
                if (linha < maxLinhas - 1) {
                    linha++;
                    return true;
                } else {
                    throw new MovimentoInvalidoException("Movimento inválido: já está à direita.");
                }
            }

            default -> throw new MovimentoInvalidoException("Direção inválida: " + direcao);
        }
    }
    public boolean mover(int direcao) throws MovimentoInvalidoException {
        lastLinha = linha;
        lastColuna = coluna;
        switch (direcao) {
            case 1 -> {
                // Cima
                if (coluna > 0) {
                    coluna--;
                    return true;
                } else {
                    throw new MovimentoInvalidoException("Movimento inválido: já está no topo.");
                }
            }

            case 2 -> {
                // Baixo
                if (coluna < maxColunas - 1) {
                    coluna++;
                    return true;
                } else {
                    throw new MovimentoInvalidoException("Movimento inválido: já está embaixo.");
                }
            }

            case 4 -> {
                // Esquerda
                if (linha > 0) {
                    linha--;
                    return true;
                } else {
                    throw new MovimentoInvalidoException("Movimento inválido: já está à esquerda.");
                }
            }

            case 3 -> {
                // Direita
                if (linha < maxLinhas - 1) {
                    linha++;
                    return true;
                } else {
                    throw new MovimentoInvalidoException("Movimento inválido: já está à direita.");
                }
            }

            default -> throw new MovimentoInvalidoException("Direção inválida: " + direcao + ". Não é um número válido.");
        }
    }

    public boolean isComida (Comida comida) { 
        return comida.getPosicao()[0] == linha && comida.getPosicao()[1] == coluna;
    }

    public void reverter() {
        linha = lastLinha;
        coluna = lastColuna;
    }

    public int[] getPosicao() {
        return new int[]{linha, coluna};
    }

    public String getCor () {
        return cor;
    }

    public boolean isExplodiu() {
        return explodiu;
    }

    public void setExplodiu(boolean explodiu) {
        this.explodiu = explodiu;
    }

    public int getMovimentos() {
        return movimentos;
    }

}
