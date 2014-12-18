package com.stime.poc.oauth2;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.rs.security.oauth2.common.ClientAccessToken;
import org.apache.cxf.rs.security.oauth2.grants.clientcred.ClientCredentialsGrant;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/proxy")
public class ProxyService {

    private WebClient client = WebClient.create("http://l000pkl360:8098/customers");
    private OAuthClientManager manager = new OAuthClientManager();

    @Path("/customers")
    public Response getCustomers() throws Exception {
        ClientCredentialsGrant grant = new ClientCredentialsGrant("stime");
        ClientAccessToken token = manager.getAccessToken(grant);
        if (token == null) {
            throw new IllegalStateException("NO VALID OAUTH2 TOKEN");
        }
        String authHeader = manager.createAuthorizationHeader(token);
        return client.get();
    }

}
