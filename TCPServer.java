
// Recebe um pacote de algum cliente
// Separa o dado, o endere�o IP e a porta deste cliente
// Imprime o dado na tela

import java.io.*;
import java.net.*;
import java.util.Date;

class TCPServer {
  public static void main(String args[]) throws Exception {
    try {
      // Instancia o ServerSocket ouvindo a porta 12345
      ServerSocket servidor = new ServerSocket(12345);
      System.out.println("Servidor ouvindo a porta 12345");
      while (true) {
        // o método accept() bloqueia a execução até que
        // o servidor receba um pedido de conexão
        Socket client = servidor.accept();

        String data = null;
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

        while ((data = in.readLine()) != null) {
          System.out.println("\r\nMessage from " + client.getInetAddress().getHostAddress() + ": " + data);

        }
      }
    } catch (Exception e) {
      System.out.println("Erro: " + e.getMessage());
    }

  }
}
