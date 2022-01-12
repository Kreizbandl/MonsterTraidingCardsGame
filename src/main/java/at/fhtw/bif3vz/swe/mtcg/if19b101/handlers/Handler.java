package at.fhtw.bif3vz.swe.mtcg.if19b101.handlers;

//import at.fhtw.bif3vz.swe.mtcg.if19b101.card.TestCard;
import at.fhtw.bif3vz.swe.mtcg.if19b101.Main;
import at.fhtw.bif3vz.swe.mtcg.if19b101.database.CardDB;
import com.fasterxml.jackson.core.type.TypeReference;
import com.sun.net.httpserver.HttpExchange;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class Handler {

    private final ObjectMapper mapper = new ObjectMapper();

    public void handle(HttpExchange exchange){
        try{
            execute(exchange);
            exchange.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    abstract protected void execute(HttpExchange exchange) throws IOException;

    protected boolean isLogged(String token){
        return Main.loggedUsersTokenList.contains(token);
    }

    protected String getAuthorizationToken(HttpExchange exchange){
        return exchange.getRequestHeaders().get("Authorization").get(0);
    }

    public static enum StatusCode {
        CREATED(201), NOCONTENT(204) ,OK(200), UNAUTHORIZED(401), FORBIDDEN(403);

        final int code;
        StatusCode(int code) {
            this.code=code;
        }

        public int getCode() {
            return code;
        }
    }

    protected <T> byte[] writeResponse(T response) throws IOException {
        return new ObjectMapper().writeValueAsBytes(response);
    }

    protected <T> T mapRequest(InputStream is, Class<T> type) throws IOException{
        mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        return mapper.readValue(is, type);
    }

    protected List<CardDB> mapCardsList(InputStream is, Class<CardDB> type) throws IOException{
        mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        return mapper.readValue(is, new TypeReference<List<CardDB>>(){});
    }

    protected List<String> mapStringList(InputStream is) throws IOException{
        mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        return mapper.readValue(is, new TypeReference<List<String>>(){});
    }

    protected Map<String,String> mapRequestListMap(InputStream is) throws IOException{
        mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        return mapper.readValue(is, new TypeReference<Map<String,String>>(){});
    }

    protected String getURIName(String uri){
        String[] parts = uri.split("/");
        return parts[2];
    }

    protected void printHeaders(Map<String, List<String>> map){
        for(Map.Entry<String, List<String>> entry : map.entrySet()){
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    protected void printBody(InputStreamReader isr) throws IOException{
        BufferedReader br = new BufferedReader(isr);

        int b;
        StringBuilder buf = new StringBuilder();
        while ((b = br.read()) != -1) {
            buf.append((char) b);
        }

        br.close();
        isr.close();
        System.out.println(buf.toString());
    }

    //JSON
    //JsonFactory factory = new
    //JsonFactory(); ObjectMapper mapper = new ObjectMapper(factory);
    //JsonNode json = mapper.readTree( inputStream )



}
