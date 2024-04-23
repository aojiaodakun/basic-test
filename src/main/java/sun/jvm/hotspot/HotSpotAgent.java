//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package sun.jvm.hotspot;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.rmi.RemoteException;
import sun.jvm.hotspot.debugger.Debugger;
import sun.jvm.hotspot.debugger.DebuggerException;
import sun.jvm.hotspot.debugger.JVMDebugger;
import sun.jvm.hotspot.debugger.MachineDescription;
import sun.jvm.hotspot.debugger.MachineDescriptionAMD64;
import sun.jvm.hotspot.debugger.MachineDescriptionIA64;
import sun.jvm.hotspot.debugger.MachineDescriptionIntelX86;
import sun.jvm.hotspot.debugger.MachineDescriptionSPARC32Bit;
import sun.jvm.hotspot.debugger.MachineDescriptionSPARC64Bit;
import sun.jvm.hotspot.debugger.NoSuchSymbolException;
import sun.jvm.hotspot.debugger.bsd.BsdDebuggerLocal;
import sun.jvm.hotspot.debugger.linux.LinuxDebuggerLocal;
import sun.jvm.hotspot.debugger.proc.ProcDebuggerLocal;
import sun.jvm.hotspot.debugger.remote.RemoteDebugger;
import sun.jvm.hotspot.debugger.remote.RemoteDebuggerClient;
import sun.jvm.hotspot.debugger.remote.RemoteDebuggerServer;
import sun.jvm.hotspot.debugger.windbg.WindbgDebuggerLocal;
import sun.jvm.hotspot.runtime.VM;
import sun.jvm.hotspot.types.TypeDataBase;
import sun.jvm.hotspot.utilities.PlatformInfo;
import sun.jvm.hotspot.utilities.UnsupportedPlatformException;

public class HotSpotAgent {
    private JVMDebugger debugger;
    private MachineDescription machDesc;
    private TypeDataBase db;
    private String os;
    private String cpu;
    private static final int PROCESS_MODE = 0;
    private static final int CORE_FILE_MODE = 1;
    private static final int REMOTE_MODE = 2;
    private int startupMode;
    private boolean isServer;
    private int pid;
    private String javaExecutableName;
    private String coreFileName;
    private String debugServerID;
    private String serverID;
    private String[] jvmLibNames;

    static void showUsage() {
    }

    public HotSpotAgent() {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                synchronized(HotSpotAgent.this) {
                    if (!HotSpotAgent.this.isServer) {
                        HotSpotAgent.this.detach();
                    }

                }
            }
        }));
    }

    public synchronized Debugger getDebugger() {
        return this.debugger;
    }

    public synchronized TypeDataBase getTypeDataBase() {
        return this.db;
    }

    public synchronized void attach(int processID) throws DebuggerException {
        if (this.debugger != null) {
            throw new DebuggerException("Already attached");
        } else {
            this.pid = processID;
            this.startupMode = 0;
            this.isServer = false;
            this.go();
        }
    }

    public synchronized void attach(String javaExecutableName, String coreFileName) throws DebuggerException {
        if (this.debugger != null) {
            throw new DebuggerException("Already attached");
        } else if (javaExecutableName != null && coreFileName != null) {
            this.javaExecutableName = javaExecutableName;
            this.coreFileName = coreFileName;
            this.startupMode = 1;
            this.isServer = false;
            this.go();
        } else {
            throw new DebuggerException("Both the core file name and Java executable name must be specified");
        }
    }

    public synchronized void attach(JVMDebugger d) throws DebuggerException {
        this.debugger = d;
        this.isServer = false;
        this.go();
    }

    public synchronized void attach(String remoteServerID) throws DebuggerException {
        if (this.debugger != null) {
            throw new DebuggerException("Already attached to a process");
        } else if (remoteServerID == null) {
            throw new DebuggerException("Debug server id must be specified");
        } else {
            this.debugServerID = remoteServerID;
            this.startupMode = 2;
            this.isServer = false;
            this.go();
        }
    }

    public synchronized boolean detach() throws DebuggerException {
        if (this.isServer) {
            throw new DebuggerException("Should not call detach() for server configuration");
        } else {
            return this.detachInternal();
        }
    }

    public synchronized void startServer(int processID, String uniqueID) {
        if (this.debugger != null) {
            throw new DebuggerException("Already attached");
        } else {
            this.pid = processID;
            this.startupMode = 0;
            this.isServer = true;
            this.serverID = uniqueID;
            this.go();
        }
    }

    public synchronized void startServer(int processID) throws DebuggerException {
        this.startServer(processID, (String)null);
    }

    public synchronized void startServer(String javaExecutableName, String coreFileName, String uniqueID) {
        if (this.debugger != null) {
            throw new DebuggerException("Already attached");
        } else if (javaExecutableName != null && coreFileName != null) {
            this.javaExecutableName = javaExecutableName;
            this.coreFileName = coreFileName;
            this.startupMode = 1;
            this.isServer = true;
            this.serverID = uniqueID;
            this.go();
        } else {
            throw new DebuggerException("Both the core file name and Java executable name must be specified");
        }
    }

    public synchronized void startServer(String javaExecutableName, String coreFileName) throws DebuggerException {
        this.startServer(javaExecutableName, coreFileName, (String)null);
    }

    public synchronized boolean shutdownServer() throws DebuggerException {
        if (!this.isServer) {
            throw new DebuggerException("Should not call shutdownServer() for client configuration");
        } else {
            return this.detachInternal();
        }
    }

    private boolean detachInternal() {
        if (this.debugger == null) {
            return false;
        } else {
            boolean retval = true;
            if (!this.isServer) {
                VM.shutdown();
            }

            Debugger dbg = null;
            DebuggerException ex = null;
            if (this.isServer) {
                try {
                    RMIHelper.unbind(this.serverID);
                } catch (DebuggerException var5) {
                    ex = var5;
                }

                dbg = this.debugger;
            } else if (this.startupMode != 2) {
                dbg = this.debugger;
            }

            if (dbg != null) {
                retval = dbg.detach();
            }

            this.debugger = null;
            this.machDesc = null;
            this.db = null;
            if (ex != null) {
                throw ex;
            } else {
                return retval;
            }
        }
    }

    private void go() {
        this.setupDebugger();
        this.setupVM();
    }

    private void setupDebugger() {
        if (this.startupMode != 2) {
            String alternateDebugger = System.getProperty("sa.altDebugger");
            if (this.debugger != null) {
                this.setupDebuggerExisting();
            } else if (alternateDebugger != null) {
                this.setupDebuggerAlternate(alternateDebugger);
            } else {
                try {
                    this.os = PlatformInfo.getOS();
                    this.cpu = PlatformInfo.getCPU();
                } catch (UnsupportedPlatformException var5) {
                    throw new DebuggerException(var5);
                }

                if (this.os.equals("solaris")) {
                    this.setupDebuggerSolaris();
                } else if (this.os.equals("win32")) {
                    this.setupDebuggerWin32();
                } else if (this.os.equals("linux")) {
                    this.setupDebuggerLinux();
                } else if (this.os.equals("bsd")) {
                    this.setupDebuggerBsd();
                } else {
                    if (!this.os.equals("darwin")) {
                        throw new DebuggerException("Operating system " + this.os + " not yet supported");
                    }

                    this.setupDebuggerDarwin();
                }
            }

            if (this.isServer) {
                RemoteDebuggerServer remote = null;

                try {
                    remote = new RemoteDebuggerServer(this.debugger);
                } catch (RemoteException var4) {
                    throw new DebuggerException(var4);
                }

                RMIHelper.rebind(this.serverID, remote);
            }
        } else {
            this.connectRemoteDebugger();
        }

    }

    private void setupVM() {
        try {
            if (this.os.equals("solaris")) {
                this.db = new HotSpotTypeDataBase(this.machDesc, new HotSpotSolarisVtblAccess(this.debugger, this.jvmLibNames), this.debugger, this.jvmLibNames);
            } else if (this.os.equals("win32")) {
                this.db = new HotSpotTypeDataBase(this.machDesc, new Win32VtblAccess(this.debugger, this.jvmLibNames), this.debugger, this.jvmLibNames);
            } else if (this.os.equals("linux")) {
                this.db = new HotSpotTypeDataBase(this.machDesc, new LinuxVtblAccess(this.debugger, this.jvmLibNames), this.debugger, this.jvmLibNames);
            } else if (this.os.equals("bsd")) {
                this.db = new HotSpotTypeDataBase(this.machDesc, new BsdVtblAccess(this.debugger, this.jvmLibNames), this.debugger, this.jvmLibNames);
            } else {
                if (!this.os.equals("darwin")) {
                    throw new DebuggerException("OS \"" + this.os + "\" not yet supported (no VtblAccess yet)");
                }

                this.db = new HotSpotTypeDataBase(this.machDesc, new BsdVtblAccess(this.debugger, this.jvmLibNames), this.debugger, this.jvmLibNames);
            }
        } catch (NoSuchSymbolException var4) {
            throw new DebuggerException("Doesn't appear to be a HotSpot VM (could not find symbol \"" + var4.getSymbol() + "\" in remote process)");
        }

        if (this.startupMode != 2) {
            this.debugger.configureJavaPrimitiveTypeSizes(this.db.getJBooleanType().getSize(), this.db.getJByteType().getSize(), this.db.getJCharType().getSize(), this.db.getJDoubleType().getSize(), this.db.getJFloatType().getSize(), this.db.getJIntType().getSize(), this.db.getJLongType().getSize(), this.db.getJShortType().getSize());
        }

        if (!this.isServer) {
            try {
                VM.initialize(this.db, this.debugger);
            } catch (DebuggerException var2) {
                throw var2;
            } catch (Exception var3) {
                throw new DebuggerException(var3);
            }
        }

    }

    private void setupDebuggerExisting() {
        this.os = this.debugger.getOS();
        this.cpu = this.debugger.getCPU();
        this.setupJVMLibNames(this.os);
        this.machDesc = this.debugger.getMachineDescription();
    }

    private void setupDebuggerAlternate(String alternateName) {
        try {
            Class c = Class.forName(alternateName);
            Constructor cons = c.getConstructor();
            this.debugger = (JVMDebugger)cons.newInstance();
            this.attachDebugger();
            this.setupDebuggerExisting();
        } catch (ClassNotFoundException var4) {
            throw new DebuggerException("Cannot find alternate SA Debugger: '" + alternateName + "'");
        } catch (NoSuchMethodException var5) {
            throw new DebuggerException("Alternate SA Debugger: '" + alternateName + "' has missing constructor.");
        } catch (InstantiationException var6) {
            throw new DebuggerException("Alternate SA Debugger: '" + alternateName + "' fails to initialise: ", var6);
        } catch (IllegalAccessException var7) {
            throw new DebuggerException("Alternate SA Debugger: '" + alternateName + "' fails to initialise: ", var7);
        } catch (InvocationTargetException var8) {
            throw new DebuggerException("Alternate SA Debugger: '" + alternateName + "' fails to initialise: ", var8);
        }

        System.err.println("Loaded alternate HotSpot SA Debugger: " + alternateName);
    }

    private void setupDebuggerSolaris() {
        this.setupJVMLibNamesSolaris();
        ProcDebuggerLocal dbg = new ProcDebuggerLocal((MachineDescription)null, true);
        this.debugger = dbg;
        this.attachDebugger();
        if (this.cpu.equals("x86")) {
            this.machDesc = new MachineDescriptionIntelX86();
        } else if (this.cpu.equals("sparc")) {
            int addressSize = dbg.getRemoteProcessAddressSize();
            if (addressSize == -1) {
                throw new DebuggerException("Error occurred while trying to determine the remote process's address size");
            }

            if (addressSize == 32) {
                this.machDesc = new MachineDescriptionSPARC32Bit();
            } else {
                if (addressSize != 64) {
                    throw new DebuggerException("Address size " + addressSize + " is not supported on SPARC");
                }

                this.machDesc = new MachineDescriptionSPARC64Bit();
            }
        } else {
            if (!this.cpu.equals("amd64")) {
                throw new DebuggerException("Solaris only supported on sparc/sparcv9/x86/amd64");
            }

            this.machDesc = new MachineDescriptionAMD64();
        }

        dbg.setMachineDescription(this.machDesc);
    }

    private void connectRemoteDebugger() throws DebuggerException {
        RemoteDebugger remote = (RemoteDebugger)RMIHelper.lookup(this.debugServerID);
        this.debugger = new RemoteDebuggerClient(remote);
        this.machDesc = ((RemoteDebuggerClient)this.debugger).getMachineDescription();
        this.os = this.debugger.getOS();
        this.setupJVMLibNames(this.os);
        this.cpu = this.debugger.getCPU();
    }

    private void setupJVMLibNames(String os) {
        if (os.equals("solaris")) {
            this.setupJVMLibNamesSolaris();
        } else if (os.equals("win32")) {
            this.setupJVMLibNamesWin32();
        } else if (os.equals("linux")) {
            this.setupJVMLibNamesLinux();
        } else if (os.equals("bsd")) {
            this.setupJVMLibNamesBsd();
        } else {
            if (!os.equals("darwin")) {
                throw new RuntimeException("Unknown OS type");
            }

            this.setupJVMLibNamesDarwin();
        }

    }

    private void setupJVMLibNamesSolaris() {
        this.jvmLibNames = new String[]{"libjvm.so"};
    }

    private void setupDebuggerWin32() {
        this.setupJVMLibNamesWin32();
        if (this.cpu.equals("x86")) {
            this.machDesc = new MachineDescriptionIntelX86();
        } else if (this.cpu.equals("amd64")) {
            this.machDesc = new MachineDescriptionAMD64();
        } else {
            if (!this.cpu.equals("ia64")) {
                throw new DebuggerException("Win32 supported under x86, amd64 and ia64 only");
            }

            this.machDesc = new MachineDescriptionIA64();
        }

        this.debugger = new WindbgDebuggerLocal(this.machDesc, !this.isServer);
        this.attachDebugger();
    }

    private void setupJVMLibNamesWin32() {
        this.jvmLibNames = new String[]{"jvm.dll"};
    }

    private void setupDebuggerLinux() {
        this.setupJVMLibNamesLinux();
        if (this.cpu.equals("x86")) {
            this.machDesc = new MachineDescriptionIntelX86();
        } else if (this.cpu.equals("ia64")) {
            this.machDesc = new MachineDescriptionIA64();
        } else if (this.cpu.equals("amd64")) {
            this.machDesc = new MachineDescriptionAMD64();
        } else if (this.cpu.equals("sparc")) {
            if (LinuxDebuggerLocal.getAddressSize() == 8) {
                this.machDesc = new MachineDescriptionSPARC64Bit();
            } else {
                this.machDesc = new MachineDescriptionSPARC32Bit();
            }
        } else {
            try {
                this.machDesc = (MachineDescription)Class.forName("sun.jvm.hotspot.debugger.MachineDescription" + this.cpu.toUpperCase()).newInstance();
            } catch (Exception var2) {
                throw new DebuggerException("Linux not supported on machine type " + this.cpu);
            }
        }

        LinuxDebuggerLocal dbg = new LinuxDebuggerLocal(this.machDesc, !this.isServer);
        this.debugger = dbg;
        this.attachDebugger();
    }

    private void setupJVMLibNamesLinux() {
        this.jvmLibNames = new String[]{"libjvm.so"};
    }

    private void setupDebuggerBsd() {
        this.setupJVMLibNamesBsd();
        if (this.cpu.equals("x86")) {
            this.machDesc = new MachineDescriptionIntelX86();
        } else {
            if (!this.cpu.equals("amd64") && !this.cpu.equals("x86_64")) {
                throw new DebuggerException("BSD only supported on x86/x86_64. Current arch: " + this.cpu);
            }

            this.machDesc = new MachineDescriptionAMD64();
        }

        BsdDebuggerLocal dbg = new BsdDebuggerLocal(this.machDesc, !this.isServer);
        this.debugger = dbg;
        this.attachDebugger();
    }

    private void setupJVMLibNamesBsd() {
        this.jvmLibNames = new String[]{"libjvm.so"};
    }

    private void setupDebuggerDarwin() {
        this.setupJVMLibNamesDarwin();
        if (!this.cpu.equals("amd64") && !this.cpu.equals("x86_64")) {
            throw new DebuggerException("Darwin only supported on x86_64. Current arch: " + this.cpu);
        } else {
            this.machDesc = new MachineDescriptionAMD64();
            BsdDebuggerLocal dbg = new BsdDebuggerLocal(this.machDesc, !this.isServer);
            this.debugger = dbg;
            this.attachDebugger();
        }
    }

    private void setupJVMLibNamesDarwin() {
        this.jvmLibNames = new String[]{"libjvm.dylib"};
    }

    private void attachDebugger() {
        if (this.startupMode == 0) {
            System.out.println("attachDebugger startupMode=0,pid=" + this.pid);
            this.debugger.attach(this.pid);
        } else {
            if (this.startupMode != 1) {
                throw new DebuggerException("Should not call attach() for startupMode == " + this.startupMode);
            }
            System.out.println("attachDebugger startupMode!=0,javaExecutableName=" + this.javaExecutableName +
                    ",coreFileName=" + this.coreFileName);
            this.debugger.attach(this.javaExecutableName, this.coreFileName);
        }

    }
}
