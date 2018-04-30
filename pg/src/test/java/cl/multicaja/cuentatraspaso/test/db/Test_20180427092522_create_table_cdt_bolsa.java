package cl.multicaja.cuentatraspaso.test.db;

import cl.multicaja.prepago.test.TestDbBase;
import cl.multicaja.prepago.utils.db.ColumnInfo;
import org.junit.Assert;
import org.junit.Test;

public class Test_20180427092522_create_table_cdt_bolsa extends TestDbBase {

  /************************************************

      id              BIGSERIAL NOT NULL,
      nombre          VARCHAR(20) NOT NULL,
      descripcion     VARCHAR(100) NOT NULL,
      estado          VARCHAR(5) NOT NULL,
      fecha_estado    TIMESTAMP NOT NULL,
      fecha_creacion  TIMESTAMP NOT NULL,
      CONSTRAINT cdt_bolsa_pk PRIMARY KEY(id)

   **************************************************/
  @Test
  public void CheckTableBolsa() {
      boolean exists = dbUtils.tableExists(Constants.SCHEMA, Constants.Tables.BOLSA.getName(), true,
      new ColumnInfo("id", "BIGSERIAL",19),
      new ColumnInfo("nombre", "VARCHAR", 20),
      new ColumnInfo("descripcion", "VARCHAR", 100),
      new ColumnInfo("estado", "VARCHAR", 5),
      new ColumnInfo("fecha_estado", "VARCHAR", 20),
      new ColumnInfo("fecha_creacion", "VARCHAR", 20));
      Assert.assertEquals("Existe tabla "+Constants.SCHEMA+"."+Constants.Tables.BOLSA.getName(), true, exists);
  }



}
