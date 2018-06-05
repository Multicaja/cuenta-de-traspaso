package cl.multicaja.cuentatraspaso.test.db;

import cl.multicaja.core.utils.db.OutParam;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Map;

public class Test_20180503142212_create_sp_mc_cdt_crea_movimiento_cuenta_v10 extends TestDB{

  /***********************************************
   * NUEVO USUARIO PREPAGO
   * PRIMERA CARGA
   * VERIFICACION DE LIMITES
   * @throws SQLException
   **********************************************/
  private JdbcTemplate jdbcTempate = dbUtils.getJdbcTemplate();
  @Test
  public void nuevoUsuarioPrimeraCarga() throws SQLException {



      //================================================================================
      // CREA CUENTA
      //================================================================================
      int idFaseMovimiento = 1; // Solicitud Primera Carga

      String cuentaUsuario = "PREPAGO_"+getUniqueRutNumber();
      String descCuenta = "Nueva cuenta "+cuentaUsuario;

      Object[] params = {cuentaUsuario, descCuenta, new OutParam("_id_cuenta", Types.NUMERIC), new OutParam("_numerror", Types.VARCHAR), new OutParam("_msjerror", Types.VARCHAR)};

      Map<String, Object> outputData = dbUtils.execute( getSchema() + Constants.Procedures.SP_CREA_CUENTA.getName(), params);

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
      Movimiento respuestaMovimiento = callCreaMovimientoCuenta(cuentaUsuario,idFaseMovimiento,0,""+getUniqueLong(),"Primera Carga Prepago rut 1-9",new BigDecimal(1000),"N");
      Assert.assertTrue("Id Movimiento Cta debe ser 0", respuestaMovimiento.getIdMovimiento() == 0);
      Assert.assertTrue("NumError == 10002", respuestaMovimiento.getNumError().equals("10002"));
      Assert.assertFalse("MsjError != vacio", StringUtils.isBlank(respuestaMovimiento.getMsjError()));

      //================================================================================
      // 2 -Monto	$55000   // Deberia fallar limite Monto carga ser menor a $50.000
      //================================================================================
      respuestaMovimiento = callCreaMovimientoCuenta(cuentaUsuario,idFaseMovimiento,0,""+getUniqueLong(),"Primera Carga Prepago rut 1-9",new BigDecimal(55000),"N");
      Assert.assertTrue("Id Movimiento Cta debe ser 0", respuestaMovimiento.getIdMovimiento() == 0);
      Assert.assertTrue("NumError == 10001", respuestaMovimiento.getNumError().equals("10001"));
      Assert.assertFalse("MsjError != vacio", StringUtils.isBlank(respuestaMovimiento.getMsjError()));

      //================================================================================
      // 3 -Monto 	$45000   // Deberia pasar sin errores.
      //================================================================================
      respuestaMovimiento = callCreaMovimientoCuenta(cuentaUsuario,idFaseMovimiento,0,""+getUniqueLong(),"Primera Carga Prepago rut 1-9",new BigDecimal(45000),"N");
      Assert.assertTrue("Id Movimiento Cta debe ser > 0", respuestaMovimiento.getIdMovimiento() > 0);
      Assert.assertTrue("NumError = 0", respuestaMovimiento.getNumError().equals("0"));
      Assert.assertTrue("MsjError = vacio", StringUtils.isBlank(respuestaMovimiento.getMsjError()));

      //================================================================================
      // 4 -Monto 	$45000   // Deberia fallar xq cantidad de primeras cargas es >1
      //================================================================================
      respuestaMovimiento = callCreaMovimientoCuenta(cuentaUsuario,idFaseMovimiento,0,""+getUniqueLong(),"Primera Carga Prepago rut 1-9",new BigDecimal(45000),"N");
      Assert.assertTrue("Id Movimiento Cta debe ser 0", respuestaMovimiento.getIdMovimiento() == 0);
      Assert.assertTrue("NumError != 0", respuestaMovimiento.getNumError().equals("10003"));
      Assert.assertFalse("MsjError != vacio", StringUtils.isBlank(respuestaMovimiento.getMsjError()));

      List lstAcumuladores = getCuentaAcumulador(idCuenta);

      //================================================================================
      // 4 -Verifica los Acumuladores
      //================================================================================
      for(Object data : lstAcumuladores) {
          Map<String,Object> aData = (Map<String, Object>) data;
          System.out.println("");
          if(((String) aData.get("CODOPE")).equalsIgnoreCase("SUM")) {
            System.out.println("VERIFICA ACUMULADORES SUMA");
            Assert.assertTrue("MsjError != vacio", ((BigDecimal) aData.get("MONTO")).doubleValue() == 45000);
          }
          else if (((String) aData.get("CODOPE")).equalsIgnoreCase("COUNT")){
            System.out.println("VERIFICA ACUMULADORES COUNT");
            Assert.assertTrue("MsjError != vacio", ((BigDecimal) aData.get("MONTO")).doubleValue() == 1);
          }
        }
  }

  /***********************************************
   * USUARIO N2 PREPAGO
   * CARGA WEB
   * VERIFICACION DE LIMITES
   * @throws SQLException
   **********************************************/

  @Test
  public void usaurioN2CargaWeb() throws SQLException {

  int idFaseMovimiento = 3; // Solicitud Carga Web

    //================================================================================
    // CREA CUENTA
    //================================================================================
    String cuentaUsuario = "PREPAGO_"+getUniqueRutNumber();
    String descCuenta = "Nueva cuenta "+cuentaUsuario;

    Object[] params = {cuentaUsuario, descCuenta, new OutParam("_id_cuenta", Types.NUMERIC), new OutParam("_numerror", Types.VARCHAR), new OutParam("_msjerror", Types.VARCHAR)};

    Map<String, Object> outputData = dbUtils.execute( getSchema() + Constants.Procedures.SP_CREA_CUENTA.getName(), params);

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
    Movimiento respuestaMovimiento = callCreaMovimientoCuenta(cuentaUsuario,idFaseMovimiento,0,""+getUniqueLong(),descCuenta,new BigDecimal(2999),"N");
    Assert.assertTrue("Id Movimiento Cta debe ser 0", respuestaMovimiento.getIdMovimiento() == 0);
    Assert.assertFalse("NumError == 10009", respuestaMovimiento.getNumError().equals("10009"));
    Assert.assertFalse("MsjError != vacio", StringUtils.isBlank(respuestaMovimiento.getMsjError()));

    //================================================================================
    // 2 -Monto	$55000   // Deberia fallar limite Monto carga ser menor a $500.000
    //================================================================================
    respuestaMovimiento = callCreaMovimientoCuenta(cuentaUsuario,idFaseMovimiento,0,""+getUniqueLong(),descCuenta,new BigDecimal(500010),"N");
    Assert.assertTrue("Id Movimiento Cta debe ser 0", respuestaMovimiento.getIdMovimiento() == 0);
    Assert.assertFalse("NumError == 10008", respuestaMovimiento.getNumError().equals("10008"));
    Assert.assertFalse("MsjError != vacio", StringUtils.isBlank(respuestaMovimiento.getMsjError()));

    int iMonto = 200000;
    for(int i = 1 ; i<= 5; i++) {
      //================================================================================
      // Monto	$200.000   // Deberia Pasar OK y sumar Acumuladores
      //================================================================================
      respuestaMovimiento = callCreaMovimientoCuenta(cuentaUsuario, idFaseMovimiento, 0, ""+getUniqueLong(), descCuenta, new BigDecimal(200000),"N");
      Assert.assertTrue("Id Movimiento Cta debe ser 0", respuestaMovimiento.getIdMovimiento() > 0);
      Assert.assertTrue("NumError != 0", respuestaMovimiento.getNumError().equals("0"));
      Assert.assertTrue("MsjError != vacio", StringUtils.isBlank(respuestaMovimiento.getMsjError()));


      List lstAcumuladores =  getCuentaAcumulador(idCuenta);
      //================================================================================
      // 4 -Verifica los acumuladores luego de la primera carga
      //================================================================================
      for (Object data : lstAcumuladores) {
        Map<String, Object> aData = (Map<String, Object>) data;
        if (((String) aData.get("CODOPE")).equalsIgnoreCase("SUM")) {
          Assert.assertTrue("MsjError != vacio", ((BigDecimal) aData.get("MONTO")).doubleValue() == iMonto);
        } else if (((String) aData.get("CODOPE")).equalsIgnoreCase("COUNT")) {
          Assert.assertTrue("MsjError != vacio", ((BigDecimal) aData.get("MONTO")).doubleValue() == i);
        }
      }
      iMonto = iMonto+200000;
    }

    //================================================================================
    // 10 Deberia fallar por el limite mensual de 1000000
    //================================================================================
    respuestaMovimiento = callCreaMovimientoCuenta(cuentaUsuario,idFaseMovimiento,0,""+getUniqueLong(),descCuenta,new BigDecimal(3000),"N");
    Assert.assertTrue("Id Movimiento Cta debe ser 0", respuestaMovimiento.getIdMovimiento() == 0);
    Assert.assertTrue("NumError == 10007", respuestaMovimiento.getNumError().equals("10007"));
    Assert.assertFalse("MsjError != vacio", StringUtils.isBlank(respuestaMovimiento.getMsjError()));

  }

  /***********************************************
   * USUARIO N2 PREPAGO
   * CARGA WEB
   * VERIFICACION DE LIMITES
   * @throws SQLException
   **********************************************/
  @Test
  public void usaurioN2CargaPos() throws SQLException {

    int idFaseMovimiento = 5; // Solicitud Carga Pos

    //================================================================================
    // CREA CUENTA
    //================================================================================
    String cuentaUsuario = "PREPAGO_"+getUniqueRutNumber();
    String descCuenta = "Nueva cuenta "+cuentaUsuario;

    Object[] params = {cuentaUsuario, descCuenta, new OutParam("_id_cuenta", Types.NUMERIC), new OutParam("_numerror", Types.VARCHAR), new OutParam("_msjerror", Types.VARCHAR)};

    Map<String, Object> outputData = dbUtils.execute( getSchema() + Constants.Procedures.SP_CREA_CUENTA.getName(), params);

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
    Movimiento respuestaMovimiento = callCreaMovimientoCuenta(cuentaUsuario,idFaseMovimiento,0,""+getUniqueLong(),descCuenta,new BigDecimal(2999),"N");
    Assert.assertTrue("Id Movimiento Cta debe ser 0", respuestaMovimiento.getIdMovimiento() == 0);
    Assert.assertTrue("NumError == 10006", respuestaMovimiento.getNumError().equals("10009"));
    Assert.assertFalse("MsjError != vacio", StringUtils.isBlank(respuestaMovimiento.getMsjError()));

    //================================================================================
    // 2 -Monto	$100.000   // Deberia fallar limite Monto carga ser menor a $100.000
    //================================================================================
    respuestaMovimiento = callCreaMovimientoCuenta(cuentaUsuario,idFaseMovimiento,0,""+getUniqueLong(),descCuenta,new BigDecimal(100010),"N");
    Assert.assertTrue("Id Movimiento Cta debe ser 0", respuestaMovimiento.getIdMovimiento() == 0);
    Assert.assertTrue("NumError == 10008", respuestaMovimiento.getNumError().equals("10008"));
    Assert.assertFalse("MsjError != vacio", StringUtils.isBlank(respuestaMovimiento.getMsjError()));

    int iMonto = 100000;
    for(int i = 1; i<=10 ; i++) {
      //================================================================================
      // 3 -Monto	 $100.000 // DEBERIA PASAR (TOTAL = 1000000)
      //================================================================================
      respuestaMovimiento = callCreaMovimientoCuenta(cuentaUsuario, idFaseMovimiento, 0, ""+getUniqueLong(), descCuenta, new BigDecimal(100000),"N");
      Assert.assertTrue("Id Movimiento Cta debe ser 0", respuestaMovimiento.getIdMovimiento() > 0);
      Assert.assertTrue("NumError != 0", respuestaMovimiento.getNumError().equals("0"));
      Assert.assertTrue("MsjError != vacio", StringUtils.isBlank(respuestaMovimiento.getMsjError()));


      List lstAcumuladores = getCuentaAcumulador(idCuenta);
      //================================================================================
      // 4 -Verifica los acumuladores luego de la primera carga
      //================================================================================
      for (Object data : lstAcumuladores) {
        Map<String, Object> aData = (Map<String, Object>) data;
        if (((String) aData.get("CODOPE")).equalsIgnoreCase("SUM")) {
          Assert.assertTrue("MsjError != vacio", ((BigDecimal) aData.get("MONTO")).doubleValue() == iMonto);
        } else if (((String) aData.get("CODOPE")).equalsIgnoreCase("COUNT")) {
          Assert.assertTrue("MsjError != vacio", ((BigDecimal) aData.get("MONTO")).doubleValue() == i);
        }
      }
      iMonto = iMonto +100000;
    }

    //================================================================================
    // 3 -Deberia Fallar por Monto supera el 1000000 mensual
    //================================================================================
    respuestaMovimiento = callCreaMovimientoCuenta(cuentaUsuario, idFaseMovimiento, 0, ""+getUniqueLong(), descCuenta, new BigDecimal(30000),"N");
    Assert.assertTrue("Id Movimiento Cta debe ser 0", respuestaMovimiento.getIdMovimiento() == 0);
    Assert.assertTrue("NumError == 10010", respuestaMovimiento.getNumError().equals("10010"));
    Assert.assertFalse("MsjError != vacio", StringUtils.isBlank(respuestaMovimiento.getMsjError()));

  }

  /***********************************************
   * Verifica que no se puedan hacer dos
   * confirmaciones o reversas de una misma
   * transaccion de solicitud
   * @throws SQLException
   **********************************************/
  @Test
  public void pruebaDobleConfirmacion()throws Exception {

    int idFaseMovimiento = 3; // Solicitud Carga Web

    //================================================================================
    // CREA CUENTA
    //================================================================================
    String cuentaUsuario = "PREPAGO"+getUniqueRutNumber();
    String descCuenta = "Nueva cuenta "+cuentaUsuario;

    Object[] params = {cuentaUsuario, descCuenta, new OutParam("_id_cuenta", Types.NUMERIC), new OutParam("_numerror", Types.VARCHAR), new OutParam("_msjerror", Types.VARCHAR)};

    Map<String, Object> outputData = dbUtils.execute( getSchema() + Constants.Procedures.SP_CREA_CUENTA.getName(), params);

    BigDecimal idCuenta = (BigDecimal) outputData.get("_id_cuenta");
    String numError = (String) outputData.get("_numerror");
    String msjError = (String) outputData.get("_msjerror");
    System.out.println("[MOVIMIENTO_CUENTA/CREA_CUENTA] NumError: "+numError +" MsjError: "+msjError);

    // Pruebas de Creacion de Cuenta.
    Assert.assertTrue("[Crea Cuenta] Numero de cuenta debe ser < 0", idCuenta.intValue() > 0);
    Assert.assertTrue("[Crea Cuenta] Numero de error 0 creacion correcta", numError.equals("0"));
    Assert.assertTrue("[Crea Cuenta] Msj de error vacio creacion correcta", StringUtils.isBlank(msjError));

    //================================================================================
    // VERIFICACION DE DOBLE CONFIRMACION
    //================================================================================

    Movimiento respuestaMovimiento = callCreaMovimientoCuenta(cuentaUsuario,idFaseMovimiento,0,""+getUniqueLong(),descCuenta,new BigDecimal(45000),"N");
    Assert.assertTrue("[Movimiento 1] Id Movimiento Cta debe ser > 0", respuestaMovimiento.getIdMovimiento() > 0);
    Assert.assertTrue("[Movimiento 1] NumError == 0", respuestaMovimiento.getNumError().equals("0"));
    Assert.assertTrue("[Movimiento 1] MsjError = vacio", StringUtils.isBlank(respuestaMovimiento.getMsjError()));

    List lstAcumuladores = getCuentaAcumulador(idCuenta);

    for(Object data : lstAcumuladores) {
      Map<String,Object> aData = (Map<String, Object>) data;
      if(((String) aData.get("CODOPE")).equalsIgnoreCase("SUM")) {
        Assert.assertTrue("Monto == $45.000", Math.round(((BigDecimal)aData.get("MONTO")).doubleValue()) == 45000);
      }
      else if (((String) aData.get("CODOPE")).equalsIgnoreCase("COUNT")) {
        Assert.assertTrue("Count == 1", Math.round(((BigDecimal) aData.get("MONTO")).doubleValue()) == 1);
      }
    }

    //================================================================================
    // CREACION DE MOVIMIENTO EXITOSO
    //================================================================================

    //Movimiento correcto aceptado
    respuestaMovimiento = callCreaMovimientoCuenta(cuentaUsuario,idFaseMovimiento,0,""+getUniqueLong(),descCuenta,new BigDecimal(45000),"N");
    Assert.assertTrue("[Movimiento 2] Id Movimiento Cta debe > 0", respuestaMovimiento.getIdMovimiento() > 0);
    Assert.assertTrue("[Movimiento 2] NumError = 0", respuestaMovimiento.getNumError().equals("0"));
    Assert.assertTrue("[Movimiento 2] MsjError = vacio", StringUtils.isBlank(respuestaMovimiento.getMsjError()));

    int idMovimientoOriginal = respuestaMovimiento.getIdMovimiento();

    // CONFIRMACION DE MOVIMIENTO DEBERIA PASAR OK
    idFaseMovimiento = 4;// CONFIRMACION CARGA
    respuestaMovimiento = callCreaMovimientoCuenta(cuentaUsuario,idFaseMovimiento,idMovimientoOriginal,""+getUniqueLong(),descCuenta,new BigDecimal(45000),"N");
    Assert.assertTrue("[Confirmacion Carga] Id Movimiento Cta debe ser 0", respuestaMovimiento.getIdMovimiento() > 0);
    Assert.assertTrue("[Confirmacion Carga] NumError == 0", respuestaMovimiento.getNumError().equals("0"));
    Assert.assertTrue("[Confirmacion Carga] MsjError != vacio", StringUtils.isBlank(respuestaMovimiento.getMsjError()));

    // REVERSA DE CARGA EJECUTADA AL MISMO TIEMPO QUE LA CONFIRMACION,  DEBERIA FALLAS
    idFaseMovimiento = 7; // REVERSA CARGA
    respuestaMovimiento = callCreaMovimientoCuenta(cuentaUsuario,idFaseMovimiento,idMovimientoOriginal,""+getUniqueLong(),descCuenta,new BigDecimal(45000),"N");
    Assert.assertTrue("[Reversa Pos Confirmacion] Id Movimiento Cta debe ser 0", respuestaMovimiento.getIdMovimiento() == 0);
    Assert.assertFalse("[Reversa Pos Confirmacion] NumError != 0", respuestaMovimiento.getNumError().equals("0"));
    Assert.assertFalse("[Reversa Pos Confirmacion] MsjError != vacio", StringUtils.isBlank(respuestaMovimiento.getMsjError()));

    lstAcumuladores = getCuentaAcumulador(idCuenta);
    for(Object data : lstAcumuladores) {
      Map<String,Object> aData = (Map<String, Object>) data;
      if(((String) aData.get("CODOPE")).equalsIgnoreCase("SUM")) {
        Assert.assertTrue("Monto == $90.000", Math.round(((BigDecimal)aData.get("MONTO")).doubleValue()) == 90000);
      }
      else if (((String) aData.get("CODOPE")).equalsIgnoreCase("COUNT")) {
        Assert.assertTrue("MsjError != vacio", Math.round(((BigDecimal) aData.get("MONTO")).doubleValue()) == 2);
      }
    }

    //================================================================================
    // CREACION DE MOVIMIENTO EXITOSO
    //================================================================================
    idFaseMovimiento=3;// Carga
    //Movimiento correcto aceptado
    respuestaMovimiento = callCreaMovimientoCuenta(cuentaUsuario,idFaseMovimiento,0,""+getUniqueLong(),descCuenta,new BigDecimal(45000),"N");
    Assert.assertTrue("[Movimiento 3]  Id Movimiento Cta debe > 0", respuestaMovimiento.getIdMovimiento() > 0);
    Assert.assertTrue("[Movimiento 3]  NumError = 0", respuestaMovimiento.getNumError().equals("0"));
    Assert.assertTrue("[Movimiento 3]  MsjError = vacio", StringUtils.isBlank(respuestaMovimiento.getMsjError()));
    idMovimientoOriginal = respuestaMovimiento.getIdMovimiento();

    System.out.println("!!!!!! MOVIMIENTO ORIGINAL : "+idMovimientoOriginal);
    lstAcumuladores  = getCuentaAcumulador(idCuenta);

    for(Object data : lstAcumuladores) {
      Map<String,Object> aData = (Map<String, Object>) data;
      if(((String) aData.get("CODOPE")).equalsIgnoreCase("SUM")) {
        Assert.assertTrue("Mto debe ser 135000", Math.round(((BigDecimal)aData.get("MONTO")).doubleValue()) == 135000);
      }
      else if (((String) aData.get("CODOPE")).equalsIgnoreCase("COUNT")) {
        Assert.assertTrue("Count debe ser 3", Math.round(((BigDecimal) aData.get("MONTO")).doubleValue()) == 3);
      }
    }
    // REVERSA DE CARGA EJECUTADA  CORRECTAMENTE
    idFaseMovimiento = 7; // REVERSA CARGA
    respuestaMovimiento = callCreaMovimientoCuenta(cuentaUsuario,idFaseMovimiento,idMovimientoOriginal,""+getUniqueLong(),descCuenta,new BigDecimal(45000),"N");
    Assert.assertTrue("[REVERSA CARGA] Id Movimiento Cta debe > 0", respuestaMovimiento.getIdMovimiento() > 0);
    Assert.assertTrue("[REVERSA CARGA] NumError = 0", respuestaMovimiento.getNumError().equals("0"));
    Assert.assertTrue("[REVERSA CARGA] MsjError = vacio", StringUtils.isBlank(respuestaMovimiento.getMsjError()));

    lstAcumuladores  = getCuentaAcumulador(idCuenta);

    for(Object data : lstAcumuladores) {
      Map<String,Object> aData = (Map<String, Object>) data;
      if(((String) aData.get("CODOPE")).equalsIgnoreCase("SUM")) {
        System.out.println("Valor Acumulador = "+Math.round(((BigDecimal)aData.get("MONTO")).doubleValue()));
        Assert.assertTrue("Valor debe ser = a 90000", Math.round(((BigDecimal)aData.get("MONTO")).doubleValue()) == 90000);
      }
      else if (((String) aData.get("CODOPE")).equalsIgnoreCase("COUNT")) {
        Assert.assertTrue("Contador deberia ser = 3", Math.round(((BigDecimal) aData.get("MONTO")).doubleValue()) == 3);
      }
    }
  }
  @Test
  public void pruebaTxExternaDuplicada()throws Exception {

    int idFaseMovimiento = 3; // Solicitud Carga Web

    //================================================================================
    // CREA CUENTA
    //================================================================================
    String cuentaUsuario = "PREPAGO"+getUniqueRutNumber();
    String descCuenta = "Nueva cuenta "+cuentaUsuario;

    Object[] params = {cuentaUsuario, descCuenta, new OutParam("_id_cuenta", Types.NUMERIC), new OutParam("_numerror", Types.VARCHAR), new OutParam("_msjerror", Types.VARCHAR)};

    Map<String, Object> outputData = dbUtils.execute( getSchema() + Constants.Procedures.SP_CREA_CUENTA.getName(), params);

    BigDecimal idCuenta = (BigDecimal) outputData.get("_id_cuenta");
    String numError = (String) outputData.get("_numerror");
    String msjError = (String) outputData.get("_msjerror");
    System.out.println("[MOVIMIENTO_CUENTA/CREA_CUENTA] NumError: "+numError +" MsjError: "+msjError);

    // Pruebas de Creacion de Cuenta.
    Assert.assertTrue("[Crea Cuenta] Numero de cuenta debe ser < 0", idCuenta.intValue() > 0);
    Assert.assertTrue("[Crea Cuenta] Numero de error 0 creacion correcta", numError.equals("0"));
    Assert.assertTrue("[Crea Cuenta] Msj de error vacio creacion correcta", StringUtils.isBlank(msjError));

    //================================================================================
    // VERIFICACION TX DUPLICADA
    //================================================================================

    Movimiento respuestaMovimiento = callCreaMovimientoCuenta(cuentaUsuario,idFaseMovimiento,0,"TX123",descCuenta,new BigDecimal(45000),"N");
    Assert.assertTrue("Id Movimiento Cta debe ser > 0", respuestaMovimiento.getIdMovimiento() > 0);
    Assert.assertTrue("NumError == 0", respuestaMovimiento.getNumError().equals("0"));
    Assert.assertTrue("sjError = vacio", StringUtils.isBlank(respuestaMovimiento.getMsjError()));

    List lstAcumuladores = getCuentaAcumulador(idCuenta);

    for(Object data : lstAcumuladores) {
      Map<String,Object> aData = (Map<String, Object>) data;
      if(((String) aData.get("CODOPE")).equalsIgnoreCase("SUM")) {
        Assert.assertTrue("Monto == $45.000", Math.round(((BigDecimal)aData.get("MONTO")).doubleValue()) == 45000);
      }
      else if (((String) aData.get("CODOPE")).equalsIgnoreCase("COUNT")) {
        Assert.assertTrue("Count == 1", Math.round(((BigDecimal) aData.get("MONTO")).doubleValue()) == 1);
      }
    }

    respuestaMovimiento = callCreaMovimientoCuenta(cuentaUsuario,idFaseMovimiento,0,"TX123",descCuenta,new BigDecimal(45000),"N");
    Assert.assertNotNull("Debe traer respuesta.",respuestaMovimiento);
    Assert.assertTrue("Id Movimiento Cta debe ser > 0", respuestaMovimiento.getIdMovimiento() == 0);
    Assert.assertFalse("NumError != 0", respuestaMovimiento.getNumError().equals("0"));
    Assert.assertFalse("MsjError = vacio", StringUtils.isBlank(respuestaMovimiento.getMsjError()));
  }


  /****
   * Teste encargado de verificar que la simulacion no afecte los acumuladores.
   */
  @Test
  public void verificaSimulacion() throws SQLException {


    //================================================================================
    // CREA CUENTA
    //================================================================================
    String cuentaUsuario = "PREPAGO"+getUniqueRutNumber();
    String descCuenta = "Nueva cuenta "+cuentaUsuario;

    Object[] params = {cuentaUsuario, descCuenta, new OutParam("_id_cuenta", Types.NUMERIC), new OutParam("_numerror", Types.VARCHAR), new OutParam("_msjerror", Types.VARCHAR)};

    Map<String, Object> outputData = dbUtils.execute( getSchema() + Constants.Procedures.SP_CREA_CUENTA.getName(), params);

    BigDecimal idCuenta = (BigDecimal) outputData.get("_id_cuenta");
    String numError = (String) outputData.get("_numerror");
    String msjError = (String) outputData.get("_msjerror");
    System.out.println("[MOVIMIENTO_CUENTA/CREA_CUENTA] NumError: "+numError +" MsjError: "+msjError);

    // Pruebas de Creacion de Cuenta.
    Assert.assertTrue("[Crea Cuenta] Numero de cuenta debe ser < 0", idCuenta.intValue() > 0);
    Assert.assertTrue("[Crea Cuenta] Numero de error 0 creacion correcta", numError.equals("0"));
    Assert.assertTrue("[Crea Cuenta] Msj de error vacio creacion correcta", StringUtils.isBlank(msjError));


    int idFaseMovimiento = 3; // Retiro POS
    Movimiento respuestaMovimiento;

    int iMonto = 0;
    for(int i = 1; i<=8 ; i++) {

      iMonto = iMonto +100000;
      //================================================================================
      // 3 -Monto	 $100.000 //
      //================================================================================
      respuestaMovimiento = callCreaMovimientoCuenta(cuentaUsuario, idFaseMovimiento, 0, ""+getUniqueLong(), descCuenta, new BigDecimal(100000),"N");
      Assert.assertTrue("Id Movimiento Cta debe ser 0", respuestaMovimiento.getIdMovimiento() > 0);
      Assert.assertTrue("NumError != 0", respuestaMovimiento.getNumError().equals("0"));
      Assert.assertTrue("MsjError != vacio", StringUtils.isBlank(respuestaMovimiento.getMsjError()));


      List lstAcumuladores = getCuentaAcumulador(idCuenta);
      //================================================================================
      // 4 -Verifica los acumuladores luego de la carga
      //================================================================================
      for (Object data : lstAcumuladores) {
        Map<String, Object> aData = (Map<String, Object>) data;
        if (((String) aData.get("CODOPE")).equalsIgnoreCase("SUM")) {
          Assert.assertTrue("Monto debe ser igual a", ((BigDecimal) aData.get("MONTO")).doubleValue() == iMonto);
        } else if (((String) aData.get("CODOPE")).equalsIgnoreCase("COUNT")) {
          Assert.assertTrue("La Cantidad debe ser igual a", ((BigDecimal) aData.get("MONTO")).doubleValue() == i);
        }
      }
    }
    //================================================================================
    //  PRUEBA DE CARGA SIMULADA ( SIN PROBLEMA DE LIMITE)
    //================================================================================
    for(int i = 0; i<=3; i++) {
      idFaseMovimiento  = 3;

      respuestaMovimiento = callCreaMovimientoCuenta(cuentaUsuario, idFaseMovimiento, 0, ""+getUniqueLong(), descCuenta, new BigDecimal(100000),"S");
      Assert.assertTrue("Id Movimiento Cta debe ser 0", respuestaMovimiento.getIdMovimiento() == 0);
      Assert.assertTrue("NumError = 0", respuestaMovimiento.getNumError().equals("0"));
      Assert.assertTrue("MsjError = vacio", StringUtils.isBlank(respuestaMovimiento.getMsjError()));

      List lstAcumuladores = getCuentaAcumulador(idCuenta);
      //========================================================================================
      // 4 -Verifica los acumuladores luego de la carga SIMULADA, NO DEBERIA SUMAR ACUMULADORES
      //========================================================================================
      for (Object data : lstAcumuladores) {
        Map<String, Object> aData = (Map<String, Object>) data;
        if (((String) aData.get("CODOPE")).equalsIgnoreCase("SUM")) {
          Assert.assertTrue("Monto debe ser igual a", ((BigDecimal) aData.get("MONTO")).doubleValue() == 800000);
        } else if (((String) aData.get("CODOPE")).equalsIgnoreCase("COUNT")) {
          Assert.assertTrue("La Cantidad debe ser igual a", ((BigDecimal) aData.get("MONTO")).doubleValue() == 8);
        }
      }
    }
    //================================================================================
    //  PRUEBA DE CARGA SIMULADA ( CON PROBLEMA DE LIMITE)
    //================================================================================
    respuestaMovimiento = callCreaMovimientoCuenta(cuentaUsuario, idFaseMovimiento, 0, ""+getUniqueLong(), descCuenta, new BigDecimal(300000),"S");
    Assert.assertTrue("Id Movimiento Cta debe ser 0", respuestaMovimiento.getIdMovimiento() == 0);
    Assert.assertFalse("NumError != 0", respuestaMovimiento.getNumError().equals("0"));
    Assert.assertFalse("MsjError != vacio", StringUtils.isBlank(respuestaMovimiento.getMsjError()));

    List lstAcumuladores = getCuentaAcumulador(idCuenta);
    //========================================================================================
    // 4 -Verifica los acumuladores luego de la carga SIMULADA, NO DEBERIA SUMAR ACUMULADORES
    //========================================================================================
    for (Object data : lstAcumuladores) {
      Map<String, Object> aData = (Map<String, Object>) data;
      if (((String) aData.get("CODOPE")).equalsIgnoreCase("SUM")) {
        Assert.assertTrue("Monto debe ser igual a", ((BigDecimal) aData.get("MONTO")).doubleValue() == 800000);
      } else if (((String) aData.get("CODOPE")).equalsIgnoreCase("COUNT")) {
        Assert.assertTrue("La Cantidad debe ser igual a", ((BigDecimal) aData.get("MONTO")).doubleValue() == 8);
      }
    }

  }



  private List getCuentaAcumulador(BigDecimal idCuenta) {
    return jdbcTempate.queryForList(
      " SELECT " +
        "     CAC.id AS ID, " +
        "     CAC.monto AS MONTO , "+
        "     RAC.codigo_operacion AS CODOPE "+
        " FROM " +
        "   "+getSchema()+"."+Constants.Tables.CUENTA_ACUMULADOR.getName() +" CAC "+
        " INNER JOIN "+getSchema()+"."+Constants.Tables.REGLA_ACUMULACION.getName()+" RAC ON CAC.id_regla_acumulacion = RAC.id "+
        " WHERE " +
        " id_cuenta = "+idCuenta
    );
  }

  private  Movimiento callCreaMovimientoCuenta(String idCuenta, int idFaseMovimiento, int idFaseMovimientoRef,String idExterno, String glosa, BigDecimal monto ,String indSimulacion) throws SQLException{
    Movimiento movimiento = new Movimiento();
    Object[] params = {idCuenta, idFaseMovimiento,idFaseMovimientoRef,idExterno,glosa,monto, indSimulacion,new OutParam("_id_movimiento_cuenta", Types.NUMERIC), new OutParam("_numerror", Types.VARCHAR), new OutParam("_msjerror", Types.VARCHAR)};

    Map<String, Object> outputData = dbUtils.execute( getSchema() + Constants.Procedures.SP_CREA_MOVIMIENTO_CUENTA.getName(), params);

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


class Movimiento {

  private int idFaseMovimiento;
  private String numError;
  private String msjError;

  public int getIdMovimiento() {
    return idFaseMovimiento;
  }

  public void setIdMovimiento(int idFaseMovimiento) {
    this.idFaseMovimiento = idFaseMovimiento;
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
