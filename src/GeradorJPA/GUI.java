package GeradorJPA;

import java.awt.BorderLayout ;
import java.awt.Color;
import java.awt.Container ;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame ;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class GUI extends JFrame{
    Container cp;
    
    private JPanel pnNorte = new JPanel(new FlowLayout());
    private JPanel pnCentro = new JPanel(new GridLayout(0,1));

    private JButton btImportEntidade = new JButton("Importar Entidade");
    private JButton btCriarGUI = new JButton("Criar GUI");
    private JButton btCriarDAOs = new JButton("Criar DAOs");
    private JLabel lbStart = new JLabel("Selecione a classe que deseja criar");
    
    boolean DAO = true;
    private List<String> listaItens = new ArrayList<>();
    private String local;
    private String arquivo;
    
    //JDialog UPTADE GUI
    private JDialog dialog = new JDialog();
    private JPanel pnDialogNorte = new JPanel(new FlowLayout());
    private JPanel pnDialogCentro = new JPanel(new GridLayout(0,1));
    private JPanel pnDialogCentroAux = new JPanel(new GridLayout(0,2));
    private JPanel pnDialogSul = new JPanel(new FlowLayout());
    private JLabel lbDialogNorte = new JLabel("Escolha o tipo do Atributo");    
    private JTextField tfDialog = new JTextField(16);    
    private JTextField tfTamanho = new JTextField(16);    
    private String[] type ={"","String", "int", "Double", "Date", "JCheckBox", "JRadioButton", "JComboBox", "Imagem"};
    private JComboBox cbbInsertType = new JComboBox(type);
    private JButton btDialog = new JButton("OK");
    int aux = 3;
    private List<String> listaUpdateItens = new ArrayList<>();
    private List<String> listaItensSize = new ArrayList<>();
    
    public GUI() {
        setSize(300, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        setTitle("Gerador JPA");

        pnNorte.setBackground(Color.white);
        pnCentro.setBackground(Color.lightGray);

        //Start
        pnNorte.add(lbStart);
        pnCentro.add(btImportEntidade);
        pnCentro.add(btCriarGUI);
        pnCentro.add(btCriarDAOs);

        //CP
        cp.add(pnNorte, BorderLayout.NORTH);
        cp.add(pnCentro, BorderLayout.CENTER);
        
        shieldAgainstUser(true, false, false);

        //setVisible True
        lbStart.setVisible(true);
        pnCentro.setVisible(true);

        //Classes
        Persistencia persistencia = new Persistencia();
        Geradores geradores = new Geradores();
        
        //UPDATE GUI
        pnDialogNorte.add(lbDialogNorte);
        pnDialogCentroAux.add(tfDialog);
        pnDialogCentroAux.add(cbbInsertType);
        pnDialogCentro.add(pnDialogCentroAux);
        pnDialogCentro.add(tfTamanho);
        pnDialogSul.add(btDialog);
        dialog.add(pnDialogNorte, BorderLayout.NORTH);
        dialog.add(pnDialogCentro, BorderLayout.CENTER);
        dialog.add(pnDialogSul, BorderLayout.SOUTH);
        dialog.setSize(300, 300);
        dialog.setResizable(false);
        dialog.setVisible(false);
        dialog.setLocationRelativeTo(cp);
        dialog.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        tfDialog.setEditable(false);
        
        //btImportEntidade
        btImportEntidade.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                FileFilter JavaFilter = new FileNameExtensionFilter("Java files", "java");
                JFileChooser chooser = new JFileChooser();
                chooser.setCurrentDirectory(new File("."));
                chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                chooser.setFileFilter(JavaFilter);
                int i = chooser.showSaveDialog(null);
                if (i == 1) {
                } else {
                    try{
                        String aux[];
                        File file = chooser.getSelectedFile();
                        local = String.valueOf(chooser.getCurrentDirectory());
                        arquivo = chooser.getSelectedFile().getName();
                        java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(file));
                        String line = reader.readLine();
                        while (line != null){
                            if (line.contains("private")){
                                if(!line.contains("static final")){
                                    line = line.trim();
                                    line = line.replace(" ", ";");
                                    aux = line.split(";");
                                    listaItens.add(aux[1]);
                                    listaItens.add(aux[2]);
                                }
                            }
                            line = reader.readLine();
                        }
                        reader.close();
                        JOptionPane.showMessageDialog(null,"Classe de Entidade Importada com Sucesso","Sucesso",JOptionPane.PLAIN_MESSAGE);
                        shieldAgainstUser(false, true, false);
                        localStart();
                    }catch(Exception file){
                    }
                }
            }
        });        
        
        btCriarGUI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    selectTypeAtb();
                    FileWriter newTxt = new FileWriter (local + "/GUI/GUI_" + arquivo + ".java");
                    persistencia.salvarArquivo(local + "/GUI/GUI_" + arquivo + ".java", geradores.GeradorGUI("GUI", arquivo, listaUpdateItens, listaItensSize));
                    if(listaUpdateItens.contains("Imagem")){
                        FileWriter newTxtImg = new FileWriter (local + "/GUI/Imagem.java");
                        persistencia.salvarArquivo(local + "/GUI/Imagem.java", geradores.Imagem("GUI"));
                    }
                    JOptionPane.showMessageDialog(null,"Classe GUI Gerada com Sucesso!","Concluido",JOptionPane.PLAIN_MESSAGE);
                    shieldAgainstUser(false, false, true);
                    toStringEntidade();
                }catch(Exception GUI){
                    JOptionPane.showMessageDialog(null,"Erro ao Criar Classe GUI!","Erro",JOptionPane.ERROR_MESSAGE);
                    System.out.println(GUI);}
                }});
        
        btCriarDAOs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try{
                    if(DAO){
                        if(JOptionPane.showConfirmDialog(null,"Deseja criar o DAOGenerico?","Aviso", JOptionPane.YES_NO_OPTION) == 0){
                            FileWriter newTxt = new FileWriter (local + "/DAOs/DAOGenerico.java");
                            persistencia.salvarArquivo(local + "/DAOs/DAOGenerico.java", geradores.GeradorDAOG());
                            DAO = false;
                            JOptionPane.showMessageDialog(null,"DAOGenerico Gerado com Sucesso!","Concluido",JOptionPane.PLAIN_MESSAGE);
                        } 
                    }
                    FileWriter newTxt = new FileWriter (local + "/DAOs/DAO" + arquivo + ".java");
                    persistencia.salvarArquivo(local + "/DAOs/DAO" + arquivo + ".java", geradores.GeradorDAOE(arquivo, listaItens));
                    JOptionPane.showMessageDialog(null,"DAO Gerado com Sucesso!","Concluido",JOptionPane.PLAIN_MESSAGE);
                    shieldAgainstUser(true, false, false);
                    arquivo = "";
                    local = "";
                    listaItens.clear();
                    listaItensSize.clear();
                    listaUpdateItens.clear();
                    aux = 3;
                    }catch(Exception DAOs){
                        System.out.println("ERROR DAOS: " + DAOs);
                }
            }
        });
        
        btDialog.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try{
                    if(cbbInsertType.getSelectedIndex() != 0 && tfTamanho.getText() != null){
                        listaUpdateItens.add(String.valueOf(cbbInsertType.getSelectedItem()));
                        listaUpdateItens.add(tfDialog.getText());
                        listaItensSize.add(tfTamanho.getText());
                        aux += 2;
                        if(aux < listaItens.size()){
                            tfDialog.setText(listaItens.get(aux));
                            cbbInsertType.setSelectedIndex(0);
                            tfTamanho.setText("");
                        }else{
                            JOptionPane.showMessageDialog(null,"Atributos Escolhidos com Sucesso","Sucesso",JOptionPane.PLAIN_MESSAGE);
                            dialog.setVisible(false);
                        }
                    }else{
                        JOptionPane.showMessageDialog(null,"Erro ao adquirir as informações","ERRO",JOptionPane.ERROR_MESSAGE);
                    }
                }catch(Exception end){
                }
            }
        });
        
        tfTamanho.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                btDialog.doClick();
            }
        });
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        
        setLocationRelativeTo(null);
        setVisible(true);
    }
    private void shieldAgainstUser(boolean Import, boolean GUI, boolean DAOs){
        btImportEntidade.setEnabled(Import);
        btCriarGUI.setEnabled(GUI);
        btCriarDAOs.setEnabled(DAOs);
    }
    
    private void selectTypeAtb(){
        listaUpdateItens.add(listaItens.get(0));
        listaUpdateItens.add(listaItens.get(1));
        listaItensSize.add("10");
        tfDialog.setText(listaItens.get(3));
        cbbInsertType.setSelectedIndex(0);
        dialog.setModal(true);
        dialog.setVisible(true);
    }
    
    private void localStart(){
        try{
            local = local.replace("\\", "/");
            local = local.replace("src/Entidades", "src");
            arquivo = arquivo.substring(0, 1).toUpperCase() + arquivo.substring(1);
            arquivo = arquivo.substring(0, arquivo.length()-5);
            new File(local + "/DAOs").mkdir();
            new File(local + "/GUI").mkdir();
        }catch(Exception mkdir){
        }
    }
    
    private void toStringEntidade(){
        String toString = firstToLowerCase(listaUpdateItens.get(1));
        String dateFormat = "";
        String dateType = "";
        for (int AUX = 3; AUX < listaUpdateItens.size(); AUX++, AUX++) {
            if(listaUpdateItens.get(AUX -1).equals("Date")){
                dateFormat = listaUpdateItens.get(AUX);
                dateType = listaItensSize.get((AUX-1)/2);
                toString += " + \";\" + date" + listaUpdateItens.get(AUX) + ".format(" + firstToLowerCase(listaUpdateItens.get(AUX)) + ")";
            }else{
                toString += " + \";\" + " + firstToLowerCase(listaUpdateItens.get(AUX));
            }
        }
        if(listaUpdateItens.contains("Date")){
            System.out.println("Copie e cole no ToString da Entidade " + arquivo + ":");
            System.out.println("SimpleDateFormat date" + dateFormat + " = new SimpleDateFormat(\"" + dateType + "\");");
            System.out.println("return " + toString + ";");
        }else{
            System.out.println("Copie e cole no ToString da Entidade " + arquivo + ":");
            System.out.println("return " + toString + ";");
        }
    }
    
    private String firstToLowerCase(String string){
        string = string.substring(0, 1).toLowerCase() + string.substring(1);
        return string;
    }
}                                        
