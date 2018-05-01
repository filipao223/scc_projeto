
package scc;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;


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
    private JLabel labelNumCliente;
    private JTextField textNumCliente;
    private JButton updateValues;
    private JButton startSimulacao;
    private JComboBox distrComboBox;
    private JLabel LstreamInfo, LstreamChegGeral, LstreamChegEmpr, LstreamServGeralGeral, LstreamServGeralEmpr, LstreamServEmprGeral, LstreamServEmprEmpr;
    private JTextField TstreamInfo, TstreamChegGeral, TstreamChegEmpr, TstreamServGeralGeral, TstreamServGeralEmpr, TstreamServEmprGeral, TstreamServEmprEmpr;
    private JPanel jpanel1;

  public Interface(Simulador s) {
      setPreferredSize(new Dimension(800, 350));
      setTitle("Simulador SCC");
      setLocation(100, 100);
      setVisible(true);
      setDefaultCloseOperation(3);

      setLayout(new GridLayout(12, 0, 4, 4));

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
      //labelServico.setBorder(new EmptyBorder(0,300,0,0));

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

      labelNumCliente = new JLabel("Clientes"); this.add(labelNumCliente);
      textNumCliente = new JTextField("1000"); this.add(textNumCliente);
      textNumCliente.setPreferredSize(new Dimension(30,20));

    LstreamInfo = new JLabel("Streams:"); this.add(LstreamInfo);

    LstreamChegGeral = new JLabel("Cheg Geral"); this.add(LstreamChegGeral);
    TstreamChegGeral = new JTextField("1"); this.add(TstreamChegGeral);
    TstreamChegGeral.setPreferredSize(new Dimension(30,20));

    LstreamChegEmpr = new JLabel("Cheg Empresa"); this.add(LstreamChegEmpr);
    TstreamChegEmpr = new JTextField("2"); this.add(TstreamChegEmpr);
    TstreamChegEmpr.setPreferredSize(new Dimension(30,20));

    LstreamServGeralGeral = new JLabel("Media Serv c.Geral b.Geral"); this.add(LstreamServGeralGeral);
    TstreamServGeralGeral = new JTextField("3"); this.add(TstreamServGeralGeral);
    TstreamServGeralGeral.setPreferredSize(new Dimension(30,20));

    LstreamServGeralEmpr = new JLabel("Media Serv c.Geral b.Empr"); this.add(LstreamServGeralEmpr);
    TstreamServGeralEmpr = new JTextField("4"); this.add(TstreamServGeralEmpr);
    TstreamServGeralEmpr.setPreferredSize(new Dimension(30,20));

      LstreamServEmprGeral = new JLabel("Media Serv c.Empr b.Geral"); this.add(LstreamServEmprGeral);
      TstreamServEmprGeral = new JTextField("5"); this.add(TstreamServEmprGeral);
      TstreamServEmprGeral.setPreferredSize(new Dimension(30,20));

      LstreamServEmprEmpr = new JLabel("Media Serv c.Empr b.Empr"); this.add(LstreamServEmprEmpr);
      TstreamServEmprEmpr = new JTextField("6"); this.add(TstreamServEmprEmpr);
      TstreamServEmprEmpr.setPreferredSize(new Dimension(30,20));

      distrComboBox = new JComboBox(new String[] {"Normal", "Exponencial"}); this.add(distrComboBox);
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
                  s.updateClientes(Integer.parseInt(textNumCliente.getText()));
                  s.updateStreams(true, true, true, Integer.parseInt(TstreamChegGeral.getText()));
                  s.updateStreams(true, false, true, Integer.parseInt(TstreamChegEmpr.getText()));
                  s.updateStreams(false, true, true, Integer.parseInt(TstreamServGeralGeral.getText()));
                  s.updateStreams(false, true, false, Integer.parseInt(TstreamServGeralEmpr.getText()));
                  s.updateStreams(false, false, true, Integer.parseInt(TstreamServEmprGeral.getText()));
                  s.updateStreams(false, false, false, Integer.parseInt(TstreamServEmprEmpr.getText()));

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
              //s.insereEvento(new Chegada(s.getInstante(), s, !s.Geral));
              //s.insereEvento(new Chegada(s.getInstante(), s, s.Geral));
              //s.printSimConfig();
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
        setSize(new Dimension(350,400));
        setTitle("Simulador SCC");
        setLocation(100, 100);
        setVisible(true);
        setDefaultCloseOperation(3);
        setLayout(new FlowLayout());

        textArea = new JTextArea(); this.add(textArea);
        textArea.append(s.resultados.toString());
    }
}