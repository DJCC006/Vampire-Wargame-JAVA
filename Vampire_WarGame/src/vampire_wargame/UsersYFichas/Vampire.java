/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vampire_wargame.UsersYFichas;

/**
 *
 * @author David
 */
public class Vampire extends Ficha{

    public Vampire(){
        super(4,3,5,2);
        super.setImageIcon("src\\resources\\vampIcon.png");
        int[][] dirVamp= {
            {-1,0}, //arriba
            {1,0},//abajo
            {0,-1},//izquierda
            {0,1},//derecha
            {-1,-1}, //esquina superior izquierda
            {-1,1},//esquina superior derecha
            {1,-1},//esquina inferior izquierda
            {1,1}//esquina inferiro derecha
        };
        super.direcciones=dirVamp;
        super.setName("VAMPIRO");
    }
    
    @Override
    public void ataqueEspecial(Ficha ficha) {
        if(ficha.ptVida>=0){
            if(super.ptVida>=4){
                super.ptVida=4;//reset a stats
                System.out.println("YA SE PUSEE HP A FULL");
            }else if(super.ptVida<4){
                ficha.ptVida-=1;
                super.ptVida+=1;
            }
        }
    }
    
}
