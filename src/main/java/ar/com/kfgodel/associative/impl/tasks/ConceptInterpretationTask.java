package ar.com.kfgodel.associative.impl.tasks;

import ar.com.kfgodel.associative.api.Identity;
import ar.com.kfgodel.associative.api.config.Analyzer;
import ar.com.kfgodel.associative.api.config.Synthesizer;
import ar.com.kfgodel.associative.api.context.InterpretationContext;
import ar.com.kfgodel.associative.impl.context.InterpretationContextImpl;
import ar.com.kfgodel.decomposer.api.DecomposableTask;
import ar.com.kfgodel.decomposer.api.context.DecomposedContext;
import ar.com.kfgodel.decomposer.api.results.DelayResult;
import ar.com.kfgodel.nary.api.Nary;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This type represents the task of interpreting a concept
 * Created by kfgodel on 13/05/15.
 */
public class ConceptInterpretationTask implements DecomposableTask {

    private Object entity;
    private InterpretationContext context;

    @Override
    public Object executeUnder(DecomposedContext taskContext) {
        Analyzer analyzer = context.getBestAnalyzerFor(entity);
        Nary<Object> parts = analyzer.analyse(entity);
        List<DecomposableTask> interpretationSubProcesses = parts.map(context::getBestProcessFor)
                .collect(Collectors.toList());
        return DelayResult.waitingFor(interpretationSubProcesses)
                .andFinally(this::makeASynthesis);
    }

    private Object makeASynthesis(DecomposedContext decomposedContext) {
        Synthesizer synthesizer = context.getBestSynthesizerFor(entity);
        List<Identity> subTaskResults = decomposedContext.getSubTaskResults();
        Object entityInterpretation = synthesizer.synthesize(subTaskResults, context);
        Identity entityIdentity = context.getIdentityFor(entity);
        context.storeInterpretation(entityIdentity, entityInterpretation);
        return entityIdentity;
    }

    public static ConceptInterpretationTask create(Object entity, InterpretationContextImpl interpretationContext) {
        ConceptInterpretationTask task = new ConceptInterpretationTask();
        task.entity = entity;
        task.context = interpretationContext;
        return task;
    }

}
