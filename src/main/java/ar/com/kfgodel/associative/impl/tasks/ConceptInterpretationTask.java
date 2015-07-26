package ar.com.kfgodel.associative.impl.tasks;

import ar.com.kfgodel.associative.api.Identity;
import ar.com.kfgodel.associative.api.config.Analyzer;
import ar.com.kfgodel.associative.api.config.Synthesizer;
import ar.com.kfgodel.associative.api.context.InterpretationContext;
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

    private Identity entityIdentity;
    private Object entity;
    private InterpretationContext context;


    @Override
    public Object executeUnder(DecomposedContext taskContext) {
        return this.analyzeEntity();
    }

    protected Object analyzeEntity() {
        Analyzer analyzer = getContext().getBestAnalyzerFor(getEntity());
        Nary<Object> parts = analyzer.analyse(getEntity());
        List<DecomposableTask> interpretationSubProcesses = parts.map(getContext()::getBestInterpretationProcessFor)
                .collect(Collectors.toList());
        return DelayResult.waitingFor(interpretationSubProcesses)
                .andFinally(this::makeASynthesis);
    }

    private Object makeASynthesis(DecomposedContext decomposedContext) {
        Synthesizer synthesizer = getContext().getBestSynthesizerFor(getEntity());
        List<Identity> subTaskResults = decomposedContext.getSubTaskResults();
        Object entityInterpretation = synthesizer.synthesize(subTaskResults, getContext());
        getContext().storeInterpretation(entityIdentity, entityInterpretation);
        return entityIdentity;
    }

    public Object getEntity() {
        return entity;
    }

    public InterpretationContext getContext() {
        return context;
    }

    public static ConceptInterpretationTask create(Identity entityIdentity, Object entity, InterpretationContext interpretationContext) {
        ConceptInterpretationTask task = new ConceptInterpretationTask();
        task.entityIdentity = entityIdentity;
        task.entity = entity;
        task.context = interpretationContext;
        return task;
    }


}
