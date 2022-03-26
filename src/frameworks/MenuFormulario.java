package frameworks;

import conexiones.ArticuloDataJDBC;
import conexiones.IArticulos;
import datos.ArticulosData;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class MenuFormulario {
    private JPanel contenedor;
    private JTextField textDescripcion;
    private JTextField textNombre;
    private JTextField textDisponibilidad;
    private JTextField textPrecio;
    private JButton btnLimpiar;
    private JButton btnInsertar;
    private JTable tableUsuarios;
    private JLabel labelError;
    private JLabel labelExito;
    private JButton btnConsultar;
    private JButton btnEliminar;
    private JLabel labelText;
    private JLabel labelColumna;
    private JButton btnModificar;
    private JLabel labelErrorPecio;
    private JLabel labelErrorDisponibilidad;
    private JLabel labelMaxCaracter4;
    private JLabel labelMaxCaracter3;
    private JLabel labelErrorAmbos;

    String[][] tablaArticulos = new String[100][5];
    String[] descripcionArticulos = {"id","Nombre ", "Descripcion", "Precio", "Disponibilidad"};
    String[][] printTablaArticulos = new String[tablaArticulos.length][5];
    IArticulos servicosJDBC = new ArticuloDataJDBC();
    int contador = 0;

    public MenuFormulario(){
        tableUsuarios.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                labelText.setVisible(false);
                labelColumna.setVisible(false);
                btnEliminar.setEnabled(true);
                btnModificar.setEnabled(true);
                if (me.getClickCount() == 1) {
                    labelText.setVisible(true);
                    JTable target = (JTable) me.getSource();
                    int row = target.getSelectedRow();
                    labelColumna.setVisible(true);
                    labelColumna.setText(tableUsuarios.getValueAt(row, 0).toString());
                    textNombre.setText(tableUsuarios.getValueAt(row, 1).toString());
                    textDescripcion.setText(tableUsuarios.getValueAt(row, 2).toString());
                    textPrecio.setText(tableUsuarios.getValueAt(row, 3).toString());
                    textDisponibilidad.setText(tableUsuarios.getValueAt(row, 4).toString());
                }
            }
        });
        btnInsertar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labelExito.setVisible(false);
                labelError.setVisible(false);
                if ( textNombre.getText().equals("") || textDescripcion.getText().equals("")
                        || textPrecio.getText().equals("")|| textDisponibilidad.getText().equals(""))
                {
                    labelError.setVisible(true);
                    limpiarCampos();
                    btnEliminar.setEnabled(false);
                    btnModificar.setEnabled(false);
                } else {
                    for(int i = 0; i < tablaArticulos.length; i++){
                        printTablaArticulos[i][0] = tablaArticulos[i][0];
                        printTablaArticulos[i][1] = tablaArticulos[i][1];
                        printTablaArticulos[i][2] = tablaArticulos[i][2];
                        printTablaArticulos[i][3] = tablaArticulos[i][3];
                    }
                    printTablaArticulos[contador][0] = textNombre.getText();
                    printTablaArticulos[contador][1] = textDescripcion.getText();
                    printTablaArticulos[contador][2] = textPrecio.getText();
                    printTablaArticulos[contador][3] = textDisponibilidad.getText();
                    try {
                        insertarDatos(textNombre.getText(), textDescripcion.getText(), Float.parseFloat(textPrecio.getText()),
                                Integer.parseInt(textDisponibilidad.getText()));
                    }catch (NumberFormatException error){
                        labelErrorAmbos.setVisible(true);
                        return;
                    }
                    printTablaArticulos = consultarJDBC();
                    llenarTabla(printTablaArticulos, descripcionArticulos);
                    limpiarCampos();
                    labelError.setVisible(false);
                    labelMaxCaracter3.setVisible(false);
                    labelMaxCaracter4.setVisible(false);
                    labelErrorAmbos.setVisible(false);
                    contador++;
                }
            }
        });

        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
                btnEliminar.setEnabled(false);
                btnModificar.setEnabled(false);
                labelMaxCaracter3.setVisible(false);
                labelMaxCaracter4.setVisible(false);
            }
        });

        btnConsultar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                printTablaArticulos = consultarJDBC();
                llenarTabla(printTablaArticulos, descripcionArticulos);
                limpiarCampos();
                btnEliminar.setEnabled(false);
                btnModificar.setEnabled(false);
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    try {
                        int id = Integer.parseInt(labelColumna.getText());
                        ArticulosData eliminarArticulo = new ArticulosData(id);
                        servicosJDBC.eliminar(eliminarArticulo);
                        printTablaArticulos = consultarJDBC();
                        llenarTabla(printTablaArticulos, descripcionArticulos);
                        limpiarCampos();
                        btnEliminar.setEnabled(false);
                        btnModificar.setEnabled(false);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
            }
        });

        btnModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArticulosData modificarArticulo = new ArticulosData(Integer.parseInt(labelColumna.getText()),
                        textNombre.getText(), textDescripcion.getText(), Float.parseFloat(textPrecio.getText()),
                        Integer.parseInt(textDisponibilidad.getText()));
                try {
                    servicosJDBC.modificar(modificarArticulo);
                    printTablaArticulos = consultarJDBC();
                    llenarTabla(printTablaArticulos, descripcionArticulos);
                    limpiarCampos();
                    btnModificar.setEnabled(false);
                    btnEliminar.setEnabled(false);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        textNombre.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evt) {
                if(textNombre.getText().length() >= 45)
                {
                    evt.consume();
                }
            }
        });

        textDescripcion.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evt) {
                if(textDescripcion.getText().length() >= 45)
                {
                    evt.consume();
                }
            }
        });

        textPrecio.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evt) {
                String [] v = {"0","1","2","3","4","5","6","7","8","9","."};
                char c = evt.getKeyChar();
                String s = "%" + c ;
                s = s.replace("%", "");
                if (s.equals(v[0])||s.equals(v[1])||s.equals(v[2])||s.equals(v[3])||s.equals(v[4])||s.equals(v[5])){
                    btnInsertar.setEnabled(true);
                }else  if (s.equals(v[6])||s.equals(v[7])||s.equals(v[8])||s.equals(v[9])||s.equals(v[10])){
                    btnInsertar.setEnabled(true);
                }else{
                    labelErrorPecio.setVisible(true);
                    limpiarCamposNumericos();
                    btnInsertar.setEnabled(false);
                }
                if(textPrecio.getText().length() >= 10)
                {
                    evt.consume();
                }
            }
        });
        textDisponibilidad.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evt) {
                String [] v = {"0","1","2","3","4","5","6","7","8","9"};
                char c = evt.getKeyChar();
                String s = "%" + c ;
                s = s.replace("%", "");
                if (s.equals(v[0])||s.equals(v[1])||s.equals(v[2])||s.equals(v[3])||s.equals(v[4])||s.equals(v[5])){
                    btnInsertar.setEnabled(true);
                }else  if (s.equals(v[6])||s.equals(v[7])||s.equals(v[8])||s.equals(v[9])){
                    btnInsertar.setEnabled(true);
                }else{
                    labelErrorDisponibilidad.setVisible(true);
                    limpiarCamposNumericos();
                    btnInsertar.setEnabled(false);
                }
                if(textDisponibilidad.getText().length() >= 8)
                {
                    evt.consume();
                }
            }
        });

    }

    private void llenarTabla(String[][] records, String[] nroColumnas) {
           tableUsuarios.setModel(new DefaultTableModel(records, nroColumnas));
    }

    private void insertarDatos(String nombre, String descripcion, float precio, int disponibilidad){
        ArticulosData inserUser = new ArticulosData(nombre, descripcion, precio, disponibilidad);
        try {
            servicosJDBC.insertar(inserUser);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String[][] consultarJDBC(){
        ArrayList<ArticulosData> resultJDBC = null;
        try {
            resultJDBC = servicosJDBC.seleccionar();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DefaultTableModel modelList = new DefaultTableModel();
        String[][] nuevosDatos = new String[tablaArticulos.length][5];
        int contador = 0;
        for (ArticulosData articulosData: resultJDBC){
            nuevosDatos[contador][0] = String.valueOf(articulosData.getIdArt());
            nuevosDatos[contador][1] = articulosData.getNombreArt();
            nuevosDatos[contador][2] = articulosData.getDescripcionArt();
            nuevosDatos[contador][3] = String.valueOf(articulosData.getPrecioArt());
            nuevosDatos[contador][4] = String.valueOf(articulosData.getDisponibilidadArt());
            modelList.addColumn(articulosData);
            contador++;
        }
        return nuevosDatos;
    }

    private void limpiarCampos(){
        textNombre.setText("");
        textDescripcion.setText("");
        textPrecio.setText("");
        textDisponibilidad.setText("");
        labelErrorDisponibilidad.setVisible(false);
        labelErrorPecio.setVisible(false);
    }

    private void limpiarCamposNumericos(){
        textPrecio.setText("");
        textDisponibilidad.setText("");
    }

    public MenuFormulario(JPanel contenedor) {
        this.contenedor = contenedor;
    }

    public JPanel getContenedor() {
        return contenedor;
    }

    public void setContenedor(JPanel contenedor) {
        this.contenedor = contenedor;
    }

    public JTextField getTextApellido() {
        return textDescripcion;
    }

    public void setTextApellido(JTextField textApellido) {
        this.textDescripcion = textApellido;
    }

    public JTextField getTextNombre() {
        return textNombre;
    }

    public void setTextNombre(JTextField textNombre) {
        this.textNombre = textNombre;
    }

    public JTextField getTextDireccion() {
        return textDisponibilidad;
    }

    public void setTextDireccion(JTextField textDireccion) {
        this.textDisponibilidad = textDireccion;
    }

    public JTextField getTextCedula() {
        return textPrecio;
    }

    public void setTextCedula(JTextField textCedula) {
        this.textPrecio = textCedula;
    }

    public JButton getBtnCancelar() {
        return btnLimpiar;
    }

    public void setBtnCancelar(JButton btnCancelar) {
        this.btnLimpiar = btnCancelar;
    }

    public JButton getBtnAceptar() {
        return btnInsertar;
    }

    public void setBtnAceptar(JButton btnAceptar) {
        this.btnInsertar = btnAceptar;
    }

    public JTable getTableUsuarios() {
        return tableUsuarios;
    }

    public void setTableUsuarios(JTable tableUsuarios) {
        this.tableUsuarios = tableUsuarios;
    }
}
