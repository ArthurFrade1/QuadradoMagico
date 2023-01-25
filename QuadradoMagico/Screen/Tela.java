package QuadradoMagico;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;



public class Tela extends JFrame implements ActionListener, FocusListener, MouseListener {


    private String var = "";
    private JTextField[][] J = new JTextField[3][3];
    private JButton[][] B = new JButton[3][3];
    private boolean[][] pode = new boolean[3][3];
    private boolean[][] apertado = new boolean[3][3];
    private Game Ga = new Game();
    private boolean acabou, vai, pd = true;
    private JButton sair, limpar;

    public Tela() {

        setTitle("Quadrado Mágico");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        setLayout(null);

        // LABELS

        JLabel la = new JLabel(" A soma de cada linha, coluna e diagonal deve ser sempre 15");
        la.setBounds(0, 15, 500, 17);
        la.setFont(new Font("Arial", Font.BOLD, 17));
        add(la);

        // BUTTONS

        limpar = new JButton("Limpar");
        limpar.setBounds(0, 405, 249, 55);
        add(limpar);
        limpar.addActionListener(this);
        limpar.addMouseListener(this);

        sair = new JButton("Sair");
        sair.setBounds(249, 405, 250, 55);
        add(sair);
        sair.addActionListener(this);
        sair.addMouseListener(this);

        int conta = 1;
        for (int cont = 0; cont < 3; cont++) {
            for (int cont2 = 0; cont2 < 3; cont2++) {
                J[cont][cont2] = new JTextField();
                J[cont][cont2].setBounds(cont * 166, cont2 * 60 + 45, 166, 60);
                J[cont][cont2].setHorizontalAlignment(JTextField.CENTER);
                J[cont][cont2].addFocusListener(this);
                J[cont][cont2].setText("0");
                add(J[cont][cont2]);

                B[cont][cont2] = new JButton();
                B[cont][cont2].setBounds(cont * 166, cont2 * 60 + 225, 166, 60);
                B[cont][cont2].addActionListener(this);
                B[cont][cont2].setText("" + conta++);
                B[cont][cont2].addMouseListener(this);
                add(B[cont][cont2]);

                pode[cont][cont2] = true;
                apertado[cont][cont2] = true;
            }
        }

        setVisible(true);

    }


    public void actionPerformed(ActionEvent e) {

        if (acabou == false && pd) {
            for (int cont = 0; cont < 3; cont++) {
                for (int cont2 = 0; cont2 < 3; cont2++) {
                    if (B[cont][cont2] == e.getSource()) {
                        var = B[cont][cont2].getText();
                        B[cont][cont2].setEnabled(false);
                        vai = true;
                        pd = false;
                        apertado[cont][cont2] = false;
                    }
                }
            }
        }
        if (e.getSource() == sair) {
            System.exit(0);
        }
        if (e.getSource() == limpar) {
            Ga.limp();
            acabou = false;
            for (int cont = 0; cont < 3; cont++) {
                for (int cont2 = 0; cont2 < 3; cont2++) {
                    J[cont][cont2].setText("0");
                    B[cont][cont2].setEnabled(true);
                    pode[cont][cont2] = true;
                    apertado[cont][cont2]=true;
                }
            }
        }
    }

    public void focusGained(FocusEvent e) {
        if (acabou == false && vai) {
            for (int cont = 0; cont < 3; cont++) {
                for (int cont2 = 0; cont2 < 3; cont2++) {
                    if (J[cont][cont2] == e.getSource() && pode[cont][cont2]) {
                        Ga.fazJogada(cont, cont2, var);
                        J[cont][cont2].setText(var);
                        pode[cont][cont2] = false;
                        telaVit();
                        telaDe();
                        vai = false;
                        pd = true;
                    }
                }
            }
        }
    }

    public void focusLost(FocusEvent e){
    }


    public void mouseEntered(MouseEvent e) {  
        for (int cont = 0; cont < 3; cont++) {
                for (int cont2 = 0; cont2 < 3; cont2++) {
                    if (B[cont][cont2] == e.getSource() && apertado[cont][cont2]) {
                        B[cont][cont2].setBackground(Color.CYAN);
                    }
                }
            }
        if (e.getSource() == sair) {
            sair.setBackground(Color.CYAN);
        }
        if (e.getSource() == limpar) {
            limpar.setBackground(Color.CYAN);
        }
    }
    public void mouseExited(MouseEvent e) {   
        for (int cont = 0; cont < 3; cont++) {
                for (int cont2 = 0; cont2 < 3; cont2++) {
                    if (B[cont][cont2] == e.getSource()) {
                        B[cont][cont2].setBackground(null);
                    }
                }
            }
        if (e.getSource() == sair) {
            sair.setBackground(null);
        }
        if (e.getSource() == limpar) {
            limpar.setBackground(null);
        }
    }
    
    public void mouseClicked(MouseEvent e) {   
    }  
    public void mousePressed(MouseEvent e) {   
    }  
    public void mouseReleased(MouseEvent e) {  
    }  

    public void telaVit() {
        if (Ga.verificaVit() && acabou == false) {
            JOptionPane.showMessageDialog(null, "Parabéns! Quadrado Mágico Preenchido Corretamente!", "Fim de Jogo", JOptionPane.INFORMATION_MESSAGE);
            acabou = true;
        }
    }

    public void telaDe() {
        if (acabou == false) {
            try{
                Ga.verificaDe();
            }
            catch(DerrotaException e){
                JOptionPane.showMessageDialog(null, "Não foi dessa vez!\nTente novamente!", "Fim de Jogo", JOptionPane.ERROR_MESSAGE);
                acabou = true;
            }
        }
    }

}

