package cl.multicaja.cuentatraspaso.test.db;

import cl.multicaja.core.utils.KeyValue;
import cl.multicaja.core.utils.db.OutParam;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Map;

public class Test_20180503142212_create_sp_mc_cdt_crea_movimiento_cuenta_v10 extends TestDB{


  private JdbcTemplate jdbcTempate = dbUtils.getJdbcTemplate();

  /***********************************************
   * NUEVO USUARIO PREPAGO
   * PRIMERA CARGA
   * VERIFICACION DE LIMITES
   * @throws SQLException
   **********************************************/
  @Test
  public void nuevoUsuarioPrimeraCarga() throws SQLException {
      Movimiento movimiento;
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
      //Movimiento(Integer idFaseMovimiento, String idCuenta, Integer idMovimientoRef, String idExterno, String glosa, BigDecimal monto, boolean bSimulacion)
      movimiento = new Movimiento(1,cuentaUsuario,0,getRandomNumericString(20),"Solicitud primera carga",new BigDecimal(1000),false);
      movimiento = callCreaMovimientoCuenta(movimiento);
      Assert.assertTrue("Id Movimiento Cta debe ser 0", movimiento.getIdMovimientoRef() == 0);
      Assert.assertTrue("NumError == 108203", movimiento.getNumError().equals("108203"));
      Assert.assertFalse("MsjError != vacio", StringUtils.isBlank(movimiento.getMsjError()));

      //================================================================================
      // 2 -Monto	$55000   // Deberia fallar limite Monto carga ser menor a $50.000
      //================================================================================
      movimiento.setIdMovimientoRef(0);
      movimiento.setMonto(new BigDecimal(55000));
      movimiento = callCreaMovimientoCuenta(movimiento);
      Assert.assertTrue("Id Movimiento Cta debe ser 0", movimiento.getIdMovimientoRef() == 0);
      Assert.assertTrue("NumError == 108206", movimiento.getNumError().equals("108206"));
      Assert.assertFalse("MsjError != vacio", StringUtils.isBlank(movimiento.getMsjError()));

      //================================================================================
      // 3 -Monto 	$45000   // Deberia pasar sin errores.
      //================================================================================
      movimiento.setIdMovimientoRef(0);
      movimiento.setMonto(new BigDecimal(45000));
      movimiento = callCreaMovimientoCuenta(movimiento);
      Assert.assertTrue("Id Movimiento Cta debe ser > 0", movimiento.getIdMovimientoRef() > 0);
      Assert.assertTrue("NumError = 0", movimiento.getNumError().equals("0"));
      Assert.assertTrue("MsjError = vacio", StringUtils.isBlank(movimiento.getMsjError()));
      // CONFIRMACION PRIMERA CARGA
      movimiento.setIdFaseMovimiento(2);
      movimiento.setIdExterno(movimiento.getIdExterno()+"_CONF");
      movimiento.setGlosa("Confirmacion Primera Carga");
      movimiento = callCreaMovimientoCuenta(movimiento);
      Assert.assertTrue("Id Movimiento Cta debe ser > 0", movimiento.getIdMovimientoRef() > 0);
      Assert.assertTrue("NumError = 0", movimiento.getNumError().equals("0"));
      Assert.assertTrue("MsjError = vacio", StringUtils.isBlank(movimiento.getMsjError()));

      //================================================================================
      // 4 -Monto 	$45000   // Deberia fallar xq cantidad de primeras cargas es >1
      //================================================================================
      movimiento = new Movimiento(1,cuentaUsuario,0,getRandomNumericString(20),"Solicitud primera carga",new BigDecimal(3000),false);
      movimiento = callCreaMovimientoCuenta(movimiento);

      Assert.assertTrue("Id Movimiento Cta debe ser 0", movimiento.getIdMovimientoRef() == 0);
      Assert.assertTrue("NumError != 0", movimiento.getNumError().equals("108001"));
      Assert.assertFalse("MsjError != vacio", StringUtils.isBlank(movimiento.getMsjError()));

      // Verificar Acumuladores Primera Carga
      verificaAcumuladores(idCuenta.longValue(),1,45000L,1L,0L);
  }

  /***********************************************
   * USUARIO N2 PREPAGO
   * CARGA WEB
   * VERIFICACION DE LIMITES
   * @throws SQLException
   **********************************************/
  @Test
  public void usaurioN2CargaWeb() throws SQLException {

     Movimiento movimiento;
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
    movimiento = new Movimiento(3,cuentaUsuario,0,getRandomNumericString(20),"Solicitud de carga WEB",new BigDecimal(2999),false);
    movimiento = callCreaMovimientoCuenta(movimiento);
    Assert.assertTrue("Id Movimiento Cta debe ser 0", movimiento.getIdMovimientoRef() == 0);
    Assert.assertFalse("NumError == 10009", movimiento.getNumError().equals("10009"));
    Assert.assertFalse("MsjError != vacio", StringUtils.isBlank(movimiento.getMsjError()));

    //================================================================================
    // 2 -Monto	$55000   // Deberia fallar limite Monto carga ser menor a $500.000
    //================================================================================
    movimiento.setMonto(new BigDecimal(500010));
    movimiento = callCreaMovimientoCuenta(movimiento);
    Assert.assertTrue("Id Movimiento Cta debe ser 0", movimiento.getIdMovimientoRef() == 0);
    Assert.assertFalse("NumError == 10008", movimiento.getNumError().equals("10008"));
    Assert.assertFalse("MsjError != vacio", StringUtils.isBlank(movimiento.getMsjError()));

    int iMonto = 200000;
    for(int i = 1 ; i<= 5; i++) {
      //================================================================================
      // Monto	$200.000   // Deberia Pasar OK y sumar Acumuladores (CARGA)
      //================================================================================
      movimiento = new Movimiento(3,cuentaUsuario,0,getRandomNumericString(20),"Solicitud de carga WEB",new BigDecimal(200000),false);
      movimiento = callCreaMovimientoCuenta(movimiento);
      Assert.assertTrue("Id Movimiento Cta debe ser 0", movimiento.getIdMovimientoRef() > 0);
      Assert.assertTrue("NumError != 0", movimiento.getNumError().equals("0"));
      Assert.assertTrue("MsjError != vacio", StringUtils.isBlank(movimiento.getMsjError()));

      //================================================================================
      // Monto	$200.000   // Deberia Pasar OK y sumar Acumuladores (CONFIRMACION)
      //================================================================================
      movimiento.setIdFaseMovimiento(4);
      movimiento.setIdExterno(movimiento.getIdExterno()+"_CONF");
      movimiento = callCreaMovimientoCuenta(movimiento);
      Assert.assertTrue("Id Movimiento Cta debe ser 0", movimiento.getIdMovimientoRef() > 0);
      Assert.assertTrue("NumError != 0", movimiento.getNumError().equals("0"));
      Assert.assertTrue("MsjError != vacio", StringUtils.isBlank(movimiento.getMsjError()));

      verificaAcumuladores(idCuenta.longValue(),3,iMonto,i,0);

      iMonto = iMonto+200000;
    }

    //================================================================================
    // 10 Deberia fallar por el limite mensual de 1000000
    //================================================================================
    movimiento.setIdFaseMovimiento(3);
    movimiento.setIdMovimientoRef(0);
    movimiento.setMonto(new BigDecimal(3000));
    movimiento.setIdExterno(getRandomNumericString(20));
    movimiento = callCreaMovimientoCuenta(movimiento);

    Assert.assertTrue("Id Movimiento Cta debe ser 0", movimiento.getIdMovimientoRef() == 0);
    Assert.assertTrue("NumError == 108204", movimiento.getNumError().equals("108204"));
    Assert.assertFalse("MsjError != vacio", StringUtils.isBlank(movimiento.getMsjError()));

  }

  /***********************************************
   * USUARIO N2 PREPAGO
   * CARGA WEB
   * VERIFICACION DE LIMITES
   * @throws SQLException
   **********************************************/
  @Test
  public void usaurioN2CargaPos() throws SQLException {

    Movimiento movimiento;
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
    movimiento = new Movimiento(5,cuentaUsuario,0,getRandomNumericString(20),"Solicitud de carga",new BigDecimal(2999),false);
    movimiento = callCreaMovimientoCuenta(movimiento);
    Assert.assertTrue("Id Movimiento Cta debe ser 0", movimiento.getIdMovimientoRef() == 0);
    Assert.assertTrue("NumError == 108203", movimiento.getNumError().equals("108203"));
    Assert.assertFalse("MsjError != vacio", StringUtils.isBlank(movimiento.getMsjError()));

    //================================================================================
    // 2 -Monto	$100.000   // Deberia fallar limite Monto carga ser menor a $100.000
    //================================================================================
    movimiento.setMonto(new BigDecimal(100010));
    movimiento = callCreaMovimientoCuenta(movimiento);
    Assert.assertTrue("Id Movimiento Cta debe ser 0", movimiento.getIdMovimientoRef() == 0);
    Assert.assertTrue("NumError == 108202", movimiento.getNumError().equals("108202"));
    Assert.assertFalse("MsjError != vacio", StringUtils.isBlank(movimiento.getMsjError()));

    int iMonto = 100000;
    for(int i = 1; i<=10 ; i++) {
      //================================================================================
      // 3 -Monto	 $100.000 // DEBERIA PASAR (TOTAL = 1000000) (SOLICITUD)
      //================================================================================
      movimiento = new Movimiento(5,cuentaUsuario,0,getRandomNumericString(20),"Solicitud de carga",new BigDecimal(100000),false);
      movimiento = callCreaMovimientoCuenta(movimiento);
      //respuestaMovimiento = callCreaMovimientoCuenta(cuentaUsuario, idFaseMovimiento, 0, ""+getUniqueLong(), descCuenta, ,"N");
      Assert.assertTrue("Id Movimiento Cta debe ser 0", movimiento.getIdMovimientoRef() > 0);
      Assert.assertTrue("NumError != 0", movimiento.getNumError().equals("0"));
      Assert.assertTrue("MsjError != vacio", StringUtils.isBlank(movimiento.getMsjError()));
      // CONFIRMACION SOLICITUD
      movimiento.setIdFaseMovimiento(6);
      movimiento.setIdExterno(movimiento.getIdExterno()+"_CONF");
      movimiento = callCreaMovimientoCuenta(movimiento);
      Assert.assertTrue("Id Movimiento Cta debe ser 0", movimiento.getIdMovimientoRef() > 0);
      Assert.assertTrue("NumError != 0", movimiento.getNumError().equals("0"));
      Assert.assertTrue("MsjError != vacio", StringUtils.isBlank(movimiento.getMsjError()));

      verificaAcumuladores(idCuenta.longValue(),5,iMonto,i,0);

      iMonto = iMonto +100000;
    }

    //================================================================================
    // 3 -Deberia Fallar por Monto supera el 1000000 mensual
    //================================================================================
    movimiento = new Movimiento(5,cuentaUsuario,0,getRandomNumericString(20),"Solicitud de carga",new BigDecimal(30000),false);
    movimiento = callCreaMovimientoCuenta(movimiento);
    Assert.assertTrue("Id Movimiento Cta debe ser 0", movimiento.getIdMovimientoRef()== 0);
    Assert.assertTrue("NumError == 108204", movimiento.getNumError().equals("108204"));
    Assert.assertFalse("MsjError != vacio", StringUtils.isBlank(movimiento.getMsjError()));

  }




  /***********************************************
   * Verifica que no se puedan hacer dos
   * confirmaciones o reversas de una misma
   * transaccion de solicitud
   * @throws SQLException
   **********************************************/
  @Test
  public void pruebaDobleConfirmacion()throws Exception {

    Movimiento movimiento;
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
    movimiento = new Movimiento(3,cuentaUsuario,0,getRandomNumericString(20),"Solicitud de carga",new BigDecimal(45000),false);
    movimiento = callCreaMovimientoCuenta(movimiento);
    Assert.assertTrue("[Movimiento 1] Id Movimiento Cta debe ser > 0", movimiento.getIdMovimientoRef() > 0);
    Assert.assertTrue("[Movimiento 1] NumError == 0", movimiento.getNumError().equals("0"));
    Assert.assertTrue("[Movimiento 1] MsjError = vacio", StringUtils.isBlank(movimiento.getMsjError()));
    int idMovimientoOriginal = movimiento.getIdMovimientoRef();
    // CONFIRMACION DE MOVIMIENTO DEBERIA PASAR OK
    movimiento.setIdFaseMovimiento(4);// CONFIRMACION CARGA
    movimiento.setIdExterno(movimiento.getIdExterno()+"_CONF");
    movimiento = callCreaMovimientoCuenta(movimiento);
    Assert.assertTrue("[Confirmacion Carga] Id Movimiento Cta debe ser 0", movimiento.getIdMovimientoRef()> 0);
    Assert.assertTrue("[Confirmacion Carga] NumError == 0", movimiento.getNumError().equals("0"));
    Assert.assertTrue("[Confirmacion Carga] MsjError != vacio", StringUtils.isBlank(movimiento.getMsjError()));
    verificaAcumuladores(idCuenta.longValue(),3,45000,1,0);

    // REVERSA DE CARGA EJECUTADA AL MISMO TIEMPO QUE LA CONFIRMACION,  DEBERIA FALLAS
    movimiento = new Movimiento(7,cuentaUsuario,idMovimientoOriginal,getRandomNumericString(20),"REVERSA ",new BigDecimal(45000),false);
    movimiento = callCreaMovimientoCuenta(movimiento);
    Assert.assertTrue("[Reversa Pos Confirmacion] Id Movimiento Cta debe ser 0", movimiento.getIdMovimientoRef() == 0);
    Assert.assertFalse("[Reversa Pos Confirmacion] NumError != 0", movimiento.getNumError().equals("0"));
    Assert.assertFalse("[Reversa Pos Confirmacion] MsjError != vacio", StringUtils.isBlank(movimiento.getMsjError()));

    verificaAcumuladores(idCuenta.longValue(),3,45000,1,0);

  }

  @Test
  public void pruebaTxExternaDuplicada()throws Exception {

    Movimiento movimiento;
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
    // VERIFICACION TX DUPLICADA (SOLICITUD)
    //================================================================================
    movimiento = new Movimiento(3,cuentaUsuario,0,"TX123","Solicitud de carga",new BigDecimal(45000),false);
    movimiento = callCreaMovimientoCuenta(movimiento);
    Assert.assertTrue("Id Movimiento Cta debe ser > 0", movimiento.getIdMovimientoRef() > 0);
    Assert.assertTrue("NumError == 0", movimiento.getNumError().equals("0"));
    Assert.assertTrue("sjError = vacio", StringUtils.isBlank(movimiento.getMsjError()));

    //================================================================================
    // VERIFICACION TX DUPLICADA (CONFIRMACION)
    //================================================================================
    movimiento.setIdExterno(movimiento.getIdExterno()+"_CONF");
    movimiento.setIdFaseMovimiento(4);
    movimiento = callCreaMovimientoCuenta(movimiento);
    Assert.assertTrue("Id Movimiento Cta debe ser > 0", movimiento.getIdMovimientoRef() > 0);
    Assert.assertTrue("NumError == 0", movimiento.getNumError().equals("0"));
    Assert.assertTrue("sjError = vacio", StringUtils.isBlank(movimiento.getMsjError()));

    // VERIFICA QUE LOS ACUMULADORES SUMARON
    verificaAcumuladores(idCuenta.longValue(),3,45000,1,0);

    movimiento = new Movimiento(3,cuentaUsuario,0,"TX123","Solicitud de carga",new BigDecimal(45000),false);
    movimiento = callCreaMovimientoCuenta(movimiento);
    Assert.assertNotNull("Debe traer respuesta.",movimiento);
    Assert.assertTrue("Id Movimiento Cta debe ser > 0", movimiento.getIdMovimientoRef() == 0);
    Assert.assertFalse("NumError != 0", movimiento.getNumError().equals("0"));
    Assert.assertFalse("MsjError = vacio", StringUtils.isBlank(movimiento.getMsjError()));

    // VERIFICA QUE LOS ACUMULADORES NO SUMARON
    verificaAcumuladores(idCuenta.longValue(),3,45000,1,0);

  }


  /****
   * Teste encargado de verificar que la simulacion no afecte los acumuladores.
   */
  @Test
  public void verificaSimulacion() throws SQLException {

    Movimiento movimiento;
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
    // SOLICITUD CARGA
    //================================================================================
    movimiento = new Movimiento(3,cuentaUsuario,0,getRandomNumericString(20),"Solicitud de carga",new BigDecimal(45000),false);
    movimiento = callCreaMovimientoCuenta(movimiento);
    Assert.assertTrue("Id Movimiento Cta debe ser > 0", movimiento.getIdMovimientoRef() > 0);
    Assert.assertTrue("NumError == 0", movimiento.getNumError().equals("0"));
    Assert.assertTrue("sjError = vacio", StringUtils.isBlank(movimiento.getMsjError()));

    //================================================================================
    // CONFIRMACION CARGA
    //================================================================================
    movimiento.setIdExterno(movimiento.getIdExterno()+"_CONF");
    movimiento.setIdFaseMovimiento(4);
    movimiento = callCreaMovimientoCuenta(movimiento);
    Assert.assertTrue("Id Movimiento Cta debe ser > 0", movimiento.getIdMovimientoRef() > 0);
    Assert.assertTrue("NumError == 0", movimiento.getNumError().equals("0"));
    Assert.assertTrue("sjError = vacio", StringUtils.isBlank(movimiento.getMsjError()));

    // VERIFICA QUE LOS ACUMULADORES SUMARON
    verificaAcumuladores(idCuenta.longValue(),3,45000,1,0);

    //================================================================================
    //  PRUEBA DE CARGA SIMULADA ( CON PROBLEMA DE LIMITE)
    //================================================================================
    movimiento = new Movimiento(3,cuentaUsuario,0,getRandomNumericString(20),"Solicitud de carga",new BigDecimal(999999),true);
    movimiento = callCreaMovimientoCuenta(movimiento);
    Assert.assertTrue("Id Movimiento Cta debe ser 0", movimiento.getIdMovimientoRef()== 0);
    Assert.assertFalse("NumError != 0", movimiento.getNumError().equals("0"));
    Assert.assertFalse("MsjError != vacio", StringUtils.isBlank(movimiento.getMsjError()));

    // VERIFICA QUE LOS ACUMULADORES NO SUMARON (SIMULACION OK)
    verificaAcumuladores(idCuenta.longValue(),3,45000,1,0);

    //================================================================================
    //  PRUEBA DE CARGA SIMULADA ( SIN  PROBLEMA DE LIMITE)
    //================================================================================
    movimiento = new Movimiento(3,cuentaUsuario,0,getRandomNumericString(20),"Solicitud de carga",new BigDecimal(10000),true);
    movimiento = callCreaMovimientoCuenta(movimiento);
    Assert.assertTrue("Id Movimiento Cta debe ser 0", movimiento.getIdMovimientoRef()== 0);
    Assert.assertTrue("NumError != 0", movimiento.getNumError().equals("0"));
    Assert.assertTrue("MsjError != vacio", StringUtils.isBlank(movimiento.getMsjError()));

    // VERIFICA QUE LOS ACUMULADORES NO SUMARON (SIMULACION OK)
    verificaAcumuladores(idCuenta.longValue(),3,45000,1,0);


  }

  @Test
  public void retiroPos() throws SQLException {

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

    Movimiento movimiento;

    int iMonto = 100000;
    for(int i = 1 ; i<= 5; i++) {
      //================================================================================
      // Monto	$200.000   // Deberia Pasar OK y sumar Acumuladores (CARGA)
      //================================================================================
      movimiento = new Movimiento(3,cuentaUsuario,0,getRandomNumericString(20),"Solicitud de carga WEB",new BigDecimal(100000),false);
      movimiento = callCreaMovimientoCuenta(movimiento);
      Assert.assertTrue("Id Movimiento Cta debe ser 0", movimiento.getIdMovimientoRef() > 0);
      Assert.assertTrue("NumError != 0", movimiento.getNumError().equals("0"));
      Assert.assertTrue("MsjError != vacio", StringUtils.isBlank(movimiento.getMsjError()));

      //================================================================================
      // Monto	$200.000   // Deberia Pasar OK y sumar Acumuladores (CONFIRMACION)
      //================================================================================
      movimiento.setIdFaseMovimiento(4);
      movimiento.setIdExterno(movimiento.getIdExterno()+"_CONF");
      movimiento = callCreaMovimientoCuenta(movimiento);
      Assert.assertTrue("Id Movimiento Cta debe ser 0", movimiento.getIdMovimientoRef() > 0);
      Assert.assertTrue("NumError != 0", movimiento.getNumError().equals("0"));
      Assert.assertTrue("MsjError != vacio", StringUtils.isBlank(movimiento.getMsjError()));

      verificaAcumuladores(idCuenta.longValue(),3,iMonto,i,0);

      iMonto = iMonto+100000;
    }

    //================================================================================
    //PRIMER RETIRO
    //================================================================================

    movimiento = new Movimiento(10,cuentaUsuario,0,getRandomNumericString(20),"Solicitud de retiro WEB",new BigDecimal(10000),false);
    movimiento = callCreaMovimientoCuenta(movimiento);
    Assert.assertTrue("Id Movimiento Cta debe ser 0", movimiento.getIdMovimientoRef() > 0);
    Assert.assertTrue("NumError != 0", movimiento.getNumError().equals("0"));
    Assert.assertTrue("MsjError != vacio", StringUtils.isBlank(movimiento.getMsjError()));

    //================================================================================
    // PRIMERA CONFIRMACION RETIRO
    //================================================================================
    movimiento.setIdFaseMovimiento(11);
    movimiento.setIdExterno(movimiento.getIdExterno()+"_CONF");
    movimiento = callCreaMovimientoCuenta(movimiento);
    Assert.assertTrue("Id Movimiento Cta debe ser 0", movimiento.getIdMovimientoRef() > 0);
    Assert.assertTrue("NumError != 0", movimiento.getNumError().equals("0"));
    Assert.assertTrue("MsjError != vacio", StringUtils.isBlank(movimiento.getMsjError()));

    //================================================================================
    //SEGUNDO RETIRO
    //================================================================================

    movimiento = new Movimiento(10,cuentaUsuario,0,getRandomNumericString(20),"Solicitud de retiro WEB",new BigDecimal(20000),false);
    movimiento = callCreaMovimientoCuenta(movimiento);
    Assert.assertTrue("Id Movimiento Cta debe ser 0", movimiento.getIdMovimientoRef() > 0);
    Assert.assertTrue("NumError != 0", movimiento.getNumError().equals("0"));
    Assert.assertTrue("MsjError != vacio", StringUtils.isBlank(movimiento.getMsjError()));

    //================================================================================
    // SEGUNDA CONFIRMACION RETIRO
    //================================================================================
    movimiento.setIdFaseMovimiento(11);
    movimiento.setIdExterno(movimiento.getIdExterno()+"_CONF");
    movimiento = callCreaMovimientoCuenta(movimiento);
    Assert.assertTrue("Id Movimiento Cta debe ser 0", movimiento.getIdMovimientoRef() > 0);
    Assert.assertTrue("NumError != 0", movimiento.getNumError().equals("0"));
    Assert.assertTrue("MsjError != vacio", StringUtils.isBlank(movimiento.getMsjError()));
    // TOTAL DE RETIROS
    verificaAcumuladores(idCuenta.longValue(),10,30000,2,0);

  }


  /***********************************************
   * Verifica que se haga una reversa
   * confirmaciones o reversas de una misma
   * transaccion de solicitud
   * @throws SQLException
   **********************************************/
  @Test
  public void pruebaReversa()throws Exception {

    Movimiento movimiento;
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
    movimiento = new Movimiento(3,cuentaUsuario,0,getRandomNumericString(20),"Solicitud de carga",new BigDecimal(45000),false);
    movimiento = callCreaMovimientoCuenta(movimiento);
    Assert.assertTrue("[Movimiento 1] Id Movimiento Cta debe ser > 0", movimiento.getIdMovimientoRef() > 0);
    Assert.assertTrue("[Movimiento 1] NumError == 0", movimiento.getNumError().equals("0"));
    Assert.assertTrue("[Movimiento 1] MsjError = vacio", StringUtils.isBlank(movimiento.getMsjError()));
    verificaAcumuladores(idCuenta.longValue(),3,45000,1,45000);

    // REVERSA DE CARGA EJECUTADA AL MISMO TIEMPO QUE LA CONFIRMACION,  DEBERIA FALLAS
    movimiento.setIdExterno(movimiento.getIdExterno()+"_REVERSA");
    movimiento.setGlosa("Reversa Recarga");
    movimiento.setIdFaseMovimiento(7);
    movimiento = callCreaMovimientoCuenta(movimiento);
    Assert.assertTrue("[Movimiento 1] Id Movimiento Cta debe ser > 0", movimiento.getIdMovimientoRef() > 0);
    Assert.assertTrue("[Movimiento 1] NumError == 0", movimiento.getNumError().equals("0"));
    Assert.assertTrue("[Movimiento 1] MsjError = vacio", StringUtils.isBlank(movimiento.getMsjError()));
    verificaAcumuladores(idCuenta.longValue(),3,0,0,0);

  }

  private void verificaAcumuladores(long idCuenta,long idFaseMovimiento,long montoSuma,long countMovimientos, long montoTx) {
    List lstAcumuladores = getCuentaAcumulador(idCuenta,idFaseMovimiento);
    //================================================================================
    // 4 -Verifica los Acumuladores
    //================================================================================
    for(Object data : lstAcumuladores) {
      Map<String,Object> aData = (Map<String, Object>) data;
      System.out.println("");//PERIOCIDAD
      if(((String) aData.get("CODOPE")).equalsIgnoreCase("SUM") && !((String) aData.get("PERIOCIDAD")).equalsIgnoreCase("VIDA")){
        System.out.println("VERIFICA ACUMULADORES SUMA");
        Assert.assertTrue("MsjError != vacio", ((BigDecimal) aData.get("MONTO")).doubleValue() == montoSuma);
      }
      else if (((String) aData.get("CODOPE")).equalsIgnoreCase("COUNT")){
        System.out.println("VERIFICA ACUMULADORES COUNT");
        Assert.assertTrue("MsjError != vacio", ((BigDecimal) aData.get("MONTO")).doubleValue() == countMovimientos);
      }
      else if(((String) aData.get("CODOPE")).equalsIgnoreCase("SUM") || ((String) aData.get("PERIOCIDAD")).equalsIgnoreCase("VIDA")){
        Assert.assertTrue("MsjError != vacio", ((BigDecimal) aData.get("MONTO")).doubleValue() == montoTx);
      }
    }
  }


  private List getCuentaAcumulador(long idCuenta,long idFase) {
      String sQuery;
          sQuery =
           " SELECT \n" +
        "      CAC.id AS ID,\n" +
        "      RAC.periocidad AS PERIOCIDAD,\n" +
        "      CAC.monto AS MONTO , \n" +
        "      RAC.codigo_operacion AS CODOPE \n" +
        " FROM  \n" +
        "   "+getSchema()+"."+Constants.Tables.CUENTA_ACUMULADOR.getName() +" CAC \n" +
        " INNER JOIN "+getSchema()+"."+Constants.Tables.REGLA_ACUMULACION.getName()+" RAC ON CAC.id_regla_acumulacion = RAC.id  \n" +
        " INNER JOIN "+getSchema()+"."+Constants.Tables.CATEGORIA_MOV_FASE.getName()+" CMF ON RAC.id_categoria_movimiento = CMF.id_categoria_movimiento\n" +
        " WHERE  \n" +
        " id_cuenta ="+idCuenta+" AND\n" +
        " CMF.id_fase_movimiento = "+idFase+" \n" +
        " GROUP BY CAC.id, RAC.codigo_operacion,periocidad";
    return jdbcTempate.queryForList(
      sQuery
    );
  }

  private  Movimiento callCreaMovimientoCuenta(Movimiento movimiento) throws SQLException{
    Object[] params = {
      movimiento.getIdCuenta(),
      movimiento.getIdFaseMovimiento(),
      movimiento.getIdMovimientoRef(),
      movimiento.getIdExterno(),
      movimiento.getGlosa(),
      movimiento.getMonto(),
      movimiento.isbSimulacion()?"S":"N",
      new OutParam("_id_movimiento_cuenta", Types.NUMERIC),
      new OutParam("_numerror", Types.VARCHAR),
      new OutParam("_msjerror", Types.VARCHAR)};

    Map<String, Object> outputData = dbUtils.execute( getSchema() + Constants.Procedures.SP_CREA_MOVIMIENTO_CUENTA.getName(), params);

    BigDecimal id_movimiento_cuenta = (BigDecimal) outputData.get("_id_movimiento_cuenta");
    String numError = (String) outputData.get("_numerror");
    String msjError = (String) outputData.get("_msjerror");
    System.out.println("[MOVIMIENTO_CUENTA/CALL_CREA_MOVIMIENTO_CUENTA] NumError: "+numError +" MsjError: "+msjError);
    movimiento.setNumError(numError);
    movimiento.setMsjError(msjError);

    if (numError.equals("0")) {
      movimiento.setIdMovimientoRef(id_movimiento_cuenta.intValue());
    }
    else {
      movimiento.setIdMovimientoRef(0);
    }
    return movimiento;
  }

}


class Movimiento {

  private Integer idFaseMovimiento;
  private String idCuenta;
  private Integer idMovimientoRef;
  private String idExterno;
  private String glosa;
  private BigDecimal monto;
  private boolean bSimulacion;

  private String numError;
  private String msjError;

  public Movimiento() {
  }

  public Movimiento(Integer idFaseMovimiento, String idCuenta, Integer idMovimientoRef, String idExterno, String glosa, BigDecimal monto, boolean bSimulacion) {
    this.idFaseMovimiento = idFaseMovimiento;
    this.idCuenta = idCuenta;
    this.idMovimientoRef = idMovimientoRef;
    this.idExterno = idExterno;
    this.glosa = glosa;
    this.monto = monto;
    this.bSimulacion = bSimulacion;
  }

  public String getIdCuenta() {
    return idCuenta;
  }

  public Integer getIdFaseMovimiento() {
    return idFaseMovimiento;
  }

  public void setIdFaseMovimiento(Integer idFaseMovimiento) {
    this.idFaseMovimiento = idFaseMovimiento;
  }

  public void setIdCuenta(String idCuenta) {
    this.idCuenta = idCuenta;
  }

  public Integer getIdMovimientoRef() {
    return idMovimientoRef;
  }

  public void setIdMovimientoRef(Integer idMovimientoRef) {
    this.idMovimientoRef = idMovimientoRef;
  }

  public String getIdExterno() {
    return idExterno;
  }

  public void setIdExterno(String idExterno) {
    this.idExterno = idExterno;
  }

  public String getGlosa() {
    return glosa;
  }

  public void setGlosa(String glosa) {
    this.glosa = glosa;
  }

  public BigDecimal getMonto() {
    return monto;
  }

  public void setMonto(BigDecimal monto) {
    this.monto = monto;
  }

  public boolean isbSimulacion() {
    return bSimulacion;
  }

  public void setbSimulacion(boolean bSimulacion) {
    this.bSimulacion = bSimulacion;
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
