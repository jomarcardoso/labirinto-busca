import java.util.Iterator;
import java.util.Vector;
import Labirinto.*;

public class Busca {
  public boolean achouOFinal = false;

  Labirinto criarLabirinto() {
		boolean debug = false;
		Labirinto labirinto  = new Labirinto(10, 10, 30, debug);

    labirinto.print(null);
    this.imprimirPosicaoInicial(labirinto);
    this.imprimirPosicaoSaida(labirinto);

    return labirinto;
  }

  void imprimirPosicaoInicial(Labirinto labirinto) {
    System.out.println("Posicao entrada: " + labirinto.getPosicaoEntrada());
  }

  void imprimirPosicaoSaida(Labirinto labirinto) {
    System.out.println("Posicao saida: " + labirinto.getPosicaoSaida());
  }

  boolean ehVizinhoDoFinal(Vector<Posicao> expansao, Posicao posicaoFinal) {
    Iterator<Posicao> expansaoIterator = expansao.iterator();

		while (expansaoIterator.hasNext()) {
      Posicao expansaoItem = (Posicao) expansaoIterator.next();
      boolean vizinho = expansaoItem.comparaCom(posicaoFinal);

      if (vizinho) return true;
    }

    return false;
  }

  Vector<Posicao> concatenaPosicoes(Vector<Posicao> Va, Vector<Posicao> Vb) {
    Vector<Posicao> merge = new Vector<Posicao>();
    merge.addAll(Va);
    merge.addAll(Vb);

    return merge;
  }

  Vector<Posicao> adicionarPosicao(Vector<Posicao> Va, Posicao Vb) {
    Vector<Posicao> merge = new Vector<Posicao>();
    merge.addAll(Va);
    merge.addElement(Vb);

    return merge;
  }

  Vector<Vector<Posicao>> gerarExtensoes(Vector<Posicao> posicoesPercorridas, Vector<Posicao> expansao, Vector<Posicao> posicoesPassadas) {
    Iterator<Posicao> expansaoIterator = expansao.iterator();
    Vector<Vector<Posicao>> extensoes = new Vector<Vector<Posicao>>();

		while (expansaoIterator.hasNext()) {
      Posicao expansaoItem = (Posicao) expansaoIterator.next();

      if (!posicoesPassadas.contains(expansaoItem)) {
        Vector<Posicao> extensao = new Vector<Posicao>();
        extensao = adicionarPosicao(posicoesPercorridas, expansaoItem);
        extensoes.addElement(extensao);
      }
    }

    return extensoes;
  }
}
