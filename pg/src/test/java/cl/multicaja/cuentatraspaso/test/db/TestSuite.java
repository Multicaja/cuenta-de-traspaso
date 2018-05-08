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

    String sData = Utils.readFile(new File("./seeds/DataForTest.sql"));
    sData = sData.replaceAll("\\$\\{schema}",schema);
    ScriptUtils.executeSqlScript(mDbutils.getConnection(),new ByteArrayResource(sData.getBytes("UTF-8")));

  }


  @AfterClass
  public static void tearDown()
  {
    JdbcTemplate template = mDbutils.getJdbcTemplate();
    // DROP TABLE CUENTA
    template.execute("TRUNCATE TABLE "+schema+".cdt_cuenta");
    template.execute("SELECT setval('"+schema+".cdt_cuenta_id_s1', 1);");

    // DROP TABLE BOLSA
    template.execute("TRUNCATE TABLE "+schema+".cdt_bolsa");
    template.execute("SELECT setval('"+schema+".cdt_bolsa_id_s1', 1);");


    // DROP TABLE CUENTA ACUMULADOR
    template.execute("TRUNCATE TABLE "+schema+".cdt_cuenta_acumulador");
    template.execute("SELECT setval('"+schema+".cdt_cuenta_acumulador_id_s1', 1);");

    // DROP TABLE LIMITE
    template.execute("TRUNCATE TABLE "+schema+".cdt_limite");
    template.execute("SELECT setval('"+schema+".cdt_limite_id_s1', 1);");

    // DROP TABLE MOVIMIENTO
    template.execute("TRUNCATE TABLE "+schema+".cdt_movimiento");
    template.execute("SELECT setval('"+schema+".cdt_movimiento_id_s1', 1);");

    // DROP TABLE TIPO MOVIMIENTO
    template.execute("TRUNCATE TABLE "+schema+".cdt_tipo_movimiento");
    template.execute("SELECT setval('"+schema+".cdt_tipo_movimiento_id_s1', 1);");

    // DROP TABLE MOVIMIENTO CUENTA
    template.execute("TRUNCATE TABLE "+schema+".cdt_movimiento_cuenta");
    template.execute("SELECT setval('"+schema+".cdt_movimiento_cuenta_id_s1', 1);");

    // DROP TABLE REGLA ACUMULACION
    template.execute("TRUNCATE TABLE "+schema+".cdt_regla_acumulacion");
    template.execute("SELECT setval('"+schema+".cdt_regla_acumulacion_id_s1', 1);");

    // DROP TABLE MOVIMIENTO TIPO MOVIMIENTO
    template.execute("TRUNCATE TABLE "+schema+".cdt_movimiento_tipo_mov");
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

  public static void main(String[] args) throws Exception{
    TestSuite.setUp();
  }
}


