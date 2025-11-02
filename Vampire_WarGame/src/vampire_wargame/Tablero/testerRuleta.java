/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vampire_wargame.Tablero;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author David
 */
public class testerRuleta {
    private ruletaGen ruletaJugador;
    
    public testerRuleta(){
       ruletaJugador = new ruletaGen();
    }
    
    
    public static void main(String[] args) {
        testerRuleta ventana = new testerRuleta();
    }
}
