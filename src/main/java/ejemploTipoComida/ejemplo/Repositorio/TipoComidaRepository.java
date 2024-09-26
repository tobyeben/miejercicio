package ejemploTipoComida.ejemplo.Repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ejemploTipoComida.ejemplo.entidad.TipoComida;

@Repository
public interface TipoComidaRepository extends JpaRepository<TipoComida,Integer> {
  public List<TipoComida>findByHabilitado(boolean habilitado);	
  public List<TipoComida>findByHabilitado1(boolean habilitado1);
  public List<TipoComida>findByesVerdura(boolean esVerdura);

}
