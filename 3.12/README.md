Proxy SOAP with SAML/STS security
---------------------------------

This use case requires the usage of the runtime, with STS installed.

In the runtime, before dropping the kar file in the deploy folder, you have to install STS:

karaf@trun> tesb:start-sts

By default, STS use the karaf JAAS realm but you can create your own realm and update etc/org.talend.esb.sts.server.cfg.
It's also possible to delegate to Syncope identity manager.
