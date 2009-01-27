package smc.generator.csharp;

import smc.generator.csharp.CSharpCodeGenerators.*;


import java.util.List;
import java.util.ArrayList;

public class CSharpCodeGeneratorBuilder
{
    private static Class[] cSharpCodeGenerators = new Class[]
    {
        InitialComments.class,
        NamespaceStatement.class,
        UsingStatements.class,
        FSMClass.class
    };
    private static Class[] csharpFSMCodeGenerators = new Class[]
    {
        ItsStateVariables.class,
        FSMConstructor.class,
        FSMAccessors.class,
        FSMMutators.class,
        FSMEvents.class
    };
    private static Class[] csharpFSMClassesCodeGenerators = new Class[]
    {
        FSMBaseState.class,
        FSMStateClasses.class
    };

    public static CSharpCodeGeneratorBuilder cSharpCode = new CSharpCodeGeneratorBuilder();

    public List cSharpFSMInstances()
    {
        return instances(csharpFSMCodeGenerators);
    }
    public List cSharpFSMClassesInstances()
    {
        return instances(csharpFSMClassesCodeGenerators);
    }
    public List cSharpInstances()
    {
        return instances(cSharpCodeGenerators);
    }
    private List instances(Class[] c)
    {
        List instances = null;
        try
        {
            instances = new ArrayList();
            for(int i = 0; i != c.length;i++)
                instances.add(c[i].newInstance());
        }
        catch(Exception e)
        {}
        return instances;

    }

}
