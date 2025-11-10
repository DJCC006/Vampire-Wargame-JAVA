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
    protected String name;
    
    
    protected int previousESC;
    protected int previousVida;
    
    
    public Ficha(int Vida, int Ataque, int Escudo, int Type){
        this.ptAtaque=Ataque;
        this.ptVida=Vida;
        this.ptEscudo=Escudo;
        this.Type=Type;
        
        
        previousESC=ptEscudo;
        previousVida=ptVida;
    }
    
    
    protected int[][] direcciones;
    
    
    
    
    public void ataque(Ficha ficha){ //se debe que ingresar la ficha a la que se realiza el ataque
        if(ficha.ptEscudo>0){
            if(ficha.ptEscudo> ptAtaque){
                ficha.setPreviousESC(ficha.ptEscudo);
                ficha.ptEscudo-=ptAtaque;
            }else if(ficha.ptEscudo<ptAtaque){
                int Stack= (ficha.ptEscudo-ptAtaque)*-1;//Stack representando al daño restante que queda después de destrozar el escudo
                ficha.setPreviousESC(ficha.ptEscudo);
                ficha.ptEscudo=0;
                ficha.setPreviousHP(ficha.ptVida);
                ficha.ptVida-=Stack;
            } 
        }else if(ficha.ptEscudo==0 && ficha.ptVida>0){
            ficha.setPreviousESC(ficha.ptEscudo);
            ficha.setPreviousHP(ficha.ptVida);
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
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name= name;
    }
    
    public int getAtaqueB(){
        return ptAtaque;
    }
    
    
    public void setPreviousESC(int esc){
        previousESC=esc;
    }
    
    
    public void setPreviousHP(int hp){
        previousVida=hp;
    }
    
    public int getPreviousESC(){
        return previousESC;
    }
    
    public int getPreviousHP(){
        return previousVida;
    }
    
    //Este metodo lo definira cada ficha por separado
    public abstract void ataqueEspecial(Ficha ficha);
    
}
