package quickfix.examples.forger;

import org.quickfixj.jmx.JmxExporter;
import quickfix.*;


import javax.management.JMException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Forger implements Application {


    public static void main(String[] args) throws IOException, ConfigError, JMException {

        System.out.println("Hello Forger");


        InputStream inputStream = null;
        if (args.length == 0) {
            inputStream = Forger.class.getResourceAsStream("banzai.cfg");
        } else if (args.length == 1) {
            inputStream = new FileInputStream(args[0]);
        }
        if (inputStream == null) {
            System.out.println("usage: " + Forger.class.getName() + " [configFile].");
            return;
        }
        SessionSettings settings = new SessionSettings(inputStream);
        inputStream.close();

        boolean logHeartbeats = Boolean.valueOf(System.getProperty("logHeartbeats", "true"));


        Application application = new Forger();
        MessageStoreFactory messageStoreFactory = new JdbcStoreFactory(settings);
        LogFactory logFactory = new ScreenLogFactory(true, true, true, logHeartbeats);
        MessageFactory messageFactory = new DefaultMessageFactory();

        Initiator initiator = null;
        initiator = new SocketInitiator(application, messageStoreFactory, settings, logFactory,
                messageFactory);

        JmxExporter exporter = new JmxExporter();
        exporter.register(initiator);

        initiator.start();


        quickfix.examples.forger.ui.Forger.main(args);

        System.in.read();
    }

    @Override
    public void onCreate(SessionID sessionId) {

    }

    @Override
    public void onLogon(SessionID sessionId) {

    }

    @Override
    public void onLogout(SessionID sessionId) {

    }

    @Override
    public void toAdmin(Message message, SessionID sessionId) {

    }

    @Override
    public void fromAdmin(Message message, SessionID sessionId) throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, RejectLogon {

    }

    @Override
    public void toApp(Message message, SessionID sessionId) throws DoNotSend {

    }

    @Override
    public void fromApp(Message message, SessionID sessionId) throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, UnsupportedMessageType {

    }
}
