/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vampire_wargame.manejoDatos;

/**
 *
 * @author David
 */
public class LogPartida {
    
    protected String JUGADOR;
    protected String Contricante;
    protected String Resultado;
    
    
    private String msgLog;
    
    
    public LogPartida(String JUGADOR, String Contricante, String Resultado){
        this.JUGADOR=JUGADOR;
        this.Contricante=Contricante;
        this.Resultado=Resultado;
        
        msgLog= formarMsg(Resultado);
    }
    
    
    
    
    private String formarMsg(String Resultado){
        String msg ="";
        if(Resultado.equals("VICTORIA")){
            msg = JUGADOR + "Vencio a "+Contricante+". HA Ganado 3 Puntos";
            
        }else if(Resultado.equals("DERROTA")){
             msg = JUGADOR + "Perdio contra "+Contricante+". HA PERDIDO 3 Puntos";
            
        }else if(Resultado.equals("RENDIDO")){
             msg = JUGADOR + "Se rindio frente "+Contricante+". HA Perdido 3 Puntos";
            
        }
        return msg;
    }
    
    
    public String getMsgLog(){
        return msgLog;
    }
    
    
    
}
