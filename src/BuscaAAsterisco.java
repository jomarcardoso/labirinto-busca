import Labirinto.*;

public class BuscaAAsterisco extends Busca {
  void prepararBusca() {

  }

  void buscar() {
    System.out.println("percorridos: " + this.percorridos);
  }

	public static void main(String[] args) {
    new BuscaAAsterisco();
	}
}
