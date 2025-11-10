/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vampire_wargame.Tablero;

import java.awt.Color;
import static java.awt.Color.GRAY;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
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
    private Calendar hoy;
    public gamePlay(Usuario PLAYER, Usuario CONTRICANTE){ 
        this.PLAYER=PLAYER;
        this.CONTRICANTE=CONTRICANTE;
        
        hoy = Calendar.getInstance();
        
         int grosor =3;
        Color colorBorde= new Color(255,215,0);
        Border bordeBoton= BorderFactory.createLineBorder(colorBorde,grosor);
        
        
        SwingUtilities.invokeLater(()-> {
            //String rutaFondo = "src/resources/backgroundWood.jpg";
            
            //panelFondo panelP= new panelFondo(rutaFondo);
            JFrame screen = new JFrame();
            //screen.setContentPane(panelP);
            Container contentPane = screen.getContentPane();
            contentPane.setBackground(Color.DARK_GRAY);
            
            screen.setSize(3000, 900);  //Tamaño standard para menus
            screen.setResizable(false);
            screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            screen.setLocationRelativeTo(null);
            screen.setLayout(null);

            
            
            
            
            //CONFIG DE RULETA
            JPanel ruletaPanel = new JPanel();
            ruletaGen generadorRuleta= new ruletaGen(ruletaPanel);
            ruletaPanel.setBackground(Color.DARK_GRAY);
            
            //Creadores de otras cosas que requiere el tablero
            JPanel panelAnuncios = new JPanel();
            panelAnuncios.setLayout(null);
            JPanel cementerioPlayer = new JPanel();
            JPanel cementerioContricante = new JPanel();
            
            JPanel panelTurnos = new JPanel();
            
            generadorTablero tablero = new generadorTablero(100, generadorRuleta, panelAnuncios, cementerioPlayer, cementerioContricante, panelTurnos, PLAYER, CONTRICANTE, screen);
            generadorRuleta.setTablero(tablero);
            JPanel panelTablero = new JPanel();
            panelTablero.setBounds(400, 10, 800, 600);
            panelTablero.setBackground(Color.DARK_GRAY);
            panelTablero.add(tablero);
            
            
            
            
            
            ruletaPanel.setLayout(null);
            ruletaPanel.add(generadorRuleta);
            ruletaPanel.setBounds(40, 10, 400, 700);
            
            
            panelAnuncios.setBounds(500, 650, 600, 100);
            panelAnuncios.setBackground(Color.WHITE);
            
            //DISPLAYERS DE PLAYERS
            
            cementerioPlayer.setBounds(1140, 100, 350, 300);
            cementerioPlayer.setBackground(Color.WHITE);
            cementerioPlayer.setLayout(new GridLayout(0,3));
            
            panelTurnos.setBackground(Color.WHITE);
            panelTurnos.setBounds(60, 400, 350, 80);
            
            
            JLabel gravePlayerLB= new JLabel("CEMENTERIO "+PLAYER.getUsername().toUpperCase());
            gravePlayerLB.setBounds(1140, 30, 350, 80);
            gravePlayerLB.setFont(new Font("Serif", Font.BOLD, 25));
            gravePlayerLB.setForeground(new Color(255,215,0));

            
            
            cementerioContricante.setBounds(1140, 500, 350, 300);
            cementerioContricante.setBackground(Color.WHITE);
            cementerioContricante.setLayout(new GridLayout(0,3));
            
            
            
            JLabel graveContriLB= new JLabel("CEMENTERIO "+CONTRICANTE.getUsername().toUpperCase());
            graveContriLB.setBounds(1140, 440, 350, 80);
            graveContriLB.setFont(new Font("Serif", Font.BOLD, 25));
            graveContriLB.setForeground(new Color(255,215,0));
            
            JLabel indicadores1= new JLabel();
            indicadores1.setText("FICHAS NEGRAS: "+CONTRICANTE.getUsername().toUpperCase());
            indicadores1.setBounds(60, 600, 200, 25);
            indicadores1.setForeground(new Color(255,215,0));
            
            JLabel indicadores2= new JLabel();
            indicadores2.setText("FICHAS BLANCAS: "+PLAYER.getUsername().toUpperCase());
            indicadores2.setBounds(60, 620, 200, 25);
            indicadores2.setForeground(new Color(255,215,0));
            
            
            
            JButton rendirseBT= new JButton("RENDIRSE");
            rendirseBT.setBounds(60, 500, 300, 80);
            rendirseBT.setBackground(GRAY);
            rendirseBT.setBorder(bordeBoton);
            
            
           rendirseBT.addActionListener(new ActionListener(){
          @Override 
          public void actionPerformed(ActionEvent e){
              Object[] opciones = {"Si", "No"};
              String patron = "dd/MM/yyyy";
              SimpleDateFormat format = new SimpleDateFormat(patron);
              Date jugada = hoy.getTime();
              String fecha= format.format(jugada);
              int choice = JOptionPane.showOptionDialog(screen, "¿Esta seguro de Rendirse?", "Confirmacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
              

            if(choice==JOptionPane.YES_OPTION){
                if(tablero.getTurno()){
                    String mensaje= "FECHA: "+fecha+" | JUEGO ENTRE "+PLAYER.getUsername().toUpperCase()+" Y "+CONTRICANTE.getUsername().toUpperCase()
                            +" | PERDEDOR: "+PLAYER.getUsername().toLowerCase()+" (SE RINDIO) | GANADOR:" +CONTRICANTE.getUsername().toUpperCase();
                    JOptionPane.showMessageDialog(screen, PLAYER.getUsername().toUpperCase()+"se ha RENDIDO. GANA "+CONTRICANTE.getUsername().toUpperCase() );
                    PLAYER.registrarPartida(mensaje);
                    CONTRICANTE.registrarPartida(mensaje);
                    CONTRICANTE.addPoints(3);
                }else{
                    String mensaje= "FECHA: "+fecha+" | JUEGO ENTRE "+PLAYER.getUsername().toUpperCase()+" Y "+CONTRICANTE.getUsername().toUpperCase()
                            +" | PERDEDOR: "+CONTRICANTE.getUsername().toLowerCase()+" (SE RINDIO) | GANADOR:" +PLAYER.getUsername().toUpperCase();
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
            
            screen.add(indicadores1);
            screen.add(indicadores2);
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
