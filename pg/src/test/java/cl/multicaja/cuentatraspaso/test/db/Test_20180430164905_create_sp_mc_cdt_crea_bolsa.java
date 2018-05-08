package cl.multicaja.cuentatraspaso.test.db;

import cl.multicaja.core.test.TestDbBase;
import cl.multicaja.core.utils.ConfigUtils;
import cl.multicaja.core.utils.db.OutParam;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;
import java.sql.Types;
import java.util.Map;

public class Test_20180430164905_create_sp_mc_cdt_crea_bolsa extends TestDbBase {

  private static String schema = ConfigUtils.getInstance().getProperty("schema");

  /*
    IN _nombre character varying,
    IN _descripcion character varying,
    OUT _numerror character varying,
    OUT _msjerror character varying)
   */
  @Test
  public void spCreaCuentaOk() throws SQLException {

      Object[] params = {"Bolsa Test","Bolsta para casos Prueba",new OutParam("_numerror",Types.VARCHAR),new OutParam("_msjerror",Types.VARCHAR)};
      Map<String,Object>  outputData = dbUtils.execute(schema+Constants.Procedures.SP_CREA_BOLSA.getName(),params);
      String numError = (String) outputData.get("_numerror");
      String msjError= (String) outputData.get("_msjerror");
      Assert.assertEquals("Numero de error 0 creacion correcta",true,numError.equals("0"));
      Assert.assertEquals("Msj de error vacio creacion correcta",true,StringUtils.isBlank(msjError));
      System.out.println("NumError: "+numError +"MsjError"+msjError);
  }

  @Test
  public void spCreaBolsaErrorSp() throws SQLException {
    Object[] params = {""," ",new OutParam("_numerror",Types.VARCHAR),new OutParam("_msjerror",Types.VARCHAR)};
    Map<String,Object>  outputData = dbUtils.execute(schema+Constants.Procedures.SP_CREA_BOLSA.getName(),params);
    String numError = (String) outputData.get("_numerror");
    String msjError= (String) outputData.get("_msjerror");
    Assert.assertEquals("Error en SP NumError != 0",true,!numError.equals("0"));
    Assert.assertEquals("Error en SP MsjError Not Blank",true,!StringUtils.isBlank(msjError));
    System.out.println("NumError: "+numError +"MsjError"+msjError);
  }
}
