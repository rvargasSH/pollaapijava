package sainthonore.pollaapi;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.nio.charset.Charset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Service
public class ConectionToPollaApiService {

    public String getInfoFromApiPolla(String method, String cuerpoPeticion) {
        String retorno = "";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-Type", "application/json");
        String finalUrl = "http://200.71.80.177:8080/pollashlts/public/" + method;
        System.out.println(finalUrl);
        HttpEntity<String> httpEntity = new HttpEntity<String>(cuerpoPeticion, httpHeaders);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
        try {
            ResponseEntity<String> response = restTemplate.exchange(finalUrl, HttpMethod.GET, httpEntity,
                    String.class);
            // check response
            if (response.getStatusCode() == HttpStatus.OK) {
                String v = String.valueOf(HttpStatus.OK);
                retorno = response.getBody();
                return retorno;
            } else {
                System.out.println(response.getStatusCode());
            }
            return retorno;
        } catch (HttpStatusCodeException exception) {
            return exception.getResponseBodyAsString();
        }
    }
}
