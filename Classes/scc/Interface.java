
package scc;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class Interface extends JFrame {

    public JLabel labelChegada;
    public JLabel labelMediaChegEmpr;
    public JLabel labelMediaChegGeral;
    public JLabel labelDPEmpre;
    public JLabel labelDPGeral;
    public JLabel labelDPGeralEmpr;
    public JLabel labelDPEmprGeral;
    public JTextField textMediaChegEmpr;
    public JTextField textMediaChegGeral;
    public JTextField textDPEmpre;
    public JTextField textDPGeral;
    public JTextField textDPGeralEmpr;
    public JTextField textDPEmprGeral;
    public JLabel labelServico;
    public JLabel labelMediaServGeralGeral;
    public JLabel labelMediaServGeralEmpr;
    public JLabel labelMediaServEmprGeral;
    public JLabel labelMediaServEmprEmpr;
    public JTextField textMediaServGeralGeral;
    public JTextField textMediaServGeralEmpr;
    public JTextField textMediaServEmprGeral;
    public JTextField textMediaServEmprEmpr;
    public JLabel labelFuncGeral;
    public JLabel labelFuncEmpr;
    public JTextField textFuncGeral;
    public JTextField textFuncEmpr;
    public JLabel labelNumCliente;
    public JTextField textNumCliente;
    public JButton updateValues;
    public JButton startSimulacao;
    public JButton defaultValues;
    public JComboBox distrComboBox;
    public JLabel LstreamInfo, LstreamChegGeral, LstreamChegEmpr, LstreamServGeralGeral, LstreamServGeralEmpr, LstreamServEmprGeral, LstreamServEmprEmpr;
    public JTextField TstreamInfo, TstreamChegGeral, TstreamChegEmpr, TstreamServGeralGeral, TstreamServGeralEmpr, TstreamServEmprGeral, TstreamServEmprEmpr;
    public JPanel jpanel1;
    FicheiroParametros ficheiroParametros;
    final Interface thisGui;

  public Interface(Simulador s) {
      setPreferredSize(new Dimension(800, 350));
      setTitle("Simulador SCC");
      setLocation(100, 100);
      setVisible(true);
      setDefaultCloseOperation(3);

      setLayout(new GridLayout(12, 0, 4, 4));

      thisGui = this;

      try{
          ficheiroParametros = new FicheiroParametros("save.txt");
      }catch(Exception ex){
          System.out.println(ex);
      }

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

      labelDPGeralEmpr = new JLabel("DP c.Geral b.Empr"); this.add(labelDPGeralEmpr);
      textDPGeralEmpr = new JTextField("5"); this.add(textDPGeralEmpr);
      textDPGeralEmpr.setPreferredSize(new Dimension(30,20));

      labelDPEmprGeral = new JLabel("DP c.Empr b.Geral"); this.add(labelDPEmprGeral);
      textDPEmprGeral = new JTextField("5"); this.add(textDPEmprGeral);
      textDPEmprGeral.setPreferredSize(new Dimension(30,20));



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
                  s.updateDistr(true);
              else
                  s.updateDistr(false);
          }
      });

      try{
          ficheiroParametros.leParametros(s, this);
      }catch(Exception ex){
          System.out.println(ex);
      }

      defaultValues = new JButton("Default"); this.add(defaultValues);
      defaultValues.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              s.updateMedia_cheg(true, 12); textMediaChegGeral.setText("12");
              s.updateMedia_cheg(false, 35); textMediaChegEmpr.setText("35");
              s.updateDP(true, true, 8); textDPGeral.setText("8");
              s.updateDP(false, false, 4); textDPEmpre.setText("4");
              s.updateDP(true, false, 5); textDPGeralEmpr.setText("5");
              s.updateDP(false, true, 5); textDPEmprGeral.setText("5");
              s.updateMedia_serv(true, true, 30); textMediaServGeralGeral.setText("30");
              s.updateMedia_serv(true, false, 25); textMediaServGeralEmpr.setText("25");
              s.updateMedia_serv(false, true, 23); textMediaServEmprGeral.setText("23");
              s.updateMedia_serv(false, false, 20); textMediaServEmprEmpr.setText("20");
              s.updateNumFunc(true, 2); textFuncGeral.setText("2");
              s.updateNumFunc(false, 1); textFuncEmpr.setText("1");
              s.updateClientes(1000); textNumCliente.setText("1000");
              s.updateStreams(true, true, true, 1); TstreamChegGeral.setText("1");
              s.updateStreams(true, false, true, 2); TstreamChegEmpr.setText("2");
              s.updateStreams(false, true, true, 3); TstreamServGeralGeral.setText("3");
              s.updateStreams(false, true, false, 4); TstreamServGeralEmpr.setText("4");
              s.updateStreams(false, false, true, 5); TstreamServEmprGeral.setText("5");
              s.updateStreams(false, false, false, 6); TstreamServEmprEmpr.setText("6");
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
                  s.updateDP(true, true, Double.parseDouble(textDPGeral.getText()));
                  s.updateDP(false, false, Double.parseDouble(textDPEmpre.getText()));
                  s.updateDP(true, false, Double.parseDouble(textDPGeralEmpr.getText()));
                  s.updateDP(false, true, Double.parseDouble(textDPEmprGeral.getText()));
                  s.updateMedia_serv(true, true, Double.parseDouble(textMediaServGeralGeral.getText()));
                  s.updateMedia_serv(true, false, Double.parseDouble(textMediaServGeralEmpr.getText()));
                  s.updateMedia_serv(false, true, Double.parseDouble(textMediaServEmprGeral.getText()));
                  s.updateMedia_serv(false, false, Double.parseDouble(textMediaServEmprEmpr.getText()));
                  s.updateNumFunc(true, Double.parseDouble(textFuncGeral.getText()));
                  s.updateNumFunc(false, Double.parseDouble(textFuncEmpr.getText()));
                  s.updateClientes(Double.parseDouble(textNumCliente.getText()));
                  s.updateStreams(true, true, true, Double.parseDouble(TstreamChegGeral.getText()));
                  s.updateStreams(true, false, true, Double.parseDouble(TstreamChegEmpr.getText()));
                  s.updateStreams(false, true, true, Double.parseDouble(TstreamServGeralGeral.getText()));
                  s.updateStreams(false, true, false, Double.parseDouble(TstreamServGeralEmpr.getText()));
                  s.updateStreams(false, false, true, Double.parseDouble(TstreamServEmprGeral.getText()));
                  s.updateStreams(false, false, false, Double.parseDouble(TstreamServEmprEmpr.getText()));

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
              try{
                  ficheiroParametros.escreveParametros(s, thisGui);
              }catch(Exception ex){
                  System.out.println(ex);
              }
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
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());

        textArea = new JTextArea(); this.add(textArea);
        textArea.append(s.resultados.toString());
    }
}

class FicheiroParametros{
    File ficheiro;
    BufferedReader reader;
    BufferedWriter writer;

    public FicheiroParametros(String src) throws IOException {
        ficheiro = new File(src);
        ficheiro.createNewFile(); //Se ja existir, nao faz nada
    }

    public void leParametros(Simulador s, Interface gui) throws IOException {
        reader = new BufferedReader(new FileReader(ficheiro));
        String text = null;
        Double[] values = new Double[19];

        int i=0;
        while((text = reader.readLine()) != null){
            values[i] = Double.parseDouble(text);
            i+=1;
        }

        if(i != 19){
            throw new IOException("Values.size() != 19");
        }

        System.out.println("LOADING CONFIGS");

        s.updateMedia_cheg(true, values[0]); gui.textMediaChegGeral.setText(Double.toString(values[0]));
        s.updateMedia_cheg(false, values[1]); gui.textMediaChegEmpr.setText(Double.toString(values[1]));
        s.updateDP(true, true, values[2]); gui.textDPGeral.setText(Double.toString(values[2]));
        s.updateDP(false, false, values[3]); gui.textDPEmpre.setText(Double.toString(values[3]));
        s.updateDP(true, false, values[4]); gui.textDPGeralEmpr.setText(Double.toString(values[4]));
        s.updateDP(false, true, values[5]); gui.textDPEmprGeral.setText(Double.toString(values[5]));
        s.updateMedia_serv(true, true, values[6]); gui.textMediaServGeralGeral.setText(Double.toString(values[6]));
        s.updateMedia_serv(true, false, values[7]); gui.textMediaServGeralEmpr.setText(Double.toString(values[7]));
        s.updateMedia_serv(false, true, values[8]); gui.textMediaServEmprGeral.setText(Double.toString(values[8]));
        s.updateMedia_serv(false, false, values[9]); gui.textMediaServEmprEmpr.setText(Double.toString(values[9]));
        s.updateNumFunc(true, values[10]); gui.textFuncGeral.setText(Double.toString(values[10]));
        s.updateNumFunc(false, values[11]); gui.textFuncEmpr.setText(Double.toString(values[11]));
        s.updateClientes(values[12]); gui.textNumCliente.setText(Double.toString(values[12]));
        s.updateStreams(true, true, true, values[13]); gui.TstreamChegGeral.setText(Double.toString(values[13]));
        s.updateStreams(true, false, true, values[14]); gui.TstreamChegEmpr.setText(Double.toString(values[14]));
        s.updateStreams(false, true, true, values[15]); gui.TstreamServGeralGeral.setText(Double.toString(values[15]));
        s.updateStreams(false, true, false, values[16]); gui.TstreamServGeralEmpr.setText(Double.toString(values[16]));
        s.updateStreams(false, false, true, values[17]); gui.TstreamServEmprGeral.setText(Double.toString(values[17]));
        s.updateStreams(false, false, false, values[18]); gui.TstreamServEmprEmpr.setText(Double.toString(values[18]));
    }

    public void escreveParametros(Simulador s, Interface gui) throws IOException{
        PrintWriter writerP = new PrintWriter(ficheiro);
        writerP.print("");
        writerP.close();

        writer = new BufferedWriter(new PrintWriter(ficheiro));

        System.out.println("SAVING CONFIGS");

        writer.write(gui.textMediaChegGeral.getText()); writer.append("\n");
        writer.append(gui.textMediaChegEmpr.getText()); writer.append("\n");
        writer.append(gui.textDPGeral.getText()); writer.append("\n");
        writer.append(gui.textDPEmpre.getText()); writer.append("\n");
        writer.append(gui.textDPGeralEmpr.getText()); writer.append("\n");
        writer.append(gui.textDPEmprGeral.getText()); writer.append("\n");
        writer.append(gui.textMediaServGeralGeral.getText()); writer.append("\n");
        writer.append(gui.textMediaServGeralEmpr.getText()); writer.append("\n");
        writer.append(gui.textMediaServEmprGeral.getText()); writer.append("\n");
        writer.append(gui.textMediaServEmprEmpr.getText()); writer.append("\n");
        writer.append(gui.textFuncGeral.getText()); writer.append("\n");
        writer.append(gui.textFuncEmpr.getText()); writer.append("\n");
        writer.append(gui.textNumCliente.getText()); writer.append("\n");
        writer.append(gui.TstreamChegGeral.getText()); writer.append("\n");
        writer.append(gui.TstreamChegEmpr.getText()); writer.append("\n");
        writer.append(gui.TstreamServGeralGeral.getText()); writer.append("\n");
        writer.append(gui.TstreamServGeralEmpr.getText()); writer.append("\n");
        writer.append(gui.TstreamServEmprGeral.getText()); writer.append("\n");
        writer.append(gui.TstreamServEmprEmpr.getText()); writer.append("\n");

        writer.close();
    }
}