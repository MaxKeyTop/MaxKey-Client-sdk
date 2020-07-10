package org.maxkey.client.http;

/*
public class ApacheHttpClient{

    private final CloseableHttpAsyncClient client;

    public ApacheHttpClient() {
        this(ApacheHttpClientConfig.defaultConfig());
    }

    public ApacheHttpClient(ApacheHttpClientConfig config) {
        this(config.getHttpAsyncClientBuilder());
    }

    public ApacheHttpClient(HttpAsyncClientBuilder builder) {
        this(builder.build());
    }

    public ApacheHttpClient(CloseableHttpAsyncClient client) {
        this.client = client;
        this.client.start();
    }

    @Override
    public void close() throws IOException {
        client.close();
    }

    @Override
    public <T> Future<T> executeAsync(String userAgent, Map<String, String> headers, Verb httpVerb, String completeUrl,
            byte[] bodyContents, OAuthAsyncRequestCallback<T> callback, OAuthRequest.ResponseConverter<T> converter) {
        final HttpEntity entity = bodyContents == null ? null : new ByteArrayEntity(bodyContents);
        return doExecuteAsync(userAgent, headers, httpVerb, completeUrl, entity, callback, converter);
    }

    @Override
    public <T> Future<T> executeAsync(String userAgent, Map<String, String> headers, Verb httpVerb, String completeUrl,
            MultipartPayload bodyContents, OAuthAsyncRequestCallback<T> callback,
            OAuthRequest.ResponseConverter<T> converter) {

        throw new UnsupportedOperationException("ApacheHttpClient does not support MultipartPayload yet.");
    }

    @Override
    public <T> Future<T> executeAsync(String userAgent, Map<String, String> headers, Verb httpVerb, String completeUrl,
            String bodyContents, OAuthAsyncRequestCallback<T> callback, OAuthRequest.ResponseConverter<T> converter) {
        final HttpEntity entity = bodyContents == null ? null : new StringEntity(bodyContents, StandardCharsets.UTF_8);
        return doExecuteAsync(userAgent, headers, httpVerb, completeUrl, entity, callback, converter);
    }

    @Override
    public <T> Future<T> executeAsync(String userAgent, Map<String, String> headers, Verb httpVerb, String completeUrl,
            File bodyContents, OAuthAsyncRequestCallback<T> callback, OAuthRequest.ResponseConverter<T> converter) {
        final HttpEntity entity = bodyContents == null ? null : new FileEntity(bodyContents);
        return doExecuteAsync(userAgent, headers, httpVerb, completeUrl, entity, callback, converter);
    }

    private <T> Future<T> doExecuteAsync(String userAgent, Map<String, String> headers, Verb httpVerb,
            String completeUrl, HttpEntity entity, OAuthAsyncRequestCallback<T> callback,
            OAuthRequest.ResponseConverter<T> converter) {
        final RequestBuilder builder = getRequestBuilder(httpVerb);
        builder.setUri(completeUrl);

        if (httpVerb.isPermitBody()) {
            if (!headers.containsKey(CONTENT_TYPE)) {
                builder.addHeader(CONTENT_TYPE, DEFAULT_CONTENT_TYPE);
            }
            builder.setEntity(entity);
        }

        for (Map.Entry<String, String> header : headers.entrySet()) {
            builder.addHeader(header.getKey(), header.getValue());
        }

        if (userAgent != null) {
            builder.setHeader(OAuthConstants.USER_AGENT_HEADER_NAME, userAgent);
        }
        final OAuthAsyncCompletionHandler<T> handler = new OAuthAsyncCompletionHandler<>(callback, converter);
        final Future<HttpResponse> future = client.execute(builder.build(), handler);
        return new ApacheHttpFuture<>(future, handler);
    }

    private static RequestBuilder getRequestBuilder(Verb httpVerb) {
        switch (httpVerb) {
            case GET:
                return RequestBuilder.get();
            case PUT:
                return RequestBuilder.put();
            case DELETE:
                return RequestBuilder.delete();
            case HEAD:
                return RequestBuilder.head();
            case POST:
                return RequestBuilder.post();
            case PATCH:
                return RequestBuilder.patch();
            case TRACE:
                return RequestBuilder.trace();
            case OPTIONS:
                return RequestBuilder.options();
            default:
                throw new IllegalArgumentException("message build error: unknown verb type");
        }
    }
}*/