package cl.multicaja.cuentatraspaso.test.db;

import cl.multicaja.core.test.TestDbBase;
import cl.multicaja.core.utils.ConfigUtils;
import cl.multicaja.core.utils.db.OutParam;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Map;

public class Test_20180430165322_create_sp_mc_cdt_crea_cuenta_v10 extends TestDB {

  @Test
  public void spCreaCuentaOk() throws SQLException {

    Object[] params = {"PREPAGO_175959289","Prepago RUT 175959289",new OutParam("_id_cuenta",Types.NUMERIC),new OutParam("_numerror",Types.VARCHAR),new OutParam("_msjerror",Types.VARCHAR)};
    Map<String,Object> outputData = dbUtils.execute(getSchema()+Constants.Procedures.SP_CREA_CUENTA.getName(),params);

    BigDecimal idCuenta = (BigDecimal) outputData.get("_id_cuenta");
    String numError = (String) outputData.get("_numerror");
    String msjError = (String) outputData.get("_msjerror");

    Assert.assertTrue("Numero de cuenta debe ser < 0", idCuenta.intValue() > 0);
    Assert.assertTrue("Numero de error 0 creacion correcta", numError.equals("0"));
    Assert.assertTrue("Msj de error vacio creacion correcta", StringUtils.isBlank(msjError));

    System.out.println("Id Cuenta: "+idCuenta+" NumError: "+numError +" MsjError: "+msjError);

  }

  @Test
  public void spCreaCuentaErrorIdCuentaExterno() throws SQLException {

    Object[] params = {"","",new OutParam("_id_cuenta",Types.NUMERIC),new OutParam("_numerror",Types.VARCHAR),new OutParam("_msjerror",Types.VARCHAR)};
    Map<String,Object>  outputData = dbUtils.execute(getSchema()+Constants.Procedures.SP_CREA_CUENTA.getName(),params);

    BigDecimal idCuenta = (BigDecimal) outputData.get("_id_cuenta");
    String numError = (String) outputData.get("_numerror");
    String msjError = (String) outputData.get("_msjerror");

    Assert.assertTrue("NNumero de cuenta debe ser != 0", idCuenta == null || idCuenta.intValue() == 0 );
    Assert.assertTrue("Numero de error = MC001", numError.equals("MC001"));
    Assert.assertFalse("Msj  no vacio", StringUtils.isBlank(msjError));

    System.out.println("Id Cuenta: "+idCuenta+" NumError: "+numError +" MsjError: "+msjError);

  }


}
