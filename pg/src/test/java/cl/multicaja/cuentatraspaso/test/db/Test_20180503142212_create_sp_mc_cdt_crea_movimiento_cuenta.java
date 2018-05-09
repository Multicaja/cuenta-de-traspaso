package cl.multicaja.cuentatraspaso.test.db;

import cl.multicaja.core.test.TestDbBase;
import cl.multicaja.core.utils.ConfigUtils;
import cl.multicaja.core.utils.db.OutParam;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Map;

public class Test_20180503142212_create_sp_mc_cdt_crea_movimiento_cuenta extends TestDbBase {

  private static String schema = ConfigUtils.getInstance().getProperty("schema");
  /*
    IN _id_cuenta               VARCHAR,
    IN _id_movimiento           NUMERIC,
    IN _id_mov_referencia       NUMERIC,
    IN _id_tx_externo           VARCHAR,
    IN _glosa                   VARCHAR,
    IN _monto                   NUMERIC,
    OUT _id_movimiento_cuenta   NUMERIC,
    OUT _NumError               VARCHAR,
    OUT _MsjError               VARCHAR
   */
 /*public static void main(String[] args) throws Exception {
    TestSuite.setUp();
    Test_20180503142212_create_sp_mc_cdt_crea_movimiento_cuenta t = new Test_20180503142212_create_sp_mc_cdt_crea_movimiento_cuenta();
    t.nuevoUsuarioPrimeraCarga();
  }*/
  @Test
  public void nuevoUsuarioPrimeraCarga() throws SQLException {

    Connection oConexion = dbUtils.getConnection();
    oConexion.setAutoCommit(false);
    JdbcTemplate jdbcTempate = dbUtils.getJdbcTemplate();
    try
    {
      //================================================================================
      // CREA CUENTA
      //================================================================================
        int idMovimiento = 1; // Solicitud Primera Carga

        String cuentaUsuario = "PREPAGO_19";
        String descCuenta = "Nueva cuenta PREPAGO_19";

        Object[] params = {cuentaUsuario, descCuenta, new OutParam("_id_cuenta", Types.NUMERIC), new OutParam("_numerror", Types.VARCHAR), new OutParam("_msjerror", Types.VARCHAR)};

        Map<String, Object> outputData = dbUtils.execute(oConexion, schema + Constants.Procedures.SP_CREA_CUENTA.getName(), params);

        BigDecimal idCuenta = (BigDecimal) outputData.get("_id_cuenta");
        String numError = (String) outputData.get("_numerror");
        String msjError = (String) outputData.get("_msjerror");
        System.out.println("[MOVIMIENTO_CUENTA/CREA_CUENTA] NumError: "+numError +" MsjError: "+msjError);

      if (numError.equals("0"))
        oConexion.commit();

      // Pruebas de Creacion de Cuenta.
      Assert.assertTrue("Numero de cuenta debe ser < 0", idCuenta.intValue() > 0);
      Assert.assertTrue("Numero de error 0 creacion correcta", numError.equals("0"));
      Assert.assertTrue("Msj de error vacio creacion correcta", StringUtils.isBlank(msjError));

      //================================================================================
      // 1 -Monto	$1000    // Deberia fallar limite Monto debe ser mayor a $3.000
      //================================================================================
      Movimiento respuestaMovimiento = callCreaMovimientoCuenta(oConexion,cuentaUsuario,idMovimiento,0,"POSTX-10001","Primera Carga Prepago rut 1-9",new BigDecimal(1000));
      Assert.assertTrue("Id Movimiento debe ser 0", respuestaMovimiento.getIdMovimiento() == 0);
      Assert.assertFalse("NumError != 0", respuestaMovimiento.getNumError().equals("0"));
      Assert.assertFalse("MsjError != vacio", StringUtils.isBlank(respuestaMovimiento.getMsjError()));

      //================================================================================
      // 2 -Monto	$55000   // Deberia fallar limite Monto carga ser menor a $50.000
      //================================================================================
      respuestaMovimiento = callCreaMovimientoCuenta(oConexion,cuentaUsuario,idMovimiento,0,"POSTX-10001","Primera Carga Prepago rut 1-9",new BigDecimal(55000));
      Assert.assertTrue("Id Movimiento debe ser 0", respuestaMovimiento.getIdMovimiento() == 0);
      Assert.assertFalse("NumError != 0", respuestaMovimiento.getNumError().equals("0"));
      Assert.assertFalse("MsjError != vacio", StringUtils.isBlank(respuestaMovimiento.getMsjError()));

      //================================================================================
      // 3 -Monto 	$50000   // Deberia pasar sin errores.
      //================================================================================
      respuestaMovimiento = callCreaMovimientoCuenta(oConexion,cuentaUsuario,idMovimiento,0,"POSTX-10001","Primera Carga Prepago rut 1-9",new BigDecimal(45000));
      Assert.assertTrue("Id Movimiento debe ser > 0", respuestaMovimiento.getIdMovimiento() > 0);
      Assert.assertTrue("NumError = 0", respuestaMovimiento.getNumError().equals("0"));
      Assert.assertTrue("MsjError = vacio", StringUtils.isBlank(respuestaMovimiento.getMsjError()));

      System.out.println("SELECT * FROM "+schema+"."+Constants.Tables.CUENTA_ACUMULADOR.getName() +" WHERE id_cuenta = "+idCuenta+";");
      List lstAcumuladores = jdbcTempate.queryForList("SELECT * FROM "+schema+"."+Constants.Tables.CUENTA_ACUMULADOR.getName() +" WHERE id_cuenta = "+idCuenta);
      System.out.println("List Size "+lstAcumuladores.size());
      int i = 1;
      for(Object data : lstAcumuladores) {
        Map<String,Object> aData = (Map<String, Object>) data;
        System.out.println("Fila================ "+i++);
        for (String key : aData.keySet() ) {
          System.out.println(key+": "+aData.get(key)+"\n");
        }
      }

    }catch (Exception e){

    }
    finally {
      dbUtils.closeConnection(oConexion);
    }

  }


  private  Movimiento callCreaMovimientoCuenta(Connection conn,String idCuenta, int idMovimiento, int idMovimientoRef,String idExterno, String glosa, BigDecimal monto ) throws SQLException{

    Movimiento movimiento = new Movimiento();

    Object[] params = {idCuenta, idMovimiento,idMovimientoRef,idExterno,glosa,monto, new OutParam("_id_movimiento_cuenta", Types.NUMERIC), new OutParam("_numerror", Types.VARCHAR), new OutParam("_msjerror", Types.VARCHAR)};

    Map<String, Object> outputData = dbUtils.execute(conn, schema + Constants.Procedures.SP_CREA_MOVIMIENTO_CUENTA.getName(), params);

    BigDecimal id_movimiento_cuenta = (BigDecimal) outputData.get("_id_movimiento_cuenta");
    String numError = (String) outputData.get("_numerror");
    String msjError = (String) outputData.get("_msjerror");
    System.out.println("[MOVIMIENTO_CUENTA/CALL_CREA_MOVIMIENTO_CUENTA] NumError: "+numError +" MsjError: "+msjError);
    movimiento.setNumError(numError);
    movimiento.setMsjError(msjError);

    if (numError.equals("0")) {
      movimiento.setIdMovimiento(id_movimiento_cuenta.intValue());
      conn.commit();
    }
    else {
      movimiento.setIdMovimiento(0);
      conn.rollback();
    }
    return movimiento;
  }

}

class Movimiento
{
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
