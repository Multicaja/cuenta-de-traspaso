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
  public enum Procedures
  {
    SP_CREA_BOLSA(".mc_cdt_crea_bolsa"),
    SP_CREA_CUENTA(".mc_cdt_crea_cuenta"),
    SP_CREA_LIMITE(".mc_cdt_crea_limite"),
    SP_CREA_MOVIMIENTO(".mc_cdt_crea_movimiento"),
    SP_CREA_TIPO_MOVIMIENTO(".mc_cdt_crea_tipo_movimiento"),
    SP_CREA_TIPO_MOV_TIPO_MOV(".mc_cdt_crea_movimiento_tipomov"),
    SP_CREA_MOVIMIENTO_CUENTA(".mc_cdt_crea_movimiento_cuenta"),
    SP_CREA_REGLA_ACUMULACION(".mc_cdt_crea_regla_acumulacion"),
    SP_CARGA_MOVIMIENTOS(".mc_cdt_carga_movimientos");

    private String name;
    Procedures(String name) {
      this.name = name;
    }
    public String getName() {
      return name;
    }

  }

}
