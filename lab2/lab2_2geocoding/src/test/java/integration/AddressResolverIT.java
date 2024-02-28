package integration;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import connection.ISimpleHttpClient;
import connection.TqsBasicHttpClient;
import geocoding.AddressResolverService;
import geocoding.Address;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddressResolverIT {

    private ISimpleHttpClient client;
    private AddressResolverService resolver;


    @BeforeEach
    public void init(){
        client = new TqsBasicHttpClient();
        resolver = new AddressResolverService(client);
    }


    @Test
    public void whenGoodCoordidates_returnAddress() throws IOException, URISyntaxException, ParseException {

        double latitude = 40.63436;
        double longitude =  -8.65616;

        Address result = resolver.findAddressForLocation(latitude, longitude).get();

        Address expected = new Address( "Avenida da Universidade", "Aveiro","3810-489", "");

        assertEquals(expected.getCity(), result.getCity());
        assertEquals(expected.getRoad(), result.getRoad());
        assertEquals(expected.getZio(), result.getZio());

    }

    @Test
    public void whenBadCoordidates_thenReturnNoValidAddrress() throws IOException, URISyntaxException, ParseException {

        //todo
        // repeat the same tests conditions from AddressResolverTest, without mocks

        double latitude = 500;
        double longitude = -20;

        Optional<Address> address = resolver.findAddressForLocation(latitude, longitude);

        assertThrows(NoSuchElementException.class, () -> address.get().getRoad());

        
    }

}
