package cl.multicaja.cuentatraspaso.test.db;

public class Constants {
  public static final String SCHEMA= "cuentatraspaso";
  public enum Tables
  {
    CUENTA("cdt_cuenta"),
    BOLSA("cdt_bolsa"),
    CUENTA_ACUMULADOR("cdt_cuenta_acumulador"),
    LIMITE("cdt_limite"),
    MOVIMIENTO("cdt_movimiento"),
    TIPO_MOVIMIENTO("cdt_tipo_movimiento"),
    MOVIMIENTO_CUENTA("cdt_movimiento_cuenta"),
    REGLA_ACUMULACION("cdt_regla_acumulacion"),
    MOVIMIENTO_TIPO_MOV("cdt_movimiento_tipo_mov");

    private String name;

    Tables(String name) {
      this.name = name;
    }
    public String getName() {
      return name;
    }

  }
}
