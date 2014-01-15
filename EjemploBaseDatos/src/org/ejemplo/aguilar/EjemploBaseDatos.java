package org.ejemplo.aguilar;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class EjemploBaseDatos extends Activity {

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //Abrimos la base de datos en modo escritura
        ClientesSQLiteHelper cliBDh = new ClientesSQLiteHelper(this, "DBClientes", null, 1);
        
        //Obtenemos referencia a la base de datos para poder modificarla.
        SQLiteDatabase bd = cliBDh.getWritableDatabase();
        
        //En caso de abrir de forma correcta la base de datos
        if (bd!=null) {
        	//Introducimos 3 clientes de ejemplo
        	for (int cont=1; cont<=3; cont++) {
        		//Creamos los datos
        		int codigo = cont;
        		String nombre = "Cliente" + cont;
        		String telefono = cont + "XXXXXXX";
        		
        		//Introducimos los datos en la tabla Clientes
        		bd.execSQL("INSERT INTO Clientes (codigo, nombre, telefono) " +
        					"VALUES (" + codigo + ", '" + nombre + "', '" + telefono + "')");
        		/*
        		//Insertar un registro
        		bd.execSQL("INSERT INTO Clientes (cliente, telefono) VALUES ('cli1','11111') ");
        		//Actualizar un registro
        		bd.execSQL("UPDATE Clientes SET telefono='00000' WHERE cliente='cli1' ");
        		//Eliminar un registro
        		bd.execSQL("DELETE FROM Clientes WHERE cliente='cli1' ");
        		
        		//Ejemplo de utilizaci�n del m�todo insert()
        		//Creamos el registro que queremos insertar utilizando un objeto ContentValues
        		ContentValues nuevoRegistro = new ContentValues();
        		nuevoRegistro.put("cliente","cli10");
        		nuevoRegistro.put("telefono", "101010");
        		//Insertamos el registro en la base de datos
        		//El primer par�metro es el nombre de la tabla donde insertaremos.
        		//El segundo par�metro lo obviamos ya que solo sirve en caso de querer introducir
        		//un registro vac�o.
        		//El tercer par�emtro es el objeto ContentValues que contiene el registro a insertar
        		bd.insert("Clientes", null, nuevoRegistro);

        		//Ejemplo de utilizaci�n del m�todo update()
        		//Establecemos los campos-valores que vamos a actualizar
        		ContentValues valores = new ContentValues();
        		valores.put("telefono", "101010XX");
        		//Actualizamos el registro en la base de datos
        		//El tercer argumento es la condici�n del UPDATE tal como lo har�amos en la cl�usula
        		//WHERE en una sentencia SQL normal.
        		//El cuarto argumento son partes variables de la sentencia SQL que aportamos en un
        		//vector de valores aparte
        		String[] args = new String[]{"cli1", "cli2"};
        		bd.update("Clientes", valores, "cliente=? OR cliente=?", args);

        		//Ejemplo de utilizaci�n del m�todo delete()
        		//Eliminamos el registro del cliente 'cli2'
        		String[] args2 = new String[]{"cli2"};
        		bd.delete("Clientes", "usuario=?", args2);
        		
        		//Ejemplo Select
        		String[] args = new String[]{"cli1"};
        		Cursor c = bd.rawQuery("SELECT cliente,telefono FROM Clientes WHERE cliente=? ", args);
        		
        		//Ejemplo Select2
        		String[] campos = new String[] {"nombre", "telefono"};
        		String[] args = new String[] {"cli1"};
        		Cursor c = bd.query("Clientes", campos, "nombre=?", args, null, null, null);
        		//Nos aseguramos de que exista al menos un registro
        		if (c.moveToFirst()) {
        			//Recorremos el cursor hasta que no haya m�s registros
        			do {
        				String nombreCli = c.getString(0);
        				String telefonoCli = c.getString(1);
        			} while (c.moveToNext());
        		}
        		*/
        	}// del for
        	
        	//Cerramos la base de datos
        	bd.close();
        }
    }
}