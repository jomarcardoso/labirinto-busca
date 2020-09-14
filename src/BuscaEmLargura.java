import java.util.Iterator;
import java.util.ListIterator;
import java.util.Vector;
import Labirinto.*;

public class BuscaEmLargura extends Busca {
  BuscaEmLargura() {
    this.criarLabirinto();
    this.buscar();

    this.labirinto.print(null);
    this.imprimirPosicaoInicial();
    this.imprimirPosicaoSaida();
    this.imprimirCaminho();
  }

  void buscarEmLargura() {
    ListIterator<Vector<Posicao>> percorridosIterator = this.percorridos.listIterator();

    while (percorridosIterator.hasNext()) {
      Vector<Posicao> percorrido = percorridosIterator.next();
      Posicao posicaoAtual = percorrido.lastElement();
      Vector<Posicao> expansao = this.labirinto.getExpansao(posicaoAtual);
      percorridosIterator.remove();
      this.posicoesPassadas.addElement(posicaoAtual);

      this.verificaESalvaCaminho(expansao, percorrido);

      Vector<Vector<Posicao>> extensoes = this.gerarExtensoes(percorrido, expansao);
      Iterator<Vector<Posicao>> extensoesIterator = extensoes.iterator();

      while (extensoesIterator.hasNext()) {
        Vector<Posicao> extensao = extensoesIterator.next();
        percorridosIterator.add(extensao);
      }
    }
  }

  void buscar() {
    Vector<Posicao> primeiroPercorrido = new Vector<Posicao>();
    Posicao posicao = this.labirinto.getPosicaoEntrada();
    primeiroPercorrido.add(posicao);
    this.percorridos.add(primeiroPercorrido);

    while(!this.achouOFinal) {
      this.buscarEmLargura();
    }
  }

	public static void main(String[] args) {
    new BuscaEmLargura();
	}
}
