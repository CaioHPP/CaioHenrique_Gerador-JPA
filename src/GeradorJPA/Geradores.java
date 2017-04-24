package GeradorJPA;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Geradores {
    
    public List<String> GeradorGUI(String nomePacote, String nomeEntidade, List<String> listaGUIAux, List<String>listaGUIAuxSize){
        List<String> listaGUI = new ArrayList<>();
        int Aux = 0;
        for (int ii = 1; ii < listaGUIAux.size(); ii+=2) {
            String Item = listaGUIAux.get(ii);
            listaGUIAux.set(ii, Item.substring(0, 1).toUpperCase() + Item.substring(1));
        }
        listaGUI.add(
        "package " + nomePacote + ";\n" +
        "\n" +
        "import java.awt.BorderLayout;\n" +
        "import DAOs.DAO" + nomeEntidade + ";\n" +
        "import Entidades." + nomeEntidade + ";\n" +
        "import java.awt.Color;\n" +
        "import java.awt.Container;\n" +
        "import java.awt.FlowLayout;\n" +
        "import java.awt.GridLayout;\n" +
        "import java.awt.event.ActionEvent;\n" +
        "import java.awt.event.ActionListener;\n" +
        "import javax.swing.JButton;\n" +
        "import javax.swing.JLabel;\n" +
        "import javax.swing.JOptionPane;\n" +
        "import javax.swing.JPanel;\n" +
        "import javax.swing.JTextField;\n" +
        "import java.awt.event.WindowAdapter;\n" +
        "import java.awt.event.WindowEvent;\n"  +
        "import java.util.ArrayList;\n" +
        "import java.util.List;\n" + 
        "import javax.swing.JDialog;\n" +
        "import javax.swing.JScrollPane;\n" +
        "import javax.swing.JTextArea;\n");
        
        if (listaGUIAux.contains("Date")){
            listaGUI.add(
            "import java.text.SimpleDateFormat;\n" +
            "import java.awt.event.FocusEvent;\n" +
            "import java.awt.event.FocusListener;\n" +
            "import java.text.ParseException;\n");}
        
        if (listaGUIAux.contains("JCheckBox")){
            listaGUI.add("import javax.swing.JCheckBox;\n");}
        
        if (listaGUIAux.contains("JRadioButton")){
            listaGUI.add(
            "import javax.swing.JRadioButton;\n" +
            "import javax.swing.ButtonGroup;");}
        
        if (listaGUIAux.contains("JComboBox")){
            listaGUI.add(
            "import javax.swing.JComboBox;\n");}
        
        if(listaGUIAux.contains("Imagem")){
            listaGUI.add(
            "import javax.swing.ImageIcon;\n" +
            "import java.io.File;\n" +
            "import javax.imageio.ImageIO;\n" +
            "import javax.swing.JFileChooser;\n" +
            "import javax.swing.filechooser.FileFilter;\n" +
            "import javax.swing.filechooser.FileNameExtensionFilter;\n" +
            "import java.awt.event.MouseAdapter;\n" +
            "import java.awt.event.MouseEvent;\n");}
       
      
        listaGUI.add(
        "\n" +
        "public class GUI_" + nomeEntidade + " extends JDialog {\n" +
        "\n" +
        "    private Container cp;\n" +
        "    private JPanel pnNorte = new JPanel(new FlowLayout());\n" +
        "    private JPanel pnCentro = new JPanel(new GridLayout(0, 2));\n" +
        "    private JPanel pnSul = new JPanel(new FlowLayout());\n\n" +
        "    private JLabel lb" + listaGUIAux.get(1) + " = new JLabel(\"" + listaGUIAux.get(1) +":\");\n" +
        "    private JTextField tf" + listaGUIAux.get(1) + " = new JTextField(" + listaGUIAuxSize.get(Aux++) + ");\n\n" +
        "    private JButton btBuscar = new JButton(\"Buscar\");\n" +
        "    private JButton btInserir = new JButton(\"Criar\");\n" +
        "    private JButton btSalvar = new JButton(\"Salvar\");\n" +
        "    private JButton btCancelar = new JButton(\"Cancelar\");\n" +
        "    private JButton btRemover = new JButton(\"Remover\");\n" +
        "    private JButton btAtualizar = new JButton(\"Atualizar\");\n" +
        "    private JButton btList" + listaGUIAux.get(1) + " = new JButton(\"Listar " + listaGUIAux.get(1) + "\");\n" +
        "    private JButton btList" + listaGUIAux.get(3) + " = new JButton(\"Listar " + listaGUIAux.get(3) + "\");\n" +
        "    private JButton btListar" + listaGUIAux.get(1) + " = new JButton(\"Listar(por " + listaGUIAux.get(1) + ")\");\n" +
        "    private JButton btListar" + listaGUIAux.get(3) + " = new JButton(\"Listar(por " + listaGUIAux.get(3) + ")\");\n" +
        "    private String list = \"" + listaGUIAux.get(1) + "\";\n" +
        "    \n" +
        "    private JLabel lbAviso = new JLabel(\"Aviso\");\n" + 
        "    DAO" + nomeEntidade + " controle = new DAO" + nomeEntidade + "();\n" +
        "    List<" + nomeEntidade + "> dados = new ArrayList<>();\n" +
        "    \n" +
        "    private boolean acao = true;\n" +
        "    " + nomeEntidade + " entidade = new " + nomeEntidade + "();\n");
        
        for (int aux = 3; aux < listaGUIAux.size(); aux++, aux++){
            if (listaGUIAux.get(aux -1).equals("Date")){
                listaGUI.add(
                "    private SimpleDateFormat Date" + listaGUIAux.get(aux) + " = new SimpleDateFormat(\"" + listaGUIAuxSize.get(Aux++) + "\");\n" +         
                "    private JLabel lb" + listaGUIAux.get(aux) + " = new JLabel(\"" + listaGUIAux.get(aux) + "\");\n" + 
                "    private JTextField tf" + listaGUIAux.get(aux) + " = new JTextField(20);\n");
            }else{
                if(listaGUIAux.get(aux -1).equals("JCheckBox") || listaGUIAux.get(aux -1).equals("JRadioButton")){
                    listaGUI.add(
                    "    private JLabel lb" + listaGUIAux.get(aux) + " = new JLabel(\"" + listaGUIAux.get(aux) + "\");\n" +
                    "    private JPanel pn" + listaGUIAux.get(aux) + " = new JPanel();\n");
                    
                    if (listaGUIAux.get(aux -1).equals("JRadioButton")){
                        listaGUI.add(
                        "    private ButtonGroup bg" + listaGUIAux.get(aux) + " = new ButtonGroup();");}
                    
                    String auxCBRB [] = listaGUIAuxSize.get((aux -1)/2).split(":");
                    Aux++;
                    for (String CBRB : auxCBRB){
                        int first = 0;
                        if (listaGUIAux.get(aux -1).equals("JCheckBox")){
                            listaGUI.add(
                            "    private JCheckBox cb" + CBRB + " = new JCheckBox(\"" + CBRB + "\");\n");
                        }else{
                            if(first == 0){
                                listaGUI.add(
                                "    private JRadioButton rb" + CBRB + " = new JRadioButton(\"" + CBRB + "\", true);\n");
                                first++;
                            }else{
                                listaGUI.add(
                                "    private JRadioButton rb" + CBRB + " = new JRadioButton(\"" + CBRB + "\");\n");}}}
                }else{
                    if (listaGUIAux.get(aux -1).equals("JComboBox")){
                        String auxComboBox = "\"" + listaGUIAuxSize.get((aux -1)/2).replace(":", "\",\"") + "\"";
                        listaGUI.add(
                        "    private JLabel lb" + listaGUIAux.get(aux) + " = new JLabel(\"" + listaGUIAux.get(aux) + "\");\n" +
                        "    String[] st" + listaGUIAux.get(aux) + " = {" + auxComboBox + "};\n" +
                        "    JComboBox cbx" + listaGUIAux.get(aux) + " = new JComboBox(st" + listaGUIAux.get(aux) + ");");
                    }else{
                        if(listaGUIAux.get(aux -1).equals("Imagem")){
                            listaGUI.add(
                            "    Imagem imagem = new Imagem();\n" +
                            "    ImageIcon i" + listaGUIAux.get(aux) + " = new ImageIcon();\n" +
                            "    private JPanel pnLeste = new JPanel();\n" +
                            "    private JLabel lb" + listaGUIAux.get(aux) + " = new JLabel();\n" +
                            "    File fl" + listaGUIAux.get(aux) + " = null;\n");
                        }else{
                            listaGUI.add(
                            "    private JLabel lb" + listaGUIAux.get(aux) + " = new JLabel(\"" + listaGUIAux.get(aux) + "\");\n" + 
                            "    private JTextField tf" + listaGUIAux.get(aux) + " = new JTextField(" + listaGUIAuxSize.get(Aux++) + ");\n");}}}}}
        
        listaGUI.add(
        "    private JDialog dialog = new JDialog();\n" +
        "    private JTextArea text = new JTextArea();\n" +
        "    private JScrollPane scroll = new JScrollPane(text);\n"+
        "    private " + listaGUIAux.get(0) + " " + listaGUIAux.get(1) + ";");
        
//        if(listaGUIAux.get(0).equals("int")  || listaGUIAux.get(aux -1).equals("Integer")){
//            listaGUI.add(
//            "    int " + listaGUIAux.get(1) + ";\n");}
//        if (listaGUIAux.get(0).equals("Double") ){
//            listaGUI.add(
//            "    Double " + listaGUIAux.get(1) + ";\n");}
//        if(listaGUIAux.get(0).equals("String") ){
//            listaGUI.add(
//            "    String " + listaGUIAux.get(1) + ";\n");}
//        if (listaGUIAux.get(0).equals("Long") ){
//            listaGUI.add(
//            "    Long " + listaGUIAux.get(1) + ";\n");}
            
        if (listaGUIAux.contains("Date")){
            listaGUI.add(
            "    public GUI_" + nomeEntidade + "() throws ParseException{\n");
        }else{
            listaGUI.add(
            "    public GUI_" + nomeEntidade + "() {\n");}
       
        
        listaGUI.add(    
        "        setSize(800, " + (100 + (listaGUIAux.size()/2 -1)*100) + ");\n" +
        "        setResizable(true);\n" +
        "        setDefaultCloseOperation(DISPOSE_ON_CLOSE);\n" +
        "        cp = getContentPane();\n" +
        "        cp.setLayout(new BorderLayout());\n" +
        "        setTitle(\"" + JOptionPane.showInputDialog(null, "Título:", "SetTitle",  JOptionPane.INFORMATION_MESSAGE) + "\");\n" +
        "\n" +
        "        pnNorte.setBackground(Color.white);\n" +
        "        pnCentro.setBackground(Color.lightGray);\n" +
        "        pnSul.setBackground(Color.green);\n" +
        "\n" + 
        "        dialog.add(scroll);\n" +
        "        text.setEditable(false);\n" +
        "        dialog.setSize(500,200);\n" + 
        "\n" + 
        "        pnNorte.add(btList" + listaGUIAux.get(1) + ");\n" +
        "        pnNorte.add(btList" + listaGUIAux.get(3) + ");\n" +
        "        pnNorte.add(lb" + listaGUIAux.get(1) + ");\n" +
        "        pnNorte.add(tf" + listaGUIAux.get(1) + ");\n" +
        "        pnNorte.add(btBuscar);\n" +
        "        pnNorte.add(btInserir);\n" +
        "        pnNorte.add(btSalvar);\n" +
        "        pnNorte.add(btCancelar);\n" +
        "        pnNorte.add(btRemover);\n" +
        "        pnNorte.add(btAtualizar);\n" +
        "        pnNorte.add(btListar" + listaGUIAux.get(1) + ");\n" +
        "        pnNorte.add(btListar" + listaGUIAux.get(3) + ");\n" +
        "        btInserir.setVisible(false);\n" +
        "        btSalvar.setVisible(false);\n" +
        "        btCancelar.setVisible(false);\n" +
        "        btRemover.setVisible(false);\n" +
        "        btAtualizar.setVisible(false);\n" + 
        "\n" +
        "        pnSul.add(lbAviso);\n" +
        "        lbAviso.setOpaque(true);\n" +
        "\n" +
        "        cp.add(pnNorte, BorderLayout.NORTH);\n" +
        "        cp.add(pnCentro, BorderLayout.CENTER);\n" +
        "        cp.add(pnSul, BorderLayout.SOUTH);\n" +
        "\n");
        
        for (int aux = 3; aux < listaGUIAux.size(); aux++, aux++){
            if (listaGUIAux.get(aux -1).equals("JCheckBox")||listaGUIAux.get(aux -1).equals("JRadioButton")){
                String auxCBRB [] = listaGUIAuxSize.get((aux -1)/2).split(":");
                for (String CBRB : auxCBRB){
                    if (listaGUIAux.get(aux -1).equals("JCheckBox")){
                        listaGUI.add(
                        "        cb" + CBRB + ".setEnabled(false);\n" +
                        "        pn" + listaGUIAux.get(aux) + ".add(cb" + CBRB + ");\n");
                    }else{
                        listaGUI.add(
                        "        rb" + CBRB + ".setEnabled(false);\n" +
                        "        bg" + listaGUIAux.get(aux) + ".add(rb" + CBRB +");\n" +
                        "        pn" + listaGUIAux.get(aux) + ".add(rb" + CBRB + ");\n");}}
                    listaGUI.add(
                    "\n"+
                    "        pnCentro.add(lb" + listaGUIAux.get(aux) + ");\n" +
                    "        pnCentro.add(pn" + listaGUIAux.get(aux) + ");\n");
            }else{
                if (listaGUIAux.get(aux -1).equals("JComboBox")){
                    listaGUI.add(
                    "        cbx" + listaGUIAux.get(aux) + ".setEnabled(false);\n" + 
                    "        pnCentro.add(lb" + listaGUIAux.get(aux) + ");\n" +
                    "        pnCentro.add(cbx" + listaGUIAux.get(aux) + ");\n");
                }else{
                    if(listaGUIAux.get(aux -1).equals("Imagem")){
                        listaGUI.add(
                        "        i" + listaGUIAux.get(aux) + " = new ImageIcon(imagem.GetImg(\"src/Imagens/add.png\", 100, 100));\n" +
                        "        lb" + listaGUIAux.get(aux) + ".setIcon(i" + listaGUIAux.get(aux) + ");\n" +
                        "        pnLeste.add(lb" + listaGUIAux.get(aux) + ");\n" +
                        "        cp.add(pnLeste, BorderLayout.EAST);\n" +
                        "        lb" + listaGUIAux.get(aux) + ".setEnabled(false);\n");
                    }else{
                        listaGUI.add(
                        "        tf" + listaGUIAux.get(aux) + ".setEnabled(false);\n" + 
                        "        pnCentro.add(lb" + listaGUIAux.get(aux) + ");\n" + 
                        "        pnCentro.add(tf" + listaGUIAux.get(aux) + ");\n");}}}}
        
        listaGUI.add(
        "\n" + 
        "        btBuscar.addActionListener(new ActionListener() {\n" +
        "\n" +
        "            @Override\n" +
        "            public void actionPerformed(ActionEvent e) {\n" +
        "                try {\n" +
        "                    entidade = new " + nomeEntidade + "();\n");
        
        if(listaGUIAux.get(0).equals("int") || listaGUIAux.get(0).equals("Integer")){
            listaGUI.add(
            "                    " + listaGUIAux.get(1) + " = Integer.valueOf(tf" + listaGUIAux.get(1) + ".getText());\n");}
        if (listaGUIAux.get(0).equals("Double") ){
            listaGUI.add(
            "                    " + listaGUIAux.get(1) + " = Double.valueOf(tf" + listaGUIAux.get(1) + ".getText());\n");}
        if(listaGUIAux.get(0).equals("String") ){
            listaGUI.add(
            "                    " + listaGUIAux.get(1) + " = tf" + listaGUIAux.get(1) + ".getText();\n");}
        if (listaGUIAux.get(0).equals("Long") ){
            listaGUI.add(
            "                    " + listaGUIAux.get(1) + " = Long.valueOf(tf" + listaGUIAux.get(1) + ".getText());\n");}
            
        listaGUI.add(
        "                    entidade.set" + listaGUIAux.get(1) + "(" + listaGUIAux.get(1) + ");\n");
        
        if(listaGUIAux.get(0).equals("String") ){
            listaGUI.add(
            "                    if (" + listaGUIAux.get(1) + ".isEmpty()) {\n" +
            "                        int error = 3/0;");
        }else {
            listaGUI.add(
            "                    if (" + listaGUIAux.get(1) + "<= 0) {\n" +
            "                        int error = 3/0;");}
            
        listaGUI.add(
        "                    }\n" +
        "                    entidade = controle.obter(entidade.get" + listaGUIAux.get(1) + "());\n" +
        "                    lbAviso.setBackground(Color.green);\n" +
        "                    if (entidade != null) {\n" +                        
        "\n" +
        "                        btInserir.setVisible(false);\n" +
        "                        btAtualizar.setVisible(true);\n" +
        "                        btRemover.setVisible(true);\n" +
        "                        lbAviso.setText(\"Achou na lista\");\n");
        
        for (int aux = 3; aux < listaGUIAux.size(); aux++, aux++){
            if(listaGUIAux.get(aux-1).equals("Imagem") ){
                listaGUI.add(
                "                        try{\n" +
                "                            i" + listaGUIAux.get(aux) + " = new ImageIcon(imagem.GetImg(entidade.get" + listaGUIAux.get(aux) + "(), 100, 100));\n" +
                "                            lb" + listaGUIAux.get(aux) + ".setIcon(i" + listaGUIAux.get(aux) + ");\n" +
                "                            if(!entidade.get" + listaGUIAux.get(aux) + "().contains(\"src\")){\n" +
                "                                int Error = 3/0;\n" +
                "                            }\n" +
                "                        }catch(Exception EX){\n" +
                "                            i" + listaGUIAux.get(aux) + " = new ImageIcon(imagem.GetImg(\"src/Imagens/add.png\", 100, 100));\n" +
                "                            lb" + listaGUIAux.get(aux) + ".setIcon(i" + listaGUIAux.get(aux) + ");\n" +
                "                        }\n");
                            }
            if(listaGUIAux.get(aux-1).equals("String") ){
                listaGUI.add(
                "                        tf" + listaGUIAux.get(aux) + ".setText(entidade.get" + listaGUIAux.get(aux) + "());\n");
            }else {
                if(listaGUIAux.get(aux-1).equals("Date") ) {
                    listaGUI.add(
                    "                        tf" + listaGUIAux.get(aux) + ".setText(Date"+listaGUIAux.get(aux)+".format(entidade.get" + listaGUIAux.get(aux) + "()));\n");
                }else{
                    if (listaGUIAux.get(aux -1).equals("JCheckBox")||listaGUIAux.get(aux -1).equals("JRadioButton")){
                        String auxCBRB [] = listaGUIAuxSize.get((aux -1)/2).split(":");
                        for (String CBRB : auxCBRB){
                            if (listaGUIAux.get(aux -1).equals("JCheckBox")){
                                listaGUI.add(
                                "                        cb" + CBRB + ".setSelected(false);\n" +
                                "                        if(entidade.get" + listaGUIAux.get(aux) + "().contains(\"" + CBRB + "\")){\n" + 
                                "                            cb" + CBRB + ".setSelected(true);}\n");
                            }else{
                                listaGUI.add(
                                "                        if(entidade.get" + listaGUIAux.get(aux) + "().contains(\"" + CBRB + "\")){\n" + 
                                "                            rb" + CBRB + ".setSelected(true);}\n");}}
                    }else{
                        if (listaGUIAux.get(aux -1).equals("JComboBox")){
                            listaGUI.add(
                            "                        cbx" + listaGUIAux.get(aux) + ".setSelectedItem(entidade.get" + listaGUIAux.get(aux) + "());\n");
                        }else{
                            if(listaGUIAux.get(aux -1).equals("Imagem")){
                            }else{
                                listaGUI.add(
                                "                        tf" + listaGUIAux.get(aux) + ".setText(String.valueOf(entidade.get" + listaGUIAux.get(aux) + "()));\n");}}}}}}
        
        listaGUI.add(
        "                    } else {\n" +
        "                        lbAviso.setText(\"Não achou na lista\");\n" +
        "                        btInserir.setVisible(true);\n" +
        "\n" +
        "                        lbAviso.setBackground(Color.red);\n" +
        "                        btAtualizar.setVisible(false);\n" +
        "                        btRemover.setVisible(false);\n" +
        "\n");
        
        for (int aux = 3; aux < listaGUIAux.size(); aux++, aux++){
            if (listaGUIAux.get(aux -1).equals("JCheckBox")||listaGUIAux.get(aux -1).equals("JRadioButton")){
                String auxCBRB [] = listaGUIAuxSize.get((aux -1)/2).split(":");
                    for (String CBRB : auxCBRB){
                        if (listaGUIAux.get(aux -1).equals("JCheckBox")){
                            listaGUI.add(
                            "                        cb" + CBRB + ".setEnabled(false);\n");   
                        }else{
                            listaGUI.add(
                            "                        rb" + CBRB + ".setEnabled(false);\n");
                        }}
            }else{
                if (listaGUIAux.get(aux - 1).equals("JComboBox")) {
                    listaGUI.add(
                    "                        cbx" + listaGUIAux.get(aux) + ".setEnabled(false);\n");
                }else{
                    if (listaGUIAux.get(aux - 1).equals("Imagem")) {
                        listaGUI.add(
                        "                        lb" + listaGUIAux.get(aux) + ".setEnabled(false);\n");
                    }else{
                        listaGUI.add(
                        "                        tf" + listaGUIAux.get(aux) + ".setEnabled(false);\n");}}}}
        
        listaGUI.add(
        "                    }\n" +
        "\n" +
        "                } catch (Exception err) {\n" +
        "                    lbAviso.setText(\"Erro nos dados\");\n" +
        "                    lbAviso.setBackground(Color.red);\n" +
        "                }\n" +
        "\n" +
        "            }\n" +
        "        }\n" +
        "        );\n" +
        "\n" +
        "        btInserir.addActionListener(\n" +
        "                new ActionListener() {\n" +
        "                    @Override\n" +
        "                    public void actionPerformed(ActionEvent e\n" +
        "                    ) {\n" +
        "                        acao = true;\n" +
        "                        btBuscar.setVisible(false);\n" +
        "                        btListar" + listaGUIAux.get(1) + ".setVisible(false);\n" +
        "                        btListar" + listaGUIAux.get(3) + ".setVisible(false);\n" +
        "                        btList" + listaGUIAux.get(1) + ".setVisible(false);\n" +
        "                        btList" + listaGUIAux.get(3) + ".setVisible(false);\n"
        );
    
        if (listaGUIAux.get(0).equals("String")){
            listaGUI.add(
            "                        tf" + listaGUIAux.get(1) + ".setText(" + listaGUIAux.get(1) + ");\n" +
            "                        tf" + listaGUIAux.get(1) + ".setEnabled(false);\n");
        }else{
            listaGUI.add(
            "                        tf" + listaGUIAux.get(1) + ".setText(String.valueOf(" + listaGUIAux.get(1) + "));\n" +
            "                        tf" + listaGUIAux.get(1) + ".setEnabled(false);\n");}
        
        
        Aux = 1;
        for (int aux = 3; aux < listaGUIAux.size(); aux++, aux++){
            if (listaGUIAux.get(aux -1).equals("JCheckBox")||listaGUIAux.get(aux -1).equals("JRadioButton")){
                String auxCBRB [] = listaGUIAuxSize.get((aux -1)/2).split(":");
                for (String CBRB : auxCBRB){
                    if (listaGUIAux.get(aux -1).equals("JCheckBox")){
                        listaGUI.add(
                        "                        cb" + CBRB + ".setEnabled(true);\n" + 
                        "                        cb" + CBRB + ".setSelected(false);\n");
                    }else{
                        listaGUI.add(
                        "                        rb" + CBRB + ".setEnabled(true);\n");}}
            }else{
                if (listaGUIAux.get(aux -1).equals("JComboBox")){
                    listaGUI.add(
                    "                        cbx" + listaGUIAux.get(aux) + ".setEnabled(true);\n" +
                    "                        cbx" + listaGUIAux.get(aux) + ".setSelectedIndex(0);\n");
                }else{
                    if (listaGUIAux.get(aux - 1).equals("Imagem")) {
                        listaGUI.add(
                        "                        i" + listaGUIAux.get(aux) + " = new ImageIcon(imagem.GetImg(\"src/Imagens/add.png\", 100, 100));\n" +
                        "                        lb" + listaGUIAux.get(aux) + ".setIcon(i" + listaGUIAux.get(aux) + ");\n" +
                        "                        lb" + listaGUIAux.get(aux) + ".setEnabled(true);\n");
                    }else{
                        listaGUI.add(
                        "                        tf" + listaGUIAux.get(aux) + ".setEnabled(true);\n" + 
                        "                        tf" + listaGUIAux.get(aux) + ".setText(String.valueOf(\"\"));\n");}}}
            
            if (listaGUIAux.get(aux -1).equals("Date")){
                listaGUI.add(
                "                        tf" + listaGUIAux.get(aux) + ".addFocusListener(new FocusListener() {\n" +
                "                        public void focusGained(FocusEvent e) {\n" +
                "                            if (tf" + listaGUIAux.get(aux) + ".getText().equals(\"" + listaGUIAuxSize.get(Aux) + "\")){\n" +
                "                                tf" + listaGUIAux.get(aux) + ".setText(\"\");}}\n" +
                "                        public void focusLost(FocusEvent e) {\n" +
                "                            if (tf" + listaGUIAux.get(aux) + ".getText().equals(\"\")){\n" +
                "                                tf" + listaGUIAux.get(aux) + ".setText(\"" + listaGUIAuxSize.get(Aux) + "\");}}});\n" +
                "                        tf" + listaGUIAux.get(aux) + ".requestFocus();\n\n");}
            Aux++;}
        
        if(!listaGUIAux.get(2).equals("JCheckBox") || !listaGUIAux.get(2).equals("JRadioButton") || !listaGUIAux.get(2).equals("JComboBox") || !listaGUIAux.get(2).equals("Imagem")){
            listaGUI.add("                        tf" + listaGUIAux.get(3) + ".requestFocus();\n");}
        
        listaGUI.add(
        "\n" +
        "                        btInserir.setVisible(false);\n" +
        "                        btSalvar.setVisible(true);\n" +
        "                        btCancelar.setVisible(true);\n" +
        "                        btRemover.setVisible(false);\n" +
        "                        btAtualizar.setVisible(false);\n" +
        "                    }\n" +
        "                }\n" +
        "        );\n" +
        "\n" +
        "        btSalvar.addActionListener(\n" +
        "                new ActionListener() {\n" +
        "\n" +
        "                    @Override\n" +
        "                    public void actionPerformed(ActionEvent e\n" +
        "                    ) {\n" +
        "                        if (acao) {\n" +
        "                            try{\n" +
        "\n" +
        "                            " + nomeEntidade + " entidade = new " + nomeEntidade + "();\n");
        
        for (int aux = 1; aux < listaGUIAux.size(); aux++, aux++){
            if (aux == 1){
                listaGUI.add(
                "                            entidade.set" + listaGUIAux.get(aux) + "(" + listaGUIAux.get(aux) + ");\n");
            }else{
                if(listaGUIAux.get(aux -1).equals("int")  || listaGUIAux.get(aux -1).equals("Integer")){
                    listaGUI.add(
                    "                            entidade.set" + listaGUIAux.get(aux) + "(Integer.valueOf(tf" + listaGUIAux.get(aux) + ".getText()));\n");}
                if (listaGUIAux.get(aux -1).equals("Double") ){
                    listaGUI.add(
                    "                            entidade.set" + listaGUIAux.get(aux) + "(Double.valueOf(tf" + listaGUIAux.get(aux) + ".getText()));\n");}
                if(listaGUIAux.get(aux-1).equals("String") ) {
                    listaGUI.add(
                    "                            entidade.set" + listaGUIAux.get(aux) + "(tf" + listaGUIAux.get(aux) + ".getText());\n");}    
                if (listaGUIAux.get(aux -1).equals("Long") ){
                    listaGUI.add(
                    "                            entidade.set" + listaGUIAux.get(aux) + "(Long.valueOf(tf" + listaGUIAux.get(aux) + ".getText()));\n");}
                if (listaGUIAux.get(aux -1).equals("Date") ){
                    listaGUI.add(
                    "                            Date" + listaGUIAux.get(aux) + ".setLenient(false);\n" +
                    "                            entidade.set" + listaGUIAux.get(aux) + "(Date" + listaGUIAux.get(aux) + ".parse(tf" + listaGUIAux.get(aux) + ".getText()));\n");}
                if (listaGUIAux.get(aux -1).equals("JCheckBox")){
                    listaGUI.add(
                    "                            String cb" + listaGUIAux.get(aux) + " = \" \";\n");
                        String auxCBRB [] = listaGUIAuxSize.get((aux -1)/2).split(":");
                    for (String CBRB : auxCBRB){
                        listaGUI.add(
                        "                            if(cb" + CBRB + ".isSelected()){\n" + 
                        "                                cb" + listaGUIAux.get(aux) + " += \"" + CBRB + ":\";}\n");}
                    listaGUI.add(
                    "                            entidade.set" + listaGUIAux.get(aux) + "(cb" + listaGUIAux.get(aux) + ");\n");}
                
                if (listaGUIAux.get(aux -1).equals("JRadioButton")){
                    listaGUI.add(
                    "                            String rb" + listaGUIAux.get(aux) + " = \" \";\n");
                        String auxCBRB [] = listaGUIAuxSize.get((aux -1)/2).split(":");
                    for (String CBRB : auxCBRB){
                        listaGUI.add(
                        "                            if(rb" + CBRB + ".isSelected()){\n" + 
                        "                                rb" + listaGUIAux.get(aux) + " = \"" + CBRB + "\";}\n");}
                    listaGUI.add(
                    "                            entidade.set" + listaGUIAux.get(aux) + "(rb" + listaGUIAux.get(aux) + ");\n");}
                if (listaGUIAux.get(aux -1).equals("JComboBox")){
                    listaGUI.add(
                    "                            entidade.set" + listaGUIAux.get(aux) + "(String.valueOf(cbx" + listaGUIAux.get(aux) + ".getSelectedItem()));\n");}}
                if (listaGUIAux.get(aux -1).equals("Imagem")){
                    listaGUI.add(
                    "                            if(fl" + listaGUIAux.get(aux) + " != null){\n" +
                    "                                entidade.set" + listaGUIAux.get(aux) + "(imagem.MoverImagem(String.valueOf(fl" + listaGUIAux.get(aux) + "), \"src/Imagens/\", String.valueOf(entidade.get" + listaGUIAux.get(1) + "())));\n" +
                    "                                fl" + listaGUIAux.get(aux) + " = null;\n" +
                    "                            }else{\n" +
                    "                                i" + listaGUIAux.get(aux) + " = new ImageIcon(imagem.GetImg(\"src/Imagens/add.png\", 100, 100));\n" +
                    "                                lb" + listaGUIAux.get(aux) + ".setIcon(i" + listaGUIAux.get(aux) + ");\n" +
                    "                            }\n");
                }}
                
        listaGUI.add(
        "                            controle.inserir(entidade);\n" +
        "\n" +
        "                            lbAviso.setText(\"Registro inserido\");\n" +
        "                            btSalvar.setVisible(false);\n" +
        "                            btCancelar.setVisible(false);\n" +
        "                            btBuscar.setVisible(true);\n" +      
        "                            btListar" + listaGUIAux.get(1) + ".setVisible(true);\n" +      
        "                            btListar" + listaGUIAux.get(3) + ".setVisible(true);\n" +      
        "                            btList" + listaGUIAux.get(1) + ".setVisible(true);\n" +      
        "                            btList" + listaGUIAux.get(3) + ".setVisible(true);\n" +      
        "                            tf" + listaGUIAux.get(1) + ".setEnabled(true);\n" +
        "                            tf" + listaGUIAux.get(1) + ".requestFocus();\n" +
        "                            tf" + listaGUIAux.get(1) + ".selectAll();\n");
        
        for (int aux = 3; aux < listaGUIAux.size(); aux++, aux++){
            if (listaGUIAux.get(aux -1).equals("JCheckBox")||listaGUIAux.get(aux -1).equals("JRadioButton")){
                String auxCBRB [] = listaGUIAuxSize.get((aux -1)/2).split(":");
                for (String CBRB : auxCBRB){
                    if (listaGUIAux.get(aux -1).equals("JCheckBox")){
                        listaGUI.add(
                        "                            cb" + CBRB + ".setEnabled(false);\n");
                    }else{
                        listaGUI.add(
                        "                            rb" + CBRB + ".setEnabled(false);\n");}}
            }else{
                if (listaGUIAux.get(aux -1).equals("JComboBox")){
                    listaGUI.add(
                    "                            cbx" + listaGUIAux.get(aux) + ".setEnabled(false);\n");
                }else{
                    if(listaGUIAux.get(aux -1).equals("Imagem")){
                        listaGUI.add(
                        "                            lb" + listaGUIAux.get(aux) + ".setEnabled(false);\n");
                    }else{
                        listaGUI.add(
                        "                            tf" + listaGUIAux.get(aux) + ".setEnabled(false);\n");}}}}
        
        listaGUI.add(
        "                            lbAviso.setBackground(Color.green);\n" +
        "                }catch (Exception btSalvar){\n" + 
        "                    JOptionPane.showMessageDialog(null, \"ERRO NOS DADOS\", \"ERROR\", JOptionPane.ERROR_MESSAGE);}\n\n" +
        "                        } else {\n" +
        "                            try{\n" +
        "                            " + nomeEntidade + " entidadeOriginal = entidade;\n" +
        "                            " + nomeEntidade + " entidadeModificada = new " + nomeEntidade + "();\n");
        
         for (int aux = 1; aux < listaGUIAux.size(); aux++, aux++){
            if (aux == 1){
                listaGUI.add(
                "                            entidadeModificada.set" + listaGUIAux.get(aux) + "(" + listaGUIAux.get(aux) + ");\n");
            }else{
                if(listaGUIAux.get(aux -1).equals("int")  || listaGUIAux.get(aux -1).equals("Integer")){
                    listaGUI.add(
                    "                            entidadeModificada.set" + listaGUIAux.get(aux) + "(Integer.valueOf(tf" + listaGUIAux.get(aux) + ".getText()));\n");}
                if (listaGUIAux.get(aux -1).equals("Double") ){
                    listaGUI.add(
                    "                            entidadeModificada.set" + listaGUIAux.get(aux) + "(Double.valueOf(tf" + listaGUIAux.get(aux) + ".getText()));\n");}
                if(listaGUIAux.get(aux-1).equals("String") ) {
                    listaGUI.add(
                    "                            entidadeModificada.set" + listaGUIAux.get(aux) + "(tf" + listaGUIAux.get(aux) + ".getText());\n");}    
                if (listaGUIAux.get(aux -1).equals("Long") ){
                    listaGUI.add(
                    "                            entidadeModificada.set" + listaGUIAux.get(aux) + "(Long.valueOf(tf" + listaGUIAux.get(aux) + ".getText()));\n");}
                if (listaGUIAux.get(aux -1).equals("Date") ){
                    listaGUI.add(
                    "                            Date" + listaGUIAux.get(aux) + ".setLenient(false);\n" +
                    "                            entidadeModificada.set" + listaGUIAux.get(aux) + "(Date" + listaGUIAux.get(aux) + ".parse(tf" + listaGUIAux.get(aux) + ".getText()));\n");}
                if (listaGUIAux.get(aux -1).equals("JCheckBox") ){
                    listaGUI.add("                           String cb" + listaGUIAux.get(aux) + " = \" \";\n");
                    String auxCBRB [] = listaGUIAuxSize.get((aux -1)/2).split(":");
                    for (String CBRB : auxCBRB){
                        listaGUI.add(
                        "                        if(cb" + CBRB + ".isSelected()){\n" + 
                        "                            cb" + listaGUIAux.get(aux) + " += \"" + CBRB + ":\";}\n");}
                    listaGUI.add("                            entidadeModificada.set" + listaGUIAux.get(aux) + "(cb" + listaGUIAux.get(aux) + ");\n");}
             if (listaGUIAux.get(aux -1).equals("JRadioButton") ){
                    listaGUI.add("                           String rb" + listaGUIAux.get(aux) + " = \" \";\n");
                    String auxCBRB [] = listaGUIAuxSize.get((aux -1)/2).split(":");
                    for (String CBRB : auxCBRB){
                        listaGUI.add(
                        "                        if(rb" + CBRB + ".isSelected()){\n" + 
                        "                            rb" + listaGUIAux.get(aux) + " = \"" + CBRB + "\";}\n");}
                    listaGUI.add("                            entidadeModificada.set" + listaGUIAux.get(aux) + "(rb" + listaGUIAux.get(aux) + ");\n");}
            if (listaGUIAux.get(aux -1).equals("JComboBox")){
                    listaGUI.add(
                    "                            entidadeModificada.set" + listaGUIAux.get(aux) + "(String.valueOf(cbx" + listaGUIAux.get(aux) + ".getSelectedItem()));\n");}}
            if(listaGUIAux.get(aux -1).equals("Imagem")){
                listaGUI.add(
                "                            if(fl" + listaGUIAux.get(aux) + " != null){\n" +
                "                                if(entidade.get" + listaGUIAux.get(aux) + "() != null){\n" +
                "                                    imagem.DeletarImagem(entidade.get" + listaGUIAux.get(aux) + "());}\n" +
                "                                entidadeModificada.set" + listaGUIAux.get(aux) + "(imagem.MoverImagem(String.valueOf(fl" + listaGUIAux.get(aux) + "), \"src/Imagens/\", String.valueOf(entidade.get" + listaGUIAux.get(1) + "())));\n" +
                "                                fl" + listaGUIAux.get(aux) + " = null;\n" +
                "                            }else{\n" +
                "                                entidadeModificada.set" + listaGUIAux.get(aux) + "(entidade.get" + listaGUIAux.get(aux) + "());\n" +
                "                            }\n");}}
         
        listaGUI.add(
        "\n" +
        "                            controle.atualizar(entidadeModificada);\n" +
        "\n" +
        "                            lbAviso.setText(\"Registro alterado\");\n" +
        "                            tf" + listaGUIAux.get(1) + ".setEnabled(true);\n" +
        "                            tf" + listaGUIAux.get(1) + ".requestFocus();\n" +
        "                            tf" + listaGUIAux.get(1) + ".selectAll();\n" +
        "                            btSalvar.setVisible(false);\n" +
        "                            btCancelar.setVisible(false);\n" +
        "                            btBuscar.setVisible(true);\n" + 
        "                            btListar" + listaGUIAux.get(1) + ".setVisible(true);\n" +
        "                            btListar" + listaGUIAux.get(3) + ".setVisible(true);\n" +
        "                            btList" + listaGUIAux.get(1) + ".setVisible(true);\n" +
        "                            btList" + listaGUIAux.get(3) + ".setVisible(true);\n"
        );
        
        for (int aux = 3; aux < listaGUIAux.size(); aux++, aux++){
            if (listaGUIAux.get(aux -1).equals("JCheckBox")||listaGUIAux.get(aux -1).equals("JRadioButton")){
                String auxCBRB [] = listaGUIAuxSize.get((aux -1)/2).split(":");
                for (String CBRB : auxCBRB){
                    if (listaGUIAux.get(aux -1).equals("JCheckBox")){
                        listaGUI.add(
                        "                            cb" + CBRB + ".setEnabled(false);\n");
                    }else{
                        listaGUI.add(
                        "                            rb" + CBRB + ".setEnabled(false);\n");}}
            }else{
                if (listaGUIAux.get(aux -1).equals("JComboBox")){
                    listaGUI.add(
                    "                            cbx" + listaGUIAux.get(aux) + ".setEnabled(false);\n");
                }else{
                    if(listaGUIAux.get(aux -1).equals("Imagem")){
                        listaGUI.add(
                        "                            lb" + listaGUIAux.get(aux) + ".setEnabled(false);\n");
                    }else{
                        listaGUI.add(
                        "                            tf" + listaGUIAux.get(aux) + ".setEnabled(false);\n");}}}}
        
        listaGUI.add(
        "                            lbAviso.setBackground(Color.green);\n" +
        "                            }catch (Exception btSalvarAtt){\n" +
        "                                JOptionPane.showMessageDialog(null, \"ERRO NOS DADOS\", \"ERROR\", JOptionPane.ERROR_MESSAGE);}\n\n" +
        "\n" +
        "                        }\n" +
        "                    }\n" +
        "                }\n" +
        "        );\n" +
        "\n" +
        "        btCancelar.addActionListener(\n" +
        "                new ActionListener() {\n" +
        "\n" +
        "                    @Override\n" +
        "                    public void actionPerformed(ActionEvent e\n" +
        "                    ) {\n" +
        "                        lbAviso.setText(\"Cancelado\");\n" +
        "                        tf" + listaGUIAux.get(1) + ".setEnabled(true);\n" +
        "\n" +
        "                        tf" + listaGUIAux.get(1) + ".requestFocus();\n" +
        "                        tf" + listaGUIAux.get(1) + ".selectAll();\n" +
        "\n" +
        "                        btSalvar.setVisible(false);\n" +
        "                        btCancelar.setVisible(false);\n" +
        "                        btBuscar.setVisible(true);\n" +
        "                        btListar" + listaGUIAux.get(1) + ".setVisible(true);\n" +
        "                        btListar" + listaGUIAux.get(3) + ".setVisible(true);\n" +
        "                        btList" + listaGUIAux.get(1) + ".setVisible(true);\n" +
        "                        btList" + listaGUIAux.get(3) + ".setVisible(true);\n" +
        "                        lbAviso.setBackground(Color.green);\n");
        
        for (int aux = 3; aux < listaGUIAux.size(); aux++, aux++){
            if (listaGUIAux.get(aux -1).equals("JCheckBox")||listaGUIAux.get(aux -1).equals("JRadioButton")){
                String auxCBRB [] = listaGUIAuxSize.get((aux -1)/2).split(":");
                for (String CBRB : auxCBRB){
                    if (listaGUIAux.get(aux -1).equals("JCheckBox")){
                        listaGUI.add(
                        "                        cb" + CBRB + ".setEnabled(false);\n" + 
                        "                        cb" + CBRB + ".setSelected(false);\n");
                    }else{
                        listaGUI.add(
                        "                        rb" + CBRB + ".setEnabled(false);\n");}}
            }else{
                if (listaGUIAux.get(aux -1).equals("JComboBox")){
                    listaGUI.add(
                    "                        cbx" + listaGUIAux.get(aux) + ".setEnabled(false);\n" +
                    "                        cbx" + listaGUIAux.get(aux) + ".setSelectedIndex(0);\n");
                }else{
                    if(listaGUIAux.get(aux -1).equals("Imagem")){
                        listaGUI.add(
                        "                        i" + listaGUIAux.get(aux) + " = new ImageIcon(imagem.GetImg(\"src/Imagens/add.png\", 100, 100));\n" +
                        "                        lb" + listaGUIAux.get(aux) + ".setIcon(i" + listaGUIAux.get(aux) + ");\n" +
                        "                        lb" + listaGUIAux.get(aux) + ".setEnabled(false);\n");
                    }else{
                        listaGUI.add(
                        "                        tf" + listaGUIAux.get(aux) + ".setEnabled(false);\n" + 
                        "                        tf" + listaGUIAux.get(aux) + ".setText(String.valueOf(\"\"));\n");}}}}
        
        listaGUI.add(
        "                    }\n" +
        "                }\n" +
        "        );\n" +
        "\n" +
        "        btAtualizar.addActionListener(\n" +
        "                new ActionListener() {\n" +
        "\n" +
        "                    @Override\n" +
        "                    public void actionPerformed(ActionEvent e\n" +
        "                    ) {\n" +
        "                        acao = false;\n");
        
        if (listaGUIAux.get(0).equals("String")){
            listaGUI.add(
            "                        tf" + listaGUIAux.get(1) + ".setText(" + listaGUIAux.get(1) + ");\n" +
            "                        tf" + listaGUIAux.get(1) + ".setEnabled(false);\n");
        }else{
            listaGUI.add(
            "                        tf" + listaGUIAux.get(1) + ".setText(String.valueOf(" + listaGUIAux.get(1) + "));\n" +
            "                        tf" + listaGUIAux.get(1) + ".setEnabled(false);\n");}
        
        for (int aux = 3; aux < listaGUIAux.size(); aux++, aux++){
            if (listaGUIAux.get(aux -1).equals("JCheckBox")||listaGUIAux.get(aux -1).equals("JRadioButton")){
                String auxCBRB [] = listaGUIAuxSize.get((aux -1)/2).split(":");
                for (String CBRB : auxCBRB){
                    if (listaGUIAux.get(aux -1).equals("JCheckBox")){
                        listaGUI.add(
                        "                        cb" + CBRB + ".setEnabled(true);\n");
                    }else{
                        listaGUI.add(
                        "                        rb" + CBRB + ".setEnabled(true);\n");}}
            }else{
                if (listaGUIAux.get(aux -1).equals("JComboBox")){
                    listaGUI.add(
                    "                        cbx" + listaGUIAux.get(aux) + ".setEnabled(true);\n");
                }else{
                    if(listaGUIAux.get(aux -1).equals("Imagem")){
                        listaGUI.add(
                        "                        lb" + listaGUIAux.get(aux) + ".setEnabled(true);\n");
                    }else{
                        listaGUI.add(
                        "                        tf" + listaGUIAux.get(aux) + ".setEnabled(true);\n");}}}}
        
        if(!listaGUIAux.get(2).equals("JCheckBox") || !listaGUIAux.get(2).equals("JRadioButton") || !listaGUIAux.get(2).equals("JComboBox")){
            listaGUI.add("                        tf" + listaGUIAux.get(3) + ".requestFocus();\n");}
        
        listaGUI.add(
        "                        btSalvar.setVisible(true);\n" +
        "                        btCancelar.setVisible(true);\n" +
        "                        btBuscar.setVisible(false);\n" +
        "                        btListar" + listaGUIAux.get(1) + ".setVisible(false);\n" +
        "                        btListar" + listaGUIAux.get(3) + ".setVisible(false);\n" +
        "                        btList" + listaGUIAux.get(1) + ".setVisible(false);\n" +
        "                        btList" + listaGUIAux.get(3) + ".setVisible(false);\n" +
        "                        btRemover.setVisible(false);\n" +
        "                        btAtualizar.setVisible(false);\n" +
        "\n" +
        "                    }\n" +
        "                }\n" +
        "        );\n" +
        "\n" +
        "        btRemover.addActionListener(\n" +
        "                new ActionListener() {\n" +
        "\n" +
        "                    @Override\n" +
        "                    public void actionPerformed(ActionEvent e\n" +
        "                    ) {\n" +
        "                        btRemover.setVisible(false);\n" +
        "                        btAtualizar.setVisible(false);\n" +
        "                        if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,\n" +
        "                                \"Confirma a exclusão do registro <ID = \" + entidade.get" + listaGUIAux.get(1) + "() + \">?\", \"Confirm\",\n" +
        "                                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {\n" +
        "\n");
        
        for (int aux = 3; aux < listaGUIAux.size(); aux++, aux++){
            if (listaGUIAux.get(aux -1).equals("JCheckBox")||listaGUIAux.get(aux -1).equals("JRadioButton")){
                String auxCBRB [] = listaGUIAuxSize.get((aux -1)/2).split(":");
                for (String CBRB : auxCBRB){
                    if (listaGUIAux.get(aux -1).equals("JCheckBox")){
                        listaGUI.add(
                        "                        cb" + CBRB + ".setEnabled(false);\n" + 
                        "                        cb" + CBRB + ".setSelected(false);\n");   
                    }else{
                        listaGUI.add(
                        "                        rb" + CBRB + ".setEnabled(false);\n");}}
            }else{
                if (listaGUIAux.get(aux -1).equals("JComboBox")){
                    listaGUI.add(
                    "                        cbx" + listaGUIAux.get(aux) + ".setEnabled(false);\n" +
                    "                        cbx" + listaGUIAux.get(aux) + ".setSelectedIndex(0);\n");
                }else{
                    if(listaGUIAux.get(aux -1).equals("Imagem")){
                        listaGUI.add(
                        "                        if(entidade.get" + listaGUIAux.get(aux) + "() != null){\n" +
                        "                            imagem.DeletarImagem(entidade.get" + listaGUIAux.get(aux) + "());\n" +
                        "                        }\n" +
                        "                        i" + listaGUIAux.get(aux) + " = new ImageIcon(imagem.GetImg(\"src/Imagens/add.png\", 100, 100));\n" +
                        "                        lb" + listaGUIAux.get(aux) + ".setIcon(i" + listaGUIAux.get(aux) + ");\n");
                    }else{
                        listaGUI.add(
                        "                        tf" + listaGUIAux.get(aux) + ".setText(String.valueOf(\"\"));\n");}}}}
        
        listaGUI.add(
        "                        tf" + listaGUIAux.get(1) + ".requestFocus();\n" +
        "                        tf" + listaGUIAux.get(1) + ".setEnabled(true);\n" +
        "                        tf" + listaGUIAux.get(1) + ".setText(String.valueOf(\"\"));\n"  +
        "                        controle.remover(entidade);\n" +
        "                        lbAviso.setText(\"Removeu\");\n" +
        "\n" +
        "\n" +
        "                        } else {\n" +
        "                            lbAviso.setText(\"Cancelada a remoção\");\n" +
        "                            btRemover.setVisible(true);\n" +
        "                            btAtualizar.setVisible(true);\n" +
        "                        }\n" +
        "\n" +
        "                    }\n" +
        "                }\n" +
        "        );\n" +
        "\n" +
        "        btList" + listaGUIAux.get(1) + ".addActionListener( new ActionListener() {\n" +
        "            @Override\n" +
        "            public void actionPerformed(ActionEvent ae) {\n" +
        "                list = \"by" + listaGUIAux.get(1) + "\";\n" +
        "                btListar" + listaGUIAux.get(1) + ".doClick();\n" +
        "            }\n" +
        "        });\n" +
        "\n" +
        "        btList" + listaGUIAux.get(3) + ".addActionListener( new ActionListener() {\n" +
        "            @Override\n" +
        "            public void actionPerformed(ActionEvent ae) {\n" +
        "                list = \"by" + listaGUIAux.get(3) + "\";\n" +
        "                btListar" + listaGUIAux.get(1) + ".doClick();\n" +
        "            }\n" +
        "        });\n" +
        "\n" +
        "        btListar" + listaGUIAux.get(3) + ".addActionListener( new ActionListener() {\n" +
        "            @Override\n" +
        "            public void actionPerformed(ActionEvent ae) {\n" +
        "                list = \"" + listaGUIAux.get(3) + "\";\n" +
        "                btListar" + listaGUIAux.get(1) + ".doClick();\n" +
        "            }\n" +
        "        });\n" +
        "\n" +
        "        btListar" + listaGUIAux.get(1) + ".addActionListener( new ActionListener(){\n" +
        "            @Override\n" +
        "            public void actionPerformed(ActionEvent e) {\n" +
        "                try{\n" +
        "                String aux[];\n" +
        "                text.setText(\"\");\n" +
        "                dados.clear();\n" +
        "                    switch (list) {\n" +
        "                        case \"" + listaGUIAux.get(1) + "\":\n" +
        "                            dados = controle.listInOrderId();\n" +
        "                            break;\n" +
        "                        case \"" + listaGUIAux.get(3) + "\":\n" +
        "                            dados = controle.listInOrderNome();\n" +
        "                            break;\n" +
        "                        case \"by" + listaGUIAux.get(1) + "\":\n" +
        "                            dados = controle.listById(Integer.valueOf(tf" + listaGUIAux.get(1) + ".getText()));\n" +
        "                            break;\n" +
        "                        default:\n" +
        "                            dados = controle.listByNome(tf" + listaGUIAux.get(1) + ".getText());\n" +
        "                            break;\n" +
        "                    }\n" +
        "                try{\n" +
        "                if (dados.get(0) == null){}\n" +
        "                }catch(Exception lista){\n" +
        "                    int erro = 3/0;" +
        "                }\n" +
        "                for (" + nomeEntidade + " linha : dados) {\n" +
        "                    aux = String.valueOf(linha).split(\";\");\n" +
        "                    text.append(");
        
        Aux = 0;
        for (int aux = 1; aux < listaGUIAux.size(); aux++, aux++){
            
            listaGUI.add("\"" + listaGUIAux.get(aux) + ": \" + ");
            
            if(listaGUIAux.get(aux -1).equals("int") || listaGUIAux.get(aux -1).equals("Integer")){
                listaGUI.add(
                "            Integer.valueOf(aux[" + (Aux++) + "])");}
            if (listaGUIAux.get(aux -1).equals("Double") ){
                listaGUI.add(
                "            Double.valueOf(aux[" + (Aux++) + "])");}
            if(listaGUIAux.get(aux-1).equals("String") || listaGUIAux.get(aux-1).equals("Imagem")) {
                listaGUI.add(
                "            aux[" + (Aux++) + "]");}
            if(listaGUIAux.get(aux-1).equals("Long") ) {
                listaGUI.add(
                "            Long.valueOf(aux[" + (Aux++) + "])");}
            if(listaGUIAux.get(aux-1).equals("Date") ) {
                listaGUI.add(
                "            Date" + listaGUIAux.get(aux) + ".format(Date"+listaGUIAux.get(aux)+".parse(aux[" + (Aux++) + "]))");}
            if (listaGUIAux.get(aux -1).equals("JCheckBox")){
                listaGUI.add(
                "            aux[" + (Aux++) + "].replace(\":\",\"|\")");}
            if (listaGUIAux.get(aux -1).equals("JRadioButton")){
                listaGUI.add(
                "            aux[" + (Aux++) + "]");}
            if (listaGUIAux.get(aux -1).equals("JComboBox")){
                listaGUI.add(
                "            aux[" + (Aux++) + "]");}
            if(aux != listaGUIAux.size()-1){
                listaGUI.add(" + \"\\n\" +");}
            }
            listaGUI.add("+ \"\\n-------------------------------------------------------------------------------------------\\n\"");
        
        listaGUI.add(
        ");}\n" +
        "                dialog.setLocationRelativeTo(cp);\n" +
        "                dialog.setModal(true);\n" + 
        "                dialog.setVisible(true);\n" +
        "                }catch(Exception Lista){\n" +
        "                    JOptionPane.showMessageDialog(null, \"NOTHING TO SEE HERE! 8P\", \"OPS\", JOptionPane.PLAIN_MESSAGE);\n" +
        "                    }finally{\n" +
        "                        list = \"" + listaGUIAux.get(1) + "\";\n" +
        "                    }\n" +
        "        }});\n" +
        "\n" +
        "       addWindowListener(new WindowAdapter() {\n" +
        "            @Override\n" +
        "            public void windowClosing(WindowEvent e) {\n" +
        "            System.exit(0);" +
        "            }\n" +
        "        });\n" +
        "        \n" + 
        "        tf" + listaGUIAux.get(1) + ".addActionListener(new ActionListener() {\n" +
        "        @Override\n" +
        "            public void actionPerformed(ActionEvent e) {\n" +
        "            btBuscar.doClick();}});\n");
        
        for (int aux = 1; aux < listaGUIAux.size(); aux++, aux++){
            if(listaGUIAux.get(aux -1).equals("Imagem")){
                listaGUI.add(
                "        lb" + listaGUIAux.get(aux) + ".addMouseListener(new MouseAdapter() {\n" +
                "            public void mouseClicked(MouseEvent me) {\n" +
                "                if (lb" + listaGUIAux.get(aux) + ".isEnabled()) {\n" +
                "                    FileFilter imageFilter = new FileNameExtensionFilter(\"Image files\", ImageIO.getReaderFileSuffixes());\n" +
                "                    JFileChooser file = new JFileChooser();\n" +
                "                    file.setFileSelectionMode(JFileChooser.FILES_ONLY);\n" +
                "                    file.setFileFilter(imageFilter);\n" +
                "                    int i = file.showSaveDialog(null);\n" +
                "                    if (i == 1) {\n" +
                "                        i" + listaGUIAux.get(aux) + " = new ImageIcon(imagem.GetImg(\"src/Imagens/add.png\", 100, 100));\n" +
                "                        lb" + listaGUIAux.get(aux) + ".setIcon(i" + listaGUIAux.get(aux) + ");\n" +
                "                        fl" + listaGUIAux.get(aux) + " = null;\n" +
                "                    } else {\n" +
                "                        try{\n" +
                "                            fl" + listaGUIAux.get(aux) + " = file.getSelectedFile();\n" +
                "                            i" + listaGUIAux.get(aux) + " = new ImageIcon(imagem.GetImg(String.valueOf(fl" + listaGUIAux.get(aux) + "), 100, 100));\n" +
                "                            lb" + listaGUIAux.get(aux) + ".setIcon(i" + listaGUIAux.get(aux) + ");\n" +
                "                        }catch(Exception img){\n" +
                "                            System.out.println(\"JFileChooser Listerner Error: \" + img);}\n" +
                "                    }\n" +
                "                }}});\n");}}
        listaGUI.add(
        "\n" +
        "        setLocationRelativeTo(null);\n" +
        "        setModal(true);\n" +
        "        setVisible(true);\n" +
        "    }\n" +
        "}\n" +
        ""        );
        return listaGUI;
    }
    
    List<String> listaImagem = new ArrayList<>();
    public List<String> Imagem(String nomePacote){
        listaImagem.add(
        "package " + nomePacote + ";\n" +
        "\n" +
        "import java.awt.Image;\n" +
        "import java.io.File;\n" +
        "import java.nio.file.Files;\n" +
        "import javax.swing.ImageIcon;\n" +
        "\n" +
        "public class Imagem {\n" +
        "\n" +
        "    public String MoverImagem(String origem, String destino, String id) throws Exception {\n" +
        "        try {\n" +
        "            File source = new File(origem);\n" +
        "            File dest = new File(\"src/" + nomePacote + "/\" + origem.substring(origem.lastIndexOf(\"\\\\\")));\n" +
        "            Files.copy(source.toPath(), dest.toPath());\n" +
        "\n" +
        "            File name = new File(String.valueOf(dest));\n" +
        "            String Dest = String.valueOf(dest);\n" +
        "            File rename = new File(Dest.substring(0, Dest.lastIndexOf(\"\\\\\")) + \"/\" + id + Dest.substring(Dest.lastIndexOf(\".\")));\n" +
        "            name.renameTo(rename);\n" +
        "\n" +
        "            Dest = String.valueOf(rename);\n" +
        "            source = new File(Dest);\n" +
        "            dest = new File(destino + Dest.substring(Dest.lastIndexOf(\"\\\\\")));\n" +
        "            Files.copy(source.toPath(), dest.toPath());\n" +
        "            Dest = String.valueOf(dest);\n" +
        "\n" +
        "            DeletarImagem(String.valueOf(rename));\n" +
        "\n" +
        "            return Dest;\n" +
        "        } catch (Exception img) {\n" +
        "            System.out.println(\"MoverImagem Error: \" + img);\n" +
        "            return null;\n" +
        "        }\n" +
        "    }\n" +
        "\n" +
        "    public void DeletarImagem(String origem) {\n" +
        "        try {\n" +
        "            File img = new File(origem);\n" +
        "            Files.delete(img.toPath());\n" +
        "        } catch (Exception img) {\n" +
        "            System.out.println(\"DeletarImagem Error: \" + img);\n" +
        "        }\n" +
        "    }\n" +
        "\n" +
        "    public Image GetImg(String origem, int x, int y) {\n" +
        "        ImageIcon imgIcone = new ImageIcon(origem);\n" +
        "        Image iIcone = imgIcone.getImage();\n" +
        "        Image Icone = iIcone.getScaledInstance(x, y, java.awt.Image.SCALE_SMOOTH);\n" +
        "        return Icone;\n" +
        "    }\n" +
        "}\n" +
        "");
        
        return listaImagem;
    }
    
    public List<String> GeradorDAOE(String Entidade, List<String> listaItens){
        List<String> listaDAOE = new ArrayList<>();
        String Item = listaItens.get(3);
        if(Item.matches("^[A-Z]$")){
            String Item2;
            for (int i = 0; i < Item.length(); i++) {
                if(Character.isUpperCase(Item.charAt(i))){
                    Item2 = Item.substring(0, --i) + "_" + Item.substring(++i, ++i).toLowerCase() + Item.substring(i);
                    Item = Item2;
                    i = 0;
                }
            }
        }
        listaDAOE.add(
        "package DAOs;\n" +
        "import Entidades." + Entidade + ";\n" +
        "import java.util.ArrayList;\n" +
        "import java.util.List;\n" +
        "\n" +
        "public class DAO" + Entidade + " extends DAOGenerico<" + Entidade + "> {\n" +
        "\n" +
        "    public DAO" + Entidade + "() {\n" +
        "        super(" + Entidade + ".class);\n" +
        "    }\n" +
        "\n" +
        "    public int autoId" + Entidade + "() {\n" +
        "        Integer a = (Integer) em.createQuery(\"SELECT MAX(e.id" + Entidade + ") FROM " + Entidade + " e \").getSingleResult();\n" +
        "        if (a != null) {\n" +
        "            return a + 1;\n" +
        "        } else {\n" +
        "            return 1;\n" +
        "        }\n" +
        "    }\n" +
        "\n" +
        "    public List<" + Entidade + "> listByNome(String " + Item + ") {\n" +
        "        return em.createQuery(\"SELECT e FROM " + Entidade + " e WHERE e." + Item + " LIKE :" + Item + "\").setParameter(\"" + Item + "\", \"%\" + " + Item + " + \"%\").getResultList();\n" +
        "    }\n" +
        "\n" +
        "    public List<" + Entidade + "> listById(int id) {\n" +
        "        return em.createQuery(\"SELECT e FROM " + Entidade + " e WHERE e.id" + Entidade + " = :id\").setParameter(\"id\", id).getResultList();\n" +
        "    }\n" +
        "\n" +
        "    public List<" + Entidade + "> listInOrderNome() {\n" +
        "        return em.createQuery(\"SELECT e FROM " + Entidade + " e ORDER BY e." + Item + "\").getResultList();\n" +
        "    }\n" +
        "\n" +
        "    public List<" + Entidade + "> listInOrderId() {\n" +
        "        return em.createQuery(\"SELECT e FROM " + Entidade + " e ORDER BY e.id" + Entidade + "\").getResultList();\n" +
        "    }\n" +
        "\n" +
        "    public List<String> listInOrderNomeStrings(String qualOrdem) {\n" +
        "        List<" + Entidade + "> lf;\n" +
        "        if (qualOrdem.equals(\"id\")) {\n" +
        "            lf = listInOrderId();\n" +
        "        } else {\n" +
        "            lf = listInOrderNome();\n" +
        "        }\n" +
        "\n" +
        "        List<String> ls = new ArrayList<>();\n" +
        "        for (int i = 0; i < lf.size(); i++) {\n" +
        "            ls.add(lf.get(i).getId" + Entidade + "()");
        for (int aux = 3; aux < listaItens.size(); aux++, aux++){
            String Aux = listaItens.get(aux).substring(0, 1).toUpperCase() + listaItens.get(aux).substring(1);
            listaDAOE.add(" + \"-\" + lf.get(i).get" + Aux + "()");
        }
        listaDAOE.add(
        ");\n" +
        "        }\n" +
        "        return ls;\n" +
        "    }\n" +
        "}");
        return listaDAOE;
    }
    
    public List<String> GeradorDAOG(){
        List<String> listaDAOG = new ArrayList<>();
        listaDAOG.add(
        "package DAOs;\n" +
        "\n" +
        "import java.util.List;\n" +
        "import javax.persistence.EntityManager;\n" +
        "import javax.persistence.Persistence;\n" +
        "\n" +
        "public class DAOGenerico<T> {\n" +
        "\n" +
        "    public static EntityManager em = Persistence.createEntityManagerFactory(\"UP\").createEntityManager();\n" +
        "    private Class clazz;\n" +
        "\n" +
        "    public DAOGenerico(Class clazz) {\n" +
        "        this.clazz = clazz;\n" +
        "    }\n" +
        "\n" +
        "    public void inserir(T e) {\n" +
        "        em.getTransaction().begin();\n" +
        "        em.persist(e);\n" +
        "        em.getTransaction().commit();\n" +
        "    }\n" +
        "\n" +
        "    public void atualizar(T e) {\n" +
        "        em.getTransaction().begin();\n" +
        "        em.merge(e);\n" +
        "        em.getTransaction().commit();\n" +
        "    }\n" +
        "\n" +
        "    public void remover(T e) {\n" +
        "        em.getTransaction().begin();\n" +
        "        em.remove(e);\n" +
        "        em.getTransaction().commit();\n" +
        "    }\n" +
        "\n" +
        "    public T obter(Long id) {\n" +
        "        return (T) em.find(clazz, id);\n" +
        "    }\n" +
        "\n" +
        "    public T obter(Integer id) {\n" +
        "        return (T) em.find(clazz, id);\n" +
        "    }\n" +
        "    \n" +
        "    public T obter(String id) {\n" +
        "        return (T) em.find(clazz, id);\n" +
        "    }\n" +
        "\n" +
        "    public List<T> list() {\n" +
        "        return em.createQuery(\"SELECT e FROM \" + clazz.getSimpleName() + \" e\").getResultList();\n" +
        "    }\n" +
        "}\n" +
        "");
        return listaDAOG;
    }
    
}
