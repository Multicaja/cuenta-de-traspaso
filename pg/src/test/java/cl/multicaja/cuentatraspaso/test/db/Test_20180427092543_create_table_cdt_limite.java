package cl.multicaja.cuentatraspaso.test.db;

import cl.multicaja.prepago.test.TestDbBase;
import cl.multicaja.prepago.utils.db.ColumnInfo;
import org.junit.Assert;
import org.junit.Test;

public class Test_20180427092543_create_table_cdt_limite extends TestDbBase {

  /********************************************************
   *       id                    BIGSERIAL NOT NULL,
   *       id_movimiento         BIGSERIAL NOT NULL,
   *       id_regla_acumulacion  BIGSERIAL,
   *       descripcion           VARCHAR(100) NOT NULL,
   *       valor                 DECIMAL NOT NULL,
   *       cod_operacion         VARCHAR(10) NOT NULL,
   *       estado                VARCHAR(5) NOT NULL,
   *       fecha_estado          TIMESTAMP NOT NULL,
   *       fecha_creacion        TIMESTAMP NOT NULL,
   *       CONSTRAINT cdt_limite_pk PRIMARY KEY(id)
   *******************************************************/

  @Test
  public void CheckTableLimite() {
    boolean exists = dbUtils.tableExists(Constants.SCHEMA, Constants.Tables.LIMITE.getName(), true,
      new ColumnInfo("id", "BIGSERIAL",19),
      new ColumnInfo("id_movimiento", "BIGSERIAL", 19),
      new ColumnInfo("id_regla_acumulacion", "BIGSERIAL", 19),
      new ColumnInfo("descripcion", "VARCHAR", 100),
      new ColumnInfo("valor", "NUMERIC", 131089),
      new ColumnInfo("cod_operacion", "VARCHAR", 10),
      new ColumnInfo("estado", "VARCHAR", 10),
      new ColumnInfo("fecha_estado", "TIMESTAMP", 29),
      new ColumnInfo("fecha_creacion", "TIMESTAMP", 29));
      Assert.assertEquals("Existe tabla "+Constants.SCHEMA+"."+Constants.Tables.LIMITE.getName(), true, exists);
  }


}
