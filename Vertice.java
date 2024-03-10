import java.util.ArrayList;

public class Vertice {
    String nomeCidade;
    ArrayList<Vertice> vizinhanca;
    ArrayList<Aresta> conexoes;
    public Vertice (String nomeCidade){
        this.nomeCidade = nomeCidade;
        this.conexoes = new ArrayList<>();
        this.vizinhanca = new ArrayList<>();
    }
    public void info_vertice (){
        System.out.println(this.nomeCidade);
    }

    public void info_vizinhos (){
        System.out.println("Os vizinhos da cidade " + this.nomeCidade + " são: ");
        for (Vertice vizinho: vizinhanca){
            System.out.println(vizinho.nomeCidade);
        }
    }
    public void info_conexoes_cidade (){
        // Bubble Sort para ordenar as cidades vizinhas pela menor distância
        for (int i = 0; i < conexoes.size() - 1; i++) {
            for (int j = 0; j < conexoes.size()- i - 1; j++) {
                Aresta conexaoAtual = conexoes.get(j);
                Aresta conexaoSeguinte = conexoes.get(j + 1);

                if (conexaoAtual.distancia > conexaoSeguinte.distancia) {
                    // Troca as conexões de posição se estiverem fora de ordem
                    conexoes.set(j, conexaoSeguinte);
                    conexoes.set(j + 1, conexaoAtual);
                }
            }
        }
        for(Aresta conexao : conexoes) {
            System.out.println("A cidade " + this.nomeCidade + " está conectada à cidade " +
                    conexao.cidade_conectada_com_verticeatual(new Vertice (this.nomeCidade)) + "  e a distância entre elas é " +
                    conexao.distancia + " km.");
        }
    }
}
