#ifndef _H_stripperContext
#define _H_stripperContext

// $Id: stContext.h,v 1.1.1.1 2001/07/17 22:59:22 micah Exp $
#include <iostream>
using namespace std;

//
// This is the context class of the comment stripper.
// This class knows about the input and output streams,
// How to read and write chars, and which critical chars to
// look for in the input stream.
//

class StripperContext
{
  private:
    char itsChar;
    istream *itsIStream;
    ostream *itsOStream;
    
  public:
    StripperContext() { }
    
    void SetStreams(istream& i, ostream& o)
    {
	    itsIStream = &i;
	    itsOStream = &o;
    }
    
    void FSMError(const char* t, const char* s)
    {cerr << "Transition error: " << t << " in state " << s << endl;}
    
    int ReadChar()
    {
	    int c;
      c = itsIStream->get();
      itsChar = c;
      return c;
    }
    void PutChar() {*itsOStream << itsChar;};
    void PutSlash() {*itsOStream << '/';};

    void OutsideIn() {cerr << "OutsideIn" << endl;}
    void OutsideOut() {cerr << "OutsideOut" << endl;}
    void StartingSlashIn() {cerr << "StartingSlashIn" << endl;}
    void StartingSlashOut() {cerr << "StartingSlashOut" << endl;}
    void SecondSlashIn() {cerr << "SecondSlashIn" << endl;}
    void SecondSlashOut() {cerr << "SecondSlashOut" << endl;}
    void InCommentIn() {cerr << "InCommentIn" << endl;}
    void InCommentOut() {cerr << "InCommentOut" << endl;}
    void StarAfterSlashIn() {cerr << "StarAfterSlashIn" << endl;}
    void StarAfterSlashOut() {cerr << "StarAfterSlashOut" << endl;}
    void StartingStarIn() {cerr << "StartingStarIn" << endl;}
    void StartingStarOut() {cerr << "StartingStarOut" << endl;}
};

#endif
