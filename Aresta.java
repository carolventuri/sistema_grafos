public class Aresta {
    Vertice cidade1, cidade2;
    int distancia;

    public Aresta (Vertice cidade1, Vertice cidade2, int distancia){
        this.cidade1 = cidade1;
        this.cidade2 = cidade2;
        this.distancia = distancia;
    }

    public void info_aresta (){
        System.out.println("A cidade " + this.cidade1.nomeCidade + " está conectada à cidade " + this.cidade2.nomeCidade + " e a distância entre elas é " + this.distancia + " km .");
    }

    String cidade_conectada_com_verticeatual (Vertice cidade){
        if (cidade.nomeCidade.equals(cidade1.nomeCidade)){
            return cidade2.nomeCidade;
        }
        else{
            return cidade1.nomeCidade;
        }
    }
}
