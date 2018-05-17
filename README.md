# Water-Quality-Portal

This code is responsible for streaming data out of the Water Quality Portal database.

It is designed to execute in a Tomcat container.

See the wqpgateway.properties file for configurable properties.

Security can be enabled by adding the following to the Tomcat's context.xml:

```
    <Parameter name="spring.profiles.active" value="default,swagger,internal" />
    <Parameter name="oauthResourceKeyUri" value = "<<url for token_key>>"/>
    <Parameter name="oauthResourceId" value="wqp"/>
```

