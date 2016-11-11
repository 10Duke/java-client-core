package com.tenduke.client;

import java.util.HashMap;
import java.util.Map;


/** Simple registry of API-providers.
 * 
 *  <p>
 *  Groups several providers in one register.
 * 
 *  <p>
 *  Default providers (i.e. providers registered with
 *  {@link #register(java.lang.Class) } share the same ApiConfiguration.
 */
public class ApiProviderRegistry {
    
    private final ApiConfiguration _configuration;
    private final Map<Class<?>, ApiProvider<?>> _registry = new HashMap<> ();


    /** Constructs new instance.
     * 
     *  @param configuration Configuration to use for the providers.
     */
    public ApiProviderRegistry(/*@NonNull*/ final ApiConfiguration configuration) {
        _configuration = configuration;
    }
    

    /** Creates the default API provider.
     * 
     *  @param <T> Type of the API-class
     *  @param apiClass the API-class
     *  @return default provider for the API-class
     */
    public <T> /*@NonNull*/ ApiProvider<T> createDefaultApiProvider (/*@NonNull*/ final Class<T> apiClass) {
        return new BaseApiProvider<> (apiClass, _configuration);
    }


    /** Manually registers a provider for given API-class.
     * 
     *  @param <T> Type of the API-class
     *  @param apiClass the API-class
     *  @param apiProvider the corresponding provider
     */
    public <T> void register (
            /*@NonNull*/ final Class<T> apiClass,
            /*@NonNull*/ final ApiProvider<T> apiProvider) {
        //
        _registry.put (apiClass, apiProvider);
    }
    

    /** Registers default provider for given API class.
     * 
     *  @param <T> Type of the API-class
     *  @param apiClass the API-class
     */
    public <T> void register (/*@NonNull*/ final Class<T> apiClass) {
        //
        register (apiClass, createDefaultApiProvider(apiClass));
    }


    /** Returns provider for given API-class.
     * 
     *  @param <T> Type of the API-class
     *  @param apiClass The API-class
     *  @return the corresponding provider. Can return {@code null} if provider has not been registered for given class.
     */
    @SuppressWarnings ("unchecked")
    public <T> /*@Nullable*/ ApiProvider<T> getProvider (/*@NonNull*/ Class<T> apiClass) {
        return (ApiProvider<T>)_registry.get (apiClass);
    }
    

    /** Instantiates API for the given class.
     * 
     * @param <T> Type of the API-class
     * @param apiClass The API-class
     * @param credentials Credentials to use in the API
     * @return API instance ready to use
     * @throws IllegalArgumentException if provider for the apiClass has not been registered
     */
    public <T> /*@NonNull*/ T provide (
            /*@NonNull*/ final Class<T> apiClass,
            /*@Nullable*/ final ApiCredentials credentials) {
        //
        final ApiProvider<T> provider = getProvider (apiClass);
        
        if (provider == null) {
            throw new IllegalArgumentException ("Unregistered api " + apiClass.getName() + " requested.");
        }
        
        return provider.provide(credentials);
    }
    
    
}
