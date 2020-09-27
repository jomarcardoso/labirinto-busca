import java.util.Iterator;
import java.util.ListIterator;
import java.util.Vector;
import Labirinto.*;

public class BuscaEmLargura extends Busca {
  void prepararBusca() {
    Vector<Posicao> primeiroPercorrido = new Vector<Posicao>();
    Posicao posicao = this.labirinto.getPosicaoEntrada();
    primeiroPercorrido.add(posicao);
    this.caminhos.add(primeiroPercorrido);
  }

  void buscar() {
    ListIterator<Vector<Posicao>> caminhosIterator = this.caminhos.listIterator();

    while (caminhosIterator.hasNext()) {
      Vector<Posicao> percorrido = caminhosIterator.next();
      Posicao posicaoAtual = percorrido.lastElement();
      Vector<Posicao> expansao = this.labirinto.getExpansao(posicaoAtual);
      caminhosIterator.remove();
      this.percorridos.addElement(posicaoAtual);

      this.verificaESalvaCaminho(expansao, percorrido);

      Vector<Vector<Posicao>> extensoes = this.gerarExtensoes(percorrido, expansao);
      Iterator<Vector<Posicao>> extensoesIterator = extensoes.iterator();

      while (extensoesIterator.hasNext()) {
        Vector<Posicao> extensao = extensoesIterator.next();
        caminhosIterator.add(extensao);
      }
    }
  }

	public static void main(String[] args) {
    new BuscaEmLargura();
	}
}
