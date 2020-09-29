import Labirinto.*;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Vector;

public class BuscaAAsterisco extends Busca {
  Vector<Posicao> verificaMaisProximo(Vector<Vector<Posicao>> caminhosComExpansao) {
    ListIterator<Vector<Posicao>> caminhosIterator = caminhosComExpansao.listIterator();

    Posicao saida = this.labirinto.getPosicaoSaida();
    Vector<Posicao> caminhoMaisCurto = caminhosComExpansao.firstElement();
    double distanciaMaisCurta = this.labirinto.getDLR(caminhoMaisCurto.lastElement(), saida);

    while (caminhosIterator.hasNext()) {
      Vector<Posicao> caminho = caminhosIterator.next();
      Posicao ultimaPosicao = caminho.lastElement();
      double distancia = this.labirinto.getDLR(ultimaPosicao, saida);

      Vector<Posicao> expansaoDaUltimaPosicao = labirinto.getExpansao(ultimaPosicao);

      this.verificaESalvaCaminho(expansaoDaUltimaPosicao, caminho);

      if (distancia < distanciaMaisCurta) {
        caminhoMaisCurto = caminho;
        distanciaMaisCurta = distancia;
      }
    }

    return caminhoMaisCurto;
  }

  void buscar() {
    ListIterator<Vector<Posicao>> caminhosIterator = this.caminhos.listIterator();
    Vector<Vector<Posicao>> caminhosComExpansao = new Vector<Vector<Posicao>>(this.caminhos);

    while (caminhosIterator.hasNext()) {
      Vector<Posicao> percorrido = caminhosIterator.next();
      Posicao posicaoAtual = percorrido.lastElement();
      Vector<Posicao> expansao = this.labirinto.getExpansao(posicaoAtual);
      ListIterator<Posicao> expansaoIterator = expansao.listIterator();

      while (expansaoIterator.hasNext()) {
        Posicao expansaoItem = expansaoIterator.next();
        Vector<Posicao> percorridoComExpansao = new Vector<Posicao>(percorrido);
        percorridoComExpansao.add(expansaoItem);
        caminhosComExpansao.add(percorridoComExpansao);
      }
    }

    Vector<Posicao> expansaoMaisCurta = this.verificaMaisProximo(caminhosComExpansao);
    this.caminhos.add(expansaoMaisCurta);
  }

	public static void main(String[] args) {
    new BuscaAAsterisco();
	}
}
