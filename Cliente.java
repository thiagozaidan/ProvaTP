package logica;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import javax.swing.*;

public class Cliente {
   public static void main(String[] args) 
         throws UnknownHostException, IOException {
     // dispara cliente
     new Cliente("127.0.0.1","padrão", 12345).executa();
   }
   
   private String host;
   private int porta;
   private String login;
   
   public Cliente (String host, String login, int porta) {
     this.host = host;
     this.porta = porta;
     this.login = login;
   }
   
   public String getLogin(){
       return login;
   }
   
   public void executa() throws UnknownHostException, IOException {
     Socket cliente = new Socket(this.host, this.porta);
     System.out.println("O cliente se conectou ao servidor!");
 
     // thread para receber mensagens do servidor
     Recebedor r = new Recebedor(cliente.getInputStream(), new JTextArea(), new JLabel(), new JTextArea());
     new Thread(r).start();
     
     // lê msgs do teclado e manda pro servidor
     Scanner teclado = new Scanner(System.in);
     PrintStream saida = new PrintStream(cliente.getOutputStream());
     while (teclado.hasNextLine()) {
       saida.println(teclado.nextLine());
     }
     
     saida.close();
     teclado.close();
     cliente.close();    
   }
 }