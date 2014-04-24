<?php

 #Nos aseguramos de que el cliente ha proporcionado un valor para "CodeToSearch"
 if (isset($_POST["CodeToSearch"]) && $_POST["CodeToSearch"] != ""){

 #Variable de configuracion
 $code = $_POST["CodeToSearch"];

 #Conexion a la base de datos
 $DB_SERVER="localhost";
 $DB_USER="root";
 $DB_PASS=""; #Modificar esta linea si se ha establecido una contraseña para el usuario root en MySQL
 $DB_DATABASE="supermercado";
 $con = mysqli_connect($DB_SERVER, $DB_USER, $DB_PASS, $DB_DATABASE);

 #Comprobamos la conexion
 if (mysqli_connect_errno()) {
  	echo 'Error de conexion a la base de datos<br>';	
  	echo 'Database connection error: ';
 	exit();
 }

 #Escapamos caracteres especiales para evitar ataques de inyección SQL
 $code = mysqli_real_escape_string($con, $code);

 #Consultamos la base de datos para obtener los datos de los productos
 $productdetails = mysqli_query($con, "SELECT * FROM productos WHERE codigo = '$code'");

 #Si no se devuelven datos, comprobamos que no existen errores de SQL
 if (!$productdetails) {
  	echo 'No se pudo ejecutar la consulta<br>';
  	echo 'Could not run query';
  	exit();
 }

 #Obtenemos la primera fila de los resultados
 $row = mysqli_fetch_row($productdetails);

 #Construimos el array resultante (Asignamos claves a los valores)
 $result_data = array(
  	'codigo' => $row[0],
  	'descripcion' => $row[1],
  	'precio' => $row[2],
 );

 #Salida de los datos JSON
 echo json_encode($result_data);
}else{
	echo "No se pudo completar la consulta. Falta un parametro<br>";
 	echo "Could not complete query. Missing parameter";
	exit();
}
?>