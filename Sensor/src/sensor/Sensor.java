/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sensor;
import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;

public class Sensor {
    public static void main(String[] args) throws IOException {

        /*if (args.length != 1) {
             System.out.println("Usage: java QuoteClient <hostname>");
             return;
        }*/
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            
        DatagramSocket socket = new DatagramSocket(); // get a datagram socket
        InetAddress address = InetAddress.getByName(args[0]);
        int portNumber = Integer.parseInt(args[1]);
        
        System.out.println("Sensores");
        System.out.println("-------------------------------------------------");
        System.out.println("Puerto: " + portNumber);
        System.out.println("Adress: " + address);
            
        while(true){// send request
            //Sentencias que recogen y generan la informacion del sensor--------
            System.out.println("---------------------------------------------");
            System.out.println("Introduce el tipo de sensor: ");
            String tipo = br.readLine();
            int medida = (int) (Math.random()* 31);
            String timeStamp = new SimpleDateFormat("h:mm a").format(new java.util.Date());
            //System.out.println("Tipo: " + tipo);
            //System.out.println("Medida: " + medida);
            //System.out.println("Hora: " + timeStamp);
            
            String mensaje = tipo + "|" + Integer.toString(medida) + "|" + timeStamp;
            //System.out.println("Mensaje: " + mensaje);
            byte[] buf;
            buf = mensaje.getBytes();
            String bufString = new String(buf);
            //------------------------------------------------------------------
            DatagramPacket packet = new DatagramPacket(buf, buf.length, address, portNumber);
            socket.send(packet);
            System.out.println("Se ha enviado: " + bufString);
        
            /*    // get response
            packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);

                // display response
            String received = new String(packet.getData(), 0, packet.getLength());
            System.out.println("Quote of the Moment: " + received);*/
        }
        //socket.close();
    }
}
