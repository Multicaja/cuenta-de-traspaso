package cl.multicaja.cuentatraspaso.test.db;

import cl.multicaja.core.test.TestDbBase;
import cl.multicaja.core.utils.ConfigUtils;
import cl.multicaja.core.utils.db.NullParam;
import cl.multicaja.core.utils.db.OutParam;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;
import java.sql.Types;
import java.util.Map;

public class Test_20180430170419_create_sp_mc_cdt_crea_categoria_movimiento extends TestDbBase {

  private static String schema = ConfigUtils.getInstance().getProperty("schema");
  /*
    IN _id_bolsa        NUMERIC,
    IN _nombre          VARCHAR,
    IN _descripcion     VARCHAR,
    OUT _NumError       VARCHAR,
    OUT _MsjError       VARCHAR
   */

  @Test
  public void spCreaCategoriaMovimiento() throws SQLException {

    Object[] params = {1 ,"Recarga Test","Recarga test (Pruebas)" ,new OutParam("_numerror",Types.VARCHAR),new OutParam("_msjerror",Types.VARCHAR)};
    Map<String,Object> outputData = dbUtils.execute(schema+Constants.Procedures.SP_CREA_CATEGORIA_MOVIMIENTO.getName(),params);

    String numError = (String) outputData.get("_numerror");
    String msjError = (String) outputData.get("_msjerror");

    System.out.println(" NumError: "+numError +" MsjError: "+msjError);

    Assert.assertTrue("Numero de error = 0 OK", numError.equals("0"));
    Assert.assertTrue("Sin Msje Error", StringUtils.isBlank(msjError));

  }

  @Test
  public void spCreaCategoriaMovimientoErrorBolsa() throws SQLException {

    Object[] params = {new NullParam(Types.NUMERIC),"Recarga Test","Recarga test (Pruebas)" ,new OutParam("_numerror",Types.VARCHAR),new OutParam("_msjerror",Types.VARCHAR)};
    Map<String,Object> outputData = dbUtils.execute(schema+Constants.Procedures.SP_CREA_CATEGORIA_MOVIMIENTO.getName(),params);

    String numError = (String) outputData.get("_numerror");
    String msjError = (String) outputData.get("_msjerror");

    System.out.println(" NumError: "+numError +" MsjError: "+msjError);

    Assert.assertFalse("Num Error != 0", numError.equals("0"));
    Assert.assertFalse("Msj Error no vacio", StringUtils.isBlank(msjError));

  }

  @Test
  public void spCreaCategoriaMovimientoErrorNombre() throws SQLException {

    Object[] params = {1,"","Recarga test (Pruebas)" ,new OutParam("_numerror",Types.VARCHAR),new OutParam("_msjerror",Types.VARCHAR)};
    Map<String,Object> outputData = dbUtils.execute(schema+Constants.Procedures.SP_CREA_CATEGORIA_MOVIMIENTO.getName(),params);

    String numError = (String) outputData.get("_numerror");
    String msjError = (String) outputData.get("_msjerror");

    System.out.println(" NumError: "+numError +" MsjError: "+msjError);

    Assert.assertFalse("Num Error != 0", numError.equals("0"));
    Assert.assertFalse("Msj Error no vacio", StringUtils.isBlank(msjError));

  }
}
