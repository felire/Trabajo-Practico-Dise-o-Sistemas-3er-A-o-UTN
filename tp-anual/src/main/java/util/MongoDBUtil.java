package util;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;

public class MongoDBUtil {

	public static Datastore getDataStore(){
		Morphia morphia = new Morphia();
		morphia.mapPackage("ar.utn.frba.disenio.tp_anual");
		Datastore datastore = morphia.createDatastore(new MongoClient(), "disenio");
		datastore.ensureIndexes();
		return datastore;
	}
	
}
