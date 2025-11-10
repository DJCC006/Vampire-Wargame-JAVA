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
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import vampire_wargame.UsersYFichas.Usuario;
import vampire_wargame.UsersYFichas.controladorLogged;
import vampire_wargame.UsersYFichas.controladorUsuarios;

/**
 *
 * @author David
 */
public class ventanaLogIn {
    
    JFrame screen = new JFrame();
    JTextField usertxt = new JTextField();
    //JTextField contratxt = new JTextField();
    JPasswordField passwordfield = new JPasswordField(20);
    
    public ventanaLogIn(){
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
        usertxt.setBounds(90, 155, 300, 25);
        
        
        JLabel contraLabel = new JLabel("Ingrese su contraseña:");
        contraLabel.setBounds(90, 175, 250, 50);
        //contratxt.setBounds(90, 210, 300, 25);
        passwordfield.setBounds(90, 210, 300, 25);
        
        
        
        JButton btCreate = new JButton("Iniciar Sesion");
        btCreate.setBounds(100, 310, 250, 40);
        JButton btVolver= new JButton("Volver");
        btVolver.setBounds(100, 410, 250, 40);
        
        
         btCreate.addActionListener(new ActionListener(){
          @Override 
          public void actionPerformed(ActionEvent e){
              
              String txt=usertxt.getText();
              char[] tempPas = passwordfield.getPassword();
              String pssString = new String(tempPas);
              if(txt.equalsIgnoreCase("")|| pssString.equalsIgnoreCase("")){
                  JOptionPane.showMessageDialog(screen, "PORFAVOR LLENAR TODOS LOS CAMPOS");
              }else{
                char[] tempPass = passwordfield.getPassword();
                String passwordString = new String(tempPass);
                boolean check = checkAccount(usertxt.getText(), passwordString);
                if(check==true){
                    java.util.Arrays.fill(tempPass, '0');//limpieza de password
                    screen.dispose();
                    menuPrincipal ventana = new menuPrincipal();
                }
              } 
          }
                    
        });
        
        
         btVolver.addActionListener(new ActionListener(){
          @Override 
          public void actionPerformed(ActionEvent e){
              screen.dispose();
              menuInicial ventana = new menuInicial();
          }
                    
        });
        
        screen.add(passwordfield);
        screen.add(btCreate);
        screen.add(btVolver);
        screen.add(contraLabel);
        //screen.add(contratxt);
        screen.add(userlabel);
        screen.add(usertxt);
        screen.add(titulo);
        
        screen.setVisible(true);
    }
    
    
    public static void main(String[] args) {
        ventanaLogIn ventana= new ventanaLogIn();
    }
    
    
    private boolean checkAccount(String username, String password){
        char[] tempPass = passwordfield.getPassword();
        String passwordString = new String(tempPass);
        for(int i=0; i<controladorUsuarios.getInstancia().getDBUsers().size(); i++){
            try{
                //solo usuarios logged
                if(controladorUsuarios.getInstancia().getDBUsers().get(i).getStatus()==true){
                  Usuario user= controladorUsuarios.getInstancia().getDBUsers().get(i);
                    if(user.getUsername().equals(username)){
                        if(user.getPassword().equals(passwordString)){
                            JOptionPane.showMessageDialog(screen, "INICIO DE SESION EXITOSO");
                            controladorLogged.getInstancia().setUsuarioLogged(user);
                            System.out.println("Entra: "+user.getUsername());
                            return true;
                        }else{
                            JOptionPane.showMessageDialog(screen, "CONTRASEÑA INCORRECTA");
                            return false;
                        }
                    }  
                }
            }catch(NullPointerException e){
                System.out.println("Exception por algo");
            }
        }
        JOptionPane.showMessageDialog(screen, "EL NOMBRE DE USUARIO NO EXISTE");
        return false; //Senial de que no se encontro una cuenta bajo ese nombre
    }
    
    
    
}
