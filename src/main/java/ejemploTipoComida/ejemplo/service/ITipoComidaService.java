package ejemploTipoComida.ejemplo.service;

import java.util.List;

import ejemploTipoComida.ejemplo.entidad.TipoComida;

public interface ITipoComidaService {
	public List<TipoComida>buscarTodos();
	public TipoComida buscarPorId(Integer id);
    public TipoComida guardar (TipoComida comida);
    public boolean eliminar ( Integer id );
    public boolean existe ( Integer id);
    public List<TipoComida>buscarHabilitado(boolean habilitado);
    public List<TipoComida>buscarHabilitado1(boolean habilitado1);
    public List<TipoComida>buscaresVerdura(boolean esVerdura);
}
