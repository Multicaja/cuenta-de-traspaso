package cl.multicaja.cuentatraspaso.test.db;

import cl.multicaja.core.test.TestDbBase;
import cl.multicaja.core.utils.ConfigUtils;

public class TestDB extends TestDbBase {
  private ConfigUtils oConfig = ConfigUtils.getInstance();
  protected String getSchema(){
    return oConfig.getProperty("schema.cdt");
  }
}
