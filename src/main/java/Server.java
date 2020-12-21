import ru.vsu.sample.Menu.DataBase.DataBase.FoodDatabaseLocal;
import ru.vsu.sample.Menu.Dish;
import ru.vsu.sample.Menu.Singularity;

import java.io.*;
import java.math.BigDecimal;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(8000))
        {
            System.out.println("Server Started!");
            while (true){
                Socket socket = server.accept();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                new Thread(() -> {
                    Client client = creatClient(reader);
                    while (true) {
                        String request = null;
                        try {
                            request = reader.readLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if (request.equals("0")) {
                            try {
                                reader.close();
                                writer.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            System.out.println("closed connection");
                            break;
                        }
                        System.out.println("Запрос:" + request);
                        String answer = "";
                        switch (request){
                            case ("1"):
                                getMenu(writer);
                                break;
                            case ("2"):
                                getCheck(writer, client);
                                break;
                            case ("3"):
                                int id3 = 0;
                                int number3 = 0;
                                try {
                                    id3 = Integer.parseInt(reader.readLine());
                                    number3 = Integer.parseInt(reader.readLine());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                if (putInCheck(client, id3, number3))answer = "добавленно";
                                else answer = "не добавленно";
                                break;
                            case ("4"):
                                int id4 = 0;
                                int number4 = 0;
                                try {
                                    id4 = Integer.parseInt(reader.readLine());
                                    number4 = Integer.parseInt(reader.readLine());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                removeFromCheck(client, id4, number4);
                                answer = "удаленно";
                                break;
                            case ("5"):
                                answer = String.valueOf(calculateCheck(client));
                                break;
                        }
                        try {
                            writer.write(answer);
                            writer.newLine();
                            writer.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }
//TODO тесты

    private static void getMenu(BufferedWriter writer){
        FoodDatabaseLocal foodDatabaseLocal = new FoodDatabaseLocal();
        List<Dish> list = foodDatabaseLocal.downloadBD();
        try {
            writer.write(list.size());
            writer.newLine();
            writer.flush();
            for (Dish dish : list){
                String answer = dish.getId() + "-" + dish.getName() + "-"
                        + dish.getTimeInMinutes() + "-" + dish.getPrice();
                writer.write(answer);
                writer.newLine();
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void getCheck(BufferedWriter writer, Client client){
        List<Dish> order = client.getOrder();
        List<Integer> numbers = client.getNumbers();
        try {
            writer.write(order.size());
            writer.newLine();
            writer.flush();
            for (int i = 0; i < order.size(); i++){
                String answer = order.get(i).getName() + "-" + numbers.get(i) + "-" +
                        order.get(i).getPrice().multiply(new BigDecimal(numbers.get(i)));
                writer.write(answer);
                writer.newLine();
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean putInCheck(Client client, int id, int number){
        Dish dish = searchDishToId(id);
        return client.putInOrder(dish, number);
    }

    private static void removeFromCheck(Client client, int id, int number){
        Dish dish = searchDishToId(id);
        client.deleteFromOrder(dish, number);
    }

    private static BigDecimal calculateCheck(Client client){
        return CalculateCheck.calculateOrder(client);
    }

    public static Dish searchDishToId(int id){
        FoodDatabaseLocal foodDatabaseLocal = new FoodDatabaseLocal();
        List<Dish> list = foodDatabaseLocal.downloadBD();
        Dish dish = null;
        for (Dish d : list){
            if (d.getId() == id){
                dish = d;
                break;
            }
        }
        return dish;
    }
    private static Client creatClient(BufferedReader reader){
        String name = null;
        int time = 0;
        BigDecimal hasMoney = null;
        try {
            name  = reader.readLine();
            time = Integer.parseInt(reader.readLine());
            hasMoney = new BigDecimal(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Client(name, time, hasMoney, Singularity.NOSINGULARITY);
    }
}
