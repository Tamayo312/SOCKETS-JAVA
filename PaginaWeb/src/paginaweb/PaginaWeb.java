/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paginaweb;
import java.net.*;
import java.io.*;
 
public class PaginaWeb {
    public static void main(String[] args) throws IOException {
         
        if (args.length != 1) {
            System.err.println("Usage: java EchoServer <port number>");
            System.exit(1);
        }
         
        int portNumber = Integer.parseInt(args[0]);
        
        System.out.println("Pagina Web");
        System.out.println("-------------------------------------------------");
        System.out.println("Esperando conexiones en..." + portNumber);
        while(true){
            try (
                ServerSocket serverSocket = new ServerSocket(Integer.parseInt(args[0]));
                Socket clientSocket = serverSocket.accept();     
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);                   
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            ) {
                //Recibo el mensaje

                    String mensaje = "";
                    mensaje = in.readLine();
                    if(mensaje != null){
                        System.out.println("Mensaje: " + mensaje);
                    }


            } catch (IOException e) {
                System.out.println("Exception caught when trying to listen on port "
                    + portNumber + " or listening for a connection");
                System.out.println(e.getMessage());
            }
        }
    }
}
