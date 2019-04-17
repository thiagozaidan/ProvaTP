/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.*;
/**
 * @author Virginia
 */
public class Servidor{
 
   private int porta;
   private List<PrintStream> clientes;
   private List<String> listaClientes;
   private int numeroClientes = 0;
   
   public Servidor (int porta) {
     this.porta = porta;
     this.clientes = new ArrayList<>();
     this.listaClientes = new ArrayList<>();
   }
     
   public static void main(String[] args) throws IOException {
     // inicia o servidor na porta 12345
     new Servidor(12345).executa();
   }
   
   public void executa() throws IOException {
     ServerSocket servidor = new ServerSocket(this.porta);
     System.out.println("Porta 12345 aberta!");
     
     while (true) {
       // aceita um cliente
       Socket cliente = servidor.accept();
       System.out.println("Nova conexão com o cliente " +   
         cliente.getInetAddress().getHostAddress()
       );
       System.out.println((numeroClientes+1)+" pessoa(s) online");
       
       // adiciona saida do cliente à lista
       PrintStream ps = new PrintStream(cliente.getOutputStream());
       this.clientes.add(ps);
       
       // cria tratador de cliente numa nova thread
       TrataCliente tc = new TrataCliente(cliente.getInputStream(), this);
       new Thread(tc).start();
     }
     
  /*   for(int i = 0; i <= numeroClientes; i++){
         if(this.clientes.equals(this.clientes)){
             this.clientes = this.clientes + i;
         }
     }
   }
\*/
   }
    //testa se nomes são iguais, se for retorna true
             
   public void distribuiMensagem(String msg) {
     // envia msg para todo mundo
     for (PrintStream cliente : this.clientes){
         
         if (msg.contains(":ENTER42")){
            String[] novo = msg.split(":"); //Mensagem sem código
            String[] login = msg.split(" "); //Mensagem só com nome
            if (!listaClientes.contains(login[0])) listaClientes.add(login[0]);
            numeroClientes++;
            msg = novo[0];
         }
         if (msg.contains(":EXIT42")){
            String[] novo = msg.split(":"); //Mensagem sem código
            String[] login = msg.split(" "); //Mensagem só com nome
            for (int i = 0; i < numeroClientes; i++){
                if (login[0].equals(listaClientes.get(i))){
                    listaClientes.remove(login[0]);
                }
            }
            if (numeroClientes != 0) numeroClientes--;
            msg = novo[0];
         }
         
         //{"Mensagem=" + msg + "Online=" + (clientes.size()+1) + "Nomes=" + listanomes}
         String on = numeroClientes + " ";
         JSONObject json = new JSONObject();
         json.put("mensagem", msg);
         json.put("online",on);
         json.put("lista",listaClientes);
         
         cliente.println(json);
     }
   }
}