/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vampire_wargame.UsersYFichas;

/**
 *
 * @author David
 */
public class Zombie extends Ficha{
    public Zombie(){
        super(1,1,0);
        super.setImageIcon("src\\resources\\zombieIcon.png");
    }
    
    @Override
    public void ataqueEspecial(Ficha ficha) {
        
    }
    
}
