/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;




/**
 *
 * @author Virginia
 */
public class Recebedor implements Runnable {

    private InputStream servidor;
    private JTextArea txtBox;
    private JLabel nPessoas;
    private JTextArea txtLista;
    private ArrayList lista;

    public Recebedor(InputStream servidor, JTextArea txtBox, JLabel nPessoas, JTextArea txtLista ) {
        this.servidor = servidor;
        this.txtBox = txtBox;
        this.nPessoas = nPessoas;
        this.txtLista = txtLista;
    }

    @Override
    public void run() {
        // recebe msgs do servidor e imprime na tela
        Scanner s = new Scanner(this.servidor);
        while (s.hasNextLine()) {
            JSONObject json = new JSONObject();
            JSONParser parser = new JSONParser();
            String msg;
            String online;//quantidade de clientes
            String msgservidor = s.nextLine();
            lista = new ArrayList<>();
            
            try {
                json = (JSONObject) parser.parse(msgservidor);
            } catch (ParseException ex) {
                Logger.getLogger(Recebedor.class.getName()).log(Level.SEVERE, null, ex);
            }
            msg = (String) json.get("mensagem");
            online = (String) json.get("online");
            
            String[] aux = online.split(" ");
            int n = Integer.parseInt(aux[0]);
            
            lista = (ArrayList) json.get("lista"); 
        //    if(msg == null || msg.isEmpty()){
             //   txtBox.setText(txtBox.getText()+"\n"+ "oi");
         //   } else {
            txtBox.setText(txtBox.getText()+"\n"+ msg);
        //    }
            nPessoas.setText(online + "pessoa(s) online");
            txtLista.setText("");
            System.out.print(lista);
            for (int i = 0; i < lista.size(); i++){
                txtLista.append(lista.get(i) + "\n");
            }
            
           
        }
    }
    }

