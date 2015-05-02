/**
 * 04/01/2014 16:37:43 Copyright (C) 2014 Darío L. García
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

import ar.com.kfgodel.tostring.Stringer;
import ar.dgarcia.procesador.fraccionables.api.ProcesadorDeTareasParticionables;
import ar.dgarcia.procesador.fraccionables.api.ResultadoIterativo;
import ar.dgarcia.procesador.fraccionables.api.TareaConPadre;
import ar.dgarcia.procesador.fraccionables.api.TareaParticionable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase representa una tarea que se resuelve ejecutando un método indicado por nombre.<br>
 * El método debe aceptar un {@link ProcesadorDeParticionables} como argumento
 * 
 * @author D. García
 */
public class TareaPorReflection<R> extends TareaParticionableSupport<R> implements TareaConPadre<R> {

	private Integer orden;
	public static final String orden_FIELD = "orden";

	private Method metodo;
	public static final String metodo_FIELD = "metodo";

	private Object instancia;
	private TareaParticionable<R> tareaPadre;

	/**
	 * Devuelve el número de orden de ejecución de esta tarea si es que existe uno
	 */
	public Integer getOrden() {
		return orden;
	}

	public void setOrden(final Integer orden) {
		this.orden = orden;
	}

	/**
	 * @see ar.dgarcia.procesador.fraccionables.api.TareaParticionable#ejecutarCon(ar.dgarcia.procesador.fraccionables.api.ProcesadorDeTareasParticionables)
	 */
	@Override
	public void ejecutarCon(final ProcesadorDeTareasParticionables procesador) {
		final Object retornadoPorMetodo = invocarMetodo(procesador);
		if (retornadoPorMetodo instanceof ResultadoIterativo) {
			// Nos indican el resultado directamente
			@SuppressWarnings("unchecked")
			final ResultadoIterativo<R> resultadoDirecto = (ResultadoIterativo<R>) retornadoPorMetodo;
			setResultado(resultadoDirecto);
		} else {
			// Nos indican resultado por valor
			@SuppressWarnings("unchecked")
			final R valorRetornado = (R) retornadoPorMetodo;
			setValorFinal(valorRetornado);
		}
	}

	/**
	 * @param procesador
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private R invocarMetodo(final ProcesadorDeTareasParticionables procesador) {
		final Object[] argumentos = adaptarArgumentosSegunFirmaDeMetodo(procesador);
		try {
			metodo.setAccessible(true);
			final Object retorno = metodo.invoke(instancia, argumentos);
			return (R) retorno;
		} catch (final IllegalAccessException e) {
			throw new RuntimeException("El metodo no permite acceso", e);
		} catch (final IllegalArgumentException e) {
			throw new RuntimeException("Los argumentos pasados no estan bien armados", e);
		} catch (final InvocationTargetException e) {
			throw new RuntimeException("Se produjo un error interno en el metodo de una tarea", e);
		} catch (final NullPointerException e) {
			throw new RuntimeException("Se paso null como argumento?", e);
		} catch (final ExceptionInInitializerError e) {
			throw new RuntimeException(
					"Se produjo un error en la inicializacion de una clase al invocar un método?", e);
		}
	}

	/**
	 * Crea los argumentos necesarios segun la firma
	 * 
	 * @param procesador
	 *            El procesador para incluir en argumentos
	 * 
	 * @return El array de argumentos
	 */
	private Object[] adaptarArgumentosSegunFirmaDeMetodo(final ProcesadorDeTareasParticionables procesador) {
		final List<Object> argumentosArmados = new ArrayList<>();
		final Class<?>[] methodArgumentTypes = metodo.getParameterTypes();
		for (int i = 0; i < methodArgumentTypes.length; i++) {
			final Class<?> methodArgumentType = methodArgumentTypes[i];
			if (ProcesadorDeTareasParticionables.class.isAssignableFrom(methodArgumentType)) {
				// Si quieren un procesador usamos el que nos llama
				argumentosArmados.add(procesador);
			} else if (TareaPorReflection.class.isAssignableFrom(methodArgumentType)) {
				// Si quieren la referencia a la tarea real nos pasamos
				argumentosArmados.add(this);
			} else {
				// Por cualquier otra cosa que quieran, no tenemos nada
				argumentosArmados.add(null);
			}
		}
		return argumentosArmados.toArray();
	}

	/**
	 * @see ar.dgarcia.procesador.fraccionables.api.TareaConPadre#getTareaPadre()
	 */
	@Override
	public TareaParticionable<R> getTareaPadre() {
		return tareaPadre;
	}

	/**
	 * @see ar.dgarcia.procesador.fraccionables.api.TareaConPadre#setTareaPadre(ar.dgarcia.procesador.fraccionables.api.TareaParticionable)
	 */
	@Override
	public void setTareaPadre(final TareaParticionable<R> tareaPadre) {
		this.tareaPadre = tareaPadre;
	}

	public static <R> TareaPorReflection<R> create(final Method metodo, final Object instancia) {
		final TareaPorReflection<R> tarea = new TareaPorReflection<>();
		tarea.metodo = metodo;
		tarea.instancia = instancia;
		return tarea;
	}

	/**
	 * @see ar.dgarcia.procesador.fraccionables.impl.TareaParticionableSupport#toString()
	 */
	@Override
	public String toString() {
		return Stringer.representationOf(this);
	}
}
