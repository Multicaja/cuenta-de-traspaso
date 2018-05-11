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

public class Test_20180430170307_create_sp_mc_cdt_crea_regla_acumulacion  extends TestDbBase {

  private static String schema = ConfigUtils.getInstance().getProperty("schema");
  /*
    IN _id_categoria_movimiento  NUMERIC,
    IN _periocidad          VARCHAR,
    IN _codigo_operacion    VARCHAR,
    OUT _NumError           VARCHAR,
    OUT _MsjError           VARCHAR
   */

  @Test
  public void spCreaReglaAcumulacion() throws SQLException {

    Object[] params = {1 ,"SEM","SUM" ,new OutParam("_numerror",Types.VARCHAR),new OutParam("_msjerror",Types.VARCHAR)};
    Map<String,Object> outputData = dbUtils.execute(schema+Constants.Procedures.SP_CREA_REGLA_ACUMULACION.getName(),params);

    String numError = (String) outputData.get("_numerror");
    String msjError = (String) outputData.get("_msjerror");

    System.out.println(" NumError: "+numError +" MsjError: "+msjError);

    Assert.assertTrue("Numero de error = 0 OK", numError.equals("0"));
    Assert.assertTrue("Sin Msje Error", StringUtils.isBlank(msjError));

  }

  @Test
  public void spCreaReglaAcumulacionErrorTipoMov() throws SQLException {

    Object[] params = {new NullParam(Types.NUMERIC),"SEM","SUM" ,new OutParam("_numerror",Types.VARCHAR),new OutParam("_msjerror",Types.VARCHAR)};
    Map<String,Object> outputData = dbUtils.execute(schema+Constants.Procedures.SP_CREA_REGLA_ACUMULACION.getName(),params);

    String numError = (String) outputData.get("_numerror");
    String msjError = (String) outputData.get("_msjerror");

    System.out.println(" NumError: "+numError +" MsjError: "+msjError);

    Assert.assertFalse("El mensaje de error debe ser != 0", numError.equals("0"));
    Assert.assertFalse("Con Msje Error", StringUtils.isBlank(msjError));

  }

  @Test
  public void spCreaReglaAcumulacionErrorPeriocidad() throws SQLException {


    Object[] params = {1,"","SUM" ,new OutParam("_numerror",Types.VARCHAR),new OutParam("_msjerror",Types.VARCHAR)};
    Map<String,Object> outputData = dbUtils.execute(schema+Constants.Procedures.SP_CREA_REGLA_ACUMULACION.getName(),params);

    String numError = (String) outputData.get("_numerror");
    String msjError = (String) outputData.get("_msjerror");

    System.out.println(" NumError: "+numError +" MsjError: "+msjError);
    Assert.assertFalse("El mensaje de error debe ser != 0", numError.equals("0"));
    Assert.assertFalse("Con Msje Error", StringUtils.isBlank(msjError));

  }
  @Test
  public void spCreaReglaAcumulacionErrorCodOperacion() throws SQLException {

    Object[] params = {1,"SEM","" ,new OutParam("_numerror",Types.VARCHAR),new OutParam("_msjerror",Types.VARCHAR)};
    Map<String,Object> outputData = dbUtils.execute(schema+Constants.Procedures.SP_CREA_REGLA_ACUMULACION.getName(),params);

    String numError = (String) outputData.get("_numerror");
    String msjError = (String) outputData.get("_msjerror");

    System.out.println(" NumError: "+numError +" MsjError: "+msjError);
    Assert.assertFalse("El mensaje de error debe ser != 0", numError.equals("0"));
    Assert.assertFalse("Con Msje Error", StringUtils.isBlank(msjError));

  }

}
