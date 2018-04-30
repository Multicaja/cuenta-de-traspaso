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
   *       estado                VARCHAR(5) NOT NULL,
   *       fecha_estado          TIMESTAMP NOT NULL,
   *       fecha_creacion        TIMESTAMP NOT NULL,
   *************************************************/
  @Test
  public void CheckTableMovimientoCuenta() {
    boolean exists = dbUtils.tableExists(Constants.SCHEMA, Constants.Tables.MOVIMIENTO_CUENTA.getName(), true,
      new ColumnInfo("id", "BIGSERIAL",19),
      new ColumnInfo("id_tipo_movimiento", "BIGSERIAL", 19),
      new ColumnInfo("periocidad", "VARCHAR", 10),
      new ColumnInfo("codigo_operacion", "VARCHAR", 10),
      new ColumnInfo("estado", "VARCHAR", 5),
      new ColumnInfo("fecha_estado", "TIMEPSTAMP", 20),
      new ColumnInfo("fecha_creacion", "TIMEPSTAMP", 20));
    Assert.assertEquals("Existe tabla "+Constants.SCHEMA+"."+Constants.Tables.MOVIMIENTO_CUENTA.getName(), true, exists);
  }
}
