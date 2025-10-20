/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vampire_wargame.UsersYFichas;

/**
 *
 * @author David
 */
public class controladorLogged {
    private static controladorLogged instancia;
    
    private Usuario loggedUser;
    
    private controladorLogged(){};
    
    public static controladorLogged getInstancia(){
        if(instancia==null){
            instancia= new controladorLogged();
        }
        return instancia;
    }
    
    public Usuario getUsuarioLogged(){
        return loggedUser;
    }
    
    public void setUsuarioLogged(Usuario usuario){
        loggedUser=usuario;
    }
    
}
