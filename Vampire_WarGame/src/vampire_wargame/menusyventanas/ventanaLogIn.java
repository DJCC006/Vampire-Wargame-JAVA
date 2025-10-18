/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vampire_wargame.menusyventanas;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author David
 */
public class ventanaLogIn {
    
    public ventanaLogIn(){
         JFrame screen = new JFrame();
        screen.setSize(500, 600);  //Tamaño standard para menus
        screen.setResizable(false);
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screen.setLocationRelativeTo(null);
        screen.setLayout(null);
        
        JLabel titulo = new JLabel("INICIAR SESION");
        titulo.setFont(new Font("Serif", Font.BOLD, 35));
        titulo.setBounds(110, 50, 500, 50);
        
        JLabel userlabel = new JLabel("Ingrese su nombre de usuario:");
        userlabel.setBounds(90, 100, 250, 50);
        JTextField usertxt = new JTextField();
        usertxt.setBounds(90, 155, 300, 25);
        
        
        JLabel contraLabel = new JLabel("Ingrese su contraseña:");
        contraLabel.setBounds(90, 175, 250, 50);
        JTextField contratxt = new JTextField();
        contratxt.setBounds(90, 210, 300, 25);
        
        
        
        JButton btCreate = new JButton("Iniciar Sesion");
        btCreate.setBounds(100, 310, 250, 40);
        JButton btVolver= new JButton("Volver");
        btVolver.setBounds(100, 410, 250, 40);
        
        
         btCreate.addActionListener(new ActionListener(){
          @Override 
          public void actionPerformed(ActionEvent e){
              screen.dispose();
              menuPrincipal ventana = new menuPrincipal();
          }
                    
        });
        
        
         btVolver.addActionListener(new ActionListener(){
          @Override 
          public void actionPerformed(ActionEvent e){
              screen.dispose();
              menuInicial ventana = new menuInicial();
          }
                    
        });
        
        
        screen.add(btCreate);
        screen.add(btVolver);
        screen.add(contraLabel);
        screen.add(contratxt);
        screen.add(userlabel);
        screen.add(usertxt);
        screen.add(titulo);
        
        screen.setVisible(true);
    }
    
    
    public static void main(String[] args) {
        ventanaLogIn ventana= new ventanaLogIn();
    }
}
