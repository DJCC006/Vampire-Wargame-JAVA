/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vampire_wargame.UsersYFichas;

/**
 *
 * @author David
 */
public abstract class Ficha {
    protected int ptAtaque;
    protected int ptVida;
    protected int ptEscudo;
    protected String ImageURL;
    
    public Ficha(int Vida, int Ataque, int Escudo){
        this.ptAtaque=Ataque;
        this.ptVida=Vida;
        this.ptEscudo=Escudo;
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
    
    //Este metodo lo definira cada ficha por separado
    public abstract void ataqueEspecial(Ficha ficha);
    
}
