/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vampire_wargame.UsersYFichas;

import java.util.ArrayList;

/**
 *
 * @author David
 */
public class controladorUsuarios {
    private static controladorUsuarios instancia;
    
    private ArrayList<Usuario> DBUsuarios;
    
    private controladorUsuarios(){};
    
    public static controladorUsuarios getInstancia(){
        if(instancia==null){
            instancia = new controladorUsuarios();
        }
        return instancia;
    }
    
    public void setDBUsers(ArrayList<Usuario> DBUsers){
        DBUsuarios=DBUsers;
    }
    
    public ArrayList<Usuario> getDBUsers(){
        return DBUsuarios;
    }
    
    
    
    
}
