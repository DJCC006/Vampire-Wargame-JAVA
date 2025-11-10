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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
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
        
        Container contentPane = screen.getContentPane();
        contentPane.setBackground(Color.DARK_GRAY);
        
        int grosor =3;
        Color colorBorde= new Color(255,215,0);
        Border bordeBoton= BorderFactory.createLineBorder(colorBorde,grosor);
        
        
        
        JLabel titulo = new JLabel("LOGS DE PARTIDAS");
        titulo.setBounds(100, 30, 700, 100);
        titulo.setFont(new Font("Serif", Font.BOLD, 50));
        titulo.setForeground(new Color(255,215,0));
        
        
        
        String[] columnas= {"Logs de Partidas"};
        int sizeActual=controladorLogged.getInstancia().getUsuarioLogged().getLogs().size();
        
        Object[][] data= new Object[sizeActual][];
        
        obtenerLogsRecursiva(data,0);
        
        JTable tablaLogs = new JTable( data, columnas);
        tablaLogs.getTableHeader().setReorderingAllowed(false);
        JScrollPane panelScroll = new JScrollPane(tablaLogs);
        panelScroll.setBounds(65, 115, 700, 300);
        
        
        
       // System.out.println("LISTA DE LOGS");
        for(String log:controladorLogged.getInstancia().getUsuarioLogged().getLogs() ){
            System.out.println(log+"\n");
        }
        //System.out.println("Size de logs: "+sizeActual);
        
        
        JButton btSalir = new JButton("Volver");
        btSalir.setBounds(500, 425, 200, 50);
        btSalir.setBackground(Color.GRAY);
        btSalir.setBorder(bordeBoton);
        
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
    
    
    
    private void obtenerLogsRecursiva(Object[][] data, int index){
        if(index>=data.length){
            return;
        }
        
        Object logEntry = controladorLogged.getInstancia().getUsuarioLogged().getLogs().get(index);
        data[index]=new Object[1];
        data[index][0]=logEntry;
        
        obtenerLogsRecursiva(data,index+1);
        
    }
    
    
}
