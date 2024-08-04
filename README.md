# async-statistic

Servicio asincrono que se suscribe al evento para actualizar las estadisticas

## Configuración local

Para ejecutar la aplicación localmente, se necesita tener la bd creada y configurar variables de entorno

### Variables de Entorno

- **`DB_URL`**: La URL de conexión a la base de datos.
    - `localhost:3306`
- **`DB_NAME`**: El nombre de la base de datos.
    - `db_project`
- **`DB_USERNAME`**: El usuario para acceder a la base de datos.
    - `root`
- **`DB_PASSWORD`**: La contraseña del usuario para acceder la base de datos.
    - `rootroot`
- **`BOOTSTRAP_SERVERS`**: Servidor donde se encuentra el topico.
  - `Se proporciona el dato por interno`
- **`CLUSTER_API_KEY`**: Api key del cluster.
  - `Se proporciona el dato por interno`
- **`CLUSTER_API_SECRET`**: Api secret del cluster.
  - `Se proporciona el dato por interno`