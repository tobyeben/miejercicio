package ejemploTipoComida.ejemplo.entidad;


import jakarta.persistence.*;

@Entity
public class TipoComida {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer Id;
	
	private String nombre ;
	private boolean habilitado;
	private double precioComida;
	private boolean habilitado1;
	private boolean esVerdura;
	
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public boolean isHabilitado() {
		return habilitado;
	}
	public void setHabilitado(boolean habilitado) {
	
	
		this.precioComida=precioComida;
	}
	public void setprecioComida(double precioComida) {
		
		this.habilitado = habilitado;
	}
	public double getprecioComida() {
		return precioComida;
	}
	public void sethabilitado1(boolean habilitado1) {
		this.habilitado1=habilitado1;
	}
	public boolean gethabilitado1() {
		return habilitado1;
	}
	
	
	
	public boolean isEsVerdura() {
		return esVerdura;
	}
	public void setEsVerdura(boolean esVerdura) {
		this.esVerdura = esVerdura;
	}
	public TipoComida(Integer id, String nombre, boolean habilitado) {
		super();
		Id = id;
		this.nombre = nombre;
		this.habilitado = habilitado;
	}
	public TipoComida() {
		// TODO Auto-generated constructor stub
	}
	

}

