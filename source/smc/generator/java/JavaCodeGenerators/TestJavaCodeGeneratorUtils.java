package smc.generator.java.JavaCodeGenerators;

import smc.builder.FSMRepresentationBuilder;
import smc.fsmrep.ConcreteState;
import smc.fsmrep.ConcreteStateImpl;

import java.util.Vector;

public class TestJavaCodeGeneratorUtils
{
     public static FSMRepresentationBuilder initBuilderState()
    {
        FSMRepresentationBuilder fsmbld = new FSMRepresentationBuilder();
        fsmbld.addPragma("package testPackage");
        fsmbld.addPragma("import aClass");
        fsmbld.addPragma("import bClass");
        fsmbld.setName("FileName");
        fsmbld.setContextName("ContextName");
        fsmbld.setVersion("TheVersion");
        fsmbld.setException("Exception");
        fsmbld.setInitialState("Locked");
        ConcreteState c = new ConcreteStateImpl("Locked");
        Vector entryVector = new Vector();
        entryVector.add("possiblyLocked");
        c.setEntryActions(entryVector);
        ConcreteState c1 = new ConcreteStateImpl("NotLocked");
        fsmbld.addBuiltConcreteState(c);
        fsmbld.addBuiltConcreteState(c1);
        fsmbld.addSuperState("SuperState",null);
        fsmbld.addSubState("SubState","SuperState",null);
        fsmbld.addTransition("test","NotLocked",null);

        fsmbld.build();
        return fsmbld;
    }
}
