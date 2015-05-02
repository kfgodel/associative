/**
 * 22/12/2013 17:52:46 Copyright (C) 2013 Darío L. García
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
package ar.dgarcia.procesador.fraccionables.impl;

import ar.com.kfgodel.tostring.ImplementedWithStringer;
import ar.com.kfgodel.tostring.Stringer;
import ar.dgarcia.procesador.fraccionables.api.DependenciaCircularException;
import ar.dgarcia.procesador.fraccionables.api.ProcesadorDeTareasParticionables;
import ar.dgarcia.procesador.fraccionables.api.ResultadoIterativo;
import ar.dgarcia.procesador.fraccionables.api.TareaParticionable;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * Esta clase es la implementación del procesador de tareas particionables.<br>
 * Esta instancia no es thread-safe. Cada thread debe crear su procesador
 * 
 * @author D. García
 */
public class ProcesadorDeParticionables implements ProcesadorDeTareasParticionables {

	private TareaParticionable<?> tareaActual;
	public static final String tareaActual_FIELD = "tareaActual";

	private ColaDeTareas pendientes;
	public static final String pendientes_FIELD = "pendientes";

	/**
	 * @see ar.dgarcia.procesador.fraccionables.api.ProcesadorDeTareasParticionables#procesar(ar.dgarcia.procesador.fraccionables.api.TareaParticionable)
	 */
	@Override
	public <R> ResultadoIterativo<R> procesar(final TareaParticionable<R> tarea) throws DependenciaCircularException {
		prepararComoProxima(tarea);

		this.procesarPendientes();

		final ResultadoIterativo<R> resultadoProcesado = tarea.getResultado();
		return resultadoProcesado;
	}

	/**
	 * Agrega la tarea a la cola de pendientes, preparándola para ser procesada en cualquier momento
	 * 
	 * @param tarea
	 *            La tarea a procesar
	 */
	private <R> void prepararComoProxima(final TareaParticionable<R> tarea) {
		tarea.prepararRecursos();
		pendientes.encolarProxima(tarea);
	}

	/**
	 * Procesa todas las tareas pendientes en el orden que están, agregando nuevas a medida que van
	 * surgiendo
	 */
	private void procesarPendientes() throws DependenciaCircularException {
		while (pendientes.tieneTareas()) {
			final TareaParticionable<?> tareaAprocesar = pendientes.sacarProxima();
			procesarPendiente(tareaAprocesar);
		}
	}

	/**
	 * Procesa la proxima tarea pendiente quitandola de la lista, y procesando sus consecuencias.<br>
	 * Si la tarea requiere varias iteraciones se vuelve a agregar como pendiente
	 */
	private void procesarPendiente(final TareaParticionable<?> tareaProcesada) {
		// La registramos como actual para los metodos que usan actual como implicito
		tareaActual = tareaProcesada;

		// La ejecutamos completamente (puede auto encolarse)
		tareaActual.ejecutarCon(this);

		// Si ya no está como pendiente, entonces terminamos con la tarea
		if (!pendientes.contieneA(tareaActual)) {
			tareaProcesada.liberarRecursos();
		}
		// Si esta como pendiente se liberará en otra vuelta
		tareaActual = null;
	}

	public static ProcesadorDeParticionables create() {
		final ProcesadorDeParticionables procesador = new ProcesadorDeParticionables();
		procesador.pendientes = ColaDeTareas.create();
		return procesador;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	@ImplementedWithStringer
	public String toString() {
		return Stringer.representationOf(this);
	}

	/**
	 * @see ar.dgarcia.procesador.fraccionables.api.ProcesadorDeTareasParticionables#procesarRequisitosYContinuarConActual(java.util.List)
	 */
	@Override
	public void procesarRequisitosYContinuarConActual(final List<? extends TareaParticionable<?>> requisitos) {
		// Primero ponemos la actual para cuando termine el resto
		pendientes.encolarProxima(tareaActual);

		// Recorremos desde atrás para mantener el orden
		for (int i = requisitos.size() - 1; i >= 0; i--) {
			final TareaParticionable<?> requisito = requisitos.get(i);
			prepararComoProxima(requisito);
		}
	}

	/**
	 * @see ar.dgarcia.procesador.fraccionables.api.ProcesadorDeTareasParticionables#procesarRequisitoYContinuarConActual(ar.dgarcia.procesador.fraccionables.api.TareaParticionable)
	 */
	@Override
	public void procesarRequisitoYContinuarConActual(final TareaParticionable<?> requisito) {
		// Invocamos como lista que sería el caso general de este método
		@SuppressWarnings("unchecked")
		final List<? extends TareaParticionable<?>> comoLista = Lists.newArrayList(requisito);
		procesarRequisitosYContinuarConActual(comoLista);
	}

}
