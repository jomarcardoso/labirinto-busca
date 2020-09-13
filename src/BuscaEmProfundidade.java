import java.util.Iterator;
import java.util.Vector;
import Labirinto.*;

public class BuscaEmProfundidade {
	public static void main(String[] args) {
    Labirinto labirinto = Busca.criarLabirinto();
    boolean vizinhoDoFinal = Busca.ehVizinhoDoFinal(labirinto.getExpansao(labirinto.getPosicaoEntrada()), labirinto.getPosicaoSaida());

    System.out.print("eh vizinho da saida: " + vizinhoDoFinal);
	}
}
