
package scc;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class Interface extends JFrame {

    private JLabel labelChegada;
    private JLabel labelMediaChegEmpr;
    private JLabel labelMediaChegGeral;
    private JLabel labelDPEmpre;
    private JLabel labelDPGeral;
    private JTextField textMediaChegEmpr;
    private JTextField textMediaChegGeral;
    private JTextField textDPEmpre;
    private JTextField textDPGeral;
    private JLabel labelServico;
    private JLabel labelMediaServGeralGeral;
    private JLabel labelMediaServGeralEmpr;
    private JLabel labelMediaServEmprGeral;
    private JLabel labelMediaServEmprEmpr;
    private JTextField textMediaServGeralGeral;
    private JTextField textMediaServGeralEmpr;
    private JTextField textMediaServEmprGeral;
    private JTextField textMediaServEmprEmpr;
    private JLabel labelFuncGeral;
    private JLabel labelFuncEmpr;
    private JTextField textFuncGeral;
    private JTextField textFuncEmpr;
    private JButton updateValues;
    private JButton startSimulacao;
    private JComboBox distrComboBox;

  public Interface(Simulador s) {
      setPreferredSize(new Dimension(800, 350));
      setTitle("Simulador SCC");
      setLocation(100, 100);
      setVisible(true);
      setDefaultCloseOperation(3);
      setLayout(new FlowLayout());


      labelChegada = new JLabel("Chegada:"); add(labelChegada);


      labelMediaChegGeral = new JLabel("Média Geral"); add(labelMediaChegGeral);

      textMediaChegGeral = new JTextField("12"); add(textMediaChegGeral);
      textMediaChegGeral.setPreferredSize(new Dimension(30, 20));

      labelMediaChegEmpr = new JLabel("Média Empresarial"); add(labelMediaChegEmpr);
      textMediaChegEmpr = new JTextField("35"); add(textMediaChegEmpr);

      textMediaChegEmpr.setPreferredSize(new Dimension(30, 20));
      labelDPGeral = new JLabel("DP Geral"); add(labelDPGeral);

      textDPGeral = new JTextField("8"); add(textDPGeral);
      textDPGeral.setPreferredSize(new Dimension(30, 20));

      labelDPEmpre = new JLabel("DP Empresarial"); add(labelDPEmpre);
      textDPEmpre = new JTextField("4"); add(textDPEmpre);
      textDPEmpre.setPreferredSize(new Dimension(30, 20));


      labelServico = new JLabel("Serviço:"); add(labelServico);

      labelMediaServGeralGeral = new JLabel("Media c.Geral b.Geral"); add(labelMediaServGeralGeral);
      textMediaServGeralGeral = new JTextField("30"); add(textMediaServGeralGeral);
      textMediaServGeralGeral.setPreferredSize(new Dimension(30, 20));

      labelMediaServGeralEmpr = new JLabel("Media c.Geral b.Empr"); add(labelMediaServGeralEmpr);
      textMediaServGeralEmpr = new JTextField("25"); add(textMediaServGeralEmpr);
      textMediaServGeralEmpr.setPreferredSize(new Dimension(30, 20));

      labelMediaServEmprGeral = new JLabel("Media c.Empr b.Geral"); add(labelMediaServEmprGeral);
      textMediaServEmprGeral = new JTextField("23"); add(textMediaServEmprGeral);
      textMediaServEmprGeral.setPreferredSize(new Dimension(30, 20));

      labelMediaServEmprEmpr = new JLabel("Media c.Empr b.Empr"); add(labelMediaServEmprEmpr);
      textMediaServEmprEmpr = new JTextField("20"); add(textMediaServEmprEmpr);
      textMediaServEmprEmpr.setPreferredSize(new Dimension(30, 20));

      labelFuncGeral = new JLabel("Num Func. Geral"); add(labelFuncGeral);
      textFuncGeral = new JTextField("2"); add(textFuncGeral);
      textFuncGeral.setPreferredSize(new Dimension(30, 20));

      labelFuncEmpr = new JLabel("Num Func. Empr"); add(labelFuncEmpr);
      textFuncEmpr = new JTextField("1"); add(textFuncEmpr);
      textFuncEmpr.setPreferredSize(new Dimension(30, 20));

      distrComboBox = new JComboBox(new String[] {"Normal", "Exponencial"});
      add(distrComboBox);
      distrComboBox.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              String distr = (String)distrComboBox.getSelectedItem();
              if(distr.equalsIgnoreCase("Normal"))
                  s.updateDistr(false);
              else
                  s.updateDistr(true);
          }
      });

    updateValues = new JButton("Update");
    add(updateValues);
    updateValues.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                s.updateMedia_cheg(true, Double.parseDouble(textMediaChegGeral.getText()));
                s.updateMedia_cheg(false, Double.parseDouble(textMediaChegEmpr.getText()));
                s.updateDP(true, Double.parseDouble(textDPGeral.getText()));
                s.updateDP(false, Double.parseDouble(textDPEmpre.getText()));
                s.updateMedia_serv(true, true, Double.parseDouble(textMediaServGeralGeral.getText()));
                s.updateMedia_serv(true, false, Double.parseDouble(textMediaServGeralEmpr.getText()));
                s.updateMedia_serv(false, true, Double.parseDouble(textMediaServEmprGeral.getText()));
                s.updateMedia_serv(false, false, Double.parseDouble(textMediaServEmprEmpr.getText()));
                s.updateNumFunc(true, Integer.parseInt(textFuncGeral.getText()));
                s.updateNumFunc(false, Integer.parseInt(textFuncEmpr.getText()));

            } catch(Exception ex) {
                JOptionPane.showMessageDialog(null, (new StringBuilder()).append("Erro: ").append(ex).toString(), "Error", 0);
            }
        }
    });

    startSimulacao = new JButton("Iniciar");
    add(startSimulacao);
    startSimulacao.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            s.insereEvento(new Chegada(s.getInstante(), s, !s.Geral, s.distrExponencial));
            s.insereEvento(new Chegada(s.getInstante(), s, s.Geral, s.distrExponencial));
            s.printSimConfig();
            s.executa();
            Resultados result = new Resultados(s);
        }
    });

    this.pack();
  }
}

class Resultados extends JFrame{
    private JTextArea textArea;

    public Resultados(Simulador s){
        setPreferredSize(new Dimension(800, 350));
        setTitle("Simulador SCC");
        setLocation(100, 100);
        setVisible(true);
        setDefaultCloseOperation(3);
        setLayout(new FlowLayout());

        textArea = new JTextArea(); this.add(textArea);
        textArea.append(s.resultados.toString());
    }
}