/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vampire_wargame.Tablero;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

/**
 *
 * @author David
 */
public class testerRuletaGrafica extends JFrame {
    public testerRuletaGrafica(){
        setTitle("PRUEBA DE RULETA");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,500);
        setLocationRelativeTo(null);
        
        ruletaGrafica ruleta = new ruletaGrafica();
        JButton botonGirar = new JButton("GIRAR");
        
        botonGirar.addActionListener(e -> ruleta.girar());
        
        JLabel resultado = new JLabel("Ficha Seleccionada: ", SwingConstants.CENTER);
        resultado.setFont(new Font("Arial", Font.BOLD, 16));
        
        
        ruleta.setResultadoLabel(resultado);
        
        add(ruleta, BorderLayout.CENTER);
        add(botonGirar, BorderLayout.SOUTH);
        add(resultado, BorderLayout.NORTH);
        
        setVisible(true);
        
    }
    
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(testerRuletaGrafica::new);
    }
    
}
