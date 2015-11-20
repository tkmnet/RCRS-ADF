package adf.communication;

import adf.communication.standard.bundle.StandardMessageBundle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MessageManager
{
    private int standardMessageClassCount;
    private int customMessageClassCount;
    private HashMap<Integer, Class<CommunicationMessage>> messageClassMap;
    private HashMap<Class<CommunicationMessage>, Integer> messageClassIDMap;
    private List<CommunicationMessage> sendMessageList;

    public MessageManager()
    {
        standardMessageClassCount = 0;   // 00000
        customMessageClassCount = 16;    // 10000
        messageClassMap = new HashMap<>(32);
        sendMessageList = new ArrayList<>();
    }

    public boolean registerMessageClass(int index, Class<CommunicationMessage> messageClass)
    {
        if (index > 31)
        {
            throw new IllegalArgumentException("index maximum is 31");
        }
        if (messageClassMap.containsKey(index))
        {
            //throw new IllegalArgumentException("index(" + index + ") is already registrated");
            System.out.println("index(" + index + ") is already registrated/"+ messageClass.getName() +" is ignored");
            return false;
        }

        messageClassMap.put(index, messageClass);
        messageClassIDMap.put(messageClass, index);

        return true;
    }

    public void registerMessageBundle(MessageBundle messageBundle)
    {
        for (Class<CommunicationMessage> messageClass : messageBundle.getMessageClassList())
        {
            this.registerMessageClass(
                    (messageBundle.getClass().equals(StandardMessageBundle.class) ? standardMessageClassCount++ : customMessageClassCount++),
                    messageClass);
        }
    }

    public Class<CommunicationMessage> getMessageClass(int index)
    {
        if (!messageClassMap.containsKey(index))
        {
            return null;
        }

        return messageClassMap.get(index);
    }

    public int getMessageClassIndex(CommunicationMessage message)
    {
        if (!messageClassMap.containsValue(message.getClass()))
        {
            throw new IllegalArgumentException(message.getClass().getName() + " isnot registorated to manager");
        }

        return messageClassIDMap.get(message.getClass());
    }

    public void addMessage(CommunicationMessage message)
    {
        sendMessageList.add(message);
    }

    public List<CommunicationMessage> getSendMessageList()
    {
        return this.sendMessageList;
    }

    public void refresh()
    {
        sendMessageList.clear();
    }
}
