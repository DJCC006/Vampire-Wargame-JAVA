/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vampire_wargame.UsersYFichas;

import javax.swing.ImageIcon;

/**
 *
 * @author David
 */
public abstract class Ficha {
    protected int ptAtaque;
    protected int ptVida;
    protected int ptEscudo;
    protected String ImageURL;
    protected ImageIcon icon;
    protected String bando;
    protected int Type;
    
    public Ficha(int Vida, int Ataque, int Escudo, int Type){
        this.ptAtaque=Ataque;
        this.ptVida=Vida;
        this.ptEscudo=Escudo;
        this.Type=Type;
    }
    
    
    protected int[][] direcciones;
    
    
    
    
    public void ataque(Ficha ficha){ //se debe que ingresar la ficha a la que se realiza el ataque
        if(ficha.ptEscudo>0){
            if(ficha.ptEscudo> ptAtaque){
                ficha.ptEscudo-=ptAtaque;
            }else if(ficha.ptEscudo<ptAtaque){
                int Stack= (ficha.ptEscudo-ptAtaque)*-1;//Stack representando al daño restante que queda después de destrozar el escudo
                ficha.ptEscudo=0;
                ficha.ptVida-=Stack;
            } 
        }else if(ficha.ptEscudo==0 && ficha.ptVida>0){
            ficha.ptVida-=ptAtaque;
        }
    }
    
    
    public void setImageIcon(String URL){
        ImageURL=URL;
        icon= new ImageIcon(ImageURL);
    }
    
    public ImageIcon getImageIcon(){
        return icon;
    }
    
    
    public void setBando(String bando){
        this.bando=bando;
    }
    
    public String getBando(){
        return bando;
    }
    
    
    public int getTypeFicha(){
        return Type;
    }
    
    
    public int[][] getdirecciones(){
        return direcciones;
    }
    
    public int getVida(){
        return ptVida;
    }
    
    public int getEscudo(){
        return ptEscudo;
    }
    
    //Este metodo lo definira cada ficha por separado
    public abstract void ataqueEspecial(Ficha ficha);
    
}
