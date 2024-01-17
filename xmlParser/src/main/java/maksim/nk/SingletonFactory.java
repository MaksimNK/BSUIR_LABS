package maksim.nk;

import javax.xml.parsers.DocumentBuilderFactory;

public class SingletonFactory {
    private static DocumentBuilderFactory instance;

    private SingletonFactory() {}

    public static synchronized DocumentBuilderFactory getInstance() {
        if (instance == null) {
            instance = DocumentBuilderFactory.newInstance();
            instance.setNamespaceAware(true);
            instance.setIgnoringComments(true);
        }
        return instance;
    }
}
