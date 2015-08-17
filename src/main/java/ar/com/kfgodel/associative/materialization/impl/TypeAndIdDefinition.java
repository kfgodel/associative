package ar.com.kfgodel.associative.materialization.impl;

import ar.com.kfgodel.associative.materialization.api.ExpectedObjectDefinition;

/**
 * Created by kfgodel on 17/08/15.
 */
public class TypeAndIdDefinition implements ExpectedObjectDefinition {

    private Class<?> expectedClass;
    private Long assignedId;


    @Override
    public Class<?> expectedClass() {
        return expectedClass;
    }

    @Override
    public Long getIdentificator() {
        return assignedId;
    }

    public static TypeAndIdDefinition create(Class<?> type, Long id) {
        TypeAndIdDefinition typeAndIdDefinition = new TypeAndIdDefinition();
        typeAndIdDefinition.assignedId = id;
        typeAndIdDefinition.expectedClass = type;
        return typeAndIdDefinition;
    }

}
