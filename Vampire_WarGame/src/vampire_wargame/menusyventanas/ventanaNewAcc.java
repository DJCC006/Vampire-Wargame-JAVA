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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
import vampire_wargame.UsersYFichas.Usuario;
import vampire_wargame.UsersYFichas.controladorLogged;
import vampire_wargame.UsersYFichas.controladorUsuarios;

/**
 *
 * @author David
 */
public class ventanaNewAcc {
        JFrame screen = new JFrame();
        JTextField usertxt = new JTextField();
        //JTextField contratxt = new JTextField();
        
        JPasswordField passwordfield = new JPasswordField(20);
    public ventanaNewAcc(){
        
        screen.setSize(500, 600);  //Tamaño standard para menus
        screen.setResizable(false);
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screen.setLocationRelativeTo(null);
        screen.setLayout(null);
        
        Container contentPane = screen.getContentPane();
        contentPane.setBackground(Color.DARK_GRAY);
        
        
        
         int grosor =3;
        Color colorBorde= new Color(255,215,0);
        Border bordeBoton= BorderFactory.createLineBorder(colorBorde,grosor);
        
        JLabel titulo = new JLabel("NUEVA CUENTA");
        titulo.setFont(new Font("Serif", Font.BOLD, 35));
        titulo.setBounds(110, 50, 500, 50);
        titulo.setForeground(new Color(255,215,0));
        
        JLabel userlabel = new JLabel("Ingrese un nombre de usuario:");
        userlabel.setBounds(90, 115, 250, 50);
        userlabel.setForeground(new Color(255,215,0));
        
        usertxt.setBounds(90, 155, 300, 25);
        
        
        JLabel contraLabel = new JLabel("Ingrese una contraseña:");
        contraLabel.setBounds(90, 175, 250, 50);
        contraLabel.setForeground(new Color(255,215,0));
        //contratxt.setBounds(90, 210, 300, 25);
        
        
        //Implementacion de passwordfield
        passwordfield.setBounds(90, 210, 300, 25);
        
        
        JButton btCreate = new JButton("Crear Cuenta");
        btCreate.setBounds(115, 310, 250, 40);
        btCreate.setBackground(Color.GRAY);
        btCreate.setBorder(bordeBoton);
        JButton btVolver= new JButton("Volver");
        btVolver.setBounds(115, 410, 250, 40);
        btVolver.setBackground(Color.GRAY);
        btVolver.setBorder(bordeBoton);
        
         btCreate.addActionListener(new ActionListener(){
          @Override 
          public void actionPerformed(ActionEvent e){
              
              String txt=usertxt.getText();
              char[] tempPas = passwordfield.getPassword();
              String pssString = new String(tempPas);
              
              if(txt.equalsIgnoreCase("")|| pssString.equalsIgnoreCase("")){
                  JOptionPane.showMessageDialog(screen, "PORFAVOR LLENAR TODOS LOS CAMPOS");
              }else{
                boolean revisionPass=checkPassword();
                boolean revisionName=checkUsername(usertxt.getText());
                if(revisionPass==true && revisionName==true){
                     JOptionPane.showMessageDialog(screen, "Se ha creado la cuenta correctamente");
                     char[] tempPass = passwordfield.getPassword();
                     String passwordString = new String(tempPass);
                     Usuario newUser= new Usuario(usertxt.getText(), passwordString);
                     controladorUsuarios.getInstancia().getDBUsers().add(newUser);//agregado de nuevo usuario
                     java.util.Arrays.fill(tempPass, '0');//limpieza de password
                     usertxt.setText("");
                     passwordfield.setText("");
                     controladorLogged.getInstancia().setUsuarioLogged(newUser);
                     menuPrincipal ventana = new menuPrincipal();
                     screen.dispose();
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
        ventanaNewAcc ventana = new ventanaNewAcc();
    }
    
    private boolean checkPassword(){
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
        char[] contra= passwordfield.getPassword();
        int lengthpss = contra.length;
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
        for(int i=0; i<contra.length; i++){
            String stChar = Character.toString(contra[i]);
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
        for(int i=0;i<contra.length; i++){
            String stChar = Character.toString(contra[i]);
            //String stChar=Character.toString(password.charAt(i));
            if(abc.contains(stChar) || ABC.contains(stChar)){
                ver5=true;
                System.out.println("Contiene una letra normal");
                break;
            }
        }
        
        
        
        //Verificar que no tiene espacios en blanco
        boolean checkSpaces=false;
        for(int i=0; i<contra.length; i++){
            char letra = contra[i];
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
    
    
   private boolean checkUsernameRecursivo(String username, int index){
       if(index>=controladorUsuarios.getInstancia().getDBUsers().size()){
           return true;
       }
       
       Usuario user = controladorUsuarios.getInstancia().getDBUsers().get(index);
       
       if(user.getUsername().equalsIgnoreCase(username)){
           JOptionPane.showMessageDialog(screen, "El nombre de usuario ya existe, por favor utilice uno distinto");
           return false;
       }
       
       
       return checkUsernameRecursivo(username, index+1);
   }
    
    
   
    private boolean checkUsername(String username){
        return checkUsernameRecursivo(username,0);
    }
    
    
    
    
}
