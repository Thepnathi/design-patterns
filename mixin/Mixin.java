package mixin;

// Imagine you have to use the below classes. But we do not want to extend them
//     - PersistentHelper (stores data on db)
//     - SecurityHelper (signs and encrypts data)
// Java allows one inheritance

class Object {}

interface IPersistantHelper {
    boolean saveObject(Object object);
    Object restoreObject(long id);
}

interface ISecurityHelper {
    boolean decryptObject(Object object);
    boolean encryptObject(Object object);
}

// Now add the interface implementation to mixin classes
class PersistentHelper implements IPersistantHelper {
    @Override
    public boolean saveObject(Object object) {
        return false;
    }

    @Override
    public Object restoreObject(long id) {
        return new Object();
    }
}

// Now add interface implementations to host class
class Person implements IPersistantHelper, ISecurityHelper {
    IPersistantHelper pHelp = new PersistentHelper();

    public final boolean saveObject(Object obj) {
        return pHelp.saveObject(obj);  // calling code in mixin
    }

    public final Object restoreObject(long id) {
        return pHelp.restoreObject(id);
    }

    public final boolean decryptObject(Object object) {
        return true;
    }
    public final boolean encryptObject(Object object) {
        return true;
    }
}

public class Mixin {
    
}
