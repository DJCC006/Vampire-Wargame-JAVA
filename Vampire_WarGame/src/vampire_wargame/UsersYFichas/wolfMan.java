/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vampire_wargame.UsersYFichas;

/**
 *
 * @author David
 */
public class wolfMan extends Ficha{
    
    public wolfMan(){
        super(5,5,2,1);
        super.setImageIcon("src\\resources\\wolfIcon.png");
        
        int[][] dirWolf ={
            {-1,0}, //arriba   y,x
            {-2,0},
            {1,0},//abajo
            {2,0},
            {0,-1},//izquierda
            {0,-2},
            {0,1},//derecha
            {0,2},//derecha
            {-1,-1}, //esquina superior izquierda
            {-2,-2}, //esquina superior izquierda
            {-1,1},//esquina superior derecha
            {-2,2},//esquina superior derecha
            {1,-1},//esquina inferior izquierda
            {2,-2},//esquina inferior izquierda
            {1,1},//esquina inferiro derecha
            {2,2}//esquina inferiro derecha
        };
        super.direcciones=dirWolf;
    };
    
    
    @Override
    public void ataqueEspecial(Ficha ficha) {
        
    }
    
    
    
    
}
