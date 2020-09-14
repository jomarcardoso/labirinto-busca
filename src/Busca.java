import java.util.Iterator;
import java.util.Arrays;
import java.util.Vector;
import Labirinto.*;

public class Busca {
  boolean achouOFinal = false;
  Labirinto labirinto = null;
  Vector<Posicao> posicoesPassadas = new Vector<Posicao>();
  Vector<Posicao> caminho = new Vector<Posicao>();
  Vector<Vector<Posicao>> percorridos = new Vector<Vector<Posicao>>();

  void criarLabirinto() {
		boolean debug = false;
		this.labirinto  = new Labirinto(10, 10, 30, debug);
  }

  void imprimirPosicaoInicial() {
    System.out.println("Posicao entrada: " + this.labirinto.getPosicaoEntrada());
  }

  void imprimirPosicaoSaida() {
    System.out.println("Posicao saida: " + this.labirinto.getPosicaoSaida());
  }

  void imprimirCaminho() {
    Object[] objCaminhoArray = this.caminho.toArray();
    Posicao[] caminhoArray = Arrays.copyOf(objCaminhoArray, objCaminhoArray.length, Posicao[].class);

    this.labirinto.print(caminhoArray);
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

  void verificaESalvaCaminho(Vector<Posicao> expansao, Vector<Posicao> percorrido) {
    if (this.ehVizinhoDoFinal(expansao, this.labirinto.getPosicaoSaida())) {
      this.achouOFinal = true;
      this.caminho = percorrido;
      this.caminho.add(this.labirinto.getPosicaoSaida());
    }
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

  boolean ehUmaPosicaoPercorrida(Posicao posicao) {
    Iterator<Posicao> posicoesPassadasIterator = this.posicoesPassadas.iterator();

    while (posicoesPassadasIterator.hasNext()) {
      Posicao posicaoPassada = (Posicao) posicoesPassadasIterator.next();

      if (posicaoPassada.comparaCom(posicao)) return true;
    }

    return false;
  }

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
}
