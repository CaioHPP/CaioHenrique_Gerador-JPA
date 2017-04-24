package GeradosDeCodigos;

import java.awt.BorderLayout ;
import java.awt.Color;
import java.awt.Container ;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame ;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUI extends JFrame{
    Container cp;

    //GUI COMUM
    JButton btVoltar = new JButton("Voltar");
    JButton btCorrigir = new JButton("Corrigir");
    JButton btExit = new JButton("Sair");
    private JPanel pnNorte = new JPanel(new FlowLayout());
    private JPanel pnCentro = new JPanel(new FlowLayout());
    private JPanel pnSul = new JPanel(new FlowLayout());

    //GUI INICIAL
    JButton btCriarMain = new JButton("Criar Classe Main");
    JButton btCriarEntidade = new JButton("Criar Classe Entidade");
    JButton btCriarControle = new JButton("Criar Classe Controle");
    JButton btCriarPersistencia = new JButton("Criar Classe de Persistência");
    JButton btCriarGUI = new JButton("Criar Classe GUI");
    private JLabel lbStart = new JLabel("Selecione a classe que deseja criar");
    private JPanel pnStart = new JPanel(new GridLayout(5,1));

    //GUI ENTIDADE
    JButton btGerarGetSet = new JButton("Gerar Atributo + Get e Set");
    JButton btGerarConstrutor = new JButton("Gerar Construtor");
    private JLabel lbEntidade = new JLabel("Criando Atributos da Entidade");
    private JLabel lbInsertAtb = new JLabel("Insira o Atributo");
    private JLabel lbInsertType = new JLabel("Insira o Tipo");
    private JLabel lbInsertTamanho = new JLabel("Insira o Tamanho");
    private JTextField tfInsertAtb = new JTextField(15);
    private JTextField tfInsertTamanho = new JTextField(20);
    private JPanel pnEntidade = new JPanel(new GridLayout(2,1));
    private JPanel pnEntidade2 = new JPanel(new GridLayout(4,2));
    String[] type ={"","String", "int", "Double", "Date", "Long", "JCheckBox", "JRadioButton", "JComboBox", "Imagem"};
    JComboBox cbInsertType = new JComboBox(type);
    JCheckBox ccbFK = new JCheckBox("FK?");
    int FK = 0;
    int gate = 0;
    String nomePacote = "";
    String nomeEntidade = "";
    
    //GUI GUI
    public GUI() {
        setSize(230, 250);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        setTitle("Gerador de Códigos");

        pnNorte.setBackground(Color.blue);
        pnCentro.setBackground(Color.gray);
        pnSul.setBackground(Color.white);

        //Start
        pnNorte.add(lbStart);
        pnStart.add(btCriarPersistencia);
        pnStart.add(btCriarEntidade);
        pnStart.add(btCriarMain);
        pnStart.add(btCriarControle);
        pnStart.add(btCriarGUI);
        pnCentro.add(pnStart);

        //Entidade
        pnNorte.add(lbEntidade);
        pnEntidade2.add(lbInsertAtb);
        pnEntidade2.add(tfInsertAtb);
        pnEntidade2.add(lbInsertType);
        pnEntidade2.add(cbInsertType);
        pnEntidade2.add(lbInsertTamanho);
        pnEntidade2.add(tfInsertTamanho);
        pnEntidade2.add(ccbFK);   
        pnEntidade2.add(btGerarGetSet);
        pnEntidade.add(pnEntidade2);
        pnEntidade.add(btGerarConstrutor);
        pnCentro.add(pnEntidade);    

        //Comum
        pnSul.add(btVoltar);
        pnSul.add(btCorrigir);
        pnSul.add(btExit);

        //CP
        cp.add(pnNorte, BorderLayout.NORTH);
        cp.add(pnCentro, BorderLayout.CENTER);
        cp.add(pnSul, BorderLayout.SOUTH);

        //setVisible False
        btVoltar.setVisible(false);
        btCorrigir.setVisible(false);
        btCorrigir.setEnabled(false);

        lbEntidade.setVisible(false);
        pnEntidade.setVisible(false);

        btCriarControle.setEnabled(false);
        btCriarMain.setEnabled(false);
        btCriarGUI.setEnabled(false);
        ccbFK.setEnabled(false);

        //setVisible True
        lbStart.setVisible(true);
        pnStart.setVisible(true);

        //Classes
        Persistencia persistencia = new Persistencia();
        Geradores geradores = new Geradores();
        geradores.NomePacote();
        nomePacote = geradores.getNomePacote();
        nomeEntidade = geradores.getNomeEntidade();
        geradores.GerarPacote();

        //Comum
        btExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(null,"Deseja mesmo sair?","Aviso", JOptionPane.YES_NO_OPTION) == 0){
                    System.exit(0);
                }}});

        btVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(null,"Deseja mesmo voltar?","Aviso", JOptionPane.YES_NO_OPTION) == 0){
                setSize(230, 250);
                geradores.Voltar();

                //setVisible False
                btVoltar.setVisible(false);
                btCorrigir.setVisible(false);

                lbEntidade.setVisible(false);
                pnEntidade.setVisible(false);

                //setVisible True
                lbStart.setVisible(true);
                pnStart.setVisible(true);
            }}});

        btCorrigir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(null,"Deseja mesmo corrigir?","Aviso", JOptionPane.YES_NO_OPTION) == 0){
                    geradores.Corrigir();
                    btCorrigir.setEnabled(false);
                    tfInsertAtb.setText("");
                    tfInsertTamanho.setText("");
                    cbInsertType.setSelectedIndex(0);
                    tfInsertAtb.requestFocus();
                }}});
        //Start
        btCriarMain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    persistencia.salvarArquivo("src/" + nomePacote +"/Main.java", geradores.GeradorMain());
                    JOptionPane.showMessageDialog(null,"Classe Main Gerada com Sucesso!","Concluido",JOptionPane.PLAIN_MESSAGE);
                    btCriarMain.setEnabled(false);
                }catch(Exception Main){
                    JOptionPane.showMessageDialog(null,"Erro ao Criar Classe Main!","Erro",JOptionPane.ERROR_MESSAGE);
                }}});    

        btCriarEntidade.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nomeEntidade = "";
                if (nomeEntidade.equals("")){
                    if (FK != 0){
                        nomeEntidade = geradores.getNomeEntidade();
                    }else{
                        nomeEntidade = geradores.NomeEntidade();}}
                if (!nomeEntidade.equals(" ") && !nomeEntidade.equals("Code: Abort")){
                    setSize(470, 310);
                    geradores.GeradorEntidadeStart(); 
                    geradores.GerarArquivoTxt();

                    //setVisible False
                    lbStart.setVisible(false);
                    pnStart.setVisible(false);

                    btGerarConstrutor.setEnabled(false);

                    //setVisible True
                    btVoltar.setVisible(true);
                    btCorrigir.setVisible(true);

                    lbEntidade.setVisible(true);
                    pnEntidade.setVisible(true);
                    }}});

        btCriarControle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    persistencia.salvarArquivo("src/" + nomePacote +"/Controle.java", geradores.GeradorControle());
                    JOptionPane.showMessageDialog(null,"Classe Controle Gerada com Sucesso!","Concluido",JOptionPane.PLAIN_MESSAGE);
                    btCriarControle.setEnabled(false);
                    btCriarGUI.setEnabled(true);
                }catch(Exception Controle){
                    JOptionPane.showMessageDialog(null,"Erro ao Criar Classe Controle!","Erro",JOptionPane.ERROR_MESSAGE);}
            }});

        btCriarPersistencia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    persistencia.salvarArquivo("src/" + nomePacote +"/Persistencia.java", geradores.GeradorPersistencia());
                    JOptionPane.showMessageDialog(null,"Classe Persistência Gerada com Sucesso!","Concluido",JOptionPane.PLAIN_MESSAGE);
                    btCriarPersistencia.setEnabled(false);
                }catch(Exception Persistencia){
                    JOptionPane.showMessageDialog(null,"Erro ao Criar Classe Persistencia!","Erro",JOptionPane.ERROR_MESSAGE);}
            }});

        //Entidade
        btGerarGetSet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Pattern pattern = Pattern.compile("[a-zA-Z0-9_]*");
                    Matcher matcher = pattern.matcher(tfInsertAtb.getText());
                    if(tfInsertAtb.getText().equals("") || tfInsertTamanho.getText().equals("")){
                        int Error = 3/0;}
                    if (tfInsertAtb.getText().substring(0,1).matches("^[0-9]*$") || !matcher.matches()){
                        int Error = 3/0;}
                    if (ccbFK.isSelected()){
                        FK++;}
                    geradores.GeradorEntidadeGetSet(tfInsertAtb.getText(), String.valueOf(cbInsertType.getSelectedItem()), tfInsertTamanho.getText(), ccbFK.isSelected());
                    JOptionPane.showMessageDialog(null,"Atributo adicionado com sucesso!","Concluido",JOptionPane.PLAIN_MESSAGE);
                    tfInsertAtb.setText("");
                    tfInsertTamanho.setText("");
                    cbInsertType.setSelectedIndex(0);
                    tfInsertAtb.requestFocus();
                    btCorrigir.setEnabled(true);
                    ccbFK.setEnabled(true);
                    tfInsertAtb.setEnabled(true);
                    cbInsertType.setEnabled(true);
                    tfInsertTamanho.setEnabled(true);
                    ccbFK.setSelected(false);
                    if (gate > 0){
                        btGerarConstrutor.setEnabled(true);
                    }else{
                        gate++;}
                }catch(Exception Entidade){
                    JOptionPane.showMessageDialog(null,"Erro ao adicionar atributo","Erro",JOptionPane.ERROR_MESSAGE);}
                }});

        btGerarConstrutor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    persistencia.salvarArquivo("src/" + nomePacote +"/" + nomeEntidade +".java", geradores.GeradorEntidadeBuilder());
                    JOptionPane.showMessageDialog(null,"Classe Entidade Finalizada!","Concluido",JOptionPane.PLAIN_MESSAGE);
                    //setVisible False
                    btVoltar.setVisible(false);
                    btCorrigir.setVisible(false);

                    lbEntidade.setVisible(false);
                    pnEntidade.setVisible(false);

                    btCriarEntidade.setEnabled(false);
                    ccbFK.setEnabled(true);

                    //setVisible True
                    lbStart.setVisible(true);
                    pnStart.setVisible(true);
                    btCriarControle.setEnabled(true);

                    if (FK == 0 && ccbFK.isVisible() == true){
                        btCriarMain.setEnabled(true);}                    

                    setSize(230, 250);
                }catch(Exception Entidade){
                    JOptionPane.showMessageDialog(null,"Erro ao Finalizar","Erro",JOptionPane.ERROR_MESSAGE);}
            }});

        tfInsertTamanho.addActionListener(new ActionListener() {
        @Override
            public void actionPerformed(ActionEvent e) {
            btGerarGetSet.doClick();}});

        lbStart.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e){
                geradores.SetPassword();}});

        ccbFK.addActionListener(new ActionListener() {
        @Override
            public void actionPerformed(ActionEvent e){
                if (ccbFK.isSelected()){
                    cbInsertType.setSelectedItem("JComboBox");
                    cbInsertType.setEnabled(false);
                    tfInsertTamanho.setText(" ");
                    tfInsertTamanho.setEnabled(false);
                }else{
                    cbInsertType.setSelectedIndex(0);
                    cbInsertType.setEnabled(true);
                    tfInsertTamanho.setText("");
                    tfInsertTamanho.setEnabled(true);}}});

        btCriarGUI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    persistencia.salvarArquivo("src/" + nomePacote +"/GUI.java", geradores.GeradorGUI());
                    JOptionPane.showMessageDialog(null,"Classe GUI Gerada com Sucesso!","Concluido",JOptionPane.PLAIN_MESSAGE);
                    btCriarMain.setEnabled(false);
                    btCriarEntidade.setEnabled(true);
                    btCriarPersistencia.setEnabled(true);
                    btCriarControle.setEnabled(false);
                    btCriarGUI.setEnabled(false);

                    if (FK != 0){
                        List<String> listaFK = geradores.ListaFKs();
                            for (int FKAux = 0; FKAux < FK; FKAux++){
                                geradores.Clear();
                                geradores.setNomePacote("FK" + listaFK.get(FKAux));
                                geradores.setNomeEntidade(listaFK.get(FKAux));
                                nomePacote = geradores.getNomePacote();
                                nomeEntidade = geradores.getNomeEntidade();
                                geradores.GerarPacote();
                                geradores.GerarArquivoTxt();
                                btCriarPersistencia.doClick();
                                gate = 0;
                                btCriarEntidade.doClick();
                                ccbFK.setVisible(false);
                                tfInsertAtb.setText(listaFK.get(FKAux));
                                tfInsertAtb.setEnabled(false);
                                cbInsertType.setSelectedIndex(0);
                                cbInsertType.setEnabled(true);}
                            if(FK == 1){
                                geradores.GerarPacote("Main");
                                persistencia.salvarArquivo("src/Main/Main.java", geradores.GeradorMainFK());}
                        FK--;}
                }catch(Exception GUI){
                    JOptionPane.showMessageDialog(null,"Erro ao Criar Classe GUI!","Erro",JOptionPane.ERROR_MESSAGE);}
                }});

        ccbFK.setVisible(false);
        btCorrigir.setVisible(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}                                        
