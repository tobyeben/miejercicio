package ejemploTipoComida.ejemplo.controlador;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ejemploTipoComida.ejemplo.entidad.TipoComida;
import ejemploTipoComida.ejemplo.service.jpa.TipoComidaServiceImpl;
import ejemploTipoComida.ejemplo.util.ResponseUtil;
import jakarta.validation.ConstraintViolationException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/v1/comida")
public class TipoComidaController {

	@Autowired
	private TipoComidaServiceImpl tipoComidaService;
	List<String> errorMessages = new ArrayList<>();

	@GetMapping("")
	public ResponseEntity<ApiResponse<Object>> buscarTodos() {
		List<TipoComida> tipoComida = tipoComidaService.buscarTodos();
		return tipoComida.isEmpty()
				? (ResponseEntity<ApiResponse<Object>>) ResponseUtil.notFound("No hay comidas guardadas")
				: ResponseUtil.success(tipoComida);

	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<TipoComida>> obtenerComidaPorId(@PathVariable("id") Integer id) {
		TipoComida tipoComida = tipoComidaService.buscarPorId(id);
		return tipoComida == null
				? ResponseUtil.notFound(" no se encontró la comida con el identificador proporcionado")
				: ResponseUtil.success(tipoComida);

	}

	@PostMapping("")
	public ResponseEntity<ApiResponse<TipoComida>> guardarComida(@RequestBody TipoComida comida) throws Exception {
		return comida.getNombre().isEmpty() ? ResponseUtil.badRequest("Debe ingresar un nombre")
				: ResponseUtil.created(tipoComidaService.guardar(comida));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<TipoComida>> modificarComida(@PathVariable("id") Integer id,
			@RequestBody TipoComida comida) throws Exception {
		return tipoComidaService.existe(comida.getId()) ? ResponseUtil.success(tipoComidaService.guardar(comida))
				: ResponseUtil.badRequest("No existe la comida con el id especificado");

	}

	@GetMapping("/habilitado")
	public ResponseEntity<ApiResponse<List<TipoComida>>> buscarHabilitados() {
		List<TipoComida> tipoComidaHabilitadas = tipoComidaService.buscarHabilitado(true);
		return tipoComidaHabilitadas.isEmpty() ? ResponseUtil.notFound("No hay comidas habilitadas ")
				: ResponseUtil.success(tipoComidaHabilitadas);
	}

	@GetMapping("/deshabilitado")
	public ResponseEntity<ApiResponse<List<TipoComida>>> buscarDeshabilitados() {
		List<TipoComida> tipoComidaDeshabilitadas = tipoComidaService.buscarHabilitado(false);
		return tipoComidaDeshabilitadas.isEmpty() ? ResponseUtil.notFound("No hay comidas Deshabilitadas ")
				: ResponseUtil.success(tipoComidaDeshabilitadas);
	}

	@GetMapping("/habilitado1")
	public ResponseEntity<ApiResponse<List<TipoComida>>> buscarHabilitados1() {
		List<TipoComida> tipoComidaHabilitadas = tipoComidaService.buscarHabilitado1(true);
		return tipoComidaHabilitadas.isEmpty() ? ResponseUtil.notFound(" No hay comidas habilitadas")
				: ResponseUtil.success(tipoComidaHabilitadas);

	}

	@GetMapping("/esVerdura")
	public ResponseEntity<ApiResponse<List<TipoComida>>> buscaresVerdura() {
		List<TipoComida> tipoComidaesVerdura = tipoComidaService.buscaresVerdura(true);
		return tipoComidaesVerdura.isEmpty() ? ResponseUtil.notFound("no hay verduras habilitadas")
				: ResponseUtil.success(tipoComidaesVerdura);
	}

	@GetMapping("/NoesVerdura")
	public ResponseEntity<ApiResponse<List<TipoComida>>> buscarNoesVerdura() {
		List<TipoComida> tipoComidaNoesVerdura = tipoComidaService.buscaresVerdura(false);
		return tipoComidaNoesVerdura.isEmpty() ? ResponseUtil.notFound(" Hay comidas qué no son verdura ")
				: ResponseUtil.success(tipoComidaNoesVerdura);
	}

	@GetMapping("/deshabilitado1")
	public ResponseEntity<ApiResponse<List<TipoComida>>> buscarDeshabilitados1() {
		List<TipoComida> tipoComidaDeshabilitadas = tipoComidaService.buscarHabilitado1(false);
		return tipoComidaDeshabilitadas.isEmpty() ? ResponseUtil.notFound("No hay comidas Deshabilitadas ")
				: ResponseUtil.success(tipoComidaDeshabilitadas);
	}

	@PutMapping("/habilitar/{id}")
	public ResponseEntity<ApiResponse<TipoComida>> habilitarComida(@PathVariable Integer id) throws Exception {
		TipoComida tipoComida = tipoComidaService.buscarPorId(id);

		if (tipoComida == null)
			return ResponseUtil.badRequest(" El ID no existe");

		if (!tipoComida.isHabilitado()) {
			tipoComida.setHabilitado(true);
			return ResponseUtil.success(tipoComidaService.guardar(tipoComida));

		} else {
			return ResponseUtil.badRequest(" La comida ya está habilitado ");
		}

	}

	@PutMapping("/deshabilitar/{id}")
	public ResponseEntity<ApiResponse<TipoComida>> deshabilitarComida(@PathVariable Integer id) throws Exception {
		TipoComida tipoComida = tipoComidaService.buscarPorId(id);

		if (tipoComida == null) {
			return ResponseUtil.badRequest("El ID no existe");
		}
		if (tipoComida.isHabilitado()) {
			tipoComida.setHabilitado(false);
			return ResponseUtil.success(tipoComidaService.guardar(tipoComida));
		} else {
			return ResponseUtil.badRequest("La comida ya está deshabilitada.");
		}
	}

	@PutMapping("/habilitarverdura/{id}")
	public ResponseEntity<ApiResponse<TipoComida>> habilitarVerdura(@PathVariable Integer id) throws Exception{
		TipoComida tipoComida = tipoComidaService.buscarPorId(id);
		
		if (tipoComida == null) {
			return ResponseUtil.badRequest(" el ID no existe ");
			
					}
		if(tipoComida.isEsVerdura()== false) {
			tipoComida.setEsVerdura(true);
			return ResponseUtil.success(tipoComidaService.guardar(tipoComida));
		}else {
			return ResponseUtil.badRequest(" la verdura está habilitada");
			
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<Boolean>> eliminarComida(@PathVariable Integer id) throws Exception {
		TipoComida tipoComida = tipoComidaService.buscarPorId(id);

		if (tipoComida == null)
			return ResponseUtil.badRequest("El ID no existe");

		return !tipoComida.isHabilitado() ? ResponseUtil.success(tipoComidaService.eliminar(id))
				: ResponseUtil.badRequest("No se puede eliminar. La comida no está deshabilitada.");
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<TipoComida>> handleException(Exception ex) {
		return ResponseUtil.badRequest(ex.getMessage());
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ApiResponse<TipoComida>> handleConstraintViolationException(ConstraintViolationException ex) {
		return ResponseUtil.handleConstraintException(ex);
	}

}
