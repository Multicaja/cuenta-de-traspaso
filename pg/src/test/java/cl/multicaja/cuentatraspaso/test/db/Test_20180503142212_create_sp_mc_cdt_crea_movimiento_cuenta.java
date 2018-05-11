package cl.multicaja.cuentatraspaso.test.db;

import cl.multicaja.core.test.TestDbBase;
import cl.multicaja.core.utils.ConfigUtils;
import cl.multicaja.core.utils.db.OutParam;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Map;

public class Test_20180503142212_create_sp_mc_cdt_crea_movimiento_cuenta extends TestDbBase {

  private static String schema = ConfigUtils.getInstance().getProperty("schema");


  @Test
  public void nuevoUsuarioPrimeraCarga() throws SQLException {


    JdbcTemplate jdbcTempate = dbUtils.getJdbcTemplate();

      //================================================================================
      // CREA CUENTA
      //================================================================================
      int idMovimiento = 1; // Solicitud Primera Carga

      String cuentaUsuario = "PREPAGO_19";
      String descCuenta = "Nueva cuenta PREPAGO_19";

      Object[] params = {cuentaUsuario, descCuenta, new OutParam("_id_cuenta", Types.NUMERIC), new OutParam("_numerror", Types.VARCHAR), new OutParam("_msjerror", Types.VARCHAR)};

      Map<String, Object> outputData = dbUtils.execute( schema + Constants.Procedures.SP_CREA_CUENTA.getName(), params);

      BigDecimal idCuenta = (BigDecimal) outputData.get("_id_cuenta");
      String numError = (String) outputData.get("_numerror");
      String msjError = (String) outputData.get("_msjerror");
      System.out.println("[MOVIMIENTO_CUENTA/CREA_CUENTA] NumError: "+numError +" MsjError: "+msjError);

      // Pruebas de Creacion de Cuenta.
      Assert.assertTrue("Numero de cuenta debe ser < 0", idCuenta.intValue() > 0);
      Assert.assertTrue("Numero de error 0 creacion correcta", numError.equals("0"));
      Assert.assertTrue("Msj de error vacio creacion correcta", StringUtils.isBlank(msjError));

      //================================================================================
      // 1 -Monto	$1000    // Deberia fallar limite Monto debe ser mayor a $3.000
      //================================================================================
      Movimiento respuestaMovimiento = callCreaMovimientoCuenta(cuentaUsuario,idMovimiento,0,"POSTX-10001","Primera Carga Prepago rut 1-9",new BigDecimal(1000));
      Assert.assertTrue("Id Movimiento Cta debe ser 0", respuestaMovimiento.getIdMovimiento() == 0);
      Assert.assertFalse("NumError != 0", respuestaMovimiento.getNumError().equals("0"));
      Assert.assertFalse("MsjError != vacio", StringUtils.isBlank(respuestaMovimiento.getMsjError()));

      //================================================================================
      // 2 -Monto	$55000   // Deberia fallar limite Monto carga ser menor a $50.000
      //================================================================================
      respuestaMovimiento = callCreaMovimientoCuenta(cuentaUsuario,idMovimiento,0,"POSTX-10001","Primera Carga Prepago rut 1-9",new BigDecimal(55000));
      Assert.assertTrue("Id Movimiento Cta debe ser 0", respuestaMovimiento.getIdMovimiento() == 0);
      Assert.assertFalse("NumError != 0", respuestaMovimiento.getNumError().equals("0"));
      Assert.assertFalse("MsjError != vacio", StringUtils.isBlank(respuestaMovimiento.getMsjError()));

      //================================================================================
      // 3 -Monto 	$45000   // Deberia pasar sin errores.
      //================================================================================
      respuestaMovimiento = callCreaMovimientoCuenta(cuentaUsuario,idMovimiento,0,"POSTX-10001","Primera Carga Prepago rut 1-9",new BigDecimal(45000));
      Assert.assertTrue("Id Movimiento Cta debe ser > 0", respuestaMovimiento.getIdMovimiento() > 0);
      Assert.assertTrue("NumError = 0", respuestaMovimiento.getNumError().equals("0"));
      Assert.assertTrue("MsjError = vacio", StringUtils.isBlank(respuestaMovimiento.getMsjError()));

      //================================================================================
      // 4 -Monto 	$45000   // Deberia fallar xq cantidad de primeras cargas es >1
      //================================================================================
      respuestaMovimiento = callCreaMovimientoCuenta(cuentaUsuario,idMovimiento,0,"POSTX-10001","Primera Carga Prepago rut 1-9",new BigDecimal(45000));
      Assert.assertTrue("Id Movimiento Cta debe ser 0", respuestaMovimiento.getIdMovimiento() == 0);
      Assert.assertFalse("NumError != 0", respuestaMovimiento.getNumError().equals("0"));
      Assert.assertFalse("MsjError != vacio", StringUtils.isBlank(respuestaMovimiento.getMsjError()));

      List lstAcumuladores = jdbcTempate.queryForList(
                          "SELECT " +
                            "     CAC.id AS ID, " +
                            "     CAC.monto AS MONTO , "+
                            "     RAC.codigo_operacion AS CODOPE "+
                            " FROM " +
                            "   "+schema+"."+Constants.Tables.CUENTA_ACUMULADOR.getName() +" CAC "+
                            " INNER JOIN "+schema+"."+Constants.Tables.REGLA_ACUMULACION.getName()+" RAC ON CAC.id_regla_acumulacion = RAC.id "+
                            " WHERE " +
                            " id_cuenta = "+idCuenta
      );

      //================================================================================
      // 4 -Verifica los Acumuladores
      //================================================================================
      for(Object data : lstAcumuladores) {
          Map<String,Object> aData = (Map<String, Object>) data;
          System.out.println("");
          if(((String) aData.get("CODOPE")).equalsIgnoreCase("SUM")) {
            System.out.println("VERIFICA ACUMULADORES SUMA");
            Assert.assertTrue("MsjError != vacio", ((BigDecimal) aData.get("MONTO")).doubleValue() <= 45000);
          }
          else if (((String) aData.get("CODOPE")).equalsIgnoreCase("COUNT")){
            System.out.println("VERIFICA ACUMULADORES COUNT");
            Assert.assertTrue("MsjError != vacio", ((BigDecimal) aData.get("MONTO")).doubleValue() == 1);
          }
        }
  }


  private  Movimiento callCreaMovimientoCuenta(String idCuenta, int idMovimiento, int idMovimientoRef,String idExterno, String glosa, BigDecimal monto ) throws SQLException{

    Movimiento movimiento = new Movimiento();

    Object[] params = {idCuenta, idMovimiento,idMovimientoRef,idExterno,glosa,monto, new OutParam("_id_movimiento_cuenta", Types.NUMERIC), new OutParam("_numerror", Types.VARCHAR), new OutParam("_msjerror", Types.VARCHAR)};

    Map<String, Object> outputData = dbUtils.execute( schema + Constants.Procedures.SP_CREA_MOVIMIENTO_CUENTA.getName(), params);

    BigDecimal id_movimiento_cuenta = (BigDecimal) outputData.get("_id_movimiento_cuenta");
    String numError = (String) outputData.get("_numerror");
    String msjError = (String) outputData.get("_msjerror");
    System.out.println("[MOVIMIENTO_CUENTA/CALL_CREA_MOVIMIENTO_CUENTA] NumError: "+numError +" MsjError: "+msjError);
    movimiento.setNumError(numError);
    movimiento.setMsjError(msjError);

    if (numError.equals("0")) {
      movimiento.setIdMovimiento(id_movimiento_cuenta.intValue());

    }
    else {
      movimiento.setIdMovimiento(0);

    }
    return movimiento;
  }

}
class ReglaAcumulacion {
  private Integer idRegla;
  private Integer monto;
  private String codOperacion;

  public Integer getIdRegla() {
    return idRegla;
  }

  public void setIdRegla(Integer idRegla) {
    this.idRegla = idRegla;
  }

  public Integer getMonto() {
    return monto;
  }

  public void setMonto(Integer monto) {
    this.monto = monto;
  }

  public String getCodOperacion() {
    return codOperacion;
  }

  public void setCodOperacion(String codOperacion) {
    this.codOperacion = codOperacion;
  }
}

class Movimiento {

  private int idMovimiento;
  private String numError;
  private String msjError;

  public int getIdMovimiento() {
    return idMovimiento;
  }

  public void setIdMovimiento(int idMovimiento) {
    this.idMovimiento = idMovimiento;
  }

  public String getNumError() {
    return numError;
  }

  public void setNumError(String numError) {
    this.numError = numError;
  }

  public String getMsjError() {
    return msjError;
  }

  public void setMsjError(String msjError) {
    this.msjError = msjError;
  }
}
