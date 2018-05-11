package us.kbase.common.executionengine;


import java.io.PrintStream;
import java.io.InputStreamReader;
import java.io.BufferredReader;

import java.util.Map;
import java.util.HashMap;

import java.net.URL;

public abstract class PayloadConverter {

    public abstract String convertPayload(Map<String, Object> payload);

    public abstract Map<String, Object> convertResponse(String response);

}

public class RestFul {
    public final String baseUrl;
    private final PayloadConverter payloadConverter;

    public Rest(String baseUrl, PayloadConverter pc){
        this.baseUrl = baseUrl;
        this.pc = pc;
    }

    private String call(String method, String resource, Map<String, Object> payload){
        HttpURLConnection conn = HttpURLConnection(URL(baseUrl+"/"+resource));
        conn.setRequestMethod(method);
        if payload != null{
            PrintStream ps = PrintStream(conn.getOutputStream());
            ps.print(this.pc.convertPayload(payload);
            ps.close();
        }
        BufferredReader br = new BufferredReader(new InputStreamReader(urlc.getInputStream()));
        String l = null;
        StringBuilder sb = StringBuilder();
        while ((l=br.readLine())!=null) {
            sb.append(l);
        }
        Map<String, Object> ret = this.pc.convertResponse(sb.toString());
        return ret;
    }

    public String post(String resource, Map<String, Object> payload){
        return call("POST", resource, payload);
    }

    public String get(String resource, Map<String, Object> payload){
        return call("GET", resource, payload);
    }
}
