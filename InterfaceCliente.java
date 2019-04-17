package gui;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;
import logica.*;
import org.json.simple.*;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class InterfaceCliente extends javax.swing.JFrame {
    private Scanner teclado;
    private PrintStream saida;
    private Recebedor r;
    private String login;
    private String ip;
    private boolean novo = true; //Para mandar "entrou no chat"

    public void setLogin(String login){
        this.login = login;
    }
    
    public void setIP(String ip){
        this.ip = ip;
    }

 
    //Interface Gráfica sendo visualizada como cliente
    public InterfaceCliente() throws IOException {
        initComponents();
        this.setVisible(true);
        
        //jLabel4
        nPessoas.setText("0 pessoa(s) online");
        
        Thread one = new Thread() {
            public void run() {
            //inicializando Socket
            //Cliente c = new Cliente(ip, login, 12345);
            Socket cliente = null;
                try {
                    cliente = new Socket(ip,12345);
                } catch (IOException ex) {
                    Logger.getLogger(InterfaceCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
            System.out.println("O cliente "+ login +" se conectou ao servidor!");
           
            // thread para receber mensagens do servidor
            Recebedor r = null;
                try {
                    r = new Recebedor(cliente.getInputStream(),msgOut, nPessoas, jTextAreaNomes);
                } catch (IOException ex) {
                    Logger.getLogger(InterfaceCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
            new Thread(r).start();

            // lê msgs do teclado e manda pro servidor
            teclado = new Scanner(System.in);
            saida = null;
                try {
                    saida = new PrintStream(cliente.getOutputStream());
                
                    if (novo){//se for novo cliente
                        String texto = login + " entrou no chat!:ENTER42";
                        // Questão 1
                        // Thiago Cassiano Campos Abreu
                        String texto1 = "Seja bem vindo(a)," + login + "!"; 
                        saida.println(texto);
                        saida.println(texto1);
                        novo = false;//para aparecer só uma vez
                    }
                } catch (IOException ex) {
                    Logger.getLogger(InterfaceCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
            while (teclado.hasNextLine()) {
              saida.println(teclado.nextLine());
            }

            saida.close();
            teclado.close();
                try {    
                    cliente.close();
                } catch (IOException ex) {
                    Logger.getLogger(InterfaceCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
    
            }  
        };
        one.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        msgOut = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        msgIn = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextAreaNomes = new javax.swing.JTextArea();
        nPessoas = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        msgOut.setEditable(false);
        msgOut.setColumns(20);
        msgOut.setRows(5);
        jScrollPane1.setViewportView(msgOut);

        msgIn.setColumns(20);
        msgIn.setRows(5);
        jScrollPane2.setViewportView(msgIn);

        jButton1.setText("Enviar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Chat do Uol");

        jLabel2.setText("Escreva sua mensagem");

        jLabel3.setText("Quem está online?");

        jTextAreaNomes.setEditable(false);
        jTextAreaNomes.setColumns(10);
        jTextAreaNomes.setRows(5);
        jScrollPane3.setViewportView(jTextAreaNomes);

        nPessoas.setText("nPessoas");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(38, 38, 38)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(nPessoas)))
                .addContainerGap(57, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(nPessoas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)))
                .addGap(7, 7, 7))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        // Questão 3
        // Thiago Cassiano Campos Abreu
        
        Som som = new Som();
        som.Som();
        
        if(msgIn.getText().equals("") || msgIn.getText().equals(" ")){
           JOptionPane.showMessageDialog(null, "Por favor, insira uma mensagem com alguns caracteres!");
        } else {
             saida.println(login + ":" + msgIn.getText());
             msgIn.setText("");
        }
        
 
       
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        //não funciona aqui
        saida.println(login + " saiu do chat!:EXIT42");
        
    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        saida.println(login + " saiu do chat!:EXIT42");
    }//GEN-LAST:event_formWindowClosing

    public static void main(String args[]) {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InterfaceCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfaceCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfaceCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfaceCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new InterfaceCliente().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(InterfaceCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextAreaNomes;
    private javax.swing.JTextArea msgIn;
    private javax.swing.JTextArea msgOut;
    private javax.swing.JLabel nPessoas;
    // End of variables declaration//GEN-END:variables
}
