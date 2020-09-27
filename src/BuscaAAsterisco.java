import Labirinto.*;
import java.util.Iterator;
import java.util.Vector;

public class BuscaAAsterisco extends Busca {
  void prepararBusca() {
    Posicao posicaoInicial = this.labirinto.getPosicaoEntrada();
    this.caminho.add(posicaoInicial);
  }

  void buscar() {
    Posicao ultimaPosicao = this.caminho.lastElement();
    Vector<Posicao> expansao = labirinto.getExpansao(ultimaPosicao);

    Iterator<Posicao> expansaoIterator = expansao.iterator();

    while (expansaoIterator.hasNext()) {
      Posicao expansaoItem = (Posicao) expansaoIterator.next();
      System.out.println("expansao: " + labirinto.getDLR(expansaoItem, labirinto.getPosicaoSaida()));
    }

    this.achouOFinal = true;
  }

	public static void main(String[] args) {
    new BuscaAAsterisco();
	}
}
