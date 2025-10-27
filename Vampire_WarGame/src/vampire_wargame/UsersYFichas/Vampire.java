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
        super(4,3,5);
        super.setImageIcon("src\\resources\\vampIcon.png");
    }
    
    @Override
    public void ataqueEspecial(Ficha ficha) {
        
    }
    
}
