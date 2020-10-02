import java.util.Iterator;
import java.util.ListIterator;
import java.util.Vector;
import Labirinto.*;

public class BuscaEmLargura extends Busca {
  Vector<Vector<Posicao>> gerarExtensoes(Vector<Posicao> posicoesPercorridas, Vector<Posicao> expansao) {
    Iterator<Posicao> expansaoIterator = expansao.iterator();
    Vector<Vector<Posicao>> extensoes = new Vector<Vector<Posicao>>();

		while (expansaoIterator.hasNext()) {
      Posicao expansaoItem = (Posicao) expansaoIterator.next();

      if (!ehUmaPosicaoPercorrida(expansaoItem)) {
        Vector<Posicao> extensao = new Vector<Posicao>();
        extensao = adicionarPosicao(posicoesPercorridas, expansaoItem);
        extensoes.addElement(extensao);
      }
    }

    return extensoes;
  }

  void buscar() {
    ListIterator<Vector<Posicao>> caminhosIterator = this.caminhos.listIterator();

    // percorro os caminhos
    while (caminhosIterator.hasNext()) {
      Vector<Posicao> percorrido = caminhosIterator.next();
      Posicao posicaoAtual = percorrido.lastElement();

      // a expansão da última posição do caminho
      Vector<Posicao> expansao = this.labirinto.getExpansao(posicaoAtual);

      // removo o caminho de origem [origem, x, x, x] => [x, x, x]
      caminhosIterator.remove();

      // salvo na lista de percorridos para evitar loops
      this.percorridos.addElement(posicaoAtual);

      // verifica as expansões se alguma é a posição final
      this.verificaESalvaCaminho(expansao, percorrido);

      // cria os novos caminhos a partir da expansão do caminho percorrido atual
      Vector<Vector<Posicao>> extensoes = this.gerarExtensoes(percorrido, expansao);
      Iterator<Vector<Posicao>> extensoesIterator = extensoes.iterator();

      while (extensoesIterator.hasNext()) {
        Vector<Posicao> extensao = extensoesIterator.next();
        // adiciono os novos caminhos gerados a partir da expansão
        // [x, x, x] -> [x, x, x, extensão, extensão]
        caminhosIterator.add(extensao);
      }
    }
  }

	public static void main(String[] args) {
    new BuscaEmLargura();
	}
}
