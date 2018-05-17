package cl.multicaja.cuentatraspaso.test.db;

import cl.multicaja.core.test.TestDbBase;
import cl.multicaja.core.utils.db.ColumnInfo;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class Test_20180427091825_create_table_cdt_cuenta extends TestDB {

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

    boolean exists = dbUtils.tableExists(getSchema(), Constants.Tables.CUENTA.getName(), true,
    new ColumnInfo("id", "BIGSERIAL",19),
    new ColumnInfo("id_externo", "VARCHAR", 50),
    new ColumnInfo("descripcion", "VARCHAR", 100),
    new ColumnInfo("estado", "VARCHAR", 10),
    new ColumnInfo("fecha_estado", "TIMESTAMP", 29),
    new ColumnInfo("fecha_creacion", "TIMESTAMP", 29));
    Assert.assertEquals("Existe tabla "+getSchema()+"."+Constants.Tables.CUENTA.getName(), true, exists);

  }

}
