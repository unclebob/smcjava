package smc.generator.java.JavaCodeGenerators;

import smc.generator.java.SMJavaGenerator;
public class FSMClass extends JavaCodeGenerator
{
    public String generateCode(SMJavaGenerator gen)
    {
        JavaCodeGenerator itsStateVariable = new ItsStateVariables();
        JavaCodeGenerator constructor = new FSMConstructor();
        JavaCodeGenerator accessors = new FSMAccessors();
        JavaCodeGenerator events = new FSMEvents();
        JavaCodeGenerator baseState = new FSMBaseState();
        JavaCodeGenerator stateClasses = new FSMStateClasses();


        String fsmName = gen.getStateMap().getName();
        StringBuffer buff = new StringBuffer();
        buff.append(printSeparator(0));
        buff.append("//\n");
        buff.append("// class " +  fsmName+ "\n");
        buff.append("//    This is the Finite State Machine class\n");
        buff.append("//\n");
        buff.append("public class " + fsmName + " extends " + gen.getStateMap().getContextName() + "\n");
        buff.append("{\n");
        buff.append("  private State itsState;\n");
        buff.append("  private static String itsVersion = \"" + gen.getStateMap().getVersion() + "\";\n\n");
        buff.append("  // instance variables for each state\n");

        buff.append(itsStateVariable.generateCode(gen));
        buff.append(constructor.generateCode(gen));
        buff.append(accessors.generateCode(gen));
        buff.append(events.generateCode(gen));
        buff.append(baseState.generateCode(gen));
        buff.append(stateClasses.generateCode(gen));
        buff.append("}\n");

        return buff.toString();
    }
}
