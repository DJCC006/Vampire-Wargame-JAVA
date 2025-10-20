/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vampire_wargame.UsersYFichas;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
/**
 *
 * @author David
 */
public class Usuario {
    private String username;
    private String password;
    private int points;
    private String fechaIngreso;//Se forma despues de convertir la fecha de calendar
    private boolean status;
    //private Logs[] logPartidas;
    
    
    //Inicializacion de objeto de Calendar
    private LocalDate creacionCuenta;
    DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    
    public Usuario(String username, String password){
        this.username=username;
        this.password=password;
        points=0;
        status=true;
        creacionCuenta= LocalDate.now();
        fechaIngreso=creacionCuenta.format(formatoFecha);
        //Proximamente linea que instancia el array de logs
    }
    
    
    //Setters
    public void addPoints(int points){
        this.points+=points;
    }
    
    public void setStatus(boolean status){
        this.status=status;
    }
    
    //Getters
    public String getUsername(){
        return username;
    }
    
    public String getPassword(){
        return password;
    }
    
    public int getPoints(){
        return points;
    }
    
    public String getFechaIngreso(){
        return fechaIngreso;
    }
    
    public boolean getStatus(){
        return status;
    }
    
}
