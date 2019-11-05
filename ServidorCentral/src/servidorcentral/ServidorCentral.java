/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorcentral;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.HashMap;

/**
 *
 * @author alumnoAD
 */
public class ServidorCentral {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SocketException, IOException {
        
        HashMap<String, Sensor> datos = new HashMap<>();
        
        byte[] recibirDatos = new byte[256];
        byte[] enviarDatos = new byte[256];
        
        int contador = 0;
        String hostName = args[1];
        int portNumber = Integer.parseInt(args[0]);
        int portNumberTCP = Integer.parseInt(args[1]);
        String mensajeTCP;
        
        DatagramSocket socketServidor = new DatagramSocket(portNumber);
        
        BufferedReader inFromUser =
        new BufferedReader(new InputStreamReader(System.in));
        
        System.out.println("ServidorCentral");
        System.out.println("-------------------------------------------------");
        System.out.println("Esperando conexiones en..." + portNumber);
        
        while(true){
            recibirDatos = new byte[256];
            DatagramPacket recibirPaquete = new DatagramPacket(recibirDatos, recibirDatos.length);

            socketServidor.receive(recibirPaquete);
            String datosSensor = new String(recibirPaquete.getData());
            //System.out.println("Datos recibidos: " + datosSensor);
            contador++;

            InetAddress IPAddress = recibirPaquete.getAddress();
            int port = recibirPaquete.getPort();

            String sensor[] = datosSensor.split("\\|");
            //System.out.println("Datos: " + sensor[0] + "|" + sensor[1] + "|" + sensor[2]);
            Sensor sensores = new Sensor(sensor[0], sensor[1], sensor[2]);
            datos.put(sensor[0], sensores);


            System.out.println("Recibido: " + datos.get(sensor[0]).getTipo()
            + "|" + datos.get(sensor[0]).getMedida() + "|" + datos.get(sensor[0]).getTiempo());
            
            //COMIENZO DEL ENVIO TCP
            if(contador==10){
                System.out.println("Enviando a... " + portNumberTCP);
                mensajeTCP = datos.get(sensor[0]).getTipo()
                            + "|" + datos.get(sensor[0]).getMedida()
                            + "|" + datos.get(sensor[0]).getTiempo();
                //System.out.println("MensajeTCP: " + mensajeTCP);
                try (
                    Socket echoSocket = new Socket("127.0.0.1", portNumberTCP);
                    PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
                    BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
                ){
                    out.printf(mensajeTCP);
                } catch (UnknownHostException e) {
                    System.err.println("Don't know about host " + hostName);
                    System.exit(1);
                } catch (IOException e) {
                    System.err.println("Couldn't get I/O for the connection to " + hostName);
                     System.exit(1);
                } 
                contador=0;
            }
        }
    }
} 

