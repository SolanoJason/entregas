/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import java.awt.Frame;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
//import ds.desktop.notify.*;


/**
 *
 * @author Lenovo
 */
public class ingalum extends JDialog{
    /** 
    *Variables usadas para llenar el JCombobox
    */
    public String nomescu[];
     public int idescu[];
     
     
     /**
      * booleano para sebaer si ya se puede proceder con el agregado
      */
     public boolean listo=true;
     /**
      * Jlabels usados para identificar los campos
      */
    public  JLabel enc = new JLabel("Bienvenido al registro del alumno");
    public  JLabel rec=new JLabel("Porfavor llene los campos (* campos obligatorios)");
    public  JLabel nom = new JLabel("*Nombre:");
    public  JLabel ape=new JLabel("*Apellido:");
    public  JLabel cod = new JLabel("*Codigo:");
    public  JLabel esc=new JLabel("*Escuela:");
    public  JLabel dni = new JLabel("*DNI:");
    public  JLabel sis=new JLabel("*Estado:");
    public  JLabel dir = new JLabel("Dirección:");

    /**
     * JTextFields usados para que el usuario pueda registrar
     */
    public  JTextField nombre = new JTextField();
    public  JTextField apellido=new JTextField();
    public  JTextField codigo = new JTextField();
    public  JTextField docnacide = new JTextField();
    public  JTextField direccion = new JTextField();
    
    /**
     * JButtons usados para implementar lo deseado 
     */
    public  JButton limpiar = new JButton("Limpiar");
    public JButton cancelar = new JButton("Cancelar");
    public JButton agregar =new JButton("Terminar");
    
    /**
     * JCombobox usado para mostrar las posibles escuelas
     */
    public JComboBox escuelas = new JComboBox();
    
    /**
     * JRadioButtons para las opciones del sisfoh
     */
    public JRadioButton nopobre = new JRadioButton("No pobre");
    public JRadioButton pobre = new JRadioButton("Pobre");
    public JRadioButton pobrex = new JRadioButton("Pobre extremo");
    
    /**
     * JPanel para ayudar en la muestra de los objetos
     */
    public  JPanel pPrincipal = new JPanel();
    
    
    public ingalum(JFrame padre) {
        
        /** 
         * Metodo usado para superponer a otro frame, bloqueandolo
         */
        super(padre,true);
        
        
        setSize(800, 500);
        setLocation(200, 200);
    
   //setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
   
   getContentPane().setLayout(null);
    
  
   
   /**
    * acción para poder pasar a la casilla apellido
    */
   nombre.addKeyListener(new KeyListener() {

            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyChar()==KeyEvent.VK_TAB || e.getKeyChar()==KeyEvent.VK_ENTER){
                    apellido.grabFocus();
                    
                } 
                
            }

            @Override
            public void keyTyped(KeyEvent e) {
                
            }

            @Override
            public void keyPressed(KeyEvent e) {
                
            }
   });
   
   /**
    * accion utilizada para cambiar su edicion
    */
   nombre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
            }
   });
   
   /**
    * acción para poder pasar a la casilla codigo
    */
   apellido.addKeyListener(new KeyListener() {

            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyChar()==KeyEvent.VK_TAB || e.getKeyChar()==KeyEvent.VK_ENTER){
                    codigo.grabFocus();
                    
                } 
                
            }

            @Override
            public void keyTyped(KeyEvent e) {
                
            }

            @Override
            public void keyPressed(KeyEvent e) {
                
            }
   });  
    
  
  
   
   /**
    * (released)acción para poder pasar a la casilla dni
    * (pressed)acción para poder restringir solo numeros y punto
    */
   codigo.addKeyListener(new KeyListener() {
            String aux="";
            String aux2="";
       
            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyChar()==KeyEvent.VK_TAB || e.getKeyChar()==KeyEvent.VK_ENTER){
                    dni.grabFocus();
                } 
               
                /**
                 * restricción
                 */
                if(!codigo.getText().equals("")){
                    aux2= codigo.getText();
                }
                if(numeropres(e) || e.getKeyChar()==KeyEvent.VK_PERIOD){
                    aux= codigo.getText();
                }
               
            }

            @Override
            public void keyTyped(KeyEvent e) {
                
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(!(((numeropres(e)||e.getKeyChar()==KeyEvent.VK_TAB)||(e.getKeyChar()==KeyEvent.VK_ENTER||e.getKeyChar()==KeyEvent.VK_PERIOD))||(e.getKeyChar()==KeyEvent.VK_BACK_SPACE))){
                    JOptionPane.showMessageDialog(null, "Porfavor escriba solo caracteres permitidos(0,1,2,...,9,'.')");
                    if(aux.equals("")){
                        codigo.setText("");
                    }else{
                        codigo.setText(aux+"");
                    }
                }else{
                    if(!codigo.getText().equals("")){   
                        aux= codigo.getText(); 
                    }
               }
            }
   });  
   
   /**
    * accion utilizada para cambiar su edicion
    */
  
   
   /**
    * (released)acción para poder pasar al Jcombobox
    * (released,pressed)acción para poder restringir solo numeros 
    */
   docnacide.addKeyListener(new KeyListener() {
            int aux=0;
            int aux2=0;
            @Override
            public void keyReleased(KeyEvent e) {
                
                /**
                 * pasar al JComboBox
                 */
                if(e.getKeyChar()==KeyEvent.VK_TAB || e.getKeyChar()==KeyEvent.VK_ENTER){
                    escuelas.grabFocus();
                } 
                
                /**
                 * restricción
                 */
                if(!docnacide.getText().equals("")){
                    aux2= Integer.parseInt(docnacide.getText());
                }
                if(aux2>100000000){
                   docnacide.setText(aux+"");
                }
                if(numeropres(e)){
                    aux= Integer.parseInt(docnacide.getText());
                }
                  
                
                   
            }

            @Override
            public void keyTyped(KeyEvent e) {
                
            }

            @Override
            public void keyPressed(KeyEvent e) {
               
               
                if(!((numeropres(e)||e.getKeyChar()==KeyEvent.VK_TAB)||(e.getKeyChar()==KeyEvent.VK_ENTER||e.getKeyChar()==KeyEvent.VK_BACK_SPACE))){
                    JOptionPane.showMessageDialog(null, "Porfavor escriba solo caracteres permitidos(0,1,2,...,9)");
                    if(aux==0){
                        docnacide.setText("");
                    }else{
                        docnacide.setText(aux+"");
                    }
                }
                else{
                    if(!docnacide.getText().equals("")){   
                        aux= Integer.parseInt(docnacide.getText()); 
                    }
               }
            
            }
   });  
   
   /**
    * accion utilizada para cambiar su edicion
    */
 
   
   /**
    * llamando al metodo  para llenar el JComboBox
    */
   llenandoescuelas(escuelas);
   
   /**
    * accion utilizada para cambiar su edicion
    */
  
   
   /**
    * metodo creado por su servidor para que solo se seleccione un JRadioButton
    */
   nopobre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pobre.setSelected(false);
                pobrex.setSelected(false);
               
            }
   });
   
    pobre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nopobre.setSelected(false);
                pobrex.setSelected(false);
               
            }
   });
    
     pobrex.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pobre.setSelected(false);
                nopobre.setSelected(false);
               
            }
   });
   
    /**
    * accion para pasar al Jbutton terminar o presionarlo directamente
    */
   direccion.addKeyListener(new KeyListener() {

            
            @Override
            public void keyReleased(KeyEvent e) {
                /**
                 * pasar
                 */
                if(e.getKeyChar()==KeyEvent.VK_TAB ){
                    agregar.grabFocus();
                } 
                /**
                 * presionar
                 */
                if(e.getKeyChar()==KeyEvent.VK_ENTER ){
                    agregandoabd();
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
                
            }

            @Override
            public void keyPressed(KeyEvent e) {
                
            }
   });  
     
   /**
    * boton usado para borrar todos los datos puestos
    */  
   limpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                nombre.setText("");
                apellido.setText("");
                codigo.setText("");
                docnacide.setText("");
                direccion.setText("");
                escuelas.setSelectedIndex(-1);
                nopobre.setSelected(false);
                pobre.setSelected(false);
                pobrex.setSelected(false);
                
            }
   });
   
   /**
    * boton usado para cancelar y volver a la ventana anterior
    */
   cancelar.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
          
          dispose();   
          padre.setVisible(true);
          padre.setExtendedState(Frame.NORMAL);
      }
    });
   /**
    * boton usado para llamar al metodo agregandoabd
    */
   agregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            
                agregandoabd();
                
            }
   });
    
    
   pPrincipal.setLayout(null);
 
   /**
    * se añade todo al panel
    */
  pPrincipal.add(enc);
  pPrincipal.add(rec);
  pPrincipal.add(nom);
   pPrincipal.add(nombre);
   pPrincipal.add(ape);
   pPrincipal.add(apellido);
  pPrincipal.add(cod);
  pPrincipal.add(codigo);
   pPrincipal.add(dni);
   pPrincipal.add(docnacide);
   pPrincipal.add(esc);
  pPrincipal.add(escuelas);
  pPrincipal.add(sis);
   pPrincipal.add(nopobre);
   pPrincipal.add(pobre);
   pPrincipal.add(pobrex);
  pPrincipal.add(dir);
  pPrincipal.add(direccion);
   pPrincipal.add(limpiar);
   pPrincipal.add(cancelar);
   pPrincipal.add(agregar);
  pPrincipal.setSize(800, 500);
    
  /**
   * a cada objeto se le da sus parametros
   */
    enc.setSize(300,25);
    enc.setLocation(300, 10);
    enc.setVisible(true);
  
    rec.setSize(450,25);
    rec.setLocation(20, 40);
    rec.setVisible(true);
    
    
    nom.setSize(100,25);
    nom.setLocation(20, 80);
    nom.setVisible(true);
    
    nombre.setSize(250, 25);
    nombre.setLocation(100, 80);
    nombre.setVisible(true);
    
    ape.setSize(100,25);
    ape.setLocation(420, 80);
    ape.setVisible(true);
    
    apellido.setSize(250, 25);
    apellido.setLocation(500, 80);
    apellido.setVisible(true);
    
    cod.setSize(80,25);
    cod.setLocation(20, 120);
    cod.setVisible(true);
    
    codigo.setSize(100, 25);
    codigo.setLocation(100, 120);
    codigo.setVisible(true);
    
    dni.setSize(100,25);
    dni.setLocation(420, 120);
    dni.setVisible(true);
    
    docnacide.setSize(100, 25);
    docnacide.setLocation(500, 120);
    docnacide.setVisible(true);
    
    esc.setSize(80,25);
    esc.setLocation(20, 160);
    esc.setVisible(true);
    
    escuelas.setSize(200, 25);
    escuelas.setLocation(100, 160);
    escuelas.setVisible(true);
    
    sis.setSize(100,25);
    sis.setLocation(320, 160);
    sis.setVisible(true);
    
    nopobre.setSize(100, 25);
    nopobre.setLocation(400, 160);
    nopobre.setVisible(true);
    
    pobre.setSize(100, 25);
    pobre.setLocation(500, 160);
    pobre.setVisible(true);
    
    pobrex.setSize(200, 25);
    pobrex.setLocation(600, 160);
    pobrex.setVisible(true);
    
    dir.setSize(100,25);
    dir.setLocation(20, 200);
    dir.setVisible(true);
    
    direccion.setSize(450, 25);
    direccion.setLocation(100, 200);
    direccion.setVisible(true);
    
    limpiar.setSize(100, 25);
    limpiar.setLocation(350, 350);
    limpiar.setVisible(true);
    
    agregar.setSize(100, 25);
    agregar.setLocation(500, 350);
    agregar.setVisible(true);
    
    cancelar.setSize(100, 25);
    cancelar.setLocation(650, 350);
    cancelar.setVisible(true);
    
    add(pPrincipal);
        
}

    /**
     * usado para crear la coexion con el servidor
     * @param serv
     * @param host
     * @param bada
     * @param usuario
     * @param contra
     * @return 
     */
public Connection getConection ( String serv, String host,String bada,String usuario,String contra){

Connection con= null;
try{
    
con=DriverManager.getConnection("jdbc:mysql://"+serv+":"+host+"/"+bada+""+"?useTimezone=true&serverTimezone=UTC",usuario,contra);

//System.out.println("coneccion exitosa");
}catch(Exception e){

//System.out.println("coneccion erronea");
    System.err.println(e);

}
return con;
    }  

/**
 * usado para llenar un combo con las escuelas de la base de datos
 * @param combo 
 */
public void llenandoescuelas(JComboBox combo){
    int cont=0;
    cont=contador("escuela");
    nomescu=new String[cont];
    idescu=new int[cont];
    try{
            Connection con = null;
            con = getConection("maria.db.arllk.com","43306","mydb","root","l33tsupah4x0r");
    PreparedStatement ps = null;
    ResultSet rs=null;
    String sql="SELECT * FROM escuela";
    
    ps= con.prepareStatement(sql);
    rs=ps.executeQuery();
    int cont2=0;
     while(rs.next())
    {
            nomescu[cont2]=rs.getString(2);
            idescu[cont2]=rs.getInt(1);
            cont2++;
    }
     
     combo.setModel(new javax.swing.DefaultComboBoxModel<>(nomescu));
     combo.setSelectedIndex(-1);
     
     }catch(Exception e){
           
        }
}

/**
 * usado para usar el procedure registrar_alumno de la base de datos
 * @param cod
 * @param esc
 * @param dni
 * @param sis
 * @param direc
 * @param nom
 * @param apel 
 */
public void agregando(String cod,int esc, String dni,String sis,String direc,String nom, String apel){
    try{
            Connection con = null;
            con = getConection("maria.db.arllk.com","43306","mydb","root","l33tsupah4x0r");
    PreparedStatement ps = null;
    ResultSet rs=null;
    
    String sql="call registrar_alumno('"+cod+"','"+esc+"','"+dni+"','"+sis+"','"+direc+"','"+nom+"','"+apel+"')";
    
    ps= con.prepareStatement(sql);
    rs=ps.executeQuery();
    
     //DesktopNotify.showDesktopMessage("", "Se registro al alumno",DesktopNotify.SUCCESS,10000L);
    
     }catch(Exception e){
           System.out.println(e);
        }
    
}

/**
 * usado para saber cuantas lineas hay en una tabla
 * @param tabla
 * @return 
 */
public int contador(String tabla){
    int cont=0;
    try{
            Connection con = null;
            con = getConection("maria.db.arllk.com","43306","mydb","root","l33tsupah4x0r");
    PreparedStatement ps = null;
    ResultSet rs=null;
    String sql="SELECT * FROM "+tabla;
    
    ps= con.prepareStatement(sql);
    rs=ps.executeQuery();
     while(rs.next())
    {
            cont++;
    }
     }catch(Exception e){
           
        }
    return cont;
}

/**
 * usado para saber si se presiono algun numero
 * @param evt
 * @return 
 */
public boolean numeropres(KeyEvent evt){
    boolean comp=false;
    
    if(evt.getKeyChar()==KeyEvent.VK_0){
        comp=true;
    }
    if(evt.getKeyChar()==KeyEvent.VK_1){
        comp=true;
    }
    if(evt.getKeyChar()==KeyEvent.VK_2){
        comp=true;
    }
    if(evt.getKeyChar()==KeyEvent.VK_3){
        comp=true;
    }
    if(evt.getKeyChar()==KeyEvent.VK_4){
        comp=true;
    }
    if(evt.getKeyChar()==KeyEvent.VK_5){
        comp=true;
    }
    if(evt.getKeyChar()==KeyEvent.VK_6){
        comp=true;
    }
    if(evt.getKeyChar()==KeyEvent.VK_7){
        comp=true;
    }
    if(evt.getKeyChar()==KeyEvent.VK_8){
        comp=true;
    }
    if(evt.getKeyChar()==KeyEvent.VK_9){
        comp=true;
    }
    
    return comp;
}

/**
 * usado para corroborar que todas las casillas esten completas y mandar al procedure
 */
public void agregandoabd(){
    listo=true;        
    revision();
    if(listo)
    {
        if(JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea registrar a este usuario?", "Atención", JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE)==JOptionPane.YES_OPTION){
            
        
                String codig=codigo.getText();
                int idesc=idescu[escuelas.getSelectedIndex()];
                String dni_es=docnacide.getText();
                String sisfoh="";
                if(nopobre.isSelected()){
                    sisfoh="no pobre";
                }
                if(pobre.isSelected()){
                    sisfoh="pobre";
                }
                if(pobrex.isSelected()){
                    sisfoh="pobre extremo";
                }
                String direcc=direccion.getText();
                if(direcc.equals("")){
                 direcc="direccion desconocida";   
                }
                
                String nombr=nombre.getText();
                String apelli= apellido.getText();
                
               
                //agregando(codig, idesc, dni_es, sisfoh, direcc, nombr, apelli);
        }
    }else{
        //DesktopNotify.showDesktopMessage("", "porfavor llenar todos los campos solicitados primero",DesktopNotify.FAIL,10000L);
    }
        
            }

/**
 * usado para comprovar y notificar que falta llenar una casilla
 */
public void revision(){
    
        /**
         * comprobando nombre
         */
        if(nombre.getText().equals("")){
            //DesktopNotify.showDesktopMessage("", "porfavor llene el nombre",DesktopNotify.ERROR,10000L);
            System.out.println("porfavor llene el nombre");
            listo=false;
        }
    
    
        /**
         * comprobando apellido
         */
        if(apellido.getText().equals("")){
            //DesktopNotify.showDesktopMessage("", "porfavor llene el apellido",DesktopNotify.ERROR,10000L);
             System.out.println("porfavor llene el apellido");
            listo=false;
        }
    
   
        /**
         * comprobando codigo
         */
        if(codigo.getText().equals("")){
            //DesktopNotify.showDesktopMessage("", "porfavor llene el codigo",DesktopNotify.ERROR,10000L);
             System.out.println("porfavor llene el codigo");
            listo=false;
        }
        if(comprobbd("codigo", codigo.getText())){
            //DesktopNotify.showDesktopMessage("", "este codigo ya esta registrado",DesktopNotify.ERROR,10000L);
             System.out.println("este codigo ya esta registrado");
            listo=false;
        }
    
   
       /**
         * comprobando dni
         */
        if(docnacide.getText().equals("")){
            //DesktopNotify.showDesktopMessage("", "porfavor llene el dni",DesktopNotify.ERROR,10000L);
             System.out.println("porfavor llene el dni");
            listo=false;
        }else{
            int auxdni=Integer.parseInt(docnacide.getText());
            if(!(auxdni>9999999 && auxdni<100000000)){
              //   DesktopNotify.showDesktopMessage("", "porfavor llene un dni valido(8 digitos)",DesktopNotify.ERROR,10000L);
               System.out.println("porfavor llene un dni valido(8 digitos)");
              listo=false;
            }
        }
        if(comprobbd("persona_dni", docnacide.getText())){
            //DesktopNotify.showDesktopMessage("", "este dni ya esta registrado",DesktopNotify.ERROR,10000L);
            System.out.println("este dni ya esta registrado");
            listo=false;
        }
    
       /**
         * comprobando escuelas
         */
        if(escuelas.getSelectedIndex()==-1){
            //DesktopNotify.showDesktopMessage("", "porfavor seleccione la escuela",DesktopNotify.ERROR,10000L);
            System.out.println("porfavor seleccione la escuela");
            listo=false;
        }
    
    
        /**
         * comprobando estado
         */
        if(!((nopobre.isSelected() || pobre.isSelected())||pobrex.isSelected())){
            //DesktopNotify.showDesktopMessage("", "porfavor seleccione un estado",DesktopNotify.ERROR,10000L);
            System.out.println("porfavor seleccione un estado");
            listo=false;
        }
    

}

/**
 * usado para comprovar si ya existe el dato en la tabla estudiante
 * @param col
 * @param cod
 * @return 
 */
public boolean comprobbd(String col, String cod){
    boolean comp=false;
    
    try{
            Connection con = null;
            con = getConection("maria.db.arllk.com","43306","mydb","root","l33tsupah4x0r");
    PreparedStatement ps = null;
    ResultSet rs=null;
    String sql="SELECT "+col+" FROM estudiante";
    
    ps= con.prepareStatement(sql);
    rs=ps.executeQuery();
     while(rs.next())
    {
        if(cod.equals(rs.getString(1))){
            comp=true;
        }
            
    }
     
    
     }catch(Exception e){
           
        }
    
    
    
    return comp;
}

}
