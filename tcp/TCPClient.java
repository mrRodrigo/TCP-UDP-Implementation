
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;
import java.nio.charset.Charset;

public class TCPClient {
  private Socket socket;
  private Scanner scanner;

  private TCPClient(String serverAddress, int serverPort) throws Exception {
    this.socket = new Socket(serverAddress, serverPort);
    this.scanner = new Scanner(System.in);
  }

  // Le constantemente o terminal
  private void start() throws IOException {
    String input;
    while (true) {
      input = scanner.nextLine();
      PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
      out.println(input);
      out.flush();
    }
  }

  // envia arquivo do tipo txt
  private void readTxt(String arq) {
    File file = new File(arq);
    try {
      FileInputStream fis = new FileInputStream(file);
      InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
      BufferedReader br = new BufferedReader(isr);
      String line;
      while ((line = br.readLine()) != null) {
        // process the line
        PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
        out.println(line);
        out.flush();
      }
      br.close();

    } catch (Exception ex) {
      System.out.print(ex);
    }

  }

  public static void main(String[] args) throws Exception {
    TCPClient client = new TCPClient("localhost", 12345);

    System.out.println("\r\nConnected to Server: " + client.socket.getInetAddress());
    String arq = "./teste.txt";
    client.readTxt(arq);
  }
}