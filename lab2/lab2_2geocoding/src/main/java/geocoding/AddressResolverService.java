package geocoding;

/**
 * Hello world!
 *
 */
public class AddressResolverService 
{
    private ISimpleHttpClient client;

    public AddressResolverService(ISimpleHttpClient client) {
        this.client = client;
    }
    
    public String prepareUriForRemoteEndpoint(double x, double y) {
        return null;
    }
}
