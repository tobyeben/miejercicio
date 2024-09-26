package ejemploTipoComida.ejemplo.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ejemploTipoComida.ejemplo.Repositorio.TipoComidaRepository;
import ejemploTipoComida.ejemplo.entidad.TipoComida;
import ejemploTipoComida.ejemplo.service.ITipoComidaService;

@Service
public class TipoComidaServiceImpl implements ITipoComidaService {
	@Autowired
	private TipoComidaRepository tipoComidaRepository;

	@Override
	public List<TipoComida> buscarTodos() {
		List<TipoComida> comidas = tipoComidaRepository.findAll();
		return comidas;

	}

	@Override
	public TipoComida buscarPorId(Integer id) {
		Optional<TipoComida> opt = tipoComidaRepository.findById(id);
		return opt.orElse(null);
	}

	@Override
	public TipoComida guardar(TipoComida comida) {
		comida = tipoComidaRepository.save(comida);
		return comida;
	}

	@Override
	public boolean eliminar(Integer id) {
		tipoComidaRepository.deleteById(id);
		return true;
	}

	@Override
	public boolean existe(Integer id) {
		return (id == null) ? false : tipoComidaRepository.existsById(id);

	}

	@Override
	public List<TipoComida> buscarHabilitado(boolean habilitado) {
		return tipoComidaRepository.findByHabilitado(habilitado);

	}

	@Override
	public List<TipoComida> buscarHabilitado1(boolean habilitado1) {
		
		return tipoComidaRepository.findByHabilitado1(habilitado1);
	}

	@Override
	public List<TipoComida> buscaresVerdura(boolean esVerdura) {
		
		return tipoComidaRepository.findByesVerdura(esVerdura);
	}

}
