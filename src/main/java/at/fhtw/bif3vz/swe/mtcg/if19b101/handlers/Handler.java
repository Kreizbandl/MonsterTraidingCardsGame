package at.fhtw.bif3vz.swe.mtcg.if19b101.handlers;

import com.sun.net.httpserver.HttpExchange;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

public abstract class Handler {

    public void handle(HttpExchange exchange){
        try{
            execute(exchange);
            exchange.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    abstract protected void execute(HttpExchange exchange) throws IOException;

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

    protected void printHeaders(Map<String, List<String>> map){
        for(Map.Entry<String, List<String>> entry : map.entrySet()){
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    protected <T> T mapRequest(InputStream is, Class<T> type) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        return mapper.readValue(is, type);
    }

}
