import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception{

        // fazer uma conexão HTTP e buscar os top 250 filmes
//        String url = "https://api.mocki.io/v2/549a5d8b";
//        ExtratorDeConteudo extrator = new ExtratorDeConteudoDoIMDb();

        // fazer uma conexão HTTP e buscar imagens da api da NASA (é preciso adicionar a chave de acesso)
//        String url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2022-06-12&end_date=2022-06-14";
//        ExtratorDeConteudo extrator = new ExtratorDeConteudoDaNasa();

        // fazer uma conexão HTTP e buscar imagens da API criada por mim
        String url = "https://alura-linguage-api.herokuapp.com/linguagens";
        ExtratorDeConteudo extrator = new ExtratorDeConteudoDaMinhaAPI();

        var http = new ClienteHttp();
        String json = http.buscaDados(url);

        // extrair e manipular os dados

        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        var geradora = new GeradoraDeFigurinhas();

        System.out.println("Linguagens de programação");

        for (int i = 0; i < conteudos.size(); i++) {

            Conteudo conteudo = conteudos.get(i);

            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
            String nomeArquivo = "saida/" + conteudo.getTitulo() + ".png";

            geradora.cria(inputStream, nomeArquivo);

            System.out.println("\u001b[1mNome\u001b[m: \u001b[3m" + conteudo.getTitulo());
            System.out.println("\u001b[1mImagem\u001b[m: \u001b[3m" + conteudo.getUrlImagem());
//            System.out.println("\u001b[1mRanking\u001b[m: " + conteudo.getRanking());
            System.out.println();
        }
    }
}
