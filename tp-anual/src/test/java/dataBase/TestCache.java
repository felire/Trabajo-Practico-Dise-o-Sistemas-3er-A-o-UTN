package dataBase;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import ar.utn.frba.disenio.tp_anual.Init;
import ar.utn.frba.disenio.tp_anual.model.CGP;
import ar.utn.frba.disenio.tp_anual.model.LocalComercial;
import ar.utn.frba.disenio.tp_anual.model.POI;
import ar.utn.frba.disenio.tp_anual.model.SucursalBanco;
import ar.utn.frba.disenio.tp_anual.servicios.CachePoisExternos;
import redis.clients.*;
import redis.clients.jedis.Jedis;
import util.Point;

public class TestCache extends Init{

	@Test
	public void testBanco() {
		CachePoisExternos cache = new CachePoisExternos();
		List<POI> bancos = new ArrayList<POI>();
		bancos.add(banco);
		cache.guardarBancos(bancos, "nose", "!");
		List<POI> bancos2 = cache.encontrarBancos("nose", "!");
		assertEquals(bancos2.get(0).getNombre(),"Banco UTN");
		cache.borrarClave("Banconose#!");
	}

	@Test
	public void TestNil() {
		CachePoisExternos cache = new CachePoisExternos();
		List<POI> bancos2 = cache.encontrarBancos("gordo", "puto");
		assertEquals(bancos2,null);
	}
	
	@Test
	public void testCGP(){
		CachePoisExternos cache = new CachePoisExternos();
		List<POI> cgps = new ArrayList<POI>();
		cgps.add(cGP);
		cache.guardarCGPs(cgps, "hola");
		List<POI> cgps2 = cache.encontrarCGP("hola");
		assertEquals(cgps2.get(0).getNombre(), "CGP1");
		cache.borrarClave("CGPhola");
	}

}
