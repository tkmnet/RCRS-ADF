package adf.communication.standard.bundle;

import adf.communication.CommunicationMessage;
import adf.communication.MessageBundle;

import java.util.List;

public class StandardMessageBundle extends MessageBundle
{
    @Override
    public List<Class<CommunicationMessage>> getMessageClassList() {
        return null;
    }
}
