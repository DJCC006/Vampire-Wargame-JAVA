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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import vampire_wargame.UsersYFichas.controladorLogged;

/**
 *
 * @author David
 */
public class ventanaLogs {
    
    public ventanaLogs(){
        JFrame screen = new JFrame();
        screen.setSize(800, 600);  //Tamaño standard para menus
        screen.setResizable(false);
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screen.setLocationRelativeTo(null);
        screen.setLayout(null);
        
        
        JLabel titulo = new JLabel("LOGS DE PARTIDAS");
        titulo.setBounds(100, 50, 700, 100);
        titulo.setFont(new Font("Serif", Font.BOLD, 50));
        
        
        
        String[] columnas= {"Logs de Partidas"};
        int sizeActual=controladorLogged.getInstancia().getUsuarioLogged().getLogs().size();
        
        Object[][] data= new Object[sizeActual][];
        
        for(int i=0; i< sizeActual ; i++ ){
            data[i]= new Object[1];
            data[i][0]=controladorLogged.getInstancia().getUsuarioLogged().getLogs().get(i);
        }
        
        JTable tablaLogs = new JTable( data, columnas);
        tablaLogs.getTableHeader().setReorderingAllowed(false);
        JScrollPane panelScroll = new JScrollPane(tablaLogs);
        panelScroll.setBounds(100, 80, 500, 300);
        
        
        
        System.out.println("LISTA DE LOGS");
        for(String log:controladorLogged.getInstancia().getUsuarioLogged().getLogs() ){
            System.out.println(log+"\n");
        }
        System.out.println("Size de logs: "+sizeActual);
        
        
        JButton btSalir = new JButton("Volver");
        btSalir.setBounds(500, 410, 200, 50);
        
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
        ventanaLogs ventana = new ventanaLogs();
    }
}
