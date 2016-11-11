# Shared libraries for 10Duke Java-clients

This repository contains supporting classes for 10Duke Java REST API clients. These artifacts are
needed by the clients. For the actual API clients, see the individual client-repositories.

The current client-implementations use [Retrofit](https://square.github.io/retrofit/Retrofit) as
REST-implementation. The dependencies are intentionally not transitive. The project is tested
against Retrofit 2.1.0 and OkHttp 3.4.1.

JSON-converter is not pre-determined. The samples use Gson and the project contains some helpers
for Gson, but otherwise, any JSON-converter can be used.

If you want to use other `Converter.Factory` (e.g. [JacksonConverterFactory](http://search.maven.org/#artifactdetails%7Ccom.squareup.retrofit2%7Cconverter-jackson%7C2.1.0%7Cjar)),
you need to make sure it handles `java.util.Date`s in ISO8601-format. See the classes in packages
[com.tenduke.client.util](./com.tenduke.client.utils/src/main/java/com/tenduke/client/util),
[com.tenduke.client.gson](./com.tenduke.client.gson/src/main/java/com/tenduke/client/gson) and
[com.tenduke.client.gson.adapters](./com.tenduke.client.gson/src/main/java/com/tenduke/client/gson/adapters).
In particular, look at class [com.tenduke.client.util.ISO8601DateFormat](com.tenduke.client.utils/src/main/java/com/tenduke/client/util/ISO8601DateFormat.java).


## Building

```console
mvn clean install
```


## Notes

### Share the OkHttpClient

You should share the OkHttpClient to conserve resources (threads, HTTP-connections, caches)
([source](http://square.github.io/okhttp/3.x/okhttp/okhttp3/OkHttpClient.html)). You likely need
only one OkHttpClient per application. If you have single ApiConfiguration, you can share that. The default
provider re-uses the OkHttpClient given in the ApiConfiguration.


### You can share the providers

You don't need to construct new provider for every request (unless your configuration changes).
`BaseApiProvider` can be shared.


### Sharing the API instance

Following things are bound to an API-instance created by a provider:

* API URL
* Credentials

If those two things have not changed, you can reuse the API-instance.

For example, in Android client, you can typically create one API instance and reuse it.

On the other hand, in a backend-application, each request typically comes from different user, the credentials
are different and you need to provide a new API-instance with correct credentials. If the request does several
API-calls, the same API-instance can be reused.

