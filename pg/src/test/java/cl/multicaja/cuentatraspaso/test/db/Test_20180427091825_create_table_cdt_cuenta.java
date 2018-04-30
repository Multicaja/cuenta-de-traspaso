package cl.multicaja.cuentatraspaso.test.db;

import cl.multicaja.prepago.test.TestDbBase;
import cl.multicaja.prepago.utils.db.ColumnInfo;
import org.junit.Assert;
import org.junit.Test;

public class Test_20180427091825_create_table_cdt_cuenta extends TestDbBase {

  /***************************************
    id              BIGSERIAL NOT NULL,
    id_externo      BIGSERIAL NOT NULL,
    descripcion     VARCHAR(100) NOT NULL,
    estado          VARCHAR(5) NOT NULL,
    fecha_estado    TIMESTAMP NOT NULL,
    fecha_creacion  TIMESTAMP NOT NULL,
   ****************************************/
  @Test
  public void CheckTableCuenta() {
    boolean exists = dbUtils.tableExists(Constants.SCHEMA, Constants.Tables.CUENTA.getName(), true,
    new ColumnInfo("id", "BIGSERIAL",19),
    new ColumnInfo("id_externo", "BIGSERIAL", 19),
    new ColumnInfo("descripcion", "VARCHAR", 100),
    new ColumnInfo("estado", "VARCHAR", 5),
    new ColumnInfo("fecha_estado", "VARCHAR", 20),
    new ColumnInfo("fecha_creacion", "VARCHAR", 20));
    Assert.assertEquals("Existe tabla "+Constants.SCHEMA+"."+Constants.Tables.CUENTA.getName(), true, exists);
  }

}
