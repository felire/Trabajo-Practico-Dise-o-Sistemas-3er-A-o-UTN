package ar.utn.frba.disenio.tp_anual.observer;

import javax.persistence.*;

import ar.utn.frba.disenio.tp_anual.model.Busqueda;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_observer_terminal", discriminatorType = DiscriminatorType.STRING)
public abstract class ObserverTerminal {
	
	@Id @GeneratedValue
	private Integer id;
	public abstract void notificar(Busqueda busqueda);
	public abstract void preNotificar();
	public Integer getId(){
		return this.id;
	}
}
