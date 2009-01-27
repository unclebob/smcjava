package smc.generator.cpp.CppCodeGenerators;

import smc.builder.FSMRepresentationBuilder;
import smc.fsmrep.ConcreteStateImpl;

public class TestCppCodeGeneratorUtils
{
     public static FSMRepresentationBuilder initBuilderState()
    {
        return initState(true);
    }
    public static FSMRepresentationBuilder initBuilderStateWithoutExceptions()
    {
        return initState(false);
    }
    public static   FSMRepresentationBuilder initBuildStateWithInclude()
    {
        FSMRepresentationBuilder builder = new FSMRepresentationBuilder();
        builder.addPragma("include aClass");
        builder.addPragma("include bClass");

        return builder;
    }

    private static FSMRepresentationBuilder initState(boolean usesExceptions)
    {
        FSMRepresentationBuilder builder = new FSMRepresentationBuilder();
        builder.addPragma("namespace TurnStyleExample");
        builder.addPragma("Header  stContext.h");
        builder.setName("TurnStyle");
        builder.setContextName("TurnStyleContext");
        builder.setVersion("");

        if(usesExceptions)
            builder.setException("Exception");

        builder.addBuiltConcreteState(new ConcreteStateImpl("Locked"));
        builder.addBuiltConcreteState(new ConcreteStateImpl("Unlocked"));

        builder.setInitialState("Locked");

        builder.addTransition("Coin","Unlocked",null);
        builder.addTransition("Pass","Locked",null);

        builder.addAction("Unlock");
        builder.addAction("Lock");
        builder.addAction("Alarm");
        builder.addAction("Thankyou");

        builder.build();
        return builder;
    }
}
