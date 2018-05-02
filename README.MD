
# Requisitos

- Ubuntu 16.04
- Postgresql 9.6
- java 1.8

# Instalacion de postgresql

## Ubuntu 12.04, 14.04, 16.04
```sh
$ sudo add-apt-repository "deb http://apt.postgresql.org/pub/repos/apt/ xenial-pgdg main"
$ wget --quiet -O - https://www.postgresql.org/media/keys/ACCC4CF8.asc | sudo apt-key add -
$ sudo apt-get update
$ sudo apt-get install postgresql-9.6
```

## Ubuntu 17.04 - 17.10
```sh
$ sudo apt-get install postgresql-9.6
```


# Configuración de postgresql

## Cambiar clave de usuario postgres:

1. conectarse
    
    ```sh
    $ sudo -u postgres psql postgres
    ```

2. cambiar la clave, usar clave: postgres
    ```sh
    $ postgres=# \password postgres
    Enter new password: postgres
    ```

3. Salir de la consola postgresql con \q
    ```sh
    $ postgres=# \q
    ```