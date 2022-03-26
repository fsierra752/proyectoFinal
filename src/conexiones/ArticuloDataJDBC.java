package conexiones;

import datos.ArticulosData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import static conexiones.Conexion.close;
import static conexiones.Conexion.getConexionJDBC;

public class ArticuloDataJDBC implements IArticulos {

    private static final String SQL_SELECT = "SELECT id_articulos, nombre, descripcion, precio, disponibilidad FROM test.articulos";
    private static final String SQL_INSERT = "INSERT INTO test.articulos (`nombre`, `descripcion`, `precio`, `disponibilidad`) VALUES (?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE test.articulos SET nombre = ?, descripcion = ?, precio = ?, disponibilidad = ? WHERE id_articulos = ?";
    private static final String SQL_DELETE = "DELETE FROM test.articulos WHERE id_articulos = ?";

    @Override
    public ArrayList<ArticulosData> seleccionar() throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<ArticulosData> listaArticulos = new ArrayList<>();
        try {
            connection = getConexionJDBC();
            preparedStatement = connection.prepareStatement(SQL_SELECT);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                int idArticulos = resultSet.getInt("id_articulos");
                String nombre = resultSet.getString("nombre");
                String descripcion = resultSet.getString("descripcion");
                Float precio = resultSet.getFloat("precio");
                int disponibilidad = resultSet.getInt("disponibilidad");
                ArticulosData articuloNuevo = new ArticulosData(idArticulos, nombre, descripcion, precio, disponibilidad);
                listaArticulos.add(articuloNuevo);
            }
        }catch (SQLException error){
            error.printStackTrace(System.out);
        }
        finally {
            try {
                close(resultSet);
                close(preparedStatement);
                close(connection);
            }catch (SQLException error){
                error.printStackTrace(System.out);
            }
        }
        return listaArticulos;
    }

    @Override
    public int insertar(ArticulosData articulosData) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int registros = 0;
        try {
            connection = getConexionJDBC();
            preparedStatement = connection.prepareStatement(SQL_INSERT);
            preparedStatement.setString(1, articulosData.getNombreArt());
            preparedStatement.setString(2, articulosData.getDescripcionArt());
            preparedStatement.setFloat(3, articulosData.getPrecioArt());
            preparedStatement.setInt(4, articulosData.getDisponibilidadArt());
            registros = preparedStatement.executeUpdate();
        }catch (SQLException error){
            error.printStackTrace(System.out);
        }finally {
            try {
                close(preparedStatement);
                close(connection);

            }catch (SQLException error){
                error.printStackTrace(System.out);
            }
        }
        return registros;
    }

    @Override
    public int modificar(ArticulosData articulosData) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int registroModificados = 0;
        try {
            connection = getConexionJDBC();
            preparedStatement = connection.prepareStatement(SQL_UPDATE);
            preparedStatement.setString(1, articulosData.getNombreArt());
            preparedStatement.setString(2, articulosData.getDescripcionArt());
            preparedStatement.setFloat(3, articulosData.getPrecioArt());
            preparedStatement.setInt(4, articulosData.getDisponibilidadArt());
            preparedStatement.setInt(5, articulosData.getIdArt());
            registroModificados = preparedStatement.executeUpdate();
        }catch (SQLException error){
            error.printStackTrace(System.out);
        }finally {
            try {
                close(preparedStatement);
                close(connection);
            }catch (SQLException error){
                error.printStackTrace(System.out);
            }
        }
        return registroModificados;
    }

    @Override
    public int eliminar(ArticulosData articulosData) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int registroEliminado = 0;
        try {
            connection = getConexionJDBC();
            preparedStatement = connection.prepareStatement(SQL_DELETE);
            preparedStatement.setInt(1, articulosData.getIdArt());
            registroEliminado = preparedStatement.executeUpdate();
        }catch (SQLException error){
            error.printStackTrace(System.out);
        }finally {
            try {
                close(preparedStatement);
                close(connection);
            }catch (SQLException error){
                error.printStackTrace(System.out);
            }
        }
        return registroEliminado;
    }
}
