package com.ceiba.usuario.servicio;

import com.ceiba.usuario.modelo.entidad.Usuario;
import com.ceiba.usuario.puerto.repositorio.RepositorioUsuario;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionLongitudValor;
import com.ceiba.usuario.servicio.testdatabuilder.UsuarioTestDataBuilder;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.mockito.Mockito;

import com.ceiba.BasePrueba;

public class ServicioCrearUsuarioTest {

	
	@Test
	public void validarUsuarioExistenciaPreviaTest() {
		// arrange
		Usuario usuario = new UsuarioTestDataBuilder().build();
		RepositorioUsuario repositorioUsuario = Mockito.mock(RepositorioUsuario.class);
		Mockito.when(repositorioUsuario.existe(Mockito.anyString())).thenReturn(true);
		ServicioCrearUsuario servicioCrearUsuario = new ServicioCrearUsuario(repositorioUsuario);
		// act - assert
		BasePrueba.assertThrows(() -> servicioCrearUsuario.ejecutar(usuario), ExcepcionDuplicidad.class,
				"El usuario ya existe en el sistema");
	}

	@Test
	public void crearUsuarioTest() {
		// arrange
		Usuario usuario = new UsuarioTestDataBuilder().build();
		Long codigoDeRespuestaEsperada = 1L;
		RepositorioUsuario repositorioUsuario = Mockito.mock(RepositorioUsuario.class);
		Mockito.when(repositorioUsuario.existe(Mockito.anyString())).thenReturn(false);
		Mockito.when(repositorioUsuario.crear(Mockito.anyObject())).thenReturn(1L);

		ServicioCrearUsuario servicioCrearUsuario = new ServicioCrearUsuario(repositorioUsuario);
		// act
		Long respuestaSolicitud = servicioCrearUsuario.ejecutar(usuario);

		// assert
		assertEquals(codigoDeRespuestaEsperada, respuestaSolicitud);

	}
}
