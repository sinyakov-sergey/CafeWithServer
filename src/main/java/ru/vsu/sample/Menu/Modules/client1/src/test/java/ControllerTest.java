import org.junit.Test;

import java.io.*;
import java.net.Socket;

import static org.junit.Assert.*;

public class ControllerTest {



    @Test
    public void sendAndReadRequestTest() {
        Socket socket = null;
        String mess = "";
        try {
            socket = new Socket("127.0.0.1",8000);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Controller.sendRequest(writer, "sergey");
            Controller.sendRequest(writer, "10");
            Controller.sendRequest(writer, "200");
            Controller.sendRequest(writer, "5");
            mess = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals("0.0", mess);
    }
}