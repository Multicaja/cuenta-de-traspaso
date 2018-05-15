package cl.multicaja.cuentatraspaso.test.db;

import cl.multicaja.core.test.TestSuiteBase;
import cl.multicaja.core.utils.ConfigUtils;
import cl.multicaja.core.utils.Utils;
import cl.multicaja.core.utils.db.DBUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import java.io.*;
import java.net.URI;
import java.net.URL;

/**
 * @autor vutreras
 */
@RunWith(DynamicSuite.class)
public class TestSuite extends TestSuiteBase {

  private static Log log = LogFactory.getLog(TestSuite.class);
  private static DBUtils mDbutils = DBUtils.getInstance();
  private static String schema = ConfigUtils.getInstance().getProperty("schema");

  @BeforeClass
  public static void setUp() throws Exception {

    deleteAllFromDB();
    String sData = Utils.readFile(new File("./seeds/DataForTest.sql"));
    sData = sData.replaceAll("\\$\\{schema}",schema);
    ScriptUtils.executeSqlScript(mDbutils.getConnection(),new ByteArrayResource(sData.getBytes("UTF-8")));

  }


  @AfterClass
  public static void tearDown() {
     // deleteAllFromDB();
  }

  private static void deleteAllFromDB() {
    JdbcTemplate template = mDbutils.getJdbcTemplate();


    //DROP TABLE CONFIRMACION_MOVIMIENTO_CUENTA
    template.execute("DELETE FROM "+schema+"."+Constants.Tables.CONFIRMACION_MOV.getName());

    // DROP TABLE MOVIMIENTO CATEGORIA MOVIMIENTO
    template.execute("DELETE FROM "+schema+"."+Constants.Tables.CATEGORIA_MOV_FASE.getName());


    // DROP TABLE MOVIMIENTO CUENTA
    template.execute("DELETE FROM  "+schema+"."+Constants.Tables.MOVIMIENTO_CUENTA.getName());
    template.execute("ALTER SEQUENCE "+schema+"."+Constants.Tables.MOVIMIENTO_CUENTA.getName()+"_id_seq RESTART WITH 1;");

    // DROP TABLE CUENTA ACUMULADOR
    template.execute("DELETE FROM "+schema+"."+Constants.Tables.CUENTA_ACUMULADOR.getName());
    template.execute("ALTER SEQUENCE "+schema+"."+Constants.Tables.CUENTA_ACUMULADOR.getName()+"_id_seq RESTART WITH 1;");


    // DROP TABLE LIMITE
    template.execute("DELETE FROM "+schema+"."+Constants.Tables.LIMITE.getName());
    template.execute("ALTER SEQUENCE "+schema+"."+Constants.Tables.LIMITE.getName()+"_id_seq RESTART WITH 1;");


    // DROP TABLE REGLA ACUMULACION
    template.execute("DELETE FROM "+schema+"."+Constants.Tables.REGLA_ACUMULACION.getName());
    template.execute("ALTER SEQUENCE "+schema+"."+Constants.Tables.REGLA_ACUMULACION.getName()+"_id_seq RESTART WITH 1;");

    // DROP TABLE FASE MOVIMIENTO
    template.execute("DELETE FROM "+schema+"."+Constants.Tables.FASE_MOVIMIENTO.getName());
    template.execute("ALTER SEQUENCE "+schema+"."+Constants.Tables.FASE_MOVIMIENTO.getName()+"_id_seq RESTART WITH 1;");

    // DROP TABLE CATEGORIA MOVIMIENTO
    template.execute("DELETE FROM  "+schema+"."+Constants.Tables.CATEGORIA_MOVIMIENTO.getName());
    template.execute("ALTER SEQUENCE "+schema+"."+Constants.Tables.CATEGORIA_MOVIMIENTO.getName()+"_id_seq RESTART WITH 1;");



    // DROP TABLE BOLSA
    template.execute("DELETE FROM "+schema+"."+Constants.Tables.BOLSA.getName());
    template.execute("ALTER SEQUENCE "+schema+"."+Constants.Tables.BOLSA.getName()+"_id_seq RESTART WITH 1;");

    // DROP TABLE CUENTA
    template.execute("DELETE FROM "+schema+"."+Constants.Tables.CUENTA.getName());
    template.execute("ALTER SEQUENCE "+schema+"."+Constants.Tables.CUENTA.getName()+"_id_seq RESTART WITH 1;");

  }
  public static Class<?>[] suite() throws Exception {

    String packageName = new TestSuite().getClass().getPackage().getName();
    log.info("packageName: " + packageName);
    Class[] classList = getClasses(packageName);
    log.info("------------ Lista de clases de test ------------");
    for (Class cls : classList) {
      log.info(cls.getSimpleName());
    }
    return  classList;

  }

}


