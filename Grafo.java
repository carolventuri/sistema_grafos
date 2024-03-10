import java.util.ArrayList;
import java.util.Scanner;
public class Grafo {
    static ArrayList<Vertice> cidades = new ArrayList<>();
    static ArrayList<Aresta> conexoes = new ArrayList<>();
    static Scanner tec = new Scanner (System.in);

    public static void cadastra_cidade(String nomeCidade){
        cidades.add (new Vertice(nomeCidade));
    }

    //opção 1 do menu
    static void cadastra_cidade(){
        int achou = 0;
        System.out.print("Digite o nome da cidade a ser cadastrada: ");
        String nomeCidade = tec.nextLine();
        for (int i = 0; i<cidades.size(); i++){
            achou = 0;
            if (cidades.get(i).nomeCidade.equalsIgnoreCase(nomeCidade)){
                System.out.println("A cidade digitada já consta no sistema.");
                achou++;
                i=cidades.size();
            }
        }
        if (achou == 0){
            cidades.add(new Vertice(nomeCidade));
            System.out.print("Cidade cadastrada com sucesso!");
            System.out.println();
        }
    }

    static void cadastra_conexao(Vertice cidade1, Vertice cidade2, int distancia){

        int indicecidade1 = 0;
        int indicecidade2 = 0;
        int achou = 0;

        //procurar dentro do array de cidades (vértice) em que posicao está a cidade1 e copiar o índice
        for (int i = 0; i<cidades.size(); i++){
            achou = 0;
            if (cidades.get(i).nomeCidade.equalsIgnoreCase(cidade1.nomeCidade)){
                indicecidade1 = i;
                achou++;
                i=cidades.size();
            }
        }
        if (achou == 0){
            System.out.println("A cidade digitada não consta no sistema. Primeiro é necessário cadastrá-la.");
            return;
        }

        //procurar dentro do array de cidades (vértice) em que posicao está a cidade2 e copiar o índice
        for (int i = 0; i<cidades.size(); i++){
            achou = 0;
            if (cidades.get(i).nomeCidade.equalsIgnoreCase(cidade2.nomeCidade)){
                indicecidade2 = i;
                achou++;
                i = cidades.size();
            }
        }
        if (achou == 0){
            System.out.println("A cidade digitada não consta no sistema. Primeiro é necessário cadastrá-la.");
            return;
        }

        //colocar dentro do array conexoes a aresta contendo as respectivas cidades e distancias
        conexoes.add( new Aresta(cidades.get(indicecidade1), cidades.get(indicecidade2), distancia));
        //por fim, conectar o vértice aos seus arrays de vértice (vizinhos) e de arestas (conexões)
        //para a cidade 1, adicionar a 2 como vizinha e
        cidades.get(indicecidade1).vizinhanca.add(cidades.get(indicecidade2));
        cidades.get(indicecidade1).conexoes.add(conexoes.get(conexoes.size()-1 ));

        cidades.get(indicecidade2).vizinhanca.add(cidades.get(indicecidade1));
        cidades.get(indicecidade2).conexoes.add(conexoes.get(conexoes.size()-1 ));
    }

    //opção 2 do menu
    static void cadastra_conexao(){
        int indicecidade1 = 0;
        int indicecidade2 = 0;
        int achou = 0;

        System.out.println("Digite a cidade 1: ");
        String cid1 = tec.nextLine();
        Vertice cidade1 = new Vertice(cid1);
        //procurar dentro do array de cidades (vértice) em que posicao está a cidade1 e copiar o índice
        for (int i = 0; i<cidades.size(); i++){
            achou = 0;
            if (cidades.get(i).nomeCidade.equalsIgnoreCase(cidade1.nomeCidade)){
                indicecidade1 = i;
                achou++;
                i=cidades.size();
            }
        }
        if (achou == 0){
            System.out.println("A cidade 1 digitada não consta no sistema. Primeiro é necessário cadastrá-la.");
            return;
        }

        System.out.println("Digite a cidade 2: ");
        String cid2 = tec.nextLine();
        Vertice cidade2 = new Vertice(cid2);
        //procurar dentro do array de cidades (vértice) em que posicao está a cidade2 e copiar o índice
        for (int i = 0; i<cidades.size(); i++){
            achou = 0;
            if (cidades.get(i).nomeCidade.equalsIgnoreCase(cidade2.nomeCidade)){
                indicecidade2 = i;
                achou++;
                i = cidades.size();
            }
        }
        if (achou == 0){
            System.out.println("A cidade 2 digitada não consta no sistema. Primeiro é necessário cadastrá-la.");
            return;
        }

        System.out.println("Digite a distância: ");
        int distancia = tec.nextInt();


        //colocar dentro do array conexoes a aresta contendo as respectivas cidades e distancias
        conexoes.add( new Aresta(cidades.get(indicecidade1), cidades.get(indicecidade2), distancia));
        //por fim, conectar o vértice aos seus arrays de vértice (vizinhos) e de arestas (conexões)
        //para a cidade 1, adicionar a 2 como vizinha e
        cidades.get(indicecidade1).vizinhanca.add(cidades.get(indicecidade2));
        cidades.get(indicecidade1).conexoes.add(conexoes.get(conexoes.size()-1 ));

        cidades.get(indicecidade2).vizinhanca.add(cidades.get(indicecidade1));
        cidades.get(indicecidade2).conexoes.add(conexoes.get(conexoes.size()-1 ));

        System.out.print("Conexão cadastrada com sucesso!");
        tec.nextLine();
        System.out.println();
    }

    // opção 3 do menu
    public static void info_cidades(){
        //cidades em ordem alfabetica
        for (int i = 0; i < cidades.size() - 1; i++) {
            for (int j = 0; j < cidades.size() - i - 1; j++) {
                Vertice cidadeAtual = cidades.get(j);
                Vertice cidadeSeguinte = cidades.get(j + 1);

                if (cidadeAtual.nomeCidade.compareToIgnoreCase(cidadeSeguinte.nomeCidade) > 0) {
                    cidades.set(j, cidadeSeguinte);
                    cidades.set(j + 1, cidadeAtual);
                }
            }
        }
        System.out.println("Segue abaixo os nomes de todas as cidades em ordem alfabética: ");
        for (Vertice cidade: cidades){
            System.out.println(cidade.nomeCidade);
        }
        System.out.println();
    }
    // opção 4 do menu
    public static void info_conexoes() {
        System.out.println("Segue abaixo todas as cidades ordenadas pela menor distância: ");
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
        for (Aresta conexao : conexoes) {
          conexao.info_aresta();
        }
        System.out.println();
    }

    // opção 5 do menu
    public static void info_conexoes_cidade() {
        int achou = 0;
        System.out.println("Digite a cidade para consultar as cidades vizinhas:");
        String cid = tec.nextLine();
        Vertice cidade = new Vertice (cid);
        for(Vertice c : cidades){
            if (c.nomeCidade.equalsIgnoreCase(cidade.nomeCidade)){
                c.info_conexoes_cidade();
                System.out.println();
                achou++;
            }
        }
        if (achou == 0){
            System.out.println("A cidade digitada não consta no sistema. Primeiro é necessário cadastrá-la.");
        }
    }

    public static void main(String[] args) {
        Scanner tec = new Scanner (System.in);

        cadastra_cidade("Porto Alegre");
        cadastra_cidade("Canoas");
        cadastra_cidade("Cachoeirinha");

        cadastra_conexao(new Vertice("Porto Alegre"), new Vertice("Canoas"), 15);
        cadastra_conexao(new Vertice("Porto Alegre"), new Vertice("Cachoeirinha"), 12);
        cadastra_conexao(new Vertice("Cachoeirinha"), new Vertice("Canoas"), 20);

        int opcao = 1;

        while (opcao !=0){
            System.out.println();
            System.out.println("---------Menu------------");
            System.out.println();
            System.out.println("1 - Cadastrar cidade");
            System.out.println("2 - Cadastrar conexão");
            System.out.println("3 - Listar cidades");
            System.out.println("4 - Listar conexões");
            System.out.println("5 - Listar cidades vizinhas");
            System.out.println("0 - Sair do sistema");
            System.out.println();

            System.out.println("Digite a opcao do menu desejada:");

            opcao = tec.nextInt();

            switch (opcao){
                case 1 -> cadastra_cidade();
                case 2 -> cadastra_conexao();
                case 3 -> info_cidades();
                case 4 -> info_conexoes();
                case 5 -> info_conexoes_cidade();
                case 0 -> System.out.println("Até logo!");
                default -> System.out.println("Digite um número entre as opções do menu (de 0 a 5)");
            }
        }
    }
}
