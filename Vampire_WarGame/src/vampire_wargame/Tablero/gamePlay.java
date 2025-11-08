/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vampire_wargame.Tablero;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import vampire_wargame.UsersYFichas.Usuario;
import vampire_wargame.UsersYFichas.controladorUsuarios;
import vampire_wargame.menusyventanas.menuPrincipal;

/**
 *
 * @author David
 */
public class gamePlay {
    
    private static Usuario PLAYER;
    private static Usuario CONTRICANTE;
    
    public gamePlay(Usuario PLAYER, Usuario CONTRICANTE){
        this.PLAYER=PLAYER;
        this.CONTRICANTE=CONTRICANTE;
        
        SwingUtilities.invokeLater(()-> {
            JFrame screen = new JFrame();
            screen.setSize(3000, 900);  //Tamaño standard para menus
            screen.setResizable(false);
            screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            screen.setLocationRelativeTo(null);
            screen.setLayout(null);

            //CONFIG DE RULETA
            JPanel ruletaPanel = new JPanel();
            ruletaGen generadorRuleta= new ruletaGen(ruletaPanel);
            
            //Creadores de otras cosas que requiere el tablero
            JPanel panelAnuncios = new JPanel();
            panelAnuncios.setLayout(null);
            JPanel cementerioPlayer = new JPanel();
            JPanel cementerioContricante = new JPanel();
            
            JPanel panelTurnos = new JPanel();
            
            generadorTablero tablero = new generadorTablero(100, generadorRuleta, panelAnuncios, cementerioPlayer, cementerioContricante, panelTurnos, PLAYER, CONTRICANTE);
            generadorRuleta.setTablero(tablero);
            JPanel panelTablero = new JPanel();
            panelTablero.setBounds(400, 10, 800, 600);
            //panelTablero.setBackground(Color.red);
            panelTablero.add(tablero);
            
            
            
            
            
            ruletaPanel.setLayout(null);
            ruletaPanel.add(generadorRuleta);
            ruletaPanel.setBounds(60, 10, 350, 400);
            
            
            panelAnuncios.setBounds(500, 650, 600, 100);
            panelAnuncios.setBackground(Color.GRAY);
            
            //DISPLAYERS DE PLAYERS
            
            cementerioPlayer.setBounds(1140, 100, 350, 300);
            cementerioPlayer.setBackground(Color.GRAY);
            
            panelTurnos.setBackground(Color.GRAY);
            panelTurnos.setBounds(60, 400, 350, 80);
            
            
            JLabel gravePlayerLB= new JLabel("CEMENTERIO "+PLAYER.getUsername().toUpperCase());
            gravePlayerLB.setBounds(1140, 30, 350, 80);
            gravePlayerLB.setFont(new Font("Serif", Font.BOLD, 25));

            
            
            cementerioContricante.setBounds(1140, 500, 350, 300);
            cementerioContricante.setBackground(Color.GRAY);
            
            
            
            
            JLabel graveContriLB= new JLabel("CEMENTERIO "+CONTRICANTE.getUsername().toUpperCase());
            graveContriLB.setBounds(1140, 440, 350, 80);
            graveContriLB.setFont(new Font("Serif", Font.BOLD, 25));
            
            
            
            JButton rendirseBT= new JButton("RENDIRSE");
            rendirseBT.setBounds(60, 500, 300, 80);
            
            
           rendirseBT.addActionListener(new ActionListener(){
          @Override 
          public void actionPerformed(ActionEvent e){
              Object[] opciones = {"Si", "No"};
              int choice = JOptionPane.showOptionDialog(screen, "¿Esta seguro de Rendirse?", "Confirmacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
              

            if(choice==JOptionPane.YES_OPTION){
                if(tablero.getTurno()){
                    String mensaje= "JUEGO ENTRE "+PLAYER.getUsername().toUpperCase()+" Y "+CONTRICANTE.getUsername().toUpperCase()
                            +" | PERDEDOR: "+PLAYER.getUsername().toLowerCase()+" | GANADOR:" +CONTRICANTE.getUsername().toUpperCase();
                    JOptionPane.showMessageDialog(screen, PLAYER.getUsername().toUpperCase()+"se ha RENDIDO. GANA "+CONTRICANTE.getUsername().toUpperCase() );
                    PLAYER.registrarPartida(mensaje);
                    CONTRICANTE.registrarPartida(mensaje);
                    CONTRICANTE.addPoints(3);
                }else{
                    String mensaje= "JUEGO ENTRE "+PLAYER.getUsername().toUpperCase()+" Y "+CONTRICANTE.getUsername().toUpperCase()
                            +" | PERDEDOR: "+CONTRICANTE.getUsername().toLowerCase()+" | GANADOR:" +PLAYER.getUsername().toUpperCase();
                    JOptionPane.showMessageDialog(screen, CONTRICANTE.getUsername().toUpperCase()+"se ha RENDIDO. GANA "+PLAYER.getUsername().toUpperCase() );
                    PLAYER.registrarPartida(mensaje);
                    CONTRICANTE.registrarPartida(mensaje);
                    PLAYER.addPoints(3);
                }
                menuPrincipal ventana = new menuPrincipal();
                screen.dispose();
            }else if(choice==JOptionPane.NO_OPTION){
                System.out.println("SIGUE EL JUEGO");

            }
          }
                    
        });
            
            
            screen.add(panelTurnos);
            screen.add(rendirseBT);
            screen.add(cementerioPlayer);
            screen.add(gravePlayerLB);
            screen.add(graveContriLB);
            screen.add(cementerioContricante);
            screen.add(panelAnuncios);
            screen.add(ruletaPanel);
            screen.add(panelTablero);
            screen.setVisible(true);
        });
        
    }
    
    public static void main(String[] args) {
        gamePlay game = new gamePlay(PLAYER, CONTRICANTE);
    }
    
    
    
    
    
   
    
    
}
