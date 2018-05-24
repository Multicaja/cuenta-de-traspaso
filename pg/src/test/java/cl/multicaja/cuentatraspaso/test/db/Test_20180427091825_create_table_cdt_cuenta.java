package cl.multicaja.cuentatraspaso.test.db;

import cl.multicaja.core.test.TestDbBase;
import cl.multicaja.core.utils.db.ColumnInfo;
import cl.multicaja.core.utils.db.SqlType;
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
    new ColumnInfo("id", SqlType.BIGSERIAL.getGetJavaType()),
    new ColumnInfo("id_externo", SqlType.VARCHAR.getGetJavaType(), 50),
    new ColumnInfo("descripcion", SqlType.VARCHAR.getGetJavaType(), 100),
    new ColumnInfo("estado", SqlType.VARCHAR.getGetJavaType(), 10),
    new ColumnInfo("fecha_estado", SqlType.TIMESTAMP.getGetJavaType()),
    new ColumnInfo("fecha_creacion", SqlType.TIMESTAMP.getGetJavaType()));
    Assert.assertEquals("Existe tabla "+getSchema()+"."+Constants.Tables.CUENTA.getName(), true, exists);
  }

}
