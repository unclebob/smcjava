package smc.generator.cpp.CppCodeGenerators;

import smc.generator.cpp.SMCppGenerator;

import java.util.TimeZone;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Header extends CppCodeGenerator
{
    public String generateCode(SMCppGenerator gen)
    {
        StringBuffer buff = new StringBuffer();
        TimeZone tz = TimeZone.getDefault();
        SimpleDateFormat ifdefFormat = new SimpleDateFormat ("_MM_dd_yyyy_kk_mm_ss_");
        ifdefFormat.setTimeZone( tz );

        String ifndefName = "FSM_" + gen.getStateMap().getName() + ifdefFormat.format(new Date()) + "H";

        buff.append("#ifndef " + ifndefName + "\n");
        buff.append("#define " + ifndefName + "\n");

        buff.append(new FileComments().generateCode(gen));
        buff.append(new HeaderIncludes().generateCode(gen));

        if( gen.getNamespace().length() > 0 )
        {
            buff.append("namespace " + gen.getNamespace() + "\n");
            buff.append("{"+ "\n");
            buff.append("\n");
        }
        
        buff.append(new HeaderForwardDeclarations().generateCode(gen));
        buff.append(new BaseStateDeclarations().generateCode(gen));
        buff.append(new StateClassDeclarations().generateCode(gen));
        buff.append(new FSMClassDeclarations().generateCode(gen));

        buff.append("\n");

        if( gen.getNamespace().length() > 0 )
        {
            buff.append("}  // end namespace " + gen.getNamespace() + "\n");
            buff.append("\n") ;
        }

        buff.append("#endif /* " + ifndefName + " */" + "\n" );

        return buff.toString();
    }
}
