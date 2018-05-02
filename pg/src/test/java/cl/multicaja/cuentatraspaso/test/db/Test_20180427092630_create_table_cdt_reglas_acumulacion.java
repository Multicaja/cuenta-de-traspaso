package cl.multicaja.cuentatraspaso.test.db;

import cl.multicaja.prepago.test.TestDbBase;
import cl.multicaja.prepago.utils.db.ColumnInfo;
import org.junit.Assert;
import org.junit.Test;

public class Test_20180427092630_create_table_cdt_reglas_acumulacion extends TestDbBase {
  /**************************************************
   *
   *       id                    BIGSERIAL NOT NULL,
   *       id_tipo_movimiento    BIGSERIAL NOT NULL,
   *       periocidad            VARCHAR(10) NOT NULL,
   *       codigo_operacion      VARCHAR(10) NOT NULL,
   *       estado                VARCHAR(10) NOT NULL,
   *       fecha_estado          TIMESTAMP NOT NULL,
   *       fecha_creacion        TIMESTAMP NOT NULL,
   *************************************************/
  @Test
  public void CheckTableReglaAcumulacion() {
    boolean exists = dbUtils.tableExists(Constants.SCHEMA, Constants.Tables.REGLA_ACUMULACION.getName(), true,
      new ColumnInfo("id", "BIGSERIAL",19),
      new ColumnInfo("id_tipo_movimiento", "BIGSERIAL", 19),
      new ColumnInfo("periocidad", "VARCHAR", 10),
      new ColumnInfo("codigo_operacion", "VARCHAR", 10),
      new ColumnInfo("estado", "VARCHAR", 10),
      new ColumnInfo("fecha_estado", "TIMESTAMP", 29),
      new ColumnInfo("fecha_creacion", "TIMESTAMP", 29));
    Assert.assertEquals("Existe tabla "+Constants.SCHEMA+"."+Constants.Tables.REGLA_ACUMULACION.getName(), true, exists);
  }
}
