package cl.multicaja.cuentatraspaso.test.db;

import cl.multicaja.core.test.TestDbBase;
import cl.multicaja.core.utils.db.ColumnInfo;
import org.junit.Assert;
import org.junit.Test;

public class Test_20180427093010_create_table_cdt_movimiento_tipo_mov extends TestDbBase {
  /*************************************************
   *    id_movimiento         BIGSERIAL NOT NULL,
   *    id_tipo_movimiento    BIGSERIAL NOT NULL,
   *
   *************************************************/
  @Test
  public void CheckTableMovimientoTipoMov() {
    boolean exists = dbUtils.tableExists(Constants.SCHEMA, Constants.Tables.MOVIMIENTO_TIPO_MOV.getName(), true,
      new ColumnInfo("id_movimiento", "BIGSERIAL",19),
      new ColumnInfo("id_tipo_movimiento", "BIGSERIAL", 19));
    Assert.assertEquals("Existe tabla "+Constants.SCHEMA+"."+Constants.Tables.MOVIMIENTO_TIPO_MOV.getName(), true, exists);
  }
}
