package org.fmgroup.mediator.language.statement;

import org.antlr.v4.runtime.ParserRuleContext;
import org.fmgroup.mediator.language.RawElement;
import org.fmgroup.mediator.language.ValidationException;
import org.fmgroup.mediator.language.generated.MediatorLangParser;
import org.fmgroup.mediator.language.term.Term;
import org.fmgroup.mediator.language.type.Type;

import java.util.Map;

public class FlowStatement implements Statement {

    private RawElement parent = null;
    private Term target = null;
    private Term derivative = null;

    public Term getTarget() {
        return target;
    }

    public FlowStatement setTarget(Term target) {
        this.target = target;
        target.setParent(this);
        return this;
    }

    public Term getDerivative() {
        return derivative;
    }

    public FlowStatement setDerivative(Term derivative) {
        this.derivative = derivative;
        derivative.setParent(this);
        return this;
    }

    @Override
    public FlowStatement fromContext(ParserRuleContext context, RawElement parent) throws ValidationException {
        if (!(context instanceof MediatorLangParser.FlowStatementContext)) {
            throw ValidationException.IncompatibleContextType(this.getClass(), "FlowStatementContext", context.toString());
        }

        setParent(parent);
        setDerivative(Term.parse(((MediatorLangParser.FlowStatementContext) context).der, this));
        setTarget(Term.parse(((MediatorLangParser.FlowStatementContext) context).target, this));
        return this;
    }

    @Override
    public String toString() {
        return "der(" + target.toString() + ") = " + derivative.toString() + ";";
    }

    @Override
    public RawElement getParent() {
        return parent;
    }

    @Override
    public FlowStatement setParent(RawElement parent) {
        this.parent = parent;
        return this;
    }

    @Override
    public RawElement copy(RawElement parent) throws ValidationException {
        FlowStatement nfs = new FlowStatement();
        nfs.setParent(parent);
        nfs.setTarget(target.copy(nfs));
        nfs.setDerivative(derivative.copy(nfs));

        return nfs;
    }

    @Override
    public Statement refactor(Map<String, Type> typeRewriteMap, Map<String, Term> termRewriteMap) throws ValidationException {
        setDerivative(derivative.refactor(typeRewriteMap, termRewriteMap));
        setTarget(target.refactor(typeRewriteMap, termRewriteMap));

        return this;
    }
}
