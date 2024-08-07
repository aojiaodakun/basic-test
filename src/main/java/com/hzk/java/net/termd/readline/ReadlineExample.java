package com.hzk.java.net.termd.readline;

import com.hzk.java.net.termd.util.Helper;
import io.termd.core.function.Consumer;
import io.termd.core.readline.Functions;
import io.termd.core.readline.Readline;
import io.termd.core.tty.TtyConnection;

/**
 * Shows how to use async Readline.
 */
public class ReadlineExample {

  public static void handle(TtyConnection conn) {
    readline(
        new Readline(Helper.loadKeymap()).addFunctions(Functions.loadDefaults()),
        conn);
  }

  public static void readline(final Readline readline, final TtyConnection conn) {
    readline.readline(conn, "% ", new Consumer<String>() {
      @Override
      public void accept(String line) {
        if (line == null) {
          conn.write("Logout").close();
        } else {
          conn.write("User entered " + line + "\n");

          // Read line again
          readline(readline, conn);
        }
      }
    });
  }
}
