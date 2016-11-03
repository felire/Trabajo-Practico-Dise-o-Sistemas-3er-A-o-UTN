package dataBase;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;

import ar.utn.frba.disenio.tp_anual.Init;
import ar.utn.frba.disenio.tp_anual.model.LocalComercial;


public class TestMorphia extends Init{

	
	@Test
	public void test() {
		try (MongoClient client = new MongoClient()) {
		      Morphia morphia = new Morphia();
		      //morphia.map(LocalComercial.class);
		      //morphia.mapPackage("ar.utn.frba.disenio.tp_anual.model");

		      Datastore datastore = morphia.createDatastore(client, "tp_anual");
		      datastore.save(banco);
		      //datastore.save(recorrido)
		      //repositorio.guardar(recorrido);
		      //repositorio.guardar(otroRecorrido);
		    }
	}

}
