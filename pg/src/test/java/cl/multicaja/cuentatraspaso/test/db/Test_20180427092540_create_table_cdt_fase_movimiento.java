package cl.multicaja.cuentatraspaso.test.db;

import cl.multicaja.core.test.TestDbBase;
import cl.multicaja.core.utils.db.ColumnInfo;
import org.junit.Assert;
import org.junit.Test;

public class Test_20180427092540_create_table_cdt_fase_movimiento extends TestDB{
  /**********************************************
   *    id                    BIGSERIAL NOT NULL,
   *    nombre                VARCHAR(20) NOT NULL,
   *    descripcion           VARCHAR(100) NOT NULL,
   *    signo                 NUMERIC NOT NULL,
   *    estado                VARCHAR(5) NOT NULL,
   *    fecha_estado          TIMESTAMP NOT NULL,
   *    fecha_creacion        TIMESTAMP NOT NULL,
   *********************************************/
  @Test
  public void CheckTableMovimiento() {
    boolean exists = dbUtils.tableExists(getSchema(), Constants.Tables.FASE_MOVIMIENTO.getName(), true,
      new ColumnInfo("id", "BIGSERIAL",19),
      new ColumnInfo("nombre", "VARCHAR", 50),
      new ColumnInfo("descripcion", "VARCHAR", 100),
      new ColumnInfo("ind_confirmacion","VARCHAR",1),
      new ColumnInfo("estado", "VARCHAR", 10),
      new ColumnInfo("fecha_estado", "TIMESTAMP",29),
      new ColumnInfo("fecha_creacion", "TIMESTAMP", 29));
    Assert.assertEquals("Existe tabla "+getSchema()+"."+Constants.Tables.FASE_MOVIMIENTO.getName(), true, exists);
  }
}
