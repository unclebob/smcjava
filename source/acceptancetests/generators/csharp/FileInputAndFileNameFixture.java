package acceptancetests.generators.csharp;

import fitnesse.fixtures.TableFixture;
import smc.generator.csharp.SMCSharpGenerator;
import smc.generator.FSMGenerator;
import smc.builder.FSMRepresentationBuilder;
import smc.fsmrep.ConcreteStateImpl;
import smc.fsmrep.StateMap;

public class FileInputAndFileNameFixture  extends TableFixture
{
    protected void doStaticTable(int i)
    {
        FSMGenerator fsm = new SMCSharpGenerator();
        FSMRepresentationBuilder fsmbld = initState();
        StateMap map = fsmbld.getStateMap() ;
        fsm.FSMInit(map,getText(2,1),getText(1,1));
        try
        {
            fsm.initialize();
        }
        catch(Exception e)
        {e.printStackTrace();}

        InputFileAndDirectoryName(fsm);

    }

    private void InputFileAndDirectoryName(FSMGenerator fsm)
    {
        if(getText(1,1).equals(fsm.getDirectory()))
            right(1,1);
        else
            wrong(1,1);

        if(getText(2,1).equals(fsm.getInputFileName()))
            right(2,1);
        else
            wrong(2,1);
    }

    private  FSMRepresentationBuilder initState()
    {
        FSMRepresentationBuilder builder = new FSMRepresentationBuilder();
        builder.addPragma("namespace TurnStyleExample");
        builder.addPragma("Header  stContext.h");
        builder.setName("TurnStyle");
        builder.setContextName("TurnStyleContext");
        builder.setVersion("");

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
