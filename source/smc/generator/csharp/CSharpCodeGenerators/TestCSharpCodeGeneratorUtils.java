package smc.generator.csharp.CSharpCodeGenerators;

import smc.builder.FSMRepresentationBuilder;
import smc.fsmrep.ConcreteStateImpl;

public class TestCSharpCodeGeneratorUtils
{
    public static FSMRepresentationBuilder initBuilderState()
    {
        return initState(true);
    }
    public static FSMRepresentationBuilder initBuilderStateWithoutExceptions()
    {
        return initState(false);
    }
    public static   FSMRepresentationBuilder initBuildStateWithUsing()
    {
        FSMRepresentationBuilder builder = new FSMRepresentationBuilder();
        builder.addPragma("using aClass");
        builder.addPragma("using bClass");

        return builder;
    }

    private static FSMRepresentationBuilder initState(boolean usesExceptions)
    {
        FSMRepresentationBuilder builder = new FSMRepresentationBuilder();
        builder.addPragma("namespace TurnStyleExample");
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
