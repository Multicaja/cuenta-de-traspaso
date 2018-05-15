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

public class Test_20180504104111_create_sp_mc_cdt_carga_fases_movimientos_v10 extends TestDbBase {

  private static String schema = ConfigUtils.getInstance().getProperty("schema");


  @Test
  public void spCargaFasesMovimiento() throws SQLException {

    Object[] params = {"Primera Carga" ,new NullParam(Types.NUMERIC),new OutParam("_movimientos",Types.OTHER),new OutParam("_numerror",Types.VARCHAR),new OutParam("_msjerror",Types.VARCHAR)};
    Map<String,Object> outputData = dbUtils.execute(schema+Constants.Procedures.SP_CARGA_FASES_MOVIMIENTOS.getName(),params);

    List lstMovimientos = (List) outputData.get("_movimientos");

    String numError = (String) outputData.get("_numerror");
    String msjError = (String) outputData.get("_msjerror");
    System.out.println(" Se encontraron: "+lstMovimientos.size()+" movimientos");
    System.out.println(" NumError: "+numError +" MsjError: "+msjError);

    Assert.assertTrue("Numero de error = 0 OK", numError.equals("0"));
    Assert.assertTrue("Sin Msje Error", StringUtils.isBlank(msjError));

  }
  @Test
  public void spCargaFasesMovimientoParamInNull() throws SQLException {

    Object[] params = {new NullParam(Types.VARCHAR),new NullParam(Types.NUMERIC),new OutParam("_movimientos",Types.OTHER),new OutParam("_numerror",Types.VARCHAR),new OutParam("_msjerror",Types.VARCHAR)};
    Map<String,Object> outputData = dbUtils.execute(schema+Constants.Procedures.SP_CARGA_FASES_MOVIMIENTOS.getName(),params);

    List lstMovimientos = (List) outputData.get("_movimientos");

    String numError = (String) outputData.get("_numerror");
    String msjError = (String) outputData.get("_msjerror");
    System.out.println(" Se encontraron: "+lstMovimientos.size()+" movimientos");
    System.out.println(" NumError: "+numError +" MsjError: "+msjError);

    Assert.assertTrue("Numero de error = 0 OK", numError.equals("0"));
    Assert.assertTrue("Sin Msje Error", StringUtils.isBlank(msjError));

  }
  @Test
  public void spCargaFasesMovimientoParamInVacio() throws SQLException {

    Object[] params = {"",0,new OutParam("_movimientos",Types.OTHER),new OutParam("_numerror",Types.VARCHAR),new OutParam("_msjerror",Types.VARCHAR)};
    Map<String,Object> outputData = dbUtils.execute(schema+Constants.Procedures.SP_CARGA_FASES_MOVIMIENTOS.getName(),params);

    List lstMovimientos = (List) outputData.get("_movimientos");

    String numError = (String) outputData.get("_numerror");
    String msjError = (String) outputData.get("_msjerror");
    System.out.println(" Se encontraron: "+lstMovimientos.size()+" movimientos");
    System.out.println(" NumError: "+numError +" MsjError: "+msjError);

    Assert.assertTrue("Numero de error = 0 OK", numError.equals("0"));
    Assert.assertTrue("Sin Msje Error", StringUtils.isBlank(msjError));

  }
}
