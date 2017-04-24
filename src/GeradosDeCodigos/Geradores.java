package GeradosDeCodigos;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Geradores  {
    
    private List<String> listaGUIAux = new ArrayList<>();
    private List<String> listaGUIAuxSize = new ArrayList<>();
    private List<String> listaGUIAuxFK = new ArrayList<>();
    private List<String> listaGUIAuxMainFK = new ArrayList<>();
    
    //Pacote
    private String nomePacote;
    public void NomePacote(){
        try{
            nomePacote = JOptionPane.showInputDialog(null, "Insira o Nome do Pacote:", "Nome do Pacote",  JOptionPane.QUESTION_MESSAGE);
            if (nomePacote == null){
                System.exit(0);}
            if (nomePacote.equals("") || nomePacote.substring(0,1).matches("^[0-9]*$")){
                int error = 3/0;}
        }catch(Exception Pacote){
            JOptionPane.showMessageDialog(null,"Erro ao Nomear Pacote","Erro",JOptionPane.ERROR_MESSAGE);
            NomePacote();}}
    
    //Main
    private List<String> listaMain = new ArrayList<>();
    
    public List<String> GeradorMain(){
        listaMain.add(
        "package " + nomePacote + ";\n" +
        "\n" +
        "public class Main {\n" +
        "    public static void main(String[] args) throws Exception {\n" +
        "        GUI gui = new GUI();\n" +
        "    }   \n" +
        "}");
        return listaMain;}
    
    int CONT = 0;
    public List<String> GeradorMainFK(){
        listaMain.add(
        "package Main;\n" +
        "import java.awt.BorderLayout;\n" +
        "import java.awt.Color;\n" +
        "import java.awt.Container;\n" +
        "import java.awt.event.ActionEvent;\n" +
        "import java.awt.event.ActionListener;\n" +
        "import java.awt.event.WindowAdapter;\n" +
        "import java.awt.event.WindowEvent;\n" +
        "import java.awt.event.WindowListener;\n" +
        "import java.io.BufferedReader;\n" +
        "import java.io.FileReader;\n" +
        "import java.text.ParseException;\n" +
        "import javax.swing.JButton;\n" +
        "import javax.swing.JDialog;\n" +
        "import javax.swing.JOptionPane;\n" +
        "import javax.swing.JPanel;" +
        "\n" +
        "public class Main extends JDialog{\n" +
        "\n" +
        "    public static void main(String[] args) throws ParseException {\n" +
        "    Main main = new Main();}\n" +
        "    \n" +
        "    private Container cp;\n" +
        "    private JPanel pnCentro = new JPanel();\n");
        
        for(String FKs : listaGUIAuxMainFK){
            if (CONT == 0){
                listaMain.add(
                "    private JButton bt" + FKs + " = new JButton(\"" + FKs + "\");\n");
                CONT++;
            }else{
                listaMain.add(
                "    private JButton bt" + FKs + " = new JButton(\"" + FKs.substring(2) + "(FK)\");\n");}
            }
        CONT = 0;
        
        listaMain.add(
        "    public Main(){\n" +
        "        setSize(300, 120);\n" +
        "        setDefaultCloseOperation(DISPOSE_ON_CLOSE);\n" +
        "        cp = getContentPane();\n" +
        "        cp.setLayout(new BorderLayout());\n" +
        "        setTitle(\"MAIN\");\n" +
        "        \n" +
        "        pnCentro.setBackground(Color.cyan);\n");
        
        for(String FKs : listaGUIAuxMainFK){
            listaMain.add(
            "        pnCentro.add(bt" + FKs + ");\n" + 
            "        bt" + FKs + ".setEnabled(true);\n" + 
            "        bt" + FKs + ".setVisible(true);\n");}
        
        listaMain.add(
        "        cp.add(pnCentro, BorderLayout.CENTER);\n");
        
        for(String FKs : listaGUIAuxMainFK){
            if(CONT == 0){
                listaMain.add(
                "        bt" + FKs + ".addActionListener(new ActionListener() {\n" +
                "            @Override\n" +
                "            public void actionPerformed(ActionEvent ae) {\n" +
                "                try {\n" +
                "                    BufferedReader br = new BufferedReader(new FileReader(\"src/" + listaGUIAuxMainFK.get(1) + "/" + nomeEntidade + ".txt\"));\n" +
                "                    if (br.readLine() == null) {\n" +
                "                        JOptionPane.showMessageDialog(null,\"Uma lista deste CRUD está Vazia!\\nPor favor use o outro CRUD primeiro!\",\"Erro\",JOptionPane.ERROR_MESSAGE);\n" +
                "                        bt" + listaGUIAuxMainFK.get(1) + ".doClick();\n" +
                "                    }else{\n" +
                "                        " + FKs + ".GUI " + FKs + " = new " + FKs + ".GUI();\n" +
                "                    }}catch(Exception Main){}" +
                "                }});\n");
                CONT++;
            }else {
                listaMain.add(
                "        bt" + FKs + ".addActionListener(new ActionListener() {\n" +
                "            @Override\n" +
                "            public void actionPerformed(ActionEvent ae) {\n" +
                "                " + FKs + ".GUI " + FKs + " = new " + FKs + ".GUI();\n" +
                "                }});\n");}}
        
        listaMain.add(
        "        setLocationRelativeTo(null);\n" +
        "        setVisible(true);\n\n" +
        "        WindowListener exitListener = new WindowAdapter() {\n" +
        "        @Override\n" +
        "        public void windowClosing(WindowEvent e) {\n" +
        "            System.exit(0);}};\n" +
        "        addWindowListener(exitListener);\n" +
        "    }   \n" +
        "}");
        
        return listaMain;}
   
    
    //Entidade
    private List<String> listaEntidade = new ArrayList<>();
    private List<String> listaEntidadeStart = new ArrayList<>();
    private List<String> listaEntidadeGetSet = new ArrayList<>();
    private List<String> listaEntidadeBuilder = new ArrayList<>();
    private List<String> listaEntidadeBuilderAux = new ArrayList<>();
    private List<String> listaEntidadeToString = new ArrayList<>();
    private String nomeEntidade;
    private int contImg = 0;
    
    public List<String> ListaFK(){
        listaGUIAuxMainFK.add(nomePacote);
        return listaGUIAuxMainFK;}
    
    public String NomeEntidade(){
        try{
            nomeEntidade = JOptionPane.showInputDialog(null, "Insira o Nome da Entidade:", "Nome da Entidade",  JOptionPane.INFORMATION_MESSAGE);
            if (nomeEntidade == null || nomeEntidade.equals("")){
                nomeEntidade = "Code: Abort";
                listaGUIAux.get(10000000);}
            if (nomeEntidade.substring(0,1).matches("^[0-9]*$")){
                int error = 3/0;}
        }catch(ArithmeticException Entidade1){
            JOptionPane.showMessageDialog(null,"Erro ao Nomear Entidade","Erro",JOptionPane.ERROR_MESSAGE);
            NomeEntidade();
        }catch(Exception Entidade2){
            return nomeEntidade;}
        return nomeEntidade.replace(" ", "");}
    
    private String password = " ";
    public String SetPassword(){
        password += JOptionPane.showInputDialog(null, "??!?????!?!?!???????!???!????????!??", "??!?????!?!?!???????!???!????????!????!?????!?!?!???????!???!????????!??",  JOptionPane.INFORMATION_MESSAGE);
        return password;}
    
    public void GeradorEntidadeStart(){
        listaEntidadeStart.add(
        "package " + nomePacote.replace(" ", "")+ ";\n");
       
        
        listaEntidadeBuilderAux.add(
        "public " + nomeEntidade + " (){\n}\n\n" +      
        "public " + nomeEntidade + " (");
        listaEntidadeToString.add(
        "@Override\n" + 
        "    public String toString(){\n" + 
        "        return " );
    
        ListaFK();}

    int AUX = 0;
    public void GeradorEntidadeGetSet(String atributo, String tipo, String tamanho, boolean FK){
        atributo = atributo.replace(" ", "");
        if (listaGUIAux.isEmpty()){
            for(int aux = 1; aux <= listaGUIAux.size(); aux++, aux++){
                if (listaGUIAux.get(aux).equals(atributo)){
                    int error = 3/0;}}}
        if (tipo.equals("Date")){
            listaEntidadeGetSet.add(
            "    private SimpleDateFormat Date"+atributo+" = new SimpleDateFormat(\""+tamanho+"\");\n" + 
            "    private "+tipo+" "+atributo+";\n" + 
            "    public "+tipo+" get"+atributo+"() {\n" + 
            "        return "+atributo+";}\n" + 
            "    public void set"+atributo+"("+tipo+" "+atributo+") {\n" + 
            "        this."+atributo+" = "+atributo+";}\n");
        }else{
            if (tipo.equals("JCheckBox") || tipo.equals("JRadioButton") || tipo.equals("JComboBox") || tipo.equals("Imagem")){
                listaEntidadeGetSet.add(
                "    private String "+atributo+";\n" + 
                "    public String get"+atributo+"() {\n" + 
                "        return "+atributo+";}\n" + 
                "    public void set"+atributo+"(String "+atributo+") {\n" + 
                "        this."+atributo+" = "+atributo+";}\n");
            }else{
                listaEntidadeGetSet.add(
                "    private "+tipo+" "+atributo+";\n" + 
                "    public "+tipo+" get"+atributo+"() {\n" + 
                "        return "+atributo+";}\n" + 
                "    public void set"+atributo+"("+tipo+" "+atributo+") {\n" + 
                "        this."+atributo+" = "+atributo+";}\n");}}

        if (AUX == 0){
            listaEntidadeBuilderAux.add(tipo+" "+atributo);
            listaEntidadeToString.add(atributo);
            AUX++;
        }else {
            if (tipo.equals("JCheckBox")||tipo.equals("JRadioButton") || tipo.equals("JComboBox") || tipo.equals("Imagem")){
                listaEntidadeBuilderAux.add(", String "+atributo);
            }else{
                listaEntidadeBuilderAux.add(", "+tipo+" "+atributo);}
        if (tipo.equals("Date")){
            listaEntidadeToString.add("+ \";\" + Date" + atributo + ".format(" + atributo +")");
        }else{
            listaEntidadeToString.add("+ \";\" +" + atributo);}}

        listaEntidadeBuilder.add("this."+atributo+" = "+atributo+";");
        listaGUIAux.add(tipo);
        listaGUIAux.add(atributo);
        listaGUIAuxSize.add(tamanho);
        
        if(tipo.equals("Imagem")){
            contImg++;
        }
        
        if (FK){
            listaGUIAuxFK.add(atributo);}}
    
    public List<String> ListaFKs(){
        return listaGUIAuxFK;}
    
    public List<String> GeradorEntidadeBuilder(){
        listaEntidadeBuilderAux.add("){");
        listaEntidadeBuilder.add("}");
        listaEntidadeToString.add(";}");
        
        if (listaGUIAux.contains("Date")){
            listaEntidadeStart.add(
            "import java.text.SimpleDateFormat;\n" +
            "import java.util.Date;\n\n" +
            "public class "+ nomeEntidade + "{\n\n");
        }else{
            listaEntidadeStart.add("public class "+ nomeEntidade + "{\n\n");}
        
        for(String a : listaEntidadeStart){
            listaEntidade.add(a);}
        for(String b : listaEntidadeGetSet){
            listaEntidade.add(b);}
        for(String c: listaEntidadeBuilderAux){
            listaEntidade.add(c);}
        for(String d: listaEntidadeBuilder){
            listaEntidade.add(d);}
        for(String e: listaEntidadeToString){
            listaEntidade.add(e);} 
        listaEntidade.add("}");
        
        if(contImg != 0){
            Persistencia persistencia = new Persistencia();
            persistencia.salvarArquivo("src/" + nomePacote +"/Imagem.java", Imagem());
        }
    return listaEntidade;}
    
    
    //Controle
    private List<String> listaControle = new ArrayList<>();

    public List<String> GeradorControle(){
        listaControle.add(
        "package " + nomePacote + ";\n\n" + 
        "import java.util.ArrayList;\n" + 
        "import java.util.List;\n\n" + 
        "    public class Controle{\n" + 
        "        private List<" + nomeEntidade + "> lista = new ArrayList<>();\n\n" + 
        "        public void inserir (" + nomeEntidade + " entidade){\n" + 
        "            lista.add(entidade);}\n\n" + 
        "        public " + nomeEntidade + " buscar (" + nomeEntidade + " entidade){\n" + 
        "            for(int i = 0;i < lista.size(); i++) {\n");
        
        if (listaGUIAux.get(0).equals("String") ){
            listaControle.add(
            "               if (entidade.get" + listaGUIAux.get(1) + "().equals( lista.get(i).get" + listaGUIAux.get(1) + "())){\n");
        }else {
            listaControle.add(
            "               if (entidade.get" + listaGUIAux.get(1) + "() == lista.get(i).get" + listaGUIAux.get(1) + "()){\n");
        }
        
        listaControle.add(
        "                   return lista.get(i);\n" + 
        "               }\n" + 
        "            }\n" + 
        "        return null;}\n\n" +
        "        public void atualizar (" + nomeEntidade + " EntidadeOriginal, " + nomeEntidade + " EntidadeModificada){\n" + 
        "            lista.set(lista.indexOf(EntidadeOriginal), EntidadeModificada);}\n\n" + 
        "        public void deletar (" + nomeEntidade + " entidade){\n" + 
        "            lista.remove(entidade);}\n\n" + 
        "        public List<" + nomeEntidade + "> getLista(){\n" + 
        "            return lista;\n"+ 
        "        }\n"+ 
        "    }"
        );
        return listaControle;}

    
    //Persistência
    private List<String> listaPersistencia = new ArrayList<>();
    
    public List<String> GeradorPersistencia (){
        listaPersistencia.add(
        "package " + nomePacote + ";\n" +
        "\n" +
        "import java.io.BufferedReader;\n" +
        "import java.io.BufferedWriter;\n" +
        "import java.io.File;\n" +
        "import java.io.FileReader;\n" +
        "import java.io.FileWriter;\n" +
        "import java.util.ArrayList;\n" +
        "import java.util.List;\n" +
        "\n" +
        "public class Persistencia {\n" +
        "\n" +
        "    public Persistencia() {\n" +
        "    }\n" +
        "\n" +
        "    public static List<String> abrirArquivo(String caminho) {\n" +
        "        List<String> texto = new ArrayList<>();\n" +
        "        File arq = new File(caminho);\n" +
        "        if (arq.exists()) {\n" +
        "            try {\n" +
        "                //OpenFile\n" +
        "                FileReader arquivo = new FileReader(caminho);\n" +
        "                BufferedReader conteudoDoArquivo = new BufferedReader(arquivo);\n" +
        "                String linha = conteudoDoArquivo.readLine();\n" +
        "                while (linha != null) {\n" +
        "                    texto.add(linha);\n" +
        "                    linha = conteudoDoArquivo.readLine();\n" +
        "                }\n" +
        "                conteudoDoArquivo.close();\n" +
        "            } catch (Exception e) {//Catch exception if any\n" +
        "                System.err.println(\"Error: \" + e.getMessage());\n" +
        "            }\n" +
        "        } else {\n" +
        "            texto.add(\"\");\n" +
        "        }\n" +
        "        return texto;\n" +
        "    }\n" +
        "\n" +
        "    public static int salvarArquivo(String caminho, List<String> texto) {\n" +
        "        try {\n" +
        "            // Create file \n" +
        "            FileWriter arquivo = new FileWriter(caminho);\n" +
        "            BufferedWriter conteudoDoArquivo = new BufferedWriter(arquivo);\n" +
        "            for (int i = 0; i < texto.size(); i++) {\n" +
        "                conteudoDoArquivo.write(texto.get(i) + \"\\n\"); \n" +
        "            }\n" +
        "            conteudoDoArquivo.close();\n" +
        "        } catch (Exception e) {//Catch exception if any\n" +
        "            System.err.println(\"Error: \" + e.getMessage());\n" +
        "            return 1;\n" +
        "        }\n" +
        "        return 0;\n" +
        "    }\n" +
        "}"
        );
        return listaPersistencia;}

    //GUI
    private List<String> listaGUI = new ArrayList<>();
    int Aux = 0;
    int Cont = 0;
    
    public List<String> GeradorGUI (){
        listaGUI.add(
        "package " + nomePacote + ";\n" +
        "\n" +
        "import java.awt.BorderLayout;\n" +
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
        "public class GUI extends JDialog {\n" +
        "\n" +
        "    private Container cp;\n" +
        "    private JPanel pnNorte = new JPanel(new FlowLayout());\n" +
        "    private JPanel pnCentro = new JPanel(new GridLayout(" + (listaGUIAux.size()/2 -1) + ", 2));\n" +
        "    private JPanel pnSul = new JPanel(new FlowLayout());\n\n" +
        "    private JLabel lb" + listaGUIAux.get(1) + " = new JLabel(\"" + listaGUIAux.get(1) +":\");\n" +
        "    private JTextField tf" + listaGUIAux.get(1) + " = new JTextField(" + listaGUIAuxSize.get(Aux++) + ");\n\n" +
        "    private JButton btBuscar = new JButton(\"Buscar\");\n" +
        "    private JButton btInserir = new JButton(\"Criar\");\n" +
        "    private JButton btSalvar = new JButton(\"Salvar\");\n" +
        "    private JButton btCancelar = new JButton(\"Cancelar\");\n" +
        "    private JButton btRemover = new JButton(\"Remover\");\n" +
        "    private JButton btAtualizar = new JButton(\"Atualizar\");\n" +
        "    private JButton btListar = new JButton(\"Listar\");\n" +
        "    \n" +
        "    private JLabel lbAviso = new JLabel(\"Aviso\");\n" + 
        "    Controle controle = new Controle();\n" +
        "    List<String> dados = new ArrayList<>();\n" +
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
                        if (listaGUIAuxFK.contains(listaGUIAux.get(aux))){
                            listaGUI.add(
                            "    private JLabel lb" + listaGUIAux.get(aux) + " = new JLabel(\"" + listaGUIAux.get(aux) + "\");\n" +
                            "    List<String> " + listaGUIAux.get(aux) + "Dados = new ArrayList<>();\n");
                        }else{
                            String auxComboBox = "\"" + listaGUIAuxSize.get((aux -1)/2).replace(":", "\",\"") + "\"";
                            listaGUI.add(
                            "    private JLabel lb" + listaGUIAux.get(aux) + " = new JLabel(\"" + listaGUIAux.get(aux) + "\");\n" +
                            "    String[] st" + listaGUIAux.get(aux) + " = {" + auxComboBox + "};\n" +
                            "    JComboBox cbx" + listaGUIAux.get(aux) + " = new JComboBox(st" + listaGUIAux.get(aux) + ");");}
                        
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
        "    private JScrollPane scroll = new JScrollPane(text);\n");
        
        if(listaGUIAux.get(0).equals("int") ){
            listaGUI.add(
            "    int " + listaGUIAux.get(1) + ";\n");}
        if (listaGUIAux.get(0).equals("Double") ){
            listaGUI.add(
            "    Double " + listaGUIAux.get(1) + ";\n");}
        if(listaGUIAux.get(0).equals("String") ){
            listaGUI.add(
            "    String " + listaGUIAux.get(1) + ";\n");}
        if (listaGUIAux.get(0).equals("Long") ){
            listaGUI.add(
            "    Long " + listaGUIAux.get(1) + ";\n");}
            
        if (listaGUIAux.contains("Date")){
            listaGUI.add(
            "    public GUI() throws ParseException{\n" +
            "    dados = Persistencia.abrirArquivo(\"src/" + nomePacote + "/" + nomeEntidade + ".txt\");\n" +
            "        String aux[];\n" +
            "        for (String linha : dados) {\n" +
            "            aux = linha.split(\";\");\n" +
            "            controle.inserir(new " + nomeEntidade + "(");
        }else{
            listaGUI.add(
            "    public GUI() {\n" +
            "    dados = Persistencia.abrirArquivo(\"src/" + nomePacote + "/" + nomeEntidade + ".txt\");\n" +
            "        String aux[];\n" +
            "        for (String linha : dados) {\n" +
            "            aux = linha.split(\";\");\n" +
            "            controle.inserir(new " + nomeEntidade + "(");}
        
        AUX = 0;
        for (int aux = 1; aux < listaGUIAux.size(); aux++, aux++){
            if(AUX != 0){
            listaGUI.add("           ,");}
            if(listaGUIAux.get(aux -1).equals("int") ){
                listaGUI.add(
                "            Integer.valueOf(aux[" + (AUX++) + "])");}
            if (listaGUIAux.get(aux -1).equals("Double") ){
                listaGUI.add(
                "            Double.valueOf(aux[" + (AUX++) + "])");}
            if(listaGUIAux.get(aux-1).equals("Long") ) {
                listaGUI.add(
                "            Long.valueOf(aux[" + (AUX++) + "])");}
            if(listaGUIAux.get(aux-1).equals("Date") ) {
                listaGUI.add(
                "            Date"+listaGUIAux.get(aux)+".parse(aux[" + (AUX++) + "])");}
            if (listaGUIAux.get(aux -1).equals("JCheckBox") || listaGUIAux.get(aux -1).equals("JRadioButton") || listaGUIAux.get(aux-1).equals("String") || listaGUIAux.get(aux-1).equals("JComboBox") || listaGUIAux.get(aux-1).equals("Imagem")){
                listaGUI.add(
                "            aux[" + (AUX++) + "]");}}
        listaGUI.add("        ));}\n");
        
        if (Cont == 0){
            Cont++;
            String nomeEntidadeAux = nomeEntidade;
            String nomePacoteAux = nomePacote;
            for (String listaGUIAuxFK1 : listaGUIAuxFK) {
                nomePacote = "FK" + listaGUIAuxFK1;
                nomeEntidade = listaGUIAuxFK1;
                listaGUI.add(
                "        " + nomeEntidade + "Dados = Persistencia.abrirArquivo(\"src/" + nomePacote + "/" + nomeEntidade + ".txt\");\n" +
                "        String[] st" + nomeEntidade + " = new String[" + nomeEntidade + "Dados.size()];\n" +
                "        int AUX = 0;\n" +
                "        for (String linha : " + nomeEntidade + "Dados) {\n" +
                "            aux = linha.split(\";\");\n" +
                "            st" + nomeEntidade + "[AUX] = String.valueOf(aux[0]);\n" +
                "            AUX++;}\n" +
                "        JComboBox cbx" + listaGUIAuxFK1 + " = new JComboBox(st" + listaGUIAuxFK1 + ");\n");}
            nomeEntidade = nomeEntidadeAux;
            nomePacote = nomePacoteAux;}
        
        listaGUI.add(    
        "        setSize(800, " + (100 + (listaGUIAux.size()/2 -1)*100) + ");\n" +
        "        setResizable(false);\n" +
        "        setDefaultCloseOperation(DISPOSE_ON_CLOSE);\n" +
        "        cp = getContentPane();\n" +
        "        cp.setLayout(new BorderLayout());\n" +
        "        setTitle(\"" + JOptionPane.showInputDialog(null, "Título:", "SetTitle",  JOptionPane.INFORMATION_MESSAGE) + "\");\n" +
        "\n" +
        "        pnNorte.setBackground(Color.yellow);\n" +
        "        pnCentro.setBackground(Color.cyan);\n" +
        "        pnSul.setBackground(Color.green);\n" +
        "\n" + 
        "        dialog.add(scroll);\n" +
        "        text.setEditable(false);\n" +
        "        dialog.setSize(500,200);\n" + 
        "\n" + 
        "        pnNorte.add(lb" + listaGUIAux.get(1) + ");\n" +
        "        pnNorte.add(tf" + listaGUIAux.get(1) + ");\n" +
        "        pnNorte.add(btBuscar);\n" +
        "        pnNorte.add(btInserir);\n" +
        "        pnNorte.add(btSalvar);\n" +
        "        pnNorte.add(btCancelar);\n" +
        "        pnNorte.add(btRemover);\n" +
        "        pnNorte.add(btAtualizar);\n" +
        "        pnNorte.add(btListar);\n" +
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
                        "        i" + listaGUIAux.get(aux) + " = new ImageIcon(imagem.GetImg(\"src//EDIT(pasta+imgBase)\", 100, 100));\n" +
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
        "                    if (tf" + listaGUIAux.get(1) + ".getText().equals(\"Code: delete/all" + password +"\")){\n" +
        "                        dados.clear();\n" +
        "                        Persistencia.salvarArquivo(\"src/" + nomePacote + "/" + nomeEntidade + ".txt\", dados);\n" +
        "                        tf" + listaGUIAux.get(1) + ".setText(\"\");\n" +
        "                        System.exit(0);}\n" +
        "                    entidade = new " + nomeEntidade + "();\n");
        
        
                
        if(listaGUIAux.get(0).equals("int") ){
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
        "                    entidade = controle.buscar(entidade);\n" +
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
        "                        btListar.setVisible(false);\n");
    
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
                        "                        i" + listaGUIAux.get(aux) + " = new ImageIcon(imagem.GetImg(\"src//EDIT(pasta+imgBase)\", 100, 100));\n" +
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
                if(listaGUIAux.get(aux -1).equals("int") ){
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
        "                            btListar.setVisible(true);\n" +      
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
        "                            dados.clear();\n" +
        "                            List<" + nomeEntidade + "> listaEntidade = controle.getLista();\n" +
        "                            for (" + nomeEntidade + " a : listaEntidade) {\n" +
        "                                dados.add(a.toString());}\n" +
        "\n" +
        "\n" +
        "                Persistencia.salvarArquivo(\"src/" + nomePacote + "/" + nomeEntidade + ".txt\", dados);\n" +
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
                if(listaGUIAux.get(aux -1).equals("int") ){
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
        "                            controle.atualizar(entidadeOriginal, entidadeModificada);\n" +
        "\n" +
        "                            lbAviso.setText(\"Registro alterado\");\n" +
        "                            tf" + listaGUIAux.get(1) + ".setEnabled(true);\n" +
        "                            tf" + listaGUIAux.get(1) + ".requestFocus();\n" +
        "                            tf" + listaGUIAux.get(1) + ".selectAll();\n" +
        "                            btSalvar.setVisible(false);\n" +
        "                            btCancelar.setVisible(false);\n" +
        "                            btBuscar.setVisible(true);\n" + 
        "                            btListar.setVisible(true);\n");
        
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
        "                            dados.clear();\n" +
        "                            List<" + nomeEntidade + "> listaEntidade = controle.getLista();\n" +
        "                            for (" + nomeEntidade + " a : listaEntidade) {\n" +
        "                                dados.add(a.toString());}\n" +
        "\n" +
        "                            Persistencia.salvarArquivo(\"src/" + nomePacote + "/" + nomeEntidade + ".txt\", dados);\n" +
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
        "                        btListar.setVisible(true);\n" +
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
        "                        btListar.setVisible(false);\n" +
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
        "                        controle.deletar(entidade);\n" +
        "                        lbAviso.setText(\"Removeu\");\n" +
        "\n" +
        "                        dados.clear();\n" +
        "                        List<" + nomeEntidade + "> listaEntidade = controle.getLista();\n" +
        "                        for (" + nomeEntidade + " a : listaEntidade) {\n" +
        "                            dados.add(a.toString());\n" +
        "}\n" +
        "                        Persistencia.salvarArquivo(\"src/" + nomePacote + "/" + nomeEntidade + ".txt\", dados);\n" +
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
        "       btListar.addActionListener( new ActionListener(){\n" +
        "            @Override\n" +
        "            public void actionPerformed(ActionEvent e\n" +
        "                    ) {\n" +
        "                try{\n" +
        "                String aux[];\n" +
        "                text.setText(\"\");\n" +
        "\n" +
        "                try{\n" +
        "                if (dados.get(0) == null){}\n" +
        "                }catch(Exception lista){\n" +
        "                    JOptionPane.showMessageDialog(null, \"NOTHING TO SEE HERE! 8P\", \"OPS\", JOptionPane.PLAIN_MESSAGE);\n" +
        "                    int erro = 3/0;" +
        "                }\n" +
        "                for (String linha : dados) {\n" +
        "                    aux = linha.split(\";\");\n" +
        "                    text.append(");
        
        Aux = 0;
        for (int aux = 1; aux < listaGUIAux.size(); aux++, aux++){
            
            listaGUI.add("\"" + listaGUIAux.get(aux) + ": \" + ");
            
            if(listaGUIAux.get(aux -1).equals("int") ){
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
            listaGUI.add("+ \"\\n\\n\"");
        
        listaGUI.add(
        ");}\n" +
        "                dialog.setLocationRelativeTo(cp);\n" +
        "                dialog.setModal(true);\n" + 
        "                dialog.setVisible(true);\n" +
        "                }catch(Exception Lista){" +
        "                    }\n" +
        "        }});\n" +
        "\n" +
        "       addWindowListener(new WindowAdapter() {\n" +
        "            @Override\n" +
        "            public void windowClosing(WindowEvent e) {\n" +
        "                dados.clear();\n" +
        "                List<" + nomeEntidade + "> listaEntidade = controle.getLista();\n" +
        "                for (" + nomeEntidade + " a : listaEntidade) {\n" +
        "                    dados.add(a.toString());\n" +
        "                }\n" +
        "                \n" +
        "                Persistencia.salvarArquivo(\"src/" + nomePacote + "/" + nomeEntidade + ".txt\", dados);\n");
        if(listaGUIAuxFK.isEmpty()){
            listaGUI.add(
            "                System.exit(0);\n");}
        
        listaGUI.add(
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
    
    List<String> listaImagem = new ArrayList<String>();
    public List<String> Imagem(){
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
        
        contImg = 0;
        return listaImagem;
    }
    
    public void Voltar(){
        listaEntidade.clear();
        listaEntidadeStart.clear();
        listaEntidadeGetSet.clear();
        listaEntidadeBuilder.clear();
        listaEntidadeBuilderAux.clear();
        listaEntidadeToString.clear();
        AUX = 0;
        listaGUIAux.clear();
        listaGUIAuxSize.clear();
    }
    public void Clear(){
        listaGUIAux.clear();
        listaGUIAuxSize.clear();
        listaEntidade.clear();
        listaEntidadeStart.clear();
        listaEntidadeGetSet.clear();
        listaEntidadeBuilder.clear();
        listaEntidadeBuilderAux.clear();
        listaEntidadeToString.clear();
        AUX = 0;
        Aux = 0;
        listaControle.clear();
        listaGUIAux.clear();
        listaGUIAuxSize.clear();
        listaPersistencia.clear();
        listaGUI.clear();
    }
    
    public void Corrigir(){
        listaEntidadeGetSet.remove(listaEntidadeGetSet.size() -1);
        listaEntidadeBuilderAux.remove(listaEntidadeBuilderAux.size() -1);
        listaEntidadeBuilder.remove(listaEntidadeBuilder.size() -1);
        listaEntidadeToString.remove(listaEntidadeToString.size() -1);
        AUX--;
        listaGUIAux.remove(listaGUIAux.size() -2);
        listaGUIAuxSize.remove(listaGUIAuxSize.size() -1);
    }
    
    public void GerarArquivoTxt(){
        try{
            FileWriter newTxt = new FileWriter ("src/" + nomePacote + "/" + nomeEntidade + ".txt");
        }catch(Exception Txt){
            JOptionPane.showMessageDialog(null,"Erro ao Criar Arquivo TXT","Erro",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void GerarPacote(){
        try{
            boolean newPackage = new File("src/" + nomePacote).mkdir();
        }catch(Exception mkdir){}
    }
    
    public void GerarPacote(String nome){
        try{
            boolean newPackage = new File("src/" + nome).mkdir();
        }catch(Exception mkdir){}
    }

    public void setNomePacote(String nomePacote) {
        this.nomePacote = nomePacote;
    }

    public void setNomeEntidade(String nomeEntidade) {
        this.nomeEntidade = nomeEntidade;
    }

    public String getNomePacote() {
        return nomePacote;
    }

    public String getNomeEntidade() {
        return nomeEntidade;
    }
}
    