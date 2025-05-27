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
        this.lastColuna = 0;
        this.lastLinha = 0;
        this.explodiu = false;
        this.movimentos = 0;
    }

    public boolean mover(String direcao) throws MovimentoInvalidoException {
        lastLinha = linha;
        lastColuna = coluna;
        switch (direcao.toLowerCase()) {
            case "up" -> {
                if (linha > 0) {
                    linha--;
                    return true;
                } else {
                    throw new MovimentoInvalidoException("Movimento inválido: já está no topo.");
                }
            }

            case "down" -> {
                if (linha < maxLinhas - 1) {
                    linha++;
                    return true;
                } else {
                    throw new MovimentoInvalidoException("Movimento inválido: já está embaixo.");
                }
            }

            case "left" -> {
                if (coluna > 0) {
                    coluna--;
                    return true;
                } else {
                    throw new MovimentoInvalidoException("Movimento inválido: já está à esquerda.");
                }
            }

            case "right" -> {
                if (coluna < maxLinhas - 1) {
                    coluna++;
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
                if (linha > 0) {
                    linha--;
                    return true;
                } else {
                    throw new MovimentoInvalidoException("Movimento inválido: já está no topo.");
                }
            }

            case 2 -> {
                // Baixo
                if (linha < maxLinhas - 1) {
                    linha++;
                    return true;
                } else {
                    throw new MovimentoInvalidoException("Movimento inválido: já está embaixo.");
                }
            }
            
            case 3 -> {
                // Direita
                if (coluna < maxColunas - 1) {
                    coluna++;
                    return true;
                } else {
                    throw new MovimentoInvalidoException("Movimento inválido: já está à direita.");
                }
            }

            case 4 -> {
                // Esquerda
                if (coluna > 0) {
                    coluna--;
                    return true;
                } else {
                    throw new MovimentoInvalidoException("Movimento inválido: já está à esquerda.");
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

    public boolean getExplodiu() {
        return explodiu;
    }

    public void setExplodiu(boolean explodiu) {
        this.explodiu = explodiu;
    }

    public int getMovimentos() {
        return movimentos;
    }
    public void setMovimentos(int movs){
        this.movimentos = movs;
    }

}
