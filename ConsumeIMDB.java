import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class ConsumeIMDB {
    public static void main(String[] args) throws Exception {
        //conexão http para buscar os dados da API do imdb
        String url = "https://imdb-api.com/en/API/top250Movies/k_v000xkd0";
        URI endereço = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereço).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
      
    //extrair os dados interessantes (título, poster, notas) (parcear o texto)
    var parser = new JsonParser();
    List<Map<String, String>> listadeFilmes = parser.parse(body) ;
    
    //exibir esses dados unicamente

    for (Map<String,String> filme : listadeFilmes) {
     System.out.println("📽️\u001b[1mTítulo:\u001b[m " + filme.get("title"));
     System.out.println(filme.get("image"));
     System.out.println(filme.get("imDbRating"));
     double classificacao = Double.parseDouble(filme.get("imDbRating"));
     int numEstrelas = (int) classificacao;
     for (int n = 1; n <= numEstrelas; n++) {
        System.out.print("🌟");
     }

     System.out.println("\n");
    }
}
} 