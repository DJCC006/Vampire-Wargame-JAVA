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
import java.awt.Polygon;
import java.awt.RenderingHints;
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
    
    
    private double velocidad=0;
    
    private final Random random = new Random();
    private JLabel resultadoLabel;
    private String fichaObjetivo=null;
    
    
    double  targetAbsolute=0;
    
    private double currentAngle=0;
   private double anguloObjetivo=0;
   
    
    
    
    private String fichaSeleccionada;
    
    
    
    private double angulo = 0;
    private Timer timer;
    private boolean girando = false;
    
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
    
    
    //METODOS PARA IMPLEMENTACION 2
    public void startGiro(){
        if(girando) return;
        
        girando=true;
        
        timer= new Timer(16,e ->{
            angulo+=10;
            if(angulo>=360) angulo -= 360;
            repaint();
        });
        timer.start();
    }
    
    
    
    public void detenerGiro(){
        if(timer!= null && timer.isRunning()){
            timer.stop();
        }
        girando=false;
    }
    
    
    public void posicionarFicha(String fichaObjetivo){
        int index =getIndexOfFicha(fichaObjetivo);
        if(index ==-1)return;
        
        double seccion =360.0/iconos.length;
        
        angulo= 360-(index*seccion+seccion/2);
        //angulo= index*seccion+seccion/2;
        //if(angulo>=360) angulo-=360;
        angulo =angulo%360;
        repaint();
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
        int index= getIndexOfFicha(fichaObjetivo);
         if(index==-1){
            System.out.println("Ficha no encontrada: "+fichaObjetivo);
            girando=false;
            return;
        }
         
        
        
        double currentNorm = ((currentAngle%360)+360)%360;
        
        double arcAngle =360/ nombres.length;
        double sectionCenter = index*arcAngle+arcAngle/2.0; 
        
        
        double needed = ((90.0-sectionCenter)%360.0+360.0)%360.0;
        
        int extraVueltas=3;
        targetAbsolute = extraVueltas * 360.0+needed;
        
        double startAbs= currentAngle;
        while(targetAbsolute <= startAbs+0.1){
            targetAbsolute +=360.0;
        }
        
        
        
        
        
        
        
        if(timer!= null && timer.isRunning()) timer.stop();
        girando=true;
        //int index = getIndexOfFicha(fichaObjetivo);
       fichaSeleccionada=null;
       
       final double[] velocidad = {25.0};
        
        //double arcAngle=360.0/nombres.length;
        //double sectionAngle = index * arcAngle +arcAngle/2;
        
        //angulo=0;
        //anguloObjetivo=1440 + (360-sectionAngle);
        
        
        timer = new Timer(16, new ActionListener() {
             
            @Override
            public void actionPerformed(ActionEvent e) {
                //angulo += 15;
                angulo += velocidad[0];
                double restante = targetAbsolute -angulo;
                if(restante<0) restante =0;
                
                if(restante<360){
                    velocidad[0]*=0.92;
                    if(velocidad[0]<1.2)velocidad[0]=1.2;
                }else{
                    velocidad[0]*=0.995;
                    if(velocidad[0]<6)velocidad[0]=6;
                }
                
                repaint();

                if (angulo >= anguloObjetivo-0.5) {
                    ((Timer) e.getSource()).stop();
                    girando = false;
                    angulo = ((angulo%360)+360)%360;
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
        
        /*
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
        */
        
        
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        int w= getWidth();
        int h= getHeight();
        int size = Math.min(w,h)-40;
        int x= (w-size)/2;
        int y=(h-size)/2;
        
        int centerX= w/2;
        int centerY=h/2;
        
        double seccion =360.0/nombres.length;
        
        g2d.rotate(Math.toRadians(angulo), centerX, centerY);
        
        for(int i=0; i< nombres.length; i++){
            if(i%2==0) g2d.setColor(new Color(230,230,230));
            else g2d.setColor( new Color(200,200,200));
            
            g2d.fillArc(x, y, size, size,(int) (i*seccion), (int)seccion);
        }
        
        for(int i=0; i< nombres.length; i++){
            double angle = Math.toRadians(i* seccion + seccion/2);
            int iconX = (int) (centerX+(size/2.5)*Math.cos(angle));
            int iconY= (int) (centerY+(size/2.5)*Math.sin(angle));
            
            Image icono = iconos[i].getImage();
            int iconSize=40;
            g2d.drawImage(icono, iconX-iconSize/2, iconY-iconSize/2, iconSize, iconSize, this);
        }
        
        g2d.rotate(-Math.toRadians(angulo), centerX, centerY);
        
        
        Polygon puntero = new Polygon();
        puntero.addPoint(centerX, y-10);
        puntero.addPoint(centerX-10, y+10);
        puntero.addPoint(centerX+10, y+10);
        g2d.setColor(Color.red);
        g2d.fillPolygon(puntero);
        
        g2d.dispose();
        
        
    }
}
