package ar.com.kfgodel.associative.materialization.impl.materializers;

import ar.com.kfgodel.associative.materialization.api.ExpectedObjectDefinition;
import ar.com.kfgodel.associative.materialization.api.config.Materializer;
import ar.com.kfgodel.associative.materialization.api.context.MaterializationContext;
import ar.com.kfgodel.associative.materialization.impl.tasks.MaterializationFromContextTask;
import ar.com.kfgodel.associative.materialization.impl.tasks.PerceptMaterializationTask;
import ar.com.kfgodel.decomposer.api.DecomposableTask;

/**
 * This type represents the materializer that can generate percepts back
 * Created by kfgodel on 17/08/15.
 */
public class PerceptMaterializer implements Materializer {

    @Override
    public DecomposableTask describeProcessFor(ExpectedObjectDefinition definition, MaterializationContext materializationContext) {
        return MaterializationFromContextTask.create(definition, materializationContext, (contextualizer)-> PerceptMaterializationTask.create(definition, materializationContext, contextualizer));
    }

    public static PerceptMaterializer create() {
        PerceptMaterializer perceptMaterializer = new PerceptMaterializer();
        return perceptMaterializer;
    }

}
