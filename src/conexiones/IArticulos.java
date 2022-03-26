package conexiones;

import datos.ArticulosData;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface IArticulos {
    public ArrayList<ArticulosData> seleccionar() throws SQLException;
    public int insertar(ArticulosData articulosData) throws SQLException;
    public int modificar(ArticulosData articulosData) throws SQLException;
    public int eliminar(ArticulosData articulosData) throws SQLException;
}
