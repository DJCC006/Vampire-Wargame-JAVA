/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vampire_wargame.menusyventanas;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import vampire_wargame.UsersYFichas.Usuario;
import vampire_wargame.UsersYFichas.controladorUsuarios;

/**
 *
 * @author David
 */
public class ventanaRanking {
    public ventanaRanking(){
        JFrame screen = new JFrame();
        screen.setSize(800, 600);  //Tamaño standard para menus
        screen.setResizable(false);
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screen.setLocationRelativeTo(null);
        screen.setLayout(null);
        
        
        JLabel titulo = new JLabel("RANKING DE JUGADORES");
        titulo.setBounds(100, 50, 700, 100);
        titulo.setFont(new Font("Serif", Font.BOLD, 50));
        
  
        
        String[] columnas ={"Posicion", "Nombre Usuario", "Puntos"};
        ArrayList<Usuario> usersActivos= new ArrayList<>();
        for(Usuario user:controladorUsuarios.getInstancia().getDBUsers()){
            if(user.getStatus()){//si es usuario activo lo toma
                usersActivos.add(user);
            }
        }
        //Todo en base a los users activos
        int sizePlayers = usersActivos.size();
        
        //Ordenamiento de users
        /*
        for(int i=0; i<sizePlayers-1; i++ ){
            for(int j=0; j<sizePlayers-i-1; j++){
                if(usersActivos.get(j).getPoints()< usersActivos.get(j+1).getPoints()){
                    Usuario temp = usersActivos.get(j);
                    usersActivos.set(j, usersActivos.get(j+1));
                    usersActivos.set(j, temp);
                }
            }
        }
*/
        
        usersActivos.sort(java.util.Comparator.comparing(Usuario::getPoints).reversed());
        
        Object[][] data= new Object[sizePlayers][3];
        for(int i=0; i<sizePlayers; i++){
            for(int j=0; j<3; j++){
                if(j==0){
                    data[i][j]=(i+1);
                }else if(j==1){
                    data[i][j]=usersActivos.get(i).getUsername();
                }else if(j==2){
                    data[i][j]=usersActivos.get(i).getPoints();
                }
                
            }
        }
        
        JTable tablaRanking = new JTable(data, columnas);
        JScrollPane panelScroll = new JScrollPane(tablaRanking);
        panelScroll.setBounds(100, 80, 500, 300);
        
        
        
        JButton btSalir = new JButton("Volver");
        btSalir.setBounds(500, 390, 200, 50);
        
        btSalir.addActionListener(new ActionListener(){
          @Override 
          public void actionPerformed(ActionEvent e){
              screen.dispose();
             menuReportes ventana = new menuReportes();
          }
                    
        });
        
        
        screen.add(panelScroll);
        screen.add(btSalir);
        screen.add(titulo);
        
        screen.setVisible(true);
    }
    
    
    public static void main(String[] args) {
        ventanaRanking ventana = new ventanaRanking();
    }
}
