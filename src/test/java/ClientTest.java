import org.junit.Test;
import ru.vsu.sample.Menu.Singularity;
import ru.vsu.sample.Menu.Soup;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class ClientTest {

    @Test
    public void putInOrder() {
        Client client = new Client("Иван",60, new BigDecimal("10000.0"), Singularity.NOSINGULARITY);
        Soup borsh = new Soup(12,"Борщ", new BigDecimal("350.0"), 30, Singularity.SOUR);
        client.putInOrder(borsh, 3);
        assertEquals(client.getOrder().get(0).getName(), "Борщ");
    }

    @Test
    public void deleteFromOrder() {
        Client client = new Client("Иван",60, new BigDecimal("10000.0"), Singularity.NOSINGULARITY);
        Soup borsh = new Soup(12,"Борщ", new BigDecimal("350.0"), 30, Singularity.SOUR);
        client.deleteFromOrder(borsh,3);
        assertEquals(client.getOrder().size(), 0);
    }

    @Test
    public void deleteAllFromOrder() {
        Client client = new Client("Иван",60, new BigDecimal("10000.0"), Singularity.NOSINGULARITY);
        client.deleteAllFromOrder();
        assertEquals(client.getOrder().size(), 0);
    }
}