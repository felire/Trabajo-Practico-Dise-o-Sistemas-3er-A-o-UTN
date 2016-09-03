package ar.utn.frba.disenio.tp_anual.servicios;

import java.util.List;

import ar.utn.frba.disenio.tp_anual.model.POI;

import javax.persistence.*;

@Entity
@Inheritance (strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn (name = "tipo")
public abstract class BuscadorExterno {
	
	@Id @GeneratedValue
	private Integer id;
	
	public abstract List<POI> filtrar(String palabraClave, String Servicio);
}
