package test;

import frameworks.MenuFormulario;
import javax.swing.*;

/**
 * @api {get} /user/:id Documentacion formulario de articulos
 * @apiName GetUser
 * @apiGroup User
 *
 * @apiParam {Number} id Articulos
 * @apiParam {String} Nombre articulo
 * @apiParam {String} Descripcion articulo
 * @apiParam {Float} Precio articulo
 * @apiParam {Int} Disponibilidad articulo
 *
 *  @apiDescription    Para ejecucion del programa se debe tener gestor de base de datos MySQL -
 *  Crear las siguientes variables de entorno en el equipo para conexion  -
 *  url_host_mysql = localhost
 *  url_puerto_mysql = 3306
 *  user_mysql = root
 *  user_password_mysql = admin
 *  url_data_base = test
 *  crear una tabla en base de datos test de nombre articulos con el siguiente Query
 *  CREATE TABLE `test`.`articulos` (
 *  `id_articulos` INT NOT NULL AUTO_INCREMENT,
 *  `nombre` VARCHAR(45) NOT NULL,
 *  `descripcion` VARCHAR(45) NOT NULL,
 *  `precio` VARCHAR(45) NOT NULL,
 *  `disponibilidad` VARCHAR(45) NOT NULL,
 *  PRIMARY KEY (`id_articulos`));
 *  boton insertar = inserta un nuevo articulo en base de datos.
 *  boton consultar = consulta todos los articulos de la base de datos.
 *  boton limpiar campos  = borra la informacion que se encuentre en los campos.
 *  boton eliminiar = borra un articulo saleccionado de la tabla consultada.
 *  boton modificar = modifica un articulo saleccionado de la tabla consultada,
 *  validaciones = se puede modificar nombre, descripcion, precio o disponibilidad, desde los campos del formulario.
 *  validaciones = nombre y descripcion, se puede ingresar cualquier caracter con limete de max de 45.
 *  validaciones = precio y disponibilidad, campos numericos con limite de max de 10 y 8.
 *
 */

public class MenuJDBC {
    static JFrame form = new JFrame("Formulario Articulos");

    public static void main(String[] args)  {
        form.setContentPane(new MenuFormulario().getContenedor());
        form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        form.pack();
        form.setSize(1000,800);
        form.setVisible(true);
    }
}
