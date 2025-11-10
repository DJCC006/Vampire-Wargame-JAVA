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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import vampire_wargame.UsersYFichas.Usuario;
import vampire_wargame.UsersYFichas.controladorLogged;

/**
 *
 * @author David
 */
public class ventanaPerfil {
    Usuario usuarioLogged= controladorLogged.getInstancia().getUsuarioLogged();
    JFrame screen = new JFrame();
    
    public ventanaPerfil(){
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
        
        
        
        JLabel titulo = new JLabel("MI PERFIL");
        titulo.setBounds(100, 50, 700, 100);
        titulo.setFont(new Font("Serif", Font.BOLD, 50));
        titulo.setForeground(new Color(255,215,0));
        
        //Labels Mostrando informacion de jugador
        JLabel usernameLabel = new JLabel("Username: "+usuarioLogged.getUsername());
        usernameLabel.setBounds(100, 150, 500, 70);
        usernameLabel.setFont(new Font("Serif", Font.BOLD, 25));
        usernameLabel.setForeground(new Color(255,215,0));
        
        
        JLabel puntosLabel = new JLabel("Puntos: "+usuarioLogged.getPoints());
        puntosLabel.setBounds(100, 200, 500, 70);
        puntosLabel.setFont(new Font("Serif", Font.BOLD, 25));
        puntosLabel.setForeground(new Color(255,215,0));
        
        JLabel dateLabel = new JLabel("Fecha de Ingreso: "+usuarioLogged.getCreacionDate());
        dateLabel.setBounds(100, 250, 500, 70);
        dateLabel.setFont(new Font("Serif", Font.BOLD, 25));
        dateLabel.setForeground(new Color(255,215,0));
        

        
        
        
        //Configuracion de botones
        if(usuarioLogged.getStatus()==true){
            JLabel statusLabel = new JLabel("Estado de Cuenta: ACTIVO");
            statusLabel.setBounds(100, 300, 500, 70);
            statusLabel.setFont(new Font("Serif", Font.BOLD, 25));
            statusLabel.setForeground(new Color(255,215,0));
            screen.add(statusLabel);
        }else{
            JLabel statusLabel = new JLabel("Estado de Cuenta: DESACTIVA");
            statusLabel.setBounds(100, 300, 500, 70);
            statusLabel.setFont(new Font("Serif", Font.BOLD, 25));
            statusLabel.setForeground(new Color(255,215,0));
            screen.add(statusLabel);
        }
        
        JButton btChangePssw = new JButton("Cambiar Contraseña");
        btChangePssw.setBounds(500, 250, 200, 50);
        btChangePssw.setBackground(Color.GRAY);
        btChangePssw.setBorder(bordeBoton);
        
        btChangePssw.addActionListener(new ActionListener(){
          @Override 
          public void actionPerformed(ActionEvent e){
              
              boolean coincidencia=false;
              String actual = JOptionPane.showInputDialog(screen, "PARA PROCEDER, INTRODUZCA SU CONTRASÑA ACTUAL:");
              if(actual.equals(controladorLogged.getInstancia().getUsuarioLogged().getPassword())){
                  boolean repeat=true;
                    while(repeat){
                         String answer =JOptionPane.showInputDialog(screen, "Ingrese la nueva contraseña "
                            + "\n(maximo 5 caracteres y uso de signos especiales) :");

                      boolean pass= passChanger(answer);
                      if(pass){
                          JOptionPane.showMessageDialog(screen, "CONTRASEÑA CAMBIADA EXITOSAMENTE");
                          controladorLogged.getInstancia().getUsuarioLogged().setPassword(answer);
                          repeat=false;
                      }else{
                          JOptionPane.showMessageDialog(screen, "CONTRASEÑA NO CUMPLE CON LOS REQUISITOS. INTENTE DE NUEVO");
                      }

                    }
              }else{
                  JOptionPane.showMessageDialog(screen, "CONTRASEÑA INCORRECTA");
              }
          }
                    
        });
        
        
        
        JButton btBorrar = new JButton("Borrar Cuenta");
        btBorrar.setBounds(500, 320, 200, 50);
        btBorrar.setBackground(Color.GRAY);
        btBorrar.setBorder(bordeBoton);
        
        btBorrar.addActionListener(new ActionListener(){
          @Override 
          public void actionPerformed(ActionEvent e){
              boolean coincidencia=false;
              String actual = JOptionPane.showInputDialog(screen, "PARA PROCEDER, INTRODUZCA SU CONTRASÑA ACTUAL:");
              if(actual.equals(controladorLogged.getInstancia().getUsuarioLogged().getPassword())){
                  controladorLogged.getInstancia().getUsuarioLogged().setStatus(false);
                  JOptionPane.showMessageDialog(screen, "BORRANDO CUENTA...");
                  menuInicial ventana = new menuInicial();
                  screen.dispose();
              }else{
                  JOptionPane.showMessageDialog(screen, "CONTRASEÑA INCORRECTA");
              }
             
          }
                    
        });
        
        JButton btSalir = new JButton("Volver");
        btSalir.setBounds(500, 390, 200, 50);
        btSalir.setBackground(Color.GRAY);
        btSalir.setBorder(bordeBoton);
        
        btSalir.addActionListener(new ActionListener(){
          @Override 
          public void actionPerformed(ActionEvent e){
              screen.dispose();
             menuPrincipal ventana = new menuPrincipal();
          }
                    
        });
        
        screen.add(usernameLabel);
        screen.add(puntosLabel);
        screen.add(dateLabel);
        
        screen.add(btChangePssw);
        screen.add(btBorrar);
        screen.add(btSalir);
        screen.add(titulo);
        
        screen.setVisible(true);
    }
    
    
    
    public static void main(String[] args) {
        ventanaPerfil ventana = new ventanaPerfil();
    }
    
    
    
    private boolean passChanger(String newPass){
        boolean revision = checkPassword(newPass);
        if(revision){
            return true;
        }
        return false;
    }
    
    
    private boolean checkPassword(String pss){
        //Verificadores 
        boolean ver1=false;//length
        //boolean ver2=false;//nums
        boolean ver3=false;//charsespeciales
        boolean ver4=false;//no espacios
        boolean ver5=false;
        
        //Verificacion de length
        //String password =contratxt.getText();
        //int lengthpss = password.length();
        
        
        //Retriever de password
        String newPasse=pss;
        int lengthpss = newPasse.length();
        System.out.println("lengthpss: "+lengthpss);
        
        
        //System.out.println("Longitud: "+lengthpss);
        if(lengthpss==5){
            ver1=true;   
        }else if(lengthpss<5){
            System.out.println("Tiene una longitud menor a 5");
        }else if(lengthpss>5){
            System.out.println("Tiene una longitud mayor a 5");
        }
        
        
        
        
        
        
        
        
        //verificar que contenga caracteres especiales
        String specialChars ="!@#$%&*()'+,-./:;<=>?[]^_`{|}";
        for(int i=0; i<newPasse.length(); i++){
            char passchar= newPasse.charAt(i);
            String stChar = Character.toString(passchar);
            //String StChar = Character.toString(password.charAt(i));
            if(specialChars.contains(stChar)){
                ver3=true;
                System.out.println("Contiene un character especial");
                break;   
            }
        }
        
        
        //verificar que contenga caracter normal
        String abc="abcdefghijklmnñopqrstuvwxyz";
        String ABC="ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
        for(int i=0;i<newPasse.length(); i++){
            char passchar= newPasse.charAt(i);
            String stChar = Character.toString(passchar);
            //String stChar=Character.toString(password.charAt(i));
            if(abc.contains(stChar) || ABC.contains(stChar)){
                ver5=true;
                System.out.println("Contiene una letra normal");
                break;
            }
        }
        
        
        
        //Verificar que no tiene espacios en blanco
        boolean checkSpaces=false;
        for(int i=0; i<newPasse.length(); i++){
            char letra = newPasse.charAt(i);
            if(letra==' '){
                checkSpaces=true;
                System.out.println("Se encontro un espacio en blanco");
                break;
            }
        }
        if(checkSpaces!=true){
            ver4=true;
        }
        
        //checkeo de cada una de las condiciones
        if(ver1==true && ver3==true && ver4==true && ver5==true){
            return true;
        }else{
             if(ver1==false){
                 JOptionPane.showMessageDialog(screen, "La contraseña debe tener una longitud exact a de 5 caracteres");
             }
             
             if(ver3==false){
                 JOptionPane.showMessageDialog(screen, "La contraseña debe contener por lo menos un caracter especial");
             }
             
             if(ver4==false){
                 JOptionPane.showMessageDialog(screen, "La contraseña no puede contener espacios en blanco");
             }
             
             if(ver5==false){
                 JOptionPane.showMessageDialog(screen, "La contraseña debe tener por lo menos una letra");
             }
            
        }
        
        return false;
    }
    
    
}
