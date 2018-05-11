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

public class Test_20180430170002_create_sp_mc_cdt_crea_fase_movimiento extends TestDbBase {

  private static String schema = ConfigUtils.getInstance().getProperty("schema");

  /*
    IN _nombre          VARCHAR,
    IN _descripcion     VARCHAR,
    IN _signo           NUMERIC,
    OUT _NumError       VARCHAR,
    OUT _MsjError       VARCHAR
   */
  @Test
  public void spCreaFaseMovimientoOK() throws SQLException {

    Object[] params = {"Movimiento de Carga Test","Carga de Tarjeta Test", 1  ,"N", new OutParam("_numerror",Types.VARCHAR),new OutParam("_msjerror",Types.VARCHAR)};
    Map<String,Object> outputData = dbUtils.execute(schema+Constants.Procedures.SP_CREA_FASE_MOVIMIENTO.getName(),params);

    String numError = (String) outputData.get("_numerror");
    String msjError = (String) outputData.get("_msjerror");
    System.out.println(" NumError: "+numError +" MsjError: "+msjError);

    Assert.assertTrue("Numero de error 0 creacion correcta", numError.equals("0"));
    Assert.assertTrue("Msj de error vacio creacion correcta", StringUtils.isBlank(msjError));

  }

  @Test
  public void spCreaFaseMovimientoErrorSinNombre() throws SQLException {

    Object[] params = {"","Carga de Tarjeta Test", 1  , "N",new OutParam("_numerror",Types.VARCHAR),new OutParam("_msjerror",Types.VARCHAR)};
    Map<String,Object> outputData = dbUtils.execute(schema+Constants.Procedures.SP_CREA_FASE_MOVIMIENTO.getName(),params);

    String numError = (String) outputData.get("_numerror");
    String msjError = (String) outputData.get("_msjerror");

    System.out.println(" NumError: "+numError +" MsjError: "+msjError);

    Assert.assertFalse("Num Error != 0", numError.equals("0"));
    Assert.assertFalse("Existe Msj Error", StringUtils.isBlank(msjError));

  }
  @Test
  public void spCreaFaseMovimientoErrorSigno() throws SQLException {

    Object[] params = {"Movimiento de Carga Test","Carga de Tarjeta Test", new NullParam(Types.NUMERIC),  "N",new OutParam("_numerror",Types.VARCHAR),new OutParam("_msjerror",Types.VARCHAR)};
    Map<String,Object> outputData = dbUtils.execute(schema+Constants.Procedures.SP_CREA_FASE_MOVIMIENTO.getName(),params);

    String numError = (String) outputData.get("_numerror");
    String msjError = (String) outputData.get("_msjerror");
    System.out.println(" NumError: "+numError +" MsjError: "+msjError);

    Assert.assertFalse("Num Error != 0", numError.equals("0"));
    Assert.assertFalse("Existe Msj Error", StringUtils.isBlank(msjError));

  }

  @Test
  public void spCreaFaseMovimientoErrorIndConfirmacion() throws SQLException {

    Object[] params = {"Movimiento de Carga Test","Carga de Tarjeta Test", 1 , "", new OutParam("_numerror",Types.VARCHAR),new OutParam("_msjerror",Types.VARCHAR)};
    Map<String,Object> outputData = dbUtils.execute(schema+Constants.Procedures.SP_CREA_FASE_MOVIMIENTO.getName(),params);

    String numError = (String) outputData.get("_numerror");
    String msjError = (String) outputData.get("_msjerror");
    System.out.println(" NumError: "+numError +" MsjError: "+msjError);

    Assert.assertFalse("Num Error != 0", numError.equals("0"));
    Assert.assertFalse("Existe Msj Error", StringUtils.isBlank(msjError));

  }
}
