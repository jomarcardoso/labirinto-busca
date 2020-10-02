import Labirinto.*;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Vector;

public class BuscaAAsterisco extends Busca {
  Vector<Posicao> verificaMaisProximo(Vector<Vector<Posicao>> caminhosComExpansao) {
    ListIterator<Vector<Posicao>> caminhosIterator = caminhosComExpansao.listIterator();
    Posicao saida = this.labirinto.getPosicaoSaida();

    // defino as variáveis temporárias usadas para descobrir o caminho mais curto
    Vector<Posicao> caminhoMaisCurto = caminhosComExpansao.firstElement();
    double distanciaMaisCurta = this.labirinto.getDLR(caminhoMaisCurto.lastElement(), saida);

    while (caminhosIterator.hasNext()) {
      Vector<Posicao> caminho = caminhosIterator.next();
      Posicao ultimaPosicao = caminho.lastElement();
      double distancia = this.labirinto.getDLR(ultimaPosicao, saida);

      Vector<Posicao> expansaoDaUltimaPosicao = labirinto.getExpansao(ultimaPosicao);

      // verifica as expansões se alguma é a posição final
      this.verificaESalvaCaminho(expansaoDaUltimaPosicao, caminho);

      // baseado na distância de todos vou verificando qual o mais próximo
      if (distancia < distanciaMaisCurta) {
        caminhoMaisCurto = caminho;
        distanciaMaisCurta = distancia;
      }
    }

    return caminhoMaisCurto;
  }

  void buscar() {
    ListIterator<Vector<Posicao>> caminhosIterator = this.caminhos.listIterator();
    // declaro uma variável temporária que irá conter todas as expansões
    // para testar qual é a mais próxima do final
    Vector<Vector<Posicao>> caminhosComExpansao = new Vector<Vector<Posicao>>(this.caminhos);

    while (caminhosIterator.hasNext()) {
      Vector<Posicao> percorrido = caminhosIterator.next();
      Posicao posicaoAtual = percorrido.lastElement();

      // a expansão da última posição do caminho
      Vector<Posicao> expansao = this.labirinto.getExpansao(posicaoAtual);
      ListIterator<Posicao> expansaoIterator = expansao.listIterator();

      // percorro a expansão
      while (expansaoIterator.hasNext()) {
        Posicao expansaoItem = expansaoIterator.next();

        // clono o caminho atual
        Vector<Posicao> percorridoComExpansao = new Vector<Posicao>(percorrido);

        // adiciono mais uma posição
        percorridoComExpansao.add(expansaoItem);

        // adiciono ao grafo
        caminhosComExpansao.add(percorridoComExpansao);
      }
    }

    // uso o grafo temporário que diz todos os caminhos para percorrer
    // e verifico qual deles é mais próximo do destino para continuar avançando por ali
    Vector<Posicao> expansaoMaisCurta = this.verificaMaisProximo(caminhosComExpansao);

    if (!this.achouOFinal) {
      // adiciono ao grafo o novo caminho mais curto
      this.caminhos.add(expansaoMaisCurta);
    }
  }

	public static void main(String[] args) {
    new BuscaAAsterisco();
	}
}
