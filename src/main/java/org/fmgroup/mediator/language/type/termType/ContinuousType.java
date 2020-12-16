package org.fmgroup.mediator.language.type.termType;

import org.antlr.v4.runtime.ParserRuleContext;
import org.fmgroup.mediator.language.RawElement;
import org.fmgroup.mediator.language.ValidationException;
import org.fmgroup.mediator.language.generated.MediatorLangParser;
import org.fmgroup.mediator.language.term.Term;
import org.fmgroup.mediator.language.type.Type;

import java.util.Map;

public class ContinuousType implements Type{

    private RawElement parent = null;


    @Override
    public ContinuousType fromContext(ParserRuleContext context, RawElement parent) throws ValidationException {
        if (!(context instanceof MediatorLangParser.ContinousTypeContext)) {
            throw ValidationException.IncompatibleContextType(this.getClass(), "ContinuousTypeContext", context.toString());
        }

        setParent(parent);
        return this;
    }

    @Override
    public RawElement getParent() {
        return parent;
    }

    @Override
    public ContinuousType setParent(RawElement parent) {
        this.parent = parent;
        return this;
    }

    @Override
    public String toString() {
        return "conti";
    }

    @Override
    public ContinuousType copy(RawElement parent) throws ValidationException {
        return new ContinuousType().setParent(parent);
    }

    @Override
    public ContinuousType refactor(Map<String, Type> typeRewriteMap, Map<String, Term> termRewriteMap) throws ValidationException {
        return this;
    }
}
