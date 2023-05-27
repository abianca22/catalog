package ro.pao.gateways;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import ro.pao.mapper.AdresaMapper;
import ro.pao.model.Adresa;
import ro.pao.repository.impl.AdresaRepositoryImpl;
import ro.pao.service.AdresaService;
import ro.pao.service.impl.AdresaServiceImpl;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Requests {
    private static HttpClient httpClient = HttpClient.newHttpClient();

    private List<Adresa> rspList = new ArrayList<>();

    private static ObjectMapper objectMapper = new ObjectMapper();

    private static final AdresaService adresaService = new AdresaServiceImpl(new AdresaRepositoryImpl());

    public void saveRequestInfo(){
        try {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(new URI("https://www.balldontlie.io/api/v1/teams"))
                    .GET()
                    .build();

            var httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            JsonNode jsonNode = objectMapper.readTree(httpResponse.body());

            var rspList2 = jsonNode.get("data");

            for(var a: rspList2){
                Adresa adresa = Adresa.builder()
                        .id(UUID.randomUUID())
                        .judet("")
                        .strada("")
                        .localitate(a.get("city").asText())
                        .numar(a.get("id").asInt())
                        .codPostal(null)
                        .tara(null)
                        .build();
                rspList.add(adresa);
            }

            adresaService.addFromGivenList(rspList);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
