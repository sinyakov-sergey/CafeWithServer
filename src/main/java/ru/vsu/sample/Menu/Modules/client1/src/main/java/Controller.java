import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Controller {

    public static void main(String[] args) {
        try{
            Socket socket = new Socket("127.0.0.1",8000);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("Connected to server");
            creatClient(writer);
            while (true) {
                printController();
                Scanner in = new Scanner(System.in);
                String request = in.nextLine();
                System.out.println("XXXXXXXXXXXXXXXXXXXXX");
                sendRequest(writer, request);
                if (request.equals("0")){
                    writer.close();
                    reader.close();
                    socket.close();
                    break;
                }
                if (request.equals("1")){
                    getMenuOrCheck(reader, 1);
                }
                if (request.equals("2")){
                    getMenuOrCheck(reader, 2);
                }
                if (request.equals("3")){
                    putInCheck(in, writer, reader);
                }
                if (request.equals("4")){
                    removeFromCheck(in, writer, reader);
                }
                if (request.equals("5")){
                    String answerFromServer = reader.readLine();
                    if (answerFromServer.equals("")){
                        answerFromServer = reader.readLine();
                    }
                    System.out.println("Ответ:");
                    System.out.println(answerFromServer);
                }
                while (reader.ready()){
                    reader.readLine();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void sendRequest(BufferedWriter writer, String request){
        try {
            writer.write(request);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void creatClient(BufferedWriter writer){
        Scanner in = new Scanner(System.in);
        System.out.println("Введите имя:");
        String name = in.nextLine();
        sendRequest(writer, name);
        System.out.println("Введите время, которым вы распологаете:");
        String time = in.nextLine();
        sendRequest(writer, time);
        System.out.println("Введите кол-во денег, которое вы можете потратить:");
        String hasMoney = in.nextLine();
        sendRequest(writer, hasMoney);
    }

    private static void printController(){
        System.out.println("XXXXXXXXXXXXXXXXXXXXX");
        System.out.println("---------------------");
        System.out.println("| 0-закрыть сокет   |");
        System.out.println("| 1-показать меню   |");
        System.out.println("| 2-показать чек    |");
        System.out.println("| 3-добавить в чек  |");
        System.out.println("| 4-удалить из чека |");
        System.out.println("| 5-посчитать чек   |");
        System.out.println("---------------------");
        System.out.println("Input a number: ");
    }

    private static void getMenuOrCheck(BufferedReader reader, int v){
        if (v == 1)System.out.println("ID-НАЗВАНИЕ-ВРЕМЯ-ЦЕНА");
        else System.out.println("НАЗВАНИЕ-КОЛИЧЕСТВО-ЦЕНА");
        try {
            int c = reader.read();
            for (int i = 0; i <= c; i++) {
                String line = reader.readLine();
                if (line.equals("") && reader.ready()){
                    i--;
                    continue;
                }
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void putInCheck(Scanner in, BufferedWriter writer, BufferedReader reader){
        System.out.println("введите айди блюда: ");
        String request2 = in.nextLine();
        sendRequest(writer, request2);
        System.out.println("введите количество которое хотите добавить: ");
        String request3 = in.nextLine();
        sendRequest(writer, request3);
        String answerFromServer = null;
        try {
            answerFromServer = reader.readLine();
            if (answerFromServer.equals("")){
                answerFromServer = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Ответ:");
        System.out.println(answerFromServer);
    }

    private static void removeFromCheck(Scanner in, BufferedWriter writer, BufferedReader reader){
        System.out.println("введите айди блюда: ");
        String request2 = in.nextLine();
        sendRequest(writer, request2);
        System.out.println("введите количество которое хотите удалить: ");
        String request3 = in.nextLine();
        sendRequest(writer, request3);
        String answerFromServer = null;
        try {
            answerFromServer = reader.readLine();
            if (answerFromServer.equals("")){
                answerFromServer = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Ответ:");
        System.out.println(answerFromServer);
    }

}

