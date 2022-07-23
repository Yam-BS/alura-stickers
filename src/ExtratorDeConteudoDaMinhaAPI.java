import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtratorDeConteudoDaMinhaAPI implements ExtratorDeConteudo{
    @Override
    public List<Conteudo> extraiConteudos(String json) {
        // pegar só os dados  que interessam (nome, poster)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeAtributos = parser.parse(json);

        List<Conteudo> conteudos = new ArrayList<>();

        // popular a lista de conteudos
        for (Map<String, String> atributos : listaDeAtributos) {
            String titulo = atributos.get("title");
            String urlImagem = atributos.get("image")
                    .replaceAll("(@+)(.*).jpg$", "$1.jpg");
            String ranking = atributos.get("ranking");

            var conteudo = new Conteudo(titulo, urlImagem, ranking);
            conteudos.add(conteudo);

        }

        return conteudos;
    }
}
