import java.util.*;
public abstract class Obstaculo {
    protected String id;
    protected int linha;
    protected int coluna;
    protected String RESET = "\u001B[0m";

    public Obstaculo(String id, int linha, int coluna) {
        this.id = id;
        this.linha = linha;
        this.coluna = coluna;
    }

    public String getId() {
        return id;
    }

    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }

    public abstract void bater(Robo robo, List<Obstaculo> lista);
}