package com.stime.poc.oauth2;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.rs.security.oauth2.client.OAuthClientUtils;
import org.apache.cxf.rs.security.oauth2.common.ClientAccessToken;
import org.apache.cxf.rs.security.oauth2.grants.clientcred.ClientCredentialsGrant;
import org.apache.cxf.rs.security.oauth2.provider.OAuthServiceException;

import java.net.URI;

public class OAuthClientManager {

    private static final String DEFAULT_CLIENT_ID = "stime";
    private static final String DEFAULT_CLIENT_SECRET = "stime";

    private WebClient accessTokenService;
    private String authorizationServiceURI;
    private OAuthClientUtils.Consumer consumer = new OAuthClientUtils.Consumer(DEFAULT_CLIENT_ID, DEFAULT_CLIENT_SECRET);

    public OAuthClientManager() { }

    public URI getAuthorizationServiceURI(URI redirectURI) {
        return OAuthClientUtils.getAuthorizationURI(authorizationServiceURI, consumer.getKey(), redirectURI.toString(), "customers", "stime");
    }

    public ClientAccessToken getAccessToken(ClientCredentialsGrant grant) {
        try {
            return OAuthClientUtils.getAccessToken(accessTokenService, consumer, grant);
        } catch (OAuthServiceException e) {
            return null;
        }
    }

    public String createAuthorizationHeader(ClientAccessToken token) {
        return OAuthClientUtils.createAuthorizationHeader(consumer, token);
    }

    public void setAccessTokenService(WebClient ats) {
        WebClient.getConfig(ats).getHttpConduit().getClient().setReceiveTimeout(1000000);
        this.accessTokenService = ats;
    }

    public void setAuthorizationServiceURI(String uri) {
        this.authorizationServiceURI = uri;
    }

}
