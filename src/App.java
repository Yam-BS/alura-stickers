import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception{

        // fazer uma conexão HTTP e buscar os top 250 filmes
        String url = "https://api.mocki.io/v2/549a5d8b";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // pegar só os dados  que interessam (titulo, poster, classificação)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        System.out.println("\u001b[1mTop 250 filmes IMDb\u001b[m \n");

        // exibir e manipular os dados
        var geradora = new GeradoraDeFigurinhas();
        for (Map<String, String> filme : listaDeFilmes) {

            String urlImagem = filme.get("image");
            String titulo = filme.get("title");

            InputStream inputStream = new URL(urlImagem).openStream();
            String nomeArquivo = titulo + ".png";

            geradora.cria(inputStream, nomeArquivo);

            System.out.println("\u001b[1mTítulo\u001b[m: \u001b[3m" + titulo + " " + "\u001b[m\uD83C\uDF7F");
//            System.out.println("\u001b[1mTítulo\u001b[m: \u001b[3m" + filme.get("title") + " " + "\u001b[m\uD83C\uDF7F");
//            System.out.println("\u001b[1mCapa\u001b[m: " + filme.get("image"));
//            System.out.println("\u001b[1mAvaliação\u001b[m: " + filme.get("imDbRating")  + "\u2B50");
            System.out.println();
        }
    }
}
