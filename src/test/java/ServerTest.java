import org.junit.Test;
import ru.vsu.sample.Menu.Dish;

import java.io.*;
import java.net.Socket;

import static org.junit.Assert.*;

public class ServerTest {

    @Test
    public void searchDishToIdTest() {
        Dish dish = Server.searchDishToId(9);
        assertEquals(dish.getName(), "Чай");
    }

    @Test
    public void TestConnection(){
        Socket socket = null;
        try {
            socket = new Socket("127.0.0.1",8000);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(socket.getPort(), 8000);
    }

    @Test
    public void TestConnectionBufferedReaderAndBufferedWriter(){
        Socket socket = null;
        String mess = "";
        try {
            socket = new Socket("127.0.0.1",8000);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer.write("sergey");
            writer.newLine();
            writer.flush();
            writer.write("15");
            writer.newLine();
            writer.flush();
            writer.write("4000");
            writer.newLine();
            writer.flush();
            writer.write("5");
            writer.newLine();
            writer.flush();
            mess = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals("0.0", mess);
    }
}