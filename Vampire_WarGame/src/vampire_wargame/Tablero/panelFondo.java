/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vampire_wargame.Tablero;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author David
 */
public class panelFondo extends JPanel{
    private Image imagenFondo;
    
    
    public panelFondo(String rutaImagen){
        try{
            imagenFondo = ImageIO.read(new File(rutaImagen));
        }catch(IOException e ){
            e.printStackTrace();
        }
    }
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        if(imagenFondo!= null){
            g.drawImage(imagenFondo, 0,0, this.getWidth(), this.getHeight(), this);
        }
    }
}
