import java.util.Iterator;
import java.util.Vector;
import Labirinto.*;

public class BuscaEmProfundidade {
  static Vector<Posicao> buscar(Labirinto labirinto) {
    Vector<Vector<Posicao>> percorridos = new Vector<Vector<Posicao>>();
    Vector<Posicao> primeiroPercorrido = new Vector<Posicao>();
    Posicao posicao = labirinto.getPosicaoEntrada();
    primeiroPercorrido.add(posicao);
    percorridos.add(primeiroPercorrido);

    boolean vizinhoDoFinal = Busca.ehVizinhoDoFinal(labirinto.getExpansao(labirinto.getPosicaoEntrada()), labirinto.getPosicaoSaida());

    if (vizinhoDoFinal) {
      primeiroPercorrido.add(labirinto.getPosicaoSaida());

      return primeiroPercorrido;
    }

    Iterator<Vector<Posicao>> percorridosIterator = percorridos.iterator();

    while (percorridosIterator.hasNext()) {
      Vector<Posicao> percorrido = percorridosIterator.next();
      Vector<Posicao> expansao = labirinto.getExpansao(percorrido.lastElement());
      percorridos.remove(percorrido);

      Vector<Vector<Posicao>> extensoes = Busca.gerarExtensoes(percorrido, expansao);
      Iterator<Vector<Posicao>> extensoesIterator = extensoes.iterator();

      while (extensoesIterator.hasNext()) {
        Vector<Posicao> extensao = extensoesIterator.next();
        percorridos.add(extensao);
      }

      System.out.print("\nexpansaoItem: " + percorridos);
    }

    return primeiroPercorrido;
  }

	public static void main(String[] args) {
    Labirinto labirinto = Busca.criarLabirinto();
    Vector<Posicao> caminho = BuscaEmProfundidade.buscar(labirinto);

    System.out.print("\ncaminho: " + caminho);
	}
}
