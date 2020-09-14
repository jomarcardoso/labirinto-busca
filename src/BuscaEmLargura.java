import java.util.Iterator;
import java.util.ListIterator;
import java.util.Vector;
import Labirinto.*;

public class BuscaEmLargura extends Busca {
  BuscaEmLargura() {
    Labirinto labirinto = this.criarLabirinto();
    Vector<Posicao> caminho = this.buscar(labirinto);

    System.out.print("\ncaminho: " + caminho.toString());
  }

  Vector<Vector<Posicao>> buscarEmLargura(Vector<Vector<Posicao>> percorridos, Labirinto labirinto, Vector<Posicao> posicoesPassadas) {
    // Vector<Vector<Posicao>> newPercorridos = percorridos.clone();
    ListIterator<Vector<Posicao>> percorridosIterator = percorridos.listIterator();

    while (percorridosIterator.hasNext()) {
      Vector<Posicao> percorrido = percorridosIterator.next();
      Vector<Posicao> expansao = labirinto.getExpansao(percorrido.lastElement());
      percorridosIterator.remove();
      posicoesPassadas = this.concatenaPosicoes(posicoesPassadas, percorrido);

      if (this.ehVizinhoDoFinal(expansao, labirinto.getPosicaoSaida())) {
        this.achouOFinal = true;
      }

      Vector<Vector<Posicao>> extensoes = this.gerarExtensoes(percorrido, expansao, posicoesPassadas);
      Iterator<Vector<Posicao>> extensoesIterator = extensoes.iterator();

      while (extensoesIterator.hasNext()) {
        Vector<Posicao> extensao = extensoesIterator.next();
        percorridosIterator.add(extensao);

        // System.out.print("\nextensao: " + extensao);
      }

      // System.out.print("\npercorridos: " + percorridos.toString());
    }

    return percorridos;
  }

  Vector<Posicao> buscar(Labirinto labirinto) {
    Vector<Vector<Posicao>> percorridos = new Vector<Vector<Posicao>>();
    Vector<Posicao> primeiroPercorrido = new Vector<Posicao>();
    Posicao posicao = labirinto.getPosicaoEntrada();
    primeiroPercorrido.add(posicao);
    percorridos.add(primeiroPercorrido);

    // boolean vizinhoDoFinal = this.ehVizinhoDoFinal(labirinto.getExpansao(labirinto.getPosicaoEntrada()), labirinto.getPosicaoSaida());

    // if (vizinhoDoFinal) {
    //   primeiroPercorrido.add(labirinto.getPosicaoSaida());

    //   return primeiroPercorrido;
    // }

    Vector<Posicao> posicoesPassadas = new Vector<Posicao>();

    while(!this.achouOFinal) {
      this.buscarEmLargura(percorridos, labirinto, posicoesPassadas);
      this.buscarEmLargura(percorridos, labirinto, posicoesPassadas);
    }

    return primeiroPercorrido;
  }

	public static void main(String[] args) {
    new BuscaEmLargura();
    // System.out.print("\ncaminho: " + caminho);
	}
}
