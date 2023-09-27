package GUICardTabbed.GUICardTabbed.src;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.*;

public class Exercicios5Diogo extends JFrame {
    int pontuacao = 0;
    public Exercicios5Diogo() {
        super("Jogo Quiz");
        CardLayout cl = new CardLayout();//criei o layout
        JPanel cardsPanel = new JPanel(cl);//painel principal de CardsLayout
        //
        //criar Primeiro Card Início
        //
        JPanel cInicio = new JPanel();
        cInicio.add(new JLabel("Sejá Bem Vindo ao Jogo"));
        JButton bStart = new JButton("Start");
        cInicio.add(bStart);
        cardsPanel.add(cInicio,"Start");
        //
        //criar Primeiro Card Pergunta
        //
        JPanel cPergunta1 = new JPanel();
        cPergunta1.add(new JLabel("Quem Foi Campeão da Copa de 70"));
        JTextField rPergunta1 = new JTextField(25);
        JButton bPergunta1= new JButton("Avançar");
        cPergunta1.add(rPergunta1);
        cPergunta1.add(bPergunta1);
        cardsPanel.add(cPergunta1,"Pergunta1");
        //
        //criar Segundo Card Pergunta
        //
        JPanel cPergunta2 = new JPanel();
        cPergunta2.add(new JLabel("Onde Foi a Copa de 70"));
        JTextField rPergunta2 = new JTextField(25);
        JButton bPergunta2= new JButton("Avançar");
        cPergunta2.add(rPergunta2);//add textField
        cPergunta2.add(bPergunta2);//add botao
        cardsPanel.add(cPergunta2,"Pergunta2");//add painel ao card
        //
        //criar Ultimo Card PResultado
        //
        JPanel cResultado = new JPanel();
        JLabel resultadoFinal = new JLabel();
        cResultado.add(new JLabel("O seu Resultado Foi"));
        cResultado.add(resultadoFinal);
        JButton bNovamente= new JButton("Jogar Novamente");
        cResultado.add(bNovamente);
        cardsPanel.add(cResultado,"Final");
        //set do frame
        this.add(cardsPanel);
        this.setDefaultCloseOperation(2);
        this.setBounds(100, 100, 300, 300);
        this.setVisible(true);
        //
        //ações para os botões
        //
        bStart.addActionListener(e->{
            cl.next(cardsPanel);
        });
        bPergunta1.addActionListener(e->{
            if(rPergunta1.getText().equals("Brasil")){
                pontuacao+=1;
            }
            cl.next(cardsPanel);
        });
        bPergunta2.addActionListener(e->{
            if(rPergunta2.getText().equals("México")){
                pontuacao+=1;
            }
            resultadoFinal.setText(" : "+pontuacao);
            cl.next(cardsPanel);
        });
        bNovamente.addActionListener(e->{
             pontuacao = 0;
            cl.first(cardsPanel);
        });

    }
    
}
