package adf.communication;

import java.util.List;

abstract public class MessageBundle
{
    abstract public List<Class<CommunicationMessage>> getMessageClassList();
}
