/**
 * 22/12/2013 18:32:56 Copyright (C) 2013 Darío L. García
 * 
 * <a rel="license" href="http://creativecommons.org/licenses/by/3.0/"><img
 * alt="Creative Commons License" style="border-width:0"
 * src="http://i.creativecommons.org/l/by/3.0/88x31.png" /></a><br />
 * <span xmlns:dct="http://purl.org/dc/terms/" href="http://purl.org/dc/dcmitype/Text"
 * property="dct:title" rel="dct:type">Software</span> by <span
 * xmlns:cc="http://creativecommons.org/ns#" property="cc:attributionName">Darío García</span> is
 * licensed under a <a rel="license" href="http://creativecommons.org/licenses/by/3.0/">Creative
 * Commons Attribution 3.0 Unported License</a>.
 */
package ar.dgarcia.procesador.fraccionables;

import ar.com.kfgodel.decomposer.api.DependenciaCircularException;
import ar.com.kfgodel.decomposer.api.ProcesadorDeTareasParticionables;
import ar.com.kfgodel.decomposer.api.ResultadoIterativo;
import ar.com.kfgodel.decomposer.api.TareaParticionable;
import ar.com.kfgodel.decomposer.impl.*;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase prueba algunos casos de uso del {@link ProcesadorDeTareasParticionables}
 * 
 * @author D. García
 */
public class ProcesadorParticionablesTest {

	private ProcesadorDeTareasParticionables procesador;

	@Before
	public void crearProcesador() {
		procesador = ProcesadorDeParticionables.create();
	}

	@Test
	public void deberiaDetectarUnaDependenciaCircularDeLaTarea() {
		final TareaParticionableSupport<Void> tareaQueDependeDeSiMisma = new TareaParticionableSupport<Void>() {
			@Override
			public void ejecutarCon(final ProcesadorDeTareasParticionables procesador) {
				procesador.procesarRequisitoYContinuarConActual(this);
			}
		};
		try {
			procesador.procesar(tareaQueDependeDeSiMisma);
			Assert.fail("No debería procesar la tarea");
		} catch (final DependenciaCircularException e) {
			// Es la excepción que esperabamos
		}
	}

	@Test
	public void deberiaProcesarPrimeroLosRequisitosLuegoLaTareaYLuegoLasConsecuenciasAntesDeTerminar() {
		final List<Integer> ordenDeProcesado = new ArrayList<>();
		final TareaDePrueba tareaConPartes = TareaDePrueba.create(3, ordenDeProcesado);
		tareaConPartes.getRequisitos().add(TareaDePrueba.create(1, ordenDeProcesado));
		tareaConPartes.getRequisitos().add(TareaDePrueba.create(2, ordenDeProcesado));

		procesador.procesar(tareaConPartes);

		Assert.assertEquals("Deberían haber 3 ejecuciones", 3, ordenDeProcesado.size());
		for (int i = 0; i < ordenDeProcesado.size(); i++) {
			Assert.assertEquals("Debería ser el numero de tarea esperado", i + 1, ordenDeProcesado.get(i).intValue());
		}
	}

	@Test
	public void siNoSeRePlanificaDeberiaTerminarAunqueLaTareaTengaIteracionesPendientes() {
		final TareaParticionable<Void> tareaSinValorFinal = new TareaParticionableSupport<Void>() {
			@Override
			public void ejecutarCon(final ProcesadorDeTareasParticionables procesador) {
				// No hacemos nada. El resultado debería indicar que quedan iteraciones pendientes
			}
		};

		procesador.procesar(tareaSinValorFinal);

		final ResultadoIterativo<Void> resultado = tareaSinValorFinal.getResultado();
		Assert.assertEquals("Deberian quedar iteraciones pendientes", true, resultado.quedanIteraciones());
	}

	@Test
	public void deberiaProcesarCadaPasoDeUnaTareaEnPasosPorReflection() {
		final List<Integer> ordenDeProcesado = new ArrayList<>();
		final TareaEnPasosTemplate<Integer> tareaEnPasos = new TareaEnPasosTemplate<Integer>() {
			@MetodoComoSubtarea(orden = 0)
			public void primerPaso(final ProcesadorDeTareasParticionables procesador) {
				// Como primer paso creamos una subtarea y la procesamos antes de seguir al 2
				final TareaDePrueba requisito = TareaDePrueba.create(1, ordenDeProcesado);
				procesador.procesarRequisitoYContinuarConActual(requisito);
			}

			@MetodoComoSubtarea(orden = 1)
			public void segundoPaso() {
				// Creamos otra tarea y la procesamos antes de pasar al 3
				final TareaDePrueba requisito = TareaDePrueba.create(2, ordenDeProcesado);
				procesador.procesarRequisitoYContinuarConActual(requisito);
			}

			@MetodoComoSubtarea(orden = 3)
			public void tercerPaso(final TareaPorReflection<Integer> self) {
				// Ultimo paso
				ordenDeProcesado.add(3);
			}
		};

		procesador.procesar(tareaEnPasos);

		Assert.assertEquals("Deberían haber 3 ejecuciones", 3, ordenDeProcesado.size());
		for (int i = 0; i < ordenDeProcesado.size(); i++) {
			Assert.assertEquals("Debería ser el numero de tarea esperado", i + 1, ordenDeProcesado.get(i).intValue());
		}

	}
}
