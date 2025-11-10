/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vampire_wargame.menusyventanas;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;
import vampire_wargame.Tablero.gamePlay;
import vampire_wargame.UsersYFichas.Usuario;
import vampire_wargame.UsersYFichas.controladorLogged;
import vampire_wargame.UsersYFichas.controladorUsuarios;

/**
 *
 * @author David
 */
public class ventanaOponentes {
    public ventanaOponentes(){
        JFrame screen = new JFrame();
        screen.setSize(800, 600);  //Tamaño standard para menus
        screen.setResizable(false);
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screen.setLocationRelativeTo(null);
        screen.setLayout(null);
        
        
        Container contentPane = screen.getContentPane();
        contentPane.setBackground(Color.DARK_GRAY);
        
        int grosor =3;
        Color colorBorde= new Color(255,215,0);
        Border bordeBoton= BorderFactory.createLineBorder(colorBorde,grosor);
        
        
        
        JLabel titulo = new JLabel("SELECCIONE SU OPONENTE");
        titulo.setBounds(100, 50, 700, 100);
        titulo.setFont(new Font("Serif", Font.BOLD, 40));
        titulo.setForeground(new Color(255,215,0));
        
        
//        JLabel player1= new JLabel();
//        player1.setText("JUGADOR 1: "
//                +controladorLogged.getInstancia().getUsuarioLogged().getUsername().toUpperCase());
//        player1.setFont(new Font("Serif", Font.BOLD, 20));
//        player1.setBounds(100, 80, 400, 250);
//        player1.setForeground(new Color(255,255,0));
//        
        
//        JLabel player2= new JLabel();
//        player2.setText("JUGADOR 2:");
//        player2.setFont(new Font("Serif", Font.BOLD, 20));
//        player2.setBounds(500, 100, 200, 150);
//        player2.setForeground(new Color(255,255,0));
//        
        
        JComboBox<String> Usernames = new JComboBox<>();
        Usernames.setBounds(300, 150, 200, 25);
        for(Usuario user: controladorUsuarios.getInstancia().getDBUsers() ){
            if(!user.getUsername().equals(controladorLogged.getInstancia().getUsuarioLogged().getUsername()) && user.getStatus()==true){
                String names = user.getUsername();
                Usernames.addItem(names);
            }
            
        }
        
        JButton btSalir = new JButton("Volver");
        btSalir.setBounds(300, 390, 200, 50);
        btSalir.setBackground(Color.GRAY);
        btSalir.setBorder(bordeBoton);
        
        
        JButton btJugar = new JButton("Jugar");
        btJugar.setBounds(300, 290, 200, 50);
        btJugar.setBackground(Color.GRAY);
        btJugar.setBorder(bordeBoton);
        
        
        btJugar.addActionListener(new ActionListener(){
          @Override 
          public void actionPerformed(ActionEvent e){
              String buscador = (String) Usernames.getSelectedItem();
              Usuario CONTRICANTE= controladorUsuarios.getInstancia().buscarUsuario(buscador);
              gamePlay ventana = new gamePlay(controladorLogged.getInstancia().getUsuarioLogged(), CONTRICANTE);
              System.out.println("PLAYER: "+controladorLogged.getInstancia().getUsuarioLogged().getUsername() + " | CONTRIANTE: "+CONTRICANTE.getUsername());
              screen.dispose();
             
          }
                    
        });
        
        
        
        btSalir.addActionListener(new ActionListener(){
          @Override 
          public void actionPerformed(ActionEvent e){
              screen.dispose();
             menuPrincipal ventana = new menuPrincipal();
          }
                    
        });
        
        
        //screen.add(player1);
        //screen.add(player2);
        screen.add(Usernames);
        screen.add(btSalir);
        screen.add(titulo);
        screen.add(btJugar);
        
        screen.setVisible(true);
    }
    
    
    
    public static void main(String[] args) {
        ventanaOponentes ventana = new ventanaOponentes();
    }
}
