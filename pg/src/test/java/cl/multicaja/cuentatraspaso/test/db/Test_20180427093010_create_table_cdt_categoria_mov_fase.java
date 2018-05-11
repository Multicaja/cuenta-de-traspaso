package cl.multicaja.cuentatraspaso.test.db;

import cl.multicaja.core.test.TestDbBase;
import cl.multicaja.core.utils.db.ColumnInfo;
import org.junit.Assert;
import org.junit.Test;

public class Test_20180427093010_create_table_cdt_categoria_mov_fase extends TestDbBase {
  /*************************************************
   *    id_fase_movimiento        BIGSERIAL NOT NULL,
   *    id_categoria_movimiento   BIGSERIAL NOT NULL,
   *
   *************************************************/
  @Test
  public void Ch() {
    boolean exists = dbUtils.tableExists(Constants.SCHEMA, Constants.Tables.CATEGORIA_MOV_FASE.getName(), true,
      new ColumnInfo("id_fase_movimiento", "INT8",19),
      new ColumnInfo("id_categoria_movimiento", "INT8", 19));
    Assert.assertEquals("Existe tabla "+Constants.SCHEMA+"."+Constants.Tables.CATEGORIA_MOV_FASE.getName(), true, exists);
  }
}
