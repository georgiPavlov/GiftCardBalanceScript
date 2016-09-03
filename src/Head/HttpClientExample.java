package Head;

import com.sun.deploy.net.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.CookieHandler;
import java.net.CookieManager;


/**
 * Created by user on 9/2/16.
 */
public class HttpClientExample {

    private String cookies;
    private HttpClient client = HttpClientBuilder.create().build();
    private final String USER_AGENT = "Mozilla/5.0";

    public static void main(String[] args) throws IOException {
        String url = "https://www.amazon.com/ap/signin?_encoding=UTF8&openid.assoc_handle=usflex&openid.claimed_id=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.identity=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.mode=checkid_setup&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0&openid.ns.pape=http%3A%2F%2Fspecs.openid.net%2Fextensions%2Fpape%2F1.0&openid.pape.max_auth_age=0&openid.return_to=https%3A%2F%2Fwww.amazon.com%2F%3Fref_%3Dnav_custrec_signin";
        String amazonGcB  = "https://www.amazon.com/gp/css/gc/balance?ie=UTF8&ref_=ya_view_gc";
        CookieHandler.setDefault(new CookieManager());
        HttpClientExample http = new HttpClientExample();
        //1
        String signPage = http.GetPageContent(url);
        System.out.println(signPage);




    }

    private String GetPageContent(String url) throws IOException {
        HttpGet request = new HttpGet(url);
        request.setHeader("User-Agent", USER_AGENT);
        request.setHeader("Accept",
                "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        request.setHeader("Accept-Language", "en-US,en;q=0.5");

        org.apache.http.HttpResponse response = client.execute(request);
        int responseCode = response.getStatusLine().getStatusCode();

        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        // set cookies
        setCookies(response.getFirstHeader("Set-Cookie") == null ? "" :
                response.getFirstHeader("Set-Cookie").toString());

        return result.toString();

    }

    private void setCookies(String cookies) {
        this.cookies = cookies;
    }
}