/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vampire_wargame.menusyventanas;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
        
        
        JLabel titulo = new JLabel("SELECCIONE SU OPONENTE");
        titulo.setBounds(100, 50, 700, 100);
        titulo.setFont(new Font("Serif", Font.BOLD, 40));
        
        JComboBox<String> Usernames = new JComboBox<>();
        Usernames.setBounds(100, 150, 200, 25);
        for(Usuario user: controladorUsuarios.getInstancia().getDBUsers() ){
            if(!user.getUsername().equals(controladorLogged.getInstancia().getUsuarioLogged().getUsername())){
                String names = user.getUsername();
                Usernames.addItem(names);
            }
            
        }
        
        JButton btSalir = new JButton("Volver");
        btSalir.setBounds(500, 390, 200, 50);
        
        
        JButton btJugar = new JButton("Jugar");
        btJugar.setBounds(300, 390, 200, 50);
        
        
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
