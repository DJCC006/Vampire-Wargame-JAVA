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
    
    
    public void ataque(Ficha ficha){
        if(ficha.ptEscudo>0){
            if(ficha.ptEscudo> ptAtaque){
                ficha.ptEscudo-=ptAtaque;
            }else if(ficha.ptEscudo<ptAtaque){
                ficha.ptEscudo=0;
                int Stack= -1*(ficha.ptEscudo-ptAtaque);//Stack representando al daño restante que queda después de destrozar el escudo
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
    
    //Este metodo lo definira cada ficha por separado
    public abstract void ataqueEspecial(Ficha ficha);
    
}
