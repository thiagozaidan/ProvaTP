/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author 2017953649
 */
// Questão 3
// Thiago Cassiano Campos Abreu
class Som {
    public void Som() {
    try{
      File file = new File("/home/2017953649/NetBeansProjects/pikachu-7952 (online-audio-converter.com).wav");
      AudioInputStream stream = AudioSystem.getAudioInputStream(file);
      Clip clip = AudioSystem.getClip();
      clip.open(stream);
      clip.start();
 
      // sleep to allow enough time for the clip to play
      Thread.sleep(500);
 
      stream.close();
 
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
    }
  }   
}
