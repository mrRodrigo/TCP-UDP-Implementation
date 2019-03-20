
import java.io.*;
import java.net.*;

class UDPServer {
  public static void main(String args[]) throws Exception {
    
    // cria socket do servidor com a porta 9876
    DatagramSocket serverSocket = new DatagramSocket(9876);

    byte[] receiveData = new byte[1024];
    while (true) {
      // declara o pacote a ser recebido
      DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

      // recebe o pacote do cliente
      serverSocket.receive(receivePacket);

      // pega conteudo do pacote recebido
      String sentence = new String(receivePacket.getData());

      System.out.print(sentence);
    }
  }
}
