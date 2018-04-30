package cl.multicaja.cuentatraspaso.test.db;

public class Constants {
  public static final String SCHEMA= "cuentatraspaso";
  public enum Tables
  {
    CUENTA("cdt_cuentas"),
    BOLSA("cdt_bolsa"),
    CUENTA_ACUMULADOR("ccdt_cuenta_acumulador"),
    LIMITE("cdt_limite"),
    MOVIMIENTO("cdt_movimiento"),
    TIPO_MOVIMIENTO("cdt_tipo_movimiento"),
    MOVIMIENTO_CUENTA("cdt_moviniento_cuenta"),
    REGLA_ACUMULACION("cdt_reglas_acumulacion"),
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
