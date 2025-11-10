/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vampire_wargame.UsersYFichas;

/**
 *
 * @author David
 */
public class NecroMancer extends Ficha {
    public NecroMancer(){
        super(4,3,1,3);
        int[][] dirNecro= {
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
        super.direcciones=dirNecro;
        super.setName("NECROMANCER");
    }
    
    
    @Override
    public void ataqueEspecial(Ficha ficha) {
        if(ficha.ptVida>=0){
            ficha.ptVida-=2;
        }
    }
    
    
    public void spawnearZombie(Ficha[][] tableroLogico, int fila, int columna, boolean turnos){
         Zombie newZomb= new Zombie();
        if(turnos){
            newZomb.setBando("JUGADOR");
            tableroLogico[fila][columna]= newZomb;
            newZomb.setImageIcon("src\\resources\\icons\\wZombieIcon.png");
        }else{
            newZomb.setBando("CONTRARIO");
            newZomb.setImageIcon("src\\resources\\icons\\bzombiecon.png");
            tableroLogico[fila][columna]= newZomb;
            
        }
    }
    
}
