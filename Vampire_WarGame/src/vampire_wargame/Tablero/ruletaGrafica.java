/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vampire_wargame.Tablero;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author David
 */
public class ruletaGrafica extends JPanel {
    private final ImageIcon[] iconos = new ImageIcon[6];
   private final String[] nombres = {"HOMBRE LOBO", "VAMPRIO", "MUERTE", "HOMBRE LOBO", "VAMPIRO", "MUERTE"};
    private double angulo = 0;
    
    private double velocidad=0;
    
    private final Random random = new Random();
    private JLabel resultadoLabel;
    private String fichaObjetivo=null;
    
    
    private double currentAngle=0;
   private double anguloObjetivo=0;
   private Timer timer;
    private boolean girando = false;
    
    
    private String fichaSeleccionada;
    
    public ruletaGrafica(){
        ImageIcon wolf = new ImageIcon("src/resources/wolfIcon.png");
        ImageIcon vamp = new ImageIcon("src/resources/vampIcon.png");
        ImageIcon death = new ImageIcon("src/resources/deathIcon.png");
        
        
        iconos[0]= wolf;
        iconos[1]= vamp;
        iconos[2]= death;
        iconos[3]= wolf;
        iconos[4]= vamp;
        iconos[5]= death;
    }
    
    
    
    public void setResultadoLabel(JLabel label){
        this.resultadoLabel=label;
    }
    
    
    public void girar(){
        repaint();
        fichaObjetivo=null;
        anguloObjetivo=-1;
        iniciarGiro();
        /*
        if(girando) return;
        girando=true;
        velocidad = 30 + random.nextInt(20);
        
        timer = new Timer(20, new ActionListener(){
            public void actionPerformed(ActionEvent e){
                angulo += velocidad;
                velocidad *= 0.98;
                if(velocidad <0.5){
                    ((Timer) e.getSource()).stop();
                    girando=false;
                    mostrarResultado();
                }
                repaint();
            }
        });
        timer.start();
*/
    }
    
    
    
    public void girarHacia(String fichaObjetivo){
        /*
        this.fichaObjetivo= fichaObjetivo;
        calcularAnguloObjetivo();
        iniciarGiro();
        */
        
        System.out.println("FICHA OBJETIVO: "+fichaObjetivo);
        if(girando) return;
        girando=true;
        
        if(timer!= null && timer.isRunning()) timer.stop();
        
        int index = getIndexOfFicha(fichaObjetivo);
        if(index==-1){
            System.out.println("Ficha no encontrada: "+fichaObjetivo);
            girando=false;
            return;
        }
        
        double arcAngle=360.0/nombres.length;
        double sectionAngle = index * arcAngle +arcAngle/2;
        
        angulo=0;
        anguloObjetivo=1440 + (360-sectionAngle);
        
        
        timer = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                angulo += 15;
                repaint();

                if (angulo >= anguloObjetivo) {
                    ((Timer) e.getSource()).stop();
                    girando = false;
                    fichaSeleccionada = fichaObjetivo;
                    System.out.println("Ficha seleccionada: " + fichaSeleccionada);
                }
            }
        });

        timer.start();
        
    }
    
    
    
    private int getIndexOfFicha(String ficha){
        for(int i=0; i< nombres.length; i++){
            if(nombres[i].equalsIgnoreCase(ficha)) return i;
        }
        return -1;
    }
    
    
    private void iniciarGiro(){
        if(girando) return;
        girando=true;
        velocidad = 40 + random.nextInt(15);
        
        timer = new Timer(20, new ActionListener(){
            public void actionPerformed(ActionEvent e){
                angulo += velocidad;
                velocidad *= 0.98;
                if(fichaObjetivo!= null && anguloObjetivo >=0 && velocidad <3){
                    double diferencia = ((angulo%360)-anguloObjetivo +360)%360;
                    if(diferencia<5){
                        ((Timer) e.getSource()).stop();
                        girando=false;
                        mostrarResultado();
                        return;
                    }   
                }
                
                if(fichaObjetivo==null && velocidad <0.5){
                    ((Timer) e.getSource()).stop();
                    girando=false;
                    mostrarResultado();
                }
                repaint();
            }
        });
        timer.start();
    }
    
    
    public String getFichaSeleccionada(){
        return fichaSeleccionada;
    }
    
    private void calcularAnguloObjetivo(){
        double seccion =360.0/nombres.length;
        
        int indice=-1;
        for(int i=0; i< nombres.length; i++){
            if(nombres[i].equalsIgnoreCase(fichaObjetivo)){
                indice=i;
                break;
            }
        }
        
        
        if(indice!=-1){
            anguloObjetivo= (360-(indice*seccion+seccion/2)+90)%360;
        }    
    }
    
    
    private void mostrarResultado(String ficha){
        String resultado;
        if(ficha!= null){
            resultado=ficha;
        }else{
            double anguloFinal=(angulo%360+360)%360;
            double seccion =360.0/nombres.length;
            double anguloAjustado = (anguloFinal+90)%360;
            int indice =(int) ((nombres.length-(anguloAjustado/seccion))%nombres.length);
            resultado = nombres[indice];
        }
        
        if(resultadoLabel != null){
            resultadoLabel.setText("Ficha Seleccionada: "+resultado);
        }
        
    }
            
    
    private void mostrarResultado(){
        double anguloFinal = (angulo % 360+360)%360;
        double seccion = 360.0 /iconos.length;
        
        
        double anguloAjustado =(anguloFinal+90)%360;
        int indice = (int) ((iconos.length-(anguloAjustado/seccion))%iconos.length);
        if(resultadoLabel!=null){
            resultadoLabel.setText("Ficha Seleccionada: "+nombres [indice]);
        }
    }
    
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D) g.create();
        
       // int width= getWidth();
        //int height= getHeight();
        int size  = Math.min(getWidth(), getHeight())-50;
        int x = (getWidth()-size)/2;
        int y= (getHeight()-size)/2;
        
        
        g2d.translate(getWidth()/2, getHeight()/2);
        g2d.rotate(Math.toRadians(angulo));
        //g2d.translate(-width/2, -height/2);
        
        double anguloPorSeccion = 2 * Math.PI/ iconos.length;
        
        double startAngle=0;
        double arcAngle=360.0/ nombres.length;
        
        
        for(int i=0; i<iconos.length; i++){
            g2d.setColor(Color.getHSBColor((float) i /iconos.length,0.8f,0.9f));
            g2d.fillArc(-size/2, -size/2, size, size, (int) Math.toDegrees(i*anguloPorSeccion), (int) Math.toDegrees(anguloPorSeccion));
        }
        
        g2d.setColor(Color.BLACK);
        
        //dibujo de iconos
        for(int i=0; i< iconos.length; i++){
            double theta = i*anguloPorSeccion + anguloPorSeccion/2;
            double iconX = Math.cos(theta) *size/3-24;
            double iconY = Math.sin(theta) * size/3-24;
            
            
            
            Image Icon = iconos[i].getImage();
            g2d.drawImage(Icon, (int) iconX, (int) iconY, 48,48, null);
            startAngle+=arcAngle;
        }
        
        g2d.dispose();
        
        
        
        g.setColor(Color.red);
        int[] px= {getWidth()/2, getWidth()/2-10, getWidth()/2+10};
        int[] py = {y-5,y+20,y+20};
        g.fillPolygon(px,py,3);
        
        
    }
}
