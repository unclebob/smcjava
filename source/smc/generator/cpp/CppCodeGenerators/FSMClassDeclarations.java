package smc.generator.cpp.CppCodeGenerators;

import smc.generator.cpp.SMCppGenerator;
import smc.fsmrep.ConcreteState;

import java.util.*;

public class FSMClassDeclarations extends CppCodeGenerator
{
    public String generateCode(SMCppGenerator gen)
    {
        StringBuffer buff = new StringBuffer();
        String className = gen.getStateMap().getName() + ": The Finite State Machine class";

        buff.append(printSeparator(className ));
        buff.append("class " + gen.getStateMap().getName() + ": public " + gen.getStateMap().getContextName() + "\n");
        buff.append("{"+ "\n");
        buff.append("  public: "+ "\n");
        buff.append("    // Static State variables" + "\n");

        List states = gen.getConcreteStates();
        for(int i =0;i!=states.size();i++)
        {
            ConcreteState cs = (ConcreteState)states.get(i);
             buff.append("    static " + gen.makeStateName(cs.getName()) + " " + cs.getName() + ";"+ "\n");

        }

        buff.append("\n");
        buff.append("    " + gen.getStateMap().getName() + "(); // default Constructor" + "\n");

        buff.append("\n");
        buff.append("    // Event functions" + "\n");

        HashSet events = gen.getStateMap().getEvents();
        Iterator evi = events.iterator();
        while( evi.hasNext() )
        {
            String evName = (String)evi.next();
            buff.append("    virtual void " + evName +    "() { itsState->" + evName + "( *this ); }" + "\n");
        }

        buff.append("\n");
        buff.append("    // State Accessor functions" + "\n");
        buff.append("    void SetState( " + gen.makeStateName("") + "& theState ) { itsState = &theState; }"+ "\n" );
        buff.append("    " + gen.makeStateName("") +  "& GetState() const { return *itsState; }" + "\n");
        buff.append("\n");
        buff.append("    const char* GetCurrentStateName() const {" + " return itsState->StateName(); }" + "\n");
        buff.append("    const char* GetVersion() const;" + "\n" );
        buff.append("\n");
        buff.append("  private: "+ "\n");
        buff.append("    " + gen.makeStateName("") + "* itsState;"+ "\n");
        buff.append("};"+ "\n");
        return buff.toString();
    }
}
