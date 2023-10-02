package com.mx.Banda.dao.controller;

import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.Banda.entidad.Banda;
import com.mx.Banda.respuesta.Respuesta;
import com.mx.Banda.service.ImpBanda;

import net.sf.jasperreports.engine.JRException;

@RequestMapping("Bandas")
@RestController
@CrossOrigin
public class BandaWS {
	@Autowired
	ImpBanda impBanda;
	@PostMapping("guardar")
	public Respuesta guardar(@RequestBody Banda banda) {
		return impBanda.guardar(banda);
	}
	@PostMapping("editar")
	public Respuesta editar(@RequestBody Banda banda) {
		return impBanda.editar(banda);
	}
	@PostMapping("eliminar")
	public Respuesta eliminar(@RequestBody Banda banda) {
		return impBanda.eliminar(banda);
	}
	@PostMapping("buscar")
	public Respuesta buscar(@RequestBody String nombre) {
		return impBanda.buscar(nombre);
	}
	@GetMapping("mostrar")
	public Respuesta mostrar() {
		return impBanda.mostrar();
	}
	@PostMapping("mostrarEscenarios")
	public Respuesta mostrarEscenarios(@RequestBody String nombre) {
		return impBanda.mostrarEscenarios(nombre);
	}
	@GetMapping("mostrarEscenariosPaisOrigen")
	public Respuesta mostrarEscenariosPaisOrigen() {
		return impBanda.mostrarEscenariosPaisOrigen();
	}
	@GetMapping("mostrarTotalAsistentes")
	public Respuesta mostrarTotalAsistentes() {
		return impBanda.mostrarTotalAsistentes();
	}
	@GetMapping("mostrarBandaGrande")
	public Respuesta mostrarBandaGrande() {
		return impBanda.mostrarBandaGrande();
	}
	@PostMapping("mostrarBandaPais")
	public Respuesta mostrarBandaPais(@RequestBody String pais) {
		return impBanda.mostrarBandaPais(pais);
	}
	@PostMapping("mostrarEscenarioGenero")
	public Respuesta mostrarEscenarioGenero(@RequestBody String genero) {
		return impBanda.mostrarEscenarioGenero(genero);
	}
	@GetMapping("mostrarBandaExitosa")
	public Respuesta mostrarBandaExitosa() {
		return impBanda.mostrarBandaExitosa();
	}
	@PostMapping("mostrarEscenarioFavorito")
	public Respuesta mostrarEscenarioFavorito(@RequestBody String nombre) {
		return impBanda.mostrarEscenarioFavorito(nombre);
	}
	@PostMapping("buscarBandaEscenario")
	public Respuesta buscarBandaEscenario(@RequestBody String nombre) {
		return impBanda.buscarBandaEscenario(nombre);
	}
	@GetMapping("/export-pdf")
    public ResponseEntity<byte[]> exportPdf() throws JRException, FileNotFoundException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("bandas", "bandas.pdf");
        return ResponseEntity.ok().headers(headers).body(impBanda.exportPdf());
    }

    @GetMapping("/export-xls")
    public ResponseEntity<byte[]> exportXls() throws JRException, FileNotFoundException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet; charset=UTF-8");
        var contentDisposition = ContentDisposition.builder("attachment")
                .filename("bandas" + ".xls").build();
        headers.setContentDisposition(contentDisposition);
        return ResponseEntity.ok()
                .headers(headers)
                .body(impBanda.exportXls());
    }
	
	
}
