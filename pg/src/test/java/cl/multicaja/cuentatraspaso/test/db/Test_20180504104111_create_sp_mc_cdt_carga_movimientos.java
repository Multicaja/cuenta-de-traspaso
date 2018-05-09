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
import java.util.List;
import java.util.Map;

public class Test_20180504104111_create_sp_mc_cdt_carga_movimientos extends TestDbBase {

  private static String schema = ConfigUtils.getInstance().getProperty("schema");
  /*
    int i = 1;
    for(Object data : lstMovimientos)
    {
      Map<String,Object> aData = (Map<String, Object>) data;
      System.out.println("Fila================ "+i++);
      for (String key : aData.keySet() )
      {
        System.out.println(aData.get(key));
      }
    }
    IN  _NOMBRE         VARCHAR,
    OUT _movimientos    REFCURSOR,
    OUT _NumError       VARCHAR,
    OUT _MsjError       VARCHAR
   */

  @Test
  public void spCargaMovimiento() throws SQLException {

    Object[] params = {"Primera Carga" ,new OutParam("_movimientos",Types.OTHER),new OutParam("_numerror",Types.VARCHAR),new OutParam("_msjerror",Types.VARCHAR)};
    Map<String,Object> outputData = dbUtils.execute(schema+Constants.Procedures.SP_CARGA_MOVIMIENTOS.getName(),params);

    List lstMovimientos = (List) outputData.get("_movimientos");

    String numError = (String) outputData.get("_numerror");
    String msjError = (String) outputData.get("_msjerror");
    System.out.println(" Se encontraron: "+lstMovimientos.size()+" movimientos");
    System.out.println(" NumError: "+numError +" MsjError: "+msjError);

    Assert.assertTrue("Numero de error = 0 OK", numError.equals("0"));
    Assert.assertTrue("Sin Msje Error", StringUtils.isBlank(msjError));

  }
  @Test
  public void spCargaMovimientoParamInNull() throws SQLException {

    Object[] params = {new NullParam(Types.VARCHAR),new OutParam("_movimientos",Types.OTHER),new OutParam("_numerror",Types.VARCHAR),new OutParam("_msjerror",Types.VARCHAR)};
    Map<String,Object> outputData = dbUtils.execute(schema+Constants.Procedures.SP_CARGA_MOVIMIENTOS.getName(),params);

    List lstMovimientos = (List) outputData.get("_movimientos");

    String numError = (String) outputData.get("_numerror");
    String msjError = (String) outputData.get("_msjerror");
    System.out.println(" Se encontraron: "+lstMovimientos.size()+" movimientos");
    System.out.println(" NumError: "+numError +" MsjError: "+msjError);

    Assert.assertTrue("Numero de error = 0 OK", numError.equals("0"));
    Assert.assertTrue("Sin Msje Error", StringUtils.isBlank(msjError));

  }
  @Test
  public void spCargaMovimientoParamInVacio() throws SQLException {

    Object[] params = {" ",new OutParam("_movimientos",Types.OTHER),new OutParam("_numerror",Types.VARCHAR),new OutParam("_msjerror",Types.VARCHAR)};
    Map<String,Object> outputData = dbUtils.execute(schema+Constants.Procedures.SP_CARGA_MOVIMIENTOS.getName(),params);

    List lstMovimientos = (List) outputData.get("_movimientos");

    String numError = (String) outputData.get("_numerror");
    String msjError = (String) outputData.get("_msjerror");
    System.out.println(" Se encontraron: "+lstMovimientos.size()+" movimientos");
    System.out.println(" NumError: "+numError +" MsjError: "+msjError);

    Assert.assertTrue("Numero de error = 0 OK", numError.equals("0"));
    Assert.assertTrue("Sin Msje Error", StringUtils.isBlank(msjError));

  }
}
