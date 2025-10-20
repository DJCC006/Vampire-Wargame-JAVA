/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vampire_wargame;

import java.util.ArrayList;
import vampire_wargame.UsersYFichas.Usuario;
import vampire_wargame.UsersYFichas.controladorUsuarios;
import vampire_wargame.menusyventanas.menuInicial;

/**
 *
 * @author David
 */
public class Ejecutador {
    
    public Ejecutador(){
        ArrayList<Usuario> DBUsuarios= new ArrayList<Usuario>();//Creamos dataBase de Usuarios
        controladorUsuarios.getInstancia().setDBUsers(DBUsuarios); //Establecemos controlador de DataBase
        menuInicial ventana = new menuInicial();
    }
    
}
