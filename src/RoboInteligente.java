    import java.util.ArrayList;
    import java.util.*;

    public class RoboInteligente extends Robo{
        private ArrayList<int[]> posicoesInvalidas = new ArrayList<>();
        private Random rand = new Random();

        public RoboInteligente(String cor) {
            super(cor);
        }

        public boolean verificarPosicao(int linha, int coluna){
            for(int i = 0; i < posicoesInvalidas.size(); i++){
                if(posicoesInvalidas.get(i)[0] == linha && posicoesInvalidas.get(i)[1] == coluna)
                    return false;
            }
            return true;
        }

        public void adicionarPosInvalida(int[] pos){
            for(int[] p : posicoesInvalidas){
                if(p[0] == pos[0] && p[1] == pos[1]) return;
            }
            posicoesInvalidas.add(pos);
        }

        @Override
        public boolean mover(int direcao) throws MovimentoInvalidoException {
            lastLinha = linha;
            lastColuna = coluna;
            while(true){
                switch (direcao) {
                    case 1:
                        // Cima
                        if(!verificarPosicao((linha - 1), coluna)){
                            direcao = rand.nextInt(4) + 1;
                            System.out.println("REAVALIANDO DIREÇÃO. (" + (linha - 1) + ", " + coluna + ") inválido");
                            continue;
                        }
                        if (linha > 0) {
                            linha--;
                            return true;
                        } else {
                            int[] coordenadas = {(linha - 1), coluna};
                            posicoesInvalidas.add(coordenadas);
                            throw new MovimentoInvalidoException("Movimento inválido: já está no topo.");
                        }

                    case 2:
                        // Baixo
                        if(!verificarPosicao((linha + 1), coluna)){
                            direcao = rand.nextInt(4) + 1;
                            System.out.println("REAVALIANDO DIREÇÃO. (" + (linha + 1) + ", " + coluna + ") inválido");
                            continue;
                        }
                        if (linha < maxLinhas - 1) {
                            linha++;
                            return true;
                        } else {
                            int[] coordenadas = {(linha + 1), coluna};
                            posicoesInvalidas.add(coordenadas);
                            throw new MovimentoInvalidoException("Movimento inválido: já está embaixo.");
                        }

                    case 3:
                        // Direita
                        if(!verificarPosicao(linha, (coluna + 1))){
                            direcao = rand.nextInt(4) + 1;
                            System.out.println("REAVALIANDO DIREÇÃO. (" + linha + ", " + (coluna + 1) + ") inválido");
                            continue;
                        }
                        if (coluna < maxColunas - 1) {
                            coluna++;
                            return true;
                        } else {
                            int[] coordenadas = {linha, (coluna + 1)};
                            posicoesInvalidas.add(coordenadas);
                            throw new MovimentoInvalidoException("Movimento inválido: já está à direita.");
                        }

                    case 4:
                        // Esquerda
                        if(!verificarPosicao(linha, (coluna - 1))){
                            direcao = rand.nextInt(4) + 1;
                            System.out.println("REAVALIANDO DIREÇÃO. (" + linha + ", " + (coluna - 1) + ") inválido");
                            continue;
                        }
                        if (coluna > 0) {
                            coluna--;
                            return true;
                        } else {
                            int[] coordenadas = {linha, (coluna - 1)};
                            posicoesInvalidas.add(coordenadas);
                            throw new MovimentoInvalidoException("Movimento inválido: já está à esquerda.");
                        }

                    default:
                        throw new MovimentoInvalidoException("Direção inválida: " + direcao + ". Não é um número válido.");
                }
            }
        }
    }
