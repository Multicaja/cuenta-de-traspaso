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

public class Test_20180430170126_create_sp_mc_cdt_crea_categoria_mov_fase_v10 extends TestDbBase {

  private static String schema = ConfigUtils.getInstance().getProperty("schema");


  @Test
  public void spCreaMovTipoMovOk() throws SQLException {

    Object[] params = { 7 , 7 ,new OutParam("_numerror",Types.VARCHAR),new OutParam("_msjerror",Types.VARCHAR)};
    Map<String,Object> outputData = dbUtils.execute(schema+Constants.Procedures.SP_CREA_MOV_TIPO_MOV.getName(),params);

    String numError = (String) outputData.get("_numerror");
    String msjError = (String) outputData.get("_msjerror");

    System.out.println(" NumError: "+numError +" MsjError: "+msjError);

    Assert.assertTrue("Numero de error = 0 OK", numError.equals("0"));
    Assert.assertTrue("Sin Msje Error", StringUtils.isBlank(msjError));

  }

  @Test
  public void spCreaMovTipoMovErrorIdMov() throws SQLException {

    Object[] params = {new NullParam(Types.NUMERIC), 1 ,new OutParam("_numerror",Types.VARCHAR),new OutParam("_msjerror",Types.VARCHAR)};
    Map<String,Object> outputData = dbUtils.execute(schema+Constants.Procedures.SP_CREA_MOV_TIPO_MOV.getName(),params);

    String numError = (String) outputData.get("_numerror");
    String msjError = (String) outputData.get("_msjerror");

    System.out.println(" NumError: "+numError +" MsjError: "+msjError);

    Assert.assertTrue("Numero de error = MC001", numError.equals("MC001"));
    Assert.assertFalse("Existe Msj Error", StringUtils.isBlank(msjError));
  }

  @Test
  public void spCreaMovTipoMovErrorIdTipoMov() throws SQLException {

    Object[] params = { 1 , new NullParam(Types.NUMERIC),new OutParam("_numerror",Types.VARCHAR),new OutParam("_msjerror",Types.VARCHAR)};
    Map<String,Object> outputData = dbUtils.execute(schema+Constants.Procedures.SP_CREA_MOV_TIPO_MOV.getName(),params);

    String numError = (String) outputData.get("_numerror");
    String msjError = (String) outputData.get("_msjerror");

    System.out.println(" NumError: "+numError +" MsjError: "+msjError);

    Assert.assertTrue("Numero de error != 0", numError.equals("MC002"));
    Assert.assertFalse("Existe Msj Error", StringUtils.isBlank(msjError));

  }

  @Test
  public void spCreaMovTipoMovErrorDuplicateKey() throws SQLException {

    Object[] params = { 7 , 7, new OutParam("_numerror",Types.VARCHAR),new OutParam("_msjerror",Types.VARCHAR)};
    Map<String,Object> outputData = dbUtils.execute(schema+Constants.Procedures.SP_CREA_MOV_TIPO_MOV.getName(),params);

    String numError = (String) outputData.get("_numerror");
    String msjError = (String) outputData.get("_msjerror");

    System.out.println(" NumError: "+numError +" MsjError: "+msjError);

    Assert.assertFalse("Numero de error = MC001", numError.equals("0"));
    Assert.assertFalse("Existe Msj Error(Duplicate Key)", StringUtils.isBlank(msjError));

  }
}
