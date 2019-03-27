import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sun.plugin2.main.server.WindowsHelper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class MapBoxServiceTests {

    private MapBoxService _mapBox;

    @Before
    public void createMapBoxService() {
        _mapBox = new MapBoxService();
    }

    @Test
    public void whenInstantiatingServiceIsNotNull() {

        Assert.assertNotNull(_mapBox);
    }

    @Test
    public void whenMakingCallNoTokenReturnsUnauthorized() throws IOException {
        String searchCode = "580 N Fourth Street Columbus OH";

        String result = _mapBox.MakeCall(searchCode, "");

        Assert.assertEquals("Unauthorized", result);
    }

    @Test
    public void whenMakingCallBadAddressReturnsUnauthorized() throws IOException {
        String apiToken = DevSettings.ApiKey;

        String result = _mapBox.MakeCall("", apiToken);

        Assert.assertEquals("Not Found", result);
    }

    @Test
    public void whenMakingCallWithTokenGoodAddressReturnsJsonArray() throws IOException {
        String searchCode = "580 N Fourth Street Columbus OH";
        String apiToken = DevSettings.ApiKey;

        String result = _mapBox.MakeCall(searchCode, apiToken);

        Assert.assertTrue(result.contains("FeatureCollection"));
    }
}
