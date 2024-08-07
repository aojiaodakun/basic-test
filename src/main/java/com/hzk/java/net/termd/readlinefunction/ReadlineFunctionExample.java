package com.hzk.java.net.termd.readlinefunction;

import com.hzk.java.net.termd.readline.ReadlineExample;
import io.termd.core.readline.Function;
import io.termd.core.readline.Functions;
import io.termd.core.readline.Keymap;
import io.termd.core.readline.Readline;
import io.termd.core.tty.TtyConnection;

/**
 * Shows how to extend readline with custom functions.
 */
public class ReadlineFunctionExample {

  public static void handle(TtyConnection conn) {

    // The reverse function simply reverse the edit buffer
    Function reverseFunction = new ReverseFunction();

    ReadlineExample.readline(
        // Bind reverse to Ctrl-g to the reverse function
        new Readline(Keymap.getDefault().bindFunction("\\C-g", "reverse")).
            addFunctions(Functions.loadDefaults()).addFunction(reverseFunction),
        conn);
  }

}
