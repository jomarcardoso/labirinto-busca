import java.util.Iterator;
import java.util.Vector;
import Labirinto.*;

public class Busca {
  static Labirinto criarLabirinto() {
		boolean debug = false;
		Labirinto labirinto  = new Labirinto(10, 10, 30, debug);

    labirinto.print(null);
    Busca.imprimirPosicaoInicial(labirinto);
    Busca.imprimirPosicaoSaida(labirinto);

    return labirinto;
  }

  static void imprimirPosicaoInicial(Labirinto labirinto) {
    System.out.println("Posicao entrada: " + labirinto.getPosicaoEntrada());
  }

  static void imprimirPosicaoSaida(Labirinto labirinto) {
    System.out.println("Posicao saida: " + labirinto.getPosicaoSaida());
  }

  static boolean ehVizinhoDoFinal(Vector<Posicao> expansao, Posicao posicaoFinal) {
    Iterator<Posicao> expansaoIterator = expansao.iterator();

		while (expansaoIterator.hasNext()) {
      Posicao expansaoItem = (Posicao) expansaoIterator.next();
      boolean vizinho = expansaoItem.comparaCom(posicaoFinal);

      if (vizinho) return true;
    }

    return false;
  }

  static Vector<Posicao> concatenaPosicoes(Vector<Posicao> Va, Vector<Posicao> Vb) {
    Vector<Posicao> merge = new Vector<Posicao>();
    merge.addAll(Va);
    merge.addAll(Vb);

    return merge;
  }

  static Vector<Posicao> concatenaPosicao(Vector<Posicao> Va, Posicao Vb) {
    Vector<Posicao> merge = new Vector<Posicao>();
    merge.addAll(Va);
    merge.addElement(Vb);

    return merge;
  }

  static Vector<Vector<Posicao>> gerarExtensoes(Vector<Posicao> posicoesPercorridas, Vector<Posicao> expansao) {
    Iterator<Posicao> expansaoIterator = expansao.iterator();
    Vector<Vector<Posicao>> extensoes = new Vector<Vector<Posicao>>();

		while (expansaoIterator.hasNext()) {
      Vector<Posicao> extensao = new Vector<Posicao>();
      Posicao expansaoItem = (Posicao) expansaoIterator.next();
      extensao = concatenaPosicao(posicoesPercorridas, expansaoItem);
      extensoes.addElement(extensao);
    }

    return extensoes;
  }
}
