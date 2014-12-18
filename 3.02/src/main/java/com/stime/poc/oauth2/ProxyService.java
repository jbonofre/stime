package com.stime.poc.oauth2;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.rs.security.oauth2.common.ClientAccessToken;
import org.apache.cxf.rs.security.oauth2.grants.code.AuthorizationCodeGrant;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("/proxy")
public class ProxyService {

    private WebClient client = WebClient.create("http://l000pkl360:8098/customers");
    private OAuthClientManager manager = new OAuthClientManager();

    @Path("/customers")
    public Response getCustomers() throws Exception {
        AuthorizationCodeGrant codeGrant = new AuthorizationCodeGrant("stime", new URI("/proxy/customers"));
        ClientAccessToken token = manager.getAccessToken(codeGrant);
        // http://l000pkl360:8098/customers
        if (token == null) {
            throw new IllegalStateException("NO VALID OAUTH2 TOKEN");
        }
        String authHeader = manager.createAuthorizationHeader(token);
        return client.get();
    }

}
