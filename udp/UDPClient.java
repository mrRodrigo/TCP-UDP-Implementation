
import java.io.*; // classes para input e output streams e
import java.net.*;// DatagramaSocket,InetAddress,DatagramaPacket
import java.nio.charset.Charset;

class UDPClient {

  static void readTxt(String arq, DatagramSocket clientSocket) {
    File file = new File(arq);
    try {
      FileInputStream fis = new FileInputStream(file);
      InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
      BufferedReader br = new BufferedReader(isr);
      String line;
      while ((line = br.readLine()) != null) {

        byte[] sendData = new byte[1024];
        sendData = line.getBytes();

        InetAddress IPAddress = InetAddress.getByName("localhost");

        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);

        // envia o pacote
        clientSocket.send(sendPacket);

      }
      // fecha o cliente
      clientSocket.close();
      br.close();

    } catch (Exception ex) {
      System.out.print(ex);
    }

  }

  public static void main(String args[]) throws Exception {
    // cria o stream do teclado
    BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

    // declara socket cliente
    DatagramSocket clientSocket = new DatagramSocket();
    readTxt("./teste.txt", clientSocket);

  }
}
