package smc.generator.java;

import smc.generator.java.JavaCodeGenerators.*;

import java.util.*;

public class JavaCodeGeneratorBuilder
{
    private static Class[] javaCodeGenerators = new Class[]
    {
        InitialComments.class,
        PackageStatement.class,
        ImportStatements.class,
        FSMClass.class
    };
    private static Class[] javaFSMCodeGenerators = new Class[]
    {
        ItsStateVariables.class,
        FSMConstructor.class,
        FSMAccessors.class,
        FSMEvents.class,
        FSMBaseState.class,
        FSMStateClasses.class
    };

    public static JavaCodeGeneratorBuilder javaCode = new JavaCodeGeneratorBuilder();

    public List javaFSMInstances()
    {
        return instances(javaFSMCodeGenerators);
    }
    public List javaInstances()
    {
        return instances(javaCodeGenerators);
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
        {
        }
        return instances;
    }

}
