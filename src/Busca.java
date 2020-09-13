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
    Iterator<Posicao> expansaoIt = expansao.iterator();

		while (expansaoIt.hasNext()) {
      Posicao p = (Posicao) expansaoIt.next();
      boolean vizinho = p.comparaCom(posicaoFinal);

      if (vizinho) return true;
    }

    return false;
  }
}
