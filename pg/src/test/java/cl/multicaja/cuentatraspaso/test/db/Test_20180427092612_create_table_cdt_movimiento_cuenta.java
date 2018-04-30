package cl.multicaja.cuentatraspaso.test.db;

import cl.multicaja.prepago.test.TestDbBase;
import cl.multicaja.prepago.utils.db.ColumnInfo;
import org.junit.Assert;
import org.junit.Test;

public class Test_20180427092612_create_table_cdt_movimiento_cuenta  extends TestDbBase {
  /**********************************************************
   *
   *       id                 BIGSERIAL NOT NULL,
   *       id_cuenta          BIGSERIAL NOT NULL,
   *       id_movimiento      BIGSERIAL NOT NULL,
   *       id_mov_referencia  BIGSERIAL NOT NULL,
   *       id_tx_externo      VARCHAR(50) NOT NULL,
   *       glosa              VARCHAR(100) NOT NULL,
   *       monto              DECIMAL NOT NULL,
   *       fecha_registro     TIMESTAMP NOT NULL,
   *       estado             VARCHAR(5) NOT NULL,
   *       fecha_estado       TIMESTAMP NOT NULL,
   **********************************************************/

  @Test
  public void CheckTableMovimientoCuenta() {
    boolean exists = dbUtils.tableExists(Constants.SCHEMA, Constants.Tables.MOVIMIENTO_CUENTA.getName(), true,
      new ColumnInfo("id", "BIGSERIAL",19),
      new ColumnInfo("id_cuenta", "BIGSERIAL", 19),
      new ColumnInfo("id_movimiento", "BIGSERIAL", 20),
      new ColumnInfo("id_mov_referencia", "BIGSERIAL", 100),
      new ColumnInfo("id_tx_externo", "VARCHAR", 50),
      new ColumnInfo("id_tx_externo", "VARCHAR", 50),
      new ColumnInfo("glosa", "VARCHAR",100),
      new ColumnInfo("monto", "DECIMAL", 20),
      new ColumnInfo("fecha_registro", "TIMEPSTAMP", 20),
      new ColumnInfo("estado", "VARCHAR", 5),
      new ColumnInfo("fecha_estado", "TIMEPSTAMP", 20));
    Assert.assertEquals("Existe tabla "+Constants.SCHEMA+"."+Constants.Tables.MOVIMIENTO_CUENTA.getName(), true, exists);
  }
}
